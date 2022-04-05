package ec.edu.uce.repository;

import java.util.List;

import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.modelo.VehiculoReporteTOVIP;

public interface IVehiculoRepository {

	void insertarVehiculo(Vehiculo vehiculo);

	Vehiculo buscarVehiculoPorID(Integer id);

	void actualizarVehiculo(Vehiculo vehiculo);

	void eliminarVehiculo(Integer id);

	List<Vehiculo> buscarVehiculosPorMarcaModelo(String marca, String modelo);

	Vehiculo buscarVehiculoPorPlaca(String placa);

	List<VehiculoReporteTOVIP> buscarVehiculosVIP(String mes, String anio);

}
