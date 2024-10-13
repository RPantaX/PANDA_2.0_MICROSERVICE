package com.panda.msquiatransporte.infraestructure.adapters;

import com.panda.msguiatransporte.aggregates.dto.DestinatarioDTO;
import com.panda.msguiatransporte.aggregates.exception.PandaExceptionBadRequest;
import com.panda.msguiatransporte.aggregates.request.RequestDestinatario;
import com.panda.msguiatransporte.aggregates.response.ResponseListPaginableDestinatario;
import com.panda.msguiatransporte.aggregates.response.ResponseSunat;
import com.panda.msguiatransporte.ports.out.DestinatarioServiceOut;
import com.panda.msquiatransporte.infraestructure.entity.DestinatarioEntity;
import com.panda.msquiatransporte.infraestructure.mapper.DestinatarioMapper;
import com.panda.msquiatransporte.infraestructure.repository.DestinatarioRepository;
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
public class DestinatarioApdapter implements DestinatarioServiceOut {

    private final DestinatarioRepository destinatarioRepository;

    private final ClienteSunat sunat;
    private final DestinatarioMapper destinatarioMapper;

    @Value("${token.api}")
    String tokenApi;

    @Override
    public DestinatarioDTO crearDestinatarioOut(RequestDestinatario requestDestinatario) {
        //validar ruc en la base de datos
        if(destinatarioRepository.existsById(requestDestinatario.getDestRuc()))
            throw new PandaExceptionBadRequest("El destinatario con el ruc:"+ requestDestinatario.getDestRuc() +"ya existe en la base de datos");
        ResponseSunat responseSunat = getExecutionSunat(requestDestinatario.getDestRuc());
        DestinatarioEntity destinatarioEntity = crearDestinatarioEntity(requestDestinatario, responseSunat);
        return destinatarioMapper.mapDestinatarioToDTO(destinatarioRepository.save(destinatarioEntity));
    }

    @Override
    public Optional<DestinatarioDTO> buscarDestinatarioPorRucOut(String ruc) {
        if(!destinatarioRepository.existsById(ruc)) throw new PandaExceptionBadRequest("El destinatario con el ruc: " +ruc +" no existe");
        return destinatarioRepository.findById(ruc).map(destinatarioMapper::mapDestinatarioToDTO);
    }

    @Override
    public List<DestinatarioDTO> buscarDestinatariosOut() {
        List<DestinatarioEntity> destinatarioEntityList = destinatarioRepository.findAll();
        return destinatarioEntityList.stream().map(destinatarioMapper::mapDestinatarioToDTO).toList();
    }

    @Override
    public DestinatarioDTO actualizarDestinatarioOut(String ruc, RequestDestinatario requestDestinatario) {
        if(!destinatarioRepository.existsById(ruc)) throw new PandaExceptionBadRequest("El destinatario con el ruc: " +ruc +" no existe");
        Optional<DestinatarioEntity> destinatarioEntityOptional = destinatarioRepository.findById(ruc);
        ResponseSunat responseSunat = getExecutionSunat(requestDestinatario.getDestRuc());
        DestinatarioEntity destinatarioEntity=destinatarioRepository.save(getEntityUpdate(responseSunat,destinatarioEntityOptional.get()));
        return destinatarioMapper.mapDestinatarioToDTO(destinatarioEntity);
    }

    @Override
    public DestinatarioDTO eliminarDestinatarioOut(String ruc) {
        if(!destinatarioRepository.existsById(ruc)) throw new PandaExceptionBadRequest("El destinatario con el ruc: " +ruc +" no existe");
        Optional<DestinatarioEntity> destinatarioEntityOptional = destinatarioRepository.findById(ruc);
        destinatarioEntityOptional.get().setEstado(false);
        destinatarioEntityOptional.get().setUsuarioModificador("PRUEBA");
        destinatarioEntityOptional.get().setEliminadoEn(getTimestamp());
        DestinatarioEntity destinatarioEntity=destinatarioRepository.save(destinatarioEntityOptional.get());
        return destinatarioMapper.mapDestinatarioToDTO(destinatarioEntity);
    }

    @Override
    public ResponseListPaginableDestinatario listarDestinatariosPaginableOut(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);
        Page<DestinatarioEntity> page = destinatarioRepository.findAll(pageable);
        List <DestinatarioEntity> destinatarioEntityList = page.getContent();
        List<DestinatarioDTO> destinatarioDTOList = destinatarioEntityList.stream().map(destinatarioMapper::mapDestinatarioToDTO).toList();
        return ResponseListPaginableDestinatario.builder()
                .destinatarioDTOList(destinatarioDTOList)
                .numeroPagina(page.getNumber())
                .medidaPagina(page.getSize())
                .totalElementos(page.getTotalElements())
                .ultima(page.isLast())
                .build();
    }

    private DestinatarioEntity getEntityUpdate(ResponseSunat sunat, DestinatarioEntity destinatarioEntity){
        destinatarioEntity.setDestRazonSocial(sunat.getRazonSocial());
        destinatarioEntity.setDestDireccion(sunat.getDireccion());
        destinatarioEntity.setUsuarioModificador("PRUEBA");
        destinatarioEntity.setModificadoEn(getTimestamp());
        return destinatarioEntity;
    }
    private DestinatarioEntity crearDestinatarioEntity(RequestDestinatario requestDestinatario, ResponseSunat responseSunat) {
        return DestinatarioEntity.builder()
                .destRuc(requestDestinatario.getDestRuc())
                .destDireccion(responseSunat.getDireccion())
                .destRazonSocial(responseSunat.getRazonSocial())
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
