
package Vistas;

import Controladores.ControladorInicio;
import DatosEstaticos.Constantes;
import DatosEstaticos.TextosJuego;
import Utilidades.UtilidadesGraficas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * @autor: Alvaro
 */
public class VistaInicio extends JFrame{
    
    //Constantes de configuracion.    
    protected final Font FUENTE_1 = new Font("Broadway", 1, 22); //Textos.
    protected final Font FUENTE_2 = new Font("Broadway", 1, 25); //Botones.
    
    private final Border blackline = BorderFactory.createLineBorder(Color.BLACK, 3); //Linea negra para el borde de ciertos elementos.
    
    private final ImageIcon ICONO_BOTON_IDIOMA_ESP_SELECTED = new ImageIcon(Constantes.PATH_ICONO_BOTON_IDIOMA_ESP_SELECTED); //icono del boton de idioma ESP seleccionado.
    private final ImageIcon ICONO_BOTON_IDIOMA_ING_SELECTED = new ImageIcon(Constantes.PATH_ICONO_BOTON_IDIOMA_ING_SELECTED); //icono del boton de idioma ING seleccionador.
    
    private final ImageIcon ICONO_BOTON_IDIOMA_ESP = new ImageIcon(Constantes.PATH_ICONO_BOTON_IDIOMA_ESP); //icono del boton de idioma ESP.
    private final ImageIcon ICONO_BOTON_IDIOMA_ING = new ImageIcon(Constantes.PATH_ICONO_BOTON_IDIOMA_ING); //icono del boton de idioma ING.
    
    private final ImageIcon ICONO_BOTON_FOURNIER = new ImageIcon(Constantes.PATH_ICONO_BOTON_FOURNIER); //icono del boton de Fournier.
    private final ImageIcon ICONO_BOTON_FOURNIER_HOVER = new ImageIcon(Constantes.PATH_ICONO_BOTON_FOURNIER_HOVER); //icono del boton de Fournier Hover.

    private final ImageIcon ICONO_BOTON_MODO1 = new ImageIcon(Constantes.PATH_ICONO_BOTON_MODO1); //icono del boton Modo 1
    private final ImageIcon ICONO_BOTON_MODO1_HOVER = new ImageIcon(Constantes.PATH_ICONO_BOTON_MODO1_HOVER); //icono del boton Modo 1 Hover 
    
    private final ImageIcon ICONO_BOTON_MODO2 = new ImageIcon(Constantes.PATH_ICONO_BOTON_MODO2); //icono del boton Modo 2
    private final ImageIcon ICONO_BOTON_MODO2_HOVER = new ImageIcon(Constantes.PATH_ICONO_BOTON_MODO2_HOVER); //icono del boton Modo 2 Hover 
    
    private final ImageIcon ICONO_BOTON_JUGAR = new ImageIcon(Constantes.PATH_ICONO_BOTON_JUGAR); //icono del boton Jugar
    private final ImageIcon ICONO_BOTON_JUGAR_HOVER = new ImageIcon(Constantes.PATH_ICONO_BOTON_JUGAR_HOVER); //icono del boton Jugar Hover 
    
    private final int FRAME_WIDTH = 1450, FRAME_HEIGHT = 860; //Medidas del frame.
    
    
    private final int BOTON_IDIOMA_ESP_X=80, BOTON_IDIOMA_ESP_Y=525; //Posicion del Boton de idioma ESP
    private final int BOTON_IDIOMA_ESP_WIDTH = 100, BOTON_IDIOMA_ESP_HEIGHT = 100; //Medidas del Boton de idioma ESP
    
    private final int BOTON_IDIOMA_ING_X=80, BOTON_IDIOMA_ING_Y=650; //Posicion del Boton de idioma ING
    private final int BOTON_IDIOMA_ING_WIDTH = 100, BOTON_IDIOMA_ING_HEIGHT = 100; //Medidas del Boton de idioma ING
    
    private final int BOTON_MODO1_X=300, BOTON_MODO1_Y=350; //Posicion del panel del Boton Modo 1.
    private final int BOTON_MODO1_WIDTH = 300, BOTON_MODO1_HEIGHT = 150; //Medidas del Boton Modo 1.
    
    private final int PANEL_J1_X=305, PANEL_J1_Y=490; //Posicion del panel del Panel J1 debajo de Modo 1.
    private final int PANEL_J1_WIDTH = 300, PANEL_J1_HEIGHT = 150; //Medidas del Panel J1 debajo de Modo 1.
    
