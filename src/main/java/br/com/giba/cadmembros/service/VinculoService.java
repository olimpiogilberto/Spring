package br.com.giba.cadmembros.service;

import br.com.giba.cadmembros.dto.VinculoDto;
import br.com.giba.cadmembros.exception.ConflictException;
import br.com.giba.cadmembros.exception.NotFoundException;
import br.com.giba.cadmembros.model.*;
import br.com.giba.cadmembros.repository.VinculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class VinculoService {
    private final VinculoRepository vinculos;
    private final PessoaService pessoas;
    private final EmpresaService empresas;
    public VinculoService(VinculoRepository vinculos, PessoaService pessoas, EmpresaService empresas) {
        this.vinculos = vinculos; this.pessoas = pessoas; this.empresas = empresas;
    }
    public VinculoDto criar(VinculoDto dto) {
        validarDatas(dto);
        if (dto.status() == StatusVinculo.ATIVO &&
                !vinculos.findByPessoa_CpfAndStatus(pessoas.buscarEntidade(dto.pessoaId()).getCpf(), StatusVinculo.ATIVO).isEmpty())
            throw new ConflictException("A pessoa já possui um vínculo ativo.");
        VinculoEmprego v = new VinculoEmprego();
        preencher(v, dto);
        return VinculoDto.from(vinculos.save(v));
    }
    public VinculoDto atualizar(Long id, VinculoDto dto) {
        validarDatas(dto);
        VinculoEmprego v = buscarEntidade(id);
        if (dto.status() == StatusVinculo.ATIVO) {
            boolean outroAtivo = vinculos.findByPessoa_CpfAndStatus(pessoas.buscarEntidade(dto.pessoaId()).getCpf(), StatusVinculo.ATIVO)
                    .stream().anyMatch(item -> !item.getId().equals(id));
            if (outroAtivo) throw new ConflictException("A pessoa já possui outro vínculo ativo.");
        }
        preencher(v, dto);
        return VinculoDto.from(vinculos.save(v));
    }
    @Transactional(readOnly = true)
    public List<VinculoDto> listar() { return vinculos.findAll().stream().map(VinculoDto::from).toList(); }
    @Transactional(readOnly = true)
    public VinculoDto buscar(Long id) { return VinculoDto.from(buscarEntidade(id)); }
    public void excluir(Long id) { vinculos.delete(buscarEntidade(id)); }
    public VinculoEmprego buscarEntidade(Long id) {
        return vinculos.findById(id).orElseThrow(() -> new NotFoundException("Vínculo não encontrado: " + id));
    }
    private void preencher(VinculoEmprego v, VinculoDto dto) {
        v.setPessoa(pessoas.buscarEntidade(dto.pessoaId())); v.setEmpresa(empresas.buscarEntidade(dto.empresaId()));
        v.setCargo(dto.cargo()); v.setSalario(dto.salario()); v.setDataInicio(dto.dataInicio());
        v.setDataFim(dto.dataFim()); v.setStatus(dto.status());
    }
    private void validarDatas(VinculoDto dto) {
        if (dto.dataInicio() != null && dto.dataInicio().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("A data inicial do vínculo não pode estar no futuro.");
        if (dto.dataFim() != null && dto.dataInicio() != null && dto.dataFim().isBefore(dto.dataInicio()))
            throw new IllegalArgumentException("A data final não pode ser anterior à data inicial.");
        if (dto.dataFim() != null && dto.dataFim().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("A data final do vínculo não pode estar no futuro.");
        if (dto.status() == StatusVinculo.ATIVO && dto.dataFim() != null)
            throw new IllegalArgumentException("Vínculo ativo não pode possuir data final.");
        if (dto.status() == StatusVinculo.ENCERRADO && dto.dataFim() == null)
            throw new IllegalArgumentException("Vínculo encerrado deve possuir data final.");
    }
}
