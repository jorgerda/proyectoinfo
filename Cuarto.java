import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cuarto {

	// Declaracion de propiedades
	private int numeroCuarto, piso, capacity, cuarto_id;
	private String edificio;

	// Default constructor
	public Cuarto(){
		capacity = numeroCuarto = piso = 0;
		cuarto_id = -1;
		edificio = null;
	}
	public Cuarto(int capacity){
		this.capacity = capacity;
		numeroCuarto = piso = 0;
		cuarto_id = -1;
		edificio = null;
	}
	public Cuarto(int capacity, int numeroCuarto, int piso, String edificio){
		this.capacity = capacity;
		this.numeroCuarto = numeroCuarto;
		this.piso = piso;
		this.edificio = edificio;
		cuarto_id = -1;
	}

	/*
		Juntar a /capacidad/ personas en un cuarto
		Criterio: Campus distinto, mismo sexo
	*/
	public boolean fillRoom(String gender) throws SQLException{
		if(capacity <= 0){
			System.out.println("Especifique la capacidad del cuarto.");
			return false;
		}
		
		String query = "";

		int temp[] = new int[capacity];
		int idtemp[] = new int[capacity];
		
		Connection conn = Database.connect();
		Statement stmt = conn.createStatement();
	
		query = "SELECT * FROM usuarios WHERE sexo = '" + gender + "' AND cuarto_id IS null";

		for (int i = 1; i <= capacity; i++) {
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			temp[i-1] = rs.getInt(7);
			idtemp[i-1] = rs.getInt(1);
			query += " AND campus IS NOT " + temp[i-1] + " AND id IS NOT " + idtemp[i-1];
			System.out.println(query);
		}
		
		query = "UPDATE usuarios SET cuarto_id =" + cuarto_id + " WHERE id=" + idtemp[0];
		
		for (int i = 1; i < idtemp.length; i++)
			query += " OR id=" + idtemp[i];

		try{
			stmt.executeUpdate(query);
			return true;
		} catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	// Ver las personas en el cuarto
	public void printUsers(){
		String query = "";
		String[] campusArray = {"Aguascalientes","Central de Veracruz","Chiapas","Chihuahua","Ciudad de México","Ciudad Juárez","Ciudad Obregón","Cuernavaca","Cumbres","Estado de México","Eugenio Garza Lagüera","Eugenio Garza Sada","Guadalajara","Hidalgo","Irapuato","Laguna","León","Morelia","Puebla","Querétaro","Saltillo","San Luis Potosí","Santa Catarina","Santa Fe","Sinaloa","Sonora Norte","Tampico","Toluca","Valle Alto","Zacatecas","Prep School El Paso","Sede Celaya","Sede Colima","Sede Esmeralda","Sede Matamoros","Sede Metepec","Sede Navojoa","Sede Santa Anita"};
		
		if(cuarto_id == -1){
			System.out.println("Cuarto no ha sido asignado");
			return;
		}
		
		try{
			Connection conn = Database.connect();
			Statement stmt = conn.createStatement();
			query = "SELECT * FROM usuarios WHERE cuarto_id = " + cuarto_id;
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				int id = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				int campus = rs.getInt(7);
				System.out.println(id + "\t" + nombre + "\t" + apellido + "\t" + campusArray[campus-1]);
			}
			
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
	}

	public void print(){
		// Imprimir los datos del cuarto
		System.out.println("Edificio: " + edificio);
		System.out.println("Número cuarto: " + numeroCuarto);
		System.out.println("Piso: " + piso);
		System.out.println("Capacidad: " + capacity);
		this.printUsers();
	}

	// Getters
	public int getCapacity(){
		return capacity;
	}
	public int getRoomNumber(){
		return numeroCuarto;
	}
	public int getPiso(){
		return piso;
	}
	public String getEdificio(){
		return edificio;
	}

	// Setters
	public void setCapacity(int capacity){
		this.capacity = capacity;
	}
	public void setRoomNumber(int roomNo){
		numeroCuarto = roomNo;
	}
	public void setPiso(int piso){
		this.piso = piso;
	}
	public void setEdificio(String edificio){
		this.edificio = edificio;
	}

}