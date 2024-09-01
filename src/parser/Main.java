package parser;

import parser.model.*;
import parser.util.*;

import java.util.ArrayList;

public class Main {
    public static void main(final String[] args) {
        String url = "http://186.2.175.5/serien";
        String html = HTMLDownloader.getHTML(url);
        if (html == null) {
            System.err.println("Main.main(String[]): html is null");
            System.err.println("Main.main(String[]): Aborting...");
            return;
        }
        String importantHtml = SeriesListCutter.cutUnimportantStuff(html);
        ArrayList<String> categories = SeriesListCutter.getCategories(importantHtml);
        Category[] parsedCategories = new Category[categories.size()]; // categorized
        SeriesListParser.parseCategoryList(categories, parsedCategories);
        String jsonRawCategorized = ConvertToJSON.categoriesToJSON(parsedCategories, false);
        SaveFiles.saveSeriesListJSON(jsonRawCategorized, false, true);
        String jsonFormattedCategorized = ConvertToJSON.categoriesToJSON(parsedCategories, true);
        SaveFiles.saveSeriesListJSON(jsonFormattedCategorized, true, true);
        ArrayList<Series> series = Repack.packSeriesArrayFromCategoryArray(parsedCategories);
        Series[] seriesArray = SeriesListToArray.convert(series); // uncategorized
        String jsonRaw = ConvertToJSON.categoriesToJSON(seriesArray, false);
        SaveFiles.saveSeriesListJSON(jsonRaw, false, false);
        String jsonFormatted = ConvertToJSON.categoriesToJSON(seriesArray, true);
        SaveFiles.saveSeriesListJSON(jsonFormatted, true, false);
    }
}
