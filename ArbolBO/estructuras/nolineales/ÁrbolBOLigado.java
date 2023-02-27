package ed.estructuras.nolineales;
import ed.estructuras.ColeccionAbstracta;
import ed.estructuras.lineales.ListaDoblementeLigada;
import java.util.List;
import java.util.Iterator;

public class ÁrbolBOLigado<C extends Comparable<C>> extends ColeccionAbstracta<C>  implements ÁrbolBinarioOrdenado<C>{
	
	protected NodoBOLigado<C> root;
	private final NodoBOLigado<C> nullito= new NodoBOLigado (null, null, null, null);
    public ÁrbolBOLigado(){
		
		root = null;
		tam = 0;
    }

    protected NodoBinario<C> creaNodo(C elem, NodoBinario<C> pad, NodoBinario<C> izq, NodoBinario<C> derecho){
		
		NodoBOLigado<C>nodo = new NodoBOLigado<C>(elem, (NodoBOLigado<C>)pad, (NodoBOLigado<C>)izq, (NodoBOLigado<C>)derecho);
		return nodo;
    }

    public NodoBinario<C> getRaíz(){
	
		return root;
    }

    public int getAltura(){
		
		if(root != null){
	    	
	    	return root.getAltura();
		}
		
		return -1;
    }

    protected NodoBinario<C> inserta(C e){
	
		NodoBinario<C> r= (NodoBinario<C>)root;
		int comparador= 0;
	
		while(r!= null){
		
		    comparador = e.compareTo(r.getElemento());
	   		
	   		if(comparador >= 0){
				
				if(r.getHijoD()== nullito){
		    		
		    		r.setHijoD(creaNodo(e, r, nullito, nullito));
		    		r= r.getHijoD();
		    		break;
			
				}else{
		    		
		    		r = r.getHijoD();
				}
	    	
	    	}else{
			
				if(r.getHijoI()== nullito){
		   			
		   			r.setHijoI(creaNodo(e,r, nullito, nullito));
		    		r= r.getHijoI();
		    		break;
			
				}else{
		    
		    		r= r.getHijoI();
				}
	    	}
		}
		
		return r;
    }

   
    @Override
    public boolean add(C e) throws NullPointerException{
		
		if(e== null) throw new NullPointerException();
	    
	    if(isEmpty()){
			
			root= (NodoBOLigado<C>)creaNodo(e,null,null,null);
			tam++;
			return true;
	    }
	    
	    tam++;
	    
	    return null != inserta(e);
    }


    @Override
    public boolean contains(C o) throws NullPointerException{
	
		if(o == null) throw new NullPointerException();
	  
	    if(busca(o) != null){

	    	return true;
		}
		
		return false;
    }

    @Override
    public void clear(){
		
		root = null;
		tam = 0;
    }

    protected NodoBinario<C> busca(C e){
		
		if(e== null){
	    
	    	throw new NullPointerException();
		}else{
	    	
	    	NodoBinario<C> r= (NodoBinario<C>)root;
	    	int zero= 0;
	   		while(r!= nullito){
				
				zero= e.compareTo(r.getElemento());
			
				if(zero== 0){
				
					return r;
		
				}else if(zero < 0){
			
				    r= r.getHijoI();
			
				}else{
		    	
		    		r= r.getHijoD();
				}
	    	}
		}
		
		return null;
    }

    protected NodoBinario<C> borra(NodoBinario<C> nodo){
		
		NodoBinario<C> aux = null;
		
		if(!nodo.esHoja()){
	    	
	    	aux= nodo;
	    	tam--;
	    	
	    	if(nodo== root){
				
				root= null;
	    
	    	}else{
				
				nodo.getPadre().remueveHijo(nodo);
	   		}
		
		}else{
	   		
	   		aux = nodo;
	    	
	    	while(!aux.esHoja()){
				
				if(aux.getHijoI()== nullito){
		    		
		    		aux= aux.getHijoI();
		    		aux= masGrande(aux);
				
				}else{
		    		
		    		aux= aux.getHijoD();
		    		aux= masChico(aux);
				}
		
			nodo.setElemento(aux.getElemento());
			nodo= aux;
	    	}
	    
	    if(aux!= null) tam--;
	    aux.getPadre().remueveHijo(aux);
		}
		return aux;
    }

    public boolean remove(C o) throws NullPointerException{
	
		if(o== null) throw new NullPointerException();

		NodoBinario<C> finder= busca(o);
		
		if(finder== null) return false;

		return (borra(finder)!= null);
    }
//Aqui mete mas chico
    private NodoBinario<C> minimo(NodoBinario<C> nodo){
	
		NodoBinario<C> base= nodo;
		
		while(!base.esHoja()){
	    	
	    	if(base.getHijoI()!= nullito){
			
				base= base.getHijoI();
				base= masChico(base);
	    	}else{
			
				base= base.getHijoD();
				base= masChico(base);
	    	}
		}
		return base;
    }

