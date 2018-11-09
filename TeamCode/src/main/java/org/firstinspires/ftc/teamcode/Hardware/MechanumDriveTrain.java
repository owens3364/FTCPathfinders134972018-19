package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

final class MechanumDriveTrain {
    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;

    static MechanumDriveTrain initialize(HardwareMap map, String leftDriveName, String rightDriveName) {
        DcMotor frontLeftDrive = map.get(DcMotor.class, leftDriveName);
        frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        DcMotor frontRightDrive = map.get(DcMotor.class, rightDriveName);
        frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        return new MechanumDriveTrain(frontLeftDrive, frontRightDrive);
    }

    private MechanumDriveTrain(DcMotor leftDrive, DcMotor rightDrive) {
        this.frontLeftDrive = leftDrive;
        this.frontRightDrive = rightDrive;
    }

    void setLeft(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontLeftDrive.setPower(power);
        }
    }

    void setRight(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontRightDrive.setPower(power);
        }
    }

    void setLeftForRotations(double power, int rotations) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontLeftDrive.setPower(power);
            frontLeftDrive.setTargetPosition(frontLeftDrive.getCurrentPosition() + rotations);
        }
    }

    void setRightForRotations(double power, int rotations) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRightDrive.setPower(power);
            frontRightDrive.setTargetPosition(frontRightDrive.getCurrentPosition() + rotations);
        }
    }
}
