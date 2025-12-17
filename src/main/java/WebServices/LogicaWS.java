package WebServices;

import DataTypes.DataAporte;
import DataTypes.DataCategoria;
import DataTypes.DataColaborador;
import DataTypes.DataComentario;
import DataTypes.DataPago;
import DataTypes.DataProponente;
import DataTypes.DataPropuesta;
import DataTypes.DataPropuestaSimple;
import DataTypes.DataRegistro;
import DataTypes.DataUsuario;
import DataTypes.DataSugerencias;
import DataTypes.EnumRetorno;
import DataTypes.EnumPago;
import DataTypes.EnumTarjeta;
import Interfaces.Fabrica;
import Interfaces.IControlador;
import Logica.Proponente;
import Logica.Usuario;
import Logica.Aporte;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author mafiu
 */
@WebService(serviceName = "LogicaWS")
public class LogicaWS {

    private Endpoint endpoint;

    IControlador ic = Fabrica.getInstancia().getIControlador();
    
    private String host;
    private int port;
    private String context;

    public LogicaWS(String host, int port, String context) {
        this.host = host;
        this.port = port;
        this.context = context;
    }
    
    public LogicaWS() {
    }
    
    @WebMethod(exclude = true)
    public void publicar() {
        String url = "http://" + host + ":" + port + context;
        this.endpoint = Endpoint.publish(url, this);
        //this.endpoint = Endpoint.publish("../service", this);
        //System.out.println("Servicio publicado en " + this.endpoint.toString());
        //System.out.println("Servicio publicado en " + this.endpoint.getEndpointReference().toString());
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return this.endpoint;
    }

//    @WebMethod(operationName = "buscarUsuario")
//    public String buscarUsuario(@WebParam(name = "ciUsu") String ciUsu) throws UsuarioNoExisteException {
//        DataUsuario du = ic.verInfoUsuario(ciUsu);
//        return du.getApellido();
//    }
    @WebMethod(operationName = "añadirUsuarioP")
    public int añadirUsuarioP(@WebParam(name = "nick") String nick, @WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido, @WebParam(name = "correo") String correo, @WebParam(name = "fecNac") String fecNac, @WebParam(name = "imagen") String imagen, @WebParam(name = "contraseña") String contraseña, @WebParam(name = "direccion") String direccion, @WebParam(name = "bio") String bio, @WebParam(name = "sitioWeb") String sitioWeb) {
        return ic.añadirUsuario(nick, nombre, apellido, correo, fecNac, imagen, contraseña, direccion, bio, sitioWeb);
    }

    @WebMethod(operationName = "añadirUsuarioC")
    public int añadirUsuarioC(@WebParam(name = "nick") String nick, @WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido, @WebParam(name = "correo") String correo, @WebParam(name = "fecNac") String fecNac, @WebParam(name = "imagen") String imagen, @WebParam(name = "contraseña") String contraseña) {
        return ic.añadirUsuario(nick, nombre, apellido, correo, fecNac, imagen, contraseña);
    }

    @WebMethod(operationName = "altaAporte")
    public int altaAporte(@WebParam(name = "miColaborador") String miColaborador, @WebParam(name = "miPropuesta") String miPropuesta, @WebParam(name = "aporte") double aporte, @WebParam(name = "cantidad") int cantidad, @WebParam(name = "retorno") EnumRetorno retorno) {
        return ic.altaAporte(miColaborador, miPropuesta, aporte, cantidad, retorno);
    }

    @WebMethod(operationName = "altaAporteF")
    public int altaAporteF(@WebParam(name = "miColaborador") String miColaborador, @WebParam(name = "miPropuesta") String miPropuesta, @WebParam(name = "aporte") double aporte, @WebParam(name = "cantidad") int cantidad, @WebParam(name = "retorno") EnumRetorno retorno, @WebParam(name = "fecAp") String fecAp) {
        return ic.altaAporte(miColaborador, miPropuesta, aporte, cantidad, retorno, fecAp);
    }

    @WebMethod(operationName = "getUsuario")
    public Usuario getUsuario(@WebParam(name = "nick") String nick) {
        return ic.getUsuario(nick);
    }

    @WebMethod(operationName = "getDataUsuarios")
    public List<DataUsuario> getDataUsuarios() {
        return ic.getDataUsuarios();
    }

    @WebMethod(operationName = "getDataUsuarioWeb")
    public DataUsuario getDataUsuarioWeb(@WebParam(name = "nick") String nick) {
        return ic.getDataUsuarioWeb(nick);
    }

    @WebMethod(operationName = "consultaDeProponenteWeb")
    public DataUsuario consultaDeProponenteWeb(@WebParam(name = "NickName") String NickName) {
        return ic.consultaDeProponenteWeb(NickName);
    }

