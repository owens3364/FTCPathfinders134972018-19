package org.firstinspires.ftc.teamcode.hardware.robots.regulators;

import org.firstinspires.ftc.teamcode.hardware.components.Direction;

public interface MecanumDriveOpModeUsageMarkIV extends Robot {
    void driveMotorsBySticks(double leftRight, double forwardBackward, double turn);
    double getFrontLeftDrive();
    double getFrontRightDrive();
    double getRearLeftDrive();
    double getRearRightDrive();

    double getLiftDrive();

    double getSliderDrive();
    double getAngular();
    double getIntakeDrive();
    double getLid();

    void setAngularMin(double encoderCount);
    void setAngularMax(double encoderCount);
    double getAngularMin();
    double getAngularMax();

    void setFrontLeftDrive(double power);
    void setFrontRightDrive(double power);
    void setRearLeftDrive(double power);
    void setRearRightDrive(double power);
    void setFrontLeftDrive(double power, double rotations);
    void setFrontRightDrive(double power, double rotations);
    void setRearLeftDrive(double power, double rotations);
    void setRearRightDrive(double power, double rotations);
    void setFrontLeftDriveForMM(double power, int mm);
    void setFrontRightDriveForMM(double power, int mm);
    void setRearLeftDriveForMM(double power, int mm);
    void setRearRightDriveForMM(double power, int mm);

    void setLiftDrive(double power);
    void setLiftDrive(double power, double rotations);
    void setLiftDriveForMM(double power, int mm);

    void setArmSliderDrive(double power);
    void setArmSliderDrive(double power, double rotations);
    void setArmSliderDriveForMM(double power, int mm);
    void setArmAngular(double power, double position);
    void setIntakeDrive(double power);
    void setIntakeDrive(double power, double rotations);
    void setIntakeDriveForMM(double power, int mm);
    void setIntakeLid(double position);

    boolean liftDriveCoasting();
    boolean liftDriveFrozen();

    boolean sliderDriveCoasting();
    boolean sliderDriveFrozen();
    boolean intakeDriveCoasting();
    boolean intakeDriveFrozen();

    void coastLift();
    void freezeLift();

    void coastArmSliders();
    void freezeArmSliders();
    void coastIntake();
    void freezeIntakeDrive();

    void allMecanumDriveMotors(double power);
    void allMecanumDriveMotors(double power, double rotations);
    void allMecanumDriveMotorsForMM(double power, int mm);
    void strafe(Direction direction, double power);
    void strafe(Direction direction, double power, double rotations);
    void strafeForMM(Direction direction, double power, int mm);
    void turn(Direction direction, double power);
    void turn(Direction direction, double power, double rotations);
    void turnForMM(Direction direction, double power, int mm);
    void turnForDegrees(Direction direction, double power, double degrees);

    void deployMarker();

    void zeroAll();
    Class getKindOfBot();
}
