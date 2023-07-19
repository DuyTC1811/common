package io.utilities.regexs;

import java.util.regex.Pattern;

public class RegexUtils {
    public static boolean validateRegex(String value, String regex) {
        return Pattern.matches(regex, value);
    }
}
