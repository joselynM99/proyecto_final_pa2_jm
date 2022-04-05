package ec.edu.uce.repository;

import ec.edu.uce.modelo.Cobro;

public interface ICobroRepository {

	void insertarCobro(Cobro cobro);

	Cobro buscarCobroPorID(Integer id);

	void actualizaCobro(Cobro cobro);

	void eliminarCobro(Integer id);

}
