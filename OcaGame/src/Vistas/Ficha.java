

package Vistas;

import DatosEstaticos.Constantes;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Enrique Sánchez 
 */
public class Ficha extends JLabel{

    private final int WIDTH = 50, HEIGHT = 50;
    
    public Ficha(int jugador, int x, int y) {
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

    
    
}
