package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

final class Arm {
    //Slider Motor, Slider Servos, Grip Servos
    private static DcMotor sliderDrive;
    private static Servo sliderYaw;
    private static Servo sliderPitch;
    private static Servo leftGrip;
    private static Servo rightGrip;

    //Bot use methods
    static void initialize(HardwareMap map) {
        sliderDrive = map.get(DcMotor.class, "");
        sliderDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        sliderYaw = map.get(Servo.class, "");
        sliderPitch = map.get(Servo.class, "");
        leftGrip = map.get(Servo.class, "");
        rightGrip = map.get(Servo.class, "");
    }

    //Run sliderDrive by power
    static void setSlidersDrive(double power) {
        sliderDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        sliderDrive.setPower(power);
    }

    //Run sliderDrive by rotations
    static void setSlidersDriveForRotations(double power, int rotations) {
        sliderDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sliderDrive.setPower(power);
        sliderDrive.setTargetPosition(sliderDrive.getCurrentPosition() + rotations);
    }

    //Servo positioning
    static void setSlidersYaw(double position) {
        sliderYaw.setPosition(position);
    }

    static void setSlidersPitch(double position) {
        sliderPitch.setPosition(position);
    }

    static void setLeftGrip(double position) {
        leftGrip.setPosition(position);
    }

    static void setRightGrip(double position) {
        rightGrip.setPosition(position);
    }
}
