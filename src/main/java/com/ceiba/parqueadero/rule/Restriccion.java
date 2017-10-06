package com.ceiba.parqueadero.rule;

import java.time.LocalDateTime;
import com.ceiba.parqueadero.domain.Vehiculo;

public interface Restriccion {

  public boolean validarRestriccion(Vehiculo vehiculo, LocalDateTime fechaIngreso);

}
