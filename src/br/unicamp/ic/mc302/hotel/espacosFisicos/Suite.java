package br.unicamp.ic.mc302.hotel.espacosFisicos;


// Classe abstrata para o tipo Suite.
abstract public class Suite extends EspacoFisico {


	private int numeroUsuariosMaximo;
	private int numeroUsuariosAtual;
	
	// Descricao especifica.
	private boolean temFrigobar;
	private boolean temArCondicionado;
	
	// Setters.
	protected void setNumeroUsuarios(int quantidade) {
		this.numeroUsuariosAtual = quantidade;
		
	}
	
	protected void setNumeroUsuariosMax(int quantidade) {
		this.numeroUsuariosMaximo = quantidade;
		
		
	}
	
	// Ocupa espaço
	public void reservar() {
		super.reservar();
		
	}
	
	// Desocupa espaço
	public void liberar() {
		super.liberar();
		
	}

	
	// Getters.
	
	// Construtor para uma suite.
	protected Suite(int numeroUsuariosMax, int codigo) {
		super(codigo);
		this.numeroUsuariosMaximo = numeroUsuariosMax;

		
	}

}
