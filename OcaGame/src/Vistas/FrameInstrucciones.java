
package Vistas;

import DatosEstaticos.Constantes;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * @autor: Alvaro
 */
public class FrameInstrucciones extends JFrame{
    
    private final int FRAME_INSTRUCCIONES_WIDHT = 750, FRAME_INSTRUCCIONES_HEIGHT = 600;    
    private BufferedImage imagenFondoInstrucciones;
    
    public FrameInstrucciones(){
        //Imagen Fondo Panel Nombres.
        try {
            this.imagenFondoInstrucciones = ImageIO.read(new File(Constantes.PATH_ICONO_INSTRUCCIONES));
        } catch (IOException ex) {
            System.out.println("Error de carga de imagen fondo nombres");
        }
        this.setSize(this.FRAME_INSTRUCCIONES_WIDHT, this.FRAME_INSTRUCCIONES_HEIGHT);
        ponerMedioPantalla(this);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        grphcs.drawImage(this.imagenFondoInstrucciones, 0, 0, this);
    }

    //Metodo que lanza el frame en el medio de la pantalla del usuario al iniciarse la VistaJuego
    private void ponerMedioPantalla(Frame ventana) {
        
        int width, height;
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        
        width = (pantalla.width/2) - (ventana.getSize().width/2);
        
        height = (pantalla.height/2) - (ventana.getSize().height/2);
        
        ventana.setLocation(width, height);

        
    }

}
