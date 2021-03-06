

package DatosEstaticos;

/**
 * Clase para almacenar constantes.
 *
 * @author Enrique Sánchez 
 */
public final class Constantes {

    //constante de posicion de jugadores en elos arrays
    
    //id de jugadores
    
    public static final int JUGADOR_1 = 0;
    public static final int JUGADOR_2 = 1;
    
    //id idiomas
    
    public static final int IDIOMA_ESPANOL = 0;
    public static final int IDIOMA_INGLES= 1;
    
    //rutas
    
    public static final String RUTA_DIRECTORIO_GUARDADO = "./partidas";
    public static final String RUTA_GUARDADO_PARTIDA_MODO_1 = RUTA_DIRECTORIO_GUARDADO + "/modo1.oca";
    public static final String RUTA_GUARDADO_PARTIDA_MODO_2 = RUTA_DIRECTORIO_GUARDADO + "/modo2.oca";
    
    public static final String IMAGEN_FONDO_TERCIOPELO = "./img/rotulos/fondo_terciopelo.png";
    
    public static final String PATH_ICONO_FICHA_GRANDEJ1 = "./img/fichas/ficha1.png";
    public static final String PATH_ICONO_FICHAJ1 = "./img/fichas/ficha1Chica.png";
    public static final String PATH_ICONO_FICHAJ2 = "./img/fichas/ficha2Chica.png";
    
    public static String PATH_ICONO_FONDO_INICIO = "./img/rotulos/fondo_VistaInicio.png";
    
    public static final String PATH_ICONO_FONDO_VISTAJUEGO = "./img/rotulos/fondo_VistaJuego.png";
    
    public static final String PATH_ICONO_INSTRUCCIONES_MODO1_ESP = "./img/instrucciones/fondo_InstruccionesModo1_ESP.png";
    public static final String PATH_ICONO_INSTRUCCIONES_MODO2_ESP = "./img/instrucciones/fondo_InstruccionesModo2_ESP.png";
    public static final String PATH_ICONO_INSTRUCCIONES_MODO1_ING = "./img/instrucciones/fondo_InstruccionesModo1_ING.png";
    public static final String PATH_ICONO_INSTRUCCIONES_MODO2_ING = "./img/instrucciones/fondo_InstruccionesModo2_ING.png";
    
    public static final String PATH_ICONO_PANEL_NOMBRES = "./img/rotulos/fondo_Panel_Nombres.png";
    public static final String PATH_ICONO_PANEL_PENALIZACIONES = "./img/rotulos/fondo_Panel_Penalizaciones.png";
    
    public static final String PATH_ICONO_BTN_TIRAR = "./img//botones/btnTirar.png"; 
    public static final String PATH_ICONO_BTN_TIRAR_HOVER = "./img//botones/btnTirarHover.png";   
    public static final String PATH_ICONO_BTN_TIRAR_DISABLE = "./img//botones/btnTirarDisable.png";
    
    public static final String PATH_ICONO_PANEL_DADO = "./img/rotulos/panel_dado.png";
    public static final String PATH_ICONO_BOTON_FOURNIER = "./img/botones/boton_Fournier.png";
    public static final String PATH_ICONO_BOTON_FOURNIER_HOVER = "./img/botones/boton_Fournier_Hover.png";
    
    public static final String PATH_MARCO_ENFASIS_NOMBRE = "./img/rotulos/enfasis_nombre.png";
    
    public static final String PATH_HILO_MUSICAL_PRINCIPAL = "./sounds/Arkansas-Traveler.aiff";
    public static final String PATH_SONIDO_GOLPE_DADO = "./sounds/kick_sound.aiff";
    public static final String PATH_SONIDO_TURNO_BLOQUEADO = "./sounds/fallo.aiff";
    public static final String PATH_SONIDO_PUNTERO_GRAFICO = "./sounds/oscilador.aiff";
    public static final String PATH_SONIDO_CAMBIO_TURNO = "./sounds/cambioTurno.aiff";
    public static final String PATH_SONIDO_RETROCESO = "./sounds/retroceso.aiff";
    public static final String PATH_SONIDO_AVANCE1 = "./sounds/cartoon018.aiff";
    public static final String PATH_SONIDO_AVANCE2 = "./sounds/cartoon016.aiff";
    public static final String PATH_SONIDO_GANAR = "./sounds/ganar.aiff";
    public static final String PATH_SONIDO_PERDER = "./sounds/perder.aiff";
    
    public static final String PATH_ICONO_JUEGO_OCA_WINDOWS = "./img/rotulos/icono_juego_oca.png"; //ico no se ve (?)
    public static final String PATH_ICONO_JUEGO_OCA_MAC = "./img/rotulos/icono_juego_oca.icns";
    public static final String PATH_ICONO_JUEGO_OCA_LINUX = "./img/rotulos/icono_juego_oca.png";
    
    //Vista Inicio
    public static final String PATH_ICONO_BOTON_MODO1 = "./img/botonesVistaInicio/botonModo1.png";
    public static final String PATH_ICONO_BOTON_MODO1_HOVER = "./img/botonesVistaInicio/botonModo1_Hover.png";
    
    public static final String PATH_ICONO_BOTON_MODO2 = "./img/botonesVistaInicio/botonModo2.png";
    public static final String PATH_ICONO_BOTON_MODO2_HOVER = "./img/botonesVistaInicio/botonModo2_Hover.png";
    
    public static final String PATH_ICONO_BOTON_JUGAR = "./img/botonesVistaInicio/botonJugar.png";
    public static final String PATH_ICONO_BOTON_JUGAR_HOVER = "./img/botonesVistaInicio/botonJugar_Hover.png";
    
    public static final String PATH_ICONO_BOTON_IDIOMA_ESP = "./img/banderas/espana.png";
    public static final String PATH_ICONO_BOTON_IDIOMA_ING = "./img/banderas/reino-unido.png";
    public static final String PATH_ICONO_BOTON_IDIOMA_ESP_SELECTED = "./img/banderas/espana_selected.png";
    public static final String PATH_ICONO_BOTON_IDIOMA_ING_SELECTED = "./img/banderas/reino-unido_selected.png";
    
    public static final String PATH_FONDO_AUTORES_ESP = "./img/autores/fondo_autores_ESP.png";
    public static final String PATH_FONDO_AUTORES_ING = "./img/autores/fondo_autores_ING.png";
    
   
    
    //comandos de acciones

    public static final String ABRIR_INSTRUCCIONES_COMMAND = "AbriR_-InstruCiOneS";
    public static final String NUEVA_PARTIDA_COMMAND = "Nue$-va_Partida";
    public static final String GUARDAR_PARTIDA_COMMAND = "G.uarDar_Partida";
    public static final String CARGAR_PARTIDA_COMMAND = "Ca%gar_Partida";
    public static final String SALIR_PARTIDA_COMMAND = "$alIr_Partida";

    public static final String LANZAR_DADO_COMMAND = "DaDo_.CommanD";
    public static final String PANTALLA_COMPLETA_COMMAND = "PaNTa..a-ComPleTa";
    
    public static final String BOTON_MODO_1_COMMAND = "Modo1.Command";
    public static final String BOTON_MODO_2_COMMAND = "Modo2.Command";
    
    public static final String BOTON_JUGAR_COMMAND = "Jugar.Command";
    
    public static final String BOTON_IDIOMA_ESP_COMMAND = "IDIOMA.ESP.Command";
    public static final String BOTON_IDIOMA_ING_COMMAND = "IDIOMA.ING.Command";
    
    public static final String ABRIR_AUTORES_COMMAND = "Autores.Command";
    
    
}
