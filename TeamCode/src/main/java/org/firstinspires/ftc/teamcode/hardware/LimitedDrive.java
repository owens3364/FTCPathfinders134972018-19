package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

final class LimitedDrive {
    private final DcMotor drive;
    //Limit switches
    private final DigitalChannel driveMin;
    private final DigitalChannel driveMax;

    LimitedDrive(HardwareMap map, String driveName, String minLimitName, String maxLimitName) {
        drive = map.get(DcMotor.class, driveName);
        drive.setDirection(DcMotorSimple.Direction.FORWARD);
        driveMin = map.get(DigitalChannel.class, minLimitName);
        driveMin.setMode(DigitalChannel.Mode.INPUT);
        driveMax = map.get(DigitalChannel.class, maxLimitName);
        driveMax.setMode(DigitalChannel.Mode.INPUT);
    }

    //Safely sets the power of the LimitedDrive, ensures it does not go past minimum or maximum limits
    void set(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            //Ensure the actuator is not at its limit
            if (power == 0) {
                drive.setPower(0);
                return;
            }

            if (power > 0) {
                if (driveMax.getState()) {
                    drive.setPower(power);
                    //Monitor the actuator's position and ensure it isn't driven past the limit
                    new Thread(() -> {
                        while (drive.getPower() != 0) {
                            //This assumes that the channel is pulled high when not pressed
                            //Remove the ! if that's not true
                            if (!driveMax.getState()) {
                                drive.setPower(0);
                            }
                        }
                    }).start();
                }
                return;
            }

            if (power < 0) {
                if (driveMin.getState()) {
                    drive.setPower(power);
                    //Monitor the actuator's position and ensure it isn't driven past the limit
                    new Thread(() -> {
                        while (drive.getPower() != 0) {
                            //This assumes that the channel is pulled high when not pressed
                            //Remove the ! if that's not true
                            if (!driveMin.getState()) {
                                drive.setPower(0);
                            }
                        }
                    }).start();
                }
            }
        }
    }
}
