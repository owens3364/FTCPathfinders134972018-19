package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

final class Lift {
    private DcMotor cableDrive;

    static Lift initialize(HardwareMap map, String cableDriveName) {
        DcMotor cableDrive = map.get(DcMotor.class, cableDriveName);
        cableDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        cableDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        return new Lift(cableDrive);
    }

    private Lift(DcMotor cableDrive) {
        this.cableDrive = cableDrive;
    }

    void setCableDrive(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            cableDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            cableDrive.setPower(power);
        }
    }

    void setCableDriveForRotations(double power, int rotations) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            cableDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            cableDrive.setPower(power);
            cableDrive.setTargetPosition(cableDrive.getCurrentPosition() + rotations);
        }
    }

    void freeze() {
        cableDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cableDrive.setPower(0);
    }

    void coast() {
        cableDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        cableDrive.setPower(0);
    }
}
