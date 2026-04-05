package cr.ac.una.progra4examen1.service;

import cr.ac.una.progra4examen1.model.Documento;
import cr.ac.una.progra4examen1.repository.DocumentoRepository;
import cr.ac.una.progra4examen1.repository.TipoDocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final DocumentoRepository     documentoRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;

    public Documento buscarPorId(String id) {
        return documentoRepository.findById(id).orElse(null);
    }

    public List<Documento> buscarPorTipo(String tipoCodigo) {
        return tipoDocumentoRepository.findById(tipoCodigo)
                .map(documentoRepository::findByTipoDocumento)
                .orElse(List.of());
    }
}