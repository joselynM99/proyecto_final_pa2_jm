package ec.edu.uce.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.modelo.VehiculoReporteTOVIP;

@Repository
@Transactional
public class VehiculoRepositoryImpl implements IVehiculoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarVehiculo(Vehiculo vehiculo) {
		this.entityManager.persist(vehiculo);
	}

	@Override
	public Vehiculo buscarVehiculoPorID(Integer id) {
		return this.entityManager.find(Vehiculo.class, id);
	}

	@Override
	public void actualizarVehiculo(Vehiculo vehiculo) {
		this.entityManager.merge(vehiculo);
	}

	@Override
	public void eliminarVehiculo(Integer id) {
		Vehiculo vehiculoABorrar = this.buscarVehiculoPorID(id);
		this.entityManager.remove(vehiculoABorrar);
	}

	@Override
	public List<Vehiculo> buscarVehiculosPorMarcaModelo(String marca, String modelo) {
		TypedQuery<Vehiculo> myQuery = this.entityManager
				.createQuery("SELECT v FROM Vehiculo v WHERE v.marca =: marca AND v.modelo =: modelo", Vehiculo.class);

		myQuery.setParameter("marca", marca);

		myQuery.setParameter("modelo", modelo);

		return myQuery.getResultList();
	}

	@Override
	public Vehiculo buscarVehiculoPorPlaca(String placa) {
		TypedQuery<Vehiculo> myQuery = this.entityManager.createQuery("SELECT v FROM Vehiculo v WHERE v.placa =: placa",
				Vehiculo.class);

		myQuery.setParameter("placa", placa);

		return myQuery.getSingleResult();
	}

	@Override
	public List<VehiculoReporteTOVIP> buscarVehiculosVIP(String mes, String anio) {

		String f = "01/" + mes + "/" + anio;

		LocalDate fdate = LocalDate.parse(f, DateTimeFormatter.ofPattern("d/MM/yyyy"));
		LocalDateTime fecha = fdate.atStartOfDay();
		LocalDateTime fecha2 = fecha.plusMonths(1);

		TypedQuery<VehiculoReporteTOVIP> myQuery = this.entityManager.createQuery(
				"SELECT NEW ec.edu.uce.modelo.VehiculoReporteTOVIP(v.placa, SUM(c.valorTotalAPagar), SUM(c.valorIVA)) "
						+ " FROM Reserva r JOIN r.vehiculo v JOIN r.cobro c"
						+ " WHERE r.fechaInicio >=: fecha AND r.fechaInicio <=:fecha2 " + " GROUP BY v.placa"
						+ " ORDER BY SUM(c.valorTotalAPagar) desc",
				VehiculoReporteTOVIP.class);

		myQuery.setParameter("fecha", fecha);
		myQuery.setParameter("fecha2", fecha2);

		return myQuery.getResultList();
	}

}
