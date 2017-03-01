package br.unicamp.ic.mc302.hotel.espacosFisicos;

// Classe para objeto do tipo Suite Presidencial
public class SuitePresidencial extends Suite {

	private static int numeroUsuariosMax = 2;
	private static double precoDiaria = 500.0;
	private static int quantidade = 0;

	
	// Retorna string com descrição da suite
	public String toString() {
		return "Suite Presidencial - " + " Capacidade: "  + Integer.toString(numeroUsuariosMax) + super.toString();
		
			
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
	protected SuitePresidencial(int codigo) {
		super(numeroUsuariosMax, codigo);
		quantidade++;
		
	}

}
