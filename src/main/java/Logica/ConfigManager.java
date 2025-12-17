package Logica;

import java.io.*;
import java.nio.file.*;
import java.util.Properties;

public class ConfigManager {
    
    private static final String CONFIG_DIR = System.getProperty("user.dir") + "/CulturarteConfig";
    private static final String CONFIG_FILE = CONFIG_DIR + "/config.properties";
    
    private static Properties props = new Properties();
    
    static {
        try {
            inicializarConfig();
        } catch (IOException e) {
            System.err.println("Error inicializando configuración: " + e.getMessage());
        }
    }

    // ============================
    //   INICIALIZACIÓN
    // ============================
    public static void inicializarConfig() throws IOException {
        Path dirPath = Paths.get(CONFIG_DIR);
        Path filePath = Paths.get(CONFIG_FILE);
        
        System.out.println(dirPath.toString());
        // Crear carpeta si no existe
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
            System.out.println("Carpeta creada: " + CONFIG_DIR);
        }

        // Crear archivo config por defecto si no existe
        if (!Files.exists(filePath)) {
            crearArchivoPorDefecto(filePath);
            System.out.println("Archivo config.properties creado con valores por defecto.");
        }

        // Cargar archivo existente
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            props.load(input);
        }
    }

    // ============================
    //   VALORES POR DEFECTO
    // ============================
    private static void crearArchivoPorDefecto(Path filePath) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("=== Configuración inicial de Culturarte ===");
        System.out.println("Seleccione modo de publicación del Web Service:");
        System.out.println("1 - Solo local (localhost)");
        System.out.println("2 - Toda la red (0.0.0.0)");
        System.out.print("Opción: ");

        String opcion = br.readLine();
        String host = "localhost";

        switch (opcion) {
            case "2":
                host = "0.0.0.0";
                break;
            default:
                host = "localhost";
        }
        
        Properties defaultProps = new Properties();
        
        //Base de Datos
        defaultProps.setProperty("jdbc.url", "jdbc:mysql://localhost:3306/culturarte?zeroDateTimeBehavior=CONVERT_TO_NULL");
        defaultProps.setProperty("jdbc.user", "tecnologo");
        defaultProps.setProperty("jdbc.password", "tecnologo");
        
        //Trello
        defaultProps.setProperty("trello.key", "");
        defaultProps.setProperty("trello.token", "");
        defaultProps.setProperty("trello.api_base", "");
        
        //Web service
        defaultProps.setProperty("ws.host", host);
        defaultProps.setProperty("ws.port", "9128");
        defaultProps.setProperty("ws.context", "/logicaWS");
        
        //Carpeta de fotos
        defaultProps.setProperty("photos.path", (String) System.getProperty("user.dir") + File.separator + "fotos" + File.separator);

        //Puerto de fotos 
        defaultProps.setProperty("photos.port","9129");
        
        try (OutputStream output = new FileOutputStream(filePath.toFile())) {
            defaultProps.store(output, "Archivo de configuración de Culturarte");
        }
        System.out.println("Configuración creada correctamente.");
    }
    
    public static boolean validarConfig() {
        boolean ok = true;

        System.out.println("Validando configuración...");

        ok &= validarObligatoria("jdbc.url");
        ok &= validarObligatoria("jdbc.user");
        ok &= validarObligatoria("jdbc.password");

        validarOpcional("trello.key");
        validarOpcional("trello.token");
        validarOpcional("trello.api_base");
        validarOpcional("photos.path");
        validarOpcional("photos.port");
        
        if (ok) {
            System.out.println("Configuración OK [OK]️");
        }

        return ok;
    }

    private static boolean validarObligatoria(String key) {
        String value = props.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            System.err.println("[ERROR] Falta configuración obligatoria: " + key);
            return false;
        }
        return true;
    }

    private static void validarOpcional(String key) {
        String value = props.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            System.out.println("[WARN]️ Configuración opcional no definida: " + key);
        }
    }

    
    
    // ============================
    //   GETTERS
    // ============================
    public static String get(String key) {
        return props.getProperty(key);
    }

    public static String getJdbcUrl() {
        return props.getProperty("jdbc.url");
    }

    public static String getJdbcUser() {
        return props.getProperty("jdbc.user");
    }

    public static String getJdbcPassword() {
        return props.getProperty("jdbc.password");
    }

    public static String getTrelloKey() {
        return props.getProperty("trello.key");
    }
    
    public static String getTrelloToken() {
        return props.getProperty("trello.token");
    }
    
    public static String getTrelloBase() {
        return props.getProperty("trello.api_base");
    }
    public static String getPhotosPath() {
        return props.getProperty("photos.path");
    }
    public static String getWsHost() {
        return props.getProperty("ws.host");
    }

    public static int getWsPort() {
        return Integer.parseInt(props.getProperty("ws.port"));
    }

    public static String getWsContext() {
        return props.getProperty("ws.context");
    }
    public static Integer getPhotosPort() {
        String port = props.getProperty("photos.port");
        if (port == null || port.isBlank()) {
            return null;
        }
        return Integer.parseInt(port);
    }
}
