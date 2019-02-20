package org.firstinspires.ftc.teamcode.hardware.robots.methods.drivetrainmovements.standard;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class SetRightDriveForMM implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "bot.setRightDriveForMM";

    @Override
    public String[] getArgs() {
        return ARGS;
    }


    @Override
    public String getName() {
        return NAME;
    }

    public SetRightDriveForMM(String[] args) {
        this.ARGS = args;
    }
}
