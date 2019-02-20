package org.firstinspires.ftc.teamcode.hardware.robots.regulators;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

import java.util.List;

interface Robot {
    boolean createConfigurationFile();
    boolean createConfigurationFile(String name);

    boolean createAutonomous(List<RobotMethod> movements);
    List<RobotMethod> getMovementsForAutonomous();
    void trackMovementsForAutonomous();
}
