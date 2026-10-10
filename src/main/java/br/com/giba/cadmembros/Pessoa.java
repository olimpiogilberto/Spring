package br.com.giba.cadmembros;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
	@Id
	private String cpf;
	
	private String nome;
	
	private Integer idade;
}
