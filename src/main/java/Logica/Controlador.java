package Logica;

import Interfaces.IControlador;
import DataTypes.EnumRetorno;
import DataTypes.EnumEstado;
import DataTypes.DataComentario;
import DataTypes.DataAporte;
import DataTypes.DataCategoria;
import DataTypes.DataColaborador;
import DataTypes.DataPago;
import DataTypes.DataProponente;
import DataTypes.DataPropuesta;
import DataTypes.DataPropuestaSimple;
import DataTypes.DataSugerencias;
import DataTypes.DataUsuario;
import DataTypes.EnumPago;
import DataTypes.EnumTarjeta;
import Persistencia.ControladoraPersistencia;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.tree.DefaultMutableTreeNode;
import org.mindrot.jbcrypt.BCrypt;


public class Controlador implements IControlador{

    public List<Usuario> misUsuarios = new ArrayList<>();
    public List<Proponente> misProponentes = new ArrayList<>();
    public List<Colaborador> misColaboradores = new ArrayList<>();
    public List<Propuesta> misPropuestas = new ArrayList<>();
    
    public Controlador() {
        
    }
    
    ControladoraPersistencia cp = new ControladoraPersistencia();
    
    @Override //colaborador
    public int añadirUsuario(String nick, String nombre, String apellido, String correo, LocalDate fecNac, String imagen, String contraseña, String imagenWeb){
        String nickNuevo = nick;
        String correoNuevo = correo;
        
        ArrayList<Usuario> listaUsuarios = cp.getListaUsuarios();
        
        for(Usuario u : listaUsuarios){
            if(u.getNickname().equals(nickNuevo)){
                return 0;
            }
            if(u.getEmail().equals(correoNuevo)){
                return 2;
            }
        }
        
        Colaborador colaNuevo = new Colaborador(nick, correo, nombre, apellido, fecNac, imagen, contraseña, imagenWeb);
        misUsuarios.add(colaNuevo);
        cp.añadirUsuario(colaNuevo);
        return 1;
    }
    
    @Override //colaborador (fecha con string)
    public int añadirUsuario(String nick, String nombre, String apellido, String correo, String fecN, String imagen, String contraseña, String imagenWeb){
        String nickNuevo = nick;
        String correoNuevo = correo;
        
        ArrayList<Usuario> listaUsuarios = cp.getListaUsuarios();
        
        for(Usuario u : listaUsuarios){
            if(u.getNickname().equals(nickNuevo)){
                return 0;
            }
            if(u.getEmail().equals(correoNuevo)){
                return 2;
            }
        }
        LocalDate fecNac = LocalDate.parse(fecN);
        Colaborador colaNuevo = new Colaborador(nick, correo, nombre, apellido, fecNac, imagen, contraseña, imagenWeb);
        misUsuarios.add(colaNuevo);
        cp.añadirUsuario(colaNuevo);
        return 1;
    }
    
    @Override //proponente
    public int añadirUsuario(String nick, String nombre, String apellido, String correo, LocalDate fecNac, String imagen, String contraseña, String direccion, String bio, String sitioWeb, String imagenWeb){
        String nickNuevo = nick;
        String correoNuevo = correo;

        ArrayList<Usuario> listaUsuarios = cp.getListaUsuarios();
        
        for(Usuario u : listaUsuarios){
            if(u.getNickname().equals(nickNuevo)){
                return 0;
            }
            if(u.getEmail().equals(correoNuevo)){
                return 2;
            }
        }
        
        Proponente propNuevo = new Proponente(direccion, bio, sitioWeb, nick, correo, nombre, apellido, fecNac, imagen, contraseña, imagenWeb);
        cp.añadirUsuario(propNuevo);
        return 1;
    }
    
    @Override //proponente (fecha con string)
    public int añadirUsuario(String nick, String nombre, String apellido, String correo, String fecN, String imagen, String contraseña, String direccion, String bio, String sitioWeb, String imagenWeb){
        String nickNuevo = nick;
        String correoNuevo = correo;

        ArrayList<Usuario> listaUsuarios = cp.getListaUsuarios();
        
        for(Usuario u : listaUsuarios){
            if(u.getNickname().equals(nickNuevo)){
                return 0;
            }
            if(u.getEmail().equals(correoNuevo)){
                return 2;
            }
        }
        
        LocalDate fecNac = LocalDate.parse(fecN);
        Proponente propNuevo = new Proponente(direccion, bio, sitioWeb, nick, correo, nombre, apellido, fecNac, imagen, contraseña, imagenWeb);
        cp.añadirUsuario(propNuevo);
        return 1;
    }
    
    @Override
    public int altaCategoria(String nombreCat){
        if(cp.findCategoria(nombreCat) != null){
            return -2; //ya existe esta categoria
        }
        
        Categoria padre = cp.findCategoria("Categoria");
        if(padre==null){
            padre = new Categoria("Categoria");
            cp.createCategoria(padre);
        }
        Categoria nueva = new Categoria(nombreCat);
        nueva.setPadre(padre);
        padre.getHijas().add(nueva);
        
        try {
            cp.createCategoria(nueva);
        } catch (Exception e){
            e.printStackTrace();
            return -3; //Error de persistencia
        }
        return 0; //Funciono
    }
    
    @Override
    public int altaCategoria(String nombreCat,String nombrePadreCat){
       if(cp.findCategoria(nombreCat) != null){
            return -2; //ya existe esta categoria
        }
       Categoria padre = cp.findCategoria(nombrePadreCat);
        if(padre == null){
            if(nombrePadreCat.equals("Categoria")){
                padre = new Categoria("Categoria");
                cp.createCategoria(padre);
            } else{
                return -1; //Padre no existe
            }    
        }
        Categoria nueva = new Categoria(nombreCat);
        nueva.setPadre(padre);
        padre.getHijas().add(nueva);
        
        try {
            cp.createCategoria(nueva);
        } catch (Exception e){
            e.printStackTrace();
            return -3; //Error de persistencia
        }
            
        return 0;
    }
 
