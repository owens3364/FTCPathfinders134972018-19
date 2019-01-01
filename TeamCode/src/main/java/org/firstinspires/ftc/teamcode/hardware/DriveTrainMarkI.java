package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

final class DriveTrainMarkI {
    private final DcMotorWrapper leftDrive;
    private final DcMotorWrapper rightDrive;

    DriveTrainMarkI(HardwareMap map, String leftDriveName, String rightDriveName) {
        leftDrive = new DcMotorWrapper(map, leftDriveName, DcMotorSimple.Direction.FORWARD,
                MotorType.NEVEREST_60);
        rightDrive = new DcMotorWrapper(map, rightDriveName, DcMotorSimple.Direction.REVERSE,
                MotorType.NEVEREST_60);
    }

    void setLeft(double power) {
        leftDrive.set(power);
    }

    void setRight(double power) {
        rightDrive.set(power);
    }

    void setLeftForRotations(double power, double rotations) {
        leftDrive.driveForRotations(power, rotations);
    }

    void setRightForRotations(double power, double rotations) {
        rightDrive.driveForRotations(power, rotations);
    }

    void setLeftForMM(double power, int mm) {
        leftDrive.set(power, mm);
    }

    void setRightForMM(double power, int mm) {
        rightDrive.set(mm);
    }
}
