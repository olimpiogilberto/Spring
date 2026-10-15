package br.com.giba.cadmembros.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "faturamentos_anuais", uniqueConstraints = @UniqueConstraint(name = "uk_faturamento_empresa_ano", columnNames = {"empresa_id", "ano"}))
@Getter @Setter @NoArgsConstructor
public class FaturamentoAnual {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY) @JoinColumn(name = "empresa_id", nullable = false) private Empresa empresa;
    @Column(nullable = false) private Integer ano;
    @Column(nullable = false, precision = 15, scale = 2) private BigDecimal valor;
}
