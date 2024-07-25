package com.panda.msquiatransporte.infraestructure.mapper;

import com.panda.msguiatransporte.aggregates.dto.GuiaTransptDTO;
import com.panda.msquiatransporte.infraestructure.entity.GuiaTransptEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GuiaTransptMapper {
    private static ModelMapper modelMapper = new ModelMapper();
    public GuiaTransptDTO mapGuiaTransptToDTO(GuiaTransptEntity guiaTranspt) {
        return modelMapper.map(guiaTranspt, GuiaTransptDTO.class);
    }
    public GuiaTransptEntity mapGuiaTransptDTOToEntity(GuiaTransptDTO guiaTransptDTO) {
        return modelMapper.map(guiaTransptDTO, GuiaTransptEntity.class);
    }
}
