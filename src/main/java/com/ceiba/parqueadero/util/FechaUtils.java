package com.ceiba.parqueadero.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FechaUtils {

  public static LocalDateTime formatearFecha(String formatoFecha, String fecha) {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern(formatoFecha);
    return LocalDateTime.parse(fecha, formato);

  }

}
