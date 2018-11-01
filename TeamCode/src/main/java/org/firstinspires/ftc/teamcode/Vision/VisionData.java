package org.firstinspires.ftc.teamcode.Vision;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public final class VisionData {
    VisionData(VectorF pos, Orientation ori) {
        this.position = pos;
        this.orientation = ori;
    }
    public VectorF position;
    public Orientation orientation;

    @Override
    public String toString() {
        return "X: " + position.get(0) +
                ", Y: " + position.get(1) +
                ", Z: " + position.get(2) +
                ", Angle One: " + orientation.firstAngle +
                ", Angle Two: " + orientation.secondAngle +
                ", Angle Three: " + orientation.thirdAngle;
    }
}
