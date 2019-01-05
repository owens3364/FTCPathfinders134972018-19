package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.hardware.BotMarkIII;
import org.firstinspires.ftc.teamcode.hardware.MechanumDriveOpModeUsageMarkII;

@Autonomous(name="RotationsTest", group="Autonomous")
@Disabled
public final class RotationsTest extends GenericAutonomous {
    private static final double POWER = 1;
    private static final double ROTATIONS = 1;


    private MechanumDriveOpModeUsageMarkII bot;

    @Override
    public void runOpMode() {
        super.runOpMode();
        bot = new BotMarkIII(hardwareMap);
        super.waitForStart();
        bot.allMechanumDriveMotors(POWER, ROTATIONS);
        sleep(5000);
    }
}
