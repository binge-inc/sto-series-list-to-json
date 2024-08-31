package serienparser.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import serienparser.model.Category;

public class CategoriesToJSON {
    public static String categoriesToJSON(final Category[] categories, final boolean formatted) {
        Gson gson;
        if (formatted) {
            gson = new Gson(); // unformatted, roughly 600kb
        } else {
            gson = new GsonBuilder().setPrettyPrinting().create(); // formatted, roughly 1mb
        }
        return gson.toJson(categories);
    }
}
