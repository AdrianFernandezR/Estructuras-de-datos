package ed.estructuras;
import java.util.Collection;
import java.util.Arrays;
import java.util.Iterator;
public abstract class ColeccionAbstracta<E> implements Collection<E>{  

    protected int tam;

    @Override
    public boolean contains(Object o){
	Iterator<E> i = iterator();
	while(i.hasNext()){
	    E auxiliar = i.next();
	    if (auxiliar==null){
		if (o==null) return true;
	    }else{
		if (auxiliar.equals(o)) return true;
	    }
	}
	return false;
    }
    
    @Override
    public Object[] toArray(){
	Iterator <E> i= iterator();
	Object [] array = new Object[this.size()];
	int j=0;
	while (i.hasNext()){
	    array [j]=i.next();

	    j++;
	}
	return array;	    
    }  
    @Override
    public <T> T[] toArray(T[] a){
	if(a == null)
	    throw new NullPointerException();
	T[] auxiliar= a;
	if (a.length< tam){
	    auxiliar = Arrays.copyOf(a,this.size());
	}
	Iterator <?> it= iterator();
	for(int i=0; i<tam; i++){
	    auxiliar[i]=(T) it.next();
	    
	}
	return auxiliar;
    }


    
    @Override
    public boolean containsAll(Collection<?> c){
	if (c==null)
	    throw new NullPointerException();
	if(c == this) return true;
	for (Object iterador: c ){
	    if (!this.contains(iterador))return false;
	}
	return true;
    }
	


    
    @Override
    public boolean addAll(Collection<? extends E> c){
	Iterator<?> i=c.iterator();
	if(c==this) throw new IllegalArgumentException();
	if(c.isEmpty()) throw new NullPointerException();
	while(i.hasNext()){
	    E o = null;
	    o = (E) i.next();
	    add(o);
	}
	return true;
    }




    
    @Override
    public boolean remove(Object o){
        Iterator<E> i = iterator();
	Object auxiliar;
	while (i.hasNext()){
	    auxiliar = i.next();
	    if (auxiliar == null){
		if (o == null){
		    i.remove();
		    return true;
		}
	    }else{
		if(auxiliar.equals(o)){
		    i.remove();
		    return true;
		}
	    }
	}
	return false;
	
    }


    
    @Override
    public boolean removeAll(Collection<?> c){
	int tamaño=this.size();
	int tamañofinal=0;
	
	if (c==null) throw new NullPointerException();
	if (c==this){
	    clear();
	    return tamaño!=this.size();
	}
	Iterator <?> i = c.iterator();
	Object guardar = null;

	while (i.hasNext()){
	    guardar = i.next();
	    if (contains(guardar)){
		remove(guardar);
	    }
	}
	if (tamaño == tamañofinal){
	    return false;
	}
	return true;
    }



    
    @Override
    public boolean retainAll(Collection<?> c){
	Iterator<E> i= iterator();
	Iterator<?> i2= c.iterator();
	int tamañO=tam;
	if (c==null)throw new NullPointerException();
	if(c.isEmpty()){
	    this.clear();
	    return true;
	}
	if (c==this){
	    return false;
	}else{
	    while(i.hasNext()){
		if (!c.contains(i.next())){
		    i.remove();
		}
	    }
	}
	return tam!= tamañO;
    }
    @Override
    public void clear(){
	Iterator <E> i = iterator();
	while(i.hasNext()){
	    i.next();
	    i.remove();
	}
    }

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
		    Iterator<?> i;
		    Iterator<E> i2;
		    ColeccionAbstracta<E> coleccion=(ColeccionAbstracta<E>)obj;
		    i=coleccion.iterator();
		    i2=this.iterator();
		    while(i.hasNext() && i2.hasNext()){
			E e =i2.next();
			Object o =i.next();
			if((o==null && e!=null)||(o!=null && e==null)){
			    return false;
			}
			if (e==null && o==null){
			    return true;
			}else{
			    if (!e.equals(o)){
				return false;
			    }
			}
		    }
		    if (i.hasNext() || i2.hasNext()) return false;
		    return true;
		}
	    }
	}
    }

    
    @Override 
    public int hashCode(){
	int oc=tam*31;
	return oc;
    }


    
    
    @Override
    public int size(){
	return tam;
    }   
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
	Iterator <E> i = iterator();
	if (i.hasNext()){
	    sb.append(i.next());
	}
	while(i.hasNext()){
	    sb.append(",_");
	    sb.append (i.next());
	}
	
	sb.append("]");
	return sb.toString();

    }  
    @Override
    public boolean isEmpty(){
	if (size()==0){
	    return true;
	}
	return false;
    }
}
