package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;

final class LimitedDriveMarkI {
    private final DcMotorWrapper drive;
    //Limit switches
    private final DigitalChannel driveMin;
    private final DigitalChannel driveMax;

    LimitedDriveMarkI(HardwareMap map, String driveName, String minLimitName, String maxLimitName) {
        drive = new DcMotorWrapper(map, driveName, FORWARD, null);
        driveMin = map.get(DigitalChannel.class, minLimitName);
        driveMin.setMode(DigitalChannel.Mode.INPUT);
        driveMax = map.get(DigitalChannel.class, maxLimitName);
        driveMax.setMode(DigitalChannel.Mode.INPUT);
    }

    //Safely sets the power of the LimitedDriveMarkI, ensures it does not go past
    // minimum or maximum limits
    void set(double power) {
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
}
