package com.ceiba.parqueadero.repositorio;


import com.ceiba.parqueadero.domain.Tarifa;
import com.ceiba.parqueadero.type.TipoCobro;
import com.ceiba.parqueadero.type.TipoVehiculo;

public interface RepositorioTarifa {

  public  Tarifa buscarTarifaParaVehiculo(TipoVehiculo tipoVehiculo,TipoCobro tipoCobro);
  
}
