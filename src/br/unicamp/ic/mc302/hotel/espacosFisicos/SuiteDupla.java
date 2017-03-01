package br.unicamp.ic.mc302.hotel.espacosFisicos;

// Classe para objeto do tipo Suite Dupla
public class SuiteDupla extends Suite {

	private static int numeroUsuariosMax = 2;
	private static double precoDiaria = 200.0;
	private static int quantidade = 0;

	
	// Retorna string com descrição da suite.
	public String toString() {
		return "Suite Dupla - " + " Capacidade: "  + Integer.toString(numeroUsuariosMax) + super.toString();
		
			
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
	protected SuiteDupla(int codigo) {
		super(numeroUsuariosMax, codigo);
		quantidade++;
		
	}

}
