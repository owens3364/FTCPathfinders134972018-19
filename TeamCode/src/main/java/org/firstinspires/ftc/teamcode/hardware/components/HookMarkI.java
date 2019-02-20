package org.firstinspires.ftc.teamcode.hardware.components;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardware.components.regulators.Component;
import org.firstinspires.ftc.teamcode.hardware.configuration.devices.Motor;
import org.firstinspires.ftc.teamcode.xmlIO.XMLUtils;

public final class HookMarkI implements Component {
    private final DcMotorWrapper hookDrive;
    private final String hookDriveName;
    private final boolean hookDriveIsPrimary;
    private final String hookDrivePort;

    private final ServoWrapper hook;
    private final String hookName;
    private final boolean hookIsPrimary;
    private final String hookPort;

    public HookMarkI(HardwareMap map, String hookDriveName, String hookName,
                     boolean hookDriveIsPrimary, boolean hookIsPrimary, String hookDrivePort,
                     String hookPort, Motor hookDriveType, org.firstinspires.ftc.teamcode.hardware
                             .configuration.devices.Servo hookType) {
        hookDrive = new DcMotorWrapper(map, hookDriveName, DcMotorSimple.Direction.FORWARD,
                hookDriveType, WheelStats.WHEEL_DIAMETER_FOUR);
        hookDrive.freezeAtZeroPower();
        hook = new ServoWrapper(map, hookName, Servo.Direction.FORWARD, hookType);

        this.hookDriveName = hookDriveName;
        this.hookName = hookName;

        this.hookDriveIsPrimary = hookDriveIsPrimary;
        this.hookIsPrimary = hookIsPrimary;

        this.hookDrivePort = hookDrivePort;
        this.hookPort = hookPort;
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
        hook.set(ServoWrapper.MAX_POSITION / 2);
    }

    public void retract () {
        hook.set(ServoWrapper.MIN_POSITION);
    }

    public void freeze() {
        hookDrive.freezeAtZeroPower();
        hookDrive.set(0);
    }

    public void coast() {
        hookDrive.coastAtZeroPower();
        hookDrive.set(0);
    }

    @Override
    public ElementWrapper[] getHardwareDeviceElements() {
        return new ElementWrapper[] {
                new ElementWrapper(hookDriveIsPrimary, XMLUtils.generateHardwareElement(
                        hookDrive.getMotorType(), hookDriveName, hookPort
                )),
                new ElementWrapper(hookIsPrimary, XMLUtils.generateHardwareElement(
                        hook.getServoType(), hookName, hookPort
                ))
        };
    }
}
