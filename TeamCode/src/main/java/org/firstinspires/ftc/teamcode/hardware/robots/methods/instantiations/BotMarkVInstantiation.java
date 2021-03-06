package org.firstinspires.ftc.teamcode.hardware.robots.methods.instantiations;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class BotMarkVInstantiation implements RobotMethod {

    private static final String[] ARGS = {"hardwareMap"};
    private static final String NAME = "bot = new BotMarkV";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
