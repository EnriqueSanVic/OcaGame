

package Controladores;

import DatosEstaticos.Constantes;
import Modelos.DirectivasEvaluacion;
import Logicas.LogicaJuegoModo1;
import Vistas.VistaJuegoModo1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Enrique Sánchez 
 */
public final class ControladorJuegoModo1 extends ControladorJuego{

    private final int SEGUNDOS_INICIO = 150;
    
    private Timer timer;

    private int segundos;
    
    private int penalizaciones;
    
    private int casillaDestino, avanceAuto, retrocesoAcumuladoCasillaFin;
    
    private boolean iniciarTurno, controlAutomatico, reevaluarDespuesDeAuto, enJuego, victoria;
    
    public ControladorJuegoModo1(int idioma, String jugador1, String jugador2) {
        
        super(idioma,jugador1, jugador2);
        
        this.vista = new VistaJuegoModo1(this, idioma, jugador1, jugador2);
        this.logica = new LogicaJuegoModo1();
        
        segundos = SEGUNDOS_INICIO;
        
        retrocesoAcumuladoCasillaFin = 0;
        
        penalizaciones = 0;
        
        controlAutomatico = false;
        victoria = false;
        
        iniciarPartida();
    }

    @Override
    protected void matarHilos() {
        super.matarHilos();
        timer.stop();
    }
    
    

    @Override
    protected void iniciarPartida() {
        
        iniciarTemporizador();
        
        ((VistaJuegoModo1)vista).setPenalizacionLabel(String.valueOf(penalizaciones));
        
        //se inicia el jugador en la posicion que tiene la lógica en este momento
        (vista).iniciarJugadorSalida(Constantes.JUGADOR_1, logica.getPosicionJugador(Constantes.JUGADOR_1));
        
        enJuego = true;
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
        
        
        
        if(logica.isGanador(Constantes.JUGADOR_1)){
             finalizarPartidaPorMeta();
        }
       
    }

    private void iniciarTemporizador() {
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
                    finalizarPartidaPorTiempo();
                }
            }
        });
        this.timer.start(); //Iniciamos el temporizador
    }
    
    

    //retorna si se ha terminado el turno actual o no
    private boolean evaluardirectivas(DirectivasEvaluacion directivas) {
        
        
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

    //accion de fin de partida ya sea por tiempo o por ganar
    private void finalizarPartidaPorTiempo(){
        
        rutinaFinPartida();
        
        enJuego = false;
        
        victoria = false;
        
        super.sonidoPerder();
        
        evaluarFinal();
        
        
    }
    
    private void finalizarPartidaPorMeta(){
    
        //si se llega a la meta todavía tiene que haber segundos en el contador
        
        rutinaFinPartida();
        
        if(segundos > 0){
            
            enJuego = false;
            
            victoria = true;
            
            super.sonidoGanar();
            
        }else{
            super.sonidoPerder();
        }
        
        evaluarFinal();
        
    }
     
    private void rutinaFinPartida(){
        
        timer.stop();
        
        vista.bloquearBoton(false);
        
        iniciarTurno = false;

    }
    
    private void evaluarFinal() {
        
        if(vista.mensajeFinPartida(Constantes.JUGADOR_1, victoria)){
            super.volverMenuInicio();
        }
        
        
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

    

    

}
