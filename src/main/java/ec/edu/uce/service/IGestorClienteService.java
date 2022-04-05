package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Cobro;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.Vehiculo;

public interface IGestorClienteService {

	List<Vehiculo> buscarVehiculosDisponibles(String marca, String modelo);

	void registrarComoCliente(Cliente cliente);

	boolean fechasSolapadas(String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin);

	Cobro calcularValorAPagar(Vehiculo vehiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin);

	Reserva realizarReserva(Cobro cobro, Cliente cliente, Vehiculo vehiculo, LocalDateTime fechaInicio,
			LocalDateTime fechaFin);

}
