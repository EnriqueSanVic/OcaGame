/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.awt.Dimension;


/**
 * Interfaz para trabajar con la clase UtilidadesGraficas.
 *
 * @author Enrique Sánchez
 */
public interface VentanaConCorrecion {
    
    /**
     * Retorna el ancho real de una venana con menú debido al error de AWT que desfigura las medidas originales.
     * @return ancho en px.
     */
    int getCorreccionWidth();
    
    /**
     * Retorna el alto real de una venana con menú debido al error de AWT que desfigura las medidas originales.
     * @return alto en px.
     */
    int getCorreccionHeight();
    
    /**
     * Retorna dimensión real de una venana con menú debido al error de AWT que desfigura las medidas originales.
     * @return dimension en px.
     */
    Dimension getSize();
    
    /**
     * Pregunta si una ventana está ya en el modo pantalla completa o no.
     * @return afirmación o negación.
     */
    boolean isPantallaCompleta();
    
    /**
     * Permite variar el estado de una ventana en su atributo pantalla completa.
     * @param isPantallaCompleta 
     */
    void setPantallaCompleta(boolean isPantallaCompleta);
    

}
