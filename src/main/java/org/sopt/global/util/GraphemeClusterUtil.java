package org.sopt.global.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphemeClusterUtil {
    private static final Pattern GRAPHEME_PATTERN = Pattern.compile("\\X");

    public static int countGraphemeClusters(String text) {
        if (text == null) {
            return 0;
        }
        final Matcher graphemeMatcher = GRAPHEME_PATTERN.matcher(text);
        return (int) graphemeMatcher.results().count();
    }
}
