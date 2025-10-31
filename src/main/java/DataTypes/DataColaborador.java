/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataTypes;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author luquiprogrammer
 */
public class DataColaborador {
    String nickname;
    String nombre;
    String apellido;
    String email;
    LocalDate fecNac;
    String imagen = "";
    List<DataPropuesta> propuestasColaboradas;
    List<DataUsuario> meSiguen;
    List<DataUsuario> losSigo;
    
    public DataColaborador(){
        
    }
    
    public DataColaborador(String nickname,String nombre, String apellido,String email,LocalDate fecNac, String imagen) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fecNac = fecNac;
        this.imagen = imagen;
        
    }
    //Tal vez hago que no se pase la lista de DataPropuesta en caso de que el colaborador no tenga propuestas a las que colaboro
     public DataColaborador(String nickname,String nombre, String apellido,String email,LocalDate fecNac, String imagen,List<DataPropuesta> propuestas) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fecNac = fecNac;
        this.imagen = imagen;
        this.propuestasColaboradas = propuestas;
    }
    public String getNickname() {
        return this.nickname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public LocalDate getFecNac() {
        return this.fecNac;
    }
    public String getImagen(){
        return this.imagen;
    }
    public List<DataPropuesta> getPropuestas(){
        return this.propuestasColaboradas;
    }

    public void setMeSiguen(List<DataUsuario> meSiguen) {
        this.meSiguen = meSiguen;
    }

    public void setLosSigo(List<DataUsuario> losSigo) {
        this.losSigo = losSigo;
    }

    public List<DataUsuario> getMeSiguen() {
        return meSiguen;
    }

    public List<DataUsuario> getLosSigo() {
        return losSigo;
    }

    public List<DataPropuesta> getPropuestasColaboradas() {
        return propuestasColaboradas;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFecNac(LocalDate fecNac) {
        this.fecNac = fecNac;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setPropuestasColaboradas(List<DataPropuesta> propuestasColaboradas) {
        this.propuestasColaboradas = propuestasColaboradas;
    }
    
    
    
}
