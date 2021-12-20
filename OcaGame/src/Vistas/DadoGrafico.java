
package Vistas;

import Hilos.Hilo;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @autor: Alvaro
 */
public class DadoGrafico extends JPanel implements Runnable, Hilo{
    
    //Constantes de configuracion.
    private final int NUMERO_INICIAL_DADO = 3; //Numero de la cara inicial del dado al iniciarse la vista.
    
    private final int POSICION_PANEL_X = 20, POSICION_PANEL_Y = 20; //Posiciones del Panel cubilete.
    private final int ALTURA_CUBILETE = 410, ANCHURA_CUBILETE = 210; //Medidas del Panel cubilete.
   
    private final int POSICION_INICIAL_DADO_X = 75, POSICION_INICIAL_DADO_Y = 360; //Posiciones del Dado.
    private final int ANCHURA_DADO = 50, ALTURA_DADO = 50; //Medidas del Dado.
    
    private final int VELOCIDAD_DE_PINTADO = 20; //Velocidad de pintado de la animacion del dado.
    
    private final int VELOCIDAD_INICIAL_DADO = 20; //Velocidad inicial del dado tanto en x como en y.
   
    //Atributos de la clase.
    private VistaJuego vistaJuego;
    
    private ImageIcon imagenDadoInicial;
    private ImageIcon imagenDadoCambiante;
    private JLabel dado;
       
    private int velocidad_Dado_X;
    private int velocidad_Dado_Y;
    private int direccionDado;
    
    private int numeroFinal;
    
    private int posicion_Dado_X;
    private int posicion_Dado_Y;
    
    private boolean dadoON;
    
    private int numeroDadoActual;

    //Constructor.
    public DadoGrafico(VistaJuego vistaJuego){
        super();
        this.vistaJuego = vistaJuego;
        this.vistaJuego.setImpulsoTirarDado(false);
        crearCubilete();
        crearDado();        
        configurarDado();
        this.dadoON = true; //Se inicia el hilo del dado.
    }

