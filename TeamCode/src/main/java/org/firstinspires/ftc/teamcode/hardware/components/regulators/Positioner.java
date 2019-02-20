package org.firstinspires.ftc.teamcode.hardware.components.regulators;

public interface Positioner {
    void setPosition(double power, double position);
    double getPosition();
    void releasePosition();
}
