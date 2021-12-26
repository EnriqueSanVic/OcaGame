
package Vistas;

import Controladores.ControladorJuego;
import DatosEstaticos.Constantes;
import DatosEstaticos.TextosJuego;
import Vistas.TableroSystem.Tablero;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @autor: Alvaro
 */
public class VistaJuegoModo2 extends VistaJuego{
    
    //Constantes de configuracion.
  
    private final int FICHAJ1_X=10, FICHAJ1_Y=50; //Posicion de la ficha J1.
    private final int FICHAJ1_WIDTH = 50, FICHAJ1_HEIGHT = 50; //Medidas de la ficha J1.
    
    private final int NOMBREJ1_X=70, NOMBREJ1_Y=50; //Posicion del nombre de J1.
    private final int NOMBREJ1_WIDTH = 160, NOMBREJ1_HEIGHT = 50; //Medidas del nombre de J1.
    
    private final int FICHAJ2_X=10, FICHAJ2_Y=150; //Posicion de la ficha J2.
    private final int FICHAJ2_WIDTH = 50, FICHAJ2_HEIGHT = 50; //Medidas de la ficha J2.
    
    private final int NOMBREJ2_X=70, NOMBREJ2_Y=150; //Posicion del nombre de J2.
    private final int NOMBREJ2_WIDTH = 160, NOMBREJ2_HEIGHT = 50; //Medidas del nombre de J2.
    
    private final int PANEL_PENALIZACION_J1_X=10, PANEL_PENALIZACION_J1_Y=50; //Posicion del Panel de Penalizacion del J1.
    private final int PANEL_PENALIZACION_J1_WIDTH = 200, PANEL_PENALIZACION_J1_HEIGHT = 140; //Medidas del Panel de Penalizacion del J1.
   
    private final int FICHAJ1_PENALIZACION_X=10, FICHAJ1_PENALIZACION_Y=10; //Posicion de la ficha de penalizacion J1.
    private final int FICHAJ1_PENALIZACION_WIDTH = 50, FICHAJ1_PENALIZACION_HEIGHT = 50; //Medidas de la ficha de penalizacion J1.
 
    private final int NUM_TURNOSJ1_PENALIZACION_X=0, NUM_TURNOSJ1_PENALIZACION_Y=40; //Posicion del label con el numero turnos de penalizacion J1.
    private final int NUM_TURNOSJ1_PENALIZACION_WIDTH = 200, NUM_TURNOSJ1_PENALIZACION_HEIGHT = 50; //Medidas del label con el numero turnos de penalizacion J1.
  
    private final int LABEL_TURNOSJ1_PENALIZACION_X=0, LABEL_TURNOSJ1_PENALIZACION_Y=80; //Posicion del label con el titulo 'turnos' de penalizacion J1.
    private final int LABEL_TURNOSJ1_PENALIZACION_WIDTH = 200, LABEL_TURNOSJ1_PENALIZACION_HEIGHT = 50; //Medidas del label con el titulo 'turnos' de penalizacion J1.
    
    private final int PANEL_PENALIZACION_J2_X=10, PANEL_PENALIZACION_J2_Y=190; //Posicion del Panel de Penalizacion del J2.
    private final int PANEL_PENALIZACION_J2_WIDTH = 200, PANEL_PENALIZACION_J2_HEIGHT = 140; //Medidas del Panel de Penalizacion del J2.
  
    private final int FICHAJ2_PENALIZACION_X=10, FICHAJ2_PENALIZACION_Y=10; //Posicion de la ficha de penalizacion J2.
    private final int FICHAJ2_PENALIZACION_WIDTH = 50, FICHAJ2_PENALIZACION_HEIGHT = 50; //Medidas de la ficha de penalizacion J2.
    
    private final int NUM_TURNOSJ2_PENALIZACION_X=0, NUM_TURNOSJ2_PENALIZACION_Y=40; //Posicion del label con el numero turnos de penalizacion J2.
    private final int NUM_TURNOSJ2_PENALIZACION_WIDTH = 200, NUM_TURNOSJ2_PENALIZACION_HEIGHT = 50; //Medidas del label con el numero turnos de penalizacion J2.
    
    private final int LABEL_TURNOSJ2_PENALIZACION_X=0, LABEL_TURNOSJ2_PENALIZACION_Y=80; //Posicion del label con el titulo 'turnos' de penalizacion J2.
    private final int LABEL_TURNOSJ2_PENALIZACION_WIDTH = 200, LABEL_TURNOSJ2_PENALIZACION_HEIGHT = 50; //Medidas del label con el titulo 'turnos' de penalizacion J2.
    
    //Atributos de la clase.
    private JLabel fichaNombreJ1;
    private JLabel nombreJugador1;
    private JLabel fichaNombreJ2;
    private JLabel nombreJugador2; 
    
    private JPanel panelPenalizacionJ1;   
    private JLabel fichaPenalizacionJ1;
    private JLabel numeroTurnosPenalizadoJ1;
    private JLabel labelTurnosJ1;
    
