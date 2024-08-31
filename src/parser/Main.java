package parser;

import parser.model.Category;
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
        Category[] parsedCategories = new Category[categories.size()];
        SeriesListParser.parseCategoryList(categories, parsedCategories);
        String jsonRaw = CategoriesToJSON.categoriesToJSON(parsedCategories, false);
        SaveFiles.saveSeriesListJSON(jsonRaw, false);
        String jsonFormatted = CategoriesToJSON.categoriesToJSON(parsedCategories, true);
        SaveFiles.saveSeriesListJSON(jsonFormatted, true);
    }
}
