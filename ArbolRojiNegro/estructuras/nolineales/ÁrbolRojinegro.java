package ed.estructuras.nolineales;

/**
 * Clase ÁrbolRojinegro
 * @author Tinoco Miguel Itzel
 */
public class ÁrbolRojinegro<C extends Comparable<C>> extends ÁrbolBOLigado<C>{

    /**
     * Constructor de un Arbol vacio
     */
    public ÁrbolRojinegro(){
	nill.setColor(NodoRojinegro.Color.NEGRO);
    }

    /** 
     *Crea un nodo del tipo requerido por la clase
     *@param dato elemento del nodo
     *@param padre padre del nodo
     *@param hijoI hijo izquierdo del nodo
     *@param hijoD hijo derecho del nodo
     *@return nodo creado
     */
    protected NodoBinario<C> creaNodo(C dato, NodoBinario<C> padre, NodoBinario<C> hijoI, NodoBinario<C> hijoD){
	NodoRojinegro<C> aux = new NodoRojinegro<C>((NodoRojinegro<C>)padre, dato, (NodoRojinegro<C>)hijoI, (NodoRojinegro<C>)hijoD);
        aux.setNill(nill);
        return aux;
    }

    /**
     * Metodo auxiliar que intercambia el color de dos nodos.
     */
    protected void interColor(NodoRojinegro<C> nodo1, NodoRojinegro<C> nodo2){
	NodoRojinegro.Color caux = nodo1.getColor();
	nodo1.setColor(nodo2.getColor());
	nodo2.setColor(caux);
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
	NodoRojinegro<C> nodoB = hojaS((NodoRojinegro<C>)aux);
	NodoRojinegro<C> nodoR = (NodoRojinegro<C>)raiz;
	nodoR.setColor(NodoRojinegro.Color.ROJO);
	borraRojinegro(nodoB);
	borra(nodoB);
	while(nodoB.getPadre() != null){
	    nodoB = nodoB.getPadre();
	}

	if(raiz != null){
	    nodoB.setColor(NodoRojinegro.Color.NEGRO);
	    raiz = nodoB;
	}
	return true;	    
    }

    /**
     * Método auxiliar que rebalancea un árbol rojinegro cuando se quiere eliminar un elemento
     * @param nodo nodo que se borrara
     */
    protected void borraRojinegro(NodoRojinegro<C> nodo){
	if(nodo.getColor() == NodoRojinegro.Color.ROJO && nodo.esHoja()){
	    return;
	}
	if(nodo.getColor() == NodoRojinegro.Color.ROJO){
	    nodo.setColor(NodoRojinegro.Color.NEGRO);
	    return;
	}

	if(nodo.getHermano().getColor() == NodoRojinegro.Color.ROJO){
	    interColor(nodo.getPadre(), nodo.getHermano());
	    NodoRojinegro<C> padre = nodo.getPadre();
	    if(nodo.getPadre().getHijoI() == nodo){
		padre.rotaIzquierda();
		actualizaAltura(padre);
	    }else{
		padre.rotaDerecha();
		actualizaAltura(padre);
	    }
	    borraRojinegro(nodo);
	}else{
	    NodoRojinegro<C> sI = nodo.getHermano().getHijoI();
	    NodoRojinegro<C> sD = nodo.getHermano().getHijoD();
	    if(sI.getColor() == NodoRojinegro.Color.NEGRO && sD.getColor() == NodoRojinegro.Color.NEGRO){
		nodo.getHermano().setColor(NodoRojinegro.Color.ROJO);
		borraRojinegro(nodo.getPadre());
	    }else{
		if(nodo.getPadre().getHijoI() == nodo && sD.getColor() == NodoRojinegro.Color.ROJO){
		    interColor(nodo.getPadre(), nodo.getHermano());
		    sD.setColor(NodoRojinegro.Color.NEGRO);
		    nodo.getPadre().rotaIzquierda();
		    return;
		}else if(nodo.getPadre().getHijoD() == nodo && sI.getColor() == NodoRojinegro.Color.ROJO){
		    interColor(nodo.getPadre(), nodo.getHermano());
		    sI.setColor(NodoRojinegro.Color.NEGRO);
		    nodo.getPadre().rotaDerecha();
		    return;
		}else if(nodo.getPadre().getHijoI() == nodo && sI.getColor() == NodoRojinegro.Color.ROJO){
		    interColor(nodo.getHermano(), sI);
		    nodo.getHermano().rotaDerecha();
		    borraRojinegro(nodo);
		}else if(nodo.getPadre().getHijoD() == nodo && sD.getColor() == NodoRojinegro.Color.ROJO){
		    interColor(nodo.getHermano(), sD);
		    nodo.getHermano().rotaIzquierda();
		    borraRojinegro(nodo);
		}
	    }
	}
	

	}


