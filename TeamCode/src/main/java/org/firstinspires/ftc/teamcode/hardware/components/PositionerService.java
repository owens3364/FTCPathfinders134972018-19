package org.firstinspires.ftc.teamcode.hardware.components;

import android.os.AsyncTask;
import android.os.Process;

import org.firstinspires.ftc.teamcode.errorIO.LogUtils;
import org.firstinspires.ftc.teamcode.hardware.components.regulators.Positioner;

import java.io.PrintWriter;
import java.io.StringWriter;

final class PositionerService extends AsyncTask<DcMotorWrapper, Void, Void> implements Positioner {

    private boolean positionSelected = false;

    private boolean stopped = false;

    private double power;
    private double targetPosition;

    private DcMotorWrapper primaryMotor;
    private DcMotorWrapper[] auxiliaryMotors;

    @Override
    protected Void doInBackground(DcMotorWrapper[] dcMotorWrappers) {
        try {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND +
                    Process.THREAD_PRIORITY_MORE_FAVORABLE);
            if (initialize(dcMotorWrappers)) {
                initialize();
                run();
            }
        } catch (Exception ex) {
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            LogUtils.logError(sw.toString());
        }
        return null;
    }

    @Override
    public void setPosition(double power, double position) {
        this.power = power;
        this.targetPosition = position;
        positionSelected = true;
    }

    @Override
    public double getPosition() {
        return primaryMotor.getPosition();
    }

    @Override
    public void releasePosition() {
        this.stopped = true;
    }

    private void initialize() {
        primaryMotor.freezeAtZeroPower();
        if (auxiliaryMotors != null) {
            for (DcMotorWrapper auxMotor : auxiliaryMotors) {
                if (auxMotor != null) {
                    auxMotor.freezeAtZeroPower();
                } else {
                    auxiliaryMotors = null;
                    break;
                }
            }
        }
    }

    private boolean initialize(DcMotorWrapper[] dcMotorWrappers) {
        if (dcMotorWrappers != null) {
            if (dcMotorWrappers.length > 0) {
                if (dcMotorWrappers[0] != null) {
                    primaryMotor = dcMotorWrappers[0];
                    if (dcMotorWrappers.length > 1) {
                        auxiliaryMotors = new DcMotorWrapper[dcMotorWrappers.length - 1];
                    }
                    for (int i = 1; i < dcMotorWrappers.length; i++) {
                        if (dcMotorWrappers[i] != null) {
                            auxiliaryMotors[i-1] = dcMotorWrappers[i];
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private void run() {
        while(!stopped) {
            if (positionSelected) {
                primaryMotor.setPosition(power, targetPosition);
                double synchronizedPower = primaryMotor.getPower();
                if (auxiliaryMotors != null) {
                    for (DcMotorWrapper auxiliaryMotor : auxiliaryMotors) {
                        auxiliaryMotor.set(synchronizedPower);
                    }
                }
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                LogUtils.logError(e.getMessage());
            }
        }
        primaryMotor.coastAtZeroPower();
    }
}
