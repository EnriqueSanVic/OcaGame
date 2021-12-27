

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
    protected int[] posicionJugador;
    
    
    private CasillaLogica[] tablero;

    public LogicaJuego() {
        init();
        
    }

    
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
    

    //implementar metodo de evaluación de tirada para devolver si es posible realizar el turno para un jugador
    public abstract boolean evaluarTurnoInicio(int jugador);
    
    //implementar metodo de evaluación de consecuencia de una tirada, es decir evalua la casilla en la que ha caido y cuales son las consecuendias
    public  DirectivasEvaluacion evaluarTurnoFinal(int jugador){
        
        int avance, tiempo;
        
        boolean tirarOtraVez;
        
        avance = tablero[posicionJugador[jugador]].avance;
        
        tiempo = tablero[posicionJugador[jugador]].penalizacion;
        
        tirarOtraVez = tablero[posicionJugador[jugador]].tirarOtraVez;
                        
        
        
        return  new DirectivasEvaluacion(avance, tiempo, tirarOtraVez);
        
        
        
    }
    

    //retorna numero de 1 al 6 como un dado clasico
    public int tirarDado(){
        
        int numero;
        
        //hay un 5% de probabilidades de que salga una tirada de 7
        if(random.nextInt(100) > 95){
            numero = 7;
        }else{
            numero = random.nextInt(6) + 1;
        }

        return numero;
        
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

    public int getPosicionJugador(int jugador) {
        return posicionJugador[jugador];
    }
    
 

    public CasillaLogica getTablero(int posicion) {
        return tablero[posicion];
    }
    
    public boolean isGanador(int jugador){
        if(posicionJugador[jugador] == tablero.length - 1){
            return true;
        }
        return false;
    }
    
    
    
    
    
}
