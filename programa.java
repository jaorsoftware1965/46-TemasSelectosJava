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

// Librerías
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.Scanner;

// Clase se debe llamar igual que el archivo
public class programa {
	// Constantes
	static final int COLS = 7; // Numero de Columnas en cada registro
	static final int ROWS = 100; // Numero Maximo de registros

	static final int COL_ID = 0; // Columna con el Id
	static final int COL_NOM = 1; // Columna del Nombre
	static final int COL_APE = 2; // Columna del Apellido
	static final int COL_TEL_CASA = 3; // Columna del Telefono de Casa
	static final int COL_TEL_MOVIL = 4; // Columna del Telefono Movil
	static final int COL_FEC_NAC = 5; // Columna de Fecha Nacimiento
	static final int COL_STATUS = 6; // Columna para el Control de Borrado

	// Nombre del Archivo
	static final String NOM_ARCHIVO = "Agenda_2023.txt"; // Nombre del Archivo

	// Opciones del Menu
	static final int OPC_AGREGAR = 1;
	static final int OPC_BUSCAR = 2;
	static final int OPC_IMPRIMIR = 3;
	static final int OPC_ELIMINAR = 4;
	static final int OPC_ACTUALIZAR = 5;
	static final int OPC_SALIR = 6;

	static final String MARCA_ACTIVO = "Activo";
	static final String MARCA_BORRADO = "Borrado";

	// Propiedades de la Clase

	// Contador de Registros en la Agenda
	static int registros = 0;

	// Matriz para los datos de la Agenda
	static String[][] matrizAgenda = new String[ROWS][COLS];

	// Función main que es obligatorio
	public static void main(String args[]) {
		boolean salir = false;
		int opcion; // Guardaremos la opcion del usuario

		// Despliega
		System.out.println("Programa de Agenda de Contactos");

		// Lee la Agenda
		leerAgenda();

		// Crea el objeto teclado para lectura de datos
		Scanner teclado = new Scanner(System.in);

		while (!salir) {
			System.out.println("----------------------");
			System.out.println("Menu Agenda           ");
			System.out.println("----------------------");
			System.out.println("1. Agregar    Contacto");
			System.out.println("2. Buscar     Contacto");
			System.out.println("3. Imprimir   Contactos");
			System.out.println("4. Eliminar   Contacto ");
			System.out.println("5. Actualizar Contactos");
			System.out.println("6. Salir");

			System.out.println("Escribe una de las opciones");
			opcion = teclado.nextInt();

			switch (opcion) {
				case OPC_AGREGAR:
					agregarContacto();
					break;
				case OPC_BUSCAR:
					buscarContacto();
					break;
				case OPC_IMPRIMIR:
					imprimirContactos();
					break;
				case OPC_ELIMINAR:
					eliminarContacto();
					break;
				case OPC_ACTUALIZAR:
					actualizarContacto();
					break;
				case OPC_SALIR:
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 1 y 4");
			}
		}

		// Cierra el teclado
		teclado.close();

		// Mensaje Final
		System.out.println("Programa Finalizado ...");
	}

	// Método para cargar los datos del Archivo
	static void leerAgenda() {
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
		try {
			// Intentamos leer el archivo
			xFile = new FileReader(NOM_ARCHIVO);

			// Asociamos el objeto File al objeto de Lectura del Archivo
			xLector = new BufferedReader(xFile);

			// Inciamos Lectura por Linea
			System.out.println("Lectura por Linea...");

			// Ciclo para leer del Archivo
			while ((linea = xLector.readLine()) != null) {
				// Despliega el Registro leido
				System.out.println(linea);

				// Obtiene los elementos separados por ","
				String[] datos = linea.split(",");

				// Coloca los datos en la matriz
				matrizAgenda[registros][COL_ID] = datos[COL_ID];
				matrizAgenda[registros][COL_NOM] = datos[COL_NOM];
				matrizAgenda[registros][COL_APE] = datos[COL_APE];
				matrizAgenda[registros][COL_TEL_CASA] = datos[COL_TEL_CASA];
				matrizAgenda[registros][COL_TEL_MOVIL] = datos[COL_TEL_MOVIL];
				matrizAgenda[registros][COL_TEL_MOVIL] = datos[COL_FEC_NAC];
				matrizAgenda[registros][COL_STATUS] = MARCA_ACTIVO;

				// Incrementa el contador de Registros
				registros++;
			}

			// Despliega el Numero de Registros en la Agenda
			System.out.println("Registros en la Agenda:" + registros);

			// Cierra el Buffer y el Archivo
			xLector.close();
			xFile.close();
		} catch (java.io.FileNotFoundException fnfex) {
			System.out.println("Archivo no encontrado: " + fnfex);
		} catch (java.io.IOException ioex) {
			System.out.println("Error en Archivo: " + ioex.getMessage());
		}

		// Mensaje Final
		System.out.println("Fin de Lectura ...");

	}

