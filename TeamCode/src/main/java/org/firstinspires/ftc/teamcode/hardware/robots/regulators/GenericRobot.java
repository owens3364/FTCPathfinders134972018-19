package org.firstinspires.ftc.teamcode.hardware.robots.regulators;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;
import org.firstinspires.ftc.teamcode.hardware.robots.methods.misc.Delay;

import java.util.LinkedList;

public class GenericRobot {

    private long lastCommandTime = 0;

    protected static final String AUTONOMOUS_GENERATED = "Autonomous Generated: ";

    protected void addCommand(LinkedList<RobotMethod> movements, RobotMethod robotMethod) {
        if (lastCommandTime != 0) {
            movements.add(new Delay(getDelay()));
        }
        movements.add(robotMethod);
        lastCommandTime = System.currentTimeMillis();
    }

    private String[] getDelay() {
        long delay = System.currentTimeMillis() - lastCommandTime;
        lastCommandTime = delay + lastCommandTime;
        return new String[] {
                String.valueOf(delay)
        };
    }
}
