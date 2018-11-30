package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

final class DriveTrain {
    private final DcMotor leftDrive;
    private final DcMotor rightDrive;

    DriveTrain(HardwareMap map, String leftDriveName, String rightDriveName) {
        leftDrive = map.get(DcMotor.class, leftDriveName);
        leftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDrive = map.get(DcMotor.class, rightDriveName);
        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
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
