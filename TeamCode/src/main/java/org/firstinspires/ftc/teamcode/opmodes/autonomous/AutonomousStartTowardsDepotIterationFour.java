package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.hardware.robots.robots.BotMarkIII;
import org.firstinspires.ftc.teamcode.hardware.robots.regulators.MecanumDriveOpModeUsageMarkII;

@Disabled
@Autonomous(name = "AutoDepotOne", group = "Autonomous")
public final class AutonomousStartTowardsDepotIterationFour extends GenericAutonomous {

    private MecanumDriveOpModeUsageMarkII bot;

    @Override
    public void runOpMode() {
        super.runOpMode();
        bot = new BotMarkIII(hardwareMap);
        bot.setIntakeAngle(1.0);
        super.waitForStart();
        land(bot);
        bot.allMecanumDriveMotors(1, 1400);
        sleep(8000);
    }

}
