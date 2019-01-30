package org.firstinspires.ftc.teamcode.driversetcontrols;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.HashMap;

public class Controller {
    public static final double[] DEFAULT_STICK_SCALE = {-1, 1, -1, 1};
    public static final double[] DEFAULT_TRIGGER_SCALE = {0, 1, 0, 1};
    private static final ScaleSchemeApplication DEFAULT_SCALE_SETTING =
            ScaleSchemeApplication.STANDARD_SCALESCHEME_FOR_ALL;

    public static final ControlScheme DEFAULT_SCHEME = ControlScheme.LINEAR;
    private static final ScaleSchemeApplication DEFAULT_SCHEME_SETTING =
            ScaleSchemeApplication.STANDARD_SCALESCHEME_FOR_ALL;

    private final Gamepad GAMEPAD;

    private double[] leftStickXScale;
    private double[] leftStickYScale;
    private double[] leftTriggerScale;
    private double[] rightStickXScale;
    private double[] rightStickYScale;
    private double[] rightTriggerScale;
    private final double[][] stickScales;
    private final double[][] triggerScales;
    private ScaleSchemeApplication stickScaleSetting;
    private ScaleSchemeApplication triggerScaleSetting;

    private ControlScheme leftStickXScheme;
    private ControlScheme leftStickYScheme;
    private ControlScheme leftTriggerScheme;
    private ControlScheme rightStickXScheme;
    private ControlScheme rightStickYScheme;
    private ControlScheme rightTriggerScheme;
    private final ControlScheme[] schemes;
    private ScaleSchemeApplication schemeSetting;

    private PreSchemeOperation<Double> leftStickXPreScheme;
    private PreSchemeOperation<Double> leftStickYPreScheme;
    private PreSchemeOperation<Double> leftTriggerPreScheme;
    private PreSchemeOperation<Double> rightStickXPreScheme;
    private PreSchemeOperation<Double> rightStickYPreScheme;
    private PreSchemeOperation<Double> rightTriggerPreScheme;

    private PostSchemePreScaleOperation<Double> leftStickXPostSchemePreScale;
    private PostSchemePreScaleOperation<Double> leftStickYPostSchemePreScale;
    private PostSchemePreScaleOperation<Double> leftTriggerPostSchemePreScale;
    private PostSchemePreScaleOperation<Double> rightStickXPostSchemePreScale;
    private PostSchemePreScaleOperation<Double> rightStickYPostSchemePreScale;
    private PostSchemePreScaleOperation<Double> rightTriggerPostSchemePreScale;

    private PostScaleOperation<Double> leftStickXPostScale;
    private PostScaleOperation<Double> leftStickYPostScale;
    private PostScaleOperation<Double> leftTriggerPostScale;
    private PostScaleOperation<Double> rightStickXPostScale;
    private PostScaleOperation<Double> rightStickYPostScale;
    private PostScaleOperation<Double> rightTriggerPostScale;


