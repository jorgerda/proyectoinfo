import java.util.Scanner;

public class Main {
	public static void main(String args[]){
		menu();
		//user.add();
	}
	public static void menu(){
		int option = 0;
		Usuario user = new Usuario();
		Scanner scanned = new Scanner(System.in);
		System.out.println("Introduzca una opción a realizar\n1 Agregar usuarios\n2 Ver usuarios\n3 Editar usuarios\n4 Cerrar el programa");
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
			System.exit(0);
		default:
			break;
		}
		menu();
	}
}