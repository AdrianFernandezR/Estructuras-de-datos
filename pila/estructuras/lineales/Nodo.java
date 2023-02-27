package ed.estructuras.lineales;

public class Nodo<E>{

    protected Object data;
    protected Nodo nxt;

    /**
     *Crea un nodo con elemento igual a valor y apuntando al vacío.
     *@param valor el Objecto que es el valor de nodo.
     **/
    Nodo (Object valor){
    
    	this (valor,null);
    }

    
    /**
     *Crea un nodo después del indicado, con elemento igual a valor.
     *@param valor el Objecto que es el valor de nodo.
     *@param n el nodo anterior al recien creado.
     **/
    Nodo (Object valor, Nodo n){
	   
       data = valor;
	   nxt = n;
    }
}
    