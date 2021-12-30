
package Vistas;

import DatosEstaticos.Constantes;
import Utilidades.UtilidadesGraficas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JDialog;

/**
 * @autor: Alvaro
 */
public final class VistaAutores extends JDialog{
    
    //Constantes de la clase.
    private final int FRAME_AUTORES_WIDHT = 750, FRAME_AUTORES_HEIGHT = 600;  
    
    //Atributos de la clase.
    private BufferedImage imagenFondoAutores;
    private int idioma;
    
    /**
     * Constructor
     * @param vistaPadre
     * @param idioma 
     */
    public VistaAutores(VistaInicio vistaPadre, int idioma) {
        super(vistaPadre, true);
        this.idioma = idioma;
        
        File imagen = null;
        
        switch (idioma) {
            case 0:
                imagen = new File(Constantes.PATH_FONDO_AUTORES_ESP);
                break;
            case 1:
                imagen = new File(Constantes.PATH_FONDO_AUTORES_ING);
                break;    
            default:
                break;
        }
        
        //Imagen Fondo Autores.
        try {
            this.imagenFondoAutores = ImageIO.read(imagen);
        } catch (IOException ex) {
            System.out.println("Error de carga de imagen fondo autores");
        }
        this.setSize(this.FRAME_AUTORES_WIDHT, this.FRAME_AUTORES_HEIGHT);
        UtilidadesGraficas.ponerMedioPantalla(this);
        this.setResizable(false);
        this.setVisible(true);
        
    }
    
    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        grphcs.drawImage(this.imagenFondoAutores, 0, 0, this);
    }
 

}
