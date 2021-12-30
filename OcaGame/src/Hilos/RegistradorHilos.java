package Hilos;

/**
 * Interfaz para implementarla en un controlador y que este pued gestiornar los hilos de la aplicación.
 *
 * @author Enrique Sánchez
 */
public interface RegistradorHilos {
    
    /**
     * Método para añadir un hilo al registro de hilos activos.
     * @param hilo activo.
     */
    void aniadirHilo(Hilo hilo);
    
    /**
     * Método para eliminar un hilo al registro de hilos activos.
     * @param hilo inactivo.
     */
    void eliminarHilo(Hilo hilo);
    
}