    @WebMethod(operationName = "consultaDeColaboradorWeb")
    public DataUsuario consultaDeColaboradorWeb(@WebParam(name = "NickName") String NickName) {
        return ic.consultaDeColaboradorWeb(NickName);
    }

    @WebMethod(operationName = "cargarCategoriasWeb")
    public List<DataCategoria> cargarCategoriasWeb() {
        return ic.cargarCategoriasWeb();
    }

    @WebMethod(operationName = "getCategorias")
    public List<String> getCategorias() {
        return ic.getCategorias();
    }

    @WebMethod(operationName = "seguirUsuario")
    public int seguirUsuario(@WebParam(name = "nick1") String nick1, @WebParam(name = "nick2") String nick2) {
        return ic.seguirUsuario(nick1, nick2);
    }

    @WebMethod(operationName = "dejarSeguirUsuario")
    public int dejarSeguirUsuario(@WebParam(name = "nick1") String nick1, @WebParam(name = "nick2") String nick2) {
        return ic.dejarSeguirUsuario(nick1, nick2);
    }

    @WebMethod(operationName = "altaPropuesta")
    public int altaPropuesta(@WebParam(name = "nick") String nick, @WebParam(name = "tipo") String tipo, @WebParam(name = "titulo") String titulo, @WebParam(name = "descripcion") String descripcion, @WebParam(name = "lugar") String lugar, @WebParam(name = "fechaPrev") String fechaPrev, @WebParam(name = "montoXentrada") String montoXentrada, @WebParam(name = "montoNecesario") String montoNecesario, @WebParam(name = "posibleRetorno") EnumRetorno posibleRetorno, @WebParam(name = "fechaActual") String fechaActual, @WebParam(name = "imagen") String imagen) {
        return ic.altaPropuesta(nick, tipo, titulo, descripcion, lugar, fechaPrev, montoXentrada, montoNecesario, posibleRetorno, fechaActual, imagen);
    }

    @WebMethod(operationName = "getPropuestas")
    public List<String> getPropuestas() {
        return ic.getPropuestas();
    }

    @WebMethod(operationName = "consultaDePropuesta")
    public DataPropuesta consultaDePropuesta(@WebParam(name = "titulo") String titulo) {
        return ic.consultaDePropuesta(titulo);
    }

    @WebMethod(operationName = "getDataPropuestaSimple")
    public DataPropuestaSimple getDataPropuestaSimple(@WebParam(name = "titulo") String titulo) {
        return ic.getDataPropuestaSimple(titulo);
    }

    @WebMethod(operationName = "existeTitulo")
    public boolean existeTitulo(@WebParam(name = "titulo") String titulo) {
        return ic.existeTitulo(titulo);
    }

    @WebMethod(operationName = "existeNick")
    public boolean existeNick(@WebParam(name = "nick") String nick) {
        return ic.existeNick(nick);
    }

    @WebMethod(operationName = "getUsuarioPorMail")
    public String getUsuarioPorMail(@WebParam(name = "nick") String nick) {
        return ic.getUsuarioPorMail(nick);
    }

    @WebMethod(operationName = "cambiarFavorita")
    public boolean cambiarFavorita(@WebParam(name = "titulo") String titulo, @WebParam(name = "nick") String nick) {
        return ic.cambiarFavorita(titulo, nick);
    }

    @WebMethod(operationName = "addComentario")
    public void addComentario(@WebParam(name = "titulo") String titulo, @WebParam(name = "nick") String nick, @WebParam(name = "comentario") String comentario) {
        ic.addComentario(titulo, nick, comentario);
    }

    @WebMethod(operationName = "getDataComentarios")
    public List<DataComentario> getDataComentarios(@WebParam(name = "titulo") String titulo) {
        return ic.getDataComentarios(titulo);
    }

    @WebMethod(operationName = "extenderFinanciacion")
    public int extenderFinanciacion(@WebParam(name = "titulo") String titulo) {
        return ic.extenderFinanciacion(titulo);
    }

    @WebMethod(operationName = "cancelarPropuesta")
    public int cancelarPropuesta(@WebParam(name = "titulo") String titulo) {
        return ic.cancelarPropuesta(titulo);
    }

    @WebMethod(operationName = "comprobarPropuestas")
    public void comprobarPropuestas() {
        ic.comprobarPropuestas();
    }

    @WebMethod(operationName = "existeCorreo")
    public boolean existeCorreo(@WebParam(name = "correo") String correo) {
        return ic.existeCorreo(correo);
    }

    @WebMethod(operationName = "verificarUsuario")
    public int verificarUsuario(@WebParam(name = "usuario") String usuario, @WebParam(name = "contraseña") String contraseña) {
        return ic.verificarUsuario(usuario, contraseña);
    }

