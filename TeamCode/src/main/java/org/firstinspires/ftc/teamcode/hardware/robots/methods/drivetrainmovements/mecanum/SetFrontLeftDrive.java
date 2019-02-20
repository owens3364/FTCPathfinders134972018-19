package org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class SetFrontLeftDrive implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "bot.setFrontLeftDrive";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public SetFrontLeftDrive(String[] args) {
        this.ARGS = args;
    }
}
