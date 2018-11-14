package org.firstinspires.ftc.teamcode.opmodes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.driversetcontrols.ControlScheme;
import org.firstinspires.ftc.teamcode.driversetcontrols.Controller;
import org.firstinspires.ftc.teamcode.hardware.BotMarkII;
import org.firstinspires.ftc.teamcode.hardware.MechanumDriveOpModeUsage;


@TeleOp(name = "TeleOpTwo", group = "TeleOp")
public final class TeleOpMarkII extends GenericTeleOp {

    private MechanumDriveOpModeUsage bot;
    private Controller controller1;
    private Controller controller2;
    private boolean yToggled = false;
    private double intakeAngle = 0.0;

    @Override
    public void init() {
        if (super.setup(gamepad1, gamepad2, telemetry)) {
            super.init();
            controller1 = super.getController1();
            controller2 = super.getController2();
            bot = new BotMarkII(hardwareMap);

            //ANY ADDITIONAL CODE HERE
            updateTelemetry();
        }
    }

    @Override
    public void init_loop() {
        if (super.isInitialized()) {
            super.init_loop();

            //ANY ADDITIONAL CODE HERE
            updateTelemetry();
        }
    }

    @Override
    public void start() {
        if (super.isInitLoopRunning()) {
            super.start();
            controller2.setControlScheme(ControlScheme.CUBED);
            //ANY ADDITIONAL CODE HERE
            updateTelemetry();
        }
    }

    @Override
    public void loop() {
        if (super.isStarted()) {
            super.loop();

            //Controller1/Bot io
            //Mechanum wheel code
            double leftX = controller1.leftStickX();
            double triggerY = evalTriggers();
            telemetry.addData("LeftStickX", leftX);
            telemetry.addData("LeftStickY", leftX);
            telemetry.addData("RightStickX", controller1.rightStickX());
            double magnitude = Math.hypot(leftX, triggerY);
            double direction = Math.atan2(triggerY, leftX) - Math.PI / 4;
            telemetry.addData("Magnitude", magnitude);
            telemetry.addData("Direction", direction);
            telemetry.addData("FrontLeftPower", magnitude * Math.cos(direction) + -controller1.rightStickX());
            telemetry.addData("FrontRightPower", magnitude * Math.sin(direction) - -controller1.rightStickX());
            telemetry.addData("RearLeftPower", magnitude * Math.sin(direction) + -controller1.rightStickX());
            telemetry.addData("RearRightPower", magnitude * Math.cos(direction) - -controller1.rightStickX());
            bot.setFrontLeftDrive(magnitude * Math.cos(direction) + controller1.rightStickX());
            bot.setFrontRightDrive(magnitude * Math.sin(direction) - controller1.rightStickX());
            bot.setRearLeftDrive(magnitude * Math.sin(direction) + controller1.rightStickX());
            bot.setRearRightDrive(magnitude * Math.cos(direction) - controller1.rightStickX());

            //Controller2/Bot io
            //Lift code
            //Move the lift up with the left trigger
            if (controller2.leftTrigger() != 0) {
                bot.setLiftDrive(controller2.leftTrigger());
            }
            //And down with the right
            else if (controller2.rightTrigger() != 0) {
                bot.setLiftDrive(-controller2.rightTrigger());
            }
            //And freeze it when X is pressed
            else if (controller2.x()) {
                bot.freezeLift();
            }
            //Run the slider drive with the left stick
            bot.setArmSliderDrive(controller2.leftStickY());
            //And freeze it when A is pressed
            if (controller2.a()) {
                bot.freezeArmSliders();
            }
            //Run the slider angler drive with the right stick
            bot.setArmAnglerDrive(controller2.rightStickY());
            //And freeze it when B is pressed
            if (controller2.b()) {
                bot.freezeArmAngler();
            }
            //Run the intake angler off the up and down dpad buttons
            if (controller2.dpadUp()) {
                intakeAngle += 0.01;
            }

            if (controller2.dpadDown()) {
                intakeAngle -= 0.01;
            }
            //Open and close the intake lid by toggling Y
            if (controller2.y()) {
                yToggled = !yToggled;
                if (yToggled) {
                    bot.closeArmLid();
                } else {
                    bot.openArmLid();
                }
            }

            //ANY ADDITIONAL CODE HERE
            updateTelemetry();
        }
    }

    @Override
    public void stop() {
        if (super.isInitLoopRunning() || super.isStartLoopRunning()) {
            super.stop();
            bot.zeroAll();

            //ANY ADDITIONAL CODE HERE
            updateTelemetry();
        }
    }

    private void updateTelemetry() {
        if (super.isSetup()) {
            //Controller input values

            //Robot output values

            telemetry.update();
        }
    }

    private double evalTriggers() {
        if (controller1.rightTrigger() != 0) {
            return controller1.rightTrigger();
        }
        if (controller1.leftTrigger() != 0) {
            return -controller1.leftTrigger();
        }
        return 0;
    }
}
