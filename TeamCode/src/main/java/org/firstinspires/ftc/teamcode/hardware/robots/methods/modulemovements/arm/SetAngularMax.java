package org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class SetAngularMax implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "bot.setAngularMax";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public SetAngularMax(String[] args) {
        this.ARGS = args;
    }
}
