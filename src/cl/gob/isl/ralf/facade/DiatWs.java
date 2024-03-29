package cl.gob.isl.ralf.facade;

//import javax.annotation.security.DeclareRoles;
import javax.jws.WebMethod;
import javax.jws.WebService;

import cl.gob.isl.ralf.model.DiatResponse;
import cl.gob.isl.ralf.service.*;

@WebService()
public class DiatWs {

	@WebMethod()
	public String getXmlDiatOA(String cun){
		
		DiatService diatService = new DiatService();
		DiatResponse dr = diatService.makeResponse(cun);
		String json = diatWrapper(dr);
		
		
		return json;
	}
	
	private String diatWrapper(DiatResponse dr){
		
		String retorno = dr.getRetorno();		
		String comentario = dr.getComment();
		String xml = dr.getXml();
		
		
		String json = "{\"return\":\"" + retorno  + "\"," 
		             +"\"comment\":\"" + comentario+ "\","
				     +"\"xml\":\"" + xml + "\"}";
		
		System.out.println(dr.getRetorno());
		
		return json;
	}
	

	
}