    public Controller(Gamepad gamepad) {
        if (gamepad != null) {
            this.GAMEPAD = gamepad;
        } else {
            this.GAMEPAD = new PhonyGamepad();
        }

        stickScaleSetting = DEFAULT_SCALE_SETTING;
        stickScales = new double[4][4];
        stickScales[0] = leftStickXScale;
        stickScales[1] = leftStickYScale;
        stickScales[2] = rightStickXScale;
        stickScales[3] = rightStickYScale;

        triggerScaleSetting = DEFAULT_SCALE_SETTING;
        triggerScales = new double[2][4];
        triggerScales[0] = leftTriggerScale;
        triggerScales[1] = rightTriggerScale;

        schemeSetting = DEFAULT_SCHEME_SETTING;
        schemes = new ControlScheme[6];
        schemes[0] = leftStickXScheme;
        schemes[1] = leftStickYScheme;
        schemes[2] = leftTriggerScheme;
        schemes[3] = rightStickXScheme;
        schemes[4] = rightStickYScheme;
        schemes[5] = rightTriggerScheme;

        leftStickXPreScheme = this::defaultPreScheme;
        leftStickYPreScheme = this::defaultPreScheme;
        leftTriggerPreScheme = this::defaultPreScheme;
        rightStickXPreScheme = this::defaultPreScheme;
        rightStickYPreScheme = this::defaultPreScheme;
        rightTriggerPreScheme = this::defaultPreScheme;

        leftStickXPostSchemePreScale = this::defaultPostSchemePreScale;
        leftStickYPostSchemePreScale = this::defaultPostSchemePreScale;
        leftTriggerPostSchemePreScale = this::defaultPostSchemePreScale;
        rightStickXPostSchemePreScale = this::defaultPostSchemePreScale;
        rightStickYPostSchemePreScale = this::defaultPostSchemePreScale;
        rightTriggerPostSchemePreScale = this::defaultPostSchemePreScale;

        leftStickXPostScale = this::defaultPostScale;
        leftStickYPostScale = this::defaultPostScale;
        leftTriggerPostScale = this::defaultPostScale;
        rightStickXPostScale = this::defaultPostScale;
        rightStickYPostScale = this::defaultPostScale;
        rightTriggerPostScale = this::defaultPostScale;

        applyToAllStickScales(DEFAULT_STICK_SCALE);
        applyToAllTriggerScales(DEFAULT_TRIGGER_SCALE);
        applyToAllSchemes(DEFAULT_SCHEME);
    }

    //Methods to set custom operations at different states of data retrieval

    public boolean setLeftStickXPreScheme(PreSchemeOperation<Double> op) {
        if (op != null) {
            leftStickXPreScheme = op;
            return true;
        }
        return false;
    }

    public boolean setLeftStickYPreScheme(PreSchemeOperation<Double> op) {
        if (op != null) {
            leftStickYPreScheme = op;
            return true;
        }
        return false;
    }

    public boolean setLeftTriggerPreScheme(PreSchemeOperation<Double> op) {
        if (op != null) {
            leftTriggerPreScheme = op;
            return true;
        }
        return false;
    }

    public boolean setRightStickXPreScheme(PreSchemeOperation<Double> op) {
        if (op != null) {
            rightStickXPreScheme = op;
            return true;
        }
        return false;
    }

    public boolean setRightStickYPreScheme(PreSchemeOperation<Double> op) {
        if (op != null) {
            rightStickYPreScheme = op;
            return true;
        }
        return false;
    }

    public boolean setRightTriggerPreScheme(PreSchemeOperation<Double> op) {
        if (op != null) {
            rightTriggerPreScheme = op;
            return true;
        }
        return false;
    }

    public boolean setLeftStickXPostScheme(PostSchemePreScaleOperation<Double> op) {
        if (op != null) {
            leftStickXPostSchemePreScale = op;
            return true;
        }
        return false;
    }

    public boolean setLeftStickYPostScheme(PostSchemePreScaleOperation<Double> op) {
        if (op != null) {
            leftStickYPostSchemePreScale = op;
            return true;
        }
        return false;
    }

    public void setLeftTriggerPostScheme(PostSchemePreScaleOperation<Double> op) {
        if (op != null) {
            leftTriggerPostSchemePreScale = op;
        }
    }

    public boolean setRightStickXPostScheme(PostSchemePreScaleOperation<Double> op) {
        if (op != null) {
            rightStickXPostSchemePreScale = op;
            return true;
        }
        return false;
    }

    public boolean setRightStickYPostScheme(PostSchemePreScaleOperation<Double> op) {
        if (op != null) {
            rightStickYPostSchemePreScale = op;
            return true;
        }
        return false;
    }

    public boolean setRightTriggerPostScheme(PostSchemePreScaleOperation<Double> op) {
        if (op != null) {
            rightTriggerPostSchemePreScale = op;
            return true;
        }
        return false;
    }

    public boolean setLeftStickXPostScale(PostScaleOperation<Double> op) {
        if (op != null) {
            leftStickXPostScale = op;
            return true;
        }
        return false;
    }

