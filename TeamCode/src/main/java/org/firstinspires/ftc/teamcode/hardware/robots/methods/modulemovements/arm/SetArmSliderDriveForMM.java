package org.firstinspires.ftc.teamcode.hardware.robots.methods.modulemovements.arm;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

public final class SetArmSliderDriveForMM implements RobotMethod {

    private final String[] ARGS;
    private static final String NAME = "bot.setArmSliderDriveForMM";

    @Override
    public String[] getArgs() {
        return ARGS;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public SetArmSliderDriveForMM(String[] args) {
        this.ARGS = args;
    }
}
