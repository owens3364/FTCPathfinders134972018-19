package org.firstinspires.ftc.teamcode.driversetcontrols;

public final class Scaler {
    //Scales one value to another
    //scale(15, 0, 30, 0, 100) yields 50
    public static double scale(double startVal, double startMin, double startMax, double endMin, double endMax) {
        if (startMin >= startMax || endMin >= endMax || startVal < startMin || startVal > startMax) {
            return 0;
        }
        return ((endMax - endMin) * (startVal - startMin) / (startMax - startMin)) + endMax;
    }
}
