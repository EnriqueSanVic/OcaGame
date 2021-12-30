

package Controladores;

import DatosEstaticos.Constantes;
import Modelos.DirectivasEvaluacion;
import Logicas.LogicaJuegoModo1;
import Modelos.DatosPartidaModo1;
import Modelos.GestorGuardado;
import Vistas.VistaJuegoModo1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.Timer;

/**
 * Controlador de juego del modo individual o modo 1.
 *
 * @author Enrique Sánchez 
 */
public final class ControladorJuegoModo1 extends ControladorJuego{

    private final int SEGUNDOS_INICIO = 150;
    
    private Timer timer;

    private int segundos;
    
    private int penalizaciones;
    
    private int casillaDestino, avanceAuto, retrocesoAcumuladoCasillaFin;
    
    private boolean iniciarTurno, controlAutomatico, reevaluarDespuesDeAuto, victoria;
    
    
    /**
     * Constructor del controlador
     * 
     * @param idioma Id del idioma con el que se debe de instanciar el modo de juego.
     * @param jugador1 Nombre del jugador 1.
     * @param jugador2 Nombre del jugador 2. 
     */    
    public ControladorJuegoModo1(int idioma, String jugador1, String jugador2) {
        
        super(idioma,jugador1, jugador2);
        
        this.vista = new VistaJuegoModo1(this, idioma, jugador1, jugador2);
        this.logica = new LogicaJuegoModo1();

        iniciarPartida(super.nombres[Constantes.JUGADOR_1], super.POSICION_SALIDA, SEGUNDOS_INICIO, 0);
        
    }


    @Override
    protected void matarHilos() {
        super.matarHilos();
        timer.stop();
    }
    
    
    /**
     * Metodo para iniciar una partida parametrizada.
     * 
     * @param nombre del jugador.
     * @param posicionJugador1 posicion de inicio del jugador.
     * @param segundos inicio en el contador.
     * @param penalizaciones de inicio de penalización. 
     */
    protected void iniciarPartida(String nombre, int posicionJugador1, int segundos, int penalizaciones) {
        
        logica.setPosicionJugador(Constantes.JUGADOR_1, posicionJugador1);
        
        this.segundos = segundos;

        retrocesoAcumuladoCasillaFin = 0;
        
        this.penalizaciones = penalizaciones;
        
        controlAutomatico = false;
        victoria = false;
        
        super.normalizarVista();
        
        iniciarTemporizador();
        
        ((VistaJuegoModo1)vista).setNombreJugador1(nombre);
        
        ((VistaJuegoModo1)vista).setSegundosTemporizador(String.valueOf(segundos)); 
        
        ((VistaJuegoModo1)vista).setPenalizacionLabel(String.valueOf(penalizaciones));

        //se inicia el jugador en la posicion que tiene la lógica en este momento
        (vista).iniciarJugadorSalida(Constantes.JUGADOR_1, logica.getPosicionJugador(Constantes.JUGADOR_1));
        
        //no se inicia el turno hasta este punto para no escuchar los eventos hasta que esté todo configurado
        iniciarTurno = true;

        
    }
    
    @Override
    protected boolean sePuedeTirarDado() {
        return iniciarTurno;
    }
    

    @Override
    public void eventoFinalizacionDado() {

        //solo se tira el dado si se puede tirar
        if (((LogicaJuegoModo1) logica).evaluarTurnoInicio(Constantes.JUGADOR_1)
                && iniciarTurno 
                && !controlAutomatico) {

            iniciarTurno = false;
            
            casillaDestino = logica.getPosicionJugador(Constantes.JUGADOR_1) + this.ultimoNumeroDado;
           
            
            //si es un tiro sin rebote por sobrepasamiento 
            if(casillaDestino < 62){
                vista.crearPuntero(casillaDestino, this);
                
                //si es un tiro sobrepasao
            }else{
                
                //vamos a hacer que llege hasta el final y retroceda las acumuladas
                
                
                retrocesoAcumuladoCasillaFin = -(casillaDestino - 62);
                        
                casillaDestino = 62;
            
                vista.crearPuntero(62, this);
                
                
            }

        }

    }

    @Override
    public void eventoToquePuntero() {

        //se mueve en la vista la ficha del jugador
        vista.mover(Constantes.JUGADOR_1, casillaDestino);
    }

    @Override
    public void eventoFinalMovimientoFicha() {
        
        //si es un tiro con retroceso 
        if(retrocesoAcumuladoCasillaFin != 0){
            
            /*Cuando el tiro es con retorceso nunca se pone logicamente en la ultima 
            casilla si no que solo se realiza el movimiento gráficamente*/
            casillaDestino += retrocesoAcumuladoCasillaFin;
            
            avanceAuto =   (62 + retrocesoAcumuladoCasillaFin) - logica.getPosicionJugador(Constantes.JUGADOR_1);
            
            retrocesoAcumuladoCasillaFin = 0;
            
            controlAutomatico = true;
            
            reevaluarDespuesDeAuto = true;
 
            vista.mover(Constantes.JUGADOR_1, casillaDestino);

            
        //si es un tiro con control automático
        }else if (controlAutomatico) {
            
            controlAutomatico = false;
            
            logica.mover(Constantes.JUGADOR_1, avanceAuto);
            
            
            if(reevaluarDespuesDeAuto){
            
                reevaluarDespuesDeAuto = false;
                
                DirectivasEvaluacion directivas = ((LogicaJuegoModo1) logica).evaluarTurnoFinal(Constantes.JUGADOR_1);

                iniciarTurno = evaluardirectivas(directivas);
                
                
            }else{
                iniciarTurno = true;
            }
            
            
        //si es un tiro normal
        } else {
            //se mueve lógicamente el jugador hasta la casilla
            logica.mover(Constantes.JUGADOR_1, ultimoNumeroDado);

            //falta evaluar la tirada que contiene este objeto
            DirectivasEvaluacion directivas = ((LogicaJuegoModo1) logica).evaluarTurnoFinal(Constantes.JUGADOR_1);

            iniciarTurno = evaluardirectivas(directivas);

        }
        
        
        //se evalua si finalmente el jugador ha ganado
        if(logica.isGanador(Constantes.JUGADOR_1)){
             finalizarPartidaPorMeta();
        }
       
    }

