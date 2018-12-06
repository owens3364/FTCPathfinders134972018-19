package org.firstinspires.ftc.teamcode.opmodes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.driversetcontrols.ControlScheme;
import org.firstinspires.ftc.teamcode.driversetcontrols.Controller;
import org.firstinspires.ftc.teamcode.driversetcontrols.Scaler;
import org.firstinspires.ftc.teamcode.hardware.BotMarkIII;
import org.firstinspires.ftc.teamcode.hardware.MechanumDriveOpModeUsageMarkII;

@TeleOp(name = "TeleOpThree", group = "TeleOp")
public final class TeleOpMarkIII extends GenericTeleOp {

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
            "Intake Angle Position"
    };

    private MechanumDriveOpModeUsageMarkII bot;
    private Controller controller1;
    private Controller controller2;

    private double intakeAngle = 0.0;

    @Override
    public void init() {
        if (super.setup(gamepad1, gamepad2, telemetry)) {
            super.init();
            controller1 = super.getController1();
            controller2 = super.getController2();
            bot = new BotMarkIII(hardwareMap);
            bot.zeroAll();

            bot.freezeLift();
            bot.freezeArmSliders();
            bot.freezeArmAngular();
            bot.setIntakeAngle(0.5);

            //ANY ADDITIONAL CODE HERE
            controller2.setControlScheme(ControlScheme.SQUARED);
            telemetryOperation(super::addData, BASIC_TELEMETRY_DATA_KEYS, getBasicDataValues());
            updateTelemetry();
        }
    }

    @Override
    public void init_loop() {
        if (super.getStateOfExecution() == StateOfExecution.INITIALIZED) {
            super.init_loop();

            //ANY ADDITIONAL CODE HERE
            telemetryOperation(super::setData, BASIC_TELEMETRY_DATA_KEYS, getBasicDataValues());
            updateTelemetry();
        }
    }

    @Override
    public void start() {
        if (super.getStateOfExecution() == StateOfExecution.INIT_LOOP_RUNNING) {
            super.start();
            controller2.setControlScheme(ControlScheme.CUBED);

            //ANY ADDITIONAL CODE HERE
            telemetryOperation(super::setData, BASIC_TELEMETRY_DATA_KEYS, getBasicDataValues());
            updateTelemetry();
        }
    }

    @Override
    public void loop() {
        if (super.getStateOfExecution() == StateOfExecution.STARTED) {
            super.loop();

            //Controller1/Bot io
            bot.driveMotorsBySticks(controller1.leftStickX(), controller1.leftStickY(), controller1.rightStickX());

            //Dpad up moves the lift up
            bot.setLiftDrive(controller1.dpadUp() ? 1.0 : 0.0);
            //Dpad down moves the lift down
            bot.setLiftDrive(controller1.dpadDown() ? -1.0 : 0.0);
            //b freezes the lift where it's at
            if (controller2.b()) {
                bot.freezeLift();
            }

            //Controller2/Bot io
            //Dpad left moves the arm angular drive clockwise
            bot.setArmAngularDrive(controller2.dpadUp() ? 1.0 : 0.0);
            //Dpad right moves the arm angular drive counter clockwise
            bot.setArmAngularDrive(controller2.dpadDown() ? -1.0 : 0.0);
            //x freezes the arm angular drive where it's at
            if (controller2.x()) {
                bot.freezeArmAngular();
            }

            //Left trigger retracts the arm linear sliders
            //Right trigger extends the arm linear sliders
            //a freezes the sliders where they're at
            if (controller2.leftTrigger() != 0) {
                bot.setArmSliderDrive(-controller2.leftTrigger());
            } else if (controller2.rightTrigger() != 0) {
                bot.setArmSliderDrive(controller2.rightTrigger());
            } else if (controller2.a()) {
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

            if (0 <= (intakeAngle + (controller2.leftStickX()) / 4) &&
                    (intakeAngle + (controller2.leftStickX()) / 4) <= 1) {
                intakeAngle += (controller2.leftStickX() / 4);
            } else {
                intakeAngle = intakeAngle < 0 ? -1 : 1;
            }
            bot.setIntakeAngle(intakeAngle);

            //Telemetry updates
            telemetryOperation(super::setData, BASIC_TELEMETRY_DATA_KEYS, getBasicDataValues());
            updateTelemetry();
        }
    }

    @Override
    public void stop() {
        if (super.getStateOfExecution() == StateOfExecution.INIT_LOOP_RUNNING || super.getStateOfExecution() == StateOfExecution.START_LOOP_RUNNING) {
            super.stop();
            bot.zeroAll();

            //ANY ADDITIONAL CODE HERE
            updateTelemetry();
            telemetryOperation(super::setData, BASIC_TELEMETRY_DATA_KEYS, getBasicDataValues());
        }
    }

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
                String.valueOf(Scaler.scale(bot.getIntakeAnglePosition(), 0, 1, 0, 180))
        };
    }
}
