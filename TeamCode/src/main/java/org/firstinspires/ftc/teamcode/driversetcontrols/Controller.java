package org.firstinspires.ftc.teamcode.driversetcontrols;

import com.qualcomm.robotcore.hardware.Gamepad;

public final class Controller {
    private static final double[] defaultStickScale = {-1, 1, -1, 1};
    private static final double[] defaultTriggerScale = {0, 1, 0, 1};

    private ControlScheme controlScheme = ControlScheme.LINEAR;
    private ScaleScheme scaleScheme = ScaleScheme.STANDARD_SCALE_FOR_ALL;

    private final Gamepad gamepad;

    //This is the standard scaling for all of the non boolean values of the gamepad
    //This is used by default
    private double stickStartMin = -1;
    private double stickStartMax = 1;
    private double stickEndMin = -1;
    private double stickEndMax = 1;

    private double triggerStartMin = 0;
    private double triggerStartMax = 1;
    private double triggerEndMin = 0;
    private double triggerEndMax = 1;

    //These are for custom scaling for individual non boolean values of the gamepad
    //These are set to their default values, the same as the standard scaling
    private double[] leftStickXScale = defaultStickScale;

    private double[] leftStickYScale = defaultStickScale;

    private double[] leftTriggerScale = defaultTriggerScale;

    private double[] rightStickXScale = defaultStickScale;

    private double[] rightStickYScale = defaultStickScale;

    private double[] rightTriggerScale = defaultTriggerScale;

