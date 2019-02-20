package org.firstinspires.ftc.teamcode.hardware.components;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardware.components.regulators.Component;
import org.firstinspires.ftc.teamcode.hardware.configuration.devices.Motor;
import org.firstinspires.ftc.teamcode.xmlIO.XMLUtils;

public final class ArmMarkIII implements Component {
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

    private final DcMotorWrapper secondaryArmAngularDrive;
    private final String secondaryArmAngularDriveName;
    private final boolean secondaryArmAngularDriveIsPrimary;
    private final String secondaryArmAngularDrivePort;

    public ArmMarkIII(HardwareMap map, String sliderDriveName, String armAngularDriveName,
                      String intakeAngularName, String secondaryArmAngularDriveName,
                      boolean sliderDriveIsPrimary, boolean armAngularDriveIsPrimary,
                      boolean intakeAngularIsPrimary, boolean secondaryArmAngularDriveIsPrimary,
                      String sliderDrivePort, String armAngularDrivePort,
                      String intakeAngularPort, String secondaryArmAngularDrivePort,
                      Motor sliderDriveType, Motor armAngularDriveType,
                      org.firstinspires.ftc.teamcode.hardware.configuration.devices
                             .Servo intakeAngularType, Motor secondaryArmAngularDriveType) {
        sliderDrive = new DcMotorWrapper(map, sliderDriveName, DcMotorSimple.Direction.FORWARD,
                sliderDriveType, WheelStats.WHEEL_DIAMETER_FOUR);
        sliderDrive.freezeAtZeroPower();
        armAngularDrive = new DcMotorWrapper(map, armAngularDriveName,
                DcMotorSimple.Direction.FORWARD, armAngularDriveType,
                WheelStats.WHEEL_DIAMETER_FOUR);
        armAngularDrive.freezeAtZeroPower();
        intakeAngular = new ServoWrapper(map, intakeAngularName, Servo.Direction.FORWARD,
                intakeAngularType);
        secondaryArmAngularDrive = new DcMotorWrapper(map, secondaryArmAngularDriveName,
                DcMotorSimple.Direction.FORWARD, secondaryArmAngularDriveType,
                WheelStats.WHEEL_DIAMETER_FOUR);

        this.sliderDriveName = sliderDriveName;
        this.armAngularDriveName = armAngularDriveName;
        this.intakeAngularName = intakeAngularName;
        this.secondaryArmAngularDriveName = secondaryArmAngularDriveName;

        this.sliderDriveIsPrimary = sliderDriveIsPrimary;
        this.armAngularDriveIsPrimary = armAngularDriveIsPrimary;
        this.intakeAngularIsPrimary = intakeAngularIsPrimary;
        this.secondaryArmAngularDriveIsPrimary = secondaryArmAngularDriveIsPrimary;

        this.sliderDrivePort = sliderDrivePort;
        this.armAngularDrivePort = armAngularDrivePort;
        this.intakeAngularPort = intakeAngularPort;
        this.secondaryArmAngularDrivePort = secondaryArmAngularDrivePort;
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

    public double getSecondaryArmAngularDrive() {
        return secondaryArmAngularDrive.getPower();
    }

    public void setSecondaryArmAngularDrive(double power) {
        secondaryArmAngularDrive.set(power);
    }


    public void setSliderDriveForRotations(double power, double rotations) {
        sliderDrive.driveForRotations(power, rotations);
    }

    public void setArmAngularDriveForRotations(double power, double rotations) {
        armAngularDrive.driveForRotations(power, rotations);
    }

    public void setSecondaryArmAngularDriveForRotations(double power, double rotations) {
        secondaryArmAngularDrive.driveForRotations(power, rotations);
    }

    public void setSliderDriveForMM(double power, int mm) {
        sliderDrive.set(power, mm);
    }

    public void setArmAngularDriveForMM(double power, int mm) {
        armAngularDrive.set(power, mm);
    }

    public void setSecondaryArmAngularDriveForMM(double power, int mm) {
        secondaryArmAngularDrive.set(power, mm);
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

    public void freezeSecondaryArmAngular() {
        secondaryArmAngularDrive.freezeAtZeroPower();
        secondaryArmAngularDrive.set(0);
    }

    public boolean getSecondaryArmAngularFrozen() {
        return secondaryArmAngularDrive.getPower() == 0 &&
                secondaryArmAngularDrive.frozenAtZeroPower();
    }

    public void coastSecondaryArmAngular() {
        secondaryArmAngularDrive.coastAtZeroPower();
        secondaryArmAngularDrive.set(0);
    }

    public boolean getSecondaryArmAngularCoasting() {
        return secondaryArmAngularDrive.getPower() == 0 &&
                secondaryArmAngularDrive.coastingAtZeroPower();
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
                )),
                new ElementWrapper(secondaryArmAngularDriveIsPrimary, XMLUtils
                        .generateHardwareElement(secondaryArmAngularDrive.getMotorType(),
                                secondaryArmAngularDriveName, secondaryArmAngularDrivePort))
        };
    }
}
