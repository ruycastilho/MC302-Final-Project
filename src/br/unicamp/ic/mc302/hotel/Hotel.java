package br.unicamp.ic.mc302.hotel;

import br.unicamp.ic.mc302.hotel.recursosHumanos.*;

import br.unicamp.ic.mc302.hotel.espacosFisicos.*;

import java.util.ArrayList;

import java.time.LocalDateTime; // Uso do tempo.

import br.unicamp.ic.mc302.customExceptions.*;

/* Classe que define um hotel e as operações associadas
 * ao seu controle.
 */
public class Hotel {

	private double caixa; // Controle financeiro.
	private ArrayList<Funcionario> listaFuncionarios; // Registro de Funcionarios.
	private ArrayList<Suite> suites; // Registro de Suites.
	private ArrayList<EspacoEventoLazer> espacosUsoGeral; // Registro de outros espaços.
	private ArrayList<Cliente> listaClientes; // Registro de Clientes.
	
	private ArrayList<Reserva> reservasAtuais; // Registro de Reservas Atuais.
	private ArrayList<Reserva> historicoReservas; // Historico de Reservas.
	private int numeroReserva = 1;
	
	private EspacoFisicoAbstractFactory fabricaEspacosFisicos;
	
	// GETTERS para atributos do hotel.
	
	// Retorna a quantidade de dinheiro do hotel.
	public double getCaixa() {
		return this.caixa;
		
	}
	
	// Retorna a quantidade de reservas atuais.
	public int getNumeroReservas() {
		return this.reservasAtuais.size();
			
	}
	
	// Retorna uma string com a lista dos espacos disponiveis para reserva.
	public String getEspacosDisponiveis() {
		String listaEspacos = "";
		int i;
		EspacoFisico espaco;
		LocalDateTime dataHoje = LocalDateTime.now().withHour(12).withMinute(0).withSecond(0).withNano(0);
		
		// Suites
		for(i=0; i < suites.size() ;i++) {
			
			espaco = suites.get(i);
			if (estaOcupado(espaco.getCodidoID(),dataHoje) == false) {
				listaEspacos += espaco.toString() + "\n";
			}

		}

		// Outros espacos
		for(i=0; i < espacosUsoGeral.size() ;i++) {
			espaco = espacosUsoGeral.get(i);
			
			if (estaOcupado(espaco.getCodidoID(),dataHoje) == false) {
				listaEspacos += espaco.toString() + "\n";
			}
			
		}

		
		return listaEspacos;
	}
	
