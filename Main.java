import java.sql.*;
import java.util.Scanner;

public class Main {
	static String nombre, fechaInicio, fechaFin, sede;
	public static void main(String args[]){
		menu();
	}
	public static void addActivity(){
		Scanner scan = new Scanner(System.in);
		String query = "";
		
		System.out.println("Introduzca el nombre de la actividad");
		nombre = scan.nextLine();
		
		System.out.println("Introduzca la fecha de inicio (YYYY-MM-DD hh:mm)");
		int l = 0;
		while(l == 0){
			fechaInicio = scan.nextLine();
			l = checkDate(fechaInicio);//Valida la fecha y hora
		}
		fechaInicio += ":00";//Agrega milisegundos
		
		System.out.println("Introduzca la fecha de conclusión (YYYY-MM-DD hh:mm)");
		l = 0;
		while(l == 0){
			fechaFin = scan.nextLine();
			l = checkDate(fechaFin);//Valida la fecha y hora
		}
		fechaFin += ":00";//Agrega milisegundos
				
		System.out.println("Introduzca la sede de la actividad");
		sede = scan.nextLine();
		
		System.out.println("Introduzca el ID del encargado de la actividad:");
		try{
			Connection conn = Database.connect();
			Statement stmt = conn.createStatement();
			query = "SELECT id,nombres,apellidos FROM USUARIOS WHERE puesto = 'Instructor'";
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("ID\tNombres\tApellidos");
			
			while(rs.next()){ //Imprimir una lista de instructores
				int id = rs.getInt(1);
				String nombres = rs.getString(2);
				String apellidos = rs.getString(3);
				System.out.println(id + "\t" + nombres + "\t" + apellidos);
			}
			conn.close();
		}catch(SQLException e){
			System.out.println("La actividad no se ha podido agregar");
			System.out.println(e.getMessage());
		}
		int choice = scan.nextInt();
		Usuario encargado = new Usuario(choice); //Establece el usuario con los datos del encargado
		System.out.println("It's me");
		Actividad nuevaActividad = new Actividad(nombre, fechaInicio, fechaFin, sede, encargado);//Crea la actividad
		if(nuevaActividad.saveToDB())
			System.out.println("Actividad agregada exitosamente.");
		else
			System.out.println("No se ha podido guardar la actividad. Intente nuevamente.");
	}
	public static Cuarto createRoom(){
		Scanner scanned = new Scanner(System.in);
		System.out.println("Introduzca la capacidad del cuarto");
		int capacidad = scanned.nextInt();
		System.out.println("Introduzca el edificio");
		scanned.nextLine();
		String edificio = scanned.nextLine();
		System.out.println("Introduzca el piso");
		int floor = scanned.nextInt();
		System.out.println("Introduzca el número de cuarto");
		int noCuarto = scanned.nextInt();
		
		System.out.println("Introduzca el género del cuarto a armar");
		scanned.nextLine(); // Clear buffer
		String gender = scanned.nextLine();
		Cuarto room = new Cuarto(capacidad, noCuarto, floor, edificio);
		try{

			if(room.fillRoom(gender))
				return room;
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return new Cuarto();
	}
	// TODO change this to boolean
	public static int checkDate(String fecha){
		int k = 0;
		boolean yearCondition = false; //Almacena si el año está en formato correcto
		boolean monthCondition = false;//Almacena si el año está en formato correcto
		boolean dayCondition = false;//Almacena si el año está en formato correcto
		boolean hourCondition = false;//Almacena si la hora está en formato correcto
		boolean minuteCondition = false;//Almacena si los minutos están en formato correcto

		int year, month, day, hour, minute;

		year = Integer.parseInt(fecha.substring(0,4));
		month = Integer.parseInt(fecha.substring(5,7));
		day = Integer.parseInt(fecha.substring(8,10));
		hour = Integer.parseInt(fecha.substring(11,13));
		minute = Integer.parseInt(fecha.substring(14,16));
			
		//Verifica el año
		if(year >= 2015 && year <= 2040){
			yearCondition = true;
		}
		//Verifica el mes
		if(month >= 1 && month <= 12){
			monthCondition = true;
		}
		//Verifica el día según el mes
		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){// Verifica si el mes tienen 31 días
			if(day >= 1 && day <= 31){
				dayCondition = true;
			}
		} else if(month == 4 || month == 6 || month == 9 || month == 11){// Verifica si el mes tiene 30 días
			if(day >= 1 && day <= 30){
				dayCondition = true;
			}
		} else if(month == 2){
			if(day >= 1 && day <= 28){
				dayCondition = true;
			}
		}
		if(hour > 0 && hour <24){
			hourCondition = true;
		}
		if(minute >= 0 && minute <= 59){
			minuteCondition = true;
		}
		if(yearCondition && monthCondition && dayCondition && hourCondition && minuteCondition){
			k = 1;
		} else{
			System.out.println("Fecha u hora inválida, intente nuevamente");
			k = 0;
		}
		return k;
	}
	public static void printAllUsers(){
		String query = "SELECT * FROM CUARTO";
		try{
			Connection conn = Database.connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(!rs.next()){
				System.out.println("No hay cuartos registrados");
				return;
			}
			System.out.println("No. Cuarto" + "\t" + "Edificio" + "\t" + "Piso");
			while(rs.next()){
				int roomNo = rs.getInt(2);
				String building = rs.getString(3);
				String piso = rs.getString(4);
				System.out.println(roomNo + "\t" + building + "\t" + piso);
			}
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	public static void printAsistenciaActividad(){
		Scanner scan = new Scanner(System.in);
		Actividad actividad = new Actividad();
		
		System.out.println("Imprimir lista de asistencia con: 1. Todos los usuarios 2. Sólo aquellos que atendieron");
		int choice = scan.nextInt();
		System.out.println("Introduzca el ID de la actividad");
		actividad.printActividades();
		int act_id = scan.nextInt();
		
		if(choice == 1){//Asistencia de todos los usuarios
			actividad.printAsistenciaAll(act_id);
			System.out.println("\nTotal de asistencias: " + actividad.numeroAsistentes(act_id) + "\n");
		} else if(choice == 2){//Imprimir sólo los que atendieron
			actividad.printAsistenciaSome(act_id);
			System.out.println("\nTotal de asistencias: " + actividad.numeroAsistentes(act_id) + "\n");
		}
	}
	public static void menu(){
		int option = 0;
		String choice = "";
		Usuario user = new Usuario();
		Cuarto room;
		Actividad activity = new Actividad();
		Transporte transport = new Transporte();
		
		Scanner scanned = new Scanner(System.in);
		System.out.println("Introduzca una opción a realizar\n\n-----USUARIOS-----\n1 Agregar usuario\n2 Ver usuarios\n3 Ordenar usuarios\n4 Editar usuarios\n5 Eliminar usuario\n6 Eliminar todos los usuarios\n7 Buscar usuario\n\n-----CUARTOS-----\n8 Crear cuarto\n9 Ver cuartos\n\n-----ACTIVIDADES-----\n10 Agregar actividad\n11 Ver actividades\n\n-----TRANSPORTES-----\n12 Agregar transporte\n13 Ver transportes\n\n-----ASISTENCIAS-----\n14 Registrar asistencia en actividad\n15 Registrar asistencia en transporte\n16 Ver asistencia en actividad \n17 Ver asistencia en transporte\n\n---------------------\n18 Cerrar programa");
		do{
			option = scanned.nextInt();
			switch(option){
				case 1:
					user.add();
					break;
				case 2:
					user.printTable();
					break;
				case 3:
					user.printOrdered();
					break;
				case 4:
					user.edit();
					break;
				case 5:
					user.deleteEntry();
					break;
				case 6:
					user.resetTable();
					break;
				case 7:
					user.search();
					break;
				case 8:
					room = createRoom();
					break;
				case 9:
					printAllUsers();
					break;
				case 10:
					addActivity();
					break;
				case 11:
					activity.printActividades();
					break;
				case 12:
					break;
				case 13:
					transport.printTransportes();
					break;
				case 14:
					break;
				case 15:
					break;
				case 16:
					printAsistenciaActividad();
					break;
				case 17:
					break;
				case 18:
					System.exit(0);
					break;
				default:
				System.out.println("Opción inválida, intente nuevamente");
				option = 0;
					break;
			}
		}while(option == 0);
		System.out.println("¿Desea continuar con el programa? Y/N");
		int k = 0;
		scanned.nextLine();// Clear buffer
		while(k == 0){
			choice = (scanned.nextLine()).toUpperCase();
			if(choice.equals("Y")){
				k = 1;
				menu();	
			} else if(choice.equals("N")){
				System.exit(0);
			} else {
				k = 0;
				System.out.println("Opción inválida, intente nuevamente");
			}
		}
	}
}