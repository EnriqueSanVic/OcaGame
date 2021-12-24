

package Utilidades;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

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
    
}
