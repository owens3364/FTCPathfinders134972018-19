package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.hardware.robots.robots.BotMarkII;
import org.firstinspires.ftc.teamcode.hardware.components.Direction;
import org.firstinspires.ftc.teamcode.hardware.robots.regulators.MecanumDriveOpModeUsageMarkI;

@Disabled
@Autonomous(name = "AutoDepotTwo", group = "Autonomous")
public final class AutonomousStartTowardsDepotIterationTwo extends GenericAutonomous {

    private MecanumDriveOpModeUsageMarkI bot;

    @Override
    public void runOpMode() {
        super.runOpMode();
        bot = new BotMarkII(hardwareMap);
        //Stay hanging on lander
        //bot.freezeLift();
        super.waitForStart();
        //Descend from lander (less than gracefully)
        bot.coastLift();
        //Move backward from the lander a bit
        bot.allMecanumDriveMotors(-.5, 1000);
        //Turn 180 right towards the depot
        bot.turn(Direction.RIGHT, .5, 2000);
        //Forward into the depot
        bot.allMecanumDriveMotors(.5, 4000);
        //Deposit the team marker in the depot
        bot.openArmLid();
        //Stop
        bot.zeroAll();
    }
}
