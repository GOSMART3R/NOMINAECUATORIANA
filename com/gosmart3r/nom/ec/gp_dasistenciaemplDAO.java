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

public class gp_dasistenciaemplDAO {
	// VARIABLES
	private DAConnectionSource connSource; // Acceso a sdic
	private DAConnection connData; // Acceso a datos

	public gp_dasistenciaemplDAO(Session session) {
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<gp_dasistenciaempl> ListadoDAsistenciaEmpl(Session session, String sEmpresa , String sOrganizacion , String sEntorno , int iAnio, int iPeriodo, String sEmpleado,
			String sTipoOrigen, Date dFecha){
		DAResultSet rs=null;
		String sSql_dasistenciaempl="";
		
		ArrayList ListadoDAsistenciaEmpl= new ArrayList();
		
		gp_dasistenciaempl gp_dasistenciaempl;
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			sSql_dasistenciaempl="";
			sSql_dasistenciaempl+=" SELECT  ";
			sSql_dasistenciaempl+=" xempresa_id,xorganizacion_id,xentorno_id,xempleado_id,xanio_id,xperiodo_id, ";
			sSql_dasistenciaempl+=" xtipoasistencia_id,xtipoorigen,xdasistenciaemp_id,xfecha,xhoras,xminutos, ";
			sSql_dasistenciaempl+=" xtotalhoras,xobservaciones,xfechagenera,xusuariogenera ";
			sSql_dasistenciaempl+=" FROM  "+connSource.translateTable("gp_dasistenciaempl");
			sSql_dasistenciaempl+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_dasistenciaempl+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_dasistenciaempl+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_dasistenciaempl+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_dasistenciaempl+=" AND xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			if(sEmpleado.length()>0){sSql_dasistenciaempl+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);}
			if(sTipoOrigen.length()>0){sSql_dasistenciaempl+=" AND xtipoorigen="+ DAUtils.formatValue(sTipoOrigen, DA.DA_DT_TEXT);}
			if(dFecha!=null){sSql_dasistenciaempl+=" AND xfecha="+ DAUtils.formatValue(dFecha, DA.DA_DT_DATE);}
			rs=connData.openSQL(sSql_dasistenciaempl);
			while(rs.moveNext()){
					gp_dasistenciaempl=new gp_dasistenciaempl();
					gp_dasistenciaempl.setXempresa_id(rs.getString("xempresa_id"));
					gp_dasistenciaempl.setXorganizacion_id(rs.getString("xorganizacion_id"));
					gp_dasistenciaempl.setXentorno_id(rs.getString("xentorno_id"));
					gp_dasistenciaempl.setXempleado_id(rs.getString("xempleado_id"));
					gp_dasistenciaempl.setXanio_id(rs.getInt("xanio_id"));
					gp_dasistenciaempl.setXperiodo_id(rs.getInt("xperiodo_id"));
					gp_dasistenciaempl.setXtipoasistencia_id(rs.getString("xtipoasistencia_id"));
					gp_dasistenciaempl.setXtipoorigen(rs.getString("xtipoorigen"));
					gp_dasistenciaempl.setXdasistenciaemp_id(rs.getInt("xdasistenciaemp_id"));
					gp_dasistenciaempl.setXfecha(rs.getDate("xfecha"));
					gp_dasistenciaempl.setXhoras(rs.getInt("xhoras"));
					gp_dasistenciaempl.setXminutos(rs.getInt("xminutos"));
					gp_dasistenciaempl.setXtotalhoras(rs.getDouble("xtotalhoras"));
					gp_dasistenciaempl.setXobservaciones(rs.getString("xobservaciones"));
					gp_dasistenciaempl.setXfechagenera(rs.getDate("xfechagenera"));
					gp_dasistenciaempl.setXusuariogenera(rs.getString("xusuariogenera"));
					ListadoDAsistenciaEmpl.add(gp_dasistenciaempl);
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ListadoDAsistenciaEmpl;
	}
}