package parser.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveFiles {
    public static void saveSeriesListJSON(final String dirPath, final String json, final boolean formatted, final boolean categorized) {
        File resultDir = new File(dirPath);
        if (!resultDir.exists()) {
            try {
                Files.createDirectory(Paths.get(dirPath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String fileName;
        if (formatted) {
            if (categorized) {
                fileName = dirPath + "series_nice_categorized.json";
            } else {
                fileName = dirPath + "series_nice.json";
            }
        } else {
            if (categorized) {
                fileName = dirPath + "series_categorized.json";
            } else {
                fileName = dirPath + "series.json";
            }
        }
        try (final FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        System.out.println("SaveFiles.saveSeriesListJSON(String, boolean): File " + fileName + " saved");
    }
}
