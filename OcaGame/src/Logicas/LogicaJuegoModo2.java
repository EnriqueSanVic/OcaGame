

package Logicas;

import Modelos.DirectivasEvaluacion;

/**
 *
 * @author Enrique Sánchez 
 */
public class LogicaJuegoModo2 extends LogicaJuego{

    //array con los turnos de bloqueo del os jugadores
    private int[] turnosBloqueo;

    public LogicaJuegoModo2() {
        super();
        
        turnosBloqueo = new int[2];
        
        turnosBloqueo[0] = 0;
        turnosBloqueo[1] = 0;
    }
    
    

    @Override
    public boolean evaluarTurnoInicio(int jugador) {
        
        //si el jugador tiene turnos de bloqueo entonces se decrementa y se retorna false
        if(turnosBloqueo[jugador] > 0){
            --turnosBloqueo[jugador];
            return false;
        } 
        
        return true;
        
    } 

    @Override
    public DirectivasEvaluacion evaluarTurnoFinal(int jugador) {
        
        DirectivasEvaluacion directivas = super.evaluarTurnoFinal(jugador);
        
        //si en las directivas de la casilla en la que se ha caido  no hay cambios de posicion y  hay turnos de bloqueo en la casilla en la que se ha caido
        if(directivas.getPenalizacion() > 0){
            turnosBloqueo[jugador] += directivas.getPenalizacion();
        }
        
        return new DirectivasEvaluacion(directivas.getPosicion(), directivas.getPenalizacion(), directivas.isTirarOtraVez());
    
    }
    
    public int getTurnosBloqueo(int jugador){
        return turnosBloqueo[jugador];
    }

}
