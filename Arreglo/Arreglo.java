/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal.
 */
package ed.estructuras.lineales;

/**
 * Arreglo de n-dimensiones
 */
public class Arreglo implements IArreglo{

	private int [] mul;
	private int [] unit;

	/**
	*Constructor del arreglo unidimensional
	*@param dim - dimensiones del arreglo multidimensional
	*@throw IllegalArgumentException si algun valor en el arreglo dimensiones es negativo
	*/
	public Arreglo(int[] dim){

		for(int i = 0; i<dim.length; i++){
		
			if(dim[i] <=0) throw new IllegalArgumentException();
	}	

		mul = dim;

		int size = 1;

		for(int j = 0; j< dim.length; j++){
			size*= dim[j];
		}

		unit= new int[size];
	}

	/**
	* Devuelve el elemento que se encuentra en la posicion <code>th</code> en el arreglo multidimensional.
	* @param indices arreglo con los indices del elemento a recuperar.
	* @return el elemento almacenado en la posicion <code>i</code>.
	*/
	public int obtenerElemento(int [] indices){
		
		int ind = this.obtenerIndice(indices);
		return unit[ind];
	}

	/**
	* Asigna un elemento en la posicion <code>th</code> del arreglo multidimensional.
	* @param indices arreglo con los indices donde se almacenara el elemento.
	* @param elem elemento a almacenar.
	*/
	public void almacenarElemento(int [] indices, int elem){
		
		int indCop = this.obtenerIndice(indices);
		unit[indCop] = elem;
	}

	/**
	* Devuelve la posicion <code>i</code> del elemento en el arreglo de una dimension.
	* @param indices arreglo con los indices donde esta el elemento en el arreglo multidimensional.
        *                se debe cumplir que cada índice es positivo y menor que el tamaño de la dimensión correspondiente.
	* @return la posicion del elemento en el arreglo de una dimension.
	* @throws IndexOutOfBoundsException si alguno de los indices del arreglo no esta dentro del rango.
	*/
	public int obtenerIndice(int [] indices){
		if(mul.length != indices.length) throw new IndexOutOfBoundsException();
		
		for(int i= 0; i<indices.length;i++ ){
		
			if(indices[i]>mul[i] || indices[i] < 0) throw new IndexOutOfBoundsException();
		}
		
		int ind = indices[0];
		
		for(int j = 1; j < indices.length; j++){
		
			ind = ind*mul[j]+indices[j];
		}
		return ind;
	}



}
	