    @Override
    public DefaultMutableTreeNode cargarNodoRaizCategorias(){
        List<Categoria> todas = cp.listarCategorias();
        
        Categoria raizCat = cp.findCategoria("Categoria");
        if(raizCat == null){
            raizCat = new Categoria("Categoria");
            cp.createCategoria(raizCat);
        }
        
        DefaultMutableTreeNode nodoRaiz = new DefaultMutableTreeNode(raizCat);
        
        List<DefaultMutableTreeNode> pendientes = new ArrayList<>();
        
        for(Categoria cat : todas){
            if(!cat.getNombre().equalsIgnoreCase("Categoria")){
                DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(cat);
                Categoria padre = cat.getPadre();
                if(padre == null){
                    nodoRaiz.add(nodo);
                }else{
                    DefaultMutableTreeNode nodoPadre = buscarNodo(nodoRaiz, padre.getNombre());
                    if(nodoPadre != null){
                        nodoPadre.add(nodo);
                    }else{
                       pendientes.add(nodo);
                    }
                }
                        
            }
        }
        
        //Reintento para huerfanos (cuando se intento ingresar no tenia el padre en el DefaultMutableTree
        boolean agregado;
        do{
            agregado = false;
            Iterator<DefaultMutableTreeNode> it = pendientes.iterator();
            while(it.hasNext()){
                DefaultMutableTreeNode nodoPendiente = it.next();
                Categoria catPend = (Categoria) nodoPendiente.getUserObject();
                DefaultMutableTreeNode nodoPadre = buscarNodo(nodoRaiz , catPend.getPadre().getNombre());
                if(nodoPadre != null){
                    nodoPadre.add(nodoPendiente);
                    it.remove();
                    agregado = true;
                }
            }
        } while(agregado && !pendientes.isEmpty());
        
        return nodoRaiz;
    }
    
