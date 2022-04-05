package ec.edu.uce.repository;

import java.util.List;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.ClienteReporteTO;

public interface IClienteRepository {

	void insertarCliente(Cliente cliente);

	Cliente buscarClientePorID(Integer id);

	void actualizarCliente(Cliente cliente);

	void eliminarCliente(Integer id);

	Cliente buscarClientePorCedula(String cedula);

	List<Cliente> buscarClienteTodos();

	List<ClienteReporteTO> buscarClienteVIP();
}
