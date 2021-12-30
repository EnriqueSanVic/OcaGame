

package Logicas;

import Modelos.DirectivasEvaluacion;

/**
 * Especificación de la lógica del modo 2.
 * 
 * @author Enrique Sánchez 
 */
public final class LogicaJuegoModo2 extends LogicaJuego{

    //array con los turnos de bloqueo del os jugadores
    private final int[] turnosBloqueo;

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
    
    public void setTurnosBloqueo(int jugador, int turnos){
        turnosBloqueo[jugador] = turnos;
    }

    public int[] getTurnosBloqueo() {
        return turnosBloqueo;
    }
    
    

}
