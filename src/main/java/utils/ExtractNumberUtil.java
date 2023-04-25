package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExtractNumberUtil {
    private static final Logger logger = AqualityServices.getLogger();

    public static int getVariantNumber(String text) {
        logger.info("Extracting the variant number from the text");
        return Integer.parseInt(text.split(":")[1].trim()); //todo magic number?
    }
}
