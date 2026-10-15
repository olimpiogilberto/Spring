package br.com.giba.cadmembros.controller;

import br.com.giba.cadmembros.dto.FaturamentoDto;
import br.com.giba.cadmembros.service.FaturamentoService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empresas/{cnpj}/faturamentos")
@Tag(name = "Faturamento anual", description = "Registro e consulta de faturamento anual por empresa.")
public class FaturamentoController {
    private final FaturamentoService service;
    public FaturamentoController(FaturamentoService service) { this.service = service; }
    @Operation(summary = "Registrar faturamento anual", description = "Permite somente um registro para cada empresa e ano.")
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public FaturamentoDto criar(@PathVariable String cnpj, @Valid @RequestBody FaturamentoDto dto) {
        return service.criar(cnpj, dto);
    }
    @Operation(summary = "Consultar faturamento por empresa e ano")
    @GetMapping public FaturamentoDto consultar(@PathVariable String cnpj, @RequestParam Integer ano) {
        return service.consultar(cnpj, ano);
    }
    @Operation(summary = "Atualizar faturamento por ID", description = "O CNPJ da rota deve corresponder à empresa proprietária do registro.")
    @PutMapping("/registro/{id}") public FaturamentoDto atualizar(
            @PathVariable String cnpj, @PathVariable Long id, @Valid @RequestBody FaturamentoDto dto) {
        return service.atualizar(cnpj, id, dto);
    }
    @Operation(summary = "Excluir faturamento por ID", description = "O CNPJ da rota deve corresponder à empresa proprietária do registro.")
    @DeleteMapping("/registro/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable String cnpj, @PathVariable Long id) { service.excluir(cnpj, id); }
}
