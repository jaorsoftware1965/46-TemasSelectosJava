// -------------------------------------------------------
// Agenda en Java
// -------------------------------------------------------

// Librerías
import java.util.Scanner;


// Clase se debe llamar igual que el archivo
public class testLectura
{   
    // Función main que es obligatorio
    public static void main(String args[])
    {
        // Variable para control de la salida
		boolean salir = false;

        // Para lectura de los datos
        String lectura;

        // Para la opcion
        int opcion;
       
		// Crea el objeto teclado para lectura de datos
		Scanner teclado = new Scanner(System.in);
        
      while(!salir)
		{   
		   System.out.println("----------------------");	         
		   System.out.println("Menu Agenda           ");
		   System.out.println("----------------------");
         System.out.println("1. Agregar    Contacto");
         System.out.println("2. Buscar     Contacto");
         System.out.println("3. Imprimir   Contactos");
		   System.out.println("4. Eliminar   Contacto ");
		   System.out.println("5. Actualizar Contactos");
         System.out.println("6. Salir");
      
         // Solicitud de Captura
         System.out.println("Seleccione:");
         lectura = teclado.nextLine();           

         try 
         {
              // Verifica si puede convertir a integer
              opcion = Integer.parseInt(lectura); 
         } 
         catch (Exception e) 
         {
              // Convierte a Mayusculas
              lectura = lectura.toUpperCase();

              // Verifica si es alguna de las palabras
              if (lectura.equals("AGREGAR"))          
                 opcion = 1;
              else
              if (lectura.equals("BUSCAR"))          
                 opcion = 2;
              else
              if (lectura.equals("IMPRIMIR"))          
                 opcion = 3;
              else
              if (lectura.equals("ELIMINAR"))          
                 opcion = 4;
              else
              if (lectura.equals("ACTUALIZAR"))          
                 opcion = 5;
              else
              if (lectura.equals("SALIR"))          
                 opcion = 6;
              else
                 opcion = 0;
         }      
         
         switch(opcion)
		   {
            case 1:
                  System.out.println("Opcion 1");
                  break;
            case 2:
                  System.out.println("Opcion 2");
                  System.out.println("Captura un numero:");
                  int x = teclado.nextInt();
                  break;
            case 3:
                  System.out.println("Opcion 3");
                  break;
				case 4:
                  System.out.println("Opcion 4");
                  break;   
				case 5:
                  System.out.println("Opcion 5");
                  break;   
            case 6:
                  salir=true;
                  break;
                default:
                  System.out.println("Opcion incorrecta");
         } 

         // Verifica si va de salida
         if (!salir)
         {
            // Pausar
            pausar();

            // Limpiar pantalla
            limpiarPantalla();
         }         
      }

      // Cierra el Teclado
      teclado.close();

		// Mensaje Final
		System.out.println("Programa Finalizado ...");					      
    }  	

   static void pausar()
	{
		// String para la pausa
		String seguir;

		// Teclado
		Scanner teclado = new Scanner(System.in);
	
		// Mensaje
		System.out.println("Presiona Enter para continue...");
	
		// Captura el error
		try
		{	
			// Lee
			seguir = teclado.nextLine();
		}
	
		catch(Exception e)
		{
			// Deja vacia la excepcion
		}
	}

   static void limpiarPantalla()
   {
      // Este comando me parece que solo funciona en Linux;
      // Habría que actualizarlo para windows.
      System.out.print("\033[H\033[2J");  
      System.out.flush();
   }
}