

package Vistas.TableroSystem;

import Hilos.Hilo;
import Hilos.RegistradorHilos;
import java.awt.Point;



/**
 * Hilo manejador de las fichas graficas para desplazarlas por el tablero.
 *
 * @author Enrique Sánchez 
 */
public final class ManejadorFicha extends Thread implements Hilo{
    
    private Ficha ficha;
    
    private final int TIEMPO_DELAY= 4;
    
    private int casillaDestino;
    
    private CasillaGrafica[] casillas;
    
    private NotificableTablero notificable;
    
    private RegistradorHilos registrador;

    /**
     * Constructor
     * 
     * @param casillaDestino posicion de la casilla hasta la que se quiere desplazar la ficha.
     * @param ficha
     * @param casillas conjunto de casilla gráficas.
     * @param notificable objeto notificable para notificarle el evento de finalización del movimiento.
     * @param registrador objeto registrador de hilos para registrar la actividad del hilo. 
     */
    public ManejadorFicha(int casillaDestino, Ficha ficha, CasillaGrafica[] casillas, NotificableTablero notificable, RegistradorHilos registrador) {
        
        this.casillaDestino = casillaDestino;
        this.ficha = ficha;
        this.casillas = casillas;
        this.notificable = notificable;
        this.registrador = registrador;
        
        this.setPriority(Thread.MAX_PRIORITY);
    }
    
    
    
    @Override
    public void run() {
        
        //elimina la ficha que se va a mover del slot de la casilla en el que se encuentra
        eliminarFichaDelSlot();
        
        //si es un avance
        if(ficha.getCasillaActual() < casillaDestino){
            
            moverAlante();
          
        //si es un retroceso
        }else if(ficha.getCasillaActual() > casillaDestino){
            
            moverAtras();
            
        }
        
        //se notifica el cese de la actividad del hilo
        registrador.eliminarHilo(this);
        
        
    }
    
    /**
     *  Recorre todas las casillas desde la de inicio hasta la de destino desplazando la ficha casilla por casilla.
     *  Crea la ilusión de movimiento hacia alaante.
     */
    private void moverAlante(){
        
        while(ficha.getCasillaActual() <  casillaDestino){
             
            moverACasilla(ficha, casillas[ficha.getCasillaActual() + 1]);
            
            ficha.setCasillaActual(ficha.getCasillaActual() + 1);
            
         }
        
        //se recalcula la posición sobre la ultima casilla para colocar las fichas.
        ManejadorFicha.iniciarEnCasilla(casillaDestino, ficha, casillas);
        
        //notifica el final del movimiento
        notificable.eventoFinalMovimientoFicha();
        
    }
    
    /**
     *  Recorre todas las casillas desde la de inicio hasta la de destino desplazando la ficha casilla por casilla.
     *  Crea la ilusión de movimiento hacia alaante.
     */
    private void moverAtras(){
        
        while(ficha.getCasillaActual() >  casillaDestino){
                
            moverACasilla(ficha, casillas[ficha.getCasillaActual() - 1]);
            
            ficha.setCasillaActual(ficha.getCasillaActual()  - 1);
            
        }
        
        //se recalcula la posición sobre la ultima casilla para colocar las fichas.
        ManejadorFicha.iniciarEnCasilla(casillaDestino, ficha, casillas);
        
        //notifica el final del movimiento
        notificable.eventoFinalMovimientoFicha();

    }
    
