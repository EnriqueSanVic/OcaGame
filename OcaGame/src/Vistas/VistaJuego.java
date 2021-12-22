
package Vistas;

import Vistas.TableroSystem.Tablero;
import Controladores.ControladorJuego;
import DatosEstaticos.Constantes;
import DatosEstaticos.TextosJuego;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 *
 * @author Enrique Sánchez 
 */
public abstract class VistaJuego extends JFrame{
    
    //Constantes de configuracion.
    protected final Font FUENTE_1 = new Font("Arial", 1, 15); //Textos.
    protected final Font FUENTE_2 = new Font("Arial", 1, 22); //Boton dado.
    protected final Font FUENTE_3 = new Font("Arial", 1, 50); //Numero penalizacion

    
    private final Color COLOR_MENU_BAR = new Color(237,134,253);
    
    private final int TABLERO_X=320, TABLERO_Y=0; //Posicion del tablero.
    private final int FRAME_WIDTH = 1450, FRAME_HEIGHT = 860; //Medidas del frame.
    
    private final int PANEL_NOMBRE__X=50, PANEL_NOMBRE_Y=50; //Posicion del panel Nombre.
    private final int PANEL_NOMBRE_WIDTH = 220, PANEL_NOMBRE_HEIGHT = 250; //Medidas del panel Nombre.
    
    private final int TITULO_JUGADORES_X=0, TITULO_JUGADORES_Y=5; //Posicion del label Jugadores.
    private final int TITULO_JUGADORES_WIDTH = 220, TITULO_JUGADORES_HEIGHT = 50; //Medidas del label Jugadores.

    private final int PANEL_PENALIZACIONES_X=50, PANEL_PENALIZACIONES_Y=350; //Posicion del panel Penalizaciones.
    private final int PANEL_PENALIZACIONES_WIDTH = 220, PANEL_PENALIZACIONES_HEIGHT = 350; //Medidas del panel Penalizaciones.
    
    private final int TITULO_PENALIZACIONES_X=0, TITULO_PENALIZACIONES_Y=5; //Posicion del label Penalizaciones.
    private final int TITULO_PENALIZACIONES_WIDTH = 220, TITULO_PENALIZACIONES_HEIGHT = 50; //Medidas del label Penalizaciones.

    private final int PANEL_DADO_X=1160, PANEL_DADO_Y=50; //Posicion del panel del Dado.
    private final int PANEL_DADO_WIDTH = 250, PANEL_DADO_HEIGHT = 450; //Medidas del panel del Dado.

    private final int BOTON_DADO_X=1160, BOTON_DADO_Y=550; //Posicion del panel del Boton lanzar dado.
    private final int BOTON_DADO_WIDTH = 250, BOTON_DADO_HEIGHT = 76; //Medidas del Boton lanzar dado.
    
    
    private final ImageIcon ICONO_BOTON_TIRAR = new ImageIcon(Constantes.PATH_ICONO_BTN_TIRAR); //icono del boton de tirar dado
    private final ImageIcon ICONO_BOTON_TIRAR_HOVER = new ImageIcon(Constantes.PATH_ICONO_BTN_TIRAR_HOVER); //icono del boton de tirar dado imagen acion 
    private final ImageIcon ICONO_BOTON_TIRAR_DISABLE = new ImageIcon(Constantes.PATH_ICONO_BTN_TIRAR_DISABLE); //icono del boton de tirar dado cuando está desactivado
    
    private final int IDIOMA = 0; //Idioma. 0=español 1=ingles;
    
    //Atributos de la clase.
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
    
    private Tablero tablero;
    
    private JPanel panelDadoCubilete;
    private DadoGrafico dadoGrafico;
    private JButton botonLanzarDado; 
    
    private Border blackline; //Linea negra para el borde de ciertos elementos.
    
    private boolean impulsoTirarDado; //Impulso de tirada de dado. 0= no hay; 1= si hay;
    
    private int numeroFinalDado; //Numero que saldrá en la tirada del dado.
    
    //Constructor.
    public VistaJuego(ControladorJuego control){
        this.controlador = control;
        iniciarVista();
    }

