package org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class SetAngularMin implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "bot.setAngularMin";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public SetAngularMin(String[] args) {
        this.ARGS = args;
    }
}
