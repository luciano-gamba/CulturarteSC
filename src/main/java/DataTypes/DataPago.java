package DataTypes;

import DataTypes.EnumPago;
import DataTypes.EnumTarjeta;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Luiano
 */
public class DataPago implements Serializable {

    private String titular;
    private EnumPago enumPago;
    private LocalDate fechaPago;
    private String fechaPagoStr;
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

    public DataPago() {
    }

    public DataPago(String titular, EnumPago enumPago, LocalDate fechaPago) {
        this.titular = titular;
        this.enumPago = enumPago;
        this.fechaPago = fechaPago;
    }

    public void setDataPagoT(String numeroT, String fechaT, String CVC, EnumTarjeta enumTarjeta) {
        this.numeroT = numeroT;
        this.fechaT = fechaT;
        this.CVC = CVC;
        this.enumTarjeta = enumTarjeta;
    }

    public void setDataPagoB(String nombreB, String numeroB) {
        this.nombreB = nombreB;
        this.numeroB = numeroB;
    }

    public void setDataPagoP(String numeroP) {
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

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFechaPagoStr() {
        this.fechaPagoStr = this.fechaPago.toString();
        return fechaPagoStr;
    }
    
        public void setFechaPagoStr(String fechaPagoStr) {
        this.fechaPagoStr = fechaPagoStr;
    }

}
