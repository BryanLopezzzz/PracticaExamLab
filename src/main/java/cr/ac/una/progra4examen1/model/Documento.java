package cr.ac.una.progra4examen1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "documentos")
public class Documento {

    @Id
    private String id;
    private String descripcion;
    private int monto;
    private int timbres;

    @ManyToOne
    @JoinColumn(name = "tipo_codigo")
    private TipoDocumento tipoDocumento;

    public Documento() {
    }

    public Documento(String id, String descripcion, int monto, int timbres, TipoDocumento tipoDocumento) {
        this.id = id;
        this.descripcion = descripcion;
        this.monto = monto;
        this.timbres = timbres;
        this.tipoDocumento = tipoDocumento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getTimbres() {
        return timbres;
    }

    public void setTimbres(int timbres) {
        this.timbres = timbres;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
