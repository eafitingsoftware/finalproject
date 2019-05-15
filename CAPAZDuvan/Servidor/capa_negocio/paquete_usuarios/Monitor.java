package capa_negocio.paquete_usuarios;

import java.util.HashMap;

public class Monitor{

	private String nombres;
	private String apellidos;
	private String tipoDocumento;
	private String numeroDocumento;
	private String email;
	private String telefono;
	private String codigoEstudiante;
	private HashMap<String, HashMap<String, String>> disponibilidad;

	public Monitor(String nombres, String apellidos, String tipoDocumento, String numeroDocumento, String email, String telefono, String codigoEstudiante, 
			HashMap<String, HashMap<String, String>> disponibilidad) {

		this.nombres = nombres;
		this.apellidos = apellidos;
	 	this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.email = email;
		this.telefono = telefono;
		this.codigoEstudiante = codigoEstudiante;
		this.disponibilidad = disponibilidad;
		
	}

	public String getCodigoEstudiante() {

		return this.codigoEstudiante;

	}

	public String getNombres() {

		return this.nombres;

	}

	public String getApellidos() {

		return this.apellidos;

	}

	public HashMap<String, HashMap<String, String>> getDisponibilidad() {

		return this.disponibilidad;

	}

}
