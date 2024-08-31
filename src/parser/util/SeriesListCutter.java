package parser.util;

import java.util.ArrayList;
import java.util.Arrays;

public class SeriesListCutter {
    public static String cutUnimportantStuff(final String input) {
        final String startPattern = "<div id=\"seriesContainer\" class=\"seriesList\">";
        final String endPattern = "<ul class=\"homeContentGenresList\">";
        int beginIndex = input.indexOf(startPattern) + startPattern.length();
        System.out.println("SeriesListCutter.cutUnimportantStuff(String): beginIndex is " + beginIndex);
        int endIndex = input.indexOf(endPattern);
        System.out.println("SeriesListCutter.cutUnimportantStuff(String): endIndex is " + endIndex);
        String output = input.substring(beginIndex, endIndex);
        System.out.println("SeriesListCutter.cutUnimportantStuff(String): Important HTML section contains " + output.length() + " characters");
        return output;
    }

    public static ArrayList<String> getCategories(final String input) {
        String splitSymbol = "<div class=\"seriesGenreList\">";
        ArrayList<String> categories = new ArrayList<>(Arrays.asList(input.split(splitSymbol)));
        categories.remove(0);
        return categories;
    }
}
