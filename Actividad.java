import java.util.Scanner;

public class Actividad extends Usuario{
	private String nombre, fechainicio, horainicio, fechafin, horafin, sede, encargado;
	
	public void add(){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Introduzca el nombre de la actividad");
		nombre = scan.nextLine();
		
		System.out.println("Introduzca la fecha de inicio (YYYY-MM-DD)");
		fechainicio = scan.nextLine();
		
		System.out.println("Introduzca la hora de inicio (HH:MM)");
		horainicio = scan.nextLine();
		
		System.out.println("Introduzca la fecha de conclusión (YYYY-MM-DD)");
		fechafin = scan.nextLine();
		
		System.out.println("Introduzca la hora de conclusión (HH:MM)");
		horafin = scan.nextLine();
		
		System.out.println("Introduzca la sede de la actividad");
		sede = scan.nextLine();
		
		System.out.println("Introduzca el encargado de la actividad");
		encargado = scan.nextLine();
		
	}
}
