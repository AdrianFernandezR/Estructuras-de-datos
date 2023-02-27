/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal.
 */
package ed.estructuras.lineales;

import java.util.Collection;

/**
 * Estructura "Primero en entrar, primero en salir".
 * Corresponde a una cola que no permite almacenar elementos nulos.
 * @author veronica
 */
public interface ICola<E> extends Collection<E> {
    
    /**
     * Muestra el elemento al inicio de la fila.
	 * Devuelve <code>null</code> si está vacía.
     * @return Una referencia al elemento siguiente.
     */
    public E mira();
    
    /**
     * Devuelve el elemento al inicio de la fila y lo elimina.
	 * Devuelve <code>null</code> si está vacía.
     * @return Una referencia al elemento siguiente.
     */
    public E atiende();
    
    /**
     * Agrega un elemento al final de la fila.
     * @param e Referencia al elemento a agregar.
	 * @throws IllegalArgumentException si se intenta agregar <code>null</code>.
     */
    public void forma(E e);
    
}
