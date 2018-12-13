package org.firstinspires.ftc.teamcode.opmodes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@TeleOp(name="STEMFEST TELEOP", group="TeleOp")
public final class STEMFestTeleOp extends OpMode {

    private static final String LEFT_DRIVE_NAME = "LEFT";
    private static final String RIGHT_DRIVE_NAME = "RIGHT";

    private DcMotor left;
    private DcMotor right;

    @Override
    public void init() {
        right = hardwareMap.get(DcMotor.class, LEFT_DRIVE_NAME);
        right.setDirection(DcMotorSimple.Direction.FORWARD);
        left = hardwareMap.get(DcMotor.class, RIGHT_DRIVE_NAME);
        left.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        left.setPower(-gamepad1.left_stick_y);
        right.setPower(-gamepad1.right_stick_y);
    }

    @Override
    public void stop() {

    }
}
