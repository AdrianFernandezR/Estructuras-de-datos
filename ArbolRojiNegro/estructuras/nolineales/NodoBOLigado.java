package ed.estructuras.nolineales;
import ed.estructuras.lineales.ListaDoblementeLigada;
import java.util.*;
/**
 *Clase NodoBOLigado
 *@author Tinoco Miguel Itzel
 */

public class NodoBOLigado<C extends Comparable<C>> implements NodoBinario<C>{

    //elemento del nodo
    protected C elem;
    //hijo Izquierdo
    protected NodoBOLigado<C> hijoI;
    //hijoDerecho
    protected NodoBOLigado<C> hijoD;
    //padre
    protected NodoBOLigado<C> padre;
    //altura
    protected int altura;
    //hojas sin información
    protected  NodoRojinegro<C> nill;
    //color del nodo
    protected NodoRojinegro.Color color;
    

    /**
     * Constructor de un nodo solo con el elemento
     * @param elemento del dato
     */
    public NodoBOLigado(C dato){
	elem = dato;
    }

    /**
     * Construtor de un nodo con todos sus elementos
     * @param p padre del nodo
     * @param e elemento del nodo
     * @param i hijo izquierdo del nodo
     * @param d hijo derecho del nodo
     */
    public NodoBOLigado( NodoBOLigado<C> p, C e,  NodoBOLigado<C>i,  NodoBOLigado<C> d){
	padre = p;
	elem = e;
	hijoI = i;
	hijoD = d;
	altura = 0;
    }

    /**
     *Contructor de un nodo solo con su elemento y su padre
     *@param dato elemento del nodo
     *@param pa padre del nodo
     */
    public NodoBOLigado(C dato,  NodoBOLigado<C> pa){
	elem = dato;
	padre = pa;
    }

    /**
     * Metodo que devuelve el padre del nodo
     * @return padre del nodo
     */
    @Override
   public NodoBinario<C> getPadre(){
	return padre;
    }

     /**
     * Metodo que devuelve el hijo izquierdo del nodo
     * @return hijo izquierdo del nodo
     */
    @Override
   public  NodoBinario<C> getHijoI(){
	return hijoI;
    }

     /**
     * Metodo que devuelve el hijo derecho del nodo
     * @return hijo derecho del nodo
     */
    @Override
    public NodoBinario<C> getHijoD(){
	return hijoD;
    }

     /**
     * Metodo que devuelve el elemento del nodo
     * @return elemento del nodo
     */
    public C getElemento(){
	return elem;
    }

    /**
     * Metodo que devuelve el color del nodo
     * @return elemento del nodo
     */
    public NodoRojinegro.Color getColor(){
        return color;
    }

     /**
     * Metodo que asigna el elemento del nodo
     * @param elemento del nodo
     */
    public void setElemento(C dato){
	elem = dato;
    }


    /**
     * Metodo que asigna el padre del nodo
     * @param padre del nodo
     */
    public void setPadre(Nodo<C> padre){
	this.padre = (NodoBOLigado<C>)padre;
    }

    /**
     * Metod que asigna el hijo izquierdo del nodo
     * @param hijo izquierdo del nodo
     */
    @Override
    public void setHijoI(NodoBinario<C> i){
	hijoI = (NodoBOLigado<C>)i;
    }

    /**
     * Metodo que asigna el hijo derecho del nodo
     * @param hijo derecho del nodo
     */
    @Override
    public void setHijoD(NodoBinario<C> d){
	hijoD = (NodoBOLigado<C>)d;
    }

    public void setNill(NodoRojinegro<C> nill){
	this.nill = nill;
    }
	
    /**
     * Indica si este nodo no tiene hijos o
     * todos sus hijos son árboles vacíos.
     */
    public boolean esHoja(){
        return getListaHijos().isEmpty();
    }
	
    /** Devuelve el altura del subárbol que tiene este nodo por raíz
     */
    public int getAltura(){
	return altura;
    }
    
    /**
     * Devuelve al hijo en la posición <code>índice</code>.
     * @param índice
     * @return hijo en la posicion indicada
     */
    public Nodo<C> getHijo(int indice) throws IndexOutOfBoundsException{
	if(indice < 0 || indice > 1){
	    throw new IndexOutOfBoundsException();
	}else{
	    if(indice == 0){
		return hijoI;
	    }else{
		return hijoD;
	    }
	}
    }
    
    /**
     * Devuelve al hijo que va después de <code>hijo</code>.
     * @param hijo
     * @throws IllegalArgumentException si <code>hijo</code> no es hijo de este
     * nodo.
     */
    public Nodo<C> getHermanoSiguiente(Nodo<C> hijo) throws IllegalArgumentException{
	if(hijo != hijoD && hijo != hijoI){
	    throw new IllegalArgumentException();
	}else{
	    if(hijo == hijoI){
		return hijoD;
	    }
	}
	return null;
    }
    
    /**
     * Devuelve el número de hijos que tiene este nodo.
     * @return 
     */
    public int getGrado(){
	if(!this.esHoja()){
	    if(hijoD != nill && hijoD != nill){
		return 2;
	    }else{
		return 1;
	    }
	}
	return 0;
    }
    
    /**
     * Devuelve una lista con todos los hijos de este nodo.
     * Cualquier alteración a la lista devuelta no debe reflejar una alteración
     * a los hijos del nodo.
     * @return hijos de este nodo.
     */
    public List<Nodo<C>> getListaHijos(){
	List<Nodo<C>> l = new ListaDoblementeLigada<>();
	if(hijoI != nill){
	    l.add(hijoI);
	}
	if(hijoD != nill){
	    l.add(hijoD);
	}
	return l;
    }
    
    /**
     * Remueve al hijo indicado.
     * @param hijo
     * @return Si el hijo estuvo presente y fue removido.
     */
   public  boolean remueveHijo(Nodo<C> hijo){
	if(hijo == hijoD){
	    hijoD = nill;
	    return true;
	}else if(hijo == hijoI){
	    hijoI = nill;
	    return true;
	}
	return false;
    }

    
    
}
