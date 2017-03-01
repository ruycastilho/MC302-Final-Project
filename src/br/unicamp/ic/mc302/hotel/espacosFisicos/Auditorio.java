package br.unicamp.ic.mc302.hotel.espacosFisicos;


// Classe para objeto do tipo Auditório
public class Auditorio extends EspacoEventoLazer {

	private int capacidadeMaximaPessoas;
	private static double precoDiaria = 300.0;
	private static int quantidade = 0;
	
	// Ocupa espaço
	public void reservar() {
		super.reservar();
		
	}
	
	// Desocupa espaço
	public void liberar() {
		super.liberar();
		
	}
	
	// Retorna string com descrição do auditório
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
	protected Auditorio(int capacidadePessoas, int codigo) {
		super(codigo);
		this.capacidadeMaximaPessoas = capacidadePessoas;
		quantidade++;
	}
}
