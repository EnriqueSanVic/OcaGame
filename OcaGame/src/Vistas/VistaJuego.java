
package Vistas;

import Vistas.TableroSystem.Tablero;
import Controladores.ControladorJuego;
import Controladores.ControladorMenu;
import DatosEstaticos.Constantes;
import DatosEstaticos.TextosJuego;
import Utilidades.UtilidadesGraficas;
import Utilidades.VentanaConCorrecion;
import Vistas.TableroSystem.Ficha;
import Vistas.TableroSystem.ManejadorFicha;
import Vistas.TableroSystem.NotificableTablero;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Enrique Sánchez 
 */
public abstract class VistaJuego extends JFrame implements VentanaConCorrecion{
    
    //Constantes de configuracion. 
    //Broadway/Arial
    protected final Font FUENTE_1 = new Font("Broadway", 1, 15); //Textos.
    protected final Font FUENTE_2 = new Font("Broadway", 1, 22); //Boton dado.
    protected final Font FUENTE_3 = new Font("Broadway", 1, 50); //Numero penalizacion
    
    private final Color COLOR_MENU_BAR = new Color(237,134,253); //Color para el MenuBar.
    private final Color COLOR_BORDES = new Color(110,7,92);
    
    private final ImageIcon ICONO_BOTON_TIRAR = new ImageIcon(Constantes.PATH_ICONO_BTN_TIRAR); //icono del boton de tirar dado
    private final ImageIcon ICONO_BOTON_TIRAR_HOVER = new ImageIcon(Constantes.PATH_ICONO_BTN_TIRAR_HOVER); //icono del boton de tirar dado imagen acion 
    private final ImageIcon ICONO_BOTON_TIRAR_DISABLE = new ImageIcon(Constantes.PATH_ICONO_BTN_TIRAR_DISABLE); //icono del boton de tirar dado cuando está desactivado
    
    private final ImageIcon ICONO_PANEL_DADO = new ImageIcon(Constantes.PATH_ICONO_PANEL_DADO); //panel del dado(bordes)
    private final ImageIcon ICONO_BOTON_FOURNIER_HOVER = new ImageIcon(Constantes.PATH_ICONO_BOTON_FOURNIER_HOVER);
    private final ImageIcon ICONO_BOTON_FOURNIER = new ImageIcon(Constantes.PATH_ICONO_BOTON_FOURNIER); //icono del boton de Fournier.

    private final int FRAME_WIDTH_ORIGINAL = 1450, FRAME_HEIGHT_ORIGINAL = 800; //Medidas del frame.
    
    private final int CORRECCION_FRAME_WIDTH = 18, CORRECCION_FRAME_HEIGHT = 72;
    
    private final int FRAME_WIDTH = FRAME_WIDTH_ORIGINAL + CORRECCION_FRAME_WIDTH, FRAME_HEIGHT = FRAME_HEIGHT_ORIGINAL + CORRECCION_FRAME_HEIGHT;
    
    private final int PANEL_NOMBRE__X=50, PANEL_NOMBRE_Y=50; //Posicion del panel Nombre.
    private final int PANEL_NOMBRE_WIDTH = 220, PANEL_NOMBRE_HEIGHT = 250; //Medidas del panel Nombre.
    
    private final int TITULO_JUGADORES_X=0, TITULO_JUGADORES_Y=5; //Posicion del label Jugadores.
    private final int TITULO_JUGADORES_WIDTH = 220, TITULO_JUGADORES_HEIGHT = 50; //Medidas del label Jugadores.

    private final int PANEL_PENALIZACIONES_X=50, PANEL_PENALIZACIONES_Y=350; //Posicion del panel Penalizaciones.
    private final int PANEL_PENALIZACIONES_WIDTH = 220, PANEL_PENALIZACIONES_HEIGHT = 350; //Medidas del panel Penalizaciones.
    
    private final int TITULO_PENALIZACIONES_X=0, TITULO_PENALIZACIONES_Y=5; //Posicion del label Penalizaciones.
    private final int TITULO_PENALIZACIONES_WIDTH = 220, TITULO_PENALIZACIONES_HEIGHT = 50; //Medidas del label Penalizaciones.
    
