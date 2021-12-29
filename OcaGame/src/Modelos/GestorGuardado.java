

package Modelos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Enrique Sánchez 
 */
public class GestorGuardado {

    public static void guardarPartidaModo1(String path, DatosPartidaModo1 datos) throws FileNotFoundException, IOException{
        
        FileOutputStream salida = new FileOutputStream(path);
        
        ObjectOutputStream escritor = new ObjectOutputStream(salida);
        
        escritor.writeObject(datos);
        
        escritor.close();
        
        salida.close();
        
    }
    
    public static DatosPartidaModo1 cargarPartidaModo1(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
        
        FileInputStream entrada = new FileInputStream(path);
        
        ObjectInputStream lector = new ObjectInputStream(entrada);
        
        DatosPartidaModo1 retorno = (DatosPartidaModo1) lector.readObject();
        
        lector.close();
        
        entrada.close();
        
        return retorno;
        
    }
    
    public static void guardarPartidaModo2(String path, DatosPartidaModo2 datos) throws FileNotFoundException, IOException{
        
        FileOutputStream salida = new FileOutputStream(path);
        
        ObjectOutputStream escritor = new ObjectOutputStream(salida);
        
        escritor.writeObject(datos);
        
        escritor.close();
        
        salida.close();
        
    }
    
    public static DatosPartidaModo2 cargarPartidaModo2(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
        
        FileInputStream entrada = new FileInputStream(path);
        
        ObjectInputStream lector = new ObjectInputStream(entrada);
        
        DatosPartidaModo2 retorno = (DatosPartidaModo2) lector.readObject();
        
        lector.close();
        
        entrada.close();
        
        return retorno;
        
    }
    
    public static void crearCarpeta(String path){
        
        File directorio = new File(path);
        
        if(!directorio.exists()){
            directorio.mkdir();
        }
        
    }
    
}
