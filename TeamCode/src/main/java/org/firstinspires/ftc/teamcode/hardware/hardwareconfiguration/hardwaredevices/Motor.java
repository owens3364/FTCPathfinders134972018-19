package org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices;

import android.support.annotation.NonNull;

public enum Motor {
    //TODO: ENSURE THESE ARE THE CORRECT XML KEYS
    Matrix12vMotor,
    MatrixLegacyMotor,
    NeveRest20Gearmotor,
    NeveRest37v1Gearmotor,
    NeveRest40Gearmotor,
    NeveRest60Gearmotor,
    RevRobotics20HDHexMotor,
    RevRobotics40HDHexMotor,
    RevRoboticsCoreHexMotor,
    TetrixMotor,
    Motor;

    //TODO: FIND OTHER MOTOR ENCODER TICKS
    private static final double MATRIX_12V_MOTOR_ENCODER_TICKS = 1478.4;
    private static final double MATRIX_LEGACY_MOTOR_ENCODER_TICKS = 757.12;
    private static final double NEVEREST_20_GEARMOTOR_ENCODER_TICKS = 537.6;
    private static final double NEVEREST_37_V1_GEARMOTOR_ENCODER_TICKS = 103;
    private static final double NEVEREST_40_GEARMOTOR_ENCODER_TICKS = 1120;
    private static final double NEVEREST_60_GEARMOTOR_ENCODER_TICKS = 1680;
    private static final double REV_ROBOTICS_20_HD_HEX_MOTOR_ENCODER_TICKS = 1120;
    private static final double REV_ROBOTICS_40_HD_HEX_MOTOR_ENCODER_TICKS = 2240;
    private static final double REV_ROBOTICS_CORE_HEX_MOTOR_ENCODER_TICKS = 288;
    private static final double TETRIX_MOTOR_ENCODER_TICKS = 1440;

    private static final String NEVEREST_37_V1_GEARMOTOR_NAME_ADAPTATION = "NeveRest3.7v1Gearmotor";

    public double encoderTicks() {
        switch (this) {
            case Matrix12vMotor:
                return MATRIX_12V_MOTOR_ENCODER_TICKS;
            case MatrixLegacyMotor:
                return MATRIX_LEGACY_MOTOR_ENCODER_TICKS;
            case NeveRest20Gearmotor:
                return NEVEREST_20_GEARMOTOR_ENCODER_TICKS;
            case NeveRest37v1Gearmotor:
                return NEVEREST_37_V1_GEARMOTOR_ENCODER_TICKS;
            case NeveRest40Gearmotor:
                return NEVEREST_40_GEARMOTOR_ENCODER_TICKS;
            case RevRobotics20HDHexMotor:
                return REV_ROBOTICS_20_HD_HEX_MOTOR_ENCODER_TICKS;
            case RevRobotics40HDHexMotor:
                return REV_ROBOTICS_40_HD_HEX_MOTOR_ENCODER_TICKS;
            case RevRoboticsCoreHexMotor:
                return REV_ROBOTICS_CORE_HEX_MOTOR_ENCODER_TICKS;
            case NeveRest60Gearmotor:
                return NEVEREST_60_GEARMOTOR_ENCODER_TICKS;
            case TetrixMotor:
                return TETRIX_MOTOR_ENCODER_TICKS;
        }
        return 0;
    }

    @NonNull
    @Override
    public String toString() {
        if (this.equals(NeveRest37v1Gearmotor)) {
            return NEVEREST_37_V1_GEARMOTOR_NAME_ADAPTATION;
        }
        return this.name();
    }
}
