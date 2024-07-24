package com.panda.facturas.infraestructure.mapper;

import com.panda.facturas.domain.aggregates.dto.FacturaDetalleDTO;
import com.panda.facturas.infraestructure.entity.FacturaDetalleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FacturaDetalleMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public FacturaDetalleDTO mapFacturaDetalleToDto(FacturaDetalleEntity facturaDetalleEntity){
        return modelMapper.map(facturaDetalleEntity, FacturaDetalleDTO.class);
    }
    public FacturaDetalleEntity mapFacturaDetalleDtoToEntity(FacturaDetalleDTO facturaDetalleDTO){
        return modelMapper.map(facturaDetalleDTO, FacturaDetalleEntity.class);
    }
}