    public boolean setLeftStickYPostScale(PostScaleOperation<Double> op) {
        if (op != null) {
            leftStickYPostScale = op;
            return true;
        }
        return false;
    }

    public boolean setLeftTriggerPostScale(PostScaleOperation<Double> op) {
        if (op != null) {
            leftTriggerPostScale = op;
            return true;
        }
        return false;
    }

    public boolean setRightStickXPostScale(PostScaleOperation<Double> op) {
        if (op != null) {
            rightStickXPostScale = op;
            return true;
        }
        return false;
    }

    public boolean setRightStickYPostScale(PostScaleOperation<Double> op) {
        if (op != null) {
            rightStickYPostScale = op;
            return true;
        }
        return false;
    }

    public boolean setRightTriggerPostScale(PostScaleOperation<Double> op) {
        if (op != null) {
            rightTriggerPostScale = op;
            return true;
        }
        return false;
    }

    //Methods to read and write schemes

    public boolean setControlScheme(ControlScheme controlScheme) {
        if (controlScheme != null) {
            if (applyToAllSchemes(controlScheme)) {
                schemeSetting = ScaleSchemeApplication.CUSTOM_SCALESCHEME_FOR_ALL;
                return true;
            }
        }
        return false;
    }

    public boolean setCustomizedControlSchemes(ControlScheme leftStickXScheme,
                                            ControlScheme leftStickYScheme,
                                            ControlScheme leftTriggerScheme,
                                            ControlScheme rightStickXScheme,
                                            ControlScheme rightStickYScheme,
                                            ControlScheme rightTriggerScheme) {
        if (leftStickXScheme != null &&
                leftStickYScheme != null &&
                leftTriggerScheme != null &&
                rightStickXScheme != null &&
                rightStickYScheme != null &&
                rightTriggerScheme != null) {
            this.leftStickXScheme = leftStickXScheme;
            this.leftStickYScheme = leftStickYScheme;
            this.leftTriggerScheme = leftTriggerScheme;
            this.rightStickXScheme = rightStickXScheme;
            this.rightStickYScheme = rightStickYScheme;
            this.rightTriggerScheme = rightTriggerScheme;
            schemeSetting = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALESCHEMES;
            return true;
        }
        return false;
    }

    /**
     * This method takes arrays as arguments
     * The array can be any length
     * The array must be in the following order
     * leftStickX, leftStickY, leftTrigger, rightStickX, rightStickY, rightTrigger
     *
     */
    public boolean setCustomizedControlSchemes(ControlScheme ... schemes) {
        if (schemes != null && schemes.length <= this.schemes.length) {
            for (ControlScheme scheme : schemes) {
                if (scheme == null) {
                    return false;
                }
            }
            System.arraycopy(schemes, 0, this.schemes, 0, schemes.length);
            schemeSetting = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALESCHEMES;
            return true;
        }
        return false;
    }

    public void setCustomizedControlSchemes(HashMap<ControlSurface, ControlScheme>
                                                       controlSchemes) {
        if (controlSchemes != null) {
            leftStickXScheme = controlSchemes.get(ControlSurface.LEFT_STICK_X) != null ?
                    controlSchemes.get(ControlSurface.LEFT_STICK_X) : leftStickXScheme;
            leftStickYScheme = controlSchemes.get(ControlSurface.LEFT_STICK_Y) != null ?
                    controlSchemes.get(ControlSurface.LEFT_STICK_Y) : leftStickYScheme;
            leftTriggerScheme = controlSchemes.get(ControlSurface.LEFT_TRIGGER) != null ?
                    controlSchemes.get(ControlSurface.LEFT_TRIGGER) : leftTriggerScheme;
            rightStickXScheme = controlSchemes.get(ControlSurface.RIGHT_STICK_X) != null ?
                    controlSchemes.get(ControlSurface.RIGHT_STICK_X) : rightStickXScheme;
            rightStickYScheme = controlSchemes.get(ControlSurface.RIGHT_STICK_Y) != null ?
                    controlSchemes.get(ControlSurface.RIGHT_STICK_Y) : rightStickYScheme;
            rightTriggerScheme = controlSchemes.get(ControlSurface.RIGHT_TRIGGER) != null ?
                    controlSchemes.get(ControlSurface.RIGHT_TRIGGER) : rightTriggerScheme;
            schemeSetting = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALESCHEMES;
        }
    }

