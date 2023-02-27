package ed.estructuras.nolineales;
import ed.estructuras.ColeccionAbstracta;
import ed.estructuras.lineales.ListaDoblementeLigada;
import java.util.List;
import java.util.Iterator;
/**
 *Clase ArbolBOLigado
 *@author Tinoco Miguel Itzel
 */

public class ÁrbolBOLigado<C extends Comparable<C>> extends ColeccionAbstracta<C>  implements ÁrbolBinarioOrdenado<C>{
    //raiz del arbol
    protected NodoBOLigado<C> raiz;
    //hoja sin informacion
    public final NodoRojinegro<C> nill = new NodoRojinegro<>(null, null,null, null);
    
    /**
     * Constructor de un árbol vacio
     */
    public ÁrbolBOLigado(){
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
    protected NodoBinario<C> creaNodo(C dato, NodoBinario<C> padre, NodoBinario<C> hijoI, NodoBinario<C> hijoD ){
	NodoBOLigado<C> nodo = new NodoBOLigado<C>((NodoBOLigado<C>)padre, dato, (NodoBOLigado<C>)hijoI, (NodoBOLigado<C>)hijoD);
	nodo.setNill(nill);
	return nodo;
    }

    /**
     * Metodo que devueleve la raiz del arbol
     * @return raiz del arbol
     */
    public NodoBinario<C> getRaíz(){
	return raiz;
    }
        
    /**
     * Devuelve el altura del árbol.  Si el árbol es vacío devuelve -1.
     * @return altura del árbol.
     */
    public int getAltura(){
	if(raiz != null){
	    return raiz.getAltura();
	}
	return -1;
    }

    /**
     * Metodo auxiliar que inserta un nodo preservando el orden de los elementos en el arbol
     * @param elemento a insertar
     * @return nodo insertado
     */
    protected NodoBinario<C> inserta(C e){
	NodoBinario<C> aux = (NodoBinario<C>)raiz;
	int comparador = 0;
	while(aux != null){
	    comparador = e.compareTo(aux.getElemento());
	    if(comparador >= 0){
		if(aux.getHijoD() == nill){
		    aux.setHijoD(creaNodo(e, aux, nill, nill));
		    aux = aux.getHijoD();
		    break;
		}else{
		    aux = aux.getHijoD();
		}
	    }else{
		if(aux.getHijoI() == nill){
		    aux.setHijoI(creaNodo(e,aux, nill, nill));
		    aux = aux.getHijoI();
		    break;
		}else{
		    aux = aux.getHijoI();
		}
	    }
	}
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
		raiz = (NodoBOLigado<C>)creaNodo(e,null,null,null);
		tam++;
		return true;
	    }
	    tam++;
	    return null != inserta(e);
    }


    
     /**
     * Indica si el objeto comparable se encuentra en este árbol.
     * La complejidad de este método es log(n) en promedio.
     * @param o
     * @return si el objeto se encuentra en el árbol.
     * @throws NullPointerException si <code>o</code> es <code>null</code>.
     */
    @Override
    public boolean contains(C o) throws NullPointerException{
	if(o == null){
	    throw new NullPointerException();
	}else{
	    if(busca(o) != null){
		return true;
	    }
	}
	return false;
    }

    /**
     * Limpia la estructura
     * raiz le asigna <code>null<code> y al tamaño le asigna 0
     */
    @Override
    public void clear(){
	raiz = null;
	tam = 0;
    }

    /**
     * Metodo auxiliar para buscar un nodo
     * @param dato a buscar
     * @return nodo en el que esta el elemento
     * si no se encontro regresa <code>null<code>
     * @throws NullPointerException si el elemento es <code>null<code>
     */
    protected NodoBinario<C> busca(C dato){
	if(dato == null){
	    throw new NullPointerException();
	}else{
	    NodoBinario<C> aux = (NodoBinario<C>)raiz;
	    int comparador = 0;
	    while(aux != nill){
		comparador = dato.compareTo(aux.getElemento());
		if(comparador == 0){
		    return aux;
		}else if(comparador < 0){
		    aux = aux.getHijoI();
		}else{
		    aux = aux.getHijoD();
		}
	    }
	}
	return null;
    }


