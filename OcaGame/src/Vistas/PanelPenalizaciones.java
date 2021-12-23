
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
public class PanelPenalizaciones extends JPanel{
    
    private BufferedImage imagenFondoPenalizaciones;

    public PanelPenalizaciones() {
        //Imagen Fondo Panel Penalizaciones.
        try {
            this.imagenFondoPenalizaciones = ImageIO.read(new File(Constantes.PATH_ICONO_PANEL_PENALIZACIONES));
        } catch (IOException ex) {
            System.out.println("Error de carga de imagen fondo penalizaciones");
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(imagenFondoPenalizaciones, 0, 0, this);
    }
         

}
