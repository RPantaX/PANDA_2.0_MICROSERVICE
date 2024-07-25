package com.panda.msquiatransporte.infraestructure.repository;

import com.panda.msquiatransporte.infraestructure.entity.DestinatarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinatarioRepository extends JpaRepository<DestinatarioEntity, String> {
}
