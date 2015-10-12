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
		Fill this room with /capacity/ random people
		Criteria: Different campus, same sex
	*/
	public boolean fillRoom(String sex){
		if(capacity <= 0){
			System.out.println("Especifique la capacidad del cuarto.");
			return false;
		}
		// SELECT * FROM usuarios WHERE genero = 'sex' AND cuarto_id != null;
		// Perform binary search

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