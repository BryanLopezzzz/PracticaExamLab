package cr.ac.una.progra4examen1.controller;

import cr.ac.una.progra4examen1.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {

    private final UsuarioRepository usuaRep;
    private final AuthenticationManager authenticationManager;

    //@Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository usuarioRepository, AuthenticationManager authenticationManager) {
        this.usuaRep = usuarioRepository;
        this.authenticationManager = authenticationManager;
    }

}