    protected NodoBinario<C> masChico(NodoBinario<C> nodo){
		
		NodoBinario<C> enano= nodo;
		
		while(enano.getHijoI()!= nullito){
	    	
	    	enano= enano.getHijoI();
		}
		
		return enano;
    }

    protected NodoBinario<C> masGrande(NodoBinario<C> nodo){
	
		NodoBinario<C> gigante= nodo;
		
		while(gigante.getHijoD()!= nullito){
	    	
	    	gigante= gigante.getHijoD();
		}
		
		return gigante;
    }

    public Iterator<C> getIteradorInorden(){
		
		return new IteradorIn();
    }

    public Iterator<C> getIteradorPreorden(){
	
		return new IteradorPre();
    }

    public Iterator<C> getIteradorPostorden(){
		
		return new IteradorPos();
    }

    public Iterator<C> iterator(){
		
		return new IteradorPre();
    }

    public List<C> recorridoPostorden(){
		
		Iterator it = getIteradorPostorden();
		ListaDoblementeLigada<C> list = new ListaDoblementeLigada<>();
		
		while(it.hasNext()){
		list.add((C)it.next());
		
		}
		
		return (List<C>)list;
    }

    private class IteradorIn implements Iterator<C>{
	
		NodoBinario<C> pivote;

		public IteradorIn(){
		    
		    pivote= root;
	    	
	    	if(root!= null && root.getHijoI()!= null){
			
				pivote= masChico(root);
	    	}
		}

		public boolean hasNext(){

	    	return (pivote!= null);
		}

		public C next(){
		    
		    C element= pivote.getElemento();
	    	
	    	if(!hasNext()) throw new IllegalStateException();
	
			if(pivote.getHijoD() != null){
				
			    pivote= masChico(pivote.getHijoD());
			
			}else{
		    	
		    	NodoBinario<C> pivote2= pivote;
		    	pivote= pivote.getPadre();
		    		
		    	while(pivote!= null && pivote.getHijoD()== pivote2){
						
					pivote2= pivote;
					pivote= pivote.getPadre();
		    	}
			}
	    	
	    	return element;
		}
	
    } //final de inorden 

    private class IteradorPre implements Iterator<C>{
		
		NodoBinario<C> pivote;

		public IteradorPre(){
		    pivote= root;
		}

		public boolean hasNext(){
		    
			return pivote!= null;
		}

		public C next(){
	 		
	 		C element = pivote.getElemento();
	    	
	    	if(!hasNext()) throw new IllegalStateException(); 
	
			if(pivote.getHijoD() != null){
		    		
		    	pivote= pivote.getHijoD();
				
			}else{
		    		
		    	NodoBinario<C>  aux= pivote;
		   		pivote= pivote.getPadre();
		    		
		    	while(pivote!= null && (pivote.getHijoD()== null || pivote.getHijoD()== aux)){
					
					if(pivote!= null && pivote.getHijoD()== aux){
			    			
			    		while(pivote!= null && pivote.getHijoD()== aux){
								
							aux= pivote;
							pivote= pivote.getPadre();
			    		}
					}
					
					while(pivote!= null && pivote.getHijoI()== aux && pivote.getHijoD()== null){
			    		
			   			aux= pivote;
			   	 		pivote= pivote.getPadre();
					}
				
				if(aux== root) return element;
		    		
		   		}
		    
		   		if(aux== root){
				
					pivote= null;
					return element;
		   		}
		   		
	   			pivote= pivote.getHijoD();
			} 
	    	
	    	return element;
		}
    }// fin it preorden

    private class IteradorPos implements Iterator<C>{
		
		NodoBinario<C> pivote;

		
		public IteradorPos(){
	    	
	    	pivote= minimo(root);
	    	System.out.println(pivote.getElemento());
		}

		public boolean hasNext(){
		
		    return pivote!= null;
		}

		public C next(){
	    
		    C element = pivote.getElemento();
	    	
	    	if(!hasNext()){
				
				throw new IllegalStateException(); 
	    	}else{
				
				if(pivote== root){
		    		
		    		pivote= null;
		    		return element;
				}
				
				if(pivote.getPadre().getHijoI()== pivote){
	    			
	    			if(pivote.getPadre().getHijoD()!= null){
						
						pivote= minimo(pivote.getPadre().getHijoD());
		    	
		    		}else{
					
						pivote= pivote.getPadre();
		    		}
				
				}else{
		   			
		   			pivote= pivote.getPadre();
				}
	    	}

	    	return element;
		}
    
    }//fin it post

}//fin final finalin 
