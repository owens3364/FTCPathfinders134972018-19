package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.robots.regulators.MecanumDriveOpModeUsageMarkIII;
import org.firstinspires.ftc.teamcode.hardware.robots.robots.BotMarkIV;

@Autonomous(name="AutoDepotFive", group="Autonomous")
public class AutonomousStartTowardsDepotIterationFive extends GenericAutonomous {

    MecanumDriveOpModeUsageMarkIII bot;

    @Override
    public void runOpMode() {
        super.runOpMode();
        bot = new BotMarkIV(hardwareMap);
        bot.setIntakeAngle(1.0);
        super.waitForStart();
        land(bot);
        bot.allMecanumDriveMotors(1, 1400);
        sleep(8000);
        bot.deployMarker();
        sleep(1000);
        bot.zeroAll();
    }
}
