package com.panda.facturas.infraestructure.mapper;

import com.panda.facturas.domain.aggregates.dto.FacturaDTO;
import com.panda.facturas.infraestructure.entity.FacturaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FacturaMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public FacturaDTO mapFacturaToDto(FacturaEntity facturaEntity) {
        return modelMapper.map(facturaEntity, FacturaDTO.class);
    }
    public FacturaEntity mapDtoToFacturaEntity(FacturaDTO facturaDTO) {
        return modelMapper.map(facturaDTO, FacturaEntity.class);
    }

}
