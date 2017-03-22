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

public class gp_cquinperiodosDAO {
	// VARIABLES
		private DAConnectionSource connSource; // Acceso a sdic
		private DAConnection connData; // Acceso a datos
		
		public gp_cquinperiodosDAO(Session session) {
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ArrayList<gp_cquinperiodos> ListaQuincenas(Session session, String sEmpresa , String sOrganizacion , String sEntorno , int iAnio , int iPeriodo , int iQuincena , Date dDesde , Date dHasta){
			DAResultSet rs=null;
			String sSql_cquinperiodos="";
			ArrayList  ListaQuincenas = new ArrayList ();
			gp_cquinperiodos gp_cquinperiodos;
			
			try {
				sSql_cquinperiodos="";
				sSql_cquinperiodos+=" SELECT  ";
				sSql_cquinperiodos+=" xperiodo_id,xanio_id,xentorno_id,xorganizacion_id, ";
				sSql_cquinperiodos+=" xempresa_id,xquincena_id,xdesde,xhasta  ";
				sSql_cquinperiodos+=" FROM  "+connSource.translateTable("gp_cquinperiodos");
				sSql_cquinperiodos+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_cquinperiodos+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_cquinperiodos+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_cquinperiodos+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
				sSql_cquinperiodos+=" AND xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
				sSql_cquinperiodos+=" AND xquincena_id="+ DAUtils.formatValue(iQuincena, DA.DA_DT_INTEGER);
				if(dDesde!=null){sSql_cquinperiodos+=" AND xdesde="+ DAUtils.formatValue(dDesde, DA.DA_DT_DATE);}
				if(dHasta!=null){sSql_cquinperiodos+=" AND xhasta="+ DAUtils.formatValue(dHasta, DA.DA_DT_DATE);}
				
				rs=connData.openSQL(sSql_cquinperiodos);
				while(rs.moveNext()){
					gp_cquinperiodos = new gp_cquinperiodos();
					gp_cquinperiodos.setXperiodo_id(rs.getInt("xperiodo_id"));
					gp_cquinperiodos.setXanio_id(rs.getInt("xanio_id"));
					gp_cquinperiodos.setXentorno_id(rs.getString("xentorno_id"));
					gp_cquinperiodos.setXorganizacion_id(rs.getString("xorganizacion_id"));
					gp_cquinperiodos.setXempresa_id(rs.getString("xempresa_id"));
					gp_cquinperiodos.setXquincena_id(rs.getInt("xquincena_id"));
					gp_cquinperiodos.setXdesde(rs.getDate("xdesde"));
					gp_cquinperiodos.setXhasta(rs.getDate("xhasta")); 
					ListaQuincenas.add(gp_cquinperiodos);
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ListaQuincenas;
		}

		
}