	// Imprimir Contactos
	static void imprimirContactos() {
		// Indica el Registro a Insertar
		System.out.println("Lista de Contactos ...: " + registros);

		// Indice
		int indice;

		// Ciclo para desplegar
		for (indice = 0; indice < registros; indice++) {
			// Agrega los datos a la Matriz
			System.out.println("Registro  :[" + (indice + 1) + "]");
			System.out.println("Id        :" + matrizAgenda[indice][COL_ID]);
			System.out.println("Nombre    :" + matrizAgenda[indice][COL_NOM]);
			System.out.println("Apellido  :" + matrizAgenda[indice][COL_APE]);
			System.out.println("Tel Casa  :" + matrizAgenda[indice][COL_TEL_CASA]);
			System.out.println("Tel Movil :" + matrizAgenda[indice][COL_TEL_MOVIL]);
			System.out.println("Fec Nac   :" + matrizAgenda[indice][COL_FEC_NAC]);
			System.out.println("Status    :" + matrizAgenda[indice][COL_STATUS]);
			System.out.println("");
		}

		// Mensaje
		System.out.println("Se han impreso todos los registros ...");

	}

	// Agregar Contacto
	static void agregarContacto() {
		// Variables para los datos
		String nombre, apellido, telCasa, telMovil, fecNac;

		// Crea el buffer del Teclado
		Scanner teclado = new Scanner(System.in);

		// Indica el Registro a Insertar
		System.out.println("El Id del registro a grabar es: " + (registros + 1));

		// Solicita los datos
		System.out.println("Captura el Nombre, Apellido, teléfono de casa, teléfono móvil y fecha de nacimiento");
		System.out.println("Dando <Enter> despues de cada dato");

		// Lee los datos
		nombre = teclado.nextLine();
		apellido = teclado.nextLine();
		telCasa = teclado.nextLine();
		telMovil = teclado.nextLine();
		fecNac = teclado.nextLine();

		// Agrega los datos a la Matriz
		matrizAgenda[registros][COL_ID] = String.valueOf(registros);
		matrizAgenda[registros][COL_NOM] = nombre;
		matrizAgenda[registros][COL_APE] = apellido;
		matrizAgenda[registros][COL_TEL_CASA] = telCasa;
		matrizAgenda[registros][COL_TEL_MOVIL] = telMovil;
		matrizAgenda[registros][COL_FEC_NAC] = fecNac;
		matrizAgenda[registros][COL_STATUS] = MARCA_ACTIVO;

		// Mensaje
		System.out.println("El Registro ha sido agregado ...");

		// Incrementa el registro
		registros++;

	}

	// Actualizar Contacto
	static void actualizarContacto() {
		// registro Actualizar
		int registroActualizar;

		// Variables para los datos
		String nombre, apellido, telCasa, telMovil, fecNac;

		// Crea el buffer del Teclado
		Scanner teclado = new Scanner(System.in);

		System.out.println("Indica el registro a actualizar (1-" + registros + "):");
		registroActualizar = teclado.nextInt();

		// Valida el registro
		if (registroActualizar < 1 || registroActualizar > registros) {
			// Error
			System.out.println("El Registro que has indicado está fuera de rango");
		} else {
			// Solicita los datos
			System.out.println("Captura el Nombre, Apellido, teléfono de casa, teléfono móvil y fecha de nacimiento");
			System.out.println("Dando <Enter> despues de cada dato");

			// Lee los datos
			nombre = teclado.nextLine();
			apellido = teclado.nextLine();
			telCasa = teclado.nextLine();
			telMovil = teclado.nextLine();
			fecNac = teclado.nextLine();

			// Agrega los datos a la Matriz
			matrizAgenda[registroActualizar - 1][COL_NOM] = nombre;
			matrizAgenda[registroActualizar - 1][COL_APE] = apellido;
			matrizAgenda[registroActualizar - 1][COL_TEL_CASA] = telCasa;
			matrizAgenda[registroActualizar - 1][COL_TEL_MOVIL] = telMovil;
			matrizAgenda[registroActualizar - 1][COL_FEC_NAC] = fecNac;

			// Mensaje
			System.out.println("El Registro ha sido actualizado ...");
		}
	}

