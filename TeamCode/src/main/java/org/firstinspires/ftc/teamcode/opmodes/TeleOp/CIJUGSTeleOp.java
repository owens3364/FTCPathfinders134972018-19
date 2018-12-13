package org.firstinspires.ftc.teamcode.opmodes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.driversetcontrols.Controller;

@Disabled
@TeleOp(name="CIJUGS TELEOP", group="TeleOp")
public final class CIJUGSTeleOp extends GenericTeleOp {

    private static final String FRONT_LEFT_DRIVE_NAME = "FrontLeftDrive";
    private static final String FRONT_RIGHT_DRIVE_NAME = "FrontRightDrive";
    private static final String REAR_LEFT_DRIVE_NAME = "RearLeftDrive";
    private static final String REAR_RIGHT_DRIVE_NAME = "RearRightDrive";

    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor rearLeftDrive;
    private DcMotor rearRightDrive;

    private Controller controller1;
    private Controller controller2;

    @Override
    public void init() {
        if (super.setup(gamepad1, gamepad2, telemetry)) {
            super.init();
            controller1 = super.getController1();
            controller2 = super.getController2();
            frontLeftDrive = hardwareMap.get(DcMotor.class, FRONT_LEFT_DRIVE_NAME);
            frontRightDrive = hardwareMap.get(DcMotor.class, FRONT_RIGHT_DRIVE_NAME);
            frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
            rearLeftDrive = hardwareMap.get(DcMotor.class, REAR_LEFT_DRIVE_NAME);
            rearRightDrive = hardwareMap.get(DcMotor.class, REAR_RIGHT_DRIVE_NAME);
            rearRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);

            //ANY ADDITIONAL CODE HERE
            updateTelemetry();
        }
    }

    @Override
    public void init_loop() {
        if (super.getStateOfExecution() == StateOfExecution.INITIALIZED || super.getStateOfExecution() == StateOfExecution.INIT_LOOP_RUNNING) {
            super.init_loop();

            //ANY ADDITIONAL CODE HERE
            updateTelemetry();
        }
    }

    @Override
    public void start() {
        if (super.getStateOfExecution() == StateOfExecution.INIT_LOOP_RUNNING) {
            super.start();
            //ANY ADDITIONAL CODE HERE
            updateTelemetry();
        }
    }

    @Override
    public void loop() {
        if (super.getStateOfExecution() == StateOfExecution.STARTED || super.getStateOfExecution() == StateOfExecution.START_LOOP_RUNNING) {
            super.loop();
            double r = Math.hypot(controller1.leftStickX(), controller1.leftStickY());
            double rightX = controller1.rightStickX();
            double robotAngle = Math.atan2(controller1.leftStickY(), controller1.leftStickX());
            double v1 = r * Math.cos(robotAngle) + rightX;
            double v2 = r * Math.sin(robotAngle) - rightX;
            double v3 = r * Math.sin(robotAngle) + rightX;
            double v4 = r * Math.cos(robotAngle) - rightX;
            frontLeftDrive.setPower(v3);
            frontRightDrive.setPower(v2);
            rearLeftDrive.setPower(v1);
            rearRightDrive.setPower(v4);
            if (controller1.a()) {
                frontLeftDrive.setPower(0);
                frontRightDrive.setPower(0);
                rearLeftDrive.setPower(0);
                rearRightDrive.setPower(0);
            }
            //ANY ADDITIONAL CODE HERE
            updateTelemetry();
        }
    }

    @Override
    public void stop() {
        if (super.getStateOfExecution() == StateOfExecution.INIT_LOOP_RUNNING || super.getStateOfExecution() == StateOfExecution.START_LOOP_RUNNING) {
            super.stop();

            //ANY ADDITIONAL CODE HERE
            updateTelemetry();
        }
    }
}
