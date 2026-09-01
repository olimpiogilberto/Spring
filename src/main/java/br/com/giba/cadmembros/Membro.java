package br.com.giba.cadmembros;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

public class Membro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Pessoa pessoa;
	
	@ManyToOne
	private Empresa empresa;
	
	@Enumerated(EnumType.STRING)
	private Cargo cargo;
	
	public Membro() {
		super();
	}
	
	public Membro(Pessoa pessoa, Empresa empresa, Cargo cargo) {
		super();
		this.pessoa = pessoa;
		this.empresa = empresa;
		this.cargo = cargo;
	}

}