    public boolean setDefaultControlSchemes() {
        if (applyToAllSchemes(DEFAULT_SCHEME)) {
            schemeSetting = DEFAULT_SCHEME_SETTING;
            return true;
        }
        return false;
    }

    public ControlScheme getControlScheme() {
        if (schemeSetting != ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALESCHEMES) {
            return leftStickXScheme;
        }
        return null;
    }

    public HashMap<ControlSurface, ControlScheme> getControlSchemes() {
        HashMap<ControlSurface, ControlScheme> controlSchemes = new HashMap<>();
        controlSchemes.put(ControlSurface.LEFT_STICK_X, leftStickXScheme);
        controlSchemes.put(ControlSurface.LEFT_STICK_Y, leftStickYScheme);
        controlSchemes.put(ControlSurface.LEFT_TRIGGER, leftTriggerScheme);
        controlSchemes.put(ControlSurface.RIGHT_STICK_X, rightStickXScheme);
        controlSchemes.put(ControlSurface.RIGHT_STICK_Y, rightStickYScheme);
        controlSchemes.put(ControlSurface.RIGHT_TRIGGER, rightTriggerScheme);
        return controlSchemes;
    }

    public ScaleSchemeApplication getSchemeSetting() {
        return schemeSetting;
    }

    public void incrScheme() {
        if (schemeSetting != ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALESCHEMES) {
            applyToAllSchemes(leftStickXScheme.next());
            return;
        }
        modifyAllControlSchemes(ControlScheme::next);
    }

    public void decrScheme() {
        if (schemeSetting != ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALESCHEMES) {
            applyToAllSchemes(leftStickXScheme.prev());
            return;
        }
        modifyAllControlSchemes(ControlScheme::prev);
    }

    public boolean setStickScale(double[] scale) {
        if (scale != null) {
            if (applyToAllStickScales(scale)) {
                stickScaleSetting = ScaleSchemeApplication.CUSTOM_SCALESCHEME_FOR_ALL;
                return true;
            }
        }
        return false;
    }

    public boolean setTriggerScale(double[] scale) {
        if (scale != null) {
            if (applyToAllTriggerScales(scale)) {
                triggerScaleSetting = ScaleSchemeApplication.CUSTOM_SCALESCHEME_FOR_ALL;
                return true;
            }
        }
        return false;
    }

    public boolean setCustomizedStickScales(double[] leftStickXScale, double[] leftStickYScale,
                                         double[] rightStickXScale, double[] rightStickYScale) {
        if (leftStickXScale != null &&
                leftStickYScale != null &&
                rightStickXScale != null &&
                rightStickYScale != null) {
            this.leftStickXScale = leftStickXScale;
            this.leftStickYScale = leftStickYScale;
            this.rightStickXScale = rightStickYScale;
            this.rightStickYScale = rightStickYScale;
            stickScaleSetting = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALESCHEMES;
            return true;
        }
        return false;
    }

    /**
     * This method takes arrays as arguments
     * The array can be any length
     * The array must be in the following order
     * leftStickX, leftStickY, leftTrigger, rightStickX, rightStickY, rightTrigger
     *
     */
    public boolean setCustomizedStickScales(double[] ... stickScales) {
        if (stickScales != null && stickScales.length <= this.stickScales.length) {
            for (double[] stickScale : stickScales) {
                if (stickScale == null) {
                    return false;
                }
            }
            System.arraycopy(stickScales, 0, this.stickScales, 0,
                    stickScales.length);
            stickScaleSetting = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALESCHEMES;
            return true;
        }
        return false;
    }

