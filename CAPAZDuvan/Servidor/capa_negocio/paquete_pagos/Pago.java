package capa_negocio.paquete_pagos;

import capa_negocio.paquete_usuarios.*;
import java.io.*;
import java.util.*;

public class Pago {

	private static final int VALOR_HORA = 4100;

	private String fecha;
	private String codigoMonitor;
	private int horasRealizadas;
	

	public Pago(String fecha, String codigoMonitor, int horasRealizadas) {
		
		this.fecha = fecha;
		this.codigoMonitor = codigoMonitor;
		this.horasRealizadas = horasRealizadas;
	
	}


	public boolean realizarPago() {

		try {

			FileWriter archivo = new FileWriter("/home/duvancardonagz/Taller3/CAPAZDuvan/Servidor/capa_datos/MySQL/historial_pagos.csv", true);
			
			PrintWriter print = new PrintWriter(archivo);

			int totalPagar = horasRealizadas * VALOR_HORA;

			GestionUsuario usuarios = new GestionUsuario();
		
			print.println(this.fecha + ";" + this.codigoMonitor + ";" + totalPagar);
			return true;


		} catch (Exception e) {

			System.out.println("Error en la base de datos.");

		}

		return false;

	}

}
