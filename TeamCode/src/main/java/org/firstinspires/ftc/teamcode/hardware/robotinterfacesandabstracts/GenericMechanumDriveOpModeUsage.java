package org.firstinspires.ftc.teamcode.hardware.robotinterfacesandabstracts;

import org.firstinspires.ftc.teamcode.hardware.components.Direction;

public interface GenericMechanumDriveOpModeUsage {
    //Any data all bots should have is listed here and implemented by class names starting with
    // "Bot"
    void setFrontLeftDrive(double power);
    void setFrontRightDrive(double power);
    void setRearLeftDrive(double power);
    void setRearRightDrive(double power);
    void setArmSliderDrive(double power);
    void setArmAngularDrive(double power);
    void setIntakeAngle(double angle);
    void setLiftDrive(double power);
    void setFrontLeftDriveForRotations(double power, double rotations);
    void setFrontRightDriveForRotations(double power, double rotations);
    void setRearLeftDriveForRotations(double power, double rotations);
    void setRearRightDriveForRotations(double power, double rotations);
    void setArmSliderDriveForRotations(double power, double rotations);
    void setArmAngularDriveForRotations(double power, double rotations);
    void setLiftDriveForRotations(double power, double rotations);
    void setFrontLeftDriveForMM(double power, int mm);
    void setFrontRightDriveForMM(double power, int mm);
    void setRearLeftDriveForMM(double power, int mm);
    void setRearRightDriveForMM(double power, int mm);
    void setArmSliderDriveForMM(double power, int mm);
    void setArmAngularDriveForMM(double power, int mm);
    void setLiftDriveForMM(double power, int mm);
    void freezeLift();
    void coastLift();
    void freezeArmAngular();
    void coastArmAngular();
    void freezeArmSliders();
    void coastArmSliders();
    void freezeAllMechanumDriveMotors();
    void allMechanumDriveMotors(double power);
    void allMechanumDriveMotors(double power, int mm);
    void allMechanumDriveMotors(double power, double rotations);
    void strafe(Direction direction, double power);
    void strafe(Direction direction, double power, int mm);
    void turn(Direction direction, double power);
    void turn(Direction direction, double power, double degrees);
    void zeroAll();
    Class getKindOfBot();
}
