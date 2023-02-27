package ed.estructuras.nolineales;
import java.util.*;
import java.lang.Math;

public class NodoAVL<C extends Comparable<C>> extends NodoBOLigado<C> implements NodoBinario<C>{

    
    protected  NodoAVL<C> nullito;

    public NodoAVL(C elemento, NodoAVL<C> padre, NodoAVL<C> hijoIzq, NodoAVL<C> hijoDer){
    
        super(elemento,padre,hijoIzq,hijoDer);
    }

    @Override
    public NodoAVL<C> getHijoI(){
	   
       return (NodoAVL<C>)hijoIzq;
    }

    @Override
    public NodoAVL<C> getHijoD(){
	   
       return (NodoAVL<C>)hijoDer;
    }

    @Override
    public NodoAVL<C> getPadre(){
	
        return (NodoAVL<C>)padre;
    }


    public void setAltura(int newAlt){
	   
        altura = newAlt;
    }


    public int calculaFB(){

        int balDer=0;
        int balIzq=0;

        if(hijoDer!= nullito){

            balDer= hijoDer.altura+1;

        }
        
        if(hijoIzq!= nullito){

            balIzq= hijoDer.altura+1;
        }
        
        return balIzq-balDer;
    }

    public void actualizaAltura(){
       
       if(this.esHoja()){
           
           this.altura= 0;
       }else{
           
            if(hijoDer!= nullito){
          
                int altDer= this.hijoDer.altura;

                if(hijoIzq== nullito){
                 
                    altura= altDer+1;
                
                }else{

                    int altIzq= hijoIzq.altura;
                    altura= Math.max(altIzq,altDer)+1;
                }
            }else{

                altura= hijoIzq.altura+1;
            }
        }       
    }

    public NodoAVL<C> rotaDerecha(){
        NodoAVL<C> rotado= this.getHijoI();
        this.hijoIzq = rotado.hijoDer;

        if(this.hijoIzq != nullito){

            this.hijoIzq.setPadre(this);
	   }

       if(this.padre != null){

            if(this.padre.hijoIzq == this) this.padre.hijoIzq = rotado;
            if(this.padre.hijoDer == this) this.padre.hijoDer = rotado;
        }
	   
       rotado.hijoDer = this;
	   rotado.setPadre(this.padre);
	   this.padre = rotado;
	
    	this.actualizaAltura();
    	return rotado;
    }

     /**
     * Metodo que hace la rotacion hacia la izquierda sobre un nodo
     * @return padre del nodo (despues de la rotacion)sobre el que se hizo la rotacion
     */
    public NodoAVL<C> rotaIzquierda(){
	   
       NodoAVL<C> rotado = this.getHijoD();
	   this.hijoDer= rotado.hijoIzq;
	   
       if(this.hijoDer!= nullito){
	       
            this.hijoDer.setPadre(this);
	   }
	   
       if(this.padre!= null){
	      
           if(this.padre.hijoDer == this) this.padre.hijoDer = rotado;
           if(this.padre.hijoIzq == this) this.padre.hijoIzq = rotado;
	   }
	   
        rotado.hijoIzq= this;
        rotado.setPadre(this.padre);
        this.padre= rotado;
	
        this.actualizaAltura();
        return rotado;
    }

}