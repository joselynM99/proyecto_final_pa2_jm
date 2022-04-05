package ec.edu.uce.repository;

import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.ReservaReporteTO;
import ec.edu.uce.modelo.VehiculoReporteTO;

public interface IReservaRepository {

	void insertarReserva(Reserva reserva);

	Reserva buscarReservaPorID(Integer id);

	void actualizarReserva(Reserva reserva);

	void eliminarReserva(Integer id);

	List<Reserva> buscarPorReservasPorFecha(String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin);

	Reserva buscarReservaPorNumero(String numero);

	List<ReservaReporteTO> buscarPorFechaTO(LocalDateTime fechaInicio, LocalDateTime fechaFinal);
}
