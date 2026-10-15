package br.com.giba.cadmembros.repository;

import br.com.giba.cadmembros.model.StatusVinculo;
import br.com.giba.cadmembros.model.VinculoEmprego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

public interface VinculoRepository extends JpaRepository<VinculoEmprego, Long> {
    long countByEmpresa_CnpjAndStatus(String cnpj, StatusVinculo status);
    List<VinculoEmprego> findByPessoa_CpfAndStatus(String cpf, StatusVinculo status);
    @Query("select v.salario from VinculoEmprego v where v.pessoa.cpf = :cpf and v.status = :status")
    List<BigDecimal> salariosAtivos(@Param("cpf") String cpf, @Param("status") StatusVinculo status);
    boolean existsByPessoa_IdAndEmpresa_IdAndStatus(Long pessoaId, Long empresaId, StatusVinculo status);
}
