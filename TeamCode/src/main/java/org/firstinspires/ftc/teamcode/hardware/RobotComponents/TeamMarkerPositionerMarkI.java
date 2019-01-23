package org.firstinspires.ftc.teamcode.hardware.RobotComponents;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public final class TeamMarkerPositionerMarkI {
    private final Servo marker;

    public TeamMarkerPositionerMarkI(HardwareMap map, String markerName) {
        marker = map.get(Servo.class, markerName);
    }

    //Dump team marker and return to original position
    public void dump() {
        new Thread(() -> {
            marker.setPosition(Servo.MAX_POSITION);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            marker.setPosition(Servo.MIN_POSITION);
        }).start();
    }
}
