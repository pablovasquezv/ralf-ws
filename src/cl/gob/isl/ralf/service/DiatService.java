package cl.gob.isl.ralf.service;

import java.sql.SQLException;

import cl.gob.isl.ralf.model.DiatResponse;

public class DiatService {
	
	//private static final Logger log = LoggerFactory.getLogger(DiatService.class);
	enum Comment {OK,ERROR};
	int retorno;
	String xml;
	
	public DiatResponse makeResponse(String cun){
		String xml;	
		DiatResponse dr = new DiatResponse();
		DiatInfo di = new DiatInfo();
		try{
			if(di.getXMLDiatSIAP(cun)!=null){
				xml = di.getXMLDiatSIAP(cun);
				System.out.println("dato de spm");
			} else if (di.getXMLDiatSPM(cun)!=null) {
				xml = di.getXMLDiatSPM(cun);
				System.out.println("dato de siap");
			} else {
				xml=null;
				System.out.println("NO SE ENCONTRO UNA DENUNCIA OA EN LAS FUENTES DE DATOS");
			}
					
			if(xml!=null){
				dr.setComment(Comment.OK.toString());
				dr.setRetorno("0");
				dr.setXml(xml);
			} else {
				dr.setComment(Comment.ERROR.toString());
				dr.setRetorno("1");
				dr.setXml(null);
			}
		} catch (SQLException sqle){
			sqle.getStackTrace();
		} catch (ClassNotFoundException cnfe){
			cnfe.getStackTrace();
		}
		return dr;
	}	
//	
//	public static void main (String arg[]){
//		DiatService ds = new DiatService();
//		System.out.println(ds.makeResponse("1976587").toString());
//	}
}
