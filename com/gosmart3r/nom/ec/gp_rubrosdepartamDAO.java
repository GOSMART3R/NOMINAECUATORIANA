package com.gosmart3r.nom.ec;

import java.util.ArrayList;

import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAException;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.session.Session;

public class gp_rubrosdepartamDAO {
	// VARIABLES
	private DAConnectionSource connSource; // Acceso a sdic
	private DAConnection connData; // Acceso a datos

	public gp_rubrosdepartamDAO(Session session) {
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<gp_rubrosdepartam> ListadoRubrosDeparta(Session session , String sEmpresa , String sOrganizacion , String sEntorno,
			String sRubro , String sDepartamento){
		DAResultSet rs=null;
		String sSql_rubrosdepartam="";
		ArrayList ListadoRubrosDeparta= new ArrayList();
		gp_rubrosdepartam gp_rubrosdepartam;
		
		try {
			sSql_rubrosdepartam="";
			sSql_rubrosdepartam+=" SELECT  ";
			sSql_rubrosdepartam+=" xrubro_id,xentorno_id,xorganizacion_id,xempresa_id, ";
			sSql_rubrosdepartam+=" xdepartamento_id,xvalor,xcuentadebe,xcuentahaber  ";
			sSql_rubrosdepartam+=" FROM "+connSource.translateTable("gp_rubrosdepartam");
			sSql_rubrosdepartam+=" WHERE xempresa_id= "+DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT);
			sSql_rubrosdepartam+=" AND xorganizacion_id= "+DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT);
			sSql_rubrosdepartam+=" AND xentorno_id= "+DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT);
			if(sRubro.length()>0){sSql_rubrosdepartam+=" AND xrubro_id= "+DAUtils.formatValue(sRubro,DA.DA_DT_TEXT);}
			if(sDepartamento.length()>0){sSql_rubrosdepartam+=" AND xdepartamento_id= "+DAUtils.formatValue(sDepartamento,DA.DA_DT_TEXT);}
					
			rs=connData.openSQL(sSql_rubrosdepartam);
			while(rs.moveNext()){
				gp_rubrosdepartam= new gp_rubrosdepartam();
				gp_rubrosdepartam.setXrubro_id(rs.getString("xrubro_id"));
				gp_rubrosdepartam.setXentorno_id(rs.getString("xentorno_id"));
				gp_rubrosdepartam.setXorganizacion_id(rs.getString("xorganizacion_id"));
				gp_rubrosdepartam.setXempresa_id(rs.getString("xempresa_id"));
				gp_rubrosdepartam.setXdepartamento_id(rs.getString("xdepartamento_id"));
				gp_rubrosdepartam.setXvalor(rs.getDouble("xvalor"));
				gp_rubrosdepartam.setXcuentadebe(rs.getString("xcuentadebe"));
				gp_rubrosdepartam.setXcuentahaber(rs.getString("xcuentahaber"));
				ListadoRubrosDeparta.add(gp_rubrosdepartam);
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ListadoRubrosDeparta;
	}

}