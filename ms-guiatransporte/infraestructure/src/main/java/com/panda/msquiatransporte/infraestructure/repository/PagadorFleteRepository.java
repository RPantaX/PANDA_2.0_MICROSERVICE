package com.panda.msquiatransporte.infraestructure.repository;

import com.panda.msquiatransporte.infraestructure.entity.PagadorFleteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagadorFleteRepository extends JpaRepository<PagadorFleteEntity, String> {
}
