package com.ceiba.parqueadero;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
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


@RunWith(MockitoJUnitRunner.class)
public class VigilanteTest {

  private Vigilante vigilante;
  @Mock
  private RepositorioParqueadero repositorioParqueadero;
  @Mock
  private RepositorioParqueo repositorioParqueo;
  @Mock
  private RepositorioTarifa repositorioTarifa;


  @Spy
  private List<Restriccion> reglasIngreso = new ArrayList<>();
  @Spy
  private List<Multa> multaSalida = new ArrayList<>();;

  private Tarifa tarifa;

  private Vehiculo vehiculo;

  private Parqueo parqueo;

  @Before
  public void SetUp() {
    crearDominiosPorDefecto();
    when(repositorioTarifa.buscarTarifaParaVehiculo(any(), any())).thenReturn(tarifa);
    when(repositorioParqueo.buscarVehiculoParqueadoPorPlaca(anyString())).thenReturn(parqueo);
  }


  @Test
  public void buscarVehiculoParqueados() {
    // arrange
    List<Parqueo> vehiculosParqueadosMock = new ArrayList<>();
    when(repositorioParqueo.buscarVehiculoParqueado()).thenReturn(vehiculosParqueadosMock);

    // act
    List<Parqueo> vehiculosParqueados = vigilante.mostrarVehiculosParqueados();

    // assert
    assertEquals(vehiculosParqueadosMock, vehiculosParqueados);

  }

  @Test
  public void registrarUnCarroConCupoParqueadero() {
    // arrange
    LocalDateTime fechaIngreso = LocalDateTime.now();
    habilitarCupoParqueadero();
    String mensajeEsperado = "Su vehiculo a sido parqueado";

    // act
    String mensajeActual = vigilante.registrarIngresoVehiculo(vehiculo, fechaIngreso);

    // assert
    assertEquals(mensajeEsperado, mensajeActual);
  }

  @Test(expected = ParqueaderoException.class)
  public void registrarUnVehiculoSinCupoParqueadero() {
    // arrange
    LocalDateTime fechaIngreso = LocalDateTime.now();
    deshabilitarCupoParqueadero();

    // act
    vigilante.registrarIngresoVehiculo(vehiculo, fechaIngreso);

  }

  @Test(expected = ParqueaderoException.class)
  public void registrarUnVehiculoParqueado() {
    // arrange
    LocalDateTime fechaIngreso = LocalDateTime.now();
    habilitarCupoParqueadero();
    String mensajeEsperado = "Su vehiculo a sido parqueado";
    when(repositorioParqueo.buscarVehiculoParqueadoPorPlaca(anyString())).thenReturn(null);
    // act
    String mensajeActual = vigilante.registrarIngresoVehiculo(vehiculo, fechaIngreso);

    // assert
    assertEquals(mensajeEsperado, mensajeActual);
  }

  @Test
  public void registrarUnMotoConCupoParqueadero() {
    // arrange
    vehiculo.setTipoVehiculo(TipoVehiculo.MOTO);
    LocalDateTime fechaIngreso = LocalDateTime.now();
    habilitarCupoParqueadero();
    String mensajeEsperado = "Su vehiculo a sido parqueado";

    // act
    String mensajeActual = vigilante.registrarIngresoVehiculo(vehiculo, fechaIngreso);

    // assert
    assertEquals(mensajeEsperado, mensajeActual);
  }

  @Test(expected = ParqueaderoException.class)
  public void registrarVehiculoSinTipoConCupoParqueadero() {
    // arrange
    vehiculo.setTipoVehiculo(null);
    LocalDateTime fechaIngreso = LocalDateTime.now();
    habilitarCupoParqueadero();
    String mensajeEsperado = "Su vehiculo a sido parqueado";

    // act
    String mensajeActual = vigilante.registrarIngresoVehiculo(vehiculo, fechaIngreso);

    // assert
    assertEquals(mensajeEsperado, mensajeActual);
  }

  @Test
  public void RegistrarSalidaVehiculo() {
    // arrange
    double valorEsperado = 0.0;
    LocalDateTime fechaSalida = LocalDateTime.now();
    // act
    double valorActual = vigilante.registrarSalidaVehiculo(vehiculo.getPlaca(), fechaSalida);

    // assert
    assertEquals(valorEsperado, valorActual, 0.0);
  }

  private void crearDominiosPorDefecto() {
    vigilante = new Vigilante(repositorioParqueadero, repositorioParqueo, repositorioTarifa);
    vehiculo = new Vehiculo("AAA365", TipoVehiculo.CARRO);
    tarifa = new Tarifa(TipoVehiculo.CARRO, TipoCobro.DIA, 5000);
    LocalDateTime fechaIngreso = LocalDateTime.now();
    parqueo = new Parqueo(vehiculo, fechaIngreso);
  }



  private void habilitarCupoParqueadero() {
    long cantidadCarrosParqueados = 0;
    long cantidadMaxCarros = 10;
    when(repositorioParqueo.cantidadCarroParqueados()).thenReturn(cantidadCarrosParqueados);
    when(repositorioParqueadero.cantidadMaxDeCarro()).thenReturn(cantidadMaxCarros);
  }

  private void deshabilitarCupoParqueadero() {
    long cantidadCarrosParqueados = 10;
    long cantidadMaxCarros = 10;
    when(repositorioParqueo.cantidadCarroParqueados()).thenReturn(cantidadCarrosParqueados);
    when(repositorioParqueadero.cantidadMaxDeCarro()).thenReturn(cantidadMaxCarros);
  }

}
