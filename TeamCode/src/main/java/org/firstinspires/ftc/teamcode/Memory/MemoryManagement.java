package org.firstinspires.ftc.teamcode.Memory;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.LinkedList;
import java.util.Objects;

import static android.content.Context.ACTIVITY_SERVICE;

public final class MemoryManagement {
    private static LinkedList<Object> objects;
    private static byte objectLimit;
    private static short managementRate;
    private static boolean previouslyInitialized = false;
    private static boolean stopped = true;

    public static void initialize(ManagementOptions options) {
        if (previouslyInitialized) {
            stopped = true;
            try {
                Thread.sleep(2048);
            } catch (InterruptedException ie) {
                Log.d("ERROR", ie.toString());
            }
        }
        objects = new LinkedList<>();
        switch (options) {
            case EXTREME:
                objectLimit = 4;
                managementRate = 512;
                break;
            case OPTIMAL:
                objectLimit = 8;
                managementRate = 1024;
                break;
            case LAX:
                objectLimit = 16;
                managementRate = 2048;
                break;
        }
        stopped = false;
        new Thread(() -> {
            while (!stopped) {
                if (objects.size() >= objectLimit) {
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
        }).start();
        previouslyInitialized = true;
    }

    public static void free(Object obj) {
        objects.add(obj);
    }

    public static void stop() {
        stopped = true;
    }

    public boolean memoryIntensiveOpAllowed(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) Objects.requireNonNull(context.getSystemService(ACTIVITY_SERVICE))).getMemoryInfo(memoryInfo);
        return !memoryInfo.lowMemory;
    }
}
