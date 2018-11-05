package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.DriverSetControls.Controller;
import org.firstinspires.ftc.teamcode.FtcRobotControllerActivityAdditions.FragmentDeploymentHelper;
import org.firstinspires.ftc.teamcode.FtcRobotControllerActivityAdditions.GamepadDisconnectedAlert;
import org.firstinspires.ftc.teamcode.FtcRobotControllerActivityAdditions.TelemetryDisconnectedAlert;

class GenericTeleOp extends OpMode {
    //State of this class
    private boolean setup = false;

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
    //State of Control Schemes
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
        if (gamepad1 == null || gamepad2 == null) {
            new GamepadDisconnectedAlert().show(
                    FragmentDeploymentHelper.prepareForFragmentDeployment(
                            GamepadDisconnectedAlert.CONTROLLER_DISCONNECTED_DIALOG),
                    GamepadDisconnectedAlert.CONTROLLER_DISCONNECTED_DIALOG);
            return false;
        }
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

    private void updateTelemetry() {
        if (setup) {
            telemetry.addData("Initialized", initialized);
            telemetry.addData("Running init loop", initLoopRunning);
            telemetry.addData("Started", started);
            telemetry.addData("Running start loop", startLoopRunning);
            telemetry.addData("Stopped", stopped);
            telemetry.addData("Controller 1 Scheme", controller1Scheme);
            telemetry.addData("Controller 2 Scheme", controller2Scheme);
            telemetry.update();
        }
    }
}