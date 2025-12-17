package Presentacion;

import Interfaces.Fabrica;
import Interfaces.IControlador;
import Logica.ConfigManager;
import WebServices.ImagenHttpServer;
import WebServices.LogicaWS;
import java.io.File;

public class Culturarte {

    public static void main(String[] args) {
      
        //Inicializa config (se crea si no existe)  
        ConfigManager.getJdbcUrl();
        
        if (!ConfigManager.validarConfig()) {
            System.err.println("Error en configuración. Revise el archivo config.properties.");
            System.exit(1);
        }
        
        IControlador ic = Fabrica.getInstancia().getIControlador();
        
        //Creo la carpeta de fotos si no existe
        File carpetaDestino = new File(ic.getPhotosSCPath());
        if (!carpetaDestino.exists()) {
          carpetaDestino.mkdirs();
        }
        
        //Publica el Web Service
        String host = ConfigManager.getWsHost();
        int port = ConfigManager.getWsPort();
        String context = ConfigManager.getWsContext();
        
        //Publica Web Service (Logica)
        LogicaWS ws = new LogicaWS(host,port,context);
        ws.publicar();
        
        System.out.println("Web Service publicado en:");
        System.out.println("http://" + host + ":" + port + context + "?wsdl");
        
        
        // Publicar servidor de imágenes
        Integer fotosPort = ConfigManager.getPhotosPort();
        if (fotosPort != null) {
            ImagenHttpServer imgServer = new ImagenHttpServer(fotosPort);
            imgServer.iniciar();
            
        }
        
        //UI
        VentanaPrincipal ventana = new VentanaPrincipal(ic);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        
    }
}