    @Override
    public void run() {  
        while(dadoON){
            //Si no hay impulso (evento), el dado espera.
            while(!this.vistaJuego.getImpulsoTirarDado()){
                esperar();
            } 
            //Si hay un impulso...Recoge el numero final del dado, que el Controlador le ha dado a la VistaJuego a traves de Logica.
            this.numeroFinal = this.vistaJuego.getNumeroFinalDado();
            
            //Escoge una direccion aleatoria de salida hacia arriba (diagonales izquierda y derecha).
            this.direccionDado= (int)(Math.random()*2);
            
            //Inicio las velocidades del dado.
            velocidad_Dado_X = 10;
            velocidad_Dado_Y = 10;
            
            //Reinicio la posicion y del dado
            this.posicion_Dado_Y=(this.ALTURA_CUBILETE-this.ALTURA_DADO)-1;
            
            //Bloqueo el boton
            this.vistaJuego.bloquearBoton(false);
            //Mientras no llegue al limite inferior de nuevo...
            while(!(this.posicion_Dado_Y>(this.ALTURA_CUBILETE-this.ALTURA_DADO))){
                this.mover(); //Realiza movimiento.
                this.rebotar(); //Comprueba si hay rebote y debe cambiar de imagen y direccion.
                this.repintar(); //Repinta objeto.
                try {
                    Thread.sleep(this.VELOCIDAD_DE_PINTADO); //espera
                } catch (InterruptedException ex) {
                    Logger.getLogger(DadoGrafico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Desbloqueo el boton
            this.vistaJuego.bloquearBoton(true);
            posicionarDadoFinal(); //Muestro la cara del dado final escogida.
            this.vistaJuego.setImpulsoTirarDado(false); //Finaliza la animacion del dado.
            this.vistaJuego.finalAnimacionDado(); //Aviso de que ha finalizado.
        }
    }
    
    //Metodo que deja en espera al hilo del dado hasta que el usuario lo vuelva a accionar.
    private synchronized void esperar(){
        try {
            wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(DadoGrafico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Metodo que crea el cubilete graficamente.
    private void crearCubilete() {
        this.setBounds(POSICION_PANEL_X, POSICION_PANEL_Y, ANCHURA_CUBILETE, ALTURA_CUBILETE);
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(null);
    }
    //Metodo que crea el dado inicial graficamente.
    private void crearDado(){
        this.imagenDadoInicial = new ImageIcon("./img/dados/DADO_3.png");
        this.dado = new JLabel();
        this.dado.setIcon(imagenDadoInicial);
        this.dado.setBounds(POSICION_INICIAL_DADO_X, POSICION_INICIAL_DADO_Y, ALTURA_DADO, ANCHURA_DADO);
        this.add(this.dado);
    }
    
    //Metodo que asigna una configuracion inicial a las variables del dado.
    private void configurarDado() {
        this.numeroDadoActual = this.NUMERO_INICIAL_DADO;
        this.velocidad_Dado_X = this.VELOCIDAD_INICIAL_DADO;
        this.velocidad_Dado_Y = this.VELOCIDAD_INICIAL_DADO;
        this.posicion_Dado_X=this.POSICION_INICIAL_DADO_X;
        this.posicion_Dado_Y=this.POSICION_INICIAL_DADO_Y;
    }

    //Metodo que notifica al hilo del dado cuando hubo un cambio en su condicionante.
    public synchronized void notificarTirada() {
        notifyAll();
    }

    //Al acabar el hilo.
    @Override
    public void matar() {
        this.dadoON=false;
    }

    //Metodo que hace que se mueva el dado.
    private void mover(){
        //segun su direccion...
        switch (direccionDado) {
            //Diagonal izquierda/arriba   
            case 0:
                setPosicionDado_X(posicion_Dado_X-=velocidad_Dado_X);
                setPosicionDado_Y(posicion_Dado_Y-=velocidad_Dado_Y); 
                break;     
            //Diagonal derecha/arriba 
            case 1:
                setPosicionDado_X(posicion_Dado_X+=velocidad_Dado_X); 
                setPosicionDado_Y(posicion_Dado_Y-=velocidad_Dado_Y);
                break;
            //Diagonal derecha/abajo    
            case 2:
                setPosicionDado_X(posicion_Dado_X+=velocidad_Dado_X);
                setPosicionDado_Y(posicion_Dado_Y+=velocidad_Dado_Y);
                break;
            //Diagonal izquierda/abajo    
            case 3:
                setPosicionDado_X(posicion_Dado_X-=velocidad_Dado_X); 
                setPosicionDado_Y(posicion_Dado_Y+=velocidad_Dado_Y);  
                break;       
            default:
                break;
        }
    }

    //Metodo que hace rebotar al dado, cambiando su direcion y velocidad aleatoriamente cuando llega a un limite.
    private void rebotar() {
        int anterior=direccionDado;
        int opuesta = getDireccionOpuesta();
        //Choca arriba.
        if(this.posicion_Dado_Y<=0){
            do{
               this.direccionDado=(int)(Math.random()*4); 
            }while(direccionDado==0 || direccionDado==1 || direccionDado==anterior || direccionDado==opuesta);  //No puede ir hacia arriba.
            efectoReboteDado(); //Cambia la imagen del dado.
            variarVelocidad(); //Varia la velocidad del dado.
            
        //Choca izquierda.    
        }else if(this.posicion_Dado_X<=0){
            do{
               this.direccionDado=(int)(Math.random()*4); 
            }while(direccionDado==0 || direccionDado==3 || direccionDado==anterior || direccionDado==opuesta);  //No puede ir hacia la izquierda.
            efectoReboteDado(); //Cambia la imagen del dado.
            variarVelocidad(); //Varia la velocidad del dado.
   
        //Choca derecha.    
        }else if(this.posicion_Dado_X>=(this.ANCHURA_CUBILETE-this.ANCHURA_DADO)){
            do{
               this.direccionDado=(int)(Math.random()*4); 
            }while(direccionDado==1 || direccionDado==2 || direccionDado==anterior || direccionDado==opuesta); //No puede ir hacia la derecha.
            efectoReboteDado(); //Cambia la imagen del dado.
            variarVelocidad(); //Varia la velocidad del dado.
        }
        //Abajo no hay por que se acaba la animacion.
    }

   //Metodo que actualiza la posicion del dado.
    private void repintar() {
        this.dado.setLocation(this.posicion_Dado_X, this.posicion_Dado_Y);
    }
    
    //Metodo que cambia la imagen del dado de forma aleatoria para simular un rebote
    private void efectoReboteDado(){
        int numeroDadoCambiado;
        do{
           numeroDadoCambiado = (int)(Math.random()*6)+1;  
        }while(numeroDadoCambiado==this.numeroDadoActual); //Para que no se repita la cara que estaba.

        this.imagenDadoCambiante = new ImageIcon("./img/dados/DADO_"+numeroDadoCambiado+".png");
        this.dado.setIcon(imagenDadoCambiante);
        this.dado.repaint();
    }
    
    //Metodo que varia la velocidad del dado cada vez que realiza un rebote.
    public void variarVelocidad(){
        int ejeX = (int)(Math.random()*2);
        if(ejeX==0){
            this.velocidad_Dado_X+=1;
        }else{
            this.velocidad_Dado_Y+=1; 
        }        
    }

    //Metodo que devuelve la direccion opuesta a la actual.
    public int getDireccionOpuesta(){
        int direccionOpuesta = 0;
        switch (this.direccionDado) {
            //Diagonal izquierda/arriba   
            case 0:
                direccionOpuesta = 2; 
                break;     
            //Diagonal derecha/arriba
            case 1:
                direccionOpuesta = 3; 
                break;
            //Diagonal derecha/abajo
            case 2:
                direccionOpuesta = 0; 
                break;
            //Diagonal izquierda/abajo    
            case 3:
                direccionOpuesta = 1;  
                break;
            default:
                break;
        }
        return direccionOpuesta; 
    }
    
    //Metodo que cambia la imagen del dado con la cara final escogida.
    private void posicionarDadoFinal() {
        //Si llega al limite inferior, se para y se pone la imagen del numero final.
        this.imagenDadoCambiante = new ImageIcon("./img/dados/DADO_"+numeroFinal+".png");
        this.dado.setIcon(imagenDadoCambiante);      
    }
    
    //Getters de posicion del dado actuales(x, Y).
    public int getPosicionDado_X() {
        return this.posicion_Dado_X;
    }

    public int getPosicionDado_Y() {
        return this.posicion_Dado_Y;
    }
    
    //Setters de posiciones del dado actuales(x, Y).
    public void setPosicionDado_X(int posicionX) {
        this.posicion_Dado_X = posicionX;
    }

    public void setPosicionDado_Y(int posicionY) {
        this.posicion_Dado_Y = posicionY;
    }
   

}
