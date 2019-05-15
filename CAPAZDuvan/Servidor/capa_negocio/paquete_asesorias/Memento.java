package capa_negocio.paquete_asesorias;

public class Memento {

	private String fechaMemento;
	private String horaMemento;
	private String monitorMemento;
	private String temasMemento;

	public Memento (String fechaMemento, String horaMemento, String monitorMemento, String temasMemento) {

		this.fechaMemento = fechaMemento;
		this.horaMemento = horaMemento;
		this.monitorMemento = monitorMemento;
		this.temasMemento = temasMemento;

	}

	public String getFechaMemento() {

		return this.fechaMemento;

	}

	public String getHoraMemento() {

		return this.horaMemento;

	}

	public String getMonitorMemento() {

		return this.monitorMemento;

	}

	public String getTemasMemento() {

		return this.temasMemento;

	}

}
