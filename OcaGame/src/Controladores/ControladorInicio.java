
package Controladores;

import DatosEstaticos.Constantes;
import Vistas.VistaAutores;
import Vistas.VistaInicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @autor: Alvaro
 */
public class ControladorInicio extends WindowAdapter implements ActionListener{
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
                    protocoloCambioIdioma(this.IDIOMA_ESP); //Cambiamos el idioma a español
                    break;
                //BOTON ING
                case Constantes.BOTON_IDIOMA_ING_COMMAND:
                    protocoloCambioIdioma(this.IDIOMA_ING); //Cambiamos el idioma a ingles
                    break;
                //BOTON MODO 1
                case Constantes.BOTON_MODO_1_COMMAND:
                    this.vistaInicio.modo1ON(true); //Cambiamos el modo del juego a modo1
                    break;
                //BOTON MODO 2
                case Constantes.BOTON_MODO_2_COMMAND:
                    this.vistaInicio.modo1ON(false); //Cambiamos el modo del juego a modo2
                    break;
                //BOTON JUGAR
                case Constantes.BOTON_JUGAR_COMMAND: 
                    String nombreJ1 = this.vistaInicio.getTextFieldNameJ1();
                    String nombreJ2 = this.vistaInicio.getTextFieldNameJ2();
                    //Modo 1
                    if(this.vistaInicio.isModo1activado()){
                        if(!nombreJ1.isEmpty()){
                            //Lanzamos el juego en modo 1 con el idioma y el nombre seleccionados.
                            new ControladorJuegoModo1(this.vistaInicio.getIdioma(), nombreJ1, nombreJ2); 
                            this.vistaInicio.dispose();
                        }else{
                            this.vistaInicio.mensajeErrorCampos();
                        }
                    //Modo 2    
                    }else{
                        if(!nombreJ1.isEmpty() && !nombreJ2.isEmpty()){
                            //Lanzamos el juego en modo 2 con el idioma y los nombres seleccionados.
                            new ControladorJuegoModo2(this.vistaInicio.getIdioma(), nombreJ1, nombreJ2); 
                            this.vistaInicio.dispose();
                        }else{
                            this.vistaInicio.mensajeErrorCampos();
                        }
                    }
                    break;
                //BOTON FOURNIER AUTORES.
                case Constantes.ABRIR_AUTORES_COMMAND:
                    new VistaAutores(this.vistaInicio, this.vistaInicio.getIdioma()); //Lanzamos la vista Autores con el idioma adecuado.
                break;
            default:
                break;
        }
            
    }
    
    //escuchador para el cierre de la ventana

    @Override
    public void windowClosing(WindowEvent we) {
        protocoloCierre();
    }

    //Metodo que se asegura de que el usuario quiso salir.
    private void protocoloCierre() {
        if(vistaInicio.mensajeSalirJuego()){
           System.exit(0);
        }
    }

    //Metodo que se asegura de que el usuario quiso salir.
    private void protocoloCambioIdioma(int idioma) {
        if(vistaInicio.mensajeCambioIdioma()){
           new VistaInicio(idioma); //Lanzamos nueva vista del juego con el idioma seleccionado.
           this.vistaInicio.dispose();
        }
    }

}
