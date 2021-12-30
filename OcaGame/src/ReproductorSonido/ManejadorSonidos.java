

package ReproductorSonido;


import Hilos.RegistradorHilos;

/**
 * Clase para manejar distintos tipos de audio.
 *
 * @author Enrique Sánchez 
 */
public final class ManejadorSonidos {

    /**
     * Crea un objeto para manejar el audio de un reproductor en bucle.
     * @param path ruta del fichero de audio.
     * @return objeto de reproductor.
     */
    public static ReproductorContinuo hiloMusical(String path){
        
        return new ReproductorContinuo(path);
        
    }
    
    /**
     * Crea un objeto para manejar el audio de un reproductor de una sola ejecución.
     * @param path ruta del fichero de audio.
     * @return reg objeto de reproductor.
     */
    public static  ReproductorUnaVez hiloPuntual(String path, RegistradorHilos reg){
        
        return new ReproductorUnaVez(path, reg);
        
    }
    
    
}
