
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
public class PanelNombres extends JPanel{

    //Atributos de la clase.
    private BufferedImage imagenFondoNombres;

    /**
     * Constructor.
     */
    public PanelNombres() {
        //Imagen Fondo Panel Nombres.
        try {
            this.imagenFondoNombres = ImageIO.read(new File(Constantes.PATH_ICONO_PANEL_NOMBRES));
        } catch (IOException ex) {
            System.out.println("Error de carga de imagen fondo nombres");
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(imagenFondoNombres, 0, 0, this);
    }

}
