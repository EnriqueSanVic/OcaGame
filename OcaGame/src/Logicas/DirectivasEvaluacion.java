

package Logicas;

/**
 * 
 * Clase a modod de estructura de datos para devolver las consecuencias de la evaluación de la casilla 
 * en la que un jugador ha caido en la lógica.
 * 
 * Estas consecuencias pueden ser una nueva posición devido a un desplazamiento provocado por las casillas que provocan esto
 * o un numero de penalizaciones ya sea en turnos de bloqueo o segundos.
 *
 * @author Enrique Sánchez 
 */
public class DirectivasEvaluacion {

    
    protected final int posicion;
    
    protected final int penalizacion;
    
    protected final boolean tirarOtraVez;
    
    public DirectivasEvaluacion(int posicion, int penalizacion, boolean tirarOtraVez ) {
        this.posicion = posicion;
        this.penalizacion = penalizacion;
        this.tirarOtraVez = tirarOtraVez;
    }
    
    
    
}
