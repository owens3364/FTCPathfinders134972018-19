package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

final class Intake {
    //Intake Motor
    private static DcMotor intakeDrive;

    //Bot use methods
    static void initialize(HardwareMap map) {
        intakeDrive = map.get(DcMotor.class, "Intake_Drive");
        intakeDrive.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    //Run by power
    static void set(double power) {
        intakeDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeDrive.setPower(power);
    }

    //Run by rotations
    static void setForRotations(double power, int rotations) {
        intakeDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeDrive.setPower(power);
        intakeDrive.setTargetPosition(intakeDrive.getCurrentPosition() + rotations);
    }
}
