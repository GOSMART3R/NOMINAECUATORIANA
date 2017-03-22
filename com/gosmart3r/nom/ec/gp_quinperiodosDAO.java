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

public class gp_quinperiodosDAO {
	// VARIABLES
		private DAConnectionSource connSource; // Acceso a sdic
		private DAConnection connData; // Acceso a datos
		
		public gp_quinperiodosDAO(Session session) {
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public ArrayList<gp_quinperiodos> ListaQuincenasPeriodos(Session session , String sEmpresa, String sOrganizacion , String sEntorno,
				int iAnio , int iPeriodo, int iQuincena, Date dFecha){
			DAResultSet rs = null;
			String sSql_quinperiodos="";
			
			ArrayList ListaQuincenasPeriodos = new ArrayList();
			gp_quinperiodos gp_quinperiodos;
			
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				sSql_quinperiodos="";
				sSql_quinperiodos+=" SELECT  ";
				sSql_quinperiodos+=" xperiodo_id,xanio_id, ";
				sSql_quinperiodos+=" xentorno_id,xorganizacion_id, ";
				sSql_quinperiodos+=" xempresa_id,xquincena_id, ";
				sSql_quinperiodos+=" xfecha,xdias, ";
				sSql_quinperiodos+=" xsigladias,xmes, ";
				sSql_quinperiodos+=" xsiglames  ";
				sSql_quinperiodos+=" FROM  "+connSource.translateTable("gp_quinperiodos");
				sSql_quinperiodos+=" WHERE xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
				sSql_quinperiodos+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
				sSql_quinperiodos+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_quinperiodos+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_quinperiodos+=" AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_quinperiodos+=" AND xquincena_id="+ DAUtils.formatValue(iQuincena, DA.DA_DT_INTEGER);
				if(dFecha!=null){sSql_quinperiodos+=" AND xfecha="+ DAUtils.formatValue(dFecha, DA.DA_DT_DATE);}
				rs=connData.openSQL(sSql_quinperiodos);
				while(rs.moveNext()){
					gp_quinperiodos=new gp_quinperiodos();
					gp_quinperiodos.setXperiodo_id(rs.getInt("xperiodo_id"));
					gp_quinperiodos.setXanio_id(rs.getInt("xanio_id"));
					gp_quinperiodos.setXentorno_id(rs.getString("xentorno_id"));
					gp_quinperiodos.setXorganizacion_id(rs.getString("xorganizacion_id"));
					gp_quinperiodos.setXempresa_id(rs.getString("xempresa_id"));
					gp_quinperiodos.setXquincena_id(rs.getInt("xquincena_id"));
					gp_quinperiodos.setXfecha(rs.getDate("xfecha"));
					gp_quinperiodos.setXdias(rs.getString("xdias"));
					gp_quinperiodos.setXsigladias(rs.getString("xsigladias"));
					gp_quinperiodos.setXmes(rs.getString("xmes"));
					gp_quinperiodos.setXsiglames(rs.getString("xsiglames")); 
					ListaQuincenasPeriodos.add(gp_quinperiodos);
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ListaQuincenasPeriodos;
		}

}