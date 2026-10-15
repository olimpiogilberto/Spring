package br.com.giba.cadmembros.service;

import br.com.giba.cadmembros.dto.*;
import br.com.giba.cadmembros.exception.ConflictException;
import br.com.giba.cadmembros.exception.NotFoundException;
import br.com.giba.cadmembros.model.Empresa;
import br.com.giba.cadmembros.repository.EmpresaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class EmpresaService {
    private final EmpresaRepository empresas;
    public EmpresaService(EmpresaRepository empresas) { this.empresas = empresas; }

    public EmpresaDto criar(EmpresaDto dto) {
        String cnpj = digits(dto.cnpj());
        if (empresas.existsByCnpj(cnpj)) throw new ConflictException("Já existe empresa com este CNPJ.");
        Empresa empresa = new Empresa();
        preencher(empresa, dto, cnpj);
        return EmpresaDto.from(empresas.save(empresa));
    }
    public EmpresaDto atualizar(Long id, EmpresaDto dto) {
        Empresa empresa = buscarEntidade(id);
        String cnpj = digits(dto.cnpj());
        if (!empresa.getCnpj().equals(cnpj) && empresas.existsByCnpj(cnpj))
            throw new ConflictException("Já existe empresa com este CNPJ.");
        preencher(empresa, dto, cnpj);
        return EmpresaDto.from(empresas.save(empresa));
    }
    @Transactional(readOnly = true)
    public List<EmpresaDto> listar() { return empresas.findAll().stream().map(EmpresaDto::from).toList(); }
    @Transactional(readOnly = true)
    public EmpresaDto buscar(Long id) { return EmpresaDto.from(buscarEntidade(id)); }
    public void excluir(Long id) { empresas.delete(buscarEntidade(id)); }
    public EmpresaDto atualizarEndereco(Long id, EnderecoDto dto) {
        Empresa empresa = buscarEntidade(id);
        empresa.setEndereco(EnderecoMapper.toEntity(dto));
        return EmpresaDto.from(empresas.save(empresa));
    }
    public EmpresaDto removerEndereco(Long id) {
        Empresa empresa = buscarEntidade(id);
        empresa.setEndereco(null);
        return EmpresaDto.from(empresas.save(empresa));
    }
    public Empresa buscarEntidade(Long id) {
        return empresas.findById(id).orElseThrow(() -> new NotFoundException("Empresa não encontrada: " + id));
    }
    public Empresa buscarPorCnpj(String cnpj) {
        return empresas.findByCnpj(digits(cnpj)).orElseThrow(() -> new NotFoundException("Empresa não encontrada para o CNPJ informado."));
    }
    private void preencher(Empresa empresa, EmpresaDto dto, String cnpj) {
        empresa.setCnpj(cnpj); empresa.setRazaoSocial(dto.razaoSocial().trim()); empresa.setSituacao(dto.situacao());
        empresa.setEndereco(EnderecoMapper.toEntity(dto.endereco()));
    }
    public static String digits(String value) { return value.replaceAll("\\D", ""); }
}
