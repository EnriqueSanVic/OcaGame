
package ocagame;

import Controladores.ControladorJuego;
import Controladores.ControladorJuegoModo1;
import Controladores.ControladorJuegoModo2;

/**
 *
 * @author Alvaro
 */
public class OcaGame {

    public static void main(String[] args) {
        ControladorJuego controlador = new ControladorJuegoModo2(0, "kike", "alvaro");
    }
    
}
