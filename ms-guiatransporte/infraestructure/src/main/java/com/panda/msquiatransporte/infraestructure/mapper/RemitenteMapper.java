package com.panda.msquiatransporte.infraestructure.mapper;

import com.panda.msguiatransporte.aggregates.dto.RemitenteDTO;
import com.panda.msquiatransporte.infraestructure.entity.RemitenteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RemitenteMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public RemitenteDTO mapRemitenteToDTO(RemitenteEntity remitenteEntity){
        return modelMapper.map(remitenteEntity, RemitenteDTO.class);
    }
    public RemitenteEntity mapRemitenteDtoToEntity(RemitenteDTO remitenteDTO){
        return modelMapper.map(remitenteDTO, RemitenteEntity.class);
    }
}
