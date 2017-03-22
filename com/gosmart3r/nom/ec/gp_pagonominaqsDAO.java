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

public class gp_pagonominaqsDAO {
	// VARIABLES
	private DAConnectionSource connSource; // Acceso a sdic
	private DAConnection connData; // Acceso a datos

	public gp_pagonominaqsDAO(Session session) {
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public boolean InsertaPagoNominaQS(Session session, gp_pagonominaqs gp_pagonominaqs){
		boolean bRetorno=false;
		String sSql_insertapagonominaqs="";
		
		try {
			sSql_insertapagonominaqs="";
			sSql_insertapagonominaqs+=" INSERT INTO    "+connSource.translateTable("gp_pagonominaqs");
			sSql_insertapagonominaqs+=" ( ";
			sSql_insertapagonominaqs+=" xperiodo_id,xanio_id,xempresa_id,xentorno_id,xorganizacion_id, ";
			sSql_insertapagonominaqs+=" xrubro_id,xtipoorigen,xtiporubro,xempleado_id,xvalor, ";
			sSql_insertapagonominaqs+=" xdepartamento_id,xdepartamento_nom, ";
			sSql_insertapagonominaqs+=" xseccion_id,xseccion_nom,xcargo_id,xcargo_nom,xcentrocosto_id, ";
			sSql_insertapagonominaqs+=" xfechaingreso,xusuariogenera,xfechagenera,xorden,xcuentadebe,xcuentahaber,xfechafinal, ";
			sSql_insertapagonominaqs+=" xdespliegarol,xsaldoprestamos,xestadorubro,xpagaiess,xpagaimpuesto,xpagaprovision, ";
			sSql_insertapagonominaqs+=" xrubrobasico,xvalordevengado,xdespliegaliq,xcontadorliq, xquinsem , xtipoquinsem ";
			sSql_insertapagonominaqs+=" ) ";
			sSql_insertapagonominaqs+=" VALUES( ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXperiodo_id(), DA.DA_DT_INTEGER)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXanio_id(), DA.DA_DT_INTEGER)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXempresa_id(), DA.DA_DT_TEXT)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXentorno_id(), DA.DA_DT_TEXT)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXorganizacion_id(), DA.DA_DT_TEXT)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXrubro_id(), DA.DA_DT_TEXT)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXtipoorigen(), DA.DA_DT_TEXT)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXtiporubro(), DA.DA_DT_TEXT)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXempleado_id(), DA.DA_DT_TEXT)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXvalor(), DA.DA_DT_DOUBLE)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXdepartamento_id(), DA.DA_DT_TEXT)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXdepartamento_nom(), DA.DA_DT_TEXT)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXseccion_id(), DA.DA_DT_TEXT)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXseccion_nom(), DA.DA_DT_TEXT)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXcargo_id(), DA.DA_DT_TEXT)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXcargo_nom(), DA.DA_DT_TEXT)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXcentrocosto_id(), DA.DA_DT_TEXT)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXfechaingreso(), DA.DA_DT_DATE)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXusuariogenera(), DA.DA_DT_TEXT)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXfechagenera(), DA.DA_DT_DATE)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXorden(), DA.DA_DT_INTEGER)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXcuentadebe(), DA.DA_DT_TEXT)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXcuentahaber(), DA.DA_DT_TEXT)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXfechafinal(), DA.DA_DT_DATE)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.isXdespliegarol(), DA.DA_DT_BOOLEAN)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXsaldoprestamos(), DA.DA_DT_DOUBLE)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.isXestadorubro(), DA.DA_DT_BOOLEAN)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.isXpagaiess(), DA.DA_DT_BOOLEAN)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.isXpagaimpuesto(), DA.DA_DT_BOOLEAN)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.isXpagaprovision(), DA.DA_DT_BOOLEAN)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.isXrubrobasico(), DA.DA_DT_BOOLEAN)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXvalordevengado(), DA.DA_DT_DOUBLE)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.isXdespliegaliq(), DA.DA_DT_BOOLEAN)+", ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXcontadorliq(), DA.DA_DT_INTEGER)+",  ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXquinsem(), DA.DA_DT_INTEGER)+" ,  ";
			sSql_insertapagonominaqs+=" "+DAUtils.formatValue(gp_pagonominaqs.getXtipoquinsem(), DA.DA_DT_TEXT)+" ";
			sSql_insertapagonominaqs+=" ) ";
			if(connData.execSQL(sSql_insertapagonominaqs)>=0){bRetorno=true;}else{bRetorno=false;};
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bRetorno;
	}
	
	public boolean EliminaPagoNominaQS(Session session, String sEmpresa , String sOrganizacion , String sEntorno , int iAnio , int iPeriodo , int iQuinSem , String sQuinSem,
		String sRubro, String sOrigen, String sTiporubro, String sEmpleado){
		boolean bRetorno=false;
		String sSql_pagonominaqs="";
		
		try {
			sSql_pagonominaqs="";
			sSql_pagonominaqs+=" DELETE FROM    ";
			sSql_pagonominaqs+="   "+connSource.translateTable("gp_pagonominaqs");
			sSql_pagonominaqs+=" WHERE xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			sSql_pagonominaqs+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_pagonominaqs+=" AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_pagonominaqs+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_pagonominaqs+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			if(sRubro.length()>0){sSql_pagonominaqs+=" AND xrubro_id="+ DAUtils.formatValue(sRubro, DA.DA_DT_TEXT);}
			if(sOrigen.length()>0){sSql_pagonominaqs+=" AND xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);}
			if(sTiporubro.length()>0){sSql_pagonominaqs+=" AND xtiporubro="+ DAUtils.formatValue(sTiporubro, DA.DA_DT_TEXT);}
			if(sEmpleado.length()>0){sSql_pagonominaqs+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);}
			sSql_pagonominaqs+=" AND xquinsem="+ DAUtils.formatValue(iQuinSem, DA.DA_DT_INTEGER);
			sSql_pagonominaqs+=" AND xtipoquinsem="+ DAUtils.formatValue(sQuinSem, DA.DA_DT_TEXT);
			
			if(connData.execSQL(sSql_pagonominaqs)>=0){bRetorno=true;}else{bRetorno=false;};
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bRetorno;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<gp_pagonominaqs> ListadoPagoNominaQS(Session session, String sEmpresa , String sOrganizacion , String sEntorno , int iAnio , int iPeriodo , int iQuinSem , String sQuinSem,
			String sRubro, String sOrigen, String sTiporubro, String sEmpleado){
		DAResultSet rs=null;
		String sSql_pagonominaqs="";
		ArrayList ListadoPagoNominaQS = new ArrayList();
		gp_pagonominaqs gp_pagonominaqs;
		
		try {
			sSql_pagonominaqs="";
			sSql_pagonominaqs+=" SELECT   ";
			sSql_pagonominaqs+=" xperiodo_id,xanio_id,xempresa_id,xentorno_id,xorganizacion_id, ";
			sSql_pagonominaqs+=" xrubro_id,xtipoorigen,xtiporubro,xempleado_id,xvalor, ";
			sSql_pagonominaqs+=" xdepartamento_id,xdepartamento_nom, ";
			sSql_pagonominaqs+=" xseccion_id,xseccion_nom,xcargo_id,xcargo_nom,xcentrocosto_id, ";
			sSql_pagonominaqs+=" xfechaingreso,xusuariogenera,xfechagenera,xorden,xcuentadebe,xcuentahaber,xfechafinal, ";
			sSql_pagonominaqs+=" xdespliegarol,xsaldoprestamos,xestadorubro,xpagaiess,xpagaimpuesto,xpagaprovision, ";
			sSql_pagonominaqs+=" xrubrobasico,xvalordevengado,xdespliegaliq,xcontadorliq, xquinsem , xtipoquinsem ";
			sSql_pagonominaqs+=" FROM "+connSource.translateTable("gp_pagonominaqs");
			sSql_pagonominaqs+=" WHERE xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			sSql_pagonominaqs+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_pagonominaqs+=" AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_pagonominaqs+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_pagonominaqs+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			if(sRubro.length()>0){sSql_pagonominaqs+=" AND xrubro_id="+ DAUtils.formatValue(sRubro, DA.DA_DT_TEXT);}
			if(sOrigen.length()>0){sSql_pagonominaqs+=" AND xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);}
			if(sTiporubro.length()>0){sSql_pagonominaqs+=" AND xtiporubro="+ DAUtils.formatValue(sTiporubro, DA.DA_DT_TEXT);}
			if(sEmpleado.length()>0){sSql_pagonominaqs+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);}
			sSql_pagonominaqs+=" AND xquinsem="+ DAUtils.formatValue(iQuinSem, DA.DA_DT_INTEGER);
			sSql_pagonominaqs+=" AND xtipoquinsem="+ DAUtils.formatValue(sQuinSem, DA.DA_DT_TEXT);
			rs=connData.openSQL(sSql_pagonominaqs);
			while(rs.moveNext()){
				gp_pagonominaqs = new gp_pagonominaqs();
				gp_pagonominaqs.setXperiodo_id(rs.getInt("xperiodo_id"));
				gp_pagonominaqs.setXanio_id(rs.getInt("xanio_id"));
				gp_pagonominaqs.setXempresa_id(rs.getString("xempresa_id"));
				gp_pagonominaqs.setXentorno_id(rs.getString("xentorno_id"));
				gp_pagonominaqs.setXorganizacion_id(rs.getString("xorganizacion_id"));
				gp_pagonominaqs.setXrubro_id(rs.getString("xrubro_id"));
				gp_pagonominaqs.setXtipoorigen(rs.getString("xtipoorigen"));
				gp_pagonominaqs.setXtiporubro(rs.getString("xtiporubro"));
				gp_pagonominaqs.setXempleado_id(rs.getString("xempleado_id"));
				gp_pagonominaqs.setXvalor(rs.getDouble("xvalor"));
				gp_pagonominaqs.setXdepartamento_id(rs.getString("xdepartamento_id"));
				gp_pagonominaqs.setXdepartamento_nom(rs.getString("xdepartamento_nom"));
				gp_pagonominaqs.setXseccion_id(rs.getString("xseccion_id"));
				gp_pagonominaqs.setXseccion_nom(rs.getString("xseccion_nom"));
				gp_pagonominaqs.setXcargo_id(rs.getString("xcargo_id"));
				gp_pagonominaqs.setXcargo_nom(rs.getString("xcargo_nom"));
				gp_pagonominaqs.setXcentrocosto_id(rs.getString("xcentrocosto_id"));
				gp_pagonominaqs.setXfechaingreso(rs.getDate("xfechaingreso"));
				gp_pagonominaqs.setXusuariogenera(rs.getString("xusuariogenera"));
				gp_pagonominaqs.setXfechagenera(rs.getDate("xfechagenera"));
				gp_pagonominaqs.setXorden(rs.getInt("xorden"));
				gp_pagonominaqs.setXcuentadebe(rs.getString("xcuentadebe"));
				gp_pagonominaqs.setXcuentahaber(rs.getString("xcuentahaber"));
				gp_pagonominaqs.setXfechafinal(rs.getDate("xfechafinal"));
				gp_pagonominaqs.setXdespliegarol(rs.getBoolean("xdespliegarol"));
				gp_pagonominaqs.setXsaldoprestamos(rs.getDouble("xsaldoprestamos"));
				gp_pagonominaqs.setXestadorubro(rs.getBoolean("xestadorubro"));
				gp_pagonominaqs.setXpagaiess(rs.getBoolean("xpagaiess"));
				gp_pagonominaqs.setXpagaimpuesto(rs.getBoolean("xpagaimpuesto"));
				gp_pagonominaqs.setXpagaprovision(rs.getBoolean("xpagaprovision"));
				gp_pagonominaqs.setXrubrobasico(rs.getBoolean("xrubrobasico"));
				gp_pagonominaqs.setXvalordevengado(rs.getDouble("xvalordevengado"));
				gp_pagonominaqs.setXdespliegaliq(rs.getBoolean("xdespliegaliq"));
				gp_pagonominaqs.setXcontadorliq(rs.getInt("xcontadorliq"));
				gp_pagonominaqs.setXquinsem(rs.getInt("xquinsem"));
				gp_pagonominaqs.setXtipoquinsem(rs.getString("xtipoquinsem"));
				ListadoPagoNominaQS.add(gp_pagonominaqs);
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ListadoPagoNominaQS;
	}
}