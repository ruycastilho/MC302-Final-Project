package br.unicamp.ic.mc302.hotel.espacosFisicos;

public final class EspacoFisicoAbstractFactory {

	
	public SuiteSimples createSuiteSimples(int codigo) {
		
		return new SuiteSimples(codigo);
		
	}
	
	public SuiteDupla createSuiteDupla(int codigo) {

		return new SuiteDupla(codigo);		
		
	}
	
	public SuitePresidencial createSuitePresidencial(int codigo) {
		
		return new SuitePresidencial(codigo);
		
	}
	
	public SalaoEvento createSalaoEvento(int capacidadeMaximaPessoas, int codigo) {
		
		return new SalaoEvento(capacidadeMaximaPessoas, codigo);
		
	}
	
	public Auditorio createAuditorio(int capacidadePessoas, int codigo) {
		
		return new Auditorio(capacidadePessoas, codigo);
		
	}
	

}
