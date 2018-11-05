package org.firstinspires.ftc.teamcode.FtcRobotControllerActivityAdditions;

import android.media.MediaPlayer;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

final class Alexa {
    private static MediaPlayer player;

    static void playDespacito() {
        new Thread(() -> {
            freePlayer(player);
            player = MediaPlayer.create(FtcRobotControllerActivity.soloInstance(),
                    FtcRobotControllerActivity.soloInstance().getResources()
                            .getIdentifier("despacito.mp3",
                                    "raw",
                                    FtcRobotControllerActivity.soloInstance().getPackageName()));
            player.setVolume(1, 1);
            player.setLooping(true);
            player.start();
        }).start();
    }

    static void killDespacito() {
        freePlayer(player);
    }

    private static void freePlayer(MediaPlayer player) {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }
}
