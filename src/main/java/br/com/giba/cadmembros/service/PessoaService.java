package br.com.giba.cadmembros.service;

import br.com.giba.cadmembros.dto.EnderecoMapper;
import br.com.giba.cadmembros.dto.PessoaDto;
import br.com.giba.cadmembros.exception.ConflictException;
import br.com.giba.cadmembros.exception.NotFoundException;
import br.com.giba.cadmembros.model.Pessoa;
import br.com.giba.cadmembros.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class PessoaService {
    private final PessoaRepository pessoas;
    public PessoaService(PessoaRepository pessoas) { this.pessoas = pessoas; }
    public PessoaDto criar(PessoaDto dto) {
        String cpf = EmpresaService.digits(dto.cpf());
        if (pessoas.existsByCpf(cpf)) throw new ConflictException("Já existe pessoa com este CPF.");
        Pessoa pessoa = new Pessoa(); preencher(pessoa, dto, cpf);
        return PessoaDto.from(pessoas.save(pessoa));
    }
    public PessoaDto atualizar(Long id, PessoaDto dto) {
        Pessoa pessoa = buscarEntidade(id);
        String cpf = EmpresaService.digits(dto.cpf());
        if (!pessoa.getCpf().equals(cpf) && pessoas.existsByCpf(cpf)) throw new ConflictException("Já existe pessoa com este CPF.");
        preencher(pessoa, dto, cpf);
        return PessoaDto.from(pessoas.save(pessoa));
    }
    @Transactional(readOnly = true)
    public List<PessoaDto> listar() { return pessoas.findAll().stream().map(PessoaDto::from).toList(); }
    @Transactional(readOnly = true)
    public PessoaDto buscar(Long id) { return PessoaDto.from(buscarEntidade(id)); }
    public void excluir(Long id) { pessoas.delete(buscarEntidade(id)); }
    public PessoaDto atualizarEndereco(Long id, br.com.giba.cadmembros.dto.EnderecoDto dto) {
        Pessoa pessoa = buscarEntidade(id);
        pessoa.setEndereco(EnderecoMapper.toEntity(dto));
        return PessoaDto.from(pessoas.save(pessoa));
    }
    public PessoaDto removerEndereco(Long id) {
        Pessoa pessoa = buscarEntidade(id);
        pessoa.setEndereco(null);
        return PessoaDto.from(pessoas.save(pessoa));
    }
    public Pessoa buscarEntidade(Long id) {
        return pessoas.findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrada: " + id));
    }
    public Pessoa buscarPorCpf(String cpf) {
        return pessoas.findByCpf(EmpresaService.digits(cpf)).orElseThrow(() -> new NotFoundException("Pessoa não encontrada para o CPF informado."));
    }
    private void preencher(Pessoa pessoa, PessoaDto dto, String cpf) {
        pessoa.setCpf(cpf); pessoa.setNome(dto.nome().trim()); pessoa.setDataNascimento(dto.dataNascimento());
        pessoa.setSituacao(dto.situacao()); pessoa.setEndereco(EnderecoMapper.toEntity(dto.endereco()));
    }
}
