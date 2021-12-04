package com.example.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateFormatterUtil {
  private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
  private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
  private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

  public static String format(Date date) {
    if (date == null) {
      return null;
    }

    return dateFormat.format(date);
  }
  public static String format(LocalDate localDate) {
    return localDate != null ? dateFormatter.format(localDate) : null;
  }

  public static String format(LocalTime localTime) {
    return localTime != null ? timeFormatter.format(localTime) : null;
  }

  public static String format(LocalDateTime localDateTime) {
    return localDateTime != null ? dateTimeFormatter.format(localDateTime) : null;
  }
}
