
package Vistas;

import Controladores.ControladorJuego;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @autor: Alvaro
 */
public class VistaJuegoModo2 extends VistaJuego{
    
    private JLabel nombreJugador1;
    private JLabel nombreJugador2;       
    private JPanel panelPenalizacionJ1;
    private JLabel penalizacionTurnosJ1;
    private JLabel labelTurnosJ1;
    private JPanel panelPenalizacionJ2;
    private JLabel penalizacionTurnosJ2;
    private JLabel labelTurnosJ2;
    private JLabel labelJ1;
    private JLabel labelJ2;
    
    private final Font FUENTE_1 = new Font("Arial", 1, 30);
    private final Font FUENTE_2 = new Font("Arial", 1, 50);
    
    public VistaJuegoModo2(ControladorJuego control) {
        super(control);
        crearObjetos();
        disenoObjetos();        
        anadirObjetos();
    }

    private void crearObjetos() {
        this.nombreJugador1 = new JLabel("Alvaro", JLabel.CENTER);
        this.nombreJugador2 = new JLabel("Enrique", JLabel.CENTER);
        this.panelPenalizacionJ1 = new JPanel();
        this.penalizacionTurnosJ1 = new JLabel("0", JLabel.CENTER);
        this.labelTurnosJ1 = new JLabel("turnos", JLabel.CENTER);
        this.labelJ1 = new JLabel("J1");
        this.panelPenalizacionJ2 = new JPanel();
        this.penalizacionTurnosJ2 = new JLabel("0", JLabel.CENTER);
        this.labelTurnosJ2 = new JLabel("turnos", JLabel.CENTER);
        this.labelJ2 = new JLabel("J2");
    }

    private void disenoObjetos() {
        this.nombreJugador1.setFont(this.FUENTE_1);
        this.nombreJugador1.setBounds(0, 50, 220, 50);
        
        this.nombreJugador2.setFont(this.FUENTE_1);
        this.nombreJugador2.setBounds(0, 150, 220, 50);
        
        this.panelPenalizacionJ1.setBorder(super.getBlackBorder());
        this.panelPenalizacionJ1.setBackground(Color.white);
        this.panelPenalizacionJ1.setLayout(null);
        this.panelPenalizacionJ1.setBounds(10, 50, 200, 140);
        
        this.penalizacionTurnosJ1.setFont(this.FUENTE_2);
        this.penalizacionTurnosJ1.setBounds(0, 20, 200, 50);
        this.labelTurnosJ1.setFont(this.FUENTE_1);
        this.labelTurnosJ1.setBounds(0, 80, 200, 50);
        
        this.panelPenalizacionJ2.setBorder(super.getBlackBorder());
        this.panelPenalizacionJ2.setBackground(Color.white);
        this.panelPenalizacionJ2.setLayout(null);
        this.panelPenalizacionJ2.setBounds(10, 190, 200, 140);
        //this.labelJ1.setFont(this.FUENTE_1);
        this.labelJ1.setBounds(10, 0, 50, 20);
        this.labelJ1.setForeground(Color.RED);
        
        this.penalizacionTurnosJ2.setFont(this.FUENTE_2);
        this.penalizacionTurnosJ2.setBounds(0, 20, 200, 50);
        this.labelTurnosJ2.setFont(this.FUENTE_1);
        this.labelTurnosJ2.setBounds(0, 80, 200, 50);
        //this.labelJ2.setFont(this.FUENTE_1);
        this.labelJ2.setBounds(10, 0, 50, 20);
        this.labelJ2.setForeground(Color.BLUE);
    }
    
    private void anadirObjetos() {
        super.getPanelNombresJugadores().add(this.nombreJugador1);
        super.getPanelNombresJugadores().add(this.nombreJugador2);
        
        super.getPanelPenalizaciones().add(this.panelPenalizacionJ1);
        this.panelPenalizacionJ1.add(this.penalizacionTurnosJ1);
        this.panelPenalizacionJ1.add(this.labelTurnosJ1);
        this.panelPenalizacionJ1.add(this.labelJ1);
        
        super.getPanelPenalizaciones().add(this.panelPenalizacionJ2);
        this.panelPenalizacionJ2.add(this.penalizacionTurnosJ2);
        this.panelPenalizacionJ2.add(this.labelTurnosJ2);
        this.panelPenalizacionJ2.add(this.labelJ2);
    }

    public JLabel getPenalizacionTurnosJ1() {
        return penalizacionTurnosJ1;
    }

    public void setPenalizacionTurnosJ1(JLabel penalizacionTurnosJ1) {
        this.penalizacionTurnosJ1 = penalizacionTurnosJ1;
    }

    public JLabel getPenalizacionTurnosJ2() {
        return penalizacionTurnosJ2;
    }

    public void setPenalizacionTurnosJ2(JLabel penalizacionTurnosJ2) {
        this.penalizacionTurnosJ2 = penalizacionTurnosJ2;
    }
  
    

}
