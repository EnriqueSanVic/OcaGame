

package Logicas;

import Modelos.DirectivasEvaluacion;
import Modelos.CasillaLogica;
import java.util.Random;

/**
 * Lógica abstracta del juego, es la generalización de las lógicas.
 * 
 * Con la lógica se consigue tener una lógica de juego modular que no depende de ninguna implementación del juego.
 *
 * @author Enrique Sánchez 
 */
public abstract class LogicaJuego {
    
    private Random random;
    
    //array con las posiciones de los jugadores
    protected int[] posicionJugador;
    
    
    private CasillaLogica[] tablero;

    /**
     * Conmstructor de la lógica
     */
    public LogicaJuego() {
        init();
        
    }

    /**
     * Inicio de las variables y las casillas lógicas.
     */
    private void init() {
        this.posicionJugador = new int[2];
        random = new Random();

        posicionJugador[0] = 0;
        posicionJugador[1] = 0;
        
        tablero = new CasillaLogica[63];

        //inicializamos cada casilla a 0 y luego re iniciamos las casillas que tiene un contenido no vacío
        for (int i = 0; i < tablero.length; i++) {
            tablero[i] = new CasillaLogica(0,0);
        }
        
        tablero[4] = new CasillaLogica(4,0,true); //oca 5
        
        tablero[5] = new CasillaLogica(6,0,true); //puente 6
        
        tablero[8] = new CasillaLogica(5,0,true); //oca 9
        
        tablero[11] = new CasillaLogica(-6,0,true); //puente 12
        
        tablero[13] = new CasillaLogica(4,0,true); //oca 14
        
        tablero[17] = new CasillaLogica(5,0,true); //oca 18
        
        tablero[18] = new CasillaLogica(0,1); //posada 19
        
        tablero[22] = new CasillaLogica(4,0,true); //oca 23
        
        tablero[25] = new CasillaLogica(27,0,true); //dado 26
        
        tablero[26] = new CasillaLogica(5,0,true); //oca 27
        
        tablero[30] = new CasillaLogica(0,2); //pozo 31
        
        tablero[31] = new CasillaLogica(4,0,true); //oca 32
        
        tablero[35] = new CasillaLogica(5,0,true); //oca 36
        
        tablero[40] = new CasillaLogica(4,0,true); //oca 41
        
        tablero[41] = new CasillaLogica(0,3); //laberinto 42
        
        tablero[44] = new CasillaLogica(5,0,true); //oca 45
        
        tablero[49] = new CasillaLogica(4,0,true); //oca 50
        
        tablero[51] = new CasillaLogica(0,4); //carcel 52
        
        tablero[52] = new CasillaLogica(-27,0,true); //dado 53

        tablero[53] = new CasillaLogica(5,0,true); //oca 54
        
        tablero[57] = new CasillaLogica(-57,0); //muerte 58
        
        tablero[58] = new CasillaLogica(4,0,true); //oca 59
        
        tablero[62] = new CasillaLogica(0,0); //oca 63 Final


    }
    


    /**
     * Ymplementar metodo de evaluación de tirada para devolver si es posible realizar el turno para un jugador.
     * 
     * @param jugador id del jugador a evaluar.
     * @return afirmación o negación de la acción de tirada de dado.
     */
    public abstract boolean evaluarTurnoInicio(int jugador);
    
   
    /**
     * Implementar metodo de evaluación de consecuencia de una tirada, es decir evalua la casilla en la que ha caido y cuales son las consecuendias.
     * 
     * @param jugador id del jugador que al que se quiere evaluar su posición.
     * @return directivas de evaluación de la posión del jugador.
     */
    public  DirectivasEvaluacion evaluarTurnoFinal(int jugador){
        
        int avance, tiempo;
        
        boolean tirarOtraVez;
        
        avance = tablero[posicionJugador[jugador]].getAvance();
        
        tiempo = tablero[posicionJugador[jugador]].getPenalizacion();
        
        tirarOtraVez = tablero[posicionJugador[jugador]].isTirarOtraVez();

        return  new DirectivasEvaluacion(avance, tiempo, tirarOtraVez);

    }
    

    
    /**
     * 
     * Retorna numero de 1 al 6 como un dado clasico.
     * 
     * Tiene una probabilidad del 3% de sacar un 7.
     * 
     * @return numero sacado del dado.
     */
    public int tirarDado(){
        
        int numero;
        
        //hay un 3% de probabilidades de que salga una tirada de 7
        if((random.nextInt(100) + 1) > 97){
            numero = 7;
        }else{
            numero = random.nextInt(6) + 1;
        }

        return numero;
        
    }
    
    
    
    /**
     * Método para mover lógicamente a un jugador por el tablero lógico.
     * 
     * @param jugador id del jugador.
     * @param casillasDesplazamiento numero de casillas de avance o retroceso que se debe mover, puede ser negativo para retroceder.
     * @return posición final del jugador.
     */
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

    


    public int getPosicionJugador(int jugador) {
        return posicionJugador[jugador];
    }


    public CasillaLogica getTablero(int posicion) {
        return tablero[posicion];
    }
    
    public void setPosicionJugador(int jugador, int posicion){
        posicionJugador[jugador] = posicion;
    }
    
    public boolean isGanador(int jugador){
        if(posicionJugador[jugador] == tablero.length - 1){
            return true;
        }
        return false;
    }

    public int[] getPosicionJugador() {
        return posicionJugador;
    }
    
    
    
    
    
}
