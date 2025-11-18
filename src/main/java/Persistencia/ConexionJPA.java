package Persistencia;

import Logica.ConfigManager;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexionJPA {
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEMF() {

        if (emf == null) {
            Map<String, Object> props = new HashMap<>();

            props.put("javax.persistence.jdbc.url", ConfigManager.getJdbcUrl());
            props.put("javax.persistence.jdbc.user", ConfigManager.getJdbcUser());
            props.put("javax.persistence.jdbc.password", ConfigManager.getJdbcPassword());

            emf = Persistence.createEntityManagerFactory("CulturartePU", props);
        }

        return emf;
    }
}
