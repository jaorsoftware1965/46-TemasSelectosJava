// -------------------------------------------------------
// Agenda en Java
// -------------------------------------------------------

// Se necesita desarrollar un algoritmo que simule el funcionamiento de una
// agenda telefónica en la cual hay que, agregar, consultar, eliminar y actualizar 
// los contactos, así como cada registro se ira guardando en un archivo de texto 
// para mantenerlos cada que ejecutemos el programa.

// Cada contacto consta de:
// - id el cual será consecutivo, 
// - nombre, 
// - apellido, 
// - teléfono de casa,
// - teléfono móvil
// - fecha de nacimiento; 

// En el archivo de texto tendrá que guardar cada campo separado por una coma 
// por ejemplo:
// 1,Miguel,Salas,4929276335,4921720450,21-03-92
// 3,Angel,Razo,49276335,4921720450,21-03-92

// El programa tendrá un menú el cual constara de las siguientes opciones:
// 1. Agregar Contacto
// 2. Buscar Contacto
// 3. Imprimir Contactos
// 4. Eliminar Contacto
// 5. Actualizar Contacto
// 6. Salir

// El programa seguirá en curso hasta que el usuario no seleccione la opción salir,
// en caso de que seleccione alguna otra el funcionamiento será el siguiente:

// El usuario podrá seleccionar la opción por medio del número o la palabra que 
// está en el menú:
// • Si seleccionan el numero 1 o ponen la palabra Agregar deberán pedir los datos 
// del nuevo contacto y al finalizar guardarlo en el archivo de texto.
// • Si seleccionan él numero 2 o la palabra Buscar deberán preguntar que numero 
// de contacto buscan e imprimir sus datos en pantalla
// • Si seleccionan el numero 3 o la palabra Imprimir tendrán que mostrar todos los
// contactos en pantalla
// • Si seleccionan el numero 4 o la palabra Eliminar deberán seleccionar el id del 
// contacto que desean eliminar y borrarlo del archivo de texto
// • Si seleccionan el numero 5 o la palabra Actualizar deberán elegir el id de 
// contacto que quieran actualizar y poner cada uno de los nuevos campos para 
// actualizar dicho contacto
// Siempre se consulten los datos en el archivo de texto se tendrán que guardar 
// directamente en una matriz para realizar cambios o ajustes.
// 
// 
		
// En esta clase veremos como poder realizar una Lectura de 
// un Archivo de Texto.
	
// Librerías
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

// Clase se debe llamar igual que el archivo
public class programa
{   
	// Constantes
	static final int COLS = 7;   // Numero de Columnas en cada registro
	static final int ROWS = 100; // Numero Maximo de registros

	static final int COL_ID         = 0; // Columna con el Id
	static final int COL_NOM        = 1; // Columna del Nombre
	static final int COL_APE        = 2; // Columna del Apellido
	static final int COL_TEL_CASA   = 3; // Columna del Telefono de Casa
	static final int COL_TEL_MOVIL  = 4; // Columna del Telefono Movil
	static final int COL_FEC_NAC    = 5; // Columna de  Fecha Nacimiento
	static final int COL_CTRL_DEL   = 6; // Columna para el Control de Borrado
	
	// Nombre del Archivo
	static final String NOM_ARCHIVO = "Agenda_2023.txt"; // Nombre del Archivo

	// Opciones del Menu
	static final int OPC_AGREGAR    = 1; 
	static final int OPC_BUSCAR     = 2; 
	static final int OPC_IMPRIMIR   = 3; 
	static final int OPC_ELIMINAR   = 4; 
	static final int OPC_ACTUALIZAR = 5; 
	static final int OPC_SALIR      = 6; 

	static final String MARCA_ACTIVO  = " ";
	static final String MARCA_BORRADO = "*";	

	// Propiedades de la Clase

	// Contador de Registros en la Agenda
	static int registros=0;


	// Matriz para los datos de la Agenda
	static String[][] matrizAgenda = new String[ROWS][COLS];
	
    // Función main que es obligatorio
    public static void main(String args[])
    {
        // Despliega
        System.out.println("Programa de Agenda de Contactos");

		// Opcion
		int opcionSeleccionada=0;
		
		// Lee la Agenda
		leerAgenda();

		// Ciclo para el menu
		while (opcionSeleccionada!=6)
		{
			// Llamando al Menu Principal
			opcionSeleccionada = menu();

			// Verifica que Método ejecutar
			switch (opcionSeleccionada) 
			{
				case OPC_AGREGAR:	
					System.out.println("Agregar\n");			
					break;

				case OPC_BUSCAR:				
					System.out.println("Buscar\n");			
					break;	 

				case OPC_ACTUALIZAR:				
					System.out.println("Actualizar\n");			
					break;	 

				case OPC_ELIMINAR:				
					System.out.println("Eliminar\n");			
					break;	 

				case OPC_IMPRIMIR:				
					System.out.println("Imprimir\n");			
					break;	 
				
				case OPC_SALIR:				
					System.out.println("Salir\n");			
					break;	 
			
				default:
					System.out.println("opcion desconocida ...\n");			
					break;
			}

		}
	
		// Deja un Linea
		System.out.println("Programa Finalizado ...\n");
					          
    }  
	
