package ec.edu.uce.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Cobro;

@Repository
@Transactional
public class CobroRepositoryImpl implements ICobroRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertarCobro(Cobro cobro) {
		this.entityManager.persist(cobro);
	}

	@Override
	public Cobro buscarCobroPorID(Integer id) {
		return this.entityManager.find(Cobro.class, id);
	}

	@Override
	public void actualizaCobro(Cobro cobro) {
		this.entityManager.merge(cobro);
	}

	@Override
	public void eliminarCobro(Integer id) {
		Cobro cobroABorrar = this.buscarCobroPorID(id);
		this.entityManager.remove(cobroABorrar);
	}

}
