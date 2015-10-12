import java.util.*;
import java.io.*;

public class Transporte {
	private String fecha, origen, destino;
	private int capacidad;
	Actividad actividad;
	Usuario encargado;

	// Default constructor
	public Transporte(){
		fecha = origen = destino = "";
		encargado = new Usuario("instructor");
		actividad = new Actividad();
		capacidad = -1;
	}
	public Transporte(Actividad act, String fecha, String origen, String destino, int capacidad, Usuario encargado){
		this.fecha = fecha;
		this.origen = origen;
		this.destino = destino;
		this.encargado = encargado;
		actividad = act;
		this.capacidad = capacidad;
	}
	public boolean createJoinEntities(){
		// Create multiple entries:
		// SELECT id FROM USUARIOS
		// El result anterior guardalo en un array users[];
		/* 
			INSERT INTO ''TABLE'' (''column1'', [''column2, ... '']) 
			VALUES 
			(''value1a'', [''value1b, ...'']), 
			(''value2a'', [''value2b, ...'']), 
			...
		*/
	}


	// Getters

	// Setters
}