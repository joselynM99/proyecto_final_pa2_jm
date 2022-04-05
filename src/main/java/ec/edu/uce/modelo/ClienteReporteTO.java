package ec.edu.uce.modelo;

import java.math.BigDecimal;

public class ClienteReporteTO {

	private String cedula;

	private BigDecimal valorTotalPagado;

	private BigDecimal valorIVAPagado;

	/**
	 * 
	 */
	public ClienteReporteTO() {
		super();
	}

	/**
	 * @param cedula
	 * @param valorTotalPagado
	 * @param valorIVAPagado
	 */
	public ClienteReporteTO(String cedula, BigDecimal valorTotalPagado, BigDecimal valorIVAPagado) {
		super();
		this.cedula = cedula;
		this.valorTotalPagado = valorTotalPagado;
		this.valorIVAPagado = valorIVAPagado;
	}

	// Métodos SET y GET
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public BigDecimal getValorTotalPagado() {
		return valorTotalPagado;
	}

	public void setValorTotalPagado(BigDecimal valorTotalPagado) {
		this.valorTotalPagado = valorTotalPagado;
	}

	public BigDecimal getValorIVAPagado() {
		return valorIVAPagado;
	}

	public void setValorIVAPagado(BigDecimal valorIVAPagado) {
		this.valorIVAPagado = valorIVAPagado;
	}

	@Override
	public String toString() {
		return "ClienteReporteTO [cedula=" + cedula + ", valorTotalPagado=" + valorTotalPagado + ", valorIVAPagado="
				+ valorIVAPagado + "]";
	}

}
