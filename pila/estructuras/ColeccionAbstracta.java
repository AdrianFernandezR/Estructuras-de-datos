package ed.estructuras;
import java.util.Collection;
import java.util.Arrays;
import java.util.Iterator;


public abstract class ColeccionAbstracta<E> implements Collection<E>{
    //Abributo que almacenará el tamaño de la colección
    protected int tam;

    /**
     *Método que nos dice si un elemento está o no dentro de la colección.
     *@paramatro elemento tipo Object llamado o, quien será buscado dentro de la colección
     *@return si el elemento está contenido se regresa true, de lo contrario false.
     **/

    @Override
    public boolean contains(Object o){
		Iterator<E> it= iterator();
		
		while(it.hasNext()){
	   		 E ind = it.next();
	    	
	    	if (ind==null){
				
				if (o==null) return true;
		
		    	}else{
			
			if (ind.equals(o)){
				return true;
	    		
	    		}
	    	}
		
		} return false;
    }

    
    /**
     *Método que crea un arreglo el cual contiene a todos los elementos de nuestra colección.
     *@return arreglo con todos los elementos dentro.
     **/
    
    @Override
    public Object[] toArray(){
		Iterator <E> it= iterator();
		
		Object [] toArr = new Object[this.size()];
		
		int ind=0;
		while (it.hasNext()){
	    	
	    	toArr [ind]=it.next();
			ind++;
		}
		
		return toArr;	    
    }


    
    /**
     *Método que crea un arreglo de cualquier tipo, el cual contendrá a todos los elementos den nuestra colección.
     *@paramatro arreglo de tipo genérico, llamado a.
     *@return arreglo con todos los elementos dentro.
     **/
    
    @Override
    public <T> T[] toArray(T[] a){
	
		if(a== null) throw new NullPointerException();
		T[] toArray= a;
		if (a.length< tam){

			toArray= Arrays.copyOf(a,this.size());
		}

		Iterator <?> it= iterator();
		
		for(int i=0; i<tam; i++){
	    	
	    	toArray[i]=(T) it.next();
			
		} return toArray;
    }


    /**
     *Método que nos dice si la colección que nos dan contiene a todos los elementos de nuestra colección
     *@parametro colección de tipo comodín llamada c 
     *@return true si la colección contiene a todos los elementos de la nuestra, de manera contrario regresamos false
     **/
    
    @Override
    public boolean containsAll(Collection<?> c){
		
		if (c==null) throw new NullPointerException();
		
		if(c == this) return true;
		
		for (Object it: c ){
	   
	    	if (!this.contains(it))return false;
		}
		return true;
    }
	

    /**
     *Método que agrega todos los elementos de la colección que nos pasan, a nuestra colección 
     *@parametro colección llamada c
     *@return true si se agregan todos los elementos a nuestra colección, en otro caso regresa false
     **/

    @Override
    public boolean addAll(Collection<? extends E> c){
		
		Iterator<?> it=c.iterator();
		
		if(c==this) throw new IllegalArgumentException();
		
		if(c.isEmpty()) throw new NullPointerException();
	
		while(it.hasNext()){
	    	
	    	E aux = null;
	    	aux = (E) it.next();
			add(aux);
		
		} return true;
    }



    
    /**
     *Método que elimina a un elemento de la colección.
     *@parametro elemento de tipo Object, nombrado o, el cual será eliminado de la colección.
     *@return true si el elemento fue removido de la colección, de lo contrario regresa false. 
     **/
    
    @Override
    public boolean remove(Object o){
        Iterator<E> it = iterator();
		Object value;
		
		while (it.hasNext()){
	    	value = it.next();
	    	if (value == null){
				
				if (o == null){
		    	
					it.remove();
		    		return true;
				}
	    	
	    	}else{
		
				if(value.equals(o)){
		    		
		    		it.remove();
				    return true;
				}
	    	}
		
		}	return false;
    }
    

    /**
     *Método que elemina todos los elementos de la colección.
     *@parametro colección llamada c, en la cual se eliminarán todos los elementos.
     *@return true si la colección cambia, de lo contrario regresa false.
     **/ 
    
