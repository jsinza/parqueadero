package com.ceiba.parqueadero.type;

public enum TipoCobro {
  DIA(2), HORA(1);
  private int value;

  TipoCobro(int value) {
    this.value = value;
  }
}
