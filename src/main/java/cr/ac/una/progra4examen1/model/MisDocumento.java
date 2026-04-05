package cr.ac.una.progra4examen1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mis_documentos")
public class MisDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Documento documento;

    private int cantidad;

    public MisDocumento() {
    }

    public MisDocumento(Long id, Usuario usuario, Documento documento, int cantidad) {
        this.id = id;
        this.usuario = usuario;
        this.documento = documento;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
