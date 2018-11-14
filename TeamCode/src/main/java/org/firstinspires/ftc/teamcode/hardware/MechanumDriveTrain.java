package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

final class MechanumDriveTrain {
    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor rearLeftDrive;
    private DcMotor rearRightDrive;

    MechanumDriveTrain(HardwareMap map, String frontLeftDriveName, String frontRightDriveName, String rearLeftDriveName, String rearRightDriveName) {
        frontLeftDrive = map.get(DcMotor.class, frontLeftDriveName);
        frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightDrive = map.get(DcMotor.class, frontRightDriveName);
        frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeftDrive = map.get(DcMotor.class, rearLeftDriveName);
        rearLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rearRightDrive = map.get(DcMotor.class, rearRightDriveName);
        rearRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    void setFrontLeft(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontLeftDrive.setPower(power);
        }
    }

    void setFrontRight(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontRightDrive.setPower(power);
        }
    }

    void setRearLeft(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            rearLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rearLeftDrive.setPower(power);
        }
    }

    void setRearRight(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            rearRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rearRightDrive.setPower(power);
        }
    }

    void setFrontLeftForRotations(double power, int rotations) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontLeftDrive.setPower(power);
            frontLeftDrive.setTargetPosition(frontLeftDrive.getCurrentPosition() + rotations);
        }
    }

    void setFrontRightForRotations(double power, int rotations) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRightDrive.setPower(power);
            frontRightDrive.setTargetPosition(frontRightDrive.getCurrentPosition() + rotations);
        }
    }

    void setRearLeftForRotations(double power, int rotations) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            rearLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rearLeftDrive.setPower(power);
            rearLeftDrive.setTargetPosition(frontLeftDrive.getCurrentPosition() + rotations);
        }
    }

    void setRearRightForRotations(double power, int rotations) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            rearRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rearRightDrive.setPower(power);
            rearRightDrive.setTargetPosition(frontRightDrive.getCurrentPosition() + rotations);
        }
    }
}
