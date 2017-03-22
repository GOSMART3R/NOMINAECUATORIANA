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



public class gp_emplnominasDAO {
	// VARIABLES
		private DAConnectionSource connSource; // Acceso a sdic
		private DAConnection connData; // Acceso a datos
		
		public gp_emplnominasDAO (Session session) {
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ArrayList<gp_emplnominas>  ListadoEmpleados(Session session, String sEmpresa, String sOrganizacion , String sEntorno , String sEmpleado , String sDepartamento, String sOrigen, String sCargo , Date  dFechaingreso , Date dFechaSalida , Date dFechafinperiodo){
			
			ArrayList ListadoEmpleados = new ArrayList();
			gp_emplnominas gp_emplnominas;
			
			
			DAResultSet rs = null;
			String sSql_empleados="";
			
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				sSql_empleados="";
				sSql_empleados+=" SELECT  ";
				sSql_empleados+=" xcargo_id,xdepartamento_id,xseccion_id, ";
				sSql_empleados+=" xfechainicial,xempresa_id,xorganizacion_id,xentorno_id, ";
				sSql_empleados+=" xempleado_id,xnombrecompleto,xcentrocosto_id,xestadocargo, ";
				sSql_empleados+=" xfechafinal,xsueldo,xfechaingreso, ";
				sSql_empleados+=" xcontrato_id,xtiposfondos, ";
				sSql_empleados+=" xadecimoc,xadecimot,xafondos, xtipoquinsem , xtiporegimen, ";
				sSql_empleados+=" xnombre , xnombre_sec , xdescripcion ";
				sSql_empleados+=" FROM  "+connSource.translateTable("gp_emplnominas");
				sSql_empleados+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_empleados+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_empleados+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				if(sDepartamento.length()>0){
					sSql_empleados+=" AND xdepartamento_id="+ DAUtils.formatValue(sDepartamento, DA.DA_DT_TEXT);
				}
				if(sEmpleado.length()>0){
					sSql_empleados+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
				}
				
				if(sOrigen.equals("R")){//ROLES DE PAGO NORMAL
					sSql_empleados+=" AND xfechafinal IS NULL";
					sSql_empleados+=" AND xfechaingreso <="+ DAUtils.formatValue(dFechafinperiodo, DA.DA_DT_DATE);
					
				}
				
				//EN EL CASO DE LIQUIDACION DEBEMOS TOMAR EN CUENTA UN UNICO REGISTRO DENTRO DE LOS CARGOS DE EMPLEADOS
				//EL PARAMETRO QUE HACE UNICO A UN PUESTO ES LA FECHA DE INGRESO Y EL CARGO
				if(sOrigen=="L" && dFechaingreso!=null && sCargo.length()>0 && sEmpleado.length()>0 && dFechaSalida!=null){
					sSql_empleados+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
					sSql_empleados+=" AND xfechaingreso="+ DAUtils.formatValue(dFechaingreso, DA.DA_DT_DATE);
					sSql_empleados+=" AND xcargo_id="+ DAUtils.formatValue(sCargo, DA.DA_DT_TEXT);
				}
				
				rs=connData.openSQL(sSql_empleados);
				while(rs.moveNext()){
					gp_emplnominas = new gp_emplnominas();
					gp_emplnominas.setXcargo_id(rs.getString("xcargo_id"));
					gp_emplnominas.setXdepartamento_id(rs.getString("xdepartamento_id"));
					gp_emplnominas.setXseccion_id(rs.getString("xseccion_id"));
					gp_emplnominas.setXfechainicial(rs.getDate("xfechainicial"));
					gp_emplnominas.setXempresa_id(rs.getString("xempresa_id"));
					gp_emplnominas.setXorganizacion_id(rs.getString("xorganizacion_id"));
					gp_emplnominas.setXentorno_id(rs.getString("xentorno_id"));
					gp_emplnominas.setXempleado_id(rs.getString("xempleado_id"));
					gp_emplnominas.setXnombrecompleto(rs.getString("xnombrecompleto"));
					gp_emplnominas.setXcentrocosto_id(rs.getString("xcentrocosto_id"));
					gp_emplnominas.setXestadocargo(rs.getString("xestadocargo"));
					gp_emplnominas.setXfechafinal(rs.getDate("xfechafinal"));
					gp_emplnominas.setXsueldo(rs.getDouble("xsueldo"));
					gp_emplnominas.setXfechaingreso(rs.getDate("xfechaingreso"));
					gp_emplnominas.setXcontrato_id(rs.getString("xcontrato_id"));
					gp_emplnominas.setXtiposfondos(rs.getString("xtiposfondos"));
					gp_emplnominas.setXadecimoc(rs.getInt("xadecimoc"));
					gp_emplnominas.setXadecimot(rs.getInt("xadecimot"));
					gp_emplnominas.setXafondos(rs.getInt("xafondos"));
					gp_emplnominas.setXtipoquinsem(rs.getString("xtipoquinsem"));
					gp_emplnominas.setXtiporegimen(rs.getString("xtiporegimen"));
					gp_emplnominas.setXnombre(rs.getString("xnombre"));//departamento_nom
					gp_emplnominas.setXnombre_sec(rs.getString("xnombre_sec"));//seccion nom
					gp_emplnominas.setXdescripcion(rs.getString("xdescripcion"));// cargo nom
					ListadoEmpleados.add(gp_emplnominas);
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ListadoEmpleados;
		}
		
}