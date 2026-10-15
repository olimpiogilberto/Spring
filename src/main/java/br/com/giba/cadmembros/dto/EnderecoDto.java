package br.com.giba.cadmembros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Endereco", description = "Endereço opcional de uma pessoa ou empresa.")
public record EnderecoDto(
        @Schema(description = "Logradouro.", example = "Rua Um")
        @NotBlank @Size(max = 200) String logradouro,
        @Schema(example = "123")
        @Size(max = 20) String numero,
        @Schema(example = "Apto 12")
        @Size(max = 100) String complemento,
        @Schema(example = "Centro")
        @Size(max = 100) String bairro,
        @Schema(example = "São Paulo")
        @NotBlank @Size(max = 100) String cidade,
        @Schema(description = "Unidade federativa com duas letras.", example = "SP")
        @NotBlank @Pattern(regexp = "[A-Za-z]{2}") String estado,
        @Schema(description = "CEP com ou sem hífen.", example = "01000-000")
        @Pattern(regexp = "\\d{5}-?\\d{3}") String cep,
        @Schema(description = "País; quando omitido, o valor persistido é Brasil.", example = "Brasil")
        @Size(max = 100) String pais) {}
