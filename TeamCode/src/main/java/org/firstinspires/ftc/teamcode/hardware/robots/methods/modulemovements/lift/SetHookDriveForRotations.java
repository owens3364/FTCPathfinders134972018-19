package org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.lift;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class SetHookDriveForRotations implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "bot.setHookDriveForRotations";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public SetHookDriveForRotations(String[] args) {
        this.ARGS = args;
    }
}
