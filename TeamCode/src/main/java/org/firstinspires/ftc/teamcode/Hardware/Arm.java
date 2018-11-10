package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public final class Arm {
    private DcMotor sliderDrive;
    private DcMotor armAnglerDrive;
    private Servo intakeAngler;
    private Servo lid;

    static Arm initialize(HardwareMap map, String sliderDriveName, String armAnglerName, String intakeAnglerName, String lidName) {
        DcMotor sliderDrive = map.get(DcMotor.class, sliderDriveName);
        sliderDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        sliderDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DcMotor armAnglerDrive = map.get(DcMotor.class, armAnglerName);
        armAnglerDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        armAnglerDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Servo intakeAngler = map.get(Servo.class, intakeAnglerName);
        Servo lid = map.get(Servo.class, lidName);
        return new Arm(sliderDrive, armAnglerDrive, intakeAngler, lid);
    }

    private Arm(DcMotor sliderDrive, DcMotor armAnglerDrive, Servo intakeAngler, Servo lid) {
        this.sliderDrive = sliderDrive;
        this.armAnglerDrive = armAnglerDrive;
        this.intakeAngler = intakeAngler;
        this.lid = lid;
    }

    void setSliderDrive(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            sliderDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            sliderDrive.setPower(power);
        }
    }

    void setArmAnglerDrive(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            armAnglerDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            armAnglerDrive.setPower(power);
        }
    }

    void setSliderDriveForRotations(double power, int rotations) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            sliderDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            sliderDrive.setPower(power);
            sliderDrive.setTargetPosition(sliderDrive.getCurrentPosition() + rotations);
        }
    }

    void setArmAnglerDriveForRotations(double power, int rotations) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            armAnglerDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            armAnglerDrive.setPower(power);
            armAnglerDrive.setTargetPosition(armAnglerDrive.getCurrentPosition() + rotations);
        }
    }

    void setIntakeAngle(double angle) {
        if (HardwareInput.validate(angle, InputType.FOR_SERVO)) {
            intakeAngler.setPosition(angle);
        }
    }

    void openLid() {
        lid.setPosition(Servo.MAX_POSITION);
    }

    void closeLid() {
        lid.setPosition(Servo.MIN_POSITION);
    }

    void freezeArmAngler() {
        armAnglerDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armAnglerDrive.setPower(0);
    }

    void coastArmAngler() {
        armAnglerDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        armAnglerDrive.setPower(0);
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
