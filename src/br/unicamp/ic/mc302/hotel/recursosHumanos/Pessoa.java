package br.unicamp.ic.mc302.hotel.recursosHumanos;

import java.util.ArrayList;

public class Pessoa {

	// Dados principais.
	private String nome;
	private String cpf;
	private String passaporte;
	private String nacionalidade;
	
	// Dados secundários.
	private String email;
	private ArrayList<String> telefones;
	private String endereco;
	
	// Adiciona um telefone à lista de telefones.
	public void adicionarTelefone(String telefone) {
		this.telefones.add(telefone);
		
	}
	
	// Retira um telefone da lista de telefones.
	public void retirarTelefone(String telefone) {
		boolean encontrado = false;
		
		for(int i=0; i < this.telefones.size() && !encontrado ;i++){
			
	        if(this.telefones.get(i).equals(telefone) ){
	            encontrado = true;
	            telefones.remove(i);
	            
	        }
	        
		}
		
		
	}
	
	// Getters para os atributos da pessoa.
	
	public String getNome() {
		return this.nome;
		
	}
	
	public String getPassaporte() {
		return this.passaporte;
		
	}
	
	public String getCPF() {
		return this.cpf;
		
	}
	
	// Construtor.
	public Pessoa(String nome, String cpf, String passaporte, String nacionalidade,
					String email, String telefone1, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.passaporte = passaporte;
		this.nacionalidade = nacionalidade;
		
		this.email = email;
		telefones = new ArrayList<String>();
		this.telefones.add(telefone1);
		this.endereco = endereco;
		
	}

}
