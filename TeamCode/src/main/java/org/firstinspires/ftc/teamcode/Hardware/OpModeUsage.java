package org.firstinspires.ftc.teamcode.Hardware;

public interface OpModeUsage {
    //Any data all bots should have is listed here and implemented by class names starting with "Bot"
    double getMMPerDriveRotation();
    void setLeftDrive(double power);
    void setRightDrive(double power);
    void setIntakeDrive(double power);
    void setLiftDrive(double power);
    void setHookDrive(double power);
    void setLeftDriveForRotations(double power, int rotations);
    void setRightDriveForRotations(double power, int rotations);
    void setIntakeDriveForRotations(double power, int rotations);
    void setLiftDriveForRotations(double power, int rotations);
    void setHookDriveForRotations(double power, int rotations);
    void setLeftDriveForMM(double power, int mm);
    void setRightDriveForMM(double power, int mm);
    void setIntakeDriveForMM(double power, int mm);
    void setLiftDriveForMM(double power, int mm);
    void setHookDriveForMM(double power, int mm);
    void freezeHook();
    void freezeLift();
    void activateHook();
    void retractHook();
    void zeroAll();
    Class getKindOfBot();
}
