package org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class SetArmSliderDrive implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "bot.setArmSliderDrive";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public SetArmSliderDrive(String[] args) {
        this.ARGS = args;
    }
}
