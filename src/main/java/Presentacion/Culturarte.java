package Presentacion;

import Interfaces.Fabrica;
import Interfaces.IControlador;
import WebServices.LogicaWS;

public class Culturarte {

    public static void main(String[] args) {
        
        //descomentar y eliminar Publicador.java cuando pasemos al cure
        LogicaWS ws = new LogicaWS();
        ws.publicar();
        
        //entrar a http://localhost:9128/logicaWS?wsdl para ver
        
        IControlador ic = Fabrica.getInstancia().getIControlador();
                
        VentanaPrincipal ventana = new VentanaPrincipal(ic);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        
    }
}
