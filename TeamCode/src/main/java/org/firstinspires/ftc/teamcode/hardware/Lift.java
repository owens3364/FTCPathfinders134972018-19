package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

final class Lift {
    private DcMotor cableDrive;

    Lift(HardwareMap map, String cableDriveName) {
        cableDrive = map.get(DcMotor.class, cableDriveName);
        cableDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        cableDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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

    void freeze(double robotWeightLBS) {
        cableDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cableDrive.setPower(robotWeightLBS / 50);
    }

    void coast() {
        cableDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        cableDrive.setPower(0);
    }
}
