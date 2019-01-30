package org.firstinspires.ftc.teamcode.hardware.components;

public final class WheelStats {
    public static final double MM_IN_AN_INCH = 25.4;

    public static final double WHEEL_DEFAULT_DIAMETER = 0.0;
    public static final double WHEEL_DIAMETER_ONE = 1.0;
    public static final double WHEEL_DIAMETER_TWO = 2.0;
    public static final double WHEEL_DIAMETER_THREE = 3.0;
    public static final double WHEEL_DIAMETER_FOUR = 4.0;
    public static final double WHEEL_DIAMETER_FIVE = 5.0;
    public static final double WHEEL_DIAMETER_SIX = 6.0;

    public static double inToMM(double in) {
        return MM_IN_AN_INCH * in;
    }

    public static double mmToIn(double mm) {
        return MM_IN_AN_INCH / mm;
    }

    public static double mmPerDriveRotationFromInDiameter(double inches) {
        return inToMM(inches) * Math.PI;
    }

    public static double mmPerDriveRotationFromInDiameter(double inches, double frictionCoefficient) {
        return mmPerDriveRotationFromInDiameter(inches) * frictionCoefficient;
    }

    public static double mmPerDriveRotationFromMMDiameter(double mm) {
        return mm * Math.PI;
    }

    public static double mmPerDriveRotationFromMMDiameter(double mm, double frictionCoefficient) {
        return mmPerDriveRotationFromMMDiameter(mm) * frictionCoefficient;
    }

    public static double inDiameterFromMMPerDriveRotation(double mmPerDriveRotation) {
        return mmToIn(mmPerDriveRotation / Math.PI);
    }

    public static double inDiameterFromMMPerDriveRotation(double mmPerDriveRotation,
                                                          double frictionCoefficient) {
        return inDiameterFromMMPerDriveRotation(mmPerDriveRotation) / frictionCoefficient;
    }

    public static double mmDiameterFromMMPerDriveRotation(double mmPerDriveRotation) {
        return mmPerDriveRotation / Math.PI;
    }

    public static double mmDiameterFromMMPerDriveRotation(double mmPerDriveRotation,
                                                          double frictionCoefficient) {
        return mmDiameterFromMMPerDriveRotation(mmPerDriveRotation) / frictionCoefficient;
    }
}
