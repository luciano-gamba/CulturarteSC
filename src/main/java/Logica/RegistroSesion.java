package Logica;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="registroSesion")

public class RegistroSesion implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String IP;
    private String URL;
    private String Navegador;
    private String SO;
    private LocalDateTime fechaRegistro; //Fecha Hora guardar LocalDateTime
    
    //A los efectos de limitar el tamaño del registro y los datos persistidos, se guardarán hasta 10.000 accesos de los últimos 30 días.
    public RegistroSesion(){
        
    }

    public RegistroSesion(String IP, String URL, String Navegador, String SO) {
        this.IP = IP;
        this.URL = URL;
        this.Navegador = Navegador;
        this.SO = SO;
        this.fechaRegistro = ZonedDateTime.now(ZoneId.of("America/Montevideo")).toLocalDateTime();
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
