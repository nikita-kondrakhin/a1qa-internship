package utils;

import constants.ConfigData;
import exceptions.LogFileException;
import lombok.experimental.UtilityClass;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class LogUtil {
    private static final String INFO = "INFO";
    private static final int LINES_QUANTITY_LIMIT = 100;
    private static final int LINE_LENGTH_LIMIT = 200;
//    private static final Path logFilePath = Paths.get(ConfigData.LOG_FILE_PATH); todo
    private static final Path logFilePath = new File(ConfigData.LOG_FILE_PATH).toPath();

    public static String getLog() {
        try (Stream<String> lines = Files.lines(logFilePath)) {
            return lines.filter(line -> !line.contains(INFO) && line.length() <= LINE_LENGTH_LIMIT)
                    .limit(LINES_QUANTITY_LIMIT)
                    .collect(Collectors.joining(System.lineSeparator()));
        }  catch (FileNotFoundException e) {
            throw new LogFileException("Log file not found", e);
        } catch (IOException e) {
            throw new LogFileException("Error reading log file", e);
        }
    }

    public static void deleteLogFile() {
        try {
            Files.deleteIfExists(logFilePath);
        } catch (IOException e) {
            throw new LogFileException("Error deleting log file", e);
        }
    }
}
