package org.firstinspires.ftc.teamcode.hardware.components;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.componentinterfaces.Component;
import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;
import org.firstinspires.ftc.teamcode.xmlio.XMLUtils;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

public final class MechanumDriveTrainMarkI implements Component {
    private final DcMotorWrapper frontLeftDrive;
    private final String frontLeftDriveName;
    private final boolean frontLeftDriveIsPrimary;
    private final String frontLeftDrivePort;

    private final DcMotorWrapper frontRightDrive;
    private final String frontRightDriveName;
    private final boolean frontRightDriveIsPrimary;
    private final String frontRightDrivePort;

    private final DcMotorWrapper rearLeftDrive;
    private final String rearLeftDriveName;
    private final boolean rearLeftDriveIsPrimary;
    private final String rearLeftDrivePort;

    private final DcMotorWrapper rearRightDrive;
    private final String rearRightDriveName;
    private final boolean rearRightDriveIsPrimary;
    private final String rearRightDrivePort;

    public MechanumDriveTrainMarkI(HardwareMap map,
                                   String frontLeftDriveName, String frontRightDriveName,
                                   String rearLeftDriveName, String rearRightDriveName,
                                   boolean frontLeftDriveIsPrimary,
                                   boolean frontRightDriveIsPrimary,
                                   boolean rearLeftDriveIsPrimary,
                                   boolean rearRightDriveIsPrimary,
                                   String frontLeftDrivePort, String frontRightDrivePort,
                                   String rearLeftDrivePort, String rearRightDrivePort,
                                   Motor frontLeftDriveType, Motor frontRightDriveType,
                                   Motor rearLeftDriveType, Motor rearRightDriveType) {
        frontLeftDrive = new DcMotorWrapper(map, frontLeftDriveName, FORWARD, frontLeftDriveType,
                WheelStats.WHEEL_DIAMETER_FOUR);
        frontRightDrive = new DcMotorWrapper(map, frontRightDriveName, REVERSE, frontRightDriveType,
                WheelStats.WHEEL_DIAMETER_FOUR);
        rearLeftDrive = new DcMotorWrapper(map, rearLeftDriveName, FORWARD, rearLeftDriveType,
                WheelStats.WHEEL_DIAMETER_FOUR);
        rearRightDrive = new DcMotorWrapper(map, rearRightDriveName, REVERSE, rearRightDriveType,
                WheelStats.WHEEL_DIAMETER_FOUR);

        this.frontLeftDriveName = frontLeftDriveName;
        this.frontRightDriveName = frontRightDriveName;
        this.rearLeftDriveName = rearLeftDriveName;
        this.rearRightDriveName = rearRightDriveName;

        this.frontLeftDriveIsPrimary = frontLeftDriveIsPrimary;
        this.frontRightDriveIsPrimary = frontRightDriveIsPrimary;
        this.rearLeftDriveIsPrimary = rearLeftDriveIsPrimary;
        this.rearRightDriveIsPrimary = rearRightDriveIsPrimary;

        this.frontLeftDrivePort = frontLeftDrivePort;
        this.frontRightDrivePort = frontRightDrivePort;
        this.rearLeftDrivePort = rearLeftDrivePort;
        this.rearRightDrivePort = rearRightDrivePort;
    }

    public void driveMotorsBySticks(double leftRight, double forwardBackward, double turn) {
        double targetPoint = Math.hypot(-leftRight, forwardBackward);
        double targetAngle = Math.atan2(forwardBackward, -leftRight) - Math.PI / 4;
        setFrontLeft(targetPoint * Math.cos(targetAngle) - turn);
        setFrontRight(targetPoint * Math.sin(targetAngle) + turn);
        setRearLeft(targetPoint * Math.sin(targetAngle) - turn);
        setRearRight(targetPoint *Math.cos(targetAngle) + turn);
    }

    public double getFrontLeft() {
        return frontLeftDrive.getPower();
    }

    public void setFrontLeft(double power) {
        frontLeftDrive.set(power);
    }

    public double getFrontRight() {
        return frontRightDrive.getPower();
    }

    public void setFrontRight(double power) {
        frontRightDrive.set(power);
    }

    public double getRearLeft() {
        return rearLeftDrive.getPower();
    }

    public void setRearLeft(double power) {
        rearLeftDrive.set(power);
    }

    public double getRearRight() {
        return rearRightDrive.getPower();
    }

    public void setRearRight(double power) {
        rearRightDrive.set(power);
    }

    public void setFrontLeftForRotations(double power, double rotations) {
        frontLeftDrive.driveForRotations(power, rotations);
    }

    public void setFrontRightForRotations(double power, double rotations) {
        frontRightDrive.driveForRotations(power, rotations);
    }

    public void setRearLeftForRotations(double power, double rotations) {
        rearLeftDrive.driveForRotations(power, rotations);
    }

    public void setRearRightForRotations(double power, double rotations) {
        rearRightDrive.driveForRotations(power, rotations);
    }

    public void setFrontLeftForMM(double power, int mm) {
        frontLeftDrive.set(power, mm);
    }

    public void setFrontRightForMM(double power, int mm) {
        frontRightDrive.set(power, mm);
    }

    public void setRearLeftForMM(double power, int mm) {
        rearLeftDrive.set(power, mm);
    }

    public void setRearRightForMM(double power, int mm) {
        rearRightDrive.set(power, mm);
    }

    @Override
    public ElementWrapper[] getHardwareDeviceElements() {
        return new ElementWrapper[] {
                new ElementWrapper(frontLeftDriveIsPrimary, XMLUtils.generateHardwareElement(
                        frontLeftDrive.getMotorType(), frontLeftDriveName, frontLeftDrivePort
                )),
                new ElementWrapper(frontRightDriveIsPrimary, XMLUtils.generateHardwareElement(
                        frontRightDrive.getMotorType(), frontRightDriveName, frontRightDrivePort
                )),
                new ElementWrapper(rearLeftDriveIsPrimary, XMLUtils.generateHardwareElement(
                        rearLeftDrive.getMotorType(), rearLeftDriveName, rearLeftDrivePort
                )),
                new ElementWrapper(rearRightDriveIsPrimary, XMLUtils.generateHardwareElement(
                        rearRightDrive.getMotorType(), rearRightDriveName, rearRightDrivePort
                ))
        };
    }
}
