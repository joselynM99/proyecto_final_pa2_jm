package ec.edu.uce.service;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Cobro;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.Vehiculo;

@SpringBootTest
@Transactional
@Rollback(true)
class PruebasUnitarias {

	@Autowired
	private IGestorClienteService gestorClienteService;

	@Autowired
	private IGestorEmpleadoService gestorEmpleadoService;

	@Autowired
	private IGestorReportesService reportesService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IReservaService reservaService;

	@Test
	void testClienteVehiculosDisponibles() {

		List<Vehiculo> v = this.gestorClienteService.buscarVehiculosDisponibles("Chevrolet", "Aveo");

		assertEquals(v.get(0).getPlaca(), "PFR923");
	}

	@Test
	void testClienteVehiculosDisponibles2() {

		List<Vehiculo> v = this.gestorClienteService.buscarVehiculosDisponibles("Chevrolet", "Aveo");

		assertEquals(v.get(0).getEstado(), "D");
	}

	@Test
	void testClienteFechasSolapadas() {

		LocalDateTime fechaInicio = LocalDateTime.of(2022, Month.APRIL, 14, 0, 0);
		LocalDateTime fechaFinal = LocalDateTime.of(2022, Month.APRIL, 29, 0, 0);

		boolean b = this.gestorClienteService.fechasSolapadas("PFR923", fechaInicio, fechaFinal);

		assertEquals(b, true);
	}

	@Test
	void testClienteFechasSolapadas2() {

		LocalDateTime fechaInicio = LocalDateTime.of(2022, Month.DECEMBER, 11, 0, 0);
		LocalDateTime fechaFinal = LocalDateTime.of(2022, Month.DECEMBER, 30, 0, 0);

		boolean b = this.gestorClienteService.fechasSolapadas("PFR923", fechaInicio, fechaFinal);

		assertEquals(b, false);
	}

	@Test
	void testClienteFechasSolapadas3() {

		LocalDateTime fechaInicio = LocalDateTime.of(2022, Month.APRIL, 11, 0, 0);
		LocalDateTime fechaFinal = LocalDateTime.of(2022, Month.DECEMBER, 30, 0, 0);

		boolean b = this.gestorClienteService.fechasSolapadas("PFR923", fechaInicio, fechaFinal);

		assertTrue(b);
	}

	@Test
	void testCalcularValorAPagar() {

		LocalDateTime fechaInicio = LocalDateTime.of(2022, Month.DECEMBER, 20, 0, 0);
		LocalDateTime fechaFinal = LocalDateTime.of(2022, Month.DECEMBER, 30, 0, 0);

		Vehiculo vehiculo = this.vehiculoService.buscarVehiculoPorPlaca("PFR923");

		Cobro cobro = this.gestorClienteService.calcularValorAPagar(vehiculo, fechaInicio, fechaFinal);

		assertEquals(cobro.getValorIVA(), cobro.getValorTotalAPagar().subtract(cobro.getSubtotal()));

	}

	@Test
	void testCalcularValorAPagar2() {

		LocalDateTime fechaInicio = LocalDateTime.of(2022, Month.DECEMBER, 20, 0, 0);
		LocalDateTime fechaFinal = LocalDateTime.of(2022, Month.DECEMBER, 30, 0, 0);

		Vehiculo vehiculo = this.vehiculoService.buscarVehiculoPorPlaca("PFR923");

		Cobro cobro = this.gestorClienteService.calcularValorAPagar(vehiculo, fechaInicio, fechaFinal);

		assertEquals(cobro.getValorTotalAPagar(), cobro.getValorIVA().add(cobro.getSubtotal()));

	}

	@Test
	void testCalcularValorAPagar3() {

		LocalDateTime fechaInicio = LocalDateTime.of(2022, Month.DECEMBER, 15, 0, 0);
		LocalDateTime fechaFinal = LocalDateTime.of(2023, Month.DECEMBER, 20, 0, 0);

		Vehiculo vehiculo = this.vehiculoService.buscarVehiculoPorPlaca("PFR923");

		Cobro cobro = this.gestorClienteService.calcularValorAPagar(vehiculo, fechaInicio, fechaFinal);

		assertEquals(cobro.getSubtotal(), cobro.getValorTotalAPagar().subtract(cobro.getValorIVA()));

	}

	@Test
	void testCalcularValorAPagar4() {

		LocalDateTime fechaInicio = LocalDateTime.of(2022, Month.DECEMBER, 20, 0, 0);
		LocalDateTime fechaFinal = LocalDateTime.of(2022, Month.DECEMBER, 30, 0, 0);

		Vehiculo vehiculo = this.vehiculoService.buscarVehiculoPorPlaca("PFR923");

		Cobro cobro = this.gestorClienteService.calcularValorAPagar(vehiculo, fechaInicio, fechaFinal);

		assertEquals(cobro.getSubtotal(), cobro.getValorTotalAPagar().subtract(cobro.getValorIVA()));

	}

