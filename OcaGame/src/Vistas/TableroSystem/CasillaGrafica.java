

package Vistas.TableroSystem;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JPanel;

/**
 * Casilla gráfica de la vista, es una panel transparente para determinar el área util de una casilla.
 *
 * @author Enrique Sánchez 
 */
public class CasillaGrafica extends JPanel{
    
    protected final int x,y;
    
    private final int width, height;
    
    protected boolean isHorizontal;
    
    protected Ficha slot1, slot2;

    /**
     * Constructor
     * 
     * @param x posicion x 
     * @param y posicion y
     * @param width
     * @param height
     * @param isHorizontal retorna true si es una casilla horizontal y false si no lo es.
     */
    public CasillaGrafica(int x, int y, int width, int height, boolean isHorizontal) {
        
        super();
        
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isHorizontal = isHorizontal;
        
        this.slot1 = null;
        this.slot2 = null;

        init();
        
    }

    private void init() {
        
        
        this.setSize(width, height);
        
        this.setLocation(x,y);
        
        this.setOpaque(false);
        
        this.setBackground(Color.black);

        this.setVisible(true);
        
        
    }
    
    /**
     * Retorna el centro relativo de la casilla.
     * @return 
     */
    public Point getCentro(){
        return new Point(width/2, height/2);
    }
    
    /**
     * 
     * @return centro absoluto de la casilla respecto a su contenedor.
     */
    public Point getCentroAbsoluto(){
        
        Point centro = getCentro();
        
        return new Point( this.getLocation().x + centro.x, this.getLocation().y + centro.y);
    }
    
    public void reiniciar(){
        slot1 = null;
        slot2 = null;
    }
    
}
