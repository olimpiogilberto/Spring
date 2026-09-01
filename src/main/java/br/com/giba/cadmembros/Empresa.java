package br.com.giba.cadmembros;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Empresa {
	
	@Id
	private String cnpj;
	
	private String norazaosocial;
	
	private LocalDate dataCriacao;
	
	public Empresa() {
		super();
	}
	
	public Empresa(String cnpj, String norazaosocial, LocalDate dataCriacao) {
		super();
		this.cnpj = cnpj;
		this.norazaosocial = norazaosocial;
		this.dataCriacao = dataCriacao;
	}
}