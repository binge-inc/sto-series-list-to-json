package parser.model;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Series> seriesList;

    public Category(final String name) {
        this.name = name;
        seriesList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ArrayList<Series> getSeriesList() {
        return seriesList;
    }

    public void addSeries(final Series series) {
        seriesList.add(series);
    }

    public Series getSeries(final int x) {
        return seriesList.get(x);
    }
}
