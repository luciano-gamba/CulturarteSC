package DataTypes;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;

public class DataAporte implements Serializable {

    Double aporte;
    LocalDateTime fechaHora;
    int cantidad;
    EnumRetorno retorno;
    String imagen;
    String imagenLocal;
    String miColaborador;
    String miPropuesta;
    Double necesaria;
    String fechaStr;
    
    
    public DataAporte(){
        
    }
    
    public DataAporte(double aporte, LocalDateTime fechaHora, int cantidad, EnumRetorno retorno, String miColaborador, String miPropuesta, String imagen, double necesaria) {
        this.aporte = aporte;
        this.fechaHora = fechaHora;
        this.fechaStr = fechaHora.toString();
        this.cantidad = cantidad;
        this.retorno = retorno;
        this.miColaborador = miColaborador;
        this.miPropuesta = miPropuesta;
        this.imagen = imagen;
        this.necesaria = necesaria;
        this.imagenLocal = System.getProperty("user.dir") + File.separator + imagen;
    }
    public DataAporte(double aporte, LocalDateTime fechaHora, String miPropuesta) {
        this.aporte = aporte;
        this.fechaHora = fechaHora;
        this.fechaStr = fechaHora.toString();
        this.miPropuesta = miPropuesta;
        this.cantidad = 0;
        this.retorno = null;
        this.miColaborador = "";
        this.imagen = "";
        this.necesaria = null;
    }
    
    public Double getAporte() {
        return aporte;
    }

    public void setAporte(Double aporte) {
        this.aporte = aporte;
    }
 
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getFechaStr() {
        return fechaStr;
    }

    public void setFechaStr(String fechaStr) {
        this.fechaStr = fechaStr;
    }
   
    public int getCantidad() {
        return cantidad;
    }

    public EnumRetorno getRetorno() {
        return retorno;
    }

    public String getMiColaborador() {
        return miColaborador;
    }

    public String getMiPropuesta() {
        return miPropuesta;
    }

    public String getImagen() {
        return imagen;
    }

    public Double getNecesaria() {
        return necesaria;
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

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setMiColaborador(String miColaborador) {
        this.miColaborador = miColaborador;
    }

    public void setMiPropuesta(String miPropuesta) {
        this.miPropuesta = miPropuesta;
    }

    public void setNecesaria(Double necesaria) {
        this.necesaria = necesaria;
    }

    public String getImagenLocal() {
        return imagenLocal;
    }

    public void setImagenLocal(String imagenLocal) {
        this.imagenLocal = imagenLocal;
    }
    
    
}
