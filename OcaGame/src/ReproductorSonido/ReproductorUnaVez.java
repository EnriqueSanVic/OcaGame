

package ReproductorSonido;

import Hilos.Hilo;
import Hilos.RegistradorHilos;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 *
 * @author Enrique Sánchez 
 */
public class ReproductorUnaVez extends Thread implements Hilo{

    
    private AudioInputStream audio;

    private long duracion;
    
    private Clip reproductor;
    
    private RegistradorHilos registrador;
    
    public ReproductorUnaVez(String path, RegistradorHilos registrador) {
        
        this.registrador = registrador;
        
        this.registrador.aniadirHilo(this);
        
        this.setPriority(Thread.MAX_PRIORITY);
        
        try {
            
            audio = AudioSystem.getAudioInputStream(new File(path));
            duracion = audio.getFrameLength();
   
                
        }catch(Exception ex){
            System.out.println("Error al cargar el archivo");
            System.out.println(ex.getMessage());
        }
        
    }

    

    @Override
    public void run() {
        
        try {
            
            reproductor = AudioSystem.getClip();
            
            reproductor.open(audio);
            
            reproductor.start();
            
            Thread.sleep(duracion);
            
            reproductor.stop();
            
            reproductor.flush();
            
            reproductor.close();
            
            audio.close();
            
            
        } catch (Exception ex) {
            System.out.println("Error de carga de audio");
        }
        
        reproductor = null;
        audio= null;

        registrador.eliminarHilo(this);
        
    }
    



    @Override
    public void matar() {
        
        try {
            
            reproductor.stop();
            
            reproductor.flush();
            
            reproductor.close();
            
            
            audio.close();
            
            
        } catch (Exception ex) {
            Logger.getLogger(ReproductorUnaVez.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        reproductor = null;
        audio= null;
        
    }

    
    
}
