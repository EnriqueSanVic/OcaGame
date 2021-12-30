

package Controladores;

import DatosEstaticos.Constantes;
import Hilos.Hilo;
import Hilos.RegistradorHilos;
import Logicas.LogicaJuego;
import Modelos.DirectivasEvaluacion;
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
 * Controlador abstracto del juego, de este heredan los controladores, es la generalización del controlador del juego.
 *
 * @author Enrique Sánchez 
 */
public abstract class ControladorJuego extends WindowAdapter implements ActionListener, NotificableTablero, RegistradorHilos{

    private final int TIEMPO_DELAY_COMIENZO_MOVIMIENTO_AUTO = 400;
    
    protected final int POSICION_SALIDA = 0;
    
    protected LogicaJuego logica;
    
    protected VistaJuego vista;
    
    protected List<Hilo> hilos;
    
    protected String[] nombres;
    
    protected int ultimoNumeroDado;
    
    private Random random;
    
    
    /**
     * Constructor del controlador
     * 
     * @param idioma Id del idioma con el que se debe de instanciar el modo de juego.
     * @param jugador1 Nombre del jugador 1.
     * @param jugador2 Nombre del jugador 2. 
     */    
    public ControladorJuego(int idioma, String jugador1, String jugador2){
        this.hilos = new ArrayList<Hilo>();
        
        this.random = new Random();
        
        this.nombres = new String[]{jugador1, jugador2};
        
        //Si se quieren monitorizar los hilos activos en cada momento llamar a esta función
        //debugearHilos();
        
        //se inicia el hilo de sonido musical presente en ambos modos de juego
        ReproductorContinuo hiloMusical = ManejadorSonidos.hiloMusical(Constantes.PATH_HILO_MUSICAL_PRINCIPAL);
        
        aniadirHilo(hiloMusical);
        
        hiloMusical.start();
                
    }
    
    /**
     * Método para depurar los hilos presentes en la aplicación por terminal.
     */
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
    
    /**
     * Método para añadir hilos activos.
     * @param hilo Hilo que se desea añadir. 
     */
    public void aniadirHilo(Hilo hilo){
        this.hilos.add(hilo);
    }
    
    /**
     * Método para eliminar un hilo cuando deja de estar activo.
     * @param hilo Hilo que se desea eliminar. 
     */
    public void eliminarHilo(Hilo hilo){
        this.hilos.remove(hilo);
    }
    
    
    //escuchador para el cierre de la ventana

    /**
     * Escuchador de la acción de cierre de la ventana
     * @param we Evento de cierre. 
     */
    @Override
    public void windowClosing(WindowEvent we) {
        protocoloCierre();
    }
    
    
    
 
    /**
     * Protolo para el cierre del juego, se le pregunta al usuario si desea salir realmente y se matan todos los hilos activo.
     * Se vuelve a lanzar el menú inicial.
     * 
     */
    public void protocoloCierre(){
        
       if(vista.mensajeSalirPartida()){
           
           matarHilos();
           volverMenuInicio();
           
       }
 
        
    }
    
    /**
     * Método para matar todos los hilos activos.
     * 
     * Es necesario hacer la iteracion de la lista de hilos en de manera sincrona para evista una excepcion
     * java.util.ConcurrentModificationException debido a que es una array de hilos que algunos de ellos manejan o son directamente
     * hilos usados por la librería grafica swing como el hilo de la clase Runnable Puntegografico.
     *  
     *
     */
    protected void matarHilos(){
        
        
        synchronized(hilos){
            
            try{
            
                for (Hilo i:hilos) {
                    i.matar();
                }
            
            }catch(ConcurrentModificationException ex){

                
            }
            
        }
        
        

    }

    /**
     * Escuchador de eventos de boton de la VistaJuego
     * @param ae Evento tipo ActionEvent
     */
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

    
    /**
     * Acción de lanzar dado
     */
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
    
    //eventos del flujo de ejecución del turno

    
    /**
     * Método abstracto para comprovar si se puede tirar el dado.
     * 
     * @return booleano indicando si se puede tirar o no el dado.
     */
    protected abstract boolean sePuedeTirarDado();
        

