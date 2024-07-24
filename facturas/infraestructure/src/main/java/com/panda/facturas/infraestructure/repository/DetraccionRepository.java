package com.panda.facturas.infraestructure.repository;

import com.panda.facturas.infraestructure.entity.CompoundKeys.DetraccionId;
import com.panda.facturas.infraestructure.entity.DetraccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetraccionRepository extends JpaRepository<DetraccionEntity,DetraccionId> {

}
