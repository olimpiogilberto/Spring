package br.com.giba.cadmembros.repository;

import br.com.giba.cadmembros.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByCnpj(String cnpj);
    boolean existsByCnpj(String cnpj);
}
