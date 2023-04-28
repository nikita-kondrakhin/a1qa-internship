package constants;

import utils.JsonDataManagerUtil;

public class JsData {
    public static final String CLOSE_POP_UP_METHOD = JsonDataManagerUtil.getStringElementByKey(DataPaths.JS_DATA_PATH, "closePopUpMethod");

    private JsData() throws InstantiationException {
        throw new InstantiationException(String.format("Static %s class should not be initialized", getClass().getSimpleName()));
    }
}
