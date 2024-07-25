package com.panda.msquiatransporte.infraestructure.mapper;

import com.panda.msguiatransporte.aggregates.dto.DestinatarioDTO;
import com.panda.msquiatransporte.infraestructure.entity.DestinatarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DestinatarioMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public DestinatarioDTO mapDestinatarioToDTO(DestinatarioEntity destinatarioEntity){
        return modelMapper.map(destinatarioEntity, DestinatarioDTO.class);
    }
    public DestinatarioEntity mapDestinatarioDTOToDestinatarioEntity(DestinatarioDTO dto){
        return modelMapper.map(dto, DestinatarioEntity.class);
    }
}
