package ec.edu.uce.modelo;

import java.util.concurrent.ThreadLocalRandom;

public class NumeroReserva {

	private String numero;

	private static final String BANCO = "1234567890";

	public NumeroReserva(Cliente cliente) {
		super();
		this.numero = cadenaAleatoria(cliente);
	}

	public static int numeroAleatorioEnRango(int minimo, int maximo) {
		// nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos
		// 1
		return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
	}

	public static String cadenaAleatoria(Cliente cliente) {

		// La cadena en donde iremos agregando un carácter aleatorio
		String cadena = "";
		for (int x = 0; x < 10; x++) {
			int indiceAleatorio = numeroAleatorioEnRango(0, BANCO.length() - 1);
			char caracterAleatorio = BANCO.charAt(indiceAleatorio);
			cadena += caracterAleatorio;
		}
		return cadena + cliente.getFechaNacimiento().getDayOfYear();
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public static String getBanco() {
		return BANCO;
	}

}
