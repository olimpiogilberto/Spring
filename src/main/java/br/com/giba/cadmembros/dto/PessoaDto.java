package br.com.giba.cadmembros.dto;

import br.com.giba.cadmembros.model.Pessoa;
import br.com.giba.cadmembros.model.SituacaoPessoa;
import br.com.giba.cadmembros.validation.CpfValido;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Schema(name = "Pessoa", description = "Dados de cadastro de uma pessoa.")
public record PessoaDto(
        @Schema(accessMode = Schema.AccessMode.READ_ONLY) Long id,
        @Schema(description = "CPF válido; pode ser enviado com pontuação ou somente dígitos.", example = "529.982.247-25")
        @NotBlank @CpfValido String cpf,
        @Schema(description = "Nome completo, com até 150 caracteres.", example = "Ana Souza")
        @NotBlank @Size(max = 150) String nome,
        @Schema(description = "Data de nascimento no formato ISO yyyy-MM-dd; deve ser anterior à data atual.", example = "1990-01-02")
        @NotNull @Past LocalDate dataNascimento,
        @NotNull SituacaoPessoa situacao,
        @Valid EnderecoDto endereco) {
    public static PessoaDto from(Pessoa p) {
        return new PessoaDto(p.getId(), p.getCpf(), p.getNome(), p.getDataNascimento(), p.getSituacao(),
                p.getEndereco() == null ? null : EnderecoMapper.from(p.getEndereco()));
    }
}
