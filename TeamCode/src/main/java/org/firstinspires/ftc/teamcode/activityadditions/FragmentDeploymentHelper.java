package org.firstinspires.ftc.teamcode.activityadditions;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

public final class FragmentDeploymentHelper {
    public static FragmentTransaction prepareForFragmentDeployment(String tag) {
        FragmentTransaction ft = FtcRobotControllerActivity.soloInstance().getFragmentManager()
                .beginTransaction();
        Fragment prev = FtcRobotControllerActivity.soloInstance().getFragmentManager()
                .findFragmentByTag(tag);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        return ft;
    }

    static AlertDialog generateAlertDialog(String title, String message, String positiveButton) {
        Alexa.playDespacito();
        return new AlertDialog.Builder(FtcRobotControllerActivity.soloInstance())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButton, (dialog, which) -> Alexa.killDespacito()).create();
    }
}
