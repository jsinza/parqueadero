package com.ceiba.parqueadero.util;

import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;
import java.time.Month;
import org.junit.Test;

public class FechaUtilsTest {

  @Test
  public void formatearUnaFecha() {
    // arrange
    String fechaTexto = "1986-04-08 12:30";
    String formatoFecha = "yyyy-MM-dd HH:mm";

    // act
    LocalDateTime fecha = FechaUtils.formatearFecha(formatoFecha, fechaTexto);

    // assert
    assertEquals(Month.APRIL, fecha.getMonth());
  }
}
