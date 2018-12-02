package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

final class LiftMarkI {
    private final DcMotor cableDrive;

    LiftMarkI(HardwareMap map, String cableDriveName) {
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

    double getCableDrive() {
        return cableDrive.getPower();
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

    boolean liftFrozen() {
        return cableDrive.getPower() == 0 && cableDrive.getZeroPowerBehavior() == DcMotor.ZeroPowerBehavior.BRAKE;
    }

    void coast() {
        cableDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        cableDrive.setPower(0);
    }

    boolean liftCoasting() {
        return cableDrive.getPower() == 0 && cableDrive.getZeroPowerBehavior() == DcMotor.ZeroPowerBehavior.FLOAT;
    }
}
