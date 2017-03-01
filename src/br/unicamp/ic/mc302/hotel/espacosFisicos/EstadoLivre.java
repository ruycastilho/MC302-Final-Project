package br.unicamp.ic.mc302.hotel.espacosFisicos;

public class EstadoLivre extends EstadoOcupacao {

	public void reservar(EspacoFisico espaco) {
		espaco.alterarEstado();
		
	}

	public void liberar(EspacoFisico espaco) {}

}
