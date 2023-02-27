

package ed.estructuras.lineales;
import java.util.*;
import java.util.Iterator;
import ed.estructuras.ColeccionAbstracta;

public class ColaArreglo<E> extends ColeccionAbstracta<E> implements ICola<E>{
    private int indHead;
    private int indTail;
    private E[] queue;
    private static final int defLength = 10;
    private E[] genQueue;

    /**
     * Constructor de una cola, crea una arreglo con el tamaño que pasan como parametro.
     * @param reArr arreglo generico de tamaño 0
     * @param length tamaño que sea desea para el arreglo
     * @throws IllegalArgumentException si reArr no tiene tamaño igual a 0 y length es menor a 0
     */
    public ColaArreglo(E[] reArr, int length){
	   
        if(queue.length != 0 || length < 0) throw new IllegalArgumentException();
        
        tam= 0;   
        queue= Arrays.copyOf(reArr,length);
        indHead=0;
        indTail= 0;
        genQueue= reArr;
    
    }

    /**
     * Constructor que crea una cola con tamaño 10 
     * @param reArr arreglo de tipo generico con tamaño 0
     * @throws IllealArgumentException si reArr no tiene tamaño 0
     */
    
    public ColaArreglo(E[] reArr){
        
        if(reArr.length != 0) throw new IllegalArgumentException();
        
        tam= 0;
        queue= Arrays.copyOf(reArr, defLength);
        indHead=0;
        indTail= 0;
        genQueue= reArr;
    }

    /**
     * Regresa el primer elemento de la cola sin alterarla
     * @return elemento que esta en la cabeza de la cola.
     */
    public E mira(){
        
        if(isEmpty()){
            
            return null;
        }else {
	       
            return queue[indHead];
        }
    }
   
    /**
     * Regresa el elemento incial de la cola y lo elimina, volviendo cabeza al siguiente elemento
     * @return El elemento siguiente, null en caso de que no haya elementos en la cola
     */
    public E atiende(){
        E auxHead; 
       
        if(isEmpty()){
    
            return null;
        
        }else {

            auxHead= queue[indHead];
            indHead= (indHead+1) % queue.length;
        }
        
        tam--;
        return auxHead;
    }
    
    /**
     * Coloca un elemento al final de la fila
     * @param e elemento que se desea agregar
     */
    public void forma(E e){
        
        if(isEmpty()){
            
            indHead= 0;
            queue[indHead] = e;
        }else{
            
            if(tam < queue.length){
                
                indTail= (indTail+1)%queue.length;
                queue[indTail]= e;
            }else{
                
                E[] array = Arrays.copyOf(genQueue, queue.length*2);
                int aux= 0;
                
                for(int i= indHead; aux< tam; i= (i+1)%queue.length){
                    
                    array[aux]= queue[i];
                    aux++;
                }
                
                queue= array;
                indHead= 0;
                indTail= tam;
                queue[indTail] = e;
            }
        }
        tam++;
    }

    /**
    * Borra un elemento de la estructura 
    * @throws UnsupportedOperationException
    */
    @Override
    public boolean remove(Object o){
    
        throw new UnsupportedOperationException();
    }

    /**
     * Deja la cola sin elementos
     * @throws UnsupportedOperationException.
     */
    @Override
    public boolean removeAll(Collection<?> c){
	   
        throw new UnsupportedOperationException();
    }

    /**
     * Compara una cola con una estructura para saber si tienen los mismos elementos
     * @throws UnsupportedOperationException.
     */
    @Override
    public boolean retainAll(Collection<?> c){
        
        throw new UnsupportedOperationException();
    }

    /**
     * Iterador de la estructura
     * @return iterador que recorre cola
     */
    public Iterator<E> iterator(){

        return new Iterador();
    }
    
    /**
     * Quita los elementos de la estructura
     * La cola queda con tamaño igual 0, indHead e indTail quedan con valor null.
     */
    @Override
    public void clear(){
        
        for(int i= indHead; i< tam; i= (i+1)%queue.length){
         
            queue[i]=null;
        }
        
        tam= 0;
        indHead=0;
        indTail= 0;
    }

    /**
     * Añade un elemento a la cola
     * @param e objeto que se desea añadir
     * @return true si se agrega, false si no se pudo agregar
     */
    @Override
    public boolean add(E e){
        
        int oldTam = tam;
        this.forma(e);
        
        return (tam != oldTam);
    }



        //Iterador de la cola.
        public class Iterador implements Iterator<E>{
        
        private int copyLength;
        private int copyHead;

        public Iterador(){
	    
           copyLength= tam;
           copyHead= indHead;
        }

        /**
         * Revisa si la cola tiene un elemento siguiente
         * @return boolean true si tiene elemento siguiente, false si no lo tiene
        */
        public boolean hasNext(){
            
            return (copyLength!= 0);
        }

        /**
         * Regresa el elemeto de la cola en donde esta parado el iterador
         * @return elemento en el que esta parado el iterador
         * @throws IllegalArgumentException si no tiene siguiente elemento
        */
        
        public E next(){
            
            if(!hasNext()){
            
                throw new IllegalArgumentException();
            }else{
              
                E data= queue[copyHead];
                copyHead= (copyHead+1)% queue.length;
                copyLength--;
                
                return data;
            }
        }
    }
}