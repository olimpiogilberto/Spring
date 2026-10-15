package br.com.giba.cadmembros.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "vinculos")
@Getter @Setter @NoArgsConstructor
public class VinculoEmprego {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY) @JoinColumn(name = "pessoa_id", nullable = false) private Pessoa pessoa;
    @ManyToOne(optional = false, fetch = FetchType.LAZY) @JoinColumn(name = "empresa_id", nullable = false) private Empresa empresa;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 40) private Cargo cargo;
    @Column(nullable = false, precision = 12, scale = 2) private BigDecimal salario;
    @Column(nullable = false) private LocalDate dataInicio;
    private LocalDate dataFim;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private StatusVinculo status;
}
