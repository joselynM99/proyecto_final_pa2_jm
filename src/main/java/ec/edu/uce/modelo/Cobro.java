package ec.edu.uce.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "cobro")
public class Cobro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cobro")
	@SequenceGenerator(name = "seq_cobro", sequenceName = "seq_cobro", allocationSize = 1)
	@Column(name = "cobr_id")
	private Integer id;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "cobr_fecha_cobro")
	private LocalDateTime fechaCobro;

	@Column(name = "cobr_subtotal")
	private BigDecimal subtotal;

	@Column(name = "cobr_numero_tarjeta")
	private String numeroTarjeta;

	@Column(name = "cobr_valor_IVA")
	private BigDecimal valorIVA;

	@Column(name = "cobr_valor_total_pagar")
	private BigDecimal valorTotalAPagar;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cobr_id_reserva")
	private Reserva reserva;

	// Metodos SET y GET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getFechaCobro() {
		return fechaCobro;
	}

	public void setFechaCobro(LocalDateTime fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getValorIVA() {
		return valorIVA;
	}

	public void setValorIVA(BigDecimal valorIVA) {
		this.valorIVA = valorIVA;
	}

	public BigDecimal getValorTotalAPagar() {
		return valorTotalAPagar;
	}

	public void setValorTotalAPagar(BigDecimal valorTotalAPagar) {
		this.valorTotalAPagar = valorTotalAPagar;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

}
