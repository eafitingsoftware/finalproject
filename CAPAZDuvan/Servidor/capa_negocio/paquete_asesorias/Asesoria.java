package capa_negocio.paquete_asesorias;

import capa_negocio.paquete_usuarios.*;

public class Asesoria {

	public String fecha;
	public String hora;
	public String monitor;
	public String estudiante;
	public String materia;
	public String aula;
	public String temas;

	public Asesoria(String fecha, String hora, String codigoMonitor, String codigoEstudiante, String materia, String aula, String temas) {

		this.fecha = fecha;
		this.hora = hora;
		this.monitor = codigoMonitor;
		this.estudiante = codigoEstudiante;
		this.materia = materia;
		this.aula = aula;
		this.temas = temas;

	}

	public String getFecha() {

		return this.fecha;

	}
	
	public String getHora() {

		return this.hora;

	}

	public String getMonitor() {

		return this.monitor;

	}

	public String getEstudiante() {

		return this.estudiante;

	}

	public String getMateria() {

		return this.materia;

	}

	public String getAula() {

		return this.aula;

	}

	public String getTemas() {

		return this.temas;

	}

	public void setFecha(String fecha) {

		this.fecha = fecha;
	}

	public void setHora(String hora) {

		this.hora = hora;

	}

	public void setMonitor(String monitor) {

		this.monitor = monitor;

	}

	public void setTemas(String temas) {

		this.temas = temas;
	}

	public Memento saveToMemento() {

		return new Memento(this.fecha, this.hora, this.monitor, this.temas);

	}

	public void restoreFromMemento(Memento memento) {

		this.fecha = memento.getFechaMemento();
		this.hora = memento.getHoraMemento();
		this.monitor = memento.getMonitorMemento();
		this.temas = memento.getTemasMemento();

	}	
}
