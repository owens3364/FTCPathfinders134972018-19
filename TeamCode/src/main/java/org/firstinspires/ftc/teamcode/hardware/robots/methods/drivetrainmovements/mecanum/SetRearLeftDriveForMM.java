package org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.mecanum;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class SetRearLeftDriveForMM implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "bot.setRearLeftDriveForMM";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public SetRearLeftDriveForMM(String[] args) {
        this.ARGS = args;
    }
}
