package org.firstinspires.ftc.teamcode.opmodes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.BotMarkIII;
import org.firstinspires.ftc.teamcode.hardware.MechanumDriveOpModeUsageMarkII;

@Autonomous(name="AutoDepotThree", group="Autonomous")
public final class AutonomousStartTowardsDepotIterationThree extends GenericAutonomous {

    private MechanumDriveOpModeUsageMarkII bot;

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
