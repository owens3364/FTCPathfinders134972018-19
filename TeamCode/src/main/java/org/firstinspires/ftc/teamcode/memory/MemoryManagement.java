package org.firstinspires.ftc.teamcode.memory;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.LinkedList;
import java.util.Objects;

import static android.content.Context.ACTIVITY_SERVICE;

public final class MemoryManagement {
    private LinkedList<Object> objects;
    private byte objectLimit;
    private short managementRate;
    private boolean alreadyInitialized = false;
    private boolean stopped = true;

    public boolean initialize(ManagementOptions options) {
        if (alreadyInitialized) {
            return false;
        }
        objects = new LinkedList<>();
        switch (options) {
            case EXTREME:
                objectLimit = 4;
                managementRate = 256;
                break;
            case OPTIMAL:
                objectLimit = 8;
                managementRate = 1024;
                break;
            case LAX:
                objectLimit = 16;
                managementRate = 4096;
                break;
        }
        stopped = false;
        new Thread(() -> {
            while (!stopped) {
                if (objects.size() > objectLimit) {
                    for (Object object: objects) {
                        object = null;
                    }
                    System.gc();
                }
                try {
                    Thread.sleep(managementRate);
                } catch(InterruptedException ie) {
                    Log.d("ERROR", ie.toString());
                }
            }
            if (objects.size() > 0) {
                for (Object object : objects) {
                    object = null;
                }
            }
            objects = null;
            System.gc();
        }).start();
        alreadyInitialized = true;
        return true;
    }

    public void free(Object obj) {
        objects.add(obj);
    }

    public void stop() {
        objectLimit = 16;
        managementRate = 4096;
        alreadyInitialized = false;
        stopped = true;
    }

    public boolean memoryIntensiveOpAllowed(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) Objects.requireNonNull(context.getSystemService(ACTIVITY_SERVICE))).getMemoryInfo(memoryInfo);
        return !memoryInfo.lowMemory;
    }
}
