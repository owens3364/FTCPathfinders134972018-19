package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;

final class IntakeMarkI {
    private final DcMotorWrapper intakeDrive;

    IntakeMarkI(HardwareMap map, String intakeDriveName) {
        intakeDrive = new DcMotorWrapper(map, intakeDriveName, FORWARD, MotorType.TETRIX_MAX);
    }

    void set(double power) {
        intakeDrive.set(power);
    }

    void setForRotations(double power, double rotations) {
        intakeDrive.driveForRotations(power, rotations);
    }

    void setForMM(double power, int mm) {
        intakeDrive.set(power, mm);
    }
}
