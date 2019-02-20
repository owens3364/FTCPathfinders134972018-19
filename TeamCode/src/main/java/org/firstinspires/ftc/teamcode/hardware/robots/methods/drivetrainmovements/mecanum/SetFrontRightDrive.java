package org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class SetFrontRightDrive implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "bot.setFrontRightDrive";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public SetFrontRightDrive(String[] args) {
        this.ARGS = args;
    }
}