    /**
     * Metodo auxiliar que busca un sustituto que sea hoja para el nodo que se quiere eliminar
     * el elemento del nodo que se quiere eliminar es cambiado por el elemento del sustituto
     * @param nodo nodo que se quiere eliminar
     * @return sustituto
     */
    protected NodoRojinegro<C> hojaS(NodoRojinegro<C> nodo){
	NodoRojinegro<C> aux = nodo;
	    while(!aux.esHoja()){
		if(aux.getHijoI()!=nill){
		    aux = aux.getHijoI();
		    aux = (NodoRojinegro<C>)super.masGrande(aux);
		}else{
		    aux = aux.getHijoD();
		    aux = (NodoRojinegro<C>)super.masChico(aux);
		}
		nodo.setElemento(aux.getElemento());
		nodo = aux;
	    }
	    return aux;
    }
    
     /**
     * Metodo que agrega un elemento al arbol, manteniendo las condicieones del árbol
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
	    NodoRojinegro<C> aux= (NodoRojinegro<C>)creaNodo(e,null,nill,nill);
	    aux.setColor(NodoRojinegro.Color.NEGRO);
	    raiz = aux;
	    tam++;
	    return true;
	}
	NodoRojinegro<C> nodoA = (NodoRojinegro<C>)inserta(e);
	actualizaAltura(nodoA);
	tam++;
	if(nodoA.getTio() != null){
	    insertaRojinegro(nodoA);
	}
	while(nodoA.getPadre() != null){
	    nodoA = nodoA.getPadre();
	}
	nodoA.setColor(NodoRojinegro.Color.NEGRO);
	raiz = nodoA;
	return true;

    }


    /**
     * Metodo auxiliar que balancea el arbol rojinegro despues de agregar un elemento
     * @param nodo nodo que se inserto
     */
    protected void insertaRojinegro(NodoRojinegro<C> nodo){
	if(nodo==raiz||nodo.getPadre().getColor()==NodoRojinegro.Color.NEGRO) return;
	if(nodo.getTio().getColor() == NodoRojinegro.Color.ROJO){
	    nodo.getPadre().setColor(NodoRojinegro.Color.NEGRO);
	    nodo.getTio().setColor(NodoRojinegro.Color.NEGRO);
	    nodo.getAbuelo().setColor(NodoRojinegro.Color.ROJO);
	    insertaRojinegro(nodo.getAbuelo());
	}else{
	    if(cruzados(nodo.getPadre(), nodo)){
		if(nodo.getAbuelo().getHijoI() == nodo.getPadre()){
		    nodo = nodo.getPadre();
		    nodo.rotaIzquierda();
		}else if(nodo.getAbuelo().getHijoD() == nodo.getPadre()){
		    nodo = nodo.getPadre();
		    nodo.rotaDerecha();
		}
		actualizaAltura(nodo);
	    }
	    nodo.getPadre().setColor(NodoRojinegro.Color.NEGRO);
	    nodo.getAbuelo().setColor(NodoRojinegro.Color.ROJO);
	    NodoRojinegro<C> abuelo = nodo.getAbuelo();
	    if(abuelo.getHijoI() == nodo.getPadre()){
		abuelo.rotaDerecha();
	    }else{
		abuelo.rotaIzquierda();
	    }
	    actualizaAltura(abuelo);
	}
    }

    /**
     * Metodo auxiliar que actualiza la altura de los nodos desde el nodo especificado hasta la raiz
     * @param nodo, nodo especificado
     */
    protected void actualizaAltura(NodoRojinegro<C> nodo){
	while(nodo!=null){
	    nodo.actualizaAltura();
	    nodo = nodo.getPadre();
	}
    }

    /**
     * Metodo auxiliar que nos dice si dos nodos especificados estan cruzados
     * es decir si un nodo es hijo derecho y el otro es hijo izquierdo o viceversa
     * @param padre, nodo especificado
     * @param hijo, nodo especificado, hijo de padre.
     */
    protected boolean cruzados(NodoRojinegro<C> padre, NodoRojinegro<C> hijo){
	if(padre.getPadre().getHijoI() == padre && padre.getHijoD() == hijo){
	    return true;
	}
	if(padre.getPadre().getHijoD() == padre && padre.getHijoI() == hijo){
	    return true;
	}

	return false;
    }

    
}
