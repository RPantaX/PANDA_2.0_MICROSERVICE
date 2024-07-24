package com.panda.facturas.infraestructure.mapper;

import com.panda.facturas.domain.aggregates.dto.EmisorDTO;
import com.panda.facturas.infraestructure.entity.EmisorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmisorMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public EmisorDTO mapEmisorToDTO (EmisorEntity emisorEntity) {
        return modelMapper.map(emisorEntity, EmisorDTO.class);
    }
    public EmisorEntity mapDTOToEmisor (EmisorDTO emisorDTOdto) {
        return modelMapper.map(emisorDTOdto, EmisorEntity.class);
    }
}
