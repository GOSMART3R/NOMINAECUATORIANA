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

public class gp_rubrosempleadoDAO {
	// VARIABLES
		private DAConnectionSource connSource; // Acceso a sdic
		private DAConnection connData; // Acceso a datos
		
		public gp_rubrosempleadoDAO(Session session) {
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public ArrayList<gp_rubrosempleado> ListadoRubrosEmpleados(Session session, String sEmpresa, String sOrganizacion , String sEntorno, String sEmpleado){
			ArrayList ListadoRubrosEmpleados = new ArrayList();
			DAResultSet rs=null, rs_ficha=null;
			String sSql_rubrosempleado="", sSql_rubrosfichaempleado="";
			
			gp_rubrosempleado gp_rubrosempleado;
			
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				sSql_rubrosempleado="";
				sSql_rubrosempleado+=" SELECT rubrosempleado.xrubro_id as  xrubro, SUM(rubrosempleado.xvalor)  as xvalor , rubrosempleado.xorden as xorden , rubrosempleado.xtiporubro as xtiporubro , rubrosempleado.xdespliegarol as xdespliegarol , rubrosempleado.xestado as xestado, rubrosempleado.xpagaiess as xpagaiess , rubrosempleado.xpagaimpuesto as xpagaimpuesto, rubrosempleado.xpagaprovision as xpagaprovision ,rubrosempleado.xrubrobasico as xrubrobasico , rubrosempleado.xdespliegaliq as xdespliegaliq, rubrosempleado.xformula as  xformula, rubrosempleado.xformulaqui as xformulaqui , rubrosempleado.xformulasem as xformulasem ,rubrosempleado.xtiformularubro as xtiformularubro , rubrosempleado.xempresa_id , rubrosempleado.xorganizacion_id, rubrosempleado.xentorno_id";
				sSql_rubrosempleado+=" FROM(  ";
				sSql_rubrosempleado+=" SELECT gp_rubrosempleado.xrubro_id , gp_rubrosempleado.xvalor ,gp_rubros.xorden , gp_rubros.xtiporubro, gp_rubros.xdespliegarol, gp_rubros.xestado,gp_rubros.xpagaiess,gp_rubros.xpagaimpuesto,gp_rubros.xpagaprovision,gp_rubros.xrubrobasico , gp_rubros.xdespliegaliq , gp_rubros.xformula , gp_rubros.xformulaqui , gp_rubros.xformulasem ,gp_rubros.xtiformularubro , gp_rubros.xempresa_id , gp_rubros.xorganizacion_id, gp_rubros.xentorno_id";
				sSql_rubrosempleado+=" FROM   ";
				sSql_rubrosempleado+="  "+connSource.translateTable("gp_rubrosempleado")+", ";
				sSql_rubrosempleado+="  "+connSource.translateTable("gp_rubros");
				sSql_rubrosempleado+=" WHERE gp_rubrosempleado.xrubro_id = gp_rubros.xrubro_id   ";
				sSql_rubrosempleado+=" AND gp_rubrosempleado.xempresa_id=gp_rubros.xempresa_id   ";
				sSql_rubrosempleado+=" AND gp_rubrosempleado.xorganizacion_id=gp_rubros.xorganizacion_id  ";
				sSql_rubrosempleado+=" AND gp_rubrosempleado.xentorno_id=gp_rubros.xentorno_id  ";
				sSql_rubrosempleado+=" AND gp_rubrosempleado.xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" AND gp_rubrosempleado.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" AND gp_rubrosempleado.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" AND gp_rubrosempleado.xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" UNION  ";
				sSql_rubrosempleado+=" SELECT gp_parametros.xrubroimpuestorent,0.00 AS xvalor ,gp_rubros.xorden , gp_rubros.xtiporubro , gp_rubros.xdespliegarol , gp_rubros.xestado,gp_rubros.xpagaiess,gp_rubros.xpagaimpuesto,gp_rubros.xpagaprovision,gp_rubros.xrubrobasico , gp_rubros.xdespliegaliq , gp_rubros.xformula , gp_rubros.xformulaqui , gp_rubros.xformulasem ,gp_rubros.xtiformularubro, gp_rubros.xempresa_id , gp_rubros.xorganizacion_id, gp_rubros.xentorno_id";
				sSql_rubrosempleado+=" FROM  "+connSource.translateTable("gp_parametros")+", ";
				sSql_rubrosempleado+="  "+connSource.translateTable("gp_rubros");
				sSql_rubrosempleado+=" WHERE gp_parametros.xempresa_id = gp_rubros.xempresa_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xorganizacion_id=gp_rubros.xorganizacion_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xentorno_id=gp_rubros.xentorno_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xrubroimpuestorent=gp_rubros.xrubro_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xempresa_id ="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" AND gp_parametros.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" AND gp_parametros.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" UNION  ";
				sSql_rubrosempleado+=" SELECT gp_parametros.xrubrofondosreserv,0.00 AS xvalor ,gp_rubros.xorden , gp_rubros.xtiporubro , gp_rubros.xdespliegarol, gp_rubros.xestado,gp_rubros.xpagaiess,gp_rubros.xpagaimpuesto,gp_rubros.xpagaprovision,gp_rubros.xrubrobasico , gp_rubros.xdespliegaliq , gp_rubros.xformula , gp_rubros.xformulaqui , gp_rubros.xformulasem ,gp_rubros.xtiformularubro, gp_rubros.xempresa_id , gp_rubros.xorganizacion_id, gp_rubros.xentorno_id "; 
				sSql_rubrosempleado+=" FROM  "+connSource.translateTable("gp_parametros")+", ";
				sSql_rubrosempleado+="  "+connSource.translateTable("gp_rubros");
				sSql_rubrosempleado+=" WHERE gp_parametros.xempresa_id = gp_rubros.xempresa_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xorganizacion_id=gp_rubros.xorganizacion_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xentorno_id=gp_rubros.xentorno_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xrubrofondosreserv=gp_rubros.xrubro_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xempresa_id ="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" AND gp_parametros.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" AND gp_parametros.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" UNION  ";
				sSql_rubrosempleado+=" SELECT gp_parametros.xrubroretjudicial,0.00 AS xvalor ,gp_rubros.xorden , gp_rubros.xtiporubro , gp_rubros.xdespliegarol, gp_rubros.xestado,gp_rubros.xpagaiess,gp_rubros.xpagaimpuesto,gp_rubros.xpagaprovision,gp_rubros.xrubrobasico , gp_rubros.xdespliegaliq , gp_rubros.xformula , gp_rubros.xformulaqui , gp_rubros.xformulasem ,gp_rubros.xtiformularubro, gp_rubros.xempresa_id , gp_rubros.xorganizacion_id, gp_rubros.xentorno_id";
				sSql_rubrosempleado+=" FROM  "+connSource.translateTable("gp_parametros")+", ";
				sSql_rubrosempleado+="  "+connSource.translateTable("gp_rubros");
				sSql_rubrosempleado+=" WHERE gp_parametros.xempresa_id = gp_rubros.xempresa_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xorganizacion_id=gp_rubros.xorganizacion_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xentorno_id=gp_rubros.xentorno_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xrubroretjudicial=gp_rubros.xrubro_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xempresa_id ="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" AND gp_parametros.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" AND gp_parametros.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" UNION  ";
				sSql_rubrosempleado+=" SELECT gp_parametros.xrubrodesahucio,0.00 AS xvalor ,gp_rubros.xorden , gp_rubros.xtiporubro , gp_rubros.xdespliegarol, gp_rubros.xestado,gp_rubros.xpagaiess,gp_rubros.xpagaimpuesto,gp_rubros.xpagaprovision,gp_rubros.xrubrobasico, gp_rubros.xdespliegaliq , gp_rubros.xformula , gp_rubros.xformulaqui , gp_rubros.xformulasem ,gp_rubros.xtiformularubro , gp_rubros.xempresa_id , gp_rubros.xorganizacion_id, gp_rubros.xentorno_id"; 
				sSql_rubrosempleado+=" FROM  "+connSource.translateTable("gp_parametros")+", ";
				sSql_rubrosempleado+="  "+connSource.translateTable("gp_rubros");
				sSql_rubrosempleado+=" WHERE gp_parametros.xempresa_id = gp_rubros.xempresa_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xorganizacion_id=gp_rubros.xorganizacion_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xentorno_id=gp_rubros.xentorno_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xrubrodesahucio=gp_rubros.xrubro_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xempresa_id ="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" AND gp_parametros.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" AND gp_parametros.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" UNION  ";
				sSql_rubrosempleado+=" SELECT gp_parametros.xrubrodespido,0.00 AS xvalor ,gp_rubros.xorden , gp_rubros.xtiporubro , gp_rubros.xdespliegarol, gp_rubros.xestado,gp_rubros.xpagaiess,gp_rubros.xpagaimpuesto,gp_rubros.xpagaprovision,gp_rubros.xrubrobasico, gp_rubros.xdespliegaliq , gp_rubros.xformula , gp_rubros.xformulaqui , gp_rubros.xformulasem ,gp_rubros.xtiformularubro, gp_rubros.xempresa_id , gp_rubros.xorganizacion_id, gp_rubros.xentorno_id "; 
				sSql_rubrosempleado+=" FROM  "+connSource.translateTable("gp_parametros")+", ";
				sSql_rubrosempleado+="  "+connSource.translateTable("gp_rubros");
				sSql_rubrosempleado+=" WHERE gp_parametros.xempresa_id = gp_rubros.xempresa_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xorganizacion_id=gp_rubros.xorganizacion_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xentorno_id=gp_rubros.xentorno_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xrubrodespido=gp_rubros.xrubro_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xempresa_id ="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" AND gp_parametros.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" AND gp_parametros.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" UNION  ";
				sSql_rubrosempleado+=" SELECT gp_parametros.xrubroembarazo,0.00 AS xvalor ,gp_rubros.xorden , gp_rubros.xtiporubro , gp_rubros.xdespliegarol, gp_rubros.xestado,gp_rubros.xpagaiess,gp_rubros.xpagaimpuesto,gp_rubros.xpagaprovision,gp_rubros.xrubrobasico , gp_rubros.xdespliegaliq , gp_rubros.xformula , gp_rubros.xformulaqui , gp_rubros.xformulasem ,gp_rubros.xtiformularubro , gp_rubros.xempresa_id , gp_rubros.xorganizacion_id, gp_rubros.xentorno_id"; 
				sSql_rubrosempleado+=" FROM  "+connSource.translateTable("gp_parametros")+", ";
				sSql_rubrosempleado+="  "+connSource.translateTable("gp_rubros");
				sSql_rubrosempleado+=" WHERE gp_parametros.xempresa_id = gp_rubros.xempresa_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xorganizacion_id=gp_rubros.xorganizacion_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xentorno_id=gp_rubros.xentorno_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xrubroembarazo=gp_rubros.xrubro_id  ";
				sSql_rubrosempleado+=" AND gp_parametros.xempresa_id ="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" AND gp_parametros.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" AND gp_parametros.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_rubrosempleado+=" )rubrosempleado  ";
				sSql_rubrosempleado+=" GROUP BY rubrosempleado.xorden,   rubrosempleado.xrubro_id , rubrosempleado.xtiporubro , rubrosempleado.xdespliegarol ,rubrosempleado.xestado,rubrosempleado.xpagaiess,rubrosempleado.xpagaimpuesto,rubrosempleado.xpagaprovision,rubrosempleado.xrubrobasico,rubrosempleado.xdespliegaliq ,  rubrosempleado.xformula , rubrosempleado.xformulaqui  , rubrosempleado.xformulasem  ,rubrosempleado.xtiformularubro, rubrosempleado.xempresa_id , rubrosempleado.xorganizacion_id, rubrosempleado.xentorno_id";
				sSql_rubrosempleado+=" ORDER BY rubrosempleado.xorden  ";
				rs=connData.openSQL(sSql_rubrosempleado);
				while(rs.moveNext()){
					
					gp_rubrosempleado = new gp_rubrosempleado();
					
					sSql_rubrosfichaempleado="";
					sSql_rubrosfichaempleado+=" SELECT xvalorquincenal , xvalorsemanal , xvalor  ";
					sSql_rubrosfichaempleado+=" FROM "+connSource.translateTable("gp_rubrosempleado");
					sSql_rubrosfichaempleado+=" WHERE xempresa_id= "+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
					sSql_rubrosfichaempleado+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
					sSql_rubrosfichaempleado+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
					sSql_rubrosfichaempleado+=" AND xrubro_id="+ DAUtils.formatValue(rs.getString("xrubro"), DA.DA_DT_TEXT);
					sSql_rubrosfichaempleado+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
					sSql_rubrosfichaempleado+=" AND xgenera="+ DAUtils.formatValue(true, DA.DA_DT_BOOLEAN);
					rs_ficha=connData.openSQL(sSql_rubrosfichaempleado);
					if(rs_ficha.moveNext()){
						gp_rubrosempleado.setXvalorquincenalficha(rs_ficha.getDouble("xvalorquincenal"));
						gp_rubrosempleado.setXvalorsemanalficha(rs_ficha.getDouble("xvalorsemanal"));
						gp_rubrosempleado.setXvalormensualficha(rs_ficha.getDouble("xvalor"));
					}
					rs_ficha.close();
					
					
					
					gp_rubrosempleado.setXrubro(rs.getString("xrubro"));
					gp_rubrosempleado.setXvalor(rs.getDouble("xvalor"));
					gp_rubrosempleado.setXorden(rs.getInt("xorden"));
					gp_rubrosempleado.setXtiporubro(rs.getString("xtiporubro"));
					gp_rubrosempleado.setXdespliegarol(rs.getBoolean("xdespliegarol"));
					gp_rubrosempleado.setXestado(rs.getBoolean("xestado"));
					gp_rubrosempleado.setXpagaiess(rs.getBoolean("xpagaiess"));
					gp_rubrosempleado.setXpagaimpuesto(rs.getBoolean("xpagaimpuesto"));
					gp_rubrosempleado.setXpagaprovision(rs.getBoolean("xpagaprovision"));
					gp_rubrosempleado.setXrubrobasico(rs.getBoolean("xrubrobasico"));
					gp_rubrosempleado.setXdespliegaliq(rs.getBoolean("xdespliegaliq"));
					gp_rubrosempleado.setXformula(rs.getString("xformula"));
					gp_rubrosempleado.setXformulaqui(rs.getString("xformulaqui"));
					gp_rubrosempleado.setXformulasem(rs.getString("xformulasem"));
					gp_rubrosempleado.setXtiformularubro(rs.getString("xtiformularubro"));

					gp_rubrosempleado.setXempresa(rs.getString("xempresa_id"));
					gp_rubrosempleado.setXorganizacion(rs.getString("xorganizacion_id"));
					gp_rubrosempleado.setXentorno(rs.getString("xentorno_id"));
					ListadoRubrosEmpleados.add(gp_rubrosempleado);
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return ListadoRubrosEmpleados;
		}

				
}