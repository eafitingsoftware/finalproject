package capa_negocio.paquete_usuarios;

import servicios_eafit.ayre.*;

import java.util.*;
import java.io.*;

public class CRUDMonitor {

	public List<Monitor> listaMonitores;
	private CRUDEstudiante queryEstudiantes;

	private FileWriter archivoAEscribir;
        private PrintWriter escritorEnArchivo;
	
	private final String rutaTablaMonitor = "/home/duvancardonagz/Taller3/CAPAZDuvan/Servidor/capa_datos/MySQL/datos_monitor.csv";

	public CRUDMonitor() {

		// Simulación de conexión a base de datos MySQL.

		listaMonitores = new ArrayList<Monitor>();
		queryEstudiantes = new CRUDEstudiante();

		try{

			// Archivo que simula la tabla Monitor de la base de datos MySQL.
			this.archivoAEscribir = new FileWriter(rutaTablaMonitor, true);
			this.escritorEnArchivo = new PrintWriter(archivoAEscribir);
			
			File archivoTablaMonitor = new File(rutaTablaMonitor);

			FileReader lectorArchivo = new FileReader(archivoTablaMonitor);

			BufferedReader bufferArchivo = new BufferedReader(lectorArchivo);
		
			String registroTablaMonitor = bufferArchivo.readLine();
			
			String[] camposRegistroTablaMonitor;
			
			HashMap<String, HashMap<String, HashMap<String, String>>> monitorDatos = new HashMap<String, HashMap<String, HashMap<String, String>>>();

			while ((registroTablaMonitor = bufferArchivo.readLine()) != null) {

				camposRegistroTablaMonitor = registroTablaMonitor.split(";");
				
				String datosMonitor = camposRegistroTablaMonitor[0] + ";";
				datosMonitor += camposRegistroTablaMonitor[1] + ";";
				datosMonitor += camposRegistroTablaMonitor[2] + ";";
				datosMonitor += camposRegistroTablaMonitor[3] + ";";
				datosMonitor += camposRegistroTablaMonitor[4] + ";";
				datosMonitor += camposRegistroTablaMonitor[5] + ";";
				datosMonitor += camposRegistroTablaMonitor[6];

				String codigoMateria = camposRegistroTablaMonitor[7];
				String disponibilidadDia = camposRegistroTablaMonitor[8];
				String disponibilidadHora = camposRegistroTablaMonitor[9];
				
				if (monitorDatos.get(datosMonitor) != null ) {

					if (monitorDatos.get(datosMonitor).get(codigoMateria) != null) {

						monitorDatos.get(datosMonitor).get(codigoMateria).put(disponibilidadDia, disponibilidadHora);

					} else {

						HashMap<String, String> mapaFechas = new HashMap<String, String>();
						mapaFechas.put(disponibilidadDia, disponibilidadHora);

						monitorDatos.get(datosMonitor).put(codigoMateria, mapaFechas);

					}

				} else {
				
					HashMap<String, HashMap<String, String>> mapaMateriaFechas = new HashMap<String, HashMap<String, String>>();
					HashMap<String, String> mapaFechas = new HashMap<String, String>();
					mapaFechas.put(disponibilidadDia, disponibilidadHora);
					mapaMateriaFechas.put(codigoMateria, mapaFechas);

					monitorDatos.put(datosMonitor, mapaMateriaFechas);
					
				}

			}

			for (Map.Entry<String, HashMap<String, HashMap<String, String>>> entry : monitorDatos.entrySet()) {

				String[] arrayDatosMonitor = entry.getKey().split(";");

				String nombres = arrayDatosMonitor[0];
				String apellidos = arrayDatosMonitor[1];
				String tipoDocumento = arrayDatosMonitor[2];
				String numeroDocumento = arrayDatosMonitor[3];
				String email = arrayDatosMonitor[4];
				String telefono = arrayDatosMonitor[5];
				String codigo = arrayDatosMonitor[6];
				HashMap<String, HashMap<String, String>> disponibilidad = entry.getValue();

				Monitor monitor = new Monitor(nombres, apellidos, tipoDocumento, numeroDocumento, email, telefono, codigo, disponibilidad);
				
				this.listaMonitores.add(monitor);

			}

		} catch (Exception e) {

			System.out.println("Se ha presentado un error con la base de datos.");

		}

	}

	public void agregarMonitor(String codigoEstudiante, HashMap<String, HashMap<String, String>> disponibilidad) {

		try {
			Estudiante estudiante;
		
			if ((estudiante = queryEstudiantes.consultarEstudiante(codigoEstudiante)) != null) {

				String datosBasicos = estudiante.getNombres() + ";";
				datosBasicos += estudiante.getApellidos() + ";";
				datosBasicos += estudiante.getTipoDocumento() + ";";
				datosBasicos += estudiante.getNumDocumento()+ ";";
				datosBasicos += estudiante.getEmail()+ ";";
				datosBasicos += estudiante.getTelefono() + ";";
				datosBasicos += estudiante.getCodigo() + ";";

				for (Map.Entry<String, HashMap<String, String>> entry : disponibilidad.entrySet()) {
					
					String codigoMateria = entry.getKey();

					for (Map.Entry<String, String> entryInterno : entry.getValue().entrySet()) {

						String dia = entryInterno.getKey();
						String hora = entryInterno.getValue();

						this.escritorEnArchivo.println(datosBasicos + codigoMateria + ";" + dia + ";" + hora);

					}

				}


			} else {

				System.out.println("No se pudo agregar el monitor dado que no pertenece a la universidad EAFIT.");

			}

			this.archivoAEscribir.close();

		} catch (Exception e) {

			System.out.println("Error en la base de datos.");

		}
	}

	public Monitor consultarMonitor(String codigoEstudiante) {

		for (Monitor monitor : this.listaMonitores) {
			if (monitor.getCodigoEstudiante().equals(codigoEstudiante)) return monitor; 
		}

		return null;

	}

	public ArrayList<Monitor> consultarMonitoresPorMateria(String codigoMateria) {

		ArrayList<Monitor> listaMonitores = new ArrayList<Monitor>();

		for (Monitor monitor: this.listaMonitores) {

			if (monitor.getDisponibilidad().get(codigoMateria) != null) listaMonitores.add(monitor);

		}

		return listaMonitores;
	}

}
