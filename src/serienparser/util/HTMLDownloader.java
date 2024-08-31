package serienparser.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.*;
import java.util.regex.*;

public class HTMLDownloader {
    public static String getHTML(final String fullUrl) {
        URL url = null;
        try {
            url = new URL(fullUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        URLConnection con = null;
        try {
            con = url.openConnection();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
        Matcher m = p.matcher(con.getContentType());
        String charset = m.matches() ? m.group(1) : "ISO-8859-1";
        String str;
        try {
            str = IOUtils.toString(con.getInputStream(), charset);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return str;
    }
}
