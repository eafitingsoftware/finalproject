package cliente;

import capa_negocio.paquete_asesorias.*;
import capa_negocio.paquete_usuarios.*;
import servicios_eafit.ayre.*;

import java.util.*;

public class Controlador {

	static CRUDAsesoria queryAsesorias = new CRUDAsesoria();
	static CRUDMonitor queryMonitores = new CRUDMonitor(); 
	static CRUDEstudiante queryEstudiantes = new CRUDEstudiante();

	public static void asesoriasPorMonitor(String codigoMonitor) {

		int contadorAsesorias = 0;
		int VALOR_ASESORIA = 4100;

		Monitor monitor = queryMonitores.consultarMonitor(codigoMonitor);
		ArrayList<Asesoria> asesoriasDelMonitor = queryAsesorias.consultarAsesoria(codigoMonitor);

		System.out.println("\nAsesorías realizadas por " + monitor.getNombres() + " " + monitor.getApellidos());

		for (Asesoria asesoria : asesoriasDelMonitor) {

			Estudiante estudiante = queryEstudiantes.consultarEstudiante(asesoria.getEstudiante());
			String nombreEstudiante = estudiante.getNombres() + " " + estudiante.getApellidos();
			System.out.println((contadorAsesorias + 1) + " => " + asesoria.getFecha() + " " + asesoria.getHora() + " " + nombreEstudiante);
			contadorAsesorias += 1;

		}

		System.out.println("\nTotal remuneración: $" + (contadorAsesorias * VALOR_ASESORIA) + "COP\n");

	}

	public static void agregarAsesoria(Asesoria asesoria) {

		queryAsesorias.agregarAsesoria(asesoria);

	}

	public static Asesoria consultarAsesoria(String codigoEstudiante, String dia, String hora) {

		Asesoria asesoria = queryAsesorias.consultarAsesoria(codigoEstudiante, dia, hora);
		
		imprimirAsesoria(asesoria);

		System.out.println("Asesorías disponibles para realizar la actualización: \n");

		consultarDisponibilidad(asesoria.getMateria());

		return asesoria;
	}

	public static void imprimirAsesoria(Asesoria asesoria) {

		System.out.println("\n=============== Datos de la asesoría =================");
		System.out.println("Día: " + asesoria.getFecha());
		System.out.println("Hora: " + asesoria.getHora());
		System.out.println("Código monitor: " + asesoria.getMonitor());
		System.out.println("Código estudiante: " + asesoria.getEstudiante());
		System.out.println("Código materia: " + asesoria.getMateria());
		System.out.println("Aula: " + asesoria.getAula());
		System.out.println("Temas: " + asesoria.getTemas());
		System.out.println("================================\n");

	}


	public static void agregarMonitor(String codigoEstudiante, HashMap<String, HashMap<String, String>> disponibilidad) {
	
		queryMonitores.agregarMonitor(codigoEstudiante, disponibilidad);	
		
	}

	public static boolean consultarDisponibilidad(String codigoMateria) {

		ArrayList<Monitor> monitores = queryMonitores.consultarMonitoresPorMateria(codigoMateria);

		int i = 0;

		for (Monitor monitor : monitores) {

			HashMap<String, String> fechasDisponibles = monitor.getDisponibilidad().get(codigoMateria);
			
			for (Map.Entry<String, String> entry : fechasDisponibles.entrySet()) {
			
				i += 1;

				System.out.println("====================================");
				System.out.println("Monitor: " + monitor.getNombres() + " " + monitor.getApellidos());
				System.out.println("Código monitor: " + monitor.getCodigoEstudiante());
				System.out.println("Día: " + entry.getKey() + " " + entry.getValue());
				System.out.println("====================================");
			}

		}

		return i > 0;

	}


}
