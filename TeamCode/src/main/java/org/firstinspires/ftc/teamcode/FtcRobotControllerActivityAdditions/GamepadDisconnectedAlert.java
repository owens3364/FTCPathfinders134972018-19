package org.firstinspires.ftc.teamcode.FtcRobotControllerActivityAdditions;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.os.Bundle;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

public final class GamepadDisconnectedAlert extends DialogFragment {
    public static final String CONTROLLER_DISCONNECTED_DIALOG = "Disconnected Controller Dialog";

    @Override
    public AlertDialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(FtcRobotControllerActivity.soloInstance).setTitle("Oh no! At least one controller is disconnected!").setMessage("Please PRESS STOP, connect and initialize all controllers, and try again.").setPositiveButton("OK", null).create();
    }
}
