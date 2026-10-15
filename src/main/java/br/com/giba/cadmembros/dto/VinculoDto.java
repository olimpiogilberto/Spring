package br.com.giba.cadmembros.dto;

import br.com.giba.cadmembros.model.Cargo;
import br.com.giba.cadmembros.model.StatusVinculo;
import br.com.giba.cadmembros.model.VinculoEmprego;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(name = "Vinculo", description = "Relação de uma pessoa com uma empresa, cargo, salário e datas.")
public record VinculoDto(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long id,
        @Schema(description = "ID de pessoa previamente cadastrada.", example = "1")
        @NotNull @Positive Long pessoaId,
        @Schema(description = "ID de empresa previamente cadastrada.", example = "1")
        @NotNull @Positive Long empresaId,
        @NotNull Cargo cargo,
        @Schema(description = "Salário não negativo.", example = "5250.50")
        @NotNull @DecimalMin("0.00") BigDecimal salario,
        @Schema(description = "Data inicial ISO yyyy-MM-dd; não pode ser futura.", example = "2023-01-01")
        @NotNull LocalDate dataInicio,
        @Schema(description = "Data final ISO yyyy-MM-dd. Obrigatória para vínculo ENCERRADO e proibida para ATIVO.", example = "2025-12-31")
        LocalDate dataFim,
        @NotNull StatusVinculo status) {
    public static VinculoDto from(VinculoEmprego v) {
        return new VinculoDto(v.getId(), v.getPessoa().getId(), v.getEmpresa().getId(), v.getCargo(), v.getSalario(),
                v.getDataInicio(), v.getDataFim(), v.getStatus());
    }
}
