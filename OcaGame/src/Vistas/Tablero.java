
package Vistas;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @autor: Alvaro
 */
public class Tablero extends JPanel{
    
    ImageIcon imagenTablero;
    protected final int HEIGHT = 800;
    protected final int WIDTH = 800;

    public Tablero() {
        super();
        this.imagenTablero = new ImageIcon(getClass().getResource("../img/tablero/TableroOca.jpg"));
        this.setSize(800, 800);
    }

    @Override
    public void paint(Graphics grafico) {
        grafico.drawImage(imagenTablero.getImage(), 0, 0, WIDTH, HEIGHT, null);
        super.paint(grafico);
    }
    
}