    private JPanel panelPenalizacionJ2; 
    private JLabel fichaPenalizacionJ2;
    private JLabel numeroTurnosPenalizadoJ2;    
    private JLabel labelTurnosJ2;
    
    private ImageIcon iconoFicha1;
    private ImageIcon iconoFicha2;
    
    //Constructor.
    public VistaJuegoModo2(ControladorJuego control, int idioma, String jugador1, String jugador2) {
        super(control, idioma, jugador1, jugador2);
    }

    //Metodo que instancia los atributos necesarios de la clase.
    @Override
    protected void crearObjetos() {
        
        super.crearObjetos();
        
        //Tablero Oca.
        this.tablero = new Tablero(2, controlador, controlador);
        
        //Iconos.
        this.iconoFicha1 = new ImageIcon(Constantes.PATH_ICONO_FICHAJ1);
        this.iconoFicha2 = new ImageIcon(Constantes.PATH_ICONO_FICHAJ2);
        
        //Panel nombres Jugadores. 
        this.fichaNombreJ1 = new JLabel();
        this.nombreJugador1 = new JLabel(super.nombresJugadores[0], JLabel.LEFT);
        
        this.fichaNombreJ2 = new JLabel();
        this.nombreJugador2 = new JLabel(super.nombresJugadores[1], JLabel.LEFT);
        
        //Panel penalizacion J1.
        this.panelPenalizacionJ1 = new JPanel();
        this.fichaPenalizacionJ1 = new JLabel();
        this.numeroTurnosPenalizadoJ1 = new JLabel("-2", JLabel.CENTER);
        this.labelTurnosJ1 = new JLabel(TextosJuego.LABEL_TURNOS[super.getIdioma()], JLabel.CENTER);
        
        //Panel penalizacion J2. 
        this.panelPenalizacionJ2 = new JPanel();
        this.fichaPenalizacionJ2 = new JLabel();
        this.numeroTurnosPenalizadoJ2 = new JLabel("0", JLabel.CENTER);
        this.labelTurnosJ2 = new JLabel(TextosJuego.LABEL_TURNOS[super.getIdioma()], JLabel.CENTER); 
    }

    //Metodo que da formato a los atributos necesarios de la clase.
    @Override
    protected void disenoObjetos() {
        
        super.disenoObjetos();
        
        //Ficha J1.
        this.fichaNombreJ1.setIcon(this.iconoFicha1);
        this.fichaNombreJ1.setBounds(this.FICHAJ1_X, this.FICHAJ1_Y, this.FICHAJ1_WIDTH, this.FICHAJ1_HEIGHT);
        //Nombre J1. 
        this.nombreJugador1.setFont(super.FUENTE_2);
        this.nombreJugador1.setBounds(this.NOMBREJ1_X, this.NOMBREJ1_Y, this.NOMBREJ1_WIDTH, this.NOMBREJ1_HEIGHT);
        //Ficha J2.
        this.fichaNombreJ2.setIcon(this.iconoFicha2);
        this.fichaNombreJ2.setBounds(this.FICHAJ2_X, this.FICHAJ2_Y, this.FICHAJ2_WIDTH, this.FICHAJ2_HEIGHT);
        //Nombre J2.
        this.nombreJugador2.setFont(super.FUENTE_2);
        this.nombreJugador2.setBounds(this.NOMBREJ2_X, this.NOMBREJ2_Y, this.NOMBREJ2_WIDTH, this.NOMBREJ2_HEIGHT);
        //Panel de Penalizacion del J1.
        this.panelPenalizacionJ1.setBorder(super.getBlackBorder());
        this.panelPenalizacionJ1.setBackground(Color.white);
        this.panelPenalizacionJ1.setLayout(null);
        this.panelPenalizacionJ1.setBounds(this.PANEL_PENALIZACION_J1_X, this.PANEL_PENALIZACION_J1_Y, this.PANEL_PENALIZACION_J1_WIDTH, this.PANEL_PENALIZACION_J1_HEIGHT);
        //Ficha penalizacion J1.
        this.fichaPenalizacionJ1.setIcon(this.iconoFicha1);
        this.fichaPenalizacionJ1.setBounds(this.FICHAJ1_PENALIZACION_X, this.FICHAJ1_PENALIZACION_Y, this.FICHAJ1_PENALIZACION_WIDTH, this.FICHAJ1_PENALIZACION_HEIGHT);
        //Numero de turnos penalizados J1.
        this.numeroTurnosPenalizadoJ1.setFont(this.FUENTE_3);
        this.numeroTurnosPenalizadoJ1.setBounds(this.NUM_TURNOSJ1_PENALIZACION_X, this.NUM_TURNOSJ1_PENALIZACION_Y, this.NUM_TURNOSJ1_PENALIZACION_WIDTH, this.NUM_TURNOSJ1_PENALIZACION_HEIGHT);
        //Label turnos J1.
        this.labelTurnosJ1.setFont(super.FUENTE_2);
        this.labelTurnosJ1.setBounds(this.LABEL_TURNOSJ1_PENALIZACION_X, this.LABEL_TURNOSJ1_PENALIZACION_Y, this.LABEL_TURNOSJ1_PENALIZACION_WIDTH, this.LABEL_TURNOSJ1_PENALIZACION_HEIGHT); 
        //Panel de Penalizacion del J2.
        this.panelPenalizacionJ2.setBorder(super.getBlackBorder());
        this.panelPenalizacionJ2.setBackground(Color.white);
        this.panelPenalizacionJ2.setLayout(null);
        this.panelPenalizacionJ2.setBounds(this.PANEL_PENALIZACION_J2_X, this.PANEL_PENALIZACION_J2_Y, this.PANEL_PENALIZACION_J2_WIDTH, this.PANEL_PENALIZACION_J2_HEIGHT);
        //Ficha penalizacion J2.
        this.fichaPenalizacionJ2.setIcon(this.iconoFicha2);
        this.fichaPenalizacionJ2.setBounds(this.FICHAJ2_PENALIZACION_X, this.FICHAJ2_PENALIZACION_Y, this.FICHAJ2_PENALIZACION_WIDTH, this.FICHAJ2_PENALIZACION_HEIGHT);
        //Numero de turnos penalizados J2.
        this.numeroTurnosPenalizadoJ2.setFont(this.FUENTE_3);
        this.numeroTurnosPenalizadoJ2.setBounds(this.NUM_TURNOSJ2_PENALIZACION_X, this.NUM_TURNOSJ2_PENALIZACION_Y, this.NUM_TURNOSJ2_PENALIZACION_WIDTH, this.NUM_TURNOSJ2_PENALIZACION_HEIGHT);
        //Label turnos J2.
        this.labelTurnosJ2.setFont(super.FUENTE_2);
        this.labelTurnosJ2.setBounds(this.LABEL_TURNOSJ2_PENALIZACION_X, this.LABEL_TURNOSJ2_PENALIZACION_Y, this.LABEL_TURNOSJ2_PENALIZACION_WIDTH, this.LABEL_TURNOSJ2_PENALIZACION_HEIGHT);
    }
    
