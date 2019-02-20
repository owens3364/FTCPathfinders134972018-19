package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controls.ControlScheme;
import org.firstinspires.ftc.teamcode.controls.Controller;
import org.firstinspires.ftc.teamcode.controls.Scaler;
import org.firstinspires.ftc.teamcode.errorIO.LogUtils;
import org.firstinspires.ftc.teamcode.hardware.robots.regulators.MecanumDriveOpModeUsageMarkIV;
import org.firstinspires.ftc.teamcode.hardware.robots.robots.BotMarkV;

import java.io.PrintWriter;
import java.io.StringWriter;


@TeleOp(name="TeleOpFive")
public final class TeleOpV extends GenericTeleOp {

    private static final double INTAKE_LID_INCREMENT = 0.02;
    private static final double ARM_ANGULAR_INCREMENT = 0.02;

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
            "Intake Power",
            "Intake Frozen",
            "Intake Coasting",
            "Arm Angle Position Unscaled",
            "Arm Angle Position Scaled",
    };

    private String[] getBasicDataValues() {
        return new String[] {
                String.valueOf(bot.getFrontLeftDrive()),
                String.valueOf(bot.getFrontRightDrive()),
                String.valueOf(bot.getRearLeftDrive()),
                String.valueOf(bot.getRearRightDrive()),
                String.valueOf(bot.getLiftDrive()),
                String.valueOf(bot.liftDriveFrozen()),
                String.valueOf(bot.liftDriveCoasting()),
                String.valueOf(bot.getSliderDrive()),
                String.valueOf(bot.sliderDriveFrozen()),
                String.valueOf(bot.sliderDriveCoasting()),
                String.valueOf(bot.getIntakeDrive()),
                String.valueOf(bot.intakeDriveFrozen()),
                String.valueOf(bot.intakeDriveCoasting()),
                String.valueOf(bot.getAngular()),
                String.valueOf(Scaler.scale(bot.getAngular(),
                        0,
                        1,
                        bot.getAngularMin(),
                        bot.getAngularMax())),
        };
    }

    private MecanumDriveOpModeUsageMarkIV bot;
    private Controller controller1;
    private Controller controller2;

    private double intakeAngle = 1.0;
    private double armAngle = 0.0;

    private boolean generatingAutonomous = false;

    @Override
    public void init() {
        try {
            if (super.setup(gamepad1, gamepad2, telemetry)) {
                super.init();
                controller1 = super.getController1();
                controller2 = super.getController2();
                controller2.setCustomizedControlSchemes(Controller.DEFAULT_SCHEME,
                        Controller.DEFAULT_SCHEME, ControlScheme.CUBED,
                        Controller.DEFAULT_SCHEME, ControlScheme.CUBED,
                        ControlScheme.CUBED);

                controller2.setLeftTriggerPreScheme((Double value) -> -value); //Left trigger inverted
                controller2.setCustomizedTriggerScales(new double[]{
                        -1, 0, -1, 0
                });


                bot = new BotMarkV(hardwareMap);
                bot.zeroAll();
                bot.setIntakeLid(intakeAngle);

                //ANY ADDITIONAL CODE HERE
                cycleTelemetry(super::addData, BASIC_TELEMETRY_DATA_KEYS, getBasicDataValues());
            }
        } catch (Exception ex) {
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            LogUtils.logError(sw.toString());
        }
    }

    @Override
    public void init_loop() {
        try {
            if (super.getStateOfExecution() == StateOfExecution.INITIALIZED ||
                    super.getStateOfExecution() == StateOfExecution.INIT_LOOP_RUNNING) {
                super.init_loop();

                checkToTrackAutonomous();
                //ANY ADDITIONAL CODE HERE
                cycleTelemetry(super::setData, BASIC_TELEMETRY_DATA_KEYS, getBasicDataValues());
            }
        } catch (Exception ex) {
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            LogUtils.logError(sw.toString());
        }
    }

    @Override
    public void start() {
        try {
            if (super.getStateOfExecution() == StateOfExecution.INIT_LOOP_RUNNING) {
                super.start();

                checkToTrackAutonomous();
                //ANY ADDITIONAL CODE HERE
                cycleTelemetry(super::setData, BASIC_TELEMETRY_DATA_KEYS, getBasicDataValues());
            }
        } catch (Exception ex) {
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            LogUtils.logError(sw.toString());
        }
    }

    @Override
    public void loop() {
        try {
            if (super.getStateOfExecution() == StateOfExecution.STARTED ||
                    super.getStateOfExecution() == StateOfExecution.START_LOOP_RUNNING) {
                super.loop();

                checkToTrackAutonomous();

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
                armAngle += controller2.rightStickY() * ARM_ANGULAR_INCREMENT;
                bot.setArmAngular(1.0, armAngle);

                //Dpad moves arm sliders in and out
                //Arm sliders freeze by default
                if (controller2.dpadUp()) {
                    bot.setArmSliderDrive(1.0);
                } else if (controller2.dpadDown()) {
                    bot.setArmSliderDrive(-1.0);
                } else {
                    bot.freezeArmSliders();
                }

                //Left and right triggers control the intake drive
                //Intake drive freezes by default
                if (controller2.leftTrigger() != 0) {
                    bot.setIntakeDrive(controller2.leftTrigger());
                } else if (controller2.rightTrigger() != 0) {
                    bot.setIntakeDrive(controller2.rightTrigger());
                } else {
                    bot.freezeIntakeDrive();
                }

                //Left and right bumpers position the intake lid
                if (controller2.leftBumper()) {
                    if (intakeAngle - INTAKE_LID_INCREMENT >= 0.0) {
                        intakeAngle -= INTAKE_LID_INCREMENT;
                    } else {
                        intakeAngle = 0.0;
                    }
                    bot.setIntakeLid(intakeAngle);
                } else if (controller2.rightBumper()) {
                    if (intakeAngle + INTAKE_LID_INCREMENT <= 1.0) {
                        intakeAngle += INTAKE_LID_INCREMENT;
                    } else {
                        intakeAngle = 1.0;
                    }
                    bot.setIntakeLid(intakeAngle);
                }

                //Left stick button deploys the team marker
                if (controller2.leftStick()) {
                    bot.deployMarker();
                }

                //Telemetry updates
                cycleTelemetry(super::setData, BASIC_TELEMETRY_DATA_KEYS, getBasicDataValues());
            }
        } catch (Exception ex) {
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            LogUtils.logError(sw.toString());
        }
    }

    @Override
    public void stop() {
        try {
            if (super.getStateOfExecution() == StateOfExecution.INIT_LOOP_RUNNING ||
                    super.getStateOfExecution() == StateOfExecution.START_LOOP_RUNNING) {
                super.stop();
                bot.zeroAll();

                if (generatingAutonomous) {
                    bot.createAutonomous(bot.getMovementsForAutonomous());
                }
                //ANY ADDITIONAL CODE HERE
                cycleTelemetry(super::setData, BASIC_TELEMETRY_DATA_KEYS, getBasicDataValues());
            }
        } catch (Exception ex) {
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            LogUtils.logError(sw.toString());
        }
    }

    private void checkToTrackAutonomous() {
        if (controller1.y()) {
            generatingAutonomous = true;
            bot.trackMovementsForAutonomous();
        }
    }

}
