

package Modelos;

import java.io.Serializable;

/**
 * Clase a modo de estructura de datos de las vatiables relevantes de una partida modo2.
 * 
 * Esta clase es parte del guardado de los datos y se puede serializar para guardar una partida modo 2.
 *
 * @author Enrique Sánchez 
 */
public final class DatosPartidaModo2 implements Serializable{

    private String[] nombres;
    
    private int[] posiciones;
    
    private int[] turnosBloqueo;
    
    private int turnoActual;

    public DatosPartidaModo2(String[] nombres, int[] posiciones, int[] turnosBloqueo, int turnoActual) {
        this.nombres = nombres;
        this.posiciones = posiciones;
        this.turnosBloqueo = turnosBloqueo;
        this.turnoActual = turnoActual;
    }

    public String[] getNombres() {
        return nombres;
    }
    
    

    public int[] getPosiciones() {
        return posiciones;
    }

    public int[] getTurnosBloqueo() {
        return turnosBloqueo;
    }

    public int getTurnoActual() {
        return turnoActual;
    }
    
    
    
}
