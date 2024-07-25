package com.panda.msquiatransporte.infraestructure.mapper;

import com.panda.msguiatransporte.aggregates.dto.PagadorFleteDTO;
import com.panda.msquiatransporte.infraestructure.entity.PagadorFleteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PagadorFleteMapper {
    private static final ModelMapper modelMapper = new ModelMapper();
    public PagadorFleteDTO mapPagadorFleteToDTO(PagadorFleteEntity pagadorFleteEntity){
        return modelMapper.map(pagadorFleteEntity, PagadorFleteDTO.class);
    }
    public PagadorFleteEntity mapPagadorFleteDTOtoEntity(PagadorFleteDTO pagadorFleteDTO){
        return modelMapper.map(pagadorFleteDTO, PagadorFleteEntity.class);
    }
}
