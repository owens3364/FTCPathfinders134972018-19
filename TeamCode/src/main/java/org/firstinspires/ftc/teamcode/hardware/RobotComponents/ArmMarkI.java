package org.firstinspires.ftc.teamcode.hardware.RobotComponents;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;

public final class ArmMarkI {
    private final DcMotorWrapper sliderDrive;
    private final DcMotorWrapper armAngularDrive;
    private final Servo intakeAngular;
    private final Servo lid;

    public ArmMarkI(HardwareMap map, String sliderDriveName, String armAngularDriveName,
             String intakeAngularName, String lidName) {
        sliderDrive = new DcMotorWrapper(map, sliderDriveName, DcMotorSimple.Direction.FORWARD,
                Motor.TetrixMotor);
        sliderDrive.freezeAtZeroPower();
        armAngularDrive = new DcMotorWrapper(map, armAngularDriveName, DcMotorSimple.Direction.FORWARD,
                Motor.TetrixMotor);
        armAngularDrive.freezeAtZeroPower();
        intakeAngular = map.get(Servo.class, intakeAngularName);
        lid = map.get(Servo.class, lidName);
    }

    public void setSliderDrive(double power) {
        sliderDrive.set(power);
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
        sliderDrive.set(power, mm);
    }

    public void setIntakeAngle(double angle) {
        if (HardwareInput.validate(angle, InputType.FOR_SERVO)) {
            intakeAngular.setPosition(angle);
        }
    }

    public void openLid() {
        lid.setPosition(Servo.MAX_POSITION);
    }

    public void closeLid() {
        lid.setPosition(Servo.MIN_POSITION);
    }

    public void freezeArmAngular() {
        armAngularDrive.freezeAtZeroPower();
        armAngularDrive.set(0);
    }

    public void coastArmAngular() {
        armAngularDrive.coastAtZeroPower();
        armAngularDrive.set(0);
    }

    public void freezeArmSliders() {
        sliderDrive.coastAtZeroPower();
        sliderDrive.set(0);
    }

    public void coastArmSliders() {
        sliderDrive.coastAtZeroPower();
        sliderDrive.set(0);
    }
}
