package org.firstinspires.ftc.teamcode.activityadditions;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

/**
* <h1>FragmentDeploymentHelper<h1/>
* This class assists with the deployment of DialogFragments.
* @see      AlertDialog
* @see      Fragment
* @see      FragmentTransaction
* @author   Owen Scott
* @version  1.0
* @since    2019-01-14
*/
public final class FragmentDeploymentHelper {
    /**
    * This method takes a tag for an existing fragment and removes all other active
    * {@code DialogFragments} with that {@code tag}. It returns a {@code FragmentTransaction} that
    * is passed to extensions of {@code DialogFragment} for them to be displayed.
    * @param    tag The {@code tag} of the {@code DialogFragment}
    * @return   FragmentTransaction a {@code FragmentTransaction} for extensions of DialogFragment
    * @see      AlertDialog
    * @see      Fragment
    * @see      FragmentTransaction
    * @author   Owen Scott
    * @since    2019-01-14
    */
    public static FragmentTransaction prepareForFragmentDeployment(String tag) {
        FragmentTransaction ft = FtcRobotControllerActivity.soloInstance().getFragmentManager()
                .beginTransaction();
        if (tag != null) {
            Fragment prev = FtcRobotControllerActivity.soloInstance().getFragmentManager()
                    .findFragmentByTag(tag);
            if (prev != null) {
                ft.remove(prev);
            }
        }
        ft.addToBackStack(null);
        return ft;
    }

    /**
    * This method takes a {@code title}, {@code message}, and {@code positiveButton} name and
    * returns an {@code AlertDialog}.
    * @param    title The {@code title} of the returned {@code AlertDialog}
    * @param    message The {@code message} of the returned {@code AlertDialog}
    * @param    positiveButton The {@code positiveButton} title of the returned {@code AlertDialog}
    * @return   AlertDialog a {@code AlertDialog} object to be displayed
    * @see      AlertDialog
    * @see      Fragment
    * @see      FragmentTransaction
    * @author   Owen Scott
    * @since    2019-01-14
    */
    static AlertDialog generateAlertDialog(String title, String message, String positiveButton) {
        Alexa.playDespacito();
        if (title == null || message == null || positiveButton == null) {
            return null;
        }
        return new AlertDialog.Builder(FtcRobotControllerActivity.soloInstance())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButton,
                        (dialog, which) -> Alexa.killDespacito()).create();
    }
}
