package ed.estructuras.nolineales;

import java.util.List;
import ed.estructuras.lineales.ListaDoblementeLigada;

public class NodoBOLigado<C extends Comparable<C> > implements NodoBinario<C>{
 
    private C elemento;
    private NodoBOLigado<C> raiz;
    private NodoBOLigado<C> padre;
    private NodoBOLigado<C> hijoIzq;
    private NodoBOLigado<C> hijoDer;
    private int altura;
    
	public NodoBOLigado(C elem, NodoBOLigado<C> pad, NodoBOLigado<C> izq, NodoBOLigado<C> der  ){

		elemento= elem;
		padre= pad;
		hijoIzq= izq;
		hijoDer= der;
		altura=0;
    }

    public NodoBOLigado(C elem){

		elemento= elem;
	}

	public NodoBOLigado (C elem, NodoBOLigado<C> pad){

		elemento= elem;
		padre= pad;
	}

    public C getElemento(){

		return elemento;

    }

    public void setElemento(C dato){

		elemento= dato;
    }

    public NodoBinario<C> getPadre(){

		return padre;
    }

    public void setPadre(Nodo <C> pad){
	
		padre= (NodoBOLigado <C>)pad;
    }

	public void setHijoD(NodoBinario<C> der){

    	hijoDer= (NodoBOLigado<C>)der;

    }

    public void setHijoI(NodoBinario<C>  izq){

    	hijoIzq= (NodoBOLigado<C>)izq;

    }

    public boolean esHoja(){

    	return getListaHijos().isEmpty();
    }

    public int getAltura(){

		return altura;
    }

    public Nodo<C> getHijo(int indice){

		if(indice> 1 || indice< 0) throw new IndexOutOfBoundsException();

		if(indice == 0){
			
			return hijoIzq;
	    	
	    }else{
				
			return hijoDer;
	    }
	
    }

    public NodoBinario <C> getHijoI(){

		return hijoIzq;
    }

    public NodoBinario <C> getHijoD(){

		return hijoDer;
    }

    public Nodo<C> getHermanoSiguiente(Nodo<C> hijo){

		if(this.padre.hijoIzq== hijo ) return this.padre.hijoDer;

		if(this.padre.hijoDer== hijo) return this.padre.hijoIzq;

		return null;

    }

    public int getGrado(){

		Nodo aux= this;
		int grad= 0;
		
		while(aux.esHoja() == false){

	    	grad+=1;
	    
	    	if(this.hijoIzq == null){

				aux= this.hijoIzq;
		
		    } else {

				aux= this.hijoDer;
	    	
	    	}
		}

		return grad;
	
    }

    public List<Nodo<C>> getListaHijos(){
		List<Nodo<C>> lAux= new ListaDoblementeLigada<>();

		NodoBOLigado<C> aux= this;
		NodoBOLigado<C> aux1= this;
		
		while(aux.esHoja() == false){

	    	if(aux.hijoDer!= null){

				lAux.add(aux.hijoDer);
				aux= aux.hijoDer;
	  	}

	    	if(aux1.hijoIzq !=null){

				lAux.add(aux1.hijoIzq);
				aux1= aux1.hijoIzq;
	    	}
		}
		return lAux; 

    }

    public boolean remueveHijo(Nodo<C> hijo){
		
		if(hijo == hijoDer){
	    	
	    	hijoDer= null;
	    	return true;
		
		}else if(hijo == hijoIzq){
	    	
	    	hijoIzq = null;
	    	return true;
		}
		return false;
    }
	
    
}