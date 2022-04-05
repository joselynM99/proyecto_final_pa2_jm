package ec.edu.uce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Cobro;
import ec.edu.uce.modelo.NumeroReserva;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.Vehiculo;

@Service
public class GestorClienteServiceImpl implements IGestorClienteService {

	private static final Logger LOG = Logger.getRootLogger();

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IReservaService reservaService;

	@Autowired
	private ICobroService cobroService;

	@Override
	public List<Vehiculo> buscarVehiculosDisponibles(String marca, String modelo) {
		List<Vehiculo> vehiculos = this.vehiculoService.buscarVehiculosPorMarcaModelo(marca, modelo);
		Stream<Vehiculo> listaVehiculosDisp = vehiculos.stream().filter(v -> v.getEstado().equals("D"));
		return listaVehiculosDisp.toList();

	}

	@Override
	public boolean fechasSolapadas(String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		List<Reserva> reservasFechasSolapadas = this.reservaService.buscarPorReservasPorFecha(placa, fechaInicio,
				fechaFin);
		if (reservasFechasSolapadas.isEmpty() || reservasFechasSolapadas == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Cobro calcularValorAPagar(Vehiculo vehiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin) {

		Cobro cobro = new Cobro();

		Integer diasReserva = (int) ChronoUnit.DAYS.between(fechaInicio, fechaFin);

		BigDecimal subtotal = vehiculo.getValorPorDia().multiply(new BigDecimal(diasReserva));
		BigDecimal IVA = (subtotal.multiply(new BigDecimal(12))).divide(new BigDecimal(100));
		BigDecimal valorAPagar = subtotal.add(IVA);

		cobro.setSubtotal(subtotal);
		cobro.setValorIVA(IVA);
		cobro.setValorTotalAPagar(valorAPagar);

//		this.cobroService.insertarCobro(cobro);

		LOG.info("Vehiculo: " + vehiculo.getPlaca() + ", precio por dia: " + vehiculo.getValorPorDia()
				+ " El valor a pagar es: " + cobro.getValorTotalAPagar() + " por: " + diasReserva + " dias");
		LOG.info("Fechas: " + fechaInicio + " - " + fechaFin);

		return cobro;
	}

	@Override
	@Transactional
	public Reserva realizarReserva(Cobro cobro, Cliente cliente, Vehiculo vehiculo, LocalDateTime fechaInicio,
			LocalDateTime fechaFin) {

		Reserva reserva = new Reserva();
		reserva.setCliente(cliente);
		reserva.setEstado("G");
		reserva.setFechaInicio(fechaInicio);
		reserva.setFechaFinal(fechaFin);
		reserva.setVehiculo(vehiculo);
		reserva.setNumeroReserva(new NumeroReserva(cliente).getNumero());

		cobro.setFechaCobro(LocalDateTime.now());

		List<Reserva> listaReservas = cliente.getReservas();
		listaReservas.add(reserva);
		cliente.setReservas(listaReservas);
		this.reservaService.insertarReserva(reserva);
		cobro.setReserva(reserva);

		this.cobroService.insertarCobro(cobro);
//		reserva.setCobro(cobro);

		this.clienteService.actualizarCliente(cliente);
//		this.tarjetaCreditoService.actualizaTarjeta(tarjeta);

		return reserva;

	}

	@Override
	public void registrarComoCliente(Cliente cliente) {
		cliente.setRegistro("C");
		this.clienteService.insertarCliente(cliente);

	}

}
