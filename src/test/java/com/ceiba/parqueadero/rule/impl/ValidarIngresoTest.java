package com.ceiba.parqueadero.rule.impl;

import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import com.ceiba.parqueadero.domain.Vehiculo;
import com.ceiba.parqueadero.exception.ParqueaderoException;


public class ValidarIngresoTest {


  private Vehiculo vehiculo;
  private LocalDateTime fechaIngreso;
  private ValidarIngreso validarIngreso;


  @Before
  public void inicializarVariable() {
    vehiculo = new Vehiculo();
    validarIngreso = new ValidarIngreso();
  }

  @Test
  public void ingresarVehiculoConPlacaIniciaA() {
    // arrange
    vehiculo.setPlaca("AAA456");
    fechaIngreso = formatearFecha("2017-10-10 10:30");
    boolean ValorEsperado = false;
    // act
    boolean valorActual = validarIngreso.validarRestriccion(vehiculo, fechaIngreso);
    // assert
    assertEquals(ValorEsperado, valorActual);
  }


  @Test(expected = ParqueaderoException.class)
  public void ingresarVehiculoConPlacaIniciaAUnLunes() {
    // arrange
    vehiculo.setPlaca("AAA456");
    fechaIngreso = formatearFecha("2017-10-02 10:30");
    // act
    validarIngreso.validarRestriccion(vehiculo, fechaIngreso);
  }

  @Test(expected = ParqueaderoException.class)
  public void ingresarVehiculoConPlacaIniciaAUnDomingo() {
    // arrange
    vehiculo.setPlaca("AAA456");
    fechaIngreso = formatearFecha("2017-10-08 10:30");
    // act
    validarIngreso.validarRestriccion(vehiculo, fechaIngreso);
  }

  @Test
  public void ingresarVehiculoConPlacaDiferenteA() {
    // arrange
    vehiculo.setPlaca("BAA456");
    fechaIngreso = formatearFecha("2016-11-09 10:30");
    boolean ValorEsperado = true;
    // act
    boolean valorActual = validarIngreso.validarRestriccion(vehiculo, fechaIngreso);
    // assert
    assertEquals(ValorEsperado, valorActual);
  }



  private LocalDateTime formatearFecha(String fecha) {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime fechaFormateada = LocalDateTime.parse(fecha, formato);
    return fechaFormateada;
  }

}
