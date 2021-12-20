

package Vistas.TableroSystem;

import Vistas.TableroSystem.Ficha;
import Hilos.Hilo;
import java.awt.Point;



/**
 *
 * @author Enrique Sánchez 
 */
public class ManejadorFicha extends Thread implements Hilo{

    private boolean activado, corriendo;
    
    private Ficha ficha;
    
    private final int TIEMPO_DELAY= 4;
    
    private int casillaDestino;
    
    private CasillaGrafica[] casillas;

    public ManejadorFicha(int casillaDestino, Ficha ficha, CasillaGrafica[] casillas) {
        
        this.casillaDestino = casillaDestino;
        this.ficha = ficha;
        this.casillas = casillas;
        
        this.setPriority(Thread.MAX_PRIORITY);
    }
    
    
    
    @Override
    public void run() {
        
        eliminarFichaDelSlot();
        
        if(ficha.getCasillaActual() < casillaDestino){
            
            moverAlante();
            
        }else if(ficha.getCasillaActual() > casillaDestino){
            
            moverAtras();
            
        }
        
        
    }
    
    
    private void moverAlante(){
        
        System.out.println("Alante");
        
        while(ficha.getCasillaActual() <  casillaDestino){
             
            moverACasilla(ficha, casillas[ficha.getCasillaActual() + 1]);
            
            ficha.setCasillaActual(ficha.getCasillaActual() + 1);
            
         }
        
        ManejadorFicha.iniciarEnCasilla(casillaDestino, ficha, casillas);
        

        
    }
    
    private void moverAtras(){
        
        while(ficha.getCasillaActual() >  casillaDestino){
                
            moverACasilla(ficha, casillas[ficha.getCasillaActual() - 1]);
            
            ficha.setCasillaActual(ficha.getCasillaActual()  - 1);
            
        }
        
        ManejadorFicha.iniciarEnCasilla(casillaDestino, ficha, casillas);
        
    }
    
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
            
            try {
                Thread.sleep(TIEMPO_DELAY);
            } catch (InterruptedException ex) {
                
            }
            
            centroFicha = ficha.getCentroAbsoluto();
            
        }
        
        
        
    }
    
    public void iniciar(){
        activado = true;
    }

    @Override
    public void matar() {
        activado = false;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public void setCasillaDestino(int casillaDestino) {
        this.casillaDestino = casillaDestino;
    }
    
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
    
    //en realizad es mover arriba o a la izquierda dependiendo de la horientación de la casilla
    private static void moverArriba(Ficha ficha, CasillaGrafica casilla){
        
        if(casilla.isHorizontal){
            ficha.mover( - ficha.getWidth()/2, 0);
        }else{
            ficha.mover(0, - ficha.getHeight()/2);
        }
        
        
    }
    
    private static void moverAbajo(Ficha ficha, CasillaGrafica casilla){
    
        if(casilla.isHorizontal){
            ficha.mover( ficha.getWidth()/2, 0);
        }else{
            ficha.mover(0, ficha.getHeight()/2);
        }
        
    }

    private void esperar() {
        try {
            wait();
        } catch (InterruptedException ex) {
        }
    }

    private void eliminarFichaDelSlot() {
        
        if(casillas[casillaDestino].slot1 == ficha){
            
            casillas[casillaDestino].slot1 = null;
            
        }else if(casillas[casillaDestino].slot2 == ficha){
            
            casillas[casillaDestino].slot2 = null;
            
        }
        
        
        
    }

    
    
    
    
    
    
    
}