    private final int LABEL_J1_X=0, LABEL_J1_Y=0; //Posicion del panel del Label J1 debajo de Modo 1.
    private final int LABEL_J1_WIDTH = 300, LABEL_J1_HEIGHT = 75; //Medidas del Label J1 debajo de Modo 1.
    
    private final int TEXTFIELD_J1_X=0, TEXTFIELD_J1_Y=75; //Posicion del panel del TextField J1 debajo de Modo 1.
    private final int TEXTFIELD_J1_WIDTH = 300, TEXTFIELD_J1_HEIGHT = 40; //Medidas del TextField J1 debajo de Modo 1.
    
    private final int BOTON_MODO2_X=850, BOTON_MODO2_Y=350; //Posicion del panel del Boton Modo 2.
    private final int BOTON_MODO2_WIDTH = 300, BOTON_MODO2_HEIGHT = 150; //Medidas del Boton Modo 2.
    
    private final int PANEL_J2_X=855, PANEL_J2_Y=490; //Posicion del panel del Panel J2 debajo de Modo 2.
    private final int PANEL_J2_WIDTH = 300, PANEL_J2_HEIGHT = 150; //Medidas del Panel J2 debajo de Modo 2.
    
    private final int LABEL_J2_X=0, LABEL_J2_Y=0; //Posicion del panel del Label J2 debajo de Modo 2.
    private final int LABEL_J2_WIDTH = 300, LABEL_J2_HEIGHT = 75; //Medidas del Label J2 debajo de Modo 2.
    
    private final int TEXTFIELD_J2_X=0, TEXTFIELD_J2_Y=75; //Posicion del panel del TextField J2 debajo de Modo 2.
    private final int TEXTFIELD_J2_WIDTH = 300, TEXTFIELD_J2_HEIGHT = 40; //Medidas del TextField J2 debajo de Modo 2.
    
    private final int BOTON_JUGAR_X=580, BOTON_JUGAR_Y=625; //Posicion del panel del Boton Jugar.
    private final int BOTON_JUGAR_WIDTH = 300, BOTON_JUGAR_HEIGHT = 150; //Medidas del Boton Jugar.
    
    private final int LABEL_AUTORES_X=1130, LABEL_AUTORES_Y=620; //Posicion del panel del Label Autores.
    private final int LABEL_AUTORES_WIDTH = 300, LABEL_AUTORES_HEIGHT = 150; //Medidas del Label Autores.
    
    private final int BOTON_FOURNIER_X=1175, BOTON_FOURNIER_Y=720; //Posicion del panel del Boton Fournier.
    private final int BOTON_FOURNIER_WIDTH = 220, BOTON_FOURNIER_HEIGHT =65; //Medidas del Boton Fournier.
    
    //Atributos de la clase.
    
    private ControladorInicio controladorInicio;
    
    private PanelFondoInicio panelFondoInicio;
    
    private JButton botonESP;
    private JButton botonING;
    
    private JButton botonModo1;
    private JButton botonModo2;
    
    private JButton botonJugar;
    
    private JPanel panelJ1;
    private JLabel labelJ1;
    private JTextField textFieldJ1;
    
    private JPanel panelJ2;
    private JLabel labelJ2;
    private JTextField textFieldJ2;

    private JLabel labelAutores;
    private JButton botonFournier;
    
    private int idioma;
    private boolean modo1activado;

    //Constructor.
    public VistaInicio(int idioma){
        this.idioma = idioma; //Comienza en español.
        this.iniciarVista();
    }

    //Metodo que crea la vista de inicio.
    private void iniciarVista(){
        crearObjetos();
        disenoObjetos();
        anadirObjetos();
        anadirEscuchadores();
        modo1ON(true); //Comienza con el modo individual seleccionado.
        this.setVisible(true);
    }
    
