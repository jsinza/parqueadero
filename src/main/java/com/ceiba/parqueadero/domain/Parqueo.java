package com.ceiba.parqueadero.domain;

import java.time.LocalDateTime;

public class Parqueo {

  private Vehiculo vehiculo;

  private LocalDateTime fechaIngreso;



  public Parqueo(Vehiculo vehiculo, LocalDateTime fechaIngreso) {
    super();
    this.vehiculo = vehiculo;
    this.fechaIngreso = fechaIngreso;
  }

  public Parqueo() {
    super();
  }

  public Vehiculo getVehiculo() {
    return vehiculo;
  }

  public void setVehiculo(Vehiculo vehiculo) {
    this.vehiculo = vehiculo;
  }

  public LocalDateTime getFechaIngreso() {
    return fechaIngreso;
  }

  public void setFechaIngreso(LocalDateTime fechaIngreso) {
    this.fechaIngreso = fechaIngreso;
  }



}
