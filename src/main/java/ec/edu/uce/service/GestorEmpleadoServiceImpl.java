package ec.edu.uce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.Vehiculo;

@Service
public class GestorEmpleadoServiceImpl implements IGestorEmpleadoService {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IReservaService reservaService;

	@Override
	public void registrarClienteComoEmpleado(Cliente cliente) {

		cliente.setRegistro("E");
		this.clienteService.insertarCliente(cliente);

	}

	@Override
	public Cliente buscarCliente(String cedula) {
		return this.clienteService.buscarClientePorCedula(cedula);
	}

	@Override
	public void ingreserVehiculo(Vehiculo vehiculo) {
		this.vehiculoService.insertarVehiculo(vehiculo);
	}

	@Override
	public Vehiculo buscarVehiculoPorPlaca(String placa) {
		return this.vehiculoService.buscarVehiculoPorPlaca(placa);
	}

	@Override
	public void retirarVehiculoReservado(Reserva reserva) {

		reserva.setEstado("E");
		Vehiculo v = reserva.getVehiculo();
		v.setEstado("ND");

		this.vehiculoService.actualizarVehiculo(v);
		this.reservaService.actualizarReserva(reserva);

	}

}
