package org.firstinspires.ftc.teamcode.errorlogging;

import android.icu.text.SimpleDateFormat;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public final class LogUtils {

    private static final String ERROR_LOG_DIRECTORY = "FTCRobotController Error Logs";
    private static final String NEW_LINE_CHARACTER = System.lineSeparator();
    private static final String FILE_EXTENSION = ".txt";

    public static void logError(HashMap<String, String>[] states) {
        writeToFile(generateFile(generatePath()), generateOutput(states));
    }

    private static File generatePath() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File path = new File(Environment.getExternalStorageDirectory(), ERROR_LOG_DIRECTORY);
            if (!path.exists()) {
                if (path.mkdirs()) {
                    return path;
                }
                return null;
            }
            return path;
        }
        return null;
    }

    private static File generateFile(File path) {
        if (path != null) {
            return new File(path, new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Timestamp(System.currentTimeMillis())) + FILE_EXTENSION);
        }
        return null;
    }

    private static String generateOutput(HashMap<String, String>[] states) {
        if (states != null) {
            StringBuilder output = new StringBuilder();
            for (HashMap<String, String> state : states) {
                if (state != null) {
                    for (Map.Entry<String, String> entry : state.entrySet()) {
                        if (entry != null) {
                            if (entry.getKey() != null) {
                                output.append(entry.getKey()).append(": ").append((entry.getValue() == null) ? "":entry.getValue()).append(NEW_LINE_CHARACTER);
                            }
                        }
                    }
                    output.append(NEW_LINE_CHARACTER).append(NEW_LINE_CHARACTER);
                }
            }
            return output.toString();
        }
        return "";
    }

    private static void writeToFile(File file, String output) {
        if (file != null) {
            try {
                FileWriter writer = new FileWriter(file);
                writer.append(output);
                writer.flush();
                writer.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
