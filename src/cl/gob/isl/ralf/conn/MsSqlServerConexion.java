package cl.gob.isl.ralf.conn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class MsSqlServerConexion {
	
//	private static final Logger log =  LoggerFactory.getLogger(MsSqlServerConexion.class);
	private String servidor;
	private String usuario;
	private String contrasenia;
	private String baseDatos;
	private String puerto;
	Connection conn = null;
	
	public MsSqlServerConexion(){
		servidor = "172.16.7.20";
		usuario = "spmprod_ro";
		contrasenia = "743qZBFVYp";
		baseDatos = "spmprod";
		puerto="1433";
	}
	
	
	public Connection getConecction() throws ClassNotFoundException{
		
	   try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://"+servidor+":"+puerto+";databaseName="+baseDatos+";user="+usuario+";password="+contrasenia+";";
//			System.out.println(url);
			conn = (Connection) DriverManager.getConnection(url);
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());	
		}
				
		return conn;
	}
	
	public void cerrarConexion(){
		
		try{
			if(conn != null){
				conn.close();
				conn = null;
			}
		} catch (SQLException sqle){
			System.out.println(sqle.getMessage());
		}
	}
}