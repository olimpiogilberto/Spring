package br.com.giba.cadmembros.dto;

import br.com.giba.cadmembros.model.Empresa;
import br.com.giba.cadmembros.model.SituacaoEmpresa;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import br.com.giba.cadmembros.validation.CnpjValido;

@Schema(name = "Empresa", description = "Dados de cadastro de uma empresa.")
public record EmpresaDto(
       @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long id,
       @Schema(description = "CNPJ válido; pode ser enviado com pontuação ou somente dígitos.", example = "11.222.333/0001-81")
       @NotBlank @CnpjValido String cnpj,
       @Schema(description = "Razão social, com até 150 caracteres.", example = "Acme Ltda")
       @NotBlank @Size(max = 150) String razaoSocial,
       @Schema(description = "Situação cadastral da empresa.")
       @NotNull SituacaoEmpresa situacao,
       @Valid EnderecoDto endereco) {
    public static EmpresaDto from(Empresa e) {
        return new EmpresaDto(e.getId(), e.getCnpj(), e.getRazaoSocial(), e.getSituacao(),
                e.getEndereco() == null ? null : EnderecoMapper.from(e.getEndereco()));
    }
}
