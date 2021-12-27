

package Controladores;

import DatosEstaticos.Constantes;
import Logicas.DirectivasEvaluacion;
import Logicas.LogicaJuegoModo2;
import Vistas.VistaJuegoModo1;
import Vistas.VistaJuegoModo2;

/**
 *
 * @author Enrique Sánchez 
 */
public final class ControladorJuegoModo2 extends ControladorJuego{

    private int jugadorTurnoActual, casillaDestino, retrocesoAcumuladoCasillaFin, avanceAuto;
    
    private boolean iniciarTurno, controlAutomatico, reevaluarDespuesDeAuto, tirarOtraVez;
    
    
    public ControladorJuegoModo2(int idioma, String jugador1, String jugador2) {
        
        super(idioma, jugador1, jugador2);
        
        super.vista = new VistaJuegoModo2(this, idioma, jugador1, jugador2);
        super.logica = new LogicaJuegoModo2();
        
        retrocesoAcumuladoCasillaFin = 0;
        avanceAuto = 0;
        controlAutomatico = false;
        
        iniciarPartida();
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

    @Override
    protected void iniciarPartida() {
        
        //se inicia el jugadores en la posiciones que tiene la lógica en este momento
        (vista).iniciarJugadorSalida(Constantes.JUGADOR_1, logica.getPosicionJugador(Constantes.JUGADOR_1));
        (vista).iniciarJugadorSalida(Constantes.JUGADOR_2, logica.getPosicionJugador(Constantes.JUGADOR_2));
        
        jugadorTurnoActual = Constantes.JUGADOR_1;
        
        actualizarTurnoJugadorVista();
        actualizarPenalizacionesJugadorVista(Constantes.JUGADOR_1);
        actualizarPenalizacionesJugadorVista(Constantes.JUGADOR_2);
        
        iniciarTurno = true;
        
        tirarOtraVez = false;


    }
    
    private void cambiarTurno(){
        
        if(jugadorTurnoActual == Constantes.JUGADOR_1){
            
            jugadorTurnoActual = Constantes.JUGADOR_2;
            
        }else if(jugadorTurnoActual == Constantes.JUGADOR_2){
            
            jugadorTurnoActual = Constantes.JUGADOR_1;
        }
        
        actualizarTurnoJugadorVista();
        
    }
    
    private void actualizarTurnoJugadorVista(){
        
        ((VistaJuegoModo2)vista).enfatizarNombreTurno(jugadorTurnoActual);
        
    }
    
    private void actualizarPenalizacionesJugadorVista(){
        
        ((VistaJuegoModo2)vista).actualizarPenalizacionesJugador(jugadorTurnoActual, ((LogicaJuegoModo2)logica).getTurnosBloqueo(jugadorTurnoActual));
        
    }
    
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
             finalizarPartidaPorMeta(jugadorTurnoActual);
        }
        
        if(iniciarTurno && !tirarOtraVez){
            cambiarTurno();
        }

    }
    
    private boolean evaluardirectivas(DirectivasEvaluacion directivas) {
        
        //en el modo un jugador no se evalua el tira otra vez por que siempre tira otra vez
        
        tirarOtraVez = directivas.isTirarOtraVez();
        
        if(directivas.getPosicion() != 0){
            
            controlAutomatico = true;
            
            avanceAuto = directivas.getPosicion();
            
            
            
            vista.mover(jugadorTurnoActual, logica.getPosicionJugador(jugadorTurnoActual) + avanceAuto);

            return false;
            
        }else if(directivas.getPenalizacion() != 0){
            
            actualizarPenalizacionesJugadorVista();
            
        }
        
        return true;
        
    }

    @Override
    public void nuevaPartida() {
    }

    @Override
    public void guardarPartida() {
    }

    @Override
    public void cargarPartida() {
    }

    private void finalizarPartidaPorMeta(int jugador) {
        
        vista.bloquearBoton(false);
        
        iniciarTurno = false;
        
        if(vista.mensajeFinPartida(jugador, true)){
            super.volverMenuInicio();
        }
        
    }
    
    

    

}
