package ed.estructuras.lineales;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ListIterator;
import ed.estructuras.ColeccionAbstracta;
import java.util.Arrays;


/**
 * <p>Clase genérica para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas no aceptan a <code>null</code> como elemento.</p>
 * @param <E>
 */
public class ListaDoblementeLigada<E>  extends ColeccionAbstracta<E> implements List<E>{

	/* Clase Nodo privada para uso interno de la clase Lista. */
    private class Nodo {
		
		public E elemento;
        public Nodo prev;
        public Nodo next;

        public Nodo(E elemento) {
	    this.elemento=elemento;
        }
    }

    private Nodo cabeza;
    private Nodo cola;
    private int tam;


    @Override
    public int size(){
		
		return tam;
    }

    @Override
   	public boolean isEmpty(){
		
		boolean aux= true;
		if(tam != 0){

			aux= false;
		}
		return aux;
    }

    @Override
    public boolean contains(Object o) {
	
		Nodo head= this.cabeza;
			while (head != null) {
	    	
	    		if(head.elemento != null){
					
					if (head.elemento.equals(o)) {
		    			
		    			return true;
					}
	    		}else{
		
					if(head.elemento == o){
		    	
		    			return true;
					}
	    		}
            
            head= head.next;	
        }
        
        return false;
    }

    @Override
    public Object[] toArray(){
		
		Iterator<E> it = iterator();
		Object[] array = new Object[tam];
		int zero=0;
	
		while(it.hasNext()){
	    
	    	array[zero++]=it.next();
		}	
		return array;
    }

    @Override
    public <T> T[] toArray(T[] a){
		
		if(a == null) throw new NullPointerException();
		T[] array= a;
		if(a.length<tam){
	    	
	    	array= Arrays.copyOf(a,tam);
		}
	
		Iterator<?> it = iterator();
		for(int i=0;i<tam;i++){
	    	
	    	array[i]=(T)it.next();
		}
		
		return array;
    }

    @Override
    public boolean add(E e) {
		
		Nodo a=cabeza;
		Nodo aux= new Nodo(e);
		
		if(this.cabeza==null){
	    	
	    	this.cabeza=aux;
	    	this.cola=aux;
		
		}else{
	    	
	    	this.cola.next=aux;
	    	aux.prev=cola;
	    	this.cola=aux;
		}
		this.tam+=1;
		return true;
    }
    
    private Nodo buscarElemento(E elemento) {
        
        Nodo head = cabeza;
        
        while (head!= null) {
	    	
	    	if(head.elemento== null && elemento== null){
			return head;
	    }else{
			
			if (head.elemento!= null && head.elemento.equals(elemento)) 
		    return head;
	    }
            head= head.next;
        }
        return head;
    }

    @Override
    
    public boolean remove(Object o) {
	
		Nodo eliminado = this.buscarElemento((E)o);
        	
        if (eliminado == null) return false;
        
        if (this.cabeza == this.cola) {
        
        
            this.cabeza = null;
            this.cola = null;
        } else if (this.cabeza == eliminado) {
            
            this.cabeza = this.cabeza.next;
            this.cabeza.prev = null;
        } else if (this.cola == eliminado) {
            
            this.cola = this.cola.prev;
            this.cola.next = null;
        } else {
           
            eliminado.prev.next = eliminado.next;
            eliminado.next.prev = eliminado.prev;
        }
		
		this.tam -= 1;
		return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
		
		if (c==null) throw new NullPointerException();
		if(c == this) return true;
	
		for (Object iterador: c ){
		
			if (!this.contains(iterador))return false;
		}
		
		return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
		
		Iterator<?> it=c.iterator();
		
		if(c==this) throw new IllegalArgumentException();
		if(c.isEmpty()) throw new NullPointerException();
		
		while(it.hasNext()){
	    	
	    	E nullO= null;
	   		nullO= (E) it.next();
	    	add(nullO);
		}
		
		return true;
    }

   	@Override
    public boolean addAll(int index, Collection<? extends E> c){
	
		if(c == null) throw new NullPointerException();
		if(index < 0) throw new IndexOutOfBoundsException();

		for(E elemento:c){
		    
		    this.add(index++,elemento);
		}
		return true;
    }

