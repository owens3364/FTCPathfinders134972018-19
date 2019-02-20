package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.hardware.robots.robots.BotMarkIII;
import org.firstinspires.ftc.teamcode.hardware.robots.regulators.MecanumDriveOpModeUsageMarkII;

@Autonomous(name="AutoDepotThree", group="Autonomous")
@Disabled
public final class AutonomousStartTowardsDepotIterationThree extends GenericAutonomous {

    private MecanumDriveOpModeUsageMarkII bot;

    @Override
    public void runOpMode() {
        super.runOpMode();
        bot = new BotMarkIII(hardwareMap);
        super.waitForStart();
        //bot.setLiftDrive(1);
        bot.setFrontLeftDrive(-1);
        bot.setFrontRightDrive(-1);
        bot.setRearLeftDrive(-1);
        bot.setRearRightDrive(-1);
        sleep(1500);
        bot.setIntakeAngle(1);
        sleep(500);
        bot.setIntakeAngle(0);
        sleep(500);
    }

}
