import java.util.Scanner;

public class Main {
	public static void main(String args[]){
		menu();
		//user.add();
	}
	public void add(){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Introduzca el nombre de la actividad");
		nombre = scan.nextLine();
		
		System.out.println("Introduzca la fecha de inicio (YYYY-MM-DD HH:mm)");
		fechaInicio = scan.nextLine();
				
		System.out.println("Introduzca la fecha de conclusión (YYYY-MM-DD HH:mm)");
		fechaFin = scan.nextLine();
				
		System.out.println("Introduzca la sede de la actividad");
		sede = scan.nextLine();
		
		// Pedir quien es el encargado		
	}
	public static void menu(){
		int option = 0;
		Usuario user = new Usuario();
		Scanner scanned = new Scanner(System.in);
		System.out.println("Introduzca una opción a realizar\n1 Agregar usuarios\n2 Ver usuarios\n3 Editar usuarios \n4 Eliminar usuario \n5 Eliminar todos los usuarios \n6 Buscar usuario \n7 Cerrar el programa");
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
				user.edit();
				break;
			case 4:
				user.deleteEntry();
				break;
			case 5:
				user.resetTable();
				break;
			case 6:
				user.search();
				break;
			case 7:
				System.exit(0);
				break;
			default:
			System.out.println("Opción inválida, intente nuevamente");
			option = 0;
				break;
			}
		}while(option == 0);
		menu();
	}
}