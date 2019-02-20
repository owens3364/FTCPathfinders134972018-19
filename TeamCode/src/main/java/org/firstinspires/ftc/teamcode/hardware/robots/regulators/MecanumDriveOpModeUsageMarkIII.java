package org.firstinspires.ftc.teamcode.hardware.robots.regulators;

public interface MecanumDriveOpModeUsageMarkIII extends MecanumDriveOpModeUsageMarkII {
    void setSecondaryArmAngularDrive(double power);
    double getSecondaryArmAngularDrive();
    void setSecondaryArmAngularDriveForRotations(double power, double rotations);
    void setSecondaryArmAngularDriveForMM(double power, int mm);
    void freezeSecondaryArmAngularDrive();
    boolean getSecondaryArmAngularDriveFrozen();
    void coastSecondaryArmAngularDrive();
    boolean getSecondaryArmAngularDriveCoasting();
    void deployMarker();
}
