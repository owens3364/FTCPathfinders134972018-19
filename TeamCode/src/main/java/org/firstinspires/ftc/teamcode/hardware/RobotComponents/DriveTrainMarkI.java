package org.firstinspires.ftc.teamcode.hardware.RobotComponents;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;

public final class DriveTrainMarkI {
    private final DcMotorWrapper leftDrive;
    private final DcMotorWrapper rightDrive;

    public DriveTrainMarkI(HardwareMap map, String leftDriveName, String rightDriveName) {
        leftDrive = new DcMotorWrapper(map, leftDriveName, DcMotorSimple.Direction.FORWARD,
                Motor.NeveRest60Gearmotor);
        rightDrive = new DcMotorWrapper(map, rightDriveName, DcMotorSimple.Direction.REVERSE,
                Motor.NeveRest60Gearmotor);
    }

    public void setLeft(double power) {
        leftDrive.set(power);
    }

    public void setRight(double power) {
        rightDrive.set(power);
    }

    public void setLeftForRotations(double power, double rotations) {
        leftDrive.driveForRotations(power, rotations);
    }

    public void setRightForRotations(double power, double rotations) {
        rightDrive.driveForRotations(power, rotations);
    }

    public void setLeftForMM(double power, int mm) {
        leftDrive.set(power, mm);
    }

    public void setRightForMM(double power, int mm) {
        rightDrive.set(mm);
    }
}
