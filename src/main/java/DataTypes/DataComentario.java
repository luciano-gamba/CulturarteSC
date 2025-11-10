/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataTypes;

import java.time.LocalDateTime;

public class DataComentario {
    
    private String comentario;
    private String fecComentario;
    private String nickColaborador;
    private String tituloPropuesta;
    
    public DataComentario(){
    }

    public DataComentario(String comentario, LocalDateTime fecComentario, String nickColaborador, String tituloPropuesta) {
        this.comentario = comentario;
        this.fecComentario = (fecComentario != null) ? fecComentario.toString() : null;
        this.nickColaborador = nickColaborador;
        this.tituloPropuesta = tituloPropuesta;
    }

    public String getComentario() {
        return comentario;
    }

    public String getFecComentario() {
        return fecComentario;
    }

    public String getNickColaborador() {
        return nickColaborador;
    }

    public String getTituloPropuesta() {
        return tituloPropuesta;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setFecComentario(String fecComentario) {
        this.fecComentario = fecComentario;
    }

    public void setNickColaborador(String nickColaborador) {
        this.nickColaborador = nickColaborador;
    }

    public void setTituloPropuesta(String tituloPropuesta) {
        this.tituloPropuesta = tituloPropuesta;
    }
    
    
    
            
}
