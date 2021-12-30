

package Vistas.TableroSystem;

import DatosEstaticos.Constantes;
import Hilos.Hilo;
import Hilos.RegistradorHilos;
import ReproductorSonido.ManejadorSonidos;
import ReproductorSonido.ReproductorContinuo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Elemento de puntero grafico para indicar al usuario el punto sobre el que tiene que hacer click.
 * 
 * Es un hilo para realizar la animación de tintineo.
 * 
 * @author Enrique Sánchez 
 */
public final class PunteroGrafico extends JLabel implements MouseListener, Hilo{
    
    public static final String ID = "puntero";
    
    private final String PUNTERO_IMAGE_PATH = "./img/tablero/puntero.png";
    private final String PUNTERO_OSCURO_IMAGE_PATH = "./img/tablero/punteroOscuro.png";
    
    private final ImageIcon imagenClara, imagenOscura;
    
    private final int PERIODO_CAMBIO_IMAGEN = 110;
    
    private final int WIDTH = 150, HEIGHT =  150;
    
    private Timer hiloGUI;
    
    private NotificableTablero notificable;
    
    private RegistradorHilos registrador;
    
    private boolean estadoImagen;
    
    private ReproductorContinuo hiloSonido;
    
    private Tablero tablero;
            
    /**
     * Constructor
     * 
     * @param centro punto sobre el que se quiere colocar.
     * @param notificable objeto notificable para notificar el evento de pulsación del elemetno.
     * @param registrador objeto registrador de hilos para registrar la actividad del hilo. 
     * @param tablero objeto del tablero al que pertenece.
     */
    public PunteroGrafico(Point centro, NotificableTablero notificable, RegistradorHilos registrador, Tablero tablero) {
        super();
        
        this.notificable = notificable;
        this.registrador = registrador;
        this.tablero = tablero;
        
        imagenClara = new ImageIcon(PUNTERO_IMAGE_PATH);
        imagenOscura = new ImageIcon(PUNTERO_OSCURO_IMAGE_PATH);
        
        estadoImagen = true;
        
        initGrafico(centro.x,centro.y);
        
       
        //se inicia un timer para poder actuar como un hilo conjunto a la librería swing
        hiloGUI = new Timer(PERIODO_CAMBIO_IMAGEN, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        
                        if(estadoImagen){
                            setIcon(imagenOscura);
                            estadoImagen = false;
                        }else{
                            setIcon(imagenClara);
                            estadoImagen = true;
                        }
                        
                    }
                });
        
        
        hiloSonido = ManejadorSonidos.hiloMusical(Constantes.PATH_SONIDO_PUNTERO_GRAFICO);
        
        registrador.aniadirHilo(this);
        registrador.aniadirHilo(hiloSonido);
        
    }

    /**
     * Inicia la parte gráfica del elemento.
     * 
     * @param x
     * @param y 
     */
    private void initGrafico(int x, int y) {
        
       this.setSize(WIDTH, HEIGHT);
        
       this.setIcon(imagenClara);
       
       this.setLocation(x - WIDTH/2, y - HEIGHT/2);
       
       this.setOpaque(false);
       
       this.setIcon(imagenClara);
       
       this.setName(ID);
       
       this.addMouseListener(this);
       
        
    }

   /**
    * Inicia la actividad de los hilos, gráfico y sonido.
    */
    public void iniciar() {
        hiloGUI.start();
        hiloSonido.start();
    }
    
    /**
     * Para la actividad de los hilos del elemento, los destruye y notifica su eliminación.
     */
    public void parar(){
        hiloGUI.stop();
        hiloSonido.matar();
        registrador.eliminarHilo(this);
        registrador.eliminarHilo(hiloSonido);
    }

    
    //escuchador de eventos de raton sobre el elemento.
    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
        notificable.eventoToquePuntero();
        tablero.eliminarPuntero();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void matar() {
        hiloGUI.stop();
    }
    
    @Override
    public String toString() {
        return "PunteroGrafico";
    }
    
    
    

}
