package org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class SetRearRightDrive implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "bot.setRearRightDrive";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public SetRearRightDrive(String[] args) {
        this.ARGS = args;
    }
}
