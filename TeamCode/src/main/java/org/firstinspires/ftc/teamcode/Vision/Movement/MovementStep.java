package org.firstinspires.ftc.teamcode.Vision.Movement;

final class MovementStep {
    private double leftTreadPower;
    private int leftTreadToPosition;
    private double rightTreadPower;
    private int rightTreadToPosition;

    MovementStep(double leftTreadPower, int leftTreadToPosition, double rightTreadPower, int rightTreadToPosition) {
        this.leftTreadPower = leftTreadPower;
        this.leftTreadToPosition = leftTreadToPosition;
        this.rightTreadPower = rightTreadPower;
        this.rightTreadToPosition = rightTreadToPosition;
    }

    public double getLeftTreadPower() {
        return leftTreadPower;
    }

    public int getLeftTreadToPosition() {
        return leftTreadToPosition;
    }

    public double getRightTreadPower() {
        return rightTreadPower;
    }

    public int getRightTreadToPosition() {
        return rightTreadToPosition;
    }
}
