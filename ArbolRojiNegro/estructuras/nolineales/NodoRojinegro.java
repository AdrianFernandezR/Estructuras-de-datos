package ed.estructuras.nolineales;

/**
 * Clase NodoRojinegro
 * @author Tinoco Miguel Itzel
 */
public class NodoRojinegro<C extends Comparable<C>> extends NodoAVL<C>{


    /**
     * Construtor de un nodo con todos sus elementos
     * @param p padre del nodo
     * @param e elemento del nodo
     * @param i hijo izquierdo del nodo
     * @param d hijo derecho del nodo
     */
    public NodoRojinegro(NodoAVL<C> p, C e, NodoAVL<C> i, NodoAVL<C> d){
	super(p,e,i,d);
	color = NodoRojinegro.Color.ROJO;
    }

    /**
     * Regresa el color del nodo
     * @return color del nodo
     */
    public Color getColor(){
	return color;
    }

    /**
     * Asigan el color a un nodo
     * @param color del nodo
     */
    public void setColor(Color c){
	color = c;
    
    }

    public enum Color{
	ROJO,
	NEGRO
    }

    /**
     * Regresa el padre del nodo
     * @return hijo padre
     */
    @Override
    public NodoRojinegro<C> getPadre(){
	return (NodoRojinegro<C>)padre;
    }

    /**
     * Regresa el hijo derecho del nodo
     * @return hijo derecho
     */
    @Override
    public NodoRojinegro<C> getHijoD(){
	return (NodoRojinegro<C>)hijoD;
    }

    /**
     * Regresa el hijo izquierdo del nodo
     * @return hijo izquierdo
     */
    @Override
    public NodoRojinegro<C> getHijoI(){
	return (NodoRojinegro<C>)hijoI;
    }

    /**
     * Regresa el hermano del nodo
     * @return hermano del nodo
     */
    public NodoRojinegro<C> getHermano(){
	if(this.padre != null){
	    if(this.padre.getHijoI() == this){
		return this.getPadre().getHijoD();
	    }else if(this.padre.getHijoD() == this){
		return this.getPadre().getHijoI();
	    }
	}
	return null;
    }

    /**
     * Regresa el tio del nodo
     * @return tio del nodo
     */
    public NodoRojinegro<C> getTio(){
	if(padre!=null){
	    if(padre.getPadre()!=null){
		if(padre.getPadre().getHijoI()==padre){
		    return (NodoRojinegro<C>) padre.getPadre().getHijoD();
		}else{
		    return (NodoRojinegro<C>) padre.getPadre().getHijoI();	
		}
	    }
	}
	return null;
    }

    /**
     * Regresa el abuelo del nodo
     * @return abuelo del nodo
     */
    public NodoRojinegro<C> getAbuelo(){
	if(this.getPadre().getPadre() != null){
	    return this.getPadre().getPadre();
	}
	return null;
    }
    
}
