

package ReproductorSonido;

import Hilos.Hilo;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Enrique Sánchez 
 */
public class ReproductorContinuo extends Thread implements Hilo{

    private File file;
    
    private Clip clip;
    
    public ReproductorContinuo(String path) {
        this.file = new File(path);
    }

    @Override
    public void run() {
        
        if (file.exists()) {

            try {

                clip = AudioSystem.getClip();

                AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);

                clip.open(inputStream);

                clip.loop(Clip.LOOP_CONTINUOUSLY);

                clip.start();

            } catch (LineUnavailableException ex) {
                
                System.out.println(ex.getMessage());
                
            } catch (UnsupportedAudioFileException ex) {
                
                System.out.println(ex.getMessage());

            } catch (IOException ex) {
                
                System.out.println(ex.getMessage());
            }

        }
        
    }

    @Override
    public void matar() {
        clip.stop();
        clip = null;
    }
    

    
}
