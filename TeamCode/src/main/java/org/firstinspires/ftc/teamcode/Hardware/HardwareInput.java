package org.firstinspires.ftc.teamcode.Hardware;

final class HardwareInput {
    public static boolean validate(double input, InputType inputType) {
        if (inputType == InputType.FOR_SERVO) {
            return (0 <= input && input <= 255);
        }

        return (-1 <= input && input <= 1);
    }
}
