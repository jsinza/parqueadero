package com.ceiba.parqueadero.rule.impl;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import com.ceiba.parqueadero.domain.Vehiculo;
import com.ceiba.parqueadero.exception.ParqueaderoException;
import com.ceiba.parqueadero.rule.Restriccion;

public class ValidarIngreso implements Restriccion {
	private static final String LETRAS_CON_INICIA = "A";

	@Override
	public boolean validarRestriccion(Vehiculo vehiculo,LocalDateTime fechaIngreso) {
		if (vehiculo.getPlaca().startsWith(LETRAS_CON_INICIA))
			return validarDiaIngreso(fechaIngreso);
		return true;

	}

	private boolean validarDiaIngreso(LocalDateTime fechaIngreso) {
		if (fechaIngreso.getDayOfWeek().equals(DayOfWeek.MONDAY)
				|| fechaIngreso.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			throw new ParqueaderoException("No se puede parquear este vehiculo");

		} else {
			return false;
		}
	}

}
