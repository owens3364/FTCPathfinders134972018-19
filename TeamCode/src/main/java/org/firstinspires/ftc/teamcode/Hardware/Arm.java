package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

final class Arm {
    private DcMotor sliderDrive;
    private Servo sliderYaw;
    private Servo sliderPitch;
    private Servo leftGrip;
    private Servo rightGrip;

    static Arm initialize(HardwareMap map, String sliderDriveName, String sliderYawName, String sliderPitchName, String leftGripName, String rightGripName) {
        DcMotor sliderDrive = map.get(DcMotor.class, sliderDriveName);
        sliderDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        Servo sliderYaw = map.get(Servo.class, sliderYawName);
        Servo sliderPitch = map.get(Servo.class, sliderPitchName);
        Servo leftGrip = map.get(Servo.class, leftGripName);
        Servo rightGrip = map.get(Servo.class, rightGripName);
        return new Arm(sliderDrive, sliderYaw, sliderPitch, leftGrip, rightGrip);
    }

    private Arm(DcMotor sliderDrive, Servo sliderYaw, Servo sliderPitch, Servo leftGrip, Servo rightGrip) {
        this.sliderDrive = sliderDrive;
        this.sliderYaw = sliderYaw;
        this.sliderPitch = sliderPitch;
        this.leftGrip = leftGrip;
        this.rightGrip = rightGrip;
    }

    void setSlidersDrive(double power) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            sliderDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            sliderDrive.setPower(power);
        }
    }

    void setSlidersDriveForRotations(double power, int rotations) {
        if (HardwareInput.validate(power, InputType.FOR_MOTOR)) {
            sliderDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            sliderDrive.setPower(power);
            sliderDrive.setTargetPosition(sliderDrive.getCurrentPosition() + rotations);
        }
    }

    void setSlidersYaw(double position) {
        if (HardwareInput.validate(position, InputType.FOR_SERVO)) {
            sliderYaw.setPosition(position);
        }
    }

    void setSlidersPitch(double position) {
        if (HardwareInput.validate(position, InputType.FOR_SERVO)) {
            sliderPitch.setPosition(position);
        }
    }

    void setLeftGrip(double position) {
        if (HardwareInput.validate(position, InputType.FOR_SERVO)) {
            leftGrip.setPosition(position);
        }
    }

    void setRightGrip(double position) {
        if (HardwareInput.validate(position, InputType.FOR_SERVO)) {
            rightGrip.setPosition(position);
        }
    }
}