    //Metodo que añade los elementos a su respectivo contenedor.
    @Override
    protected void anadirObjetos() {
        
        super.anadirObjetos();
        
        //Nombre y ficha J1.
        super.getPanelNombresJugadores().add(this.fichaNombreJ1);
        super.getPanelNombresJugadores().add(this.nombreJugador1);
        
        //Nombre y ficha J2.
        super.getPanelNombresJugadores().add(this.fichaNombreJ2);
        super.getPanelNombresJugadores().add(this.nombreJugador2);
        
        //Panel Penalizacion J1.
        super.getPanelPenalizaciones().add(this.panelPenalizacionJ1);
        this.panelPenalizacionJ1.add(this.fichaPenalizacionJ1);
        this.panelPenalizacionJ1.add(this.numeroTurnosPenalizadoJ1);
        this.panelPenalizacionJ1.add(this.labelTurnosJ1);

        //Panel Penalizacion J2.
        super.getPanelPenalizaciones().add(this.panelPenalizacionJ2);
        this.panelPenalizacionJ2.add(this.fichaPenalizacionJ2);
        this.panelPenalizacionJ2.add(this.numeroTurnosPenalizadoJ2);
        this.panelPenalizacionJ2.add(this.labelTurnosJ2);  
    } 

    //Getter que devuelve el nombre del J1 actual.
    public String getNombreJugador1() {
        return nombreJugador1.getText();
    }

    //Setter que actualiza el nombre del J1 actual.
    public void setNombreJugador1(String nombreJugador1) {
        this.nombreJugador1.setText(nombreJugador1);
    }

    //Getter que devuelve el nombre del J2 actual.
    public String getNombreJugador2() {
        return nombreJugador2.getText();
    }

    //Setter que actualiza el nombre del J2 actual.
    public void setNombreJugador2(String nombreJugador2) {
        this.nombreJugador2.setText(nombreJugador2);
    }

    //Metodo que devuelve el String actual del numero de turnos penalizados del Jugador 1.
    public String getPenalizacionTurnosJ1() {
        return numeroTurnosPenalizadoJ1.getText();
    }

    //Metodo que modifica el String actual del numero de turnos penalizados del Jugador 1.
    public void setPenalizacionTurnosJ1(String penalizacionTurnosJ1) {
        this.numeroTurnosPenalizadoJ1.setText(penalizacionTurnosJ1);
    }

    //Metodo que devuelve el String actual del numero de turnos penalizados del Jugador 2.
    public String getPenalizacionTurnosJ2() {
        return numeroTurnosPenalizadoJ2.getText();
    }

    //Metodo que modifica el String actual del numero de turnos penalizados del Jugador 2.
    public void setPenalizacionTurnosJ2(String penalizacionTurnosJ2) {
        this.numeroTurnosPenalizadoJ2.setText(penalizacionTurnosJ2);
    }

}
