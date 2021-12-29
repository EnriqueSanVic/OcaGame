

package Controladores;

import DatosEstaticos.Constantes;
import Hilos.Hilo;
import Hilos.RegistradorHilos;
import Logicas.LogicaJuego;
import ReproductorSonido.ManejadorSonidos;
import ReproductorSonido.ReproductorContinuo;
import Utilidades.UtilidadesGraficas;
import Utilidades.VentanaConCorrecion;
import Vistas.TableroSystem.NotificableTablero;
import Vistas.VistaInicio;
import Vistas.VistaInstrucciones;
import Vistas.VistaJuego;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;
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
    
    protected int ultimoNumeroDado;
    
    private Random random;
        
    public ControladorJuego(int idioma, String jugador1, String jugador2){
        this.hilos = new ArrayList<Hilo>();
        
        this.random = new Random();
        //Si se quieren monitorizar los hilos activos en cada momento llamar a esta función
        //debugearHilos();
        
        //se inicia el hilo
        
        ReproductorContinuo hiloMusical = ManejadorSonidos.hiloMusical(Constantes.PATH_HILO_MUSICAL_PRINCIPAL);
        
        aniadirHilo(hiloMusical);
        
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
           volverMenuInicio();
           
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
            
            try{
            
                for (Hilo i:hilos) {
                    i.matar();
                }
            
            }catch(ConcurrentModificationException ex){
                
                System.out.println("Error de cierre");
                
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
    
    
    protected void evaluarSonidoMovimiento(int posicion) {

        if (posicion > 0) {
            sonidoAvance();
        } else if (posicion < 0) {
            sonidoRetroceso();
        }

    }
    
    protected void volverMenuInicio(){
        //guardar/serializar
        
        this.vista.dispose();
        new VistaInicio(this.vista.getIdioma()).setVisible(true); //Lanzamos la vista inicio
        
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
    
    protected void sonidoRetroceso(){
        ManejadorSonidos.hiloPuntual(Constantes.PATH_SONIDO_RETROCESO, this).start();
    }
    
    protected void sonidoAvance(){
        
        String path;
       
        path = random.nextBoolean() ? Constantes.PATH_SONIDO_AVANCE1 : Constantes.PATH_SONIDO_AVANCE2;
       
       ManejadorSonidos.hiloPuntual(path, this).start();
        
    }
    
    protected void sonidoTurnoBloqueado(){
        
        ManejadorSonidos.hiloPuntual(Constantes.PATH_SONIDO_TURNO_BLOQUEADO, this).start();
        
    }
    
    protected void sonidoCambioTurno(){
        
        ManejadorSonidos.hiloPuntual(Constantes.PATH_SONIDO_CAMBIO_TURNO, this).start();
        
    }
    
    protected void sonidoGanar(){
        ManejadorSonidos.hiloPuntual(Constantes.PATH_SONIDO_GANAR, this).start();
    }
    
    protected void sonidoPerder(){
        ManejadorSonidos.hiloPuntual(Constantes.PATH_SONIDO_PERDER, this).start();
    }
    
}
    
    
    
    
