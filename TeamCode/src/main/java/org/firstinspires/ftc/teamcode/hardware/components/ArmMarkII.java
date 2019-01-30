package org.firstinspires.ftc.teamcode.hardware.components;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardware.componentinterfaces.Component;
import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;
import org.firstinspires.ftc.teamcode.xmlio.XMLUtils;

public final class ArmMarkII implements Component {
    private final DcMotorWrapper sliderDrive;
    private final String sliderDriveName;
    private final boolean sliderDriveIsPrimary;
    private final String sliderDrivePort;

    private final DcMotorWrapper armAngularDrive;
    private final String armAngularDriveName;
    private final boolean armAngularDriveIsPrimary;
    private final String armAngularDrivePort;

    private final ServoWrapper intakeAngular;
    private final String intakeAngularName;
    private final boolean intakeAngularIsPrimary;
    private final String intakeAngularPort;

    public ArmMarkII(HardwareMap map, String sliderDriveName, String armAngularDriveName,
                     String intakeAngularName, boolean sliderDriveIsPrimary,
                     boolean armAngularDriveIsPrimary, boolean intakeAngularIsPrimary,
                     String sliderDrivePort, String armAngularDrivePort,
                     String intakeAngularPort, Motor sliderDriveType, Motor armAngularDriveType,
                     org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices
                             .Servo intakeAngularType) {
        sliderDrive = new DcMotorWrapper(map, sliderDriveName, DcMotorSimple.Direction.FORWARD,
                sliderDriveType, WheelStats.WHEEL_DIAMETER_FOUR);
        sliderDrive.freezeAtZeroPower();
        armAngularDrive = new DcMotorWrapper(map, armAngularDriveName,
                DcMotorSimple.Direction.FORWARD, armAngularDriveType,
                WheelStats.WHEEL_DIAMETER_FOUR);
        armAngularDrive.freezeAtZeroPower();
        intakeAngular = new ServoWrapper(map, intakeAngularName, Servo.Direction.FORWARD,
                intakeAngularType, WheelStats.WHEEL_DEFAULT_DIAMETER);

        this.sliderDriveName = sliderDriveName;
        this.armAngularDriveName = armAngularDriveName;
        this.intakeAngularName = intakeAngularName;

        this.sliderDriveIsPrimary = sliderDriveIsPrimary;
        this.armAngularDriveIsPrimary = armAngularDriveIsPrimary;
        this.intakeAngularIsPrimary = intakeAngularIsPrimary;

        this.sliderDrivePort = sliderDrivePort;
        this.armAngularDrivePort = armAngularDrivePort;
        this.intakeAngularPort = intakeAngularPort;
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
        return intakeAngular.get();
    }

    public void setIntakeAngle(double angle) {
        intakeAngular.set(angle);
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

    @Override
    public ElementWrapper[] getHardwareDeviceElements() {
        return new ElementWrapper[] {
                new ElementWrapper(sliderDriveIsPrimary, XMLUtils.generateHardwareElement(
                        sliderDrive.getMotorType(), sliderDriveName, sliderDrivePort
                )),
                new ElementWrapper(armAngularDriveIsPrimary, XMLUtils.generateHardwareElement(
                        armAngularDrive.getMotorType(), armAngularDriveName, armAngularDrivePort
                )),
                new ElementWrapper(intakeAngularIsPrimary, XMLUtils.generateHardwareElement(
                        intakeAngular.getServoType(), intakeAngularName, intakeAngularPort
                ))
        };
    }
}
