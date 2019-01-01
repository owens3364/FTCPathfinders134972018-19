package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

final class MechanumDriveTrainMarkI {
    private final DcMotorWrapper frontLeftDrive;
    private final DcMotorWrapper frontRightDrive;
    private final DcMotorWrapper rearLeftDrive;
    private final DcMotorWrapper rearRightDrive;

    MechanumDriveTrainMarkI(HardwareMap map,
                            String frontLeftDriveName,
                            String frontRightDriveName,
                            String rearLeftDriveName,
                            String rearRightDriveName) {
        frontLeftDrive = new DcMotorWrapper(map, frontLeftDriveName, FORWARD, MotorType.NEVEREST_60);
        frontRightDrive = new DcMotorWrapper(map, frontRightDriveName, REVERSE, MotorType.NEVEREST_60);
        rearLeftDrive = new DcMotorWrapper(map, rearLeftDriveName, FORWARD, MotorType.NEVEREST_60);
        rearRightDrive = new DcMotorWrapper(map, rearRightDriveName, REVERSE, MotorType.NEVEREST_60);
    }

    void driveMotorsBySticks(double leftRight, double forwardBackward, double turn) {
        double targetPoint = Math.hypot(-leftRight, forwardBackward);
        double targetAngle = Math.atan2(forwardBackward, -leftRight) - Math.PI / 4;
        setFrontLeft(targetPoint * Math.cos(targetAngle) - turn);
        setFrontRight(targetPoint * Math.sin(targetAngle) + turn);
        setRearLeft(targetPoint * Math.sin(targetAngle) - turn);
        setRearRight(targetPoint *Math.cos(targetAngle) + turn);
    }

    double getFrontLeft() {
        return frontLeftDrive.getPower();
    }

    void setFrontLeft(double power) {
        frontLeftDrive.set(power);
    }

    double getFrontRight() {
        return frontRightDrive.getPower();
    }

    void setFrontRight(double power) {
        frontRightDrive.set(power);
    }

    double getRearLeft() {
        return rearLeftDrive.getPower();
    }

    void setRearLeft(double power) {
        rearLeftDrive.set(power);
    }

    double getRearRight() {
        return rearRightDrive.getPower();
    }

    void setRearRight(double power) {
        rearRightDrive.set(power);
    }

    void setFrontLeftForRotations(double power, double rotations) {
        frontLeftDrive.driveForRotations(power, rotations);
    }

    void setFrontRightForRotations(double power, double rotations) {
        frontRightDrive.driveForRotations(power, rotations);
    }

    void setRearLeftForRotations(double power, double rotations) {
        rearLeftDrive.driveForRotations(power, rotations);
    }

    void setRearRightForRotations(double power, double rotations) {
        rearRightDrive.driveForRotations(power, rotations);
    }

    void setFrontLeftForMM(double power, int mm) {
        frontLeftDrive.set(power, mm);
    }

    void setFrontRightForMM(double power, int mm) {
        frontRightDrive.set(power, mm);
    }

    void setRearLeftForMM(double power, int mm) {
        rearLeftDrive.set(power, mm);
    }

    void setRearRightForMM(double power, int mm) {
        rearRightDrive.set(power, mm);
    }
}
