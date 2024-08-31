package serienparser.model;

public class Series {
    private String name;
    private String url;

    public Series() {
        name = null;
        url = null;
    }

    public Series(final String name, final String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }
}
