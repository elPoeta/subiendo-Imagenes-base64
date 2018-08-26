package conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	 private static DB INSTANCE = null;
     private static String LABASE = "jdbc:hsqldb:file:~/dbimg/dbimg";
     private static String LABASEUSUARIO = "SA"; 
     private static String LABASECLAVE = "";   

	
	public static DB getInstance() throws ClassNotFoundException, IOException, SQLException {
         if (INSTANCE == null) {
             INSTANCE = new DB();
         }
         return INSTANCE;
     }
     private DB() throws ClassNotFoundException,
             IOException, SQLException {
     }

     public Connection getConnection() throws ClassNotFoundException,
             IOException, SQLException {
         Class.forName("org.hsqldb.jdbc.JDBCDriver");
         return DriverManager.getConnection(LABASE, LABASEUSUARIO, LABASECLAVE);
     }
}
