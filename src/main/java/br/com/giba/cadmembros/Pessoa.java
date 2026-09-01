package br.com.giba.cadmembros;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Pessoa {
	@Id
	private String cpf;
	
	private String nome;
	
	private Integer idade;
	
	public Pessoa() {
		super();
	}
	
	public Pessoa(String cpf, String nome, Integer idade) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
	}
}
