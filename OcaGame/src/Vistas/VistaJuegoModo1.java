
package Vistas;

import Controladores.ControladorJuego;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @autor: Alvaro
 */
public class VistaJuegoModo1 extends VistaJuego{
    
    //Atributos de la clase.
    private JLabel nombreJugador1;
    private JLabel penalizacionLabel;
    private JLabel labelSegundosPenalizacion;
    private JPanel panelTemporizador;
    private JLabel segundosTemporizador;
    private JLabel labelSegundosTemporizador;
    private final Font FUENTE_1 = new Font("Arial", 1, 30);
    private final Font FUENTE_2 = new Font("Arial", 1, 50);
    
    private Timer timer;
    
    //Constructor.
    public VistaJuegoModo1(ControladorJuego control) {
        super(control);
        crearObjetos();
        disenoObjetos();
        anadirObjetos();
        iniciarTemporizador();
    }
    
    //Metodo que inicializa elementos de la vista modo 1
    private void crearObjetos() {
        this.nombreJugador1 = new JLabel("Alvaro", JLabel.CENTER); //Viene desde vista inicio la cadena
        this.penalizacionLabel = new JLabel("-10", JLabel.CENTER); //VIene desde la logica/controlador la penalizacion.
        this.labelSegundosPenalizacion = new JLabel("seg", JLabel.CENTER);
        this.panelTemporizador = new JPanel();
        this.segundosTemporizador = new JLabel("200", JLabel.CENTER);
        this.labelSegundosTemporizador = new JLabel("seg", JLabel.CENTER);
    }
    
    //Metodo que da un diseño a los elementos de la vista modo 1
    private void disenoObjetos(){        
        this.nombreJugador1.setFont(this.FUENTE_1);
        this.nombreJugador1.setBounds(0, 50, 220, 50);
        
        this.penalizacionLabel.setFont(this.FUENTE_2);
        this.penalizacionLabel.setBounds(0, 50, 220, 50);
        
        this.labelSegundosPenalizacion.setFont(this.FUENTE_1);
        this.labelSegundosPenalizacion.setBounds(0, 90, 220, 50);
                
        this.panelTemporizador.setBorder(super.getBlackBorder());
        this.panelTemporizador.setBackground(Color.white);
        this.panelTemporizador.setLayout(null);
        this.panelTemporizador.setBounds(10, 180, 200, 150);
        
        this.segundosTemporizador.setFont(this.FUENTE_2);
        this.segundosTemporizador.setBounds(0, 20, 200, 50);
        this.labelSegundosTemporizador.setFont(this.FUENTE_1);
        this.labelSegundosTemporizador.setBounds(0, 80, 200, 50);
    }
    
    //Metodo que añade elementos a su padre en la vista modo 1
    private void anadirObjetos(){
        super.getPanelNombresJugadores().add(this.nombreJugador1);
        
        super.getPanelPenalizaciones().add(this.penalizacionLabel);
        super.getPanelPenalizaciones().add(this.labelSegundosPenalizacion);
        
        super.getPanelPenalizaciones().add(this.panelTemporizador);
        this.panelTemporizador.add(this.segundosTemporizador);
        this.panelTemporizador.add(this.labelSegundosTemporizador);
    }

    //Getter label penalizacion (-10seg)
    public JLabel getPenalizacionLabel() {
        return penalizacionLabel;
    }

    //Setter label penalizacion (-10seg)
    public void setPenalizacionLabel(JLabel penalizacionLabel) {
        this.penalizacionLabel = penalizacionLabel;
    }

    //Getter texto label penalizacion (200seg)
    public String getSegundosTemporizador() {
        return segundosTemporizador.getText();
    }

    //Setter texto label penalizacion (200seg)
    public void setSegundosTemporizador(String segundosTemporizador) {
        this.segundosTemporizador.setText(segundosTemporizador);
    }

    //Metodo que inicia la cuenta atras del temporizador.
    private void iniciarTemporizador() {
        //Se repite a cada segundo.
        this.timer = new Timer(1000, new ActionListener() {
            int numeroActual = Integer.valueOf(getSegundosTemporizador());
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(numeroActual>0){
                    numeroActual-=1; //resto un segundo
                    setSegundosTemporizador(String.valueOf(numeroActual));                   
                    numeroActual = Integer.valueOf(getSegundosTemporizador());
                }
            }
        });
        this.timer.start(); //Iniciamos el temporizador
    }
 
}
