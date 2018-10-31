package org.firstinspires.ftc.teamcode.DriverSetControls;

import com.qualcomm.robotcore.hardware.Gamepad;

public final class Controller {
    private ControlScheme controlScheme = ControlScheme.LINEAR;

    private Gamepad gamepad;

    //These keep track of what scale to use
    //They're set to their default values
    private boolean usingCustomScaleForAll = false;
    private boolean usingStandardScaleForAll = true;
    private boolean usingCustomScales = false;

    //This is the standard scaling for all of the non boolean values of the gamepad
    //This is used by default
    private double startMin = -1;
    private double startMax = 1;
    private double endMin = -1;
    private double endMax = 1;

    //These are for custom scaling for individual non boolean values of the gamepad
    //These are set to their default values, the same as the standard scaling
    private double[] leftStickXScale = {-1, 1, -1, 1};

    private double[] leftStickYScale = {-1, 1, -1, 1};

    private double[] leftTriggerScale = {-1, 1, -1, 1};

    private double[] rightStickXScale = {-1, 1, -1, 1};

    private double[] rightStickYScale = {-1, 1, -1, 1};

    private double[] rightTriggerScale = {-1, 1, -1, 1};

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
        if (usingStandardScaleForAll) {
            return Schemer.schemeValue(gamepad.left_stick_x, controlScheme);
        }
        if (usingCustomScaleForAll) {
            return Scaler.scale(Schemer.schemeValue(gamepad.left_stick_x, controlScheme), startMin, startMax, endMin, endMax);
        }
        if (usingCustomScales) {
            return Scaler.scale(Schemer.schemeValue(gamepad.left_stick_x, controlScheme), leftStickXScale[0], leftStickXScale[1], leftStickXScale[2], leftStickXScale[3]);
        }
        return Schemer.schemeValue(gamepad.left_stick_x, controlScheme);
    }

    public double leftStickY() {
        if (usingStandardScaleForAll) {
            return Schemer.schemeValue(gamepad.left_stick_y, controlScheme);
        }
        if (usingCustomScaleForAll) {
            return Scaler.scale(Schemer.schemeValue(gamepad.left_stick_y, controlScheme), startMin, startMax, endMin, endMax);
        }
        if (usingCustomScales) {
            return Scaler.scale(Schemer.schemeValue(gamepad.left_stick_y, controlScheme), leftStickYScale[0], leftStickYScale[1], leftStickYScale[2], leftStickYScale[3]);
        }
        return Schemer.schemeValue(gamepad.left_stick_y, controlScheme);
    }

    public double leftTrigger() {
        if (usingStandardScaleForAll) {
            return Schemer.schemeValue(gamepad.left_trigger, controlScheme);
        }
        if (usingCustomScaleForAll) {
            return Scaler.scale(Schemer.schemeValue(gamepad.left_trigger, controlScheme), startMin, startMax, endMin, endMax);
        }
        if (usingCustomScales) {
            return Scaler.scale(Schemer.schemeValue(gamepad.left_trigger, controlScheme), leftTriggerScale[0], leftTriggerScale[1], leftTriggerScale[2], leftTriggerScale[3]);
        }
        return Schemer.schemeValue(gamepad.left_trigger, controlScheme);
    }

    public double rightStickX() {
        if (usingStandardScaleForAll) {
            return Schemer.schemeValue(gamepad.right_stick_x, controlScheme);
        }
        if (usingCustomScaleForAll) {
            return Scaler.scale(Schemer.schemeValue(gamepad.right_stick_x, controlScheme), startMin, startMax, endMin, endMax);
        }
        if (usingCustomScales) {
            return Scaler.scale(Schemer.schemeValue(gamepad.right_stick_x, controlScheme), rightStickXScale[0], rightStickXScale[1], rightStickXScale[2], rightStickXScale[3]);
        }
        return Schemer.schemeValue(gamepad.right_stick_x, controlScheme);
    }

    public double rightStickY() {
        if (usingStandardScaleForAll) {
            return Schemer.schemeValue(gamepad.right_stick_y, controlScheme);
        }
        if (usingCustomScaleForAll) {
            return Scaler.scale(Schemer.schemeValue(gamepad.right_stick_y, controlScheme), startMin, startMax, endMin, endMax);
        }
        if (usingCustomScales) {
            return Scaler.scale(Schemer.schemeValue(gamepad.right_stick_y, controlScheme), rightStickYScale[0], rightStickYScale[1], rightStickYScale[2], rightStickYScale[3]);
        }
        return Schemer.schemeValue(gamepad.right_stick_y, controlScheme);
    }

    public double rightTrigger() {
        if (usingStandardScaleForAll) {
            return Schemer.schemeValue(gamepad.right_trigger, controlScheme);
        }
        if (usingCustomScaleForAll) {
            return Scaler.scale(Schemer.schemeValue(gamepad.right_trigger, controlScheme), startMin, startMax, endMin, endMax);
        }
        if (usingCustomScales) {
            return Scaler.scale(Schemer.schemeValue(gamepad.right_trigger, controlScheme), rightTriggerScale[0],rightTriggerScale[1], rightTriggerScale[2], rightTriggerScale[3]);
        }
        return Schemer.schemeValue(gamepad.right_trigger, controlScheme);
    }


    //Control Scheme methods

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

    public boolean isUsingStandardScaleForAll() {
        return usingStandardScaleForAll;
    }

    public boolean isUsingCustomScaleForAll() {
        return usingCustomScaleForAll;
    }

    public boolean isUsingCustomScales() {
        return usingCustomScales;
    }

    public void useCustomScaleForAll(double startMin, double startMax, double endMin, double endMax) {
        this.startMin = startMin;
        this.startMax = startMax;
        this.endMin = endMin;
        this.endMax = endMax;
        usingCustomScaleForAll = true;
        usingStandardScaleForAll = false;
        usingCustomScales = false;
    }

    public void revertToStandardScaleForAll() {
        startMin = -1;
        startMax = 1;
        endMin = -1;
        endMax = 1;
        usingCustomScaleForAll = false;
        usingStandardScaleForAll = true;
        usingCustomScales = false;
    }

    public void setLeftStickXScale(double startMin, double startMax, double endMin, double endMax) {
        leftStickXScale = new double[] {startMin, startMax, endMin, endMax};
        usingCustomScaleForAll = false;
        usingStandardScaleForAll = false;
        usingCustomScales = true;
    }

    public void setLeftStickYScale(double startMin, double startMax, double endMin, double endMax) {
        leftStickYScale = new double[] {startMin, startMax, endMin, endMax};
        usingCustomScaleForAll = false;
        usingStandardScaleForAll = false;
        usingCustomScales = true;
    }

    public void setLeftTriggerScale(double startMin, double startMax, double endMin, double endMax) {
        leftTriggerScale = new double[] {startMin, startMax, endMin, endMax};
        usingCustomScaleForAll = false;
        usingStandardScaleForAll = false;
        usingCustomScales = true;
    }

    public void setRightStickXScale(double startMin, double startMax, double endMin, double endMax) {
        rightStickXScale = new double[] {startMin, startMax, endMin, endMax};
        usingCustomScaleForAll = false;
        usingStandardScaleForAll = false;
        usingCustomScales = true;
    }

    public void setRightStickYScale(double startMin, double startMax, double endMin, double endMax) {
        rightStickYScale = new double[] {startMin, startMax, endMin, endMax};
        usingCustomScaleForAll = false;
        usingStandardScaleForAll = false;
        usingCustomScales = true;
    }

    public void setRightTriggerScale(double startMin, double startMax, double endMin, double endMax) {
        rightTriggerScale = new double[] {startMin, startMax, endMin, endMax};
        usingCustomScaleForAll = false;
        usingStandardScaleForAll = false;
        usingCustomScales = true;
    }
}