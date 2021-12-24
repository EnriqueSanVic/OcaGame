

package Controladores;

import DatosEstaticos.Constantes;
import Logicas.DirectivasEvaluacion;
import Logicas.LogicaJuegoModo1;
import Vistas.VistaJuegoModo1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Enrique Sánchez 
 */
public class ControladorJuegoModo1 extends ControladorJuego{

    private final int SEGUNDOS_INICIO = 150;
    
    private Timer timer;

    private int segundos;
    
    private int casillaDestino, avanceAuto;
    
    private boolean iniciarTurno, controlAutomatico;
    
    public ControladorJuegoModo1() {
        super();
        
        this.vista = new VistaJuegoModo1(this);
        this.logica = new LogicaJuegoModo1();
        
        segundos = SEGUNDOS_INICIO;
        
        iniciarTurno = true;
        controlAutomatico = false;
        
        iniciarPartida();
    }

    @Override
    protected void iniciarPartida() {
        
        iniciarTemporizador();
        
        ((VistaJuegoModo1)vista).iniciarJugadorSalida();
        
        
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
            if(casillaDestino <= 63){
                vista.crearPuntero(casillaDestino, this);
                
                //si es un tiro sobrepasao
            }else{
            
                
                
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
        
        if (controlAutomatico) {
            
            controlAutomatico = false;
            
            logica.mover(Constantes.JUGADOR_1, avanceAuto);
            
            iniciarTurno = true;
            
        } else {
            //se mueve lógicamente el jugador hasta la casilla
            logica.mover(Constantes.JUGADOR_1, ultimoNumeroDado);

            //falta evaluar la tirada que contiene este objeto
            DirectivasEvaluacion directivas = ((LogicaJuegoModo1) logica).evaluarTurnoFinal(Constantes.JUGADOR_1);

            iniciarTurno = evaluardirectivas(directivas);

        }
       
    }

    private void iniciarTemporizador() {
        //Se repite a cada segundo.
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                
                if(segundos>0){
                    segundos-=1; //Resto un segundo en el label
                    ((VistaJuegoModo1)vista).setSegundosTemporizador(String.valueOf(segundos));                   
                }else{
                    finalizarPartida();
                }
            }
        });
        this.timer.start(); //Iniciamos el temporizador
    }
    
    //accion de fin de partida ya sea por tiempo o por ganar
    private void finalizarPartida(){
        timer.stop();
        
        
    }

    //retorna si se ha terminado el turno actual o no
    private boolean evaluardirectivas(DirectivasEvaluacion directivas) {
        
        //en el modo un jugador no se evalua el tira otra vez por que siempre tira otra vez
        
        if(directivas.getPosicion() != 0){
            
            System.out.println("Movimiento auto de " + directivas.getPosicion());
            
            controlAutomatico = true;
            
            avanceAuto = directivas.getPosicion();
            
            System.out.println("Avance auto " + avanceAuto);
            
            vista.mover(Constantes.JUGADOR_1, logica.getPosicionJugador(Constantes.JUGADOR_1) + avanceAuto);

            return false;
            
        }else if(directivas.getPenalizacion() != 0){
            
            int penalizacion = directivas.getPenalizacion() * 10;
            
            segundos -= penalizacion;
                    
             
            int valorPenalizacionesTotales = Integer.valueOf(((VistaJuegoModo1)vista).getPenalizacionLabel());
            
            ((VistaJuegoModo1)vista).setPenalizacionLabel(String.valueOf(valorPenalizacionesTotales - penalizacion));
            
        }
        
        return true;
        
    }

    

    

}
