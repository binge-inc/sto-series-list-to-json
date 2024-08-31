package serienparser.util;

import serienparser.model.Category;
import serienparser.model.Series;

import java.util.ArrayList;
import java.util.Arrays;

public class SeriesListParser {

    /**
     * Parses the category name from the HTML.
     *
     * @param input raw data
     * @return category name
     */
    public static String getCategoryName(final String input) {
        final String startPattern = "<h3>";
        final String endPattern = "</h3>";
        return input.substring(input.indexOf(startPattern) + startPattern.length(), input.indexOf(endPattern));
    }

    /**
     * ToDo
     *
     * @param categories raw data
     * @param parsedCategories array to store to
     * @return
     */
    public static boolean parseCategoryList(final ArrayList<String> categories, final Category[] parsedCategories) {
        if (categories.size() == parsedCategories.length) {
            for (int i = 0; i < categories.size(); i++) {
                parsedCategories[i] = new Category(SeriesListParser.getCategoryName(categories.get(i)));
            }
            for (int i = 0; i < categories.size(); i++) {
                parse(categories.get(i), parsedCategories[i]);
            }
            return true;
        } else {
            System.err.println("categories.size() does not match parsedCategories.length [" + categories.size() + " != " + parsedCategories.length + "].");
            return false;
        }
    }

    public static boolean parseCategoryList(final int index, final String category, final Category[] parsedCategories) {
        parsedCategories[index] = new Category(SeriesListParser.getCategoryName(category));
        parse(category, parsedCategories[index]);
        return true;
    }

    /**
     * ToDo
     *
     * @param input raw data
     * @param parsedCategory Category object to store to
     * @return
     */
    public static boolean parse(final String input, final Category parsedCategory) {
        final String urlBeginPattern = "href=\"";
        final String urlEndPattern = "\" ";
        final String nameBeginPattern = "\">";
        final String nameEndPattern = "</a>";
        final String seriesSplitPattern = "data-alternative-title=\"";
        ArrayList<String> splitted = new ArrayList<>(Arrays.asList(input.split(seriesSplitPattern)));
        splitted.remove(0);
        for (int i = 0; i < splitted.size(); i++) {
            splitted.set(i, splitted.get(i).substring(splitted.get(i).indexOf(urlBeginPattern)));
        }
        String s, url, name;
        for (int i = 0; i < splitted.size(); i++) {
            s = splitted.get(i);
            url = s.substring(s.indexOf(urlBeginPattern) + urlBeginPattern.length(), s.indexOf(urlEndPattern));
            name = s.substring(s.indexOf(nameBeginPattern) + nameBeginPattern.length(), s.indexOf(nameEndPattern));
            parsedCategory.addSeries(new Series(name, url));
        }
        return true;
    }
}