	// Determina se espaco de determinado codigo esta ocupada em determinada data.
	public boolean estaOcupado(int codigo, LocalDateTime data) {
		
		Reserva reserva;
		
		for(int i=0; i < reservasAtuais.size(); i++) {
			reserva = reservasAtuais.get(i);
			
			if ( reserva.getCodigoID() == codigo &&reserva.getChegada().withHour(12).withMinute(0).withSecond(0).withNano(0).isBefore(data) 
					&& reserva.getExpiracao().isAfter(data) ) {
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	// Retorna uma string com a lista de espacos reservados.
	public String getReservas() {
		String listaReservas = "";
		
		for(int i=0; i < reservasAtuais.size() ;i++) {
			
			listaReservas += reservasAtuais.get(i).toString() + "\n";
		}

		return listaReservas;
	}

	// Retorna uma string com a lista de espacos cujas reservas estao datadas para o dia atual.
	public String getReservasHoje(LocalDateTime data) {
		String listaReservas = "";
		LocalDateTime dataHoje = data.withHour(14).withMinute(0).withSecond(0).withNano(0);
		Reserva reservaChecada;
		
		for(int i=0; i < reservasAtuais.size() ;i++) {
			reservaChecada = reservasAtuais.get(i);
			
			// Checa expiração da reserva
			if ( ( reservaChecada.getChegada().isEqual(dataHoje) || reservaChecada.getChegada().isBefore(dataHoje) ) && reservaChecada.getOcupacao() == false ) {
				listaReservas += reservasAtuais.get(i).toString() + "\n";
		
			}
		}	
		
		if (listaReservas.equals("")) {
			return "Nenhuma reserva datada para hoje.";
		}
		
		return listaReservas;
	}

	
	// Retorna uma string com a lista de clientes registrados.
	public String getClientes() {
		String listaClientes = "";
		Cliente cliente;
		
		for(int i=0; i < this.listaClientes.size() ;i++) {
			
			cliente = this.listaClientes.get(i);
			listaClientes += "- \"" + cliente.getNome() + "\" --- CPF: " + cliente.getCPF() +
							" --- Passaporte: " + cliente.getPassaporte() + "\n";
		}

		return listaClientes;
		
		
		
	}

	
	
	// Funcionamento do Hotel
	
	// Realiza um pagamento ao caixa do hotel.
	private void pagamentoCaixa(double valor) {
		
		if (valor > 0) {
			this.caixa += valor;
			
		}

		
	}
	
	/* Checagem de disponibilidade para a realização de reservas.
	 * Retorna true, caso haja.
	 * Caso contrario, retorna false.
	 */
	public boolean existeDisponibilidade(LocalDateTime dataRecebida, int numeroDiarias, String tipo) {

		int quantidadeReservas = 0;
		Reserva reservaChecada;
		LocalDateTime dataChegadaReserva;
		LocalDateTime dataSaidaReserva;
		int quantidadeEspacos = 0;
		
		switch (tipo) {
		
			case "Suite Simples":
				quantidadeEspacos = SuiteSimples.getQuantidade();
				break;
				
			case "Suite Dupla":
				quantidadeEspacos = SuiteDupla.getQuantidade();
				break;
				
			case "Suite Presidencial":
				quantidadeEspacos = SuitePresidencial.getQuantidade();
				break;
		
			case "Auditorio":
				quantidadeEspacos = Auditorio.getQuantidade();
				break;

			case "Salao de Eventos":
				quantidadeEspacos = SalaoEvento.getQuantidade();
				break;
		}
		
		for(int i=0; i < reservasAtuais.size() ;i++) {
			reservaChecada  = reservasAtuais.get(i);
			dataChegadaReserva = reservaChecada.getChegada();
			dataSaidaReserva = reservaChecada.getExpiracao();
			
			if ( reservaChecada.getTipo().equals(tipo) ) {
			
				// Checa se tem reserva pro mesmo período.
				if ( ( dataChegadaReserva.isBefore(dataRecebida) || dataChegadaReserva.isEqual(dataRecebida) ) 
														&& dataSaidaReserva.isAfter(dataRecebida)   ){
					quantidadeReservas++;
				}
				
				// Checa se reserva nova começa antes da ja existente e termina no meio ou após a existente.
				else if ( dataChegadaReserva.isAfter(dataRecebida) &&  dataChegadaReserva.isBefore(dataRecebida.plusDays(numeroDiarias)) ) {
					
					quantidadeReservas++;
					
				}
				
			}
			
			
		}
		if ( quantidadeReservas == quantidadeEspacos ) {
			return false;
			
		}
		
		return true;
		
	}
	
	/* Busca de reservas por codigo do espaco físico e chegada na lista de reservas atuais.
	 * Retorna uma referencia ao objeto do Tipo Reserva, caso encontrado.
	 * Caso contrario, retorna null.
	 */
	public Reserva procuraReservaCodigo(int codigo) {

		Reserva reservaChecada;
		
		for(int i=0; i < reservasAtuais.size() ;i++) {
			reservaChecada  = reservasAtuais.get(i);
			
			// Checa codigo e chegada
			if ( reservaChecada.getCodigoID() == codigo ) {
				return reservaChecada;
			}
			
			
		}
		
		return null;
	}
	
	
	/* Realiza a reserva de um espaco fisico.
	 * Retorna true se for efetuada, e false, caso contrario.
	 */
	public boolean reservar(String tipo, int numeroDiarias, 
							LocalDateTime chegada, String nome, String passaporte, 
							String formaPagamento, double desconto) throws NullClientePointerException, NoRoomAvailabilityException {


		// Ha disponibilidade.
		if ( !existeDisponibilidade(chegada, numeroDiarias, tipo) ) {
			throw new NoRoomAvailabilityException();
		}
		
		else {
			Cliente cliente= procurarCliente(nome, passaporte);

			if ( cliente == null ) {
				throw new NullClientePointerException();
				
			}
			
			else {
		
				double precoDiaria = 0;
				
				// Determina o preco da diaria.
				switch (tipo) {
				
					case "Suite Simples":
						precoDiaria = SuiteSimples.getDiaria();
						break;
						
					case "Suite Dupla":
						precoDiaria = SuiteDupla.getDiaria();
						break;
						
					case "Suite Presidencial":
						precoDiaria = SuitePresidencial.getDiaria();
						break;
						
						
					case "Auditorio":
						precoDiaria = Auditorio.getDiaria();
						break;
						
					case "Salao de Eventos":
						precoDiaria = SalaoEvento.getDiaria();
						break;
				}
				
				
				reservasAtuais.add(new Reserva(cliente, tipo, numeroReserva,
						numeroDiarias, precoDiaria, chegada, formaPagamento, desconto));
				numeroReserva++;
				
				return true;
			}

		}
		
	}

	/* Realiza atualização de reserva .
	 * Recebe o código da reserva, a nova data de chegada e o novo numero de diarias.
	 * Retorna true, se realizada com sucesso, e false, caso contrário. 
	 */
	public boolean atualizarReserva(int codigo, String tipo, LocalDateTime novaChegada, int novoNumDiarias) {
		
		Reserva reservaExistente = procuraReservaCodigo(codigo);
		
		if (reservaExistente == null) {
			return false;
			
		}
		else {
			
			reservasAtuais.remove(reservaExistente);
			
			boolean haDisponibilidade = existeDisponibilidade(novaChegada, novoNumDiarias, tipo);
			
			if ( haDisponibilidade == true ) {
				reservaExistente.alterarData(novaChegada, novoNumDiarias);
				reservaExistente.alterarEspaco(tipo, novoNumDiarias);
				reservasAtuais.add(reservaExistente);
				return true;
				
				
			}
			
			reservasAtuais.add(reservaExistente);
			return false;
			
		
		}

	}
	
	/* Realiza o fechamento de uma reserva atual, ao receber um codigo e data de chegada.
	 * Retorna true se foi efetuado, e false, caso contrario.
	 */
	public boolean fecharReserva(int codigo, LocalDateTime data) {

		Reserva reservaChecada =  procuraReservaCodigo(codigo);
		LocalDateTime dataHoje = data.withHour(14).withMinute(0).withSecond(0).withNano(0);
		
		// Checa se a reserva existe.
		
		if ( reservaChecada == null ) {
			return false;
			
		} else {	// Caso exista.
			// Data para checagem da multa.
			LocalDateTime dataMulta = reservaChecada.getChegada().minusHours(12);
			
			historicoReservas.add(reservaChecada); // Adiciona a reserva ao historico.
			reservasAtuais.remove(reservaChecada); // Remove a reserva da lista de reservas.
			
			// Desocupa o espaco, se a reserva for atual.
			// Checa se é necessário aplicar multa.
			if (dataHoje.isAfter(dataMulta) && !reservaChecada.getOcupacao() ) {
				pagamentoCaixa(reservaChecada.getMulta());
				
			}
			
			else if ( reservaChecada.getOcupacao() ) {
				// Faz o pagamento das diárias.
				pagamentoCaixa(reservaChecada.getPagamento());
				reservaChecada.fecharReserva();
				
			}

			
			return true;
			
		}
		
	}
	
	/* Realiza o check-in de uma reserva.
	 * Recebe o nome e passaporte do cliente, o numero da reserva e a data atual.
	 * Retorna o numero do espaco fisico a ser ocupado, ou 0, se o check-in falhar.
	 */
	public int realizarCheckin(String nome, String passaporte, int numeroReserva, LocalDateTime dataAtual) 
			throws NullClientePointerException, NullReservaPointerException, DataArgumentException {
		

		if (procurarCliente(nome, passaporte) == null) {
			throw new NullClientePointerException();
			
		}
		
		Reserva reserva = procuraReservaCodigo(numeroReserva);
		if ( reserva == null ) {
			throw new NullReservaPointerException();
			
		}
		
		if ( !dataAtual.isAfter(dataAtual.withHour(12).withMinute(0).withSecond(0).withNano(0)) 
				&& !dataAtual.withHour(14).withMinute(0).withSecond(0).withNano(0).isEqual(reserva.getChegada()) ) {
			
			throw new DataArgumentException();
			
		}
		
		
		EspacoFisico espaco = null;
		boolean encontrado = false;
		
		switch( reserva.getTipo() ) {
		
			case "Suite Simples":
				for(int i=0; i < suites.size() && !encontrado ; i++) {
					espaco = suites.get(i);
					
					if ( espaco instanceof SuiteSimples && espaco.estaOcupado() == false ) {
						encontrado = true;

					}
					
					
				}
				break;
				
			case "Suite Dupla":
				for(int i=0; i < suites.size() && !encontrado ; i++) {
					espaco = suites.get(i);
					
					if ( espaco instanceof SuiteDupla && espaco.estaOcupado() == false ) {
						encontrado = true;
					}
					
					
				}
				break;
				
			case "Suite Presidencial":
				for(int i=0; i < suites.size() && !encontrado ; i++) {
					espaco = suites.get(i);
					
					if ( espaco instanceof SuitePresidencial && espaco.estaOcupado() == false ) {
						encontrado = true;
					}
					
					
				}
				break;
		
			case "Auditorio":
				for(int i=0; i < espacosUsoGeral.size() && !encontrado ; i++) {
					espaco = espacosUsoGeral.get(i);
					
					if ( espaco instanceof Auditorio && espaco.estaOcupado() == false ) {
						encontrado = true;
					}
					
					
				}
				break;
				
			case "Salao de Eventos":
				for(int i=0; i < espacosUsoGeral.size() && !encontrado ; i++) {
					espaco = espacosUsoGeral.get(i);
					
					if ( espaco instanceof SalaoEvento && espaco.estaOcupado() == false ) {
						encontrado = true;
					}
					
					
				}
				break;
		}
		
		
		if (encontrado == true) {
			reserva.realizarCheckin(espaco);
			return reserva.getCodigoEspaco();
			
		}

		
		return 0;
	}
	
	public boolean realizarCheckout(String nome, String passaporte, int numeroReserva, LocalDateTime data) {
		
		if (procurarCliente(nome, passaporte) == null) {
			return false;
			
		}
		
		Reserva reserva = procuraReservaCodigo(numeroReserva);
		if ( reserva == null ) {
			return false;
			
		}
		
		reserva.fecharReserva();
		fecharReserva(reserva.getCodigoID(), data);
		
		return true;
	}
	
	
	/* Busca de espaçoo físico do hotel.
	 * Recebe o codigo do espaço físico.
	 * Retorna uma referência ao objeto do tipo EspacoFisico.
	 */
	public EspacoFisico procuraEspacoFisico(int codigo) {
		EspacoFisico espaco = null;
		
		// Procura uma suite com o codigo especificado.
		espaco = procurarSuite(codigo);
		
		// Caso nao seja uma suite, procura um outro espaço.
		if (espaco == null) {
			espaco = procurarEspacoEventoLazer(codigo);
		
		}
		
		return espaco;
		
	}
	
	/* Registra um cliente novo no hotel.
	 * Recebe as informações pessoais da pessoa.
	 */
	public void registrarCliente(String nome, String cpf, String passaporte, String nacionalidade,
			String email, String telefone1, String endereco) {
		
		// Checa se o cliente ja esta registrado.
		if ( procurarCliente(nome, passaporte) == null) { 
			listaClientes.add(new Cliente(nome, cpf, passaporte, nacionalidade,
				email, telefone1, endereco));
			
		}
		
		
	}
	
	/* Busca de clientes na lista de clientes do hotel.
	 * Recebe o nome e o passaporte do cliente.
	 * Retorna uma referência ao objeto Cliente caso exista. Caso contrario,
	 * retorna null,
	 */
	public Cliente procurarCliente(String nome, String passaporte) {
		
		Cliente cliente = null;
		boolean encontrado = false; // Flag para parada da busca.
		
		for(int i=0; i < listaClientes.size() && !encontrado; i++) {
			
			// Compara informações com cada cliente da lista
			if ( listaClientes.get(i).getNome().equals(nome) ||
					listaClientes.get(i).getPassaporte().equals(passaporte)) {
				encontrado = true;
				cliente = listaClientes.get(i);
			}
			
		}
		
		return cliente;
		
	}
	
	// Registra uma suite na lista de suites do hotel.
	public void registrarSuite(String tipo, int codigo) {

		// Testa se um espaco de mesmo codigo ja não existe.
		if ( procuraEspacoFisico(codigo) == null) {

			switch (tipo) {
				
				case "Presidencial":
						suites.add(fabricaEspacosFisicos.createSuitePresidencial(codigo));
						break;
						
				case "Simples":
						suites.add(fabricaEspacosFisicos.createSuiteSimples(codigo));
						break;
						
				case "Dupla":
						suites.add(fabricaEspacosFisicos.createSuiteDupla(codigo));
						break;
			
			}
			
		}
		
	}
	
	
	/* Busca de suite com um codigo especificado.
	 * Recebe o codigo procurado.
	 * Retorna uma referência para objeto do tipo Suite, caso encontrada.
	 * Caso contrario, retorna null.
	 */
	public Suite procurarSuite(int codigo) {
		
		Suite suite = null;
		boolean encontrado = false; // Flag para parada da busca.
		
		for(int i=0; i < suites.size() && !encontrado; i++) {
			if ( suites.get(i).getCodidoID() == codigo) {
				encontrado = true;
				suite = suites.get(i);
		
			}
		}
		
		return suite;
	}
	
	/* Registra um espaço de evento/lazer na lista de espaços do hotel.
	 * Recebe o tipo do espaço, a capacidade de pessoas e o preco da diaria.
	 */
	public void registrarEspacoEventoLazer(String tipo, int codigo, int capacidadePessoas,
											double precoDiaria) {
		
		// Testa se um espaco de mesmo codigo ja não existe.
		if ( procuraEspacoFisico(codigo) == null) {
			switch (tipo) {
			
				case "Auditorio":
						espacosUsoGeral.add(fabricaEspacosFisicos.createAuditorio(capacidadePessoas, codigo));
						break;
						
				case "SalaoEvento":
						espacosUsoGeral.add(fabricaEspacosFisicos.createSalaoEvento(capacidadePessoas, codigo));
						break;

			
			}
			
		}
		
		
	}
	
	/* Busca de espaços de evento/lazer na lista de espaços do hotel.
	 * Recebe o codigo do espaço.
	 * Retorna uma referência para objeto do tipo EspacoEventoLazer caso encontre,
	 * Caso contrário, retorna null.
	 */
	public EspacoEventoLazer procurarEspacoEventoLazer(int codigo) {
		
		EspacoEventoLazer espaco = null;
		boolean encontrado = false; // Flag para parada da busca.
		
		for(int i=0; i < espacosUsoGeral.size() && !encontrado; i++) {
			if ( espacosUsoGeral.get(i).getCodidoID() == codigo) 
				encontrado = true;
				espaco = espacosUsoGeral.get(i);
		}
		
		return espaco;
	}
	
	
	/* Registra funcionário na lista de funcionários do hotel.
	 * Recebe as informações pessoais do funcionário.
	 */
	public void registrarFuncionario(String nome, String cpf, String passaporte, String nacionalidade,
			String email, String telefone1, String endereco, int senha) {
		
		// Testa se já está registrado.
		if ( procurarFuncionario(nome) == null) { 
			listaFuncionarios.add(new Funcionario(nome, cpf, passaporte, nacionalidade,
				email, telefone1, endereco, senha));
			
		}
		
		
	}
	
	/* Busca de funcionário na lista de funcionários do hotel.
	 * Recebe nome do funcionário.
	 * Retorna uma referência para objeto do tipo Funcionário, caso encontrado.
	 * Caso contrário, retorna null.
	 */
	public Funcionario procurarFuncionario(String nome) {
		
		Funcionario funcionario = null;
		boolean encontrado = false;
		
		for(int i=0; i < listaFuncionarios.size() && !encontrado; i++) {
			if ( listaFuncionarios.get(i).getNome().equals(nome) ) {
				encontrado = true;
				funcionario = listaFuncionarios.get(i);
			}
			
		}
		
		return funcionario;
		
	}
	
	public boolean verificaFuncionario(String nome, int senha) {
		
		Funcionario funcionario = null;
		boolean encontrado = false;
		
		for(int i=0; i < listaFuncionarios.size() && !encontrado; i++) {
			funcionario = listaFuncionarios.get(i);
			if ( funcionario.getNome().equals(nome) && funcionario.verificaSenha(senha) ) {
				encontrado = true;

			}
			
		}
		
		return encontrado;
		
	}
	
	// Construtor.
	public Hotel() {
		
		// Instancia as listas utilizadas pelo hotel.
		listaFuncionarios = new ArrayList<Funcionario>();
		suites = new ArrayList<Suite>();
		espacosUsoGeral = new ArrayList<EspacoEventoLazer>();
		listaClientes = new ArrayList<Cliente>();
		
		reservasAtuais = new ArrayList<Reserva>();
		historicoReservas = new ArrayList<Reserva>();

		fabricaEspacosFisicos = new EspacoFisicoAbstractFactory();
		
	}
	
	
}
