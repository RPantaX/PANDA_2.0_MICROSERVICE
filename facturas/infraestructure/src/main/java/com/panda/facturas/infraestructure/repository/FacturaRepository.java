package com.panda.facturas.infraestructure.repository;

import com.panda.facturas.infraestructure.entity.FacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturaRepository extends JpaRepository<FacturaEntity, Long> {
    Optional<FacturaEntity> findByFacturaSerienumero(String serienumero);
}
