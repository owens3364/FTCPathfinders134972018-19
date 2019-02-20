package org.firstinspires.ftc.teamcode.hardware.components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardware.components.regulators.Component;
import org.firstinspires.ftc.teamcode.xmlIO.XMLUtils;

public final class TeamMarkerPositionerMarkI implements Component {
    private final ServoWrapper marker;
    private final String markerName;
    private final boolean markerIsPrimary;
    private final String markerPort;

    public TeamMarkerPositionerMarkI(HardwareMap map, String markerName, boolean markerIsPrimary,
                                     String markerPort, org.firstinspires.ftc.teamcode.hardware
                                             .configuration.devices
                                             .Servo markerType) {
        marker = new ServoWrapper(map, markerName, Servo.Direction.FORWARD, markerType);
        this.markerName = markerName;
        this.markerIsPrimary = markerIsPrimary;
        this.markerPort = markerPort;
    }

    //Dump team marker and return to original position
    public void dump() {
        new Thread(() -> {
            marker.set(Servo.MAX_POSITION);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            marker.set(Servo.MIN_POSITION);
        }).start();
    }

    @Override
    public ElementWrapper[] getHardwareDeviceElements() {
        return new ElementWrapper[] {
                new ElementWrapper(markerIsPrimary, XMLUtils.generateHardwareElement(
                        marker.getServoType(), markerName, markerPort
                ))
        };
    }
}
