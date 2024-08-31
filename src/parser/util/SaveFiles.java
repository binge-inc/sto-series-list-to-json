package parser.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveFiles {
    public static void saveSeriesListJSON(final String json, final boolean formatted) {
        File resultDir = new File("./result/");
        if (!resultDir.exists()) {
            try {
                Files.createDirectory(Paths.get("./result/"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String fileName;
        if (formatted) {
            fileName = "./result/series_nice.json";
        } else {
            fileName = "./result/series.json";
        }
        try (final FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