    @Override
    public boolean removeAll(Collection<?> c){
		
		int firstSize=this.size();
		int lastSize=0;
	
		if (c==null) throw new NullPointerException();
		
		if (c==this){
	    	
	    	clear();
	    	return firstSize!=this.size();
		}
	
		Iterator <?> it = c.iterator();
		Object ind = null;

		while (it.hasNext()){
	    	
	    	ind = it.next();
	    	
	    	if (contains(ind)){
			
				remove(ind);
	    	}
		}
	
		if (firstSize == lastSize){
	    	
	    	return false;
		
		} return true;
    
    }



    
    /**
     *Método que elimina a los elementos de nuestra colección que no están en la colección que nos dan.
     *@parametro colección llamada c la cual contiene a los elementos por verificar.
     *@return true si es que la colección cambia, de otra manera, regresa false.
     **/

    @Override
    public boolean retainAll(Collection<?> c){
	
		Iterator<E> it= iterator();
		int size=tam;
	
		if (c==null)throw new NullPointerException();
		
		if(c.isEmpty()){
	    	
	    	this.clear();
	   		return true;
		}
		
		if (c==this){
	    	return false;
			
		}else{
	    	
	    	while(it.hasNext()){
			
				if (!c.contains(it.next())){
		    		
		    		it.remove();
				}
	    	}
		
		} return tam!= size;
    }



    
    /**
     *Método que elimina todos los elementos de la colección.
     **/
    
    @Override
    public void clear(){
		
		Iterator <E> it= iterator();
		
		while(it.hasNext()){
	    	
	    	it.next();
	   		it.remove();
		}
    }


    
    /**
     *Método que comprara si dos objectos son iguales
     *@parametro objecto a comparar
     *@return true si son iguales, de otra forma, regresa false.
     **/
    @Override 
    public boolean equals(Object obj){
		if (obj == this){
	    
	    	return true;
		}else{
	    
	    	if (obj ==null){
		
				return false;
	    
	    	}else{
		
				if (!(obj instanceof ColeccionAbstracta)){
		 		return false;
		
			}else{
		    
		    	Iterator<?> it;
		    	Iterator<E> it2;
		    	ColeccionAbstracta<E> colection=(ColeccionAbstracta<E>)obj;
		    	it=colection.iterator();
		    	it2=this.iterator();
		    	
		    	while(it.hasNext() && it2.hasNext()){
					
					E ind= it2.next();
					Object aux=it.next();
					
					if((aux==null && ind!=null)||(aux!=null && ind==null)){
			  			
			  			return false;
					}
			
					if (ind==null && aux==null){
					 
						return true;
					
					}else{
			
					    if (!ind.equals(aux)){
						return false;
			    	}
				}
		    }
		    
		    if (it.hasNext() || it2.hasNext()) return false;
		    
		    	return true;
				}
	    	}
		}		
    }
    
    @Override 
    public int hashCode(){
		
		int hash=tam*31;
		return hash;
    }

    
    /**
     *Método que duelve el tamaño de la colección
     *@return el número de elementos que contiene el arreglo.
     **/
    
    @Override
    
    public int size(){
	
		return tam;
    }

    /**
     *Método que imprime todo lo está en nuestra colección
     *@return cadena de elementos en strings.
     **/
    
    @Override
    public String toString(){
     
    	StringBuilder corch = new StringBuilder("[");
		Iterator <E> it = iterator();
		
		if (it.hasNext()){
	    	
	    	corch.append(it.next());
		}
	
		while(it.hasNext()){
	    	
	    	corch.append(", ");
	    	corch.append (it.next());
		}
	
		corch.append("]");
		return corch.toString();

    }


    
    /**
     *Método que nos dice que si la colección está vacía.
     *@return si el arreglo []=null entonces true; si el arreglo tiene elementos entonces false;
     **/
   
    @Override
    public boolean isEmpty(){
		
		if (size()==0){
	    
	    	return true;
		}
		
		return false;
    }
}
