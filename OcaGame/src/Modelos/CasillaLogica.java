

package Modelos;

/**
 * Clase a modo de estructura de datos para representar una casilla lógica.
 *
 * @author Enrique Sánchez 
 */
public final class CasillaLogica {
    
    
    //caracteristicas modo 1
    //numero de casillas que te hace avanzar la casilla si se cae en ella
    private final int avance;
    
    //numero de turnos retenido que te tiene la casilla si caes en ella
    private final int penalizacion;
    
    private final boolean tirarOtraVez;
    
    /**
     * Constructor
     * @param avance avence positivo o negativo de la consecuencia de caer sobre la casilla.
     * @param penalizacion penalización de turnos o tiempo.
     */
    public CasillaLogica(int avance, int penalizacion) {
        this.avance = avance;
        this.penalizacion = penalizacion;
        this.tirarOtraVez = false;
    }

    /**
     * Constructor
     * @param avance avence positivo o negativo de la consecuencia de caer sobre la casilla.
     * @param penalizacion penalización de turnos o tiempo.
     * @param tirarOtraVez tirar otra vez-¡.
     */
    public CasillaLogica(int avance, int penalizacion, boolean tirarOtraVez) {
        this.avance = avance;
        this.penalizacion = penalizacion;
        this.tirarOtraVez = tirarOtraVez;
    }

    public int getAvance() {
        return avance;
    }

    public int getPenalizacion() {
        return penalizacion;
    }

    public boolean isTirarOtraVez() {
        return tirarOtraVez;
    }
    
    
    
    
    
    
    
    
    
    
    
}
