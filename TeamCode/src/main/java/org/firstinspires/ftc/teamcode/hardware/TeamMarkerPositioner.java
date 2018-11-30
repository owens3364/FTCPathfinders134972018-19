package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

final class TeamMarkerPositioner {
    private final Servo marker;

    TeamMarkerPositioner(HardwareMap map, String markerName) {
        marker = map.get(Servo.class, markerName);
    }

    //Dump team marker and return to original position
    void dump() {
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
