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

public class gp_asistenquseDAO {
	// VARIABLES
		private DAConnectionSource connSource; // Acceso a sdic
		private DAConnection connData; // Acceso a datos
		
		public gp_asistenquseDAO(Session session) {
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		public boolean ActualizaAsistenciaQuSe(Session session , gp_asistenquse gp_asistenquse){
			boolean bRetorno=false;
			
			String sSql_actualizar="";
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				sSql_actualizar="";
				sSql_actualizar+=" UPDATE ";
				sSql_actualizar+="    "+connSource.translateTable("gp_asistenquse");
				sSql_actualizar+=" SET xhoras=  "+DAUtils.formatValue(gp_asistenquse.getXhoras(), DA.DA_DT_DOUBLE);
				sSql_actualizar+=" ,  xusuariogenera=  "+DAUtils.formatValue(gp_asistenquse.getXusuariogenera(), DA.DA_DT_TEXT);
				sSql_actualizar+=" ,  xfechagenera=  "+DAUtils.formatValue(gp_asistenquse.getXfechagenera(), DA.DA_DT_DATE);
				sSql_actualizar+=" WHERE xentorno_id="+ DAUtils.formatValue(gp_asistenquse.getXentorno_id(), DA.DA_DT_TEXT);
				sSql_actualizar+=" AND xorganizacion_id="+ DAUtils.formatValue(gp_asistenquse.getXorganizacion_id(), DA.DA_DT_TEXT);
				sSql_actualizar+=" AND xempresa_id="+ DAUtils.formatValue(gp_asistenquse.getXempresa_id(), DA.DA_DT_TEXT);
				sSql_actualizar+=" AND xanio_id="+ DAUtils.formatValue(gp_asistenquse.getXanio_id(), DA.DA_DT_INTEGER);
				sSql_actualizar+=" AND xperiodo_id="+ DAUtils.formatValue(gp_asistenquse.getXperiodo_id(), DA.DA_DT_INTEGER);
				sSql_actualizar+=" AND xempleado_id="+ DAUtils.formatValue(gp_asistenquse.getXempleado_id(), DA.DA_DT_TEXT); 
				sSql_actualizar+=" AND xtipoasistencia_id="+ DAUtils.formatValue(gp_asistenquse.getXtipoasistencia_id(), DA.DA_DT_TEXT); 
				sSql_actualizar+=" AND xtipoorigen="+ DAUtils.formatValue(gp_asistenquse.getXtipoorigen(), DA.DA_DT_TEXT);
				sSql_actualizar+=" AND xquinsem="+ DAUtils.formatValue(gp_asistenquse.getXquinsem(), DA.DA_DT_INTEGER);
				sSql_actualizar+=" AND xtipoquinsem="+ DAUtils.formatValue(gp_asistenquse.getXtipoquinsem(), DA.DA_DT_TEXT);
				sSql_actualizar+=" AND xfecha="+ DAUtils.formatValue(gp_asistenquse.getXfecha(), DA.DA_DT_TEXT);
				if(connData.execSQL(sSql_actualizar)>=0){bRetorno=true;}else{bRetorno=false;};
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return bRetorno;
		}
		
		public boolean InsertaAsistenciaQuSe(Session session , gp_asistenquse gp_asistenquse){
			boolean bRetorno=false;
			String sSql_insertaasitencia="";
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				sSql_insertaasitencia="";
				sSql_insertaasitencia+=" INSERT INTO "+connSource.translateTable("gp_asistenquse")+" ( ";
				sSql_insertaasitencia+=" xentorno_id,xorganizacion_id,xempresa_id,xanio_id, ";
				sSql_insertaasitencia+=" xperiodo_id,xempleado_id,xtipoasistencia_id,xtipoorigen, ";
				sSql_insertaasitencia+=" xquinsem,xtipoquinsem,xcargo_id,xcontadorliq, ";
				sSql_insertaasitencia+=" xdepartamento_id,xfechafinal,xfechagenera,xfechaingreso, ";
				sSql_insertaasitencia+=" xfechainicial,xhoras,xhorastrabajadas,xseccion_id, ";
				sSql_insertaasitencia+=" xusuariogenera,xfecha ";
				sSql_insertaasitencia+=" ) ";
				sSql_insertaasitencia+=" VALUES( ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXentorno_id(), DA.DA_DT_TEXT)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXorganizacion_id(), DA.DA_DT_TEXT)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXempresa_id(), DA.DA_DT_TEXT)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXanio_id(), DA.DA_DT_INTEGER)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXperiodo_id(), DA.DA_DT_INTEGER)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXempleado_id(), DA.DA_DT_TEXT)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXtipoasistencia_id(), DA.DA_DT_TEXT)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXtipoorigen(), DA.DA_DT_TEXT)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXquinsem(), DA.DA_DT_INTEGER)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXtipoquinsem(), DA.DA_DT_TEXT)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXcargo_id(), DA.DA_DT_TEXT)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXcontadorliq(), DA.DA_DT_INTEGER)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXdepartamento_id(), DA.DA_DT_TEXT)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXfechafinal(), DA.DA_DT_DATE)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXfechagenera(), DA.DA_DT_DATE)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXfechaingreso(), DA.DA_DT_DATE)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXfechainicial(), DA.DA_DT_DATE)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXhoras(), DA.DA_DT_DOUBLE)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXhorastrabajadas(), DA.DA_DT_DOUBLE)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXseccion_id(), DA.DA_DT_TEXT)+", ";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXusuariogenera(), DA.DA_DT_TEXT)+" ,";
				sSql_insertaasitencia+=" "+DAUtils.formatValue(gp_asistenquse.getXfecha(), DA.DA_DT_DATE)+" ";
				sSql_insertaasitencia+=" ) ";
				if(connData.execSQL(sSql_insertaasitencia)>=0){bRetorno=true;}else{bRetorno=false;};
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return bRetorno;
		}
		
		public boolean EliminaAsistenciaQuSe(Session session, String sEmpresa , String sOrganizacion , String sEntorno , int iAnio , int iPeriodo,
				String sEmpleado, String sTipoAsistencia , String sTipoOrigen, int iQuinSema, String sTipoQuinSem, Date dFecha){
			boolean bRetorno=false;
			String sSql_eliminaasitencia="";
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				sSql_eliminaasitencia=" ";
				sSql_eliminaasitencia+="DELETE ";
				sSql_eliminaasitencia+=" FROM  "+connSource.translateTable("gp_asistenquse");
				sSql_eliminaasitencia+=" WHERE xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_eliminaasitencia+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_eliminaasitencia+=" AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_eliminaasitencia+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
				sSql_eliminaasitencia+=" AND xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
				if(sEmpleado.length()>0){sSql_eliminaasitencia+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);}
				if(sTipoAsistencia.length()>0){sSql_eliminaasitencia+=" AND xtipoasistencia_id="+ DAUtils.formatValue(sTipoAsistencia, DA.DA_DT_TEXT);}
				sSql_eliminaasitencia+=" AND xtipoorigen="+ DAUtils.formatValue(sTipoOrigen, DA.DA_DT_TEXT);
				sSql_eliminaasitencia+=" AND xquinsem="+ DAUtils.formatValue(iQuinSema, DA.DA_DT_INTEGER);
				sSql_eliminaasitencia+=" AND xtipoquinsem="+ DAUtils.formatValue(sTipoQuinSem, DA.DA_DT_TEXT);
				if(dFecha!=null){sSql_eliminaasitencia+=" AND xfecha="+ DAUtils.formatValue(dFecha, DA.DA_DT_DATE);}
				if(connData.execSQL(sSql_eliminaasitencia)>=0){bRetorno=true;}else{bRetorno=false;};
				
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return bRetorno;
		}
		
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ArrayList<gp_asistenquse> ListadoAsistenciasQuSe(Session session, String sEmpresa , String sOrganizacion , String sEntorno , int iAnio , int iPeriodo,
				String sEmpleado, String sTipoAsistencia , String sTipoOrigen, int iQuinSema, String sTipoQuinSem, Date dFecha){
			
			DAResultSet rs = null;
			String sSql_asistenciasquse="";
			gp_asistenquse gp_asistenquse;
			
			ArrayList ListadoAsistenciasQuSe = new ArrayList();
			
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				sSql_asistenciasquse="";
				sSql_asistenciasquse+=" SELECT  ";
				sSql_asistenciasquse+=" xentorno_id,xorganizacion_id,xempresa_id,xanio_id, ";
				sSql_asistenciasquse+=" xperiodo_id,xempleado_id,xtipoasistencia_id,xtipoorigen, ";
				sSql_asistenciasquse+=" xquinsem,xtipoquinsem,xcargo_id,xcontadorliq, ";
				sSql_asistenciasquse+=" xdepartamento_id,xfechafinal,xfechagenera,xfechaingreso, ";
				sSql_asistenciasquse+=" xfechainicial,xhoras,xhorastrabajadas,xseccion_id, ";
				sSql_asistenciasquse+=" xusuariogenera , xfecha";
				sSql_asistenciasquse+=" FROM  "+connSource.translateTable("gp_asistenquse");
				sSql_asistenciasquse+=" WHERE xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_asistenciasquse+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_asistenciasquse+=" AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_asistenciasquse+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
				sSql_asistenciasquse+=" AND xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
				if(sEmpleado.length()>0){sSql_asistenciasquse+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);}
				if(sTipoAsistencia.length()>0){sSql_asistenciasquse+=" AND xtipoasistencia_id="+ DAUtils.formatValue(sTipoAsistencia, DA.DA_DT_TEXT);}
				sSql_asistenciasquse+=" AND xtipoorigen="+ DAUtils.formatValue(sTipoOrigen, DA.DA_DT_TEXT);
				sSql_asistenciasquse+=" AND xquinsem="+ DAUtils.formatValue(iQuinSema, DA.DA_DT_INTEGER);
				sSql_asistenciasquse+=" AND xtipoquinsem="+ DAUtils.formatValue(sTipoQuinSem, DA.DA_DT_TEXT);
				if(dFecha!=null){sSql_asistenciasquse+=" AND xfecha="+ DAUtils.formatValue(dFecha, DA.DA_DT_DATE);}
				rs=connData.openSQL(sSql_asistenciasquse);
				while(rs.moveNext()){
						gp_asistenquse = new gp_asistenquse();
						gp_asistenquse.setXentorno_id(rs.getString("xentorno_id"));
						gp_asistenquse.setXorganizacion_id(rs.getString("xorganizacion_id"));
						gp_asistenquse.setXempresa_id(rs.getString("xempresa_id"));
						gp_asistenquse.setXanio_id(rs.getInt("xanio_id"));
						gp_asistenquse.setXperiodo_id(rs.getInt("xperiodo_id"));
						gp_asistenquse.setXempleado_id(rs.getString("xempleado_id"));
						gp_asistenquse.setXtipoasistencia_id(rs.getString("xtipoasistencia_id"));
						gp_asistenquse.setXtipoorigen(rs.getString("xtipoorigen"));
						gp_asistenquse.setXquinsem(rs.getInt("xquinsem"));
						gp_asistenquse.setXtipoquinsem(rs.getString("xtipoquinsem"));
						gp_asistenquse.setXcargo_id(rs.getString("xcargo_id"));
						gp_asistenquse.setXcontadorliq(rs.getInt("xcontadorliq"));
						gp_asistenquse.setXdepartamento_id(rs.getString("xdepartamento_id"));
						gp_asistenquse.setXfechafinal(rs.getDate("xfechafinal"));
						gp_asistenquse.setXfechagenera(rs.getDate("xfechagenera"));
						gp_asistenquse.setXfechaingreso(rs.getDate("xfechaingreso"));
						gp_asistenquse.setXfechainicial(rs.getDate("xfechainicial"));
						gp_asistenquse.setXhoras(rs.getDouble("xhoras"));
						gp_asistenquse.setXhorastrabajadas(rs.getDouble("xhorastrabajadas"));
						gp_asistenquse.setXseccion_id(rs.getString("xseccion_id"));
						gp_asistenquse.setXusuariogenera(rs.getString("xusuariogenera"));
						gp_asistenquse.setXfecha(rs.getDate("xfecha"));
						ListadoAsistenciasQuSe.add(gp_asistenquse);
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ListadoAsistenciasQuSe;
		}

}