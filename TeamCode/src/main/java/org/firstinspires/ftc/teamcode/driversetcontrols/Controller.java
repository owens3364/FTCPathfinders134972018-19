package org.firstinspires.ftc.teamcode.driversetcontrols;

import com.qualcomm.robotcore.hardware.Gamepad;

public final class Controller {

    private static final double[] defaultStickScale = {-1, 1, -1, 1};
    private static final double[] defaultTriggerScale = {0, 1, 0, 1};

    private final Gamepad gamepad;

    private ScaleSchemeApplication schemeApplication = ScaleSchemeApplication.STANDARD_SCALE_FOR_ALL;

    //These are for custom scheming for individual non boolean values of the gamepad
    //These are set to their default values, the same as linear scheming
    private ControlScheme leftStickXScheme = ControlScheme.LINEAR;
    private ControlScheme leftStickYScheme = ControlScheme.LINEAR;
    private ControlScheme leftTriggerScheme = ControlScheme.LINEAR;
    private ControlScheme rightStickXScheme = ControlScheme.LINEAR;
    private ControlScheme rightStickYScheme = ControlScheme.LINEAR;
    private ControlScheme rightTriggerScheme = ControlScheme.LINEAR;

    private ScaleSchemeApplication scaleApplication = ScaleSchemeApplication.STANDARD_SCALE_FOR_ALL;

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
        switch (scaleApplication) {
            case STANDARD_SCALE_FOR_ALL:
                return Schemer.schemeValue(gamepad.left_stick_x, leftStickXScheme);
            case CUSTOM_SCALE_FOR_ALL:
                return Scaler.scale(Schemer.schemeValue(gamepad.left_stick_x, leftStickXScheme),
                        stickStartMin, stickStartMax, stickEndMin, stickEndMax);
            case INDIVIDUALLY_CUSTOMIZED_SCALES:
                return Scaler.scale(Schemer.schemeValue(gamepad.left_stick_x, leftStickXScheme),
                        leftStickXScale[0],
                        leftStickXScale[1],
                        leftStickXScale[2],
                        leftStickXScale[3]);
        }
        return Schemer.schemeValue(gamepad.left_stick_x, leftStickXScheme);
    }

    public double leftStickY() {
        switch (scaleApplication) {
            case STANDARD_SCALE_FOR_ALL:
                return Schemer.schemeValue(gamepad.left_stick_y, leftStickYScheme);
            case CUSTOM_SCALE_FOR_ALL:
                return Scaler.scale(Schemer.schemeValue(gamepad.left_stick_y, leftStickYScheme),
                        stickStartMin, stickStartMax, stickEndMin, stickEndMax);
            case INDIVIDUALLY_CUSTOMIZED_SCALES:
                return Scaler.scale(Schemer.schemeValue(gamepad.left_stick_y, leftStickYScheme),
                        leftStickYScale[0],
                        leftStickYScale[1],
                        leftStickYScale[2],
                        leftStickYScale[3]);
        }
        return Schemer.schemeValue(gamepad.left_stick_y, leftStickYScheme);
    }

    public double leftTrigger() {
        switch (scaleApplication) {
            case STANDARD_SCALE_FOR_ALL:
                return Schemer.schemeValue(gamepad.left_trigger, leftTriggerScheme);
            case CUSTOM_SCALE_FOR_ALL:
                return Scaler.scale(Schemer.schemeValue(gamepad.left_trigger, leftTriggerScheme),
                        triggerStartMin, triggerStartMax, triggerEndMin, triggerEndMax);
            case INDIVIDUALLY_CUSTOMIZED_SCALES:
                return Scaler.scale(Schemer.schemeValue(gamepad.left_trigger, leftTriggerScheme),
                        leftTriggerScale[0],
                        leftTriggerScale[1],
                        leftTriggerScale[2],
                        leftTriggerScale[3]);
        }
        return Schemer.schemeValue(gamepad.left_trigger, leftTriggerScheme);
    }

    public double rightStickX() {
        switch (scaleApplication) {
            case STANDARD_SCALE_FOR_ALL:
                return Schemer.schemeValue(gamepad.right_stick_x, rightStickXScheme);
            case CUSTOM_SCALE_FOR_ALL:
                return Scaler.scale(Schemer.schemeValue(gamepad.right_stick_x, rightStickXScheme),
                        stickStartMin, stickStartMax, stickEndMin, stickEndMax);
            case INDIVIDUALLY_CUSTOMIZED_SCALES:
                return Scaler.scale(Schemer.schemeValue(gamepad.right_stick_x, rightStickXScheme),
                        rightStickXScale[0],
                        rightStickXScale[1],
                        rightStickXScale[2],
                        rightStickXScale[3]);
        }
        return Schemer.schemeValue(gamepad.right_stick_x, rightStickXScheme);
    }

    public double rightStickY() {
        switch (scaleApplication) {
            case STANDARD_SCALE_FOR_ALL:
                return Schemer.schemeValue(gamepad.right_stick_y, rightStickYScheme);
            case CUSTOM_SCALE_FOR_ALL:
                return Scaler.scale(Schemer.schemeValue(gamepad.right_stick_y, rightStickYScheme),
                        stickStartMin, stickStartMax, stickEndMin, stickEndMax);
            case INDIVIDUALLY_CUSTOMIZED_SCALES:
                return Scaler.scale(Schemer.schemeValue(gamepad.right_stick_y, rightStickYScheme),
                        rightStickYScale[0],
                        rightStickYScale[1],
                        rightStickYScale[2],
                        rightStickYScale[3]);
        }
        return Schemer.schemeValue(gamepad.right_stick_y, rightStickYScheme);
    }

    public double rightTrigger() {
        switch (scaleApplication) {
            case STANDARD_SCALE_FOR_ALL:
                return Schemer.schemeValue(gamepad.right_trigger, rightTriggerScheme);
            case CUSTOM_SCALE_FOR_ALL:
                return Scaler.scale(Schemer.schemeValue(gamepad.right_trigger, rightTriggerScheme),
                        triggerStartMin, triggerStartMax, triggerEndMin, triggerEndMax);
            case INDIVIDUALLY_CUSTOMIZED_SCALES:
                return Scaler.scale(Schemer.schemeValue(gamepad.right_trigger, rightTriggerScheme),
                        rightTriggerScale[0],
                        rightTriggerScale[1],
                        rightTriggerScale[2],
                        rightTriggerScale[3]);
        }
        return Schemer.schemeValue(gamepad.right_trigger, rightTriggerScheme);
    }


    //Control Scheme methods
    public void setControlScheme(ControlScheme controlScheme) {
        this.leftStickXScheme = controlScheme;
        this.leftStickYScheme = controlScheme;
        this.leftTriggerScheme = controlScheme;
        this.rightStickXScheme = controlScheme;
        this.rightStickYScheme = controlScheme;
        this.rightTriggerScheme = controlScheme;
    }

    public void incrScheme() {
        if (schemeApplication == ScaleSchemeApplication.STANDARD_SCALE_FOR_ALL ||
                schemeApplication == ScaleSchemeApplication.CUSTOM_SCALE_FOR_ALL) {
            switch (leftStickXScheme) {
                case LINEAR:
                    leftStickXScheme = ControlScheme.SQRT;
                    leftStickYScheme = ControlScheme.SQRT;
                    leftTriggerScheme = ControlScheme.SQRT;
                    rightStickXScheme = ControlScheme.SQRT;
                    rightStickYScheme = ControlScheme.SQRT;
                    rightTriggerScheme = ControlScheme.SQRT;
                    break;
                case SQRT:
                    leftStickXScheme = ControlScheme.CBRT;
                    leftStickYScheme = ControlScheme.CBRT;
                    leftTriggerScheme = ControlScheme.CBRT;
                    rightStickXScheme = ControlScheme.CBRT;
                    rightStickYScheme = ControlScheme.CBRT;
                    rightTriggerScheme = ControlScheme.CBRT;
                    break;
                case CBRT:
                    leftStickXScheme = ControlScheme.SQUARED;
                    leftStickYScheme = ControlScheme.SQUARED;
                    leftTriggerScheme = ControlScheme.SQUARED;
                    rightStickXScheme = ControlScheme.SQUARED;
                    rightStickYScheme = ControlScheme.SQUARED;
                    rightTriggerScheme = ControlScheme.SQUARED;
                    break;
                case SQUARED:
                    leftStickXScheme = ControlScheme.CUBED;
                    leftStickYScheme = ControlScheme.CUBED;
                    leftTriggerScheme = ControlScheme.CUBED;
                    rightStickXScheme = ControlScheme.CUBED;
                    rightStickYScheme = ControlScheme.CUBED;
                    rightTriggerScheme = ControlScheme.CUBED;
                    break;
                case CUBED:
                    leftStickXScheme = ControlScheme.FOURTH;
                    leftStickYScheme = ControlScheme.FOURTH;
                    leftTriggerScheme = ControlScheme.FOURTH;
                    rightStickXScheme = ControlScheme.FOURTH;
                    rightStickYScheme = ControlScheme.FOURTH;
                    rightTriggerScheme = ControlScheme.FOURTH;
                    break;
                case FOURTH:
                    leftStickXScheme = ControlScheme.LINEAR;
                    leftStickYScheme = ControlScheme.LINEAR;
                    leftTriggerScheme = ControlScheme.LINEAR;
                    rightStickXScheme = ControlScheme.LINEAR;
                    rightStickYScheme = ControlScheme.LINEAR;
                    rightTriggerScheme = ControlScheme.LINEAR;
                    break;
            }
        }
    }

    public void decrScheme() {
        if (schemeApplication == ScaleSchemeApplication.STANDARD_SCALE_FOR_ALL ||
                schemeApplication == ScaleSchemeApplication.CUSTOM_SCALE_FOR_ALL) {
            switch (leftStickXScheme) {
                case LINEAR:
                    leftStickXScheme = ControlScheme.FOURTH;
                    leftStickYScheme = ControlScheme.FOURTH;
                    leftTriggerScheme = ControlScheme.FOURTH;
                    rightStickXScheme = ControlScheme.FOURTH;
                    rightStickYScheme = ControlScheme.FOURTH;
                    rightTriggerScheme = ControlScheme.FOURTH;
                    break;
                case SQRT:
                    leftStickXScheme = ControlScheme.LINEAR;
                    leftStickYScheme = ControlScheme.LINEAR;
                    leftTriggerScheme = ControlScheme.LINEAR;
                    rightStickXScheme = ControlScheme.LINEAR;
                    rightStickYScheme = ControlScheme.LINEAR;
                    rightTriggerScheme = ControlScheme.LINEAR;
                    break;
                case CBRT:
                    leftStickXScheme = ControlScheme.SQRT;
                    leftStickYScheme = ControlScheme.SQRT;
                    leftTriggerScheme = ControlScheme.SQRT;
                    rightStickXScheme = ControlScheme.SQRT;
                    rightStickYScheme = ControlScheme.SQRT;
                    rightTriggerScheme = ControlScheme.SQRT;
                    break;
                case SQUARED:
                    leftStickXScheme = ControlScheme.CBRT;
                    leftStickYScheme = ControlScheme.CBRT;
                    leftTriggerScheme = ControlScheme.CBRT;
                    rightStickXScheme = ControlScheme.CBRT;
                    rightStickYScheme = ControlScheme.CBRT;
                    rightTriggerScheme = ControlScheme.CBRT;
                    break;
                case CUBED:
                    leftStickXScheme = ControlScheme.SQUARED;
                    leftStickYScheme = ControlScheme.SQUARED;
                    leftTriggerScheme = ControlScheme.SQUARED;
                    rightStickXScheme = ControlScheme.SQUARED;
                    rightStickYScheme = ControlScheme.SQUARED;
                    rightTriggerScheme = ControlScheme.SQUARED;
                    break;
                case FOURTH:
                    leftStickXScheme = ControlScheme.CUBED;
                    leftStickYScheme = ControlScheme.CUBED;
                    leftTriggerScheme = ControlScheme.CUBED;
                    rightStickXScheme = ControlScheme.CUBED;
                    rightStickYScheme = ControlScheme.CUBED;
                    rightTriggerScheme = ControlScheme.CUBED;
                    break;
            }
        }
    }

    public void setDefaultScheme() {
        leftStickXScheme = ControlScheme.LINEAR;
    }

    public ControlScheme getControlScheme() {
        if (schemeApplication == ScaleSchemeApplication.STANDARD_SCALE_FOR_ALL ||
                schemeApplication == ScaleSchemeApplication.CUSTOM_SCALE_FOR_ALL) {
            return leftStickXScheme;
        }
        return null;
    }

    public ScaleSchemeApplication getScaleApplication() {
        return scaleApplication;
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
        scaleApplication = ScaleSchemeApplication.CUSTOM_SCALE_FOR_ALL;
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
        scaleApplication = ScaleSchemeApplication.STANDARD_SCALE_FOR_ALL;
    }

    public ScaleSchemeApplication getSchemeApplication() {
        return schemeApplication;
    }

    public void useCustomSchemeForAll(ControlScheme leftStickXScheme,
                                      ControlScheme leftStickYScheme,
                                      ControlScheme leftTriggerScheme,
                                      ControlScheme rightStickXScheme,
                                      ControlScheme rightStickYScheme,
                                      ControlScheme rightTriggerScheme) {
        this.leftStickXScheme = leftStickXScheme;
        this.leftStickYScheme = leftStickYScheme;
        this.leftTriggerScheme = leftTriggerScheme;
        this.rightStickXScheme = rightStickXScheme;
        this.rightStickYScheme = rightStickYScheme;
        this.rightTriggerScheme = rightTriggerScheme;
        schemeApplication = ScaleSchemeApplication.CUSTOM_SCALE_FOR_ALL;
    }

    public void revertToStandardSchemeForAll() {
        leftStickXScheme = ControlScheme.LINEAR;
        leftStickYScheme = ControlScheme.LINEAR;
        leftTriggerScheme = ControlScheme.LINEAR;
        rightStickXScheme = ControlScheme.LINEAR;
        rightStickYScheme = ControlScheme.LINEAR;
        rightTriggerScheme = ControlScheme.LINEAR;
    }

    public void setLeftStickXScale(double startMin, double startMax, double endMin,
                                   double endMax) {
        leftStickXScale = new double[] {startMin, startMax, endMin, endMax};
        scaleApplication = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }

    public void setLeftStickYScale(double startMin, double startMax, double endMin,
                                   double endMax) {
        leftStickYScale = new double[] {startMin, startMax, endMin, endMax};
        scaleApplication = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }

    public void setLeftTriggerScale(double startMin, double startMax, double endMin,
                                    double endMax) {
        leftTriggerScale = new double[] {startMin, startMax, endMin, endMax};
        scaleApplication = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }

    public void setRightStickXScale(double startMin, double startMax, double endMin,
                                    double endMax) {
        rightStickXScale = new double[] {startMin, startMax, endMin, endMax};
        scaleApplication = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }

    public void setRightStickYScale(double startMin, double startMax, double endMin,
                                    double endMax) {
        rightStickYScale = new double[] {startMin, startMax, endMin, endMax};
        scaleApplication = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }

    public void setRightTriggerScale(double startMin, double startMax, double endMin,
                                     double endMax) {
        rightTriggerScale = new double[] {startMin, startMax, endMin, endMax};
        scaleApplication = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }

    public void setLeftStickXScheme(ControlScheme scheme) {
        leftStickXScheme = scheme;
        schemeApplication = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }

    public void setLeftStickYScheme(ControlScheme scheme) {
        leftStickYScheme = scheme;
        schemeApplication = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }

    public void setLeftTriggerScheme(ControlScheme scheme) {
        leftTriggerScheme = scheme;
        schemeApplication = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }

    public void setRightStickXScheme(ControlScheme scheme) {
        rightStickXScheme = scheme;
        schemeApplication = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }

    public void setRightStickYScheme(ControlScheme scheme) {
        rightStickYScheme = scheme;
        schemeApplication = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }

    public void setRightTriggerScheme(ControlScheme scheme) {
        rightTriggerScheme = scheme;
        schemeApplication = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALES;
    }
}