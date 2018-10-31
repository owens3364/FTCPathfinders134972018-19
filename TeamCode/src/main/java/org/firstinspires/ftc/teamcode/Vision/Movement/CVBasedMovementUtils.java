package org.firstinspires.ftc.teamcode.Vision.Movement;

import org.firstinspires.ftc.teamcode.Vision.Vuforia.VuforiaData;

import java.util.ArrayList;
import java.util.List;

public final class CVBasedMovementUtils {
    private static final double MM_TO_IN = 0.0393701;
    private static final double TURNING_CIRCUMFERENCE = 51.64; //IN INCHES
    private static final double MAX_TURNING_POSITION = 12.91;
    private static final double INCHES_PER_ROTATION = 1; //TODO: FIND THIS
    

    public static MovementStep[] getMovementSteps(VuforiaData pose) {
        //Ensures method is possible, otherwise returns null
        if (pose == null) {
            return null;
        }
        return generateMovementSteps(pose);
    }

    private static MovementStep[] generateMovementSteps(VuforiaData pose) {
        List<MovementStep> steps = new ArrayList<>();
        if (pose.position.get(0) != 0) {
            steps.add(adjustAngleForX(pose.position.get(0) * MM_TO_IN, pose.position.get(2) * MM_TO_IN));
        }
        if (pose.position.get(2) != 0) {
            steps.add(adjustRotationsForZ(pose.position.get(2) * MM_TO_IN));
        }
        return steps.toArray(new MovementStep[0]);
    }

    private static MovementStep adjustAngleForX(double x, double z) {
        return calculateMovementStepFromAngle(Math.toDegrees(Math.asin(x / Math.hypot(x, z))));
    }

    private static MovementStep calculateMovementStepFromAngle(double angle) {
        int position = (int) Math.round(angle * TURNING_CIRCUMFERENCE / 360); //Proportion of angle over 360 = desiredPosition / circumference
        double power = position * 50 / MAX_TURNING_POSITION;
        position /= INCHES_PER_ROTATION;
        return new MovementStep(power, position, -power, -position);
    }

    private static MovementStep adjustRotationsForZ(double z) {
        int rotations = (int) Math.round(z / INCHES_PER_ROTATION);
        double power;
        if (z < 12) {
            power = 1/8;
        } else if (z < 24) {
            power = 1/4;
        } else {
            power = 1/2;
        }
        return new MovementStep(power, rotations, power, rotations);
    }
}
