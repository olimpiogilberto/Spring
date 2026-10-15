package br.com.giba.cadmembros.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "pessoas", uniqueConstraints = @UniqueConstraint(name = "uk_pessoa_cpf", columnNames = "cpf"))
@Getter @Setter @NoArgsConstructor
public class Pessoa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 11, unique = true) private String cpf;
    @Column(nullable = false, length = 150) private String nome;
    @Column(nullable = false) private LocalDate dataNascimento;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private SituacaoPessoa situacao;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "endereco_id", unique = true)
    private Endereco endereco;
}
