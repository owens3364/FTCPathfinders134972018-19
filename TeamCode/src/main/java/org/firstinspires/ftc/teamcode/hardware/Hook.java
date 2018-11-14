package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

final class Hook {
    private DcMotor hookDrive;
    private Servo hookServo;

    Hook(HardwareMap map, String hookDriveName, String hookServoName) {
        hookDrive = map.get(DcMotor.class, hookDriveName);
        hookDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        hookDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hookServo = map.get(Servo.class, hookServoName);
    }

    void setDrive(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            hookDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            hookDrive.setPower(power);
        }
    }

    void setDriveForRotations(double power, int rotations) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            hookDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            hookDrive.setPower(power);
            hookDrive.setTargetPosition(hookDrive.getCurrentPosition() + rotations);
        }
    }

    void activate() {
        hookServo.setPosition(Servo.MAX_POSITION / 2);
    }

    void retract () {
        hookServo.setPosition(Servo.MIN_POSITION);
    }

    void freeze() {
        hookDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hookDrive.setPower(0);
    }

    void coast() {
        hookDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        hookDrive.setPower(0);
    }
}
