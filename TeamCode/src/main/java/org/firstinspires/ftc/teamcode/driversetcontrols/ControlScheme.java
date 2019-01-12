package org.firstinspires.ftc.teamcode.driversetcontrols;

public enum ControlScheme {
    LINEAR, SQRT, CBRT, SQUARED, CUBED, FOURTH;

    ControlScheme prev() {
        switch (this) {
            case LINEAR:
                return FOURTH;
            case SQRT:
                return LINEAR;
            case CBRT:
                return SQRT;
            case SQUARED:
                return CBRT;
            case CUBED:
                return SQUARED;
            case FOURTH:
                return CUBED;
        }
        return null;
    }

    ControlScheme next() {
        switch(this) {
            case LINEAR:
                return SQRT;
            case SQRT:
                return CBRT;
            case CBRT:
                return SQUARED;
            case SQUARED:
                return CUBED;
            case CUBED:
                return FOURTH;
            case FOURTH:
                return LINEAR;
        }
        return null;
    }
}
