package com.gosmart3r.nom.ec;

import java.util.ArrayList;
import java.util.Date;

import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAException;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.session.Session;

public class gp_semaperiodosDAO {
	// VARIABLES
	private DAConnectionSource connSource; // Acceso a sdic
	private DAConnection connData; // Acceso a datos

	public gp_semaperiodosDAO(Session session) {
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<gp_semaperiodos> ListaSemanaPeriodos(Session session, String sEmpresa , String sOrganizacion , String sEntorno,
			int iAnio , int iPeriodo, int iSemana , Date dFecha){
		DAResultSet rs=null;
		String sSql_semanaperiodos="";
		gp_semaperiodos gp_semaperiodos;
		ArrayList ListaSemanaPeriodos = new ArrayList ();
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			sSql_semanaperiodos="";
			sSql_semanaperiodos+=" SELECT  ";
			sSql_semanaperiodos+=" xperiodo_id,xanio_id,xentorno_id,xorganizacion_id, ";
			sSql_semanaperiodos+=" xempresa_id,xsemana_id,xfecha,xdias, ";
			sSql_semanaperiodos+=" xsigladias,xmes,xsiglames  ";
			sSql_semanaperiodos+=" FROM  "+connSource.translateTable("gp_semaperiodos");
			sSql_semanaperiodos+=" WHERE xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			sSql_semanaperiodos+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_semanaperiodos+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_semanaperiodos+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_semanaperiodos+=" AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_semanaperiodos+=" AND xsemana_id="+ DAUtils.formatValue(iSemana, DA.DA_DT_INTEGER);
			if(dFecha!=null){sSql_semanaperiodos+=" AND xfecha="+ DAUtils.formatValue(dFecha, DA.DA_DT_DATE);};
			rs=connData.openSQL(sSql_semanaperiodos);
			while(rs.moveNext()){
				gp_semaperiodos = new gp_semaperiodos();
				gp_semaperiodos.setXperiodo_id(rs.getInt("xperiodo_id"));
				gp_semaperiodos.setXanio_id(rs.getInt("xanio_id"));
				gp_semaperiodos.setXentorno_id(rs.getString("xentorno_id"));
				gp_semaperiodos.setXorganizacion_id(rs.getString("xorganizacion_id"));
				gp_semaperiodos.setXempresa_id(rs.getString("xempresa_id"));
				gp_semaperiodos.setXsemana_id(rs.getInt("xsemana_id"));
				gp_semaperiodos.setXfecha(rs.getDate("xfecha"));
				gp_semaperiodos.setXdias(rs.getString("xdias"));
				gp_semaperiodos.setXsigladias(rs.getString("xsigladias"));
				gp_semaperiodos.setXmes(rs.getString("xmes"));
				gp_semaperiodos.setXsiglames(rs.getString("xsiglames"));
				ListaSemanaPeriodos.add(gp_semaperiodos);
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ListaSemanaPeriodos;
	}
}