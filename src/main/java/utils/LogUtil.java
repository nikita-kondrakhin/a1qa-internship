package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import constants.ConfigData;
import exceptions.LogFileException;
import lombok.experimental.UtilityClass;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class LogUtil {
    private static final Logger logger = AqualityServices.getLogger();
    private static final String INFO = "INFO";
    private static final int LINES_QUANTITY_LIMIT = 100;
    private static final int LINE_LENGTH_LIMIT = 200;
    private static final Path logFilePath = new File(ConfigData.LOG_FILE_PATH).toPath();

    public static String getLog() {
        logger.info("Retrieving data from log file");
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
}
