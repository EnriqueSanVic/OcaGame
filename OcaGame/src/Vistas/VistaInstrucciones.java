
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
public class VistaInstrucciones extends JDialog{
    
    //Constantes de configuracion.
    private final int FRAME_INSTRUCCIONES_WIDHT = 750, FRAME_INSTRUCCIONES_HEIGHT = 600;  
    
    //Atributos de la clase.
    private BufferedImage imagenFondoInstrucciones;
    int idioma;
    
    /**
     * Constructor.
     * @param vistaPadre 
     */
    public VistaInstrucciones(VistaJuego vistaPadre){
        
        super(vistaPadre, true);

        this.idioma = vistaPadre.getIdioma();
        
        File imagen = null;
        
        switch (idioma) {
            case 0:
                if(vistaPadre instanceof VistaJuegoModo1){
                    imagen = new File(Constantes.PATH_ICONO_INSTRUCCIONES_MODO1_ESP);
                }else{
                    imagen = new File(Constantes.PATH_ICONO_INSTRUCCIONES_MODO2_ESP);
                }
                break;
            case 1:
                if(vistaPadre instanceof VistaJuegoModo1){
                    imagen = new File(Constantes.PATH_ICONO_INSTRUCCIONES_MODO1_ING);
                }else{
                    imagen = new File(Constantes.PATH_ICONO_INSTRUCCIONES_MODO2_ING);
                }
                imagen = new File(Constantes.PATH_ICONO_INSTRUCCIONES_MODO1_ING);
                break;    
            default:
                break;
        }

        
        //Imagen Fondo Panel Instrucciones.
        try {
            this.imagenFondoInstrucciones = ImageIO.read(imagen);
        } catch (IOException ex) {
            System.out.println("Error de carga de imagen fondo instrucciones");
        }
        this.setSize(this.FRAME_INSTRUCCIONES_WIDHT, this.FRAME_INSTRUCCIONES_HEIGHT);
        UtilidadesGraficas.ponerMedioPantalla(this);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        grphcs.drawImage(this.imagenFondoInstrucciones, 0, 0, this);
    }


}
