package io.utilities.formarts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatUtils {
    public static LocalDateTime formatterTime(String time, EPattenTime pattenTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattenTime.getPatten());
        return LocalDateTime.parse(time, formatter);
    }
}
