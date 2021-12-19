

package Logicas;

/**
 *
 * @author Enrique Sánchez 
 */
public class CasillaLogica {
    
    
    //caracteristicas modo 1
    //numero de casillas que te hace avanzar la casilla si se cae en ella
    protected final int avance;
    
    //numero de turnos retenido que te tiene la casilla si caes en ella
    protected final int penalizacion;
    
    protected final boolean tirarOtraVez;
    
    public CasillaLogica(int avance, int penalizacion) {
        this.avance = avance;
        this.penalizacion = penalizacion;
        this.tirarOtraVez = false;
    }

    public CasillaLogica(int avance, int penalizacion, boolean tirarOtraVez) {
        this.avance = avance;
        this.penalizacion = penalizacion;
        this.tirarOtraVez = tirarOtraVez;
    }
    
    
    
    
    
    
    
    
    
}
