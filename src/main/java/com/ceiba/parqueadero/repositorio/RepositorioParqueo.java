package com.ceiba.parqueadero.repositorio;

import java.util.List;
import com.ceiba.parqueadero.domain.Parqueo;

public interface RepositorioParqueo {

  public void parquear(Parqueo parqueo); 
  
  public long  cantidadMotoParqueada();
  
  public long  cantidadCarroParqueados();
  
  public void  retirarVehiculoParqueado(String placa); 
  
  public List<Parqueo> buscarVehiculoParqueado();
  
  public Parqueo buscarVehiculoParqueadoPorPlaca(String placa);
}
