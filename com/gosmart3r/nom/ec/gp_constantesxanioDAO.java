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

public class gp_constantesxanioDAO {
	// VARIABLES
		private DAConnectionSource connSource; // Acceso a sdic
		private DAConnection connData; // Acceso a datos
		
		public gp_constantesxanioDAO(Session session) {
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ArrayList<gp_constantesxanio> ListaConstantes(Session session, String sEmpresa , String sOrganizacion , String sEntorno, String sConstante , int iAnio ){
			DAResultSet rs = null;
			String sSql_constantesxanio="";
			ArrayList ListaConstantes = new ArrayList();
			gp_constantesxanio gp_constantesxanio;
			
			try {
				sSql_constantesxanio="";
				sSql_constantesxanio+=" SELECT  ";
				sSql_constantesxanio+=" xconstante_id,xentorno_id, ";
				sSql_constantesxanio+=" xorganizacion_id,xempresa_id, ";
				sSql_constantesxanio+=" xanio_id,xvalor  ";
				sSql_constantesxanio+=" FROM  "+connSource.translateTable("gp_constantesxanio");
				sSql_constantesxanio+=" WHERE xconstante_id="+ DAUtils.formatValue(sConstante, DA.DA_DT_TEXT);
				sSql_constantesxanio+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_constantesxanio+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_constantesxanio+=" AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_constantesxanio+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
				rs= connData.openSQL(sSql_constantesxanio);
				while(rs.moveNext()){
					gp_constantesxanio= new gp_constantesxanio();
					gp_constantesxanio.setXconstante_id(rs.getString("xconstante_id"));
					gp_constantesxanio.setXentorno_id(rs.getString("xentorno_id"));
					gp_constantesxanio.setXorganizacion_id(rs.getString("xorganizacion_id"));
					gp_constantesxanio.setXempresa_id(rs.getString("xempresa_id"));
					gp_constantesxanio.setXanio_id(rs.getInt("xanio_id"));
					gp_constantesxanio.setXvalor(rs.getDouble("xvalor"));
					ListaConstantes.add(gp_constantesxanio);
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ListaConstantes;
		}
		
}