

package Modelos;

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
public final class DirectivasEvaluacion {

    
    private final int posicion;
    
    private final int penalizacion;
    
    private final boolean tirarOtraVez;
    
    public DirectivasEvaluacion(int posicion, int penalizacion, boolean tirarOtraVez ) {
        this.posicion = posicion;
        this.penalizacion = penalizacion;
        this.tirarOtraVez = tirarOtraVez;
    }

    public int getPosicion() {
        return posicion;
    }

    public int getPenalizacion() {
        return penalizacion;
    }

    public boolean isTirarOtraVez() {
        return tirarOtraVez;
    }
    
    
    
    
    
}
