package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.controls.ControlScheme;
import org.firstinspires.ftc.teamcode.controls.Controller;
import org.firstinspires.ftc.teamcode.activity.FragmentDeploymentHelper;
import org.firstinspires.ftc.teamcode.activity.TelemetryDisconnectedAlert;
import org.firstinspires.ftc.teamcode.opmodes.DataRemovalHashMapOperation;
import org.firstinspires.ftc.teamcode.opmodes.DataRetrievalHashMapOperation;
import org.firstinspires.ftc.teamcode.opmodes.DataSetHashMapOperation;

import java.util.LinkedHashMap;
import java.util.LinkedList;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
class GenericTeleOp extends OpMode {

    private static final String LOG_CONNECTED = "CONNECTED";
    private static final String LOG_DISCONNECTED = "DISCONNECTED";
    private static final String GAMEPAD = "GAMEPAD";
    private static final String TELEMETRY = "TELEMETRY";

    private static final String TELEMETRY_STATE_OF_EXECUTION = "State of Execution";
    private static final String TELEMETRY_CONTROLLER_1_SCHEME = "Controller 1 Scheme";
    private static final String TELEMETRY_CONTROLLER_2_SCHEME = "Controller 2 Scheme";

    private static final String INDIVIDUALIZED_SCHEMES = "Individualized Control Schemes";
    private static final String INDIVIDUALIZED_STICK_SCALES = "Individualized Stick Scales";
    private static final String INDIVIDUALIZED_TRIGGER_SCALES = "Individualized Trigger Scales";

    //State of this class
    private boolean setup = false;

    final boolean isSetup() {
        return setup;
    }

    //The Telemetry telemetry object should NEVER be accessed outside of the setData() and
    // updateTelemetry()
    //While the Telemetry object is accessible outside of this class...
    //It SHOULD ONLY be accessed from this class' setData() and updateTelemetry() methods
    //This is to keep telemetry from going crazy from all of the update() calls
    //And all of the addData() calls
    //When multiple objects are calling the update methods at different times on different runloops
    //Then Telemetry goes crazy because it displays the set of data for each object before the next
    // object calls update()
    //The purpose of this HashMap is to store ALL of the telemetry data in one place
    //Then this HashMap can be iterated through to show all of the telemetry KV pairs
    private LinkedHashMap<String, String> telemetryData;

    //Everything in this block goes to telemetry
    //State of OpMode execution

    private StateOfExecution stateOfExecution = StateOfExecution.NOT_YET_INITIALIZED;

