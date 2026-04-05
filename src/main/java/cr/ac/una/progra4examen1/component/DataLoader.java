package cr.ac.una.progra4examen1.component;

import cr.ac.una.progra4examen1.model.MisDocumento;
import cr.ac.una.progra4examen1.model.Usuario;
import cr.ac.una.progra4examen1.repository.DocumentoRepository;
import cr.ac.una.progra4examen1.repository.TipoDocumentoRepository;
import cr.ac.una.progra4examen1.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import cr.ac.una.progra4examen1.model.TipoDocumento;
import cr.ac.una.progra4examen1.model.Documento;
import cr.ac.una.progra4examen1.repository.MisDocumentoRepository;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuaRep;
    private final PasswordEncoder passEn;
    private final TipoDocumentoRepository tipDoc;
    private final DocumentoRepository docRep;
    private final MisDocumentoRepository misDocRep;

    public DataLoader(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, TipoDocumentoRepository tipoDocumentoRepository, DocumentoRepository documentoRepository, MisDocumentoRepository misDocumentoRepository) {
        this.usuaRep = usuarioRepository;
        this.passEn = passwordEncoder;
        this.tipDoc = tipoDocumentoRepository;
        this.docRep = documentoRepository;
        this.misDocRep = misDocumentoRepository;
    }

    @Override
    public void run(String... args) {
        if (usuaRep.count() == 0) {
            Usuario user1 = new Usuario();
            user1.setLogin("JohnDoe");
            user1.setPassword(passEn.encode("pwd123"));
            user1.setNombre("John");
            user1.setApellido("Doe");
            user1.setCedula("1122334455");

            Usuario user2 = new Usuario();
            user2.setLogin("JaneDoe");
            user2.setPassword(passEn.encode("user123"));
            user2.setNombre("Jane");
            user2.setApellido("Done");
            user2.setCedula("6677889900");

            Usuario user3 = new Usuario();
            user3.setLogin("Alvaro");
            user3.setPassword(passEn.encode("123"));
            user3.setNombre("Alvaro");
            user3.setApellido("Alvarado");
            user3.setCedula("9988776655");

            usuaRep.save(user1);
            usuaRep.save(user2);
            usuaRep.save(user3);

            System.out.println("Usuarios hardcoded insertados correctamente.");
        }

        if (tipDoc.count() == 0) {
            TipoDocumento t1 = new TipoDocumento("001", "Personas Juridicas");
            TipoDocumento t2 = new TipoDocumento("002", "Bienes Inmuebles");
            TipoDocumento t3 = new TipoDocumento("003", "Bienes Muebles");
            TipoDocumento t4 = new TipoDocumento("004", "Catastro");
            TipoDocumento t5 = new TipoDocumento("005", "Placas");
            TipoDocumento t6 = new TipoDocumento("006", "Propiedad Intelectual");
            TipoDocumento t7 = new TipoDocumento("007", "Alerta Registral");

            tipDoc.saveAll(List.of(t1, t2, t3, t4, t5, t6, t7));

            docRep.saveAll(List.of(
                    new Documento("001", "Afectacion", 2785, 315, t1),
                    new Documento("002", "Cedula Juridica", 2785, 315, t1),
                    new Documento("003", "Historica de Movimientos", 2785, 315, t1),
                    new Documento("004", "Historica de Propiedades", 2515, 115, t2),
                    new Documento("005", "Literal de Inmuebles", 2785, 315, t2),
                    new Documento("006", "Indice de Personas", 2515, 115, t2),
                    new Documento("007", "Solicitud de Placas de Motos y remolques", 10900, 0, t5),
                    new Documento("008", "Solicitud de Placas de Vehículo", 17600, 0, t5)
            ));
        }

        var userOpt = usuaRep.findByLogin("JohnDoe");
        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();

            List<Documento> docs = docRep.findAll();
            if (!docs.isEmpty()) {
                MisDocumento md1 = new MisDocumento(null, user, docs.get(0), 1);
                MisDocumento md2 = new MisDocumento(null, user, docs.get(1), 2);

                misDocRep.saveAll(List.of(md1, md2));

                System.out.println("Documentos asignados a JohnDoe correctamente.");
            }
        }

        var AlvaroOpt = usuaRep.findByLogin("Alvaro");
        if (AlvaroOpt.isPresent()) {
            Usuario Alvaro = AlvaroOpt.get();
            List<Documento> docs = docRep.findAll();

            if (!docs.isEmpty()) {
                MisDocumento a1 = new MisDocumento(null, Alvaro, docs.get(4), 1); // Literal de Inmuebles
                MisDocumento a2 = new MisDocumento(null, Alvaro, docs.get(7), 1); // Solicitud de Placas de Vehículo

                misDocRep.saveAll(List.of(a1, a2));

                System.out.println("Documentos asignados a Alvaro correctamente.");
            }
        }

    }

}
