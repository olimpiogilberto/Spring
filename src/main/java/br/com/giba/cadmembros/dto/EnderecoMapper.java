package br.com.giba.cadmembros.dto;

import br.com.giba.cadmembros.model.Endereco;

public final class EnderecoMapper {
    private EnderecoMapper() {}
    public static EnderecoDto from(Endereco e) {
        return new EnderecoDto(e.getLogradouro(), e.getNumero(), e.getComplemento(), e.getBairro(), e.getCidade(),
                e.getEstado(), e.getCep(), e.getPais());
    }
    public static Endereco toEntity(EnderecoDto d) {
        if (d == null) return null;
        Endereco e = new Endereco();
        e.setLogradouro(d.logradouro()); e.setNumero(d.numero()); e.setComplemento(d.complemento());
        e.setBairro(d.bairro()); e.setCidade(d.cidade()); e.setEstado(d.estado().toUpperCase());
        e.setCep(d.cep()); e.setPais(d.pais() == null || d.pais().isBlank() ? "Brasil" : d.pais());
        return e;
    }
}
