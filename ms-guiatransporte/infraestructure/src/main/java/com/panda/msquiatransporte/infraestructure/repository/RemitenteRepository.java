package com.panda.msquiatransporte.infraestructure.repository;

import com.panda.msquiatransporte.infraestructure.entity.RemitenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemitenteRepository extends JpaRepository<RemitenteEntity, String> {
}
