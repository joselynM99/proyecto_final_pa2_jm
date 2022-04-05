package ec.edu.uce.service;

import ec.edu.uce.modelo.Cobro;

public interface ICobroService {

	void insertarCobro(Cobro cobro);

	Cobro buscarCobroPorID(Integer id);

	void actualizaCobro(Cobro cobro);

	void eliminarCobro(Integer id);

}
