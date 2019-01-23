package org.firstinspires.ftc.teamcode.hardware.RobotComponents;

final class HardwareInput {

    private static final double[] SERVO_RANGE = {
            0.0,
            1.0
    };

    private static final double[] MOTOR_RANGE = {
            -1.0,
            1.0
    };

    static boolean validate(double input, InputType inputType) {
        if (inputType == InputType.FOR_SERVO) {
            return (SERVO_RANGE[0] <= input && input <= SERVO_RANGE[1]);
        }

        return (MOTOR_RANGE[0] <= input && input <= MOTOR_RANGE[1]);
    }
}
