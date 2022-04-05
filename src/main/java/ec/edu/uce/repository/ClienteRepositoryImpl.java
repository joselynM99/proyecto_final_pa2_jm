package ec.edu.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.ClienteReporteTO;

@Repository
@Transactional
public class ClienteRepositoryImpl implements IClienteRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarCliente(Cliente cliente) {
		this.entityManager.persist(cliente);
	}

	@Override
	public Cliente buscarClientePorID(Integer id) {
		return this.entityManager.find(Cliente.class, id);
	}

	@Override
	public void actualizarCliente(Cliente cliente) {
		this.entityManager.merge(cliente);
	}

	@Override
	public void eliminarCliente(Integer id) {
		Cliente clienteABorrar = this.buscarClientePorID(id);
		this.entityManager.remove(clienteABorrar);
	}

	@Override
	public Cliente buscarClientePorCedula(String cedula) {
		TypedQuery<Cliente> myQuery = this.entityManager.createQuery("SELECT c FROM Cliente c WHERE c.cedula =: cedula",
				Cliente.class);

		myQuery.setParameter("cedula", cedula);

		return myQuery.getSingleResult();
	}

	@Override
	public List<Cliente> buscarClienteTodos() {
		TypedQuery<Cliente> myQuery = this.entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);

		return myQuery.getResultList();
	}

	@Override
	public List<ClienteReporteTO> buscarClienteVIP() {
		TypedQuery<ClienteReporteTO> myQuery = this.entityManager.createQuery(
				"SELECT NEW ec.edu.uce.modelo.ClienteReporteTO(c.cedula, SUM(p.valorTotalAPagar), SUM(p.valorIVA)) "
						+ "FROM Reserva r JOIN r.cliente c JOIN r.cobro p GROUP BY c.cedula ORDER BY SUM(p.valorTotalAPagar) desc",
				ClienteReporteTO.class);

		return myQuery.getResultList();
	}

}
