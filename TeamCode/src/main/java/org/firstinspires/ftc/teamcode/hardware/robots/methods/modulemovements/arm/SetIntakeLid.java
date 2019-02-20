package org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class SetIntakeLid implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "bot.setIntakeLid";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public SetIntakeLid(String[] args) {
        this.ARGS = args;
    }
}
