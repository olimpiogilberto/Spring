package br.com.giba.cadmembros.controller;

import br.com.giba.cadmembros.dto.VinculoDto;
import br.com.giba.cadmembros.service.VinculoService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vinculos")
@Tag(name = "Vínculos", description = "Vínculos entre pessoas e empresas, incluindo cargo e salário.")
public class VinculoController {
    private final VinculoService service;
    public VinculoController(VinculoService service) { this.service = service; }
    @Operation(summary = "Cadastrar vínculo", description = "Uma pessoa pode ter somente um vínculo ativo por vez.")
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public VinculoDto criar(@Valid @RequestBody VinculoDto dto) { return service.criar(dto); }
    @Operation(summary = "Listar vínculos")
    @GetMapping public List<VinculoDto> listar() { return service.listar(); }
    @Operation(summary = "Consultar vínculo por ID")
    @GetMapping("/{id}") public VinculoDto buscar(@PathVariable Long id) { return service.buscar(id); }
    @Operation(summary = "Atualizar vínculo", description = "Substitui os dados do vínculo e valida as datas e a unicidade de vínculo ativo.")
    @PutMapping("/{id}") public VinculoDto atualizar(@PathVariable Long id, @Valid @RequestBody VinculoDto dto) {
        return service.atualizar(id, dto);
    }
    @Operation(summary = "Excluir vínculo")
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) { service.excluir(id); }
}
