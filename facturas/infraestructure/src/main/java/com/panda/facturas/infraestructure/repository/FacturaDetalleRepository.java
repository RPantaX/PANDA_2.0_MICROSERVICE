package com.panda.facturas.infraestructure.repository;

import com.panda.facturas.infraestructure.entity.CompoundKeys.FacturaDetalleId;
import com.panda.facturas.infraestructure.entity.FacturaDetalleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaDetalleRepository extends JpaRepository<FacturaDetalleEntity, FacturaDetalleId> {
}
