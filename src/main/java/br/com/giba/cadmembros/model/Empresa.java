package br.com.giba.cadmembros.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empresas", uniqueConstraints = @UniqueConstraint(name = "uk_empresa_cnpj", columnNames = "cnpj"))
@Getter @Setter @NoArgsConstructor
public class Empresa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 14, unique = true) private String cnpj;
    @Column(nullable = false, length = 150) private String razaoSocial;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private SituacaoEmpresa situacao;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "endereco_id", unique = true)
    private Endereco endereco;
}
