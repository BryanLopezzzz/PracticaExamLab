package cr.ac.una.progra4examen1.component;

import cr.ac.una.progra4examen1.model.*;
import cr.ac.una.progra4examen1.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository    usuarioRepo;
    private final PasswordEncoder      encoder;
    private final TipoDocumentoRepository tipoRepo;
    private final DocumentoRepository  docRepo;
    private final MisDocumentoRepository misDocRepo;

    @Override
    public void run(String... args) {
        cargarUsuarios();
        cargarCatalogo();
    }

    private void cargarUsuarios() {
        if (usuarioRepo.count() > 0) return;

        List<Usuario> usuarios = List.of(
                crearUsuario("JPerez",  "111", "Juan",   "Pérez",    "101110111"),
                crearUsuario("MMata",   "222", "María",  "Mata",     "202220222"),
                crearUsuario("Alvaro",  "123", "Álvaro", "Alvarado", "303330333")
        );

        usuarioRepo.saveAll(usuarios);
        log.info(" {} usuarios cargados", usuarios.size());
    }

    private Usuario crearUsuario(String login, String clave,
                                 String nombre, String apellido, String cedula) {
        Usuario u = new Usuario();
        u.setLogin(login);
        u.setPassword(encoder.encode(clave));
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setCedula(cedula);
        return u;
    }

    private void cargarCatalogo() {
        if (tipoRepo.count() > 0) return;

        TipoDocumento pj  = new TipoDocumento("001", "Personas Juridicas");
        TipoDocumento bi  = new TipoDocumento("002", "Bienes Inmuebles");
        TipoDocumento bm  = new TipoDocumento("003", "Bienes Muebles");
        TipoDocumento cat = new TipoDocumento("004", "Catastro");
        TipoDocumento pl  = new TipoDocumento("005", "Placas");
        TipoDocumento pi  = new TipoDocumento("006", "Propiedad Intelectual");
        TipoDocumento ar  = new TipoDocumento("007", "Alerta Registral");

        tipoRepo.saveAll(List.of(pj, bi, bm, cat, pl, pi, ar));

        docRepo.saveAll(List.of(
                new Documento("001", "Afectacion",                              2785, 315, pj),
                new Documento("002", "Cedula Juridica",                         2785, 315, pj),
                new Documento("003", "Historica de Movimientos",                2785, 315, pj),
                new Documento("004", "Historica de Propiedades",                2515, 115, bi),
                new Documento("005", "Literal de Inmuebles",                    2785, 315, bi),
                new Documento("006", "Indice de Personas",                      2515, 115, bi),
                new Documento("007", "Solicitud de Placas de Motos y remolques",10900,  0, pl),
                new Documento("008", "Solicitud de Placas de Vehiculo",        17600,  0, pl)
        ));

        log.info(" Catálogo de documentos cargado");
    }
}
