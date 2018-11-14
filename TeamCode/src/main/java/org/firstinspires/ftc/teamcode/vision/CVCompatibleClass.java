package org.firstinspires.ftc.teamcode.vision;

import java.util.LinkedList;

public interface CVCompatibleClass {
    void setVuforiaStream(LinkedList<VisionData[]> list);
    void setVuforiaData(VisionData[] data);
    void setTFODStream(LinkedList<GoldMineralPosition> list);
    void setTFODData(GoldMineralPosition data);
}
