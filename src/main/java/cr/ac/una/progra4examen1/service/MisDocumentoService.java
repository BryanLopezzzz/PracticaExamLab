package cr.ac.una.progra4examen1.service;

import cr.ac.una.progra4examen1.model.Documento;
import cr.ac.una.progra4examen1.model.MisDocumento;
import cr.ac.una.progra4examen1.model.Usuario;
import cr.ac.una.progra4examen1.repository.MisDocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MisDocumentoService {

    private final MisDocumentoRepository misDocumentoRepository;

    public MisDocumento agregarDocumento(MisDocumento misDocumento) {
        return misDocumentoRepository.save(misDocumento);
    }

    public List<MisDocumento> obtenerPorUsuarioId(Long usuarioId) {
        return misDocumentoRepository.findByUsuarioId(usuarioId);
    }

    public Optional<MisDocumento> buscarPorUsuarioYDocumento(Usuario usuario, Documento documento) {
        return misDocumentoRepository.findByUsuarioAndDocumento(usuario, documento);
    }

    public void eliminarPorId(Long id) {
        misDocumentoRepository.deleteById(id);
    }
}
