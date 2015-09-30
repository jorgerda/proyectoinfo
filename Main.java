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
		System.out.println("Introduzca una opción a realizar\n1 Agregar usuarios\n2 Ver usuarios\n3 Editar usuarios \n4 Eliminar usuario \n5 Eliminar todos los usuarios \n6 Cerrar el programa");
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