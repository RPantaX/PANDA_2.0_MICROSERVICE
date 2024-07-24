package com.panda.facturas.infraestructure.repository;

import com.panda.facturas.infraestructure.entity.EmisorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmisorRepository extends JpaRepository<EmisorEntity, String> {
}
