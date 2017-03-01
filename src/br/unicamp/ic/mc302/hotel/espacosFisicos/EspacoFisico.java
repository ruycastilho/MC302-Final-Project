package br.unicamp.ic.mc302.hotel.espacosFisicos;

abstract public class EspacoFisico {

	// Atributos do espa�o f�sico
	private int codigoID;
	private EstadoOcupacao  estado;
	private EstadoOcupacao  estadoOcupado;
	private EstadoOcupacao  estadoLivre;
	
	// Retorna string com descri�ao do espa�o fisico
	public String toString() {
		return " Codigo: " +  getCodidoID();
		
	}
	
	// Ocupa o espa�o
	public void reservar() {
		estado.reservar(this);
		
	}
	
	// Desocupa o espa�o
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
	
	// Retorna o estado de ocupa��o do espa�o
	public boolean estaOcupado() {
		return estado instanceof EstadoOcupado;
		
	}
	
	// Setter para c�digo do espa�o f�sico.
	protected void setCodigoID(int codigo) {
		this.codigoID = codigo;
		
	}
	
	// Getters para atributos do espa�o f�sico.
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