    private final int TABLERO_X=320, TABLERO_Y=0; //Posicion del tablero.

    private final int PANEL_DADO_X=1160, PANEL_DADO_Y=50; //Posicion del panel del Dado.
    private final int PANEL_DADO_WIDTH = 250, PANEL_DADO_HEIGHT = 450; //Medidas del panel del Dado.

    private final int BOTON_DADO_X=1160, BOTON_DADO_Y=530; //Posicion del panel del Boton lanzar dado.
    private final int BOTON_DADO_WIDTH = 250, BOTON_DADO_HEIGHT = 76; //Medidas del Boton lanzar dado.
    
    private final int LABEL_INSTRUCCIONES_X=1175, LABEL_INSTRUCCIONES_Y=645; //Posicion del label Instrucciones.
    private final int LABEL_INSTRUCCIONES_WIDTH = 220, LABEL_INSTRUCCIONES_HEIGHT = 50; //Medidas del label Instrucciones.
    
    private final int BOTON_FOURNIER_X=1175, BOTON_FOURNIER_Y=690; //Posicion del panel del Boton Fournier que despliega las instrucciones de cada casilla.
    private final int BOTON_FOURNIER_WIDTH = 220, BOTON_FOURNIER_HEIGHT =65; //Medidas del Boton Fournier que despliega las instrucciones de cada casilla.
    
    private final int idioma; //Idioma. 0=español 1=ingles;
    
    private final Border bordePanel= BorderFactory.createLineBorder(COLOR_BORDES, 4);

    private final Border blackline = BorderFactory.createLineBorder(COLOR_BORDES, 3); //Linea negra para el borde de ciertos elementos.
    
    private final Border blacklineTablero = new MatteBorder(3,2,3,2, COLOR_BORDES );
    
    //Atributos de la clase.
    protected ControladorJuego controlador;
    
    protected ControladorMenu controladorMenu;
    
    private PanelFondo fondoVistaJuego;
    
    private JMenuBar menuBar;
    
    private JMenu menuPartida;
    private JMenu menuAcciones;
    
    private JMenuItem menuNuevaPartida;
    private JMenuItem menuGuardarPartida;
    private JMenuItem menuCargarPartida;
    private JMenuItem menuSalir;
    
    private JMenuItem menuLanzarDado;
    private JMenuItem menuPantallaCompleta;
    
    private PanelNombres panelNombresJugadores;
    private JLabel jugadoresTitulo;
    
    private PanelPenalizaciones panelPenalizaciones;
    private JLabel penalizacionesTitulo;
    
    protected Tablero tablero;
    
    private JLabel fondoPanelDado;
    
    private JPanel panelDadoCubilete;
    private DadoGrafico dadoGrafico;
    private JButton botonLanzarDado; 
    
    private JLabel labelInstrucciones;
    private JButton botonFournier;
    
    private boolean pantallaCompleta;
    
    private boolean impulsoTirarDado; //Impulso de tirada de dado. 0= no hay; 1= si hay;
    
    private int numeroFinalDado; //Numero que saldrá en la tirada del dado.
    
    protected final String[] nombresJugadores;
    