    @Override
    public boolean removeAll(Collection<?> c){
    	
    	if(c == null) throw new NullPointerException();
		if(c.size()==0) return false;
	
		for(Object elem: c){
	    	
	    	this.remove(elem);
	    }
		return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
		if(c == null) throw new NullPointerException();
		
		Iterator<E> it= iterator();
		Iterator<?> it2= c.iterator();
		int size=tam;
		
		if(c.isEmpty()){
	    	
	    	this.clear();
	    	return true;
		}
		
		if (c == this){
	    
	    	return false;
		
		}else{
	    	
	    	while(it.hasNext()){
				
				if (!c.contains(it.next())){
		    		
		    		it.remove();
				}
	    	}
		}
		return tam != size;
    }

    @Override
    public void clear() {
    
        this.cabeza=null;
        this.cola=null;
		this.tam = 0;
    }

    @Override
    public int hashCode() {
    
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void add(int index, E element) {
		
		if(index< 0) throw new IndexOutOfBoundsException();
	    
	    if(index== 0){
	    	
	    	this.agregaInicio(element);
		}

		if(index >= tam-1){
		
		    this.agregaFinal(element);
		}
	
		Nodo a = new Nodo(element);
		Nodo aux=cabeza;
		
		for(int i=1; i< index; i++){
	    	
	    	aux=aux.next;
		}
	
		a.next=aux.next;
		a.prev=aux;
		aux.next=a;
		tam+=1;
    }
    
    public void eliminaPrimero(){
		
		if(tam==1){
			this.clear();
		
		}else{
	    
	    	this.cabeza=this.cabeza.next;
	    	this.cabeza.prev=null;
		}
		
		this.tam-=1;
    }
    
    public void eliminaUltimo(){
	
		if(this.tam==1){
	    	
	    	this.cabeza=this.cola=null;
		
		}else{
	    	
	    	this.cola.prev = cola;
	   		this.cola.next=null;
		}
		
		this.tam-=1;
    }
    
    public E get(int i) {
        
		if(i<0 || i>=this.tam){
	    
	    	throw new IndexOutOfBoundsException();
		}
	
		Nodo head = this.cabeza;
		int zero=0;
		
		while(zero!=i){
	    	
	    	head=head.next;
	   		zero++;
		}
		
		return head.elemento;
   	}
    
    @Override
    public E set(int index, E element){
    
      	if(index == tam){
		
		    E tail=  cola.elemento;
			this.eliminaUltimo();
			this.agregaFinal(element);
	    	return tail;
		}
	    
		if(index < 0 || index > tam) throw new IndexOutOfBoundsException();
	
		E elemInd = this.get(index);
        this.remove(index);
		this.add(index,element);
		return elemInd;
    }
    
    @Override
	public E remove(int index){
		
		if(index < 0 || index > tam -1) throw new IndexOutOfBoundsException();
		
		Nodo head = cabeza;
        
        for(int i=0;i<index;i++){
	    
	    	head=head.next;
		}
		
		E elemHead= head.elemento;
		
		if(index== 0){
	    	
	    	eliminaPrimero();
	    	return elemHead;
		}
		
		if(index== tam-1){
	    	
	    	eliminaUltimo();
	    	return elemHead;
		}
		
		head.prev.next = head.next;
		head.next.prev = head.prev;
		tam--;
	
		return elemHead;
    }

    @Override
    public int indexOf(Object o){
    
    	Nodo head=this.cabeza;
		int zero=0;
		
		while(head!=null){
	    	
	    	if(head.elemento== null && null== o) return zero;
	    
	    	if(head.elemento != null && o != null){
				
				if(head.elemento.equals(o)){
		    		
		    		return zero;
				}
	    	}
	    
	    	zero++;
	   		head=head.next;
		}
		
		return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
		
		if(this.tam == 0) return -1;
		
		Nodo tail=this.cola;
		int aux=tam-1;
	
		while(tail!=null){
	    	
	    	if(tail.elemento == null && null == o) return aux;
	    	
	    	if(tail.elemento != null && o != null){
				
				if(tail.elemento.equals(o)){
					
					return aux;
				}
	    	}
	    
	    aux--;
	    
	    tail=tail.prev;
	    }
		
		return -1;
    }

    @Override
    public ListIterator<E> listIterator(){
	
		return new Iterador();
    }

    @Override
    public ListIterator<E> listIterator(int index){
		
		if(index < 0) throw new IndexOutOfBoundsException();
		
		Iterador it = new Iterador();
		
		for(int i=0; i<index; i++) it.next();
	
		return it;
    }


    /* Clase Iterador privada para iteradores. */
    private class Iterador implements ListIterator<E> {
		
		public Nodo prev;
        public Nodo next;

        public Iterador(){
	   		
	   		this.prev=null;
	    	this.next=cabeza;
        }

        public boolean hasNext(){
	    
	    	return this.next!=null;
        }

        public E next(){
	    	
	    	if(next==null) throw new NoSuchElementException();
	    	
	    	prev=next;
			next=next.next;
			return prev.elemento;
        }

        public boolean hasPrevious(){
	    	
	    	return this.prev != null;
        }

        public E previous(){
	    	
	    	if(prev==null) throw new NoSuchElementException();
	    	
	    	next=prev;
	    	prev=prev.prev;
	    	
	    	return next.elemento;
        }

        public void start() {
	    	
	    	prev =null;
	    	next=cabeza;
        }

        public void end() {
	    	
	    	next=null;
	    	prev=cola;
        }
	
		public void add(E element){
	    	
	    	agregaFinal(element);
		}
	
		public void remove(){
	    	
	    	this.next.prev=this.prev.prev;
	    	this.prev.prev.next=this.next;
	    	this.prev=this.next.prev;
		}
	
		public int previousIndex(){
	    	
	    	return indexOf((Object)previous());
		}
		
		public int nextIndex(){
		    return indexOf((Object)next());
		}

		public void set(E element){
	   		
		}
    }



    @Override 
    public List<E> subList(int fromIndex, int toIndex){
		
		if(fromIndex < 0 || toIndex > tam || fromIndex > toIndex) throw new IndexOutOfBoundsException();
       
		ListaDoblementeLigada<E> l = new ListaDoblementeLigada<>();
		
		for(int i=fromIndex;i<toIndex;i++){
	    	
	    	l.add(this.get(i));
		}
		return l;
    }

    public void agregaFinal(E elemento) {
		
		this.add(elemento);
    }
    
    public void agregaInicio(E elemento) {
		
		Nodo aux= new Nodo(elemento);
		
		if(cabeza==null){
	    	
	    	this.cabeza=aux;
	    	this.cola=aux;
		
		}else{
	   		
	   		this. cabeza.prev=aux;
	    	aux.next=this.cabeza;
		    this.cabeza=aux;
		}
		
		tam+=1;
    }

    public String toString(){
		
		Nodo it = this.cabeza;
        String string_lista = "[";
        
        while (it != null){
            
            string_lista += it.elemento;
            it = it.next;
            
            if (it != null) {
                
                string_lista += ", ";
            }
        }
        
        string_lista += "]";
		return string_lista;
    }

    public boolean equals(Object o){
		
		if (o == null) return false;
	
		ListaDoblementeLigada<E> lista = (ListaDoblementeLigada<E>)o;
		Nodo aux=this.cabeza;
		Nodo aux1=lista.cabeza;
		
		while(aux != null && aux1 != null){
	    	
	    	if(aux.elemento!=null && aux1.elemento != null){
			
				if(!aux.elemento.equals(aux1.elemento)){
		    		
		    		return false;
				}
	    	}else{
			
				if(aux.elemento == null && aux1.elemento != null)
		    	
		    	return false;
	    	}
	
	    	aux = aux.next;
	   	 aux1 = aux1.next;
		}
		
		return (aux1!=null) == (aux!=null);
    }
    
    public Iterator<E> iterator() {
    
        return new Iterador();
    }
    
    public static void print(Object s){
		
		System.out.println(s.toString());
    }

}