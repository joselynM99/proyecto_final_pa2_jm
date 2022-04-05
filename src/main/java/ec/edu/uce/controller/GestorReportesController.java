package ec.edu.uce.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ec.edu.uce.modelo.ClienteReporteTO;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.ReservaReporteTO;
import ec.edu.uce.modelo.VehiculoReporteTOVIP;
import ec.edu.uce.service.IGestorReportesService;

@Controller
@RequestMapping("/reportes/")
public class GestorReportesController {

	private static final Logger LOG = Logger.getRootLogger();
	@Autowired
	private IGestorReportesService reportesService;

	@GetMapping("reservas")
	public String obtenerPaginaReservasDatos(Reserva reserva) {
		return "reporte/obtenerReservas";
	}

	@GetMapping("reporteReservas")
	public String reporteReservas(Reserva reserva, Model modelo) {

		List<ReservaReporteTO> reservas = this.reportesService.resporteReservas(reserva.getFechaInicio(),
				reserva.getFechaFinal());

		modelo.addAttribute("reservas", reservas);

		return "reporte/listaReservasReporte";
	}

	@GetMapping("clientesVIP")
	public String obtenerPaginaClientesDatos(Model model) {
		List<ClienteReporteTO> clientes = this.reportesService.reporteClientesVIP();
		model.addAttribute("clientes", clientes);
		return "reporte/listaClientesVIP";
	}

	@GetMapping("reporteVehiculosVIP/{mes}/{anio}")
	public String reporteVehiculosVIP(@PathVariable("mes") String mes, @PathVariable("anio") String anio,
			Model modelo) {

		LOG.info(mes);

		List<VehiculoReporteTOVIP> vehiculos = this.reportesService.reporteVehiculosVIP(mes, anio);

		modelo.addAttribute("vehiculos", vehiculos);

		return "reporte/listaVehiculosReporte";
	}

}
