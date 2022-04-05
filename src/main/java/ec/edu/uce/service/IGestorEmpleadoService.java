package ec.edu.uce.service;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.Vehiculo;

public interface IGestorEmpleadoService {

	void registrarClienteComoEmpleado(Cliente cliente);

	Cliente buscarCliente(String cedula);

	void ingreserVehiculo(Vehiculo vehiculo);

	Vehiculo buscarVehiculoPorPlaca(String placa);

	void retirarVehiculoReservado(Reserva reserva);

}
