package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controls.ControlScheme;
import org.firstinspires.ftc.teamcode.controls.Controller;
import org.firstinspires.ftc.teamcode.controls.Scaler;
import org.firstinspires.ftc.teamcode.hardware.robots.regulators.MecanumDriveOpModeUsageMarkIII;
import org.firstinspires.ftc.teamcode.hardware.robots.robots.BotMarkIV;

@Disabled
@TeleOp(name = "TeleOpFour", group = "TeleOp")
public final class TeleOpMarkIV extends GenericTeleOp {

    private static final double INTAKE_INCREMENT = 0.02;

    private static final String[] BASIC_TELEMETRY_DATA_KEYS = {
            "Front Left Power",
            "Front Right Power",
            "Rear Left Power",
            "Rear Right Power",
            "Lift Power",
            "Lift Frozen",
            "Lift Coasting",
            "Arm Slider Power",
            "Arm Sliders Frozen",
            "Arm Sliders Coasting",
            "Arm Angular Power",
            "Arm Angular Frozen",
            "Arm Angular Coasting",
            "Intake Angle Position Unscaled",
            "Intake Angle Position Scaled",
            "Secondary Arm Angular Power",
            "Secondary Arm Angular Frozen",
            "Secondary Arm Angular Coasting"
    };

    private String[] getBasicDataValues() {
        return new String[] {
                String.valueOf(bot.getFrontLeftDrivePower()),
                String.valueOf(bot.getFrontRightDrivePower()),
                String.valueOf(bot.getRearLeftDrivePower()),
                String.valueOf(bot.getRearRightDrivePower()),
                String.valueOf(bot.getLiftDrivePower()),
                String.valueOf(bot.getLiftFrozen()),
                String.valueOf(bot.getLiftCoasting()),
                String.valueOf(bot.getArmSliderDrivePower()),
                String.valueOf(bot.getArmSlidersFrozen()),
                String.valueOf(bot.getArmSlidersCoasting()),
                String.valueOf(bot.getArmAngularDrivePower()),
                String.valueOf(bot.getArmAngularFrozen()),
                String.valueOf(bot.getArmAngularCoasting()),
                String.valueOf(bot.getIntakeAngle()),
                String.valueOf(Scaler.scale(bot.getIntakeAngle(),
                        0,
                        1,
                        0,
                        180)),
                String.valueOf(bot.getSecondaryArmAngularDrive()),
                String.valueOf(bot.getSecondaryArmAngularDriveFrozen()),
                String.valueOf(bot.getSecondaryArmAngularDriveCoasting())
        };
    }

    private MecanumDriveOpModeUsageMarkIII bot;
    private Controller controller1;
    private Controller controller2;

    private double intakeAngle = 1.0;

    @Override
    public void init() {
        if (super.setup(gamepad1, gamepad2, telemetry)) {
            super.init();
            controller1 = super.getController1();
            controller2 = super.getController2();
            controller2.setCustomizedControlSchemes(Controller.DEFAULT_SCHEME,
                    Controller.DEFAULT_SCHEME, ControlScheme.CUBED,
                    Controller.DEFAULT_SCHEME, ControlScheme.CUBED,
                    ControlScheme.CUBED);

            controller2.setLeftTriggerPreScheme((Double value) -> -value); //Left trigger inverted
            controller2.setCustomizedTriggerScales(new double[] {
                    -1, 0, -1, 0
            });


            bot = new BotMarkIV(hardwareMap);
            bot.zeroAll();

            bot.freezeLift();
            bot.freezeArmSliders();
            bot.freezeArmAngular();
            bot.freezeSecondaryArmAngularDrive();
            bot.setIntakeAngle(1.0);

            //ANY ADDITIONAL CODE HERE
            cycleTelemetry(super::addData, BASIC_TELEMETRY_DATA_KEYS, getBasicDataValues());
        }
    }

    @Override
    public void init_loop() {
        if (super.getStateOfExecution() == StateOfExecution.INITIALIZED ||
                super.getStateOfExecution() == StateOfExecution.INIT_LOOP_RUNNING) {
            super.init_loop();

            //ANY ADDITIONAL CODE HERE
            cycleTelemetry(super::setData, BASIC_TELEMETRY_DATA_KEYS, getBasicDataValues());
        }
    }

    @Override
    public void start() {
        if (super.getStateOfExecution() == StateOfExecution.INIT_LOOP_RUNNING) {
            super.start();
            //ANY ADDITIONAL CODE HERE
            cycleTelemetry(super::setData, BASIC_TELEMETRY_DATA_KEYS, getBasicDataValues());
        }
    }

    @Override
    public void loop() {
        if (super.getStateOfExecution() == StateOfExecution.STARTED ||
                super.getStateOfExecution() == StateOfExecution.START_LOOP_RUNNING) {
            super.loop();

            //Controller1/Bot io
            bot.driveMotorsBySticks(controller1.leftStickX(),
                    controller1.leftStickY(),
                    controller1.rightStickX());

            //Dpad up moves the lift up
            //Dpad down moves the lift down
            //Lift freezes by default
            if (controller1.dpadUp()) {
                bot.setLiftDrive(1);
            } else if (controller1.dpadDown()) {
                bot.setLiftDrive(-1);
            } else {
                bot.freezeLift();
            }

            //Controller2/Bot io
            //Right Stick Y controls the arm angular drive
            bot.setArmAngularDrive(controller2.rightStickY());
            //a freezes the arm angular drive where it's at
            if (controller2.a()) {
                bot.freezeArmAngular();
            }

            //Dpad moves arm sliders in and out
            //Arm sliders freeze by default
            if (controller2.dpadUp()) {
                bot.setArmSliderDrive(1.0);
            } else if (controller2.dpadDown()) {
                bot.setArmSliderDrive(-1.0);
            } else {
                bot.freezeArmSliders();
            }

            //Left and right triggers control the secondary arm angular drive
            //Secondary arm angular drive freezes by default
            if (controller2.leftTrigger() != 0) {
                bot.setSecondaryArmAngularDrive(controller2.leftTrigger());
            } else if (controller2.rightTrigger() != 0) {
                bot.setSecondaryArmAngularDrive(controller2.rightTrigger());
            } else {
                bot.freezeSecondaryArmAngularDrive();
            }

            //Left and right bumpers spin the intake servo
            if (controller2.leftBumper()) {
                if (intakeAngle - INTAKE_INCREMENT >= 0.0) {
                    intakeAngle -= INTAKE_INCREMENT;
                } else {
                    intakeAngle = 0.0;
                }
            } else if (controller2.rightBumper()) {
                if (intakeAngle + INTAKE_INCREMENT <= 1.0) {
                    intakeAngle += INTAKE_INCREMENT;
                } else {
                    intakeAngle = 1.0;
                }
            }
            bot.setIntakeAngle(intakeAngle);

            //Left stick button deploys the team marker
            if (controller2.leftStick()) {
                bot.deployMarker();
            }

            //Telemetry updates
            cycleTelemetry(super::setData, BASIC_TELEMETRY_DATA_KEYS, getBasicDataValues());
        }
    }

    @Override
    public void stop() {
        if (super.getStateOfExecution() == StateOfExecution.INIT_LOOP_RUNNING ||
                super.getStateOfExecution() == StateOfExecution.START_LOOP_RUNNING) {
            super.stop();
            bot.zeroAll();

            //ANY ADDITIONAL CODE HERE
            cycleTelemetry(super::setData, BASIC_TELEMETRY_DATA_KEYS, getBasicDataValues());
        }
    }
}
