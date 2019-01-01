package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.hardware.BotMarkII;
import org.firstinspires.ftc.teamcode.hardware.Direction;
import org.firstinspires.ftc.teamcode.hardware.MechanumDriveOpModeUsageMarkI;

@Disabled
@Autonomous(name = "AutoCraterTwo", group = "Autonomous")
public final class AutonomousStartTowardsCraterIterationTwo extends GenericAutonomous {

    private MechanumDriveOpModeUsageMarkI bot;

    @Override
    public void runOpMode() {
        super.runOpMode();
        bot = new BotMarkII(hardwareMap);
        //Stay hanging on the lander
        //bot.freezeLift();
        super.waitForStart();
        //Descend from lander (less than gracefully)
        bot.coastLift();
        //Move backward from the lander a bit
        bot.allMechanumDriveMotors(-.5, 1000);
        //Turn right towards the wall
        bot.turn(Direction.LEFT, .5, 1000);
        //Move forward towards the wall
        bot.allMechanumDriveMotors(.5, 2000);
        //Turn parallel to the wall
        bot.turn(Direction.LEFT, .5, 500);
        //Move parallel to the wall towards the depot
        bot.allMechanumDriveMotors(.5, 3000);
        //Deposit the team marker in the depot
        bot.openArmLid();
        //Back into the crater
        bot.allMechanumDriveMotors(-.5, 6000);
        //Stop
        bot.zeroAll();
    }
}
