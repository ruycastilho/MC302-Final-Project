package br.unicamp.ic.mc302.hotel;

import br.unicamp.ic.mc302.customExceptions.DataArgumentException;
import br.unicamp.ic.mc302.customExceptions.NoRoomAvailabilityException;
import br.unicamp.ic.mc302.customExceptions.NullClientePointerException;
import br.unicamp.ic.mc302.customExceptions.NullReservaPointerException;
import br.unicamp.ic.mc302.hotel.*;
import java.awt.*;       // Using layouts
import java.awt.event.*; // Using AWT event classes and listener interfaces
import javax.swing.*;    // Using Swing components and containers

import java.time.LocalDateTime; // Uso do tempo.
import java.time.format.DateTimeFormatter;

// Terminal para uso do sistema de hotel.
public class Terminal {
	
	private Hotel hotel; // Variavel referencia para o objeto Hotel.
	LocalDateTime dataHotel; // Variavel para o uso do tempo.
	
	// Componentes da interface Grafica.
	private JFrame framePrincipal;
	private JPanel panelPrincipal;
	private JTextArea status;
	private JTextArea espacosDisponiveis;
	private JTextArea reservas;
	private JTextArea reservasHoje;
	private JButton botaoData;
	private JButton botaoLogin;
	private JButton botaoLogout;
	private JButton botaoCheckin;
	private JButton botaoCheckout;
	private JButton botaoRegistroCliente;
	private JButton botaoRegistroFuncionario;
	private JButton botaoReservas;
	private JButton botaoAtualizarReservas;
	private JButton botaoFecharReservas;
	private JButton botaoListaCliente;
	
	
	// Atualiza os componentes da interface gráfica.
	private void atualizar() {
		atualizarStatus();
		atuailizarEspacosDisponiveis();
		atualizarReservas();
		atualizarReservasHoje();
		
	}
	
