
package Vistas;

import DatosEstaticos.Constantes;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @autor: Alvaro
 */
public class Tablero extends JPanel{
    
    private final String IMAGE_PATH = "./img/tablero/tablero.png";
    
    private BufferedImage imagenTablero;
    protected final int HEIGHT = 800;
    protected final int WIDTH = 800;

    protected Ficha ficha1, ficha2;
    
    private CasillaGrafica[] casillas;
    
    public Tablero() {

        //se carga la imagen del tablero
        try {
            this.imagenTablero = ImageIO.read(new File(IMAGE_PATH));
        } catch (IOException ex) {
            System.out.println("Error de carga de imagen");
        }
        
        //se le asigna un tamaño al tablero
        this.setSize(WIDTH, HEIGHT);
        
        this.setLayout(null);
        
        
        initFichas();
        initCasillas();
        
        
        
    }

    

    //se pinta el componente con la imagen del tablero
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(imagenTablero, 0, 0, this);
    }

    private void initFichas() {
        
        ficha1 = new Ficha(0, Constantes.JUGADOR_1, 90, 700);
        
        this.add(ficha1);
        
        ficha2 = new Ficha(0, Constantes.JUGADOR_2, 90, 700);
        
        this.add(ficha2);
        
    }

    private void initCasillas() {
        
        casillas = new CasillaGrafica[63];
        
        casillas[0] = new CasillaGrafica(7, 689,245, 105, false);
        
        casillas[1] = new CasillaGrafica(258, 689,62, 105, false);
        
        casillas[2] = new CasillaGrafica(325, 689,63, 105, false);
        
        casillas[3] = new CasillaGrafica(393, 689,63, 105, false);
        
        casillas[4] = new CasillaGrafica(460, 689,63, 105, false);
        
        casillas[5] = new CasillaGrafica(528, 689,63, 105, false);
        
        casillas[6] = new CasillaGrafica(595, 689,63, 105, false);
        
        casillas[7] = new CasillaGrafica(662, 700,35, 93, false);
        
        casillas[8] = new CasillaGrafica(700, 664,93, 35, true);
        
        casillas[9] = new CasillaGrafica(690, 598,103, 60, true);
        
        casillas[10] = new CasillaGrafica(690, 533,103, 60, true);
        
        casillas[11] = new CasillaGrafica(690, 468,103, 60, true);
        
        casillas[12] = new CasillaGrafica(690, 403,103, 60, true);
        
        casillas[13] = new CasillaGrafica(690, 338,103, 60, true);
        
        casillas[14] = new CasillaGrafica(690, 273,103, 60, true);
        
        casillas[15] = new CasillaGrafica(690, 208,103, 60, true);
        
        casillas[16] = new CasillaGrafica(690, 143,103, 60, true);
        
        casillas[17] = new CasillaGrafica(700, 105,93, 35, true);
        
        casillas[18] = new CasillaGrafica(662, 8,35, 93, false);
        
        casillas[19] = new CasillaGrafica(596, 8,61, 105, false);
        
        casillas[20] = new CasillaGrafica(532, 8,61, 105, false);
        
        casillas[21] = new CasillaGrafica(468, 8,61, 105, false);
        
        casillas[22] = new CasillaGrafica(403, 8,61, 105, false);
        
        casillas[23] = new CasillaGrafica(337, 8,61, 105, false);
        
        casillas[24] = new CasillaGrafica(272, 8,61, 105, false);
        
        casillas[25] = new CasillaGrafica(207, 8,61, 105, false);

        casillas[26] = new CasillaGrafica(142, 8,61, 105, false);
        
        
        for (int i = 0; i < casillas.length; i++) {
            
            if(casillas[i] != null){
                this.add(casillas[i]);
            }
        }
        
        
        
        
    }

    public void initEscena() {
        
        //se inicializa la ficha en la casilla
        ManejadorFicha.iniciarEnCasilla(0, ficha1, casillas);
        ManejadorFicha.iniciarEnCasilla(0, ficha2, casillas);
        
        ManejadorFicha manejador = new ManejadorFicha(18, ficha1, casillas);
        manejador.start();
        
        try {
            Thread.sleep(7000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        manejador = new ManejadorFicha(18, ficha2, casillas);
        manejador.start();
        
        
        
    }
    
    
    
}
