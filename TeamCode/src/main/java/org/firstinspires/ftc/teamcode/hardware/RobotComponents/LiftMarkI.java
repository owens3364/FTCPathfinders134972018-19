package org.firstinspires.ftc.teamcode.hardware.RobotComponents;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;

public final class LiftMarkI {
    private final DcMotorWrapper cableDrive;

    public LiftMarkI(HardwareMap map, String cableDriveName) {
        cableDrive = new DcMotorWrapper(map, cableDriveName, DcMotorSimple.Direction.FORWARD,
                Motor.NeveRest60Gearmotor);
        cableDrive.freezeAtZeroPower();
    }

    public void setCableDrive(double power) {
        cableDrive.set(power);
    }

    public double getCableDrive() {
        return cableDrive.getPower();
    }

    public void setCableDriveForRotations(double power, double rotations) {
        cableDrive.driveForRotations(power, rotations);
    }

    public void setCableDriveForMM(double power, int mm) {
        cableDrive.set(power, mm);
    }

    public void freeze() {
        cableDrive.freezeAtZeroPower();
        cableDrive.set(0); //TODO: Measure correct value
    }

    public boolean liftFrozen() {
        return cableDrive.getPower() == 0 && cableDrive.frozenAtZeroPower();
    }

    public void coast() {
        cableDrive.coastAtZeroPower();
        cableDrive.set(0);
    }

    public boolean liftCoasting() {
        return cableDrive.getPower() == 0 && cableDrive.coastingAtZeroPower();
    }
}
