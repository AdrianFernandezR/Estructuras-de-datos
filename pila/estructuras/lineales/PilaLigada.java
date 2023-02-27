package ed.estructuras.lineales;
import java.util.Iterator;
import ed.estructuras.ColeccionAbstracta;
import java.util.Collection;

public class PilaLigada<E> extends ColeccionAbstracta<E> implements IPila<E>{
    private Nodo<E> head;

    public PilaLigada(){
		
		tam=0;
		head=null;
    }
    

   @Override
    public E mira(){

    	if(isEmpty()){

    		return null;
    	} else {

    		return (E)head.data;
    	}
		
    }


    @Override
    public E expulsa(){
		if (isEmpty()) return null;
		
		Object headDat= head.data;
		head=head.nxt;
		tam -- ;
		
		return (E)head;
    }

    @Override
    public void empuja(E e){

		head= new Nodo (e, head);
		tam++;
    }


    @Override
    public boolean add(E e){
    	
    	int tamOld= tam;
		Nodo<E> nodoNew= new Nodo<E>(e);	
     	head = nodoNew;
		tam++;

		if(tamOld<tam){

			return true;
		
		} else {

			return false;
		}
    }


	@Override
	public Iterator<E> iterator(){

		Iterador<E> it= new Iterador();

		return it;
	}
	
	@Override
	public boolean remove(Object e){
		
		throw new UnsupportedOperationException();    
	}
	
	@Override 
	public boolean removeAll(Collection<?> e){
		
		throw new UnsupportedOperationException();
	}


	@Override
	public boolean retainAll(Collection<?> e){
		
		throw new UnsupportedOperationException();
	}
		//Iterador que nos permitira recorrer la pila sin modificar su estructura
    	private class Iterador<E> implements Iterator <E > {
		private Nodo ind=head;

		public Iterador(){

			Nodo ind= head;

		}

		@Override
		public boolean hasNext(){
	    	if (ind!=null) return true;

	    	return false;
		}
	
		@Override
		public E next(){
	    	
	    	if (hasNext()){
		
				E headDat= (E)head.data;
				ind = ind.nxt;
			
				return headDat;
	   		}
	  		
	  		return null;
		}	


	
		public void clear(){	
	
			while (!isEmpty()){
			
				expulsa();
			}	
		}
	}
}  