    //Constructor.
    public VistaJuego(ControladorJuego control, int idioma, String jugador1, String jugador2){
        this.controlador = control;
        this.controladorMenu = new ControladorMenu(controlador);
        this.idioma = idioma;
        this.nombresJugadores = new String[]{jugador1, jugador2};
        this.pantallaCompleta = false;
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

    //Metodo que inicializa elementos de la vista general del juego.
    protected void crearObjetos() {
        //Fondo vista Juego
        this.fondoVistaJuego = new PanelFondo(this);
        //MenuItems Partida.
        this.menuNuevaPartida = new JMenuItem(TextosJuego.MENU_NUEVA_PARTIDA[this.idioma]);
        this.menuGuardarPartida = new JMenuItem(TextosJuego.MENU_GUARDAR_PARTIDA[this.idioma]);
        this.menuCargarPartida = new JMenuItem(TextosJuego.MENU_CARGAR_PARTIDA[this.idioma]);
        this.menuSalir = new JMenuItem(TextosJuego.MENU_SALIR[this.idioma]);
        //MenuItems Acciones.
        this.menuLanzarDado = new JMenuItem(TextosJuego.MENU_LANZAR_DADO[this.idioma]);
        this.menuPantallaCompleta = new JMenuItem(TextosJuego.MENU_PANTALLA_COMPLETA[this.idioma]);
        //Menus.
        this.menuPartida = new JMenu(TextosJuego.MENU_PARTIDA[this.idioma]);
        this.menuAcciones = new JMenu(TextosJuego.MENU_ACCIONES[this.idioma]);
        //MenuBar.
        this.menuBar = new JMenuBar();
        //Panel nombres.
        this.panelNombresJugadores = new PanelNombres();
        this.jugadoresTitulo = new JLabel(TextosJuego.TITULO_JUGADORES[this.idioma], JLabel.CENTER);
        //Panel penalizacion.
        this.panelPenalizaciones = new PanelPenalizaciones();
        this.penalizacionesTitulo = new JLabel(TextosJuego.LABEL_PENALIZACIONES[this.idioma], JLabel.CENTER);
        
        //Panel dado.
        this.panelDadoCubilete = new JPanel();
        this.dadoGrafico = new DadoGrafico(this);
        this.controlador.aniadirHilo(this.dadoGrafico);
        new Thread(this.dadoGrafico).start();
        //Label fondo panel dado.
        this.fondoPanelDado = new JLabel();
        //Boton dado.
        this.botonLanzarDado = new JButton();
        //Label Instrucciones.
        this.labelInstrucciones = new JLabel(TextosJuego.LABEL_INSTRUCCCIONES[this.idioma], JLabel.CENTER);
        //Boton Fournier.
        this.botonFournier = new JButton();
        
    }

    //Metodo que da un diseño a los elementos de la vista general del juego.
    protected void disenoObjetos() {
        //Frame.
        this.setLayout(null);
        this.getContentPane().setBackground(Color.orange);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setResizable(true);
        this.setMinimumSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
        this.setIconImage(this.getIconImage());
        UtilidadesGraficas.ponerMedioPantalla(this);
        
        //Fondo Vista Juego
        this.fondoVistaJuego.setBounds(0, 0, FRAME_WIDTH_ORIGINAL, FRAME_HEIGHT_ORIGINAL);
        this.fondoVistaJuego.setBorder(bordePanel);
        this.fondoVistaJuego.setLayout(null);
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
        this.menuNuevaPartida.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        this.menuNuevaPartida.setActionCommand(Constantes.NUEVA_PARTIDA_COMMAND);
        
        this.menuGuardarPartida.setFont(this.FUENTE_1);
        this.menuGuardarPartida.setBackground(COLOR_MENU_BAR);
        this.menuGuardarPartida.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        this.menuGuardarPartida.setActionCommand(Constantes.GUARDAR_PARTIDA_COMMAND);
        
        this.menuCargarPartida.setFont(this.FUENTE_1);
        this.menuCargarPartida.setBackground(COLOR_MENU_BAR);
        this.menuCargarPartida.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        this.menuCargarPartida.setActionCommand(Constantes.CARGAR_PARTIDA_COMMAND);
        
        this.menuSalir.setFont(this.FUENTE_1);
        this.menuSalir.setBackground(COLOR_MENU_BAR);
        this.menuSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        this.menuSalir.setActionCommand(Constantes.SALIR_PARTIDA_COMMAND);
        
        this.menuLanzarDado.setFont(this.FUENTE_1);
        this.menuLanzarDado.setBackground(COLOR_MENU_BAR);
        this.menuLanzarDado.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        this.menuLanzarDado.setActionCommand(Constantes.LANZAR_DADO_COMMAND);
        
        this.menuPantallaCompleta.setFont(this.FUENTE_1);
        this.menuPantallaCompleta.setBackground(COLOR_MENU_BAR);
        this.menuPantallaCompleta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, ActionEvent.CTRL_MASK));
        this.menuPantallaCompleta.setActionCommand(Constantes.PANTALLA_COMPLETA_COMMAND);
        
