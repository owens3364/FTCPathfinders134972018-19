package org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.standard;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class SetRightDriveForRotations implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "bot.setRightDriveForRotations";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public SetRightDriveForRotations(String[] args) {
        this.ARGS = args;
    }
}
