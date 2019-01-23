package org.firstinspires.ftc.teamcode.hardware.RobotComponents;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

public final class MechanumDriveTrainMarkI {
    private final DcMotorWrapper frontLeftDrive;
    private final DcMotorWrapper frontRightDrive;
    private final DcMotorWrapper rearLeftDrive;
    private final DcMotorWrapper rearRightDrive;

    public MechanumDriveTrainMarkI(HardwareMap map,
                            String frontLeftDriveName,
                            String frontRightDriveName,
                            String rearLeftDriveName,
                            String rearRightDriveName) {
        frontLeftDrive = new DcMotorWrapper(map, frontLeftDriveName, FORWARD, Motor.NeveRest60Gearmotor);
        frontRightDrive = new DcMotorWrapper(map, frontRightDriveName, REVERSE, Motor.NeveRest60Gearmotor);
        rearLeftDrive = new DcMotorWrapper(map, rearLeftDriveName, FORWARD, Motor.NeveRest60Gearmotor);
        rearRightDrive = new DcMotorWrapper(map, rearRightDriveName, REVERSE, Motor.NeveRest60Gearmotor);
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
}
