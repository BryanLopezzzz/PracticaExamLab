package cr.ac.una.progra4examen1.controller;

import cr.ac.una.progra4examen1.model.*;
import cr.ac.una.progra4examen1.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/documentos")
@RequiredArgsConstructor
public class DocumentoController {

    private final MisDocumentoService  misDocService;
    private final DocumentoService     docService;
    private final UsuarioService       usuService;
    private final TipoDocumentoService tipoService;

    @GetMapping({"/show", ""})
    public String mostrar(@AuthenticationPrincipal UserDetails userDetails,
                          @RequestParam(required = false) String tipo,
                          Model model) {

        Usuario usuario = usuService.buscarPorLogin(userDetails.getUsername());

        List<MisDocumento> misDocumentos = misDocService.obtenerPorUsuarioId(usuario.getId());

        double total = misDocumentos.stream()
                .mapToDouble(md -> (double)(md.getDocumento().getMonto()
                        + md.getDocumento().getTimbres()) * md.getCantidad())
                .sum();

        model.addAttribute("tipos",              tipoService.listarTodos());
        model.addAttribute("selectedTipo",        tipo);
        model.addAttribute("documentosFiltrados", tipo != null && !tipo.isBlank()
                ? docService.buscarPorTipo(tipo)
                : List.of());
        model.addAttribute("misDocumentos",       misDocumentos);
        model.addAttribute("misDocumento",        new MisDocumento());
        model.addAttribute("total",               total);
        model.addAttribute("username",            userDetails.getUsername());

        return "documentos";
    }

    @PostMapping("/agregar")
    public String agregar(@RequestParam String docId,
                          @RequestParam String tipoActual,
                          @AuthenticationPrincipal UserDetails userDetails) {

        Usuario   usuario   = usuService.buscarPorLogin(userDetails.getUsername());
        Documento documento = docService.buscarPorId(docId);

        Optional<MisDocumento> existente =
                misDocService.buscarPorUsuarioYDocumento(usuario, documento);

        if (existente.isPresent()) {
            MisDocumento md = existente.get();
            md.setCantidad(md.getCantidad() + 1);
            misDocService.agregarDocumento(md);
        } else {
            MisDocumento nuevo = new MisDocumento();
            nuevo.setUsuario(usuario);
            nuevo.setDocumento(documento);
            nuevo.setCantidad(1);
            misDocService.agregarDocumento(nuevo);
        }

        return "redirect:/documentos/show?tipo=" + tipoActual;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        misDocService.eliminarPorId(id);
        return "redirect:/documentos/show";
    }
}