	@Test
	void testReserva() {
		LocalDateTime fechaInicio = LocalDateTime.of(2022, Month.DECEMBER, 20, 0, 0);
		LocalDateTime fechaFinal = LocalDateTime.of(2022, Month.DECEMBER, 30, 0, 0);

		Vehiculo vehiculo = this.vehiculoService.buscarVehiculoPorPlaca("PFR923");

		Cobro cobro = this.gestorClienteService.calcularValorAPagar(vehiculo, fechaInicio, fechaFinal);

		Cliente c = this.clienteService.buscarClientePorCedula("1548230256");

		Reserva reserva = this.gestorClienteService.realizarReserva(cobro, c, vehiculo, fechaInicio, fechaFinal);

		assertEquals(reserva.getCliente().getGenero(), "F");
	}

	@Test
	void testReserva2() {
		LocalDateTime fechaInicio = LocalDateTime.of(2022, Month.DECEMBER, 20, 0, 0);
		LocalDateTime fechaFinal = LocalDateTime.of(2022, Month.DECEMBER, 30, 0, 0);

		Vehiculo vehiculo = this.vehiculoService.buscarVehiculoPorPlaca("PFR923");

		Cobro cobro = this.gestorClienteService.calcularValorAPagar(vehiculo, fechaInicio, fechaFinal);

		Cliente c = this.clienteService.buscarClientePorCedula("1548230256");

		Reserva reserva = this.gestorClienteService.realizarReserva(cobro, c, vehiculo, fechaInicio, fechaFinal);

		assertEquals(reserva.getEstado(), "G");
	}

	@Test
	void registrarComoCliente() {
		Cliente cliente = new Cliente();
		cliente.setApellido("dadafa");
		cliente.setNombre("Ajda");
		cliente.setCedula("121133333");
		cliente.setFechaNacimiento(LocalDateTime.of(1999, Month.DECEMBER, 20, 0, 0));
		this.gestorClienteService.registrarComoCliente(cliente);
		Cliente c = this.clienteService.buscarClientePorCedula("121133333");

		assertEquals(c.getRegistro(), "C");
	}

	@Test
	void registrarComoCliente2() {
		Cliente cliente = new Cliente();
		cliente.setApellido("dadafa");
		cliente.setNombre("Ajda");
		cliente.setCedula("121133333");
		cliente.setFechaNacimiento(LocalDateTime.of(1999, Month.DECEMBER, 20, 0, 0));
		this.gestorClienteService.registrarComoCliente(cliente);
		Cliente c = this.clienteService.buscarClientePorCedula("121133333");

		assertEquals(c.getApellido(), "dadafa");
	}

	// Empleados

	@Test
	void registrarClienteComoEmpleado() {
		Cliente cliente = new Cliente();
		cliente.setApellido("dadafa");
		cliente.setNombre("Ajda");
		cliente.setCedula("121133333");
		cliente.setFechaNacimiento(LocalDateTime.of(1999, Month.DECEMBER, 20, 0, 0));
		this.gestorEmpleadoService.registrarClienteComoEmpleado(cliente);
		Cliente c = this.clienteService.buscarClientePorCedula("121133333");

		assertEquals(c.getRegistro(), "E");
	}

	@Test
	void registrarClienteComoEmpleado2() {
		Cliente cliente = new Cliente();
		cliente.setApellido("dadafa");
		cliente.setNombre("Ajda");
		cliente.setCedula("121133333");
		cliente.setFechaNacimiento(LocalDateTime.of(1999, Month.DECEMBER, 20, 0, 0));
		this.gestorEmpleadoService.registrarClienteComoEmpleado(cliente);
		Cliente c = this.clienteService.buscarClientePorCedula("121133333");

		assertEquals(c.getCedula(), "121133333");
	}

	@Test
	void buscarCliente() {
		Cliente c = this.gestorEmpleadoService.buscarCliente("17548339345");

		assertEquals(c.getApellido(), "Rojas");
	}

	@Test
	void buscarCliente2() {
		Cliente c = this.gestorEmpleadoService.buscarCliente("1433566620");

		assertEquals(c.getGenero(), "F");
	}

	@Test
	void ingreserVehiculo() {
		Vehiculo v = new Vehiculo();
		v.setPlaca("FSS155");
		v.setValorPorDia(new BigDecimal(60));
		this.gestorEmpleadoService.ingreserVehiculo(v);

		assertEquals(this.vehiculoService.buscarVehiculoPorPlaca("FSS155").getValorPorDia(), new BigDecimal(60));
	}

	@Test
	void retirarVehiculo() {
		Reserva r = this.reservaService.buscarReservaPorNumero("851555901152");

		this.gestorEmpleadoService.retirarVehiculoReservado(r);

		Reserva r2 = this.reservaService.buscarReservaPorNumero("851555901152");

		assertEquals(r2.getEstado(), "E");
	}

	@Test
	void retirarVehiculo2() {
		Reserva r = this.reservaService.buscarReservaPorNumero("343371388052");

		this.gestorEmpleadoService.retirarVehiculoReservado(r);

		Reserva r2 = this.reservaService.buscarReservaPorNumero("343371388052");

		assertEquals(r2.getEstado(), "E");
	}

}
