package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controls.ControlScheme;
import org.firstinspires.ftc.teamcode.controls.Controller;
import org.firstinspires.ftc.teamcode.controls.Scaler;
import org.firstinspires.ftc.teamcode.hardware.robots.robots.BotMarkIII;
import org.firstinspires.ftc.teamcode.hardware.robots.regulators.MecanumDriveOpModeUsageMarkII;

@Disabled
@TeleOp(name = "TeleOpThree", group = "TeleOp")
public final class TeleOpMarkIII extends GenericTeleOp {

    private static final double SERVO_INCREMENT_DIVISOR = 40;

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
            "Intake Angle Position Scaled"
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
                        180))
        };
    }

    private MecanumDriveOpModeUsageMarkII bot;
    private Controller controller1;
    private Controller controller2;

    private double intakeAngle = 0.0;

    @Override
    public void init() {
        if (super.setup(gamepad1, gamepad2, telemetry)) {
            super.init();
            controller1 = super.getController1();
            controller2 = super.getController2();
            controller2.setCustomizedControlSchemes(Controller.DEFAULT_SCHEME,
                    ControlScheme.CUBED);
            controller2.setCustomizedStickScales(Controller.DEFAULT_STICK_SCALE,
                    new double[] {
                    -1, 1, -.75, .75
                    }); //LEFT STICK Y SCALED -1, 1 to -.75, .75

            bot = new BotMarkIII(hardwareMap);
            bot.zeroAll();

            bot.freezeLift();
            bot.freezeArmSliders();
            bot.freezeArmAngular();
            bot.setIntakeAngle(1.0);

            //ANY ADDITIONAL CODE HERE
            controller2.setControlScheme(ControlScheme.SQUARED);
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
            controller2.setControlScheme(ControlScheme.CUBED);

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
            //b freezes the lift where it's at
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

            //Right Stick Y controls the arm slider drive
            bot.setArmSliderDrive(controller2.rightStickY());
            //b freezes the arm slider drive where it's at
            if (controller2.b()) {
                bot.freezeArmSliders();
            }

            /*
            This looks a bit odd... it's the code to control the angle of the intake box
            With a servo motor.
            Servo motors only accept a range between zero (zero degrees or zero radians)
            And one (180 degrees or pi radians).
            We don't want to scale the servo position to the stick value constantly.
            That would cause the driver to have to worry about the servo position all the time.
            Instead, we store the intake angle that the servo is set to in "intakeAngle"
            Then we change it based upon the movement of leftStickX().
            What if the leftStickX() tells the variable to move out of the range of the servo?
            The hardware objects have built in protection for that- input is validated to ensure
            it's in range of the possible inputs for that hardware object.
            But intakeAngle could increase uncontrollably, even while the hardware object does
            nothing with the calls being made to it.
            To prevent this, the if statement ensures that adding the stick value will not cause
            intakeAngle to exceed the bounds of its range.
            If adding the stick value won't cause the intakeAngle to exceed its boundaries, we're
            good. We set the intakeAngle and are on our way.
            However, if adding the stick value WILL cause the intakeAngle to exceed its
            boundaries, then we need to ensure that does not happen.
            Doing nothing would be confusing to the drivers, because they'd need to reduce the
            Extremes on the sticks which would just feel strange.
            Instead, we check if the intakeAngle is positive or negative and set the value to
            The extreme. We did this in a single ternary expression though- that seems a bit too
            simple.
            The reasoning for this is in the if statement. We are checking if the leftStickX()
            will cause the intakeAngle to exceed its boundaries when leftStickX() is divided by
            four. The range of possible (leftStickX() / 4) values is [-.25, .25].
            If the that value is enough to cause the intakeAngle to exceed its boundaries,
            then we know that the intake angle is at at least +.75 or at most -.75.
            If the intakeAngle is already at least .75 or at most -.75, and being pushed past
            extreme, then (while the most extreme possible leftStickX() / 4 is +1 or -1) -.75 or
            less will go to -1 and +.75 or more will go to 1. We know intakeAngle is at least .75
            or at most -.75 so all we need to check now is if it's negative. That's what the
            ternary expression in the else block does.
            */

            if (0 <= (intakeAngle + (controller2.leftStickX()) / SERVO_INCREMENT_DIVISOR) &&
                    (intakeAngle + (controller2.leftStickX()) / SERVO_INCREMENT_DIVISOR) <= 1) {
                intakeAngle += (controller2.leftStickX() / SERVO_INCREMENT_DIVISOR);
            } else {
                intakeAngle = intakeAngle < 0.5 ? 0 : 1;
            }
            bot.setIntakeAngle(intakeAngle);

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
