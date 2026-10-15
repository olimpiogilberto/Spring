package br.com.giba.cadmembros.repository;

import br.com.giba.cadmembros.model.FaturamentoAnual;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FaturamentoRepository extends JpaRepository<FaturamentoAnual, Long> {
    Optional<FaturamentoAnual> findByEmpresa_CnpjAndAno(String cnpj, Integer ano);
    boolean existsByEmpresa_IdAndAno(Long empresaId, Integer ano);
}
