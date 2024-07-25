package com.panda.msquiatransporte.infraestructure.repository;

import com.panda.msquiatransporte.infraestructure.entity.GuiaTransptEntity;
import com.panda.msquiatransporte.infraestructure.entity.compoundKeys.GuiaTransptId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuiaTransptRepository extends JpaRepository<GuiaTransptEntity, GuiaTransptId> {
    public GuiaTransptEntity findByGuiaTranspSerienumero(GuiaTransptId id);
}
