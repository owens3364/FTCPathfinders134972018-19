package org.firstinspires.ftc.teamcode.hardware.RobotComponents;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;

public final class HookMarkI {
    private final DcMotorWrapper hookDrive;
    private final Servo hookServo;

    public HookMarkI(HardwareMap map, String hookDriveName, String hookServoName) {
        hookDrive = new DcMotorWrapper(map, hookDriveName, DcMotorSimple.Direction.FORWARD,
                Motor.NeveRest60Gearmotor);
        hookDrive.freezeAtZeroPower();
        hookServo = map.get(Servo.class, hookServoName);
    }

    public void setDrive(double power) {
        hookDrive.set(power);
    }

    public void setDriveForRotations(double power, double rotations) {
        hookDrive.driveForRotations(power, rotations);
    }

    public void setDriveForMM(double power, int mm) {
        hookDrive.set(power, mm);
    }

    public void activate() {
        hookServo.setPosition(Servo.MAX_POSITION / 2);
    }

    public void retract () {
        hookServo.setPosition(Servo.MIN_POSITION);
    }

    public void freeze() {
        hookDrive.freezeAtZeroPower();
        hookDrive.set(0);
    }

    public void coast() {
        hookDrive.coastAtZeroPower();
        hookDrive.set(0);
    }
}
