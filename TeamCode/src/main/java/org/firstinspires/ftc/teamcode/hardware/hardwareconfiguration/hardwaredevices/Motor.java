package org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices;

public enum Motor {
    //TODO: ENSURE THESE ARE THE CORRECT XML KEYS
    Matrix12vMotor,
    MatrixLegacy96vMotor,
    NeveRest20Gearmotor,
    NeveRest37v1Gearmotor,
    NeveRest40Gearmotor,
    NeveRest60Gearmotor,
    REVRobotics201HDHexMotor,
    REVRobotics401HDHexMotor,
    REVRoboticsCoreHexMotor,
    TetrixMotor,
    UnspecifiedMotor;

    //TODO: FIND OTHER MOTOR ENCODER TICKS
    private static final double MATRIX_12V_MOTOR_ENCODER_TICKS = 1478.4;
    private static final double MATRIX_LEGACY_96V_MOTOR_ENCODER_TICKS = 757.12;
    private static final double NEVEREST_20_GEARMOTOR_ENCODER_TICKS = 537.6;
    private static final double NEVEREST_37_V1_GEARMOTOR_ENCODER_TICKS = 103;
    private static final double NEVEREST_40_GEARMOTOR_ENCODER_TICKS = 1120;
    private static final double NEVEREST_60_ENCODER_TICKS = 1680;
    private static final double REVROBOTICS_201_HD_HEX_MOTOR_ENCODER_TICKS = 1120;
    private static final double REVROBOTICS_401_HD_HEX_MOTOR_ENCODER_TICKS = 2240;
    private static final double REVROBOTICS_CORE_HEX_MOTOR_ENCODER_TICKS = 288;
    private static final double TETRIX_MOTOR_ENCODER_TICKS = 1440;

    public double encoderTicks() {
        switch (this) {
            case Matrix12vMotor:
                return MATRIX_12V_MOTOR_ENCODER_TICKS;
            case MatrixLegacy96vMotor:
                return MATRIX_LEGACY_96V_MOTOR_ENCODER_TICKS;
            case NeveRest20Gearmotor:
                return NEVEREST_20_GEARMOTOR_ENCODER_TICKS;
            case NeveRest37v1Gearmotor:
                return NEVEREST_37_V1_GEARMOTOR_ENCODER_TICKS;
            case NeveRest40Gearmotor:
                return NEVEREST_40_GEARMOTOR_ENCODER_TICKS;
            case REVRobotics201HDHexMotor:
                return REVROBOTICS_201_HD_HEX_MOTOR_ENCODER_TICKS;
            case REVRobotics401HDHexMotor:
                return REVROBOTICS_401_HD_HEX_MOTOR_ENCODER_TICKS;
            case REVRoboticsCoreHexMotor:
                return REVROBOTICS_CORE_HEX_MOTOR_ENCODER_TICKS;
            case NeveRest60Gearmotor:
                return NEVEREST_60_ENCODER_TICKS;
            case TetrixMotor:
                return TETRIX_MOTOR_ENCODER_TICKS;
        }
        return 0;
    }
}
