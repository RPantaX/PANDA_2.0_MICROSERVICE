package com.panda.facturas.infraestructure.repository;

import com.panda.facturas.infraestructure.entity.CompoundKeys.FacturaDetalleId;
import com.panda.facturas.infraestructure.entity.FacturaDetalleEntity;
import com.panda.facturas.infraestructure.entity.FacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaDetalleRepository extends JpaRepository<FacturaDetalleEntity, FacturaDetalleId> {
    List<FacturaDetalleEntity> findAllByFacturaSerienumero(String serienumero);
}
