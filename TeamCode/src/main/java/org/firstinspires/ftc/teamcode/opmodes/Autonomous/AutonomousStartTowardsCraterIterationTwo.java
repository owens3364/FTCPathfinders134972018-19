package org.firstinspires.ftc.teamcode.opmodes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.hardware.BotMarkII;
import org.firstinspires.ftc.teamcode.hardware.MechanumDriveOpModeUsage;

@Autonomous(name = "AutoCraterTwo", group = "Autonomous")
public final class AutonomousStartTowardsCraterIterationTwo extends GenericAutonomous {

    private MechanumDriveOpModeUsage bot;

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
        bot.backward(50, 1000);
        //Turn right towards the wall
        bot.turnRight(50, 1000);
        //Move forward towards the wall
        bot.forward(50, 2000);
        //Turn parallel to the wall
        bot.turnLeft(50, 500);
        //Move parallel to the wall towards the depot
        bot.forward(50, 3000);
        //Deposit the team marker in the depot
        bot.openArmLid();
        //Back into the crater
        bot.backward(50, 6000);
        //Stop
        bot.zeroAll();
    }
}
