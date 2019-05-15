package capa_negocio.paquete_asesorias; 

import capa_negocio.paquete_usuarios.*;
import servicios_eafit.ayre.*;
import java.io.*;
import java.util.*;

public class CRUDAsesoria {

	private List<Asesoria> listaAsesorias = new ArrayList<Asesoria>();

	private FileWriter archivoAEscribir;
	private PrintWriter escritorEnArchivo;

	private File archivo;
	private FileReader archivoLectura;
	private BufferedReader lectorArchivo;

	private CRUDEstudiante estudiantes;
	private CRUDMonitor monitores;

	private final String rutaTabla = "/home/duvancardonagz/Taller3/CAPAZDuvan/Servidor/capa_datos/MySQL/registro_asesorias.csv";

	public CRUDAsesoria() {

		try {

			this.archivoAEscribir = new FileWriter(rutaTabla, true);
			this.escritorEnArchivo = new PrintWriter(archivoAEscribir);

			this.archivo = new File(this.rutaTabla);
			this.archivoLectura = new FileReader(this.archivo);
			this.lectorArchivo = new BufferedReader(this.archivoLectura);

			String registroTablaAsesoria = lectorArchivo.readLine();

			String[] camposRegistroTablaAsesoria;

			while ((registroTablaAsesoria = lectorArchivo.readLine()) != null) {

				camposRegistroTablaAsesoria = registroTablaAsesoria.split(";");

				String fecha = camposRegistroTablaAsesoria[0];
				String hora = camposRegistroTablaAsesoria[1];
				String codigoMonitor = camposRegistroTablaAsesoria[2];
				String codigoEstudiante = camposRegistroTablaAsesoria[3];
				String materia = camposRegistroTablaAsesoria[4];
				String aula = camposRegistroTablaAsesoria[5];
				String temas = camposRegistroTablaAsesoria[6];
				
				Asesoria asesoria = new Asesoria(fecha, hora, codigoMonitor, codigoEstudiante, materia, aula, temas);

				this.listaAsesorias.add(asesoria);
			}

			this.estudiantes = new CRUDEstudiante();
			this.monitores = new CRUDMonitor();

		} catch (Exception e) {

			System.out.println("Ocurrió un error con la conexión.");

		}	

	}


	public void agregarAsesoria(Asesoria asesoria) {

		try {

			String codigoEstudiante = asesoria.getEstudiante();
			String codigoMonitor = asesoria.getMonitor();

			if (this.estudiantes.consultarEstudiante(codigoEstudiante) != null) {

				if (this.monitores.consultarMonitor(codigoMonitor) != null) {
					
					String datosAsesoria = asesoria.getFecha() + ";";
					datosAsesoria += asesoria.getHora() + ";";
					datosAsesoria += monitores.consultarMonitor(codigoMonitor).getCodigoEstudiante() + ";";
				       	datosAsesoria += estudiantes.consultarEstudiante(codigoEstudiante).getCodigo() + ";";
					datosAsesoria += asesoria.getMateria() + ";";
					datosAsesoria += asesoria.getAula() + ";";
					datosAsesoria += asesoria.getTemas();	

					System.out.println(datosAsesoria);

					this.escritorEnArchivo.println(datosAsesoria);

					System.out.println("La asesoría se asignó de manera correcta.");

					this.archivoAEscribir.close();

					return;

				}

				System.out.println("El estudiante con código " + codigoMonitor + " no es un monitor.");
				System.out.println("No se pudo asignar la asesoría.");

				return;
			} 
				
			System.out.println("El estudiante con código " + codigoEstudiante + " no se encuentra matriculado en la universidad EAFIT.");
			System.out.println("No se pudo asignar la asesoría.");

			return;
	

		} catch (Exception e) {
			
			System.out.println("Error con la base de datos.");

		}
	}

	public ArrayList<Asesoria> consultarAsesoria(String codigoMonitor) {

		ArrayList<Asesoria> asesoriasMonitor = new ArrayList<Asesoria>();


		for (Asesoria asesoria : this.listaAsesorias) {
				
			if (asesoria.getMonitor().equals(codigoMonitor)) asesoriasMonitor.add(asesoria);

		}

		return asesoriasMonitor;
	}

	public Asesoria consultarAsesoria(String codigoEstudiante, String dia, String hora) {

		for (Asesoria asesoriaIterador : this.listaAsesorias) {

			if (asesoriaIterador.getEstudiante().equals(codigoEstudiante) && asesoriaIterador.getFecha().equals(dia) &&
					asesoriaIterador.getHora().equals(hora)) return asesoriaIterador;

		}

		return null;
	}





}
