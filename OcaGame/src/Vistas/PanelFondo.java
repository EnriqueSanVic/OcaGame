
package Vistas;

import DatosEstaticos.Constantes;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @autor: Alvaro
 */
public class PanelFondo extends JPanel{
    //Atributos de la clase
    private BufferedImage imagenFondoVistaJuego;
    private VistaJuego vistaPadre;
    
    /**
     * Constructor
     * @param vistaPadre 
     */
    public PanelFondo(VistaJuego vistaPadre) {
        super();
        this.vistaPadre = vistaPadre;
        //Imagen Fondo Juego.
        try {
            this.imagenFondoVistaJuego = ImageIO.read(new File(Constantes.PATH_ICONO_FONDO_VISTAJUEGO));
        } catch (IOException ex) {
            System.out.println("Error de carga de imagen fondo vistaJuego");
        }
    }

    @Override
    public void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); 
        grphcs.drawImage(imagenFondoVistaJuego, 0, 0, this);
    }

}
