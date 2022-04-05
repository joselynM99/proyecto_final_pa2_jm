package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.ClienteReporteTO;
import ec.edu.uce.modelo.ReservaReporteTO;
import ec.edu.uce.modelo.VehiculoReporteTOVIP;

@Service
public class GestorReportesServiceImpl implements IGestorReportesService {

	@Autowired
	private IReservaService reservaService;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Override
	public List<ReservaReporteTO> resporteReservas(LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
		return this.reservaService.buscarPorFechaTO(fechaInicio, fechaFinal);
	}

	@Override
	public List<ClienteReporteTO> reporteClientesVIP() {
		return this.clienteService.buscarClienteVIP();
	}

	@Override
	public List<VehiculoReporteTOVIP> reporteVehiculosVIP(String mes, String anio) {
		return this.vehiculoService.buscarVehiculosVIP(mes, anio);

	}

}
