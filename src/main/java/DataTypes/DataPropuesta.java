/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataTypes;

import Logica.Aporte;
import Logica.Estado;
import Logica.Proponente;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nahud
 */
public class DataPropuesta {
    
    String titulo;
    String imagen;
    Estado estadoActual;
    Proponente p;
    String desc;
    String lugar;
    double entrada;
    double necesaria;
    double alcanzada;
    LocalDate fecha;
    LocalDate fechaPubli;
    String fechaPubliStr;
    LocalDateTime fechaLimit;
    String fechaLimitStr;
    EnumRetorno posibleRetorno;
    String categoria;
    String nickProponenteDe;
    private int cantidadColaboradores;
    private List<Aporte> misAportes = new ArrayList<>();

    public DataPropuesta() {
    }
    
    public DataPropuesta(String titulo, String imagen, Estado estadoActual, Proponente p, String descrip, String lugar, double entrada, double necesaria,double alcanzada,LocalDate fecha, LocalDate fechaP, LocalDateTime fechaL , EnumRetorno retorno, String categoria, List<Aporte> misAportes ){
        this.alcanzada = alcanzada;
        this.titulo = titulo;
        this.imagen = imagen;
        this.estadoActual = estadoActual;
        this.p = p;
        this.desc = descrip;
        this.lugar = lugar;
        this.entrada = entrada;
        this.necesaria = necesaria;
        this.fecha = fecha;
        this.fechaPubli = fechaP;
        this.fechaPubliStr = fechaP.toString();
        this.fechaLimit = fechaL;
        this.fechaLimitStr = fechaL.toString();
        this.posibleRetorno = retorno;
        this.categoria = categoria;
        this.misAportes = misAportes;
        this.nickProponenteDe = p.getNickname();
    }
    
    public DataPropuesta(double alcanzada, String titulo, Estado estadoActual, String lugar){
        this.alcanzada = alcanzada;
        this.titulo = titulo;
        this.estadoActual = estadoActual;
        this.lugar = lugar;
    }
    public DataPropuesta(double alcanzada, String titulo, Estado estadoActual, String lugar,String nickProponente){
        this.alcanzada = alcanzada;
        this.titulo = titulo;
        this.estadoActual = estadoActual;
        this.lugar = lugar;
        this.nickProponenteDe = nickProponente;
    }
    
    public String getNickProponenteDe(){
        return this.nickProponenteDe;
    }
    public Double getAlcanzadaa(){
        return this.alcanzada;
    }
    
    public String getTitulo(){
        return this.titulo;
    }
    
    public String getImagen(){
        return this.imagen;
    }
    
    public Estado getEstadoActual(){
        return this.estadoActual;
    }
    
    public Proponente getProponente(){
        return this.p;
    }
    
    public String getLugar(){
        return this.lugar;
    }
    
    public Double getEntradaa(){
        return this.entrada;
    }
    
    public Double getNecesariaa(){
        return this.necesaria;
    }
    
    public double getEntrada(){
        return this.entrada;
    }
    
    public double getNecesaria(){
        return this.necesaria;
    }
    
    public double getAlcanzada(){
        return this.alcanzada;
    }
    
    public EnumRetorno getRetorno(){
        return this.posibleRetorno;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
     public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Aporte> getMisAportes() {
        return misAportes;
    }
     public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setFechaPubli(LocalDate fechaPubli) {
        this.fechaPubli = fechaPubli;
    }

    public LocalDateTime getFechaLimit() {
        return fechaLimit;
    }

    public void setFechaLimit(LocalDateTime fechaLimit) {
        this.fechaLimit = fechaLimit;
    }

    public int getCantidadColaboradores() {
        return cantidadColaboradores;
    }

    public void setCantidadColaboradores(int cantidadColaboradores) {
        this.cantidadColaboradores = cantidadColaboradores;
    }
       
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setNickProponenteDe(String nickProponenteDe) {
        this.nickProponenteDe = nickProponenteDe;
    }

    public Proponente getP() {
        return p;
    }

    public String getDesc() {
        return desc;
    }

    public LocalDate getFechaPubli() {
        return fechaPubli;
    }

    public EnumRetorno getPosibleRetorno() {
        return posibleRetorno;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public void setP(Proponente p) {
        this.p = p;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setEntrada(double entrada) {
        this.entrada = entrada;
    }

    public void setNecesaria(double necesaria) {
        this.necesaria = necesaria;
    }

    public void setAlcanzada(double alcanzada) {
        this.alcanzada = alcanzada;
    }

    public void setPosibleRetorno(EnumRetorno posibleRetorno) {
        this.posibleRetorno = posibleRetorno;
    }

    public void setMisAportes(List<Aporte> misAportes) {
        this.misAportes = misAportes;
    }

    public String getFechaPubliStr() {
        return fechaPubliStr;
    }

    public void setFechaPubliStr(String fechaPubliStr) {
        this.fechaPubliStr = fechaPubliStr;
    }

    public String getFechaLimitStr() {
        return fechaLimitStr;
    }

    public void setFechaLimitStr(String fechaLimitStr) {
        this.fechaLimitStr = fechaLimitStr;
    }

    
    
   
}
