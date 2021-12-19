

package Vistas;

import Controladores.ControladorJuego;
import DatosEstaticos.TextosJuego;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
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
    
    private final int WIDTH = 1450, HEIGHT = 865;
    
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
    
    //cola de dibujado por comodidad para una vez configurados los conmponentes sean añadidos para al finalizar la configuracion se añadan al frame
    private List<Component> colaDibujado;
    
    private final int idioma = 0; //0=español 1=ingles;
    
    
    public VistaJuego(ControladorJuego control){
        this.controlador = control;
        iniciarVista();
    }

    private void iniciarVista() {
        
        colaDibujado = new ArrayList<Component>();
        
        crearObjetos();
        disenoObjetos();
        anadirObjetos();
        anadirEscuchadores();
        
        
        this.setVisible(true);

    }
    
    public void initEscena(){
        panelTableroOca.initEscena();
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
        
        //configuracion del frame
        this.setLayout(null);
        this.getContentPane().setBackground(Color.orange);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        
        //configuracion del tablero
        final int tableroX = 320, tableroY = 0;
        
        this.panelTableroOca.setLocation(tableroX, tableroY);
        colaDibujado.add(panelTableroOca);
        
        
        
    }

    private void anadirObjetos() {
        
        //re recorren todos los componoentes de la cola de dibujado para añadirlos al frame
        for (Component i:colaDibujado) {
            this.add(i);
        }
        
    }

    private void anadirEscuchadores() {
        
        this.addWindowListener(controlador);
    }
    
}
