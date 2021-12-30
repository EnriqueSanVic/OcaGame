

package Controladores;

import DatosEstaticos.Constantes;
import Modelos.DirectivasEvaluacion;
import Logicas.LogicaJuegoModo2;
import Modelos.DatosPartidaModo2;
import Modelos.GestorGuardado;
import Vistas.VistaJuegoModo2;
import java.io.IOException;


/**
 * Controlador de juego del modo dos jugadores o modo 2.
 *
 * @author Enrique Sánchez 
 */
public final class ControladorJuegoModo2 extends ControladorJuego{
    
    private final int[] POSICIONES_INICIO = new int[]{0,0};
    
    private final int[] TURNOS_BLOQUEO_INICIO = new int[]{0,0};
    
    private final int TURNO_INICIO = Constantes.JUGADOR_1;

    private int jugadorTurnoActual, casillaDestino, retrocesoAcumuladoCasillaFin, avanceAuto;
    
    private boolean iniciarTurno, controlAutomatico, reevaluarDespuesDeAuto, tirarOtraVez;
    
    /**
     * Constructor del controlador
     * 
     * @param idioma Id del idioma con el que se debe de instanciar el modo de juego.
     * @param jugador1 Nombre del jugador 1.
     * @param jugador2 Nombre del jugador 2. 
     */  
    public ControladorJuegoModo2(int idioma, String jugador1, String jugador2) {
        
        super(idioma, jugador1, jugador2);
        
        super.vista = new VistaJuegoModo2(this, idioma, jugador1, jugador2);
        super.logica = new LogicaJuegoModo2();

        iniciarPartida(super.nombres, POSICIONES_INICIO, TURNOS_BLOQUEO_INICIO, TURNO_INICIO);
    }

    
    @Override
    protected boolean sePuedeTirarDado() {
        
        if(((LogicaJuegoModo2) logica).getTurnosBloqueo(jugadorTurnoActual) == 0 && iniciarTurno){
            
            return true;
            
        }else{
            
            eventoFinalizacionDado();
            return  false;
        }
        
        
        
    }
    /**
     * Metodo para iniciar una partida parametrizada.
     * 
     * @param nombres array de nombres de los jugadores.
     * @param posiciones array de posiciones iniciales de los jugadores.
     * @param turnosBloqueo array de turnos de bloqueo iniciales de los jugadores.
     * @param turnoActual id del usuario con el turno de inicio.
     */
    protected void iniciarPartida(String[] nombres, int[] posiciones, int[] turnosBloqueo, int turnoActual) {
        
        logica.setPosicionJugador(Constantes.JUGADOR_1, posiciones[Constantes.JUGADOR_1]);
        logica.setPosicionJugador(Constantes.JUGADOR_2, posiciones[Constantes.JUGADOR_2]);
        
        ((LogicaJuegoModo2)logica).setTurnosBloqueo(Constantes.JUGADOR_1, turnosBloqueo[Constantes.JUGADOR_1]);
        ((LogicaJuegoModo2)logica).setTurnosBloqueo(Constantes.JUGADOR_2, turnosBloqueo[Constantes.JUGADOR_2]);
        
        jugadorTurnoActual = turnoActual;
        
        retrocesoAcumuladoCasillaFin = 0;
        avanceAuto = 0;
        controlAutomatico = false;
        tirarOtraVez = false;
        
        super.normalizarVista();

        //se inicia el jugadores en la posiciones que tiene la lógica en este momento
        (vista).iniciarJugadorSalida(Constantes.JUGADOR_1, logica.getPosicionJugador(Constantes.JUGADOR_1));
        (vista).iniciarJugadorSalida(Constantes.JUGADOR_2, logica.getPosicionJugador(Constantes.JUGADOR_2));
        

        actualizarTurnoJugadorVista();
        actualizarPenalizacionesJugadorVista(Constantes.JUGADOR_1);
        actualizarPenalizacionesJugadorVista(Constantes.JUGADOR_2);
        
        iniciarTurno = true;

    }
    
    /**
     * Metodo para cambiar turno entre los jugadores.
     */
    private void cambiarTurno(){
        
        if(jugadorTurnoActual == Constantes.JUGADOR_1){
            
            jugadorTurnoActual = Constantes.JUGADOR_2;
            
        }else if(jugadorTurnoActual == Constantes.JUGADOR_2){
            
            jugadorTurnoActual = Constantes.JUGADOR_1;
        }
        
        super.sonidoCambioTurno();
        
        actualizarTurnoJugadorVista();
        
    }
    
    /**
     * Actualizar turno del jugador actual en la vista.
     */
    private void actualizarTurnoJugadorVista(){
        
        ((VistaJuegoModo2)vista).enfatizarNombreTurno(jugadorTurnoActual);
        
    }
    
    /**
     * Actualizar la penalización de los jugadores en la vista.
     */
    private void actualizarPenalizacionesJugadorVista(){
        
        ((VistaJuegoModo2)vista).actualizarPenalizacionesJugador(jugadorTurnoActual, ((LogicaJuegoModo2)logica).getTurnosBloqueo(jugadorTurnoActual));
        
    }
    
