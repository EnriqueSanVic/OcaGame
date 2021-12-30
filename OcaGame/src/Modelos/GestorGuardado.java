

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
 * Clase de métodos estáticos para gestionar el guardado de partidas.
 * 
 * @author Enrique Sánchez 
 */
public final class GestorGuardado {

    /**
     * Metodo para guardar una partida Modo1.
     * 
     * @param path ruta donde se quiere guardar el ficharo de guardado.
     * @param datos objeto que contiene los datos de la partida.
     * @throws FileNotFoundException Excepcion de error del manejo de fichero.
     * @throws IOException Excepcion de error de entrada-salida.
     */
    public static void guardarPartidaModo1(String path, DatosPartidaModo1 datos) throws FileNotFoundException, IOException{
        
        FileOutputStream salida = new FileOutputStream(path);
        
        ObjectOutputStream escritor = new ObjectOutputStream(salida);
        
        escritor.writeObject(datos);
        
        escritor.close();
        
        salida.close();
        
    }
    
    /**
     * Metodo leer un fichero de guardado modo 1.
     * 
     * @param path ruta del fichero que se quiere buscar y recupear los datos de un partida.
     * @return datos de la partida modo 1.
     * @throws FileNotFoundException FileNotFoundException Excepcion de error del manejo de fichero.
     * @throws IOException IOException Excepcion de error de entrada-salida.
     * @throws ClassNotFoundException Excepcion de error al hacer casting de una clase. 
     */
    public static DatosPartidaModo1 cargarPartidaModo1(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
        
        FileInputStream entrada = new FileInputStream(path);
        
        ObjectInputStream lector = new ObjectInputStream(entrada);
        
        DatosPartidaModo1 retorno = (DatosPartidaModo1) lector.readObject();
        
        lector.close();
        
        entrada.close();
        
        return retorno;
        
    }
    
    /**
     * Metodo para guardar una partida Modo2.
     * 
     * @param path ruta donde se quiere guardar el ficharo de guardado.
     * @param datos Objeto que contiene los datos de la partida.
     * @throws FileNotFoundException Excepcion de error del manejo de fichero.
     * @throws IOException Excepcion de error de entrada-salida.
     */
    public static void guardarPartidaModo2(String path, DatosPartidaModo2 datos) throws FileNotFoundException, IOException{
        
        FileOutputStream salida = new FileOutputStream(path);
        
        ObjectOutputStream escritor = new ObjectOutputStream(salida);
        
        escritor.writeObject(datos);
        
        escritor.close();
        
        salida.close();
        
    }
    
    /**
     * Metodo leer un fichero de guardado modo 2.
     * 
     * @param path ruta del fichero que se quiere buscar y recupear los datos de un partida.
     * @return datos de la partida modo 2.
     * @throws FileNotFoundException FileNotFoundException Excepcion de error del manejo de fichero.
     * @throws IOException IOException Excepcion de error de entrada-salida.
     * @throws ClassNotFoundException Excepcion de error al hacer casting de una clase. 
     */
    public static DatosPartidaModo2 cargarPartidaModo2(String path) throws FileNotFoundException, IOException, ClassNotFoundException{
        
        FileInputStream entrada = new FileInputStream(path);
        
        ObjectInputStream lector = new ObjectInputStream(entrada);
        
        DatosPartidaModo2 retorno = (DatosPartidaModo2) lector.readObject();
        
        lector.close();
        
        entrada.close();
        
        return retorno;
        
    }
    
    /**
     * Si no existe un path de directorio crea el directorio.
     * @param path ruta del directorio. 
     */
    public static void crearCarpeta(String path){
        
        File directorio = new File(path);
        
        if(!directorio.exists()){
            directorio.mkdir();
        }
        
    }
    
}
