package br.sc.senac.budgetech.backend.repository;

import br.sc.senac.budgetech.backend.model.Woodwork;
import br.sc.senac.budgetech.backend.projection.WoodworkProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WoodworkRepository extends JpaRepository<Woodwork, Long> {

    Optional<WoodworkProjection> findWoodworkByCompanyName(String companyName);

    Optional<WoodworkProjection> findWoodworkByCnpj(String cnpj);
}