    private DefaultMutableTreeNode buscarNodo(DefaultMutableTreeNode raiz, String nombre){
        Enumeration<?> en = raiz.breadthFirstEnumeration();
        while(en.hasMoreElements()){
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) en.nextElement();
            Categoria cat = (Categoria) nodo.getUserObject();
            if(cat.getNombre().equalsIgnoreCase(nombre)){
                return nodo;
            }
        }
        return null;
    }
    
    @Override
    public int altaAporte(String strmiColaborador, String strmiPropuesta,  double aporte, int cantidad, EnumRetorno retorno){
        Propuesta miPropuesta = null;
        Colaborador miColaborador = null;                
        for (Colaborador c : cp.getColaboradores()){
            if(c.getNickname().equals(strmiColaborador)){
                miColaborador = c;
                break;
            }
        }        
        for (Propuesta p : cp.getListaPropuestas()) {
            if (p.getTitulo_Nickname().equals(strmiPropuesta)) {
                miPropuesta = p;
                break;        
            }
        }                
        //if($aporte > miPropuesta.getmontoNecesaria() || $aporte > miPropuesta.getmontoNecesaria()-miPropuesta.getmontoAlcanzada()){
        //    return -2;//ERROR: Aporte superior a lo permitido - ESTO HAY QUE SACARLO el monto puede ser infinito, esto no es error
        //}        
        if (miColaborador.createAporte(miPropuesta.getTitulo(), aporte, cantidad, retorno) == null) {
            return -3;  //Error: El usuario ya colabora con la Propuesta
        }         
        if (miPropuesta.getPosibleRetorno()!=EnumRetorno.AMBOS && miPropuesta.getPosibleRetorno()!=retorno){
            return -4; //Error: Retorno no valido en esta Propuesta
        }        
        Aporte a = miColaborador.createAporte(miPropuesta.getTitulo(), aporte, cantidad, retorno);
        miPropuesta.addAporte(a);
        miColaborador.añadirAporte(a);
        cp.añadirAporte(a, miPropuesta, miColaborador);
        
        return 0; //PROPUESTA AGREGADA CORRECTAMENTE  
    }
    
    @Override
    public int altaAporte(String strmiColaborador, String strmiPropuesta,  double aporte, int cantidad, EnumRetorno retorno, LocalDateTime fecAp){
        Propuesta miPropuesta = null;
        Colaborador miColaborador = null;                
        miColaborador = cp.buscarColaborador(strmiColaborador);
        miPropuesta = cp.getPropuesta(strmiPropuesta);
        
//        if($aporte > miPropuesta.getmontoNecesaria() || $aporte > miPropuesta.getmontoNecesaria()-miPropuesta.getmontoAlcanzada()){
//            return -2;//ERROR: Aporte superior a lo permitido
//        }        
        if (miColaborador.createAporte(miPropuesta.getTitulo(), aporte, cantidad, retorno) == null) {
            return -3;  //Error: El usuario ya colabora con la Propuesta
        }         
        if (miPropuesta.getPosibleRetorno()!=EnumRetorno.AMBOS && miPropuesta.getPosibleRetorno()!=retorno){
            return -4; //Error: Retorno no valido en esta Propuesta
        }        
        Aporte a = miColaborador.createAporte(miPropuesta.getTitulo(), aporte, cantidad, retorno, fecAp);
        miPropuesta.addAporte(a);
        miColaborador.añadirAporte(a);
        cp.añadirAporte(a, miPropuesta, miColaborador);
        
        return 0; //PROPUESTA AGREGADA CORRECTAMENTE  
    }
    
    @Override //fecha con string
    public int altaAporte(String strmiColaborador, String strmiPropuesta,  double aporte, int cantidad, EnumRetorno retorno, String fecA){
        Propuesta miPropuesta = null;
        Colaborador miColaborador = null;                
        miColaborador = cp.buscarColaborador(strmiColaborador);
        miPropuesta = cp.getPropuesta(strmiPropuesta);
        
//        if($aporte > miPropuesta.getmontoNecesaria() || $aporte > miPropuesta.getmontoNecesaria()-miPropuesta.getmontoAlcanzada()){
//            return -2;//ERROR: Aporte superior a lo permitido
//        }        
        if (miColaborador.createAporte(miPropuesta.getTitulo(), aporte, cantidad, retorno) == null) {
            return -3;  //Error: El usuario ya colabora con la Propuesta
        }         
        if (miPropuesta.getPosibleRetorno()!=EnumRetorno.AMBOS && miPropuesta.getPosibleRetorno()!=retorno){
            return -4; //Error: Retorno no valido en esta Propuesta
        }

        LocalDateTime fecAp;
        if(fecA.equals("fechaActual")){
            fecAp = LocalDateTime.now();
        }else{
            fecAp = LocalDateTime.parse(fecA);
        }
        Aporte a = miColaborador.createAporte(miPropuesta.getTitulo(), aporte, cantidad, retorno, fecAp);
        miPropuesta.addAporte(a);
        miColaborador.añadirAporte(a);
        cp.añadirAporte(a, miPropuesta, miColaborador);
        
        return 0; //PROPUESTA AGREGADA CORRECTAMENTE  
    }
    
    
    @Override
    public List<String> getUsuarios() {
        List<String> listaNombres = new ArrayList<>();
        
//        String aux;
//        for(Usuario u : misUsuarios){
//            aux = u.getNickname();
//            listaNombres.add(aux);
//        }
        
//        si fuera con persistencia
        ArrayList<Usuario> listaUsuarios = cp.getListaUsuarios();
        String aux;
        boolean seMuestra = false;
        for(Usuario u : listaUsuarios){
            if(u instanceof Proponente){
                Proponente p = (Proponente) u;
                seMuestra = p.isActivo();
            }else{
                seMuestra = true;
            }
            if(seMuestra){
                aux = u.getNickname();
                listaNombres.add(aux);
            }
        }
        
        return listaNombres;
    }
    
    @Override
    public Usuario getUsuario(String nick){
        return cp.buscarUsuario(nick);
    }
    
    @Override
    public List<Proponente> getProponentes(){
        return cp.getListaProponentes();
    }
    
    @Override
    public List<Colaborador> getUsuariosColaboradores(){
        return cp.getColaboradores();
    }
    
    @Override
     public List<DataUsuario> getDataUsuarios(){
        ArrayList<Usuario> listaUsuarios = cp.getListaUsuarios();
        List<DataUsuario> ListaDTUsuario = new ArrayList<>(); 
        boolean SeMuestra = false;
        for(Usuario u : listaUsuarios){
          
          DataUsuario data = new DataUsuario();
          
          data.setNickname(u.getNickname());
          data.setNombre(u.getNombre());
          data.setApellido(u.getApellido());
          data.setImagen(u.getImagenWeb());
          data.setMeSiguen(this.getSeguidores(u));
          
          if (u instanceof Proponente){
              Proponente p = (Proponente) u;
              data.setBiografia(p.getBiografia());
              data.setTipo("Proponente");
              SeMuestra = p.isActivo(); //Si el proponente no esta activo no lo agrego
          }else if (u instanceof Colaborador){
              Colaborador c = (Colaborador) u;
              data.setTipo("Colaborador");
              data.setBiografia(null);
              SeMuestra = true;
          }else {
              System.out.println("ERROR usuario sin tipo asignado?");
          }
          if(SeMuestra){
            ListaDTUsuario.add(data);
          }
        }
        return ListaDTUsuario;
    }
    
    @Override
    public List<DataUsuario> ordenarDTUporSeguidores(List<DataUsuario> listaDTU){
        return listaDTU.stream().filter(u -> u.getMeSiguen() != null && !u.getMeSiguen().isEmpty()).sorted((u1, u2) -> Integer.compare(u2.getMeSiguen().size(), u1.getMeSiguen().size())).collect(Collectors.toList());
    }
    
    @Override
    public DataUsuario getDataUsuarioWeb(String nick){
        Usuario usu = cp.buscarUsuario(nick);
        DataUsuario data = new DataUsuario();
        data.setNickname(usu.getNickname());
        data.setImagen(usu.getImagenWeb());
        
        if (usu instanceof Proponente){
            data.setTipo("Proponente");
        }else if (usu instanceof Colaborador){
            data.setTipo("Colaborador");
        }
        return data;
    }
    
     @Override
    public List<String> getColaboradores() {
        
        //MEMORIA LOCAL
//        List<String> lista = new ArrayList<>();
//        for(Colaborador c : misColaboradores){
//            lista.add(c.getNickname());
//        }
//        return lista;
        
        //CON PERSISTENCIA
        return cp.getNickColaboradores();
    }
    
    @Override
    public List<String> getPropuestas_Proponentes() {
        //MEMORIA LOCAL
//        List<String> lista = new ArrayList<>();
//        for(Propuesta p : misPropuestas){
//            lista.add(p.getTitulo_Nickname());
//        }
//        return lista;
        
        //CON PERSISTENCIA 
        List<String> lista = new ArrayList<>();
        for(Propuesta p : cp.getListaPropuestas()){
            if(p.isProponenteActivo()){
                lista.add(p.getTitulo_Nickname());
            }
        }
        return lista;
    }
    
    @Override
    public List<String> getUsuariosProponentes() {
        List<String> listaNombres = new ArrayList<>();
//        String aux;
//        for(Proponente p : misProponentes){
//            aux = p.getNickname();
//            listaNombres.add(aux);
//        }
        
        
        ArrayList<Proponente> listaProponentes = cp.getListaProponentes();
        String aux;
        for(Proponente p : listaProponentes){
            if(p.isActivo()){
                aux = p.getNickname();
                listaNombres.add(aux);
            }
        }
        //Lo podemos dejar como prefieran pero yo siento que queda mejor si directamente le pedimos los name al controlador  de 
        //persistencia y funcionaria igual sin tener que hacer que el controlador reciba en este punto los Proponentes
        //return cp.getNickProponente();
        return listaNombres;
    }
    
    @Override
    public List<String> getSeguidos(String seguidor) {
//        List<String> listaNombres = new ArrayList<>();
//        for(Usuario u : this.misUsuarios){
//            if(u.getNickname().equals(seguidor)){
//                listaNombres = u.getSeguidos();
//                break;
//            }
//        }

//        persistencia
        List<String> listaNombres;
        Usuario usu = cp.buscarUsuario(seguidor);
        listaNombres = usu.getSeguidos();
        
        return listaNombres;
    }

    @Override
    public int seguirUsuario(String nick1, String nick2) {

        Usuario seguidor, seguir;
        seguidor = cp.buscarUsuario(nick1);
        seguir = cp.buscarUsuario(nick2);

        int resultado = seguidor.seguirUsuario(seguir);
        if (resultado == 0) {
            return 0; //error 0: ya sigue al usuario nick2
        }else{
            cp.editarUsuario(seguidor);
            return 1;
        }
    }
    
    @Override
    public int dejarSeguirUsuario(String nick1, String nick2){

        Usuario seguidor, seguir;
        seguidor = cp.buscarUsuario(nick1);
        seguir = cp.buscarUsuario(nick2);
        
        int res = seguidor.dejarDeSeguir(seguir);
        if(res == 1){
            //persistencia
            cp.editarUsuario(seguidor);
            return 1;
        }else{
            return 0; //error: no lo encontró
        }
    }
    
    @Override
    public int altaPropuesta(String nick, String tipo, String titulo, String descripcion, String lugar, LocalDate fechaPrev, String montoXentrada, String montoNecesario, EnumRetorno posibleRetorno, LocalDate fechaActual, String imagen){
        if (existeTitulo(titulo)) {
            return -1;
        }
        
        Proponente prop = cp.buscarProponente(nick);
        
        Categoria c  = cp.findCategoria(tipo);
        
        Propuesta nuevaProp = new Propuesta(c, prop, titulo, descripcion, lugar, fechaPrev, Double.parseDouble(montoXentrada), Double.parseDouble(montoNecesario), posibleRetorno, fechaActual, imagen);
//        misPropuestas.add(nuevaProp);
          cp.añadirEstado(nuevaProp.getEstadoActual());
          cp.añadirPropuesta(nuevaProp);
            //Agregar propuesta a esa categoria directamente lo hare con persistencia antes seria c.agregarPropuesta(nuevaProp);
        return 1;
    }
    
    @Override //fechas con string
    public int altaPropuesta(String nick, String tipo, String titulo, String descripcion, String lugar, String fechaP, String montoXentrada, String montoNecesario, EnumRetorno posibleRetorno, String fechaA, String imagen){
        if (existeTitulo(titulo)) {
            return -1;
        }
        
        Proponente prop = cp.buscarProponente(nick);
        
        Categoria c  = cp.findCategoria(tipo);
        
        LocalDate fechaPrev = LocalDate.parse(fechaP);
        LocalDate fechaActual;
        if(fechaA.equals("fechaActual")){
            fechaActual = LocalDate.now();
        }else{
            fechaActual = LocalDate.parse(fechaA);
        }
        
        
        Propuesta nuevaProp = new Propuesta(c, prop, titulo, descripcion, lugar, fechaPrev, Double.parseDouble(montoXentrada), Double.parseDouble(montoNecesario), posibleRetorno, fechaActual, imagen);
//        misPropuestas.add(nuevaProp);
          cp.añadirEstado(nuevaProp.getEstadoActual());
          cp.añadirPropuesta(nuevaProp);
            //Agregar propuesta a esa categoria directamente lo hare con persistencia antes seria c.agregarPropuesta(nuevaProp);
        return 1;
    }
   
    @Override
    public int cambiarEstadoPropuesta(String titulo, String estado) {
        Propuesta p = cp.getPropuesta(titulo);

        p.modificarPropuesta(p.getDescripcion(), p.getLugar(), p.getFechaARealizar(), p.getEntrada(), p.getNecesaria(), p.getPosibleRetorno().toString(), estado, p.getImagen(), p.getCategoriaClase());
        cp.modificarPropuesta(p);
        cp.editarPropuesta(p);

        return 0;
    }
    
    @Override
    public int modificarPropuesta(String titulo, String descripcion, String lugar, LocalDate fechaPrev, String montoXentrada, String montoNecesario, String posibleRetorno, String estado, String imagen, String categoria){
        
//        for(Propuesta p : this.misPropuestas){
//            if(p.getTitulo().equals(titulo)){
//                Categoria c = cp.findCategoria(categoria);
//                //Ya se busca directamente en la BD el arbol categoria no tendra los
//                 //datos
//                
//                //Quitar esta propuesta de la categoria que la apuntaba (por el caso de cambio de categoria) hacerlo directo con persistencia
//                p.modificarPropuesta(descripcion, lugar, fechaPrev, Double.parseDouble(montoXentrada),Double.parseDouble(montoNecesario), posibleRetorno, estado, imagen, c);
//                //Agregar propuesta a esa categoria directamente lo hare con persistencia antes seria c.agregarPropuesta(nuevaProp);
//                return 0;
//            }
//        }
//        return 1; //error 1: no deberia llegar acá

///////////////persistencia/////////////////
        
        Propuesta p = cp.getPropuesta(titulo);
        Categoria c = cp.findCategoria(categoria);
        //Ya se busca directamente en la BD el arbol categoria no tendra los
        //datos
        boolean seCambioCat = false;
        //Quitar esta propuesta de la categoria que la apuntaba (por el caso de cambio de categoria) hacerlo directo con persistencia
        if(!p.getCategoria().equals(c.getNombre())){ //p.getCategoria no anda
//            //Quitar esta propuesta de la categoria que la apuntaba
              Categoria viejaCat = p.getCategoriaClase(); //Aca saco la categoria que tenia antes ya que aun no se modifico
              viejaCat.sacarPropuesta(p); //La saco de su lista de propuestas
              cp.editarCategoria(viejaCat); //mando el edit para reflejar cambios en BD
              seCambioCat = true;
        }
        p.modificarPropuesta(descripcion, lugar, fechaPrev, Double.parseDouble(montoXentrada), Double.parseDouble(montoNecesario), posibleRetorno, estado, imagen, c);
        cp.modificarPropuesta(p);
        
        //Agregar propuesta a esa categoria directamente lo hare con persistencia antes seria c.agregarPropuesta(nuevaProp);
        if(seCambioCat){
            c.agregarPropuesta(p); //Si se cambio entonces la nuevaCat o bueno "c" debe agregar esta propuesta a su lista
            cp.editarCategoria(c); //Y reflejarlo en la BD
        }
        return 0;
    }
    
    @Override
    public List<String> getPropuestas() {
//        List<String> listaPropuestas = new ArrayList<>();
//        String aux;
//        for(Propuesta p : misPropuestas){
//            aux = p.getTitulo();
//            listaPropuestas.add(aux);
//        }
//        return listaPropuestas;
          

          //PERSISTENCIA
          List<String> listaPropuestas = new ArrayList<>();
          String aux;
          for (Propuesta p : cp.getListaPropuestas()) {
              aux = p.getTitulo();
              if(p.isProponenteActivo()){
                listaPropuestas.add(aux); 
              }
            }
          return listaPropuestas;
    }

    public List<String> getPropuestasI() {
        List<String> listaPropuestas = new ArrayList<>();
        String aux;
        for (Propuesta p : cp.getListaPropuestas()) {
            if (p.getEstadoActual().getEstado().toString().equals("INGRESADA") && p.isProponenteActivo()) {
                aux = p.getTitulo();
                listaPropuestas.add(aux);
            }
        }
        return listaPropuestas;
    }
        
    @Override
    public DataPropuesta consultaDePropuesta(String titulo){
        
//        DataPropuesta DP = null;
        
//        boolean encontrado = false;
//        for (Propuesta p : misPropuestas) {
//            if (p.getTitulo().equalsIgnoreCase(titulo)) {
//                encontrado = true;
//                DP = new DataPropuesta(titulo, p.getImagen(), p.getEstadoActual(), p.getProponente(), p.getDescripcion(), p.getLugar(), p.getEntrada(), p.getNecesaria(),p.getAlcanzada() , p.getFechaARealizar(), p.getRetorno(), p.getCategoria());
//                return DP;
//            }
//        }
//       return DP;

        //persistencia
        Propuesta p = cp.getPropuesta(titulo);
        Proponente propo = p.getProponente();
        List<DataAporte> aportesP = new ArrayList<>();
    for (Aporte a : p.getAportes()) {
        aportesP.add(new DataAporte(
            a.getAporte(),
            a.getFechaHora(),
            a.getCantidad(),
            a.getRetorno(),
            a.getNicknameMiColaborador(),
            a.getTituloMiPropuesta(),
            a.getImagenMiPropuesta(),
            a.getNecesaria()
        ));
    }

    // ---- Convertir todas las propuestas del proponente ----
    List<DataPropuesta> propuestasProponente = new ArrayList<>();

    if (propo != null) {
        for (Propuesta x : propo.getPropuestas()) {

            // lista propia por propuesta
            List<DataAporte> aportesX = new ArrayList<>();
            for (Aporte ax : x.getAportes()) {
                aportesX.add(new DataAporte(
                    ax.getAporte(),
                    ax.getFechaHora(),
                    ax.getCantidad(),
                    ax.getRetorno(),
                    ax.getNicknameMiColaborador(),
                    ax.getTituloMiPropuesta(),
                    ax.getImagenMiPropuesta(),
                    ax.getNecesaria()
                ));
            }

            propuestasProponente.add(new DataPropuesta(
                x.getTitulo(),
                x.getImagen(),
                x.getEstadoActual(),
                x.getDescripcion(),
                x.getLugar(),
                x.getEntrada(),
                x.getNecesaria(),
                x.getAlcanzada(),
                x.getFecha(),
                x.getFechaARealizar(),
                x.getFechaLimit(),
                x.getRetorno(),
                x.getCategoria(),
                aportesX
            ));
        }
    }

    // ---- DataProponente ----
    DataProponente dp = null;
    if (propo != null) {
        dp = new DataProponente(
            propo.getNickname(),
            propo.getNombre(),
            propo.getApellido(),
            propo.getEmail(),
            propo.getFecNac(),
            propo.getImagen(),
            propo.getDireccion(),
            propo.getBiografia(),
            propo.getSitioWeb(),
            propuestasProponente
        );
    }

    // ---- DataPropuesta principal ----
    DataPropuesta respuesta = new DataPropuesta(
        p.getTitulo(),
        p.getImagen(),
        p.getEstadoActual(),
        dp,
        p.getDescripcion(),
        p.getLugar(),
        p.getEntrada(),
        p.getNecesaria(),
        p.getAlcanzada(),
        p.getFecha(),
        p.getFechaARealizar(),
        p.getFechaLimit(),
        p.getRetorno(),
        p.getCategoria(),
        aportesP
    );
    respuesta.setCantidadFav(p.getCantidadFav());
    respuesta.setProponenteActivo(propo.isActivo());
    return respuesta;
}
    
    @Override
    public DataPropuesta getDataPropuesta(String titulo_nick){
        //CON MEMORIA LOCAL
//        DataPropuesta DP = null;
//        for (Propuesta p : misPropuestas) {
//            if (p.getTitulo_Nickname().equalsIgnoreCase(titulo_nick)) {
//                DP = new DataPropuesta(p.getTitulo(), p.getImagen(), p.getEstadoActual(), p.getProponente(), p.getDescripcion(), p.getLugar(), p.getEntrada(), p.getNecesaria(),p.getmontoAlcanzada(), p.getFechaARealizar(), p.getRetorno(), p.getCategoria());
//                return DP;
//            }
//        }
//        return DP;
        
        //CON PERSISTENCIA
        DataPropuesta DP = null;
        for (Propuesta p : cp.getListaPropuestas()) {
            if (p.getTitulo_Nickname().equalsIgnoreCase(titulo_nick)) {
                Proponente propo = p.getProponente();
                List<DataPropuesta> LDP = new ArrayList<>();
                List<DataAporte> LDA = new ArrayList<>();

                for (Aporte a : p.getAportes()) {
                    DataAporte da = new DataAporte(a.getAporte(), a.getFechaHora(), a.getCantidad(), a.getRetorno(), a.getNicknameMiColaborador(), a.getTituloMiPropuesta(), a.getImagenMiPropuesta(), a.getNecesaria());
                    LDA.add(da);
                }

                for (Propuesta x : propo.getPropuestas()) {
                    DataPropuesta z = new DataPropuesta(x.getTitulo(), x.getImagen(), x.getEstadoActual(), x.getDescripcion(), x.getLugar(), x.getEntrada(), x.getNecesaria(), x.getAlcanzada(), x.getFecha(), x.getFechaARealizar(), x.getFechaLimit(), x.getRetorno(), x.getCategoria(), LDA);
                    LDP.add(z);
                }
                DataProponente dp = new DataProponente(propo.getNickname(), propo.getNombre(),propo.getApellido(),propo.getEmail(),propo.getFecNac(),propo.getImagen(),propo.getDireccion(),propo.getBiografia(),propo.getSitioWeb(), LDP);
                DP = new DataPropuesta(p.getTitulo(), p.getImagen(), p.getEstadoActual(), dp, p.getDescripcion(), p.getLugar(), p.getEntrada(), p.getNecesaria(),p.getmontoAlcanzada(),p.getFecha() ,p.getFechaARealizar(),p.getFechaLimit(), p.getRetorno(), p.getCategoria(), LDA);
                DP.setProponenteActivo(propo.isActivo());
                return DP;
            }
        }
        return DP;
        
    }
    
    @Override
    public DataPropuestaSimple getDataPropuestaSimple(String titulo){
        Propuesta p = cp.getPropuesta(titulo);        
        return new DataPropuestaSimple(p.getTitulo(),p.getDescripcion(),p.getLugar(),p.getEstadoActual().getEstado());
    }
    
    
    @Override
    public DataProponente consultaDeProponente(String NickName){
        
        DataProponente DProp = null;
        Proponente p = cp.buscarProponente(NickName);
                List<DataPropuesta> propuestasDe = new ArrayList<>();
                DataPropuesta dataProp;
                for(Propuesta prop : p.getPropuestas()){
                    dataProp = new DataPropuesta(prop.getAlcanzada(),prop.getTitulo(),prop.getEstadoActual(),prop.getLugar());
                    dataProp.setDesc(prop.getDescripcion());
                    dataProp.setImagen(prop.getImagen());
                    dataProp.setFechaPubli(prop.getFechaARealizar());
                    dataProp.setFechaLimit(prop.getFechaLimit()); //Sin esto seteado no se muestra correctamente cuantos dias le quedan a la propuesta en PerfilUsuario
                    dataProp.setFechaPubliStr(prop.getFechaARealizar().toString());
                    dataProp.setFechaLimitStr(prop.getFechaLimit().toString());
                    dataProp.setnecesaria(prop.getNecesaria()); //Sin esto no se muestra bien barra progreso de perfilUsuario con respecto a las Propuestas
                    dataProp.setCantidadColaboradores(prop.getAportes().size());
                    propuestasDe.add(dataProp);
                }
                DProp = new DataProponente(NickName, p.getNombre(),p.getApellido(),p.getEmail(),p.getFecNac(),p.getImagen(),p.getDireccion(),p.getBiografia(),p.getSitioWeb(),propuestasDe);
                return DProp;
    }
    
    @Override
    public DataColaborador consultaDeColaborador(String NickName){
        
        DataColaborador DCola = null;
        Colaborador c = cp.buscarColaborador(NickName);
        DCola = new DataColaborador(NickName, c.getNombre(),c.getApellido(),c.getEmail(),c.getFecNac(),c.getImagen(),c.getPropuestas());
        DCola.setMisAportes(c.getListaAportes());
        DCola.setLosSigo(c.getDtUSeguidos());
        return DCola;
        
    }
    
    @Override
    public DataColaborador getDataColaborador(String nickname,String nombre, String apellido,String email,String fecNac, String imagen){
        return new DataColaborador(nickname, nombre, apellido, email, LocalDate.parse(fecNac), imagen);
    }
    
    @Override
    public DataColaborador getDataColaboradorWeb(String nickname, String imagen){
        return new DataColaborador(nickname, imagen);
    }
    
    public List<DataUsuario> getSeguidores(Usuario seguido) {
        List<DataUsuario> seguidores = new ArrayList<>();

        for (Usuario u : cp.getListaUsuarios()) {
            if (u.getMisSeguidosNick().contains(seguido.getNickname())) {
                DataUsuario du = new DataUsuario();
                du.setNickname(u.getNickname());
                 if (u instanceof Proponente){
                  du.setTipo("Proponente");
                }else if (u instanceof Colaborador){
                  du.setTipo("Colaborador");
                }
                du.setImagen(u.getImagenWeb());
                seguidores.add(du);
            }
        }
        return seguidores;
    }

    @Override
    public DataUsuario consultaDeProponenteWeb(String NickName){
        
        DataProponente DProp = consultaDeProponente(NickName);
        Proponente p = cp.buscarProponente(NickName);
                List<DataPropuesta> propuestasDe = new ArrayList<>();
                List<DataPropuesta> propuestasFiltradas = new ArrayList<>();
                List<DataPropuesta> propuestasIngresadas = new ArrayList<>();
                for(DataPropuesta prop : DProp.getPropuestas()){
                    if(!"Ingresada".equalsIgnoreCase(prop.getEstadoActual().getEstado().toString())) {
                        propuestasFiltradas.add(prop);
                    }else{
                        propuestasIngresadas.add(prop);
                    }
                }           
                
                DataUsuario usuario = new DataUsuario(DProp.getNickname(),DProp.getNombre(),DProp.getApellido(),"Proponente",propuestasFiltradas,this.getSeguidores(p),p.getDtUSeguidos());
                usuario.setEmail(DProp.getEmail());
                usuario.setImagen(p.getImagenWeb());
                usuario.setDireccion(DProp.getDireccion());
                usuario.setBiografia(DProp.getBiografia());
                usuario.setSitioWeb(DProp.getSitioWeb());
                usuario.setMisPropuestasIngresadas(propuestasIngresadas);
                usuario.setMisPropuestasFav(p.getMisFavoritasData());
                
                return usuario;
    }
    
    @Override
    public DataUsuario consultaDeColaboradorWeb(String NickName){
        //Estas 2 funciones que terminan en Web toman los datos que me faltan y reutilizan los consultas ya diseñados anteriormente
        DataColaborador DCola = consultaDeColaborador(NickName);
        Colaborador c = cp.buscarColaborador(NickName);
        
        DataUsuario usuario = new DataUsuario(DCola.getNickname(),DCola.getNombre(),DCola.getApellido(),"Colaborador",DCola.getPropuestas(),this.getSeguidores(c),c.getDtUSeguidos());
        usuario.setEmail(DCola.getEmail());
        usuario.setImagen(c.getImagenWeb());
        
        usuario.setBiografia("");
        
        usuario.setDireccion("");
        
        usuario.setSitioWeb("");
        
        usuario.setMisPropuestasFav(c.getMisFavoritasData());
        
        if(usuario.getTipo().equals("Colaborador")){
            usuario.setListaAporte(c.getListaAportes());
        }else{
            usuario.setListaAporte(null);
        }
        return usuario;
        
    }
    
    public List<DataCategoria> cargarCategoriasWeb(){
        List<Categoria> todas = cp.listarCategorias();
        List<DataCategoria> ListaDataCat= new ArrayList<>();
        String padreDefault = null;
        for (Categoria cat : todas){
            String padre;
            if(cat.getPadre()!= null){
                padre = cat.getPadre().getNombre();
            }else{
                padre = padreDefault;
            }
            DataCategoria data = new DataCategoria(cat.getNombre(),padre);
            ListaDataCat.add(data);
        }

        return ListaDataCat; 
    }
    
    public List<String> getCategorias(){
        List<Categoria> todas = cp.listarCategorias();
        List<String> ListaCat= new ArrayList<>();
        for (Categoria cat : todas){
            
            if(!cat.getNombre().equalsIgnoreCase("Categoria")){
                ListaCat.add(cat.getNombre());
            }
        }

        return ListaCat; 
    }
    
    
    @Override
    public List<String> getEstados(){
    List<String> listaEstados = new ArrayList<>();
    for (EnumEstado e : EnumEstado.values()) {
        listaEstados.add(e.name());
    }
    return listaEstados;
    }
    
        @Override
    public List<String> getPropXEstado(String estado){
//        List<String> listaPropuestas = new ArrayList<>();
//        String aux;
//        for(Propuesta p : misPropuestas){
//            aux = p.getTitulo();
//            if(p.getEstadoActual().getEstado().toString().equalsIgnoreCase(estado)){
//                listaPropuestas.add(aux);
//            }
//        }
//        return listaPropuestas;
        
        //PERSISTENCIA
        
        List<String> listaPropuestas = new ArrayList<>();
        String aux;
        for(Propuesta p : cp.getListaPropuestas()){
            aux = p.getTitulo();
            if(p.getEstadoActual().getEstado().toString().equalsIgnoreCase(estado) && p.isProponenteActivo()){
                listaPropuestas.add(aux);
            }
        }
        return listaPropuestas;
    }
    
    
    @Override
    public List<String> getPropuestasXColaborador(String colab){
        //CON MEMEORIA LOCAL
//        for(Colaborador c : this.misColaboradores){
//            if(colab.equals(c.getNickname())){
//                return c.getTituloPropuestas();
//            }
//        }   
//        return null;
        
        //CON PERSISTENCIA
        for(Colaborador c : cp.getColaboradores()){
            if(colab.equals(c.getNickname())){
                return c.getTituloPropuestas();
            }
        }   
        return null;
    }
    
    @Override
    public DataAporte getDataAporte(String tituloNick, String nick){
        //CON MEMEORIA LOCAL
//        for(Colaborador c : misColaboradores){
//            if(nick.equals(c.getNickname())){
//                return c.getDataAporte(tituloNick);
//            }
//        }
//        return null;
        
        //CON PERSISTENCIA
        for(Colaborador c : cp.getColaboradores()){
            if(nick.equals(c.getNickname())){
                return c.getDataAporte(tituloNick);
            }
        }
        return null;
    }
    
    @Override
    public void borrarAporte(String tituloNick, String nick){
        //CON MEMEORIA LOCAL
//        for(Colaborador c : misColaboradores){
//            if(nick.equals(c.getNickname())){
//                c.borrarAporte(tituloNick);
//                break;
//            }
//        }
        
        //CON PERSISTENCIA
        for(Colaborador c : cp.getColaboradores()){
            if(nick.equals(c.getNickname())){
                Aporte a = c.borrarAporte(tituloNick);
                 try {
                    cp.borrarAporte(a,a.getPropuestaP(),c);  
                } catch (Exception ex) {
                    System.getLogger(Controlador.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
                break;
            }
        }
    }
    
    @Override
    public boolean existeTitulo(String titulo){
        
        boolean encontrado = false;
        for (Propuesta p : cp.getListaPropuestas()) {
            if (p.getTitulo().equalsIgnoreCase(titulo)) {
                encontrado = true;
            }
        }
        
        return encontrado;
    }
    
    @Override
    public boolean existeNick(String nick){
        for (Usuario u : cp.getListaUsuarios()){
            if(u.getNickname().equals(nick)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean existeCorreo(String correo){
        for (Usuario u : cp.getListaUsuarios()){
            if(u.getEmail().equals(correo)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public List<String> getColabsProp(String titulo){
//        List<String> listaColabProp = new ArrayList<>();
//        Propuesta prop = null;
//        double aporte$;
//        Colaborador c = null;
//        String aporteColab;
//        
//        for (Propuesta p : misPropuestas) {
//            if (p.getTitulo().equalsIgnoreCase(titulo)) {
//                prop = p;
//            }
//        } // pa encontrar la propuesta
//        
//        if (prop == null) {
//            return listaColabProp;
//        }
//        
//        for (Aporte a : prop.getAportes()) {
//            aporte$ = a.get$aporte();
//            c = a.getColaborador();
//            
//            aporteColab = c.getNickname() + "\t" + aporte$;
//            listaColabProp.add(aporteColab);
//        }
//        
//        return listaColabProp;
        
        //PERSISTENCIA
        
        List<String> listaColabProp = new ArrayList<>();
        Propuesta prop = cp.getPropuesta(titulo);
        double aporte;
        Colaborador c;
        String aporteColab;
        
        for (Aporte a : prop.getAportes()) {
            aporte = a.getAporte();
            c = a.getColaborador();
            
            aporteColab = c.getNickname() + "\t" + aporte;
            listaColabProp.add(aporteColab);
        }
        
        return listaColabProp;
    }
    
    @Override
    public boolean seleccionaCategoria(String categoria){
        boolean encontrado = false;
        
        for (Categoria c : cp.listarCategorias()) {
            if (c.getNombre().equalsIgnoreCase(categoria)) {
                encontrado = true;
            }
        }
        
        
        return encontrado;
    }
    
    @Override
    public int verificarUsuario(String usuario, String contraseña){
        List<Usuario> listaUsuarios = cp.getListaUsuarios();
        if(listaUsuarios != null){
            for(Usuario u : listaUsuarios){
                if(usuario.equals(u.getNickname())){
                    if(BCrypt.checkpw(contraseña, u.getContraseña())){
                        if(u instanceof Proponente ){
                            Proponente p = (Proponente)u;
                            if(!p.isActivo()){
                                return -1;
                            }
                        }
                        return 1;
                    }
                }
                if(usuario.equals(u.getEmail())){
                    if(BCrypt.checkpw(contraseña, u.getContraseña())){
                        if(u instanceof Proponente ){
                            Proponente p = (Proponente)u;
                            if(!p.isActivo()){
                                return -1;
                            }
                        }
                        return 2;
                    }
                }
            }
        }
        return 0;
    }
    
    @Override
    public String getUsuarioPorMail(String email){
        List<Usuario> listaUsuarios = cp.getListaUsuarios();
        for(Usuario u : listaUsuarios){
            if(u.getEmail().equals(email)){
                return u.getNickname();
            }
        }
        return null;
    }
   
    @Override
    public void cambiarEstado(String titulo, int n){
        Propuesta prop = cp.getPropuesta(titulo);
        //LocalDate fec = LocalDate.now();
        EnumEstado est;
        if (n == 0) {
            est = EnumEstado.valueOf("PUBLICADA");
        }else{
            est = EnumEstado.valueOf("CANCELADA");
        }
        
        prop.actualizarEstadoActual(est);
        cp.editarPropuesta(prop);
    }
        
    @Override
    public void eliminarUsuario(String usu){
        cp.eliminarUsuario(usu);
    }
    
    @Override
    public boolean esFavorita(String titulo, String nick){
        Usuario u = cp.buscarUsuario(nick);
        Propuesta p = cp.getPropuesta(titulo);
        
        return u.esFavorita(p);
    }
    
    @Override
    public boolean cambiarFavorita(String titulo, String nick){
        Usuario u = cp.buscarUsuario(nick);
        Propuesta p = cp.getPropuesta(titulo);
        
        if(!(u.esFavorita(p))){
            p.setCantidadFav(p.getCantidadFav() + 1);
            u.addFavorita(p);
            cp.editarUsuario(u);
            cp.editarPropuesta(p);
            return true;
        }else{
            p.setCantidadFav(p.getCantidadFav() - 1);
            u.getMisFavoritas().removeIf(prop -> prop.getTitulo().equals(p.getTitulo()));
            cp.editarUsuario(u);
            cp.editarPropuesta(p);
            return false;
        }
    }
    
    @Override
    public void addComentario(String titulo, String nick, String comentario){
        Colaborador c = cp.buscarColaborador(nick);
        //System.out.println(titulo);
        Aporte a = c.getAporteXtitulo(titulo);
        a.setComentario(comentario);
        a.setFecComentario(LocalDateTime.now());
        cp.editarAporte(a);
    }
    
    
    @Override
    public DataComentario getDataComentario(String titulo, String nick){
        Colaborador c = cp.buscarColaborador(nick);
        //List<Aporte> aps = cp.getAportes();
        Aporte a = c.getAporteXtitulo(titulo);
                    
        return new DataComentario(a.getComentario(),a.getFecComentario(),nick,titulo);               
    }  
    
    @Override
    public List<DataComentario> getDataComentarios(String titulo){
        Propuesta propu = cp.getPropuesta(titulo);
        List<DataComentario> lista = new ArrayList();
        for(Aporte a : propu.getAportes()){
            DataComentario dc = this.getDataComentario(titulo, a.getNicknameMiColaborador());
            if (!(dc.getComentario()==null)){
            lista.add(dc);    
            }            
        }
        return lista;
    }
    
    @Override
    public int extenderFinanciacion(String titulo){
        Propuesta propu = cp.getPropuesta(titulo);
        if(propu.getEstadoActual().getEstado()==EnumEstado.EN_FINANCIACION || propu.getEstadoActual().getEstado()==EnumEstado.PUBLICADA){
            propu.setFechaLimit(LocalDateTime.now().plusDays(30));
            cp.modificarPropuesta(propu);
            return 0;
        }else{
            return -1;
        }
    }
    
    @Override
    public int cancelarPropuesta(String titulo){
        Propuesta propu = cp.getPropuesta(titulo);
        if(propu.getEstadoActual().getEstado()==EnumEstado.FINANCIADA){
            propu.actualizarEstadoActual(EnumEstado.CANCELADA);
            cp.modificarPropuesta(propu);
            return 0;
        }else{
            return -1;
        }
    }
    
    @Override
    public void comprobarPropuestas(){
        List<Propuesta> props = cp.getListaPropuestas();
        for (Propuesta p : props) {
            if (p.getFechaLimit().equals(LocalDateTime.now()) || p.getFechaLimit().isBefore(LocalDateTime.now()) || p.getFechaARealizar().isBefore(LocalDate.now())) {
                if (p.getEstadoActual().getEstado().equals(EnumEstado.PUBLICADA)) {
                    p.actualizarEstadoActual(EnumEstado.NO_FINANCIADA);
                    cp.editarPropuesta(p);
                }else if(p.getEstadoActual().getEstado().equals(EnumEstado.EN_FINANCIACION)){
                    if (p.getAlcanzada() >= p.getNecesaria()) {
                        p.actualizarEstadoActual(EnumEstado.FINANCIADA);
                    }else{
                        p.actualizarEstadoActual(EnumEstado.NO_FINANCIADA);
                    }
                    cp.editarPropuesta(p);
                }
            }
        }
    }
    
    @Override
    public DataAporte getDataAporte(double aporte, String fechaHora, String miPropuesta){ //NO USAR
        return new DataAporte(aporte, LocalDateTime.parse(fechaHora), miPropuesta);
    }

    @Override
    public void eliminarProponente(String nick) {
        Proponente propo = cp.buscarProponente(nick);
        propo.setActivo(false);
        for(Propuesta prop : propo.getPropuestas()){
            prop.setProponenteActivo(false);
            cp.editarPropuesta(prop);
        }
        // Borro todas las relaciones de seguidores/seguidos en usuarioseguidos usando un query
        cp.eliminarSeguidosDeProponente(nick);
        /*
        for(Usuario usu : propo.getMisSeguidos()){ Probo hacerlo de esta manera pero no funciona del todo y no es tan eficiente como usar un query 
            propo.dejarDeSeguir(usu); 
            cp.editarUsuario(usu); 
        } 
        Usuario seguidor; 
        for (DataUsuario dataU : this.getSeguidores(propo)){ 
            seguidor = cp.buscarUsuario(dataU.getNickname()); 
            seguidor.dejarDeSeguir(propo); 
            cp.editarUsuario(seguidor); 
        }*/
        
        cp.editarProponente(propo);
    }
    
    
    @Override
    public DataSugerencias getDataSugerencia(String titulo, int puntaje){
        DataSugerencias DS = new DataSugerencias(titulo, puntaje);
        return DS;
    }
    
    @Override
    public DataProponente getDataProponente(){
        DataProponente DP = new DataProponente();
        return DP;
    }
    
//    public List<DataAporte> getAportesColab(String nick){
//        for(Colaborador c: cp.getColaboradores()){
//            if (c.getNickname().equals(nick)) {
//                DataColaborador dc = this.consultaDeColaborador(nick);
//                //return c.getMisAportes();
//            }
//        }
//    }
    

    @Override
    public void setPagoT(String titular, String nick, String titulo,String numeroT, String fechaT, String CVC, EnumTarjeta enumTarjeta) {
        Pago P = new Pago(titular,EnumPago.TARJETA);
        P.setPagoT(numeroT, fechaT, CVC, enumTarjeta);
        Colaborador c = cp.buscarColaborador(nick);
        Aporte a = c.getAporteXtitulo(titulo);
        a.setPago(P);
        cp.añadirPago(P, a);
    }

    @Override
    public void setPagoB(String titular, String nick, String titulo,String nombreB, String numeroB) {
        Pago P = new Pago(titular,EnumPago.BANCO);
        P.setPagoB(nombreB, numeroB);
        Colaborador c = cp.buscarColaborador(nick);
        Aporte a = c.getAporteXtitulo(titulo);
        a.setPago(P);
        cp.añadirPago(P, a);
    }

    @Override
    public void setPagoP(String titular, String nick, String titulo,String numeroP){
        Pago P = new Pago(titular,EnumPago.PAYPAL);
        P.setPagoP(numeroP);
        Colaborador c = cp.buscarColaborador(nick);
        Aporte a = c.getAporteXtitulo(titulo);
        a.setPago(P);
        cp.añadirPago(P, a);
    }
    
    @Override
    public DataPago getDataPago(String nick, String titulo){
        Colaborador c = cp.buscarColaborador(nick);
        Aporte a = c.getAporteXtitulo(titulo);
        if(a!=null){
            return a.getDataPago();
        }
        return null;
    }
}    
