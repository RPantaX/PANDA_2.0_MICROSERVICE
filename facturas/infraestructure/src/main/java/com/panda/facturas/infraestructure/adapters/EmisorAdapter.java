package com.panda.facturas.infraestructure.adapters;

import com.panda.facturas.domain.aggregates.dto.EmisorDTO;
import com.panda.facturas.domain.aggregates.exceptions.FacturaAppExceptionBadRequest;
import com.panda.facturas.domain.aggregates.exceptions.FacturaAppExceptionNotFound;
import com.panda.facturas.domain.aggregates.request.RequestEmisor;
import com.panda.facturas.domain.aggregates.response.ResponseListPaginableEmisor;
import com.panda.facturas.domain.aggregates.response.ResponseSunat;
import com.panda.facturas.domain.ports.out.EmisorServiceOut;
import com.panda.facturas.infraestructure.entity.EmisorEntity;
import com.panda.facturas.infraestructure.mapper.EmisorMapper;
import com.panda.facturas.infraestructure.repository.EmisorRepository;
import com.panda.facturas.infraestructure.rest.client.ClienteSunat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmisorAdapter implements EmisorServiceOut {
    private final EmisorRepository emisorRepository;
    private final ClienteSunat clienteSunat;
    private final EmisorMapper emisorMapper;
    @Value("${token.api}")
    String tokenApi;

    @Override
    public EmisorDTO crearEmisorOut(RequestEmisor requestEmisor) {
        if(emisorRepository.existsById(requestEmisor.getEmisorRuc()))
            throw new FacturaAppExceptionBadRequest("El emisor con el ruc:"+ requestEmisor.getEmisorRuc() +"ya existe en la base de datos");
        ResponseSunat responseSunat = getExecutionSunat(requestEmisor.getEmisorRuc());
        EmisorEntity emisorEntity = crearRemitenteEntity(requestEmisor, responseSunat);
        return emisorMapper.mapEmisorToDTO(emisorRepository.save(emisorEntity));
    }
    @Override
    public Optional<EmisorDTO> buscarEmisorPorRucOut(String ruc) {
        if(!emisorRepository.existsById(ruc)) throw new FacturaAppExceptionNotFound("El emisor con el ruc: " +ruc +" no existe");
        return emisorRepository.findById(ruc).map(emisorMapper::mapEmisorToDTO);
    }

    @Override
    public List<EmisorDTO> buscarEmisoresOut() {
            List<EmisorEntity> emisorEntityList = emisorRepository.findAll();
        return emisorEntityList.stream().map(emisorMapper::mapEmisorToDTO).toList();
    }

    @Override
    public EmisorDTO actualizarEmisorOut(String ruc, RequestEmisor requestEmisor) {
        if(!emisorRepository.existsById(ruc)) throw new FacturaAppExceptionNotFound("El emisor con el ruc: " +ruc +" no existe");
        Optional<EmisorEntity> emisorEntityOptional = emisorRepository.findById(ruc);
        ResponseSunat responseSunat = getExecutionSunat(requestEmisor.getEmisorRuc());
        EmisorEntity emisorEntity=emisorRepository.save(getEntityUpdate(responseSunat,emisorEntityOptional.get()));
        return emisorMapper.mapEmisorToDTO(emisorEntity);
    }

    @Override
    public EmisorDTO eliminarEmisorOut(String ruc) {
        if(!emisorRepository.existsById(ruc)) throw new FacturaAppExceptionNotFound("El emisor con el ruc: " +ruc +" no existe");
        Optional<EmisorEntity> emisorEntityOptional = emisorRepository.findById(ruc);
        emisorEntityOptional.get().setEstado(false);
        emisorEntityOptional.get().setUsuarioModificador("PRUEBA");
        emisorEntityOptional.get().setEliminadoEn(getTimestamp());
        EmisorEntity emisorEntity=emisorRepository.save(emisorEntityOptional.get());
        return emisorMapper.mapEmisorToDTO(emisorEntity);
    }

    @Override
    public ResponseListPaginableEmisor listarPaginableEmisoresOut(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);
        Page<EmisorEntity> emisorEntityPage = emisorRepository.findAll(pageable);

        List<EmisorEntity> emisorEntityList = emisorEntityPage.getContent();
        List <EmisorDTO> emisorDTOList = emisorEntityList.stream().map(emisorMapper::mapEmisorToDTO).toList();

        return ResponseListPaginableEmisor.builder()
                .emisorDTOList(emisorDTOList)
                .numeroPagina(emisorEntityPage.getNumber())
                .medidaPagina(emisorEntityPage.getSize())
                .totalElementos(emisorEntityPage.getTotalElements())
                .ultima(emisorEntityPage.isLast())
                .build();
    }

    private EmisorEntity getEntityUpdate(ResponseSunat sunat, EmisorEntity emisor){
        emisor.setEmisorRazonSocial(sunat.getRazonSocial());
        emisor.setEmisorDireccion(sunat.getDireccion());
        emisor.setUsuarioModificador("PRUEBA");
        emisor.setModificadoEn(getTimestamp());
        return emisor;
    }
    private EmisorEntity crearRemitenteEntity(RequestEmisor requestEmisor, ResponseSunat responseSunat) {
        return EmisorEntity.builder()
                .emisorRuc(requestEmisor.getEmisorRuc())
                .emisorDireccion(responseSunat.getDireccion())
                .emisorRazonSocial(responseSunat.getRazonSocial())
                .estado(true)
                .usuarioModificador("PRUEBA")
                .creadoEn(getTimestamp())
                .build();
    }
    public ResponseSunat getExecutionSunat(String numero){
        String authorization = "Bearer " + tokenApi;
        return clienteSunat.getInfoSunat(numero,authorization);
    }
    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }
}