    @WebMethod(operationName = "esFavorita")
    public boolean esFavorita(@WebParam(name = "titulo") String titulo, @WebParam(name = "nick") String nick) {
        return ic.esFavorita(titulo, nick);
    }

    @WebMethod(operationName = "getProponentes")
    public List<Proponente> getProponentes() {
        return ic.getProponentes();
    }

    @WebMethod(operationName = "consultaDeColaborador")
    public DataColaborador consultaDeColaborador(@WebParam(name = "NickName") String NickName) {
        return ic.consultaDeColaborador(NickName);
    }
    
    @WebMethod(operationName = "consultaDeProponente")
    public DataProponente consultaDeProponente(@WebParam(name = "NickName") String NickName){
        return ic.consultaDeProponente(NickName);
    }

    @WebMethod(operationName = "getDataColaborador")
    public DataColaborador getDataColaborador(@WebParam(name = "nickname") String nickname, @WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido, @WebParam(name = "email") String email, @WebParam(name = "fecNac") String fecNac, @WebParam(name = "imagen") String imagen) {
        return ic.getDataColaborador(nickname, nombre, apellido, email, fecNac, imagen);
    }

    @WebMethod(operationName = "getDataColaboradorWeb")
    public DataColaborador getDataColaboradorWeb(String nickname, String imagen) {
        return ic.getDataColaboradorWeb(nickname, imagen);
    }

    @WebMethod(operationName = "getDataAporte")
    public DataAporte getDataAporte(@WebParam(name = "tituloNick") String tituloNick, @WebParam(name = "nick") String nick) {
        return ic.getDataAporte(tituloNick, nick);
    }

//    @WebMethod(operationName = "getDataAporte") ??????????????????????
//    public DataAporte getDataAporte(@WebParam(name = "aporte") double aporte, @WebParam(name = "fechaHora") String fechaHora, @WebParam(name = "miPropuesta") String miPropuesta){
//        return ic.getDataAporte(aporte, fechaHora, miPropuesta);
//    }
    @WebMethod(operationName = "eliminarProponente")
    public void eliminarProponente(@WebParam(name = "nick") String nick) {
        ic.eliminarProponente(nick);
    }

    @WebMethod(operationName = "getDataSugerencia")
    public DataSugerencias getDataSugerencia(@WebParam(name = "titulo") String titulo, @WebParam(name = "puntaje") int puntaje) {
        return ic.getDataSugerencia(titulo, puntaje);
    }
 
    @WebMethod(operationName = "getDataProponente")
    public DataProponente getDataProponente() {
        return ic.getDataProponente();
    }
    
    @WebMethod(operationName = "ordenarDTUporSeguidores")
    public List<DataUsuario> ordenarDTUporSeguidores(@WebParam(name = "listaDTU") List<DataUsuario> listaDTU){
        return ic.ordenarDTUporSeguidores(listaDTU);
    }

    @WebMethod(operationName = "setPagoT")
    public void setPagoT(@WebParam(name = "titular") String titular, @WebParam(name = "nick") String nick, @WebParam(name = "titulo") String titulo, @WebParam(name = "numeroT") String numeroT, @WebParam(name = "fechaT") String fechaT, @WebParam(name = "CVC") String CVC, @WebParam(name = "enumTarjeta") EnumTarjeta enumTarjeta) {
        ic.setPagoT(titular, nick, titulo, numeroT, fechaT, CVC, enumTarjeta);

    
    }
	
    @WebMethod(operationName = "setPagoB")
    public void setPagoB(@WebParam(name = "titular") String titular, @WebParam(name = "nick") String nick, @WebParam(name = "titulo") String titulo, @WebParam(name = "numeroB") String numeroB, @WebParam(name = "nombreB") String nombreB) {
        ic.setPagoB(titular, nick, titulo, nombreB, numeroB);
    }

    @WebMethod(operationName = "setPagoP")
    public void setPagoP(@WebParam(name = "titular") String titular, @WebParam(name = "nick") String nick, @WebParam(name = "titulo") String titulo, @WebParam(name = "numeroP") String numeroP) {
        ic.setPagoP(titular, nick, titulo, numeroP);
    }

    @WebMethod(operationName = "getDataPago")
    public DataPago getDataPago(@WebParam(name = "nick") String nick, @WebParam(name = "titulo") String titulo) {
        return ic.getDataPago( nick, titulo);
    }

    @WebMethod(operationName = "registrarSesion")
    public void RegistrarSesion(@WebParam(name = "dataSesion") DataRegistro dataSesion){
        ic.registrarSesion(dataSesion);
    }
    
    @WebMethod(operationName = "getTrello")
    public List<String> getTrello(){
        return ic.getTrello();
    }
    
    @WebMethod(operationName = "subirFoto")
    public void subirFotoAlCentral(String nombre, String base64) {
        ic.procesarFoto(nombre,base64);
    }
}