        //Panel de nombres.
        this.panelNombresJugadores.setBorder(this.blackline);
        this.panelNombresJugadores.setBounds(this.PANEL_NOMBRE__X, this.PANEL_NOMBRE_Y, this.PANEL_NOMBRE_WIDTH, this.PANEL_NOMBRE_HEIGHT);
        this.panelNombresJugadores.setLayout(null);
        //Titulo Jugadores.
        this.jugadoresTitulo.setFont(this.FUENTE_1);
        this.jugadoresTitulo.setForeground(Color.BLACK);
        this.jugadoresTitulo.setBounds(this.TITULO_JUGADORES_X, this.TITULO_JUGADORES_Y, this.TITULO_JUGADORES_WIDTH, this.TITULO_JUGADORES_HEIGHT);
        //Panel de Penalizaciones/Cuenta atrás.
        this.panelPenalizaciones.setBorder(this.blackline);
        this.panelPenalizaciones.setBounds(this.PANEL_PENALIZACIONES_X, this.PANEL_PENALIZACIONES_Y, this.PANEL_PENALIZACIONES_WIDTH, this.PANEL_PENALIZACIONES_HEIGHT);
        this.panelPenalizaciones.setLayout(null);
        //Titulo Penalizaciones.
        this.penalizacionesTitulo.setFont(this.FUENTE_1);
        this.penalizacionesTitulo.setForeground(Color.BLACK);
        this.penalizacionesTitulo.setBounds(this.TITULO_PENALIZACIONES_X, this.TITULO_PENALIZACIONES_Y, this.TITULO_PENALIZACIONES_WIDTH, this.TITULO_PENALIZACIONES_HEIGHT);
        //Tablero Oca. (el propio tablero se ajusta solo).
        this.tablero.setBorder(this.blacklineTablero);
        this.tablero.setLocation(this.TABLERO_X, this.TABLERO_Y);
        //Panel dado y cubilete.
        //this.panelDadoCubilete.setBackground(Color.MAGENTA);
        this.panelDadoCubilete.setOpaque(false);
        this.panelDadoCubilete.setBounds(this.PANEL_DADO_X, this.PANEL_DADO_Y, this.PANEL_DADO_WIDTH, this.PANEL_DADO_HEIGHT);
        this.panelDadoCubilete.setLayout(null);
        //JLabel fondo panel boton.
        this.fondoPanelDado.setIcon(this.ICONO_PANEL_DADO);
        this.fondoPanelDado.setBounds(0, 0, this.PANEL_DADO_WIDTH, this.PANEL_DADO_HEIGHT);
        //Boton Lanzar Dado.
        this.botonLanzarDado.setFont(this.FUENTE_2);
        this.botonLanzarDado.setForeground(Color.BLACK);
        this.botonLanzarDado.setText(TextosJuego.BOTON_LANZAR_DADO[this.idioma]);
        this.botonLanzarDado.setHorizontalTextPosition(SwingConstants.CENTER);
        this.botonLanzarDado.setActionCommand(Constantes.LANZAR_DADO_COMMAND);
        this.botonLanzarDado.setIcon(ICONO_BOTON_TIRAR);
        this.botonLanzarDado.setDisabledIcon(ICONO_BOTON_TIRAR_DISABLE);
        this.botonLanzarDado.setOpaque(false);
        this.botonLanzarDado.setBorderPainted(false);
        this.botonLanzarDado.setFocusPainted(false);
        this.botonLanzarDado.setContentAreaFilled(false); 
        this.botonLanzarDado.setBounds(this.BOTON_DADO_X, this.BOTON_DADO_Y, this.BOTON_DADO_WIDTH, this.BOTON_DADO_HEIGHT);
 
        /*
            Definición de los métodos de escuchador para el boton de Lanzar Dado, está implementado como unca clase anonima
            debido a que es para aplicar unas funcionalidades puramente visuales que no tiene nada que ver con el resto de 
            acciones del juego
        */
        this.botonLanzarDado.addMouseListener(new MouseAdapter() {
            
            private final int HUNDIMIENTO_BOTON = 5;
            private final Color HUNDIMIENTO_COLOR = new Color(95,196,92);

            @Override
            public void mouseEntered(MouseEvent e) {
                botonLanzarDado.setIcon(ICONO_BOTON_TIRAR_HOVER);
                botonLanzarDado.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonLanzarDado.setIcon(ICONO_BOTON_TIRAR);
                botonLanzarDado.setForeground(Color.BLACK);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                botonLanzarDado.setForeground(HUNDIMIENTO_COLOR);
                botonLanzarDado.setLocation(botonLanzarDado.getLocation().x, botonLanzarDado.getLocation().y + HUNDIMIENTO_BOTON);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                botonLanzarDado.setForeground(Color.BLACK);
                botonLanzarDado.setLocation(botonLanzarDado.getLocation().x, botonLanzarDado.getLocation().y - HUNDIMIENTO_BOTON);
            }
        });
        
