package com.ceiba.parqueadero.rule.impl;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.ceiba.parqueadero.domain.Vehiculo;
import com.ceiba.parqueadero.rule.Multa;
import com.ceiba.parqueadero.type.TipoVehiculo;

public class ValidarCilandreTest {

  private Vehiculo vehiculo;
  private Multa validarCilandre;

  @Before
  public void inicializarVariable() {   
    vehiculo = new Vehiculo();
    validarCilandre = new ValidarCilandre();
  }

  @Test
  public void retirarUnaMotoCilandreMenor500() {
    // arrange
    crearUnVehiculoParqueado(TipoVehiculo.MOTO, 150);
    double ValorEsperado = 0.0;
    // act
    double valorObtenido = validarCilandre.cobrarMulta(vehiculo);
    // assert
    assertEquals(ValorEsperado, valorObtenido, 0.0);
  }

  @Test
  public void retirarUnaMotoCilandreMayor500() {
    // arrange
    crearUnVehiculoParqueado(TipoVehiculo.MOTO, 600);
    double ValorEsperado = 2000;
    // act
    double valorObtenido = validarCilandre.cobrarMulta(vehiculo);
    // assert
    assertEquals(ValorEsperado, valorObtenido, 0.0);
  }

  @Test
  public void retirarUnaCarro() {
    // arrange
    crearUnVehiculoParqueado(TipoVehiculo.CARRO, 0.0);
    double ValorEsperado = 0.0;
    // act
    double valorObtenido = validarCilandre.cobrarMulta(vehiculo);
    // assert
    assertEquals(ValorEsperado, valorObtenido, 0.0);
  }

  private void crearUnVehiculoParqueado(TipoVehiculo tipovehiculo, double cilandre) {
    vehiculo.setTipoVehiculo(tipovehiculo);
    if (tipovehiculo.equals(TipoVehiculo.MOTO)) {
      vehiculo.setCilandre(cilandre);
    }
    
  }
}
