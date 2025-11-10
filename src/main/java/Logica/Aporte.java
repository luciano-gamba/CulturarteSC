package Logica;

import DataTypes.DataPago;
import DataTypes.DataPropuesta;
import DataTypes.EnumPago;
import DataTypes.EnumRetorno;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Aporte")
public class Aporte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "titulo")
    private Propuesta miPropuesta;
    @ManyToOne
    @JoinColumn(name = "nickname")
    private Colaborador miColaborador;
    private double aporte;
    private LocalDateTime fechaHora;
    private int cantidad;
    private EnumRetorno retorno;
    private String comentario;
    private LocalDateTime fecComentario;
    @OneToOne
    private Pago pago;

    public Aporte() {
    }

    public Aporte(Colaborador miColaborador, double aporte, int cantidad, EnumRetorno retorno) {

        this.miColaborador = miColaborador;
        this.aporte = aporte;
        this.fechaHora = LocalDateTime.now();
        this.cantidad = cantidad;
        this.retorno = retorno;
    }

    public Aporte(Colaborador miColaborador, double aporte, int cantidad, EnumRetorno retorno, LocalDateTime fecAp) {

        this.miColaborador = miColaborador;
        this.aporte = aporte;
        this.fechaHora = fecAp;
        this.cantidad = cantidad;
        this.retorno = retorno;
    }

    public String getTituloNickMiPropuesta() {
        if (this.miPropuesta != null) {
            return this.miPropuesta.getTitulo_Nickname();
        } else {
            return null;
        }
    }

    public String getTituloMiPropuesta() {
        if (this.miPropuesta != null) {
            return this.miPropuesta.getTitulo();
        } else {
            return null;
        }
    }

    public String getNicknameMiColaborador() {
        if (this.miColaborador != null) {
            return this.miColaborador.getNickname();
        } else {
            return null;
        }
    }

    public void desvincular() {
        this.miPropuesta.desvincularAporte(this);
        //miColaborador=null;
        //miPropuesta=null;
    }

    public double getAporte() {
        return aporte;
    }

    public void setMiPropuesta(Propuesta miPropuesta) {
        this.miPropuesta = miPropuesta;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public int getCantidad() {
        return cantidad;
    }

    public EnumRetorno getRetorno() {
        return retorno;
    }

    public Colaborador getColaborador() {
        return miColaborador;
    }

    public DataPropuesta getPropuesta() {
        if (miPropuesta != null) {
            DataPropuesta dataProp = new DataPropuesta(miPropuesta.getAlcanzada(), miPropuesta.getTitulo(), miPropuesta.getEstadoActual(), miPropuesta.getLugar(), miPropuesta.getProponente().getNickname());
            dataProp.setDesc(miPropuesta.getDescripcion());
            dataProp.setImagen(miPropuesta.getImagen());
            dataProp.setFechaPubli(miPropuesta.getFechaARealizar());
            dataProp.setFechaLimit(miPropuesta.getFechaLimit()); //Con esto se arregla lo de mostrar cuantos dias le quedan a las propuestas en perfil de usuario
            dataProp.setFechaPubliStr(miPropuesta.getFechaARealizar().toString());
            dataProp.setFechaLimitStr(miPropuesta.getFechaLimit().toString());
            dataProp.setnecesaria(miPropuesta.getNecesaria()); //Sin esto no se mostraba correctamente barra de progreso en perfilUsuario
            dataProp.setCantidadColaboradores(this.getCantidadcolaboradores());
            return dataProp;
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagenMiPropuesta() {
        return this.miPropuesta.getImagen();
    }

    public double getNecesaria() {
        return this.miPropuesta.getNecesaria();
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getFecComentario() {
        return fecComentario;
    }

    public void setFecComentario(LocalDateTime fecComentario) {
        this.fecComentario = fecComentario;
    }

    public Propuesta getPropuestaP() {
        return this.miPropuesta;
    }

    public int getCantidadcolaboradores() {
        return this.miPropuesta.getAportes().size();
    }

    public Propuesta getMiPropuesta() {
        return miPropuesta;
    }

    public Colaborador getMiColaborador() {
        return miColaborador;
    }

    public void setMiColaborador(Colaborador miColaborador) {
        this.miColaborador = miColaborador;
    }

    public void setAporte(double aporte) {
        this.aporte = aporte;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setRetorno(EnumRetorno retorno) {
        this.retorno = retorno;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago dataPago) {
        this.pago = dataPago;
    }

    public DataPago getDataPago() {
        if (this.pago == null) {
            return null;
        } else {
            return this.pago.getDataPago();
        }
    }

}
