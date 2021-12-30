

package Modelos;

import java.io.Serializable;

/**
 * Clase a modo de estructura de datos de las vatiables relevantes de una partida modo1.
 * 
 * Esta clase es parte del guardado de los datos y se puede serializar para guardar una partida modo 1.
 *
 * @author Enrique Sánchez 
 */
public final class DatosPartidaModo1 implements Serializable{

    private String nombre;
    
    private int posicion;

    private int segundos;
     
    private int penalizaciones;

    public DatosPartidaModo1(String nombre, int posicion, int segundos, int penalizaciones) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.segundos = segundos;
        this.penalizaciones = penalizaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public int getSegundos() {
        return segundos;
    }

    public int getPenalizaciones() {
        return penalizaciones;
    }
     
    
     
     
    
}
