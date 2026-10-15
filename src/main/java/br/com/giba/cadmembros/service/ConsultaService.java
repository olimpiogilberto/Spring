package br.com.giba.cadmembros.service;

import br.com.giba.cadmembros.dto.ConsultaEmpresaDto;
import br.com.giba.cadmembros.exception.NotFoundException;
import br.com.giba.cadmembros.model.StatusVinculo;
import br.com.giba.cadmembros.model.VinculoEmprego;
import br.com.giba.cadmembros.repository.VinculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ConsultaService {
    private final VinculoRepository vinculos;
    private final PessoaService pessoas;
    private final EmpresaService empresas;
    public ConsultaService(VinculoRepository vinculos, PessoaService pessoas, EmpresaService empresas) {
        this.vinculos = vinculos; this.pessoas = pessoas; this.empresas = empresas;
    }
    public ConsultaEmpresaDto empresaPorCpf(String cpf) {
        var pessoa = pessoas.buscarPorCpf(cpf);
        List<VinculoEmprego> ativos = vinculos.findByPessoa_CpfAndStatus(pessoa.getCpf(), StatusVinculo.ATIVO);
        if (ativos.isEmpty()) throw new NotFoundException("A pessoa não possui vínculo ativo.");
        VinculoEmprego v = ativos.get(0);
        return new ConsultaEmpresaDto(pessoa.getCpf(), pessoa.getNome(), v.getEmpresa().getId(), v.getEmpresa().getCnpj(),
                v.getEmpresa().getRazaoSocial(), v.getCargo().name(), v.getStatus().name());
    }
    public long contarEmpregados(String cnpj) {
        empresas.buscarPorCnpj(cnpj);
        return vinculos.countByEmpresa_CnpjAndStatus(EmpresaService.digits(cnpj), StatusVinculo.ATIVO);
    }
    public BigDecimal salarioPorCpf(String cpf) {
        var pessoa = pessoas.buscarPorCpf(cpf);
        List<BigDecimal> salarios = vinculos.salariosAtivos(pessoa.getCpf(), StatusVinculo.ATIVO);
        if (salarios.isEmpty()) throw new NotFoundException("A pessoa não possui salário de vínculo ativo.");
        return salarios.get(0);
    }
}
