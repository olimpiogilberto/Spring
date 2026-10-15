package br.com.giba.cadmembros.dto;

public record ConsultaEmpresaDto(String cpf, String nome, Long empresaId, String cnpj,
        String razaoSocial, String cargo, String status) {}
