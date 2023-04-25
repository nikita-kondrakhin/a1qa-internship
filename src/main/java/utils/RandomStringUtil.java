package utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

@UtilityClass
public class RandomStringUtil {
    public static String getRandomString(int length) {
        return RandomStringUtils.randomNumeric(length);
    }
}
