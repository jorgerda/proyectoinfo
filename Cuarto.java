import java.io.*;
import java.util.*;
public class Cuarto {

	// Declaracion variables (propiedades)
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
	public boolean fillRoom(String gender){
		int idtemp1, idtemp2, idtemp3, idtemp4, temp1, temp2, temp3, temp4;
		int idinput[] = new int[4];
		int campustemp[] = new int[4];
		
		if(capacity <= 0){
			System.out.println("Especifique la capacidad del cuarto.");
			return false;
		
		String query = "";

			int temp[] = new int[capacity];
			int idtemp[] = new int[capacity];

			Statement stmt = conn.createStatement();
		
			query = "SELECT * FROM usuarios WHERE sexo = 'H' AND cuarto_id IS null";

			for (int i = 1; i <= capacity; i++) {
				ResultSet rs = stmt.executeQuery(query);
				rs.next();
				temp[i-1] = rs.getInt(7);
				idtemp[i-1] = rs.getInt(1);
				query += " AND campus IS NOT " + temp[i-1] + " AND id IS NOT " + idtemp[i-1];
				System.out.println(query);
			}
			
			query = "UPDATE usuarios SET cuarto_id =" + cuarto_id + " WHERE id=" + idtemp[0];
			
			for (int i = 1; i < idtemp.length; i++) {
				query += " OR id=" + idtemp[i];
			}
			stmt.executeUpdate(query);

		return false;
	}

	// Get users in this room
	public void printUsers(){
		if(cuarto_id == -1){
			System.out.println("Cuarto no ha sido asignado");
			return;
		}
		// Query a la BD
		// SELECT * FROM usuarios WHERE cuarto_id = `cuarto_id`
		// Iterate over resultSet
		// Imprimes los usuarios
	}

	public void print(){
		// Imprimir los datos del cuarto
		System.out.println("Edificio: " + edificio);
		System.out.println("Numero cuarto: " + numeroCuarto);
		System.out.println("Piso: " + piso);
		System.out.println("Capacidad: " + capacity);
		this.printUsers();
	}

	/* Getters */
	public int getCapacity(){return capacity;}
	public int getRoomNumber(){return numeroCuarto;}
	public int getPiso(){return piso;}
	public String getEdificio(){return edificio;}

	/* Setters */
	public void setCapacity(int capacity){this.capacity = capacity;}
	public void setRoomNumber(int roomNo){numeroCuarto = roomNo;}
	public void setPiso(int piso){this.piso = piso;}
	public void setEdificio(String edificio){this.edificio = edificio;}

}