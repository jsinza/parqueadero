package com.ceiba.parqueadero.rule.impl;


import com.ceiba.parqueadero.domain.Vehiculo;
import com.ceiba.parqueadero.rule.Multa;
import com.ceiba.parqueadero.type.TipoVehiculo;

public class ValidarCilandre implements Multa {

  public static final double VALOR_MULTA = 2000;

  public static final int CILANDRE_MAXIMO = 500;

  @Override
  public double cobrarMulta(Vehiculo vehiculo) {
    if (vehiculo.getTipoVehiculo().equals(TipoVehiculo.MOTO)
        && (vehiculo.getCilandre() > CILANDRE_MAXIMO)) {
      return VALOR_MULTA;
    }
    return 0.0;
  }
}
