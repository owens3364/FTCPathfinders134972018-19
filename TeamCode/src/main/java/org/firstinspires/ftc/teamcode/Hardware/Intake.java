package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

final class Intake {
    private DcMotor intakeDrive;

    static Intake initialize(HardwareMap map, String intakeDriveName) {
        DcMotor intakeDrive = map.get(DcMotor.class, intakeDriveName);
        intakeDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        return new Intake(intakeDrive);
    }

    private Intake(DcMotor intakeDrive) {
        this.intakeDrive = intakeDrive;
    }

    void set(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            intakeDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            intakeDrive.setPower(power);
        }
    }

    void setForRotations(double power, int rotations) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            intakeDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            intakeDrive.setPower(power);
            intakeDrive.setTargetPosition(intakeDrive.getCurrentPosition() + rotations);
        }
    }
}
