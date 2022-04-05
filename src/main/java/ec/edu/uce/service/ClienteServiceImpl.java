package ec.edu.uce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.ClienteReporteTO;
import ec.edu.uce.repository.IClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepository clienteRepository;

	@Override
	public void insertarCliente(Cliente cliente) {
		this.clienteRepository.insertarCliente(cliente);
	}

	@Override
	public Cliente buscarClientePorID(Integer id) {
		return this.clienteRepository.buscarClientePorID(id);
	}

	@Override
	public void actualizarCliente(Cliente cliente) {
		this.clienteRepository.actualizarCliente(cliente);
	}

	@Override
	public void eliminarCliente(Integer id) {
		this.clienteRepository.eliminarCliente(id);
	}

	@Override
	public Cliente buscarClientePorCedula(String cedula) {
		return this.clienteRepository.buscarClientePorCedula(cedula);
	}

	@Override
	public List<Cliente> buscarClienteTodos() {
		return this.clienteRepository.buscarClienteTodos();
	}

	@Override
	public List<ClienteReporteTO> buscarClienteVIP() {
		return this.clienteRepository.buscarClienteVIP();
	}

}
