package br.unicamp.ic.mc302.hotel.espacosFisicos;

public abstract class EstadoOcupacao {

	abstract void reservar(EspacoFisico espaco);

	abstract void liberar(EspacoFisico espaco);

}
