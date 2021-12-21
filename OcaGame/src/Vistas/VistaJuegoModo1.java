
package Vistas;

import Controladores.ControladorJuego;
import DatosEstaticos.TextosJuego;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @autor: Alvaro
 */
public class VistaJuegoModo1 extends VistaJuego{
    
    //Constantes de configuracion.
    private final String PATH_ICONO_FICHAJ1 = "./img/ficha1.png"; //Icono grande ficha 1.
    
    private final Font FUENTE_1 = new Font("Arial", 1, 15); //Cuenta atras
    private final Font FUENTE_2 = new Font("Arial", 1, 30); //Turnos y nombre
    private final Font FUENTE_3 = new Font("Arial", 1, 50); //Numero penalizacion
    
    private final int FICHAJ1_X=60, FICHAJ1_Y=60; //Posicion de la ficha J1.
    private final int FICHAJ1_WIDTH = 100, FICHAJ1_HEIGHT = 100; //Medidas de la ficha J1.
    
    private final int NOMBREJ1_X=0, NOMBREJ1_Y=170; //Posicion del nombre del J1.
    private final int NOMBREJ1_WIDTH = 220, NOMBREJ1_HEIGHT = 50; //Medidas del nombre del J1.
    
    private final int PANEL_PENALIZACION_X=10, PANEL_PENALIZACION_Y=50; //Posicion del Panel de Penalizacion.
    private final int PANEL_PENALIZACION_WIDTH = 200, PANEL_PENALIZACION_HEIGHT = 120; //Medidas Panel de Penalizacion.

    private final int LABEL_NUMERO_PENALIZACION_X=0, LABEL_NUMERO_PENALIZACION_Y=20; //Posicion del Label con el numero de Penalizacion.
    private final int LABEL_NUMERO_PENALIZACION_WIDTH = 200, LABEL_NUMERO_PENALIZACION_HEIGHT = 50; //Medidas del Label con el numero de Penalizacion.
    
    private final int LABEL_SEC_PENALIZACION_X=0, LABEL_SEC_PENALIZACION_Y=60; //Posicion del Label con el titulo seg/sec de Penalizacion.
    private final int LABEL_SEC_PENALIZACION_WIDTH = 200, LABEL_SEC_PENALIZACION_HEIGHT = 50; //Medidas del Label con el titulo seg/sec de Penalizacion.
    
    private final int LABEL_CUENTAATRAS_X=0, LABEL_CUENTAATRAS_Y=175; //Posicion del Label Cuenta Atras.
    private final int LABEL_CUENTAATRAS_WIDTH = 220, LABEL_CUENTAATRAS_HEIGHT = 50; //Medidas del Label Cuenta Atras.
    
    private final int PANEL_TEMPORIZADOR_X=10, PANEL_TEMPORIZADOR_Y=220; //Posicion del Panel del Temporizador.
    private final int PANEL_TEMPORIZADOR_WIDTH = 200, PANEL_TEMPORIZADOR_HEIGHT = 120; //Medidas Panel del Temporizador.
    
    private final int NUM_SEC_TEMPORIZADOR_X=0, NUM_SEC_TEMPORIZADOR_Y=20; //Posicion del Label con el numero de segundos del Temporizador.
    private final int NUM_SEC_TEMPORIZADOR_WIDTH = 200, NUM_SEC_TEMPORIZADOR_HEIGHT = 50; //Medidas del Label con el numero de segundos del Temporizador.
    
    private final int LABEL_SEC_TEMPORIZADOR_X=0, LABEL_SEC_TEMPORIZADOR_Y=60; //Posicion del Label con el titulo seg/sec del Temporizador.
    private final int LABEL_SEC_TEMPORIZADOR_WIDTH = 200, LABEL_SEC_TEMPORIZADOR_HEIGHT = 50; //Medidas del Label con el titulo seg/sec del Temporizador.
    //Atributos de la clase.
    private ImageIcon iconoFichaJ1;
    private JLabel fichaNombreJ1;
    private JLabel nombreJugador1;