	// buscar Contacto
	static void buscarContacto() {
		// El registro a buscar
		int registroBuscar;

		// Crea el buffer del Teclado
		Scanner teclado = new Scanner(System.in);

		System.out.println("Indica el registro a buscar (1-" + registros + "):");
		registroBuscar = teclado.nextInt();

		// Valida el registro
		if (registroBuscar < 1 || registroBuscar > registros) {
			// Error
			System.out.println("El Registro que has indicado está fuera de rango");
		} else {
			// Despliega los datos del registro
			System.out.println("Registro encontrado:");

			System.out.println("Id        :" + matrizAgenda[registroBuscar][COL_ID]);
			System.out.println("Nombre    :" + matrizAgenda[registroBuscar][COL_NOM]);
			System.out.println("Apellido  :" + matrizAgenda[registroBuscar][COL_APE]);
			System.out.println("Tel Casa  :" + matrizAgenda[registroBuscar][COL_TEL_CASA]);
			System.out.println("Tel Movil :" + matrizAgenda[registroBuscar][COL_TEL_MOVIL]);
			System.out.println("Fec Nac   :" + matrizAgenda[registroBuscar][COL_FEC_NAC]);
			System.out.println("Fec Nac   :" + matrizAgenda[registroBuscar][COL_FEC_NAC]);
		}

		// Cierro el Teclado
		teclado.close();

	}

	// Eliminar Contacto
	static void eliminarContacto() {
		// El registro a eliminar
		int registroEliminar;

		// Crea el buffer del Teclado
		Scanner teclado = new Scanner(System.in);

		System.out.println("Indica el registro a eliminar (1-" + registros + "):");
		registroEliminar = teclado.nextInt();

		// Valida el registro
		if (registroEliminar < 0 || registroEliminar > registros) {
			// Error
			System.out.println("El Registro que has indicado está fuera de rango");
		} else {
			// Verifica que no esté eliminado
			if (matrizAgenda[registroEliminar][COL_FEC_NAC].equals(MARCA_BORRADO)) {
				// Mensaje
				System.out.print("El Registro ya se encuentra borrado");
			} else {
				// Despliega los datos del registro
				System.out.println("Registro a eliminar:");

				System.out.println("Id        :" + matrizAgenda[registroEliminar][COL_ID]);
				System.out.println("Nombre    :" + matrizAgenda[registroEliminar][COL_NOM]);
				System.out.println("Apellido  :" + matrizAgenda[registroEliminar][COL_APE]);
				System.out.println("Tel Casa  :" + matrizAgenda[registroEliminar][COL_TEL_CASA]);
				System.out.println("Tel Movil :" + matrizAgenda[registroEliminar][COL_TEL_MOVIL]);
				System.out.println("Fec Nac   :" + matrizAgenda[registroEliminar][COL_FEC_NAC]);

				// Le coloca la marca de Borrado
				matrizAgenda[registroEliminar][COL_STATUS] = MARCA_BORRADO;
			}
		}

		// Mensaje
		System.out.println("El Registro ha sido eliminado ...");

	}

	// ----------------------------
	// Crea la Agenda si no existe
	// ----------------------------
	static void crearAgenda() {
		// Crea el archivo
		File file = new File(NOM_ARCHIVO);

		try {
			// Si el archivo no existe es creado
			if (!file.exists()) {
				// Crea el Archivo
				file.createNewFile();

				// Mensaje
				System.out.println("Se ha creado la Agenda ...");
			} else
				// Mensaje
				System.out.println("La Agenda ya existe ...");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void pausar() {
		// String para la pausa
		String seguir;

		// Teclado
		Scanner teclado = new Scanner(System.in);

		// Mensaje
		System.out.println("Presiona Enter para continue...");

		// Captura el error
		try {
			// Lee
			seguir = teclado.nextLine();
		}

		catch (Exception e) {
			// Deja vacia la excepcion
		}
	}
}