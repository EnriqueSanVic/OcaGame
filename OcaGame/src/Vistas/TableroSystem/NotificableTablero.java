/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.TableroSystem;

/**
 * Interfaz que sirve para hacer de cualquier clase un escuchador de los eventos que genera el sistema del tablero
 * 
 * De esta manera se notifican las acciones que se producen en el tablero para controlarlas.
 *
 * @author Enrique Sánchez
 */
public interface NotificableTablero {
    
    void eventoToquePuntero();
    void eventoFinalMovimientoFicha();
    
}