    //Metodo que crea la vista del juego.
    private void iniciarVista() { 
        crearObjetos();
        disenoObjetos();
        anadirObjetos();
        anadirEscuchadores();

        this.setVisible(true);
    }
    
    public void initEscena(){
        tablero.initEscena();
    }

    //Metodo que inicializa elementos de la vista general del juego.
    protected void crearObjetos() {
        //MenuItems Partida.
        this.menuNuevaPartida = new JMenuItem(TextosJuego.MENU_NUEVA_PARTIDA[this.IDIOMA]);
        this.menuNuevaPartida.setMnemonic(KeyEvent.VK_N);
        this.menuGuardarPartida = new JMenuItem(TextosJuego.MENU_GUARDAR_PARTIDA[this.IDIOMA]);
        this.menuGuardarPartida.setMnemonic(KeyEvent.VK_G);
        this.menuCargarPartida = new JMenuItem(TextosJuego.MENU_CARGAR_PARTIDA[this.IDIOMA]);
        this.menuCargarPartida.setMnemonic(KeyEvent.VK_C);
        this.menuSalir = new JMenuItem(TextosJuego.MENU_SALIR[this.IDIOMA]);
        this.menuSalir.setMnemonic(KeyEvent.VK_S); 
        //MenuItems Acciones.
        this.menuLanzarDado = new JMenuItem(TextosJuego.MENU_LANZAR_DADO[this.IDIOMA]);
        this.menuLanzarDado.setMnemonic(KeyEvent.VK_D);
        //Menus.
        this.menuPartida = new JMenu(TextosJuego.MENU_PARTIDA[this.IDIOMA]);
        this.menuAcciones = new JMenu(TextosJuego.MENU_ACCIONES[this.IDIOMA]);
        //MenuBar.
        this.menuBar = new JMenuBar();
        //Panel nombres.
        this.panelNombresJugadores = new JPanel();
        this.jugadoresTitulo = new JLabel(TextosJuego.TITULO_JUGADORES[this.IDIOMA], JLabel.CENTER);
        //Panel penalizacion.
        this.panelPenalizaciones = new JPanel();
        this.penalizacionesTitulo = new JLabel(TextosJuego.LABEL_PENALIZACIONES[this.IDIOMA], JLabel.CENTER);
        //Tablero Oca.
        this.tablero = new Tablero();
        //Panel dado.
        this.panelDadoCubilete = new JPanel();
        this.dadoGrafico = new DadoGrafico(this);
        this.controlador.aniadirHilo(this.dadoGrafico);
        new Thread(this.dadoGrafico).start();
        //Boton dado.
        this.botonLanzarDado = new JButton();
        
        
    }

