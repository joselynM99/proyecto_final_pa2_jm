package ec.edu.uce.service;

import java.util.List;

import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.modelo.VehiculoReporteTOVIP;

public interface IVehiculoService {

	void insertarVehiculo(Vehiculo vehiculo);

	Vehiculo buscarVehiculoPorID(Integer id);

	void actualizarVehiculo(Vehiculo vehiculo);

	void eliminarVehiculo(Integer id);

	List<Vehiculo> buscarVehiculosPorMarcaModelo(String marca, String modelo);

	Vehiculo buscarVehiculoPorPlaca(String placa);

	List<VehiculoReporteTOVIP> buscarVehiculosVIP(String mes, String anio);

}
