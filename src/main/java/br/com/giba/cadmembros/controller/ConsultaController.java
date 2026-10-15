package br.com.giba.cadmembros.controller;

import br.com.giba.cadmembros.dto.ConsultaEmpresaDto;
import br.com.giba.cadmembros.dto.ContagemEmpregadosDto;
import br.com.giba.cadmembros.dto.FaturamentoDto;
import br.com.giba.cadmembros.dto.SalarioDto;
import br.com.giba.cadmembros.service.ConsultaService;
import br.com.giba.cadmembros.service.EmpresaService;
import br.com.giba.cadmembros.service.FaturamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consultas")
@Tag(name = "Consultas", description = "Consultas de vínculos, empregados ativos, salários e faturamento.")
public class ConsultaController {
    private final ConsultaService consultas;
    private final EmpresaService empresas;
    private final FaturamentoService faturamentos;
    public ConsultaController(ConsultaService consultas, EmpresaService empresas, FaturamentoService faturamentos) {
        this.consultas = consultas; this.empresas = empresas; this.faturamentos = faturamentos;
    }
    @Operation(summary = "Consultar empresa por CPF", description = "Retorna a empresa do vínculo ativo da pessoa.")
    @GetMapping("/empresa-por-cpf/{cpf}") public ConsultaEmpresaDto empresaPorCpf(@PathVariable String cpf) {
        return consultas.empresaPorCpf(cpf);
    }
    @Operation(summary = "Contar empregados ativos por CNPJ")
    @GetMapping("/empregados/{cnpj}/contagem") public ContagemEmpregadosDto contar(@PathVariable String cnpj) {
        return new ContagemEmpregadosDto(empresas.buscarPorCnpj(cnpj).getCnpj(), consultas.contarEmpregados(cnpj));
    }
    @Operation(summary = "Consultar salário por CPF", description = "Retorna o salário do vínculo ativo da pessoa.")
    @GetMapping("/salario/{cpf}") public SalarioDto salario(@PathVariable String cpf) {
        return new SalarioDto(br.com.giba.cadmembros.service.EmpresaService.digits(cpf), consultas.salarioPorCpf(cpf));
    }
    @Operation(summary = "Consultar faturamento por empresa e ano")
    @GetMapping("/faturamento/{cnpj}") public FaturamentoDto faturamento(@PathVariable String cnpj, @RequestParam Integer ano) {
        return faturamentos.consultar(cnpj, ano);
    }
}
