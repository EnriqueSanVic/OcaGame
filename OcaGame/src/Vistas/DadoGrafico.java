
package Vistas;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @autor: Alvaro
 */
public class DadoGrafico extends JPanel implements Runnable{
    
    private final int ALTURA_CUBILETE = 210;
    private final int ANCHURA_CUBILETE = 410;
    private final int POSICION_PANEL_X = 20;
    private final int POSICION_PANEL_Y = 20;
    private final int ALTURA_DADO = 50;
    private final int ANCHURA_DADO = 50;
    private final int POSICION_INICIAL_DADO_X = 75;
    private final int POSICION_INICIAL_DADO_Y = 360;
    
    private ImageIcon imagenDado;
    private JLabel dado;
    
    private boolean impulso;
    
    private VistaJuego vistaJuego;
    
    public DadoGrafico(VistaJuego vistaJuego){
        super();
        crearCubilete();
        crearDado();
        this.impulso=false;
        this.vistaJuego = vistaJuego;
    }

    @Override
    public void run() {
        //Si no hay impulso, el dado espera.
        while(impulso==false){
            esperar();
        }
        //Recoger numero final (del controlador).
        //Escoger movimiento inicial.
        //Rebotar y cambiar de imagen por cada rebote.
        //Si llega al limite inferior, se para y se pone la imagen del numero final.
    }
    
    private void esperar(){
        try {
            wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(DadoGrafico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void crearCubilete() {
        this.setBounds(POSICION_PANEL_X, POSICION_PANEL_Y, ALTURA_CUBILETE, ANCHURA_CUBILETE);
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(null);
    }
    
    private void crearDado(){
        this.imagenDado = new ImageIcon("./img/dados/DADO_3.png");
        this.dado = new JLabel();
        this.dado.setIcon(imagenDado);
        this.dado.setBounds(POSICION_INICIAL_DADO_X, POSICION_INICIAL_DADO_Y, ALTURA_DADO, ANCHURA_DADO);
        this.add(this.dado);
    }

    

}
