package ec.edu.uce.modelo;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class ReservaTO {

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime fechaInicio;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime fechaFinal;

	private Cliente cliente;

	private Vehiculo vehiculo;

	private Cobro cobro;

	// Métodos SET y GET
	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(LocalDateTime fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cobro getCobro() {
		return cobro;
	}

	public void setCobro(Cobro cobro) {
		this.cobro = cobro;
	}

}
