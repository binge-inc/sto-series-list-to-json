package serienparser.util;

import java.io.File;

public class TempClear {
    public static void clear() {
        File tempDir = new File("./temp/");
        if (tempDir.exists()) {
            File tempFile = new File("./temp/series.html");
            if (tempFile.exists()) {
                tempFile.delete();
            }
            tempDir.delete();
        }
    }
}