    /**
     * Mueve el centro una ficha pixel por pixel al centro de una casilla.
     * @param ficha
     * @param casilla 
     */
    private void moverACasilla(Ficha ficha, CasillaGrafica casilla) {

        
        Point centroFicha = ficha.getCentroAbsoluto();
        Point centroCasilla = casilla.getCentroAbsoluto();

        //cuando salga del while ya estará sobre el centro de la casilla de destino
        
        while(centroFicha.x != centroCasilla.x || centroFicha.y != centroCasilla.y){
        

            
            if(centroFicha.x < centroCasilla.x){
                ficha.mover(1, 0);
            }else if(centroFicha.x > centroCasilla.x){
                ficha.mover(-1, 0);
            }
            
            if(centroFicha.y < centroCasilla.y){
                ficha.mover(0, 1);
            }else if(centroFicha.y > centroCasilla.y){
                ficha.mover(0, -1);
            }
            
            //tiene un pequeño delay para hacer un movimiento más suvae
            try {
                Thread.sleep(TIEMPO_DELAY);
            } catch (InterruptedException ex) {
                
            }
            
            centroFicha = ficha.getCentroAbsoluto();
            
        }
        
        
        
    }
    
  
    @Override
    public void matar() {
        //es un hilo que no tiene un bucle de ejecución por lo que simpre podemos asegurar que se destruye solo eventualmente.
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public void setCasillaDestino(int casillaDestino) {
        this.casillaDestino = casillaDestino;
    }
    
    /**
     * Se redistribuyen las dicha de una casilla en la que se coloca una ficha.
     * 
     * Si solo hay una ficha esta se queda en el medio.
     * 
     * Si hay dos fichas colocan de tal manera que no se superpongan.
     * 
     * Existen dos modalidades de colozación de las fichas dependiendo de si la casilla es horizontal o vertical.
     * @param casilla
     * @param ficha
     * @param casillas array de casillas 
     */
    public static void iniciarEnCasilla(int casilla, Ficha ficha, CasillaGrafica[] casillas){
        

        //transportamos al centro de la casilla de destino
        ficha.casillaActual = casilla;

        Point p = casillas[casilla].getCentro();

        ficha.transportarCentro(casillas[casilla].x + p.x, casillas[casilla].y + p.y);

        //si están libres los dos slots 
        if(casillas[casilla].slot1 == null && casillas[casilla].slot2 == null){
            
            casillas[casilla].slot1= ficha;
            
        //si solo está el slot1 libre
        }else if(casillas[casilla].slot1 == null && casillas[casilla].slot2 != null){
            
            //priemro movemos a su posicion a la segundo ficha
            moverArriba(ficha, casillas[casilla]);
            
            casillas[casilla].slot1= ficha;
            
            moverAbajo(casillas[casilla].slot2, casillas[casilla]);
            
         //si solo está libre el slot 2
        }else if(casillas[casilla].slot1 != null && casillas[casilla].slot2 == null){
            
            moverAbajo(ficha, casillas[casilla]);
            
            casillas[casilla].slot2= ficha;
            
            moverArriba(casillas[casilla].slot1, casillas[casilla]);
            
        }
        
 
    }
    
    /**
     * Mueve la ficha arriba o a la izquierda de una casilla dependiendo de la horientación de la casilla.
     * @param ficha 
     * @param casilla 
     */
    private static void moverArriba(Ficha ficha, CasillaGrafica casilla){
        
        if(casilla.isHorizontal){
            ficha.mover( - ficha.getWidth()/2, 0);
        }else{
            ficha.mover(0, - ficha.getHeight()/2);
        }
        
        
    }
    
    /**
     * Mueve la ficha abajo o a la derecha de una casilla dependiendo de la horientación de la casilla.
     * @param ficha 
     * @param casilla 
     */
    private static void moverAbajo(Ficha ficha, CasillaGrafica casilla){
    
        if(casilla.isHorizontal){
            ficha.mover( ficha.getWidth()/2, 0);
        }else{
            ficha.mover(0, ficha.getHeight()/2);
        }
        
    }


    private void eliminarFichaDelSlot() {
        
        
                
        if(casillas[ficha.casillaActual].slot1 == ficha){
            
            casillas[ficha.casillaActual].slot1 = null;
            
        }else if(casillas[ficha.casillaActual].slot2 == ficha){
            
            casillas[ficha.casillaActual].slot2 = null;
            
        }
        
        
        if(casillas[casillaDestino].slot1 == ficha){
            
            casillas[casillaDestino].slot1 = null;
            
        }else if(casillas[casillaDestino].slot2 == ficha){
            
            casillas[casillaDestino].slot2 = null;
            
        }
        
        
        
    }
    
    @Override
    public String toString() {
        return "ManejadorFicha";
    }

    
    
    
    
    
    
    
}
