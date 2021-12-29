
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
public class PanelFondoInicio extends JPanel{
    
    private BufferedImage imagenFondoInicio;
    private VistaInicio vistaPadre;
    
    public PanelFondoInicio(VistaInicio vistaPadre) {
        super();
        this.vistaPadre = vistaPadre;
        //Imagen Fondo Inicio.
        try {
            this.imagenFondoInicio = ImageIO.read(new File(Constantes.PATH_ICONO_FONDO_INICIO));
        } catch (IOException ex) {
            System.out.println("Error de carga de imagen fondo inicio");
        }
    }

    @Override
    public void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); 
        grphcs.drawImage(imagenFondoInicio, 18, 0, this);
    }
}
