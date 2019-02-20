package org.firstinspires.ftc.teamcode.hardware.components;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.components.regulators.Component;
import org.firstinspires.ftc.teamcode.hardware.configuration.devices.Motor;
import org.firstinspires.ftc.teamcode.hardware.configuration.devices.Servo;
import org.firstinspires.ftc.teamcode.xmlIO.XMLUtils;

public final class ArmMarkIV implements Component {
    private final DcMotorWrapper sliderDrive;
    private final String sliderDriveName;
    private final boolean sliderDriveIsPrimary;
    private final String sliderDrivePort;

    private final DcMotorServoAdaptation angular;

    private final DcMotorWrapper intakeDrive;
    private final String intakeDriveName;
    private final boolean intakeDriveIsPrimary;
    private final String intakeDrivePort;

    private final ServoWrapper intakeLid;
    private final String intakeLidName;
    private final boolean intakeLidIsPrimary;
    private final String intakeLidPort;

    public ArmMarkIV(HardwareMap map, String sliderDriveName, String angularName,
                     String intakeDriveName, String intakeLidName, boolean sliderDriveIsPrimary,
                     boolean angularIsPrimary, boolean intakeDriveIsPrimary,
                     boolean intakeLidIsPrimary, String sliderDrivePort, String angularPort,
                     String intakeDrivePort, String intakeLidPort, Motor sliderDriveType,
                     Motor angularType, Motor intakeDriveType, Servo intakeLidType) {
        sliderDrive = new DcMotorWrapper(map, sliderDriveName, DcMotorSimple.Direction.FORWARD,
                sliderDriveType, WheelStats.WHEEL_DIAMETER_FOUR);
        angular = new DcMotorServoAdaptation(map, angularName, null,
                angularIsPrimary, null, angularPort,
                null, angularType, null,
                DcMotorSimple.Direction.FORWARD, null,
                WheelStats.WHEEL_DIAMETER_FOUR, null);
        intakeDrive = new DcMotorWrapper(map, intakeDriveName, DcMotorSimple.Direction.FORWARD,
                intakeDriveType, WheelStats.WHEEL_DIAMETER_FOUR);
        intakeLid = new ServoWrapper(map, intakeLidName,
                com.qualcomm.robotcore.hardware.Servo.Direction.FORWARD, intakeLidType);

        this.sliderDriveName = sliderDriveName;
        this.intakeDriveName = intakeDriveName;
        this.intakeLidName = intakeLidName;

        this.sliderDriveIsPrimary = sliderDriveIsPrimary;
        this.intakeDriveIsPrimary = intakeDriveIsPrimary;
        this.intakeLidIsPrimary = intakeLidIsPrimary;

        this.sliderDrivePort = sliderDrivePort;
        this.intakeDrivePort = intakeDrivePort;
        this.intakeLidPort = intakeLidPort;
    }

    public double getSliderDrive() {
        return sliderDrive.getPower();
    }

    public void setSliderDrive(double power) {
        sliderDrive.set(power);
    }

    public double getAngular() {
        return angular.getPosition();
    }

    public double getAngularMin() {
        return angular.getMin();
    }

    public double getAngularMax() {
        return angular.getMax();
    }

    public void setAngularMin(double min) {
        angular.setMin(min);
    }

    public void setAngularMax(double max) {
        angular.setMax(max);
    }

    public void setAngular(double power, double position) {
        angular.setPosition(power, position);
    }

    public double getIntakeDrive() {
        return intakeDrive.getPower();
    }

    public void setIntakeDrive(double power) {
        intakeDrive.set(power);
    }

    public double getIntakeLid() {
        return intakeLid.get();
    }

    public void setIntakeLid(double position) {
        intakeLid.set(position);
    }

    public void setSliderDriveForRotations(double power, double rotations) {
        sliderDrive.driveForRotations(power, rotations);
    }

    public void setIntakeDriveForRotations(double power, double rotations) {
        intakeDrive.driveForRotations(power, rotations);
    }

    public void setSliderDriveForMM(double power, double mm) {
        sliderDrive.set(power, mm);
    }

    public void setIntakeDriveForMM(double power, double mm) {
        intakeDrive.set(power, mm);
    }

    public void freezeSliderDrive() {
        sliderDrive.freezeAtZeroPower();
        sliderDrive.set(0);
    }

    public boolean getSliderDriveFrozen() {
        return sliderDrive.frozenAtZeroPower() && sliderDrive.getPower() == 0;
    }

    public void coastSliderDrive() {
        sliderDrive.coastAtZeroPower();
        sliderDrive.set(0);
    }

    public boolean getSliderDriveCoasting() {
        return sliderDrive.coastingAtZeroPower() && sliderDrive.getPower() == 0;
    }

    public void freezeintakeDrive() {
        intakeDrive.freezeAtZeroPower();
        intakeDrive.set(0);
    }

    public boolean getIntakeDriveFrozen() {
        return intakeDrive.frozenAtZeroPower() && intakeDrive.getPower() == 0;
    }

    public void coastIntakeDrive() {
        intakeDrive.coastAtZeroPower();
        intakeDrive.set(0);
    }

    public boolean getIntakeDriveCoasting() {
        return intakeDrive.coastingAtZeroPower() && intakeDrive.getPower() == 0;
    }

    public void killPositioner() {
        angular.killPositioner();
    }


    @Override
    public ElementWrapper[] getHardwareDeviceElements() {
        ElementWrapper[] angularElements = angular.getHardwareDeviceElements();
        ElementWrapper[] otherElements = new ElementWrapper[] {
                new ElementWrapper(sliderDriveIsPrimary, XMLUtils.generateHardwareElement(
                        sliderDrive.getMotorType(), sliderDriveName, sliderDrivePort
                )),
                new ElementWrapper(intakeDriveIsPrimary, XMLUtils.generateHardwareElement(
                        intakeDrive.getMotorType(), intakeDriveName, intakeDrivePort
                )),
                new ElementWrapper(intakeLidIsPrimary, XMLUtils.generateHardwareElement(
                        intakeLid.getServoType(), intakeLidName, intakeLidPort
                ))
        };
        ElementWrapper[] combinedElements =
                new ElementWrapper[angularElements.length + otherElements.length];
        System.arraycopy(angularElements, 0, combinedElements, 0,
                angularElements.length);
        System.arraycopy(otherElements, 0, combinedElements, angularElements.length,
                otherElements.length);
        return combinedElements;
    }
}


















