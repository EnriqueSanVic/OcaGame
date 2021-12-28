
package Controladores;

import DatosEstaticos.Constantes;
import Vistas.VistaAutores;
import Vistas.VistaInicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @autor: Alvaro
 */
public class ControladorInicio implements ActionListener{
    //Constantes de configuracion.
    private final int IDIOMA_ESP = 0;
    private final int IDIOMA_ING = 1;
    
    //Atributos de la clase.
    protected VistaInicio vistaInicio;
    
    //Constructor.
    public ControladorInicio(VistaInicio vistaInicio) {
        this.vistaInicio = vistaInicio;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
            switch (ae.getActionCommand()) {
                //BOTON ESP
                case Constantes.BOTON_IDIOMA_ESP_COMMAND:
                    new VistaInicio(0);
                    this.vistaInicio.dispose();
                    break;
                //BOTON ING
                case Constantes.BOTON_IDIOMA_ING_COMMAND:
                    new VistaInicio(1);
                    this.vistaInicio.dispose();
                    break;
                //BOTON MODO 1
                case Constantes.BOTON_MODO_1_COMMAND:
                    break;
                //BOTON MODO 2
                case Constantes.BOTON_MODO_2_COMMAND:
                    break;
                //BOTON JUGAR
                case Constantes.BOTON_JUGAR_COMMAND:
                    break;
                //BOTON FOURNIER.
                case Constantes.ABRIR_AUTORES_COMMAND:
                    //Lanza la vista Autores.
                    new VistaAutores(this.vistaInicio, this.vistaInicio.getIdioma());
                break;
            default:
                break;
        }
            
    }

}
