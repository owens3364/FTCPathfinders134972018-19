package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

final class LiftMarkI {
    private final DcMotorWrapper cableDrive;

    LiftMarkI(HardwareMap map, String cableDriveName) {
        cableDrive = new DcMotorWrapper(map, cableDriveName, DcMotorSimple.Direction.FORWARD,
                MotorType.NEVEREST_60);
        cableDrive.freezeAtZeroPower();
    }

    void setCableDrive(double power) {
        cableDrive.set(power);
    }

    double getCableDrive() {
        return cableDrive.getPower();
    }

    void setCableDriveForRotations(double power, double rotations) {
        cableDrive.driveForRotations(power, rotations);
    }

    void setCableDriveForMM(double power, int mm) {
        cableDrive.set(power, mm);
    }

    void freeze() {
        cableDrive.freezeAtZeroPower();
        cableDrive.set(0); //TODO: Measue correct value
    }

    boolean liftFrozen() {
        return cableDrive.getPower() == 0 && cableDrive.frozenAtZeroPower();
    }

    void coast() {
        cableDrive.coastAtZeroPower();
        cableDrive.set(0);
    }

    boolean liftCoasting() {
        return cableDrive.getPower() == 0 && cableDrive.coastingAtZeroPower();
    }
}