	// Atualiza o componente de Status do hotel na interface gráfica.
	private void atualizarStatus() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String dataFormatada = dataHotel.format(formatter); //
		status.setText("\nStatus Atual\n\n" + "	Dia e Hora: " + dataFormatada + "\n	Numero de reservas atuais: " 
						+ hotel.getNumeroReservas() + "\n	Caixa: " + hotel.getCaixa() + "\n\nSuite Simples: 100,0"
						+ "\nSuite Dupla: 200,0" + "\nSuite Presidencial: 500.0" + "\nMulta: 40% do valor do pagamento.");
		status.setEditable(false);
		status.setLineWrap(true);
		status.setOpaque(false);
		
		
	}
	
	// Atualiza o componente de lista de Espaços do hotel na interface gráfica.
	public void atuailizarEspacosDisponiveis() {
		espacosDisponiveis.setText("Espacos Disponiveis\n\n" + hotel.getEspacosDisponiveis());
		espacosDisponiveis.setEditable(false);
		espacosDisponiveis.setLineWrap(true);
		espacosDisponiveis.setOpaque(false);
		
		
	}
	
	// Atualiza o componente de lista de reservas do hotel na interface gráfica.
	public void atualizarReservas() {
		reservas.setText("Reservas Atuais\n\n" + hotel.getReservas());
		reservas.setEditable(false);
		reservas.setLineWrap(true);
		reservas.setOpaque(false);
		
	}
	
	// Atualiza o componente de lista de reservas expirando do hotel na interface gráfica.
	public void atualizarReservasHoje() {
		reservasHoje.setText("Reservas para check-in\n\n" + hotel.getReservasHoje(dataHotel));
		reservasHoje.setEditable(false);
		reservasHoje.setLineWrap(true);
		reservasHoje.setOpaque(false);

		
	}
	
	// Atualiza a interface gráfica no caso de login/logout.
	public void atualizarLogin(boolean status) {
		
		if (status == true) {
			botaoListaCliente.setVisible(true);
			botaoRegistroCliente.setVisible(true);
			botaoRegistroFuncionario.setVisible(true);
			botaoReservas.setVisible(true);
			botaoAtualizarReservas.setVisible(true);
			botaoFecharReservas.setVisible(true);
			botaoLogout.setVisible(true);
			botaoLogin.setVisible(false);
			botaoCheckout.setVisible(true);
			botaoCheckin.setVisible(true);
			botaoData.setVisible(true);
		
		}
		else {
			botaoListaCliente.setVisible(false);
			botaoRegistroCliente.setVisible(false);
			botaoRegistroFuncionario.setVisible(false);
			botaoReservas.setVisible(false);
			botaoAtualizarReservas.setVisible(false);
			botaoFecharReservas.setVisible(false);
			botaoLogout.setVisible(false);
			botaoLogin.setVisible(true);
			botaoCheckout.setVisible(false);
			botaoCheckin.setVisible(false);
			botaoData.setVisible(false);
		}
	
	}
	
	/* Construtor.
	 * Inicializa e prepara a interface gráfica.
	 * Recebe uma referência para um objeto do tipo Hotel.
	 */
	public Terminal(Hotel hotel) {
		this.framePrincipal = new JFrame();
		this.hotel = hotel;
		this.dataHotel = LocalDateTime.now().withHour(11); // Para uso do tempo.
													  
		
		this.panelPrincipal = new JPanel();
		
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.PAGE_AXIS));
		
		panelPrincipal.add(new JLabel("Hotel MC302"));
		
		// Componente Stauts.
		status = new JTextArea();
		atualizarStatus();
		panelPrincipal.add(status);
		
		
		// Componente Lista de Espaços Disponíveis.
		espacosDisponiveis = new JTextArea();
		atuailizarEspacosDisponiveis();
		panelPrincipal.add(espacosDisponiveis);
		
		
		// Componente Lista de Reservas atuais.
		reservas = new JTextArea();
		atualizarReservas();
		panelPrincipal.add(reservas);
		
		
		// Componente Lista de Reservas expirando.
		reservasHoje = new JTextArea();
		atualizarReservasHoje();
		panelPrincipal.add(reservasHoje);
		
		
		// INTERFACE PARA LOGIN DE FUNCIONARIOS.
				botaoLogin = new JButton("Login de Funcionário");
				panelPrincipal.add(botaoLogin);
				botaoLogin.setVisible(true);
				
				// Listener para botão.
				botaoLogin.addMouseListener(new MouseListener() {
					
			          @Override
			          public void mouseClicked(MouseEvent evt) {

			        	  JFrame frameLogin = new JFrame();
			        	  Container panelLogin = frameLogin.getContentPane();
			        	  panelLogin.setLayout(new BoxLayout(panelLogin, BoxLayout.PAGE_AXIS));
			    	      frameLogin.setSize(500, 160);

			        	  frameLogin.setTitle("Login"); 
			        	  
			        	  // Nome
			        	  panelLogin.add( new JLabel("Nome:"));
			        	  JTextField inputNome = new JTextField(30);
			        	  panelLogin.add(inputNome);
			        	  
			        	  // Senha
			        	  panelLogin.add( new JLabel("Senha:"));
			        	  JTextField inputSenha = new JTextField(10);
			        	  panelLogin.add(inputSenha);
		    	  
			        	  // Botao para login
			      		  JButton login = new JButton("Login");
			    		  panelLogin.add(login);
			    		  
			    		  // Label para mostrar estado das operações.
			    		  JLabel estadoLogin = new JLabel("Estado do Login: ---- ");
			    		  panelLogin.add(estadoLogin);
			    		  
			    		  
			        	  
			      		  frameLogin.setVisible(true);
			      		  
			      		  
			      		// Listener para botão.
			    		  login.addMouseListener(new MouseListener() {
			    			  
			    	          public void mouseClicked(MouseEvent evt) {
			    	        	  
			    	        	  if (hotel.verificaFuncionario(inputNome.getText(), Integer.parseInt(inputSenha.getText())) ) {
		    	      		  		
			    			  		atualizarLogin(true);
				    	      	    estadoLogin.setText("Estado do Login: Login efetuado com sucesso.");
			    			  
			    			  
			    		  		  }
			    		  		  else { 
			    		  		    estadoLogin.setText("Estado do Login: Login não efetuado.");
			    		  		  }
			    	      		  
			    	          }
			    	          
			    	          @Override public void mousePressed(MouseEvent evt) { }
			    	          @Override public void mouseReleased(MouseEvent evt) { }
			    	          @Override public void mouseEntered(MouseEvent evt) { }
			    	          @Override public void mouseExited(MouseEvent evt) { }
			    	          
			    		  });
			    		  
			          }
			          
			          @Override public void mousePressed(MouseEvent evt) { }
			          @Override public void mouseReleased(MouseEvent evt) { }
			          @Override public void mouseEntered(MouseEvent evt) { }
			          @Override public void mouseExited(MouseEvent evt) { }
			    
				});
				
		
		// INTERFACE PARA LOGOUT DE FUNCIONARIOS.
		botaoLogout = new JButton("Logout de Funcionário");
		panelPrincipal.add(botaoLogout);
		botaoLogout.setVisible(false);
		
		// Listener para botão.
		botaoLogout.addMouseListener(new MouseListener() {
			
	          @Override
	          public void mouseClicked(MouseEvent evt) {
	    		 
	        	  atualizarLogin(false);
	        	  
	          }
	          
	          @Override public void mousePressed(MouseEvent evt) { }
	          @Override public void mouseReleased(MouseEvent evt) { }
	          @Override public void mouseEntered(MouseEvent evt) { }
	          @Override public void mouseExited(MouseEvent evt) { }
	    
		});

		
		
		
		// INTERFACE PARA CHECK-IN.
				botaoCheckin = new JButton("Check-in");
				panelPrincipal.add(botaoCheckin);
				botaoCheckin.setVisible(false);
				
				// Listener para botão.
				botaoCheckin.addMouseListener(new MouseListener() {
					
			          @Override
			          public void mouseClicked(MouseEvent evt) {

			        	  JFrame frameCheckin = new JFrame();
			        	  Container panelCheckin = frameCheckin.getContentPane();
			        	  panelCheckin.setLayout(new BoxLayout(panelCheckin, BoxLayout.PAGE_AXIS));
			    	      frameCheckin.setSize(500, 200);

			        	  frameCheckin.setTitle("Check-in"); 
			        	  
			        	  // Nome
			        	  panelCheckin.add( new JLabel("Nome:"));
			        	  JTextField inputNome = new JTextField(30);
			        	  panelCheckin.add(inputNome);
			        	  
			        	  // Passaporte
			        	  panelCheckin.add( new JLabel("Passaporte:"));
			        	  JTextField inputPassaporte = new JTextField(15);
			        	  panelCheckin.add(inputPassaporte);
			        	  
			        	  // Codigo
			        	  panelCheckin.add( new JLabel("Codigo da Reserva:"));
			        	  JTextField inputCodigo = new JTextField(15);
			        	  panelCheckin.add(inputCodigo);
			        	  
			        	  // Botao para checkin
			      		  JButton checkin = new JButton("Check-in");
			    		  panelCheckin.add(checkin);
			    		  
			    		  // Label para mostrar estado das operações.
			    		  JLabel estadoCheckin = new JLabel("Estado do Check-in: ---- ");
			    		  panelCheckin.add(estadoCheckin);
			    		  
			    		  
			        	  
			      		  frameCheckin.setVisible(true);
			      		  
			      		  
			      		// Listener para botão.
			    		  checkin.addMouseListener(new MouseListener() {
			    			  
			    	          public void mouseClicked(MouseEvent evt) {
			    	        	  
			    	        	  int resultado = 0;
			    	        	  
			    	        	  try {
				    	        	  resultado = hotel.realizarCheckin(inputNome.getText(), inputPassaporte.getText(),
  			  								Integer.parseInt(inputCodigo.getText()), dataHotel); 
			    	        	  }
			    	        	  catch (NullClientePointerException e1) {
			    	        		  estadoCheckin.setText("Check-in não realizado. Cliente não cadastrado.");

			    	        		  
			    	        	  }
			    	        	  catch (NullReservaPointerException e2) {
			    	        		  estadoCheckin.setText("Check-in não realizado. Reserva não cadastrada.");
			    	        				  
								  }

			    	        	  catch (DataArgumentException e3) {
			    	        		  estadoCheckin.setText("Check-in não realizado. Data invalida.");

			    	        				  
								  }
			    	        	  finally {
			    	        		  
				    	        	  if ( resultado != 0 ) {
				    	        		  estadoCheckin.setText("Check-in realizado com sucesso! Código do Espaço: " + resultado);
				    	        		  atualizar();
				    	        		  
				    	        	  }
				    	        	  
			    	        	  }
			    	      		  

			    	          }
			    	          
			    	          @Override public void mousePressed(MouseEvent evt) { }
			    	          @Override public void mouseReleased(MouseEvent evt) { }
			    	          @Override public void mouseEntered(MouseEvent evt) { }
			    	          @Override public void mouseExited(MouseEvent evt) { }
			    	          
			    		  });
			    		  
			          }
			          
			          @Override public void mousePressed(MouseEvent evt) { }
			          @Override public void mouseReleased(MouseEvent evt) { }
			          @Override public void mouseEntered(MouseEvent evt) { }
			          @Override public void mouseExited(MouseEvent evt) { }
			    
				});
		
		
		// INTERFACE PARA CHECK-OUT.
				botaoCheckout = new JButton("Check-out");
				panelPrincipal.add(botaoCheckout);
				botaoCheckout.setVisible(false);
				
				// Listener para botão.
				botaoCheckout.addMouseListener(new MouseListener() {
					
			          @Override
			          public void mouseClicked(MouseEvent evt) {

			        	  JFrame frameCheckout = new JFrame();
			        	  Container panelCheckout = frameCheckout.getContentPane();
			        	  panelCheckout.setLayout(new BoxLayout(panelCheckout, BoxLayout.PAGE_AXIS));
			    	      frameCheckout.setSize(500, 200);

			        	  frameCheckout.setTitle("Check-out"); 
			        	  
			        	  // Nome
			        	  panelCheckout.add( new JLabel("Nome:"));
			        	  JTextField inputNome = new JTextField(30);
			        	  panelCheckout.add(inputNome);

			        	  // Passaporte
			        	  panelCheckout.add( new JLabel("Passaporte:"));
			        	  JTextField inputPassaporte = new JTextField(15);
			        	  panelCheckout.add(inputPassaporte);
			        	  
			        	  // Codigo
			        	  panelCheckout.add( new JLabel("Codigo da Reserva:"));
			        	  JTextField inputCodigo = new JTextField(15);
			        	  panelCheckout.add(inputCodigo);
			        	  
			        	  // Botao para checkout
			      		  JButton checkout = new JButton("Check-out");
			    		  panelCheckout.add(checkout);
			    		  
			    		  // Label para mostrar estado das operações.
			    		  JLabel estadoCheckout = new JLabel("Estado do Check-out: ---- ");
			    		  panelCheckout.add(estadoCheckout);
			    		  
			    		  
			        	  
			      		  frameCheckout.setVisible(true);
			      		  
			      		  
			      		// Listener para botão.
			    		  checkout.addMouseListener(new MouseListener() {
			    			  
			    	          public void mouseClicked(MouseEvent evt) {
			    	        	  
			    	        	  double calculoPagamento = hotel.getCaixa();
			    	      			  			    	      			  
			    	        	  if ( hotel.realizarCheckout(inputNome.getText(), inputPassaporte.getText(),
			  								Integer.parseInt(inputCodigo.getText()), dataHotel) ) {
			    	        		  
			    	      			  calculoPagamento = hotel.getCaixa() - calculoPagamento;

			    	        		  estadoCheckout.setText("Check-out realizado com sucesso! - Pagamento: " + calculoPagamento);
			    	        		  atualizar();
			    	        	  }
			    	        	  else {
			    	        		  estadoCheckout.setText("Check-out não realizado");
			    	        		  
			    	        	  }
			    	      		  
			    	          }
			    	          
			    	          @Override public void mousePressed(MouseEvent evt) { }
			    	          @Override public void mouseReleased(MouseEvent evt) { }
			    	          @Override public void mouseEntered(MouseEvent evt) { }
			    	          @Override public void mouseExited(MouseEvent evt) { }
			    	          
			    		  });
			    		  
			          }
			          
			          @Override public void mousePressed(MouseEvent evt) { }
			          @Override public void mouseReleased(MouseEvent evt) { }
			          @Override public void mouseEntered(MouseEvent evt) { }
			          @Override public void mouseExited(MouseEvent evt) { }
			    
				});
	

		
		
		// INTERFACE PARA DATA.
				botaoData = new JButton("Data");
				panelPrincipal.add(botaoData);
				botaoData.setVisible(false);
				
				// Listener para botão.
				botaoData.addMouseListener(new MouseListener() {
					
			          @Override
			          public void mouseClicked(MouseEvent evt) {

			        	  JFrame frameData = new JFrame();
			        	  Container panelData = frameData.getContentPane();
			        	  panelData.setLayout(new BoxLayout(panelData, BoxLayout.PAGE_AXIS));
			    	      frameData.setSize(500, 200);

			        	  frameData.setTitle("Data"); 
			        	  
			        	  // Data
			        	  panelData.add( new JLabel("Dia - Mês - Ano - Hora):"));
			        	  
			        	  JTextField inputDia =  new JTextField(2);
			        	  panelData.add(inputDia);

			        	  JTextField inputMes =  new JTextField(2);
			        	  panelData.add(inputMes);
			        	  
			        	  JTextField inputAno =  new JTextField(4);
			        	  panelData.add(inputAno);
			        	  
			        	  JTextField inputHora =  new JTextField(2);
			        	  panelData.add(inputHora);  
			        	  
			        	  // Botao para data
			      		  JButton data = new JButton("Alterar");
			    		  panelData.add(data);
			    		  
			    		  // Label para mostrar estado das operações.
			    		  JLabel estadoData = new JLabel("Estado da Data: ---- ");
			    		  panelData.add(estadoData);

	    	    		  
			      		  frameData.setVisible(true);
			      		  
			      		  
			      		// Listener para botão.
			    		  data.addMouseListener(new MouseListener() {
			    			  
			    	          public void mouseClicked(MouseEvent evt) {
			    	    		  int hora = Integer.parseInt(inputHora.getText());
			    	    		  int dia = Integer.parseInt(inputDia.getText());
			    	    		  int mes = Integer.parseInt(inputMes.getText());
			    	    		  int ano = Integer.parseInt(inputAno.getText());
			    	        	  
			    	        	  
			    	      		  dataHotel = LocalDateTime.of(ano, mes, dia, hora, 0);
			    	      		  atualizar();
			    	      		  estadoData.setText("Estado da Data: Alterado com sucesso.");
			    	          }
			    	          
			    	          @Override public void mousePressed(MouseEvent evt) { }
			    	          @Override public void mouseReleased(MouseEvent evt) { }
			    	          @Override public void mouseEntered(MouseEvent evt) { }
			    	          @Override public void mouseExited(MouseEvent evt) { }
			    	          
			    		  });
			    		  
			          }
			          
			          @Override public void mousePressed(MouseEvent evt) { }
			          @Override public void mouseReleased(MouseEvent evt) { }
			          @Override public void mouseEntered(MouseEvent evt) { }
			          @Override public void mouseExited(MouseEvent evt) { }
			    
				});
		
		
		
				
		// INTERFACE PARA LISTA DE CLIENTES.
		botaoListaCliente = new JButton("Lista de clientes");
		panelPrincipal.add(botaoListaCliente);
		botaoListaCliente.setVisible(false);
		
		// Listener para botão.
		botaoListaCliente.addMouseListener(new MouseListener() {
			
	          @Override
	          public void mouseClicked(MouseEvent evt) {

	        	  JFrame frameListaClientes = new JFrame();
	        	  Container panelListaClientes = frameListaClientes.getContentPane();
	        	  panelListaClientes.setLayout(new BoxLayout(panelListaClientes, BoxLayout.PAGE_AXIS));
	    	      frameListaClientes.setSize(500, 150);

	        	  frameListaClientes.setTitle("Lista de Clientes"); 
	        	  
	    		  panelListaClientes.add(new JLabel("Lista de Clientes"));
	    		  
  	        	  JTextArea listaClientes = new JTextArea();
  	      		  panelListaClientes.add(listaClientes);
  	      		     	        	 
  	      		  listaClientes.setText(hotel.getClientes());
  	      		  listaClientes.setEditable(false);
  	      		  listaClientes.setLineWrap(true);
  	      		  listaClientes.setOpaque(false);
    	  
	      		  frameListaClientes.setVisible(true);

	          }
	          
	          @Override public void mousePressed(MouseEvent evt) { }
	          @Override public void mouseReleased(MouseEvent evt) { }
	          @Override public void mouseEntered(MouseEvent evt) { }
	          @Override public void mouseExited(MouseEvent evt) { }
	    });
		
		
		// INTERFACE PARA REGISTRO DE CLIENTES.
		panelPrincipal.add(Box.createRigidArea(new Dimension(0,5))); // Box para separação entre elementos.
		botaoRegistroCliente = new JButton("Registrar cliente");
		panelPrincipal.add(botaoRegistroCliente);
		botaoRegistroCliente.setVisible(false);
		
		
		// Listener para botão.
		botaoRegistroCliente.addMouseListener(new MouseListener() {
			
	          @Override
	          public void mouseClicked(MouseEvent evt) {

	        	  // Cria JTextFields para a entrada de todas as informações do cliente.
	        	  JFrame frameRegistroClientes = new JFrame();
	        	  Container panelRegistroClientes = frameRegistroClientes.getContentPane();
	        	  panelRegistroClientes.setLayout(new FlowLayout());
	    	      frameRegistroClientes.setSize(1000, 150);

	        	  frameRegistroClientes.setTitle("Novo Registro de Cliente"); 	  
	     
	        	  // Nome
	        	  panelRegistroClientes.add( new JLabel("Nome:"));
	        	  JTextField inputNome = new JTextField(30);
	        	  panelRegistroClientes.add(inputNome);
	        	  
	        	  // CPF
	        	  panelRegistroClientes.add( new JLabel("CPF:"));
	        	  JTextField inputCPF = new JTextField(15);
	        	  panelRegistroClientes.add(inputCPF);
	        	  
	        	  // Passaporte
	        	  panelRegistroClientes.add( new JLabel("Passaporte:"));
	        	  JTextField inputPassaporte = new JTextField(20);
	        	  panelRegistroClientes.add(inputPassaporte);
	        	  
	        	  // Nacionalidade
	        	  panelRegistroClientes.add( new JLabel("Nacionalidade:"));
	        	  JTextField inputNacionalidade =  new JTextField(10);
	        	  panelRegistroClientes.add(inputNacionalidade);
	        	  
	        	  // Email
	        	  panelRegistroClientes.add( new JLabel("Email:"));
	        	  JTextField inputEmail =  new JTextField(15);
	        	  panelRegistroClientes.add(inputEmail);
	        	   
	        	  // Telefone
	        	  panelRegistroClientes.add( new JLabel("Telefone:"));
	        	  JTextField inputTelefone =  new JTextField(10);
	        	  panelRegistroClientes.add(inputTelefone);

	        	  // Endereço
	        	  panelRegistroClientes.add( new JLabel("Endereço:"));
	        	  JTextField inputEndereco =  new JTextField(10);
	        	  panelRegistroClientes.add(inputEndereco);
	        	   
	        	  // Botao para efetuar registro
	      		  JButton registrar = new JButton("Registrar");
	    		  panelRegistroClientes.add(registrar);
	    		  
	    		  // Label para mostrar estado das operações.
	    		  JLabel estadoRegistro = new JLabel("Estado do Registro: ---- ");
	    		  panelRegistroClientes.add(estadoRegistro);
	    		  
	    		  // Listener para botão.
	    		  registrar.addMouseListener(new MouseListener() {
	    			  
	    	          public void mouseClicked(MouseEvent evt) {
	    	        	  
	    	        	  // Caso o cliente já esteja registrado.
	    	      		  if ( hotel.procurarCliente(inputNome.getText(), inputPassaporte.getText()) != null) {
	    	      			  
	    	      			  estadoRegistro.setText("Estado do Registro: Cliente ja registrado.");
	    	      			  
	    	      			  
	    	      		  }	// Caso não esteja.
	    	      		  else {
	  	      			  
	    	      			  hotel.registrarCliente(inputNome.getText(), inputCPF.getText(),  inputPassaporte.getText(),
	    	    	        	      inputNacionalidade.getText(), inputEmail.getText(), inputTelefone.getText(), 
	    	    	        		  inputEndereco.getText() );
	    	      			   
	    	      			  
	    	      			  
	    	      			  
	    	      			  estadoRegistro.setText("Estado do Registro: Registro efetuado com sucesso.");
	    	      		  }
	    	      		  
	    	          }
	    	          
	    	          @Override public void mousePressed(MouseEvent evt) { }
	    	          @Override public void mouseReleased(MouseEvent evt) { }
	    	          @Override public void mouseEntered(MouseEvent evt) { }
	    	          @Override public void mouseExited(MouseEvent evt) { }
	    	          
	    		  });
	        	  

	      		  frameRegistroClientes.setVisible(true);

	          }
	          
	          @Override public void mousePressed(MouseEvent evt) { }
	          @Override public void mouseReleased(MouseEvent evt) { }
	          @Override public void mouseEntered(MouseEvent evt) { }
	          @Override public void mouseExited(MouseEvent evt) { }
	    });
		
		
	
	
	
	
	// INTERFACE PARA REGISTRO DE FUNCIONARIOS.
	panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
	botaoRegistroFuncionario = new JButton("Registrar funcionario");
	panelPrincipal.add(botaoRegistroFuncionario);
	botaoRegistroFuncionario.setVisible(false);
	
	
	// Listener para botão.
	botaoRegistroFuncionario.addMouseListener(new MouseListener() {
		
          @Override
          public void mouseClicked(MouseEvent evt) {

        	  JFrame frameRegistroFuncionario = new JFrame();
        	  Container panelRegistroFuncionario = frameRegistroFuncionario.getContentPane();
        	  panelRegistroFuncionario.setLayout(new FlowLayout());
    	      frameRegistroFuncionario.setSize(1000, 150);

        	  frameRegistroFuncionario.setTitle("Novo Registro de Funcionario"); 	  
     
        	  // Cria JTextFields para a entrada de todas as informações do funcionário.
        	  
        	  // Nome
        	  panelRegistroFuncionario.add( new JLabel("Nome:"));
        	  JTextField inputNome = new JTextField(30);
        	  panelRegistroFuncionario.add(inputNome);
        	  
        	  // CPF
        	  panelRegistroFuncionario.add( new JLabel("CPF:"));
        	  JTextField inputCPF = new JTextField(15);
        	  panelRegistroFuncionario.add(inputCPF);
        	  
        	  // Passaporte
        	  panelRegistroFuncionario.add( new JLabel("Passaporte:"));
        	  JTextField inputPassaporte = new JTextField(20);
        	  panelRegistroFuncionario.add(inputPassaporte);
        	  
        	  // Nacionalidade
        	  panelRegistroFuncionario.add( new JLabel("Nacionalidade:"));
        	  JTextField inputNacionalidade =  new JTextField(10);
        	  panelRegistroFuncionario.add(inputNacionalidade);
        	  
        	  // Email
        	  panelRegistroFuncionario.add( new JLabel("Email:"));
        	  JTextField inputEmail =  new JTextField(15);
        	  panelRegistroFuncionario.add(inputEmail);
        	   
        	  // Telefone
        	  panelRegistroFuncionario.add( new JLabel("Telefone:"));
        	  JTextField inputTelefone =  new JTextField(10);
        	  panelRegistroFuncionario.add(inputTelefone);

        	  // Endereço
        	  panelRegistroFuncionario.add( new JLabel("Endereço:"));
        	  JTextField inputEndereco =  new JTextField(10);
        	  panelRegistroFuncionario.add(inputEndereco);
        	   
        	  // Senha
        	  panelRegistroFuncionario.add( new JLabel("Senha:"));
        	  JTextField inputSenha =  new JTextField(10);
        	  panelRegistroFuncionario.add(inputSenha);
        	  
        	  // Botao para registrar
      		  JButton registrar = new JButton("Registrar");
    		  panelRegistroFuncionario.add(registrar);
    		  
    		  // Label para mostrar estado das operações.
    		  JLabel estadoRegistro = new JLabel("Estado do Registro: ---- ");
    		  panelRegistroFuncionario.add(estadoRegistro);
    		  
    		  // Listener para botão
    		  registrar.addMouseListener(new MouseListener() {
    			  
    	          public void mouseClicked(MouseEvent evt) {
    	        	  
    	        	  // Caso funcionário já esteja registrado.
    	      		  if ( hotel.procurarFuncionario(inputNome.getText()) != null) {
    	      			  
    	      			  estadoRegistro.setText("Estado do Registro: Funcionario ja registrado.");
    	      			  
    	      			  
    	      		  } // Caso contrário.
    	      		  else {
  	      			  
    	      			  hotel.registrarFuncionario(inputNome.getText(), inputCPF.getText(),  inputPassaporte.getText(),
    	    	        	      inputNacionalidade.getText(), inputEmail.getText(), inputTelefone.getText(), 
    	    	        		  inputEndereco.getText(), Integer.parseInt(inputSenha.getText()) );
    	      			   
    	      			  
    	      			  
    	      			  
    	      			  estadoRegistro.setText("Estado do Registro: Registro efetuado com sucesso.");
    	      		  }
    	      		  
    	          }
    	          
    	          @Override public void mousePressed(MouseEvent evt) { }
    	          @Override public void mouseReleased(MouseEvent evt) { }
    	          @Override public void mouseEntered(MouseEvent evt) { }
    	          @Override public void mouseExited(MouseEvent evt) { }
    	          
    		  });
        	  

      		  frameRegistroFuncionario.setVisible(true);

          	}
          
          @Override public void mousePressed(MouseEvent evt) { }
          @Override public void mouseReleased(MouseEvent evt) { }
          @Override public void mouseEntered(MouseEvent evt) { }
          @Override public void mouseExited(MouseEvent evt) { }
          
	
    	});

	// INTERFACE PARA NOVAS RESERVAS.
	panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
	botaoReservas = new JButton("Nova reserva");
	panelPrincipal.add(botaoReservas);
	botaoReservas.setVisible(false);
	
	
	// Listener para botão.
	botaoReservas.addMouseListener(new MouseListener() {
		
          @Override
          public void mouseClicked(MouseEvent evt) {

        	  JFrame frameReservas = new JFrame();
        	  Container panelReserva = frameReservas.getContentPane();
        	  panelReserva.setLayout(new FlowLayout());
    	      frameReservas.setSize(1000, 150);

        	  frameReservas.setTitle("Nova Reserva"); 	  
     
        	  // Cria JTextFields para a entrada de todas as informações da reserva.
        	  
        	  // Tipo
        	  panelReserva.add( new JLabel("Tipo:"));
        	  JTextField inputTipo = new JTextField(15);
        	  panelReserva.add(inputTipo);
        	  
        	  // Hospede principal
        	  panelReserva.add( new JLabel("Hospede Principal:"));
        	  JTextField inputHospedePrincipal = new JTextField(30);
        	  panelReserva.add(inputHospedePrincipal);
        	  
        	  // Passaporte
        	  panelReserva.add( new JLabel("Passaporte:"));
        	  JTextField inputPassaporte = new JTextField(20);
        	  panelReserva.add(inputPassaporte);
        	  
        	  // Numero de diárias
        	  panelReserva.add( new JLabel("Numero de Diarias:"));
        	  JTextField inputNumeroDiarias =  new JTextField(5);
        	  panelReserva.add(inputNumeroDiarias);
        	  
        	  // Data
        	  panelReserva.add( new JLabel("Chegada(Dia - Mês - Ano):"));
        	  
        	  JTextField inputDiaChegada =  new JTextField(2);
        	  panelReserva.add(inputDiaChegada);

        	  JTextField inputMesChegada =  new JTextField(2);
        	  panelReserva.add(inputMesChegada);
        	  
        	  JTextField inputAnoChegada =  new JTextField(4);
        	  panelReserva.add(inputAnoChegada);
        	  
        	  // Forma de pagamento
        	  panelReserva.add( new JLabel("Forma de Pagamento:"));
        	  JTextField inputFormaPagamento =  new JTextField(10);
        	  panelReserva.add(inputFormaPagamento);

        	  // Desconto
        	  panelReserva.add( new JLabel("Desconto(%):"));
        	  JTextField inputDesconto =  new JTextField(10);
        	  panelReserva.add(inputDesconto);
        	  
        	  // Botão para reserva.
      		  JButton reservar = new JButton("Reservar");
    		  panelReserva.add(reservar);
    		  
    		  // Label para mostrar estado das operações
    		  JLabel estadoReserva = new JLabel("Estado da Reserva: ---- ");
    		  panelReserva.add(estadoReserva);
    		  
    		  
    		  // Listener para botão
    		  reservar.addMouseListener(new MouseListener() {
    			  
    	          public void mouseClicked(MouseEvent evt) {
    	        	  
    	        	  // Conversão dos inputs de data.
    	    		  int dia = Integer.parseInt(inputDiaChegada.getText());
    	    		  int mes = Integer.parseInt(inputMesChegada.getText());
    	    		  int ano = Integer.parseInt(inputAnoChegada.getText());
    	    		  
    	    		  // Caso cliente não esteja registrado.
    	      		  if ( hotel.procurarCliente(inputHospedePrincipal.getText(), inputPassaporte.getText()) == null) {
    	      			  
    	      			  estadoReserva.setText("Estado da Reserva: Cliente não encontrado.");
    	      			  
    	      			  
    	      		  } // Caso data seja inválida.
    	      		  else if ( ( dia > 31 || dia < 1 ) || ( mes > 12 || mes < 1 ) 
    	      				  	|| ano < dataHotel.getYear() || ( dia > 29 && mes == 2 ) || ( dia < dataHotel.getDayOfMonth() 
    	      				  	&& mes <= dataHotel.getMonthValue() && ano <= dataHotel.getYear()) ) {
    	      			  
    	      			estadoReserva.setText("Estado da Reserva: Data invalida.");
    	      			  
    	      			  
    	      			  
    	      		  }	// Caso as condições sejam cumpridas.
    	      		  else {
    	      			  LocalDateTime chegada = LocalDateTime.of(ano, mes, dia, 14, 0);
    	      			  
    	      			  boolean reservaEfetuada = false;
    	      			  
    	      			  try {
        	      			  reservaEfetuada =  hotel.reservar(inputTipo.getText(), Integer.parseInt(inputNumeroDiarias.getText()),
  	      					  		chegada, inputHospedePrincipal.getText(), 
  	      					  		inputPassaporte.getText(),  inputFormaPagamento.getText(), 
  	      					  		Double.parseDouble(inputDesconto.getText()) );
        	      			  
    	      			  }
    	      			  catch ( NullClientePointerException e1 ) {
	    	      			  estadoReserva.setText("Reserva não efetuada. Cliente não cadastrado. ");
    	      				  
    	      			  }
    	      			  catch ( NoRoomAvailabilityException e2 ) {
	    	      			  estadoReserva.setText("Reserva não efetuada. Sem disponibilidade para a data especificada. ");
    	      				  
    	      			  }
    	      			  
    	      			  
    	      			  // Caso seja efetuada.
    	      			  if ( reservaEfetuada == true ) {
	    	      			  estadoReserva.setText("Reserva Efetuada com sucesso.");
	    	      			  
	    	      			  // Atualiza interface
	    	      			  atualizar();
	    	      			  
	    	      			  
    	      			  }

    	      		  }
    	      		  
    	          }
    	          
    	          @Override public void mousePressed(MouseEvent evt) { }
    	          @Override public void mouseReleased(MouseEvent evt) { }
    	          @Override public void mouseEntered(MouseEvent evt) { }
    	          @Override public void mouseExited(MouseEvent evt) { }
    	          
    		  });
        	  

      		  frameReservas.setVisible(true);

          }
          
          @Override public void mousePressed(MouseEvent evt) { }
          @Override public void mouseReleased(MouseEvent evt) { }
          @Override public void mouseEntered(MouseEvent evt) { }
          @Override public void mouseExited(MouseEvent evt) { }
    });
	
	// INTERFACE PARA ATUALiZAR RESERVAS.
	panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
	botaoAtualizarReservas = new JButton("Atualizar reserva");
	panelPrincipal.add(botaoAtualizarReservas);
	botaoAtualizarReservas.setVisible(false);
	
	// Listener para botão.
	botaoAtualizarReservas.addMouseListener(new MouseListener() {
		
          @Override
          public void mouseClicked(MouseEvent evt) {

        	  JFrame frameAtualizarReserva = new JFrame();
        	  Container panelAtualizarReserva = frameAtualizarReserva.getContentPane();
        	  panelAtualizarReserva.setLayout(new FlowLayout());
        	  frameAtualizarReserva.setSize(1000, 150);
        	  JLabel estadoAtualizarReserva = new JLabel("Estado da Atualização: ---- ");

        	  
        	  frameAtualizarReserva.setTitle("Atualização de Reserva");
        	  
      		  JButton atualizar = new JButton("Atualizar Reserva");
    		  
        	  // Cria JTextFields para a entrada de todas as informações da atualização.

      		  // Codigo.
    		  panelAtualizarReserva.add( new JLabel("Codigo:"));
        	  JTextField inputCodigo =  new JTextField(10);
        	  panelAtualizarReserva.add(inputCodigo);
        	  
        	  // Tipo
        	  panelAtualizarReserva.add( new JLabel("Tipo:"));
        	  JTextField inputTipo = new JTextField(15);
        	  panelAtualizarReserva.add(inputTipo);
        	  
        	  // Nova Data
        	  panelAtualizarReserva.add( new JLabel("Nova Chegada(Dia - Mês - Ano - Horario):"));
        	  
        	  JTextField inputNovoDiaChegada =  new JTextField(2);
        	  panelAtualizarReserva.add(inputNovoDiaChegada);

        	  JTextField inputNovoMesChegada =  new JTextField(2);
        	  panelAtualizarReserva.add(inputNovoMesChegada);
        	  
        	  JTextField inputNovoAnoChegada =  new JTextField(4);
        	  panelAtualizarReserva.add(inputNovoAnoChegada);
        	  
       
        	  // Novo número de diárias
        	  panelAtualizarReserva.add( new JLabel("Novo número de diárias"));
        	  
        	  JTextField inputNovoNumDiarias =  new JTextField(2);
        	  panelAtualizarReserva.add(inputNovoNumDiarias);
        	  
        	  // Listener para botão.
        	  atualizar.addMouseListener(new MouseListener() {
    			  
    	          public void mouseClicked(MouseEvent evt) {
    	        	  
    	        	  // Conversão dos inputs da data.
    	    		  int novoDia = Integer.parseInt(inputNovoDiaChegada.getText());
    	    		  int novoMes = Integer.parseInt(inputNovoMesChegada.getText());
    	    		  int novoAno = Integer.parseInt(inputNovoAnoChegada.getText());
    	    		  
    	    		  // Testa validez da data.
    	      		  if ( ( novoDia > 31 || novoDia < 1 ) || ( novoMes > 12 || novoMes < 1 ) 
  	      				  	|| novoAno < dataHotel.getYear() || ( novoDia > 29 && novoMes == 2 ) || ( novoDia < dataHotel.getDayOfMonth() 
  	      				  	&& novoMes <= dataHotel.getMonthValue() && novoAno <= dataHotel.getYear()) ) {
  	      			  
	    	      			estadoAtualizarReserva.setText("Estado da Atualização: Data invalida.");
    	        	  }
    	        	  else {
    	        		  
	    	        	  LocalDateTime novaChegada = LocalDateTime.of(novoAno, novoMes, novoDia, 14, 0); // Cria data nova.
	    	        	  
	    	        	  // Caso seja fechada.
	    	      		  if ( hotel.atualizarReserva(Integer.parseInt(inputCodigo.getText()), inputTipo.getText(),  novaChegada, 
	    	      				  										Integer.parseInt(inputNovoNumDiarias.getText()))  	      				  										 ) {
	    	      			  estadoAtualizarReserva.setText("Estado da Atualização: Atualização efetuada com sucesso.");
	    	      			 
	    	      			  // Atualiza interface
	    	      			  atualizar();
	    	      			  
	    	      		  }
	    	      		  else { // Caso nao seja fechada.
	    	      			estadoAtualizarReserva.setText("Estado da Atualização: Atualização não efetuada.");
	    	      			  
	    	      		  }

    	        	  }
    	      		  
    	      		  
    	          }
    	          
    	          @Override public void mousePressed(MouseEvent evt) { }
    	          @Override public void mouseReleased(MouseEvent evt) { }
    	          @Override public void mouseEntered(MouseEvent evt) { }
    	          @Override public void mouseExited(MouseEvent evt) { }
    	          
    		  });
        	  
    		  panelAtualizarReserva.add(atualizar);
      		  frameAtualizarReserva.setVisible(true);
        	  panelAtualizarReserva.add(estadoAtualizarReserva);
        	  
		  };
		  

          @Override public void mousePressed(MouseEvent evt) { }
          @Override public void mouseReleased(MouseEvent evt) { }
          @Override public void mouseEntered(MouseEvent evt) { }
          @Override public void mouseExited(MouseEvent evt) { }

	
	});	    
	
	// INTERFACE PARA FECHAR RESERVAS.
	panelPrincipal.add(Box.createRigidArea(new Dimension(0,5)));
	botaoFecharReservas = new JButton("Fechar reserva");
	panelPrincipal.add(botaoFecharReservas);
	botaoFecharReservas.setVisible(false);
	
	// Listener para botão.
	botaoFecharReservas.addMouseListener(new MouseListener() {
		
          @Override
          public void mouseClicked(MouseEvent evt) {

        	  JFrame frameFecharReserva = new JFrame();
        	  Container panelFecharReserva = frameFecharReserva.getContentPane();
        	  panelFecharReserva.setLayout(new FlowLayout());
        	  frameFecharReserva.setSize(1000, 150);
        	  JLabel estadoFecharReserva = new JLabel("Estado do Fechamento: ---- ");
        	  
        	  frameFecharReserva.setTitle("Fechamento de Reserva");
        	  
      		  JButton fecharGeral = new JButton("Fechar Reserva");
    		  
        	  // Cria JTextFields para a entrada de todas as informações da reserva.

      		  // Codigo.
    		  panelFecharReserva.add( new JLabel("Codigo da Reserva:"));
        	  JTextField inputCodigo =  new JTextField(10);
        	  panelFecharReserva.add(inputCodigo);
        	  
        	  panelFecharReserva.add(estadoFecharReserva);
        	  
        	  // Listener para botão.
    		  fecharGeral.addMouseListener(new MouseListener() {
    			  
    	          public void mouseClicked(MouseEvent evt) {
    	        	  double calculoPagamento = hotel.getCaixa();
    	        	  
    	        	  // Caso seja fechada.
    	      		  if ( hotel.fecharReserva(Integer.parseInt(inputCodigo.getText()), dataHotel) ) {
    	      			  
    	      			  calculoPagamento = hotel.getCaixa() - calculoPagamento;
    	      			  
    	      			  estadoFecharReserva.setText("Estado do Fechamento: Fechamento efetuado. Multa(R$): " + calculoPagamento);
    	      			 
    	      			  // Atualiza interface
    	      			  atualizar();
    	      			  
    	      		  }
    	      		  else { // Caso nao seja fechada.
    	      			estadoFecharReserva.setText("Estado do Fechamento: Fechamento não efetuado.");
    	      			  
    	      		  }

	      		  
    	      		  
    	          }
    	          
    	          @Override public void mousePressed(MouseEvent evt) { }
    	          @Override public void mouseReleased(MouseEvent evt) { }
    	          @Override public void mouseEntered(MouseEvent evt) { }
    	          @Override public void mouseExited(MouseEvent evt) { }
    	          
    		  });
        	  
    		  panelFecharReserva.add(fecharGeral);
    		  
	      	  frameFecharReserva.setVisible(true);
  			  
    		  };
        	  
	          @Override public void mousePressed(MouseEvent evt) { }
	          @Override public void mouseReleased(MouseEvent evt) { }
	          @Override public void mouseEntered(MouseEvent evt) { }
	          @Override public void mouseExited(MouseEvent evt) { }
	
		
	});	          
	
	// Configurações do Frame principal
	panelPrincipal.setVisible(true);
    framePrincipal.add(panelPrincipal);
	framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    framePrincipal.setTitle("Hotel MC302"); 
    framePrincipal.setSize(520, 1000);             
    framePrincipal.setVisible(true);    


	
	}
	

}
