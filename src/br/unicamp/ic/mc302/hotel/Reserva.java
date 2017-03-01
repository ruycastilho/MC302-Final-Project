package br.unicamp.ic.mc302.hotel;

import br.unicamp.ic.mc302.hotel.espacosFisicos.EspacoFisico;
import br.unicamp.ic.mc302.hotel.recursosHumanos.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reserva {

	// Atributos de uma reserva.
	
	private Cliente hospedePrincipal; // Referência a um objeto do tipo cliente, para o Hospede principa
	private EspacoFisico espacoReservado = null; // Referência a um objeto do tipo EspaçoFisico, para o espaço reservado.
	private int codigoReserva; // Codigo identificador da reserva.
	private int codigoEspaco = 0; // Codigo do espaço reservado.
	private String tipo; // Tipo de espaço reservado.
	private LocalDateTime chegada; // Data de chegada do cliente.
	private LocalDateTime saida; // Data de saída do cliente.
	
	private int numeroDiarias;	// Número de diárias da reserva.
	private double precoDiaria = 0; // Preço da diária do espaço.
	private static double multaDesistencia = 0.4; // Multa de 40% sobre o valor.
	private String formaPagamento; // Forma de pagamento da reserva.
	private double desconto; // Desconto no pagamento da reserva.
	
	// Construtor.	
	public Reserva(Cliente hospedePrincipal, String tipo, int codigo,
					int numeroDiarias, double precoDiaria, LocalDateTime chegada, String formaPagamento,
					double desconto) {
		
		this.hospedePrincipal = hospedePrincipal;
		this.chegada = chegada;
		this.saida = this.getExpiracao();
		this.codigoReserva = codigo;
		this.numeroDiarias = numeroDiarias;
		this.precoDiaria = precoDiaria;
		this.formaPagamento = formaPagamento;
		this.desconto = desconto;
		this.tipo = tipo;
		
	}
	
	
	public void realizarCheckin(EspacoFisico espaco) {
		espacoReservado = espaco;
		espacoReservado.reservar();
		codigoEspaco = espacoReservado.getCodidoID();
		precoDiaria = espacoReservado.getPrecoDiaria();
		
	}
	
	// Desocupa espaço reservado.
	public void fecharReserva()  {
		espacoReservado.liberar();
		
		
	}
	
	// Retorna uma string com a descrição da reserva.
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String dataFormatada = chegada.format(formatter);
		
		if ( espacoReservado == null )
			return (tipo + " - Codigo Reserva: " + codigoReserva + 
					" - Hospede Principal: " + hospedePrincipal.getNome() + " - Chegada: " + dataFormatada 
					+ " - Numero de Diarias: " + numeroDiarias + " - Check-in não realizado");
		
		else
			return (tipo + " - Codigo Reserva: " + codigoReserva +  
					" - Hospede Principal: " + hospedePrincipal.getNome() + " - Chegada: " + dataFormatada 
					+ " - Numero de Diarias: " + numeroDiarias + " - Check-in realizado - Código Espaço: " + codigoEspaco);
		
	}
	
	// Getters para os atributos da reserva.
	public int getCodigoEspaco() {
		return this.codigoEspaco;
		
	}
	
	public int getCodigoID() {
		return this.codigoReserva;
		
	}
	
	public Cliente getCliente() {
		return this.hospedePrincipal;
		
	}
	
	public double getPagamento() {
		return numeroDiarias*precoDiaria*(1-desconto/100);
		
	}
	
	public double getMulta() {
		return this.getPagamento()*multaDesistencia;
		
	}
	
	public String getFormaPagamento() {
		return this.formaPagamento;
		
	}
	
	public double getDesconto() {
		return this.desconto;
		
	}
	
	public String getTipo() {
		return this.tipo;
		
	}
	
	public boolean getOcupacao() {
		return ( espacoReservado != null );
		
	}
	
	
	
	public LocalDateTime getExpiracao() {
		return chegada.plusDays(numeroDiarias).withHour(12).withMinute(0).withSecond(0).withNano(0);
		
		
	}
	
	public LocalDateTime getChegada() {
		return chegada;
		
		
	}
	
	public void alterarData(LocalDateTime chegada, int numeroDiarias) {
		this.chegada = chegada;
		this.numeroDiarias = numeroDiarias;
		this.saida = this.getExpiracao();
		
	}
	
	public void alterarEspaco(String tipo, int numeroDiarias) {
		this.tipo = tipo;
		this.numeroDiarias = numeroDiarias;
		this.saida = this.getExpiracao();
		
	}
}
