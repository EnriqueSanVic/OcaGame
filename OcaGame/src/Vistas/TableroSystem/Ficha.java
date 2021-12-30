

package Vistas.TableroSystem;

import DatosEstaticos.Constantes;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Ficha gráfica.
 *
 * @author Enrique Sánchez 
 */
public final class Ficha extends JLabel{
    
    protected int casillaActual;

    private final int WIDTH = 50, HEIGHT = 50;
    
    /**
     * 
     * Constructor
     * 
     * @param casillaActual id actual en la que se encuentra la ficha
     * @param jugador id del jugador al que representa.
     * @param x posicion x inicial.
     * @param y posicion y inicial.
     */
    
    public Ficha(int casillaActual, int jugador, int x, int y) {
        
        this.casillaActual = casillaActual;
        
        init(jugador, x, y);
    }

    /**
     * inicialización del componente.
     * 
     * @param jugador
     * @param x
     * @param y 
     */
    private void init(int jugador, int x, int y) {
        
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(x, y);
        
        this.setOpaque(false);
        
        switch(jugador){
            case Constantes.JUGADOR_2:
                
                this.setIcon(new ImageIcon(Constantes.PATH_ICONO_FICHAJ2));
                
                break;
                
            //tanto para el jugador 1 como por defecto se escoge la igamen de la 1
            case Constantes.JUGADOR_1:  
            default:
                
                this.setIcon(new ImageIcon(Constantes.PATH_ICONO_FICHAJ1));
                
                break;
        }
        
        
    }
    
    /**
     * 
     * @return centro relativo.
     */
    public Point getCentro(){
        return new Point(WIDTH/2, HEIGHT/2);
    }
    
    /**
     * 
     * @return centro absoluto de la ficha respecto a su contenedor.
     */
    public Point getCentroAbsoluto(){
        
        Point centro = getCentro();
        
        return new Point( this.getLocation().x + centro.x, this.getLocation().y + centro.y);
    }

    public void mover(int x,int y){
        this.setLocation(this.getLocation().x + x, this.getLocation().y + y);
    }
    
    
    /**
     * transporta el centro de la ficha a las coordenadas.
     * @param x nueva posicion x.
     * @param y nueva posicion y .
     */
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
