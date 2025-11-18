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

        Properties defaultProps = new Properties();
        defaultProps.setProperty("jdbc.url", "jdbc:mysql://localhost:3306/culturarte?zeroDateTimeBehavior=CONVERT_TO_NULL");
        defaultProps.setProperty("jdbc.user", "tecnologo");
        defaultProps.setProperty("jdbc.password", "tecnologo");
        defaultProps.setProperty("trello.key", "");
        defaultProps.setProperty("trello.token", "");
        defaultProps.setProperty("trello.api_base", "");



        try (OutputStream output = new FileOutputStream(filePath.toFile())) {
            defaultProps.store(output, "Archivo de configuración de Culturarte");
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
}
