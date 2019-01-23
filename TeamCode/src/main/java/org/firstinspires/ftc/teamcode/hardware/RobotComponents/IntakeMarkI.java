package org.firstinspires.ftc.teamcode.hardware.RobotComponents;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;

public final class IntakeMarkI {
    private final DcMotorWrapper intakeDrive;

    public IntakeMarkI(HardwareMap map, String intakeDriveName) {
        intakeDrive = new DcMotorWrapper(map, intakeDriveName, FORWARD, Motor.TetrixMotor);
    }

    public void set(double power) {
        intakeDrive.set(power);
    }

    public void setForRotations(double power, double rotations) {
        intakeDrive.driveForRotations(power, rotations);
    }

    public void setForMM(double power, int mm) {
        intakeDrive.set(power, mm);
    }
}
