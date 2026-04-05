package cr.ac.una.progra4examen1.repository;
import cr.ac.una.progra4examen1.model.Documento;
import cr.ac.una.progra4examen1.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface DocumentoRepository extends JpaRepository<Documento, String> {
    List<Documento> findByTipoDocumento(TipoDocumento tipoDocumento);
}