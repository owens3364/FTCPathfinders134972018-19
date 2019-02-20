package org.firstinspires.ftc.teamcode.activity;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
* <h1>GamepadDisconnectedAlert<h1/>
* This class extends {@code DialogFragment} to create a new DialogFragment, in this case for
* an alert indicating a disconnected gamepad.
* @author   Owen Scott
* @version  1.0
* @since    2019-01-14
*/
public final class GamepadDisconnectedAlert extends DialogFragment {

    /**
    * A {@code String} indicating the tag for this {@code DialogFragment}
    */
    public static final String CONTROLLER_DISCONNECTED_DIALOG = "Disconnected Controller Dialog";

    private static final String TITLE = "Oh no! At least one controller is disconnected!";
    private static final String MESSAGE = "Please PRESS STOP, reconnect and initialize all " +
            "controllers, and try again.";
    private static final String POSITIVE_BUTTON = "OK";

    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState)
    {
        return FragmentDeploymentHelper.generateAlertDialog(TITLE, MESSAGE, POSITIVE_BUTTON);
    }
}