    public Controller(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    public boolean a() {
        return gamepad.a;
    }

    public boolean b() {
        return gamepad.b;
    }

    public boolean x() {
        return gamepad.x;
    }

    public boolean y() {
        return gamepad.y;
    }

    public boolean dpadLeft() {
        return gamepad.dpad_left;
    }

    public boolean dpadUp() {
        return gamepad.dpad_up;
    }

    public boolean dpadRight() {
        return gamepad.dpad_right;
    }

    public boolean dpadDown() {
        return gamepad.dpad_down;
    }

    public boolean leftBumper() {
        return gamepad.left_bumper;
    }

    public boolean rightBumper() {
        return gamepad.right_bumper;
    }

    public boolean leftStick() {
        return gamepad.left_stick_button;
    }

    public boolean rightStick() {
        return gamepad.right_stick_button;
    }

    public boolean start() {
        return gamepad.start;
    }

    public boolean guide() {
        return gamepad.guide;
    }

    public double leftStickX() {
        switch (scaleScheme) {
            case STANDARD_SCALE_FOR_ALL:
                return Schemer.schemeValue(gamepad.left_stick_x, controlScheme);
            case CUSTOM_SCALE_FOR_ALL:
                return Scaler.scale(Schemer.schemeValue(gamepad.left_stick_x, controlScheme),
                        stickStartMin, stickStartMax, stickEndMin, stickEndMax);
            case INDIVIDUALLY_CUSTOMIZED_SCALES:
                return Scaler.scale(Schemer.schemeValue(gamepad.left_stick_x, controlScheme),
                        leftStickXScale[0],
                        leftStickXScale[1],
                        leftStickXScale[2],
                        leftStickXScale[3]);
        }
        return Schemer.schemeValue(gamepad.left_stick_x, controlScheme);
    }

    public double leftStickY() {
        switch (scaleScheme) {
            case STANDARD_SCALE_FOR_ALL:
                return Schemer.schemeValue(gamepad.left_stick_y, controlScheme);
            case CUSTOM_SCALE_FOR_ALL:
                return Scaler.scale(Schemer.schemeValue(gamepad.left_stick_y, controlScheme),
                        stickStartMin, stickStartMax, stickEndMin, stickEndMax);
            case INDIVIDUALLY_CUSTOMIZED_SCALES:
                return Scaler.scale(Schemer.schemeValue(gamepad.left_stick_y, controlScheme),
                        leftStickYScale[0],
                        leftStickYScale[1],
                        leftStickYScale[2],
                        leftStickYScale[3]);
        }
        return Schemer.schemeValue(gamepad.left_stick_y, controlScheme);
    }

    public double leftTrigger() {
        switch (scaleScheme) {
            case STANDARD_SCALE_FOR_ALL:
                return Schemer.schemeValue(gamepad.left_trigger, controlScheme);
            case CUSTOM_SCALE_FOR_ALL:
                return Scaler.scale(Schemer.schemeValue(gamepad.left_trigger, controlScheme),
                        triggerStartMin, triggerStartMax, triggerEndMin, triggerEndMax);
            case INDIVIDUALLY_CUSTOMIZED_SCALES:
                return Scaler.scale(Schemer.schemeValue(gamepad.left_trigger, controlScheme),
                        leftTriggerScale[0],
                        leftTriggerScale[1],
                        leftTriggerScale[2],
                        leftTriggerScale[3]);
        }
        return Schemer.schemeValue(gamepad.left_trigger, controlScheme);
    }

    public double rightStickX() {
        switch (scaleScheme) {
            case STANDARD_SCALE_FOR_ALL:
                return Schemer.schemeValue(gamepad.right_stick_x, controlScheme);
            case CUSTOM_SCALE_FOR_ALL:
                return Scaler.scale(Schemer.schemeValue(gamepad.right_stick_x, controlScheme),
                        stickStartMin, stickStartMax, stickEndMin, stickEndMax);
            case INDIVIDUALLY_CUSTOMIZED_SCALES:
                return Scaler.scale(Schemer.schemeValue(gamepad.right_stick_x, controlScheme),
                        rightStickXScale[0],
                        rightStickXScale[1],
                        rightStickXScale[2],
                        rightStickXScale[3]);
        }
        return Schemer.schemeValue(gamepad.right_stick_x, controlScheme);
    }

    public double rightStickY() {
        switch (scaleScheme) {
            case STANDARD_SCALE_FOR_ALL:
                return Schemer.schemeValue(gamepad.right_stick_y, controlScheme);
            case CUSTOM_SCALE_FOR_ALL:
                return Scaler.scale(Schemer.schemeValue(gamepad.right_stick_y, controlScheme),
                        stickStartMin, stickStartMax, stickEndMin, stickEndMax);
            case INDIVIDUALLY_CUSTOMIZED_SCALES:
                return Scaler.scale(Schemer.schemeValue(gamepad.right_stick_y, controlScheme),
                        rightStickYScale[0],
                        rightStickYScale[1],
                        rightStickYScale[2],
                        rightStickYScale[3]);
        }
        return Schemer.schemeValue(gamepad.right_stick_y, controlScheme);
    }

    public double rightTrigger() {
        switch (scaleScheme) {
            case STANDARD_SCALE_FOR_ALL:
                return Schemer.schemeValue(gamepad.right_trigger, controlScheme);
            case CUSTOM_SCALE_FOR_ALL:
                return Scaler.scale(Schemer.schemeValue(gamepad.right_trigger, controlScheme),
                        triggerStartMin, triggerStartMax, triggerEndMin, triggerEndMax);
            case INDIVIDUALLY_CUSTOMIZED_SCALES:
                return Scaler.scale(Schemer.schemeValue(gamepad.right_trigger, controlScheme),
                        rightTriggerScale[0],
                        rightTriggerScale[1],
                        rightTriggerScale[2],
                        rightTriggerScale[3]);
        }
        return Schemer.schemeValue(gamepad.right_trigger, controlScheme);
    }


    //Control Scheme methods
    public void setControlScheme(ControlScheme controlScheme) {
        this.controlScheme = controlScheme;
    }

    public void incrScheme() {
        switch (controlScheme) {
            case LINEAR:
                controlScheme = ControlScheme.SQRT;
                break;
            case SQRT:
                controlScheme = ControlScheme.CBRT;
                break;
            case CBRT:
                controlScheme = ControlScheme.SQUARED;
                break;
            case SQUARED:
                controlScheme = ControlScheme.CUBED;
                break;
            case CUBED:
                controlScheme = ControlScheme.FOURTH;
                break;
            case FOURTH:
                controlScheme = ControlScheme.LINEAR;
                break;
        }
    }

    public void decrScheme() {
        switch (controlScheme) {
            case LINEAR:
                controlScheme = ControlScheme.FOURTH;
                break;
            case SQRT:
                controlScheme = ControlScheme.LINEAR;
                break;
            case CBRT:
                controlScheme = ControlScheme.SQRT;
                break;
            case SQUARED:
                controlScheme = ControlScheme.CBRT;
                break;
            case CUBED:
                controlScheme = ControlScheme.SQUARED;
                break;
            case FOURTH:
                controlScheme = ControlScheme.CUBED;
                break;
        }
    }

    public void setDefaultScheme() {
        controlScheme = ControlScheme.LINEAR;
    }

    public ControlScheme getControlScheme() {
        return controlScheme;
    }

    public ScaleScheme getScaleScheme() {
        return scaleScheme;
    }

    public void useCustomScaleForAll(double stickStartMin,
                                     double stickStartMax,
                                     double stickEndMin,
                                     double stickEndMax,
                                     double triggerStartMin,
                                     double triggerStartMax,
                                     double triggerEndMin,
                                     double triggerEndMax) {
        this.stickStartMin = stickStartMin;
        this.stickStartMax = stickStartMax;
        this.stickEndMin = stickEndMin;
        this.stickEndMax = stickEndMax;
        this.triggerStartMin = triggerStartMin;
        this.triggerStartMax = triggerStartMax;
        this.triggerEndMin = triggerEndMin;
        this.triggerEndMax = triggerEndMax;
        scaleScheme = ScaleScheme.CUSTOM_SCALE_FOR_ALL;
    }

    public void revertToStandardScaleForAll() {
        stickStartMin = -1;
        stickStartMax = 1;
        stickEndMin = -1;
        stickEndMax = 1;
        triggerStartMin = 0;
        triggerStartMax = 1;
        triggerEndMin = 0;
        triggerEndMax = 1;
        scaleScheme = ScaleScheme.STANDARD_SCALE_FOR_ALL;
    }

    public void setLeftStickXScale(double startMin, double startMax, double endMin,
                                   double endMax) {
        leftStickXScale = new double[] {startMin, startMax, endMin, endMax};
        scaleScheme = ScaleScheme.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }

    public void setLeftStickYScale(double startMin, double startMax, double endMin,
                                   double endMax) {
        leftStickYScale = new double[] {startMin, startMax, endMin, endMax};
        scaleScheme = ScaleScheme.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }

    public void setLeftTriggerScale(double startMin, double startMax, double endMin,
                                    double endMax) {
        leftTriggerScale = new double[] {startMin, startMax, endMin, endMax};
        scaleScheme = ScaleScheme.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }

    public void setRightStickXScale(double startMin, double startMax, double endMin,
                                    double endMax) {
        rightStickXScale = new double[] {startMin, startMax, endMin, endMax};
        scaleScheme = ScaleScheme.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }

    public void setRightStickYScale(double startMin, double startMax, double endMin,
                                    double endMax) {
        rightStickYScale = new double[] {startMin, startMax, endMin, endMax};
        scaleScheme = ScaleScheme.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }

    public void setRightTriggerScale(double startMin, double startMax, double endMin,
                                     double endMax) {
        rightTriggerScale = new double[] {startMin, startMax, endMin, endMax};
        scaleScheme = ScaleScheme.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }
}