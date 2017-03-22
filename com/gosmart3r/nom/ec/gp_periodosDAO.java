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

public class gp_periodosDAO {
	// VARIABLES
		private DAConnectionSource connSource; // Acceso a sdic
		private DAConnection connData; // Acceso a datos
		
		
		public gp_periodosDAO(Session session) {
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ArrayList<gp_periodos> ListadoPeriodos(Session session, String sEmpresa , String sOrganizacion , String sEntorno , int iAnio, int iPeriodo){
			DAResultSet rs = null;
			String sSql_periodos = "";
			gp_periodos gp_periodos;
			
			ArrayList ListadoPeriodos = new ArrayList();
			
			try {
				sSql_periodos = "";
				sSql_periodos += " SELECT  ";
				sSql_periodos += " xperiodo_id,xanio_id,xentorno_id,xorganizacion_id, ";
				sSql_periodos += " xempresa_id,xperiodo,xdesde,xhasta,xdias, ";
				sSql_periodos += " xhorastrabajadas,xperiodo_des ";
				sSql_periodos += " FROM  "	+ connSource.translateTable("gp_periodos");
				sSql_periodos += " WHERE xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
				sSql_periodos += " AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
				sSql_periodos += " AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_periodos += " AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_periodos += " AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				rs=connData.openSQL(sSql_periodos);
				while(rs.moveNext()){
					gp_periodos= new gp_periodos();
					gp_periodos.setXperiodo_id(rs.getInt("xperiodo_id"));
					gp_periodos.setXanio_id(rs.getInt("xanio_id"));
					gp_periodos.setXentorno_id(rs.getString("xentorno_id"));
					gp_periodos.setXorganizacion_id(rs.getString("xorganizacion_id"));
					gp_periodos.setXempresa_id(rs.getString("xempresa_id"));
					gp_periodos.setXperiodo(rs.getInt("xperiodo"));
					gp_periodos.setXdesde(rs.getDate("xdesde"));
					gp_periodos.setXhasta(rs.getDate("xhasta"));
					gp_periodos.setXdias(rs.getInt("xdias"));
					gp_periodos.setXhorastrabajadas(rs.getDouble("xhorastrabajadas"));
					gp_periodos.setXperiodo_des(rs.getString("xperiodo_des"));
					ListadoPeriodos.add(gp_periodos);
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ListadoPeriodos;
			
		}

}