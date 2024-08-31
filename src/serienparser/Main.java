package serienparser;

import org.apache.commons.io.FileUtils;
import serienparser.model.Category;
import serienparser.util.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(final String[] args) {
        String url = "http://186.2.175.5/serien";
        String fileLocation = "./temp/serien.html";

        String html = HTMLDownloader.getHTML(url);
        System.out.println("Full HTML contains " + html.length() + " characters.");
        String importantHtml = SeriesListCutter.cutUnimportantStuff(html);
        System.out.println("Important HTML section contains " + importantHtml.length() + " characters.");
        File file = new File(fileLocation);
        try {
            FileUtils.writeStringToFile(file, importantHtml, "UTF-8");
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("File " + file.getAbsolutePath() + " saved");
        ArrayList<String> categories = SeriesListCutter.getCategories(importantHtml);
        int categoryAmount = categories.size();
        Category[] parsedCategories = new Category[categoryAmount];
        SeriesListParser.parseCategoryList(categories, parsedCategories);
        String jsonRaw = CategoriesToJSON.categoriesToJSON(parsedCategories, false);
        SaveFiles.saveSeriesListJSON(jsonRaw, false);
        String jsonFormatted = CategoriesToJSON.categoriesToJSON(parsedCategories, true);
        SaveFiles.saveSeriesListJSON(jsonFormatted, true);
        System.out.println("ok");
    }
}