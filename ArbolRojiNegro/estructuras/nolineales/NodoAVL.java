package ed.estructuras.nolineales;
import java.util.*;
import java.lang.Math;

/**
 *Clase NodoAVL
 *@author Tinoco Miguel Itzel
 */
public class NodoAVL<C extends Comparable<C>> extends NodoBOLigado<C> implements NodoBinario<C>{

     /**
     * Construtor de un nodo con todos sus elementos
     * @param p padre del nodo
     * @param e elemento del nodo
     * @param i hijo izquierdo del nodo
     * @param d hijo derecho del nodo
     */
    public NodoAVL(NodoAVL<C> p, C e, NodoAVL<C> i, NodoAVL<C> d){
        super(p,e,i,d);
    }

     /**
     * Metodo que devuelve el hijo izquierdo del nodo
     * @return hijo izquierdo del nodo
     */
    @Override
    public NodoAVL<C> getHijoI(){
	return (NodoAVL<C>)hijoI;
    }

     /**
     * Metodo que devuelve el hijo derecho del nodo
     * @return hijo derecho del nodo
     */
     @Override
    public NodoAVL<C> getHijoD(){
	 return (NodoAVL<C>)hijoD;
    }

    /**
     * Metodo que devuelve el padre del nodo
     * @return padre del nodo
     */
     @Override
    public NodoAVL<C> getPadre(){
	 return (NodoAVL<C>)padre;
    }

    /**
     * Metodo que asiga la altura a un nodo
     * @param h, altura nueva
     */
    public void setAltura(int h){
	altura = h;
    }
    
    /**
     * Metodo que hace la rotacion hacia la derecha sobre un nodo
     * @return padre del nodo (despues de la rotacion)sobre el que se hizo la rotacion
     */
    public NodoAVL<C> rotaDerecha(){
	NodoAVL<C> nodo = this.getHijoI();
	this.hijoI = nodo.hijoD;
	if(this.hijoI != nill){
	    this.hijoI.setPadre(this);
	}
	if(this.padre != null){
	    if(this.padre.hijoD == this) this.padre.hijoD = nodo;
	    if(this.padre.hijoI == this) this.padre.hijoI = nodo;
	}
	nodo.hijoD = this;
	nodo.setPadre(this.padre);
	this.padre = nodo;
	
	this.actualizaAltura();
	return nodo;
    }

     /**
     * Metodo que hace la rotacion hacia la izquierda sobre un nodo
     * @return padre del nodo (despues de la rotacion)sobre el que se hizo la rotacion
     */
    public NodoAVL<C> rotaIzquierda(){
	NodoAVL<C> nodo = this.getHijoD();
	this.hijoD = nodo.hijoI;
	if(this.hijoD != nill){
	    this.hijoD.setPadre(this);
	}
	if(this.padre != null){
	    if(this.padre.hijoD == this) this.padre.hijoD = nodo;
	    if(this.padre.hijoI == this) this.padre.hijoI = nodo;
	}
	nodo.hijoI = this;
	nodo.setPadre(this.padre);
	this.padre = nodo;
	
	this.actualizaAltura();
	return nodo;
    }

    /**
     * Metodo que actualiza la altura del nodo
     */
    public void actualizaAltura(){
	if(this.esHoja()){
	    this.altura = 0;
	}else{
	    if(hijoD != nill){
		int a = this.hijoD.altura;
		if(hijoI == nill){
		    altura = a + 1;
		}else{
		    int b = hijoI.altura;
		    altura = Math.max(b,a) + 1;
		}
	    }else{
		altura = hijoI.altura +1;
		}
	    }		
    }

    /**
     * Metodo que calcula el factor de balanceo de un nodo
     * @return el factor de balanceo
     */
    public int calculaFB(){
	int hI = (hijoI == nill)? 0:hijoI.altura+1;
	int hD = (hijoD == nill)? 0:hijoD.altura+1;
	return hI-hD;
    }
}
