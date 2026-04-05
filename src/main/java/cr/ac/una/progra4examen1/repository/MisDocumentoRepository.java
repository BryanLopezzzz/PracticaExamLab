package cr.ac.una.progra4examen1.repository;
import cr.ac.una.progra4examen1.model.Documento;
import cr.ac.una.progra4examen1.model.MisDocumento;
import cr.ac.una.progra4examen1.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MisDocumentoRepository extends JpaRepository<MisDocumento, Long> {
    List<MisDocumento> findByUsuarioId(Long usuarioId);
    Optional<MisDocumento> findByUsuarioAndDocumento(Usuario usuario, Documento documento);
}
