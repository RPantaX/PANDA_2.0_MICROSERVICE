package com.panda.facturas.infraestructure.mapper;

import com.panda.facturas.domain.aggregates.dto.DetraccionDTO;
import com.panda.facturas.infraestructure.entity.DetraccionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DetraccionMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public DetraccionDTO mapDetraccionToDTO(DetraccionEntity detraccionEntity){
        return modelMapper.map(detraccionEntity, DetraccionDTO.class);
    }
    public DetraccionEntity mapDetraccionDTOToEntity(DetraccionDTO detraccionDTO){
        return modelMapper.map(detraccionDTO, DetraccionEntity.class);
    }
}
