package org.firstinspires.ftc.teamcode.errorio;

import java.util.LinkedHashMap;
import java.util.Map;

import org.firstinspires.ftc.teamcode.fileio.FileIOUtils;
import org.firstinspires.ftc.teamcode.fileio.Purpose;

public final class LogUtils {

    private static final String NEW_LINE_CHARACTER = System.lineSeparator();

    public static boolean logError(LinkedHashMap<String, String>[] states) {
        return logError(generateOutput(states));
    }

    public static boolean logError(String error) {
        return FileIOUtils.writeToFile(FileIOUtils.generateFile(FileIOUtils
                .generatePath(FileIOUtils.ERROR_LOG_DIRECTORY),
                Purpose.ERROR_LOGGING,
                null), error);
    }

    private static String generateOutput(LinkedHashMap<String, String>[] states) {
        if (states != null) {
            StringBuilder output = new StringBuilder();
            for (LinkedHashMap<String, String> state : states) {
                if (state != null) {
                    for (Map.Entry<String, String> entry : state.entrySet()) {
                        if (entry != null) {
                            if (entry.getKey() != null) {
                                output.append(entry.getKey()).append(": ")
                                        .append((entry.getValue() == null) ?
                                                "" : entry.getValue()).append(NEW_LINE_CHARACTER);
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
}
