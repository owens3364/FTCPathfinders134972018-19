package org.firstinspires.ftc.teamcode.hardware.robots.regulators;

public interface StandardDriveOpModeUsageMarkI extends Robot {
    //Any data all bots should have is listed here and implemented by class names starting with
    // "Bot"
    void setLeftDrive(double power);
    void setRightDrive(double power);
    void setIntakeDrive(double power);
    void setLiftDrive(double power);
    void setHookDrive(double power);
    void setLeftDriveForRotations(double power, double rotations);
    void setRightDriveForRotations(double power, double rotations);
    void setIntakeDriveForRotations(double power, double rotations);
    void setLiftDriveForRotations(double power, double rotations);
    void setHookDriveForRotations(double power, double rotations);
    void setLeftDriveForMM(double power, int mm);
    void setRightDriveForMM(double power, int mm);
    void setIntakeDriveForMM(double power, int mm);
    void setLiftDriveForMM(double power, int mm);
    void setHookDriveForMM(double power, int mm);
    void freezeHook();
    void freezeLift();
    void coastHook();
    void coastLift();
    void activateHook();
    void retractHook();
    void zeroAll();
    Class getKindOfBot();
}
