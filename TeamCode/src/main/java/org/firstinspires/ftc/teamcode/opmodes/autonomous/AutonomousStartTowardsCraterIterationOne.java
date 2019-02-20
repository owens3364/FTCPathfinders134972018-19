package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.hardware.robots.robots.BotMarkI;
import org.firstinspires.ftc.teamcode.hardware.robots.regulators.StandardDriveOpModeUsageMarkI;

@Autonomous(name = "AutoCraterOne", group = "Autonomous")
@Disabled
public final class AutonomousStartTowardsCraterIterationOne extends GenericAutonomous {

    private StandardDriveOpModeUsageMarkI bot;

    @Override
    public void runOpMode() {
        super.runOpMode();
        bot = new BotMarkI(hardwareMap);
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
