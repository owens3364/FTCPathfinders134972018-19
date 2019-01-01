package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.memory.ManagementOptions;
import org.firstinspires.ftc.teamcode.memory.MemoryManagement;

import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;

public final class VisionUtils {
    // ///XXX***DO NOT CHANGE***XXX\\\
    //Constants for navigation
    private static final float MM_PER_INCH = 25.4f;
    //Length/Height not necessary, field is square
    private static final float MM_FTC_FIELD_WIDTH = (12*6) * MM_PER_INCH;
    //Height of center of VuMark targets (Blue-Rover, Red-Footprint, Front-Craters, Back-Space
    private static final float MM_TARGET_HEIGHT = (6) * MM_PER_INCH;

    /**
     * Create a transformation matrix describing where the phone is on the robot.
     *
     * The coordinate frame for the robot looks the same as the field.
     * The robot's "forward" direction is facing out along X axis,
     * with the LEFT side facing out along the Y axis.
     * Z is UP on the robot.  This equates to a bearing angle of Zero degrees.
     *
     * The phone starts out lying flat, with the screen facing
     * up and with the physical top of the phone
     * pointing to the LEFT side of the Robot.
     * It's very important when you test this code that the top of the
     * camera is pointing to the left side of the  robot.
     * The rotation angles don't work if you flip the phone.
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
     * In this example, it is centered (left to right),
     * but 110 mm forward of the middle of the robot, and 200 mm above ground level.
     */

    //TODO: MODIFY FOR PHONE'S POSITION
    // eg: Camera is 110 mm in front of robot center
    private static final int CAMERA_FORWARD_DISPLACEMENT = 110;
    // eg: Camera is 200 mm above ground
    private static final int CAMERA_VERTICAL_DISPLACEMENT = 200;
    // eg: Camera is ON the robot's center line
    private static final int CAMERA_LEFT_DISPLACEMENT = 0;

    //Constants for TensorFlow Object Detection
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String TFOD_MONITOR_VIEW_ID = "tfodMonitorViewId";
    //Constants for Vuforia
    /////XXX***DO NOT CHANGE***XXX\\\\\
    //Vuforia Development Key
    private static final String VUFORIA_KEY = "AYkrQsv/////AAABmdvvwseKyUaBtmMB68LAeOcSWZgNWbDYY" +
            "juWtsW0qGDzLQT/QqFCU8yfFXAYA9EzKAEvDrkzq4CYzUh0VmKXOSRsBxYVyum41hbPswQ918OcsByS7YvG" +
            "pvHfRsKKR5GMIEUF0WPwmby0cqdJSeBqP4xX5BrolZIFSqBnOw0bboUdP44uoj7ZvBPeX7fWpFH5VGgYD4d" +
            "Pq1SrqC2kC30YGdwKp7vWtXjBRtEXa8jbQz4nztQCPvXvicsjqeVPkTkp8WWaTwasosQ/dQwlEoBN8dNImd" +
            "QEshWXOnrHy3k4YhdW2FBwHsYdCi4vRZhJX5gBSEB9+Aao3fPe0vNkMcz0HzKszJYiUcbGN+aFcRKdN9q7";

    private static final String VUFORIA_MONITOR_VIEW_ID = "cameraMonitorViewId";
    private static final String roverRuckusTargetsFilename = "RoverRuckus";
    private static final String blueRoverName = "Blue-Rover";
    private static final String redFootprintName = "Red-Footprint";
    private static final String frontCratersName = "Front-Craters";
    private static final String backSpaceName = "Back-Space";
    //Constants for both
    private static final String ID_TYPE = "id";

    //Last robot location
    private static OpenGLMatrix lastLocation = null;

    //Helper class for setting up vuforia engine
    private VuforiaLocalizer vuforia = null;
    //TensorFlow Object Detection
    private TFObjectDetector tfod = null;


    //Targets
    private VuforiaTrackable blueRover = null;
    private VuforiaTrackable redFootprint = null;
    private VuforiaTrackable frontCraters = null;
    private VuforiaTrackable backSpace = null;
    private VuforiaTrackable[]  trackables;


    //To prevent null pointer exceptions after Vuforia and TFOD are deactivated
    private boolean vuforiaActivated;
    private boolean tfodActivated;
    private final HardwareMap map;

