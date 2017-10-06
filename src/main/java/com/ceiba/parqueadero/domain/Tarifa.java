package com.ceiba.parqueadero.domain;

import com.ceiba.parqueadero.type.TipoCobro;
import com.ceiba.parqueadero.type.TipoVehiculo;

public class Tarifa {

	private TipoVehiculo tipoVehiculo;
	
	private TipoCobro tipoCobro;
	
	private double valor;
	

	public Tarifa(TipoVehiculo tipoVehiculo, TipoCobro tipoCobro, double valor) {
    super();
    this.tipoVehiculo = tipoVehiculo;
    this.tipoCobro = tipoCobro;
    this.valor = valor;
  }

  public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public TipoCobro getTipoCobro() {
		return tipoCobro;
	}

	public void setTipoCobro(TipoCobro tipoCobro) {
		this.tipoCobro = tipoCobro;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
}
