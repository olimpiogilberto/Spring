package br.com.giba.cadmembros;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
	
	@Id
	private String cnpj;
	
	private String norazaosocial;
	
	private LocalDate dataCriacao;
}