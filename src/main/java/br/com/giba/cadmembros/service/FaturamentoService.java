package br.com.giba.cadmembros.service;

import br.com.giba.cadmembros.dto.FaturamentoDto;
import br.com.giba.cadmembros.exception.ConflictException;
import br.com.giba.cadmembros.exception.NotFoundException;
import br.com.giba.cadmembros.model.Empresa;
import br.com.giba.cadmembros.model.FaturamentoAnual;
import br.com.giba.cadmembros.repository.FaturamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FaturamentoService {
    private final FaturamentoRepository faturamentos;
    private final EmpresaService empresas;
    public FaturamentoService(FaturamentoRepository faturamentos, EmpresaService empresas) {
        this.faturamentos = faturamentos; this.empresas = empresas;
    }
    public FaturamentoDto criar(String cnpj, FaturamentoDto dto) {
        Empresa empresa = empresas.buscarPorCnpj(cnpj);
        if (faturamentos.existsByEmpresa_IdAndAno(empresa.getId(), dto.ano()))
            throw new ConflictException("Já existe faturamento registrado para esta empresa e ano.");
        FaturamentoAnual f = new FaturamentoAnual();
        f.setEmpresa(empresa); f.setAno(dto.ano()); f.setValor(dto.valor());
        return FaturamentoDto.from(faturamentos.save(f));
    }
    public FaturamentoDto atualizar(String cnpj, Long id, FaturamentoDto dto) {
        FaturamentoAnual f = faturamentos.findById(id).orElseThrow(() -> new NotFoundException("Faturamento não encontrado: " + id));
        validarEmpresaDoFaturamento(cnpj, f);
        if (!f.getAno().equals(dto.ano()) && faturamentos.existsByEmpresa_IdAndAno(f.getEmpresa().getId(), dto.ano()))
            throw new ConflictException("Já existe faturamento registrado para esta empresa e ano.");
        f.setAno(dto.ano()); f.setValor(dto.valor());
        return FaturamentoDto.from(faturamentos.save(f));
    }
    @Transactional(readOnly = true)
    public FaturamentoDto consultar(String cnpj, Integer ano) {
        empresas.buscarPorCnpj(cnpj);
        if (ano == null) throw new IllegalArgumentException("Informe o parâmetro ano.");
        return faturamentos.findByEmpresa_CnpjAndAno(EmpresaService.digits(cnpj), ano)
                .map(FaturamentoDto::from).orElseThrow(() -> new NotFoundException("Faturamento não encontrado para o ano informado."));
    }
    public void excluir(String cnpj, Long id) {
        FaturamentoAnual f = faturamentos.findById(id).orElseThrow(() -> new NotFoundException("Faturamento não encontrado: " + id));
        validarEmpresaDoFaturamento(cnpj, f);
        faturamentos.delete(f);
    }

    private void validarEmpresaDoFaturamento(String cnpj, FaturamentoAnual faturamento) {
        if (!faturamento.getEmpresa().getCnpj().equals(EmpresaService.digits(cnpj))) {
            throw new NotFoundException("Faturamento não encontrado para a empresa indicada.");
        }
    }
}
