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

public class gp_csemaperiodosDAO {
	// VARIABLES
		private DAConnectionSource connSource; // Acceso a sdic
		private DAConnection connData; // Acceso a datos
		
		public gp_csemaperiodosDAO(Session session) {
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ArrayList<gp_csemaperiodos> ListaCSemanaPeriodos(Session session, String sEmpresa , String sOrganizacion , String sEntorno , int iAnio , int iPeriodo,
				int iSemana , Date dDesde , Date dHasta){
			
			DAResultSet rs = null;
			String sSql_csemanaperiodos="";
			
			ArrayList ListaCSemanaPeriodos = new ArrayList();
			gp_csemaperiodos gp_csemaperiodos ;
			
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				sSql_csemanaperiodos="";
				sSql_csemanaperiodos+=" SELECT  ";
				sSql_csemanaperiodos+=" xperiodo_id,xanio_id,xentorno_id,xorganizacion_id, ";
				sSql_csemanaperiodos+=" xempresa_id,xsemana_id,xdesde,xhasta ";
				sSql_csemanaperiodos+=" FROM   "+connSource.translateTable("gp_csemaperiodos");
				sSql_csemanaperiodos+=" WHERE xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
				sSql_csemanaperiodos+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
				sSql_csemanaperiodos+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_csemanaperiodos+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_csemanaperiodos+=" AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_csemanaperiodos+=" AND xsemana_id="+ DAUtils.formatValue(iSemana, DA.DA_DT_INTEGER);
				if(dDesde!=null){sSql_csemanaperiodos+=" AND xdesde="+ DAUtils.formatValue(dDesde, DA.DA_DT_DATE);}
				if(dHasta!=null){sSql_csemanaperiodos+=" AND xhasta="+ DAUtils.formatValue(dHasta, DA.DA_DT_DATE);}
				rs=connData.openSQL(sSql_csemanaperiodos);
				while(rs.moveNext()){
					gp_csemaperiodos= new gp_csemaperiodos();
					gp_csemaperiodos.setXperiodo_id(rs.getInt("xperiodo_id"));
					gp_csemaperiodos.setXanio_id(rs.getInt("xanio_id"));
					gp_csemaperiodos.setXentorno_id(rs.getString("xentorno_id"));
					gp_csemaperiodos.setXorganizacion_id(rs.getString("xorganizacion_id"));
					gp_csemaperiodos.setXempresa_id(rs.getString("xempresa_id"));
					gp_csemaperiodos.setXsemana_id(rs.getInt("xsemana_id"));
					gp_csemaperiodos.setXdesde(rs.getDate("xdesde"));
					gp_csemaperiodos.setXhasta(rs.getDate("xhasta"));
					ListaCSemanaPeriodos.add(gp_csemaperiodos);
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ListaCSemanaPeriodos;
		}
}