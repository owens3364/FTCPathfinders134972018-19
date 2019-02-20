package org.firstinspires.ftc.teamcode.hardware.components;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.components.regulators.Component;
import org.firstinspires.ftc.teamcode.hardware.configuration.devices.Motor;
import org.firstinspires.ftc.teamcode.xmlIO.XMLUtils;

public final class LiftMarkI implements Component {
    private final DcMotorWrapper cableDrive;
    private final String cableDriveName;
    private final boolean cableDriveIsPrimary;
    private final String cableDrivePort;

    public LiftMarkI(HardwareMap map, String cableDriveName, boolean cableDriveIsPrimary,
                     String cableDrivePort, Motor cableDriveType) {
        cableDrive = new DcMotorWrapper(map, cableDriveName, DcMotorSimple.Direction.FORWARD,
                cableDriveType, WheelStats.WHEEL_DIAMETER_FOUR);
        cableDrive.freezeAtZeroPower();

        this.cableDriveName = cableDriveName;
        this.cableDriveIsPrimary = cableDriveIsPrimary;
        this.cableDrivePort = cableDrivePort;
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

    @Override
    public ElementWrapper[] getHardwareDeviceElements() {
        return new ElementWrapper[] {
                new ElementWrapper(cableDriveIsPrimary, XMLUtils.generateHardwareElement(
                        cableDrive.getMotorType(), cableDriveName, cableDrivePort
                ))
        };
    }
}