    //Metodo que da un diseño a los elementos de la vista general del juego.
    protected void disenoObjetos() {
        //Frame.
        this.setLayout(null);
        this.getContentPane().setBackground(Color.orange);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(false);
        ponerMedioPantalla(this);
        
        //Menu bar
        this.menuBar.setBackground(COLOR_MENU_BAR);
        this.menuBar.setBorderPainted(false);

        //Menus
        this.menuPartida.setFont(this.FUENTE_1);
        this.menuPartida.setBackground(COLOR_MENU_BAR);
        this.menuAcciones.setFont(this.FUENTE_1);
        this.menuAcciones.setBackground(COLOR_MENU_BAR);
        //MenuItems
        this.menuNuevaPartida.setFont(this.FUENTE_1);
        this.menuNuevaPartida.setBackground(COLOR_MENU_BAR);
        this.menuGuardarPartida.setFont(this.FUENTE_1);
        this.menuGuardarPartida.setBackground(COLOR_MENU_BAR);
        this.menuCargarPartida.setFont(this.FUENTE_1);
        this.menuCargarPartida.setBackground(COLOR_MENU_BAR);
        this.menuSalir.setFont(this.FUENTE_1);
        this.menuSalir.setBackground(COLOR_MENU_BAR);
        this.menuLanzarDado.setFont(this.FUENTE_1);
        this.menuLanzarDado.setBackground(COLOR_MENU_BAR);
        //Panel de nombres.
        this.blackline = BorderFactory.createLineBorder(Color.BLACK);
        this.panelNombresJugadores.setBorder(this.blackline);
        this.panelNombresJugadores.setBackground(Color.CYAN);
        this.panelNombresJugadores.setBounds(this.PANEL_NOMBRE__X, this.PANEL_NOMBRE_Y, this.PANEL_NOMBRE_WIDTH, this.PANEL_NOMBRE_HEIGHT);
        this.panelNombresJugadores.setLayout(null);
        //Titulo Jugadores.
        this.jugadoresTitulo.setFont(this.FUENTE_1);
        this.jugadoresTitulo.setBounds(this.TITULO_JUGADORES_X, this.TITULO_JUGADORES_Y, this.TITULO_JUGADORES_WIDTH, this.TITULO_JUGADORES_HEIGHT);
        //Panel de Penalizaciones/Cuenta atrás.
        this.panelPenalizaciones.setBorder(blackline);
        this.panelPenalizaciones.setBackground(Color.PINK);
        this.panelPenalizaciones.setBounds(this.PANEL_PENALIZACIONES_X, this.PANEL_PENALIZACIONES_Y, this.PANEL_PENALIZACIONES_WIDTH, this.PANEL_PENALIZACIONES_HEIGHT);
        this.panelPenalizaciones.setLayout(null);
        //Titulo Penalizaciones.
        this.penalizacionesTitulo.setFont(this.FUENTE_1);
        this.penalizacionesTitulo.setBounds(this.TITULO_PENALIZACIONES_X, this.TITULO_PENALIZACIONES_Y, this.TITULO_PENALIZACIONES_WIDTH, this.TITULO_PENALIZACIONES_HEIGHT);
        //Tablero Oca. (el propio tablero se ajusta solo).
        this.tablero.setLocation(this.TABLERO_X, this.TABLERO_Y);
        //Panel dado y cubilete.
        this.panelDadoCubilete.setBorder(blackline);
        this.panelDadoCubilete.setBackground(Color.MAGENTA);
        this.panelDadoCubilete.setBounds(this.PANEL_DADO_X, this.PANEL_DADO_Y, this.PANEL_DADO_WIDTH, this.PANEL_DADO_HEIGHT);
        this.panelDadoCubilete.setLayout(null);        
        //Boton Lanzar Dado.
        this.botonLanzarDado.setFont(this.FUENTE_2);
        this.botonLanzarDado.setBounds(this.BOTON_DADO_X, this.BOTON_DADO_Y, this.BOTON_DADO_WIDTH, this.BOTON_DADO_HEIGHT);
        this.botonLanzarDado.setOpaque(false);
        this.botonLanzarDado.setBorderPainted(false);
        this.botonLanzarDado.setFocusPainted(false);
        this.botonLanzarDado.setContentAreaFilled(false);
        
        this.botonLanzarDado.setText(TextosJuego.BOTON_LANZAR_DADO[this.IDIOMA]);
        this.botonLanzarDado.setForeground(Color.BLACK);
        this.botonLanzarDado.setActionCommand(TextosJuego.BOTON_LANZAR_DADO[0]);
        this.botonLanzarDado.setIcon(ICONO_BOTON_TIRAR);
        this.botonLanzarDado.setDisabledIcon(ICONO_BOTON_TIRAR_DISABLE);
        this.botonLanzarDado.setHorizontalTextPosition(SwingConstants.CENTER);
        
        
        
        /*
        
            Definición de los métodos de escuchador para el boton de lanzar dado, está implementado como unca clase anonima
            debido a que es para aplicar unas funcionalidades puramente visuales que no tiene nada que ver con el resto de 
            acciones del juego
        */
        this.botonLanzarDado.addMouseListener(new MouseAdapter() {
            private final int HUNDIMIENTO_BOTON = 5;

            public void mouseEntered(MouseEvent e) {
                botonLanzarDado.setIcon(ICONO_BOTON_TIRAR_HOVER);
                botonLanzarDado.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e) {
                botonLanzarDado.setIcon(ICONO_BOTON_TIRAR);
                botonLanzarDado.setForeground(Color.BLACK);
            }

            public void mousePressed(MouseEvent e) {
                botonLanzarDado.setLocation(botonLanzarDado.getLocation().x, botonLanzarDado.getLocation().y + HUNDIMIENTO_BOTON);
            }

            public void mouseReleased(MouseEvent e) {
                botonLanzarDado.setLocation(botonLanzarDado.getLocation().x, botonLanzarDado.getLocation().y - HUNDIMIENTO_BOTON);
            }
        });
        
        
    }

