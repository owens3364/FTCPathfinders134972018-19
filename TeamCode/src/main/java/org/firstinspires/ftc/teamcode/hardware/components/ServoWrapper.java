package org.firstinspires.ftc.teamcode.hardware.components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

final class ServoWrapper {

    private final Servo servo;
    private final
    org.firstinspires.ftc.teamcode.hardware.configuration.devices.Servo servoType;

    public static final double MIN_POSITION = Servo.MIN_POSITION;
    public static final double MAX_POSITION = Servo.MAX_POSITION;

    ServoWrapper(HardwareMap map, String servoName, Servo.Direction servoRotatingDirection,
    org.firstinspires.ftc.teamcode.hardware.configuration.devices.Servo servoType) {
        this.servo = map.get(Servo.class, servoName);
        servo.setDirection(servoRotatingDirection);
        this.servoType = servoType;

    }

    void set(double position) {
        if (HardwareInput.validate(position, InputType.FOR_SERVO)) {
            servo.setPosition(position);
        }
    }

    double get() {
        return servo.getPosition();
    }

    org.firstinspires.ftc.teamcode.hardware.configuration.devices.Servo
    getServoType() {
        return servoType;
    }

}
