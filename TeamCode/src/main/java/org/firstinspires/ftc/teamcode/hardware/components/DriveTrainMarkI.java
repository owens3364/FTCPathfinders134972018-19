package org.firstinspires.ftc.teamcode.hardware.components;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.componentinterfaces.Component;
import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;
import org.firstinspires.ftc.teamcode.xmlio.XMLUtils;

public final class DriveTrainMarkI implements Component {
    private final DcMotorWrapper leftDrive;
    private final String leftDriveName;
    private final boolean leftDriveIsPrimary;
    private final String leftDrivePort;

    private final DcMotorWrapper rightDrive;
    private final String rightDriveName;
    private final boolean rightDriveIsPrimary;
    private final String rightDrivePort;

    public DriveTrainMarkI(HardwareMap map, String leftDriveName, String rightDriveName,
                           boolean leftDriveIsPrimary, boolean rightDriveIsPrimary,
                           String leftDrivePort, String rightDrivePort, Motor leftDriveType,
                           Motor rightDriveType) {
        leftDrive = new DcMotorWrapper(map, leftDriveName, DcMotorSimple.Direction.FORWARD,
                leftDriveType, WheelStats.WHEEL_DIAMETER_FOUR);
        rightDrive = new DcMotorWrapper(map, rightDriveName, DcMotorSimple.Direction.REVERSE,
                rightDriveType, WheelStats.WHEEL_DIAMETER_FOUR);

        this.leftDriveName = leftDriveName;
        this.rightDriveName = rightDriveName;

        this.leftDriveIsPrimary = leftDriveIsPrimary;
        this.rightDriveIsPrimary = rightDriveIsPrimary;

        this.leftDrivePort = leftDrivePort;
        this.rightDrivePort = rightDrivePort;
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

    @Override
    public ElementWrapper[] getHardwareDeviceElements() {
        return new ElementWrapper[] {
                new ElementWrapper(leftDriveIsPrimary, XMLUtils.generateHardwareElement(
                        leftDrive.getMotorType(), leftDriveName, leftDrivePort
                )),
                new ElementWrapper(rightDriveIsPrimary, XMLUtils.generateHardwareElement(
                        rightDrive.getMotorType(), rightDriveName, rightDrivePort
                ))
        };
    }
}
