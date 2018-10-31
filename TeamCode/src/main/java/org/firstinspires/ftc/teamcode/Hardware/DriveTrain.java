package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

final class DriveTrain {
    private DcMotor leftDrive;
    private DcMotor rightDrive;

    static DriveTrain initialize(HardwareMap map, String leftDriveName, String rightDriveName) {
        DcMotor leftDrive = map.get(DcMotor.class, leftDriveName);
        leftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        DcMotor rightDrive = map.get(DcMotor.class, rightDriveName);
        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        return new DriveTrain(leftDrive, rightDrive);
    }

    private DriveTrain(DcMotor leftDrive, DcMotor rightDrive) {
        this.leftDrive = leftDrive;
        this.rightDrive = rightDrive;
    }

    void setLeft(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftDrive.setPower(power);
        }
    }

    void setRight(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightDrive.setPower(power);
        }
    }

    void setLeftForRotations(double power, int rotations) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftDrive.setPower(power);
            leftDrive.setTargetPosition(leftDrive.getCurrentPosition() + rotations);
        }
    }

    void setRightForRotations(double power, int rotations) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightDrive.setPower(power);
            rightDrive.setTargetPosition(rightDrive.getCurrentPosition() + rotations);
        }
    }
}
