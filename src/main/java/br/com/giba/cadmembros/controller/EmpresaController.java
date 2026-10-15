package br.com.giba.cadmembros.controller;

import br.com.giba.cadmembros.dto.EmpresaDto;
import br.com.giba.cadmembros.dto.EnderecoDto;
import br.com.giba.cadmembros.service.EmpresaService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@Tag(name = "Empresas", description = "Cadastro, consulta e endereços das empresas.")
public class EmpresaController {
    private final EmpresaService service;
    public EmpresaController(EmpresaService service) { this.service = service; }
    @Operation(summary = "Cadastrar empresa", description = "Cria empresa; CNPJ deve ser válido e único. Endereço é opcional.")
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public EmpresaDto criar(@Valid @RequestBody EmpresaDto dto) { return service.criar(dto); }
    @Operation(summary = "Listar empresas")
    @GetMapping public List<EmpresaDto> listar() { return service.listar(); }
    @Operation(summary = "Consultar empresa por ID")
    @GetMapping("/{id}") public EmpresaDto buscar(@PathVariable Long id) { return service.buscar(id); }
    @Operation(summary = "Atualizar empresa", description = "Substitui os dados cadastrais da empresa.")
    @PutMapping("/{id}") public EmpresaDto atualizar(@PathVariable Long id, @Valid @RequestBody EmpresaDto dto) {
        return service.atualizar(id, dto);
    }
    @Operation(summary = "Excluir empresa", description = "Pode retornar conflito se houver registros relacionados.")
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) { service.excluir(id); }
    @Operation(summary = "Criar ou substituir endereço da empresa")
    @PutMapping("/{id}/endereco") public EmpresaDto atualizarEndereco(@PathVariable Long id, @Valid @RequestBody EnderecoDto dto) {
        return service.atualizarEndereco(id, dto);
    }
    @Operation(summary = "Remover endereço da empresa")
    @DeleteMapping("/{id}/endereco") public EmpresaDto removerEndereco(@PathVariable Long id) {
        return service.removerEndereco(id);
    }
}
