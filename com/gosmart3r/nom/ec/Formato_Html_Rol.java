package com.gosmart3r.nom.ec;

import java.sql.Date;

import com.unit4.karat.base.OTException;
import com.unit4.karat.base.util.Format;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAException;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.session.Session;

public class Formato_Html_Rol {
	//VARIABLES
	private DAConnectionSource connSource; // Acceso a sdic
	private DAConnection connData; // Acceso a datos
	/**
	 * 
	 */
	public Formato_Html_Rol() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String Formatonumero(String sNumero){
		String sValor="";
		try {
			sValor=sNumero;
			Format format = new Format() ;
			format.setFormatOptions("###,###,###,###,##0.00", Format.FOR_L_SPANISH);
			sValor = format.OTFormat(sValor, Format.Type.NUMERIC);
		} catch (OTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sValor;
	}
	
	//
	public String Formatoletras(String sNumero){
		String sValor="";
		try {
			sValor=sNumero;
			Format format = new Format() ;
			format.setFormatOptions("S|#MDólares |Dolar #2m con |centavos", Format.FOR_L_SPANISH);
			sValor = format.OTFormat(sValor, Format.Type.NUMERIC);
		} catch (OTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sValor;
	}
	
	public String Hojacss(){
		String sCss="";
		sCss="";
		sCss+=" <style type=\"text/css\"> ";
		sCss+=" <!-- ";
		sCss+=" .titulo { ";
		sCss+=" 	font-family: Tahoma; ";
		sCss+=" 	font-size: 14px; ";
		sCss+=" 	font-style: normal; ";
		sCss+=" 	font-weight: bold; ";
		sCss+=" 	font-variant: normal; ";
		sCss+=" 	color: #009900; ";
		sCss+=" } ";
		sCss+=" .parrafo { ";
		sCss+=" 	font-family: Tahoma; ";
		sCss+=" 	font-size: 12px; ";
		sCss+=" 	font-style: normal; ";
		sCss+=" 	font-weight: normal; ";
		sCss+=" font-variant: normal; ";
		sCss+=" 	color: #000000; ";
		sCss+=" } ";
		sCss+=" .datagrid table { border-collapse: collapse; text-align: left; width: 100%; } .datagrid {font: normal 12px/150% Arial, Helvetica, sans-serif; background: #fff; overflow: hidden; border: 1px solid #006699; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px; }.datagrid table td, .datagrid table th { padding: 3px 10px; }.datagrid table thead th {background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F) );background:-moz-linear-gradient( center top, #006699 5%, #00557F 100% );filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F');background-color:#006699; color:#FFFFFF; font-size: 15px; font-weight: bold; border-left: 1px solid #0070A8; } .datagrid table thead th:first-child { border: none; }.datagrid table tbody td { color: #00496B; border-left: 1px solid #E1EEF4;font-size: 12px;border-bottom: 1px solid #00496B;font-weight: normal; }.datagrid table tbody .alt td { background: #E1EEF4; color: #00496B; }.datagrid table tbody td:first-child { border-left: none; }.datagrid table tbody tr:last-child td { border-bottom: none; }";
		
		sCss+=" --> ";
		sCss+=" </style> ";
		return sCss;
	}

	
	//HOJA TOMADA DE LA PAGINA http://www.tablesgenerator.com/html_tables#
	public String Hojacssrolgeneral(){
		String sCss="";
		sCss="";
		sCss+=" <style type=\"text/css\"> "; 
		sCss+=" .titulo { ";
		sCss+=" 	font-family: Tahoma; ";
		sCss+=" 	font-size: 14px; ";
		sCss+=" 	font-style: normal; ";
		sCss+=" 	font-weight: bold; ";
		sCss+=" 	font-variant: normal; ";
		sCss+=" 	color: #009900; ";
		sCss+=" } ";
		sCss+=" .parrafo { ";
		sCss+=" 	font-family: Tahoma; ";
		sCss+=" 	font-size: 12px; ";
		sCss+=" 	font-style: normal; ";
		sCss+=" 	font-weight: normal; ";
		sCss+=" font-variant: normal; ";
		sCss+=" 	color: #000000; ";
		sCss+=" } ";
		sCss+=".tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}";
		sCss+=".tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}";
		sCss+=".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}";
		sCss+=".tg .tg-kl7f{font-size:11px;font-family:Arial, Helvetica, sans-serif !important;;vertical-align:top}";
		sCss+=".tg .tg-h2j0{font-weight:bold;font-size:11px;font-family:Arial, Helvetica, sans-serif !important;;vertical-align:top}";
		sCss+=".tg .tg-m3d7{font-size:10px;font-family:Arial, Helvetica, sans-serif !important;;color:#009901;vertical-align:top}";
		sCss+=" </style> ";
		
		//EJEMPLO
		//<table class="tg">
		//  <tr>
		//    <th class="tg-yw4l">cabecera</th>
		//  </tr>
		//	  <tr>
		//	<td class="tg">linea 1</td>
		//  </tr>
		//</table>
		
		return sCss;
	}
	
	
	//HTML PARA EL ROL INDIVIDUAL FRUIZ
	@SuppressWarnings("unused")
	public String RolHtml(Session session, Integer iAnio, Integer iPeriodo, String sEmpresa , String sOrganizacion , String sEntorno, String sEmpleado, String sOrigen){
		String sHtml="", sSql_roles="",sSql_rubro="", sGrid="", sRubro="", sValor="";
		Integer iContador=0;
		DAResultSet rs_roles = null, rs_rubro=null;
		String sXempleado_id,sXnombrecompleto ,sXrubro_in,sXrubro_de,sXdepartamento_id,sXdepartamento_nom;
		Double dXvalor_in, dXvalor_de, dTotal, dXsaldoprestamo;
		
		
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//REALIZAMOS LA CONSULTA A LA TABLA DE ROLES PARA ARMAR EL HTML
		try {
			sSql_roles=" ";
			sSql_roles+=" SELECT  ";
			sSql_roles+=" gp_empleados.xempleado_id,gp_empleados.xnombrecompleto , ";
			sSql_roles+=" gp_rolesemple.xrubro_in,gp_rolesemple.xvalor_in, ";
			sSql_roles+=" gp_rolesemple.xrubro_de, gp_rolesemple.xvalor_de, ";
			sSql_roles+=" gp_rolesemple.xdepartamento_id,gp_rolesemple.xdepartamento_nom ,";
			sSql_roles+=" gp_rolesemple.xsaldoprestamo ";
			sSql_roles+=" FROM  ";
			sSql_roles+="  "+connSource.translateTable("gp_rolesemple")+" , "; 
			sSql_roles+="  "+connSource.translateTable("gp_empleados");
			sSql_roles+=" WHERE  ";
			sSql_roles+=" gp_rolesemple.xempresa_id=gp_empleados.xempresa_id ";
			sSql_roles+=" AND gp_rolesemple.xorganizacion_id=gp_empleados.xorganizacion_id ";
			sSql_roles+=" AND gp_rolesemple.xentorno_id=gp_empleados.xentorno_id ";
			sSql_roles+=" AND gp_rolesemple.xempleado_id=gp_empleados.xempleado_id ";
			sSql_roles+=" AND gp_rolesemple.xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_roles+=" AND gp_rolesemple.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_roles+=" AND gp_rolesemple.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_roles+=" AND gp_rolesemple.xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_roles+=" AND gp_rolesemple.xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			sSql_roles+=" AND gp_rolesemple.xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			sSql_roles+=" AND gp_rolesemple.xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
			rs_roles=connData.openSQL(sSql_roles);
			while (rs_roles.moveNext()) {
				iContador+=1;
				
				sXempleado_id=rs_roles.getString("xempleado_id");
				sXnombrecompleto=rs_roles.getString("xnombrecompleto");
				sXrubro_in=rs_roles.getString("xrubro_in");
				sXrubro_de=rs_roles.getString("xrubro_de");
				sXdepartamento_id=rs_roles.getString("xdepartamento_id");
				sXdepartamento_nom=rs_roles.getString("xdepartamento_nom");
				dXvalor_in=rs_roles.getDouble("xvalor_in");
				dXvalor_de=rs_roles.getDouble("xvalor_de");
				dXsaldoprestamo=rs_roles.getDouble("xsaldoprestamo");
				if(dXvalor_de==null){dXvalor_de=0.00;}
				if(dXvalor_in==null){dXvalor_in=0.00;}
				if(dXsaldoprestamo==null){dXsaldoprestamo=0.00;}
				
				if(iContador==1){//CONDICION PARA TOMAR LOS DATOS DE LA CABECERA DEL HTML
					sHtml="";
					sHtml+=" <HTML> ";
					sHtml+=" <HEAD> ";
					sHtml+=" <TITLE>ROL DE PAGOS : "+sXnombrecompleto+"</TITLE> ";
					sHtml+=Hojacss();
					sHtml+=" </HEAD> ";
					sHtml+="";
					sHtml+=" <BODY> ";
					sHtml+="<div class=\"datagrid\">";
					sHtml+="<p class=\"titulo\">Estimad@, "+sXnombrecompleto+"</p>";
					sHtml+="<p class=\"parrafo\">Informamos que su rol de pagos para el periodo : "+iPeriodo+" Año : "+iAnio+", ha sido generado : </p>";
					sHtml+="<table> ";
					sHtml+="<thead> ";
					sHtml+="<tr> ";
					sHtml+="  <th>Ingresos</th> ";
					sHtml+="  <th>Valor</th> ";
					sHtml+="  <th>Descuentos</th> ";
					sHtml+="  <th>Valor</th> ";
					sHtml+="  <th>Saldo Préstamos</th> ";
					sHtml+="</tr> ";
					sHtml+="</thead> ";
					sHtml+="<tbody> ";
				}
				//GENERAMOS EL DETALLE
				if(iContador%2==0){sGrid="<tr class=\"alt\">";}else{sGrid="<tr>";}
				sHtml+=sGrid;
				if(sXrubro_in==null || sXrubro_in.length()==0){
					sHtml+="<td></td>";
					sHtml+="<td></td>";
				}else{
					sSql_rubro="";
					sSql_rubro+=" SELECT xdescripcion  ";
					sSql_rubro+=" FROM "+connSource.translateTable("gp_rubros");
					sSql_rubro+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
					sSql_rubro+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
					sSql_rubro+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
					sSql_rubro+=" AND xrubro_id="+ DAUtils.formatValue(sXrubro_in, DA.DA_DT_TEXT);
					rs_rubro=connData.openSQL(sSql_rubro);
					sRubro="";
					if(rs_rubro.moveNext()){
						sRubro=rs_rubro.getString("xdescripcion");
					}
					rs_rubro.close();
					sValor="";
					sValor=Formatonumero(dXvalor_in.toString());
					sHtml+="<td>"+sXrubro_in+" : "+sRubro+"</td>";
					sHtml+="<td align=\"right\">"+sValor+"</td>";
				}
				
				if(sXrubro_de==null || sXrubro_de.length()==0){
					sHtml+="<td></td>";
					sHtml+="<td></td>";
				}else{
					sSql_rubro="";
					sSql_rubro+=" SELECT xdescripcion  ";
					sSql_rubro+=" FROM "+connSource.translateTable("gp_rubros");
					sSql_rubro+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
					sSql_rubro+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
					sSql_rubro+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
					sSql_rubro+=" AND xrubro_id="+ DAUtils.formatValue(sXrubro_de, DA.DA_DT_TEXT);
					rs_rubro=connData.openSQL(sSql_rubro);
					sRubro="";
					if(rs_rubro.moveNext()){
						sRubro=rs_rubro.getString("xdescripcion");
					}
					rs_rubro.close();
					sValor="";
					sValor=Formatonumero(dXvalor_de.toString());
					
					sHtml+="<td>"+sXrubro_de+" : "+sRubro+"</td>";
					sHtml+="<td align=\"right\" >"+sValor+"</td>";
					
					if(dXsaldoprestamo>0){
						sValor="";
						sValor=Formatonumero(dXsaldoprestamo.toString());
						sHtml+="<td align=\"right\" >"+sValor+"</td>";
					}
				}
				sHtml+="</tr>";
				
				
			}
			rs_roles.close();
			
			//TOTALES INGRESOS Y DESCUENTOS
			sSql_roles="";
			sSql_roles+=" SELECT  ";
			sSql_roles+=" SUM(gp_rolesemple.xvalor_in), ";
			sSql_roles+=" SUM(gp_rolesemple.xvalor_de) ";
			sSql_roles+=" FROM  ";
			sSql_roles+="  "+connSource.translateTable("gp_rolesemple")+" ,  ";
			sSql_roles+="  "+connSource.translateTable("gp_empleados");
			sSql_roles+=" WHERE  ";
			sSql_roles+=" gp_rolesemple.xempresa_id=gp_empleados.xempresa_id ";
			sSql_roles+=" AND gp_rolesemple.xorganizacion_id=gp_empleados.xorganizacion_id ";
			sSql_roles+=" AND gp_rolesemple.xentorno_id=gp_empleados.xentorno_id ";
			sSql_roles+=" AND gp_rolesemple.xempleado_id=gp_empleados.xempleado_id ";
			sSql_roles+=" AND gp_rolesemple.xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_roles+=" AND gp_rolesemple.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_roles+=" AND gp_rolesemple.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_roles+=" AND gp_rolesemple.xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_roles+=" AND gp_rolesemple.xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			sSql_roles+=" AND gp_rolesemple.xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			sSql_roles+=" AND gp_rolesemple.xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
			rs_roles=connData.openSQL(sSql_roles);
			dTotal=0.00;
			dXvalor_de=0.00;
			dXvalor_in=0.00;
			if(rs_roles.moveNext()){
				dXvalor_in=rs_roles.getDouble(1);
				dXvalor_de=rs_roles.getDouble(2);
				if(dXvalor_de==null){dXvalor_de=0.00;}
				if(dXvalor_in==null){dXvalor_in=0.00;}
			}
			rs_roles.close();
			dTotal = dXvalor_in - dXvalor_de;
			
			sHtml+="<tr> ";
			sHtml+="<td>TOTAL INGRESOS : </td> ";
			sHtml+="<td align=\"right\" >"+Formatonumero(dXvalor_in.toString())+"</td> ";
			sHtml+="<td>TOTAL DESCUENTOS : </td> ";
			sHtml+="<td align=\"right\" >"+Formatonumero(dXvalor_de.toString())+"</td> ";
			sHtml+="</tr> ";
			
			sHtml+="</tbody> ";
			sHtml+="</table>";
			sHtml+="<p class=\"titulo\">Su total a recibir es : "+Formatonumero(dTotal.toString())+"("+Formatoletras(dTotal.toString())+")"+"</p>";
			sHtml+=" </div>";
			sHtml+=" </BODY> ";
			sHtml+=" </HTML> ";
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sHtml;
	}
	
	//ROL HORIZONTAL SEGUN PARAMETROS
	@SuppressWarnings("unused")
	public String Htmlrolhorizontal(Session session, Integer iAnio , Integer iPeriodo, String sEmpresa, String sOrganizacion , String sEntorno, String sOrigen , String sEmpleado , String sDepartamento){
		String sHtml="" , sSql_novedades="" , sSql_ingresos="", sSql_empleados="", sSql_departamentos="", sSql_seccion="";
		DAResultSet rs_novedades = null, rs_ingresos=null,rs_empleados=null, rs_departamentos=null, rs_seccion=null;
		//EL VECTOR VA HA TENER UNA ESTRUCTURA DE 
		//DATOS EMPLEADOS--NOVEDADES DE ASISTENCIA--INGRESOS IESS--INGRESOS NO IESS-- DESCUENTOS-- PROVISIONES
		String []sCabecera=new String [200];
		String []sCodigos=new String [200];
		String []sTipo=new String[200]; // C--cabecera , N--novedades , II--ingresos iess , IN--ingresos no iess, D--descuentos, P--provision
		Integer iTamanio=10;
		
		//DATOS INICIALES DEL EMPLEADO
		sCabecera[0]="ORIGEN";				sCodigos[0]="ORIGEN"; 				sTipo[0]="C";
		sCabecera[1]="N°";					sCodigos[1]="N°"; 					sTipo[1]="C";
		sCabecera[2]="CÉDULA";				sCodigos[2]="CÉDULA"; 				sTipo[2]="C";
		sCabecera[3]="NOMBRES Y APELLIDOS";	sCodigos[3]="NOMBRES Y APELLIDOS"; 	sTipo[3]="C";
		sCabecera[4]="CARGO";				sCodigos[4]="CARGO"; 				sTipo[4]="C";
		sCabecera[5]="FECHA DE INGRESO";	sCodigos[5]="FECHA DE INGRESO"; 	sTipo[5]="C";
		//ADICIONALES PARA DATOS DEL EMPLEADO
		sCabecera[6]=null;	sCodigos[6]=null; sTipo[6]="C";
		sCabecera[7]=null;	sCodigos[7]=null; sTipo[7]="C";
		sCabecera[8]=null;	sCodigos[8]=null; sTipo[8]="C";
		sCabecera[9]=null;	sCodigos[9]=null; sTipo[9]="C";
		sCabecera[10]=null;	sCodigos[10]=null; sTipo[10]="C";
		
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//LA IDEA ES ARMAR UN VECTOR CON TODOS LOS DATOS DE UN ROL DE PAGOS PARA HACER HTML
		
		if(sOrigen==null){sOrigen="";}
		if(sEmpleado==null){sEmpleado="";}
		if(sDepartamento==null){sDepartamento="";}
		
		try {
			//VAMOS A COLOCAR LAS NOVEDADES
			sSql_novedades="";
			sSql_novedades+="  SELECT  DISTINCT gp_asistencias.xtipoasistencia_id  ,gp_tiposasistencia.xabreviatura ";
			sSql_novedades+="  FROM  "+connSource.translateTable("gp_asistencias")+" , "+connSource.translateTable("gp_tiposasistencia");
			sSql_novedades+="  WHERE  ";
			sSql_novedades+="  gp_asistencias.xempresa_id=gp_tiposasistencia.xempresa_id ";
			sSql_novedades+="  AND gp_asistencias.xorganizacion_id=gp_tiposasistencia.xorganizacion_id ";
			sSql_novedades+="  AND gp_asistencias.xentorno_id=gp_tiposasistencia.xentorno_id ";
			sSql_novedades+="  AND gp_asistencias.xtipoasistencia_id=gp_tiposasistencia.xtipoasistencia_id ";
			sSql_novedades+="  AND gp_asistencias.xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_novedades+="  AND gp_asistencias.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_novedades+="  AND gp_asistencias.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			if(sEmpleado.length()>0)sSql_novedades+="  AND gp_asistencias.xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			if(sEmpleado.length()>0)sSql_novedades+="  AND gp_asistencias.xdepartamento_id="+ DAUtils.formatValue(sDepartamento, DA.DA_DT_TEXT);
			sSql_novedades+="  AND gp_asistencias.xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_novedades+="  AND gp_asistencias.xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			if(sOrigen.length()>0)sSql_novedades+="  AND gp_asistencias.xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
			sSql_novedades+="  GROUP BY gp_asistencias.xtipoasistencia_id ,gp_tiposasistencia.xabreviatura ";
			sSql_novedades+="  ORDER BY gp_asistencias.xtipoasistencia_id ";
			rs_novedades=connData.openSQL(sSql_novedades);
			while (rs_novedades.moveNext()) {
				iTamanio+=1;
				sCabecera[iTamanio]=rs_novedades.getString(2);sCodigos[iTamanio]=rs_novedades.getString(1);sTipo[iTamanio]="N";
			}
			rs_novedades.close();
			
			//VAMOS A COLOCAR LOS INGRESOS QUE PAGAN IESS
			sSql_ingresos="";
			sSql_ingresos+=" SELECT  ";
			sSql_ingresos+=" DISTINCT gp_pagonomina.xrubro_id, gp_rubros.xdescripcion , gp_pagonomina.xorden";
			sSql_ingresos+=" FROM "+connSource.translateTable("gp_pagonomina")+" ,  ";
			sSql_ingresos+=" "+connSource.translateTable("gp_rubros");
			sSql_ingresos+=" WHERE  ";
			sSql_ingresos+=" gp_pagonomina.xempresa_id=gp_rubros.xempresa_id ";
			sSql_ingresos+=" AND gp_pagonomina.xorganizacion_id=gp_rubros.xorganizacion_id ";
			sSql_ingresos+=" AND gp_pagonomina.xentorno_id=gp_rubros.xentorno_id ";
			sSql_ingresos+=" AND gp_pagonomina.xrubro_id=gp_rubros.xrubro_id ";
			sSql_ingresos+=" AND gp_pagonomina.xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_ingresos+=" AND gp_pagonomina.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_ingresos+=" AND gp_pagonomina.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_ingresos+=" AND gp_pagonomina.xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_ingresos+=" AND gp_pagonomina.xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			if(sEmpleado.length()>0)sSql_ingresos+=" AND gp_pagonomina.xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			if(sDepartamento.length()>0)sSql_ingresos+=" AND gp_pagonomina.xdepartamento_id="+ DAUtils.formatValue(sDepartamento, DA.DA_DT_TEXT);
			if(sOrigen.length()>0)sSql_ingresos+=" AND gp_pagonomina.xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
			sSql_ingresos+=" AND gp_pagonomina.xtiporubro='A' ";//INGRESO
			sSql_ingresos+=" AND gp_pagonomina.xpagaiess=-1 ";//IESS
			sSql_ingresos+=" AND gp_pagonomina.xdespliegarol=-1 ";
			sSql_ingresos+=" ORDER BY gp_pagonomina.xorden  ";
			rs_ingresos=connData.openSQL(sSql_ingresos);
			while(rs_ingresos.moveNext()){
				iTamanio+=1;
				sCabecera[iTamanio]=rs_ingresos.getString(2);sCodigos[iTamanio]=rs_ingresos.getString(1);sTipo[iTamanio]="II";
			}
			rs_ingresos.close();
			
			//COLOCAMOS LOS TOTALES IESS
			iTamanio+=1;
			sCabecera[iTamanio]="TOTAL INGRESOS PAGAN IESS";sCodigos[iTamanio]="TOTAL";sTipo[iTamanio]="TII";
			
			//VAMOS A COLOCAR LOS INGRESOS QUE NO PAGAN IESS
			sSql_ingresos="";
			sSql_ingresos+=" SELECT  ";
			sSql_ingresos+=" DISTINCT gp_pagonomina.xrubro_id, gp_rubros.xdescripcion , gp_pagonomina.xorden";
			sSql_ingresos+=" FROM "+connSource.translateTable("gp_pagonomina")+" ,  ";
			sSql_ingresos+=" "+connSource.translateTable("gp_rubros");
			sSql_ingresos+=" WHERE  ";
			sSql_ingresos+=" gp_pagonomina.xempresa_id=gp_rubros.xempresa_id ";
			sSql_ingresos+=" AND gp_pagonomina.xorganizacion_id=gp_rubros.xorganizacion_id ";
			sSql_ingresos+=" AND gp_pagonomina.xentorno_id=gp_rubros.xentorno_id ";
			sSql_ingresos+=" AND gp_pagonomina.xrubro_id=gp_rubros.xrubro_id ";
			sSql_ingresos+=" AND gp_pagonomina.xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_ingresos+=" AND gp_pagonomina.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_ingresos+=" AND gp_pagonomina.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_ingresos+=" AND gp_pagonomina.xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_ingresos+=" AND gp_pagonomina.xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			if(sEmpleado.length()>0)sSql_ingresos+=" AND gp_pagonomina.xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			if(sDepartamento.length()>0)sSql_ingresos+=" AND gp_pagonomina.xdepartamento_id="+ DAUtils.formatValue(sDepartamento, DA.DA_DT_TEXT);
			if(sOrigen.length()>0)sSql_ingresos+=" AND gp_pagonomina.xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
			sSql_ingresos+=" AND gp_pagonomina.xtiporubro='A' ";//INGRESO
			sSql_ingresos+=" AND gp_pagonomina.xpagaiess=0 ";//NO IESS
			sSql_ingresos+=" AND gp_pagonomina.xdespliegarol=-1 ";
			sSql_ingresos+=" ORDER BY gp_pagonomina.xorden  ";
			rs_ingresos=connData.openSQL(sSql_ingresos);
			while(rs_ingresos.moveNext()){
				iTamanio+=1;
				sCabecera[iTamanio]=rs_ingresos.getString(2);sCodigos[iTamanio]=rs_ingresos.getString(1);sTipo[iTamanio]="IN";
			}
			rs_ingresos.close();
			
			//COLOCAMOS LOS TOTALES NO IESS
			iTamanio+=1;
			sCabecera[iTamanio]="TOTAL INGRESOS NO PAGAN IESS";sCodigos[iTamanio]="TOTAL";sTipo[iTamanio]="TIN";
			
			
			//VAMOS A COLOCAR LOS DESCUENTO
			sSql_ingresos="";
			sSql_ingresos+=" SELECT  ";
			sSql_ingresos+=" DISTINCT gp_pagonomina.xrubro_id, gp_rubros.xdescripcion, gp_pagonomina.xorden ";
			sSql_ingresos+=" FROM "+connSource.translateTable("gp_pagonomina")+" ,  ";
			sSql_ingresos+=" "+connSource.translateTable("gp_rubros");
			sSql_ingresos+=" WHERE  ";
			sSql_ingresos+=" gp_pagonomina.xempresa_id=gp_rubros.xempresa_id ";
			sSql_ingresos+=" AND gp_pagonomina.xorganizacion_id=gp_rubros.xorganizacion_id ";
			sSql_ingresos+=" AND gp_pagonomina.xentorno_id=gp_rubros.xentorno_id ";
			sSql_ingresos+=" AND gp_pagonomina.xrubro_id=gp_rubros.xrubro_id ";
			sSql_ingresos+=" AND gp_pagonomina.xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_ingresos+=" AND gp_pagonomina.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_ingresos+=" AND gp_pagonomina.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_ingresos+=" AND gp_pagonomina.xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_ingresos+=" AND gp_pagonomina.xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			if(sEmpleado.length()>0)sSql_ingresos+=" AND gp_pagonomina.xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			if(sDepartamento.length()>0)sSql_ingresos+=" AND gp_pagonomina.xdepartamento_id="+ DAUtils.formatValue(sDepartamento, DA.DA_DT_TEXT);
			if(sOrigen.length()>0)sSql_ingresos+=" AND gp_pagonomina.xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
			sSql_ingresos+=" AND gp_pagonomina.xtiporubro='B' ";//DESCUENTO
			//sSql_ingresos+=" AND gp_pagonomina.xpagaiess=0 ";//NO IESS
			sSql_ingresos+=" AND gp_pagonomina.xdespliegarol=-1 ";
			sSql_ingresos+=" ORDER BY gp_pagonomina.xorden  ";
			rs_ingresos=connData.openSQL(sSql_ingresos);
			while(rs_ingresos.moveNext()){
				iTamanio+=1;
				sCabecera[iTamanio]=rs_ingresos.getString(2);sCodigos[iTamanio]=rs_ingresos.getString(1);sTipo[iTamanio]="D";
			}
			rs_ingresos.close();
			
			//COLOCAMOS LOS TOTALES NO IESS
			iTamanio+=1;
			sCabecera[iTamanio]="TOTAL DESCUENTOS";sCodigos[iTamanio]="TOTAL";sTipo[iTamanio]="TD";
			
			//COLOCAMOS EL TOTAL DE GENERAL
			iTamanio+=1;
			sCabecera[iTamanio]="TOTAL A RECIBIR";sCodigos[iTamanio]="TOTAL";sTipo[iTamanio]="TT";
			
			//VAMOS A COLOCAR PROVISIONES
			sSql_ingresos="";
			sSql_ingresos+=" SELECT  ";
			sSql_ingresos+=" DISTINCT gp_pagonomina.xrubro_id, gp_rubros.xdescripcion, gp_pagonomina.xorden ";
			sSql_ingresos+=" FROM "+connSource.translateTable("gp_pagonomina")+" ,  ";
			sSql_ingresos+=" "+connSource.translateTable("gp_rubros");
			sSql_ingresos+=" WHERE  ";
			sSql_ingresos+=" gp_pagonomina.xempresa_id=gp_rubros.xempresa_id ";
			sSql_ingresos+=" AND gp_pagonomina.xorganizacion_id=gp_rubros.xorganizacion_id ";
			sSql_ingresos+=" AND gp_pagonomina.xentorno_id=gp_rubros.xentorno_id ";
			sSql_ingresos+=" AND gp_pagonomina.xrubro_id=gp_rubros.xrubro_id ";
			sSql_ingresos+=" AND gp_pagonomina.xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_ingresos+=" AND gp_pagonomina.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_ingresos+=" AND gp_pagonomina.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_ingresos+=" AND gp_pagonomina.xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_ingresos+=" AND gp_pagonomina.xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			if(sEmpleado.length()>0)sSql_ingresos+=" AND gp_pagonomina.xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			if(sDepartamento.length()>0)sSql_ingresos+=" AND gp_pagonomina.xdepartamento_id="+ DAUtils.formatValue(sDepartamento, DA.DA_DT_TEXT);
			if(sOrigen.length()>0)sSql_ingresos+=" AND gp_pagonomina.xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
			sSql_ingresos+=" AND gp_pagonomina.xtiporubro='P' ";//DESCUENTO
			//sSql_ingresos+=" AND gp_pagonomina.xpagaiess=0 ";//NO IESS
			//sSql_ingresos+=" AND gp_pagonomina.xdespliegarol=-1 ";
			sSql_ingresos+=" ORDER BY gp_pagonomina.xorden  ";
			rs_ingresos=connData.openSQL(sSql_ingresos);
			while(rs_ingresos.moveNext()){
				iTamanio+=1;
				sCabecera[iTamanio]=rs_ingresos.getString(2);sCodigos[iTamanio]=rs_ingresos.getString(1);sTipo[iTamanio]="P";
			}
			rs_ingresos.close();
			
			//COLOCAMOS LOS TOTALES NO IESS
			iTamanio+=1;
			sCabecera[iTamanio]="TOTAL PROVISIONES";sCodigos[iTamanio]="TOTAL";sTipo[iTamanio]="TP";
			
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		//ARMAMOS EL HTM
		String sXempleado_id,sXnombrecompleto,sXcargo_nom, sXtipoorigen, sXdepartamento_nom, sXseccion_nom,sXcentrocosto_id, sXempresa_id , sXorganizacion_id , sXentorno_id;
		Date dXfechaingreso ;
		String sTabla="" , sSql_obtenervalor="",sTipovector="",sNombrecabecera="";
		Integer iNumero=0, iXanio_id=0 , iXperiodo_id=0;
		DAResultSet rs_obtenervalor = null, rs_datos=null;
		Boolean bVerdadero=true, bFalso=false;
		Double dValor=0.00, dTotalII=0.00, dTotalIN=0.00, dTotalD=0.00, dTotalP=0.00, dTotalT=0.00;
		Double dTotalingresos=0.00,dTotalnoingresos=0.00,dTotaldescuentos=0.00, dTotalarecibir=0.00, dTotalprovisiones=0.00;
		Double dTotalingresos_dep=0.00,dTotalnoingresos_dep=0.00,dTotaldescuentos_dep=0.00, dTotalarecibir_dep=0.00, dTotalprovisiones_dep=0.00;
		String sXdepartamento_id,sXseccion_id;
		String sSql_datos="", sNombreempresa, sNombreentorno, sNombreorganizacion;
		
		//TOMAMOS LOS DATOS GENERALES PARA LA CABECER
		sNombreempresa=sNombreentorno=sNombreorganizacion="";
		try {
			sSql_datos="";
			sSql_datos+=" SELECT  ";
			sSql_datos+=" gp_entorno.xnombre, gp_organizacion.xnombre, gp_empresas.xnombre ";
			sSql_datos+=" FROM  ";
			sSql_datos+="  "+connSource.translateTable("gp_entorno")+" ,  ";
			sSql_datos+="  "+connSource.translateTable("gp_organizacion")+" ,  ";
			sSql_datos+="  "+connSource.translateTable("gp_empresas")+" ";
			sSql_datos+=" WHERE ";
			sSql_datos+=" gp_entorno.xempresa_id=gp_organizacion.xempresa_id ";
			sSql_datos+=" AND gp_entorno.xorganizacion_id=gp_organizacion.xorganizacion_id ";
			sSql_datos+=" AND gp_entorno.xempresa_id=gp_empresas.xempresa_id ";
			sSql_datos+=" AND gp_organizacion.xempresa_id=gp_empresas.xempresa_id ";
			sSql_datos+=" AND gp_entorno.xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_datos+=" AND gp_entorno.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_datos+=" AND gp_entorno.xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			rs_datos=connData.openSQL(sSql_datos);
			if(rs_datos.moveNext()){
				sNombreentorno=rs_datos.getString(1);
				sNombreorganizacion=rs_datos.getString(2);
				sNombreempresa=rs_datos.getString(3);
			}
			rs_datos.close();
		} catch (DAException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sUsuario=connSource.getUser();
		
		
		sHtml="";
		sHtml+=" <HTML> ";
		sHtml+=" <HEAD> ";
		sHtml+=" <TITLE>ROL DE PAGO REPORTE GENERAL</TITLE> ";
		sHtml+=Hojacssrolgeneral();
		sHtml+=" </HEAD> ";
		sHtml+="";
		sHtml+=" <BODY> ";
		//sHtml+="<div class=\"datagrid\">";
		sHtml+="<p class=\"titulo\">"+"ROL DE PAGOS"+"</p>";
		sHtml+="<p class=\"parrafo\">Año : "+iAnio+" </p>";
		sHtml+="<p class=\"parrafo\">Periodo : "+iPeriodo+" </p>";
		sHtml+="<p class=\"parrafo\">Empresa : "+sNombreempresa+" </p>";
		sHtml+="<p class=\"parrafo\">Organizacion : "+sNombreorganizacion+" </p>";
		sHtml+="<p class=\"parrafo\">Entorno : "+sNombreentorno+" </p>";
		sHtml+="<p class=\"parrafo\">Generado por : "+sUsuario+" </p>";
		
		sTabla="";
		sTabla+="<table class=\"tg\"> ";
		sTabla+="<thead> ";
		for(int i=0;i<sCabecera.length;i++){
			sNombrecabecera=sCabecera[i];
			if(sNombrecabecera!=null){
				sTabla+="  <th  class=\"tg-m3d7\" align=\"center\">"+sNombrecabecera+"</th> ";
			}
		}
		sTabla+="</thead> ";
		
		try {//1
			
			sSql_departamentos="";
			sSql_departamentos+=" SELECT DISTINCT xdepartamento_id, xdepartamento_nom, xanio_id , xperiodo_id ,xempresa_id,xorganizacion_id,xentorno_id ";
			sSql_departamentos+=" FROM  "+connSource.translateTable("gp_pagonomina");
			sSql_departamentos+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_departamentos+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_departamentos+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_departamentos+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_departamentos+=" AND xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			if(sOrigen.length()>0)sSql_departamentos+=" AND xtipoorigen ="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
			if(sEmpleado.length()>0)sSql_departamentos+=" AND xempleado_id ="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			if(sDepartamento.length()>0)sSql_departamentos+=" AND xdepartamento_id ="+ DAUtils.formatValue(sDepartamento, DA.DA_DT_TEXT);
			rs_departamentos=connData.openSQL(sSql_departamentos);
			sXdepartamento_nom="";
			while(rs_departamentos.moveNext()){//departamento
					dTotalingresos_dep= dTotalnoingresos_dep= dTotaldescuentos_dep= dTotalarecibir_dep= dTotalprovisiones_dep=0.00;
					iXanio_id=rs_departamentos.getInt("xanio_id");
					iXperiodo_id=rs_departamentos.getInt("xperiodo_id");
					sXdepartamento_id=rs_departamentos.getString("xdepartamento_id");
					sXempresa_id=rs_departamentos.getString("xempresa_id");
					sXorganizacion_id=rs_departamentos.getString("xorganizacion_id");
					sXentorno_id=rs_departamentos.getString("xentorno_id");
					sXdepartamento_nom=rs_departamentos.getString("xdepartamento_nom");
					
					sTabla+="<tr>";
					sTabla+="<td colspan=\""+iTamanio+"\" class=\"tg-kl7f\">->"+sXdepartamento_nom+"</td>";
					sTabla+="</tr>";
					
					sSql_seccion="";
					sSql_seccion+=" SELECT DISTINCT xseccion_id, xseccion_nom ";
					sSql_seccion+=" FROM  "+connSource.translateTable("gp_pagonomina");
					sSql_seccion+=" WHERE xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
					sSql_seccion+=" AND xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
					sSql_seccion+=" AND xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
					sSql_seccion+=" AND xanio_id="+ DAUtils.formatValue(iXanio_id, DA.DA_DT_INTEGER);
					sSql_seccion+=" AND xperiodo_id="+ DAUtils.formatValue(iXperiodo_id, DA.DA_DT_INTEGER);
					if(sOrigen.length()>0)sSql_seccion+=" AND xtipoorigen ="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
					if(sEmpleado.length()>0)sSql_seccion+=" AND xempleado_id ="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
					sSql_seccion+=" AND xdepartamento_id ="+ DAUtils.formatValue(sXdepartamento_id, DA.DA_DT_TEXT);
					rs_seccion=connData.openSQL(sSql_seccion);
					sXseccion_nom="";
					while(rs_seccion.moveNext()){//seccion
						dTotalingresos=dTotalnoingresos=dTotaldescuentos=dTotalarecibir=dTotalprovisiones=0.00;
						sXseccion_id=rs_seccion.getString("xseccion_id");
						sXseccion_nom=rs_seccion.getString("xseccion_nom");
					
						sTabla+="<tr>";
						sTabla+="<td colspan=\""+iTamanio+"\" class=\"tg-kl7f\">--->"+sXseccion_nom+"</td>";
						sTabla+="</tr>";
						
						sSql_empleados="";
						sSql_empleados+=" SELECT DISTINCT  ";
						sSql_empleados+=" gp_pagonomina.xempleado_id, ";
						sSql_empleados+=" gp_empleados.xnombrecompleto, ";
						sSql_empleados+=" gp_pagonomina.xfechaingreso  ,  ";
						sSql_empleados+=" gp_pagonomina.xcargo_nom,  ";
						sSql_empleados+=" gp_pagonomina.xtipoorigen,  ";
						sSql_empleados+=" gp_pagonomina.xdepartamento_nom,  ";
						sSql_empleados+=" gp_pagonomina.xseccion_nom, ";
						sSql_empleados+=" gp_pagonomina.xcentrocosto_id , ";
						sSql_empleados+=" gp_pagonomina.xanio_id , ";
						sSql_empleados+=" gp_pagonomina.xperiodo_id , ";
						sSql_empleados+=" gp_pagonomina.xempresa_id ,";
						sSql_empleados+=" gp_pagonomina.xorganizacion_id ,";
						sSql_empleados+=" gp_pagonomina.xentorno_id";
						sSql_empleados+=" FROM  "+connSource.translateTable("gp_pagonomina")+",  ";
						sSql_empleados+="  "+connSource.translateTable("gp_empleados");
						sSql_empleados+=" WHERE  ";
						sSql_empleados+=" gp_empleados.xempresa_id=gp_pagonomina.xempresa_id ";
						sSql_empleados+=" AND gp_empleados.xorganizacion_id=gp_pagonomina.xorganizacion_id ";
						sSql_empleados+=" AND gp_empleados.xentorno_id=gp_pagonomina.xentorno_id ";
						sSql_empleados+=" AND gp_empleados.xempleado_id=gp_pagonomina.xempleado_id ";
						sSql_empleados+=" AND gp_pagonomina.xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
						sSql_empleados+=" AND gp_pagonomina.xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
						sSql_empleados+=" AND gp_pagonomina.xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
						sSql_empleados+=" AND gp_pagonomina.xanio_id="+ DAUtils.formatValue(iXanio_id, DA.DA_DT_INTEGER);
						sSql_empleados+=" AND gp_pagonomina.xperiodo_id="+ DAUtils.formatValue(iXperiodo_id, DA.DA_DT_INTEGER);
						if(sEmpleado.length()>0)sSql_empleados+=" AND gp_pagonomina.xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
						sSql_empleados+=" AND gp_pagonomina.xdepartamento_id="+ DAUtils.formatValue(sXdepartamento_id, DA.DA_DT_TEXT);
						if(sOrigen.length()>0)sSql_empleados+=" AND gp_pagonomina.xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
						sSql_empleados+=" AND gp_pagonomina.xseccion_id="+ DAUtils.formatValue(sXseccion_id, DA.DA_DT_TEXT);
						sSql_empleados+=" ORDER BY gp_pagonomina.xcargo_nom,gp_empleados.xnombrecompleto ";
						rs_empleados=connData.openSQL(sSql_empleados);
						iTamanio=10;
						iNumero+=1;
						dTotalII=dTotalIN=dTotalD=dTotalP=dTotalT=0.00;
						while(rs_empleados.moveNext()){
							sXempleado_id="";sXnombrecompleto="";
							dXfechaingreso =null;sXcargo_nom="";
							sXtipoorigen="";sXdepartamento_nom="";
							sXseccion_nom="";sXcentrocosto_id="";
							dTotalP=0.0;
							dTotalII=0.0;
							dTotalD=0.0;
							dTotalIN=0.0;
							
							sXempleado_id=rs_empleados.getString(1);
							sXnombrecompleto=rs_empleados.getString(2);
							dXfechaingreso =rs_empleados.getDate(3);
							sXcargo_nom=rs_empleados.getString(4);
							sXtipoorigen=rs_empleados.getString(5);
							sXdepartamento_nom=rs_empleados.getString(6);
							sXseccion_nom=rs_empleados.getString(7);
							sXcentrocosto_id=rs_empleados.getString(8);
							
							
							
							sTabla+="<tr>";
							int iTamaniovector=(sCodigos.length) - 1;
							
							for(int i=0;i<iTamaniovector;i++){
								
								// C--cabecera , N--novedades , II--ingresos iess , IN--ingresos no iess, D--descuentos, P--provision
								sTipovector=sTipo[i];
								if(sTipovector==null){sTipovector="";}
								System.out.println ("tamanio--"+i+" de "+iTamaniovector + "---"+sTipo[i]);
								if(sTipovector.equals("C")){
									if(i==0) {sTabla+="<td class=\"tg-kl7f\">"+sXtipoorigen+"</td>";}
									if(i==1) {sTabla+="<td class=\"tg-kl7f\">"+iNumero+"</td>";}
									if(i==2) {sTabla+="<td class=\"tg-kl7f\">"+sXempleado_id+"</td>";}
									if(i==3) {sTabla+="<td class=\"tg-kl7f\">"+sXnombrecompleto+"</td>";}
									if(i==4) {sTabla+="<td class=\"tg-kl7f\">"+sXcargo_nom+"</td>";}
									if(i==5) {sTabla+="<td class=\"tg-kl7f\">"+dXfechaingreso+"</td>";}
									//if(i==6) {sTabla+="<td>"++"</td>";}
									//if(i==7) {sTabla+="<td>"++"</td>";}
									//if(i==8) {sTabla+="<td>"++"</td>";}
									//if(i==9) {sTabla+="<td>"++"</td>";}
									//if(i==10){sTabla+="<td>"++"</td>";}
									System.out.println ("Cabecera--"+sTabla);
								}
								
								if(sTipovector.equals("N")){
									sSql_obtenervalor="";
									sSql_obtenervalor+=" SELECT SUM(xhoras)  ";
									sSql_obtenervalor+=" FROM "+connSource.translateTable("gp_asistencias");
									sSql_obtenervalor+=" WHERE xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xanio_id="+ DAUtils.formatValue(iXanio_id, DA.DA_DT_INTEGER);
									sSql_obtenervalor+=" AND xperiodo_id="+ DAUtils.formatValue(iXperiodo_id, DA.DA_DT_INTEGER);
									sSql_obtenervalor+=" AND xempleado_id="+ DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xtipoorigen="+ DAUtils.formatValue(sXtipoorigen, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xtipoasistencia_id="+ DAUtils.formatValue(sCodigos[i], DA.DA_DT_TEXT);
									rs_obtenervalor=connData.openSQL(sSql_obtenervalor);
									dValor=0.00;
									if(rs_obtenervalor.moveNext()){
										dValor=rs_obtenervalor.getDouble(1);
										if(dValor==null){dValor=0.00;}
									}
									sTabla+="<td align=\"right\" class=\"tg-kl7f\">"+Formatonumero(dValor.toString())+"</td>";
									rs_obtenervalor.close();
									System.out.println ("Novedades--"+sTabla);
								}
								
								if(sTipovector.equals("II")){
									sSql_obtenervalor="";
									sSql_obtenervalor+=" SELECT SUM(xvalor)  ";
									sSql_obtenervalor+=" FROM "+connSource.translateTable("gp_pagonomina");
									sSql_obtenervalor+=" WHERE xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xanio_id="+ DAUtils.formatValue(iXanio_id, DA.DA_DT_INTEGER);
									sSql_obtenervalor+=" AND xperiodo_id="+ DAUtils.formatValue(iXperiodo_id, DA.DA_DT_INTEGER);
									sSql_obtenervalor+=" AND xempleado_id="+ DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xtipoorigen="+ DAUtils.formatValue(sXtipoorigen, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xtiporubro='A' ";
									sSql_obtenervalor+=" AND xpagaiess="+ DAUtils.formatValue(bVerdadero, DA.DA_DT_BOOLEAN);
									sSql_obtenervalor+=" AND xdespliegarol="+ DAUtils.formatValue(bVerdadero, DA.DA_DT_BOOLEAN);
									sSql_obtenervalor+=" AND xrubro_id="+ DAUtils.formatValue(sCodigos[i], DA.DA_DT_TEXT);
									rs_obtenervalor=connData.openSQL(sSql_obtenervalor);
									dValor=0.00;
									if(rs_obtenervalor.moveNext()){
										dValor=rs_obtenervalor.getDouble(1);
										if(dValor==null){dValor=0.00;}
										dTotalII+=dValor;
									}
									sTabla+="<td align=\"right\" class=\"tg-kl7f\">"+Formatonumero(dValor.toString())+"</td>";
									rs_obtenervalor.close();
									System.out.println ("ingresos iess--"+sTabla);
								}
								
								
								if(sTipovector.equals("IN")){
									sSql_obtenervalor="";
									sSql_obtenervalor+=" SELECT SUM(xvalor)  ";
									sSql_obtenervalor+=" FROM "+connSource.translateTable("gp_pagonomina");
									sSql_obtenervalor+=" WHERE xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xanio_id="+ DAUtils.formatValue(iXanio_id, DA.DA_DT_INTEGER);
									sSql_obtenervalor+=" AND xperiodo_id="+ DAUtils.formatValue(iXperiodo_id, DA.DA_DT_INTEGER);
									sSql_obtenervalor+=" AND xempleado_id="+ DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xtipoorigen="+ DAUtils.formatValue(sXtipoorigen, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xtiporubro='A' ";
									sSql_obtenervalor+=" AND xpagaiess="+ DAUtils.formatValue(bFalso, DA.DA_DT_BOOLEAN);
									sSql_obtenervalor+=" AND xdespliegarol="+ DAUtils.formatValue(bVerdadero, DA.DA_DT_BOOLEAN);
									sSql_obtenervalor+=" AND xrubro_id="+ DAUtils.formatValue(sCodigos[i], DA.DA_DT_TEXT);
									rs_obtenervalor=connData.openSQL(sSql_obtenervalor);
									dValor=0.00;
									if(rs_obtenervalor.moveNext()){
										dValor=rs_obtenervalor.getDouble(1);
										if(dValor==null){dValor=0.00;}
										dTotalIN+=dValor;
									}
									sTabla+="<td align=\"right\" class=\"tg-kl7f\">"+Formatonumero(dValor.toString())+"</td>";
									rs_obtenervalor.close();
									System.out.println ("ingresos no iess--"+sTabla);
								}
								
								
								if(sTipovector.equals("D")){
									sSql_obtenervalor="";
									sSql_obtenervalor+=" SELECT SUM(xvalor)  ";
									sSql_obtenervalor+=" FROM "+connSource.translateTable("gp_pagonomina");
									sSql_obtenervalor+=" WHERE xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xanio_id="+ DAUtils.formatValue(iXanio_id, DA.DA_DT_INTEGER);
									sSql_obtenervalor+=" AND xperiodo_id="+ DAUtils.formatValue(iXperiodo_id, DA.DA_DT_INTEGER);
									sSql_obtenervalor+=" AND xempleado_id="+ DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xtipoorigen="+ DAUtils.formatValue(sXtipoorigen, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xtiporubro='B' ";
									//sSql_obtenervalor+=" AND xpagaiess="+ DAUtils.formatValue(bFalso, DA.DA_DT_BOOLEAN);
									sSql_obtenervalor+=" AND xdespliegarol="+ DAUtils.formatValue(bVerdadero, DA.DA_DT_BOOLEAN);
									sSql_obtenervalor+=" AND xrubro_id="+ DAUtils.formatValue(sCodigos[i], DA.DA_DT_TEXT);
									rs_obtenervalor=connData.openSQL(sSql_obtenervalor);
									dValor=0.00;
									if(rs_obtenervalor.moveNext()){
										dValor=rs_obtenervalor.getDouble(1);
										if(dValor==null){dValor=0.00;}
										dTotalD+=dValor;
									}
									sTabla+="<td align=\"right\" class=\"tg-kl7f\">"+Formatonumero(dValor.toString())+"</td>";
									rs_obtenervalor.close();
									System.out.println ("Descuentos--"+sTabla);
								}
								
								
								if(sTipovector.equals("P")){
									sSql_obtenervalor="";
									sSql_obtenervalor+=" SELECT SUM(xvalor)  ";
									sSql_obtenervalor+=" FROM "+connSource.translateTable("gp_pagonomina");
									sSql_obtenervalor+=" WHERE xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xanio_id="+ DAUtils.formatValue(iXanio_id, DA.DA_DT_INTEGER);
									sSql_obtenervalor+=" AND xperiodo_id="+ DAUtils.formatValue(iXperiodo_id, DA.DA_DT_INTEGER);
									sSql_obtenervalor+=" AND xempleado_id="+ DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xtipoorigen="+ DAUtils.formatValue(sXtipoorigen, DA.DA_DT_TEXT);
									sSql_obtenervalor+=" AND xtiporubro='P' ";
									//sSql_obtenervalor+=" AND xpagaiess="+ DAUtils.formatValue(bFalso, DA.DA_DT_BOOLEAN);
									//sSql_obtenervalor+=" AND xdespliegarol="+ DAUtils.formatValue(bVerdadero, DA.DA_DT_BOOLEAN);
									sSql_obtenervalor+=" AND xrubro_id="+ DAUtils.formatValue(sCodigos[i], DA.DA_DT_TEXT);
									rs_obtenervalor=connData.openSQL(sSql_obtenervalor);
									dValor=0.00;
									if(rs_obtenervalor.moveNext()){
										dValor=rs_obtenervalor.getDouble(1);
										if(dValor==null){dValor=0.00;}
										dTotalP+=dValor;
									}
									sTabla+="<td align=\"right\" class=\"tg-kl7f\">"+Formatonumero(dValor.toString())+"</td>";
									rs_obtenervalor.close();
									System.out.println ("provisiones--"+sTabla);
								}
								
								if(sTipovector.equals("TII")){
									sTabla+="<td align=\"right\" class=\"tg-h2j0\">"+Formatonumero(dTotalII.toString())+"</td>";
									dTotalingresos+=dTotalII;
								}
								
								if(sTipovector.equals("TIN")){
									sTabla+="<td align=\"right\" class=\"tg-h2j0\">"+Formatonumero(dTotalIN.toString())+"</td>";
									dTotalnoingresos+=dTotalIN;
								}
								
								if(sTipovector.equals("TD")){
									sTabla+="<td align=\"right\" class=\"tg-h2j0\">"+Formatonumero(dTotalD.toString())+"</td>";
									dTotaldescuentos+=dTotalD;
								}
								
								if(sTipovector.equals("TP")){
									sTabla+="<td align=\"right\" class=\"tg-h2j0\">"+Formatonumero(dTotalP.toString())+"</td>";
									dTotalprovisiones+=dTotalP;
								}
								
								if(sTipovector.equals("TT")){
									dTotalT=dTotalII+dTotalIN-dTotalD;
									sTabla+="<td align=\"right\" class=\"tg-h2j0\">"+Formatonumero(dTotalT.toString())+"</td>";
									dTotalarecibir+=dTotalT;
								}
							}
							sTabla+="</tr>";
							
						}
						rs_empleados.close();
					}//seccion
					rs_seccion.close();
					//PRIMER NIVEL TOTALES SECCIONES
					sTabla+="<tr>";
					for(int i=0;i<sTipo.length;i++){
						sTipovector=null;
						sTipovector=sTipo[i];
						if(sTipovector==null){sTipovector="";}
						
						if(sTipovector.length()>0){
							
							if((sTipovector!="TII" || sTipovector!="TIN" || sTipovector!="TD" || sTipovector!="TP" || sTipovector!="TT")){
								if(sCabecera[i]!=null && sCodigos[i]!="TOTAL" && i==0)sTabla+="<td  colspan=\"4\" align=\"right\" class=\"tg-h2j0\">TOTAL "+sXseccion_nom+"</td>";
								if(sCabecera[i]!=null && sCodigos[i]!="TOTAL" && i>3)sTabla+="<td align=\"right\" class=\"tg-h2j0\"></td>";
							}
								
							
							
							if(sTipovector.equals("TII")){
								sTabla+="<td align=\"right\" class=\"tg-h2j0\">"+Formatonumero(dTotalingresos.toString())+"</td>";
								dTotalingresos_dep+=dTotalingresos;
							}
							
							if(sTipovector.equals("TIN")){
								sTabla+="<td align=\"right\" class=\"tg-h2j0\">"+Formatonumero(dTotalnoingresos.toString())+"</td>";
								dTotalnoingresos_dep+=dTotalnoingresos;
							}
							
							if(sTipovector.equals("TD")){
								sTabla+="<td align=\"right\" class=\"tg-h2j0\">"+Formatonumero(dTotaldescuentos.toString())+"</td>";
								dTotaldescuentos_dep+=dTotaldescuentos;
							}
							
							if(sTipovector.equals("TP")){
								sTabla+="<td align=\"right\" class=\"tg-h2j0\">"+Formatonumero(dTotalprovisiones.toString())+"</td>";
								dTotalprovisiones_dep+=dTotalprovisiones;
							}
							
							if(sTipovector.equals("TT")){
								sTabla+="<td align=\"right\" class=\"tg-h2j0\">"+Formatonumero(dTotalarecibir.toString())+"</td>";
								dTotalarecibir_dep+=dTotalarecibir;
							}
						}
					}
					sTabla+="</tr>";
			}//departamento
			rs_departamentos.close();
			//PRIMER NIVEL TOTALES SECCIONES
			sTabla+="<tr>";
			for(int i=0;i<sTipo.length;i++){
				sTipovector=null;
				sTipovector=sTipo[i];
				if(sTipovector==null){sTipovector="";}
				
				if(sTipovector.length()>0){
					
					if((sTipovector!="TII" || sTipovector!="TIN" || sTipovector!="TD" || sTipovector!="TP" || sTipovector!="TT")){
						if(sCabecera[i]!=null && sCodigos[i]!="TOTAL" && i==0)sTabla+="<td  colspan=\"4\" align=\"right\" class=\"tg-h2j0\">TOTAL "+sXdepartamento_nom+"</td>";
						if(sCabecera[i]!=null && sCodigos[i]!="TOTAL" && i>3)sTabla+="<td align=\"right\" class=\"tg-h2j0\"></td>";
					}
						
					
					
					if(sTipovector.equals("TII")){
						sTabla+="<td align=\"right\" class=\"tg-h2j0\">"+Formatonumero(dTotalingresos_dep.toString())+"</td>";
					}
					
					if(sTipovector.equals("TIN")){
						sTabla+="<td align=\"right\" class=\"tg-h2j0\">"+Formatonumero(dTotalnoingresos_dep.toString())+"</td>";
					}
					
					if(sTipovector.equals("TD")){
						sTabla+="<td align=\"right\" class=\"tg-h2j0\">"+Formatonumero(dTotaldescuentos_dep.toString())+"</td>";
					}
					
					if(sTipovector.equals("TP")){
						sTabla+="<td align=\"right\" class=\"tg-h2j0\">"+Formatonumero(dTotalprovisiones_dep.toString())+"</td>";
					}
					
					if(sTipovector.equals("TT")){
						sTabla+="<td align=\"right\" class=\"tg-h2j0\">"+Formatonumero(dTotalarecibir_dep.toString())+"</td>";
					}
				}
			}
			sTabla+="</tr>";
			
			sTabla+="</table> ";
			//sTabla+=" </div>";
			System.out.println ("tablasssss--"+ sHtml +sTabla);
			
		
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		return sHtml+sTabla;
	}
	
			
}