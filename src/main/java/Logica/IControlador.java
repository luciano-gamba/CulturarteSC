package Logica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;

public interface IControlador {
    
    int añadirUsuario(String nick, String nombre, String apellido, String correo, LocalDate fecNac, String imagen, String contraseña, String direccion, String bio, String sitioWeb, String imagenWeb);
    
    int añadirUsuario(String nick, String nombre, String apellido, String correo, LocalDate fecNac, String imagen, String contraseña, String imagenWeb);
    
    int altaCategoria(String nombreCat);
    
    int altaCategoria(String nombreCat,String nombrePadreCat);
    
    int altaAporte(String miColaborador, String miPropuesta, double $aporte, int cantidad, EnumRetorno retorno);
    
    int altaAporte(String miColaborador, String miPropuesta, double $aporte, int cantidad, EnumRetorno retorno,LocalDateTime fecAp);
        
    List<String> getUsuarios(); 
    
    Usuario getUsuario(String nick);
    
    List<DataUsuario> getDataUsuarios();
    
    DataUsuario getDataUsuarioWeb(String nick);
    
    List<String> getUsuariosProponentes();
    
    List<DataPropuesta> getPropuestasPorCategoria(String Categoria);
    
    List<Proponente> getProponentes();
    
    List<Colaborador> getUsuariosColaboradores();
    
    DataProponente consultaDeProponente(String NickName);
    
    DataUsuario consultaDeProponenteWeb(String NickName);
    
    DataColaborador consultaDeColaborador(String NickName);
    
    DataUsuario consultaDeColaboradorWeb(String NickName);
    
    List<DataCategoria> cargarCategoriasWeb();
    
    List<String> getCategorias();
    
    List<String> getSeguidos(String seguidor);
    
    List<String> getColaboradores();
    
    List<String> getPropuestas_Proponentes();
    
    int seguirUsuario(String nick1, String nick2);
    
    int dejarSeguirUsuario(String nick1, String nick2);
    
    int altaPropuesta(String nick, String tipo, String titulo, String descripcion, String lugar, LocalDate fechaPrev, String montoXentrada, String montoNecesario, EnumRetorno posibleRetorno, LocalDate fechaActual, String imagen);
    
    int modificarPropuesta(String titulo, String descripcion, String lugar, LocalDate fechaPrev, String montoXentrada, String montoNecesario, String posibleRetorno, String estado, String imagen, String categoria);
    
    DefaultMutableTreeNode cargarNodoRaizCategorias();
        
    List<String> getPropuestas();
    
    DataPropuesta consultaDePropuesta(String titulo);
    
    DataPropuesta getDataPropuesta(String titulo_nick);
    
    DataPropuestaSimple getDataPropuestaSimple(String titulo);
    
    List<String> getEstados();
     
    List<String> getPropuestasXColaborador(String nick);
    
    DataAporte getDataAporte(String tituloNick, String nick);
    
    void borrarAporte(String tituloNick, String nick);
    
    boolean existeTitulo(String titulo);
    
    boolean existeNick(String nick);
    
    boolean existeCorreo(String correo);
    
    List<String> getColabsProp(String titulo);
    
    boolean seleccionaCategoria(String categoria);
    
    int verificarUsuario(String usuario, String contraseña);
    
    String getUsuarioPorMail(String nick);
        
    boolean esFavorita(String titulo, String nick);
    
    boolean cambiarFavorita(String titulo, String nick);
    
    void addComentario(String titulo, String nick, String comentario);
    
    DataComentario getDataComentario(String titulo, String nick);
    
    List<DataComentario> getDataComentarios(String titulo);
    
    int extenderFinanciacion(String titulo);
    
    int cancelarPropuesta(String titulo);
    
    void comprobarPropuestas();
}