        //Label Instrucciones.
        this.labelInstrucciones.setFont(this.FUENTE_1);
        this.labelInstrucciones.setForeground(Color.BLACK);
        this.labelInstrucciones.setBounds(this.LABEL_INSTRUCCIONES_X, this.LABEL_INSTRUCCIONES_Y, this.LABEL_INSTRUCCIONES_WIDTH, this.LABEL_INSTRUCCIONES_HEIGHT);
        //Boton Fournier.
        this.botonFournier.setBounds(this.BOTON_FOURNIER_X, this.BOTON_FOURNIER_Y, this.BOTON_FOURNIER_WIDTH, this.BOTON_FOURNIER_HEIGHT);
        this.botonFournier.setBorder(this.blackline);
        this.botonFournier.setActionCommand(Constantes.ABRIR_INSTRUCCIONES_COMMAND);
        this.botonFournier.setIcon(ICONO_BOTON_FOURNIER);
        
        /*
            Definición de los métodos de escuchador para el boton de Fournier, está implementado como unca clase anonima
            debido a que es para aplicar unas funcionalidades puramente visuales que no tiene nada que ver con el resto de 
            acciones del juego
        */
        this.botonFournier.addMouseListener(new MouseAdapter() {
            
            private final int HUNDIMIENTO_BOTON = 5;

            @Override
            public void mouseEntered(MouseEvent e) {
                botonFournier.setIcon(ICONO_BOTON_FOURNIER_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonFournier.setIcon(ICONO_BOTON_FOURNIER);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                botonFournier.setLocation(botonFournier.getLocation().x, botonFournier.getLocation().y + HUNDIMIENTO_BOTON);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                botonFournier.setLocation(botonFournier.getLocation().x, botonFournier.getLocation().y - HUNDIMIENTO_BOTON);
            }
        });
    }
    
    //Metodo sobreescrito para cambiar el icono del juego.
    @Override
    public Image getIconImage() {
        //System.getProperties();
        String sistemaOperativo = System.getProperty("os.name").toLowerCase();
        System.out.println(sistemaOperativo);
        Image iconoJuego = null;
        switch(sistemaOperativo.charAt(0)){
            //Windows
            case 'w':
                iconoJuego = Toolkit.getDefaultToolkit().createImage(Constantes.PATH_ICONO_JUEGO_OCA_WINDOWS);
                break;
            //MacOs    
            case 'm':
                iconoJuego = Toolkit.getDefaultToolkit().createImage(Constantes.PATH_ICONO_JUEGO_OCA_MAC);
                break;
            //UNIX    
            default:
                iconoJuego = Toolkit.getDefaultToolkit().createImage(Constantes.PATH_ICONO_JUEGO_OCA_LINUX);
                break;
        }
        return iconoJuego;
    }
    

    //Metodo que añade elementos a la vista general del juego.
    protected void anadirObjetos() {
        
        //Menus.
        this.menuPartida.add(this.menuNuevaPartida);
        this.menuPartida.add(this.menuGuardarPartida);
        this.menuPartida.add(this.menuCargarPartida);
        this.menuPartida.add(this.menuSalir);
        this.menuAcciones.add(this.menuPantallaCompleta);
        this.menuAcciones.add(this.menuLanzarDado);
        
        //MenuBar.
        this.menuBar.add(this.menuPartida);
        this.menuBar.add(this.menuAcciones);
        this.setJMenuBar(this.menuBar);
        //Panel Nombres
        this.panelNombresJugadores.add(this.jugadoresTitulo);
        //Panel penalizaciones
        this.panelPenalizaciones.add(this.penalizacionesTitulo);
        //Panel DadoGrafico y fondo
        this.panelDadoCubilete.add(this.dadoGrafico);
        this.panelDadoCubilete.add(this.fondoPanelDado);
        //Paneles al frame
        this.fondoVistaJuego.add(this.panelNombresJugadores);
        this.fondoVistaJuego.add(this.panelPenalizaciones);
        this.fondoVistaJuego.add(this.tablero);
        this.fondoVistaJuego.add(this.panelDadoCubilete);
        //Boton lanzar dado al frame
        this.fondoVistaJuego.add(this.botonLanzarDado);
        //Label Instrucciones.
        this.fondoVistaJuego.add(this.labelInstrucciones);
        //Boton fournier al frame.
        this.fondoVistaJuego.add(this.botonFournier);
        //Fondo vistaJuego.
        this.add(this.fondoVistaJuego);
    }

