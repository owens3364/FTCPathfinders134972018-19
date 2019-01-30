package org.firstinspires.ftc.teamcode.hardware.robotinterfacesandabstracts;

public interface MechanumDriveOpModeUsageMarkIII extends MechanumDriveOpModeUsageMarkII {
    void setSecondaryArmAngularDrive(double power);
    double getSecondaryArmAngularDrive();
    void setSecondaryArmAngularDriveForRotations(double power, double rotations);
    void setSecondaryArmAngularDriveForMM(double power, int mm);
    void freezeSecondaryArmAngularDrive();
    boolean getSecondaryArmAngularDriveFrozen();
    void coastSecondaryArmAngularDrive();
    boolean getSecondaryArmAngularDriveCoasting();
}
