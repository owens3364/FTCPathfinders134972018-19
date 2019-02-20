package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.hardware.robots.robots.BotMarkI;
import org.firstinspires.ftc.teamcode.hardware.robots.regulators.StandardDriveOpModeUsageMarkI;

@Autonomous(name = "AutoDepotOne", group = "Autonomous")
@Disabled
public final class AutonomousStartTowardsDepotIterationOne extends GenericAutonomous {

    private StandardDriveOpModeUsageMarkI bot;

    @Override
    public void runOpMode() {
        super.init();
        bot = new BotMarkI(hardwareMap);
        super.waitForStart();
        //Forward four seconds and stop
        bot.setLeftDrive(-.5);
        bot.setRightDrive(-.5);
        sleep(4500);
        bot.setLeftDrive(0);
        bot.setRightDrive(0);
        //Release team marker
        bot.setIntakeDrive(1);
        sleep(500);
        bot.setIntakeDrive(0);
        //Backward two seconds and stop
        bot.setLeftDrive(.5);
        bot.setRightDrive(.5);
        sleep(2500);
        bot.setLeftDrive(0);
        bot.setRightDrive(0);
        //Turn 100 degrees left
        bot.setLeftDrive(-1);
        bot.setRightDrive(1);
        sleep(1250);
        bot.setLeftDrive(0);
        bot.setRightDrive(0);
        //Park in crater
        bot.setLeftDrive(-.5);
        bot.setRightDrive(-.5);
        sleep(5000);
        bot.setLeftDrive(0);
        bot.setRightDrive(0);
        //Stop
        bot.zeroAll();
    }
}
