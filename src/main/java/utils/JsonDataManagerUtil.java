package utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import exceptions.JsonDataParsingException;
import lombok.experimental.UtilityClass;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@UtilityClass
public class JsonDataManagerUtil {
    public static String getStringElementByKey(String dataPath, String key) {
        try (Reader reader = new FileReader(dataPath)) {
            JsonElement json = JsonParser.parseReader(reader);
            JsonElement element = json.getAsJsonObject().get(key);
            if (element != null && !element.isJsonNull()) {
                return element.getAsString();
            }
            throw new JsonDataParsingException(String.format("Cannot find or parse the string value for key '%s' in the JSON file", key));
        } catch (IOException e) {
            throw new JsonDataParsingException("Error reading or parsing the JSON file", e);
        }
    }

    public static int getIntElementByKey(String dataPath, String key) {
        try (Reader reader = new FileReader(dataPath)) {
            JsonElement json = JsonParser.parseReader(reader);
            JsonElement element = json.getAsJsonObject().get(key);
            if (element != null && !element.isJsonNull()) {
                return element.getAsInt();
            }
            throw new JsonDataParsingException(String.format("Cannot find or parse the string value for key '%s' in the JSON file", key));
        } catch (IOException e) {
            throw new JsonDataParsingException("Error reading or parsing the JSON file", e);
        }
    }
}
