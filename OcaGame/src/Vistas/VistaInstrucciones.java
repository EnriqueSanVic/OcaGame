
package Vistas;

import DatosEstaticos.Constantes;
import Utilidades.UtilidadesGraficas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * @autor: Alvaro
 */
public class VistaInstrucciones extends JDialog{
    
    private final int FRAME_INSTRUCCIONES_WIDHT = 750, FRAME_INSTRUCCIONES_HEIGHT = 600;    
    private BufferedImage imagenFondoInstrucciones;
    
    public VistaInstrucciones(Frame vistaPadre){
        
        super(vistaPadre, true);
        
        //Imagen Fondo Panel Nombres.
        try {
            this.imagenFondoInstrucciones = ImageIO.read(new File(Constantes.PATH_ICONO_INSTRUCCIONES));
        } catch (IOException ex) {
            System.out.println("Error de carga de imagen fondo nombres");
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
