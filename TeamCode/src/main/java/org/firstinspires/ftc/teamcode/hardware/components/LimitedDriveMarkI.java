package org.firstinspires.ftc.teamcode.hardware.components;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.componentinterfaces.Component;
import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Device;
import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;
import org.firstinspires.ftc.teamcode.xmlio.XMLUtils;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;

public final class LimitedDriveMarkI implements Component {
    private final DcMotorWrapper drive;
    private final String driveName;
    private final boolean driveIsPrimary;
    private final String drivePort;

    //Limit switches
    private final DigitalChannel driveMin;
    private final Device driveMinType;
    private final String driveMinName;
    private final boolean driveMinIsPrimary;
    private final String driveMinPort;
    private final String driveMinBus;

    private final DigitalChannel driveMax;
    private final Device driveMaxType;
    private final String driveMaxName;
    private final boolean driveMaxIsPrimary;
    private final String driveMaxPort;
    private final String driveMaxBus;

    public LimitedDriveMarkI(HardwareMap map, String driveName, String driveMinName,
                             String driveMaxName, boolean driveIsPrimary, boolean driveMinIsPrimary,
                             boolean driveMaxIsPrimary, String drivePort, String driveMinPort,
                             String driveMaxPort, String driveMinBus, String driveMaxBus,
                             Motor driveType, Device driveMinType,
                             Device driveMaxType) {
        drive = new DcMotorWrapper(map, driveName, FORWARD, driveType,
                WheelStats.WHEEL_DIAMETER_FOUR);
        driveMin = map.get(DigitalChannel.class, driveMinName);
        driveMin.setMode(DigitalChannel.Mode.INPUT);
        driveMax = map.get(DigitalChannel.class, driveMaxName);
        driveMax.setMode(DigitalChannel.Mode.INPUT);

        this.driveName = driveName;
        this.driveMinName = driveMinName;
        this.driveMaxName = driveMaxName;

        this.driveIsPrimary = driveIsPrimary;
        this.driveMinIsPrimary = driveMinIsPrimary;
        this.driveMaxIsPrimary = driveMaxIsPrimary;

        this.drivePort = drivePort;
        this.driveMinPort = driveMinPort;
        this.driveMaxPort = driveMaxPort;

        this.driveMinType = driveMinType;
        this.driveMaxType = driveMaxType;

        this.driveMinBus = driveMinBus;
        this.driveMaxBus = driveMaxBus;
    }

    //Safely sets the power of the LimitedDriveMarkI, ensures it does not go past
    // minimum or maximum limits
    public void set(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            //Ensure the actuator is not at its limit
            if (power == 0) {
                drive.set(0);
                return;
            }

            if (power > 0) {
                if (driveMax.getState()) {
                    drive.set(power);
                    //Monitor the actuator's position and ensure it isn't driven past the limit
                    new Thread(() -> {
                        while (drive.getPower() != 0) {
                            //This assumes that the channel is pulled high when not pressed
                            //Remove the ! if that's not true
                            if (!driveMax.getState()) {
                                drive.set(0);
                            }
                        }
                    }).start();
                }
                return;
            }

            if (power < 0) {
                if (driveMin.getState()) {
                    drive.set(power);
                    //Monitor the actuator's position and ensure it isn't driven past the limit
                    new Thread(() -> {
                        while (drive.getPower() != 0) {
                            //This assumes that the channel is pulled high when not pressed
                            //Remove the ! if that's not true
                            if (!driveMin.getState()) {
                                drive.set(0);
                            }
                        }
                    }).start();
                }
            }
        }
    }

    @Override
    public ElementWrapper[] getHardwareDeviceElements() {
        return new ElementWrapper[] {
                new ElementWrapper(driveIsPrimary, XMLUtils.generateHardwareElement(
                        drive.getMotorType(), driveName, drivePort)),
                new ElementWrapper(driveMinIsPrimary, XMLUtils.generateHardwareElement(
                        driveMinType, driveMinName, driveMinPort, driveMinBus)),
                new ElementWrapper(driveMaxIsPrimary, XMLUtils.generateHardwareElement(
                        driveMaxType, driveMaxName, driveMaxPort, driveMaxBus
                ))
        };
    }
}
