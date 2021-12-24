

package Controladores;

import DatosEstaticos.Constantes;
import Logicas.DirectivasEvaluacion;
import Logicas.LogicaJuegoModo1;
import Vistas.VistaJuegoModo1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Enrique Sánchez 
 */
public class ControladorJuegoModo1 extends ControladorJuego{

    private Timer timer;

    private int segundos;
    
    private int casillaDestino;
    
    private boolean iniciarTurno;
    
    public ControladorJuegoModo1() {
        super();
        
        this.vista = new VistaJuegoModo1(this);
        this.logica = new LogicaJuegoModo1();
        
        iniciarTurno = true;
        
        iniciarPartida();
    }

    @Override
    protected void iniciarPartida() {
        
        iniciarTemporizador();
        
        ((VistaJuegoModo1)vista).iniciarJugadorSalida();
        
        
    }
    
    @Override
    protected boolean sePuedeTirarDado() {
        return iniciarTurno;
    }
    

    @Override
    public void eventoFinalizacionDado() {

        //solo se tira el dado si se puede tirar
        if (((LogicaJuegoModo1) logica).evaluarTurnoInicio(Constantes.JUGADOR_1)
                && iniciarTurno) {

            iniciarTurno = false;
            
            casillaDestino = logica.getPosicionJugador(Constantes.JUGADOR_1) + this.ultimoNumeroDado;
           
            //System.out.println("Ultimo Numero " + this.ultimoNumeroDado);
            
            System.out.println("Posicion Logica " + logica.getPosicionJugador(Constantes.JUGADOR_1));
            
            //System.out.println("destino " + casillaDestino);

            //se crea el puntero en el tablero
            vista.crearPuntero(casillaDestino, this);

        }

    }

    @Override
    public void eventoToquePuntero() {

        //se mueve en la vista la ficha del jugador
        vista.mover(Constantes.JUGADOR_1, casillaDestino);
    }

    @Override
    public void eventoFinalMovimientoFicha() {
        
        //ystem.out.println("evento final movimiento grafico");
        
        //se mueve lógicamente el jugador hasta la casilla
        logica.mover(Constantes.JUGADOR_1, ultimoNumeroDado);
        
        System.out.println("Posicion logica jugador " + logica.getPosicionJugador(Constantes.JUGADOR_1));

        //falta evaluar la tirada que contiene este objeto
        DirectivasEvaluacion directivas = ((LogicaJuegoModo1)logica).evaluarTurnoFinal(Constantes.JUGADOR_1);
        
        iniciarTurno = true;
    }

    private void iniciarTemporizador() {
        //Se repite a cada segundo.
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(segundos>0){
                    segundos-=1; //Resto un segundo en el label
                    ((VistaJuegoModo1)vista).setSegundosTemporizador(String.valueOf(segundos));                   
                }else{
                    finalizarPartida();
                }
            }
        });
        this.timer.start(); //Iniciamos el temporizador
    }
    
    //accion de fin de partida ya sea por tiempo o por ganar
    private void finalizarPartida(){
        timer.stop();
        
        
    }

    

    

}
