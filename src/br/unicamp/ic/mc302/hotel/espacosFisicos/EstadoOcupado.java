package br.unicamp.ic.mc302.hotel.espacosFisicos;

public final class EstadoOcupado extends EstadoOcupacao {

	public void reservar(EspacoFisico espaco) {}

	public void liberar(EspacoFisico espaco) {
		espaco.alterarEstado();
	}

}
