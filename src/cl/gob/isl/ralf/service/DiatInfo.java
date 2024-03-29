package cl.gob.isl.ralf.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;




import cl.gob.isl.ralf.conn.MsSqlServerConexion;
import cl.gob.isl.ralf.conn.MySqlConexion;
import cl.gob.isl.ralf.model.Diat;

public class DiatInfo {
	
	private PreparedStatement ps = null;
	MsSqlServerConexion msSqlConn = new MsSqlServerConexion();
	MySqlConexion mySqlConn = new MySqlConexion();
	
	public 	DiatInfo(){
	}
	
	public String getXMLDiatSPM(String cun) throws SQLException {
		Diat diat = new Diat();
		ResultSet rs = null;		

		String sql = "select d.xml_enviado xml" 
					 +" from diat d"
					 +" join siniestro s on d.siniestro_id = s.id"
					 +" where s.cun is not null" 
					 +" and d.calificacion_denunciante_id = 7"
					 +" and s.cun = '"+cun+"'"
					 +" and d.gravedad id in (2,3)"
					 +" and d.estado_id = 10";
		
		System.out.println(sql);
		
		try{
			ps = msSqlConn.getConecction().prepareStatement(sql);
			rs = ps.executeQuery();
		} catch(SQLException sqle){
			System.out.println(sqle.getMessage());
			msSqlConn.cerrarConexion();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(rs.next()){
			diat.setXml(rs.getString("xml"));
		}
		else{
			diat.setXml(null);
		}
		return diat.getXml();
	}
	
	public String getXMLDiatSIAP(String cun) throws ClassNotFoundException, SQLException{
		Diat diat = new Diat();
		ResultSet rs = null;		

		String sql = " SELECT max(xs.xmlstring_id),xs.xmlstring "
					 + " FROM xmlstring xs "
					 + " JOIN xml x on xs.xmlstring_id = x.xmlstring_id "
					 + " JOIN caso c on x.caso_id = c.caso_id "
					 + " WHERE x.tpxml_id=1 and x.valido=1 and c.caso_cun= "+ Integer.parseInt(cun);
		try{
			ps = mySqlConn.getConecction().prepareStatement(sql);
			rs = ps.executeQuery();
		} catch(SQLException sqle){
			System.out.println(sqle.getMessage());
			sqle.printStackTrace();
			msSqlConn.cerrarConexion();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		if(rs.next()){
			diat.setXml(rs.getString("xs.xmlstring"));
		} else {
			diat.setXml(null);
		}
		return diat.getXml();
	}
	
	
	

}

