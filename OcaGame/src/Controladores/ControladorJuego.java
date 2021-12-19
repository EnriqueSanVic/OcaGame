

package Controladores;

import Vistas.VistaJuego;

/**
 *
 * @author Enrique Sánchez 
 */
public class ControladorJuego {

    private VistaJuego vista;
    
    public ControladorJuego(){
        this.vista = new VistaJuego(this);
    }
    
}
