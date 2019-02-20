package org.firstinspires.ftc.teamcode.activity;

import android.media.MediaPlayer;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.teamcode.R;

/**
* <h1>Alexa<h1/>
* This class plays and stops "Despacito".
* @author   Owen Scott
* @version  1.0
* @since    2019-01-14
*/
final class Alexa {

    private static final float MAX_VOLUME = 1.0f;

    private static MediaPlayer player;

    /**
    * This creates a new Thread.
    * The new thread creates a android.media.MediaPlayer instance and starts playing
    * "Despacito" on loop at full volume. Do not worry about calling this method too many times or
    * not calling {@code killDespacito()}.
    * @see      MediaPlayer
    * @see      {@link #killDespacito()}
    * @author   Owen Scott
    * @since    2019-01-14
    */
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

    /**
    * This stops "Despacito". Do not worry about calling this method too many times or not calling
    * {@code playDespacito()}.
    * @see      MediaPlayer
    * @see      playDespacito
    * @author   Owen Scott
    * @since    2019-01-14
    */
    static void killDespacito() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
