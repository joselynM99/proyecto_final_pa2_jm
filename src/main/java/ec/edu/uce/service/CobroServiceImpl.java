package ec.edu.uce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Cobro;
import ec.edu.uce.repository.ICobroRepository;

@Service
public class CobroServiceImpl implements ICobroService{
	
	@Autowired
	private ICobroRepository cobroRepository;

	@Override
	public void insertarCobro(Cobro cobro) {
		this.cobroRepository.insertarCobro(cobro);
	}

	@Override
	public Cobro buscarCobroPorID(Integer id) {
		return this.cobroRepository.buscarCobroPorID(id);
	}

	@Override
	public void actualizaCobro(Cobro cobro) {
		this.cobroRepository.actualizaCobro(cobro);
	}

	@Override
	public void eliminarCobro(Integer id) {
		this.cobroRepository.eliminarCobro(id);
	}

}
