package br.unicamp.ic.mc302.hotel.espacosFisicos;

// Classe para objeto do tipo Sal�o de Evento
public class SalaoEvento extends EspacoEventoLazer {

	private int capacidadeMaximaPessoas;
	private boolean estaOcupado;
	private static double precoDiaria = 300.0;
	private static int quantidade = 0;

	
	// Ocupa espa�o
	public void reservar() {
		super.reservar();
		
	}
	
	// Desocupa espa�o
	public void liberar() {
		super.liberar();
		
	}
	
	// Retorna string com descri��o do espa�o.
	public String toString() {
		
		return "Salao de Evento " + " Capacidade: " + capacidadeMaximaPessoas
				+ super.toString();
		
	}
	

	public double getPrecoDiaria() {
		return precoDiaria;
		
	}
	
	public static double getDiaria() {
		return precoDiaria;
		
	}
	
	public static int getQuantidade() {
		return quantidade;
		
	}
	
	
	// Construtor
	protected SalaoEvento(int capacidadeMaximaPessoas, int codigo) {
		super(codigo);
		this.capacidadeMaximaPessoas = capacidadeMaximaPessoas;
		quantidade++;
	}

}
