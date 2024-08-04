package com.panda.msquiatransporte.infraestructure.repository;

import com.panda.msquiatransporte.infraestructure.entity.GuiaTransptEntity;
import com.panda.msquiatransporte.infraestructure.entity.compoundKeys.GuiaTransptId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuiaTransptRepository extends JpaRepository<GuiaTransptEntity, GuiaTransptId> {
    Optional<GuiaTransptEntity> findByGuiaTranspSerienumero(String GuiaTranspSerienumero);
    List<GuiaTransptEntity> findByFacturaSerienumero(String FacturaSerienumero);
}
