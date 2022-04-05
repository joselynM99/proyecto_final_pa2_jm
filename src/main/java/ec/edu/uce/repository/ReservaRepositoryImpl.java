package ec.edu.uce.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.ReservaReporteTO;

@Repository
@Transactional
public class ReservaRepositoryImpl implements IReservaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarReserva(Reserva reserva) {
		this.entityManager.persist(reserva);
	}

	@Override
	public Reserva buscarReservaPorID(Integer id) {
		return this.entityManager.find(Reserva.class, id);
	}

	@Override
	public void actualizarReserva(Reserva reserva) {
		this.entityManager.merge(reserva);
	}

	@Override
	public void eliminarReserva(Integer id) {
		Reserva reservaABorrar = this.buscarReservaPorID(id);
		this.entityManager.remove(reservaABorrar);
	}

	@Override
	public List<Reserva> buscarPorReservasPorFecha(String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
				"SELECT r FROM Reserva r JOIN FETCH r.vehiculo v WHERE v.placa=:placa AND ((r.fechaInicio <:fechaInicio AND r.fechaFinal>:fechaInicio) "
						+ "OR (r.fechaInicio <: fechaFin AND r.fechaFinal >=:fechaFin) OR (r.fechaInicio >:fechaInicio AND r.fechaFinal <:fechaFin))",
				Reserva.class);

		myQuery.setParameter("fechaInicio", fechaInicio);

		myQuery.setParameter("placa", placa);

		myQuery.setParameter("fechaFin", fechaFin);

		return myQuery.getResultList();
	}

	@Override
	public Reserva buscarReservaPorNumero(String numeroReserva) {

		TypedQuery<Reserva> myQuery = this.entityManager
				.createQuery("SELECT r FROM Reserva r WHERE r.numeroReserva=:numeroReserva", Reserva.class);

		myQuery.setParameter("numeroReserva", numeroReserva);

		return myQuery.getSingleResult();

	}

	@Override
	public List<ReservaReporteTO> buscarPorFechaTO(LocalDateTime fechaInicio, LocalDateTime fechaFinal) {

		TypedQuery<ReservaReporteTO> myQuery = this.entityManager.createQuery(
				"SELECT NEW ec.edu.uce.modelo.ReservaReporteTO(r.fechaInicio, r.fechaFinal, r.estado, r.numeroReserva"
						+ ", c.cedula, c.nombre, p.fechaCobro, v.modelo, v.valorPorDia, v.placa) "
						+ "FROM Reserva r JOIN r.vehiculo v JOIN r.cliente c JOIN r.cobro p "
						+ "WHERE r.fechaInicio >=: fechaInicio AND r.fechaInicio<=:fechaFinal",
				ReservaReporteTO.class);

		myQuery.setParameter("fechaInicio", fechaInicio);
		myQuery.setParameter("fechaFinal", fechaFinal);

		return myQuery.getResultList();
	}

}
