package org.firstinspires.ftc.teamcode.vision;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public final class VisionData {


    public VisionData(VectorF pos, Orientation ori, VuMarkType vuMarkType) {
        this.position = pos;
        this.orientation = ori;
        this.vuMarkType = vuMarkType;
    }

    private final VectorF position;
    private final Orientation orientation;
    private final VuMarkType vuMarkType;

    public VectorF getPosition() {
        return position;
    }

    public float getX() {
        return position.get(0);
    }

    public float getY() {
        return position.get(1);
    }

    public float getZ() {
        return position.get(2);
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public float getAngle1() {
        return orientation.firstAngle;
    }

    public float getAngle2() {
        return orientation.secondAngle;
    }

    public float getAngle3() {
        return orientation.thirdAngle;
    }

    public VuMarkType getVuMarkType() {
        return vuMarkType;
    }

    @Override
    public String toString() {
        return "VuMark Type: " + vuMarkType.name() +
                "X: " + position.get(0) +
                ", Y: " + position.get(1) +
                ", Z: " + position.get(2) +
                ", Angle One: " + orientation.firstAngle +
                ", Angle Two: " + orientation.secondAngle +
                ", Angle Three: " + orientation.thirdAngle;
    }
}
