package org.firstinspires.ftc.teamcode.Vision;

import java.util.LinkedList;

final class VuforiaCheckerThread extends Thread {
    public boolean stopped;
    private long checkAtIntervalsOf;
    public LinkedList<VuforiaData> dataOut = null;
    public VuforiaData finalData = null;
    private CVCompatibleClass caller;

    VuforiaCheckerThread(long checkingInterval, OutputType outputType, CVCompatibleClass caller) {
        this.stopped = false;
        this.checkAtIntervalsOf = checkingInterval;
        if (outputType == OutputType.STREAM) {
            dataOut = new LinkedList<>();
        }
        this.caller = caller;
    }
    @Override
    public void run() {
        while (!stopped) {
            if (dataOut != null) {
                dataOut.add(VuforiaUtils.checkForTargets());
            } else {
                VuforiaData data = VuforiaUtils.checkForTargets();
                if (data != null) {
                    finalData = data;
                    stopped = true;
                }
            }
            try {
                Thread.sleep(checkAtIntervalsOf);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        if (dataOut != null) {
            caller.setStream(dataOut);
        } else {
            caller.setData(finalData);
        }
    }
}
