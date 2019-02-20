package org.firstinspires.ftc.teamcode.hardware.robots.methods.misc;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class Delay implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "sleep";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Delay(String[] args) {
        this.ARGS = args;
    }
}
