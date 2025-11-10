/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataTypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataSugerencias")
public class DataSugerencias {
    
    private String Propuesta;
    private int puntaje;
    
    public DataSugerencias(){
    }
    
    public DataSugerencias(String propuesta, int puntaje){
        this.Propuesta = propuesta;
        this.puntaje = puntaje;
    }

    public String getPropuesta() {
        return Propuesta;
    }

    public void setPropuesta(String Propuesta) {
        this.Propuesta = Propuesta;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
    
}