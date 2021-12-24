

package Vistas.TableroSystem;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Enrique Sánchez 
 */
public class PunteroGrafico extends JLabel implements MouseListener{
    
    public static final String ID = "puntero";
    
    private final String PUNTERO_IMAGE_PATH = "./img/tablero/puntero.png";
    private final String PUNTERO_OSCURO_IMAGE_PATH = "./img/tablero/punteroOscuro.png";
    
    private final ImageIcon imagenClara, imagenOscura;
    
    private final int PERIODO_CAMBIO_IMAGEN = 110;
    
    private final int WIDTH = 150, HEIGHT =  150;
    
    private Timer hiloGUI;
    
    private NotificableTablero notificable;
    
    private boolean estadoImagen;
    
    private Tablero tablero;
            

    public PunteroGrafico(Point centro, NotificableTablero notificable, Tablero tablero) {
        super();
        
        this.notificable = notificable;
        this.tablero = tablero;
        
        imagenClara = new ImageIcon(PUNTERO_IMAGE_PATH);
        imagenOscura = new ImageIcon(PUNTERO_OSCURO_IMAGE_PATH);
        
        estadoImagen = true;
        
        initGrafico(centro.x,centro.y);
        
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

    private void initGrafico(int x, int y) {
        
       this.setSize(WIDTH, HEIGHT);
        
       this.setIcon(imagenClara);
       
       this.setLocation(x - WIDTH/2, y - HEIGHT/2);
       
       this.setOpaque(false);
       
       this.setIcon(imagenClara);
       
       this.setName(ID);
       
       this.addMouseListener(this);
       
        
    }

   
    public void iniciar() {
        hiloGUI.start();
    }
    
    public void parar(){
        hiloGUI.stop();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        notificable.eventoToquePuntero();
        tablero.eliminarPuntero();
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
    
    

}
