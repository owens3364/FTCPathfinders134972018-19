package org.firstinspires.ftc.teamcode.activity;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
* <h1>TelemetryDisconnectedAlert<h1/>
* This class extends {@code DialogFragment} to create a new DialogFragment, in this case for
* an alert indicating disconnected telemetry.
* @author   Owen Scott
* @version  1.0
* @since    2019-01-14
*/
public final class TelemetryDisconnectedAlert extends DialogFragment {

    /**
    * A {@code String} indicating the tag for this {@code DialogFragment}
    */
    public static final String TELEMETRY_DISCONNECTED_DIALOG = "Disconnected Telemetry Dialog";

    private static final String TITLE = "Oh no! Telemetry is disconnected!";
    private static final String MESSAGE = "Please PRESS STOP, RESTART the robot, QUIT BOTH the " +
            "GenericRobot Controller and Driver Station, and try again.";
    private static final String POSITIVE_BUTTON = "OK";

    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState)
    {
        return FragmentDeploymentHelper.generateAlertDialog(TITLE, MESSAGE, POSITIVE_BUTTON);
    }
}
