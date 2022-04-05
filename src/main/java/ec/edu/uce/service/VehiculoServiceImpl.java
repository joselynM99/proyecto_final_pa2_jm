package ec.edu.uce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.modelo.VehiculoReporteTOVIP;
import ec.edu.uce.repository.IVehiculoRepository;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepository vehiculoRepository;

	@Override
	public void insertarVehiculo(Vehiculo vehiculo) {
		this.vehiculoRepository.insertarVehiculo(vehiculo);
	}

	@Override
	public Vehiculo buscarVehiculoPorID(Integer id) {
		return this.vehiculoRepository.buscarVehiculoPorID(id);
	}

	@Override
	public void actualizarVehiculo(Vehiculo vehiculo) {
		this.vehiculoRepository.actualizarVehiculo(vehiculo);
	}

	@Override
	public void eliminarVehiculo(Integer id) {
		this.vehiculoRepository.eliminarVehiculo(id);
	}

	@Override
	public List<Vehiculo> buscarVehiculosPorMarcaModelo(String marca, String modelo) {
		return this.vehiculoRepository.buscarVehiculosPorMarcaModelo(marca, modelo);
	}

	@Override
	public Vehiculo buscarVehiculoPorPlaca(String placa) {
		return this.vehiculoRepository.buscarVehiculoPorPlaca(placa);
	}

	@Override
	public List<VehiculoReporteTOVIP> buscarVehiculosVIP(String mes, String anio) {
		return this.vehiculoRepository.buscarVehiculosVIP(mes, anio);
	}

}
