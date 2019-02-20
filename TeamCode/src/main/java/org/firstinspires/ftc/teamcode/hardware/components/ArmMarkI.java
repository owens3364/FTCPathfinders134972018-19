package org.firstinspires.ftc.teamcode.hardware.components;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo.Direction;

import org.firstinspires.ftc.teamcode.hardware.components.regulators.Component;
import org.firstinspires.ftc.teamcode.hardware.configuration.devices.Motor;
import org.firstinspires.ftc.teamcode.xmlIO.XMLUtils;

public final class ArmMarkI implements Component {
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

    private final ServoWrapper lid;
    private final String lidName;
    private final boolean lidIsPrimary;
    private final String lidPort;

    public ArmMarkI(HardwareMap map, String sliderDriveName, String armAngularDriveName,
                    String intakeAngularName, String lidName, boolean sliderDriveIsPrimary,
                    boolean armAngularDriveIsPrimary, boolean intakeAngularIsPrimay,
                    boolean lidIsPrimary, String sliderDrivePort,
                    String armAngularDrivePort, String intakeAngularPort,
                    String lidPort, Motor sliderDriveType, Motor armAngularDriveType,
                    org.firstinspires.ftc.teamcode.hardware.configuration
                            .devices.Servo intakeAngularType, org.firstinspires
                    .ftc.teamcode.hardware.configuration.devices.Servo
                    lidType) {
        sliderDrive = new DcMotorWrapper(map, sliderDriveName, DcMotorSimple.Direction.FORWARD,
                sliderDriveType, WheelStats.WHEEL_DIAMETER_FOUR);
        sliderDrive.freezeAtZeroPower();
        armAngularDrive = new DcMotorWrapper(map, armAngularDriveName,
                DcMotorSimple.Direction.FORWARD, armAngularDriveType,
                WheelStats.WHEEL_DIAMETER_FOUR);
        armAngularDrive.freezeAtZeroPower();
        intakeAngular = new ServoWrapper(map, intakeAngularName, Direction.FORWARD,
                intakeAngularType);
        lid = new ServoWrapper(map, lidName, Direction.FORWARD, lidType);

        this.sliderDriveName = sliderDriveName;
        this.armAngularDriveName = armAngularDriveName;
        this.intakeAngularName = intakeAngularName;
        this.lidName = lidName;

        this.sliderDriveIsPrimary = sliderDriveIsPrimary;
        this.armAngularDriveIsPrimary = armAngularDriveIsPrimary;
        this.intakeAngularIsPrimary =  intakeAngularIsPrimay;
        this.lidIsPrimary = lidIsPrimary;

        this.sliderDrivePort = sliderDrivePort;
        this.armAngularDrivePort = armAngularDrivePort;
        this.intakeAngularPort = intakeAngularPort;
        this.lidPort = lidPort;
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
        intakeAngular.set(angle);
    }

    public void openLid() {
        lid.set(ServoWrapper.MAX_POSITION);
    }

    public void closeLid() {
        lid.set(ServoWrapper.MIN_POSITION);
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

    @Override
    public ElementWrapper[] getHardwareDeviceElements() {
        return new ElementWrapper[] {
                new ElementWrapper(sliderDriveIsPrimary,
                        XMLUtils.generateHardwareElement(sliderDrive.getMotorType(),
                                sliderDriveName, sliderDrivePort)),
                new ElementWrapper(armAngularDriveIsPrimary,
                        XMLUtils.generateHardwareElement(armAngularDrive.getMotorType(),
                                armAngularDriveName, armAngularDrivePort)),
                new ElementWrapper(intakeAngularIsPrimary,
                        XMLUtils.generateHardwareElement(intakeAngular.getServoType(),
                                intakeAngularName, intakeAngularPort)),
                new ElementWrapper(lidIsPrimary,
                        XMLUtils.generateHardwareElement(lid.getServoType(), lidName, lidPort))
        };
    }
}
