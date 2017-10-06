package com.ceiba.parqueadero.rule;


import com.ceiba.parqueadero.domain.Vehiculo;

public interface Multa {
	public double cobrarMulta(Vehiculo vehiculo) ;
}