    //Metodo que añade elementos a la vista general del juego.
    protected void anadirObjetos() {
        //Menus.
        this.menuPartida.add(this.menuNuevaPartida);
        this.menuPartida.add(this.menuGuardarPartida);
        this.menuPartida.add(this.menuCargarPartida);
        this.menuPartida.add(this.menuSalir);
        this.menuAcciones.add(this.menuLanzarDado);
        //MenuBar.
        this.menuBar.add(this.menuPartida);
        this.menuBar.add(this.menuAcciones);
        this.setJMenuBar(this.menuBar);
        //Panel Nombres
        this.panelNombresJugadores.add(this.jugadoresTitulo);
        //Panel penalizaciones
        this.panelPenalizaciones.add(this.penalizacionesTitulo);
        //Panel DadoGrafico
        this.panelDadoCubilete.add(this.dadoGrafico);
        //Paneles al frame
        this.add(this.panelNombresJugadores);
        this.add(this.panelPenalizaciones);
        this.add(this.tablero);
        this.add(this.panelDadoCubilete);
        //Boton lanzar dado al frame
        this.add(this.botonLanzarDado);
    }

    //Metodo que añade el escuchador necesario a ciertos elemento.
    protected void anadirEscuchadores() {
        this.addWindowListener(this.controlador);
        this.botonLanzarDado.addActionListener(this.controlador);
    }

    //Metodo que devuelve el resultado booleano de impulso tirar dado, 
    //que indica que se ha iniciado o finalizado la animacion de la tirada del dado.
    public boolean getImpulsoTirarDado() {
        return impulsoTirarDado;
    }
    
    //Metodo que actualiza el estado booleano del impulso tirar dado, 
    //que indica que se ha iniciado o finalizado la animacion de la tirada del dado.
    public void setImpulsoTirarDado(boolean impulso) {
        this.impulsoTirarDado = impulso;
    }

    public synchronized void notificarTiradaDado() {
        dadoGrafico.notificarTirada();
    }

    //Metodo que retorna el numero final del dado que ha salido en una tirada.
    public int getNumeroFinalDado() {
        return numeroFinalDado;
    }

    //Metodo que actualiza el numero final del dado que ha salido en una tirada.
    public void setNumeroFinalDado(int numeroFinalDado) {
        this.numeroFinalDado = numeroFinalDado;
    }
    
    //Metodo que avisa al controlador cuando la animacion del dado a finalizado.
    public void finalAnimacionDado() {
        this.controlador.eventoFinalizacionDado();
    }
    
    //Metodo que bloquea o desbloqueael boton de lanzar dado dependiendo el parametro enviado.
    public void bloquearBoton(boolean b){
        this.botonLanzarDado.setEnabled(b);
    }

    //Getter del JPanel de nombres de jugadores.
    public JPanel getPanelNombresJugadores() {
        return this.panelNombresJugadores;
    }

    //Getter del JPanel de penalizaciones.
    public JPanel getPanelPenalizaciones() {
        return this.panelPenalizaciones;
    }
    
    //Metodo que devuelve el objeto border utilizado.
    public Border getBlackBorder(){
        return this.blackline;
    }

    //Metodo que devuelve el idioma escogido por el usuario.
    public int getIdioma() {
        return this.IDIOMA;
    }
   
    
    private void ponerMedioPantalla(Frame ventana) {
        
        int width, height;
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        
        width = (pantalla.width/2) - (ventana.getSize().width/2);
        
        height = (pantalla.height/2) - (ventana.getSize().height/2);
        
        ventana.setLocation(width, height);

        
    }
    
    public void crearPuntero(int nCasilla, MouseListener escuchador){
        tablero.crearPuntero(nCasilla, escuchador);
    }
    
    public void eliminarPuntero(){
        tablero.eliminarPuntero();
    }
    
}
