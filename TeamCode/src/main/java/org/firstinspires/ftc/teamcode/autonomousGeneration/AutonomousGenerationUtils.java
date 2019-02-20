package org.firstinspires.ftc.teamcode.autonomousGeneration;

import org.firstinspires.ftc.teamcode.hardware.robots.methods.RobotMethod;

import java.util.List;

public final class AutonomousGenerationUtils {
    private static final String ARGUMENT_SEPARATOR = ", ";
    private static final String CLOSE_PARENTHESIS = ")";
    private static final String LINE_ENDING = ";";
    private static final String NEW_LINE_CHAR = System.lineSeparator();
    private static final String OPEN_PARENTHESIS = "(";

    public static String generateAutonomous(List<RobotMethod> robotMethods) {
        StringBuilder autonomous = new StringBuilder();
        if (robotMethods != null && robotMethods.size() > 0) {
            for (RobotMethod robotMethod : robotMethods) {
                if (robotMethod != null) {
                    if (robotMethod.getName() != null && !robotMethod.getName().isEmpty()) {
                        autonomous.append(insertArguments(robotMethod.getName(),
                                robotMethod.getArgs()));
                    }
                }
            }
            if (!autonomous.toString().isEmpty()) {
                return autonomous.toString();
            }
        }
        return null;
    }

    private static String insertArguments(String methodCall, String[] args) {
        StringBuilder methodCallWithArgs = new StringBuilder(methodCall);
        methodCallWithArgs.append(OPEN_PARENTHESIS);
        if (args != null && args.length > 0) {
            if (args.length == 1) {
                methodCallWithArgs.append(args[0]);
            } else {
                for (int i = 0; i < args.length - 1; i++) {
                    if (args[i] != null && !args[i].isEmpty()) {
                        methodCallWithArgs.append(args[i]);
                        methodCallWithArgs.append(ARGUMENT_SEPARATOR);
                    }
                }
                if (args[args.length - 1] != null && !args[args.length - 1].isEmpty()) {
                    methodCallWithArgs.append(args[args.length - 1]);
                }
            }
        }
        methodCallWithArgs.append(CLOSE_PARENTHESIS);
        methodCallWithArgs.append(LINE_ENDING);
        methodCallWithArgs.append(NEW_LINE_CHAR);
        return methodCallWithArgs.toString();
    }
}