    public void setCustomizedStickScales(HashMap<ControlSurface, double[]> stickScales) {
        if (stickScales != null) {
            leftStickXScale = stickScales.get(ControlSurface.LEFT_STICK_X) != null ?
                    stickScales.get(ControlSurface.LEFT_STICK_X) : leftStickXScale;
            leftStickYScale = stickScales.get(ControlSurface.LEFT_STICK_Y) != null ?
                    stickScales.get(ControlSurface.LEFT_STICK_Y) : leftStickYScale;
            rightStickXScale = stickScales.get(ControlSurface.RIGHT_STICK_X) != null ?
                    stickScales.get(ControlSurface.RIGHT_STICK_X) : rightStickXScale;
            rightStickYScale = stickScales.get(ControlSurface.RIGHT_STICK_Y) != null ?
                    stickScales.get(ControlSurface.RIGHT_STICK_Y) : rightStickYScale;
            stickScaleSetting = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALESCHEMES;
        }
    }

    public boolean setCustomizedTriggerScales(double[] leftTriggerScale,
                                              double[] rightTriggerScale) {
        if (leftTriggerScale != null &&
                rightTriggerScale != null) {
            this.leftTriggerScale = leftTriggerScale;
            this.rightTriggerScale = rightTriggerScale;
            triggerScaleSetting = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALESCHEMES;
            return true;
        }
        return false;
    }

    public boolean setCustomizedTriggerScales(double[] ... triggerScales) {
        if (triggerScales != null && triggerScales.length < this.triggerScales.length) {
            for (double[] triggerScale : triggerScales) {
                if (triggerScale == null) {
                    return false;
                }
            }
            System.arraycopy(triggerScales, 0, this.triggerScales, 0,
                    triggerScales.length);
            triggerScaleSetting = ScaleSchemeApplication.INDIVIDUALLY_CUSTOMIZED_SCALESCHEMES;
            return true;
        }
        return false;
    }

    public void setCustomizedTriggerScales(HashMap<ControlSurface, double[]> triggerScales) {
        if (triggerScales != null) {
            leftTriggerScale = triggerScales.get(ControlSurface.LEFT_TRIGGER) != null ?
                    triggerScales.get(ControlSurface.LEFT_TRIGGER) : leftTriggerScale;
            rightTriggerScale = triggerScales.get(ControlSurface.RIGHT_TRIGGER) != null ?
                    triggerScales.get(ControlSurface.RIGHT_TRIGGER) : rightTriggerScale;
        }
    }

    public boolean setDefaultStickScales() {
        if (applyToAllStickScales(DEFAULT_STICK_SCALE)) {
            stickScaleSetting = DEFAULT_SCALE_SETTING;
            return true;
        }
        return false;
    }

    public boolean setDefaultTriggerScales() {
        if (applyToAllTriggerScales(DEFAULT_TRIGGER_SCALE)) {
            triggerScaleSetting = DEFAULT_SCALE_SETTING;
            return true;
        }
        return false;
    }

    public HashMap<ControlSurface, double[]> getStickScales() {
        HashMap<ControlSurface, double[]> stickScaleDict = new HashMap<>();
        stickScaleDict.put(ControlSurface.LEFT_STICK_X, leftStickXScale);
        stickScaleDict.put(ControlSurface.LEFT_STICK_Y, leftStickYScale);
        stickScaleDict.put(ControlSurface.RIGHT_STICK_X, rightStickXScale);
        stickScaleDict.put(ControlSurface.RIGHT_STICK_Y, rightStickYScale);
        return stickScaleDict;
    }

    public HashMap<ControlSurface, double[]> getTriggerScales() {
        HashMap<ControlSurface, double[]> triggerScaleDict = new HashMap<>();
        triggerScaleDict.put(ControlSurface.LEFT_TRIGGER, leftTriggerScale);
        triggerScaleDict.put(ControlSurface.RIGHT_TRIGGER, rightTriggerScale);
        return triggerScaleDict;
    }