    /**
     * Metodo para iniciar el temporizador del cronómetro de los segundos restantes de juego.
     */
    private void iniciarTemporizador() {
        
        //si hubiera de otra partida un timer iniciado se para
        if(this.timer != null){
            this.timer.stop();
            this.timer = null;
        }
        
        //Se repite a cada segundo.
        this.timer = new Timer(1000, new ActionListener() {
            
            private boolean cambioColor = false;
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                ((VistaJuegoModo1)vista).setSegundosTemporizador(String.valueOf(segundos));        

                if(segundos>0){
                    segundos-=1; //Resto un segundo en el label
                    
                    if(!cambioColor && segundos < 10){
                        ((VistaJuegoModo1)vista).cambiarColorFinTemporizador();
                        cambioColor = true;
                    }
                    
                }else{
                    //si se acaba el tiempo se finaliza la partida
                    finalizarPartidaPorTiempo();
                }
            }
        });
        this.timer.start(); //Iniciamos el temporizador
    }
    
    

    //retorna si se ha terminado el turno actual o no
    @Override
    protected boolean evaluardirectivas(DirectivasEvaluacion directivas) {
        
        
        //en el modo un jugador no se evalua el tira otra vez por que siempre tira otra vez
        
        if(directivas.getPosicion() != 0){
            
            controlAutomatico = true;
            
            avanceAuto = directivas.getPosicion();
            
            super.evaluarSonidoMovimiento(directivas.getPosicion());
            
            super.esperarDelayAntesDeAccionAuto();
            
            vista.mover(Constantes.JUGADOR_1, logica.getPosicionJugador(Constantes.JUGADOR_1) + avanceAuto);

            return false;
            
        }else if(directivas.getPenalizacion() != 0){
            
            int penalizacion = directivas.getPenalizacion() * 10;
            
            segundos -= penalizacion;
                    
            penalizaciones -= penalizacion;
            
            super.sonidoTurnoBloqueado();
            
            ((VistaJuegoModo1)vista).setPenalizacionLabel(String.valueOf(penalizaciones));
            
        }
        
        return true;
        
    }

    /**
     * Accion de fin de partida ya sea por tiempo o por ganar
     */
    private void finalizarPartidaPorTiempo(){
        
        rutinaFinPartida();
        
        
        victoria = false;
        
        super.sonidoPerder();
        
        evaluarFinal();
        
        
    }
    
    /**
     * Accion de fin de partida por llegada a la meta.
     */
    private void finalizarPartidaPorMeta(){
    
        //si se llega a la meta todavía tiene que haber segundos en el contador
        
        rutinaFinPartida();
        
        if(segundos > 0){
                        
            victoria = true;
            
            super.sonidoGanar();
            
        }else{
            super.sonidoPerder();
        }
        
        evaluarFinal();
        
    }
     
    /**
     * rutina de finalización de partida para parar el cronometro u bloquear el boton del dado.
     */
    private void rutinaFinPartida(){
        
        timer.stop();
        
        vista.bloquearBoton(false);
        
        iniciarTurno = false;

    }
    
    /**
     * Lanza un mensaje de victoria del usuario y le pregunta si quiere volver a la vista de inicio.
     */
    private void evaluarFinal() {
        
        if(vista.mensajeFinPartida(Constantes.JUGADOR_1, victoria)){
            super.volverMenuInicio();
        }
        
        
    }

    /**
     * Creación de una nueva partida desde el principio.
     */
    @Override
    public void nuevaPartida() {
        iniciarPartida(super.nombres[Constantes.JUGADOR_1], super.POSICION_SALIDA, SEGUNDOS_INICIO, 0);
    }

    
    @Override
    public void guardarPartida() {
        
        DatosPartidaModo1 datos = new DatosPartidaModo1(super.nombres[Constantes.JUGADOR_1], logica.getPosicionJugador()[Constantes.JUGADOR_1], segundos, penalizaciones);
        
        try {
            GestorGuardado.guardarPartidaModo1(Constantes.RUTA_GUARDADO_PARTIDA_MODO_1, datos);
        } catch (IOException ex) {
            vista.mensajeErrorGuardado();
        }
        
        
    }

    
    @Override
    public void cargarPartida() {
        
        try {
            DatosPartidaModo1 datos = GestorGuardado.cargarPartidaModo1(Constantes.RUTA_GUARDADO_PARTIDA_MODO_1);
            
            iniciarPartida(datos.getNombre(), datos.getPosicion(), datos.getSegundos(), datos.getPenalizaciones());
        } catch (IOException | ClassNotFoundException ex) {
            vista.mensajeErrorCarga();
        }

    }

    

    

}
