package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

final class TeamMarkerPositioner {
    private Servo marker;

    static TeamMarkerPositioner initialize(HardwareMap map, String markerName) {
        Servo marker = map.get(Servo.class, markerName);
        return new TeamMarkerPositioner(marker);
    }

    private TeamMarkerPositioner(Servo marker) {
        this.marker = marker;
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
