package ec.edu.uce.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Cobro;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.ReservaTO;
import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.modelo.VehiculoTO;
import ec.edu.uce.service.IClienteService;
import ec.edu.uce.service.IGestorClienteService;
import ec.edu.uce.service.IReservaService;
import ec.edu.uce.service.IVehiculoService;

@Controller
@RequestMapping("/clientes/")
public class GestorClienteController {
	private static final Logger LOG = Logger.getRootLogger();

	@Autowired
	private IGestorClienteService gestorClienteService;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IReservaService reservaService;

	@GetMapping("buscarVehiculos")
	public String obtenerDatosVehiculo(Vehiculo vehiculo) {

		return "cliente/vehiculoDatos";
	}

	@GetMapping("vehiculosDisponibles")
	public String obtenerUsuario(VehiculoTO vehiculo, Model model) {
		List<Vehiculo> vehiculos = this.gestorClienteService.buscarVehiculosDisponibles(vehiculo.getMarca(),
				vehiculo.getModelo());

		model.addAttribute("vehiculos", vehiculos);
		return "cliente/vehiculosDisponibles";
	}

	@GetMapping("registroCliente")
	public String obtenerPaginaIngresoDatos(Cliente cliente) {

		return "cliente/clienteNuevo";
	}

	@PostMapping("insertarCliente")
	public String insertarCliente(Cliente cliente, BindingResult result, Model modelo,
			RedirectAttributes redirectAttributes) {

		this.gestorClienteService.registrarComoCliente(cliente);

		return "cliente/registroClienteExitoso";
	}

	@GetMapping("generarReserva")
	public String obtenerPaginaDatosReserva(Reserva reserva) {
		return "cliente/paginaFechas";

	}

	@GetMapping("verificarFechas")
	public String comprobarFechas(ReservaTO reserva, Model modelo, RedirectAttributes redirectAttributes) {

		LOG.info(reserva.getCliente().getCedula());
		Cliente cliente = this.clienteService.buscarClientePorCedula(reserva.getCliente().getCedula());
		LOG.info(cliente.getCedula());
		Vehiculo vehiculo = this.vehiculoService.buscarVehiculoPorPlaca(reserva.getVehiculo().getPlaca());
		if (this.gestorClienteService.fechasSolapadas(vehiculo.getPlaca(), reserva.getFechaInicio(),
				reserva.getFechaFinal())) {

			Reserva res = this.reservaService
					.buscarPorReservasPorFecha(vehiculo.getPlaca(), reserva.getFechaInicio(), reserva.getFechaFinal())
					.get(0);
			LocalDateTime fechaDisponible = res.getFechaFinal().plusHours(5);
			LOG.info(res.getFechaFinal().plusHours(5).toString());

			modelo.addAttribute("fechaDisponible", fechaDisponible);
			return "cliente/FechaNoDisponible";
		} else {
			Cobro cobro = this.gestorClienteService.calcularValorAPagar(vehiculo, reserva.getFechaInicio(),
					reserva.getFechaFinal());

			reserva.setCobro(cobro);
			LOG.info(reserva.getCliente().getCedula());
			modelo.addAttribute("cobro", cobro);
			modelo.addAttribute("vehiculo", vehiculo);
			modelo.addAttribute("cliente", cliente);
			modelo.addAttribute("reserva", reserva);

			return "cliente/pagarReserva";
		}
	}

	@PostMapping("reservarVehiculo")
	public String reservar(Model model, ReservaTO reserva) {

		LOG.info(reserva.getFechaInicio().toString());
		LOG.info(reserva.getCobro().getNumeroTarjeta());

		Cobro cobro = reserva.getCobro();
		LOG.info(cobro.getSubtotal().toString());

		Cliente cliente = this.clienteService.buscarClientePorCedula(reserva.getCliente().getCedula());
		Vehiculo vehiculo = this.vehiculoService.buscarVehiculoPorPlaca(reserva.getVehiculo().getPlaca());

		this.gestorClienteService.realizarReserva(cobro, cliente, vehiculo, reserva.getFechaInicio(),
				reserva.getFechaFinal());
		return "cliente/reservaExitosa";
	}

}
