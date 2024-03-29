package cl.gob.isl.ralf.conn;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;



public class MySqlConexion {

	private String servidor;
	private String usuario;
	private String contrasenia;
	private String baseDatos;
   Connection conn = null;
	
	public MySqlConexion() {
		servidor = "172.16.6.48";
		usuario = "siap";
	    contrasenia = "siap_isl";
		baseDatos = "siap_isl_binary_prod";
		conn = null;
		
	}
	
	public Connection getConecction() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		   try{
			    Class.forName("com.mysql.jdbc.Driver"); 
				String url = "jdbc:mysql://"+servidor+":3306/"+baseDatos;
				conn = DriverManager.getConnection(url, usuario, contrasenia);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
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