    private JPanel panelPenalizacion;
    private JLabel cuentaAtrasLabel;
    private JLabel numeroPenalizacionLabel;
    private JLabel labelSegundosPenalizacion;
    
    private JPanel panelTemporizador;
    private JLabel segundosTemporizador; 
    private JLabel labelSegundosTemporizador;
 
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
        //Icono y nombre J1.
        this.iconoFichaJ1 = new ImageIcon(this.PATH_ICONO_FICHAJ1);
        this.fichaNombreJ1 = new JLabel();
        this.nombreJugador1 = new JLabel("Alvaro", JLabel.CENTER); //Viene desde vista inicio la cadena
        //Penalizaciones.
        this.panelPenalizacion = new JPanel();
        this.cuentaAtrasLabel = new JLabel(TextosJuego.LABEL_CUENTA_ATRAS_TEMPORIZADOR[super.getIdioma()], JLabel.CENTER);
        this.numeroPenalizacionLabel = new JLabel("-10", JLabel.CENTER); //VIene desde la logica/controlador la penalizacion.
        this.labelSegundosPenalizacion = new JLabel(TextosJuego.LABEL_SEGUNDOS_TEMPORIZADOR[super.getIdioma()], JLabel.CENTER);
        //Temporizador.
        this.panelTemporizador = new JPanel();
        this.segundosTemporizador = new JLabel("200", JLabel.CENTER);
        this.labelSegundosTemporizador = new JLabel(TextosJuego.LABEL_SEGUNDOS_TEMPORIZADOR[super.getIdioma()], JLabel.CENTER);
    }
    
    //Metodo que da un diseño a los elementos de la vista modo 1
    private void disenoObjetos(){
        //Ficha J1.
        this.fichaNombreJ1.setIcon(this.iconoFichaJ1);
        this.fichaNombreJ1.setBounds(this.FICHAJ1_X, this.FICHAJ1_Y, this.FICHAJ1_WIDTH, this.FICHAJ1_HEIGHT);
        //Nombre J1.
        this.nombreJugador1.setFont(this.FUENTE_2);
        this.nombreJugador1.setBounds(this.NOMBREJ1_X, this.NOMBREJ1_Y, this.NOMBREJ1_WIDTH, this.NOMBREJ1_HEIGHT);
        //Panel del Penalizacion.       
        this.panelPenalizacion.setBorder(super.getBlackBorder());
        this.panelPenalizacion.setBackground(Color.WHITE);
        this.panelPenalizacion.setLayout(null);
        this.panelPenalizacion.setBounds(this.PANEL_PENALIZACION_X, this.PANEL_PENALIZACION_Y, this.PANEL_PENALIZACION_WIDTH, this.PANEL_PENALIZACION_HEIGHT);
        //Numero de Penalizacion (en seg).
        this.numeroPenalizacionLabel.setFont(this.FUENTE_3);
        this.numeroPenalizacionLabel.setBounds(this.LABEL_NUMERO_PENALIZACION_X, this.LABEL_NUMERO_PENALIZACION_Y, this.LABEL_NUMERO_PENALIZACION_WIDTH, this.LABEL_NUMERO_PENALIZACION_HEIGHT);
        //Label seg / sec penalizados.
        this.labelSegundosPenalizacion.setFont(this.FUENTE_2);
        this.labelSegundosPenalizacion.setBounds(this.LABEL_SEC_PENALIZACION_X, this.LABEL_SEC_PENALIZACION_Y, this.LABEL_SEC_PENALIZACION_WIDTH, this.LABEL_SEC_PENALIZACION_HEIGHT);
        //Label cuenta atras.
        this.cuentaAtrasLabel.setFont(this.FUENTE_1);
        this.cuentaAtrasLabel.setBounds(this.LABEL_CUENTAATRAS_X, this.LABEL_CUENTAATRAS_Y, this.LABEL_CUENTAATRAS_WIDTH, this.LABEL_CUENTAATRAS_HEIGHT);
        //Panel del temporizador .       
        this.panelTemporizador.setBorder(super.getBlackBorder());
        this.panelTemporizador.setBackground(Color.WHITE);
        this.panelTemporizador.setLayout(null);
        this.panelTemporizador.setBounds(this.PANEL_TEMPORIZADOR_X, this.PANEL_TEMPORIZADOR_Y, this.PANEL_TEMPORIZADOR_WIDTH, this.PANEL_TEMPORIZADOR_HEIGHT);
        //Numero de segundos en el temporizador.
        this.segundosTemporizador.setFont(this.FUENTE_3);
        this.segundosTemporizador.setBounds(this.NUM_SEC_TEMPORIZADOR_X, this.NUM_SEC_TEMPORIZADOR_Y, this.NUM_SEC_TEMPORIZADOR_WIDTH, this.NUM_SEC_TEMPORIZADOR_HEIGHT);
        //Label seg / sec actuales.
        this.labelSegundosTemporizador.setFont(this.FUENTE_2);
        this.labelSegundosTemporizador.setBounds(this.LABEL_SEC_TEMPORIZADOR_X, this.LABEL_SEC_TEMPORIZADOR_Y, this.LABEL_SEC_TEMPORIZADOR_WIDTH, this.LABEL_SEC_TEMPORIZADOR_HEIGHT);
    }
    
    //Metodo que añade elementos a su padre en la vista modo 1.
    private void anadirObjetos(){
        //Ficha y nombre J1.
        super.getPanelNombresJugadores().add(this.fichaNombreJ1);
        super.getPanelNombresJugadores().add(this.nombreJugador1);
        
        //Penalizaciones.
        super.getPanelPenalizaciones().add(this.panelPenalizacion);
        this.panelPenalizacion.add(this.numeroPenalizacionLabel);
        this.panelPenalizacion.add(this.labelSegundosPenalizacion);
        
        //Temporizador
        super.getPanelPenalizaciones().add(this.cuentaAtrasLabel);
        super.getPanelPenalizaciones().add(this.panelTemporizador);
        this.panelTemporizador.add(this.segundosTemporizador);
        this.panelTemporizador.add(this.labelSegundosTemporizador);
    }
    
    //Metodo que inicia la cuenta atras del temporizador hasta que llega a 0.
    private void iniciarTemporizador() {
        //Se repite a cada segundo.
        this.timer = new Timer(1000, new ActionListener() {
            int numeroActual = Integer.valueOf(getSegundosTemporizador());
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(numeroActual>0){
                    numeroActual-=1; //Resto un segundo en el label
                    setSegundosTemporizador(String.valueOf(numeroActual));                   
                    numeroActual = Integer.valueOf(getSegundosTemporizador());
                }
            }
        });
        this.timer.start(); //Iniciamos el temporizador
    }

    //Getter que devuelve el nombre del J1 actual.
    public String getNombreJugador1() {
        return nombreJugador1.getText();
    }

    //Setter que actualiza el nombre del J1 actual.
    public void setNombreJugador1(String nombreJugador1) {
        this.nombreJugador1.setText(nombreJugador1);
    }

    //Getter label penalizacion (-10seg)
    public String getPenalizacionLabel() {
        return numeroPenalizacionLabel.getText();
    }

    //Setter label penalizacion (-10seg)
    public void setPenalizacionLabel(String penalizacionLabel) {
        this.numeroPenalizacionLabel.setText(penalizacionLabel);
    }

    //Getter texto label penalizacion (200seg)
    public String getSegundosTemporizador() {
        return segundosTemporizador.getText();
    }

    //Setter texto label penalizacion (200seg)
    public void setSegundosTemporizador(String segundosTemporizador) {
        this.segundosTemporizador.setText(segundosTemporizador);
    }

}
