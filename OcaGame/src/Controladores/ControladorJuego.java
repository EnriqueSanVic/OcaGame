

package Controladores;

import Hilos.Hilo;
import Vistas.VistaJuego;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Sánchez 
 */
public class ControladorJuego extends WindowAdapter{

    private VistaJuego vista;
    
    private List<Hilo> hilos;
    
    public ControladorJuego(){
        this.vista = new VistaJuego(this);
        this.hilos = new ArrayList<Hilo>();
        
        vista.initEscena();
    }
    
    
    //escuchador para el cierre de la ventana

    @Override
    public void windowClosing(WindowEvent we) {
        protocoloCierre();
    }
    
    
    
    //protocolo de cierre de todos los hilos
    public void protocoloCierre(){
        
       matarHilos();
       
       System.exit(0);
        
    }
    
    
    
    //metodo para matar todos los hilos del programa
    private void matarHilos(){
        for (Hilo i:hilos) {
            i.matar();
        }
    }
    
    
    
    
}
