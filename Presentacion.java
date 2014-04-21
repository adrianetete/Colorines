/* Calvo Rojo, Adrian */

/* Anta Alonso, Guillermo */

package colorines;
import java.util.Scanner;

public class Presentacion {
	
	public static void main(String[] args){
		
		//Creacion de un objeto de la clase Scanner para la lectura del teclado
		Scanner in = new Scanner(System.in);
		//Objeto de la clase Tablero
		Tablero a = new Tablero();
		
		//Inicialización de las variables enteras "nuevaFicha", "movimientos" y "columna"
		// nuevaFicha    - variable generada a través del método aleatorioSeis() va a recoger el color generado aleatoriamente
		// movimientos   - se irá incrementando según las fichas que se vayan insertando
		// columna       - recibirá el número dado por el usuario, donde va a querer insertar la ficha
		int nuevaFicha = 0, movimientos = -1, columna;
		
		//Inicialización de las variables booleanas "incorrecto" y "acabar"
		// incorrecto   - cualquier error detectado en la entrada de datos, se va a encargar de volver a pedirles en caso de error
		// acabar       - finalizará la ejecucion del programa cuando se llene una columna o el usuario introduzca un 0
		boolean incorrecto = false, acabar = false;
		
		System.out.println("Bienvenido a Colorines.");
		System.out.println ("   - El tablero esta formado por 10 filas y 6 columnas ");
		System.out.println ("   - Elije una columna y la ficha caera hasta la ultima fila vacia ");
		System.out.println ("   - Colores:");
		System.out.println ("       1 = Rojo\n       2 = Amarillo\n       3 = Naranja\n       4 = Azul\n       5 = Morado\n       6 = Verde\n");
		System.out.println ("   - Las casillas vacias estan representadas con \"-\" ");
		System.out.println ("   - Las fichas pueden modificar el color de las piezas sobre las que caen:");
		System.out.println ("       Rojo + Amarillo = Naranja\n       Rojo + Azul = Morado\n       Amarillo + Azul = Verde\n       Naranja + Morado = Rojo\n       Naranja + Verde = Amarillo\n       Morado + Verde = Azul\n");
		System.out.println ("   - En cada turno el usuario podra introducir los siguientes valores: ");
		System.out.println ("       0 para acabar partida\n       Del 1 al 6 para seleccionar columna\n       El resto de posibles valores introducidos serán tomado como incorrectos\n");
		
		while(!acabar){
		
			//Mostrar tablero
			a.verTablero();
			
			//Pedir la ficha como número aleatorio entre [1 - 6]
			nuevaFicha = a.aleatorioSeis();
			
			System.out.print("Siguiente ficha -> " + nuevaFicha + "\n ¿En qué columna [1-6]? ");
			
			/* Si le pasamos un 0 por teclado termina el programa
			 * Si le pasamos un número entre 1 y 6 inserta una ficha "nuevaFicha" en dicha columna
			 * Si le pasamos un número mayor que 6, el catch captura el error (incorrecto = true), y vuelve a pedir el dato
			 */
			do{
				//Try & Cath por si detecta algun error en la entrada, volver a pedir el dato
				try{
					//columna se iguala al valor introducido por teclado (in.next())
					columna = Integer.parseInt(in.next());
					
					if(columna != 0){
						
						if(!a.columnaLlena(columna)){
							
							//La columna no está llena, se pueden seguir insertando fichas
							a.insertarFicha(nuevaFicha, columna);
							//En caso que en la anterior entrada de datos haya habido un error, volvemos a igualar incorrecto a false
							incorrecto = false;
						
						}else{
							//Columna llena, por lo que insertamos la nueva ficha y finalizamos el programa
							a.insertarFicha(nuevaFicha, columna);
							acabar = true;
							//En caso que en la anterior entrada de datos haya habido un error, volvemos a igualar incorrecto a false
							incorrecto = false;
						}
					}else{
						//Finalizamos los dos bucles porque el usuario a introducido un 0
						acabar = true;
						//En caso que en la anterior entrada de datos haya habido un error, volvemos a igualar incorrecto a false
						incorrecto = false;
					}
					
				}catch(Exception g){
					
					//Error en la entrada de datos capturado, se lo indicamos al usuario y volvemos a pedirles
					incorrecto = true;
					System.out.print("Error. ");
					System.out.print("¿En qué columna [1-6]? ");
				}
				 
			}while(incorrecto);
			
			//Incrementamos los movimientos
			movimientos++;
		}
		
		//Mostrar por ultima vez el tablero
		a.verTablero();
		//Cerrar el flujo de entrada de datos
		in.close();
		System.out.println("Fin del juego. " + movimientos + " movimientos.");
	}
}
