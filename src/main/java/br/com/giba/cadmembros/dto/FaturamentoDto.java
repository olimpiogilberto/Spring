package br.com.giba.cadmembros.dto;

import br.com.giba.cadmembros.model.FaturamentoAnual;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(name = "FaturamentoAnual", description = "Faturamento de uma empresa em um ano civil.")
public record FaturamentoDto(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long id,
        @Schema(description = "Ano de referência entre 1900 e 9999.", example = "2025")
        @NotNull @Min(1900) @Max(9999) Integer ano,
        @Schema(description = "Valor anual não negativo.", example = "1200000.00")
        @NotNull @DecimalMin("0.00") BigDecimal valor) {
    public static FaturamentoDto from(FaturamentoAnual f) {
        return new FaturamentoDto(f.getId(), f.getAno(), f.getValor());
    }
}
