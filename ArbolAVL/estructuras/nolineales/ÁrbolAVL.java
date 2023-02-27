package ed.estructuras.nolineales;
import java.util.*;

public class ÁrbolAVL<C extends Comparable<C>> extends ÁrbolBOLigado<C>{

	private final NodoAVL<C> nullito= new NodoAVL(null, null, null, null);

	public ÁrbolAVL(){
		
		root = null;
		tam = 0;
	}
	
	@Override
    protected NodoBinario<C> creaNodo(C data, NodoBinario<C> padre, NodoBinario<C> hijoIzq, NodoBinario<C> hijoDer){
		
		NodoAVL<C> nodo = new NodoAVL<C>(data, (NodoAVL<C>)padre,(NodoAVL<C>)hijoIzq, (NodoAVL<C>)hijoDer);
		return nodo;
    }

    //aqui mete insertaAVL
    protected void  agrega(C c){
		
		NodoAVL<C> nodo = insertaAVL(c);
		
		if(nodo != root){
	    	
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
		
		NodoAVL<C> r= (NodoAVL<C>)root;
		int zero= 0;
		
		while(r!= null){
	    	
	    	zero= e.compareTo(r.getElemento());
	    		
	    		if(zero>= 0){
					
					if(r.getHijoD()== null){

						r.setHijoD(creaNodo(e, r, null, null));
						r= r.getHijoD();
		    			break;
					
					}else{
		    			
		    			r= r.getHijoD();
					}
	    		
	    		}else{
					
					if(r.getHijoI()== null){
		    			
		    			r.setHijoI(creaNodo(e,r, null, null));
		   				r= r.getHijoI();
		    			break;
					
				}else{
		    		
		    		r= r.getHijoI();
				}
	    	}
		}
		
		reroot(r.getPadre());
		return r;
    }
    
    @Override
    public boolean add(C c) throws NullPointerException{
		
		if(c== null) throw new NullPointerException();
	    
		if(this.isEmpty()){
	    	
	    	root= (NodoAVL<C>)creaNodo(c, null, null, null);
	    	tam++;
	    	return true;
		}
		
		int size= tam;
        
        agrega(c);

        if (size== tam) return false;
		
		return false;
    }

    protected NodoAVL<C> elimina(NodoBinario<C> borrado){
		
		NodoAVL<C> nodo= (NodoAVL<C>)borra(borrado);
		reroot(nodo.getPadre());
		balancea(nodo.getPadre());
		
		return (NodoAVL<C>)nodo;
	
    }

    @Override
    public boolean remove(C c) throws NullPointerException{
		
		NodoBinario<C> finder= busca(c);
    	
    	if(c== null) throw new NullPointerException();
		if(finder== null) return false;
		
		int size= tam;
		elimina(finder);
		
		return (size!= tam);
    }
    
    /**
     * Metodo auxiliar que balancea el árbol
     * @param nodo, se balancea del nodo hasta la root.
     */
    protected void balancea(NodoAVL<C> nodo){
    	
		NodoAVL<C> aux= null;
		NodoAVL<C> aux2= null;
		int fb= nodo.calculaFB();
		
		while(nodo!= null){
	    	
	    	fb= nodo.calculaFB();
			
			if(fb== 2){
	    		
	    		if(nodo.getHijoI().calculaFB()>= 0){
					
					aux= nodo.rotaDerecha().getHijoD();
					reroot(aux);
	    		
	    		}

	    		if(nodo.getHijoI().calculaFB()< 0){
					
					aux= nodo.getHijoI().rotaIzquierda().getHijoI();
					reroot(aux);
					aux2= nodo.rotaDerecha().getHijoD();
					reroot(aux2);
	    		}
			
			}

			if(fb== -2){
	    	
	    		if(nodo.getHijoD().calculaFB()<= 0){
				
					aux= nodo.rotaIzquierda().getHijoI();
					reroot(aux);
   	
	    		}
	    			
	    		if(nodo.getHijoD().calculaFB() > 0){
				
					aux= nodo.getHijoD().rotaDerecha().getHijoD();
					reroot(aux);
					aux2= nodo.rotaIzquierda().getHijoI();
					reroot(aux2);
	    	
	    		}
			}
		
			if(nodo.getPadre() == null){
			
				root = nodo;
			}
		
			nodo = nodo.getPadre();
		}
    }

    protected void reroot(NodoAVL<C> nodo){
		
		if(nodo == null) throw new IllegalArgumentException();
			
		while(nodo != null){
	    		
	   		nodo.actualizaAltura();
	   		nodo= nodo.getPadre();
		}
    }
}