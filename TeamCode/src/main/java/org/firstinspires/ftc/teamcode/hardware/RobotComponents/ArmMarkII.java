package org.firstinspires.ftc.teamcode.hardware.RobotComponents;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;

public final class ArmMarkII {
    private final DcMotorWrapper sliderDrive;
    private final DcMotorWrapper armAngularDrive;
    private final Servo intakeAngular;

    public ArmMarkII(HardwareMap map, String sliderDriveName, String armAngularDriveName,
              String intakeAngularName) {
        sliderDrive = new DcMotorWrapper(map, sliderDriveName, DcMotorSimple.Direction.FORWARD,
                Motor.TetrixMotor);
        sliderDrive.freezeAtZeroPower();
        armAngularDrive = new DcMotorWrapper(map, armAngularDriveName, DcMotorSimple.Direction.FORWARD,
                Motor.TetrixMotor);
        armAngularDrive.freezeAtZeroPower();
        intakeAngular = map.get(Servo.class, intakeAngularName);
    }

    public double getSliderDrive() {
        return sliderDrive.getPower();
    }

    public void setSliderDrive(double power) {
        sliderDrive.set(power);
    }

    public double getArmAngularDrive() {
        return armAngularDrive.getPower();
    }

    public void setArmAngularDrive(double power) {
        armAngularDrive.set(power);
    }

    public void setSliderDriveForRotations(double power, double rotations) {
        sliderDrive.driveForRotations(power, rotations);
    }

    public void setArmAngularDriveForRotations(double power, double rotations) {
        armAngularDrive.driveForRotations(power, rotations);
    }

    public void setSliderDriveForMM(double power, int mm) {
        sliderDrive.set(power, mm);
    }

    public void setArmAngularDriveForMM(double power, int mm) {
        armAngularDrive.set(power, mm);
    }

    public double getIntakeAngle() {
        return intakeAngular.getPosition();
    }

    public void setIntakeAngle(double angle) {
        if (HardwareInput.validate(angle, InputType.FOR_SERVO)) {
            intakeAngular.setPosition(angle);
        }
    }

    public void freezeArmAngular() {
        armAngularDrive.freezeAtZeroPower();
        armAngularDrive.set(0);
    }

    public boolean getArmAngularFrozen() {
        return armAngularDrive.getPower() == 0 && armAngularDrive.frozenAtZeroPower();
    }

    public void coastArmAngular() {
        armAngularDrive.coastAtZeroPower();
        armAngularDrive.set(0);
    }

    public boolean getArmAngularCoasting() {
        return armAngularDrive.getPower() == 0 && armAngularDrive.coastingAtZeroPower();
    }

    public void freezeArmSliders() {
        sliderDrive.freezeAtZeroPower();
        sliderDrive.set(0);
    }

    public boolean getArmSlidersFrozen() {
        return sliderDrive.getPower() == 0 && sliderDrive.frozenAtZeroPower();
    }

    public void coastArmSliders() {
        sliderDrive.freezeAtZeroPower();
        sliderDrive.set(0);
    }

    public boolean getArmSlidersCoasting() {
        return sliderDrive.getPower() == 0 && sliderDrive.coastingAtZeroPower();
    }

}
