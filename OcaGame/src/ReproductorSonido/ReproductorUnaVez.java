

package ReproductorSonido;

import Hilos.Hilo;
import Hilos.RegistradorHilos;
import java.io.File;
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
    
    private String id;
    
    public ReproductorUnaVez(String path, RegistradorHilos registrador) {
        
        this.id = path;
        
        this.registrador = registrador;
        
        this.registrador.aniadirHilo(this);
        
        this.setPriority(Thread.MAX_PRIORITY);
        
        try {
            
            audio = AudioSystem.getAudioInputStream(new File(path));
            duracion = audio.getFrameLength()/2;
   
                
        }catch(Exception ex){

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
            //si se mata el hilo va a saltar esta excepcion por el sleep para la reproducción
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
            
        }
        
        reproductor = null;
        audio= null;
        
    }

    @Override
    public String toString() {
        return "ReproductorUnaVez " + id;
    }

    
    
    
}
