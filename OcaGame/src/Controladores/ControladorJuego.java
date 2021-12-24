

package Controladores;

import DatosEstaticos.Constantes;
import Hilos.Hilo;
import Logicas.LogicaJuego;
import Vistas.TableroSystem.NotificableTablero;
import Vistas.VistaInstrucciones;
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
public abstract class ControladorJuego extends WindowAdapter implements ActionListener, NotificableTablero{

    protected LogicaJuego logica;
    
    protected VistaJuego vista;
    
    protected List<Hilo> hilos;
    
    protected int ultimoNumeroDado;
    
    public ControladorJuego(){
        this.hilos = new ArrayList<Hilo>(); 

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
                case Constantes.LANZAR_DADO_COMMAND:
                    lanzarDado();
                break;
                //BOTON FOURNIER.
                case Constantes.ABRIR_INSTRUCCIONES_COMMAND:
                    //Lanza la vista Instrucciones.
                    new VistaInstrucciones(vista);
                break;
            default:
                break;
        }
            
    }

    
    private void lanzarDado() {
        
        if(sePuedeTirarDado()){
        
            //Pido tirar el dado y que me de la cara/numero final.
            ultimoNumeroDado = logica.tirarDado();
            //System.out.println("HA SALIDO EL: "+numeroFinal);
            //Digo a la vista que hay un impulso
            this.vista.setImpulsoTirarDado(true);
            this.vista.setNumeroFinalDado(ultimoNumeroDado);
            this.vista.notificarTiradaDado();
        
        }
        
        
        
    }
    
    protected abstract boolean sePuedeTirarDado();
    
    protected abstract void iniciarPartida();

    public abstract void eventoFinalizacionDado();

    public abstract void eventoToquePuntero();
    
    public abstract void eventoFinalMovimientoFicha();
    
 
    
    
}
    
    
    
    
