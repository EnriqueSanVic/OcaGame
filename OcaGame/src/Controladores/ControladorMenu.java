

package Controladores;

import DatosEstaticos.Constantes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Enrique Sánchez 
 */
public class ControladorMenu implements ActionListener{

    private ControladorJuego controladorJuego;
    
    public ControladorMenu(ControladorJuego controladorJuego) {
        this.controladorJuego = controladorJuego;
    }
    
    

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
