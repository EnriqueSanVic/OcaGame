
package Vistas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @autor: Alvaro
 */
public class Tablero extends JPanel{
    
    private final String IMAGE_PATH = "./img/tablero/tablero.png";
    
    private BufferedImage imagenTablero;
    protected final int HEIGHT = 800;
    protected final int WIDTH = 800;

    protected Ficha ficha1;
    
    public Tablero() {

        //se carga la imagen del tablero
        try {
            this.imagenTablero = ImageIO.read(new File(IMAGE_PATH));
        } catch (IOException ex) {
            System.out.println("Error de carga de imagen");
        }
        
        //se le asigna un tamaño al tablero
        this.setSize(WIDTH, HEIGHT);
        
        
        initFichas();
    }

    

    //se pinta el componente con la imagen del tablero
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(imagenTablero, 0, 0, this);
    }

    private void initFichas() {
        
        ficha1 = new Ficha(0, 20, 20);
        
        this.add(ficha1);
        
    }
    
    
    
}
