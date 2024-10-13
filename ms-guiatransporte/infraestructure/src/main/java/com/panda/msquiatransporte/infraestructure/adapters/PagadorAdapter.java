package com.panda.msquiatransporte.infraestructure.adapters;

import com.panda.msguiatransporte.aggregates.dto.PagadorFleteDTO;
import com.panda.msguiatransporte.aggregates.exception.PandaExceptionBadRequest;
import com.panda.msguiatransporte.aggregates.request.RequestPagadorFlete;
import com.panda.msguiatransporte.aggregates.response.ResponseListPaginablePagador;
import com.panda.msguiatransporte.aggregates.response.ResponseSunat;
import com.panda.msguiatransporte.ports.out.PagadorFleteServiceOut;
import com.panda.msquiatransporte.infraestructure.entity.PagadorFleteEntity;
import com.panda.msquiatransporte.infraestructure.mapper.PagadorFleteMapper;
import com.panda.msquiatransporte.infraestructure.repository.PagadorFleteRepository;
import com.panda.msquiatransporte.infraestructure.rest.client.ClienteSunat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagadorAdapter implements PagadorFleteServiceOut {
    private final PagadorFleteRepository pagadorFleteRepository;
    private final ClienteSunat sunat;
    private final PagadorFleteMapper pagadorFleteMapper;
    @Value("${token.api}")
    String tokenApi;
    @Override
    public PagadorFleteDTO crearPagadorFleteOut(RequestPagadorFlete requestPagadorFlete) {
        if(pagadorFleteRepository.existsById(requestPagadorFlete.getPagFleteRuc()))
            throw new PandaExceptionBadRequest("El pagador de flete con el ruc:"+ requestPagadorFlete.getPagFleteRuc() +"ya existe en la base de datos");
        ResponseSunat responseSunat = getExecutionSunat(requestPagadorFlete.getPagFleteRuc());
        PagadorFleteEntity pagadorFleteEntity = crearPagadorFleteEnity(requestPagadorFlete, responseSunat);
        return pagadorFleteMapper.mapPagadorFleteToDTO(pagadorFleteRepository.save(pagadorFleteEntity));
    }

    @Override
    public Optional<PagadorFleteDTO> buscarPagadorPorRucOut(String ruc) {
        if(!pagadorFleteRepository.existsById(ruc)) throw new PandaExceptionBadRequest("El pagador de flete con el ruc: " +ruc +" no existe");
        return pagadorFleteRepository.findById(ruc).map(pagadorFleteMapper::mapPagadorFleteToDTO);
    }

    @Override
    public List<PagadorFleteDTO> buscarPagadoresOut() {
        List<PagadorFleteEntity> pagadorFleteEntities = pagadorFleteRepository.findAll();
        return pagadorFleteEntities.stream().map(pagadorFleteMapper::mapPagadorFleteToDTO).toList();
    }

    @Override
    public PagadorFleteDTO actualizarPagadorOut(String ruc, RequestPagadorFlete requestPagadorFlete) {
        if(!pagadorFleteRepository.existsById(ruc)) throw new PandaExceptionBadRequest("El pagador de flete con el ruc: " +ruc +" no existe");
        Optional<PagadorFleteEntity> pagadorFleteEntityOptional = pagadorFleteRepository.findById(ruc);
        ResponseSunat responseSunat = getExecutionSunat(requestPagadorFlete.getPagFleteRuc());
        PagadorFleteEntity pagadorFleteEntity = pagadorFleteRepository.save(getEntityUpdate(responseSunat, pagadorFleteEntityOptional.get(), requestPagadorFlete));
        return pagadorFleteMapper.mapPagadorFleteToDTO(pagadorFleteEntity);
    }

    @Override
    public PagadorFleteDTO eliminarPagadorOut(String ruc) {
        if(!pagadorFleteRepository.existsById(ruc)) throw new PandaExceptionBadRequest("El pagador de flete con el ruc: " +ruc +" no existe");
        Optional<PagadorFleteEntity> pagadorFleteEntityOptional = pagadorFleteRepository.findById(ruc);
        pagadorFleteEntityOptional.get().setEstado(false);
        pagadorFleteEntityOptional.get().setUsuarioModificador("PRUEBA");
        pagadorFleteEntityOptional.get().setEliminadoEn(getTimestamp());
        PagadorFleteEntity pagadorFleteEntity=pagadorFleteRepository.save(pagadorFleteEntityOptional.get());
        return pagadorFleteMapper.mapPagadorFleteToDTO(pagadorFleteEntity);
    }

    @Override
    public ResponseListPaginablePagador listaPaginablePagadoresOut(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);
        Page<PagadorFleteEntity> pagadorFleteEntityPage = pagadorFleteRepository.findAll(pageable);

        List<PagadorFleteEntity> pagadorFleteEntities = pagadorFleteEntityPage.getContent();
        List <PagadorFleteDTO> pagadorFleteDTOS = pagadorFleteEntities.stream().map(pagadorFleteMapper::mapPagadorFleteToDTO).toList();
        return ResponseListPaginablePagador.builder()
                .list(pagadorFleteDTOS)
                .numeroPagina(pagadorFleteEntityPage.getNumber())
                .medidaPagina(pagadorFleteEntityPage.getSize())
                .totalElementos(pagadorFleteEntityPage.getTotalElements())
                .ultima(pagadorFleteEntityPage.isLast())
                .build();
    }

    private PagadorFleteEntity getEntityUpdate(ResponseSunat sunat, PagadorFleteEntity pagadorFleteEntity, RequestPagadorFlete requestPagadorFlete){
        pagadorFleteEntity.setPagFleteRazonSocial(sunat.getRazonSocial());
        pagadorFleteEntity.setPagFleteDireccion(sunat.getDireccion());
        pagadorFleteEntity.setEmail(requestPagadorFlete.getEmail());
        pagadorFleteEntity.setUsuarioModificador("PRUEBA");
        pagadorFleteEntity.setModificadoEn(getTimestamp());
        return pagadorFleteEntity;
    }
    private PagadorFleteEntity crearPagadorFleteEnity(RequestPagadorFlete requestPagadorFlete, ResponseSunat responseSunat) {
        return PagadorFleteEntity.builder()
                .pagFleteRuc(requestPagadorFlete.getPagFleteRuc())
                .pagFleteDireccion(responseSunat.getDireccion())
                .email(requestPagadorFlete.getEmail())
                .pagFleteRazonSocial(responseSunat.getRazonSocial())
                .estado(true)
                .usuarioModificador("PRUEBA")
                .creadoEn(getTimestamp())
                .build();
    }
    public ResponseSunat getExecutionSunat(String numero){
        String authorization = "Bearer "+tokenApi;
        return sunat.getInfoSunat(numero,authorization);
    }
    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }
}
