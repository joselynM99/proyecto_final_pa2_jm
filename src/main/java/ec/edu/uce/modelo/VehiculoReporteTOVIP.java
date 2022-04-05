package ec.edu.uce.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VehiculoReporteTOVIP {

	private String placa;

	private BigDecimal valorTotalPagado;

	private BigDecimal valorIVAPagado;

	/**
	 * 
	 */
	public VehiculoReporteTOVIP() {
		super();
	}

	/**
	 * @param placa
	 * @param valorTotalPagado
	 * @param valorIVAPagado
	 */
	public VehiculoReporteTOVIP(String placa, BigDecimal valorTotalPagado, BigDecimal valorIVAPagado) {
		super();
		this.placa = placa;
		this.valorTotalPagado = valorTotalPagado;
		this.valorIVAPagado = valorIVAPagado;
	}

	// Métodos SET y GET

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
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
		return "VehiculoReporteTOVIP [placa=" + placa + ", valorTotalPagado=" + valorTotalPagado + ", valorIVAPagado="
				+ valorIVAPagado + "]";
	}

}
