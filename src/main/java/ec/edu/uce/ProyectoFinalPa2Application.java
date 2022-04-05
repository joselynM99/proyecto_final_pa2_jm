package ec.edu.uce;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ec.edu.uce.modelo.ClienteReporteTO;
import ec.edu.uce.modelo.ReservaReporteTO;
import ec.edu.uce.modelo.VehiculoReporteTOVIP;
import ec.edu.uce.service.IClienteService;
import ec.edu.uce.service.IGestorClienteService;
import ec.edu.uce.service.IGestorReportesService;
import ec.edu.uce.service.IReservaService;
import ec.edu.uce.service.IVehiculoService;

@SpringBootApplication
public class ProyectoFinalPa2Application implements CommandLineRunner {

	private static final Logger LOG = Logger.getRootLogger();

	@Autowired
	private IGestorClienteService gestorClienteService;

	@Autowired
	private IReservaService reservaService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IGestorReportesService gestorReportesService;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalPa2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		this.gestorClienteService.reservarVehiculo("PH212", "455454", LocalDateTime.of(2022, Month.JUNE, 10, 0, 0),
//				LocalDateTime.of(2022, Month.JUNE, 29, 0, 0));
//
		List<ReservaReporteTO> reservas = this.gestorReportesService.resporteReservas(
				LocalDateTime.of(2021, Month.MARCH, 31, 0, 0), LocalDateTime.of(2023, Month.APRIL, 28, 0, 0));

		for (ReservaReporteTO r : reservas) {
			LOG.info("Reserva: " + r.toString());

		}

		List<ClienteReporteTO> clientesVIP = this.clienteService.buscarClienteVIP();
		for (ClienteReporteTO c : clientesVIP) {
			LOG.info("Reserva: " + c.toString());
		}

		List<VehiculoReporteTOVIP> vehiculosVIP = this.vehiculoService.buscarVehiculosVIP("04", "2022");
		for (VehiculoReporteTOVIP v : vehiculosVIP) {
			LOG.info("Reserva: " + v.toString());
		}

//		List<VehiculoReporteTOVIP> veh = this.gestorReportesService.reporteVehiculosVIP(4, 2022);
//		for (VehiculoReporteTOVIP v : veh) {
//			LOG.info("vehiculos: " + v.toString());
//		}

//		
//		Cliente cliente = new Cliente();
//		cliente.setApellido("Perez");
//		cliente.setCedula("1454875");
//		cliente.setGenero("F");
//		cliente.setNombre("Pamela");
//		cliente.setFechaNacimiento(LocalDateTime.of(2003, Month.AUGUST, 8, 0, 0));

//		this.gestorClienteService.registrarComoCliente(cliente);

//		List<Vehiculo> vehiculos = this.gestorClienteService.buscarVehiculosDisponibles("Chevrolet", "Aveo");
//
//		if (vehiculos != null) {
//			for (Vehiculo v : vehiculos) {
//				LOG.info(v.toString());
//			}
//		} else {
//			LOG.info("vacio");
//		}

	}

}