    /**
     * Método escuchador del evento de finalización de la animación del dado en la vista.
     */
    public abstract void eventoFinalizacionDado();

    /**
     * Método escuchador del evento del click sobre el puntero gráfico en la vista.
     */
    public abstract void eventoToquePuntero();
    
    /**
     * Método escuchador del evento de finalización del movimiento de la ficha gráfica en la vista.
     */
    public abstract void eventoFinalMovimientoFicha();
    
    //acciones 
    
    /**
     * Método para cargar una nueva partida.
     */
    public abstract void nuevaPartida();
    
    /**
     * Método par guardar la partida actual
     */
    public abstract void guardarPartida();
    
    
    /**
     * Método para cargar la partida guardada.
     */
    public abstract void cargarPartida();
    
    
    /**
     * Método para discriminar tipo de sonido a ejecutar en un avance automático.
     * @param posicion Incremento o decremento de la posición del jugador como consecuencia en el avance automático.
     */
    protected void evaluarSonidoMovimiento(int posicion) {

        if (posicion > 0) {
            sonidoAvance();
        } else if (posicion < 0) {
            sonidoRetroceso();
        }

    }
    
    protected void volverMenuInicio(){
        //guardar/serializar
        matarHilos();
        this.vista.dispose();
        new VistaInicio(this.vista.getIdioma()).setVisible(true); //Lanzamos la vista inicio
        
    }
    
    /**
     * Metodo para dormir el hilo antes de un avance automático.
     */
    protected void esperarDelayAntesDeAccionAuto(){
        
        try {
            Thread.sleep(this.TIEMPO_DELAY_COMIENZO_MOVIMIENTO_AUTO);
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Metodo para evaluar las directivas de comportamiento de una casilla en la que ha caido un jugador.
     * @param directivas de la casilla.
     * @return 
     */
    protected abstract boolean evaluardirectivas(DirectivasEvaluacion directivas);
    
    /**
     * Metodo para poner o quitar el modo pantalla completa.
     */
    public void accionPantallaCompleta(){
        UtilidadesGraficas.pantallaCompleta((VentanaConCorrecion)vista);
    }
    
    /**
     * Metodo para invocar un sonido de retroceso.
     */
    protected void sonidoRetroceso(){
        ManejadorSonidos.hiloPuntual(Constantes.PATH_SONIDO_RETROCESO, this).start();
    }
    
    /**
     * Metodo para invocar un sonido de avance.
     */
    protected void sonidoAvance(){
        
        String path;
       
        path = random.nextBoolean() ? Constantes.PATH_SONIDO_AVANCE1 : Constantes.PATH_SONIDO_AVANCE2;
       
       ManejadorSonidos.hiloPuntual(path, this).start();
        
    }
    
    /**
     * Metodo para invocar un sonido de bloqueo de turno.
     */
    protected void sonidoTurnoBloqueado(){
        
        ManejadorSonidos.hiloPuntual(Constantes.PATH_SONIDO_TURNO_BLOQUEADO, this).start();
        
    }
    
    /**
     * Metodo para invocar un sonido de cambio de turno.
     */
    protected void sonidoCambioTurno(){
        
        ManejadorSonidos.hiloPuntual(Constantes.PATH_SONIDO_CAMBIO_TURNO, this).start();
        
    }
    
    /**
     * Metodo para invocar un sonido de ganar.
     */
    protected void sonidoGanar(){
        ManejadorSonidos.hiloPuntual(Constantes.PATH_SONIDO_GANAR, this).start();
    }
    
    /**
     * Metodo para invocar un sonido de perder.
     */
    protected void sonidoPerder(){
        ManejadorSonidos.hiloPuntual(Constantes.PATH_SONIDO_PERDER, this).start();
    }
    
    
    /**
     * Método que sirve para quitar elementos temporales de la vista que en caso de reiniciarse deberían desaparece.
     */
    protected void normalizarVista(){
        
        vista.eliminarPuntero();
        vista.reiniciarCasillas();
        
    }
}
    
    
    
    
