package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

public final class Bot {
    //Public methods for use
    //All hardware connections handled privately

    //Set up all hardware objects
    public static void initialize(HardwareMap map) {
        DriveTrain.initialize(map);
        Lift.initialize(map);
        Intake.initialize(map);
        Arm.initialize(map);
        TeamMarkerPositioner.initialize(map);
    }

    //Drive motors by power
    public static void setLeftDrive(double power) {
        DriveTrain.setLeft(power);
    }

    public static void setRightDrive(double power) {
        DriveTrain.setRight(power);
    }

    public static void setVerticalDrive(double power) {
        DriveTrain.setVertical(power);
    }

    public static void setLockDrive(double power) {
        Lift.setCableLock(power);
    }

    public static void setCableDrive(double power) {
        Lift.setCableDrive(power);
    }

    public static void verticallyFrozen(boolean frozen) {
        Lift.freeze();
    }

    public static void setIntakeDrive(double power) {
        Intake.set(power);
    }

    public static void setSlidersDrive(double power) {
        Arm.setSlidersDrive(power);
    }

    //Drive servos by position
    public static void setSlidersYaw(double position) {
        Arm.setSlidersYaw(position);
    }

    public static void setSlidersPitch(double position) {
        Arm.setSlidersPitch(position);
    }

    public static void setLeftGrip(double position) {
        Arm.setLeftGrip(position);
    }

    public static void setRightGrip(double position) {
        Arm.setRightGrip(position);
    }

    public static void dumpMarker() {
        TeamMarkerPositioner.dump();
    }

    //Drive motors by rotations
    public static void setLeftDriveForRotations(double power, int rotations) {
        DriveTrain.setLeftForRotations(power, rotations);
    }

    public static void setRightDriveForRotations(double power, int rotations) {
        DriveTrain.setRightForRotations(power, rotations);
    }

    public static void setCableDriveForRotations(double power, int rotations) {
        Lift.setCableDriveForRotations(power, rotations);
    }

    public static void setIntakeDriveForRotations(double power, int rotations) {
        Intake.setForRotations(power, rotations);
    }

    public static void setSlidersDriveForRotations(double power, int rotations) {
        Arm.setSlidersDriveForRotations(power, rotations);
    }
}
