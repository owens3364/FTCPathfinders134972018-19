package org.firstinspires.ftc.teamcode.activityadditions;

import android.media.MediaPlayer;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.teamcode.R;

final class Alexa {

    private static final float MAX_VOLUME = 1.0f;

    private static MediaPlayer player;

    static void playDespacito() {
        new Thread(() -> {
            killDespacito();
            player = MediaPlayer.create(FtcRobotControllerActivity.soloInstance(),
                    R.raw.despacito);
            player.setVolume(MAX_VOLUME, MAX_VOLUME);
            player.setLooping(true);
            player.start();
        }).start();
    }

    static void killDespacito() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
