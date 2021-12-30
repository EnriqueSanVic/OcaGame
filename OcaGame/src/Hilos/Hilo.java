package Hilos;

/**
 * Interfaz para clases que sean un hilo o manejen uno.
 * 
 * Sirve para tratar a todos los hilos de una forma homogenea.
 * 
 * 
 * @author Enrique Sánchez
 */
public interface Hilo {
    
    /**
     * Metodo a implementar en cada hilo para matar el hilo.
     */
    void matar();
    
}