    /**
     * Metodo auxiliar que borra un nodo del arbol
     * @param nodo a borra
     * @return nodo eliminado
     */
    protected NodoBinario<C> borra(NodoBinario<C> nodo){
	 NodoBinario<C> aux = null;
	if(nodo.esHoja()){
	    aux = nodo;
	    tam--;
	    if(nodo == raiz){
		raiz = null;
	    }else{
		nodo.getPadre().remueveHijo(nodo);
	    }
	}else{
	    aux = nodo;
	    while(!aux.esHoja()){
		if(aux.getHijoI()!=nill){
		    aux = aux.getHijoI();
		    aux = masGrande(aux);
		}else{
		    aux = aux.getHijoD();
		    aux = masChico(aux);
		}
		nodo.setElemento(aux.getElemento());
		nodo = aux;
	    }
	    aux.getPadre().remueveHijo(aux);
	    
	    if(aux != null) tam--;
	}
	return aux;
    }

    /**
     * Remueve el objeto comparable o.
     * La complejidad de este método es log(n) en promedio.
     * @param o el objeto a remover
     * @return si el objeto estuvo presente y lo removió
     * @throws NullPointerException si o es null.
     */
    public boolean remove(C o) throws NullPointerException{
	if(o == null){
	    throw new NullPointerException();
	}

	NodoBinario<C> aux = busca(o);
	if(aux == null) return false;

	return borra(aux) != null;
    }

    /**
     * Metodo que devuelve la hoja mas pequeña al nodo del parametro
     * @param nodo del cual se quiere la hoja mas pequeña
     * @return hoja mas pequeña
     */
    private NodoBinario<C> minimo(NodoBinario<C> nodoB){
	NodoBinario<C> aux = nodoB;
	while(!aux.esHoja()){
	    if(aux.getHijoI() != nill){
		aux = aux.getHijoI();
		aux = masChico(aux);
	    }else{
		aux = aux.getHijoD();
		aux = masChico(aux);
	    }
	}
	return aux;
    }

    /**
     * Metodo auxiliar que indica el hijo mas pequeño al nodo que se recibe(aunque no sea hoja)
     * @param nodo recibido
     * @return hijo mas chico
     */
    protected NodoBinario<C> masChico(NodoBinario<C> nb){
	NodoBinario<C> aux = nb;
	while(aux.getHijoI() != nill){
	    aux = aux.getHijoI();
	}
	return aux;
    }

    /**
     * Metodo auxiliar que indica el hijo mas grande del nodo recibido
     * @param nodo recibido
     * @return hijo mas grande
     */
    protected NodoBinario<C> masGrande(NodoBinario<C> nb){
	NodoBinario<C> aux = nb;
	while(aux.getHijoD() != nill){
	    aux = aux.getHijoD();
	}
	return aux;
    }

    /**
     *Metodo que devuelve un iterador en Inorden
     * @return iterador en Inorden
     */
     public Iterator<C> getIteradorInorden(){
	return new IteradorIn();
    }

    /**
     *Metodo que devuelve un iterador en Preorden
     * @return iterador en Preorden
     */
    public Iterator<C> getIteradorPreorden(){
	return new IteradorPre();
    }

    /**
     *Metodo que devuelve un iterador en Posorden
     * @return iterador en Posorden
     */
    public Iterator<C> getIteradorPostorden(){
	return new IteradorPos();
    }

    /**
     *Metodo que devuelve un iterador
     * @return iterador en Preorden
     */
    public Iterator<C> iterator(){
	return new IteradorPre();
    }

    /**
     * Metodo que devuelve una lista con los elementos del arbol con un recorrido en Postorden
     * @return Lista con los elementos del arbol
     */
    public List<C> recorridoPostorden(){
	Iterator it = getIteradorPostorden();
	ListaDoblementeLigada<C> l = new ListaDoblementeLigada<>();
	while(it.hasNext()){
	l.add((C)it.next());
	}
	return (List<C>)l;
    }

