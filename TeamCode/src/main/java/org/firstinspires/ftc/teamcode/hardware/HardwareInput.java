package org.firstinspires.ftc.teamcode.hardware;

final class HardwareInput {
    static boolean validate(double input, InputType inputType) {
        if (inputType == InputType.FOR_SERVO) {
            return (0 <= input && input <= 255);
        }
        return (-1 <= input && input <= 1);
    }
}
