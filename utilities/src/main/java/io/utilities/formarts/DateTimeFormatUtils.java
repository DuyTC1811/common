package io.utilities.formarts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatUtils {
    public static LocalDateTime formatterTime(String time, EPatternTime pattenTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattenTime.getPattern());
        return LocalDateTime.parse(time, formatter);
    }
}
