package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

final class HookMarkI {
    private final DcMotorWrapper hookDrive;
    private final Servo hookServo;

    HookMarkI(HardwareMap map, String hookDriveName, String hookServoName) {
        hookDrive = new DcMotorWrapper(map, hookDriveName, DcMotorSimple.Direction.FORWARD,
                MotorType.NEVEREST_60);
        hookDrive.freezeAtZeroPower();
        hookServo = map.get(Servo.class, hookServoName);
    }

    void setDrive(double power) {
        hookDrive.set(power);
    }

    void setDriveForRotations(double power, double rotations) {
        hookDrive.driveForRotations(power, rotations);
    }

    void setDriveForMM(double power, int mm) {
        hookDrive.set(power, mm);
    }

    void activate() {
        hookServo.setPosition(Servo.MAX_POSITION / 2);
    }

    void retract () {
        hookServo.setPosition(Servo.MIN_POSITION);
    }

    void freeze() {
        hookDrive.freezeAtZeroPower();
        hookDrive.set(0);
    }

    void coast() {
        hookDrive.coastAtZeroPower();
        hookDrive.set(0);
    }
}
