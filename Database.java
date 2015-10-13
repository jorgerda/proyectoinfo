import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import org.h2.jdbcx.JdbcDataSource;

public class Database{
	public static Connection connect(){
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
	}
}