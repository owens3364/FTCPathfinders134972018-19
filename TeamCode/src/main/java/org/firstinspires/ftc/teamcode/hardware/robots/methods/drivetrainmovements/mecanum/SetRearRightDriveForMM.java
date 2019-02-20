package org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class SetRearRightDriveForMM implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "bot.setRearRightDriveForMM";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public SetRearRightDriveForMM(String[] args) {
        this.ARGS = args;
    }
}
