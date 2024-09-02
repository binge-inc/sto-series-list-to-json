package parser.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.*;
import java.util.regex.*;

public class HTMLDownloader {
    public static String getHTML(final String fullUrl, final boolean debug) {
        URL url;
        if (debug) System.out.println("HTMLDownloader.getHTML(String): Checking url " + fullUrl + "...");
        try {
            url = new URL(fullUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        if (debug) System.out.println("HTMLDownloader.getHTML(String): Connecting...");
        URLConnection con;
        try {
            con = url.openConnection();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
        if (con.getContentType() == null) {
            System.err.println("Something went wrong. We probably got a 403 or some other non-good server response.");
            return null;
        }
        if (debug) System.out.println("HTMLDownloader.getHTML(String): Matching charset...");
        Matcher m = p.matcher(con.getContentType());
        String charset = m.matches() ? m.group(1) : "ISO-8859-1";
        String str;
        if (debug) System.out.println("HTMLDownloader.getHTML(String): Downloading HTML...");
        try {
            str = IOUtils.toString(con.getInputStream(), charset);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return str;
    }
}
