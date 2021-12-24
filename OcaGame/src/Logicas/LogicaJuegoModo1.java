

package Logicas;

/**
 *
 * @author Enrique Sánchez 
 */
public class LogicaJuegoModo1 extends LogicaJuego{

    public LogicaJuegoModo1() {
        super();
    }
    
    

    //en el modo 1 siempre va a poder avanzar en su turno , no existen los bloqueos
    @Override
    public boolean evaluarTurnoInicio(int jugador) {
        return true;
    }

    
    /**
     * El metodo sirve para evaluar la consecuencia del final del turno osea en la casilla en la que se 
     * ha caido, las consecuencias pueden ser de tiempo o de movimiento. 
     * 
     * 
     * @param jugador Indice del jugador, en este caso siempre será 1
     * @return Se retorna un objeto DirectivasEvaluacion para que el controlador sepa actuar en base a las directivas que este contiene como resultado.
     */
    @Override
    public DirectivasEvaluacion evaluarTurnoFinal(int jugador) {
        
      
        
        //en el modo 1 no se actua con la penalizacion, se le retorna al controlador para que el actue
        
        return super.evaluarTurnoFinal(jugador);
       
    }

}
