

package ReproductorSonido;

import Hilos.Hilo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import sun.misc.IOUtils;

/**
 *
 * @author Enrique Sánchez 
 */
public class ReproductorUnaVez extends Thread implements Hilo{

    byte[] master_audio;
    private long duracion;
    
    
    public ReproductorUnaVez(String path) {
        
        try {

                AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
                   
                
                
                
        }catch(Exception ex){
            System.out.println("Erro al cargar el archivo");
        }
        
    }
    
    private AudioInputStream recuperarAudio(){
        
        ByteArrayInputStream b = new ByteArrayInputStream(master_audio);
        

        return null;
        
        
    }

    @Override
    public void run() {
        
        Clip copia;
        
    }
    
    
    

    @Override
    public void matar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
