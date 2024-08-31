package parser.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import parser.model.Category;

public class CategoriesToJSON {
    public static String categoriesToJSON(final Category[] categories, final boolean formatted) {
        Gson gson;
        if (formatted) {
            gson = new GsonBuilder().setPrettyPrinting().create(); // formatted, roughly 1mb
        } else {
            gson = new Gson(); // unformatted, roughly 600kb
        }
        return gson.toJson(categories);
    }
}
