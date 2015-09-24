import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import org.h2.jdbcx.JdbcDataSource;

public class Usuario {
	// public static void main(String args[]) throws SQLException, IOException{


	// Declaracion de variables
	private String nombre, apellido, matricula, genero, correo, celular, puesto, alergias, comida, medicina;
	private boolean vegetariano;
	private int campus;

	public Connection connect(){
		// private int id;
		//Create DataSource
		JdbcDataSource ds = new JdbcDataSource();
		Connection c = null;
		try{
			ds.setURL("jdbc:h2:~/test");
			ds.setUser("sa");
			ds.setPassword("sa");
			c = ds.getConnection();
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return c;

		/*
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM carnero ORDER BY id DESC LIMIT 1");
		rs.next();
		id = rs.getInt(1);
		 */

	}

	// Statement stmt = conn.createStatement();
	// ResultSet rs = stmt.executeQuery("SELECT * FROM carnero ORDER BY id DESC LIMIT 1");
	// rs.next();
	// id = rs.getInt(1);

	public void add(){
		//Agregar persona
		Scanner scan = new Scanner(System.in);

		System.out.println("Introduzca el nombre del participante");
		nombre = scan.nextLine();
		System.out.println("Introduzca el apellido del participante");
		apellido = scan.nextLine();
		
		// Escape this
		String query = "INSERT INTO carnero(nombres, apellidos) VALUES('"+nombre+"','"+apellido+"')";

		Connection connection = this.connect();
		try{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Usuario agregado");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// System.out.println(query);
	}
	public void edit(){
		Scanner scan = new Scanner(System.in);
		String value; // Columna a editar
		String query = "";

		// Initiate DB connection
		Connection connection = this.connect();

		//Referenciar al usuario a editar
		System.out.println("Introduzca el ID de la persona a editar");
		int id = scan.nextInt();

		System.out.println("Introduzca el tipo de dato a editar \n 
			1 Datos personales \n 
			2 Datos médicos \n 
			3 Datos generales \n 
			4 Contactos de emergencia"
		);
		int choice = scan.nextInt();

		switch(choice){
			// Modificacion de datos personales
			case 1: 
				System.out.println("Introduzca el campo a editar  \n
					1 Nombres\n
					2 Apellidos \n
					3 Matrícula \n
					4 Género \n
					5 Fecha de nacimiento \n
					6 Correo electrónico \n
					7 Celular \n
					8 Campus \n
					9 Puesto"
				);
				choice = scan.nextInt();


				switch(choice){
					case 1:
						column = "NOMBRES";
						break;
					case 2:
						column = "APELLIDOS";
						break;
					case 3:
						column = "MATRICULA";
						break;
					case 4:
						column = "SEXO";
						break;
					case 5:
						// Date of birth
						column = "DOB";
					break;
					case 6:
						column = "CORREO";
					break;
					case 7:
						column = "CELULAR";
					break;
					case 8:
						column = "CAMPUS";
						String[] campus = {"Aguascalientes","Central de Veracruz","Chiapas","Chihuahua","Ciudad de México","Ciudad Juárez","Ciudad Obregón","Cuernavaca","Cumbres","Estado de México","Eugenio Garza Lagüera","Eugenio Garza Sada","Guadalajara","Hidalgo","Irapuato","Laguna","León","Morelia","Puebla","Querétaro","Saltillo","San Luis Potosí","Santa Catarina","Santa Fe","Sinaloa","Sonora Norte","Tampico","Toluca","Valle Alto","Zacatecas","Prep School El Paso","Sede Celaya","Sede Colima","Sede Esmeralda","Sede Matamoros","Sede Metepec","Sede Navojoa","Sede Santa Anita"};

						// Print campuses
						for (int i=0; i<campus.length(); i++)
							System.out.println(i+1 +" " + campus[i]);

						System.out.println("Introduzca el campus");
						scan.nextLine(); // Clear buffer
						int opcion = scan.nextInt();
						value = campus[opcion-1];
					break;
					case 9:
						column = "PUESTO";
					break;
					default:
				}
				if(choice != 8){				
					System.out.println("Introduzca el nuevo dato");
					scan.nextLine();
					value = scan.nextLine();
				}
				query = "UPDATE carnero SET "+ column + "='"+ value + "' WHERE ID=" + id;

			break;
			// Modificacion de datos medicos
			case 2:
				System.out.println("Introduzca el campo a editar \n
				 	1 Alergias \n
				 	2 Dietas especiales"
				);
				choice = scan.nextInt(); // Leer opcion
				switch(choice){
					// ALERGIAS
					case 1:
						column = "ALERGIAS";
						System.out.println("¿Alergias? Y/N");
						scan.nextLine(); // Clear buffer
						alergias = scan.nextLine();

						if(alergias.toUpperCase().equals("Y")){

							String column2 = "", newValue = "";
							do{
								System.out.println();
								System.out.println("Introduzca el campo a editar \n 1 Alergias a alimentos \n 2 Alergias a medicamentos");
								choice = scan.nextInt(); // Leer opcion


								// Alergias a alimentos
								if(choice == 1) 
									column2 = "COMIDA";
								// Alergias a medicamentos
								else if(choice == 2)
									column2 = "MEDICINA";
								else
									System.out.println("Opción inválida");

							}while(choice != 1 || choice != 2);


							System.out.println("Introduzca las alergias a " + column2.toLowerCase());
							scan.nextLine(); // Clear buffer

							newValue = scan.nextLine(); // Read line

							/*
								column = ALERGIAS
								alergias = Y/N
								column2 = COMIDA/MEDICINA

							*/
							query = "UPDATE carnero SET "+ column + "='"+ alergias +"', " + column2 + "='" + newValue + "' WHERE ID="+id;

						} 
						// Si no tiene alergias
						else if(alergias.toUpperCase().equals("N")){
							/*
								Incluir descripcion de justificacion de este metodo
							*/
							String value2 = "COMIDA";
							String value3 = "MEDICINA";
							query = "UPDATE carnero SET "+ column + "='"+ alergias +"', " + value2 + "= NULL, " + value3 + "= NULL WHERE ID=" + id;
						} 
						else{
							System.out.println("Opción inválida");
						}
					break;
					// DIETAS ESPECIALES
					case 2:
						break;
					default:
				}
			break;
			case 3:
				System.out.println("Introduzca el campo a editar \n 
					1 Vegetariano \n 
					2 Capacitaciones previas \n
					3 Talla de camiseta"
				);
				choice = scan.nextInt();
				switch(choice){

				}
			break;
			case 4:
				System.out.println("Introduzca el campo a editar \n 
					1 Contacto de emergencia \n 
					2 Parentesco \n 
					3 Teléfono de emergencia \n 
					4 Aseguradora \n 
					5 Póliza \n 
					6 Vencimiento"
				);
				choice = scan.nextInt();
				switch(choice){

				}
			break;
			default:
				System.out.println("Opción inválida");
		}
		try{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Dato editado exitosamente");
		} 
		catch (SQLException e) {
			System.out.print
			ln(e.getMessage());
		}
		//UPDATE carnero SET NAME=' ' WHERE ID=;
	}
	/*
//stmt.executeUpdate("UPDATE CARNERO SET NOMBRE='Héctor' WHERE ID=2");
//COUNT ROWS

//ResultSet rs= stmt.executeQuery("SELECT COUNT(*) FROM CARNERO");
//rs.next();
//int count = rs.getInt(1);

// END COUNT ROWS
// PRINT TABLE
	 */
	public void printTable(){
		// Print user info
		//add this.
		Connection connection = this.connect();
		Statement stmt;
		try{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM CARNERO ORDER BY ID ASC");
			while(rs.next()){
				int id = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);

				System.out.println(id + "\t" + nombre + "\t" + apellido);
			}
			rs.close();
			connection.close();
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}

	}
}