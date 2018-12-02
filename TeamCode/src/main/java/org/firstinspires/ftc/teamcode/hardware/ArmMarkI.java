package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

final class ArmMarkI {
    private final DcMotor sliderDrive;
    private final DcMotor armAngularDrive;
    private final Servo intakeAngular;
    private final Servo lid;

    ArmMarkI(HardwareMap map, String sliderDriveName, String armAngularName, String intakeAngularName, String lidName) {
        sliderDrive = map.get(DcMotor.class, sliderDriveName);
        sliderDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        sliderDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armAngularDrive = map.get(DcMotor.class, armAngularName);
        armAngularDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        armAngularDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeAngular = map.get(Servo.class, intakeAngularName);
        lid = map.get(Servo.class, lidName);
    }

    void setSliderDrive(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            sliderDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            sliderDrive.setPower(power);
        }
    }

    void setArmAngularDrive(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            armAngularDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            armAngularDrive.setPower(power);
        }
    }

    void setSliderDriveForRotations(double power, int rotations) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            sliderDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            sliderDrive.setPower(power);
            sliderDrive.setTargetPosition(sliderDrive.getCurrentPosition() + rotations);
        }
    }

    void setArmAngularDriveForRotations(double power, int rotations) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            armAngularDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            armAngularDrive.setPower(power);
            armAngularDrive.setTargetPosition(armAngularDrive.getCurrentPosition() + rotations);
        }
    }

    void setIntakeAngle(double angle) {
        if (HardwareInput.validate(angle, InputType.FOR_SERVO)) {
            intakeAngular.setPosition(angle);
        }
    }

    void openLid() {
        lid.setPosition(Servo.MAX_POSITION);
    }

    void closeLid() {
        lid.setPosition(Servo.MIN_POSITION);
    }

    void freezeArmAngular() {
        armAngularDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armAngularDrive.setPower(0);
    }

    void coastArmAngular() {
        armAngularDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        armAngularDrive.setPower(0);
    }

    void freezeArmSliders() {
        sliderDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sliderDrive.setPower(0);
    }

    void coastArmSliders() {
        sliderDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        sliderDrive.setPower(0);
    }
}
