

package Vistas;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author Enrique Sánchez 
 */
public class CasillaGrafica extends JPanel{
    
    protected final int x,y;
    
    private final int width, height;
    
    protected boolean isHorizontal;
    
    protected Ficha slot1, slot2;

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
        
        this.setOpaque(true);
        
        this.setBackground(Color.black);

        this.setVisible(true);
        
        
    }
    
    
    public Point getCentro(){
        return new Point(width/2, height/2);
    }
    
    public Point getCentroAbsoluto(){
        
        Point centro = getCentro();
        
        return new Point( this.getLocation().x + centro.x, this.getLocation().y + centro.y);
    }
    
    
}
