

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
    
    public static void pantallaCompleta(VentanaConCorrecion ventana) {
        
        
        if(!ventana.isPantallaCompleta()){
            ((JFrame)ventana).dispose();
            ((JFrame)ventana).setUndecorated(true);
            ((JFrame)ventana).setVisible(true);
            ((JFrame)ventana).setResizable(false);
            ((JFrame)ventana).setLocation(0,0);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            ((JFrame)ventana).setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
            ventana.setPantallaCompleta(true);
        }else{
            ((JFrame)ventana).dispose();
            ((JFrame)ventana).setUndecorated(false);
            ((JFrame)ventana).setResizable(true);
            ((JFrame)ventana).setVisible(true);
            ventana.setPantallaCompleta(false);
        }
        
    }
    
    
    
    
}
