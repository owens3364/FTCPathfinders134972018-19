package org.firstinspires.ftc.teamcode.hardware.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.hardwareconfiguration.hardwaredevices.Motor;

final class DcMotorWrapper {

    private final DcMotor motor;
    private final Motor motorType;
    private final double mmPerDriveRotation;

    DcMotorWrapper(HardwareMap map, String motorName, Direction motorRotatingDirection,
                   Motor motorType, double wheelDiameter) {
        this.motor = map.get(DcMotor.class, motorName);
        motor.setDirection(motorRotatingDirection);
        this.motorType = motorType;
        this.mmPerDriveRotation = WheelStats.mmPerDriveRotationFromInDiameter(wheelDiameter);
    }

    void set(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            motor.setMode(RunMode.RUN_WITHOUT_ENCODER);
            motor.setPower(power);
        }
    }

    void set(double power, double mm) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR) && motorType != null) {
            motor.setMode(RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(RunMode.RUN_TO_POSITION);
            motor.setTargetPosition(mmToTicks(mm));
            motor.setPower(power);
        }
    }

    void driveForRotations(double power, double rotations) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR) && motorType != null) {
            motor.setMode(RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(RunMode.RUN_TO_POSITION);
            motor.setTargetPosition((int) (rotations * motorType.encoderTicks()));
            motor.setPower(power);
        }
    }

    double getPower() {
        return motor.getPower();
    }

    void freezeAtZeroPower() {
        motor.setZeroPowerBehavior(ZeroPowerBehavior.BRAKE);
    }

    void coastAtZeroPower() {
        motor.setZeroPowerBehavior(ZeroPowerBehavior.FLOAT);
    }

    boolean frozenAtZeroPower() {
        return motor.getZeroPowerBehavior() == ZeroPowerBehavior.BRAKE;
    }

    boolean coastingAtZeroPower() {
        return motor.getZeroPowerBehavior() == ZeroPowerBehavior.FLOAT;
    }

    Motor getMotorType() {
        return motorType;
    }

    private int mmToTicks(double mm) {
        return motorType == null ? -1 :
        (int) (((mm * motorType.encoderTicks()) / mmPerDriveRotation ) + .5);
    }
}
