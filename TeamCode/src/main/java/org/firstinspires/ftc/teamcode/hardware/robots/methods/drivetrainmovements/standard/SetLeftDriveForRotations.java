package org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.standard;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class SetLeftDriveForRotations implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "bot.setLeftDriveForRotations";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public SetLeftDriveForRotations(String[] args) {
        this.ARGS = args;
    }
}
