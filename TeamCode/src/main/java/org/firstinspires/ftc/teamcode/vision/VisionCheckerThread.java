package org.firstinspires.ftc.teamcode.vision;

import java.util.LinkedList;

final class VisionCheckerThread extends Thread {
    private boolean stopped = false;
    private final long checkAtIntervalsOf;
    private final CVCompatibleClass caller;
    private LinkedList<VisionData[]> vuforiaDataOut = null;
    private VisionData[] vuforiaFinalData = null;
    private LinkedList<GoldMineralPosition> tfodDataOut = null;
    private GoldMineralPosition tfodFinalData = null;
    private final VisionUtils visionUtils;

    VisionCheckerThread(VisionUtils visionUtils, long checkingInterval, CVCompatibleClass caller,
                        VisionCheckerOutputType outputType) {
        this.checkAtIntervalsOf = checkingInterval;
        this.caller = caller;
        switch (outputType) {
            case VUFORIA_STREAM:
                vuforiaDataOut = new LinkedList<>();
                break;
            case VUFORIA_ONCE:
                vuforiaFinalData = new VisionData[0];
                break;
            case TFOD_STREAM:
                tfodDataOut = new LinkedList<>();
                break;
            case TFOD_ONCE:
                tfodFinalData = GoldMineralPosition.CENTER;
                break;
        }
        this.visionUtils = visionUtils;
    }
    @Override
    public void run() {
        while (!stopped) {
            //If a stream is wanted then an list of VisionData[] arrays is generated and added to
            //Every checkingInterval
            //Otherwise it's a VUFORIA_ONCE operation and the results of one check will be returned
            if (vuforiaDataOut != null) {
                vuforiaDataOut.add(visionUtils.checkForTargets());
            } else if (vuforiaFinalData != null) {
                vuforiaFinalData = visionUtils.checkForTargets();
                stopThisThread();
            } else if (tfodDataOut != null) {
                tfodDataOut.add(visionUtils.getGoldMineralPosition());
            } else {
                tfodFinalData = visionUtils.getGoldMineralPosition();
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
