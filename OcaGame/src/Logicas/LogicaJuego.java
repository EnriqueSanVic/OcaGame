

package Logicas;

import java.util.Random;

/**
 *
 * @author Enrique Sánchez 
 * @author Alvaro Cañada
 */
public abstract class LogicaJuego {
    
    private Random random;
    
    //array con las posiciones de los jugadores
    private int[] posicionJugador;
    
    
    
    private CasillaLogica[] tablero;

    public LogicaJuego() {
        init();
    }

    
    private void init() {
        
        random = new Random();
        
        posicionJugador[0] = 0;
        posicionJugador[1] = 0;

        //inicializamos cada casilla a 0 y luego re iniciamos las casillas que tiene un contenido no vacío
        for (int i = 0; i < tablero.length; i++) {
            tablero[i] = new CasillaLogica(0,0);
        }
        
        tablero[4] = new CasillaLogica(4,0); //oca 5
        
        tablero[5] = new CasillaLogica(6,0); //puente 6
        
        tablero[8] = new CasillaLogica(5,0); //oca 9
        
        tablero[11] = new CasillaLogica(-6,0); //puente 12
        
        tablero[13] = new CasillaLogica(4,0); //oca 14
        
        tablero[17] = new CasillaLogica(5,0); //oca 18
        
        tablero[18] = new CasillaLogica(0,1); //posada 19
        
        tablero[22] = new CasillaLogica(4,0); //oca 23
        
        tablero[25] = new CasillaLogica(27,0); //dado 26
        
        tablero[26] = new CasillaLogica(5,0); //oca 27
        
        tablero[30] = new CasillaLogica(0,2); //pozo 31
        
        tablero[31] = new CasillaLogica(4,0); //oca 32
        
        tablero[35] = new CasillaLogica(5,0); //oca 36
        
        tablero[40] = new CasillaLogica(4,0); //oca 41
        
        tablero[41] = new CasillaLogica(0,3); //laberinto 42
        
        tablero[44] = new CasillaLogica(5,0); //oca 45
        
        tablero[49] = new CasillaLogica(4,0); //oca 50
        
        tablero[51] = new CasillaLogica(0,4); //carcer 52
        
        tablero[52] = new CasillaLogica(-27,0); //dado 53

        tablero[53] = new CasillaLogica(5,0); //oca 54
        
        tablero[57] = new CasillaLogica(-57,0); //muerte 58
        
        tablero[58] = new CasillaLogica(4,0); //oca 59
        
        tablero[62] = new CasillaLogica(0,0); //oca 63 Final




    }
    

    //implementar metodo de evaluación de tirada para devolver si es posible realizar el turno para un jugador
    public abstract boolean evaluarTurnoInicio(int jugador);
    
    //implementar metodo de evaluación de consecuencia de una tirada, es decir evalua la casilla en la que ha caido y cuales son las consecuendias
    public abstract DirectivasEvaluacion evaluarTurnoFinal(int jugador);

    
    //retorna numero de 1 al 6 como un dado clasico
    public int tirarDado(){
        return random.nextInt(6) + 1;
    }
    
    
    
    //el retorno es el numero de casillas de avance o retroceso que se debe usar
    public int mover (int jugador, int casillasDesplazamiento){
        
        
        //si se sobrepasa de el limite de casillas se hace el rebote de la ficha
        if(posicionJugador[jugador] + casillasDesplazamiento > tablero.length){
            
            int diferencia = (posicionJugador[jugador] + casillasDesplazamiento) - tablero.length;
            
            posicionJugador[jugador] = tablero.length - diferencia;
         
            //si es una tirada normal
        }else{
            posicionJugador[jugador] += casillasDesplazamiento;
        }
        
        return posicionJugador[jugador];
        
    }
    
    
    
    private boolean evaluarFin(int jugador){
        
        if(posicionJugador[jugador] == tablero.length){
            return true;
        }
        
        return false;
    }
    
    
    
    
}
