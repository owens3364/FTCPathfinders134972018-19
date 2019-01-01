package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.hardware.BotMarkII;
import org.firstinspires.ftc.teamcode.hardware.MechanumDriveOpModeUsageMarkI;

@Disabled
@Autonomous(name = "AutoDepotTwo", group = "Autonomous")
public final class AutonomousStartTowardsDepotIterationTwo extends GenericAutonomous {

    private MechanumDriveOpModeUsageMarkI bot;

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
        bot.backward(50, 1000);
        //Turn 180 right towards the depot
        bot.turnRight(50, 2000);
        //Forward into the depot
        bot.forward(50, 4000);
        //Deposit the team marker in the depot
        bot.openArmLid();
        //Stop
        bot.zeroAll();
    }
}
