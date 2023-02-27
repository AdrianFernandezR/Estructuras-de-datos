package ed.complejidad;

public class UsoComplejidad{

	public static void main(String[] args) {
		
		for(int i=0; i<10; i++){

			try{

				//Ciclo Fibonacci
				Complejidad firstComp= new Complejidad();
				Complejidad secComp= new Complejidad(); 
				
				//Fibonacci
				firstComp.fibonacciIt(i);
				IComplejidad.escribeOperaciones("FibonacciIt.dat", i, firstComp.leeContador());
				
				secComp.fibonacciRec(i);
				IComplejidad.escribeOperaciones("FibonacciRec.dat", i, secComp.leeContador());
				
				for(int j=0; j<i; j++){

					//Ciclo Pascal
					Complejidad thirdComp= new Complejidad();
					Complejidad fourthComp= new Complejidad();

					//Pascal
					thirdComp.tPascalIt(i,j);
					IComplejidad.escribeOperaciones("PascalIt.dat", i-1, j, thirdComp.leeContador());

					fourthComp.tPascalRec(i,j);
					IComplejidad.escribeOperaciones("PascalRec.dat", i-1, j, fourthComp.leeContador());
				
				}

				IComplejidad.escribeLineaVacia("PascalIt.dat");
				IComplejidad.escribeLineaVacia("PascalRec.dat");
			
			} catch(Exception e){
				
			}
		}
	}
}