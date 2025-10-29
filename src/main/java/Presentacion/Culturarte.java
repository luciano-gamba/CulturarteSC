package Presentacion;

import Interfaces.Fabrica;
import Interfaces.IControlador;

public class Culturarte {

    public static void main(String[] args) {
        
//        Fabrica fb = new Fabrica();
        IControlador ic = Fabrica.getInstancia().getIControlador();
                
        VentanaPrincipal ventana = new VentanaPrincipal(ic);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        
    }
}