    //Metodo que inicializa elementos de la vista inicio.
    private void crearObjetos() {
        //Atributo modo
        this.modo1activado=true;
        //Controlador Inicio
        this.controladorInicio = new ControladorInicio(this);
        //Fondo Vista Inicio.
        this.panelFondoInicio = new PanelFondoInicio(this);
        //Botones de idiomas.
        this.botonESP = new JButton();
        this.botonING = new JButton();
        //Boton modo1.
        this.botonModo1 = new JButton(TextosJuego.MODO_1[idioma]);
        //Panel J1.
        this.panelJ1 = new JPanel();
        //Label J1.
        this.labelJ1 = new JLabel(TextosJuego.NOMBRE_J1[idioma], JLabel.CENTER);
        //Textfield J1.
        this.textFieldJ1 = new JTextField(10);
        //Boton modo2.
        this.botonModo2 = new JButton(TextosJuego.MODO_2[idioma]);
        //Panel J2.
        this.panelJ2 = new JPanel();
        //Label J2.
        this.labelJ2 = new JLabel(TextosJuego.NOMBRE_J2[idioma], JLabel.CENTER);
        //Textfield J2.
        this.textFieldJ2 = new JTextField(10);
        //Boton Jugar.
        this.botonJugar = new JButton(TextosJuego.JUGAR[idioma]);
        //Label autores.
        this.labelAutores = new JLabel(TextosJuego.AUTORES[idioma], JLabel.CENTER);
        //Boton Fournier.
        this.botonFournier = new JButton();
        
    }

