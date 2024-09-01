package parser.util;

import parser.model.Category;
import parser.model.Series;

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
     * Takes an ArrayList<String> containing the raw html of all series, with 1 category per element.
     * The parsed data is stored into the specified Category array.
     * The array parsedCategories must be initialized and must have the same size as
     *
     * @param categories raw data
     * @param parsedCategories array to store to
     */
    public static void parseCategoryList(final ArrayList<String> categories, final Category[] parsedCategories) {
        System.out.println("SeriesListParser.parseCategoryList(ArrayList<String>, Category[]): Parsing categories...");
        if (categories.size() == parsedCategories.length) {
            for (int i = 0; i < categories.size(); i++) {
                parsedCategories[i] = new Category(SeriesListParser.getCategoryName(categories.get(i)));
            }
            for (int i = 0; i < categories.size(); i++) {
                System.out.println("SeriesListParser.parseCategoryList(ArrayList<String>, Category[]): " + (i + 1) + "/" + categories.size() + ": " + parsedCategories[i].getName());
                parse(categories.get(i), parsedCategories[i]);
            }
        } else {
            System.err.println("categories.size() does not match parsedCategories.length [" + categories.size() + " != " + parsedCategories.length + "].");
        }
    }

    /**
     * ToDo
     *
     * @param input raw data
     * @param parsedCategory Category object to store to
     */
    public static void parse(final String input, final Category parsedCategory) {
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
            while (name.startsWith(" ")) {
                name = name.substring(1);
            }
            parsedCategory.addSeries(new Series(name, url));
        }
    }
}