    /**
     * Actualizar la penalización de un jugador en la vista.
     * 
     * @param id jugador que se quiere actualizar.
     */
    private void actualizarPenalizacionesJugadorVista(int jugador){
        
        ((VistaJuegoModo2)vista).actualizarPenalizacionesJugador(jugador, ((LogicaJuegoModo2)logica).getTurnosBloqueo(jugador));
        
    }

    
    @Override
    public void eventoFinalizacionDado() {

        //solo se tira el dado si se puede tirar
        if (((LogicaJuegoModo2) logica).evaluarTurnoInicio(jugadorTurnoActual)) {

            if (iniciarTurno && !controlAutomatico) {

                iniciarTurno = false;

                casillaDestino = logica.getPosicionJugador(jugadorTurnoActual) + this.ultimoNumeroDado;

                //si es un tiro sin rebote por sobrepasamiento 
                if (casillaDestino < 62) {
                    vista.crearPuntero(casillaDestino, this);

                    //si es un tiro sobrepasao
                } else {

                    //vamos a hacer que llege hasta el final y retroceda las acumuladas
                    retrocesoAcumuladoCasillaFin = -(casillaDestino - 62);

                    casillaDestino = 62;

                    vista.crearPuntero(62, this);

                }

            }

        } else {
            super.sonidoTurnoBloqueado();
            actualizarPenalizacionesJugadorVista();
            cambiarTurno();
        }

    }

    @Override
    public void eventoToquePuntero() {
        //se mueve en la vista la ficha del jugador
        vista.mover(jugadorTurnoActual, casillaDestino);
    }

    @Override
    public void eventoFinalMovimientoFicha() {
        
        //si es un tiro con retroceso 
        if(retrocesoAcumuladoCasillaFin != 0){
            
            /*Cuando el tiro es con retorceso nunca se pone logicamente en la ultima 
            casilla si no que solo se realiza el movimiento gráficamente*/
            casillaDestino += retrocesoAcumuladoCasillaFin;
            
            avanceAuto =  (62 + retrocesoAcumuladoCasillaFin) - logica.getPosicionJugador(jugadorTurnoActual);
            
            retrocesoAcumuladoCasillaFin = 0;
            
            controlAutomatico = true;
            
            reevaluarDespuesDeAuto = true;
 
            vista.mover(jugadorTurnoActual, casillaDestino);

            
        //si es un tiro con control automático
        }else if (controlAutomatico) {
            
            controlAutomatico = false;
            
            logica.mover(jugadorTurnoActual, avanceAuto);
            
            
            if(reevaluarDespuesDeAuto){
            
                reevaluarDespuesDeAuto = false;
                
                DirectivasEvaluacion directivas = ((LogicaJuegoModo2) logica).evaluarTurnoFinal(jugadorTurnoActual);

                iniciarTurno = evaluardirectivas(directivas);
                
                
            }else{
                iniciarTurno = true;
            }
            
            
        //si es un tiro normal
        } else {
            
            //se mueve lógicamente el jugador hasta la casilla
            logica.mover(jugadorTurnoActual, ultimoNumeroDado);

            //falta evaluar la tirada que contiene este objeto
            DirectivasEvaluacion directivas = ((LogicaJuegoModo2) logica).evaluarTurnoFinal(jugadorTurnoActual);

            iniciarTurno = evaluardirectivas(directivas);

        }
        
        if(logica.isGanador(jugadorTurnoActual)){
             super.sonidoGanar();
             finalizarPartidaPorMeta(jugadorTurnoActual);
        }
        
        if(iniciarTurno && !tirarOtraVez){
            cambiarTurno();
        }

    }
    
    @Override
    protected boolean evaluardirectivas(DirectivasEvaluacion directivas) {
        
        //en el modo un jugador no se evalua el tira otra vez por que siempre tira otra vez
        
        tirarOtraVez = directivas.isTirarOtraVez();
        
        if(directivas.getPosicion() != 0){
            
            controlAutomatico = true;
            
            avanceAuto = directivas.getPosicion();
            
            super.evaluarSonidoMovimiento(directivas.getPosicion());
            
            super.esperarDelayAntesDeAccionAuto();
 
            vista.mover(jugadorTurnoActual, logica.getPosicionJugador(jugadorTurnoActual) + avanceAuto);

            return false;
            
        }else if(directivas.getPenalizacion() != 0){
            
            super.sonidoTurnoBloqueado();
            actualizarPenalizacionesJugadorVista();
            
        }
        
        return true;
        
    }

    @Override
    public void nuevaPartida() {
        iniciarPartida(super.nombres, POSICIONES_INICIO, TURNOS_BLOQUEO_INICIO, TURNO_INICIO);
    }

    @Override
    public void guardarPartida() {
        
        DatosPartidaModo2 datos = new DatosPartidaModo2(super.nombres, logica.getPosicionJugador(), ((LogicaJuegoModo2)logica).getTurnosBloqueo(), jugadorTurnoActual );
        
        try {
            GestorGuardado.guardarPartidaModo2(Constantes.RUTA_GUARDADO_PARTIDA_MODO_2, datos);
        } catch (IOException ex) {
            vista.mensajeErrorGuardado();
        }
        
    }

    @Override
    public void cargarPartida() {
        
        try {
            
            DatosPartidaModo2 datos = GestorGuardado.cargarPartidaModo2(Constantes.RUTA_GUARDADO_PARTIDA_MODO_2);
            
            iniciarPartida(datos.getNombres(), datos.getPosiciones(), datos.getTurnosBloqueo(), datos.getTurnoActual());
            
        } catch (IOException | ClassNotFoundException ex) {
            vista.mensajeErrorCarga();
        }
        
        
    }
    
    /**
     * Evento de finalización de partida por llegada a meta de un jugador.
     * 
     * se le pregunta al usuario si quiere volver a la vista de inicio.
     * @param id del jugador que ha llegado a la meta.
     */
    private void finalizarPartidaPorMeta(int jugador) {
        
        vista.bloquearBoton(false);
        
        iniciarTurno = false;
        
        if(vista.mensajeFinPartida(jugador, true)){
            super.volverMenuInicio();
        }
        
    }
    
    
    

    

}
