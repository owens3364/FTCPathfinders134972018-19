package org.firstinspires.ftc.teamcode.activityadditions;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.os.Bundle;

public final class TelemetryDisconnectedAlert extends DialogFragment {
    public static final String TELEMETRY_DISCONNECTED_DIALOG = "Disconnected Telemetry Dialog";

    private static final String TITLE = "Oh no! Telemetry is disconnected!";
    private static final String MESSAGE = "Please PRESS STOP, RESTART the robot, QUIT BOTH the " +
            "Robot Controller and Driver Station, and try again.";
    private static final String POSITIVE_BUTTON = "OK";

    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState)
    {
        return FragmentDeploymentHelper.generateAlertDialog(TITLE, MESSAGE, POSITIVE_BUTTON);
    }
}
