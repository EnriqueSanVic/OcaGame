

package Vistas.TableroSystem;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Enrique Sánchez 
 */
public class PunteroGrafico extends JLabel{
    
    public static final String ID = "puntero";
    
    private final String PUNTERO_IMAGE_PATH = "./img/tablero/puntero.png";
    private final String PUNTERO_OSCURO_IMAGE_PATH = "./img/tablero/punteroOscuro.png";
    
    private final ImageIcon imagenClara, imagenOscura;
    
    private final int PERIODO_CAMBIO_IMAGEN = 110;
    
    private final int WIDTH = 150, HEIGHT =  150;
    
    private Timer hiloGUI;
    
    private boolean estadoImagen;
            

    public PunteroGrafico(Point centro, MouseListener escuchador) {
        super();
        
        
        imagenClara = new ImageIcon(PUNTERO_IMAGE_PATH);
        imagenOscura = new ImageIcon(PUNTERO_OSCURO_IMAGE_PATH);
        
        estadoImagen = true;
        
        initGrafico(centro.x,centro.y, escuchador);
        
        hiloGUI = new Timer(PERIODO_CAMBIO_IMAGEN, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        
                        if(estadoImagen){
                            setIcon(imagenOscura);
                            estadoImagen = false;
                        }else{
                            setIcon(imagenClara);
                            estadoImagen = true;
                        }
                        
                    }
                });
        
    }

    private void initGrafico(int x, int y, MouseListener escuchador) {
        
       this.setSize(WIDTH, HEIGHT);
        
       this.setIcon(imagenClara);
       
       this.setLocation(x - WIDTH/2, y - HEIGHT/2);
       
       this.setOpaque(false);
       
       this.setIcon(imagenClara);
       
       this.setName(ID);
       
       this.addMouseListener(escuchador);
       
        
    }

   
    public void iniciar() {
        hiloGUI.start();
    }
    
    public void parar(){
        hiloGUI.stop();
    }
    
    
    

}
