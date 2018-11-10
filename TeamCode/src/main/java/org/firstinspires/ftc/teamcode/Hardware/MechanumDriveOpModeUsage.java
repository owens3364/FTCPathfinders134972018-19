package org.firstinspires.ftc.teamcode.Hardware;

public interface MechanumDriveOpModeUsage {
    //Any data all bots should have is listed here and implemented by class names starting with "Bot"
    double getMMPerDriveRotation();
    void setFrontLeftDrive(double power);
    void setFrontRightDrive(double power);
    void setRearLeftDrive(double power);
    void setRearRightDrive(double power);
    void setArmSliderDrive(double power);
    void setArmAnglerDrive(double power);
    void setIntakeAngle(double angle);
    void setLiftDrive(double power);
    void setFrontLeftDriveForRotations(double power, int rotations);
    void setFrontRightDriveForRotations(double power, int rotations);
    void setRearLeftDriveForRotations(double power, int rotations);
    void setRearRightDriveForRotations(double power, int rotations);
    void setArmSliderDriveForRotations(double power, int rotations);
    void setArmAnglerDriveForRotations(double power, int rotations);
    void setLiftDriveForRotations(double power, int rotations);
    void setFrontLeftDriveForMM(double power, int mm);
    void setFrontRightDriveForMM(double power, int mm);
    void setRearLeftDriveForMM(double power, int mm);
    void setRearRightDriveForMM(double power, int mm);
    void setArmSliderDriveForMM(double power, int mm);
    void setArmAnglerDriveForMM(double power, int mm);
    void setLiftDriveForMM(double power, int mm);
    void freezeLift();
    void coastLift();
    void freezeArmAngler();
    void coastArmAngler();
    void freezeArmSliders();
    void coastArmSliders();
    void openArmLid();
    void closeArmLid();
    void zeroAll();
    Class getKindOfBot();
}
