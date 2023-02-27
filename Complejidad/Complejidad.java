package ed.complejidad;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Complejidad implements IComplejidad{

	long cont;
	
    /**
     * Método para calcular, de forma recursiva, el elemento en la fila 
     * <code>i</code>, en la columna <code>j</code> del triangulo de Pascal 
     * @param ren el numero de fila
     * @param col el numero de columna  Si es <code>null</code> no se realizará
     *                este cálculo.
     * @return El elemento en la <code>i</code>-esima fila y la 
     * <code>j</code>-esima columna del triangulo de Pascal.
     * @throws IndexOutOfBoundsException Si los indices <code>i</code> o 
     * <code>j</code> son inválidos
     */
	
	public int tPascalRec(int ren, int col){

		if(ren < 0) throw new IndexOutOfBoundsException();
		if(col < 0) throw new IndexOutOfBoundsException();
		
		
		if(ren== 1 || col== ren || col==0) {
			cont+=1;
			return 1;
		
		}else {
			cont+=1;
			return tPascalRec(ren-1,col-1) + tPascalRec(ren-1, col);
		}

	}

	/**
     * Metodo para calcular, iterativamente, el elemento en la fila 
     * <code>i</code> y la columna <code>j</code> del triangulo de Pascal
     * @param ren el numero de fila 
     * @param col el numero de columna
     * @return El elemento en la i-esima fila y la j-esima columna del triangulo
     * de Pascal.
     * @throws IndexOutOfBoundsException Si los indices <code>i</code> o 
     * <code>j</code> son inválidos
     */
    
    public int tPascalIt(int ren, int col){
    	cont=0;

        if(ren <0 ||col<0 || (col>ren)) throw new IndexOutOfBoundsException();

        int [][] aux = new int [ren+1][];
        for(int i = 0; i <= ren;i++){
        	cont+=1;
            
            aux[i]= new int[i+1];

        }
        for(int i=0; i<aux.length; i++){
        	cont+=1;

            for(int j=0; j<aux[i].length; j++){
            	cont+=1;

                if(j==0 || j==i){
                	cont+=1;

                    aux[i][j]=1;
                } else{
                	cont+=1;

                    aux[i][j]= aux[i-1][j-1] + aux[i-1][j];
                }
            }
        } return aux[ren][col];
    }

    /**
     * Devuelve el n-esimo elemento, calculado de forma recursiva,
     * de la sucesion de Fibonacci
     * @param n el indice del elemento que se desea calcular
     * @return el n-esimo elemento de la sucesion de Fibonacci     
     * @throws IndexOutOfBoundsException Si el valor de <code>n</code>es inválido
     */

	public int fibonacciRec(int n){

		
		
		if (n < 0) throw new IndexOutOfBoundsException();
		
		if (n == 0){
			
			cont+=1;
			return 0;
		}

		if (n==1){
			
			cont+=1;
			return 1;
		
		} else {
			
			cont+=1;
			return fibonacciRec(n-1) + fibonacciRec(n-2);
		}

	}
	

	/**
     * Devuelve el n-esimo elemento, calculado de forma iterativa,
     * de la sucesion de Fibonacci
     * @param n el indice del elemento que se desea calcular
     * @return el n-esimo elemento de la sucesiond de Fibonacci  
     * @throws IndexOutOfBoundsException Si el valor de <code>n</code>es inválido
     */
	
	public int fibonacciIt(int n){
		cont= 0;

		if (n<0) throw new IndexOutOfBoundsException();

		if (n==0 || n==1){
			cont+=1;

			return 1;
		
		} else{
			cont+=1;

			int fiboIt []= new int [n];
			fiboIt[0]= 1;
			fiboIt[1]= 1;
		
			for (int i= 2; i< fiboIt.length; i++){
				cont+=1;

				int aux= 0;
				fiboIt [i]= fiboIt[i-1]+fiboIt[i-2];
				aux+=1;
			}
			
			return fiboIt[fiboIt.length-1];
		}
	}

	/**
     * Devuelve el número de operaciones acumuladas desde la última vez
     * que se reinició el contador.
     * @return número de operaciones.
     */
	public long leeContador(){

		return cont;
	}

	/**
     * Método para guardar las operaciones cuando sólo se usa un parámetro.
     * @param archivo nombre del archivo en el cual se agrega el reporte
     *                del número de operaciones que tardó en ejecutarse
     *                el método.
     * @param par valor que se usó al llamar al último método ejecutado.
     * @param ops número de operaciones realizadas.
     * @throws java.io.FileNotFoundException
     */
	public static void escribeOperaciones(String archivo, int par, long ops) throws FileNotFoundException {
      	
        try (PrintStream writer = new PrintStream(new FileOutputStream(archivo, true))) {
       
            writer.println(par+ " " + ops);
        }
    }

    /**
     * Método para guardar las operaciones cuando se llamó una función con dos
     * parámetros.
     * @param archivo nombre del archivo en el cual se agrega el reporte
     *                del número de operaciones que tardó en ejecutarse
     *                el método.
     * @param par1 primer valor que se usó al llamar al último método ejecutado.
     * @param par2 segundo valor que se usó al llamar al último método ejecutado.
     * @param ops número de operaciones realizadas.
     */

    public static void escribeOperaciones(String archivo, int par1, int par2, long ops) throws FileNotFoundException {
       
        
        try (PrintStream writer = new PrintStream(new FileOutputStream(archivo, true))) {
            
            writer.println(par1 + " " + par2 + " " + ops);
        }
    }

    /**
     * Método para escribir una línea en blanco en un archivo.
     * Se utilizará para las gráficas 3D.
     * @param archivo Nombre del archivo.
     * @throws FileNotFoundException 
     */
    
    public static void escribeLineaVacia(String archivo) throws FileNotFoundException {
     
        try (PrintStream writer = new PrintStream(new FileOutputStream(archivo, true))) {
     
            writer.println();
        }
    }
}