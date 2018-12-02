package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

final class ArmMarkII {
    private final DcMotor sliderDrive;
    private final DcMotor armAngularDrive;
    private final Servo intakeAngular;

    ArmMarkII(HardwareMap map, String sliderDriveName, String armAngularName, String intakeAngularName) {
        sliderDrive = map.get(DcMotor.class, sliderDriveName);
        sliderDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        sliderDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armAngularDrive = map.get(DcMotor.class, armAngularName);
        armAngularDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        armAngularDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeAngular = map.get(Servo.class, intakeAngularName);
    }

    double getSliderDrive() {
        return sliderDrive.getPower();
    }

    void setSliderDrive(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            sliderDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            sliderDrive.setPower(power);
        }
    }

    double getArmAngularDrive() {
        return armAngularDrive.getPower();
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

    double getIntakeAngle() {
        return intakeAngular.getPosition();
    }

    void setIntakeAngle(double angle) {
        if (HardwareInput.validate(angle, InputType.FOR_SERVO)) {
            intakeAngular.setPosition(angle);
        }
    }

    void freezeArmAngular() {
        armAngularDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armAngularDrive.setPower(0);
    }

    boolean getArmAngularFrozen() {
        return armAngularDrive.getPower() == 0 && armAngularDrive.getZeroPowerBehavior() == DcMotor.ZeroPowerBehavior.BRAKE;
    }

    void coastArmAngular() {
        armAngularDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        armAngularDrive.setPower(0);
    }

    boolean getArmAngularCoasting() {
        return armAngularDrive.getPower() == 0 && armAngularDrive.getZeroPowerBehavior() == DcMotor.ZeroPowerBehavior.FLOAT;
    }

    void freezeArmSliders() {
        sliderDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sliderDrive.setPower(0);
    }

    boolean getArmSlidersFrozen() {
        return sliderDrive.getPower() == 0 && sliderDrive.getZeroPowerBehavior() == DcMotor.ZeroPowerBehavior.BRAKE;
    }

    void coastArmSliders() {
        sliderDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        sliderDrive.setPower(0);
    }

    boolean getArmSlidersCoasting() {
        return sliderDrive.getPower() == 0 && sliderDrive.getZeroPowerBehavior() == DcMotor.ZeroPowerBehavior.FLOAT;
    }

}
