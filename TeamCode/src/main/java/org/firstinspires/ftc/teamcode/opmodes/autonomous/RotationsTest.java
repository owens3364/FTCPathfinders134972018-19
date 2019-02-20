package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.hardware.robots.robots.BotMarkIII;
import org.firstinspires.ftc.teamcode.hardware.robots.regulators.MecanumDriveOpModeUsageMarkII;

@Autonomous(name="RotationsTest", group="Autonomous")
@Disabled
public final class RotationsTest extends GenericAutonomous {
    private static final double POWER = 1;
    private static final double ROTATIONS = 1;


    private MecanumDriveOpModeUsageMarkII bot;

    @Override
    public void runOpMode() {
        super.runOpMode();
        bot = new BotMarkIII(hardwareMap);
        super.waitForStart();
        bot.allMecanumDriveMotors(POWER, ROTATIONS);
        sleep(5000);
    }
}
