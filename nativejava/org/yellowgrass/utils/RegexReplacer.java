package org.yellowgrass.utils;

public class RegexReplacer {
    public static String replaceAll(String haystack, String regex, String replacement) {
        return haystack.replaceAll(regex, replacement);
    }
}
