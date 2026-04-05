package cr.ac.una.progra4examen1.service;

import cr.ac.una.progra4examen1.model.Documento;
import cr.ac.una.progra4examen1.model.TipoDocumento;
import cr.ac.una.progra4examen1.repository.DocumentoRepository;
import cr.ac.una.progra4examen1.repository.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;

    public DocumentoService(DocumentoRepository documentoRepository, TipoDocumentoRepository tipoDocumentoRepository) {
        this.documentoRepository = documentoRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    public List<Documento> listarTodos() {
        return documentoRepository.findAll();
    }

    public Documento buscarPorId(String id) {
        return documentoRepository.findById(id).orElse(null);
    }

    public List<Documento> buscarPorTipo(String tipoCodigo) {
        TipoDocumento tipo = tipoDocumentoRepository.findById(tipoCodigo).orElse(null);
        if (tipo != null) {
            return documentoRepository.findByTipoDocumento(tipo);
        }
        return new ArrayList<>();
    }

}