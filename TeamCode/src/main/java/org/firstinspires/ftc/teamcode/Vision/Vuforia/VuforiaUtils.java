package org.firstinspires.ftc.teamcode.Vision.Vuforia;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public final class VuforiaUtils {
    // ///XXX***DO NOT CHANGE***XXX\\\
    //Constants for navigation
    private static final float mmPerInch = 25.4f;
    private static final float mmFTCFieldWidth = (12*6) * mmPerInch; //Length/Height not necesssary, field is square
    private static final float mmTargetHeight = (6) * mmPerInch; //Height of center of VuMark targets (Blue-Rover, Red-Footprint, Front-Craters, Back-Space
    //Constants for Vuforia
    // ///XXX***DO NOT CHANGE***XXX\\\
    //Vuforia Development Key
    private static final String VUFORIA_KEY = "AYkrQsv/////AAABmdvvwseKyUaBtmMB68LAeOcSWZgNWbDYYjuWtsW0qGDzLQT/QqFCU8yfFXAYA9EzKAEvDrkzq4CYzUh0VmKXOSRsBxYVyum41hbPswQ918OcsByS7YvGpvHfRsKKR5GMIEUF0WPwmby0cqdJSeBqP4xX5BrolZIFSqBnOw0bboUdP44uoj7ZvBPeX7fWpFH5VGgYD4dPq1SrqC2kC30YGdwKp7vWtXjBRtEXa8jbQz4nztQCPvXvicsjqeVPkTkp8WWaTwasosQ/dQwlEoBN8dNImdQEshWXOnrHy3k4YhdW2FBwHsYdCi4vRZhJX5gBSEB9+Aao3fPe0vNkMcz0HzKszJYiUcbGN+aFcRKdN9q7";

    //Last robot location
    private static OpenGLMatrix lastLocation = null;
    //Helper class for setting up vuforia engine
    private static VuforiaLocalizer vuforia;

    //Queue of images vuforia takes
    private static BlockingQueue<VuforiaLocalizer.CloseableFrame> queue;

    //Targets
    private static VuforiaTrackable blueRover = null;
    private static VuforiaTrackable redFootprint = null;
    private static VuforiaTrackable frontCraters = null;
    private static VuforiaTrackable backSpace = null;
    private static List<VuforiaTrackable> trackables = new ArrayList<>();

    //Telemetry
    private static Telemetry telemetry = null;

    //Used to determine if this class has been initialized
    //Used only by org.firstinspires.ftc.teamcode.Vision.JavaCV.JCVUtils.java
    public static boolean initialized = false;

    //Methods for public use
    public static void initialize(HardwareMap map, Telemetry tele) {
        //Get the telemetry object so target and robot visibility and location data can be added to it
        telemetry = tele;
        //Generate working vuforia with the VUFORIA_KEY and rear camera
        vuforia = generateVuforiaEngine(map);
        //Initialize the targets, their locations, the camera's location, etc.
        //This doesn't find targets, just defines where trackables are on field
        initializeTrackables();
        locateTrackables();
        alertTrackablesToCameraLocation(locateCamera());
        Vuforia.setHint(com.vuforia.HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 3);
        initialized = true;
    }

    public static VuforiaLocalizer.CloseableFrame getNextFrame() {
        return queue.poll();
    }

    public static VuforiaData checkForTargets() {
        VuforiaData vuforiaData = null;
        boolean targetVisible = false;
        for (VuforiaTrackable trackable : trackables) {
            if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
                targetVisible = true;
                telemetry.addData("Visible Target", trackable.getName());
                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
                //If the robot location is the same this returns null
                if (robotLocationTransform != null) {
                    lastLocation = robotLocationTransform;
                }
                break;
            }
        }

        if (targetVisible) {
            //Position of robot in inches
            VectorF position = lastLocation.getTranslation();
            //Rotation of the robot in degrees
            Orientation orientation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
            vuforiaData = new VuforiaData(position, orientation);
            telemetry.addData("Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f",
                    position.get(0) / mmPerInch, position.get(1) / mmPerInch, position.get(2) / mmPerInch);
            telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", orientation.firstAngle, orientation.secondAngle, orientation.thirdAngle);
        }
        else {
            telemetry.addData("Visible Target", "None");
        }
        telemetry.update();
        return vuforiaData;
    }

    //Returns a thread that calls checkForTargets at a regular interval and writes the data to a LinkedList
    //OR returns a thread that calls checkForTargets at a regular interval and stops as soon as a target is located, writing the data to a VuforiaData object
    public static VuforiaCheckerThread getRegularTargetChecker(long checkingInterval, VuforiaCheckerThreadOutputType outputType, CVCompatibleClass caller) {
        return new VuforiaCheckerThread(checkingInterval, outputType, caller);
    }

    //Private helper methods
    private static VuforiaLocalizer generateVuforiaEngine(HardwareMap map) {
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(map.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", map.appContext.getPackageName()));
        params.vuforiaLicenseKey = VUFORIA_KEY;
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        VuforiaLocalizer mvuforia = ClassFactory.getInstance().createVuforia(params);
        mvuforia.setFrameQueueCapacity(8);
        queue = mvuforia.getFrameQueue();
        return mvuforia;
    }

    private static void initializeTrackables() {
        VuforiaTrackables roverRuckusTargets = vuforia.loadTrackablesFromAsset("RoverRuckus");
        blueRover = roverRuckusTargets.get(0);
        blueRover.setName("Blue-Rover");
        redFootprint = roverRuckusTargets.get(1);
        redFootprint.setName("Red-Footprint");
        frontCraters = roverRuckusTargets.get(2);
        frontCraters.setName("Front-Craters");
        backSpace = roverRuckusTargets.get(3);
        backSpace.setName("Back-Space");
        trackables.addAll(roverRuckusTargets);
    }

    private static void locateTrackables() {
        blueRover.setLocation(OpenGLMatrix.translation(0, mmFTCFieldWidth, mmTargetHeight).multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 0)));
        redFootprint.setLocation(OpenGLMatrix.translation(0, -mmFTCFieldWidth, mmTargetHeight).multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, 180)));
        frontCraters.setLocation(OpenGLMatrix.translation(-mmFTCFieldWidth, 0, mmTargetHeight).multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0 , 90)));
        backSpace.setLocation(OpenGLMatrix.translation(mmFTCFieldWidth, 0, mmTargetHeight).multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));
    }

    private static OpenGLMatrix locateCamera() {
        /**
         * Create a transformation matrix describing where the phone is on the robot.
         *
         * The coordinate frame for the robot looks the same as the field.
         * The robot's "forward" direction is facing out along X axis, with the LEFT side facing out along the Y axis.
         * Z is UP on the robot.  This equates to a bearing angle of Zero degrees.
         *
         * The phone starts out lying flat, with the screen facing Up and with the physical top of the phone
         * pointing to the LEFT side of the Robot.  It's very important when you test this code that the top of the
         * camera is pointing to the left side of the  robot.  The rotation angles don't work if you flip the phone.
         *
         * If using the rear (High Res) camera:
         * We need to rotate the camera around it's long axis to bring the rear camera forward.
         * This requires a negative 90 degree rotation on the Y axis
         *
         * If using the Front (Low Res) camera
         * We need to rotate the camera around it's long axis to bring the FRONT camera forward.
         * This requires a Positive 90 degree rotation on the Y axis
         *
         * Next, translate the camera lens to where it is on the robot.
         * In this example, it is centered (left to right), but 110 mm forward of the middle of the robot, and 200 mm above ground level.
         */

        //MODIFY FOR PHONE's POSITION!!!!!!!!!!!!!
        final int CAMERA_FORWARD_DISPLACEMENT = 110; // eg: Camera is 110 mm in front of robot center
        final int CAMERA_VERTICAL_DISPLACEMENT = 200; // eg: Camera is 200 mm above ground
        final int CAMERA_LEFT_DISPLACEMENT = 0; // eg: Camera is ON the robot's center line

        return OpenGLMatrix.translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT).multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES,-90, 0, 0));
    }

    private static void alertTrackablesToCameraLocation(OpenGLMatrix cameraLocation) {
        for (VuforiaTrackable trackable : trackables)
        {
            ((VuforiaTrackableDefaultListener)trackable.getListener()).setPhoneInformation(cameraLocation, VuforiaLocalizer.CameraDirection.BACK);
        }
    }
}
