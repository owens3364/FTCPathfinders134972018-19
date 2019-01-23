package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.driversetcontrols.ControlScheme;
import org.firstinspires.ftc.teamcode.driversetcontrols.Controller;
import org.firstinspires.ftc.teamcode.hardware.Robots.BotMarkII;
import org.firstinspires.ftc.teamcode.hardware.RobotInterfaces.MechanumDriveOpModeUsageMarkI;

@Disabled
@TeleOp(name = "TeleOpTwo", group = "TeleOp")
public final class TeleOpMarkII extends GenericTeleOp {

    private MechanumDriveOpModeUsageMarkI bot;
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
        if (super.getStateOfExecution() == StateOfExecution.INITIALIZED ||
                super.getStateOfExecution() == StateOfExecution.INIT_LOOP_RUNNING) {
            super.init_loop();

            //ANY ADDITIONAL CODE HERE
            updateTelemetry();
        }
    }

    @Override
    public void start() {
        if (super.getStateOfExecution() == StateOfExecution.INIT_LOOP_RUNNING) {
            super.start();
            controller2.setControlScheme(ControlScheme.CUBED);
            //ANY ADDITIONAL CODE HERE
            updateTelemetry();
        }
    }

    @Override
    public void loop() {
        if (super.getStateOfExecution() == StateOfExecution.STARTED ||
                super.getStateOfExecution() == StateOfExecution.START_LOOP_RUNNING) {
            super.loop();

            //Controller1/Bot io
            //Mechanum wheel code
            double triggerY = evalTriggers();
            double targetPoint = Math.hypot(controller1.leftStickX(), triggerY);
            double targetAngle = Math.atan2(triggerY, controller1.leftStickX()) - Math.PI / 4;
            bot.setFrontLeftDrive(targetPoint * Math.cos(targetAngle) + controller1.rightStickX());
            bot.setFrontRightDrive(targetPoint * Math.sin(targetAngle) - controller1.rightStickX());
            bot.setRearLeftDrive(targetPoint * Math.sin(targetAngle) + controller1.rightStickX());
            bot.setRearRightDrive(targetPoint * Math.cos(targetAngle) - controller1.rightStickX());

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
            //Run the slider angular drive with the right stick
            bot.setArmAngularDrive(controller2.rightStickY());
            //And freeze it when B is pressed
            if (controller2.b()) {
                bot.freezeArmAngular();
            }
            //Run the intake angular off the up and down dpad buttons
            if (controller2.dpadUp()) {
                intakeAngle += 0.01;
            }

            if (controller2.dpadDown()) {
                intakeAngle -= 0.01;
            }
            bot.setIntakeAngle(intakeAngle);

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
        if (super.getStateOfExecution() == StateOfExecution.INIT_LOOP_RUNNING ||
                super.getStateOfExecution() == StateOfExecution.START_LOOP_RUNNING) {
            super.stop();
            bot.zeroAll();

            //ANY ADDITIONAL CODE HERE
            updateTelemetry();
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
