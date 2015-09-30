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
	private String nombre, apellido, matricula, genero, correo, celular, puesto, alergias, food, value, medicina, dieta, vegetariano, capacitado, camiseta;
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
		String[] inputs = {"Nombres","Apellidos","Matrícula","Puesto","Género","Campus","Fecha de nacimiento","Correo electrónico","Celular","Alergias","Alergias a alimentos","Alergias a medicamentos","Vegetariano","Dieta especial","Capacitado previamente","Talla de camiseta","Nombre del contacto de emergencia","Parentesco","Teléfono de emergencia","Aseguradora","Póliza","Vencimiento"};
		String[] columnNames = {"nombres","apellidos","matricula","puesto","sexo","campus","dob","correo","celular","alergias","comida","medicina","vegetariano","dieta","capacitado","camiseta","contactoemergencia","parentesco","telemergencia","aseguradora","poliza","vence"};
		String[] puestos = {"Participante","Staff","Instructor","Coordinador","Coordinador DAE"};
		String[] parentescos = {"Padre","Madre","Herman@","Abuel@","Tutor"};
		String[] campus = {"Aguascalientes","Central de Veracruz","Chiapas","Chihuahua","Ciudad de México","Ciudad Juárez","Ciudad Obregón","Cuernavaca","Cumbres","Estado de México","Eugenio Garza Lagüera","Eugenio Garza Sada","Guadalajara","Hidalgo","Irapuato","Laguna","León","Morelia","Puebla","Querétaro","Saltillo","San Luis Potosí","Santa Catarina","Santa Fe","Sinaloa","Sonora Norte","Tampico","Toluca","Valle Alto","Zacatecas","Prep School El Paso","Sede Celaya","Sede Colima","Sede Esmeralda","Sede Matamoros","Sede Metepec","Sede Navojoa","Sede Santa Anita"};
		String value = "", query = "", temp="";
		int choice;

		System.out.println("Introduzca los siguientes datos");
		int i = 0;

		// Pedir y registrar nombre, apellido y matrícula
		do{
			System.out.println(inputs[i]+":");
			value = scan.nextLine();
			temp = "'" + value + "',";
			query = query + temp;
			i++;
		}while(i<=2);

		// Pedir puesto
		System.out.println(inputs[3] + ": \n1 Participante\n2 Staff\n3 Instructor\n4 Coordinador\n5 Coordinador DAE");
		do{
			choice = scan.nextInt();
			
			if(choice<1 || choice>5){
				System.out.println("Opción inválida, intente nuevamente");
				choice = 0;
			}
		}while(choice == 0);
		// Registrar puesto
		value=puestos[choice-1];
		
		temp = "'" + value + "',";
		query = query + temp;

		// Pedir y regsitrar género
		int j = 0;
		System.out.println(inputs[4] + ": (H/M)");
		scan.nextLine();//Clear buffer

		do{
			value = scan.nextLine();

			if((value.toUpperCase()).equals("H")){
				j = 1;
				temp = "'" + value.toUpperCase() + "',";
				query = query + temp;

			} else if((value.toUpperCase()).equals("M")){
				j = 1;
				temp = "'" + value.toUpperCase() + "',";
				query = query + temp;
			} else{
				j = 0;
				System.out.println("Opción inválida, intente nuevamente");
			}
		}while(j == 0);

		// Pedir y registrar campus
		j=0;
		int digit = 0;
		System.out.println(inputs[5]+":");
			for (int k=0; k<campus.length; k++)// Imprimir lista de campus
				System.out.println(k+1 +" " + campus[k]);

		do{
			digit = scan.nextInt();
		
			if(digit>=1 && digit<=38){
				value = campus[digit-1];
				temp = "'" + value + "',";
				query = query + temp;
				j = 1;
			} else{
				j = 0;
				System.out.println("Opción inválida, intente nuevamente");
			}
		}while(j == 0);

		// Pedir y registrar DOB
		System.out.println(inputs[6] + ": (YYYY-MM-DD)");
		scan.nextLine();//Clear buffer
		value = scan.nextLine();
		
		temp = "'" + value + "',";
		query = query + temp;

		// Pedir y registrar correo y celular
		//scan.nextLine();//Clear buffer
		i = 7;
		do{
			System.out.println(inputs[i]+":");
			value = scan.nextLine();
			temp = "'" + value + "',";
			query = query + temp;
			i++;
		}while(i<=8);

		// Pedir si existen alergias
		j = 0;
		System.out.println(inputs[9]+": (Y/N)");
		do{
			value = scan.nextLine();
			if((value.toUpperCase()).equals("Y")){
				j = 1;
				temp = "'" + value.toUpperCase() + "',";
				query = query + temp;
				
				// Verificiar si tiene alergias a alimentos
				int l = 0;
				System.out.println("Alergias a alimentos: (Y/N)");
				do{
					value = scan.nextLine();
					if((value.toUpperCase()).equals("Y")){
						l = 1;
						System.out.println("Alergias a alimentos:");
						value = scan.nextLine();
						temp = "'" + value + "',";
						query = query + temp;
					} else if((value.toUpperCase()).equals("N")){
						l = 1;
						value = "NULL";
						temp = value + ",";
						query = query + temp;
					} else{
						l = 0;
						System.out.println("Opción inválida, intente nuevamente");
					}
				}while(l == 0);
			
				// Verificar si tiene alergias a medicamentos
				l = 0;
				System.out.println("Alergias a medicamentos: (Y/N)");	
				do{
					value = scan.nextLine();
					if((value.toUpperCase()).equals("Y")){
						l = 1;
						System.out.println("Alergias a medicamentos:");
						value = scan.nextLine();
						temp = "'" + value + "',";
						query = query + temp;
					} else if((value.toUpperCase()).equals("N")){
						l = 1;
						value = "NULL";
						temp = value + ",";
						query = query + temp;
					} else{
						l = 0;
						System.out.println("Opción inválida, intente nuevamente");
					}
				}while(l == 0);
			}else if((value.toUpperCase()).equals("N")){
				j = 1;
				for(int o = 0; o<3; o++){
					value = "NULL";
						temp = value + ",";
						query = query + temp;
				}
			} else{
				j = 0;
				System.out.println("Opción inválida, intente nuevamente");
			}
		}while(j == 0);

		// Pedir si es vegetariano
		j = 0;
		System.out.println("Vegetariano: (Y/N)");

		do{
			value = scan.nextLine();
			if((value.toUpperCase()).equals("Y")){
				j = 1;
			} else if((value.toUpperCase()).equals("N")){
				j = 1;
			} else{
				j = 0;
				System.out.println("Opción inválida, intente nuevamente");
			}
		}while(j == 0);
		
		temp = "'" + value.toUpperCase() + "',";
		query = query + temp;

		// Pedir si tiene dieta especial
		j = 0;
		System.out.println("Dieta especial: (Y/N)");

		do{
			value = scan.nextLine();
			if((value.toUpperCase()).equals("Y")){
				j = 1;
				System.out.println("Dieta especial:");
				value = scan.nextLine();
				temp = "'" + value + "',";
				query = query + temp;
			} else if((value.toUpperCase()).equals("N")){
				j = 1;
				value = "NULL";
				temp = value + ",";
				query = query + temp;
			} else{
				j = 0;
				System.out.println("Opción inválida, intente nuevamente");
			}
		}while(j == 0);

		// Pedir si ha sido capacitado
		j = 0;
		System.out.println("Capacitado previamente: (Y/N)");

		do{
			value = scan.nextLine();
			if((value.toUpperCase()).equals("Y")){
				j = 1;
			} else if((value.toUpperCase()).equals("N")){
				j = 1;
			} else{
				j = 0;
				System.out.println("Opción inválida, intente nuevamente");
			}
		}while(j == 0);
		
		temp = "'" + value.toUpperCase() + "',";
		query = query + temp;

		// Pedir talla de camiseta
		System.out.println("Talla de camiseta: (S-M-L)");
	
		value = scan.nextLine();
	
		while(!((value.toUpperCase()).equals("S")) && !((value.toUpperCase()).equals("M")) && !((value.toUpperCase()).equals("L"))){
			System.out.println("Opción inválida, intente nuevamente");
			value = scan.nextLine();
		}
		
		temp = "'" + value.toUpperCase() + "',";
		query = query + temp;

		// Pedir contacto de emergencia
		System.out.println(inputs[16]+":");
		value = scan.nextLine();
		temp = "'" + value + "',";
		query = query + temp;

		// Pedir parentesco
		System.out.println(inputs[17]+": \n1 Padre \n2 Madre \n3 Herman@ \n4 Abuel@ \n5 Tutor");
		do{
			choice = scan.nextInt();
			
			if(choice<1 || choice>5){
				System.out.println("Opción inválida, intente nuevamente");
				choice = 0;
			}
		}while(choice == 0);
		// Registrar parentesco
		value = parentescos[choice-1];
		
		temp = "'" + value + "',";
		query = query + temp;

		// Pedir teléfono de emergencia
		scan.nextLine();//Clear buffer
		System.out.println(inputs[18]+":");
		value = scan.nextLine();
		temp = "'" + value + "',";
		query = query + temp;

		// Pedir aseguradora
		System.out.println(inputs[19]+":");
		value = scan.nextLine();
		temp = "'" + value + "',";
		query = query + temp;

		// Pedir póliza
		System.out.println(inputs[20]+":");
		value = scan.nextLine();
		temp = "'" + value + "',";
		query = query + temp;

		// Pedir vencimiento
		System.out.println(inputs[21] + ": (YYYY-MM-DD)");
		value = scan.nextLine();
		temp = "'" + value + "',";
		query = query + temp;
				
		
		// Escape this
		String columnary = "";
		temp = "";
		value = "";

		for(int m=0; m<inputs.length;m++){
			value = columnNames[m];
			temp = value + ",";
			columnary = columnary + temp;
		}
		
		String insert = "INSERT INTO carnero(" + columnary + ") VALUES(" + query + ")";

		Connection connection = this.connect();
		try{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(insert);
			System.out.println("Usuario agregado exitosamente");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void edit(){
		Scanner scan = new Scanner(System.in);
		String column; // Columna a editar
		String query = "";
		int choice;

		// Iniciar la conexión con la BD
		Connection connection = this.connect();

		// Referenciar al usuario a editar
		System.out.println("Introduzca el ID de la persona a editar");
		int id = scan.nextInt();

		System.out.println("Introduzca el tipo de dato a editar \n1 Datos personales \n2 Datos médicos \n3 Datos generales \n4 Datos de emergencia");

		do{
			choice = scan.nextInt();
			switch(choice){
			// Modificación de datos personales
				case 1: 
					System.out.println("Introduzca el campo a editar  \n1 Nombres\n2 Apellidos \n3 Matrícula \n4 Género \n5 Fecha de nacimiento \n6 Correo electrónico \n7 Celular \n8 Campus \n9 Puesto");
					column = ""; 

					do{
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
								// Fecha de nacimiento
								column = "DOB";
								System.out.println("Introduzca la nueva fecha en formato YYYY-MM-DD");
								scan.nextLine(); // Clear buffer
								value = scan.nextLine();
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

							// Imprimir lista de campus
							for (int i=0; i<campus.length; i++)
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
						System.out.println("Opción inválida, intente nuevamente");
						choice=0;
						}
					}while(choice == 0);
					
					if((choice != 8) && (choice != 5)){				
							System.out.println("Introduzca el nuevo dato");
							scan.nextLine();
							value = scan.nextLine();
					}
					query = "UPDATE carnero SET "+ column + "='"+ value + "' WHERE ID=" + id;

					break;
				// Modificación de datos médicos
				case 2:
					System.out.println("Introduzca el campo a editar \n1 Alergias \n2 Dietas especiales");
					choice = scan.nextInt(); // Leer opción
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
							query = "UPDATE carnero SET "+ column + "='"+ alergias.toUpperCase() +"', " + column2 + "='" + newValue + "' WHERE ID="+id;

						} 
						// Si no tiene alergias
						else if(alergias.toUpperCase().equals("N")){
							/*
									Incluir descripcion de justificacion de este metodo
							 */
							String value2 = "COMIDA";
							String value3 = "MEDICINA";
							query = "UPDATE carnero SET "+ column + "='"+ alergias.toUpperCase() +"', " + value2 + "= NULL, " + value3 + "= NULL WHERE ID=" + id;
						} 
						else{
							System.out.println("Opción inválida");
						}
						break;
						// DIETAS ESPECIALES
					case 2:
						column = "DIETA";
						System.out.println("¿Dieta especial? Y/N");
						scan.nextLine();//Clear buffer
						dieta = scan.nextLine();

						if((dieta.toUpperCase()).equals("Y")){
							System.out.println("Introduzca la dieta especial");
							dieta = scan.nextLine();
							query = "UPDATE carnero SET "+ column + "='"+ dieta + "' WHERE ID="+id;
						}

						//Si no tiene dieta especial
						else if((dieta.toUpperCase()).equals("N")){
							query = "UPDATE carnero SET "+ column + "= NULL WHERE ID="+id;
						}

						else{
							System.out.println("Opción inválida");
						}
						break;
					default:
					}

					break;
				// Modificación de datos generales
				case 3:
					System.out.println("Introduzca el campo a editar \n1 Vegetariano \n2 Capacitaciones previas \n3 Talla de camiseta");
					choice = scan.nextInt();
					column = "";
					switch(choice){
					case 1:
						column = "VEGETARIANO";
						System.out.println("¿Vegetariano? Y/N");
						scan.nextLine();//Clear buffer
						vegetariano = scan.nextLine();

						while(!((vegetariano.toUpperCase()).equals("Y")) && !((vegetariano.toUpperCase()).equals("N"))){//Repetir si no es Y o N{
							System.out.println("Opción inválida, intente nuevamente");
							vegetariano = scan.nextLine();
						}

						value = vegetariano.toUpperCase();
						break;

					case 2:
						column = "CAPACITADO";
						System.out.println("¿Capacitado previamente? Y/N");
						scan.nextLine();//Clear buffer
						capacitado = scan.nextLine();

						while(!((capacitado.toUpperCase()).equals("Y")) && !((capacitado.toUpperCase()).equals("N"))){//Repetir si no es Y o N
							System.out.println("Opción inválida, intente nuevamente");
							capacitado = scan.nextLine();
						}

						value = capacitado.toUpperCase();
						break;

					case 3:
						column = "CAMISETA";
						System.out.println("¿Talla de camiseta? S-M-L");
						scan.nextLine();//Clear buffer
						camiseta = scan.nextLine();

						while(!((camiseta.toUpperCase()).equals("S")) && !((camiseta.toUpperCase()).equals("M")) && !((camiseta.toUpperCase()).equals("L"))){//Repetir si no es S o M o L
							System.out.println("Opción inválida, intente nuevamente");
							camiseta = scan.nextLine();
						}

						value = camiseta.toUpperCase();
						break;
					}
					query = "UPDATE carnero SET "+ column + "='"+ value + "' WHERE ID=" + id;

					break;
				// Modificación de datos de emergencia
				case 4:
					column = "";
					System.out.println("Introduzca el campo a editar \n1 Contacto de emergencia \n2 Seguro de Gastos Médicos Mayores");
					do{
						choice = scan.nextInt();
						switch(choice){
							case 1:
								System.out.println("Introduzca el campo a editar \n1 Nombre del contacto \n2 Parentesco \n3 Teléfono de emergencia");
								scan.nextLine();//Clear buffer
								do{
									choice = scan.nextInt(); // Leer opción
									switch(choice){
										case 1:
											column = "CONTACTOEMERGENCIA";
											break;

										case 2:
											column = "PARENTESCO";
											break;

										case 3:
											column = "TELEMERGENCIA";
											break;

										default:
										System.out.println("Opción inválida, intente nuevamente");
										choice = 0;
									}
								}while(choice == 0);
								System.out.println("Introduzca el nuevo dato");
								scan.nextLine();
								value = scan.nextLine();
								query = "UPDATE carnero SET "+ column + "='"+ value + "' WHERE ID=" + id;
								break;

							case 2:
								System.out.println("Introduzca el campo a editar \n1 Aseguradora \n2 Póliza \n3 Vencimiento");
								do{
									choice = scan.nextInt(); // Leer opción
									switch(choice){
										case 1:
											column ="ASEGURADORA";
										break;

										case 2:
											column = "POLIZA";
										break;

										case 3:
											column = "VENCE";
											System.out.println("Introduzca la nueva fecha en formato YYYY-MM-DD");
											scan.nextLine(); // Clear buffer
											value = scan.nextLine();
										break;

										default:
										System.out.println("Opción inválida, intente nuevamente");
										choice = 0;
									}
								}while(choice ==0);
				
								if(choice != 3){				
									System.out.println("Introduzca el nuevo dato");
									scan.nextLine();
									value = scan.nextLine();
								}
								query = "UPDATE carnero SET "+ column + "='"+ value + "' WHERE ID=" + id;
								break;
							
							default:
							System.out.println("Opción inválida, intente nuevamente");
							choice = 0;
						}
					}while(choice == 0);
					break;
				default:
					System.out.println("Opción inválida, intente nuevamente");
					choice = 0;
			}
		}while(choice == 0);
		try{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Dato editado exitosamente \n");
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public void deleteEntry(){
		Scanner scan = new Scanner(System.in);
		int value;
		String choice = "", query = "";
		Connection connection = this.connect();
		Statement stmt;
		
		try{
			System.out.println("Introduzca el ID del usuario a eliminar");
			value = scan.nextInt();
			query = "SELECT nombres, apellidos FROM carnero WHERE ID=" + value;
						
			stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			// Comprobar si desea eliminar al usuario
			System.out.println("Desea eliminar al usuario: " + rs.getString(1) + " " + rs.getString(2) + " (Y/N)");
			scan.nextLine();//Clear buffer
			choice = scan.nextLine();

			if((choice.toUpperCase()).equals("Y")){
				query = "DELETE FROM carnero WHERE ID="+value;
				stmt.executeUpdate(query);
				System.out.println("Usuario eliminado exitosamente");
			} else {
				System.out.println("Cancelado");
			}
		rs.close();
		connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		
	}
}

	public void resetTable(){
		Scanner scan = new Scanner(System.in);
		String query = "";
		int value;
		
		Connection connection = this.connect();
		System.out.println("Introduzca el ID del usuario a eliminar");
		value = scan.nextInt();
		query = "'DELETE FROM carnero WHERE ID=" + value + "'";
		
		try{
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Tabla borrada exitosamente");
	//	rs.close();
		connection.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
/*
//COUNT ROWS

ResultSet rs= stmt.executeQuery("SELECT COUNT(*) FROM CARNERO");
rs.next();
int count = rs.getInt(1);
*/
	public void printTable(){
		// Print user info
		Connection connection = this.connect();
		Statement stmt;
		String[]  table = {"ID","Nombres","Apellidos","Matrícula","Puesto","Género","Campus","Fecha de nacimiento","Correo electrónico","Celular","Alergias","A. Alimentos","A. Medicamentos","Vegetariano","Dieta","Capacitación previa","Talla de camiseta","Contacto de emergencia","Parentesco","Teléfono de emergencia","Aseguradora","Póliza","Vencimiento"};

		try{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM CARNERO ORDER BY ID ASC");

			for(int i=0; i<table.length; i++){
				System.out.print(table[i]+"\t");
			}
			System.out.println();

			while(rs.next()){
				int id = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				String matricula = rs.getString(4);
				String puesto = rs.getString(5);
				String genero = rs.getString(6);
				String campus = rs.getString(7);
				String dob = rs.getString(8);
				String correo = rs.getString(9);
				String celular = rs.getString(10);
				String alergias = rs.getString(11);
				String comida = rs.getString(12);
				String medicina = rs.getString(13);
				String vegetariano = rs.getString(14);
				String dieta = rs.getString(15);
				String capacitacion = rs.getString(16);
				String camiseta = rs.getString(17);

				System.out.println(id + "\t" + nombre + "\t" + apellido + "\t" + matricula + "\t" + puesto + "\t" + genero +  "\t" + campus + "\t" + dob + "\t" + correo + "\t" + celular + "\t" + alergias + "\t" + comida + "\t" + medicina + "\t" + vegetariano + "\t" + dieta + "\t" + capacitacion + "\t" + camiseta);
			}
			rs.close();
			connection.close();
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
}