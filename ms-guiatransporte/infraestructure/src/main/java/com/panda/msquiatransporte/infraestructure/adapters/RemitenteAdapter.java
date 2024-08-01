package com.panda.msquiatransporte.infraestructure.adapters;

import com.panda.msguiatransporte.aggregates.dto.RemitenteDTO;
import com.panda.msguiatransporte.aggregates.exception.PandaExceptionBadRequest;
import com.panda.msguiatransporte.aggregates.request.RequestRemitente;
import com.panda.msguiatransporte.aggregates.response.ResponseSunat;
import com.panda.msguiatransporte.ports.out.RemitenteServiceOut;
import com.panda.msquiatransporte.infraestructure.entity.RemitenteEntity;
import com.panda.msquiatransporte.infraestructure.mapper.RemitenteMapper;
import com.panda.msquiatransporte.infraestructure.repository.RemitenteRepository;
import com.panda.msquiatransporte.infraestructure.rest.client.ClienteSunat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RemitenteAdapter implements RemitenteServiceOut {
    private final RemitenteRepository remitenteRepository;
    private final ClienteSunat sunat;
    private final RemitenteMapper remitenteMapper;
    @Value("${token.api}")
    String tokenApi;
    @Override
    public RemitenteDTO crearRemitenteOut(RequestRemitente requestRemitente) {
        if(remitenteRepository.existsById(requestRemitente.getRemitenteRuc()))
            throw new PandaExceptionBadRequest("El remitente con el ruc:"+ requestRemitente.getRemitenteRuc() +"ya existe en la base de datos");
        ResponseSunat responseSunat = getExecutionSunat(requestRemitente.getRemitenteRuc());
        RemitenteEntity remitenteEntity = crearRemitenteEntity(requestRemitente, responseSunat);
        return remitenteMapper.mapRemitenteToDTO(remitenteRepository.save(remitenteEntity));
    }

    @Override
    public Optional<RemitenteDTO> buscarRemitentePorRucOut(String ruc) {
        if(!remitenteRepository.existsById(ruc)) throw new PandaExceptionBadRequest("El remitente con el ruc: " +ruc +" no existe");
        return remitenteRepository.findById(ruc).map(remitenteMapper::mapRemitenteToDTO);
    }

    @Override
    public List<RemitenteDTO> buscarRemitentesOut() {
        List<RemitenteEntity> remitenteEntityList = remitenteRepository.findAll();
        return remitenteEntityList.stream().map(remitenteMapper::mapRemitenteToDTO).toList();
    }

    @Override
    public RemitenteDTO actualizarRemitenteOut(String ruc, RequestRemitente requestRemitente) {
        if(!remitenteRepository.existsById(ruc)) throw new PandaExceptionBadRequest("El remitente con el ruc: " +ruc +" no existe");
        Optional<RemitenteEntity> remitenteEntityOptional = remitenteRepository.findById(ruc);
        ResponseSunat responseSunat = getExecutionSunat(requestRemitente.getRemitenteRuc());
        RemitenteEntity remitenteEntity=remitenteRepository.save(getEntityUpdate(responseSunat,remitenteEntityOptional.get()));
        return remitenteMapper.mapRemitenteToDTO(remitenteEntity);
    }

    @Override
    public RemitenteDTO eliminarRemitenteOut(String ruc) {
        if(!remitenteRepository.existsById(ruc)) throw new PandaExceptionBadRequest("El remitente con el ruc: " +ruc +" no existe");
        Optional<RemitenteEntity> remitenteEntityOptional = remitenteRepository.findById(ruc);
        remitenteEntityOptional.get().setEstado(false);
        remitenteEntityOptional.get().setUsuarioModificador("PRUEBA");
        remitenteEntityOptional.get().setEliminadoEn(getTimestamp());
        RemitenteEntity remitenteEntity=remitenteRepository.save(remitenteEntityOptional.get());
        return remitenteMapper.mapRemitenteToDTO(remitenteEntity);
    }

    private RemitenteEntity getEntityUpdate(ResponseSunat sunat, RemitenteEntity remitenteEntity){
        remitenteEntity.setRemitenteRazonSocial(sunat.getRazonSocial());
        remitenteEntity.setRemitenteDireccion(sunat.getDireccion());
        remitenteEntity.setUsuarioModificador("PRUEBA");
        remitenteEntity.setModificadoEn(getTimestamp());
        return remitenteEntity;
    }
    private RemitenteEntity crearRemitenteEntity(RequestRemitente requestRemitente, ResponseSunat responseSunat) {
        return RemitenteEntity.builder()
                .remitenteRuc(requestRemitente.getRemitenteRuc())
                .remitenteDireccion(responseSunat.getDireccion())
                .remitenteRazonSocial(responseSunat.getRazonSocial())
                .estado(true)
                .usuarioModificador("PRUEBA")
                .creadoEn(getTimestamp())
                .build();
    }
    public ResponseSunat getExecutionSunat(String numero){
        String authorization = "Bearer " + tokenApi;
        return sunat.getInfoSunat(numero,authorization);
    }
    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }
}
