package org.firstinspires.ftc.teamcode.FtcRobotControllerActivityAdditions;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.os.Bundle;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

public final class TelemetryDisconnectedAlert extends DialogFragment {
    public static final String TELEMETRY_DISCONNECTED_DIALOG = "Disconnected Telemetry Dialog";

    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(FtcRobotControllerActivity.soloInstance).setTitle("Oh no! Telemetry is disconnected!").setMessage("Please PRESS STOP, RESTART the robot, QUIT BOTH the Robot Controller and Driver Station, and try again.").setPositiveButton("OK", null).create();
    }
}
