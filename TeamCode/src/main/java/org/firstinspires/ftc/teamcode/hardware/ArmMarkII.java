package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

final class ArmMarkII {
    private final DcMotorWrapper sliderDrive;
    private final DcMotorWrapper armAngularDrive;
    private final Servo intakeAngular;

    ArmMarkII(HardwareMap map, String sliderDriveName, String armAngularDriveName,
              String intakeAngularName) {
        sliderDrive = new DcMotorWrapper(map, sliderDriveName, DcMotorSimple.Direction.FORWARD,
                MotorType.TETRIX_MAX);
        sliderDrive.freezeAtZeroPower();
        armAngularDrive = new DcMotorWrapper(map, armAngularDriveName, DcMotorSimple.Direction.FORWARD,
                MotorType.TETRIX_MAX);
        armAngularDrive.freezeAtZeroPower();
        intakeAngular = map.get(Servo.class, intakeAngularName);
    }

    double getSliderDrive() {
        return sliderDrive.getPower();
    }

    void setSliderDrive(double power) {
        sliderDrive.set(power);
    }

    double getArmAngularDrive() {
        return armAngularDrive.getPower();
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
        armAngularDrive.set(power, mm);
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
        armAngularDrive.freezeAtZeroPower();
        armAngularDrive.set(0);
    }

    boolean getArmAngularFrozen() {
        return armAngularDrive.getPower() == 0 && armAngularDrive.frozenAtZeroPower();
    }

    void coastArmAngular() {
        armAngularDrive.coastAtZeroPower();
        armAngularDrive.set(0);
    }

    boolean getArmAngularCoasting() {
        return armAngularDrive.getPower() == 0 && armAngularDrive.coastingAtZeroPower();
    }

    void freezeArmSliders() {
        sliderDrive.freezeAtZeroPower();
        sliderDrive.set(0);
    }

    boolean getArmSlidersFrozen() {
        return sliderDrive.getPower() == 0 && sliderDrive.frozenAtZeroPower();
    }

    void coastArmSliders() {
        sliderDrive.freezeAtZeroPower();
        sliderDrive.set(0);
    }

    boolean getArmSlidersCoasting() {
        return sliderDrive.getPower() == 0 && sliderDrive.coastingAtZeroPower();
    }

}
