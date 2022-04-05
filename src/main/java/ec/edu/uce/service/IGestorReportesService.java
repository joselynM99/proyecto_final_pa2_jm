package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.modelo.ClienteReporteTO;
import ec.edu.uce.modelo.ReservaReporteTO;
import ec.edu.uce.modelo.VehiculoReporteTOVIP;

public interface IGestorReportesService {

	List<ReservaReporteTO> resporteReservas(LocalDateTime fechaInicio, LocalDateTime fechaFinal);

	List<ClienteReporteTO> reporteClientesVIP();

	List<VehiculoReporteTOVIP> reporteVehiculosVIP(String mes, String anio);

}
