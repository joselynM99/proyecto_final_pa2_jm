package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.ReservaReporteTO;
import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.repository.IReservaRepository;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IReservaRepository reservaRepository;

	@Override
	public void insertarReserva(Reserva reserva) {
		this.reservaRepository.insertarReserva(reserva);
	}

	@Override
	public Reserva buscarReservaPorID(Integer id) {
		return this.reservaRepository.buscarReservaPorID(id);
	}

	@Override
	public void actualizarReserva(Reserva reserva) {
		this.reservaRepository.actualizarReserva(reserva);
	}

	@Override
	public void eliminarReserva(Integer id) {
		this.reservaRepository.eliminarReserva(id);
	}

	@Override
	public List<Reserva> buscarPorReservasPorFecha(String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		return this.reservaRepository.buscarPorReservasPorFecha(placa, fechaInicio, fechaFin);
	}

	@Override
	public Reserva buscarReservaPorNumero(String numero) {
		return this.reservaRepository.buscarReservaPorNumero(numero);
	}

	@Override
	public List<ReservaReporteTO> buscarPorFechaTO(LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
		// TODO Auto-generated method stub
		return this.reservaRepository.buscarPorFechaTO(fechaInicio, fechaFinal);
	}
}