    public ScaleSchemeApplication getStickScaleSetting() {
        return stickScaleSetting;
    }

    public ScaleSchemeApplication getTriggerScaleSetting() {
        return triggerScaleSetting;
    }

    //Here's the non boolean Controller methods
    public double leftStickX() {
        return getAppropriatelyScaledAndSchemedValue(GAMEPAD.left_stick_x,
                ControlSurface.LEFT_STICK_X);
    }

    public double leftStickY() {
        return getAppropriatelyScaledAndSchemedValue(GAMEPAD.left_stick_y,
                ControlSurface.LEFT_STICK_Y);
    }

    public double leftTrigger() {
        return getAppropriatelyScaledAndSchemedValue(GAMEPAD.left_trigger,
                ControlSurface.LEFT_TRIGGER);
    }

    public double rightStickX() {
        return getAppropriatelyScaledAndSchemedValue(GAMEPAD.right_stick_x,
                ControlSurface.RIGHT_STICK_X);
    }

    public double rightStickY() {
        return getAppropriatelyScaledAndSchemedValue(GAMEPAD.right_stick_y,
                ControlSurface.RIGHT_STICK_Y);
    }

    public double rightTrigger() {
        return getAppropriatelyScaledAndSchemedValue(GAMEPAD.right_trigger,
                ControlSurface.RIGHT_TRIGGER);
    }

    // And the rest is simple boolean stuff
    public boolean a() {
        return GAMEPAD.a;
    }

    public boolean b() {
        return GAMEPAD.b;
    }

    public boolean x() {
        return GAMEPAD.x;
    }

    public boolean y() {
        return GAMEPAD.y;
    }

    public boolean dpadLeft() {
        return GAMEPAD.dpad_left;
    }

    public boolean dpadUp() {
        return GAMEPAD.dpad_up;
    }

    public boolean dpadRight() {
        return GAMEPAD.dpad_right;
    }

    public boolean dpadDown() {
        return GAMEPAD.dpad_down;
    }

    public boolean leftBumper() {
        return GAMEPAD.left_bumper;
    }

    public boolean rightBumper() {
        return GAMEPAD.right_bumper;
    }

    public boolean leftStick() {
        return GAMEPAD.left_stick_button;
    }

    public boolean rightStick() {
        return GAMEPAD.right_stick_button;
    }

    public boolean start() {
        return GAMEPAD.start;
    }

    public boolean guide() {
        return GAMEPAD.guide;
    }

    private double getAppropriatelyScaledAndSchemedValue(double value,
                                                         ControlSurface controlSurface) {
        if (value != Double.NaN && controlSurface != null) {
            return scaleAndSchemeValue(value,
                    getSurfaceScale(controlSurface),
                    getSurfaceScheme(controlSurface),
                    getSurfacePreSchemeOp(controlSurface),
                    getSurfacePostSchemePreScaleOp(controlSurface),
                    getSurfacePostScaleOp(controlSurface));
        }
        return 0.0;
    }

    private double[] getSurfaceScale(ControlSurface controlSurface) {
        switch (controlSurface) {
            case LEFT_STICK_X:
                return leftStickXScale;
            case LEFT_STICK_Y:
                return leftStickYScale;
            case LEFT_TRIGGER:
                return leftTriggerScale;
            case RIGHT_STICK_X:
                return rightStickXScale;
            case RIGHT_STICK_Y:
                return rightStickYScale;
            case RIGHT_TRIGGER:
                return rightTriggerScale;
        }
        return DEFAULT_STICK_SCALE;
    }

    private ControlScheme getSurfaceScheme(ControlSurface controlSurface) {
        switch (controlSurface) {
            case LEFT_STICK_X:
                return leftStickXScheme;
            case LEFT_STICK_Y:
                return leftStickYScheme;
            case LEFT_TRIGGER:
                return leftTriggerScheme;
            case RIGHT_STICK_X:
                return rightStickXScheme;
            case RIGHT_STICK_Y:
                return rightStickYScheme;
            case RIGHT_TRIGGER:
                return rightTriggerScheme;
        }
        return DEFAULT_SCHEME;
    }

