package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

final class DriveTrain {
    //DriveTrain Motors
    private static DcMotor frontLeftDrive;
    private static DcMotor rearLeftDrive;
    private static DcMotor frontRightDrive;
    private static DcMotor rearRightDrive;
    private static LimitedDrive verticalDrive;

    //Bot use methods
    static void initialize(HardwareMap map) {
        frontLeftDrive = map.get(DcMotor.class, "Front_Left_Drive");
        frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rearLeftDrive = map.get(DcMotor.class, "Rear_Left_Drive");
        rearLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightDrive = map.get(DcMotor.class, "Front_Right_Drive");
        frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRightDrive = map.get(DcMotor.class, "Rear_Right_Drive");
        rearRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        verticalDrive = new LimitedDrive("Vertical_Drive", "Vertical_Min", "Vertical_Max", map);
    }

    //Run motors by power
    static void setLeft(double power) {
        runWithEncoders(false);
        frontLeftDrive.setPower(power);
        rearLeftDrive.setPower(power);
    }

    static void setRight(double power) {
        runWithEncoders(false);
        frontRightDrive.setPower(power);
        rearRightDrive.setPower(power);
    }

    //Run motors by rotations
    static void setLeftForRotations(double power, int rotations) {
        runWithEncoders(true);
        frontLeftDrive.setPower(power);
        frontLeftDrive.setTargetPosition(frontLeftDrive.getCurrentPosition() + rotations);
        rearLeftDrive.setPower(power);
        rearLeftDrive.setTargetPosition(rearLeftDrive.getCurrentPosition() + rotations);
    }

    static void setRightForRotations(double power, int rotations) {
        runWithEncoders(true);
        frontRightDrive.setPower(power);
        frontRightDrive.setTargetPosition(frontRightDrive.getCurrentPosition() + rotations);
        rearRightDrive.setPower(power);
        rearRightDrive.setTargetPosition(rearRightDrive.getCurrentPosition() + rotations);
    }

    //Safely use vertical lift, do not allow it to exceed its limits
    static void setVertical(double power) {
        verticalDrive.set(power);
    }

    //Private method for running motors with or without encoders
    private static void runWithEncoders(boolean withEncoders) {
        if (withEncoders) {
            frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rearLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rearRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else {
            frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rearLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rearRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }
}
