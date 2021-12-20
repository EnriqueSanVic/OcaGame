

package Vistas.TableroSystem;

import DatosEstaticos.Constantes;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Enrique Sánchez 
 */
public class Ficha extends JLabel{
    
    protected int casillaActual;

    private final int WIDTH = 50, HEIGHT = 50;
    
    public Ficha(int casillaActual, int jugador, int x, int y) {
        
        this.casillaActual = casillaActual;
        
        init(jugador, x, y);
    }

    private void init(int jugador, int x, int y) {
        
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(x, y);
        
        this.setOpaque(false);
        
        switch(jugador){
            case Constantes.JUGADOR_2:
                
                this.setIcon(new ImageIcon("./img/ficha2Chica.png"));
                
                break;
                
            //tanto para el jugador 1 como por defecto se escoge la igamen de la 1
            case Constantes.JUGADOR_1:  
            default:
                
                this.setIcon(new ImageIcon("./img/ficha1Chica.png"));
                
                break;
        }
        
        
    }
    
    public Point getCentro(){
        return new Point(WIDTH/2, HEIGHT/2);
    }
    
    public Point getCentroAbsoluto(){
        
        Point centro = getCentro();
        
        return new Point( this.getLocation().x + centro.x, this.getLocation().y + centro.y);
    }

    public void mover(int x,int y){
        this.setLocation(this.getLocation().x + x, this.getLocation().y + y);
    }
    
    
    //trnasporta el centro de la ficha a las coordenadas de los argumentos
    public void transportarCentro(int x,int y){
        
        Point centro = getCentro();
        
        this.setLocation(x - centro.x, y - centro.y);
    }

    public int getCasillaActual() {
        return casillaActual;
    }

    public void setCasillaActual(int casillaActual) {
        this.casillaActual = casillaActual;
    }
    
    
    
    
    
}
