import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.h2.jdbcx.JdbcDataSource;

public class Transporte {
	private String fecha, origen, destino;
	private int capacidad;
	private static int transporte_id;
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
	public static boolean createJoinEntities(){
		// Create multiple entries:
		try{
			Connection conn = Database.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id FROM usuarios");
			String query = "INSERT INTO asistencia_transporte (transporte_id, usuario_id) VALUES ";
			while(rs.next())
				query += "(" + transporte_id + ", " + rs.getInt(1)+"), ";
			// Quita la coma final
			query = query.substring(0, query.length()-2);
			query += ";";
			System.out.println(query);
			return true;
		} catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	// Getters

	// Setters
}