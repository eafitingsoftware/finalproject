package capa_negocio.paquete_usuarios;

public class Prueba {

	public static void main(String[] args) {

		CRUDMonitor c = new CRUDMonitor();

		for (Monitor m : c.listaMonitores) {

			System.out.println(m.getDisponibilidad());

		}

	}

}
