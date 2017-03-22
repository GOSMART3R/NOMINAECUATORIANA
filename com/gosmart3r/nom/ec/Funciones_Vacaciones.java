package com.gosmart3r.nom.ec;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAException;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.form.FMObject;
import com.unit4.karat.report.RE;
import com.unit4.karat.report.REConfiguration;
import com.unit4.karat.report.REPrint;
import com.unit4.karat.session.Session;



public class Funciones_Vacaciones {
	//FUNCIONES PARA LA PARTE DE VACACIONES
	private DAConnectionSource connSource; // Acceso a sdic
	private DAConnection connData; // Acceso a datos
	private Funciones_Generales funcionesGenerales;
	
	public Funciones_Vacaciones(Session session)  {
		super();
		//CONSTRUCTOR DE LA CLASE, DEBO INICIAL DE LEY LAS VARIABLES DE CONEXION A LA BASE DE DATOS
		// TODO Auto-generated constructor stub
		
		this.connSource=session.getConnectionSource();
		try {
			this.connData=session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		funcionesGenerales=new Funciones_Generales();
	}
	
	public void mensajeError(FMEvent fmEvento,String sMensaje,String sTitulo){
		//PRESENTA MENSAJE DE ERROR EN EL FORM OCUPADO
		fmEvento.setCancel(FMEvent.CANCEL_NO_MESSAGE);
		fmEvento.setMessageTrace("" + sMensaje);
		fmEvento.setMessage("ShowError " + sTitulo);
		fmEvento.setMessageFlags(com.unit4.karat.form.FMEvent.MessageFlags.MESSAGE_INTEGRATED.getCode() + com.unit4.karat.form.FMEvent.MessageFlags.MESSAGE_ERROR.getCode());
	}
	
	public String sqlEmpleados(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,String sPrefijo,String sCampos){
		//SE BUSCA LA FECHA DE INGRESO DEL EMPLEADO
		String sSQL_o,sPrefijo2;
		
		sSQL_o="";
		sPrefijo2="";
		
		if(sPrefijo.length()>0){
			sPrefijo2=sPrefijo.substring(0,(sPrefijo.length())-1);
		}
		
		try {
			sSQL_o="select ";
				sSQL_o=sSQL_o + sCampos + " ";
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable("gp_empleados") + " " + sPrefijo2 + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + sPrefijo + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
	}
	
	public String sqlReporteVacacion(String sEmpresa,String sEntorno,String sOrganizacion,Integer iAnio,double dPeriodo,String sPrefijo,String sCampos,String sCondicion,Integer iOpcion,Integer iTabla){
		//SE BUSCA LA FECHA DE INGRESO DEL EMPLEADO
		String sSQL_o,sPrefijo2,sTabla;
		
		sSQL_o="";
		sPrefijo2="";
		
		if(sPrefijo.length()>0){
			sPrefijo2=sPrefijo.substring(0,(sPrefijo.length())-1);
		}
		
		switch(iTabla){
			case 1:
				sTabla="gp_repovaccab";
				break;
			case 2:
				sTabla="gp_reportevacacion";
				break;
			default:
				sTabla=" ";
				break;
		}
		
		try {
			if(iOpcion==1){
				sSQL_o="select ";
					sSQL_o=sSQL_o + sCampos + " ";
			}
			if(iOpcion==2){
				sSQL_o="delete ";
			}
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable(sTabla) + " " + sPrefijo2 + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + sPrefijo + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xanio_id=" + DAUtils.formatValue(iAnio,DA.DA_DT_INTEGER) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xperiodo_id=" + DAUtils.formatValue(dPeriodo,DA.DA_DT_LONG) + " ";
				sSQL_o=sSQL_o + sCondicion + " ";
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
	}
	
	public String sqlPagoNomina(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,String sPrefijo,String sCampo,String sCondicion,String sTipo,String sRubro){
		//TRAE LOS DATOS DE LA TABLA DE LOS PAGOS DE LA NÓMINA
		String sSQL_o,sPrefijo2;
		
		sSQL_o="";
		sPrefijo2="";
		
		if(sPrefijo.length()>0){
			sPrefijo2=sPrefijo.substring(0,(sPrefijo.length())-1);
		}
		
		try {
			sSQL_o="select ";
				sSQL_o=sSQL_o + sCampo + " ";
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable("gp_pagonomina") + " " + sPrefijo2 + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + sPrefijo + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xfechafinal is null ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xrubro_id=" + DAUtils.formatValue(sRubro,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
				if(sTipo.length()>0){
					sSQL_o=sSQL_o + "and " + sPrefijo + "xtipoorigen=" + DAUtils.formatValue(sTipo,DA.DA_DT_TEXT) + " ";
				}
				sSQL_o=sSQL_o + sCondicion + " ";
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
	}
	
	public String sqlDatosCargoEmple(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,String sPrefijo,String sCampo,String sCondicion,int iOpcion){
		//SE TRAE LOS DATOS DEL EMPLEADO DESDE LOS CARGOS
		String sSQL_o,sPrefijo2;
		
		sSQL_o="";
		sPrefijo2="";
		
		if(sPrefijo.length()>0){
			sPrefijo2=sPrefijo.substring(0,(sPrefijo.length())-1);
		}
		
		try {
			sSQL_o="select ";
				sSQL_o=sSQL_o + sCampo + " ";
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable("gp_cargosemplea") + " " + sPrefijo2 + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + sPrefijo + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				if(iOpcion==1){
					sSQL_o=sSQL_o + "and " + sPrefijo + "xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
				}
				sSQL_o=sSQL_o + sCondicion + " ";
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
	}
	
	public String sqlDatosCargo(String sEmpresa,String sEntorno,String sOrganizacion,String sCargo,String sPrefijo,String sCampo,String sCondicion){
		//SE TRAE LOS DATOS DEL EMPLEADO DESDE LOS CARGOS
		String sSQL_o,sPrefijo2;
		
		sSQL_o="";
		sPrefijo2="";
		
		if(sPrefijo.length()>0){
			sPrefijo2=sPrefijo.substring(0,(sPrefijo.length())-1);
		}
		
		try {
			sSQL_o="select ";
				sSQL_o=sSQL_o + sCampo + " ";
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable("gp_cargos") + " " + sPrefijo2 + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + sPrefijo + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xcargo_id=" + DAUtils.formatValue(sCargo,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + sCondicion + " ";
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
	}
	
	public String sqlParametros(String sEmpresa,String sEntorno,String sOrganizacion,String sPrefijo,String sCampo,String sCondicion){
		//SE TRAE LOS DATOS DESDE LOS PARAMETROS
		String sSQL_o,sPrefijo2;
		
		sSQL_o="";
		sPrefijo2="";
		
		if(sPrefijo.length()>0){
			sPrefijo2=sPrefijo.substring(0,(sPrefijo.length())-1);
		}
		
		try {
			sSQL_o="select ";
				sSQL_o=sSQL_o + sCampo + " ";
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable("gp_parametros") + " " + sPrefijo2 + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + sPrefijo + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + sCondicion + " ";
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
	}
	
	public String sqlPeriodosVacaciones(String sCampos,String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,String sCondicion,Integer iOpcion,String sPrefijo){
		//OBTIENE CONSULTAS DE LA TABLA DE PERIODOS DE LAS VACACIONES DE LOS EMPLEADOS
		String sSQL_o,sPrefijo2;
		
		sSQL_o="";
		sPrefijo2="";
		
		if(sPrefijo.length()>0){
			sPrefijo2=sPrefijo.substring(0,(sPrefijo.length())-1);
		}
		
		try {
			sSQL_o="select "; 
				sSQL_o=sSQL_o + sCampos + " "; 
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable("gp_periodovacacion") + " " + sPrefijo2 + " "; 
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + sPrefijo + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " "; 
				sSQL_o=sSQL_o + "and " + sPrefijo + "xfechafinal is null ";
				sSQL_o=sSQL_o + sCondicion + " ";
			if(iOpcion==1){
				sSQL_o=sSQL_o + "order by ";
					sSQL_o=sSQL_o + sPrefijo + "xperiodovac_id ";
			}
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
	}
	
	public String sqlPermisoVacacion(String sCampos,String sEmpleado,String sEntorno,String sOrganizacion,String sEmpresa,String sCondicion,String sPrefijo){
		//SE BUSCAN LOS PERMISOS DE LAS VACACIONES 
		String sSQL_o,sPrefijo2;
		
		sSQL_o="";
		sPrefijo2="";
		
		if(sPrefijo.length()>0){
			sPrefijo2=sPrefijo.substring(0,(sPrefijo.length())-1);
		}

		try {
			sSQL_o="select ";
				sSQL_o=sSQL_o + sCampos + " ";
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable("gp_permisovacacion") + " " + sPrefijo2 + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + sPrefijo + "xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorganizacion_id="+ DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xfechafinal is null ";
				sSQL_o=sSQL_o + sCondicion + " ";
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
	}
	
	public String sqlVacacion(String sCampos,String sEmpleado,String sEntorno,String sOrganizacion,String sEmpresa,String sCondicion,String sPrefijo,Integer iOpcion){
		//SE BUSCAN LAS VACACIONES
		String sSQL_o,sPrefijo2,sTabla;
		
		sSQL_o="";
		sPrefijo2="";
		sTabla="";
		
		switch(iOpcion){
			case 1:
				sTabla="gp_vacacion";
				break;
			case 2:
				sTabla="gp_vacacionnega";
				break;
			default:
				sTabla=" ";
				break;
		}

		if(sPrefijo.length()>0){
			sPrefijo2=sPrefijo.substring(0,(sPrefijo.length())-1);
		}

		try {
			sSQL_o="select ";
				sSQL_o=sSQL_o + sCampos + " ";
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable(sTabla) + " " + sPrefijo2 + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + sPrefijo + "xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorganizacion_id="+ DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xfechafinal is null ";
				if(sEmpleado!=null){
					sSQL_o=sSQL_o + "and " + sPrefijo + "xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
				}
				sSQL_o=sSQL_o + sCondicion + " ";
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
	}
	
	public String sqlNovedad(String sCampos,String sEmpleado,String sEntorno,String sOrganizacion,String sEmpresa,String sAnio,Double dPeriodo,String sOrigen,String sRubro,String sCondicion,String sPrefijo,Integer iOpcion){
		//SE BUSCAN LAS NOVEDADES
		String sSQL_o,sPrefijo2;
		
		sSQL_o="";
		sPrefijo2="";
		
		if(sPrefijo.length()>0){
			sPrefijo2=sPrefijo.substring(0,(sPrefijo.length())-1);
		}

		try {
			if(iOpcion==1){
				sSQL_o="select ";
					sSQL_o=sSQL_o + sCampos + " ";
				sSQL_o=sSQL_o + "from ";
					sSQL_o=sSQL_o + connSource.translateTable("gp_rubrosvariables") + " " + sPrefijo2 + " ";
			}
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + sPrefijo + "xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorganizacion_id="+ DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xanio_id=" + DAUtils.formatValue(sAnio,DA.DA_DT_INTEGER) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xtipoorigen=" + DAUtils.formatValue(sOrigen,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xrubro_id=" + DAUtils.formatValue(sRubro,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xperiodo_id=" + DAUtils.formatValue(dPeriodo,DA.DA_DT_LONG) + " ";
				sSQL_o=sSQL_o + sCondicion + " ";
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
	}
	
	public String sqlDevVac(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,String sPrefijo,String sCampos,String sCondicion,Double dPeriodo,String sOrigenProceso){
		//SE BUSCAN LAS NOVEDADES
		String sSQL_o,sPrefijo2;
		
		sSQL_o="";
		sPrefijo2="";
		
		if(sPrefijo.length()>0){
			sPrefijo2=sPrefijo.substring(0,(sPrefijo.length())-1);
		}

		try {
			sSQL_o="select ";
				sSQL_o=sSQL_o + sCampos + " ";
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable("gp_devvac") + " " + sPrefijo2 + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + sPrefijo + "xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorganizacion_id="+ DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xperiododevengado=" + DAUtils.formatValue(dPeriodo,DA.DA_DT_LONG) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorigenproceso=" + DAUtils.formatValue(sOrigenProceso,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xfechafinal is null ";
				if(sEmpleado!=null){
					sSQL_o=sSQL_o + "and " + sPrefijo + "xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
				}
				sSQL_o=sSQL_o + sCondicion + " ";
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
	}
	
	public int verificarAprobada(String sEmpleado,String sEntorno,String sOrganizacion,String sEmpresa,Double dCodigo,String sEstado){
		//VERIFICA EL ESTADO DE UNA SOLICITUD DE VACACIONES
		String sSQL_o,sCondicion;
		
		sSQL_o="";
		sCondicion="";
		
		DAResultSet rs=null;
		
		int iResultado;
		
		iResultado=0;
		
		try {
			sCondicion="and xcodigovacacion=" + DAUtils.formatValue(dCodigo,DA.DA_DT_LONG) + " ";
			sCondicion=sCondicion + "and xautorizada=" + DAUtils.formatValue(sEstado,DA.DA_DT_TEXT);
			
			sSQL_o=sqlPermisoVacacion("count(xempleado_id) valor",sEmpleado,sEntorno,sOrganizacion,sEmpresa,sCondicion,"");
			
			rs=connData.openSQL(sSQL_o);
			
			while (rs.moveNext()) {
				iResultado=rs.getInt("valor");
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(iResultado>0){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	public int verificarPermisos(String sEmpleado,String sEntorno,String sOrganizacion,String sEmpresa,Date dFechaInicial,Integer iDias){
		//VERIFICA LOS PERMISOS CREADOS EN LA TABLA
		//PARA QUE NO SE INSERTEN PERMISOS DUPLICADOS O SOLAPADOS
		String sSQL_o,sCondicion;
		sSQL_o="";
		sCondicion="";
		
		DAResultSet rs=null;
		
		int iResultado;
		
		iResultado=0;
		
		try {
			
			sCondicion=sCondicion + "and ( ";
				sCondicion=sCondicion + DAUtils.formatValue(dFechaInicial,DA.DA_DT_DATE) + " between xfechainicia and xfechatermina ";
				sCondicion=sCondicion + "or " + DAUtils.formatValue(funcionesGenerales.sumarRestarDiasFecha(dFechaInicial,(iDias-1)),DA.DA_DT_DATE) + " between xfechainicia and xfechatermina ";
			sCondicion=sCondicion + ") ";
		
			sSQL_o=sqlPermisoVacacion("count(xempleado_id) valor",sEmpleado,sEntorno,sOrganizacion,sEmpresa,sCondicion,"");
			
			rs=connData.openSQL(sSQL_o);
			
			while (rs.moveNext()) {
				iResultado=rs.getInt("valor");
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(iResultado>0){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	public String sqlDiasMaximos(int iPeriodoCerrado,String sEmpleado,String sEntorno,String sOrganizacion,String sEmpresa){
		//OBTIENE DESDE LA CONSULTA DEL SQL LOS DATOS DE LOS DÍAS DE VACACIONES
    	String sSQL_o;
    	sSQL_o="";
    	
		try {
			sSQL_o="select ";
				sSQL_o=sSQL_o + "a.xaniofinal, ";
				sSQL_o=sSQL_o + "a.xanioinicial, ";
				sSQL_o=sSQL_o + "a.xmes, ";
				sSQL_o=sSQL_o + "a.xdia, ";
				sSQL_o=sSQL_o + "a.xperiodovac_id, ";
				sSQL_o=sSQL_o + "a.xsaldodias, ";
				sSQL_o=sSQL_o + "a.xdiasperiodo, ";
				sSQL_o=sSQL_o + "( ";
					sSQL_o=sSQL_o + "select ";
						sSQL_o=sSQL_o + "sum(c.xsaldodias) ";
					sSQL_o=sSQL_o + "from ";
						sSQL_o=sSQL_o + connSource.translateTable("gp_periodovacacion") + " c ";
					sSQL_o=sSQL_o + "where ";
						sSQL_o=sSQL_o + "c.xempleado_id=a.xempleado_id ";
						sSQL_o=sSQL_o + "and c.xentorno_id=a.xentorno_id ";
						sSQL_o=sSQL_o + "and c.xorganizacion_id=a.xorganizacion_id ";
						sSQL_o=sSQL_o + "and c.xempresa_id=a.xempresa_id ";
						sSQL_o=sSQL_o + "and c.xfechafinal is null ";
						sSQL_o=sSQL_o + "and c.xperiodovac_id<( ";
							sSQL_o=sSQL_o + "select ";
								sSQL_o=sSQL_o + "b.xperiodovac_id ";
							sSQL_o=sSQL_o + "from ";
								sSQL_o=sSQL_o + connSource.translateTable("gp_periodovacacion") + " b ";
							sSQL_o=sSQL_o + "where ";
								sSQL_o=sSQL_o + "b.xempleado_id=a.xempleado_id ";
								sSQL_o=sSQL_o + "and b.xentorno_id=a.xentorno_id ";
								sSQL_o=sSQL_o + "and b.xorganizacion_id=a.xorganizacion_id ";
								sSQL_o=sSQL_o + "and b.xempresa_id=a.xempresa_id ";
								sSQL_o=sSQL_o + "and " + iPeriodoCerrado + "<((b.xaniofinal*100)+b.xmes) ";
								sSQL_o=sSQL_o + "and " + iPeriodoCerrado + ">=((b.xanioinicial*100)+b.xmes) ";
								sSQL_o=sSQL_o + "and b.xfechafinal is null ";
						sSQL_o=sSQL_o + ") ";
				sSQL_o=sSQL_o + ") saldo, ";
				sSQL_o=sSQL_o + "( ";
					sSQL_o=sSQL_o + "select ";
						sSQL_o=sSQL_o + "sum(c.xdiasperiodo) ";
					sSQL_o=sSQL_o + "from ";
						sSQL_o=sSQL_o + connSource.translateTable("gp_periodovacacion") + " c ";
					sSQL_o=sSQL_o + "where ";
						sSQL_o=sSQL_o + "c.xempleado_id=a.xempleado_id ";
						sSQL_o=sSQL_o + "and c.xentorno_id=a.xentorno_id ";
						sSQL_o=sSQL_o + "and c.xorganizacion_id=a.xorganizacion_id ";
						sSQL_o=sSQL_o + "and c.xempresa_id=a.xempresa_id ";
						sSQL_o=sSQL_o + "and c.xfechafinal is null ";
						sSQL_o=sSQL_o + "and c.xperiodovac_id<( ";
							sSQL_o=sSQL_o + "select ";
								sSQL_o=sSQL_o + "b.xperiodovac_id ";
							sSQL_o=sSQL_o + "from ";
								sSQL_o=sSQL_o + connSource.translateTable("gp_periodovacacion") + " b ";
							sSQL_o=sSQL_o + "where ";
								sSQL_o=sSQL_o + "b.xempleado_id=a.xempleado_id ";
								sSQL_o=sSQL_o + "and b.xentorno_id=a.xentorno_id ";
								sSQL_o=sSQL_o + "and b.xorganizacion_id=a.xorganizacion_id ";
								sSQL_o=sSQL_o + "and b.xempresa_id=a.xempresa_id ";
								sSQL_o=sSQL_o + "and " + iPeriodoCerrado + "<((b.xaniofinal*100)+b.xmes) ";
								sSQL_o=sSQL_o + "and " + iPeriodoCerrado + ">=((b.xanioinicial*100)+b.xmes) ";
								sSQL_o=sSQL_o + "and b.xfechafinal is null ";
						sSQL_o=sSQL_o + ") ";
				sSQL_o=sSQL_o + ") sumadias, ";
				sSQL_o=sSQL_o + "( ";
				sSQL_o=sSQL_o + "select ";
					sSQL_o=sSQL_o + "sum(c.xdiastomados) ";
				sSQL_o=sSQL_o + "from ";
					sSQL_o=sSQL_o + connSource.translateTable("gp_periodovacacion") + " c ";
				sSQL_o=sSQL_o + "where ";
					sSQL_o=sSQL_o + "c.xempleado_id=a.xempleado_id ";
					sSQL_o=sSQL_o + "and c.xentorno_id=a.xentorno_id ";
					sSQL_o=sSQL_o + "and c.xorganizacion_id=a.xorganizacion_id ";
					sSQL_o=sSQL_o + "and c.xempresa_id=a.xempresa_id ";
					sSQL_o=sSQL_o + "and c.xfechafinal is null ";
					sSQL_o=sSQL_o + "and c.xperiodovac_id<=( ";
						sSQL_o=sSQL_o + "select ";
							sSQL_o=sSQL_o + "b.xperiodovac_id ";
						sSQL_o=sSQL_o + "from ";
							sSQL_o=sSQL_o + connSource.translateTable("gp_periodovacacion") + " b ";
						sSQL_o=sSQL_o + "where ";
							sSQL_o=sSQL_o + "b.xempleado_id=a.xempleado_id ";
							sSQL_o=sSQL_o + "and b.xentorno_id=a.xentorno_id ";
							sSQL_o=sSQL_o + "and b.xorganizacion_id=a.xorganizacion_id ";
							sSQL_o=sSQL_o + "and b.xempresa_id=a.xempresa_id ";
							sSQL_o=sSQL_o + "and " + iPeriodoCerrado + "<((b.xaniofinal*100)+b.xmes) ";
							sSQL_o=sSQL_o + "and " + iPeriodoCerrado + ">=((b.xanioinicial*100)+b.xmes) ";
							sSQL_o=sSQL_o + "and b.xfechafinal is null ";
					sSQL_o=sSQL_o + ") ";
				sSQL_o=sSQL_o + ") diastomados, ";
				sSQL_o=sSQL_o + "a.xdiastomados ";
			sSQL_o=sSQL_o + "from  ";
				sSQL_o=sSQL_o + connSource.translateTable("gp_periodovacacion") + " a ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + "a.xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + iPeriodoCerrado + "<((a.xaniofinal*100)+a.xmes) ";
				sSQL_o=sSQL_o + "and " + iPeriodoCerrado + ">=((a.xanioinicial*100)+a.xmes) ";
				sSQL_o=sSQL_o + "and a.xfechafinal is null ";
				sSQL_o=sSQL_o + "and a.xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and a.xorganizacion_id="+ DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and a.xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
    }
	
	public void insertarPeriodos(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,Date dFechaIngreso,Integer iX,String sCadena,Integer iDias,Integer iDiasTomados,Date dFechaFinal,Integer iAnio,Integer iAnio2,Integer iDia,Integer iMes,Integer iDia2,Integer iMes2,Integer iSaldoDias){
		//INSERTA LOS PERIODOS DE LAS VACACIONES
		String sSQL_o;
		
		sSQL_o="";
		
		try {
			sSQL_o="insert ";
				sSQL_o=sSQL_o + "into " + connSource.translateTable("gp_periodovacacion") + " ";
					sSQL_o=sSQL_o + "( ";
						sSQL_o=sSQL_o + "xempresa_id, ";
						sSQL_o=sSQL_o + "xentorno_id, ";
						sSQL_o=sSQL_o + "xorganizacion_id, ";
						sSQL_o=sSQL_o + "xempleado_id, ";
						sSQL_o=sSQL_o + "xfechaingreso, ";
						sSQL_o=sSQL_o + "xperiodovac_id, ";
						sSQL_o=sSQL_o + "xperiodovac_des, ";
						sSQL_o=sSQL_o + "xdiasperiodo, ";
						sSQL_o=sSQL_o + "xdiastomados, ";
						sSQL_o=sSQL_o + "xfechafinal, ";
						sSQL_o=sSQL_o + "xanioinicial, ";
						sSQL_o=sSQL_o + "xaniofinal, ";
						sSQL_o=sSQL_o + "xdia, ";
						sSQL_o=sSQL_o + "xmes, ";
						sSQL_o=sSQL_o + "xdiafinal, ";
						sSQL_o=sSQL_o + "xmesfinal, ";
						sSQL_o=sSQL_o + "xfechainicia, ";
						sSQL_o=sSQL_o + "xfechatermina, ";
						sSQL_o=sSQL_o + "xsaldodias ";
					sSQL_o=sSQL_o + ")  ";
				sSQL_o=sSQL_o + "values  ";
					sSQL_o=sSQL_o + "( ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dFechaIngreso,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iX,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCadena,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iDias,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iDiasTomados,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dFechaFinal,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iAnio,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iAnio2,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iDia,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iMes,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iDia2,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iMes2,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iDia + "/" + iMes + "/" + iAnio,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iDia2 + "/" + iMes2 + "/" + iAnio2,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iSaldoDias,DA.DA_DT_INTEGER) + " ";
					sSQL_o=sSQL_o + ") ";
					
			connData.execSQL(sSQL_o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertarPermiso(Session session,String sEmpresa, String sEntorno, String sOrganizacion, String sEmpleado, Date dFechaIngreso, Date dFechaFinal, String sAutorizada, String sUsuarioAutoriza, Date dFechaAutoriza, String sTipoVacacion, String sUsuarioGenera, Date dFechaGenera, Date dFechaInicia, Integer iDiasATomar, Integer iDiasPorLey, Integer iDiasNegativos, Integer iDiasAcumulados, String sNombreEmpleado){
		//INSERTA LOS PERMISOS INGRESADOS EN LA TABLA DE PERMISOS
		//REVISANDO QUE NO SEAN PERMISOS CON FECHAS SOBREPUESTAS
		String sSQL_o;
		
		sSQL_o="";
		
		try{
		
			sSQL_o="insert ";
				sSQL_o=sSQL_o + "into " + connSource.translateTable("gp_permisovacacion") + " ";
					sSQL_o=sSQL_o + "( ";
						sSQL_o=sSQL_o + "xempresa_id, ";
						sSQL_o=sSQL_o + "xentorno_id, ";
						sSQL_o=sSQL_o + "xorganizacion_id, ";
						sSQL_o=sSQL_o + "xempleado_id, ";
						sSQL_o=sSQL_o + "xfechaingreso, ";
						sSQL_o=sSQL_o + "xcodigovacacion, ";
						sSQL_o=sSQL_o + "xfechafinal, ";
						sSQL_o=sSQL_o + "xautorizada, ";
						sSQL_o=sSQL_o + "xusuarioautoriza, ";
						sSQL_o=sSQL_o + "xfechaautoriza, ";
						sSQL_o=sSQL_o + "xtipovacacion, ";
						sSQL_o=sSQL_o + "xusuariogenera, ";
						sSQL_o=sSQL_o + "xfechagenera, ";
						sSQL_o=sSQL_o + "xfechainicia, ";
						sSQL_o=sSQL_o + "xfechatermina, ";
						sSQL_o=sSQL_o + "xfechareinicia, ";
						sSQL_o=sSQL_o + "xfechainiciatext, ";
						sSQL_o=sSQL_o + "xfechaterminatext, ";
						sSQL_o=sSQL_o + "xfechareiniciatext, ";
						sSQL_o=sSQL_o + "xdiasatomar, ";
						sSQL_o=sSQL_o + "xdiasporley, ";
						sSQL_o=sSQL_o + "xdiasnegativos, ";
						sSQL_o=sSQL_o + "xdiasacumulados, ";
						sSQL_o=sSQL_o + "xnombreempleado ";
					sSQL_o=sSQL_o + ") ";
				sSQL_o=sSQL_o + "values ";
					sSQL_o=sSQL_o + "( ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dFechaIngreso,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue((funcionesGenerales.maximoCodigo(session,"gp_permisovacacion","xcodigovacacion",sEmpresa,sOrganizacion,sEntorno))+1,DA.DA_DT_LONG) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dFechaFinal,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sAutorizada,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sUsuarioAutoriza,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dFechaAutoriza,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sTipoVacacion,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sUsuarioGenera,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dFechaGenera,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dFechaInicia,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(funcionesGenerales.sumarRestarDiasFecha(dFechaInicia,(iDiasATomar-1)),DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(funcionesGenerales.sumarRestarDiasFecha(dFechaInicia,iDiasATomar),DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(funcionesGenerales.fechasLargas(dFechaInicia),DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(funcionesGenerales.fechasLargas(funcionesGenerales.sumarRestarDiasFecha(dFechaInicia,(iDiasATomar-1))),DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(funcionesGenerales.fechasLargas(funcionesGenerales.sumarRestarDiasFecha(dFechaInicia,iDiasATomar)),DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iDiasATomar,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iDiasPorLey,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iDiasNegativos,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iDiasAcumulados,DA.DA_DT_INTEGER) + ",";
						sSQL_o=sSQL_o + DAUtils.formatValue(sNombreEmpleado,DA.DA_DT_TEXT) + " ";
					sSQL_o=sSQL_o + ")";
			
			connData.execSQL(sSQL_o);
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertarVacacion(int iOpcion,String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,Date dFechaIngreso,Double dCodigoVacacion,int iParte,String sTipoVacacion,Date dFechaFinal,String sAutorizada,String sUsuarioAutoriza,Date dFechaAutoriza,String sUsuarioGenera,Date dFechaGenera,Date dFechaInicia,Date dFechaTermina,String sFechaIniciaText,String sFechaTerminaText,Integer iDiasATomar,int iDiasPorLey,int iDiasNegativos,int iDiasAcumulados,String sPeriodovacDes,Double dPeriodovacId,Integer dSaldoInicialDias,Integer dDiasDescontar,Integer dSaldoFinalDias,String sNombreEmpleado,Integer iTotalPartes,String sPartesText,Integer iPasadasAConta,Date dFechaDevengada,Double dPeriodoDevengado,String sEnviadasA,String sUsuarioEnvia,Date dFechaEnvia){
		//INSERTA LA VACACION DEL EMPLEADO
		//EN LA TABLA DE VACACIONES O EN LA TABLA DE VACACIONES NEGATIVAS
		//DEPENDIENDO DE LOS DATOS CON LOS QUE SE MANDA A EJECUTAR LA FUNCION
		String sSQL_o,sTabla,sSQL_o1,sSQL_o2;
		
		sSQL_o="";
		sSQL_o1="";
		sSQL_o2="";
		sTabla="";
		
		
		switch(iOpcion){
		case 1:
			sTabla="gp_vacacion";
			
			sSQL_o1="xpasadasaconta, ";
			sSQL_o1=sSQL_o1 + "xfechadevengada, ";
			sSQL_o1=sSQL_o1 + "xperiododevengado ";
			
			sSQL_o2=DAUtils.formatValue(iPasadasAConta,DA.DA_DT_INTEGER) + ", ";
			sSQL_o2=sSQL_o2 + DAUtils.formatValue(dFechaDevengada,DA.DA_DT_DATE) + ", ";
			sSQL_o2=sSQL_o2 + DAUtils.formatValue(dPeriodoDevengado,DA.DA_DT_DOUBLE) + " ";
			break;
		case 2:
			sTabla="gp_vacacionnega";
			
			sSQL_o1="xenviadasa, ";
			sSQL_o1=sSQL_o1 + "xusuarioenvia, ";
			sSQL_o1=sSQL_o1 + "xfechaenvia ";
			
			sSQL_o2=DAUtils.formatValue(sEnviadasA,DA.DA_DT_TEXT) + ", ";
			sSQL_o2=sSQL_o2 + DAUtils.formatValue(sUsuarioEnvia,DA.DA_DT_TEXT) + ", ";
			sSQL_o2=sSQL_o2 + DAUtils.formatValue(dFechaEnvia,DA.DA_DT_DATE) + " ";
			break;
		default:
			sTabla=" ";
			sSQL_o1=" ";
			sSQL_o2=" ";
			break;
		}
		
		try {
			sSQL_o="insert ";
				sSQL_o=sSQL_o + "into " + connSource.translateTable(sTabla) + "( ";
					sSQL_o=sSQL_o + "xempresa_id, ";
					sSQL_o=sSQL_o + "xentorno_id, ";
					sSQL_o=sSQL_o + "xorganizacion_id, ";
					sSQL_o=sSQL_o + "xempleado_id, ";
					sSQL_o=sSQL_o + "xfechaingreso, ";
					sSQL_o=sSQL_o + "xcodigovacacion, ";
					sSQL_o=sSQL_o + "xparte, ";
					sSQL_o=sSQL_o + "xtipovacacion, ";
					sSQL_o=sSQL_o + "xfechafinal, ";
					sSQL_o=sSQL_o + "xautorizada, ";
					sSQL_o=sSQL_o + "xusuarioautoriza, ";
					sSQL_o=sSQL_o + "xfechaautoriza, ";
					sSQL_o=sSQL_o + "xusuariogenera, ";
					sSQL_o=sSQL_o + "xfechagenera, ";
					sSQL_o=sSQL_o + "xfechainicia, ";
					sSQL_o=sSQL_o + "xfechatermina, ";
					sSQL_o=sSQL_o + "xfechainiciatext, ";
					sSQL_o=sSQL_o + "xfechaterminatext, ";
					sSQL_o=sSQL_o + "xdiasatomar, ";
					sSQL_o=sSQL_o + "xdiasporley, ";
					sSQL_o=sSQL_o + "xdiasnegativos, ";
					sSQL_o=sSQL_o + "xdiasacumulados, ";
					sSQL_o=sSQL_o + "xperiodovac_des, ";
					sSQL_o=sSQL_o + "xperiodovac_id, ";
					sSQL_o=sSQL_o + "xsaldoinicialdias, ";
					sSQL_o=sSQL_o + "xdiasdescontar, ";
					sSQL_o=sSQL_o + "xsaldofinaldias, ";
					sSQL_o=sSQL_o + "xnombreempleado, ";
					sSQL_o=sSQL_o + "xtotalpartes, ";
					sSQL_o=sSQL_o + "xpartestext, ";
					sSQL_o=sSQL_o + sSQL_o1 + " ";
				sSQL_o=sSQL_o + ") values( ";
					sSQL_o=sSQL_o + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(dFechaIngreso,DA.DA_DT_DATE) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(dCodigoVacacion,DA.DA_DT_LONG) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(iParte,DA.DA_DT_INTEGER) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(sTipoVacacion,DA.DA_DT_TEXT) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(dFechaFinal,DA.DA_DT_DATE) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(sAutorizada,DA.DA_DT_TEXT) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(sUsuarioAutoriza,DA.DA_DT_TEXT) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(dFechaAutoriza,DA.DA_DT_DATE) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(sUsuarioGenera,DA.DA_DT_TEXT) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(dFechaGenera,DA.DA_DT_DATE) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(dFechaInicia,DA.DA_DT_DATE) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(dFechaTermina,DA.DA_DT_DATE) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(sFechaIniciaText,DA.DA_DT_TEXT) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(sFechaTerminaText,DA.DA_DT_TEXT) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(iDiasATomar,DA.DA_DT_INTEGER) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(iDiasPorLey,DA.DA_DT_INTEGER) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(iDiasNegativos,DA.DA_DT_INTEGER) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(iDiasAcumulados,DA.DA_DT_INTEGER) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(sPeriodovacDes,DA.DA_DT_TEXT) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(dPeriodovacId,DA.DA_DT_LONG) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(dSaldoInicialDias,DA.DA_DT_INTEGER) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(dDiasDescontar,DA.DA_DT_INTEGER) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(dSaldoFinalDias,DA.DA_DT_INTEGER) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(sNombreEmpleado,DA.DA_DT_TEXT) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(iTotalPartes,DA.DA_DT_INTEGER) + ", ";
					sSQL_o=sSQL_o + DAUtils.formatValue(sPartesText,DA.DA_DT_TEXT) + ", ";
					sSQL_o=sSQL_o + sSQL_o2 + " ";
				sSQL_o=sSQL_o + ") ";
				
			connData.execSQL(sSQL_o);
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertarNovedad(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,String sAnio,Double dPeriodo,String sOrigen,String sRubro,double dValor,String sUsuario,Date dFechaActual,String sObservacion,Integer iOpcion){
		//INSERTA LAS NOVEDADES
		String sSQL_o,sTabla,sCampo1,sCampo2;
		
		sSQL_o="";
		sTabla="";
		sCampo1="";
		sCampo2="";
		
		switch(iOpcion){
			case 1:
				sTabla="gp_rubrosvariables";
				sCampo1=", xvalortotal";
				sCampo2=", " + DAUtils.formatValue(dValor,DA.DA_DT_LONG);
				break;
			case 2:
				sTabla="gp_rubvariablesdet";
				sCampo1=", xvalor, xfecha";
				sCampo2=", " + DAUtils.formatValue(dValor,DA.DA_DT_LONG) + ", " + DAUtils.formatValue(dFechaActual,DA.DA_DT_DATE);
				break;
		}
		
		try {
			sSQL_o="insert ";
				sSQL_o=sSQL_o + "into " + connSource.translateTable(sTabla) + " ";
					sSQL_o=sSQL_o + "( ";
						sSQL_o=sSQL_o + "xempresa_id, ";
						sSQL_o=sSQL_o + "xentorno_id, ";
						sSQL_o=sSQL_o + "xorganizacion_id, ";
						sSQL_o=sSQL_o + "xanio_id, ";
						sSQL_o=sSQL_o + "xperiodo_id, ";
						sSQL_o=sSQL_o + "xempleado_id, ";
						sSQL_o=sSQL_o + "xtipoorigen, ";
						sSQL_o=sSQL_o + "xrubro_id, ";
						sSQL_o=sSQL_o + "xusuariogenera, ";
						sSQL_o=sSQL_o + "xfechagenera, ";
						sSQL_o=sSQL_o + "xobservacion ";
						sSQL_o=sSQL_o + sCampo1 + " ";
					sSQL_o=sSQL_o + ") ";
				sSQL_o=sSQL_o + "values ";
					sSQL_o=sSQL_o + "( ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sAnio,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dPeriodo,DA.DA_DT_LONG) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sOrigen,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sRubro,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sUsuario,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dFechaActual,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sObservacion,DA.DA_DT_TEXT) + " ";
						sSQL_o=sSQL_o + sCampo2 + " ";
					sSQL_o=sSQL_o + ") ";
				
				if(dValor>0){
					connData.execSQL(sSQL_o);
				}
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertarDevVac(Double dPeriodo,Integer iAnio,String sEmpresa,String sEntorno,String sOrganizacion,String sRubro,String sTipoOrigen,String sEmpleado,Double dCodigoVacacion,Integer iParte,Double dValor,String sDepartamento,String sDepartamentoNom,String sSeccion,String sSeccionNom,String sCargo,String sCargoNom,String sCentroCosto,Date dFechaIngreso,String sUsuarioGenera,Date dFechaGenera,String sCuentaDebe,String sCuentaHaber,Date dFechaFinal,String sOrigenProceso,Double dPeriodoDevengado,String sNombreEmpleado,String sContabilizadas){
		//INSERTA VALORES EN LA TABLA DE DEVENGAR VACACIONES
		String sSQL_o;
		
		sSQL_o="";
		
		try {
			sSQL_o="insert ";
				sSQL_o=sSQL_o + "into " + connSource.translateTable("gp_devvac") + " ";
					sSQL_o=sSQL_o + "( ";
						sSQL_o=sSQL_o + "xperiodo_id, ";
						sSQL_o=sSQL_o + "xanio_id, ";
						sSQL_o=sSQL_o + "xempresa_id, ";
						sSQL_o=sSQL_o + "xentorno_id, ";
						sSQL_o=sSQL_o + "xorganizacion_id, ";
						sSQL_o=sSQL_o + "xrubro_id, ";
						sSQL_o=sSQL_o + "xtipoorigen, ";
						sSQL_o=sSQL_o + "xempleado_id, ";
						sSQL_o=sSQL_o + "xcodigovacacion, ";
						sSQL_o=sSQL_o + "xparte, ";
						sSQL_o=sSQL_o + "xvalor, ";
						sSQL_o=sSQL_o + "xdepartamento_id, ";
						sSQL_o=sSQL_o + "xdepartamento_nom, ";
						sSQL_o=sSQL_o + "xseccion_id, ";
						sSQL_o=sSQL_o + "xseccion_nom, ";
						sSQL_o=sSQL_o + "xcargo_id, ";
						sSQL_o=sSQL_o + "xcargo_nom, ";
						sSQL_o=sSQL_o + "xcentrocosto_id, ";
						sSQL_o=sSQL_o + "xfechaingreso, ";
						sSQL_o=sSQL_o + "xusuariogenera, ";
						sSQL_o=sSQL_o + "xfechagenera, ";
						sSQL_o=sSQL_o + "xcuentadebe, ";
						sSQL_o=sSQL_o + "xcuentahaber, ";
						sSQL_o=sSQL_o + "xfechafinal, ";
						sSQL_o=sSQL_o + "xorigenproceso, ";
						sSQL_o=sSQL_o + "xperiododevengado, ";
						sSQL_o=sSQL_o + "xnombreempleado, ";
						sSQL_o=sSQL_o + "xcontabilizadas ";
					sSQL_o=sSQL_o + ") ";
				sSQL_o=sSQL_o + "values ";
					sSQL_o=sSQL_o + "( ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dPeriodo,DA.DA_DT_LONG) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iAnio,DA.DA_DT_LONG) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sRubro,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sTipoOrigen,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dCodigoVacacion,DA.DA_DT_DOUBLE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iParte,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dValor,DA.DA_DT_CURRENCY) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sDepartamento,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sDepartamentoNom,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sSeccion,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sSeccionNom,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCargo,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCargoNom,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCentroCosto,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dFechaIngreso,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sUsuarioGenera,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dFechaGenera,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCuentaDebe,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCuentaHaber,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dFechaFinal,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sOrigenProceso,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dPeriodoDevengado,DA.DA_DT_LONG) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sNombreEmpleado,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sContabilizadas,DA.DA_DT_TEXT) + " ";
					sSQL_o=sSQL_o + ") ";
			connData.execSQL(sSQL_o);
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void insertarReporteDias(String sEntorno,String sOrganizacion,String sEmpresa,String sEmpleado,Integer iOpcion,Integer iAnio,double dPeriodo,Date dFechaIngreso,String sCargo,double dValor,double dDiasMaximos,double dDiasGanados,String sNombreEmpleado,String sCargoNom){
		String sSQL_o,sTabla;
		
		Date dFechaActual;
		
		sSQL_o="";
		
		dFechaActual=new Date();
		
		switch(iOpcion){
			case 1:
				sTabla="gp_reportevacacion";
				break;
			case 2:
				sTabla="gp_repovaccab";
				break;
			default:
				sTabla=" ";
				break;
		}
		
		try {
			sSQL_o="insert ";
				sSQL_o=sSQL_o + "into " + connSource.translateTable(sTabla) + " ";
					sSQL_o=sSQL_o + "( ";
						sSQL_o=sSQL_o + "xentorno_id, ";
						sSQL_o=sSQL_o + "xempresa_id, ";
						sSQL_o=sSQL_o + "xorganizacion_id, ";
						sSQL_o=sSQL_o + "xanio_id, ";
						sSQL_o=sSQL_o + "xperiodo_id, ";
						if(iOpcion==1){
							sSQL_o=sSQL_o + "xempleado_id, ";
							sSQL_o=sSQL_o + "xfechaingreso, ";
							sSQL_o=sSQL_o + "xcargo_id, ";
							sSQL_o=sSQL_o + "xvalorprovision, ";
							sSQL_o=sSQL_o + "xmaximosdias, ";
							sSQL_o=sSQL_o + "xdiasganados, ";
							sSQL_o=sSQL_o + "xnombreempleado, ";
							sSQL_o=sSQL_o + "xcargo_desc, ";
						}
						sSQL_o=sSQL_o + "xusuariogenera, ";
						sSQL_o=sSQL_o + "xfechagenera ";
					sSQL_o=sSQL_o + ") ";
				sSQL_o=sSQL_o + "values ";
					sSQL_o=sSQL_o + "( ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iAnio,DA.DA_DT_LONG) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dPeriodo,DA.DA_DT_LONG) + ", ";
						if(iOpcion==1){
							sSQL_o=sSQL_o + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + ", ";
							sSQL_o=sSQL_o + DAUtils.formatValue(dFechaIngreso,DA.DA_DT_DATE) + ", ";
							sSQL_o=sSQL_o + DAUtils.formatValue(sCargo,DA.DA_DT_TEXT) + ", ";
							sSQL_o=sSQL_o + DAUtils.formatValue(dValor,DA.DA_DT_CURRENCY) + ", ";
							sSQL_o=sSQL_o + DAUtils.formatValue(dDiasMaximos,DA.DA_DT_DOUBLE) + ", ";
							sSQL_o=sSQL_o + DAUtils.formatValue(dDiasGanados,DA.DA_DT_DOUBLE) + ", ";
							sSQL_o=sSQL_o + DAUtils.formatValue(sNombreEmpleado,DA.DA_DT_TEXT) + ", ";
							sSQL_o=sSQL_o + DAUtils.formatValue(sCargoNom,DA.DA_DT_TEXT) + ", ";
						}
						sSQL_o=sSQL_o + DAUtils.formatValue(connSource.getUser(),DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dFechaActual,DA.DA_DT_DATE) + " ";
					sSQL_o=sSQL_o + ") ";
					
			connData.execSQL(sSQL_o);
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertarSaldoDias(String sEntorno,String sEmpresa,String sOrganizacion,String sEmpleado,Date dFechaIngreso,Double dMaximosDias,Double dDiasGanados,String sNombreEmpleado,String sCargoId,String sCargoDesc,Date dFechaActual,Double dSaldoDias,Integer iDiasTomados){
		String sSQL_o;
		
		sSQL_o="";
		
		try {
			sSQL_o="insert "; 
				sSQL_o=sSQL_o + "into gp_saldovacacion ";
					sSQL_o=sSQL_o + "( ";
						sSQL_o=sSQL_o + "xentorno_id, ";
						sSQL_o=sSQL_o + "xempresa_id, ";
						sSQL_o=sSQL_o + "xorganizacion_id, ";
						sSQL_o=sSQL_o + "xempleado_id, ";
						sSQL_o=sSQL_o + "xfechaingreso, ";
						sSQL_o=sSQL_o + "xmaximosdias, ";
						sSQL_o=sSQL_o + "xdiasganados, ";
						sSQL_o=sSQL_o + "xnombreempleado, ";
						sSQL_o=sSQL_o + "xcargo_id, ";
						sSQL_o=sSQL_o + "xcargo_desc, ";
						sSQL_o=sSQL_o + "xusuariogenera, ";
						sSQL_o=sSQL_o + "xfechagenera, ";
						sSQL_o=sSQL_o + "xsaldodias, ";
						sSQL_o=sSQL_o + "xdiastomados ";
					sSQL_o=sSQL_o + ") ";
				sSQL_o=sSQL_o + "values ";
					sSQL_o=sSQL_o + "( ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dFechaIngreso,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dMaximosDias,DA.DA_DT_DOUBLE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dDiasGanados,DA.DA_DT_DOUBLE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sNombreEmpleado,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCargoId,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCargoDesc,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(connSource.getUser(),DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dFechaActual,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dSaldoDias,DA.DA_DT_DOUBLE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iDiasTomados,DA.DA_DT_INTEGER) + " ";
					sSQL_o=sSQL_o + ") ";
			connData.execSQL(sSQL_o);
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarVacacion(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,String sAutorizada,Double dCodigo,String sCondicion,Integer iOpcion){
		//ELIMINA EL PERMISO DEL EMPLEADO
		String sSQL_o,sTabla;
		
		sSQL_o="";
		sTabla="";
		
		switch(iOpcion){
			case 1:
				sTabla="gp_vacacion";
				break;
			case 2:
				sTabla="gp_vacacionnega";
				break;
			case 3:
				sTabla="gp_permisovacacion";
				break;
		}
		
		try {
			sSQL_o="delete ";
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable(sTabla) + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + "xcodigovacacion=" + DAUtils.formatValue(dCodigo,DA.DA_DT_LONG) + " ";
				sSQL_o=sSQL_o + "and xautorizada=" + DAUtils.formatValue(sAutorizada,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xorganizacion_id="+ DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + sCondicion + " ";
				
			connData.execSQL(sSQL_o);
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarDevVac(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,String sPrefijo,String sCondicion,Double dPeriodo,String sOrigenProceso){
		//SE BUSCAN LAS NOVEDADES
		String sSQL_o,sPrefijo2;
		
		sSQL_o="";
		sPrefijo2="";
		
		if(sPrefijo.length()>0){
			sPrefijo2=sPrefijo.substring(0,(sPrefijo.length())-1);
		}

		try {
			sSQL_o="delete ";
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable("gp_devvac") + " " + sPrefijo2 + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + sPrefijo + "xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorganizacion_id="+ DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xperiododevengado=" + DAUtils.formatValue(dPeriodo,DA.DA_DT_LONG) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorigenproceso=" + DAUtils.formatValue(sOrigenProceso,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xfechafinal is null ";
				if(sEmpleado!=null){
					sSQL_o=sSQL_o + "and " + sPrefijo + "xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
				}
				sSQL_o=sSQL_o + sCondicion + " ";
				
			connData.execSQL(sSQL_o);
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarSaldoVacacion(String sEmpresa,String sEntorno,String sOrganizacion,String sPrefijo,String sCondicion){
		//SE BUSCAN LAS NOVEDADES
		String sSQL_o,sPrefijo2;
		
		sSQL_o="";
		sPrefijo2="";
		
		if(sPrefijo.length()>0){
			sPrefijo2=sPrefijo.substring(0,(sPrefijo.length())-1);
		}

		try {
			sSQL_o="delete ";
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable("gp_saldovacacion") + " " + sPrefijo2 + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + sPrefijo + "xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorganizacion_id="+ DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + sCondicion + " ";
				
			connData.execSQL(sSQL_o);
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarNovedad(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,String sAnio,Double dPeriodo,String sOrigen,String sRubro,double dValor){
		//ACTUALIZA LAS NOVEDADES
		String sSQL_o;
		
		sSQL_o="";
		
		try {
			sSQL_o="update ";
				sSQL_o=sSQL_o + connSource.translateTable("gp_rubrosvariables") + " ";
			sSQL_o=sSQL_o + "set ";
				sSQL_o=sSQL_o + "xvalortotal=xvalortotal+" + DAUtils.formatValue(dValor,DA.DA_DT_LONG) + " ";
			sSQL_o=sSQL_o + sqlNovedad("",sEmpleado,sEntorno,sOrganizacion,sEmpresa,sAnio,dPeriodo,sOrigen,sRubro,"","",0);
			if(dValor>0){
				connData.execSQL(sSQL_o);
			}
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarPeriodoVacaciones(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,Integer iCodigo,Integer iDias,Integer iOpcion){
		//ACTUALIZA LOS PERIODOS DE LAS VACACIONES
		String sSQL_o,sTexto;
		
		sSQL_o="";
		sTexto="";
		
		switch(iOpcion){
			case 1:
				sTexto="" + iDias;
				break;
			case 2:
				sTexto="(" + iDias + "*-1)";
				break;
		}
		
		try {
			sSQL_o="update ";
				sSQL_o=sSQL_o + connSource.translateTable("gp_periodovacacion") + " ";
			sSQL_o=sSQL_o + "set ";
				sSQL_o=sSQL_o + "xsaldodias=xsaldodias-" + sTexto + ", ";
				sSQL_o=sSQL_o + "xdiastomados=xdiastomados+" + sTexto + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + "xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xorganizacion_id="+ DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xfechafinal is null ";
				sSQL_o=sSQL_o + "and xperiodovac_id=" + DAUtils.formatValue(iCodigo,DA.DA_DT_INTEGER) + " ";
			
			connData.execSQL(sSQL_o);
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarVacaciones(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,Double dCodigo,String sCampos,int iOpcion,String sCondicion){
		//ACTUALIZA LAS VACACIONES
		String sSQL_o,sTabla;
		
		sSQL_o="";
		sTabla="";
		
		switch(iOpcion){
			case 1:
				sTabla="gp_vacacion";
				break;
			case 2:
				sTabla="gp_vacacionnega";
				break;
			default:
				sTabla=" ";
				break;
		}
		
		try {
			sSQL_o="update ";
				sSQL_o=sSQL_o + connSource.translateTable(sTabla) + " ";
			sSQL_o=sSQL_o + "set ";
				sSQL_o=sSQL_o + sCampos + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + "xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xorganizacion_id="+ DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xfechafinal is null ";
				sSQL_o=sSQL_o + "and xcodigovacacion=" + DAUtils.formatValue(dCodigo,DA.DA_DT_DOUBLE) + " ";
				sSQL_o=sSQL_o + sCondicion + " ";
			
			connData.execSQL(sSQL_o);
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarPagoNomina(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,String sCampo,String sCondicion,String sTipo,String sRubroVacacion){
		//TRAE LOS DATOS DE LA TABLA DE LOS PAGOS DE LA NÓMINA
		String sSQL_o;
		
		sSQL_o="";

		try {
			sSQL_o="update ";
				sSQL_o=sSQL_o + connSource.translateTable("gp_pagonomina") + " ";
			sSQL_o=sSQL_o + "set ";
				sSQL_o=sSQL_o + sCampo + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xfechafinal is null ";
				sSQL_o=sSQL_o + "and xrubro_id=" + DAUtils.formatValue(sRubroVacacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
				if(sTipo.length()>0){
					sSQL_o=sSQL_o + "and xtipoorigen=" + DAUtils.formatValue(sTipo,DA.DA_DT_TEXT) + " ";
				}
				sSQL_o=sSQL_o + sCondicion + " ";
			
			connData.execSQL(sSQL_o);
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarPartesTexto(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,Double dCodigo,int iOpcion){
		//ACTUALIZA LAS PARTES DE TEXTO DE LAS VACACIONES
		String sSQL_o,sTabla,sCondicion;
		
		DAResultSet rs=null;
		
		sSQL_o="";
		sTabla="";
		
		switch(iOpcion){
			case 1:
				sTabla="gp_vacacion";
				break;
			case 2:
				sTabla="gp_vacacionnega";
				break;
			default:
				sTabla=" ";
				break;
		}
		
		try {
			sSQL_o="select ";
				sSQL_o=sSQL_o + "xparte, ";
				sSQL_o=sSQL_o + "xtotalpartes ";
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable(sTabla) + " ";
			sSQL_o=sSQL_o + "where ";
			sSQL_o=sSQL_o + "xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
			sSQL_o=sSQL_o + "and xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
			sSQL_o=sSQL_o + "and xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
			sSQL_o=sSQL_o + "and xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
			sSQL_o=sSQL_o + "and xfechafinal is null ";
			sSQL_o=sSQL_o + "and xcodigovacacion=" + DAUtils.formatValue(dCodigo,DA.DA_DT_LONG) + " ";
			
			rs=connData.openSQL(sSQL_o);

			while (rs.moveNext()) {
				sCondicion="and xparte=" + DAUtils.formatValue(rs.getInt("xparte"),DA.DA_DT_INTEGER) + " ";
				sCondicion=sCondicion + "and xtotalpartes=" + DAUtils.formatValue(rs.getInt("xtotalpartes"),DA.DA_DT_INTEGER) + " ";
				
				actualizarVacaciones(sEmpresa,sEntorno,sOrganizacion,sEmpleado,dCodigo,"xpartestext=" + DAUtils.formatValue(rs.getInt("xparte") + " de " + rs.getInt("xtotalpartes"),DA.DA_DT_TEXT),iOpcion,sCondicion);
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void crearPeriodos(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado){
		//CREA LOS DIFERENTES PERIODOS DE VACACIONES DE LOS EMPLEADOS
		String sSql,sCadena,sDia,sMes,sCondicion;
		
		int iX,iAnio,iDias,iExiste,iDia,iMes,iDia2,iMes2,iAnio2;
		
		Date dFechaIngreso=new Date();
		
		Calendar cal=GregorianCalendar.getInstance();
		
		iX=1;
		iAnio=0;
		iDias=15;
		iExiste=0;
		iDia=0;
		iMes=0;
		iDia2=0;
		iMes2=0;
		iAnio2=0;
		
		sCadena="";
		sDia="";
		sMes="";
		sCondicion="";
		
		DAResultSet rs=null;
		
		try {
			sCondicion="and a.xfechaingreso=( ";
				sCondicion=sCondicion + sqlEmpleados(sEmpresa,sEntorno,sOrganizacion,sEmpleado,"b.","b.xfechaingreso");
			sCondicion=sCondicion + ")";
			
			sSql=sqlPeriodosVacaciones("count(a.xperiodovac_id)",sEmpresa,sEntorno,sOrganizacion,sEmpleado,sCondicion,0,"a.");
			
			rs=connData.openSQL(sSql);

			while (rs.moveNext()) {
				iExiste=rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(iExiste==0){
			
			try {
				sSql=sqlEmpleados(sEmpresa,sEntorno,sOrganizacion,sEmpleado,"","xfechaingreso");
				
				rs=connData.openSQL(sSql);
	
				while (rs.moveNext()) {
					dFechaIngreso=rs.getDate(1);
				}
				rs.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			cal.setTime(dFechaIngreso);
			
			iDia=cal.get(Calendar.DAY_OF_MONTH);
			iMes=cal.get(Calendar.MONTH)+1;
			iAnio=cal.get(Calendar.YEAR);
			
			cal.add(Calendar.YEAR,1);
			cal.add(Calendar.DATE,-1);
			
			iDia2=cal.get(Calendar.DAY_OF_MONTH);
			iMes2=cal.get(Calendar.MONTH)+1;
			iAnio2=cal.get(Calendar.YEAR);
			
			if(iDia<10){
				sDia="0";
			}
			else{
				sDia="";
			}
			
			if(iMes<10){
				sMes="0";
			}
			else{
				sMes="";
			}
			
			for(iX=1;iX<=30;iX++){
				sCadena=sDia + iDia + "-" + sMes + iMes + "/" + iAnio + "-" + (iAnio+1);
				
				if(iX>5){
					iDias++;
				}
				
				if(iDias>30){
					iDias=30;
				}
				
				insertarPeriodos(sEmpresa,sEntorno,sOrganizacion,sEmpleado,dFechaIngreso,iX,sCadena,iDias,0,null,iAnio,iAnio2,iDia,iMes,iDia2,iMes2,iDias);
				
				iAnio++;
				iAnio2++;
			}
		}
	}
	
	public int maximosDias(String sEntorno,String sOrganizacion,String sEmpresa,String sEmpleado,Integer iOpcion){
		//FUNCION PARA CALCULAR EL MAXIMO NUMERO DE DIAS SIN QUE ESTOS SEAN NEGATIVOS
		//opcion 0 es los por ley
		//opcion 1 es los maximos
		
    	String sSql;
    	
    	Date dFecha1,dFecha2,dFechaActual,dUltimoDia;
    	
    	Calendar cal=GregorianCalendar.getInstance();
    	Calendar cal2=GregorianCalendar.getInstance();
    	
    	int iMesActual,iAnioActual,iPeriodoCerrado,iCorresponden,iSaldoAnterior,iDiasTomados,iDiasPeriodo;
    	int iDia,iMes,iAnioInicial,iAnioFinal,iMesActual1,iAnioActual1,iDiasTomadosGlobal;
    	
    	double iDiasAnio,iDiasTrabajadosAnio,iDiasGanados,iDiasTotales,iDiasMaximos;
    	
    	dFechaActual=new Date();
    	
    	cal.setTime(dFechaActual);
    	
    	DAResultSet rs=null;
    	
    	iDiasTrabajadosAnio=0;
    	iDiasTotales=0;
    	iDiasGanados=0;
    	iDiasMaximos=0;
    	iSaldoAnterior=0;
    	iCorresponden=0;
    	iDiasTomados=0;
    	iDiasPeriodo=0;
    	iDia=0;
    	iMes=0;
    	iAnioInicial=0;
    	iDiasTomadosGlobal=0;
    	iDiasAnio=0;
    	iAnioFinal=0;
    	iMesActual1=0;
    	iAnioActual1=0;
    	
		iMesActual=cal.get(Calendar.MONTH)+1;
		iAnioActual=cal.get(Calendar.YEAR);
		
		iDiasMaximos=0;
		
		if((iMesActual-1)==0){
			iMesActual1=12;
			iAnioActual1=iAnioActual-1;
		}
		else{
			iMesActual1=iMesActual-1;
			iAnioActual1=iAnioActual;
		}
		
		iPeriodoCerrado=(iAnioActual1*100)+(iMesActual1*1);
		
		try {
			sSql=sqlDiasMaximos(iPeriodoCerrado,sEmpleado,sEntorno,sOrganizacion,sEmpresa);
			
			rs=connData.openSQL(sSql);
			
			while (rs.moveNext()) {
				iCorresponden=rs.getInt("xdiasperiodo");
				iSaldoAnterior=rs.getInt("saldo");
				iDiasPeriodo=rs.getInt("sumadias");
				iDiasTomados=rs.getInt("xdiastomados");
				iDia=rs.getInt("xdia");
				iMes=rs.getInt("xmes");
				iAnioInicial=rs.getInt("xanioinicial");
				iAnioFinal=rs.getInt("xaniofinal");
				iDiasTomadosGlobal=rs.getInt("diastomados");
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cal.set(iAnioInicial,iMes-1,iDia);
		cal2.set(iAnioFinal,iMes-1,iDia);
		
		dFecha1=cal.getTime();
		dFecha2=cal2.getTime();
		
		iDiasAnio=((dFecha2.getTime()-dFecha1.getTime())/(24*60*60*1000))+1;
		
		cal.set(iAnioActual, iMesActual-1, 1);
		
		cal.add(Calendar.DATE,-1);
		
		dUltimoDia=cal.getTime();
		
		iDiasTrabajadosAnio=((dUltimoDia.getTime()-dFecha1.getTime())/(24*60*60*1000))+1;
		
		iDiasGanados=(iDiasTrabajadosAnio*iCorresponden)/iDiasAnio;
				
		iDiasTotales=iDiasGanados-iDiasTomados+iSaldoAnterior;
		
		if(iDiasTotales<=0){
			iDiasMaximos=0;
		}
		else{
			iDiasMaximos=iDiasTotales;
		}
		
		if(iOpcion==0){
			return (int)iSaldoAnterior;
		}
		else{
			if(iOpcion==1){
				return (int)iDiasMaximos;
			}
			else{
				if(iOpcion==2){
					return (int)iDiasTomadosGlobal;
				}
				else{
					return (int)iDiasPeriodo;
				}
			}
		}
    }
	
	public double maximosDiasDoble(String sEntorno,String sOrganizacion,String sEmpresa,String sEmpleado,double dPeriodo){
		//FUNCION PARA CALCULAR EL MAXIMO NUMERO DE DIAS SIN QUE ESTOS SEAN NEGATIVOS
		//opcion 0 es los por ley
		//opcion 1 es los maximos
		
    	String sSql;
    	
    	Date dFecha1,dFecha2,dFechaActual,dUltimoDia;
    	
    	Calendar cal=GregorianCalendar.getInstance();
    	Calendar cal2=GregorianCalendar.getInstance();
    	
    	int iMesActual,iAnioActual,iPeriodoCerrado,iCorresponden,iSaldoAnterior,iDiasTomados;
    	int iDia,iMes,iAnioInicial,iAnioFinal,iMesActual1,iAnioActual1;
    	
    	double iDiasAnio,iDiasTrabajadosAnio,iDiasGanados,iDiasTotales,iDiasMaximos;
    	
    	dFechaActual=new Date();
    	
    	cal.setTime(dFechaActual);
    	
    	DAResultSet rs=null;
    	
    	iDiasTrabajadosAnio=0;
    	iDiasTotales=0;
    	iDiasGanados=0;
    	iDiasMaximos=0;
    	iSaldoAnterior=0;
    	iCorresponden=0;
    	iDiasTomados=0;
    	iDia=0;
    	iMes=0;
    	iAnioInicial=0;
    	iDiasAnio=0;
    	iAnioFinal=0;
    	iMesActual1=0;
    	iAnioActual1=0;
    	
		iMesActual=cal.get(Calendar.MONTH)+1;
		iAnioActual=cal.get(Calendar.YEAR);
		
		iDiasMaximos=0;
		
		if((iMesActual-1)==0){
			iMesActual1=12;
			iAnioActual1=iAnioActual-1;
		}
		else{
			iMesActual1=iMesActual-1;
			iAnioActual1=iAnioActual;
		}
		
		iPeriodoCerrado=(iAnioActual1*100)+(iMesActual1*1);
		
		if(dPeriodo<iPeriodoCerrado){
			iPeriodoCerrado=(int) dPeriodo;
		}
		
		try {
			sSql=sqlDiasMaximos(iPeriodoCerrado,sEmpleado,sEntorno,sOrganizacion,sEmpresa);
			
			rs=connData.openSQL(sSql);
			
			while (rs.moveNext()) {
				iCorresponden=rs.getInt("xdiasperiodo");
				iSaldoAnterior=rs.getInt("saldo");
				iDiasTomados=rs.getInt("xdiastomados");
				iDia=rs.getInt("xdia");
				iMes=rs.getInt("xmes");
				iAnioInicial=rs.getInt("xanioinicial");
				iAnioFinal=rs.getInt("xaniofinal");
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cal.set(iAnioInicial,iMes-1,iDia);
		cal2.set(iAnioFinal,iMes-1,iDia);
		
		dFecha1=cal.getTime();
		dFecha2=cal2.getTime();
		
		iDiasAnio=((dFecha2.getTime()-dFecha1.getTime())/(24*60*60*1000))+1;
		
		cal.set(iAnioActual, iMesActual-1, 1);
		
		cal.add(Calendar.DATE,-1);
		
		dUltimoDia=cal.getTime();
		
		iDiasTrabajadosAnio=((dUltimoDia.getTime()-dFecha1.getTime())/(24*60*60*1000))+1;
		
		iDiasGanados=(iDiasTrabajadosAnio*iCorresponden)/iDiasAnio;
				
		iDiasTotales=iDiasGanados-iDiasTomados+iSaldoAnterior;
		
		if(iDiasTotales<=0){
			iDiasMaximos=0;
		}
		else{
			iDiasMaximos=iDiasTotales;
		}
		
		return iDiasMaximos;
    }
	
	public int diasNegativos(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado){
		//SE OBTIENE LOS DIAS NEGATIVOS QUE TIENE EL EMPLEADO
		Integer iDias;
		String sSQL;
		
		iDias=0;
		
		DAResultSet rs=null;
		
		try {
			sSQL=sqlVacacion("sum(xdiasatomar)",sEmpleado,sEntorno,sOrganizacion,sEmpresa,"and xenviadasa is null","",2);
			rs=connData.openSQL(sSQL);

			while (rs.moveNext()) {
				iDias=rs.getInt(1);
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return iDias;
	}
	
	public void bajarDias(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,Double dCodigo,Integer iDiasMaximos,Integer iDiasATomar,Date dFechaIngreso,String sUsuarioAutoriza,String sUsuarioGenera,Date dFechaGenera,Date dFechaInicia,String sNombreEmpleado,Integer iOpcion,Integer iControlaNegativos){
		//GENERA LA VACACION EN SI, TANTO SEA VACACION NEGATIVA COMO VACACION NORMAL
		//ver dias disponibles
		//insertar en vacaciones normales
		//si es el caso insertar en vacaciones negativas
		String sSQL_existen,sCondicion,sSQL;
		
		int iSaldo,iParte,iSaldoNegativos,iDiasNegativos;
		
		Date dFechaActual=new Date();
		
		Calendar cal=GregorianCalendar.getInstance();
		Calendar cal2=GregorianCalendar.getInstance();
		
		DAResultSet rs=null;
		
		sSQL_existen="";
		sCondicion="";
		sSQL="";
				
		iParte=1;
		iSaldoNegativos=0;
		iSaldo=0;
		iDiasNegativos=0;
				
		cal.setTime(dFechaInicia);
		if(iControlaNegativos==1){
			try {
				sSQL=sqlVacacion("sum(xdiasatomar)",sEmpleado,sEntorno,sOrganizacion,sEmpresa,"and xenviadasa is null","",2);
				rs=connData.openSQL(sSQL);
	
				while (rs.moveNext()) {
					iDiasNegativos=rs.getInt(1);
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(iDiasNegativos==0){
			if(iDiasMaximos<iDiasATomar){
				iSaldo=iDiasMaximos;
				iSaldoNegativos=iDiasATomar-iDiasMaximos;
			}
			else{
				iSaldo=iDiasATomar;
			}
		}
		else{
			iSaldoNegativos=iDiasATomar;
			iSaldo=0;
		}
		
		if(iOpcion==1){
			try {
				sSQL=sqlVacacion("count(xcodigovacacion)",sEmpleado,sEntorno,sOrganizacion,sEmpresa,"and xcodigovacacion=" + DAUtils.formatValue(dCodigo,DA.DA_DT_LONG),"",1);
				rs=connData.openSQL(sSQL);

				while (rs.moveNext()) {
					iParte=rs.getInt(1)+1;
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//inserta en la base las vacaciones de los empleados dividiendo los datos en los diferentes periodos que tiene
		//el empleado generados en la empresa, se generan 30 años de vacaciones con sus diferentes caracteristicas
		try {
			sCondicion="and xsaldodias>0 ";
			sCondicion=sCondicion + "and " + DAUtils.formatValue(dFechaActual,DA.DA_DT_DATE) + ">=xfechainicia";
			
			sSQL_existen=sqlPeriodosVacaciones("xperiodovac_id, xperiodovac_des, xsaldodias",sEmpresa,sEntorno,sOrganizacion,sEmpleado,sCondicion,1,"");
			rs=connData.openSQL(sSQL_existen);

			while (rs.moveNext()) {
				if(iSaldo>0){
					if(rs.getInt("xsaldodias")>=iSaldo){
						cal2.setTime(cal.getTime());
						
						cal2.add(Calendar.DATE,iSaldo-1);
						
						insertarVacacion(1,sEmpresa,sEntorno,sOrganizacion,sEmpleado,dFechaIngreso,dCodigo,iParte,"N",null,"S",sUsuarioAutoriza,dFechaActual,sUsuarioGenera,dFechaGenera,cal.getTime(),cal2.getTime(),funcionesGenerales.fechasLargas(cal.getTime()),funcionesGenerales.fechasLargas(cal2.getTime()),iSaldo,maximosDias(sEntorno,sOrganizacion,sEmpresa,sEmpleado,0),iSaldoNegativos,maximosDias(sEntorno,sOrganizacion,sEmpresa,sEmpleado,1),rs.getString("xperiodovac_des"),rs.getDouble("xperiodovac_id"),rs.getInt("xsaldodias"),iSaldo,rs.getInt("xsaldodias")-iSaldo,sNombreEmpleado,null,null,0,null,null,null,null,null);
						
						actualizarPeriodoVacaciones(sEmpresa,sEntorno,sOrganizacion,sEmpleado,rs.getInt("xperiodovac_id"),iSaldo,1);

						iSaldo=0;
						
						cal.setTime(cal2.getTime());
						
						cal.add(Calendar.DATE,1);
					}
					else{
						cal2.setTime(cal.getTime());
						
						cal2.add(Calendar.DATE,rs.getInt("xsaldodias")-1);
						
						insertarVacacion(1,sEmpresa,sEntorno,sOrganizacion,sEmpleado,dFechaIngreso,dCodigo,iParte,"N",null,"S",sUsuarioAutoriza,dFechaActual,sUsuarioGenera,dFechaGenera,cal.getTime(),cal2.getTime(),funcionesGenerales.fechasLargas(cal.getTime()),funcionesGenerales.fechasLargas(cal2.getTime()),rs.getInt("xsaldodias"),maximosDias(sEntorno,sOrganizacion,sEmpresa,sEmpleado,0),iSaldoNegativos,maximosDias(sEntorno,sOrganizacion,sEmpresa,sEmpleado,1),rs.getString("xperiodovac_des"),rs.getDouble("xperiodovac_id"),rs.getInt("xsaldodias"),rs.getInt("xsaldodias"),rs.getInt("xsaldodias")-rs.getInt("xsaldodias"),sNombreEmpleado,null,null,0,null,null,null,null,null);
						
						actualizarPeriodoVacaciones(sEmpresa,sEntorno,sOrganizacion,sEmpleado,rs.getInt("xperiodovac_id"),rs.getInt("xsaldodias"),1);
						
						iSaldo=iSaldo-rs.getInt("xsaldodias");
						
						cal.setTime(cal2.getTime());
						
						cal.add(Calendar.DATE,1);
					}
					iParte++;
				}
			}
			rs.close();
			
			if(iSaldoNegativos>0){
				cal2.setTime(cal.getTime());
				
				cal2.add(Calendar.DATE,iSaldoNegativos-1);
				
				insertarVacacion(2,sEmpresa,sEntorno,sOrganizacion,sEmpleado,dFechaIngreso,dCodigo,iParte,"A",null,"S",sUsuarioAutoriza,dFechaActual,sUsuarioGenera,dFechaGenera,cal.getTime(),cal2.getTime(),funcionesGenerales.fechasLargas(cal.getTime()),funcionesGenerales.fechasLargas(cal2.getTime()),iSaldoNegativos,0,0,0,null,null,0,iSaldoNegativos,0-iSaldoNegativos,sNombreEmpleado,null,null,0,null,null,null,null,null);
			}
			
			iParte--;
			
			actualizarVacaciones(sEmpresa,sEntorno,sOrganizacion,sEmpleado,dCodigo,"xtotalpartes=" + DAUtils.formatValue(iParte,DA.DA_DT_INTEGER),1,"");
			
			actualizarPartesTexto(sEmpresa,sEntorno,sOrganizacion,sEmpleado,dCodigo,1);
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void subirDias(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,Double dCodigo){
		//RESTAURA LOS DIAS DE VACACIONES DEL EMPLEADO EN EL CASO DE QUE LA PERSONA QUE AUTORIZA ELIMINE ESA AUTORIZACION
		
		//seleccionar todos los datos de la solicitud ingresada y sumar los dias correspondientes
		//esos datos subir en los diferentes periodos
		String sSQL,sCondicion,sCondicion2,sCondicion3;
		
		DAResultSet rs=null;
		
		sCondicion="and xcodigovacacion=" + DAUtils.formatValue(dCodigo,DA.DA_DT_LONG) + " ";
		sCondicion2="and xpasadasaconta=" + DAUtils.formatValue(0,DA.DA_DT_INTEGER) + " ";
		sCondicion3="and xautorizada=" + DAUtils.formatValue("S",DA.DA_DT_TEXT) + " ";
		
		sCondicion=sCondicion + sCondicion2 + sCondicion3;
		
		try {
			sSQL=sqlVacacion("*",sEmpleado,sEntorno,sOrganizacion,sEmpresa,sCondicion,"",1);
			rs=connData.openSQL(sSQL);

			while (rs.moveNext()) {		
				actualizarPeriodoVacaciones(sEmpresa,sEntorno,sOrganizacion,sEmpleado,rs.getInt("xperiodovac_id"),rs.getInt("xdiasatomar"),2);
			}
			rs.close();
			
			eliminarVacacion(sEmpresa,sEntorno,sOrganizacion,sEmpleado,"S",dCodigo,sCondicion2 + sCondicion3,1);
			
			eliminarVacacion(sEmpresa,sEntorno,sOrganizacion,sEmpleado,"S",dCodigo,"and xenviadasa is null ",2);
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void subirNegativos(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,Integer iMaximo,FMEvent fmEvento){
		//PASA LOS DÍAS NEGATIVOS DE LA PARTE BAJA A LA PARTE ALTA PARA QUE SE SIGAN DESCONTANDO ESTOS DÍAS YA NORMALMENTE
		
		if(iMaximo>0){
			String sSQL,sCampos;
			Integer iDias,iDiasRegistrar,iAccion;
			Double dCodigo;
			Date dFechaInicia;
			Date dFechaActual=new Date();
			Calendar cal=GregorianCalendar.getInstance();
			
			DAResultSet rs=null;
			
			iDias=0;
			iDiasRegistrar=0;
			dCodigo=0.0;
			iAccion=-1;
			
			sCampos="";
			
			try {
				sSQL=sqlVacacion("*",sEmpleado,sEntorno,sOrganizacion,sEmpresa,"and xenviadasa is null order by xfechaautoriza asc","",2);
				rs=connData.openSQL(sSQL);

				while (rs.moveNext() && iMaximo>0){		
					iDias=rs.getInt("xdiasatomar");
					dCodigo=rs.getDouble("xcodigovacacion");
					dFechaInicia=rs.getDate("xfechainicia");
					
					cal.setTime(dFechaInicia);
					
					if(iDias>=iMaximo){
							iDiasRegistrar=iMaximo;
							iAccion=0;
					}
					else{
						if(iDias<iMaximo){
							iDiasRegistrar=iDias;
							iAccion=1;
						}
					}
					
					cal.add(Calendar.DATE,iDiasRegistrar);
					
					bajarDias(sEmpresa,sEntorno,sOrganizacion,sEmpleado,dCodigo,iMaximo,iDiasRegistrar,rs.getDate("xfechaingreso"),rs.getString("xusuarioautoriza"),connSource.getUser(),dFechaActual,dFechaInicia,rs.getString("xnombreempleado"),1,2);
					
					if(iAccion==0){
						sCampos="xdiasatomar=xdiasatomar-" + DAUtils.formatValue(iDiasRegistrar,DA.DA_DT_INTEGER) + ", ";
						sCampos=sCampos + "xdiasdescontar=xdiasdescontar-" + DAUtils.formatValue(iDiasRegistrar,DA.DA_DT_INTEGER) + ", ";
						sCampos=sCampos + "xsaldofinaldias=xsaldofinaldias+" + DAUtils.formatValue(iDiasRegistrar,DA.DA_DT_INTEGER) + ", ";
						sCampos=sCampos + "xfechainicia=" + DAUtils.formatValue(cal.getTime(),DA.DA_DT_DATE) + ", ";
						sCampos=sCampos + "xfechainiciatext=" + DAUtils.formatValue(funcionesGenerales.fechasLargas(cal.getTime()),DA.DA_DT_TEXT) + " ";
						
						actualizarVacaciones(sEmpresa,sEntorno,sOrganizacion,sEmpleado,dCodigo,sCampos,2,"and xenviadasa is null");
					}
					else{
						if(iAccion==1){
							eliminarVacacion(sEmpresa,sEntorno,sOrganizacion,sEmpleado,"S",dCodigo,"",2);
						}
					}
					
					iMaximo=0;
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if(iAccion==1 || iAccion==0){
					sSQL=sqlVacacion("xdiasatomar",sEmpleado,sEntorno,sOrganizacion,sEmpresa,"and xenviadasa is null and xcodigovacacion=" + DAUtils.formatValue(dCodigo,DA.DA_DT_LONG),"",2);
					rs=connData.openSQL(sSQL);
		
					while (rs.moveNext()){
						iDias=rs.getInt("xdiasatomar");
					}
					rs.close();
					
					actualizarVacaciones(sEmpresa,sEntorno,sOrganizacion,sEmpleado,dCodigo,"xdiasnegativos=" + DAUtils.formatValue(iDias,DA.DA_DT_INTEGER),1,"");
				}
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			mensajeError(fmEvento,"El empleado no cuenta con días disponibles para realizar esta acción, espere a cumplir un nuevo periodo y vuelvalo a intentar.","Sin días disponibles para esta acción");
		}
	}

	public void pasarNegativos(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,Integer iOpcion,Integer iMaximo,FMEvent fmEvento,double dPeriodo){
		//SUBE LOS NEGATIVOS A VACACIONES NORMALES
		if(dPeriodo!=9999999){
			String sSQL,sOrigen,sCampos,sTextoOrigen,sAnio,sRubroVacacion;
			Integer iHacer,iDias,iExiste;
			double dSueldo,dCodigo,dValor;
			Date dFechaActual=new Date();
			
			DAResultSet rs=null;
			
			dSueldo=0;
			iDias=0;
			dCodigo=0;
			dValor=0;
			iExiste=0;
			
			sAnio=("" + dPeriodo).substring(0,4);
			sOrigen="";
			sTextoOrigen="";
			sCampos="";
			sRubroVacacion="";
			
			iHacer=1;
			
			try {
				sSQL=sqlDatosCargoEmple(sEmpresa,sEntorno,sOrganizacion,sEmpleado,"","xsueldo","and xfechafinal is null",1);
				rs=connData.openSQL(sSQL);
	
				while (rs.moveNext()){
					dSueldo=rs.getDouble("xsueldo");
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				sSQL=sqlVacacion("*",sEmpleado,sEntorno,sOrganizacion,sEmpresa,"and xenviadasa is null order by xcodigovacacion asc","",2);
				rs=connData.openSQL(sSQL);
	
				while (rs.moveNext() && iHacer==1){
					iDias=rs.getInt("xdiasatomar");
					dCodigo=rs.getDouble("xcodigovacacion");
					iHacer=0;
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				sSQL=sqlParametros(sEmpresa,sEntorno,sOrganizacion,"","xrubrovacacion","");
				rs=connData.openSQL(sSQL);
	
				while (rs.moveNext()){
					sRubroVacacion=rs.getString(1);
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			dValor=(iDias*dSueldo/30);
			
			switch(iOpcion){
				case 1:
					sOrigen="R";
					sTextoOrigen="Rol";
					break;
				case 2:
					sOrigen="L";
					sTextoOrigen="Liquidación";
					break;
			}
			
			try {
				sSQL=sqlNovedad("count(xempresa_id)",sEmpleado,sEntorno,sOrganizacion,sEmpresa,sAnio,dPeriodo,sOrigen,sRubroVacacion,"","",1);
				rs=connData.openSQL(sSQL);
	
				while (rs.moveNext()){
					iExiste=rs.getInt(1);
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(iExiste==0){
				//insertar en la tabla de los valores con la opcion R o L, tanto en la cabecera como en el detalle
				insertarNovedad(sEmpresa,sEntorno,sOrganizacion,sEmpleado,sAnio,dPeriodo,sOrigen,sRubroVacacion,dValor,connSource.getUser(),dFechaActual,"Días negativos enviados a " + sTextoOrigen,1);
					
				insertarNovedad(sEmpresa,sEntorno,sOrganizacion,sEmpleado,sAnio,dPeriodo,sOrigen,sRubroVacacion,dValor,connSource.getUser(),dFechaActual,"Días negativos enviados a " + sTextoOrigen,2);
			}
			else{
				if(iExiste>0){
					//actualizar la cabecera de novedades y despues insertar el registro
					actualizarNovedad(sEmpresa,sEntorno,sOrganizacion,sEmpleado,sAnio,dPeriodo,sOrigen,sRubroVacacion,dValor);

					insertarNovedad(sEmpresa,sEntorno,sOrganizacion,sEmpleado,sAnio,dPeriodo,sOrigen,sRubroVacacion,dValor,connSource.getUser(),dFechaActual,"Días negativos enviados a " + sTextoOrigen,2);
				}
			}
			
			sCampos="xenviadasa=" + DAUtils.formatValue(sTextoOrigen,DA.DA_DT_TEXT) + ", ";
			sCampos=sCampos + "xusuarioenvia=" + DAUtils.formatValue(connSource.getUser(),DA.DA_DT_TEXT) + ", ";
			sCampos=sCampos + "xfechaenvia=" + DAUtils.formatValue(dFechaActual,DA.DA_DT_DATE) + " ";
			
			actualizarVacaciones(sEmpresa,sEntorno,sOrganizacion,sEmpleado,dCodigo,sCampos,2,"");
		}
		else{
			mensajeError(fmEvento,"Debe ingresar el periodo al que va a cargar estos valores","Error, periodo no ingresado");
		}
	}
	
	public void devengarVacaciones(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,FMEvent fmEvento,double dPeriodo,String sOrigenProceso){
		if(dPeriodo!=9999999){
			//devenga las vacaciones de los empleados, en caso de ser todas las vacaciones de la empresa como ser de un solo individuo
			String sSQL,sCondicion,sEmpleado2,sRubroVacacion;
			
			Integer iMes,iAnio,iPeriodoVac,iMesInicio,iAnioInicio,iMesFin,iAnioFin,iDiaInicio,iDiaFin,iPeriodoInicial,iPeriodoFinal,iDiasCorresponden,iDiasTomar,iParte,iDiasAnio;
			
			Double dValorProvisionado1,dValorProvisionado2,dValorProvisionado3,dValorDevengar,iDias,dCodigoVacacion;
			
			Calendar cal=Calendar.getInstance();
			Calendar cal2=Calendar.getInstance();
			
			Date dFecha1,dFecha2,dFechaActual;
			
			SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
			
			DAResultSet rs,rs2;
		
			rs=null;
			rs2=null;
			
			sSQL="";
			sEmpleado2="";
			sCondicion="and xperiododevengado is null ";
			sRubroVacacion="";
			
			iDias=0.0;
			iDiasAnio=0;
			iDiasCorresponden=0;
			iDiaInicio=0;
			iMesInicio=0;
			iAnioInicio=0;
			iDiaFin=0;
			iMesFin=0;
			iAnioFin=0;
			iPeriodoVac=0;
			iPeriodoInicial=0;
			iPeriodoFinal=0;
			iDiasTomar=0;
			
			dValorProvisionado1=0.0;
			dValorProvisionado2=0.0;
			dValorProvisionado3=0.0;
			dValorDevengar=0.0;
			dCodigoVacacion=0.0;
			
			dFechaActual=new Date();
			
			try {
				sSQL=sqlParametros(sEmpresa,sEntorno,sOrganizacion,"","xrubrovacacion","");
				rs=connData.openSQL(sSQL);
				
				while (rs.moveNext()){
					sRubroVacacion=rs.getString(1);
				}
			} catch (DAException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//sRubroVacacion="@RPVAC";
			
			if(String.valueOf(dPeriodo).length()>0){
				//sacar los periodos que existen
				iMes=Integer.parseInt(String.valueOf(dPeriodo).substring(4,6));
				iAnio=Integer.parseInt(String.valueOf(dPeriodo).substring(0,4));
				
				if((iMes+1)==13){
					iMes=1;
					iAnio++;
				}
				else{
					iMes++;
				}
				
				try {
					cal.setTime(formatter.parse("01/" + iMes + "/" + iAnio));
					cal.add(Calendar.DATE,-1);
					
					sCondicion=sCondicion + "and xfechainicia<=" + DAUtils.formatValue(cal.getTime(),DA.DA_DT_DATE) + " ";
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				sSQL=sqlVacacion("*",sEmpleado,sEntorno,sOrganizacion,sEmpresa,sCondicion + "order by xempleado_id,xcodigovacacion ","",1);
				rs=connData.openSQL(sSQL);
				
				while (rs.moveNext()){
					if(rs.getString("xempleado_id").length()>0){
						dCodigoVacacion=rs.getDouble("xcodigovacacion");
						sEmpleado2=rs.getString("xempleado_id");
						iPeriodoVac=rs.getInt("xperiodovac_id");
						iDiasTomar=rs.getInt("xdiasatomar");
		
						sSQL=sqlPeriodosVacaciones("xanioinicial,xmes,xdia,xaniofinal,xmesfinal,xdiafinal,xdiasperiodo",sEmpresa,sEntorno,sOrganizacion,sEmpleado2,"and xperiodovac_id=" + DAUtils.formatValue(iPeriodoVac,DA.DA_DT_INTEGER),0,"");
						rs2=connData.openSQL(sSQL);
						
						while (rs2.moveNext()){
							iDiasCorresponden=rs2.getInt("xdiasperiodo");
							iDiaInicio=rs2.getInt("xdia");
							iMesInicio=rs2.getInt("xmes");
							iAnioInicio=rs2.getInt("xanioinicial");
							iDiaFin=rs2.getInt("xdiafinal");
							iMesFin=rs2.getInt("xmesfinal");
							iAnioFin=rs2.getInt("xaniofinal");
						}
						rs2.close();
						
						iPeriodoInicial=(iAnioInicio*100)+(iMesInicio*1);
						iPeriodoFinal=(iAnioFin*100)+(iMesFin*1);
						
						iMes=iMesInicio;
						iAnio=iAnioInicio;
						
						if((iMes+1)>13){
							iMes=1;
							iAnio++;
						}
						else{
							iMes++;
						}
						
						try {
							cal.setTime(formatter.parse(iDiaInicio + "/" + iMesInicio + "/" + iAnioInicio));
							
							cal2.setTime(formatter.parse("01/" + iMes + "/" + iAnio));
							cal2.add(Calendar.DATE,-1);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						dFecha1=cal.getTime();
						dFecha2=cal2.getTime();
						
						sSQL=sqlPagoNomina(sEmpresa,sEntorno,sOrganizacion,sEmpleado2,"a.","sum(a.xvalor)","and a.xperiodo_id in (select b.xperiodo_id from imp.gp_periodos b where b.xperiodo=" + DAUtils.formatValue(iPeriodoInicial,DA.DA_DT_LONG) + ")","R",sRubroVacacion);
						rs2=connData.openSQL(sSQL);
						
						while (rs2.moveNext()){
							dValorProvisionado1=rs2.getDouble(1);
						}
						rs2.close();
						
						if(iPeriodoVac!=1){
							iDias=(double) (((dFecha2.getTime()-dFecha1.getTime())/(24*60*60*1000))+1);
						
							dValorProvisionado1=(dValorProvisionado1*iDias)/cal2.get(Calendar.DAY_OF_MONTH);
						}
						
						sSQL=sqlPagoNomina(sEmpresa,sEntorno,sOrganizacion,sEmpleado2,"a.","sum(a.xvalor)","and a.xperiodo_id in (select b.xperiodo_id from imp.gp_periodos b where b.xperiodo between " + DAUtils.formatValue(iPeriodoInicial+1,DA.DA_DT_LONG) + " and " + DAUtils.formatValue(iPeriodoFinal-1,DA.DA_DT_LONG) + ")","R",sRubroVacacion);
						rs2=connData.openSQL(sSQL);
						
						while (rs2.moveNext()){
							dValorProvisionado2=rs2.getDouble(1);
						}
						rs2.close();
						
						iMes=iMesFin;
						iAnio=iAnioFin;
						
						if((iMes+1)>13){
							iMes=1;
							iAnio++;
						}
						else{
							iMes++;
						}
						
						try {
							cal.setTime(formatter.parse(iDiaFin + "/" + iMesFin + "/" + iAnioFin));
							
							cal2.setTime(formatter.parse("01/" + iMes + "/" + iAnio));
							cal2.add(Calendar.DATE,-1);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						dFecha1=cal.getTime();
						dFecha2=cal2.getTime();
						
						sSQL=sqlPagoNomina(sEmpresa,sEntorno,sOrganizacion,sEmpleado2,"a.","sum(a.xvalor)","and a.xperiodo_id in (select b.xperiodo_id from imp.gp_periodos b where b.xperiodo=" + DAUtils.formatValue(iPeriodoFinal,DA.DA_DT_LONG) + ")","R",sRubroVacacion);
						rs2=connData.openSQL(sSQL);
						
						while (rs2.moveNext()){
							dValorProvisionado3=rs2.getDouble(1);
						}
						rs2.close();
						
						if(dFecha1.equals(dFecha2)){
							iDias=(double) cal.get(Calendar.DAY_OF_MONTH);
						}
						else{
							iDias=(double) (((dFecha2.getTime()-dFecha1.getTime())/(24*60*60*1000))+1);
						}
						
						dValorProvisionado3=(dValorProvisionado3*iDias)/cal.get(Calendar.DAY_OF_MONTH);
						
						dValorProvisionado3=dValorProvisionado1+dValorProvisionado2+dValorProvisionado3;
						
						iMes=Integer.parseInt(String.valueOf(dPeriodo).substring(4,6));
						iAnio=Integer.parseInt(String.valueOf(dPeriodo).substring(0,4));
						
						if((iMes+1)>13){
							iMes=1;
							iAnio++;
						}
						else{
							iMes++;
						}
						
						try {
							cal.setTime(formatter.parse("01/" + iMes + "/" + iAnio));
							cal.add(Calendar.DATE,-1);
							
							cal2.setTime(formatter.parse(iDiaInicio + "/"+ iMesInicio +"/" + iAnioInicio));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						dFecha1=cal.getTime();
						dFecha2=cal2.getTime();
						
						iDias=(double) (((dFecha1.getTime()-dFecha2.getTime())/(24*60*60*1000))+1);
		
						try {
							cal.setTime(formatter.parse(iDiaInicio + "/"+ iMesInicio +"/" + iAnioInicio));
							
							cal2.setTime(formatter.parse(iDiaFin + "/"+ iMesFin +"/" + iAnioFin));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						dFecha1=cal.getTime();
						dFecha2=cal2.getTime();
						
						if((((dFecha2.getTime()-dFecha1.getTime())/(24*60*60*1000))+1)==366){
							iDiasAnio=366;
						}
						else{
							iDiasAnio=365;
						}
						
						if(iDias<iDiasAnio){
							dValorProvisionado3=(dValorProvisionado3*iDiasTomar)/((iDias*iDiasCorresponden)/iDiasAnio);
						}
						else{
							dValorProvisionado3=(dValorProvisionado3*iDiasTomar)/iDiasCorresponden;
						}
		
						iParte=1;
		
						sSQL=sqlPagoNomina(sEmpresa,sEntorno,sOrganizacion,sEmpleado2,"a.","*","and a.xperiodo_id in (select b.xperiodo_id from imp.gp_periodos b where b.xperiodo between " + DAUtils.formatValue(iPeriodoInicial,DA.DA_DT_LONG) + " and " + DAUtils.formatValue(iPeriodoFinal,DA.DA_DT_LONG) + ") order by a.xperiodo_id","R",sRubroVacacion);
						rs2=connData.openSQL(sSQL);
						
						while (rs2.moveNext() && dValorProvisionado3>0){
							dValorProvisionado1=(rs2.getDouble("xvalor")*1.0);
							dValorProvisionado2=(rs2.getDouble("xvalordevengado")*1.0);
							
							dValorDevengar=dValorProvisionado1-dValorProvisionado2;
							
							if(dValorDevengar>0){
								if(dValorProvisionado3-dValorDevengar>=0){
									actualizarPagoNomina(sEmpresa,sEntorno,sOrganizacion,sEmpleado2,"xvalordevengado=" + DAUtils.formatValue((dValorProvisionado2+dValorDevengar),DA.DA_DT_CURRENCY),"and xperiodo_id=" + DAUtils.formatValue(rs2.getDouble("xperiodo_id"),DA.DA_DT_LONG),"R",sRubroVacacion);
									
									insertarDevVac(rs2.getDouble("xperiodo_id"),rs2.getInt("xanio_id"),sEmpresa,sEntorno,sOrganizacion,sRubroVacacion,"R",sEmpleado2,rs.getDouble("xcodigovacacion"),iParte,dValorDevengar,rs2.getString("xdepartamento_id"),rs2.getString("xdepartamento_nom"),rs2.getString("xseccion_id"),rs2.getString("xseccion_nom"),rs2.getString("xcargo_id"),rs2.getString("xcargo_nom"),rs2.getString("xcentrocosto_id"),rs2.getDate("xfechaingreso"),connSource.getUser(),dFechaActual,rs2.getString("xcuentadebe"),rs2.getString("xcuentahaber"),rs2.getDate("xfechafinal"),sOrigenProceso,dPeriodo,rs.getString("xnombreempleado"),"N");
									
									dValorProvisionado3=dValorProvisionado3-(dValorDevengar);
								}
								else{
									actualizarPagoNomina(sEmpresa,sEntorno,sOrganizacion,sEmpleado2,"xvalordevengado=" + DAUtils.formatValue((dValorProvisionado2+dValorProvisionado3),DA.DA_DT_CURRENCY),"and xperiodo_id=" + DAUtils.formatValue(rs2.getDouble("xperiodo_id"),DA.DA_DT_LONG),"R",sRubroVacacion);
									
									insertarDevVac(rs2.getDouble("xperiodo_id"),rs2.getInt("xanio_id"),sEmpresa,sEntorno,sOrganizacion,sRubroVacacion,"R",sEmpleado2,rs.getDouble("xcodigovacacion"),iParte,dValorProvisionado3,rs2.getString("xdepartamento_id"),rs2.getString("xdepartamento_nom"),rs2.getString("xseccion_id"),rs2.getString("xseccion_nom"),rs2.getString("xcargo_id"),rs2.getString("xcargo_nom"),rs2.getString("xcentrocosto_id"),rs2.getDate("xfechaingreso"),connSource.getUser(),dFechaActual,rs2.getString("xcuentadebe"),rs2.getString("xcuentahaber"),rs2.getDate("xfechafinal"),sOrigenProceso,dPeriodo,rs.getString("xnombreempleado"),"N");
									
									dValorProvisionado3=0.0;
								}
								
								iParte++;
							}
						}
						rs2.close();
						
						actualizarVacaciones(sEmpresa,sEntorno,sOrganizacion,sEmpleado2,dCodigoVacacion,"xperiododevengado=" + DAUtils.formatValue(dPeriodo,DA.DA_DT_LONG) + ", xfechadevengada=" + DAUtils.formatValue(dFechaActual,DA.DA_DT_DATE),1,"and xperiododevengado is null");
					}
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			mensajeError(fmEvento,"Debe ingresar el periodo que se va a devengar","Error, periodo no ingresado");
		}
	}
	
	public void reversarVacaciones(String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,FMEvent fmEvento,double dPeriodo,String sOrigenProceso){
		//REVERSA LAS VACACIONES DEVENGADAS YA SEA POR LIQUIDACION O POR PROCESO NORMAL DE ROL
		if(dPeriodo!=9999999){
			String sSQL;
			
			DAResultSet rs;
			
			rs=null;
			
			Double dValor1;
			
			dValor1=0.0;
			
			try {
				sSQL=sqlDevVac(sEmpresa,sEntorno,sOrganizacion,sEmpleado,"","*","and xcontabilizadas=" + DAUtils.formatValue("N",DA.DA_DT_TEXT) + " order by xempleado_id,xcodigovacacion desc,xparte desc",dPeriodo,sOrigenProceso);
				rs=connData.openSQL(sSQL);
				
				while(rs.moveNext()){
					if(rs.getString("xempleado_id").length()>0){
						dValor1=(rs.getDouble("xvalor")*1.0);
						
						actualizarPagoNomina(sEmpresa,sEntorno,sOrganizacion,rs.getString("xempleado_id"),"xvalordevengado=xvalordevengado-" + DAUtils.formatValue(dValor1,DA.DA_DT_CURRENCY),"and xperiodo_id=" + DAUtils.formatValue(rs.getDouble("xperiodo_id"),DA.DA_DT_LONG),rs.getString("xtipoorigen"),rs.getString("xrubro_id"));
						
						eliminarDevVac(sEmpresa,sEntorno,sOrganizacion,rs.getString("xempleado_id"),"","and xcontabilizadas=" + DAUtils.formatValue("N",DA.DA_DT_TEXT) + " and xparte=" + DAUtils.formatValue(rs.getInt("xparte"),DA.DA_DT_INTEGER) + " and xcodigovacacion=" + DAUtils.formatValue(rs.getDouble("xcodigovacacion"),DA.DA_DT_LONG) + " and xperiodo_id="+ DAUtils.formatValue(rs.getDouble("xperiodo_id"),DA.DA_DT_LONG),dPeriodo,sOrigenProceso);
						
						actualizarVacaciones(sEmpresa,sEntorno,sOrganizacion,rs.getString("xempleado_id"),rs.getDouble("xcodigovacacion"),"xperiododevengado=null, xfechadevengada=null",1,"and xperiododevengado=" + DAUtils.formatValue(dPeriodo,DA.DA_DT_LONG));
					}
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			mensajeError(fmEvento,"Debe ingresar el periodo que se va a devengar","Error, periodo no ingresado");
		}
	}
	
	public void imprimirPermiso(Session session,String sEmpleado,String sEmpresa,String sOrganizacion,String sEntorno,double dCodigoVacacion) throws OTException {
		//funcion para imprimir los permisos de vacaciones
		String sCondicion,sNombre,sSQL;
		
		DAResultSet rs;
		
		rs=null;
		
		sNombre="";
		sSQL="";
		
		try {
			sSQL=sqlPermisoVacacion("distinct xnombreempleado",sEmpleado,sEntorno,sOrganizacion,sEmpresa,"","");
			rs=connData.openSQL(sSQL);
			
			while(rs.moveNext()){
				sNombre=rs.getString(1);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sCondicion="xcodigovacacion=" + DAUtils.formatValue(dCodigoVacacion,DA.DA_DT_LONG) + " ";
		sCondicion=sCondicion + "and xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
		sCondicion=sCondicion + "and xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
		sCondicion=sCondicion + "and xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
		sCondicion=sCondicion + "and xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
		
		REPrint MyREPrint=new REPrint(session,"gp_solicitudvacacion");
		MyREPrint.setDeviceType(RE.RE_DEVICE_SCREEN);
		MyREPrint.setDevice("Solicitud vacaciones_" + String.valueOf(dCodigoVacacion).substring(0,String.valueOf(dCodigoVacacion).length()-2) + "_" + sEmpleado + "_" + sNombre + ".pdf");
		MyREPrint.setFormat(RE.RE_FORMAT_PDF);
		REConfiguration config=MyREPrint.getConfiguration("SolicitudVacacion", true);
		
        config.clearConditions();
        config.setCondition(sCondicion);

		MyREPrint.setOpenMode(REPrint.OPEN_PRINT);
		MyREPrint.print(config);
		MyREPrint=null;
		
		MyREPrint=new REPrint(session,"gp_solicitudvacacion");
		MyREPrint.setDeviceType(RE.RE_DEVICE_FILE);
		MyREPrint.setDevice("Solicitud vacaciones_" + String.valueOf(dCodigoVacacion).substring(0,String.valueOf(dCodigoVacacion).length()-2) + "_" + sEmpleado + "_" + sNombre + ".pdf");
		MyREPrint.setFormat(RE.RE_FORMAT_PDF);
		config=MyREPrint.getConfiguration("SolicitudVacacion", true);
		
        config.clearConditions();
        config.setCondition(sCondicion);

		MyREPrint.setOpenMode(REPrint.OPEN_PRINT);
		MyREPrint.print(config);
		MyREPrint=null;
	}
	
	public void calcularSaldosVacaciones(Session session,String sEmpresa,String sOrganizacion,String sEntorno,Integer iAnio,double dPeriodo,FMObject fmObject){
		String sSQL,sNombreEmpleado,sNombreCargo,sRubroVacaciones;
		
		DAResultSet rs,rs2;
		
		double dValor;
		
		int iHacer;
		
		iHacer=0;
		dValor=0.0;
		
		rs=null;
		rs2=null;
		
		sNombreEmpleado="";
		sNombreCargo="";
		sRubroVacaciones="";
		
		try {
			sSQL=sqlReporteVacacion(sEmpresa,sEntorno,sOrganizacion,iAnio,dPeriodo,"","count(xanio_id)","and xusuariogenera is not null",1,1);
			rs=connData.openSQL(sSQL);
			
			while(rs.moveNext()){
				if(rs.getInt(1)>0){
					if(fmObject.showMessageText("Se eliminará el reporte anteriormente generado. ¿Desea continuar?." , "Aceptar/Visualizar")==1){
						sSQL=sqlReporteVacacion(sEmpresa,sEntorno,sOrganizacion,iAnio,dPeriodo,"","count(xanio_id)","and xusuariogenera is null",2,1);
						connData.execSQL(sSQL);
						
						sSQL=sqlReporteVacacion(sEmpresa,sEntorno,sOrganizacion,iAnio,dPeriodo,"","count(xanio_id)","and xusuariogenera is null",2,2);
						connData.execSQL(sSQL);
						
						insertarReporteDias(sEntorno,sOrganizacion,sEmpresa,"",2,iAnio,dPeriodo,null,null,0,0,0,null,null);
						
						iHacer=1;
					}
				}
				else{
					insertarReporteDias(sEntorno,sOrganizacion,sEmpresa,"",2,iAnio,dPeriodo,null,null,0,0,0,null,null);
					
					iHacer=1;
				}
			}
			
			if(iHacer==1){
				sSQL=sqlDatosCargoEmple(sEmpresa,sEntorno,sOrganizacion,"","","*","and xfechafinal is null",0);
				rs=connData.openSQL(sSQL);
				
				while(rs.moveNext()){
					
					try {
						sSQL=sqlParametros(sEmpresa,sEntorno,sOrganizacion,"","xrubrovacacion","");
						rs2=connData.openSQL(sSQL);
						while(rs2.moveNext()){
							sRubroVacaciones=rs2.getString("xrubrovacacion");
						}
						rs2.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//sRubroVacaciones="@RPVAC";
					
					try {
						sSQL=sqlEmpleados(sEmpresa,sEntorno,sOrganizacion,rs.getString("xempleado_id"),"","xnombrecompleto");
						rs2=connData.openSQL(sSQL);
						while(rs2.moveNext()){
							sNombreEmpleado=rs2.getString("xnombrecompleto");
						}
						rs2.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						sSQL=sqlDatosCargo(sEmpresa,sEntorno,sOrganizacion,rs.getString("xcargo_id"),"","xdescripcion","");
						rs2=connData.openSQL(sSQL);
						while(rs2.moveNext()){
							sNombreCargo=rs2.getString("xdescripcion");
						}
						rs2.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						sSQL=sqlPagoNomina(sEmpresa,sEntorno,sOrganizacion,rs.getString("xempleado_id"),"","xvalor,xvalordevengado","","R",sRubroVacaciones);
						rs2=connData.openSQL(sSQL);
						while(rs2.moveNext()){
							dValor=dValor+(rs2.getDouble("xvalor")-rs2.getDouble("xvalordevengado"));
						}
						rs2.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					insertarReporteDias(sEntorno,sOrganizacion,sEmpresa,rs.getString("xempleado_id"),1,iAnio,dPeriodo,rs.getDate("xfechainicial"),rs.getString("xcargo_id"),dValor,maximosDiasDoble(sEntorno,sOrganizacion,sEmpresa,rs.getString("xempleado_id"),dPeriodo),this.maximosDias(sEntorno,sOrganizacion,sEmpresa,rs.getString("xempleado_id"),0),sNombreEmpleado,sNombreCargo);
				}
				rs.close();
				
				fmObject.showMessageText("Realizado.","Aceptar");
			}
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reporteSaldoVacacaciones(Session session,String sEmpresa,String sOrganizacion,String sEntorno){
		String sSQL,sNombreEmpleado,sNombreCargo,sEmpleado,sCargoId;
		
		DAResultSet rs,rs2;
		
		Date dFechaActual,dFechaInicio;
		
		Double dDiasMaximos,dDiasGanados;
		
		Integer iDiasTomados;
		
		dFechaActual=new Date();
		dFechaInicio=new Date();
		
		rs=null;
		rs2=null;
		
		sSQL="";
		sNombreCargo="";
		sNombreEmpleado="";
		sEmpleado="";
		sCargoId="";
		
		dDiasMaximos=0.0;
		dDiasGanados=0.0;
		iDiasTomados=0;
		
		try {
			eliminarSaldoVacacion(sEmpresa,sEntorno,sOrganizacion,"","");
			
			sSQL=sqlDatosCargoEmple(sEmpresa,sEntorno,sOrganizacion,"","","*","and xfechafinal is null",0);
			rs=connData.openSQL(sSQL);
			
			while(rs.moveNext()){
				sEmpleado=rs.getString("xempleado_id");
				sCargoId=rs.getString("xcargo_id");
				dDiasMaximos=maximosDiasDoble(sEntorno,sOrganizacion,sEmpresa,sEmpleado,999999);
				dDiasGanados=(double)maximosDias(sEntorno,sOrganizacion,sEmpresa,sEmpleado,3);
				iDiasTomados=maximosDias(sEntorno,sOrganizacion,sEmpresa,sEmpleado,2);
				
				try {
					sSQL=sqlEmpleados(sEmpresa,sEntorno,sOrganizacion,sEmpleado,"","xnombrecompleto");
					rs2=connData.openSQL(sSQL);
					while(rs2.moveNext()){
						sNombreEmpleado=rs2.getString("xnombrecompleto");
					}
					rs2.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					sSQL=sqlEmpleados(sEmpresa,sEntorno,sOrganizacion,sEmpleado,"","xfechaingreso");
					rs2=connData.openSQL(sSQL);
					while(rs2.moveNext()){
						dFechaInicio=rs2.getDate("xfechaingreso");
					}
					rs2.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					sSQL=sqlDatosCargo(sEmpresa,sEntorno,sOrganizacion,sCargoId,"","xdescripcion","");
					rs2=connData.openSQL(sSQL);
					while(rs2.moveNext()){
						sNombreCargo=rs2.getString("xdescripcion");
					}
					rs2.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				insertarSaldoDias(sEntorno,sEmpresa,sOrganizacion,sEmpleado,dFechaInicio,dDiasMaximos+iDiasTomados,dDiasGanados,sNombreEmpleado,sCargoId,sNombreCargo,dFechaActual,dDiasMaximos,iDiasTomados);
			}
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}