    //Metodo que da un diseño a los elementos de la vista inicio.
    private void disenoObjetos() {
        //Frame.
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(this.FRAME_WIDTH, this.FRAME_HEIGHT);
        this.setIconImage(this.getIconImage());
        UtilidadesGraficas.ponerMedioPantalla(this);
        //Panel fondo vista inicio.
        this.panelFondoInicio.setBounds(0, 0, this.FRAME_WIDTH, this.FRAME_HEIGHT);
        //Boton idioma ESP.
        this.botonESP.setActionCommand(Constantes.BOTON_IDIOMA_ESP_COMMAND);
        switch (idioma) {
            case 0:
                this.botonESP.setIcon(this.ICONO_BOTON_IDIOMA_ESP_SELECTED);
                break;
            case 1:
                this.botonESP.setIcon(this.ICONO_BOTON_IDIOMA_ESP);
                break;    
            default:
                break;
        }
        this.botonESP.setOpaque(false);
        this.botonESP.setBorderPainted(false);
        this.botonESP.setFocusPainted(false);
        this.botonESP.setContentAreaFilled(false);
        this.botonESP.setBounds(this.BOTON_IDIOMA_ESP_X, this.BOTON_IDIOMA_ESP_Y, this.BOTON_IDIOMA_ESP_WIDTH, this.BOTON_IDIOMA_ESP_HEIGHT);
        /*
            Definición de los métodos de escuchador para el boton modo1, está implementado como unca clase anonima
            debido a que es para aplicar unas funcionalidades puramente visuales que no tiene nada que ver con el resto de 
            acciones del juego
        */
        this.botonESP.addMouseListener(new MouseAdapter() {
            private final int HUNDIMIENTO_BOTON = 5;

            @Override
            public void mouseEntered(MouseEvent e) {
                //
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //
            }

            @Override
            public void mousePressed(MouseEvent e) {
                botonESP.setLocation(botonESP.getLocation().x, botonESP.getLocation().y + HUNDIMIENTO_BOTON);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                botonESP.setLocation(botonESP.getLocation().x, botonESP.getLocation().y - HUNDIMIENTO_BOTON);
            }
        });
        //Boton idioma ING.
        this.botonING.setActionCommand(Constantes.BOTON_IDIOMA_ING_COMMAND);
        switch (idioma) {
            case 0:
                this.botonING.setIcon(this.ICONO_BOTON_IDIOMA_ING);
                break;
            case 1:
                this.botonING.setIcon(this.ICONO_BOTON_IDIOMA_ING_SELECTED);
                break;    
            default:
                break;
        }
        this.botonING.setBorder(this.blackline);
        this.botonING.setOpaque(false);
        this.botonING.setBorderPainted(false);
        this.botonING.setFocusPainted(false);
        this.botonING.setContentAreaFilled(false);
        this.botonING.setBounds(this.BOTON_IDIOMA_ING_X, this.BOTON_IDIOMA_ING_Y, this.BOTON_IDIOMA_ING_WIDTH, this.BOTON_IDIOMA_ING_HEIGHT);
        this.botonING.addMouseListener(new MouseAdapter() {
            private final int HUNDIMIENTO_BOTON = 5;

            @Override
            public void mouseEntered(MouseEvent e) {
                //
            }

            @Override
            public void mouseExited(MouseEvent e) {
               // 
            }

            @Override
            public void mousePressed(MouseEvent e) {
                botonING.setLocation(botonING.getLocation().x, botonING.getLocation().y + HUNDIMIENTO_BOTON);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                botonING.setLocation(botonING.getLocation().x, botonING.getLocation().y - HUNDIMIENTO_BOTON);
            }
        });
        //Boton Modo 1.
        this.botonModo1.setFont(this.FUENTE_2);
        this.botonModo1.setForeground(Color.GREEN); //Comienza seleccionado.
        this.botonModo1.setHorizontalTextPosition(SwingConstants.CENTER);
        this.botonModo1.setActionCommand(Constantes.BOTON_MODO_1_COMMAND);
        this.botonModo1.setIcon(this.ICONO_BOTON_MODO1_HOVER); //Comienza seleccionado.
        this.botonModo1.setOpaque(false);
        this.botonModo1.setBorderPainted(false);
        this.botonModo1.setFocusPainted(false);
        this.botonModo1.setContentAreaFilled(false); 
        this.botonModo1.setBounds(this.BOTON_MODO1_X, this.BOTON_MODO1_Y, this.BOTON_MODO1_WIDTH, this.BOTON_MODO1_HEIGHT);
        /*
            Definición de los métodos de escuchador para el boton modo1, está implementado como unca clase anonima
            debido a que es para aplicar unas funcionalidades puramente visuales que no tiene nada que ver con el resto de 
            acciones del juego
        */
        this.botonModo1.addMouseListener(new MouseAdapter() {
            
            private final int HUNDIMIENTO_BOTON = 5;
            private final Color HUNDIMIENTO_COLOR = Color.GREEN;

            @Override
            public void mouseEntered(MouseEvent e) {
                if(!modo1activado){
                    botonModo1.setIcon(ICONO_BOTON_MODO1_HOVER);
                    botonModo1.setForeground(Color.WHITE);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(!modo1activado){
                    botonModo1.setIcon(ICONO_BOTON_MODO1);
                    botonModo1.setForeground(Color.BLACK);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                botonModo2.setIcon(ICONO_BOTON_MODO2);
                botonModo1.setForeground(HUNDIMIENTO_COLOR);
                botonModo1.setLocation(botonModo1.getLocation().x, botonModo1.getLocation().y + HUNDIMIENTO_BOTON);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(!modo1activado){
                    botonModo1.setForeground(Color.BLACK);
                }else{
                    botonModo1.setForeground(HUNDIMIENTO_COLOR);
                }
                botonModo1.setLocation(botonModo1.getLocation().x, botonModo1.getLocation().y - HUNDIMIENTO_BOTON);
            }
        });
        //Panel J1.
        this.panelJ1.setLayout(null);
        this.panelJ1.setOpaque(false);
        this.panelJ1.setBounds(this.PANEL_J1_X, this.PANEL_J1_Y, this.PANEL_J1_WIDTH, this.PANEL_J1_HEIGHT);
        //Label Nombre J1.
        this.labelJ1.setFont(this.FUENTE_1);
        this.labelJ1.setForeground(Color.BLACK);
        this.labelJ1.setBounds(this.LABEL_J1_X, this.LABEL_J1_Y, this.LABEL_J1_WIDTH, this.LABEL_J1_HEIGHT);
        //Textfield J1.
        this.textFieldJ1.setFont(this.FUENTE_1);
        this.textFieldJ1.setBorder(this.blackline);
        this.textFieldJ1.setForeground(Color.BLACK);
        this.textFieldJ1.setHorizontalAlignment(JTextField.CENTER);
        this.textFieldJ1.setBounds(this.TEXTFIELD_J1_X, this.TEXTFIELD_J1_Y, this.TEXTFIELD_J1_WIDTH, this.TEXTFIELD_J1_HEIGHT);
        //Boton Modo 2.
        this.botonModo2.setFont(this.FUENTE_2);
        this.botonModo2.setForeground(Color.BLACK);
        this.botonModo2.setHorizontalTextPosition(SwingConstants.CENTER);
        this.botonModo2.setActionCommand(Constantes.BOTON_MODO_2_COMMAND);
        this.botonModo2.setIcon(this.ICONO_BOTON_MODO2);
        this.botonModo2.setOpaque(false);
        this.botonModo2.setBorderPainted(false);
        this.botonModo2.setFocusPainted(false);
        this.botonModo2.setContentAreaFilled(false);
        this.botonModo2.setBounds(this.BOTON_MODO2_X, this.BOTON_MODO2_Y, this.BOTON_MODO2_WIDTH, this.BOTON_MODO2_HEIGHT);
        /*
            Definición de los métodos de escuchador para el boton modo 2, está implementado como unca clase anonima
            debido a que es para aplicar unas funcionalidades puramente visuales que no tiene nada que ver con el resto de 
            acciones del juego
        */
        this.botonModo2.addMouseListener(new MouseAdapter() {
            
            private final int HUNDIMIENTO_BOTON = 5;
            private final Color HUNDIMIENTO_COLOR = Color.GREEN;//new Color(95,196,92);

            @Override
            public void mouseEntered(MouseEvent e) {
                if(modo1activado){
                    botonModo2.setIcon(ICONO_BOTON_MODO2_HOVER);
                    botonModo2.setForeground(Color.WHITE);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(modo1activado){
                    botonModo2.setIcon(ICONO_BOTON_MODO2);
                    botonModo2.setForeground(Color.BLACK);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                botonModo1.setForeground(Color.BLACK);
                botonModo1.setIcon(ICONO_BOTON_MODO1);
                botonModo2.setForeground(HUNDIMIENTO_COLOR);
                botonModo2.setLocation(botonModo2.getLocation().x, botonModo2.getLocation().y + HUNDIMIENTO_BOTON);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(modo1activado){
                    botonModo2.setForeground(Color.BLACK);
                }else{
                   botonModo2.setForeground(HUNDIMIENTO_COLOR); 
                }
                botonModo2.setLocation(botonModo2.getLocation().x, botonModo2.getLocation().y - HUNDIMIENTO_BOTON);
            }
        });
        //Panel J2.
        this.panelJ2.setOpaque(false);
        this.panelJ2.setLayout(null);
        this.panelJ2.setBounds(this.PANEL_J2_X, this.PANEL_J2_Y, this.PANEL_J2_WIDTH, this.PANEL_J2_HEIGHT);
        //Label Nombre J2.
        this.labelJ2.setFont(this.FUENTE_1);
        this.labelJ2.setForeground(Color.BLACK);
        this.labelJ2.setBackground(Color.cyan);
        this.labelJ2.setBounds(this.LABEL_J2_X, this.LABEL_J2_Y, this.LABEL_J2_WIDTH, this.LABEL_J2_HEIGHT);
        //Textfield J2.
        this.textFieldJ2.setFont(this.FUENTE_1);
        this.textFieldJ2.setBorder(this.blackline);
        this.textFieldJ2.setForeground(Color.BLACK);
        this.textFieldJ2.setHorizontalAlignment(JTextField.CENTER);
        this.textFieldJ2.setBounds(this.TEXTFIELD_J2_X, this.TEXTFIELD_J2_Y, this.TEXTFIELD_J2_WIDTH, this.TEXTFIELD_J2_HEIGHT);
        //Boton Jugar.
        this.botonJugar.setFont(this.FUENTE_2);
        this.botonJugar.setForeground(Color.BLACK);
        this.botonJugar.setHorizontalTextPosition(SwingConstants.CENTER);
        this.botonJugar.setActionCommand(Constantes.BOTON_JUGAR_COMMAND);
        this.botonJugar.setIcon(this.ICONO_BOTON_JUGAR);
        this.botonJugar.setOpaque(false);
        this.botonJugar.setBorderPainted(false);
        this.botonJugar.setFocusPainted(false);
        this.botonJugar.setContentAreaFilled(false);
        this.botonJugar.setBounds(this.BOTON_JUGAR_X, this.BOTON_JUGAR_Y, this.BOTON_JUGAR_WIDTH, this.BOTON_JUGAR_HEIGHT);
        /*
            Definición de los métodos de escuchador para el boton modo1, está implementado como unca clase anonima
            debido a que es para aplicar unas funcionalidades puramente visuales que no tiene nada que ver con el resto de 
            acciones del juego
        */
        this.botonJugar.addMouseListener(new MouseAdapter() {
            
            private final int HUNDIMIENTO_BOTON = 5;
            private final Color HUNDIMIENTO_COLOR = Color.MAGENTA;//new Color(95,196,92);

            @Override
            public void mouseEntered(MouseEvent e) {
                botonJugar.setIcon(ICONO_BOTON_JUGAR_HOVER);
                botonJugar.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonJugar.setIcon(ICONO_BOTON_JUGAR);
                botonJugar.setForeground(Color.BLACK);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                botonJugar.setForeground(HUNDIMIENTO_COLOR);
                botonJugar.setLocation(botonJugar.getLocation().x, botonJugar.getLocation().y + HUNDIMIENTO_BOTON);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                botonJugar.setForeground(Color.BLACK);
                botonJugar.setLocation(botonJugar.getLocation().x, botonJugar.getLocation().y - HUNDIMIENTO_BOTON);
            }
        });
        //Label autores.
        this.labelAutores.setFont(this.FUENTE_1);
        this.labelAutores.setForeground(Color.BLACK);
        this.labelAutores.setBounds(this.LABEL_AUTORES_X, this.LABEL_AUTORES_Y, this.LABEL_AUTORES_WIDTH, this.LABEL_AUTORES_HEIGHT);
        //Boton fournier.
        this.botonFournier.setBorder(this.blackline);
        this.botonFournier.setIcon(this.ICONO_BOTON_FOURNIER);
        this.botonFournier.setBounds(this.BOTON_FOURNIER_X, this.BOTON_FOURNIER_Y, this.BOTON_FOURNIER_WIDTH, this.BOTON_FOURNIER_HEIGHT);
        this.botonFournier.setActionCommand(Constantes.ABRIR_AUTORES_COMMAND);
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

    //Metodo que añade elementos a la vista inicio.
    private void anadirObjetos() {
        this.add(this.botonESP);
        this.add(this.botonING);
        this.add(this.botonModo1);
        this.add(this.panelJ1);
        this.panelJ1.add(this.labelJ1);
        this.panelJ1.add(this.textFieldJ1);
        this.add(this.botonModo2);
        this.add(this.panelJ2);
        this.panelJ2.add(this.labelJ2);
        this.panelJ2.add(this.textFieldJ2);
        this.add(this.botonJugar);
        this.add(this.labelAutores);
        this.add(this.botonFournier);
        this.add(this.panelFondoInicio);
    }

    //Metodo que añade el escuchador necesario a ciertos elementos.
    private void anadirEscuchadores() {
        this.botonESP.addActionListener(this.controladorInicio);
        this.botonING.addActionListener(this.controladorInicio);
        this.botonFournier.addActionListener(this.controladorInicio);
        this.botonModo1.addActionListener(this.controladorInicio);
        this.botonModo2.addActionListener(this.controladorInicio);
        this.botonJugar.addActionListener(this.controladorInicio);
        this.addWindowListener(this.controladorInicio);
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

    //Getter y Setter de idioma.
    public int getIdioma() {
        return idioma;
    }

    public void setIdioma(int idioma) {
        this.idioma = idioma;
    }
    
    //Metodo que lanza un dialogo y retorna true si el jugador quiere salir del juego.
    public boolean mensajeSalirJuego(){

        int opcion =  JOptionPane.showConfirmDialog(this, TextosJuego.MENSAJE_SALIR_JUEGO[idioma]);
        
        if(opcion == JOptionPane.YES_OPTION){
            return true;
        }
        
        return false;
        
    }
    
    //Metodo que lanza un dialogo y retorna true si el jugador quiere cambiar el idioma del juego.
    public boolean mensajeCambioIdioma(){

        int opcion =  JOptionPane.showConfirmDialog(this, TextosJuego.MENSAJE_CAMBIAR_IDIOMA[idioma]);
        
        if(opcion == JOptionPane.YES_OPTION){
            return true;
        }
        
        return false;
        
    }
    
    //Metodo que lanza un dialogo y retorna true si el jugador quiere cambiar el idioma del juego.
    public void mensajeErrorCampos(){
        JOptionPane.showMessageDialog(this, TextosJuego.MENSAJE_ERROR_CAMPOS[idioma]);
    }

    //Getters del contenido de los textfields (Nombres Jugadores)
    public String getTextFieldNameJ1(){
        return this.textFieldJ1.getText();
    }
    
    public String getTextFieldNameJ2(){
        return this.textFieldJ2.getText();
    }
    
    //Getter del modo de juego actual.
    public boolean isModo1activado() {
        return modo1activado;
    }
    

    public void modo1ON(boolean activado){
        //Si está activado el modo 1, se ocultan los campos del modo 2.
        if(activado){
            this.modo1activado=true;
            this.botonModo2.setForeground(Color.BLACK);
            this.labelJ2.setVisible(false);
            this.textFieldJ2.setVisible(false);
            this.textFieldJ2.setText("");
        //Si se desactiva el modo 1(se activa el modo 2), se visibilizan los campos del modo 2    
        }else{
            this.modo1activado=false;
            this.botonModo2.setForeground(Color.GREEN);
            this.labelJ2.setVisible(true);
            this.textFieldJ2.setVisible(true);
        }
    }
}
