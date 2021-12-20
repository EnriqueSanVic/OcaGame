

package Controladores;

import Hilos.Hilo;
import Logicas.LogicaJuegoModo1;
import Vistas.VistaJuego;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Sánchez 
 */
public class ControladorJuego extends WindowAdapter implements ActionListener{

    private LogicaJuegoModo1 logica1;
    
    private VistaJuego vista;
    
    private List<Hilo> hilos;
    
    public ControladorJuego(){
        this.hilos = new ArrayList<Hilo>(); 
        this.vista = new VistaJuego(this);
        this.logica1 = new LogicaJuegoModo1();
      
        vista.initEscena();
        
        

        
    }
    
    
    public void aniadirHilo(Hilo hilo){
        this.hilos.add(hilo);
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

    @Override
    public void actionPerformed(ActionEvent ae) {
            switch (ae.getActionCommand()) {
                case "DROP DICE":   
                case "LANZAR DADO":
                    //Pido tirar el dado y que me de la cara/numero final.
                    int numeroFinal = logica1.tirarDado();
                    System.out.println("HA SALIDO EL: "+numeroFinal);
                    //Digo a la vista que hay un impulso
                    this.vista.setImpulsoTirarDado(true);
                    this.vista.setNumeroFinalDado(numeroFinal);
                    this.vista.notificarTiradaDado();
                    
                    
                break;
            default:
                break;
        }
            
    }

    public void eventoFinalizacionDado() {
        System.out.println("Evento animacion de dado finalizado.");
    }
}
    
    
    
    
