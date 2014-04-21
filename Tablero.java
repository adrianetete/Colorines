/* Calvo Rojo, Adrian */

/* Anta Alonso, Guillermo */

package colorines;

public class Tablero {

	//Tablero con el que vamos a trabajar. Esta formado por un array de enteros con dos dimensiones. 10 filas x 6 columnas.
	//Es una variable privada, lo que hace que solo pueda ser accesible desde esta misma clase. Se puede trabajar con ella
	// a traves de los métodos, por eso estos son publicos.
	private int tablero[][] = 
		{
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0}
		};

	/**
	 * @param color   - Número que representa el color de la ficha
	 * @param columna - Número de la columna en la que se va a insertar la ficha
	 * 
	 * El método simula la caida de la ficha en una columna. En un principio la intenta colocar
	 * en la última posición de la columna dada, si esta llena, pasa a una posición anterior.
	 */
	public void insertarFicha(int color, int columna){
		
		columna -= 1; // Restamos uno a la columna para trabajar en términos de array
		int fila = tablero.length - 1; //Última fila
		boolean seguir = true;
		
		/* Mientras el hueco donde vamos a hacer la inserción está lleno, intentamos insertar
		 * en una posición anterior hasta llegar a la fila 1.
		 */
		while(seguir){
			
			if(!estaLleno(columna, fila)){
				
				//Inserción de ficha
				tablero[fila][columna] = color;
				
				//Cambiar el color, si se puede
				
				// Para no tener que comprobar todas las filas, se va a comprobar solo la fila
				// en la que se inserta la ficha y la siguiente (por si cambia de color)
				if(filaIgual(fila))
					eliminaFila(fila);
				
				// Cuando la fila sea menor que 9, intenta hacer el cambio de color y 
				// comprueba si puede eliminar la fila actual o la siguiente
				if(fila < 9){
					
					cambioColor(fila, columna);
					
					if(filaIgual(fila + 1))
						eliminaFila(fila + 1);
				}
				seguir = false;
				
			}else{
				
				if(fila != 0){
					
					fila-=1;
				}else{
					
					seguir = false;
				}				
			}
		}		
	}
	
	/**
	 * @param columna - Columna posición
	 * @param fila    - Fila posición
	 * 
	 * Retorna true si la posición dada está ocupada por otra ficha, color de 1 a 6, y
	 * false si está vacio, 0.
	 */
	public boolean estaLleno(int columna, int fila){
		
		 if(tablero[fila][columna] == 0){
			 
			 return false;
		 }else{
			 
			 return true;
		 }
	}
	
	/**
	 * @param veces - Número de veces que se va a ejecutar la sentencia
	 * 
	 * Este método inserta en el tablero una ficha de un color aleatorio en una posición
	 * aleatoria en cada vuelta. Sólo usado en la fase de pruebas.
	 */	
	public void insercionAletoria(int veces){
		
		for(int i = 0; i < veces; i++){

			insertarFicha(aleatorioSeis(), aleatorioSeis());
		}
	}
		
	/**
	 * @param columna - Posición de la columna que se va a evaluar
	 * 
	 * Método encargado de comprobar cuando una columna está llena.
	 */	
	public boolean columnaLlena(int columna){

		if(tablero[1][columna - 1] == 0){
		
				return false;
		}else{
				return true;
		}
	}
	
	/**
	 * @param fila - Posición de la fila que se va a evaluar entre[0-9]
	 * 
	 * Retorna true si todos los colores de una fila son iguales
	 */
	private boolean filaIgual(int fila){
		
		//Coger el color de la primera columna para compararlo con las demas.
		int color = tablero[fila][0];
		//Inicializamos igual a true
		boolean igual = true;
		
		//Recorremos cada columna
		for(int i = 0; i < 6; i++){
			
			//En caso de que los huecos de la columna siguiente sea distinto del color elegido
			//(aunque también puede ser que hayamos cogido un 0)
			//o sea igual a 0, la variable igual cambia a false
			if(tablero[fila][i] != color || tablero[fila][i] == 0)				
				igual = false;
		}
		return igual;
	}
	
	/**
	 * @param fila - Posición de la fila que se va a evaluar entre[0-9]
	 * 
	 * Método que modifica el array cuando esa fila tiene todas sus casillas de un mismo color
	 * Si se da el caso, cada casilla pasa a valer lo mismo que la que tiene encima. Si es la fila 0,
	 * todas sus casillas pasan a valer 0
	 * 
	 * Este método depende y se ejecuta despues del método filaIgual() ya que no queremos que una
	 * fila se elimine si no está llena.
	 */
	private void eliminaFila(int fila){
		
		//Recorrer columnas de menor a mayor
		for(int i = 0; i < 6; i++){
			
			//Recorrer filas de mayor a menor (abajo a arriba)
			for(int j = fila; j >= 0; j--){
				
				//Si llegamos a la fila 0
				if(j == 0){
					//El valor de sus casillas es 0
					tablero[j][i] = 0;
				//Si no
				}else{
					//Cada casilla pasa a valer lo que valga la casilla de encima
					tablero[j][i] = tablero[j-1][i];					
				}
			}
		}
	}
	/**
	 * @param fila    - fila en la que se encuentra la ficha
	 * @param columna - columna en la que se encuentra la ficha
	 * 
	 * Cuando introducimos una ficha en una determinada casilla, si esta y la que esta colocada
	 * debajo tienen unos determinados colores, se mezclan
	 * Por ejemplo 1 + 2 = 3
	 */
	private void cambioColor(int fila, int columna){
		
		//Rojo + Amarillo = Naranja(1+2=3)
		if(tablero[fila][columna] + tablero[fila + 1][columna] == 3){
			
			tablero[fila][columna] = 3;
			tablero[fila + 1][columna] = 3;
		}
		
		//Rojo + Azul = Morado(1+4=5) 
		if((tablero[fila][columna] == 1 && tablero[fila + 1][columna] == 4) 
		|| (tablero[fila][columna] == 4 && tablero[fila + 1][columna] == 1)){
			
			tablero[fila][columna] = 5;
			tablero[fila + 1][columna] = 5;
		}
		
		//Amarillo + Azul = Verde(2+4=6)
		if((tablero[fila][columna] == 2 && tablero[fila + 1][columna] == 4) 
		|| (tablero[fila][columna] == 4 && tablero[fila + 1][columna] == 2)){
			
			tablero[fila][columna] = 6;
			tablero[fila + 1][columna] = 6;
		}
		
		//Naranja + Morado = Rojo(3+5=1)
		if((tablero[fila][columna] == 3 && tablero[fila + 1][columna] == 5) 
		|| (tablero[fila][columna] == 5 && tablero[fila + 1][columna] == 3)){
			
			tablero[fila][columna] = 1;
			tablero[fila + 1][columna] = 1;
		}
		
		//Naranja + Verde = Amarillo(3+6=2)
		if((tablero[fila][columna] == 3 && tablero[fila + 1][columna] == 6) 
		|| (tablero[fila][columna] == 6 && tablero[fila + 1][columna] == 3)){
			
			tablero[fila][columna] = 2;
			tablero[fila + 1][columna] = 2;
		}
		
		//Morado + Verde = Azul(5+6=4)
		if((tablero[fila][columna] == 5 && tablero[fila + 1][columna] == 6) 
		|| (tablero[fila][columna] == 6 && tablero[fila + 1][columna] == 5)){
			
			tablero[fila][columna] = 4;
			tablero[fila + 1][columna] = 4;
		}
	}
	
	/** 
	 * Devuelve un dato de tipo int comprendido entre [1, 6]
	 */
	public int aleatorioSeis(){
		
		return (int)(Math.random() * 6) + 1;
	}
	
	/**
	 * Imprime el tablero por consola.
	 */
	public void verTablero(){
		
		System.out.println();
		System.out.println(" 1  2  3  4  5  6 \n");
		for(int i = 0; i < tablero.length; i++){
			
			for(int j = 0; j < tablero[1].length; j++){
				
				if(tablero[i][j] == 0){
					
					System.out.print(" - ");
				}else{
					
					System.out.print(" " + tablero[i][j] + " ");
				}
			}
			System.out.println();
		}
	}
}
