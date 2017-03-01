package br.unicamp.ic.mc302.hotel.espacosFisicos;


// Classe para espaco do tipo Suite Simples
public class SuiteSimples extends Suite {

	private static int numeroUsuariosMax = 1;
	private static double precoDiaria = 100.0;
	private static int quantidade = 0;

	
	// Retorna string com descrição da suite.
	public String toString() {
		return "Suite Simples - " + " Capacidade: "  + Integer.toString(numeroUsuariosMax) + super.toString();
		
			
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
	protected SuiteSimples(int codigo) {
		super(numeroUsuariosMax, codigo);
		quantidade++;
		
	}

}