    /**
     * Clase privada Iterador en inorden
     */
    private class IteradorIn implements Iterator<C>{
	//nodo actual
	NodoBinario<C> actual;

	/**
	 * Constructor del iterador 
	 * inicializa el iterador en el hijo mas chico de la raiz
	 */
	public IteradorIn(){
	    actual = raiz;
	    if(raiz != null && raiz.getHijoI() != null){
		actual = masChico(raiz);
	    }
	}

	/**
	 *Metodo que indica si hay un elemento siguiente
	 *@return true si hay un elemento siguiente
	 */
	public boolean hasNext(){
	    return actual != null;
	}

	/**
	 * Metodo que regresa el elemento siguiente
	 * @return elemento siguiente
	 */
	public C next(){
	    C elem = actual.getElemento();
	    if(!hasNext()){
		throw new IllegalStateException(); 
	    }else{
	
		if(actual.getHijoD() != null){
		    actual = masChico(actual.getHijoD());
		}else{
		    NodoBinario<C> aux = actual;
		    actual = actual.getPadre();
		    while(actual != null && actual.getHijoD() == aux){
			aux = actual;
			actual = actual.getPadre();
		    }
		}
	    }
	    return elem;
	}
	
    }

    /**
     * Clase privada Iterador en preorden
     */
    private class IteradorPre implements Iterator<C>{
	//nodo actual
	NodoBinario<C> actual;

	/**
	 * Constructor del iterador 
	 * inicializa el iterador en la raiz
	 */
	public IteradorPre(){
	    actual = raiz;
	}

	/**
	 *Metodo que indica si hay un elemento siguiente
	 *@return true si hay un elemento siguiente
	 */
	public boolean hasNext(){
	    return actual != null;
	}

	/**
	 * Metodo que regresa el elemento siguiente
	 * @return elemento siguiente
	 */
	public C next(){
	   C elem = actual.getElemento();
	    if(!hasNext()){
		throw new IllegalStateException(); 
	    }else{
	
		if(actual.getHijoD() != null){
		    actual = actual.getHijoD();
		}else{
		    NodoBinario<C> aux = actual;
		    actual = actual.getPadre();
		    while(actual != null && (actual.getHijoD() == null || actual.getHijoD() == aux)){
			if(actual != null && actual.getHijoD() == aux){
			    while(actual != null && actual.getHijoD() == aux){
				aux = actual;
				actual = actual.getPadre();
			    }
			}
			while(actual != null && actual.getHijoI() == aux && actual.getHijoD() == null){
			    aux = actual;
			    actual = actual.getPadre();
			}
			if(aux == raiz) return elem;
		    }
		    if(aux == raiz){
			actual = null;
			return elem;
		    }
		    actual = actual.getHijoD();
		}
		    
	    }
	    return elem;
	}
    }

    /**
     * Clase privada iterador en posorden
     */
    private class IteradorPos implements Iterator<C>{
	NodoBinario<C> actual;

	/**
	 * Constructor del iterador 
	 * inicializa el iterador en la hoja mas pequeña de la raiz
	 */
	public IteradorPos(){
	    actual = minimo(raiz);
	    System.out.println(actual.getElemento());
	}

	/**
	 *Metodo que indica si hay un elemento siguiente
	 *@return true si hay un elemento siguiente
	 */
	public boolean hasNext(){
	    return actual != null;
	}

	/**
	 * Metodo que regresa el elemento siguiente
	 * @return elemento siguiente
	 */
	public C next(){
	    C elem = actual.getElemento();
	    if(!hasNext()){
		throw new IllegalStateException(); 
	    }else{
		if(actual == raiz){
		    actual = null;
		    return elem;
		}

		if(actual.getPadre().getHijoI() == actual){
		    if(actual.getPadre().getHijoD() != null){
			actual = minimo(actual.getPadre().getHijoD());
		    }else{
			actual = actual.getPadre();
		    }
		}else{
		    actual = actual.getPadre();
		}
	    }

	    return elem;
	}
    }
}
