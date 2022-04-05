package ec.edu.uce.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class ReservaReporteTO {

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime fechaInicio;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime fechaFinal;

	private String estado;

	private String numeroReserva;

	private String cedula;

	private String nombre;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime fechaCobro;

	private String modelo;

	private BigDecimal valorPorDia;

	private String placa;

	public ReservaReporteTO() {
		super();
	}

	/**
	 * @param fechaInicio
	 * @param fechaFinal
	 * @param estado
	 * @param numeroReserva
	 * @param cedula
	 * @param nombre
	 * @param fechaCobro
	 * @param modelo
	 * @param valorPorDia
	 * @param placa
	 */
	public ReservaReporteTO(LocalDateTime fechaInicio, LocalDateTime fechaFinal, String estado, String numeroReserva,
			String cedula, String nombre, LocalDateTime fechaCobro, String modelo, BigDecimal valorPorDia,
			String placa) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.estado = estado;
		this.numeroReserva = numeroReserva;
		this.cedula = cedula;
		this.nombre = nombre;
		this.fechaCobro = fechaCobro;
		this.modelo = modelo;
		this.valorPorDia = valorPorDia;
		this.placa = placa;
	}

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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumeroReserva() {
		return numeroReserva;
	}

	public void setNumeroReserva(String numeroReserva) {
		this.numeroReserva = numeroReserva;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDateTime getFechaCobro() {
		return fechaCobro;
	}

	public void setFechaCobro(LocalDateTime fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public BigDecimal getValorPorDia() {
		return valorPorDia;
	}

	public void setValorPorDia(BigDecimal valorPorDia) {
		this.valorPorDia = valorPorDia;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	@Override
	public String toString() {
		return "ReservaReporteTO [fechaInicio=" + fechaInicio + ", fechaFinal=" + fechaFinal + ", estado=" + estado
				+ ", numeroReserva=" + numeroReserva + ", cedula=" + cedula + ", nombre=" + nombre + ", fechaCobro="
				+ fechaCobro + ", modelo=" + modelo + ", valorPorDia=" + valorPorDia + ", placa=" + placa + "]";
	}

}
