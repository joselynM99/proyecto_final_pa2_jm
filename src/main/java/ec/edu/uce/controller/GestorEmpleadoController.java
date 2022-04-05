package ec.edu.uce.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.service.IGestorEmpleadoService;
import ec.edu.uce.service.IReservaService;
import ec.edu.uce.service.IVehiculoService;

@Controller
@RequestMapping("/empleados/")
public class GestorEmpleadoController {

	private static final Logger LOG = Logger.getRootLogger();

	@Autowired
	private IGestorEmpleadoService gestorEmpleadoService;

	@Autowired
	private IReservaService reservaService;

	@Autowired
	private IVehiculoService vehiculoService;

	@GetMapping("registroCliente")
	public String obtenerPaginaIngresoDatos(Cliente cliente) {

		return "empleado/clienteNuevo";
	}

	@PostMapping("insertarCliente")
	public String insertarCliente(Cliente cliente, BindingResult result, Model modelo,
			RedirectAttributes redirectAttributes) {

		this.gestorEmpleadoService.registrarClienteComoEmpleado(cliente);

		return "cliente/registroClienteExitoso";
	}

	@GetMapping("registroVehiculo")
	public String obtenerPaginaDatosVehiculo(Vehiculo Vehiculo) {
		return "empleado/vehiculoNuevo";
	}

	@PostMapping("insertarVehiculo")
	public String insertarVehiculo(Vehiculo vehiculo, BindingResult result, Model modelo,
			RedirectAttributes redirectAttributes) {

		this.gestorEmpleadoService.ingreserVehiculo(vehiculo);

		return "empleado/registroVehiculoExitoso";
	}

	@GetMapping("buscarCliente")
	public String buscarCliente(Cliente cliente) {
		return "empleado/clienteCedula";
	}

	@GetMapping("cliente")
	public String obtenerCliente(Cliente cliente, Model model) {
		Cliente c = this.gestorEmpleadoService.buscarCliente(cliente.getCedula());
		model.addAttribute("cliente", c);
		return "empleado/clienteInformacion";
	}

	@GetMapping("buscarVehiculo")
	public String obtenerPaginaBuscarPorPlaca(Model model, Vehiculo vehiculo) {
		return "empleado/vehiculoPlaca";
	}

	@GetMapping("vehiculo")
	public String obtenerVehiculoDatos(Model model, Vehiculo vehiculo) {
		Vehiculo v = this.gestorEmpleadoService.buscarVehiculoPorPlaca(vehiculo.getPlaca());
		model.addAttribute("vehiculo", v);
		return "empleado/vehiculoInformacion";
	}

	@GetMapping("buscarReserva")
	public String buscarVehiculo(Reserva reserva, Model model) {
		return "empleado/buscarReserva";

	}

	@GetMapping("reserva")
	public String obtenerReserva(Reserva reserva, Model model) {
		Reserva reser = this.reservaService.buscarReservaPorNumero(reserva.getNumeroReserva());
		Vehiculo vehiculo = this.vehiculoService.buscarVehiculoPorID(reser.getVehiculo().getId());
		model.addAttribute("reserva", reser);
		model.addAttribute("vehiculo", vehiculo);
		return "empleado/reserva";

	}

	@PutMapping("retirarVehiculo")
	public String retirarVehiculo(Reserva reserva, Vehiculo vehiculo, Model model) {

		Reserva r = this.reservaService.buscarReservaPorID(reserva.getId());
		Vehiculo v = this.vehiculoService.buscarVehiculoPorPlaca(vehiculo.getPlaca());
		LOG.info(r.getId() + "" + r.getNumeroReserva());
		LOG.info(v.getId() + "");

		r.setEstado("E");

		v.setEstado("ND");

		this.vehiculoService.actualizarVehiculo(v);
		this.reservaService.actualizarReserva(r);

		model.addAttribute("reserva", reserva);

		return "empleado/reservaExitosa";
	}

}
