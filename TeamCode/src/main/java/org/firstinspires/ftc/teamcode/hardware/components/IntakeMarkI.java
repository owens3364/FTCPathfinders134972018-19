package org.firstinspires.ftc.teamcode.hardware.components;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.componentinterfaces.Component;
import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;
import org.firstinspires.ftc.teamcode.xmlio.XMLUtils;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;

public final class IntakeMarkI implements Component {
    private final DcMotorWrapper intakeDrive;
    private final String intakeDriveName;
    private final boolean intakeDriveIsPrimary;
    private final String intakeDrivePort;

    public IntakeMarkI(HardwareMap map, String intakeDriveName, boolean intakeDriveIsPrimary,
                       String intakeDrivePort, Motor intakeDriveType) {
        intakeDrive = new DcMotorWrapper(map, intakeDriveName, FORWARD, intakeDriveType,
                WheelStats.WHEEL_DIAMETER_FOUR);

        this.intakeDriveName = intakeDriveName;
        this.intakeDriveIsPrimary = intakeDriveIsPrimary;
        this.intakeDrivePort = intakeDrivePort;
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

    @Override
    public ElementWrapper[] getHardwareDeviceElements() {
        return new ElementWrapper[] {
                new ElementWrapper(intakeDriveIsPrimary, XMLUtils.generateHardwareElement(
                        intakeDrive.getMotorType(), intakeDriveName, intakeDrivePort
                ))
        };
    }
}