    private PreSchemeOperation<Double> getSurfacePreSchemeOp(ControlSurface controlSurface) {
        switch (controlSurface) {
            case LEFT_STICK_X:
                return leftStickXPreScheme;
            case LEFT_STICK_Y:
                return leftStickYPreScheme;
            case LEFT_TRIGGER:
                return leftTriggerPreScheme;
            case RIGHT_STICK_X:
                return rightStickXPreScheme;
            case RIGHT_STICK_Y:
                return rightStickYPreScheme;
            case RIGHT_TRIGGER:
                return rightTriggerPreScheme;
        }
        return this::defaultPreScheme;
    }

    private PostSchemePreScaleOperation<Double> getSurfacePostSchemePreScaleOp(
            ControlSurface controlSurface) {
        switch (controlSurface) {
            case LEFT_STICK_X:
                return leftStickXPostSchemePreScale;
            case LEFT_STICK_Y:
                return leftStickYPostSchemePreScale;
            case LEFT_TRIGGER:
                return leftTriggerPostSchemePreScale;
            case RIGHT_STICK_X:
                return rightStickXPostSchemePreScale;
            case RIGHT_STICK_Y:
                return rightStickYPostSchemePreScale;
            case RIGHT_TRIGGER:
                return rightTriggerPostSchemePreScale;
        }
        return this::defaultPostSchemePreScale;
    }

    private PostScaleOperation<Double> getSurfacePostScaleOp(ControlSurface controlSurface) {
        switch (controlSurface) {
            case LEFT_STICK_X:
                return leftStickXPostScale;
            case LEFT_STICK_Y:
                return leftStickYPostScale;
            case LEFT_TRIGGER:
                return leftTriggerPostScale;
            case RIGHT_STICK_X:
                return rightStickXPostScale;
            case RIGHT_STICK_Y:
                return rightStickYPostScale;
            case RIGHT_TRIGGER:
                return rightTriggerPostScale;
        }
        return this::defaultPostScale;
    }

    private double scaleAndSchemeValue(double value, double[] scale, ControlScheme scheme,
                                       PreSchemeOperation<Double> preSchemeOperation,
                                       PostSchemePreScaleOperation<Double> postSchemeOperation,
                                       PostScaleOperation<Double> postScaleOperation) {
        return (postScaleOperation.apply(Scaler.scale(postSchemeOperation.apply(
                Schemer.schemeValue(preSchemeOperation.apply(value), scheme)),
                scale[0], scale[1], scale[2], scale[3])));
    }

    private void modifyAllControlSchemes(PrevNextMethod prevNextMethod) {
        setCustomizedControlSchemes(prevNextMethod.apply(leftStickXScheme),
                prevNextMethod.apply(leftStickYScheme),
                prevNextMethod.apply(leftTriggerScheme),
                prevNextMethod.apply(rightStickXScheme),
                prevNextMethod.apply(rightStickXScheme),
                prevNextMethod.apply(rightTriggerScheme));
    }

    private boolean applyToAllSchemes(ControlScheme scheme) {
        if (scheme != null) {
            for (int i = 0; i < schemes.length; i++) {
                schemes[i] = scheme;
            }
            return true;
        }
        return false;
    }

    private boolean applyToAllStickScales(double[] scale) {
        if (scale != null) {
            for (int i = 0; i < stickScales.length; i++) {
                stickScales[i] = scale;
            }
            return true;
        }
        return false;
    }

    private boolean applyToAllTriggerScales(double[] scale) {
        if (scale != null) {
            for (int i = 0; i < triggerScales.length; i++) {
                triggerScales[i] = scale;
            }
            return true;
        }
        return false;
    }

    private double defaultPreScheme(double in) {
        return in;
    }

    private double defaultPostSchemePreScale(double in) {
        return in;
    }

    private double defaultPostScale(double in) {
        return in;
    }
}
