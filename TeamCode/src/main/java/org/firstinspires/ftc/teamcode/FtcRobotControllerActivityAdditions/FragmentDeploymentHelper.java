package org.firstinspires.ftc.teamcode.FtcRobotControllerActivityAdditions;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

public final class FragmentDeploymentHelper {
    public static FragmentTransaction prepareForFragmentDeployment(String tag) {
        alexaPlayDespacito();
        FragmentTransaction ft = FtcRobotControllerActivity.soloInstance().getFragmentManager().beginTransaction();
        Fragment prev = FtcRobotControllerActivity.soloInstance().getFragmentManager().findFragmentByTag(tag);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        return ft;
    }

    private static void alexaPlayDespacito() {
        new Thread(() -> {
            AudioManager volumeControl = (AudioManager) FtcRobotControllerActivity.soloInstance().getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            volumeControl.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.);
            MediaPlayer player = MediaPlayer.create(FtcRobotControllerActivity.soloInstance(), Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/Music/Despacito.mp3"));
            player.setLooping(true);
            player.start();
        }).start();
    }
}
