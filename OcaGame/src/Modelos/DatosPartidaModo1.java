

package Modelos;

import java.io.Serializable;

/**
 *
 * @author Enrique Sánchez 
 */
public class DatosPartidaModo1 implements Serializable{

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
