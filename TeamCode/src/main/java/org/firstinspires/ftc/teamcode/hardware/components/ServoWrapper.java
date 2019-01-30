package org.firstinspires.ftc.teamcode.hardware.components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

final class ServoWrapper {

    private final Servo servo;
    private final
    org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Servo servoType;
    private final double mmPerDriveRotation;

    public static final double MIN_POSITION = Servo.MIN_POSITION;
    public static final double MAX_POSITION = Servo.MAX_POSITION;

    ServoWrapper(HardwareMap map, String servoName, Servo.Direction servoRotatingDirection,
    org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Servo servoType,
                 double wheelDiameter) {
        this.servo = map.get(Servo.class, servoName);
        servo.setDirection(servoRotatingDirection);
        this.servoType = servoType;
        this.mmPerDriveRotation = WheelStats.mmPerDriveRotationFromInDiameter(wheelDiameter);

    }

    void set(double position) {
        if (HardwareInput.validate(position, InputType.FOR_SERVO)) {
            servo.setPosition(position);
        }
    }

    double get() {
        return servo.getPosition();
    }

    org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Servo
    getServoType() {
        return servoType;
    }

}
