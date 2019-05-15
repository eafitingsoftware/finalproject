package servicios_eafit.ayre;

import java.util.*;
import java.io.*;

public class CRUDEstudiante {

	private List<Estudiante> listaDeEstudiantes;
	private final String rutaTablaEstudiante = "/home/duvancardonagz/Taller3/CAPAZDuvan/Servidor/servicios_eafit/MySQL_EAFIT/datos_estudiante.csv";

	public CRUDEstudiante() {

		// Simulación de conexión a base de datos MySQL.

		listaDeEstudiantes = new ArrayList<Estudiante>();

		try{

			// Archivo que simula la tabla Estudiante de la base de datos MySQL.
			File archivoTablaEstudiante = new File(rutaTablaEstudiante);

			FileReader lectorArchivo = new FileReader(archivoTablaEstudiante);

			BufferedReader bufferArchivo = new BufferedReader(lectorArchivo);
		
			String registroTablaEstudiante = bufferArchivo.readLine();
			
			String[] camposRegistroTablaEstudiante;
			
			while ((registroTablaEstudiante = bufferArchivo.readLine()) != null) {

				camposRegistroTablaEstudiante = registroTablaEstudiante.split(";");
				
				String nombres = camposRegistroTablaEstudiante[0];
				String apellidos = camposRegistroTablaEstudiante[1];
				String tipoDocumento = camposRegistroTablaEstudiante[2];
				String numDocumento = camposRegistroTablaEstudiante[3];
				String email = camposRegistroTablaEstudiante[4];
				String telefono = camposRegistroTablaEstudiante[5];
				String codigo = camposRegistroTablaEstudiante[7];

				Estudiante estudiante;
				estudiante = new Estudiante(nombres, apellidos, tipoDocumento, numDocumento, email, telefono, codigo);

				this.listaDeEstudiantes.add(estudiante);

			}


		} catch (Exception e) {

			System.out.println("Se ha presentado un error con la base de datos.");

		}

	}

	public Estudiante consultarEstudiante(String codigoEstudiante) {

		for (Estudiante estudiante : this.listaDeEstudiantes) {
			if (estudiante.getCodigo().equals(codigoEstudiante)) return estudiante; 
		}

		return null;

	}

}
