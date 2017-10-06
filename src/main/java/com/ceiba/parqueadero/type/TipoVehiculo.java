package com.ceiba.parqueadero.type;

public enum TipoVehiculo {
  CARRO(2), MOTO(1);
  private int value;

  TipoVehiculo(int value) {
    this.value = value;
  }
}
