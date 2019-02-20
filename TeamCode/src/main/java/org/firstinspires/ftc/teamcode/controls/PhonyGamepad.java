package org.firstinspires.ftc.teamcode.controls;

import com.qualcomm.robotcore.hardware.Gamepad;

final class PhonyGamepad extends Gamepad {
    private static final float DEFAULT_FLOAT = 0.0f;
    private static final boolean DEFAULT_BOOLEAN = false;

    PhonyGamepad() {
        left_stick_x = DEFAULT_FLOAT;
        left_stick_y = DEFAULT_FLOAT;
        left_trigger = DEFAULT_FLOAT;
        right_stick_x = DEFAULT_FLOAT;
        right_stick_y = DEFAULT_FLOAT;
        right_trigger = DEFAULT_FLOAT;
        a = DEFAULT_BOOLEAN;
        b = DEFAULT_BOOLEAN;
        x = DEFAULT_BOOLEAN;
        y = DEFAULT_BOOLEAN;
        dpad_left = DEFAULT_BOOLEAN;
        dpad_up = DEFAULT_BOOLEAN;
        dpad_right = DEFAULT_BOOLEAN;
        dpad_down = DEFAULT_BOOLEAN;
        left_bumper = DEFAULT_BOOLEAN;
        right_bumper = DEFAULT_BOOLEAN;
        left_stick_button = DEFAULT_BOOLEAN;
        right_stick_button = DEFAULT_BOOLEAN;
        start = DEFAULT_BOOLEAN;
        guide = DEFAULT_BOOLEAN;
    }
}
