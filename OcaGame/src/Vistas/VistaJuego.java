

package Vistas;

import Controladores.ControladorJuego;
import DatosEstaticos.TextosJuego;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Enrique Sánchez 
 */
public class VistaJuego extends JFrame{
    
    private ControladorJuego controlador;
    
    private JMenuBar menuBar;
    private JMenu menuPartida;
    private JMenu menuAcciones;
    private JMenuItem menuNuevaPartida;
    private JMenuItem menuGuardarPartida;
    private JMenuItem menuCargarPartida;
    private JMenuItem menuSalir;
    private JMenuItem menuLanzarDado;
    private JPanel panelNombresJugadores;
    private JLabel nombreJugador1;
    private JPanel panelPenalizaciones;
    private JLabel penalizaciones;
    private Tablero panelTableroOca;
    private JPanel panelDadoCubilete;
    private JButton botonLanzarDado; 
    
    private int idioma = 0; //0=español 1=ingles;
    
    
    public VistaJuego(ControladorJuego control){
        this.controlador = control;
        iniciarVista();
    }

    private void iniciarVista() {
        
        crearObjetos();
        disenoObjetos();
        anadirObjetos();
        anadirEscuchadores();
        
        
        this.setVisible(true);
    }

    private void crearObjetos() {
        this.menuNuevaPartida = new JMenuItem(TextosJuego.MENU_NUEVA_PARTIDA[this.idioma]);
        this.menuGuardarPartida = new JMenuItem(TextosJuego.MENU_GUARDAR_PARTIDA[this.idioma]);
        this.menuCargarPartida = new JMenuItem(TextosJuego.MENU_CARGAR_PARTIDA[this.idioma]);
        this.menuSalir = new JMenuItem(TextosJuego.MENU_SALIR[this.idioma]);
        this.menuLanzarDado = new JMenuItem(TextosJuego.MENU_LANZAR_DADO[this.idioma]);
        this.menuPartida = new JMenu(TextosJuego.MENU_PARTIDA[this.idioma]);
        this.menuAcciones = new JMenu(TextosJuego.MENU_ACCIONES[this.idioma]);
        this.menuBar = new JMenuBar();
        
        this.panelNombresJugadores = new JPanel();
        this.nombreJugador1 = new JLabel("Antonio");
        
        this.panelPenalizaciones = new JPanel();
        this.penalizaciones = new JLabel(TextosJuego.LABEL_PENALIZACIONES[this.idioma]);
        
        this.panelTableroOca = new Tablero();
        
        this.panelDadoCubilete = new JPanel();
        this.botonLanzarDado = new JButton(TextosJuego.BOTON_LANZAR_DADO[this.idioma]);
    }

    private void disenoObjetos() {
        this.setLayout(null);
        this.getContentPane().setBackground(Color.orange);
        this.setSize(1170, 800);
        
        this.panelTableroOca.setBounds(185, 0, this.panelTableroOca.WIDTH, this.panelTableroOca.HEIGHT);
    }

    private void anadirObjetos() {
        this.add(this.panelTableroOca);
    }

    private void anadirEscuchadores() {
    }
    
}
