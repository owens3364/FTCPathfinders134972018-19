package org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class SetRearLeftDriveForRotations implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "bot.setRearLeftDriveForRotations";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public SetRearLeftDriveForRotations(String[] args) {
        this.ARGS = args;
    }
}
