package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.ReservaReporteTO;

public interface IReservaService {

	void insertarReserva(Reserva reserva);

	Reserva buscarReservaPorID(Integer id);

	void actualizarReserva(Reserva reserva);

	void eliminarReserva(Integer id);

	List<Reserva> buscarPorReservasPorFecha(String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin);

	Reserva buscarReservaPorNumero(String numero);
	
	List<ReservaReporteTO> buscarPorFechaTO(LocalDateTime fechaInicio, LocalDateTime fechaFinal);

}
