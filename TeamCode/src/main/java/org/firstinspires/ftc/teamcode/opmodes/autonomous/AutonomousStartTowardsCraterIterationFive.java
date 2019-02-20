package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.hardware.robots.regulators.MecanumDriveOpModeUsageMarkIII;
import org.firstinspires.ftc.teamcode.hardware.robots.robots.BotMarkIV;

@Autonomous(name="AutoCraterFive", group="Autonomous")
public class AutonomousStartTowardsCraterIterationFive extends GenericAutonomous {

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
        bot.zeroAll();
    }
}
