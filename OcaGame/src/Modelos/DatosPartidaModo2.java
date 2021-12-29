

package Modelos;

import java.io.Serializable;

/**
 *
 * @author Enrique Sánchez 
 */
public class DatosPartidaModo2 implements Serializable{

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
