

package Vistas;

import Controladores.ControladorJuego;
import DatosEstaticos.TextosJuego;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Enrique Sánchez 
 */
public class VistaJuego extends JFrame{

    //Fuente textos
    private final Font FUENTE_1 = new Font("Arial", 1, 15);
    //Fuente boton lanzar dado
    private final Font FUENTE_2 = new Font("Arial", 1, 20);
    private final int TABLERO_X=320, TABLERO_Y=0;
    
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
    private JLabel jugadoresTitulo;
    private JPanel panelPenalizaciones;
    private JLabel penalizacionesTitulo;
    private Tablero panelTableroOca;
    private JPanel panelDadoCubilete;
    private DadoGrafico dadoGrafico;
    private JButton botonLanzarDado; 
    private Border blackline;
    
    private int idioma = 0; //0=español 1=ingles;
    
    private boolean impulsoTirarDado; //0= no; 1= si;
    
    private int numeroFinalDado;
    
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
    
    public void initEscena(){
        panelTableroOca.initEscena();
    }

    private void crearObjetos() {
        //Menus
        this.menuNuevaPartida = new JMenuItem(TextosJuego.MENU_NUEVA_PARTIDA[this.idioma]);
        this.menuNuevaPartida.setMnemonic(KeyEvent.VK_N);
        this.menuGuardarPartida = new JMenuItem(TextosJuego.MENU_GUARDAR_PARTIDA[this.idioma]);
        this.menuGuardarPartida.setMnemonic(KeyEvent.VK_G);
        this.menuCargarPartida = new JMenuItem(TextosJuego.MENU_CARGAR_PARTIDA[this.idioma]);
        this.menuCargarPartida.setMnemonic(KeyEvent.VK_C);
        this.menuSalir = new JMenuItem(TextosJuego.MENU_SALIR[this.idioma]);
        this.menuSalir.setMnemonic(KeyEvent.VK_S); 
        
        this.menuLanzarDado = new JMenuItem(TextosJuego.MENU_LANZAR_DADO[this.idioma]);
        this.menuLanzarDado.setMnemonic(KeyEvent.VK_D);
        
        this.menuPartida = new JMenu(TextosJuego.MENU_PARTIDA[this.idioma]);
        this.menuAcciones = new JMenu(TextosJuego.MENU_ACCIONES[this.idioma]);
        
        this.menuBar = new JMenuBar();
        
        //Panel nombres
        this.panelNombresJugadores = new JPanel();
        this.jugadoresTitulo = new JLabel(TextosJuego.TITULO_JUGADORES[this.idioma]);
        //Panel penalizacion
        this.panelPenalizaciones = new JPanel();
        this.penalizacionesTitulo = new JLabel(TextosJuego.LABEL_PENALIZACIONES[this.idioma]);
        //Tablero
        this.panelTableroOca = new Tablero();
        //Panel dado
        this.panelDadoCubilete = new JPanel();
        this.dadoGrafico = new DadoGrafico(this);
        this.controlador.aniadirHilo(this.dadoGrafico);
        new Thread(this.dadoGrafico).start();
        //Boton dado
        this.botonLanzarDado = new JButton(TextosJuego.BOTON_LANZAR_DADO[this.idioma]);
    }

