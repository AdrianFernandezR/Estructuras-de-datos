package ed.estructuras.nolineales;
import java.util.*;
/**
 * Clase ÁrbolAVL
 * @author Tinoco Miguel Itzel
 */
public class ÁrbolAVL<C extends Comparable<C>> extends ÁrbolBOLigado<C>{

    /**
     * Constructor de un árbol vacio
     */
    public ÁrbolAVL(){
	raiz = null;
	tam = 0;
    }

    /**
     * Constructor auxiliar de un nodo
     * @param dato elemento del nodo
     * @param padre padre del nodo
     * @param hijoI hijo izquierdo del nodo
     * @param hijoD hijo derecho del nodo
     * @return NodoBinario creado
     */
    @Override
    protected NodoBinario<C> creaNodo(C dato, NodoBinario<C> padre, NodoBinario<C> hijoI, NodoBinario<C> hijoD){
	NodoAVL<C> nodo = new NodoAVL<C>((NodoAVL<C>)padre, dato, (NodoAVL<C>)hijoI, (NodoAVL<C>)hijoD);
	nodo.setNill(nill);
	return nodo;
    }

    /**
     * Metodo auxiliar que inserta un elemento al árbol y lo deja balanceado
     * @param e elemento a agregar
     */
    protected void  agrega(C e){
	NodoAVL<C> nodo = insertaAVL(e);
	if(nodo != raiz){
	    nodo = nodo.getPadre();
	    balancea(nodo);
	}
	tam++;
    }

    /**
     * Metodo auxiliar que inserta un nodo preservando el orden de los elementos en el árbol
     * @param elemento a insertar
     * @return nodo insertado
     */
    protected NodoAVL<C> insertaAVL(C e){
	NodoAVL<C> aux = (NodoAVL<C>)raiz;
	int comparador = 0;
	while(aux != null){
	    comparador = e.compareTo(aux.getElemento());
	    if(comparador >= 0){
		if(aux.getHijoD() == null){
		    aux.setHijoD(creaNodo(e, aux, null, null));
		    aux = aux.getHijoD();
		    break;
		}else{
		    aux = aux.getHijoD();
		}
	    }else{
		if(aux.getHijoI() == null){
		    aux.setHijoI(creaNodo(e,aux, null, null));
		    aux = aux.getHijoI();
		    break;
		}else{
		    aux = aux.getHijoI();
		}
	    }
	}
	actualizaRaiz(aux.getPadre());
	return aux;
    }
    
    /**
     * Metodo que agrega un elemento al arbol
     * @param elemento a agregar
     * @return true si se agrego, false si no
     * @throws NullPointerException si el elemento a agregar es null
     */
    @Override
     public boolean add(C e) throws NullPointerException{
	if(e == null){
	    throw new NullPointerException();
	}
	    
	if(isEmpty()){
	    raiz = (NodoAVL<C>)creaNodo(e,null,null,null);
	    tam++;
	    return true;
	}
	int tam2 = tam;
        agrega(e);
	return tam2 != tam;
    }

    /**
     * Metodo auxiliar que elimina un nodo del árbol dejandolo balanceado
     * @param e nodo a eliminar
     */
    protected NodoRojinegro<C> elimina(NodoBinario<C> e){
	NodoAVL<C> nodo = (NodoAVL<C>)borra(e);
	//nodo = nodo.getPadre();
	actualizaRaiz(nodo.getPadre());
	balancea(nodo.getPadre());
	return (NodoRojinegro<C>)nodo;
	
    }

     /**
     * Remueve el objeto comparable o.
     * La complejidad de este método es log(n) en promedio.
     * @param o el objeto a remover
     * @return si el objeto estuvo presente y lo removió
     * @throws NullPointerException si o es null.
     */
    @Override
     public boolean remove(C o) throws NullPointerException{
	if(o == null){
	    throw new NullPointerException();
	}

	NodoBinario<C> aux = busca(o);
	if(aux == null) return false;
	int tam2 = tam;
	elimina(aux);
	return tam2 != tam;
    }
    
    /**
     * Metodo auxiliar que balancea el árbol
     * @param nodo, se balancea del nodo hasta la raiz.
     */
    protected void balancea(NodoAVL<C> nodo){
	int fb = nodo.calculaFB();
	NodoAVL<C> aux = null;
	NodoAVL<C> temp = null;
	while(nodo != null){
	    fb = nodo.calculaFB();
	if(fb == 2){
	    if(nodo.getHijoI().calculaFB() >= 0){
		aux = nodo.rotaDerecha().getHijoD();
		actualizaRaiz(aux);
	    }else if(nodo.getHijoI().calculaFB() < 0){
		aux = nodo.getHijoI().rotaIzquierda().getHijoI();
		actualizaRaiz(aux);
		temp = nodo.rotaDerecha().getHijoD();
		actualizaRaiz(temp);
	    }
	}else if(fb == -2){
	    if(nodo.getHijoD().calculaFB() <= 0){
		aux = nodo.rotaIzquierda().getHijoI();
		actualizaRaiz(aux);
	    }else if(nodo.getHijoD().calculaFB() > 0){
		aux = nodo.getHijoD().rotaDerecha().getHijoD();
		actualizaRaiz(aux);
		temp = nodo.rotaIzquierda().getHijoI();
		actualizaRaiz(temp);
	    }
	}
	if(nodo.getPadre() == null){
	    raiz = nodo;
	}
	nodo = nodo.getPadre();
	}
	
    }

    /**
     * Metodo auxiliar que actiualiza todas las alturas desde el nodo dado hasta la raiz
     * @param nodo, nodo desde el cual se empieza a actualizar las alturas
     */
    protected void actualizaRaiz(NodoAVL<C> nodo){
	if(nodo == null) throw new IllegalArgumentException();
	while(nodo != null){
	    nodo.actualizaAltura();
	    nodo = nodo.getPadre();
	}
    }

}
