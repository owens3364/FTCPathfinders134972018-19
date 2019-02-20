package org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class DriveMotorsBySticks implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "bot.driveMotorsBySticks";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public DriveMotorsBySticks(String[] args) {
        this.ARGS = args;
    }
}
