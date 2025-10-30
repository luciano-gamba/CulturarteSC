/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WebServices;

import DataTypes.DataCategoria;
import DataTypes.DataComentario;
import DataTypes.DataPropuesta;
import DataTypes.DataPropuestaSimple;
import DataTypes.DataUsuario;
import DataTypes.EnumRetorno;
import Interfaces.Fabrica;
import Interfaces.IControlador;
import Logica.Usuario;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
//import javax.jws.WebMethod;
//import javax.jws.WebParam;
//import javax.jws.WebService;

/**
 *
 * @author mafiu
 */
@WebService(serviceName = "LogicaWS")
public class LogicaWS {
    
    private Endpoint endpoint;
    
    IControlador ic = Fabrica.getInstancia().getIControlador();

    public LogicaWS(){
    }
    
    @WebMethod(exclude = true)
    public void publicar(){
        this.endpoint = 
                Endpoint.publish("http://localhost:9128/logicaWS", this);
        //this.endpoint = Endpoint.publish("../service", this);
        System.out.println("Servicio publicado en "+this.endpoint.toString());
        System.out.println("Servicio publicado en "+this.endpoint.getEndpointReference().toString());
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint(){
        return this.endpoint;
    }
    
//    @WebMethod(operationName = "buscarUsuario")
//    public String buscarUsuario(@WebParam(name = "ciUsu") String ciUsu) throws UsuarioNoExisteException {
//        DataUsuario du = ic.verInfoUsuario(ciUsu);
//        return du.getApellido();
//    }
    
    @WebMethod(operationName = "añadirUsuarioP")
    public int añadirUsuario(@WebParam(name = "nick") String nick, @WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido, @WebParam(name = "correo") String correo, @WebParam(name = "fecNac") LocalDate fecNac, @WebParam(name = "imagen") String imagen, @WebParam(name = "contraseña") String contraseña, @WebParam(name = "direccion") String direccion, @WebParam(name = "bio") String bio, @WebParam(name = "sitioWeb") String sitioWeb, @WebParam(name = "imagenWeb") String imagenWeb){
        return ic.añadirUsuario(nick, nombre, apellido, correo, fecNac, imagen, contraseña, direccion, bio, sitioWeb, imagenWeb);
    }
    
    @WebMethod(operationName = "añadirUsuarioC")
    public int añadirUsuario(@WebParam(name = "nick") String nick, @WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido, @WebParam(name = "correo") String correo, @WebParam(name = "fecNac") LocalDate fecNac, @WebParam(name = "imagen") String imagen, @WebParam(name = "contraseña") String contraseña, @WebParam(name = "imagenWeb") String imagenWeb){
        return ic.añadirUsuario(nick, nombre, apellido, correo, fecNac, imagen, contraseña, imagenWeb);
    }
    
    @WebMethod(operationName = "altaAporte")
    public int altaAporte(@WebParam(name = "miColaborador") String miColaborador, @WebParam(name = "miPropuesta") String miPropuesta, @WebParam(name = "$aporte") double $aporte, @WebParam(name = "cantidad") int cantidad, @WebParam(name = "retorno") EnumRetorno retorno){
        return ic.altaAporte(miColaborador, miPropuesta, $aporte, cantidad, retorno);
    }
    
    @WebMethod(operationName = "altaAporteF")
    public int altaAporte(@WebParam(name = "miColaborador") String miColaborador, @WebParam(name = "miPropuesta") String miPropuesta, @WebParam(name = "$aporte") double $aporte, @WebParam(name = "cantidad") int cantidad, @WebParam(name = "retorno") EnumRetorno retorno, @WebParam(name = "fecAp") LocalDateTime fecAp){
        return ic.altaAporte(miColaborador, miPropuesta, $aporte, cantidad, retorno, fecAp);
    }
    
    @WebMethod(operationName = "getUsuario")
    public Usuario getUsuario(@WebParam(name = "nick") String nick){
        return ic.getUsuario(nick);
    }
    
    @WebMethod(operationName = "getDataUsuarios")
    public List<DataUsuario> getDataUsuarios(){
        return ic.getDataUsuarios();
    }
    
    @WebMethod(operationName = "getDataUsuarioWeb")
    public DataUsuario getDataUsuarioWeb(@WebParam(name = "nick") String nick){
        return ic.getDataUsuarioWeb(nick);
    }
    
    @WebMethod(operationName = "consultaDeProponenteWeb")
    public DataUsuario consultaDeProponenteWeb(@WebParam(name = "NickName") String NickName){
        return ic.consultaDeProponenteWeb(NickName);
    }
    
    @WebMethod(operationName = "consultaDeColaboradorWeb")
    public DataUsuario consultaDeColaboradorWeb(@WebParam(name = "NickName") String NickName){
        return ic.consultaDeColaboradorWeb(NickName);
    }
    
    @WebMethod(operationName = "cargarCategoriasWeb")
    public List<DataCategoria> cargarCategoriasWeb(){
        return ic.cargarCategoriasWeb();
    }
    
    @WebMethod(operationName = "getCategorias")
    public List<String> getCategorias(){
        return ic.getCategorias();
    }
    
    @WebMethod(operationName = "seguirUsuario")
    public int seguirUsuario(@WebParam(name = "nick1") String nick1, @WebParam(name = "nick2") String nick2){
        return ic.seguirUsuario(nick1, nick2);
    }
    
    @WebMethod(operationName = "dejarSeguirUsuario")
    public int dejarSeguirUsuario(@WebParam(name = "nick1") String nick1, @WebParam(name = "nick2") String nick2){
        return ic.dejarSeguirUsuario(nick1, nick2);
    }
    
    @WebMethod(operationName = "altaPropuesta")
    public int altaPropuesta(@WebParam(name = "nick") String nick, @WebParam(name = "tipo") String tipo, @WebParam(name = "titulo") String titulo, @WebParam(name = "descripcion") String descripcion, @WebParam(name = "lugar") String lugar, @WebParam(name = "fechaPrev") LocalDate fechaPrev, @WebParam(name = "montoXentrada") String montoXentrada, @WebParam(name = "montoNecesario") String montoNecesario, @WebParam(name = "posibleRetorno") EnumRetorno posibleRetorno, @WebParam(name = "fechaActual") LocalDate fechaActual, @WebParam(name = "imagen") String imagen){
        return ic.altaPropuesta(nick, tipo, titulo, descripcion, lugar, fechaPrev, montoXentrada, montoNecesario, posibleRetorno, fechaActual, imagen);
    }
        
    @WebMethod(operationName = "getPropuestas")
    public List<String> getPropuestas(){
        return ic.getPropuestas();
    }
    
    @WebMethod(operationName = "consultaDePropuesta")
    public DataPropuesta consultaDePropuesta(@WebParam(name = "titulo") String titulo){
        return ic.consultaDePropuesta(titulo);
    }
    
    @WebMethod(operationName = "getDataPropuestaSimple")
    public DataPropuestaSimple getDataPropuestaSimple(@WebParam(name = "titulo") String titulo){
        return ic.getDataPropuestaSimple(titulo);
    }
    
    @WebMethod(operationName = "existeTitulo")
    public boolean existeTitulo(@WebParam(name = "titulo") String titulo){
        return ic.existeTitulo(titulo);
    }
    
    @WebMethod(operationName = "existeNick")
    public boolean existeNick(@WebParam(name = "nick") String nick){
        return ic.existeNick(nick);
    }
    
    @WebMethod(operationName = "getUsuarioPorMail")
    public String getUsuarioPorMail(@WebParam(name = "nick") String nick){
        return ic.getUsuarioPorMail(nick);
    }
    
    @WebMethod(operationName = "cambiarFavorita")
    public boolean cambiarFavorita(@WebParam(name = "titulo") String titulo, @WebParam(name = "nick") String nick){
        return ic.cambiarFavorita(titulo, nick);
    }
    
    @WebMethod(operationName = "addComentario")
    public void addComentario(@WebParam(name = "titulo") String titulo, @WebParam(name = "nick") String nick, @WebParam(name = "comentario") String comentario){
        ic.addComentario(titulo, nick, comentario);
    }
    
    @WebMethod(operationName = "getDataComentarios")
    public List<DataComentario> getDataComentarios(@WebParam(name = "titulo") String titulo){
        return ic.getDataComentarios(titulo);
    }
    
    @WebMethod(operationName = "extenderFinanciacion")
    public int extenderFinanciacion(@WebParam(name = "titulo") String titulo){
        return ic.extenderFinanciacion(titulo);
    }
    
    @WebMethod(operationName = "cancelarPropuesta")
    public int cancelarPropuesta(@WebParam(name = "titulo") String titulo){
        return ic.cancelarPropuesta(titulo);
    }
    
    @WebMethod(operationName = "comprobarPropuestas")
    public void comprobarPropuestas(){
        ic.comprobarPropuestas();
    }
}
