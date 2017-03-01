package br.unicamp.ic.mc302.hotel.espacosFisicos;

abstract public class EspacoFisico {

	// Atributos do espaço físico
	private int codigoID;
	private EstadoOcupacao  estado;
	private EstadoOcupacao  estadoOcupado;
	private EstadoOcupacao  estadoLivre;
	
	// Retorna string com descriçao do espaço fisico
	public String toString() {
		return " Codigo: " +  getCodidoID();
		
	}
	
	// Ocupa o espaço
	public void reservar() {
		estado.reservar(this);
		
	}
	
	// Desocupa o espaço
	public void liberar() {
		estado.liberar(this);
		
	}
	
	protected void alterarEstado() {
		
		if ( estado instanceof EstadoOcupado ) {
			estado = estadoLivre;
			
		}
		
		else {
			estado = estadoOcupado;
			
		}
		
			
	}
	
	// Retorna o estado de ocupação do espaço
	public boolean estaOcupado() {
		return estado instanceof EstadoOcupado;
		
	}
	
	// Setter para código do espaço físico.
	protected void setCodigoID(int codigo) {
		this.codigoID = codigo;
		
	}
	
	// Getters para atributos do espaço físico.
	public int getCodidoID() {
		return this.codigoID;
		
	}
	
	abstract public double getPrecoDiaria();
	
	// Construtor
	protected EspacoFisico(int codigo) {
		this.codigoID = codigo;
		estadoOcupado = new EstadoOcupado();
		estadoLivre = new EstadoLivre();
		
		estado = estadoLivre;
		
	 }
}
