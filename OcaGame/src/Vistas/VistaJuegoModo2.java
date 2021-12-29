
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
  
    private final int FICHAJ1_X=10, FICHAJ1_Y=65; //Posicion de la ficha J1.
    private final int FICHAJ1_WIDTH = 50, FICHAJ1_HEIGHT = 50; //Medidas de la ficha J1.
    
    private final int NOMBREJ1_X=70, NOMBREJ1_Y=65; //Posicion del nombre de J1.
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
    
    private final Color COLOR_ENFASIS_TURNO = new Color(228, 253,0);
    private final Color COLOR_NORMAL_TURNO = new Color(134,115,129);
    
    //Atributos de la clase.
    
    private JLabel marco_enfasis_nombreJ1;
    private JLabel fichaNombreJ1;
    private JLabel nombreJugador1;
    private JLabel marco_enfasis_nombreJ2;
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
    
    /**
     * Constructor.
     * @param control
     * @param idioma
     * @param jugador1
     * @param jugador2 
     */
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
        this.marco_enfasis_nombreJ1 = new JLabel();
        this.fichaNombreJ1 = new JLabel();
        this.nombreJugador1 = new JLabel(super.nombresJugadores[0], JLabel.LEFT);
        
        this.marco_enfasis_nombreJ2 = new JLabel();
        this.fichaNombreJ2 = new JLabel();
        this.nombreJugador2 = new JLabel(super.nombresJugadores[1], JLabel.LEFT);
        
        //Panel penalizacion J1.
        this.panelPenalizacionJ1 = new JPanel();
        this.fichaPenalizacionJ1 = new JLabel();
        this.numeroTurnosPenalizadoJ1 = new JLabel("-", JLabel.CENTER);
        this.labelTurnosJ1 = new JLabel(TextosJuego.LABEL_TURNOS[super.getIdioma()], JLabel.CENTER);
        
        //Panel penalizacion J2. 
        this.panelPenalizacionJ2 = new JPanel();
        this.fichaPenalizacionJ2 = new JLabel();
        this.numeroTurnosPenalizadoJ2 = new JLabel("-", JLabel.CENTER);
        this.labelTurnosJ2 = new JLabel(TextosJuego.LABEL_TURNOS[super.getIdioma()], JLabel.CENTER); 
    }

    //Metodo que da formato a los atributos necesarios de la clase.
    @Override
    protected void disenoObjetos() {
        
        super.disenoObjetos();
        
        //marco enfasis nombre J1
        this.marco_enfasis_nombreJ1.setIcon(new ImageIcon(Constantes.PATH_MARCO_ENFASIS_NOMBRE));
        this.marco_enfasis_nombreJ1.setSize(243, 70);
        this.marco_enfasis_nombreJ1.setLocation(5, 55);
        //Ficha J1.
        this.fichaNombreJ1.setIcon(this.iconoFicha1);
        this.fichaNombreJ1.setBounds(this.FICHAJ1_X, this.FICHAJ1_Y, this.FICHAJ1_WIDTH, this.FICHAJ1_HEIGHT);
        //Nombre J1. 
        this.nombreJugador1.setFont(super.FUENTE_2);
        this.nombreJugador1.setBounds(this.NOMBREJ1_X, this.NOMBREJ1_Y, this.NOMBREJ1_WIDTH, this.NOMBREJ1_HEIGHT);
        //marco enfasis nombre J2
        this.marco_enfasis_nombreJ2.setIcon(new ImageIcon(Constantes.PATH_MARCO_ENFASIS_NOMBRE));
        this.marco_enfasis_nombreJ2.setSize(243, 70);
        this.marco_enfasis_nombreJ2.setLocation(5, 141);
        this.marco_enfasis_nombreJ2.setVisible(false);
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
        
        super.getPanelNombresJugadores().add(this.marco_enfasis_nombreJ1);
        super.getPanelNombresJugadores().add(this.fichaNombreJ1);
        super.getPanelNombresJugadores().add(this.nombreJugador1);
        
        //Nombre y ficha J2.
        super.getPanelNombresJugadores().add(this.marco_enfasis_nombreJ2);
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

    /**
     * Getter que devuelve el nombre del J1 actual.
     * @return String nombreJugador1
     */
    public String getNombreJugador1() {
        return nombreJugador1.getText();
    }

    /**
     * Setter que actualiza el nombre del J1 actual.
     * @param nombreJugador1 
     */
    public void setNombreJugador1(String nombreJugador1) {
        this.nombreJugador1.setText(nombreJugador1);
    }

    /**
     * Getter que devuelve el nombre del J2 actual.
     * @return String nombreJugador2
     */
    public String getNombreJugador2() {
        return nombreJugador2.getText();
    }

    /**
     * Setter que actualiza el nombre del J2 actual.
     * @param nombreJugador2 
     */
    public void setNombreJugador2(String nombreJugador2) {
        this.nombreJugador2.setText(nombreJugador2);
    }

    /**
     * Metodo que devuelve el String actual del numero de turnos penalizados del Jugador 1.
     * @return String numeroTurnosPenalizadoJ1
     */
    public String getPenalizacionTurnosJ1() {
        return numeroTurnosPenalizadoJ1.getText();
    }

    /**
     * Metodo que modifica el String actual del numero de turnos penalizados del Jugador 1.
     * @param penalizacionTurnosJ1 
     */
    public void setPenalizacionTurnosJ1(String penalizacionTurnosJ1) {
        this.numeroTurnosPenalizadoJ1.setText(penalizacionTurnosJ1);
    }

    /**
     * Metodo que devuelve el String actual del numero de turnos penalizados del Jugador 2.
     * @return String numeroTurnosPenalizadoJ2
     */
    public String getPenalizacionTurnosJ2() {
        return numeroTurnosPenalizadoJ2.getText();
    }

    /**
     * Metodo que modifica el String actual del numero de turnos penalizados del Jugador 2.
     * @param penalizacionTurnosJ2 
     */
    public void setPenalizacionTurnosJ2(String penalizacionTurnosJ2) {
        this.numeroTurnosPenalizadoJ2.setText(penalizacionTurnosJ2);
    }
    
    /**
     * Metodo que enfatiza con un marco y color el nombre del jugador al que le pertenece el turno.
     * @param jugador 
     */
    public void enfatizarNombreTurno(int jugador){
        
        if(jugador == Constantes.JUGADOR_1){
            
            normalizarNombresTurno(Constantes.JUGADOR_2);
            nombreJugador1.setForeground(COLOR_ENFASIS_TURNO);
            marco_enfasis_nombreJ1.setVisible(true);
            
        } if(jugador == Constantes.JUGADOR_2){
            
            normalizarNombresTurno(Constantes.JUGADOR_1);
            nombreJugador2.setForeground(COLOR_ENFASIS_TURNO);
            marco_enfasis_nombreJ2.setVisible(true);

        }
    
    }
    
    /**
     * Metodo que hace variar el marco y color en los nombres dependiendo el jugador que tiene el turno.
     * @param jugador 
     */
    private void normalizarNombresTurno(int jugador){
        
        if(jugador == Constantes.JUGADOR_1){
            nombreJugador1.setForeground(COLOR_NORMAL_TURNO);
            marco_enfasis_nombreJ1.setVisible(false);
        } if(jugador == Constantes.JUGADOR_2){
            nombreJugador2.setForeground(COLOR_NORMAL_TURNO);
            marco_enfasis_nombreJ2.setVisible(false);
        }

    }
    
    /**
     * Metodo setter que actualiza las penalizaciones de los jugadores.
     * @param jugador
     * @param turnos 
     */
    public void actualizarPenalizacionesJugador(int jugador, int turnos){
        
        if(jugador == Constantes.JUGADOR_1){
            numeroTurnosPenalizadoJ1.setText(String.valueOf(turnos));
        } if(jugador == Constantes.JUGADOR_2){
            numeroTurnosPenalizadoJ2.setText(String.valueOf(turnos));
        }
        
    }

}
