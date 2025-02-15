package com.panda.msquiatransporte.infraestructure.adapters;

import com.panda.msguiatransporte.aggregates.dto.GuiaTransptDTO;
import com.panda.msguiatransporte.aggregates.exception.PandaAppExceptionNotFound;
import com.panda.msguiatransporte.aggregates.request.RequestGuiaTranspt;
import com.panda.msguiatransporte.aggregates.response.ResponseListPaginableGuiaTranspt;
import com.panda.msguiatransporte.ports.out.GuiaTransportistaOut;
import com.panda.msquiatransporte.infraestructure.entity.GuiaTransptEntity;
import com.panda.msquiatransporte.infraestructure.entity.compoundKeys.GuiaTransptId;
import com.panda.msquiatransporte.infraestructure.mapper.GuiaTransptMapper;
import com.panda.msquiatransporte.infraestructure.repository.DestinatarioRepository;
import com.panda.msquiatransporte.infraestructure.repository.GuiaTransptRepository;
import com.panda.msquiatransporte.infraestructure.repository.PagadorFleteRepository;
import com.panda.msquiatransporte.infraestructure.repository.RemitenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuiaTransptAdapter implements GuiaTransportistaOut {
    private final GuiaTransptRepository guiaTransptRepository;
    private final DestinatarioRepository destinatarioRepository;
    private final PagadorFleteRepository pagadorFleteRepository;
    private final RemitenteRepository remitenteRepository;
    private final GuiaTransptMapper guiaTransptMapper;

    @Override
    public GuiaTransptDTO crearGuiaTransportistaOut(RequestGuiaTranspt requestGuiaTranspt) {
        validarExistenciaEntidades(requestGuiaTranspt);

        GuiaTransptEntity guiaTransptEntity = construirGuiaTransptEntity(requestGuiaTranspt);
        guiaTransptRepository.save(guiaTransptEntity);

        return guiaTransptMapper.mapGuiaTransptToDTO(construirGuiaTransptEntity(requestGuiaTranspt));
    }

    @Override
    public Optional<GuiaTransptDTO> obtenerGuiaTransportistaOut(Long guiaTranspNumero, String guiaTranspSerie) {
        GuiaTransptId guiaTransptId= new GuiaTransptId(guiaTranspNumero, guiaTranspSerie);
        return Optional.ofNullable(guiaTransptMapper.mapGuiaTransptToDTO(guiaTransptRepository.findById(guiaTransptId).get()));
    }

    @Override
    public List<GuiaTransptDTO> obtenerGuiaTransportistasOut() {
        List<GuiaTransptEntity> guiaTransptEntities = guiaTransptRepository.findAll();
        /*List<GuiaTransptDTO> guiaTransptDTOs = new ArrayList<>();
        for(GuiaTransptEntity guiaTransptEntity : guiaTransptEntities){
            GuiaTransptDTO guiaTransptDTO = guiaTransptMapper.mapGuiaTransptToDTO(guiaTransptEntity);
            guiaTransptDTOs.add(guiaTransptDTO);
        }*/
        //programación funcional
        return guiaTransptEntities.stream().map(guiaTransptMapper::mapGuiaTransptToDTO).toList();
    }

    @Override
    public GuiaTransptDTO referenciarFacturaAGuiaTransptOut(String guiaTransptSerieNumero, String facturaSerieNumero) {
        Optional<GuiaTransptEntity> guiaTransptSaved = guiaTransptRepository.findByGuiaTranspSerienumero(guiaTransptSerieNumero);
        guiaTransptSaved.get().setFacturaSerienumero(facturaSerieNumero);
        GuiaTransptEntity guiaTransptUpdated = guiaTransptRepository.save(guiaTransptSaved.get());
        return guiaTransptMapper.mapGuiaTransptToDTO(guiaTransptUpdated);
    }

    @Override
    public List<GuiaTransptDTO> ListarGuiasPorFacturaSerieNumeroOut(String facturaSerieNumero) {
        List<GuiaTransptEntity> guias = guiaTransptRepository.findByFacturaSerienumero(facturaSerieNumero);
        return guias.stream().map(guiaTransptMapper::mapGuiaTransptToDTO).toList();
    }

    @Override
    public ResponseListPaginableGuiaTranspt listarGuiasPorFacturaSerieNumeroOut(int numeroDePagina, int medidaDePagina, String ordenarPor, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(ordenarPor).ascending() : Sort.by(ordenarPor).descending();
        Pageable pageable = PageRequest.of(numeroDePagina, medidaDePagina, sort);
        Page<GuiaTransptEntity> guiaTransptEntitiesPage = guiaTransptRepository.findAll(pageable);
        List<GuiaTransptEntity> guiaTransptEntities = guiaTransptEntitiesPage.getContent();
        List<GuiaTransptDTO> guiaTransptDTOPage = guiaTransptEntities.stream().map(guiaTransptMapper::mapGuiaTransptToDTO).toList();
        return ResponseListPaginableGuiaTranspt.builder()
                .list(guiaTransptDTOPage)
                .numeroPagina(guiaTransptEntitiesPage.getNumber())
                .medidaPagina(guiaTransptEntitiesPage.getSize())
                .totalElementos(guiaTransptEntitiesPage.getTotalElements())
                .ultima(guiaTransptEntitiesPage.isLast())
                .build();
    }

    private void validarExistenciaEntidades(RequestGuiaTranspt requestGuiaTranspt) {
        if (!destinatarioRepository.existsById(requestGuiaTranspt.getDestRuc())) {
            throw new PandaAppExceptionNotFound("El destinatario con el ruc: " + requestGuiaTranspt.getDestRuc() + " no existe en la base de datos");
        }
        if (!pagadorFleteRepository.existsById(requestGuiaTranspt.getPagFleteRuc())) {
            throw new PandaAppExceptionNotFound("El pagador flete con el ruc:"+ requestGuiaTranspt.getPagFleteRuc() +" no existe en la base de datos" );
        }
        if (!remitenteRepository.existsById(requestGuiaTranspt.getRemitenteRuc())) {
            throw new PandaAppExceptionNotFound("El remitente con el ruc:"+ requestGuiaTranspt.getPagFleteRuc() +" no existe en la base de datos" );
        }
        // TODO: Validar el documento de identidad del conductor
    }
    private GuiaTransptEntity construirGuiaTransptEntity(RequestGuiaTranspt requestGuiaTranspt) {
        return GuiaTransptEntity.builder()
                .guiaTranspNumero(requestGuiaTranspt.getGuiaTransptNumero())
                .guiaTranspSerie("EG03")
                .guiaTranspSerienumero("EG03"+requestGuiaTranspt.getGuiaTransptNumero())
                .fechaEmision(Timestamp.valueOf(LocalDateTime.now()))
                .fechaInicioTraslado(requestGuiaTranspt.getFechaInicioTraslado())
                .registroMTC(requestGuiaTranspt.getRegistroMTC())
                .partida(requestGuiaTranspt.getPartida())
                .llegada(requestGuiaTranspt.getLlegada())
                .unidadMedidaPesoBruto(requestGuiaTranspt.getUnidadMedidaPesoBruto())
                .pesoBrutoTotal(requestGuiaTranspt.getPesoBrutoTotal())
                .transbordoProgramado(requestGuiaTranspt.getTransbordoProgramado())
                .retornoVehiculoVacio(requestGuiaTranspt.getRetornoVehiculoVacio())
                .transporteSubcontratado(requestGuiaTranspt.getTransporteSubcontratado())
                .usuarioEmisor("probando")
                .destRuc(requestGuiaTranspt.getDestRuc())
                .remitenteRuc(requestGuiaTranspt.getRemitenteRuc())
                .pagFleteRuc(requestGuiaTranspt.getPagFleteRuc())
                .docIdentidad(requestGuiaTranspt.getCondDocIdentidad())
                .build();
    }
}
