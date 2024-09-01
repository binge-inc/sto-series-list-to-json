package parser.util;

import com.google.gson.*;
import parser.model.*;

public class ConvertToJSON {
    public static String categoriesToJSON(final Category[] categories, final boolean formatted) {
        Gson gson = getGson(formatted);
        return gson.toJson(categories);
    }

    public static String categoriesToJSON(final Series[] series, final boolean formatted) {
        Gson gson = getGson(formatted);
        return gson.toJson(series);
    }

    public static Gson getGson(final boolean formatted) {
        Gson gson;
        if (formatted) {
            gson = new GsonBuilder().setPrettyPrinting().create(); // formatted
        } else {
            gson = new Gson(); // unformatted
        }
        return gson;
    }
}
