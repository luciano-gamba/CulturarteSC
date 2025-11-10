package Logica;

import DataTypes.EnumPago;
import DataTypes.EnumTarjeta;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Luiano
 */
@Entity
@Table(name="Pago")
public class Pago implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String titular;
    private EnumPago enumPago;
    //TARJETA
    private String numeroT;
    private String fechaT;
    private String CVC;
    private EnumTarjeta enumTarjeta;
    //BANCO
    private String nombreB;
    private String numeroB;
    //PAYPAL
    private String numeroP;

    public Pago() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Pago(String titular, EnumPago enumPago) {
        this.titular = titular;
        this.enumPago = enumPago;
    }

    public void setPagoT(String numeroT, String fechaT, String CVC, EnumTarjeta enumTarjeta) {
        this.numeroT = numeroT;
        this.fechaT = fechaT;
        this.CVC = CVC;
        this.enumTarjeta = enumTarjeta;
    }

    public void setPagoB(String nombreB, String numeroB) {
        this.nombreB = nombreB;
        this.numeroB = numeroB;
    }

    public void setPagoP(String numeroP){
        this.numeroP = numeroP;
    }
    
    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public EnumPago getEnumPago() {
        return enumPago;
    }

    public void setEnumPago(EnumPago enumPago) {
        this.enumPago = enumPago;
    }

    public String getNumeroT() {
        return numeroT;
    }

    public void setNumeroT(String numeroT) {
        this.numeroT = numeroT;
    }

    public String getFechaT() {
        return fechaT;
    }

    public void setFechaT(String fechaT) {
        this.fechaT = fechaT;
    }

    public String getCVC() {
        return CVC;
    }

    public void setCVC(String CVC) {
        this.CVC = CVC;
    }

    public EnumTarjeta getEnumTarjeta() {
        return enumTarjeta;
    }

    public void setEnumTarjeta(EnumTarjeta enumTarjeta) {
        this.enumTarjeta = enumTarjeta;
    }

    public String getNombreB() {
        return nombreB;
    }

    public void setNombreB(String nombreB) {
        this.nombreB = nombreB;
    }

    public String getNumeroB() {
        return numeroB;
    }

    public void setNumeroB(String numeroB) {
        this.numeroB = numeroB;
    }

    public String getNumeroP() {
        return numeroP;
    }

    public void setNumeroP(String numeroP) {
        this.numeroP = numeroP;
    }

    
    
}
