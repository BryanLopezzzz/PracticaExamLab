package cr.ac.una.progra4examen1.controller;

import cr.ac.una.progra4examen1.model.Documento;
import cr.ac.una.progra4examen1.model.MisDocumento;
import cr.ac.una.progra4examen1.model.TipoDocumento;
import cr.ac.una.progra4examen1.model.Usuario;
import cr.ac.una.progra4examen1.service.DocumentoService;
import cr.ac.una.progra4examen1.service.MisDocumentoService;
import cr.ac.una.progra4examen1.service.TipoDocumentoService;
import cr.ac.una.progra4examen1.service.UsuarioService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/documentos")
public class DocumentoController {

    private final MisDocumentoService misDocSer;
    private final DocumentoService docSer;
    private final UsuarioService usuSer;
    private final TipoDocumentoService tipoDocSer;

    public DocumentoController(MisDocumentoService misDocumentoService,
                               DocumentoService documentoService,
                               UsuarioService usuarioService,
                               TipoDocumentoService tipoDocumentoService) {
        this.misDocSer = misDocumentoService;
        this.docSer = documentoService;
        this.usuSer = usuarioService;
        this.tipoDocSer = tipoDocumentoService;
    }

    @GetMapping("/show")
    public String verDocumentos(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam(name = "tipo", required = false) String tipoCodigo,
                                Model model) {
        String login = userDetails.getUsername();
        Usuario usuario = usuSer.buscarPorLogin(login);

        List<MisDocumento> documentosUsuario = misDocSer.obtenerPorUsuarioId(usuario.getId());

        double total = documentosUsuario.stream()
                .mapToDouble(md -> (md.getDocumento().getMonto() + md.getDocumento().getTimbres()) * md.getCantidad())
                .sum();

        List<TipoDocumento> tipos = tipoDocSer.listarTodos();

        List<Documento> documentosFiltrados = new ArrayList<>();
        if (tipoCodigo != null && !tipoCodigo.isEmpty()) {
            documentosFiltrados = docSer.buscarPorTipo(tipoCodigo);
        }

        model.addAttribute("tipos", tipos);
        model.addAttribute("selectedTipo", tipoCodigo);
        model.addAttribute("documentosFiltrados", documentosFiltrados);
        model.addAttribute("misDocumentos", documentosUsuario);
        model.addAttribute("misDocumento", new MisDocumento());
        model.addAttribute("total", total);
        model.addAttribute("username", login);

        return "documentos";
    }

    @PostMapping("/agregar")
    public String agregarDocumento(@RequestParam("docId") String docId,
                                   @RequestParam("tipoActual") String tipoCodigo,
                                   @AuthenticationPrincipal UserDetails userDetails) {

        Usuario usuario = usuSer.buscarPorLogin(userDetails.getUsername());
        Documento documento = docSer.buscarPorId(docId);

        Optional<MisDocumento> existente = misDocSer.buscarPorUsuarioYDocumento(usuario, documento);

        if (existente.isPresent()) {
            MisDocumento md = existente.get();
            md.setCantidad(md.getCantidad() + 1);
            misDocSer.agregarDocumento(md);
        } else {
            MisDocumento nuevo = new MisDocumento();
            nuevo.setUsuario(usuario);
            nuevo.setDocumento(documento);
            nuevo.setCantidad(1);
            misDocSer.agregarDocumento(nuevo);
        }

        return "redirect:/documentos/show?tipo=" + tipoCodigo;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDocumento(@PathVariable Long id) {
        misDocSer.eliminarPorId(id);
        return "redirect:/documentos/show";
    }

    @GetMapping
    public String redirigir() {
        return "redirect:/documentos/show";
    }
}
