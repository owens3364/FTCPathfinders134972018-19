package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.DriverSetControls.Controller;
import org.firstinspires.ftc.teamcode.Hardware.BotMarkII;
import org.firstinspires.ftc.teamcode.Hardware.MechanumDriveOpModeUsage;


@TeleOp(name = "TeleOpTwo", group = "TeleOp")
public final class TeleOpMarkII extends GenericTeleOp {

    private MechanumDriveOpModeUsage bot;
    private Controller controller1;
    private Controller controller2;

    @Override
    public void init() {
        if (super.setup(gamepad1, gamepad2, telemetry)) {
            super.init();
            controller1 = super.getController1();
            controller2 = super.getController2();
            bot = BotMarkII.initialize(hardwareMap);

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

            //ANY ADDITIONAL CODE HERE
            updateTelemetry();
        }
    }

    @Override
    public void loop() {
        if (super.isStarted()) {
            super.loop();
            
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
}