    private void disenoObjetos() {
        //Frame
        this.setLayout(null);
        this.getContentPane().setBackground(Color.orange);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        //Menus
        this.menuPartida.setFont(this.FUENTE_1);
        this.menuAcciones.setFont(this.FUENTE_1);
        this.menuNuevaPartida.setFont(this.FUENTE_1);
        this.menuGuardarPartida.setFont(this.FUENTE_1);
        this.menuCargarPartida.setFont(this.FUENTE_1);
        this.menuSalir.setFont(this.FUENTE_1);
        this.menuLanzarDado.setFont(this.FUENTE_1);
        //Panel de nombres
        this.blackline = BorderFactory.createLineBorder(Color.black);
        this.panelNombresJugadores.setBorder(blackline);
        this.panelNombresJugadores.setBackground(Color.CYAN);
        this.panelNombresJugadores.setBounds(50, 50, 220, 250);
        this.panelNombresJugadores.setLayout(null);
        //Titulo jugadores
        this.jugadoresTitulo.setFont(this.FUENTE_1);
        this.jugadoresTitulo.setBounds(65, 5, 200, 50);
        //Panel de penalizaciones/tiempo
        this.panelPenalizaciones.setBorder(blackline);
        this.panelPenalizaciones.setBackground(Color.PINK);
        this.panelPenalizaciones.setBounds(50, 350, 220, 350);
        this.panelPenalizaciones.setLayout(null);
        //Titulo penalizaciones
        this.penalizacionesTitulo.setFont(this.FUENTE_1);
        this.penalizacionesTitulo.setBounds(50, 5, 200, 50);
        //Tablero oca
        this.panelTableroOca.setLocation(this.TABLERO_X, this.TABLERO_Y);
        //Panel dado y cubilete
        this.panelDadoCubilete.setBorder(blackline);
        this.panelDadoCubilete.setBackground(Color.MAGENTA);
        this.panelDadoCubilete.setBounds(1160, 50, 250, 450);
        this.panelDadoCubilete.setLayout(null);        
        //Boton lanzar dado
        this.botonLanzarDado.setFont(this.FUENTE_2);
        this.botonLanzarDado.setBackground(Color.green);
        this.botonLanzarDado.setBounds(1180, 600, 200, 100);

    }

    private void anadirObjetos() {
        //Menus
        this.menuPartida.add(this.menuNuevaPartida);
        this.menuPartida.add(this.menuGuardarPartida);
        this.menuPartida.add(this.menuCargarPartida);
        this.menuPartida.add(this.menuSalir);
        this.menuAcciones.add(this.menuLanzarDado);
        this.menuBar.add(this.menuPartida);
        this.menuBar.add(this.menuAcciones);
        this.setJMenuBar(this.menuBar);
        //Titulo nombres
        this.panelNombresJugadores.add(this.jugadoresTitulo);
        //Titulo penalizaciones
        this.panelPenalizaciones.add(this.penalizacionesTitulo);
        //DadoGrafico
        this.panelDadoCubilete.add(this.dadoGrafico);
        //Paneles
        this.add(this.panelNombresJugadores);
        this.add(this.panelPenalizaciones);
        this.add(this.panelTableroOca);
        this.add(this.panelDadoCubilete);
        //Boton lanzar dado
        this.add(this.botonLanzarDado);
    }

    private void anadirEscuchadores() {
        this.addWindowListener(this.controlador);
        this.botonLanzarDado.addActionListener(this.controlador);
    }

    public boolean getImpulsoTirarDado() {
        return impulsoTirarDado;
    }

    public void setImpulsoTirarDado(boolean impulso) {
        this.impulsoTirarDado = impulso;
    }

    public synchronized void notificarTiradaDado() {
        dadoGrafico.notificarTirada();
    }

    public int getNumeroFinalDado() {
        return numeroFinalDado;
    }

    public void setNumeroFinalDado(int numeroFinalDado) {
        this.numeroFinalDado = numeroFinalDado;
    }
    
    //Metodo que avisa al controlador cuando la animacion del dado a finalizado.
    public void finalAnimacionDado() {
        this.controlador.eventoFinalizacionDado();
    }
    
    public void bloquearBoton(boolean b){
        this.botonLanzarDado.setEnabled(b);
    }
    
    //Getters elementos paneles.

    public JPanel getPanelNombresJugadores() {
        return this.panelNombresJugadores;
    }

    public JPanel getPanelPenalizaciones() {
        return this.panelPenalizaciones;
    }
    
    //Metodo que devuelve el objeto border utilizado.
    public Border getBlackBorder(){
        return this.blackline;
    }
   
}
