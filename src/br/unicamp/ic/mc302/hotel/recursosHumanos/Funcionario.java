package br.unicamp.ic.mc302.hotel.recursosHumanos;

// Classe para objeto do tipo Funcionário
public class Funcionario extends Pessoa {

	// Atributos do funcionário
	private double salario;
	private int senha;
	
	// Retorna o salário do funcionário
	public double getSalario() {
		return this.salario;
		
	}
	
	// Define o salário do funcionário
	protected void setSalario(double salario) {
		this.salario = salario;
		
	}
	
	// Verifica senha do funcionário.
	public boolean verificaSenha(int senha) {
		return this.senha == senha;
		
	}
	
	// Construtor
	public Funcionario(String nome, String cpf, String passaporte, String nacionalidade,
			String email, String telefone1, String endereco, int senha) {
				
		super(nome, cpf, passaporte, nacionalidade, email, telefone1, endereco);
		this.senha = senha;
	}
	
}
