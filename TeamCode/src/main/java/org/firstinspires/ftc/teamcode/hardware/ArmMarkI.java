package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

final class ArmMarkI {
    private final DcMotorWrapper sliderDrive;
    private final DcMotorWrapper armAngularDrive;
    private final Servo intakeAngular;
    private final Servo lid;

    ArmMarkI(HardwareMap map, String sliderDriveName, String armAngularDriveName,
             String intakeAngularName, String lidName) {
        sliderDrive = new DcMotorWrapper(map, sliderDriveName, DcMotorSimple.Direction.FORWARD,
                MotorType.TETRIX_MAX);
        sliderDrive.freezeAtZeroPower();
        armAngularDrive = new DcMotorWrapper(map, armAngularDriveName, DcMotorSimple.Direction.FORWARD,
                MotorType.TETRIX_MAX);
        armAngularDrive.freezeAtZeroPower();
        intakeAngular = map.get(Servo.class, intakeAngularName);
        lid = map.get(Servo.class, lidName);
    }

    void setSliderDrive(double power) {
        sliderDrive.set(power);
    }

    void setArmAngularDrive(double power) {
        armAngularDrive.set(power);
    }

    void setSliderDriveForRotations(double power, double rotations) {
        sliderDrive.driveForRotations(power, rotations);
    }

    void setArmAngularDriveForRotations(double power, double rotations) {
        armAngularDrive.driveForRotations(power, rotations);
    }

    void setSliderDriveForMM(double power, int mm) {
        sliderDrive.set(power, mm);
    }

    void setArmAngularDriveForMM(double power, int mm) {
        sliderDrive.set(power, mm);
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
        armAngularDrive.freezeAtZeroPower();
        armAngularDrive.set(0);
    }

    void coastArmAngular() {
        armAngularDrive.coastAtZeroPower();
        armAngularDrive.set(0);
    }

    void freezeArmSliders() {
        sliderDrive.coastAtZeroPower();
        sliderDrive.set(0);
    }

    void coastArmSliders() {
        sliderDrive.coastAtZeroPower();
        sliderDrive.set(0);
    }
}
