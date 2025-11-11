package DataTypes;


import java.time.LocalDateTime;

public class DataRegistro {
    
    private int id;
    private String IP;
    private String URL;
    private String Navegador;
    private String SO;
    private LocalDateTime fechaRegistro; //Fecha Hora guardar y mostrar en el InterConsultaSesion
    
   public DataRegistro(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getNavegador() {
        return Navegador;
    }

    public void setNavegador(String Navegador) {
        this.Navegador = Navegador;
    }

    public String getSO() {
        return SO;
    }

    public void setSO(String SO) {
        this.SO = SO;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}
