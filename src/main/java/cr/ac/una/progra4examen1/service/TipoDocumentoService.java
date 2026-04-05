package cr.ac.una.progra4examen1.service;

import cr.ac.una.progra4examen1.model.TipoDocumento;
import cr.ac.una.progra4examen1.repository.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDocumentoService {

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    public List<TipoDocumento> listarTodos() {
        return tipoDocumentoRepository.findAll();
    }

    public TipoDocumento buscarPorCodigo(String codigo) {
        return tipoDocumentoRepository.findById(codigo).orElse(null);
    }
}