    //Metodo que añade el escuchador necesario a ciertos elemento.
    protected void anadirEscuchadores() {
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(this.controlador);
        
        this.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent we) {
                centrarEnVentana();
            }
        });
        
        this.addComponentListener(new ComponentAdapter(){  
        public void componentResized(ComponentEvent evt) {
            centrarEnVentana();
        }
        });
        
        
        this.menuNuevaPartida.addActionListener(controladorMenu);
        this.menuGuardarPartida.addActionListener(controladorMenu);
        this.menuCargarPartida.addActionListener(controladorMenu);
        this.menuSalir.addActionListener(controladorMenu);
        this.menuLanzarDado.addActionListener(controladorMenu);
        this.menuPantallaCompleta.addActionListener(controladorMenu);


        this.botonLanzarDado.addActionListener(this.controlador);
        this.botonFournier.addActionListener(this.controlador);
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
        return this.idioma;
    }
   
    
    public void crearPuntero(int nCasilla, NotificableTablero escuchador){
        tablero.crearPuntero(nCasilla, escuchador);
    }
    
    public void mover(int jugador, int destino){
        tablero.mover(jugador, destino);
    }
    
    public void eliminarPuntero(){
        tablero.eliminarPuntero();
    }
    
    public void iniciarJugadorSalida(int jugador, int casillaSalida){
        
        Ficha ficha = null;
        
        if(jugador == Constantes.JUGADOR_1){
            ficha = tablero.getFicha1();
        }else if(jugador == Constantes.JUGADOR_2){
            ficha = tablero.getFicha2();
        }
        
        ManejadorFicha.iniciarEnCasilla(casillaSalida, ficha, tablero.getCasillas());
    }
    
    //retorna true si el jugador quiere salir al menu o false si no quiere
    public boolean mensajeFinPartida(int jugador, boolean victoria){
        
        String mensaje = TextosJuego.MENSAJE_JUGADOR_INICIO[idioma] + this.nombresJugadores[jugador];
        
        mensaje += victoria ? TextosJuego.MENSAJE_VICTORIA[idioma]:TextosJuego.MENSAJE_DERROTA[idioma]; 
        
        mensaje += "\n" + TextosJuego.MENSAEJ_PREGUNTA_VOLVER_MENU[idioma];
        
        int opcion =  JOptionPane.showConfirmDialog(this, mensaje);
        
        if(opcion == JOptionPane.YES_OPTION){
            return true;
        }
        
        return false;
        
    }
    
    //retorna true si el jugador quiere salir al menu o false si no quiere
    public boolean mensajeSalirPartida(){

        int opcion =  JOptionPane.showConfirmDialog(this, TextosJuego.MENSAJE_SALIR_PARTIDA[idioma]);
        
        if(opcion == JOptionPane.YES_OPTION){
            return true;
        }
        
        return false;
        
    }
    
    private void centrarEnVentana(){
        UtilidadesGraficas.ponerPanelMedioFrame(fondoVistaJuego, this);
        this.repaint();
    }
    
    public int getCorreccionWidth(){
        return CORRECCION_FRAME_WIDTH;
    }
    
    public int getCorreccionHeight(){
        return CORRECCION_FRAME_HEIGHT;
    }

    @Override
    public boolean isPantallaCompleta() {
        return pantallaCompleta;
    }

    @Override
    public void setPantallaCompleta(boolean isPantallaCompleta) {
        pantallaCompleta = isPantallaCompleta;
    }
    
    
   
   
}