	// Método para Desplegar el Menu y obtener la opción seleccionada
	static int menu()
	{
		// resultado
		int opcion = 0;

		// Creamos un objeto de la Clase
        Scanner oEntrada = new Scanner(System.in);

		// Lectura de la opcion
		String captura;
		
		// Ciclo para leer la opción correcta
		while (true)
		{
			// Despliega el Menu
			System.out.println("-----------------");
			System.out.println("Menu de la Agenda");
			System.out.println("-----------------");
			System.out.println("1. Agregar    Contacto");
			System.out.println("2. Buscar     Contacto");
			System.out.println("3. Imprimir   Contactos");
			System.out.println("4. Eliminar   Contacto");
			System.out.println("5. Actualizar Contacto");
			System.out.println("6. Salir");

			// Mensaje
			System.out.println("Capture Numero o Palabra:");

			// Captura del Usuario
			captura = oEntrada.nextLine(); 

			// Convierte a Mayusculas
			captura = captura.toUpperCase();
 
			// Si el nombre es vacío termina la captura
			if (captura.length()==0)
			{
				// Mensaje de Error
				System.out.println("Error. No deje vacia la captura.");
			}
			else
				// Verificamos que haya capturado mas de 1 caracter
				if (captura.length()>1)
				{
					// Comparamos que sea una palabra correcta
					if (captura == "AGREGAR")
					{
						// Asigna agregar
						opcion = OPC_AGREGAR;
					}
					else
					if (captura == "BUSCAR") 
					{
						// Asigna agregar
						opcion = OPC_BUSCAR;
					}
					else
					if (captura == "IMPRIMIR") 
					{
						// Asigna agregar
						opcion = OPC_IMPRIMIR;
					}
					else
					if (captura == "ELIMINAR") // Verifica si es agregar
					{
						// Asigna agregar
						opcion = OPC_ELIMINAR;
					}
					else
					if (captura == "ACTUALIZAR") // Verifica si es agregar
					{
						// Asigna agregar
						opcion = OPC_AGREGAR;
					}
					else
					if (captura == "SALIR") // Verifica si es agregar
					{
						// Asigna agregar
						opcion = OPC_AGREGAR;
					}
					else 
					{
						// Mensaje de Error
						System.out.println("Error en opción.");
					}
				}
				else
				{
					// Convertimos a numero
					opcion = Integer.parseInt(captura);

					// Verificamos si desborda
					if (opcion >= OPC_AGREGAR && opcion <= OPC_SALIR)
					   // Es correcta y salimos
					   break;
				}	
			
			// Limpia el Buffer
			oEntrada.nextLine();									   
		}

		 // Cerramos el Objeto
		 oEntrada.close();

		// Devuelve la opción seleccionada
		return opcion;

	}

	// Método para cargar los datos del Archivo
	static void leerAgenda()
	{		
		// Declaramos un Objeto de la Clase FileReader
		FileReader xFile;
								
		// Variable para Leer el Archivo
		BufferedReader xLector;

		// Variable de Tipo String
		String linea;
		
		// Crea la agenda si no existe
		crearAgenda();

		// Desplegamos el Mensaje
		System.out.println("Leyendo Agenda ...");
            		
		// Capturamos el Error
		try 
		{
			// Intentamos leer el archivo
		    xFile = new FileReader(NOM_ARCHIVO);
		    
		    // Asociamos el objeto File al objeto de Lectura del Archivo
		    xLector = new BufferedReader(xFile);

		    // Inciamos Lectura por Linea
		    System.out.println("Lectura por Linea...");

		    // Ciclo para leer del Archivo
		    while((linea = xLector.readLine()) != null)
			{
				// Despliega el Registro leido
		    	System.out.println(linea);

				// Obtiene los elementos separados por ","
				String[] datos = linea.split(",");

				// Coloca los datos en la matriz
				matrizAgenda[registros][COL_ID]        = datos[COL_ID];
				matrizAgenda[registros][COL_NOM]       = datos[COL_ID];
				matrizAgenda[registros][COL_APE]       = datos[COL_ID];
				matrizAgenda[registros][COL_TEL_CASA]  = datos[COL_ID];
				matrizAgenda[registros][COL_TEL_MOVIL] = datos[COL_ID];								
				matrizAgenda[registros][COL_CTRL_DEL]  = MARCA_ACTIVO;

				// Incrementa el contador de Registros
				registros++;
			}

			// Despliega el Numero de Registros en la Agenda
			System.out.println("Registros en la Agenda:"+registros);

		    // Cierra el Buffer y el Archivo
		    xLector.close();
		    xFile.close();		    
		}
		catch(java.io.FileNotFoundException fnfex) 
		{
		   System.out.println("Archivo no encontrado: " + fnfex);
		}
		catch(java.io.IOException ioex) 
		{
			System.out.println("Error en Archivo: " + ioex.getMessage());			
		}

		// Mensaje Final
        System.out.println("Fin de Lectura ...");       
	}

	// ----------------------------
	// Crea la Agenda si no existe
	// ----------------------------
	static void crearAgenda()
	{
		//  Crea el archivo
		File file = new File(NOM_ARCHIVO);		
		
		try 
		{	
			// Si el archivo no existe es creado
			if (!file.exists()) 
			{			
				// Crea el Archivo
				file.createNewFile();

				// Mensaje
				System.out.println("Se ha creado la Agenda ...");
			}
			else
			    // Mensaje
			    System.out.println("La Agenda ya existe ...");

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}