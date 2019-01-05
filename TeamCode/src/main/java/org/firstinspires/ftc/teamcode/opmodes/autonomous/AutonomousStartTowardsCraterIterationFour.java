package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.BotMarkIII;
import org.firstinspires.ftc.teamcode.hardware.MechanumDriveOpModeUsageMarkII;
@Autonomous(name = "AutoCraterOne", group = "Autonomous")
public final class AutonomousStartTowardsCraterIterationFour extends GenericAutonomous {

    private MechanumDriveOpModeUsageMarkII bot;

    @Override
    public void runOpMode() {
        super.runOpMode();
        bot = new BotMarkIII(hardwareMap);
        bot.setIntakeAngle(1.0);
        super.waitForStart();
        land(bot);
        bot.allMechanumDriveMotors(1, 1400);
    }
}
