package br.com.giba.cadmembros.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enderecos")
@Getter @Setter @NoArgsConstructor
public class Endereco {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200) private String logradouro;
    @Column(length = 20) private String numero;
    @Column(length = 100) private String complemento;
    @Column(length = 100) private String bairro;
    @Column(nullable = false, length = 100) private String cidade;
    @Column(nullable = false, length = 2) private String estado;
    @Column(length = 9) private String cep;
    @Column(nullable = false, length = 100) private String pais = "Brasil";
}
