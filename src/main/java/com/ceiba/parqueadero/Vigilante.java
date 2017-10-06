package com.ceiba.parqueadero;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import com.ceiba.parqueadero.domain.Parqueo;
import com.ceiba.parqueadero.domain.Tarifa;
import com.ceiba.parqueadero.domain.Vehiculo;
import com.ceiba.parqueadero.exception.ParqueaderoException;
import com.ceiba.parqueadero.repositorio.RepositorioParqueadero;
import com.ceiba.parqueadero.repositorio.RepositorioParqueo;
import com.ceiba.parqueadero.repositorio.RepositorioTarifa;
import com.ceiba.parqueadero.rule.Multa;
import com.ceiba.parqueadero.rule.Restriccion;
import com.ceiba.parqueadero.type.TipoCobro;
import com.ceiba.parqueadero.type.TipoVehiculo;

public class Vigilante {

  private RepositorioParqueadero repositorioParqueadero;

  private RepositorioParqueo repositorioParqueo;

  private RepositorioTarifa repositorioTarifa;

  private List<Restriccion> reglasIngreso;

  private List<Multa> multaSalida;

  public Vigilante(RepositorioParqueadero repositorioParqueadero,
      RepositorioParqueo repositorioParqueo, RepositorioTarifa repositorioTarifa) {
    super();
    this.repositorioParqueadero = repositorioParqueadero;
    this.repositorioParqueo = repositorioParqueo;
    this.repositorioTarifa = repositorioTarifa;
  }


  public String registrarIngresoVehiculo(Vehiculo vehiculo, LocalDateTime fechaIngreso) {
    String mensaje = "";

    validarIngresoVehiculo(vehiculo, fechaIngreso);

    Parqueo parqueo = new Parqueo(vehiculo, fechaIngreso);
    repositorioParqueo.parquear(parqueo);
    mensaje = "Su vehiculo a sido parqueado";

    return mensaje;
  }


  private void validarIngresoVehiculo(Vehiculo vehiculo, LocalDateTime fechaIngreso) {
    if (buscarVehiculoParqueado(vehiculo.getPlaca())) {
      throw new ParqueaderoException("El vehiculo ya se encuentra parqueado");
    }

    if (!validarReglasIngreso(vehiculo, fechaIngreso)) {
      throw new ParqueaderoException("No hay cumple con la reglas");
    }

    if (!validarCupoDisponible(vehiculo)) {
      throw new ParqueaderoException("No hay cupo Disponible");
    }
  }

  private boolean buscarVehiculoParqueado(String placa) {
    Parqueo parqueo = repositorioParqueo.buscarVehiculoParqueadoPorPlaca(placa);
    return (parqueo == null);
  }


  public List<Parqueo> mostrarVehiculosParqueados() {
    return repositorioParqueo.buscarVehiculoParqueado();
  }

  public double registrarSalidaVehiculo(String placa, LocalDateTime fechaSalida) {
    Parqueo parqueo = buscarYValidarVehiculoParqueado(placa);
    repositorioParqueo.retirarVehiculoParqueado(placa);
    return calcularValorTotal(parqueo, fechaSalida);
  }


  private Parqueo buscarYValidarVehiculoParqueado(String placa) {
    Parqueo parqueo = repositorioParqueo.buscarVehiculoParqueadoPorPlaca(placa);
    if (parqueo == null) {
      throw new ParqueaderoException("El vehiculo no se encuentra parqueado");
    }
    return parqueo;
  }

  private double calcularValorTotal(Parqueo parqueo, LocalDateTime fechaSalida) {
    double totalAPagar = 0;
    double totalPorDia = calcularValorTotalporDia(parqueo, fechaSalida);
    double totalPorHora = calcularValorTotalporHora(parqueo, fechaSalida);
    double multas = calcularMultas(parqueo.getVehiculo());
    totalAPagar = totalPorDia + totalPorHora + multas;
    return totalAPagar;

  }


  private double calcularValorTotalporHora(Parqueo parqueo, LocalDateTime fechaSalida) {
    Duration tiempoDeParqueo = Duration.between(parqueo.getFechaIngreso(), fechaSalida);
    Vehiculo vehiculo = parqueo.getVehiculo();
    Tarifa tarifaHora =
        repositorioTarifa.buscarTarifaParaVehiculo(vehiculo.getTipoVehiculo(), TipoCobro.HORA);
    long horaParqueo = tiempoDeParqueo.toHours();
    return (tarifaHora.getValor() * horaParqueo);

  }


  private double calcularValorTotalporDia(Parqueo parqueo, LocalDateTime fechaSalida) {
    Duration tiempoDeParqueo = Duration.between(parqueo.getFechaIngreso(), fechaSalida);
    Vehiculo vehiculo = parqueo.getVehiculo();
    Tarifa tarifaDia =
        repositorioTarifa.buscarTarifaParaVehiculo(vehiculo.getTipoVehiculo(), TipoCobro.DIA);
    long diasParqueo = tiempoDeParqueo.toDays();
    return (tarifaDia.getValor() * diasParqueo);

  }

  private double calcularMultas(Vehiculo vehiculo) {
    double multas = 0;
    if (multaSalida == null || multaSalida.isEmpty()) {
      return multas;
    }
    for (Multa multa : multaSalida) {
      multas += multa.cobrarMulta(vehiculo);

    }
    return multas;

  }

  private boolean validarReglasIngreso(Vehiculo vehiculo, LocalDateTime fechaIngreso) {
    if (reglasIngreso == null || reglasIngreso.isEmpty()) {
      return true;
    }
    for (Restriccion restriccion : reglasIngreso) {
      if (!restriccion.validarRestriccion(vehiculo, fechaIngreso)) {
        return false;
      }
    }
    return true;
  }

  private boolean validarCupoDisponible(Vehiculo vehiculo) {
    if (vehiculo.getTipoVehiculo() == null) {
      throw new ParqueaderoException("El vehiculo no es un  carro o  moto");
    }
    if (vehiculo.getTipoVehiculo().equals(TipoVehiculo.MOTO)) {
      return cupoDisponibleParaMoto();
    } else {
      return cupoDisponibleParaCarro();
    }

  }

  private boolean cupoDisponibleParaMoto() {
    long cantMaxMoto = repositorioParqueadero.cantidadMaxDeMoto();
    long cantAutoParqueados = repositorioParqueo.cantidadMotoParqueada();
    return (cantMaxMoto >= cantAutoParqueados);

  }

  private boolean cupoDisponibleParaCarro() {
    long cantMaxCarro = repositorioParqueadero.cantidadMaxDeMoto();
    long cantCarroParqueados = repositorioParqueo.cantidadCarroParqueados();
    return (cantMaxCarro >= cantCarroParqueados);
  }


  public void setReglasIngreso(List<Restriccion> reglasIngreso) {
    this.reglasIngreso = reglasIngreso;
  }


  public void setMultaSalida(List<Multa> multaSalida) {
    this.multaSalida = multaSalida;
  }


}
