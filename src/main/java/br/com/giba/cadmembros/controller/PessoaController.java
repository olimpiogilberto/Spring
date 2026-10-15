package br.com.giba.cadmembros.controller;

import br.com.giba.cadmembros.dto.EnderecoDto;
import br.com.giba.cadmembros.dto.PessoaDto;
import br.com.giba.cadmembros.service.PessoaService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
@Tag(name = "Pessoas", description = "Cadastro, consulta e endereços das pessoas.")
public class PessoaController {
    private final PessoaService service;
    public PessoaController(PessoaService service) { this.service = service; }
    @Operation(summary = "Cadastrar pessoa", description = "Cria pessoa; CPF deve ser válido e único. Endereço é opcional.")
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public PessoaDto criar(@Valid @RequestBody PessoaDto dto) { return service.criar(dto); }
    @Operation(summary = "Listar pessoas")
    @GetMapping public List<PessoaDto> listar() { return service.listar(); }
    @Operation(summary = "Consultar pessoa por ID")
    @GetMapping("/{id}") public PessoaDto buscar(@PathVariable Long id) { return service.buscar(id); }
    @Operation(summary = "Atualizar pessoa", description = "Substitui os dados cadastrais da pessoa.")
    @PutMapping("/{id}") public PessoaDto atualizar(@PathVariable Long id, @Valid @RequestBody PessoaDto dto) {
        return service.atualizar(id, dto);
    }
    @Operation(summary = "Excluir pessoa", description = "Pode retornar conflito se houver registros relacionados.")
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) { service.excluir(id); }
    @Operation(summary = "Criar ou substituir endereço da pessoa")
    @PutMapping("/{id}/endereco") public PessoaDto atualizarEndereco(@PathVariable Long id, @Valid @RequestBody EnderecoDto dto) {
        return service.atualizarEndereco(id, dto);
    }
    @Operation(summary = "Remover endereço da pessoa")
    @DeleteMapping("/{id}/endereco") public PessoaDto removerEndereco(@PathVariable Long id) {
        return service.removerEndereco(id);
    }
}
