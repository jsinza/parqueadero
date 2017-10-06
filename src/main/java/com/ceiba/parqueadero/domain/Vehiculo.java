package com.ceiba.parqueadero.domain;

import com.ceiba.parqueadero.type.TipoVehiculo;

public class Vehiculo {

  private String placa;

  private TipoVehiculo tipoVehiculo;

  private double cilandre;

  public Vehiculo() {
    super();

  }

  public Vehiculo(String placa, TipoVehiculo tipoVehiculo) {
    super();
    this.placa = placa;
    this.tipoVehiculo = tipoVehiculo;
  }

  public Vehiculo(String placa, TipoVehiculo tipoVehiculo, double cilandre) {
    super();
    this.placa = placa;
    this.tipoVehiculo = tipoVehiculo;
    this.cilandre = cilandre;
  }

  public TipoVehiculo getTipoVehiculo() {
    return tipoVehiculo;
  }

  public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
    this.tipoVehiculo = tipoVehiculo;
  }

  public double getCilandre() {
    return cilandre;
  }

  public void setCilandre(double cilandre) {
    this.cilandre = cilandre;
  }

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

}
