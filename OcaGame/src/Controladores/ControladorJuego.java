

package Controladores;

import DatosEstaticos.Constantes;
import Hilos.Hilo;
import Hilos.RegistradorHilos;
import Logicas.LogicaJuego;
import ReproductorSonido.ReproductorSonido;
import Utilidades.UtilidadesGraficas;
import Utilidades.VentanaConCorrecion;
import Vistas.TableroSystem.NotificableTablero;
import Vistas.VistaInstrucciones;
import Vistas.VistaJuego;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Enrique Sánchez 
 */
public abstract class ControladorJuego extends WindowAdapter implements ActionListener, NotificableTablero, RegistradorHilos{

    private final int TIEMPO_DELAY_COMIENZO_MOVIMIENTO_AUTO = 400;
    
    protected LogicaJuego logica;
    
    protected VistaJuego vista;
    
    protected List<Hilo> hilos;
    
    private ReproductorSonido hiloMusical;
    
    protected int ultimoNumeroDado;
        
    public ControladorJuego(int idioma, String jugador1, String jugador2){
        this.hilos = new ArrayList<Hilo>();
        
        //Si se quieren monitorizar los hilos activos en cada momento llamar a esta función
        //debugearHilos();
        
        hiloMusical = new ReproductorSonido(Constantes.PATH_HILO_MUSICAL_PRINCIPAL);
        
        hilos.add(hiloMusical);
        
        hiloMusical.start();
                
    }
    
    private void debugearHilos(){
        
        Timer pr= new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                for(Hilo i:hilos){
                    System.out.println("hilo - " + i);
                }
                
                System.out.print("\n");
                
            }
        });
        
        pr.start();
        
    }
    
    
    public void aniadirHilo(Hilo hilo){
        this.hilos.add(hilo);
    }
    
    public void eliminarHilo(Hilo hilo){
        this.hilos.remove(hilo);
    }
    
    
    //escuchador para el cierre de la ventana

    @Override
    public void windowClosing(WindowEvent we) {
        protocoloCierre();
    }
    
    
    
    //protocolo de cierre de todos los hilos
    public void protocoloCierre(){
        
       if(vista.mensajeSalirPartida()){
           
           matarHilos();
           System.exit(0);
           
       }
 
        
    }
    
    //metodo para matar todos los hilos del programa
    protected void matarHilos(){
        
        /*
            Es necesario hacer la iteracion de la lista de hiloas en de manera sincrona para evista una excepcion
            java.util.ConcurrentModificationException debido a que es una array de hilos que algunos de ellos manejan o son directamente
            hilos usados por la librería grafica swing como el hilo de la clase Runnable Puntegografico
        */
        
        synchronized(hilos){
            for (Hilo i:hilos) {
                i.matar();
            }
        }
        
        

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
            switch (ae.getActionCommand()) {
                //BOTON LANZAR DADO
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

    
    protected void lanzarDado() {
        
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
    
    //eventos del flujo de ejecución del turno

    public abstract void eventoFinalizacionDado();

    public abstract void eventoToquePuntero();
    
    public abstract void eventoFinalMovimientoFicha();
    
    //acciones 
    
    public abstract void nuevaPartida();
    
    public abstract void guardarPartida();
    
    public abstract void cargarPartida();

    
    protected void volverMenuInicio(){
        
        System.out.println("Menu inicio");
    }
    
    
    protected void esperarDelayAntesDeAccionAuto(){
        
        try {
            Thread.sleep(this.TIEMPO_DELAY_COMIENZO_MOVIMIENTO_AUTO);
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void accionPantallaCompleta(){
        UtilidadesGraficas.pantallaCompleta((VentanaConCorrecion)vista);
    }
    
}
    
    
    
    
