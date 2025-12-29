package WebServices;

import Logica.ConfigManager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;

public class ImagenHttpServer {

    private HttpServer server;
    
    private Integer port;
    
    public ImagenHttpServer(Integer puerto){
        port = puerto;
    }
    
    public void iniciar() {
        try {
            String context = ConfigManager.getPhotosContext();
            server = HttpServer.create(new InetSocketAddress(port), 0);

            server.createContext(context, new ImagenHandler());

            server.setExecutor(null); // default
            server.start();

            System.out.println("Servidor de imágenes iniciado en:");
            System.out.println("http://0.0.0.0:9129/imagenes/{nombre}");

        } catch (IOException e) {
            System.err.println("Error iniciando servidor de imágenes: " + e.getMessage());
        }
    }

    static class ImagenHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String path = exchange.getRequestURI().getPath();
            String nombre = path.replace("/imagenes/", "");

            if (nombre.isEmpty() || nombre.contains("..") || nombre.contains("/")) {
                exchange.sendResponseHeaders(400, -1);
                return;
            }

            File img = new File(ConfigManager.getPhotosPath(), nombre);

            if (!img.exists()) {
                exchange.sendResponseHeaders(404, -1);
                return;
            }

            String contentType = Files.probeContentType(img.toPath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            exchange.getResponseHeaders().add("Content-Type", contentType);
            exchange.sendResponseHeaders(200, img.length());

            OutputStream os = exchange.getResponseBody();
            Files.copy(img.toPath(), os);
            os.close();
        }
    }
}
