package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

final class TeamMarkerPositioner {
    //TeamMarkerPositioner Servo
    private static Servo marker;

    //Bot use methods
    static void initialize(HardwareMap map) {
        marker = map.get(Servo.class, "Team-Marker-Positioner");
    }

    //Dump team marker and return to original position
    static void dump() {
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
