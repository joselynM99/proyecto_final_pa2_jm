package ec.edu.uce.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reserva")
	@SequenceGenerator(name = "seq_reserva", sequenceName = "seq_reserva", allocationSize = 1)
	@Column(name = "rese_id")
	private Integer id;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "rese_fecha_inicio")
	private LocalDateTime fechaInicio;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "rese_fecha_final")
	private LocalDateTime fechaFinal;

	@Column(name = "rese_estado")
	private String estado;

	@Column(name = "rese_numero_reserva")
	private String numeroReserva;

	@ManyToOne
	@JoinColumn(name = "clie_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "vehi_id")
	private Vehiculo vehiculo;

	@OneToOne(mappedBy = "reserva")
	private Cobro cobro;

	// Métodos GET y SET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Cobro getCobro() {
		return cobro;
	}

	public void setCobro(Cobro cobro) {
		this.cobro = cobro;
	}

	
}
