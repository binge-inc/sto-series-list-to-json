package parser.util;

import parser.model.Category;
import parser.model.Series;

import java.util.ArrayList;
import java.util.Collections;

public class Repack {

    public static ArrayList<Series> packSeriesArrayFromCategoryArray(final Category[] categories) {
        ArrayList<Series> seriesList = new ArrayList<>();
        for (final Category category : categories) {
            seriesList.addAll(category.getSeriesList());
        }
        Collections.sort(seriesList);
        return seriesList;
    }
}
