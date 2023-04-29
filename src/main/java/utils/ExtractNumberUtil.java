package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExtractNumberUtil {
    private static final Logger logger = AqualityServices.getLogger();
    private static final int VARIANT_INDEX = 1;

    public static int getVariantNumber(String text) {
        logger.info("Extracting the variant number from the text");
        return Integer.parseInt(text.split(":")[VARIANT_INDEX].trim());
    }
}