    //To ensure there aren't multiple instances of visionUtils
    private static boolean alreadyInitialized = false;
    private static VisionUtils activeInstance = null;

    //Methods for public use
    public static VisionUtils getInstance(HardwareMap map) {
        if (!alreadyInitialized) {
            activeInstance = new VisionUtils(map);
            alreadyInitialized = true;
        }
        return activeInstance;
    }

    private VisionUtils(HardwareMap map) {
        this.map = map;
        vuforiaActivated = activateVuforia();
        //Initialize TensorFlow Object Detection
        tfodActivated = activateTFOD();
    }

    public VisionData[] checkForTargets() {
        vuforiaActivated = activateVuforia();
        VisionData[] visionData = new VisionData[4];
        for (int i = 0; i < trackables.length; i++) {
            VuforiaTrackable trackable = trackables[i];
            if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
                //If the robot location is the same this returns null
                OpenGLMatrix robotLocationTransform = (
                        (VuforiaTrackableDefaultListener)trackable.getListener())
                        .getUpdatedRobotLocation();
                if (robotLocationTransform != null) {
                    lastLocation = robotLocationTransform;
                }
                visionData[i] = new VisionData(lastLocation.getTranslation(),
                        Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES),
                        getVuMarkType(i));
            }
            visionData[i] = null;
        }
        return visionData;
    }

    //Returns a Thread that calls checkForTargets()
    //at a regular interval and writes the data to a List
    //OR returns a Thread that calls checkForTargets()
    //and writes the data to a VisionData object
    //OR return a Thread that calls getGoldMineralPosition()
    //at a regular interval and writes the data to a List
    //OR returns a Thread that calls getGoldMineralPosition()
    //and writes the data to a GoldMineralPosition object
    public VisionCheckerThread getRegularTargetChecker(long checkingInterval,
                                                       VisionCheckerOutputType outputType,
                                                       CVCompatibleClass caller) {
        return new VisionCheckerThread(this, checkingInterval, caller, outputType);
    }

    public GoldMineralPosition getGoldMineralPosition() {
        vuforiaActivated = activateVuforia();
        tfodActivated = activateTFOD();
        GoldMineralPosition goldMineralPosition = null;
        if (tfod != null) {
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                if (updatedRecognitions.size() == 3) {
                    int goldMineralX = -1;
                    int silverMineral1X = -1;
                    int silverMineral2X = -1;
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                        } else if (silverMineral1X == -1) {
                            silverMineral1X = (int) recognition.getLeft();
                        } else {
                            silverMineral2X = (int) recognition.getLeft();
                        }
                    }
                    if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                        if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                            goldMineralPosition = GoldMineralPosition.LEFT;
                        } else if (goldMineralX > silverMineral1X
                                && goldMineralX > silverMineral2X) {
                            goldMineralPosition = GoldMineralPosition.RIGHT;
                        } else {
                            goldMineralPosition = GoldMineralPosition.CENTER;
                        }
                    }
                }
            }
        }
        return goldMineralPosition;
    }

    private boolean activateVuforia() {
        if (!vuforiaActivated) {
            //Initialize Vuforia and the Trackables
            //Generate working vuforia with the VUFORIA_KEY and rear camera
            vuforia = generateVuforiaEngine(map);
            //Initialize the targets, their locations, the camera's location, etc.
            //This doesn't find targets, just defines where trackables are on field
            initializeTrackables();
            locateTrackables();
            alertTrackablesToCameraLocation(locateCamera());
            Vuforia.setHint(com.vuforia.HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 3);
        }
        return true;
    }

    private boolean activateTFOD() {
        if (!tfodActivated) {
            if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
                tfod = generateTFODEngine(map);
                return true;
            }
            return false;
        }
        return true;
    }

    public void deactivateTFOD() {
        tfod.deactivate();
        tfod.shutdown();
    }

    public void deactivateVuforiaAndTFOD() {
        deactivateTFOD();
        Vuforia.onPause();
        Vuforia.deinit();
        new Thread(() -> {
            MemoryManagement management = new MemoryManagement();
            if (management.initialize(ManagementOptions.EXTREME)) {
                management.free(tfod);
                management.free(vuforia);
                management.free(lastLocation);
                management.free(blueRover);
                management.free(redFootprint);
                management.free(frontCraters);
                management.free(backSpace);
                management.free(trackables);
            }
        }).start();
    }

    //Private helper methods
    private VuforiaLocalizer generateVuforiaEngine(HardwareMap map) {
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.
                Parameters(map
                .appContext
                .getResources()
                .getIdentifier(VUFORIA_MONITOR_VIEW_ID, ID_TYPE,
                        map.appContext.getPackageName()));
        params.vuforiaLicenseKey = VUFORIA_KEY;
        params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        return ClassFactory.getInstance().createVuforia(params);
    }

    private void initializeTrackables() {
        VuforiaTrackables roverRuckusTargets = vuforia.
                loadTrackablesFromAsset(roverRuckusTargetsFilename);
        blueRover = roverRuckusTargets.get(0);
        blueRover.setName(blueRoverName);
        redFootprint = roverRuckusTargets.get(1);
        redFootprint.setName(redFootprintName);
        frontCraters = roverRuckusTargets.get(2);
        frontCraters.setName(frontCratersName);
        backSpace = roverRuckusTargets.get(3);
        backSpace.setName(backSpaceName);
        trackables = new VuforiaTrackable[] {blueRover, redFootprint, frontCraters, backSpace};
    }

    private void locateTrackables() {
        blueRover.setLocation(
                OpenGLMatrix.translation(
                        0, MM_FTC_FIELD_WIDTH, MM_TARGET_HEIGHT)
                        .multiplied(Orientation.getRotationMatrix(EXTRINSIC,
                                XYZ,
                                DEGREES,
                                90, 0, 0)));
        redFootprint.setLocation(
                OpenGLMatrix.translation(
                        0, -MM_FTC_FIELD_WIDTH, MM_TARGET_HEIGHT)
                        .multiplied(Orientation.getRotationMatrix(EXTRINSIC,
                                XYZ,
                                DEGREES,
                                90, 0, 180)));
        frontCraters.setLocation(
                OpenGLMatrix.translation(
                        -MM_FTC_FIELD_WIDTH, 0, MM_TARGET_HEIGHT)
                        .multiplied(Orientation.getRotationMatrix(EXTRINSIC,
                                XYZ,
                                DEGREES,
                                90, 0 , 90)));
        backSpace.setLocation(
                OpenGLMatrix.translation(
                        MM_FTC_FIELD_WIDTH, 0, MM_TARGET_HEIGHT)
                        .multiplied(Orientation.getRotationMatrix(EXTRINSIC,
                                XYZ,
                                DEGREES,
                                90, 0, -90)));
    }

    private OpenGLMatrix locateCamera() {
        return OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT,
                        CAMERA_LEFT_DISPLACEMENT,
                        CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC,
                        YZX,
                        DEGREES,
                        -90,
                        0,
                        0)); //TODO: MODIFY FOR PHONE'S ORIENTATION
    }

    private void alertTrackablesToCameraLocation(OpenGLMatrix cameraLocation) {
        for (VuforiaTrackable trackable : trackables)
        {
            ((VuforiaTrackableDefaultListener)trackable.getListener())
                    .setPhoneInformation(cameraLocation, VuforiaLocalizer.CameraDirection.BACK);
        }
    }

    private TFObjectDetector generateTFODEngine(HardwareMap map) {
        TFObjectDetector.Parameters parameters = new TFObjectDetector
                .Parameters(map
                .appContext
                .getResources()
                .getIdentifier(TFOD_MONITOR_VIEW_ID, ID_TYPE,
                        map.appContext.getPackageName()));
        TFObjectDetector tfod = ClassFactory.getInstance()
                .createTFObjectDetector(parameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
        tfod.activate();
        return tfod;
    }

    private VuMarkType getVuMarkType(int vuMarkType) {
        switch (vuMarkType) {
            case 0:
                return VuMarkType.BLUE_ROVER;
            case 1:
                return VuMarkType.RED_FOOTPRINT;
            case 2:
                return VuMarkType.FRONT_CRATERS;
            case 3:
                return VuMarkType.BACK_SPACE;
        }
        return null;
    }
}
