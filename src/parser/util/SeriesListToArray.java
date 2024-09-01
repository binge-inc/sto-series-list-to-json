package parser.util;

import parser.model.Series;

import java.util.ArrayList;

public class SeriesListToArray {
    public static Series[] convert(final ArrayList<Series> seriesArrayList) {
        Series[] seriesArray = new Series[seriesArrayList.size()];
        for (int i = 0; i < seriesArrayList.size(); i++) {
            seriesArray[i] = seriesArrayList.get(i);
        }
        return seriesArray;
    }
}
