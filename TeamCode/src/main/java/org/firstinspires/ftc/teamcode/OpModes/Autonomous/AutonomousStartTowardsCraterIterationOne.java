package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Hardware.BotMarkI;
import org.firstinspires.ftc.teamcode.Hardware.StandardDriveOpModeUsage;

@Autonomous(name = "AutoCraterOne", group = "Autonomous")

public final class AutonomousStartTowardsCraterIterationOne extends GenericAutonomous {

    private StandardDriveOpModeUsage bot;

    @Override
    public void runOpMode() {
        super.runOpMode();
        bot = BotMarkI.initialize(hardwareMap);
        super.waitForStart();
        //Forward four seconds and stop
        bot.setLeftDrive(.5);
        bot.setRightDrive(.5);
        sleep(4000);
        bot.setLeftDrive(0);
        bot.setRightDrive(0);
        //Stop
        bot.zeroAll();
    }
}
