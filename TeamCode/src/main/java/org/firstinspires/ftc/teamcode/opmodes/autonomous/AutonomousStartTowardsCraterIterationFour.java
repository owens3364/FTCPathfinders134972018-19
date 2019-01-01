package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import org.firstinspires.ftc.teamcode.hardware.BotMarkIII;
import org.firstinspires.ftc.teamcode.hardware.MechanumDriveOpModeUsageMarkII;

public final class AutonomousStartTowardsCraterIterationFour extends GenericAutonomous {

    private MechanumDriveOpModeUsageMarkII bot;

    @Override
    public void runOpMode() {
        super.runOpMode();
        bot = new BotMarkIII(hardwareMap);
        super.waitForStart();
        land(bot);
        approachAllianceVuMark(bot, StartPosition.CRATER);
        setHomePos(bot, StartPosition.CRATER);
    }
}
