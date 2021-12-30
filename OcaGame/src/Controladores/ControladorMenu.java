

package Controladores;

import DatosEstaticos.Constantes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador del menú de la vista de juego.
 *
 * @author Enrique Sánchez 
 */
public final class ControladorMenu implements ActionListener{

    private ControladorJuego controladorJuego;
    
    public ControladorMenu(ControladorJuego controladorJuego) {
        this.controladorJuego = controladorJuego;
    }
    
    
    /**
     * Escuchador de los eventos de pulsación sobre los botones que componen el menú.
     * @param es Evento tipo ActionEvent. 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        switch(ae.getActionCommand()){
            
            case Constantes.NUEVA_PARTIDA_COMMAND:
                controladorJuego.nuevaPartida();
                break;
             
            case Constantes.GUARDAR_PARTIDA_COMMAND:
                controladorJuego.guardarPartida();
                break;
                
            case Constantes.CARGAR_PARTIDA_COMMAND:
                controladorJuego.cargarPartida();
                break;
                
            case Constantes.SALIR_PARTIDA_COMMAND:
                controladorJuego.protocoloCierre();
                break;
            
            case Constantes.LANZAR_DADO_COMMAND:
                controladorJuego.lanzarDado();
                break;
            
            case Constantes.PANTALLA_COMPLETA_COMMAND:
                controladorJuego.accionPantallaCompleta();
                break;
        }
        
    }

    
    
}
