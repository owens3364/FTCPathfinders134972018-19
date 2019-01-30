package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.driversetcontrols.ControlScheme;
import org.firstinspires.ftc.teamcode.driversetcontrols.Controller;
import org.firstinspires.ftc.teamcode.driversetcontrols.Scaler;
import org.firstinspires.ftc.teamcode.hardware.robotinterfacesandabstracts.MechanumDriveOpModeUsageMarkIII;
import org.firstinspires.ftc.teamcode.hardware.robots.BotMarkIV;

@TeleOp(name = "TeleOpFour", group = "TeleOp")
public final class TeleOpMarkIV extends GenericTeleOp {

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

    private MechanumDriveOpModeUsageMarkIII bot;
    private Controller controller1;
    private Controller controller2;

    private boolean intakeToggled = false;

    @Override
    public void init() {
        if (super.setup(gamepad1, gamepad2, telemetry)) {
            super.init();
            controller1 = super.getController1();
            controller2 = super.getController2();
            controller2.setCustomizedControlSchemes(Controller.DEFAULT_SCHEME,
                    ControlScheme.CUBED, ControlScheme.CUBED);
            controller2.setCustomizedStickScales(Controller.DEFAULT_STICK_SCALE,
                    new double[] {
                            -1, 1, -.50, .50
                    }); //LEFT STICK Y SCALED -1, 1 to -.75, .75
            controller2.setCustomizedTriggerScales(
                    new double[] {
                            -1, 0, -.5, 0
                    },
                    new double[] {
                            0, 1, 0, .75
                    }
            );

            controller2.setLeftTriggerPreScheme((Double value) -> -value); //Left trigger inverted

            bot = new BotMarkIV(hardwareMap);
            bot.zeroAll();

            bot.freezeLift();
            bot.freezeArmSliders();
            bot.freezeArmAngular();
            bot.freezeSecondaryArmAngularDrive();
            bot.setIntakeAngle(1.0);

            //ANY ADDITIONAL CODE HERE
            controller2.setControlScheme(ControlScheme.CUBED);
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
            if (controller1.dpadUp()) {
                bot.setLiftDrive(1);
            } else if (controller1.dpadDown()) {
                bot.setLiftDrive(-1);
            }
            //a freezes the lift where it's at
            if (controller1.a()) {
                bot.freezeLift();
            }

            //Controller2/Bot io
            //Left Stick Y controls the arm angular drive
            bot.setArmAngularDrive(controller2.leftStickY());
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

            //Right stick button toggles the intake servo open and closed
            if (controller2.rightStick()) {
                intakeToggled = !intakeToggled;
            }
            bot.setIntakeAngle(intakeToggled ? 1.0 : 0.0);

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
