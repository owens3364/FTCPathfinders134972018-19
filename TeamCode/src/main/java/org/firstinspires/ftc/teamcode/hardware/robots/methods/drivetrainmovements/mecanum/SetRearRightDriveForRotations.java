package org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class SetRearRightDriveForRotations implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "bot.setRearRightDriveForRotations";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public SetRearRightDriveForRotations(String[] args) {
        this.ARGS = args;
    }
}
