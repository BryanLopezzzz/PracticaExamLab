package cr.ac.una.progra4examen1.controller;

import cr.ac.una.progra4examen1.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {

    private final UsuarioRepository usuRep;
    private final PasswordEncoder passEn;

    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuRep = usuarioRepository;
        this.passEn = passwordEncoder;
    }

    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        if (auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String)) {
            var userDetails = (UserDetails) auth.getPrincipal();
            username = userDetails.getUsername();
        }
        model.addAttribute("username", username);
        return "index";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }

}
