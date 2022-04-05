package ec.edu.uce.modelo;

import java.math.BigDecimal;

public class VehiculoReporteTO {

	private String modelo;

	private BigDecimal valorPorDia;

	private String placa;

	/**
	 * 
	 */
	public VehiculoReporteTO() {
		super();
	}

	/**
	 * @param modelo
	 * @param valorPorDia
	 * @param placa
	 */
	public VehiculoReporteTO(String modelo, BigDecimal valorPorDia, String placa) {
		super();
		this.modelo = modelo;
		this.valorPorDia = valorPorDia;
		this.placa = placa;
	}

	// Métodos SET y GET
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

}