package org.firstinspires.ftc.teamcode.hardware;

enum MotorType {

    TETRIX_MAX, NEVEREST_60;

    private static final int TETRIX_MAX_ENCODER_TICKS = 1440;
    private static final int NEVEREST_60_ENCODER_TICKS = 1680;

    int encoderTicks() {
        switch (this) {
            case TETRIX_MAX:
                return TETRIX_MAX_ENCODER_TICKS;
            case NEVEREST_60:
                return NEVEREST_60_ENCODER_TICKS;
        }
        return -1;
    }

}
