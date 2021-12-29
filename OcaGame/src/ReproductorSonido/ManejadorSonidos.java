

package ReproductorSonido;


import Hilos.RegistradorHilos;

/**
 *
 * @author Enrique Sánchez 
 */
public class ManejadorSonidos {

    public static ReproductorContinuo hiloMusical(String path){
        
        return new ReproductorContinuo(path);
        
    }
    
    public static  ReproductorUnaVez hiloPuntual(String path, RegistradorHilos reg){
        
        return new ReproductorUnaVez(path, reg);
        
    }
    
    
}
