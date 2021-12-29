

package ReproductorSonido;

import Hilos.Hilo;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 *
 * @author Enrique Sánchez 
 */
public class ReproductorContinuo extends Thread implements Hilo{

    private File file;
    
    private Clip clip;
    
    private String id;
    
    public ReproductorContinuo(String path) {
        this.id = path;
        this.file = new File(path);
    }

    @Override
    public void run() {
        
        if (file.exists()) {

            try {

                clip = AudioSystem.getClip();

                AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);

                clip.open(inputStream);

                clip.loop(Clip.LOOP_CONTINUOUSLY); //Para que se repita la cancion infinitamente mientras el juego este abierto.

                clip.start();

            } catch (Exception ex) {
                
            }

        }
        
    }

    @Override
    public void matar() {
        
        if(clip != null){
            clip.stop();
            clip = null;
        }
        
    }
    
    
    @Override
    public String toString() {
        return "ReproductorContinuo" + id;
    }

    
}
