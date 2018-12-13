package org.firstinspires.ftc.teamcode.activityadditions;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.os.Bundle;

public final class GamepadDisconnectedAlert extends DialogFragment {
    public static final String CONTROLLER_DISCONNECTED_DIALOG = "Disconnected Controller Dialog";

    private static final String TITLE = "Oh no! At least one controller is disconnected!";
    private static final String MESSAGE = "Please PRESS STOP, reconnect and initialize all controllers, and try again.";
    private static final String POSITIVE_BUTTON = "OK";

    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState)
    {
        return FragmentDeploymentHelper.generateAlertDialog(TITLE, MESSAGE, POSITIVE_BUTTON);
    }
}