    StateOfExecution getStateOfExecution() {
        return stateOfExecution;
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


    final boolean setup(Gamepad gamepad1, Gamepad gamepad2, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.telemetryData = new LinkedHashMap<>();
        addData("Status...", "Telemetry initialized and setup called");
        //This if statement is here to ensure the controllers are connected and initialized
        // BEFORE TELEOP STARTS
        //It shows an onscreen alert if controllers are disconnected
        //It doesn't stop you from running the OpMode, but the OpMode won't do anything
        // (in this class at least)
        //This eliminates NullPointerExcepions thrown because of this class.
        //By checking the public state of execution booleans of this class by its subclasses,
        //The subclasses can ensure that the OpMode doesn't do anything in their classes,
        //And that no NullPointerExceptions are thrown.
/*
        if (gamepad1.getUser() == null || gamepad2.getUser() == null) {
            LogUtils.logError(LOG_DISCONNECTED + GAMEPAD);
            new GamepadDisconnectedAlert().show(
                    FragmentDeploymentHelper.prepareForFragmentDeployment(
                            GamepadDisconnectedAlert.CONTROLLER_DISCONNECTED_DIALOG),
                    GamepadDisconnectedAlert.CONTROLLER_DISCONNECTED_DIALOG);
            setData("Status...", "Through gamepad checks");
            return setup;
        }
        */
        setData("Status...", "Gamepads connected");


        //This if statement is here to ensure Telemetry is connected and initialized
        // BEFORE TELEOP STARTS
        //It shows an onscreen alert if Telemetry is disconnected
        //It doesn't stop you from running the OpMode, but the OpMode won't do anything
        // (in this class at least)
        //This eliminates NullPointerExceptions thrown because of this class.
        //By checking the public state of execution booleans of this class by its subclasses,
        //The subclasses can ensure that the OpMode doesn't do anything in their classes,
        //And that no NullPointerExceptions are thrown.
        if (telemetry == null) {
            new TelemetryDisconnectedAlert().show(
                    FragmentDeploymentHelper.prepareForFragmentDeployment(
                            TelemetryDisconnectedAlert.TELEMETRY_DISCONNECTED_DIALOG),
                    TelemetryDisconnectedAlert.TELEMETRY_DISCONNECTED_DIALOG);
            addData("Status...", "Through telemetry checks");
            return setup;
        }
        setData("Status...", "Telemetry connected");
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        setup = true;
        return true;
    }

    @Override
    public void init() {
        if (setup) {
            controller1 = new Controller(gamepad1);
            controller2 = new Controller(gamepad2);
            ControlScheme scheme1 = controller1.getControlScheme();
            if (scheme1 != null) {
                controller1Scheme = scheme1.toString();
            } else {
                controller1Scheme = INDIVIDUALIZED_SCHEMES;
            }
            ControlScheme scheme2 = controller2.getControlScheme();
            if (scheme2 != null) {
                controller2Scheme = scheme2.toString();
            } else {
                controller2Scheme = INDIVIDUALIZED_SCHEMES;
            }
            stateOfExecution = StateOfExecution.INITIALIZED;
            updateTelemetry();
        }
    }

    @Override
    public void init_loop() {
        if (stateOfExecution == StateOfExecution.INITIALIZED) {
            stateOfExecution = StateOfExecution.INIT_LOOP_RUNNING;
        }
        if (stateOfExecution == StateOfExecution.INIT_LOOP_RUNNING) {
            adjustControlScheme();
            updateTelemetry();
        }
    }

    @Override
    public void start() {
        if (stateOfExecution == StateOfExecution.INIT_LOOP_RUNNING) {
            stateOfExecution = StateOfExecution.STARTED;
            updateTelemetry();
        }
    }

    @Override
    public void loop() {
        if (stateOfExecution == StateOfExecution.STARTED) {
            stateOfExecution = StateOfExecution.START_LOOP_RUNNING;
        }
        if (stateOfExecution == StateOfExecution.START_LOOP_RUNNING) {
            adjustControlScheme();
            updateTelemetry();
        }
    }

    @Override
    public void stop() {
        if (stateOfExecution == StateOfExecution.INIT_LOOP_RUNNING ||
                stateOfExecution == StateOfExecution.START_LOOP_RUNNING) {
            stateOfExecution = StateOfExecution.STOPPED;
            updateTelemetry();
        }
    }

    private void adjustControlScheme() {
        if (setup) {
            if (controller1.dpadRight()) {
                controller1.incrScheme();
            } else if (controller1.dpadLeft()) {
                controller1.decrScheme();
            } else if (controller2.dpadRight()) {
                controller2.incrScheme();
            } else if (controller2.dpadLeft()) {
                controller2.decrScheme();
            }
        }
    }

    final void updateTelemetry() {
        if (setup) {
            addData(TELEMETRY_STATE_OF_EXECUTION, stateOfExecution.name());
            addData(TELEMETRY_CONTROLLER_1_SCHEME, controller1Scheme);
            addData(TELEMETRY_CONTROLLER_2_SCHEME, controller2Scheme);

            for (LinkedHashMap.Entry<String, String> entry : telemetryData.entrySet()) {
                telemetry.addData(entry.getKey(), entry.getValue());
            }

            telemetry.update();
        }
    }

    final void cycleTelemetry(DataSetHashMapOperation<String, String, Boolean> operation, String[] keys, String[] values) {
        telemetryOperation(operation, keys, values);
        updateTelemetry();
    }

    final boolean addData(String key, String value) {
        if (key != null && value != null) {
            if (setup) {
                telemetryData.put(key, value);
                return true;
            }
        }
        return false;
    }

    final boolean addData(String key, double value) {
        if (key != null) {
            if (setup) {
                telemetryData.put(key, String.valueOf(value));
                return true;
            }
        }
        return false;
    }

    final boolean addData(String key, boolean value) {
        if (key != null) {
            if (setup) {
                telemetryData.put(key, String.valueOf(value));
                return true;
            }
        }
        return false;
    }

    final boolean setData(String key, String value) {
        if (key != null && value != null) {
            if (setup) {
                telemetryData.replace(key, value);
                return true;
            }
        }
        return false;
    }

    final boolean setData(String key, double value) {
        if (key != null) {
            if (setup) {
                telemetryData.replace(key, String.valueOf(value));
                return true;
            }
        }
        return false;
    }

    final boolean setData(String key, boolean value) {
        if (key != null) {
            if (setup) {
                telemetryData.replace(key, String.valueOf(value));
                return true;
            }
        }
        return false;
    }

    final String getData(String key) {
        if (key != null) {
            if (setup) {
                return telemetryData.get(key);
            }
        }
        return null;
    }

    final boolean removeData(String key) {
        if (key != null) {
            if (setup) {
                telemetryData.remove(key);
                return true;
            }
        }
        return false;
    }

    final LinkedList<Boolean> telemetryOperation(DataSetHashMapOperation
                                                   <String, String, Boolean> operation,
                                           String[] keys, String[] values) {
        if (validateOperation(operation, keys, values)) {
            LinkedList<Boolean> operationCompletions = new LinkedList<>();
            for (int i = 0; i < keys.length; i++) {
                operationCompletions.add(operation.apply(keys[i], values[i]));
            }
            return operationCompletions;
        }
        return null;
    }

    final LinkedList<String> telemetryOperation(DataRetrievalHashMapOperation
                                                  <String, String> operation, String[] keys) {
        if (validateOperation(operation, keys)) {
            LinkedList<String> operationCompletions = new LinkedList<>();
            for (String key : keys) {
                operationCompletions.add(operation.apply(key));
            }
            return operationCompletions;
        }
        return null;
    }

    final LinkedList<Boolean> telemetryOperation(DataRemovalHashMapOperation
                                                   <String, Boolean> operation, String[] keys) {
        if (validateOperation(operation, keys)) {
            LinkedList<Boolean> operationCompletions = new LinkedList<>();
            for (String key : keys) {
                operationCompletions.add(operation.apply(key));
            }
            return operationCompletions;
        }
        return null;
    }

    private boolean validateOperation(Object operation, String[] keys, Object[] values) {
        if (operation == null || keys == null || values == null) {
            return false;
        }
        return keys.length == values.length;
    }

    private boolean validateOperation(Object operation, String[] keys) {
        return operation != null && keys != null;
    }
}
