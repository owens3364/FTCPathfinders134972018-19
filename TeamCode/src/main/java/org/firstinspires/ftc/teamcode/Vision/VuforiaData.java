package org.firstinspires.ftc.teamcode.Vision;

import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

final class VuforiaData {
    public VectorF position = null;
    public Orientation orientation = null;
    VuforiaData(VectorF pos, Orientation ori) {
        this.position = pos;
        this.orientation = ori;
    }
}
