

package Utilidades;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Enrique Sánchez 
 */
public class UtilidadesGraficas {

    
    
    //Metodo que lanza el frame en el medio de la pantalla del usuario al iniciarse la VistaJuego
    public static void ponerMedioPantalla(Window ventana) {
        
        int width, height;
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        
        width = (pantalla.width/2) - (ventana.getSize().width/2);
        
        height = (pantalla.height/2) - (ventana.getSize().height/2);
        
        ventana.setLocation(width, height);

        
    }
    
    
    public static void ponerPanelMedioFrame(JPanel panel, VentanaConCorrecion ventana) {
        
        int width, height;
        Dimension dimensionesConstenedor = ventana.getSize();
        
        width = ((dimensionesConstenedor.width - ventana.getCorreccionWidth())/2) - (panel.getSize().width/2);
        
        height = ((dimensionesConstenedor.height - ventana.getCorreccionHeight())/2) - (panel.getSize().height/2);
        
        panel.setLocation(width, height);


    }
    
    
    
    
}
