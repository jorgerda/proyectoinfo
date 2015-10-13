import java.util.Scanner;
public class Actividad{

	// Declaracion de variables
	private String nombre, fechaInicio, fechaFin, sede;
	private int act_id;
	private Usuario encargado;

	// Default constructor
	public Actividad(){
		nombre = fechaInicio = fechaFin = sede = "";
		act_id = -1;
		encargado = new Usuario("instructor");
	}
	public Actividad(String nombre, String fechaInicio, String fechaFin, String sede, Usuario encargado){
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.sede = sede;
		this.encargado = encargado;
		act_id = saveToDB();
		// Create entries en la DB en el JOIN TABLE assuming * USERS are required to attend this event
		createJoinEntries();
	}
	private int saveToDB(){
		if(act_id != -1){
			System.out.println("Ya existe esta actividad en la base de datos.");
			return 0;
		}

		// Parse DATE
		// CREAR Query
		// STORE
		// Guardar en una variable el insertion id
		// return el id;
	}
	public int getAsistentes(){
		return asistentes(this.act_id);
	}
	public static int getAsistentes(int act_id){
		return asistentes(act_id);
	}
	private static int asistentes(int act_id){
		// SELECT * FROM ASISTENCIA_ACTIVIDAD WHERE actividad_id = act_id JOIN USUARIO ON asistencia_actividad.usuario_id = usuario.id
		// Iterate over resultSet and print it, count number of assistants = TRUE
		// return count

	}
	public void printAsistencia(){
		// Lo mesmo de arriba, pero ahora imprime todos, no nada mas los que si fueron
	}
	private boolean createJoinEntries(){
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
	public String getNombre(){return nombre;}
	public String getFechaInicio(){return fechaInicio;}
	public String getFechaFin(){return fechaFin;}
	public String getSede(){return sede;}
	public Usuario getEncargado(){return encargado;}
	public int getID(){return act_id;}

	// Setters
	public void setNombre(String nombre){this.nombre = nombre;}
	public void setFechaInicio(String fechaInicio){this.fechaInicio = fechaInicio;}
	public void setFechaFin(String fechaFin){this.fechaFin = fechaFin;}
	public void setSede(String sede){this.sede = sede;}
	public void setEncargado(Usuario encargado){this.encargado = encargado;}
}
