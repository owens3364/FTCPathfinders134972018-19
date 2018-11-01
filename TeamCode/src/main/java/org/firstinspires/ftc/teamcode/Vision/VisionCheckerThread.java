package org.firstinspires.ftc.teamcode.Vision;

import java.util.LinkedList;

final class VisionCheckerThread extends Thread {
    private boolean stopped = false;
    private long checkAtIntervalsOf;
    private CVCompatibleClass caller;
    private LinkedList<VisionData[]> vuforiaDataOut = null;
    private VisionData[] vuforiaFinalData = null;
    private LinkedList<GoldMineralPosition> tfodDataOut = null;
    private GoldMineralPosition tfodFinalData = null;

    VisionCheckerThread(long checkingInterval, CVCompatibleClass caller, VisionCheckerOutputType outputType) {
        this.checkAtIntervalsOf = checkingInterval;
        this.caller = caller;
        switch (outputType) {
            case VUFORIASTREAM:
                vuforiaDataOut = new LinkedList<>();
                break;
            case VUFORIAONCE:
                vuforiaFinalData = new VisionData[0];
                break;
            case TFODSTREAM:
                tfodDataOut = new LinkedList<>();
                break;
            case TFODONCE:
                tfodFinalData = GoldMineralPosition.CENTER;
                break;
        }
    }
    @Override
    public void run() {
        while (!stopped) {
            //If a stream is wanted then an list of VisionData[] arrays is generated and added to
            //Every checkingInterval
            //Otherwise it's a VUFORIAONCE operation and the results of one check will be returned
            if (vuforiaDataOut != null) {
                vuforiaDataOut.add(VisionUtils.checkForTargets());
            } else if (vuforiaFinalData != null) {
                vuforiaFinalData = VisionUtils.checkForTargets();
                stopThisThread();
            } else if (tfodDataOut != null) {
                tfodDataOut.add(VisionUtils.getGoldMineralPosition());
            } else {
                tfodFinalData = VisionUtils.getGoldMineralPosition();
                stopThisThread();
            }
            try {
                Thread.sleep(checkAtIntervalsOf);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        if (vuforiaDataOut != null) {
            caller.setVuforiaStream(vuforiaDataOut);
        } else if (vuforiaFinalData != null) {
            caller.setVuforiaData(vuforiaFinalData);
        } else if (tfodDataOut != null) {
            caller.setTFODStream(tfodDataOut);
        } else {
            caller.setTFODData(tfodFinalData);
        }
    }

    public void stopThisThread() {
        stopped = true;
    }
}
