package org.firstinspires.ftc.teamcode.controls;

final class Schemer {
    static double schemeValue(double value, ControlScheme controlScheme) {
        if (value == 0) {
            return 0;
        }
        switch (controlScheme) {
            case LINEAR:
                //Linear controls
                return value;
            case SQRT:
                //Y = sqrt(X) controls
                if (value > 0) {
                    return Math.sqrt(value);
                }
                return -Math.sqrt(Math.abs(value));
            case CBRT:
                //Y = cbrt(X) controls
                return Math.cbrt(value);
            case SQUARED:
                //Y = X^2 controls
                if (value > 0) {
                    return Math.pow(value, 2);
                }
                return -Math.pow(Math.abs(value), 2);
            case CUBED:
                //Y = X^3 controls
                return Math.pow(value, 3);
            case FOURTH:
                //Y = X^4 controls
                if (value > 0) {
                    return Math.pow(value, 4);
                }
                return -Math.pow(Math.abs(value), 4);
        }
        return Double.NaN;
    }
}
