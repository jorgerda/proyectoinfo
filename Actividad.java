import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.h2.jdbcx.JdbcDataSource;
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
		encargado = new Usuario("Instructor");
	}
	public Actividad(String nombre, String fechaInicio, String fechaFin, String sede, Usuario encargado){
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.sede = sede;
		this.encargado = encargado;
		this.act_id = -1;
	}
	public boolean saveToDB(){
		if(act_id != -1){
			System.out.println("Ya existe esta actividad en la base de datos.");
			return false;
		}
		try{
			Connection conn = Database.connect();
			Statement stmt = conn.createStatement();
			
			String query = "INSERT INTO actividad(nombre, fecha_inicio, fecha_fin, sede) VALUES('" + this.nombre + "','" + this.fechaInicio + "','" + this.fechaFin + "','" + this.sede + "')";
			stmt.execute(query);
			query = "SELECT TOP 1 ID FROM actividad ORDER BY ID DESC";
			
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			act_id = rs.getInt(1);
			conn.close();

		} catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
		// Crear entradas en la BD en el JOIN TABLE asumiendo que todos los usuarios tienen que asistir
		createJoinEntries();
		return true;
	}
	public void printActividades(){
		try{
			Connection conn = Database.connect();
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM ACTIVIDAD";
			ResultSet rs = stmt.executeQuery(query);

			System.out.println("ID\tNombre\tFecha de Inicio\tFecha Fin\tSede");
			while(rs.next()){
				int id = rs.getInt(1);
				String nombre = rs.getString(2);
				String fecha_inicio = rs.getString(3);
				String fecha_fin = rs.getString(4);
				String sede = rs.getString(5);
				System.out.println(id + "\t" + nombre + "\t" + fecha_inicio + "\t" + fecha_fin + "\t" + sede);
			}
			conn.close();
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	public int getAsistentes(){
		return asistentes(this.act_id);
	}
	public static int getAsistentes(int act_id){
		return asistentes(act_id);
	}
	public int numeroAsistentes(int act_id){
		String query = "";
		query = "SELECT asistencia_actividad.usuario_id, usuarios.nombres, usuarios.apellidos, asistencia_actividad.asistencia FROM asistencia_actividad JOIN usuarios ON asistencia_actividad.usuario_id = usuarios.id AND actividad_id = " + act_id + "AND asistencia = TRUE";
		int count = 0;
		try{
			Connection conn = Database.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				boolean asistencia = rs.getBoolean(4);
				if(asistencia)
					count++;
			}
			conn.close();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return count;
	}
	public static int asistentes(int act_id){
		String query = "";
		query = "SELECT asistencia_actividad.usuario_id, usuarios.nombres, usuarios.apellidos, asistencia_actividad.asistencia FROM asistencia_actividad JOIN usuarios ON asistencia_actividad.usuario_id = usuarios.id AND actividad_id = " + act_id + "AND asistencia = TRUE";
		int count = 0;
		try{
			Connection conn = Database.connect();
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			System.out.println("ID\tNombres\tApellidos\tAsistió");

			while(rs.next()){
				int id = rs.getInt(1);
				String nombres = rs.getString(2);
				String apellidos = rs.getString(3);
				boolean asistencia = rs.getBoolean(4);
				System.out.println(id + "\t" + nombres + "\t" + apellidos + "\t" + asistencia);
				
				if(asistencia){
					count++;
				}
			}
			conn.close();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return count;
	}
	public void printAsistenciaSome(int act_id){
		asistentes(act_id);
	}
	public void printAsistenciaAll(int act_id){
		//Imprime toda persona, aunque haya asistido o no.
		String query = "";
		query = "SELECT asistencia_actividad.usuario_id, usuarios.nombres, usuarios.apellidos, asistencia_actividad.asistencia FROM asistencia_actividad JOIN usuarios ON asistencia_actividad.usuario_id = usuarios.id AND actividad_id = " + act_id;
		try{
			Connection conn = Database.connect();
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			
			System.out.println("ID\tNombres\tApellidos\tAsistió");

			while(rs.next()){
				int id = rs.getInt(1);
				String nombres = rs.getString(2);
				String apellidos = rs.getString(3);
				boolean asistencia = rs.getBoolean(4);
				System.out.println(id + "\t" + nombres + "\t" + asistencia);
			}
			conn.close();
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	private boolean createJoinEntries(){
		// Create multiple entries:
		try{
			Connection conn = Database.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id FROM usuarios");
			String query = "INSERT INTO asistencia_actividad(actividad_id, usuario_id) VALUES ";
			while(rs.next()){
				query += "(" + act_id + ", " + rs.getInt(1)+"), ";
			}
			// Quita la coma final
			query = query.substring(0, query.length()-2);
			query += ";";
			
			stmt.execute(query);

			conn.close();
			return true;
		} catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
	}

	// Getters
	public String getNombre(){
		return nombre;
	}
	public String getFechaInicio(){
		return fechaInicio;
	}
	public String getFechaFin(){
		return fechaFin;
	}
	public String getSede(){
		return sede;
	}
	public Usuario getEncargado(){
		return encargado;
	}
	public int getID(){
		return act_id;
	}

	// Setters
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public void setFechaInicio(String fechaInicio){
		this.fechaInicio = fechaInicio;
	}
	public void setFechaFin(String fechaFin){
		this.fechaFin = fechaFin;
	}
	public void setSede(String sede){
		this.sede = sede;
	}
	public void setEncargado(Usuario encargado){
		this.encargado = encargado;
	}
}