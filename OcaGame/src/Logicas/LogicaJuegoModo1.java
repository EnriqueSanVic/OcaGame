

package Logicas;

/**
 *
 * @author Enrique Sánchez 
 */
public class LogicaJuegoModo1 extends LogicaJuego{



    
    
    
    @Override
    public boolean evaluarTurnoInicio(int jugador) {
        return true;
    }

    @Override
    public DirectivasEvaluacion evaluarTurnoFinal(int jugador) { 
        return null;
    }

    

}
