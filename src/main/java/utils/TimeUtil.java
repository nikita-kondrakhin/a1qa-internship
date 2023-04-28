package utils;

import constants.ConfigData;
import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
public class TimeUtil {
    public static String getCurrentTime() {
        return new SimpleDateFormat(ConfigData.TIME_FORMAT).format(new Date());
    }
}
