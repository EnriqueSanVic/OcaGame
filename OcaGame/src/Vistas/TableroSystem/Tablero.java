
package Vistas.TableroSystem;

import DatosEstaticos.Constantes;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    
    private NotificableTablero notificable;

    protected Ficha ficha1, ficha2;
    
    protected PunteroGrafico puntero;
    
    private CasillaGrafica[] casillas;
    
    public Tablero(int jugadores, NotificableTablero notificable) {
        
        this.notificable = notificable;

        //se carga la imagen del tablero
        try {
            this.imagenTablero = ImageIO.read(new File(IMAGE_PATH));
        } catch (IOException ex) {
            System.out.println("Error de carga de imagen");
        }
        
        //se le asigna un tamaño al tablero
        this.setSize(WIDTH, HEIGHT);
        
        this.setLayout(null);
        
        
        initFichas(jugadores);
        initCasillas();
        

    }

    //se pinta el componente con la imagen del tablero
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(imagenTablero, 0, 0, this);
    }

    private void initFichas(int jugadores) {
        
        
        if (jugadores == 1) {
            
            ficha1 = new Ficha(0, Constantes.JUGADOR_1, 90, 700);

            this.add(ficha1);
            
        } else if (jugadores == 2) {
            
            ficha1 = new Ficha(0, Constantes.JUGADOR_1, 90, 700);

            this.add(ficha1);

            ficha2 = new Ficha(0, Constantes.JUGADOR_2, 90, 700);

            this.add(ficha2);
        }
        
        
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
        
        casillas[27] = new CasillaGrafica(102, 8,35, 93, false);
        
        casillas[28] = new CasillaGrafica(7, 105,93, 37, true);
        
        casillas[29] = new CasillaGrafica(7, 147,103, 63, true);
        
        casillas[30] = new CasillaGrafica(7, 214,103, 63, true);
        
        casillas[31] = new CasillaGrafica(7, 282,103, 63, true);
        
        casillas[32] = new CasillaGrafica(7, 349,103, 63, true);
        
        casillas[33] = new CasillaGrafica(7, 417,103, 63, true);
        
        casillas[34] = new CasillaGrafica(7, 484,103, 63, true);
        
        casillas[35] = new CasillaGrafica(7, 551,93, 37, true);
        
        casillas[36] = new CasillaGrafica(102, 588,35, 93, false);
        
        casillas[37] = new CasillaGrafica(142, 579,62, 105, false);
        
        casillas[38] = new CasillaGrafica(208, 579,62, 105, false);
        
        casillas[39] = new CasillaGrafica(274, 579,62, 105, false);
        
        casillas[40] = new CasillaGrafica(340, 579,62, 105, false);
        
        casillas[41] = new CasillaGrafica(407, 579,62, 105, false);
        
        casillas[42] = new CasillaGrafica(473, 579,62, 105, false);
        
        casillas[43] = new CasillaGrafica(540, 589,43, 93, false);
        
        casillas[44] = new CasillaGrafica(579, 538,103, 39, true);
        
        casillas[45] = new CasillaGrafica(579, 470,103, 63, true);
        
        casillas[46] = new CasillaGrafica(579, 402,103, 63, true);
        
        casillas[47] = new CasillaGrafica(579, 335,103, 63, true);
        
        casillas[48] = new CasillaGrafica(579, 267,103, 63, true);
        
        casillas[49] = new CasillaGrafica(579, 226,103, 36, true);
        
        casillas[50] = new CasillaGrafica(539, 119,48, 93, false);
        
        casillas[51] = new CasillaGrafica(470, 118,63, 105, false);
        
        casillas[52] = new CasillaGrafica(402, 118,63, 105, false);
        
        casillas[53] = new CasillaGrafica(334, 118,63, 105, false);
        
        casillas[54] = new CasillaGrafica(266, 118,63, 105, false);
        
        casillas[55] = new CasillaGrafica(213, 119,48, 93, false);
        
        casillas[56] = new CasillaGrafica(118, 210,93, 64, true);
        
        casillas[57] = new CasillaGrafica(118, 279,103, 65, true);
        
        casillas[58] = new CasillaGrafica(118, 348,103, 68, true);
        
        casillas[59] = new CasillaGrafica(118, 420,103, 45, true);
        
        casillas[60] = new CasillaGrafica(213, 478,48, 93, false);
        
        casillas[61] = new CasillaGrafica(267, 467,62, 105, false);
        
        casillas[62] = new CasillaGrafica(350, 467,62, 105, false);
        
        for (int i = 0; i < casillas.length; i++) {
            
            if(casillas[i] != null){
                this.add(casillas[i]);
            }
        }

    }
    
    public void crearPuntero(int nCasilla, NotificableTablero escuchadorPuntero){
        
        //si existe un puntero por que no se ha controlado la destrucción, se destruye antes de crear otro
        if(puntero != null){
            puntero.parar();
            puntero = null;
        }
        
        puntero = new PunteroGrafico(casillas[nCasilla].getCentroAbsoluto(), escuchadorPuntero, this);
        
        this.add(puntero);
        
        puntero.iniciar();
        
        this.repaint();
        
    }
    
    public void eliminarPuntero(){
        
        puntero.parar();
        
        this.remove(puntero);
        
        puntero = null;
        
        this.repaint();
        
    }
    
    

   
    
    
    public void mover(int jugador, int destino){
        
        Ficha ficha = null;
        
        if(jugador == Constantes.JUGADOR_1){
            ficha = ficha1;
        }else if(jugador == Constantes.JUGADOR_2){
            ficha = ficha2;
        }
        
        new ManejadorFicha(destino, ficha, casillas, notificable).start();
        
    }

    public Ficha getFicha1() {
        return ficha1;
    }

    public Ficha getFicha2() {
        return ficha2;
    }

    public CasillaGrafica[] getCasillas() {
        return casillas;
    }
    
    
    
    
    
}
