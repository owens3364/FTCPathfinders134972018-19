package org.firstinspires.ftc.teamcode.opmodes.TeleOp;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.activityadditions.GamepadDisconnectedAlert;
import org.firstinspires.ftc.teamcode.driversetcontrols.Controller;
import org.firstinspires.ftc.teamcode.activityadditions.FragmentDeploymentHelper;
import org.firstinspires.ftc.teamcode.activityadditions.TelemetryDisconnectedAlert;

import java.util.HashMap;
import java.util.Map;

class GenericTeleOp extends OpMode {
    //State of this class
    private boolean setup = false;

    //The Telemetry telemetry object should NEVER be accessed outside of the setData() and updateTelemetry()
    //While the Telemetry object is accessible outside of this class...
    //It SHOULD ONLY be accessed from this class' setData() and updateTelemetry() methods
    //This is to keep telemetry from going crazy from all of the update() calls
    //And all of the addData() calls
    //When multiple objects are calling the update methods at different times on different runloops
    //Then Telemetry goes crazy because it displays the set of data for each object before the next object calls update()
    //The purpose of this HashMap is to store ALL of the telemetry data in one place
    //Then this HashMap can be iterated through to show all of the telemetry KV pairs
    private HashMap<String, String> telemetryData;

    //Everything in this block goes to telemetry
    //State of OpMode execution
    private boolean initialized = false;
    private boolean initLoopRunning = false;
    private boolean started = false;
    private boolean startLoopRunning = false;
    private boolean stopped = false;

    boolean isSetup() {
        return setup;
    }

    boolean isInitialized() {
        return initialized;
    }

    boolean isInitLoopRunning() {
        return initLoopRunning;
    }

    boolean isStarted() {
        return started;
    }

    boolean isStartLoopRunning() {
        return startLoopRunning;
    }

    boolean isStopped() {
        return stopped;
    }

    //State of Control Schemes, also go to telemetry
    private String controller1Scheme;
    private String controller2Scheme;

    //These do not go to telemetry
    //Controllers
    private Controller controller1;
    private Controller controller2;

    Controller getController1() {
        return controller1;
    }

    Controller getController2() {
        return controller2;
    }


    boolean setup(Gamepad gamepad1, Gamepad gamepad2, Telemetry telemetry) {
        //This if statement is here to ensure the controllers are connected and initialized BEFORE TELEOP STARTS
        //It shows an onscreen alert if controllers are disconnected
        //It doesn't stop you from running the OpMode, but the OpMode won't do anything (in this class at least)
        //This eliminates NullPointerExcepions thrown because of this class.
        //By checking the public state of execution booleans of this class by its subclasses,
        //The subclasses can ensure that the OpMode doesn't do anything in their classes,
        //And that no NullPointerExceptions are thrown.

        if (gamepad1.getUser() == null || gamepad2.getUser() == null) {
            Log.d("DISCONNECTED", "GAMEPAD");
            new GamepadDisconnectedAlert().show(
                    FragmentDeploymentHelper.prepareForFragmentDeployment(
                            GamepadDisconnectedAlert.CONTROLLER_DISCONNECTED_DIALOG),
                    GamepadDisconnectedAlert.CONTROLLER_DISCONNECTED_DIALOG);
            return false;
        }
        Log.d("CONNECTED", "GAMEPAD");

        //This if statement is here to ensure Telemetry is connected and initialized BEFORE TELEOP STARTS
        //It shows an onscreen alert if Telemetry is disconnected
        //It doesn't stop you from running the OpMode, but the OpMode won't do anything (in this class at least)
        //This eliminates NullPointerExceptions thrown because of this class.
        //By checking the public state of execution booleans of this class by its subclasses,
        //The subclasses can ensure that the OpMode doesn't do anything in their classes,
        //And that no NullPointerExceptions are thrown.
        if (telemetry == null) {
            new TelemetryDisconnectedAlert().show(
                    FragmentDeploymentHelper.prepareForFragmentDeployment(
                            TelemetryDisconnectedAlert.TELEMETRY_DISCONNECTED_DIALOG),
                    TelemetryDisconnectedAlert.TELEMETRY_DISCONNECTED_DIALOG);
            return false;
        }

        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        this.telemetry = telemetry;
        this.telemetryData = new HashMap<>();
        setup = true;
        return true;
    }

    @Override
    public void init() {
        if (setup) {
            controller1 = new Controller(gamepad1);
            controller1.setDefaultScheme();
            controller1Scheme = controller1.getControlScheme().name();
            controller2 = new Controller(gamepad2);
            controller2.setDefaultScheme();
            controller2Scheme = controller2.getControlScheme().name();
            initialized = true;
            updateTelemetry();
        }
    }

    @Override
    public void init_loop() {
        if (initialized) {
            initLoopRunning = true;
            if (controller1.dpadRight()) {
                controller1.incrScheme();
            } else if (controller1.dpadLeft()) {
                controller1.decrScheme();
            } else if (controller2.dpadRight()) {
                controller2.incrScheme();
            } else if (controller2.dpadLeft()) {
                controller2.decrScheme();
            }
            updateTelemetry();
        }
    }

    @Override
    public void start() {
        if (initLoopRunning) {
            initLoopRunning = false;
            started = true;
            updateTelemetry();
        }
    }

    @Override
    public void loop() {
        if (started) {
            startLoopRunning = true;
            updateTelemetry();
        }
    }

    @Override
    public void stop() {
        if (initLoopRunning || startLoopRunning) {
            startLoopRunning = false;
            stopped = true;
            updateTelemetry();
        }
    }

    void updateTelemetry() {
        if (setup) {
            addData("Initialized", String.valueOf(initialized));
            addData("Running init loop", String.valueOf(initLoopRunning));
            addData("Started", String.valueOf(started));
            addData("Running start loop", String.valueOf(startLoopRunning));
            addData("Stopped", String.valueOf(stopped));
            addData("Controller 1 Scheme", controller1Scheme);
            addData("Controller 2 Scheme", controller2Scheme);

            for (Map.Entry<String, String> entry : telemetryData.entrySet()) {
                telemetry.addData(entry.getKey(), entry.getValue());
            }

            telemetry.update();
        }
    }

    void addData(String key, String value) {
        if (setup) {
            telemetryData.put(key, value);
        }
    }

    void addData(String key, double value) {
        if (setup) {
            telemetryData.put(key, String.valueOf(value));
        }
    }

    boolean setData(String key, String value) {
        if (setup) {
            telemetryData.replace(key, value);
            return true;
        }
        return false;
    }

    boolean setData(String key, double value) {
        if (setup) {
            telemetryData.replace(key, String.valueOf(value));
            return true;
        }
        return false;
    }

    String getData(String key) {
        if (setup) {
            return telemetryData.get(key);
        }
        return null;
    }

    boolean removeData(String key) {
        if (setup) {
            telemetryData.remove(key);
            return true;
        }
        return false;
    }
}