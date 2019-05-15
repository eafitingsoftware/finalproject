package cliente; 

import java.util.Scanner;
import java.util.HashMap;

import capa_negocio.paquete_asesorias.Asesoria;
import capa_negocio.paquete_asesorias.Memento;

public class Vista {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		menuPrincipal();

	}

	public static void menuPrincipal() {
		
		System.out.println("\n========================================");
		System.out.println("Sistema de información CAPAZ.");
		System.out.println("========================================\n");

		System.out.println("Seleccione una opción.\n");
		
		System.out.println("1. Asignar asesoría.");
		System.out.println("2. Consultar asesorías por monitor.");
		System.out.println("3. Inscribir un nuevo monitor.");
		System.out.println("4. Modificar asesoría.\n");

		System.out.print("Opción: ");
		int option = scanner.nextInt();

		switch(option) {

			case 1:
				asignarAsesoria();
				break;
			case 2:
				asesoriasPorMonitor();
				break;
			case 3:
				agregarMonitor();
				break;
			case 4:
				modificarAsesoria();
				break;
		}

	}

	public static void modificarAsesoria() {

		System.out.println("\n========================================");
		System.out.println("Modificar asesoría.");
		System.out.println("========================================\n");

		System.out.print("Código del estudiante: ");
		String codigoEstudiante = scanner.next();
		
		System.out.print("Día asesoría: ");
		String dia = scanner.next();

		System.out.print("Hora asesoría: ");
		String hora = scanner.next();
		
		System.out.println("\nSeleccione una opción: \n");
		System.out.println("1. Consultar");
		System.out.println("2. Cancelar");
		System.out.println("3. Salir\n");

		System.out.print("Opción: ");
		int option = scanner.nextInt();

		switch(option) {

			case 1:
				// Esta es la asesoría a la que se le desea regresar el estado.
				Asesoria asesoria = Controlador.consultarAsesoria(codigoEstudiante, dia, hora);
				Memento memento = asesoria.saveToMemento(); // memento alamcenará el estado de asesoria.

				System.out.print("Nuevo código del monitor: ");
				String codigoMonitor = scanner.next();
				asesoria.setMonitor(codigoMonitor);

				System.out.print("Nuevo día: ");
				dia = scanner.next();
				asesoria.setFecha(dia);
					
				System.out.print("Nueva hora: ");
				hora = scanner.next();
				asesoria.setHora(hora);

				System.out.print("Nuevos temas: ");
				String temas = scanner.next();
				asesoria.setTemas(temas);

				// Ahora, el objeto asesoria ha cambiado pero su estado anterior está almacenado en memento.
				
				System.out.println("\nSeleccione una opción: \n");
				System.out.println("1. Modificar");
				System.out.println("2. Cancelar");
				System.out.println("3. Salir\n");

				System.out.print("Opción: ");
				int optionAux = scanner.nextInt();	


				switch (optionAux) {

					case 1:
						//Controlador.modificarAsesoria(asesoria, memento);
						System.out.println("Se realizaron los cambios.");
						Controlador.imprimirAsesoria(asesoria);
						break;
					case 2:
						asesoria.restoreFromMemento(memento); // Se retorna al estado inicial desde el objeto memento.
						System.out.println("No se realizarón cambios.");
						Controlador.imprimirAsesoria(asesoria);
						break;

				}

				break;

		}


	}

	public static void asignarAsesoria() {

		System.out.println("\n========================================");
		System.out.println("Asignar nueva asesoría.");
		System.out.println("========================================\n");

		System.out.print("Código del estudiante: ");
		String codigoEstudiante = scanner.next();

		System.out.print("Código de la materia: ");
		String codigoMateria = scanner.next();

		System.out.println("\nSeleccione una opción: \n");
		System.out.println("1. Consultar disponibilidad");
		System.out.println("2. Cancelar");
		System.out.println("3. Salir\n");

		System.out.print("Opción: ");
		int option = scanner.nextInt();

		switch(option) {

			case 1: 
				//Asesoria asesoria = new Asesoria(fecha, hora, codigoMonitor, codigoEstudiante, codigoMateria, aula, temas);
				//Operaciones.agregarAsesoria(asesoria);
				if (Controlador.consultarDisponibilidad(codigoMateria)) {

					System.out.print("Código del monitor: ");
					String codigoMonitor = scanner.next();

					System.out.print("Día: ");
					String dia = scanner.next();
					
					System.out.print("Hora: ");
					String hora = scanner.next();

					System.out.print("Temas: ");
					String temas = scanner.next();
					
					Asesoria asesoria = new Asesoria(dia, hora, codigoMonitor, codigoEstudiante, codigoMateria, "23-204", temas);
					Controlador.agregarAsesoria(asesoria);
				}
				menuPrincipal();
				break;
			case 2:
				menuPrincipal();
				break;
			case 3:
				System.exit(0);

		}
	}

	public static void asesoriasPorMonitor() {

		System.out.println("\n========================================");
		System.out.println("Consultar asesorías realizadas.");
		System.out.println("========================================\n");

		System.out.print("Código del monitor: ");
		String codigoMonitor = scanner.next();
		
		System.out.println("\nSeleccione una opción: \n");
		System.out.println("1. Consultar");
		System.out.println("2. Cancelar");
		System.out.println("3. Salir\n");

		System.out.print("Opción: ");
		int option = scanner.nextInt();

		switch(option) {

			case 1:
				Controlador.asesoriasPorMonitor(codigoMonitor);
				break;
			case 2:
				menuPrincipal();
				break;
			case 3:
				System.exit(0);

		}
	

	}

	public static void agregarMonitor() {

		System.out.println("\n========================================");
		System.out.println("Agregar monitor.");
		System.out.println("========================================\n");

		System.out.print("Código como estudiante: \n");
		String codigoEstudiante = scanner.next();

		System.out.print("Número de materias que dictará: \n");
		int numMaterias = scanner.nextInt();

		HashMap<String, HashMap<String, String>> mapaDisponibilidad = new HashMap<String, HashMap<String, String>>();

		for (int i = 1; i <= numMaterias; i++) {

			System.out.print("Código materia número " + i + "\n");
			String codigoMateria = scanner.next();

			System.out.print("Número de horas que dictará para la materia " + codigoMateria + ":\n");
			int numHoras = scanner.nextInt();

			HashMap<String, String> mapaFechasHoras = new HashMap<String, String>();

			for (int j = 0; j < numHoras; j++) {

				System.out.print("Día disponible: \n");
				String dia = scanner.next();
				System.out.print("Hora disponible para el " + dia + ": \n");
				String hora = scanner.next();

				mapaFechasHoras.put(dia, hora);
		
			}

			mapaDisponibilidad.put(codigoMateria, mapaFechasHoras);

		}

		System.out.println("\nSeleccione una opción: \n");
		System.out.println("1. Agregar");
		System.out.println("2. Cancelar");
		System.out.println("3. Salir\n");

		System.out.print("Opción: ");
		int option = scanner.nextInt();

		switch(option) {

			case 1:
				Controlador.agregarMonitor(codigoEstudiante, mapaDisponibilidad);
				break;
			case 2:
				menuPrincipal();
				break;
			case 3:
				System.exit(0);

		}

	
	}

}
