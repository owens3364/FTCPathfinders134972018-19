package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

final class Lift {
    //Lift Motors
    private static DcMotor cableDrive;
    private static LimitedDrive cableLock;

    //Bot use methods
    static void initialize(HardwareMap map) {
        cableDrive = map.get(DcMotor.class, "Cable_Drive");
        cableDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        cableDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cableLock = new LimitedDrive("Cable_Lock", "Cable_Min", "Cable_Max", map);
    }

    //Run by power
    static void setCableDrive(double power) {
        cableDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        cableDrive.setPower(power);
    }

    //Run by rotations
    static void setCableDriveForRotations(double power, int rotations) {
        cableDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        cableDrive.setPower(power);
        cableDrive.setTargetPosition(cableDrive.getCurrentPosition() + rotations);
    }

    //Safely use cable lock, do not allow it to exceed its limits
    static void setCableLock(double power) {
        cableLock.set(power);
    }

    //Freeze the robot's position on the cable
    static void freeze() {
        cableDrive.setPower(0);
    }
}
