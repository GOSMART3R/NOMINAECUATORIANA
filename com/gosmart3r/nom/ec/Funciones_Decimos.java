package com.gosmart3r.nom.ec;

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
import com.unit4.karat.session.Session;

public class Funciones_Decimos {
	
	//FUNCIONES PARA LA PARTE DE DÉCIMOS
		private DAConnectionSource connSource; // Acceso a sdic
		private DAConnection connData; // Acceso a datos
	
	public Funciones_Decimos(Session session)  {
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
	}
	
	public void mensajeError(FMEvent fmEvento,String sMensaje,String sTitulo){
		//PRESENTA MENSAJE DE ERROR EN EL FORM OCUPADO
		fmEvento.setCancel(FMEvent.CANCEL_NO_MESSAGE);
		fmEvento.setMessageTrace("" + sMensaje);
		fmEvento.setMessage("ShowError " + sTitulo);
		fmEvento.setMessageFlags(com.unit4.karat.form.FMEvent.MessageFlags.MESSAGE_INTEGRATED.getCode() + com.unit4.karat.form.FMEvent.MessageFlags.MESSAGE_ERROR.getCode());
	}
	
	public String sSqlPagoNomina(String sCampos,String sCondicion,String sEmpresa,String sEntorno,String sOrganizacion,String sRubro,Integer iPeriodoInicial,Integer iPeriodoFinal,Integer iAnioInicial,Integer iAnioFinal,String sPrefijo){
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
				sSQL_o=sSQL_o + connSource.translateTable("gp_pagonomina") + " " + sPrefijo2 + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + sPrefijo + "xperiodo_id between " + DAUtils.formatValue(iPeriodoInicial,DA.DA_DT_INTEGER) + " and " + DAUtils.formatValue(iPeriodoFinal,DA.DA_DT_INTEGER) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xanio_id between " + DAUtils.formatValue(iAnioInicial,DA.DA_DT_INTEGER) + " and " + DAUtils.formatValue(iAnioFinal,DA.DA_DT_INTEGER) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xfechafinal is null ";
				if(sRubro.length()>0){
					sSQL_o=sSQL_o + "and " + sPrefijo + "xrubro_id=" + DAUtils.formatValue(sRubro,DA.DA_DT_TEXT) + " ";
				}
				sSQL_o=sSQL_o + sCondicion + " ";
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
	}
	
	public String sSqlParametros(String sCampos,String sCondicion,String sEmpresa,String sEntorno,String sOrganizacion,String sPrefijo){
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
	
	public String sSqlConstantes(String sCampos,String sCondicion,String sEmpresa,String sEntorno,String sOrganizacion,String sPrefijo,String sConstante,Double dAnio){
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
				sSQL_o=sSQL_o + connSource.translateTable("gp_constantesxanio") + " " + sPrefijo2 + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + sPrefijo + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xconstante_id=" + DAUtils.formatValue(sConstante,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xanio_id=" + DAUtils.formatValue(dAnio,DA.DA_DT_LONG) + " ";
				sSQL_o=sSQL_o + sCondicion + " ";
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
	}
	
	public String sSqlAsistencia(String sCampos,String sCondicion,String sEmpresa,String sEntorno,String sOrganizacion,String sTipoAsistencia,Integer iPeriodoInicial,Integer iPeriodoFinal,Integer iAnioInicial,Integer iAnioFinal,String sPrefijo,String sEmpleado){
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
				sSQL_o=sSQL_o + connSource.translateTable("gp_asistencias") + " " + sPrefijo2 + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + sPrefijo + "xperiodo_id between " + DAUtils.formatValue(iPeriodoInicial,DA.DA_DT_INTEGER) + " and " + DAUtils.formatValue(iPeriodoFinal,DA.DA_DT_INTEGER) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xanio_id between " + DAUtils.formatValue(iAnioInicial,DA.DA_DT_INTEGER) + " and " + DAUtils.formatValue(iAnioFinal,DA.DA_DT_INTEGER) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xfechafinal is null ";
				sSQL_o=sSQL_o + "and " + sPrefijo + "xtipoasistencia_id=" + DAUtils.formatValue(sTipoAsistencia,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + sCondicion + " ";
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
	}
	
	public String sSqlRetenciones(String sCampos,String sCondicion,String sEmpresa,String sEntorno,String sOrganizacion,String sEmpleado,String sPrefijo){
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
				sSQL_o=sSQL_o + connSource.translateTable("gp_retenciones") + " " + sPrefijo2 + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xempleado_id=" + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xestadoretencion=" + DAUtils.formatValue("A",DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + sCondicion + " ";
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
	}
	
	
	public String sSqlEmpleados(String sCondicion,String sEmpresa,String sEntorno,String sOrganizacion){
		String sSQL_o;
		
		sSQL_o="";
		
		try {
			sSQL_o="select "; 
				sSQL_o=sSQL_o + "distinct a.xempleado_id, ";
				sSQL_o=sSQL_o + "a.xcargo_id, ";
				sSQL_o=sSQL_o + "b.xdescripcion xcargo_desc, ";
				sSQL_o=sSQL_o + "a.xdepartamento_id, ";
				sSQL_o=sSQL_o + "c.xnombre xdepartamento_desc, ";
				sSQL_o=sSQL_o + "a.xseccion_id, ";
				sSQL_o=sSQL_o + "d.xnombre xseccion_desc, ";
				sSQL_o=sSQL_o + "a.xnombrecompleto, ";
				sSQL_o=sSQL_o + "a.xcentrocosto_id, ";
				sSQL_o=sSQL_o + "e.xnombre xcentrocosto_desc, ";
				sSQL_o=sSQL_o + "a.xfechaingreso, ";
				sSQL_o=sSQL_o + "a.xcontrato_id, ";
				sSQL_o=sSQL_o + "f.xdescripcion xcontrato_desc, ";
				sSQL_o=sSQL_o + "a.xadecimoc, ";
				sSQL_o=sSQL_o + "a.xadecimot, ";
				sSQL_o=sSQL_o + "a.xsueldo, ";
				sSQL_o=sSQL_o + "g.xcorreo, ";
				sSQL_o=sSQL_o + "h.xformasdepago, ";
				sSQL_o=sSQL_o + "h.xbanco_id, ";
				sSQL_o=sSQL_o + "h.xtipocuenta, ";
				sSQL_o=sSQL_o + "h.xcuenta, ";
				sSQL_o=sSQL_o + "i.xnombre xbanco_desc, ";
				sSQL_o=sSQL_o + "( ";
					sSQL_o=sSQL_o + "select "; 
						sSQL_o=sSQL_o + "count(j.xempleado_id) ";
					sSQL_o=sSQL_o + "from ";
						sSQL_o=sSQL_o + "imp.gp_minusvalias j ";
					sSQL_o=sSQL_o + "where ";
						sSQL_o=sSQL_o + "j.xempleado_id=a.xempleado_id "; 
						sSQL_o=sSQL_o + "and j.xempresa_id=a.xempresa_id  ";
						sSQL_o=sSQL_o + "and j.xorganizacion_id=a.xorganizacion_id ";
						sSQL_o=sSQL_o + "and j.xentorno_id=a.xentorno_id ";
						sSQL_o=sSQL_o + "and xminusvalia=" + DAUtils.formatValue("S",DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + ") xdiscapacidad, ";
				sSQL_o=sSQL_o + "g.xsexo, ";
				sSQL_o=sSQL_o + "g.xprimernombre, ";
				sSQL_o=sSQL_o + "g.xsegundonombre, ";
				sSQL_o=sSQL_o + "g.xprimerapellido, ";
				sSQL_o=sSQL_o + "g.xsegundoapellido, ";
				sSQL_o=sSQL_o + "b.xsectorial, ";
				sSQL_o=sSQL_o + "f.xparcial, ";
				sSQL_o=sSQL_o + "f.xhorassemana, ";
				sSQL_o=sSQL_o + "f.xhorasdiaria, ";
				sSQL_o=sSQL_o + "b.xsectorial, ";
				sSQL_o=sSQL_o + "f.xparcial, ";
				sSQL_o=sSQL_o + "f.xhorassemana, ";
				sSQL_o=sSQL_o + "k.xbanco1, ";
				sSQL_o=sSQL_o + "l.xnombre xbanco_emp_desc1, ";
				sSQL_o=sSQL_o + "k.xcuenta1, ";
				sSQL_o=sSQL_o + "k.xbanco2, ";
				sSQL_o=sSQL_o + "m.xnombre xbanco_emp_desc2, ";
				sSQL_o=sSQL_o + "k.xcuenta2, ";
				sSQL_o=sSQL_o + "k.xbanco3, ";
				sSQL_o=sSQL_o + "n.xnombre xbanco_emp_desc3, ";
				sSQL_o=sSQL_o + "k.xcuenta3 ";
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable("gp_emplnominas") + " a ";
					sSQL_o=sSQL_o + "left join " + connSource.translateTable("gp_cargos") + " b ";
						sSQL_o=sSQL_o + "on b.xcargo_id=a.xcargo_id and b.xempresa_id=a.xempresa_id and b.xentorno_id=a.xentorno_id and b.xorganizacion_id=a.xorganizacion_id ";
					sSQL_o=sSQL_o + "left join " + connSource.translateTable("gp_departamentos") + " c ";
						sSQL_o=sSQL_o + "on c.xdepartamento_id=a.xdepartamento_id and b.xempresa_id=a.xempresa_id and b.xentorno_id=a.xentorno_id and b.xorganizacion_id=a.xorganizacion_id ";
					sSQL_o=sSQL_o + "left join " + connSource.translateTable("gp_secciones") + " d ";
						sSQL_o=sSQL_o + "on d.xseccion_id=a.xseccion_id and d.xempresa_id=a.xempresa_id and d.xentorno_id=a.xentorno_id and d.xorganizacion_id=a.xorganizacion_id ";
					sSQL_o=sSQL_o + "left join " + connSource.translateTable("gp_centroscostos") + " e ";
						sSQL_o=sSQL_o + "on e.xcentrocosto_id=a.xcentrocosto_id and e.xempresa_id=a.xempresa_id and e.xentorno_id=a.xentorno_id and e.xorganizacion_id=a.xorganizacion_id ";
					sSQL_o=sSQL_o + "left join " + connSource.translateTable("gp_contratos") + " f ";
						sSQL_o=sSQL_o + "on f.xcontrato_id=a.xcontrato_id and f.xempresa_id=a.xempresa_id and f.xentorno_id=a.xentorno_id and f.xorganizacion_id=a.xorganizacion_id ";
					sSQL_o=sSQL_o + "left join " + connSource.translateTable("gp_empleados") + " g ";
						sSQL_o=sSQL_o + "on g.xempleado_id=a.xempleado_id and g.xempresa_id=a.xempresa_id and g.xentorno_id=a.xentorno_id and g.xorganizacion_id=a.xorganizacion_id ";
					sSQL_o=sSQL_o + "left join " + connSource.translateTable("gp_formasdpago") + " h ";
						sSQL_o=sSQL_o + "on h.xempleado_id=a.xempleado_id and h.xempresa_id=a.xempresa_id and h.xentorno_id=a.xentorno_id and h.xorganizacion_id=a.xorganizacion_id and h.xestadoformadpago=" + DAUtils.formatValue("A",DA.DA_DT_TEXT) + " ";
					sSQL_o=sSQL_o + "left join " + connSource.translateTable("gp_bancos") + " i ";
						sSQL_o=sSQL_o + "on i.xbanco_id=h.xbanco_id and i.xempresa_id=a.xempresa_id ";
					sSQL_o=sSQL_o + "left join " + connSource.translateTable("gp_empresas") + " k ";
						sSQL_o=sSQL_o + "on k.xempresa_id=a.xempresa_id ";
					sSQL_o=sSQL_o + "left join " + connSource.translateTable("gp_bancos") + " l ";
						sSQL_o=sSQL_o + "on l.xbanco_id=k.xbanco1 and l.xempresa_id=a.xempresa_id ";
					sSQL_o=sSQL_o + "left join " + connSource.translateTable("gp_bancos") + " m ";
						sSQL_o=sSQL_o + "on m.xbanco_id=k.xbanco2 and m.xempresa_id=a.xempresa_id ";
					sSQL_o=sSQL_o + "left join " + connSource.translateTable("gp_bancos") + " n ";
						sSQL_o=sSQL_o + "on n.xbanco_id=k.xbanco3 and n.xempresa_id=a.xempresa_id ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + "a.xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and a.xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and a.xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and a.xfechafinal is null ";
				sSQL_o=sSQL_o + sCondicion + " ";
				
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
	}
	
	public String rubrosIngresos(String sCampo,String sEmpresa,String sEntorno,String sOrganizacion,String sCondicion,String sPrefijo){
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
				sSQL_o=sSQL_o + connSource.translateTable("gp_rubros") + " " + sPrefijo2 + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + sCondicion + " ";
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sSQL_o;
	}
	
	public String descripcion(String sCampo,String sEmpresa,String sEntorno,String sOrganizacion,String sTabla,String sCondicion){
		String sDescripcion,sSQL_o;
		
		DAResultSet rs=null;
		
		sDescripcion="";
		sSQL_o="";
		
		try {
			sSQL_o="select ";
				sSQL_o=sSQL_o + sCampo + " valor ";
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable(sTabla) + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + sCondicion + " ";
			
			rs=connData.openSQL(sSQL_o);
				
			while(rs.moveNext()){
				sDescripcion=rs.getString("valor");
			}
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sDescripcion;
	}
	
	public double codigoDecimo(String sEmpresa,String sEntorno,String sOrganizacion,String sTipoDecimo,Double dAnio){
		Double dCodigo;
		
		String sSQL_o;
		
		DAResultSet rs=null;
		
		sSQL_o="";
		
		dCodigo=0.0;
		
		try {
			sSQL_o="select ";
				sSQL_o=sSQL_o + "max(xdecimo_id) valor ";
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable("gp_decimo") + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xanio_id=" + DAUtils.formatValue(dAnio,DA.DA_DT_LONG) + " ";
				sSQL_o=sSQL_o + "and xtipodecimo=" + DAUtils.formatValue(sTipoDecimo,DA.DA_DT_TEXT) + " ";
			
			rs=connData.openSQL(sSQL_o);
			
			while(rs.moveNext()){
				dCodigo=rs.getDouble("valor");
			}
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dCodigo++;
		
		return dCodigo;
	}
	
	public void insertarDecimo(String sEmpresa,String sEntorno,String sOrganizacion,String sTipoDecimo,Double dDecimoId,Double dAnioId,Double dPeriodoId,Double dAnioDesc,String sBancoId,String sBancoDesc,String sCargoId,String sCargoDesc,String sCentroCostoId,String sCentroCostoDesc,String sContratoId,String sContratoDesc,String sContratoParcial,String sCorreoEmpleado,String sDepartamentoId,String sDepartamentoDesc,Integer iDiasLaborados,String sDiscapacidad,String sEmpleado,Date dFechaIngreso,Date dFechaJubilacion,String sFormasDePago,String sFormasDePagoEmpresa,Integer iHorasFalta,Integer iHorasParciales,String sNombres,String sApellidos,String sNombresCompletos,Double dPeriodoDesc,String sSeccionId,String sSeccionDesc,String sSectorial,String sSexo,Double dSueldoNomina,Double dSumaProvisiones,Double dValorRoles,Double dValorIngresos,Double dValorTotal,Double dValorFaltas,Double dValorRetencion,Double dValorRecibir,Double dValorBanco,String sCuentaEmpleado,String sCodigoDptoBanco,String sComentarioBanco,String sComentarioNomina2,String sComentarioPeriodo,Integer iContadorFilas,String sCuentaEmpresa1,String sCuentaEmpresa2,String sCuentaEmpresa3,String sTipoCuentaEmpleado,String sEspacio1,String sEspacio2,String sEspacio3,String sPrefijoC,String sPrefijoCta,String sPrefijoPA,String sSiglaUsd,String sBancoId1,String sBancoId2,String sBancoId3,String sBancoDesc1,String sBancoDesc2,String sBancoDesc3,String sRegimen){
		String sSQL_o;
		
		Date dFechaActual;
		
		dFechaActual=new Date();
		
		sSQL_o="";
		
		try {
			sSQL_o="insert ";
				sSQL_o=sSQL_o + "into imp.gp_decimo ";
					sSQL_o=sSQL_o + "( ";
						sSQL_o=sSQL_o + "xempresa_id, ";
						sSQL_o=sSQL_o + "xentorno_id, ";
						sSQL_o=sSQL_o + "xorganizacion_id, ";
						sSQL_o=sSQL_o + "xtipodecimo, ";
						sSQL_o=sSQL_o + "xdecimo_id, ";
						sSQL_o=sSQL_o + "xanio_id, ";
						sSQL_o=sSQL_o + "xperiodo_id, ";
						sSQL_o=sSQL_o + "xanio_desc, ";
						sSQL_o=sSQL_o + "xbanco_id, ";
						sSQL_o=sSQL_o + "xbanco_desc, ";
						sSQL_o=sSQL_o + "xcargo_id, ";
						sSQL_o=sSQL_o + "xcargo_desc, ";
						sSQL_o=sSQL_o + "xcentrocosto_id, ";
						sSQL_o=sSQL_o + "xcentrocosto_desc, ";
						sSQL_o=sSQL_o + "xcontrato_id, ";
						sSQL_o=sSQL_o + "xcontrato_desc, ";
						sSQL_o=sSQL_o + "xcontratoparcial, ";
						sSQL_o=sSQL_o + "xcorreoempleado, ";
						sSQL_o=sSQL_o + "xdepartamento_id, ";
						sSQL_o=sSQL_o + "xdepartamento_desc, ";
						sSQL_o=sSQL_o + "xdiaslaborados, ";
						sSQL_o=sSQL_o + "xdiscapacidad, ";
						sSQL_o=sSQL_o + "xempleado_id, ";
						sSQL_o=sSQL_o + "xfechaingreso, ";
						sSQL_o=sSQL_o + "xfechajubilacion, ";
						sSQL_o=sSQL_o + "xformasdepago, ";
						sSQL_o=sSQL_o + "xformadepagoemp, ";
						sSQL_o=sSQL_o + "xhorasfalta, ";
						sSQL_o=sSQL_o + "xhorasparciales, ";
						sSQL_o=sSQL_o + "xnombres, ";
						sSQL_o=sSQL_o + "xapellidos, ";
						sSQL_o=sSQL_o + "xnombrecompleto, ";
						sSQL_o=sSQL_o + "xperiodo_desc, ";
						sSQL_o=sSQL_o + "xseccion_id, ";
						sSQL_o=sSQL_o + "xseccion_desc, ";
						sSQL_o=sSQL_o + "xsectorial, ";
						sSQL_o=sSQL_o + "xsexo, ";
						sSQL_o=sSQL_o + "xsueldonomina, ";
						sSQL_o=sSQL_o + "xsumaprovisiones, ";
						sSQL_o=sSQL_o + "xvalorroles, ";
						sSQL_o=sSQL_o + "xvaloringresos, ";
						sSQL_o=sSQL_o + "xvalortotal, ";
						sSQL_o=sSQL_o + "xvalorfaltas, ";
						sSQL_o=sSQL_o + "xvalorretencion, ";
						sSQL_o=sSQL_o + "xvalorrecibir, ";
						sSQL_o=sSQL_o + "xvalorbanco, ";
						sSQL_o=sSQL_o + "xcuentaempleado, ";
						sSQL_o=sSQL_o + "xcodigodptobanco, ";
						sSQL_o=sSQL_o + "xcomentariobanco, ";
						sSQL_o=sSQL_o + "xcomentarionomina2, ";
						sSQL_o=sSQL_o + "xcomentarioperiodo, ";
						sSQL_o=sSQL_o + "xcontadorfilas, ";
						sSQL_o=sSQL_o + "xcuentaempresa1, ";
						sSQL_o=sSQL_o + "xcuentaempresa2, ";
						sSQL_o=sSQL_o + "xcuentaempresa3, ";
						sSQL_o=sSQL_o + "xtipocuentaemplead, ";
						sSQL_o=sSQL_o + "xespacio1, ";
						sSQL_o=sSQL_o + "xespacio2, ";
						sSQL_o=sSQL_o + "xespacio3, ";
						sSQL_o=sSQL_o + "xprefijoc, ";
						sSQL_o=sSQL_o + "xprefijocta, ";
						sSQL_o=sSQL_o + "xprefijopa, ";
						sSQL_o=sSQL_o + "xsiglausd, ";
						sSQL_o=sSQL_o + "xfechagenera, ";
						sSQL_o=sSQL_o + "xusuariogenera, ";
						sSQL_o=sSQL_o + "xbanco_id1, ";
						sSQL_o=sSQL_o + "xbanco_id2, ";
						sSQL_o=sSQL_o + "xbanco_id3, ";
						sSQL_o=sSQL_o + "xbanco_desc1, ";
						sSQL_o=sSQL_o + "xbanco_desc2, ";
						sSQL_o=sSQL_o + "xbanco_desc3, ";
						sSQL_o=sSQL_o + "xregimen ";
					sSQL_o=sSQL_o + ") ";
				sSQL_o=sSQL_o + "values ";
					sSQL_o=sSQL_o + "( ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sTipoDecimo,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dDecimoId,DA.DA_DT_DOUBLE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dAnioId,DA.DA_DT_LONG) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dPeriodoId,DA.DA_DT_LONG) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dAnioDesc,DA.DA_DT_LONG) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sBancoId,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sBancoDesc,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCargoId,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCargoDesc,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCentroCostoId,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCentroCostoDesc,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sContratoId,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sContratoDesc,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sContratoParcial,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCorreoEmpleado,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sDepartamentoId,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sDepartamentoDesc,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iDiasLaborados,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sDiscapacidad,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEmpleado,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dFechaIngreso,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dFechaJubilacion,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sFormasDePago,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sFormasDePagoEmpresa,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iHorasFalta,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iHorasParciales,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sNombres,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sApellidos,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sNombresCompletos,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dPeriodoDesc,DA.DA_DT_LONG) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sSeccionId,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sSeccionDesc,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sSectorial,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sSexo,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dSueldoNomina,DA.DA_DT_CURRENCY) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dSumaProvisiones,DA.DA_DT_CURRENCY) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dValorRoles,DA.DA_DT_CURRENCY) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dValorIngresos,DA.DA_DT_CURRENCY) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dValorTotal,DA.DA_DT_CURRENCY) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dValorFaltas,DA.DA_DT_CURRENCY) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dValorRetencion,DA.DA_DT_CURRENCY) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dValorRecibir,DA.DA_DT_CURRENCY) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dValorBanco,DA.DA_DT_LONG) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCuentaEmpleado,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCodigoDptoBanco,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sComentarioBanco,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sComentarioNomina2,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sComentarioPeriodo,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(iContadorFilas,DA.DA_DT_INTEGER) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCuentaEmpresa1,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCuentaEmpresa2,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sCuentaEmpresa3,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sTipoCuentaEmpleado,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEspacio1,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEspacio2,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sEspacio3,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sPrefijoC,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sPrefijoCta,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sPrefijoPA,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sSiglaUsd,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(dFechaActual,DA.DA_DT_DATE) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(connSource.getUser(),DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sBancoId1,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sBancoId2,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sBancoId3,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sBancoDesc1,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sBancoDesc2,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sBancoDesc3,DA.DA_DT_TEXT) + ", ";
						sSQL_o=sSQL_o + DAUtils.formatValue(sRegimen,DA.DA_DT_TEXT) + " ";
					sSQL_o=sSQL_o + ") ";
			
			connData.execSQL(sSQL_o);
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void eliminarDecimo(String sEmpresa,String sEntorno,String sOrganizacion,Double dAnio,Double dPeriodo,String sTipoDecimo,String sCondicion){
		String sSQL_o;
		
		sSQL_o="";
			
		try {
			sSQL_o="delete ";
			sSQL_o=sSQL_o + "from ";
				sSQL_o=sSQL_o + connSource.translateTable("gp_decimo") + " ";
			sSQL_o=sSQL_o + "where ";
				sSQL_o=sSQL_o + "xempresa_id=" + DAUtils.formatValue(sEmpresa,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xentorno_id=" + DAUtils.formatValue(sEntorno,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xorganizacion_id=" + DAUtils.formatValue(sOrganizacion,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + "and xanio_id=" + DAUtils.formatValue(dAnio,DA.DA_DT_DOUBLE) + " ";
				sSQL_o=sSQL_o + "and xperiodo_id=" + DAUtils.formatValue(dPeriodo,DA.DA_DT_DOUBLE) + " ";
				sSQL_o=sSQL_o + "and xtipodecimo=" + DAUtils.formatValue(sTipoDecimo,DA.DA_DT_TEXT) + " ";
				sSQL_o=sSQL_o + sCondicion + " ";
			
			connData.execSQL(sSQL_o);
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void decimo4(String sEmpresa,String sEntorno,String sOrganizacion,Double dAnio,Double dPeriodo,String sRegimen,String sDepartamentoViene){
		String sSQL,sTipoDecimo,sParcial,sDiscapacidad,sComentarioBanco,sComentarioNomina2,sComentarioPeriodo,sEspacio1,sEspacio2,sEspacio3,sPrefijoC;
		String sPrefijoCta,sPrefijoPA,sSiglaUsd,sFormasDePago,sCodigoDptoBanco,sHorasFalta,sRubroFalta,sRubroDeci4,sSueldoBasico,sRubroDeci4rol;
		
		Double dValorIngresos,dValorFaltas,dValorRecibir,dValorBanco,dValorRetencion,dValorTotal,dValorRoles,dCodigo,dCodigoAnio,dCodigoPeriodo,dSumaProvisiones,dValorSueldoBasico;
		
		Integer iHorasFalta,iContadorFilas,iDiasLaborados,iPeriodoInicial,iPeriodoFinal,iAnioInicial,iAnioFinal,iDiasFalta;
		
		Calendar calIni=GregorianCalendar.getInstance();
		Calendar calFin=GregorianCalendar.getInstance();
		
		Date dFechaInicio,dFechaFin;
		
		DAResultSet rs,rs2;

		sSQL="";
		sParcial="";
		sDiscapacidad="";
		sEspacio1="";
		sEspacio2="";
		sEspacio3="";
		sFormasDePago="";
		sSueldoBasico="";
		sRubroDeci4rol="";
		sHorasFalta="";
		sRubroFalta="";
		sRubroDeci4="";
		
		dCodigo=0.0;
		dCodigoAnio=0.0;
		dCodigoPeriodo=0.0;
		dSumaProvisiones=0.0;
		dValorSueldoBasico=0.0;
		dValorIngresos=0.0;
		dValorRoles=0.0;
		dValorRetencion=0.0;
		dValorFaltas=0.0;
		
		sTipoDecimo="DC";
		
		rs=null;
		rs2=null;
		
		if(sRegimen.equals("RC")){
			calFin.set((int) (dAnio-0),3,1);
			calFin.add(Calendar.DATE,-1);
			calIni.set((int) (dAnio-1),3,1);
		}
		else{
			calFin.set((int) (dAnio-0),7,31);
			calIni.set((int) (dAnio-1),8,1);
		}
		
		dCodigoAnio=Double.parseDouble(descripcion("xanio",sEmpresa,sEntorno,sOrganizacion,"gp_anios","and xanio_id=" + DAUtils.formatValue(dAnio,DA.DA_DT_LONG)));
		dCodigoPeriodo=Double.parseDouble(descripcion("xperiodo",sEmpresa,sEntorno,sOrganizacion,"gp_periodos","and xperiodo_id=" + DAUtils.formatValue(dPeriodo,DA.DA_DT_LONG) + " and xanio_id=" + DAUtils.formatValue(dAnio,DA.DA_DT_LONG)));
		
		sComentarioBanco="P DECIMO4TO " + dCodigoAnio;
		sComentarioNomina2="PAGO DECIMO CUARTO";
		sComentarioPeriodo="PAGO DECIMO CUARTO";
		
		sPrefijoC="C";
		sPrefijoCta="CTA";
		sPrefijoPA="PA";
		sSiglaUsd="USD";
		sCodigoDptoBanco="1";
		
		iHorasFalta=0;
	
		iAnioInicial=(int) (dAnio-1);
		iAnioFinal=(int) (dAnio-0);
		
		if(sRegimen.equals("RS")){
			iPeriodoInicial=(iAnioInicial*100)+8;
			iPeriodoFinal=(iAnioFinal*100)+7;
		}
		else{
			iPeriodoInicial=(iAnioInicial*100)+3;
			iPeriodoFinal=(iAnioFinal*100)+2;
		}
		
		iContadorFilas=1;
		
		try {
			
			eliminarDecimo(sEmpresa,sEntorno,sOrganizacion,dAnio,dPeriodo,"DC","and xregimen=" + DAUtils.formatValue(sRegimen,DA.DA_DT_TEXT));
			
			sSQL=sSqlParametros("xhorasfaltas,xrubrofaltas,xrubrodecimo4,xsueldobasico,xrubrodecimo4rol","",sEmpresa,sEntorno,sOrganizacion,"");
			rs=connData.openSQL(sSQL);
			
			while (rs.moveNext()){
				sHorasFalta=rs.getString("xhorasfaltas");
				sRubroFalta=rs.getString("xrubrofaltas");
				sSueldoBasico=rs.getString("xsueldobasico");
				sRubroDeci4=rs.getString("xrubrodecimo4");
				sRubroDeci4rol=rs.getString("xrubrodecimo4rol");
			}
			rs.close();
			
			sSQL=sSqlEmpleados("and a.xdepartamento_id=" + DAUtils.formatValue(sDepartamentoViene,DA.DA_DT_TEXT) + " and a.xtiporegimen=" + DAUtils.formatValue(sRegimen,DA.DA_DT_TEXT) + " and a.xfechaingreso<=" + DAUtils.formatValue(calFin.getTime(),DA.DA_DT_DATE),sEmpresa,sEntorno,sOrganizacion);
			rs=connData.openSQL(sSQL);
			
			while (rs.moveNext()) {
				sSQL=sSqlAsistencia("sum(xhoras) valor","",sEmpresa,sEntorno,sOrganizacion,sHorasFalta,iPeriodoInicial,iPeriodoFinal,iAnioInicial,iAnioFinal,"",rs.getString("xempleado_id"));
				rs2=connData.openSQL(sSQL);
				
				while(rs2.moveNext()){
					iHorasFalta=rs2.getInt("valor");
				}
				rs2.close();
				
				sSQL=sSqlPagoNomina("sum(xvalor) valor","",sEmpresa,sEntorno,sOrganizacion,sRubroFalta,iPeriodoInicial,iPeriodoFinal,iAnioInicial,iAnioFinal,"");
				rs2=connData.openSQL(sSQL);
				
				while(rs2.moveNext()){
					dValorFaltas=rs2.getDouble("valor");
				}
				rs2.close();
				
				sSQL=sSqlPagoNomina("sum(xvalor) valor","",sEmpresa,sEntorno,sOrganizacion,sRubroDeci4,iPeriodoInicial,iPeriodoFinal,iAnioInicial,iAnioFinal,"");
				rs2=connData.openSQL(sSQL);
				
				while(rs2.moveNext()){
					dSumaProvisiones=rs2.getDouble("valor");
				}
				rs2.close();
				
				sSQL=sSqlPagoNomina("sum(xvalor) valor","",sEmpresa,sEntorno,sOrganizacion,sRubroDeci4rol,iPeriodoInicial,iPeriodoFinal,iAnioInicial,iAnioFinal,"");
				rs2=connData.openSQL(sSQL);
				
				while(rs2.moveNext()){
					dValorRoles=rs2.getDouble("valor");
				}
				rs2.close();
				
				sSQL=sSqlRetenciones("sum(xvalorcuarto) valor","",sEmpresa,sEntorno,sOrganizacion,rs.getString("xempleado_id"),"");
				rs2=connData.openSQL(sSQL);
				
				while(rs2.moveNext()){
					dValorRetencion=rs2.getDouble("valor");
				}
				rs2.close();
		
				sSQL=sSqlConstantes("xvalor","",sEmpresa,sEntorno,sOrganizacion,"",sSueldoBasico,dAnio);
				rs2=connData.openSQL(sSQL);
				
				while(rs2.moveNext()){
					dValorSueldoBasico=rs2.getDouble("xvalor");
				}
				rs2.close();

				dFechaFin=calFin.getTime();
				dFechaInicio=calIni.getTime();
				
				iDiasLaborados=(int) ((dFechaFin.getTime()-dFechaInicio.getTime())/(24*60*60*1000))+1;
				
				if(iDiasLaborados>360){
					iDiasLaborados=360;
				}
				
				if(rs.getInt("xparcial")==0){
					sParcial="";
				}
				else{
					sParcial="X";
				}
				
				if(rs.getInt("xdiscapacidad")==0){
					sDiscapacidad="";
				}
				else{
					sDiscapacidad="X";
				}
				
				if(rs.getString("xformasdepago")=="T"){
					sFormasDePago="A";
				}
				else{
					sFormasDePago="P";
				}
				
				dCodigo=codigoDecimo(sEmpresa,sEntorno,sOrganizacion,sTipoDecimo,dAnio);
				
				iDiasFalta=iHorasFalta/rs.getInt("xhorasdiaria");
			
				dValorTotal=((dValorSueldoBasico*(iDiasLaborados-iDiasFalta))/360);
				dValorRecibir=dValorTotal-dValorRetencion-dValorRoles;
				dValorBanco=dValorRecibir*100;
				
				iDiasLaborados=iDiasLaborados-iDiasFalta;
				
				insertarDecimo(
					sEmpresa,
					sEntorno,
					sOrganizacion,
					sTipoDecimo,
					dCodigo,
					dAnio,
					dPeriodo,
					dCodigoAnio,
					rs.getString("xbanco_id"),
					rs.getString("xbanco_desc"),
					rs.getString("xcargo_id"),
					rs.getString("xcargo_desc"),
					rs.getString("xcentrocosto_id"),
					rs.getString("xcentrocosto_desc"),
					rs.getString("xcontrato_id"),
					rs.getString("xcontrato_desc"),
					sParcial,
					rs.getString("xcorreo"),
					rs.getString("xdepartamento_id"),
					rs.getString("xdepartamento_desc"),
					iDiasLaborados,						
					sDiscapacidad,
					rs.getString("xempleado_id"),
					rs.getDate("xfechaingreso"),
					null,
					sFormasDePago,
					rs.getString("xformasdepago"),
					iHorasFalta,
					rs.getInt("xhorassemana"),
					rs.getString("xprimernombre") + " " + rs.getString("xsegundonombre"),
					rs.getString("xprimerapellido") + " " + rs.getString("xsegundoapellido"),
					rs.getString("xnombrecompleto"),
					dCodigoPeriodo,
					rs.getString("xseccion_id"),
					rs.getString("xseccion_desc"),
					rs.getString("xsectorial"),
					rs.getString("xsexo"),
					rs.getDouble("xsueldo"),
					dSumaProvisiones,
					dValorRoles,
					dValorIngresos,
					dValorTotal,
					dValorFaltas,
					dValorRetencion,
					dValorRecibir,
					dValorBanco,
					rs.getString("xcuenta"),
					sCodigoDptoBanco,
					sComentarioBanco,
					sComentarioNomina2,
					sComentarioPeriodo,
					iContadorFilas,
					rs.getString("xcuenta1"),
					rs.getString("xcuenta2"),
					rs.getString("xcuenta3"),
					rs.getString("xtipocuenta"),
					sEspacio1,
					sEspacio2,
					sEspacio3,
					sPrefijoC,
					sPrefijoCta,
					sPrefijoPA,
					sSiglaUsd,
					rs.getString("xbanco1"),
					rs.getString("xbanco2"),
					rs.getString("xbanco3"),
					rs.getString("xbanco_emp_desc1"),
					rs.getString("xbanco_emp_desc2"),
					rs.getString("xbanco_emp_desc3"),
					sRegimen
				);
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void decimo3(String sEmpresa,String sEntorno,String sOrganizacion,Double dAnio,Double dPeriodo,String sDepartamentoViene){
		String sSQL,sTipoDecimo,sParcial,sDiscapacidad,sComentarioBanco,sComentarioNomina2,sComentarioPeriodo,sEspacio1,sEspacio2,sEspacio3,sPrefijoC;
		String sPrefijoCta,sPrefijoPA,sSiglaUsd,sFormasDePago,sCodigoDptoBanco,sHorasFalta,sRubroFalta,sRubroDeci3,sRubroDeci3rol;
		
		Double dValorIngresos,dValorFaltas,dValorRecibir,dValorBanco,dValorRetencion,dValorTotal,dValorRoles,dCodigo,dCodigoAnio,dCodigoPeriodo,dSumaProvisiones;
		
		Integer iHorasFalta,iContadorFilas,iDiasLaborados,iPeriodoInicial,iPeriodoFinal,iAnioInicial,iAnioFinal,iDiasFalta;
		
		Calendar calIni=GregorianCalendar.getInstance();
		Calendar calFin=GregorianCalendar.getInstance();
		
		Date dFechaInicio,dFechaFin;
		
		DAResultSet rs,rs2;

		sSQL="";
		sParcial="";
		sDiscapacidad="";
		sEspacio1="";
		sEspacio2="";
		sEspacio3="";
		sFormasDePago="";
		sRubroDeci3rol="";
		sHorasFalta="";
		sRubroFalta="";
		sRubroDeci3="";
		
		dCodigo=0.0;
		dCodigoAnio=0.0;
		dCodigoPeriodo=0.0;
		dSumaProvisiones=0.0;
		dValorIngresos=0.0;
		dValorRoles=0.0;
		dValorRetencion=0.0;
		dValorFaltas=0.0;
		
		sTipoDecimo="DT";
		
		rs=null;
		rs2=null;
		
		calIni.set((int) (dAnio-1),11,1,0,0,0);
		calFin.set((int) (dAnio-0),10,30,23,59,59);
		
		
		dCodigoAnio=Double.parseDouble(descripcion("xanio",sEmpresa,sEntorno,sOrganizacion,"gp_anios","and xanio_id=" + DAUtils.formatValue(dAnio,DA.DA_DT_LONG)));
		dCodigoPeriodo=Double.parseDouble(descripcion("xperiodo",sEmpresa,sEntorno,sOrganizacion,"gp_periodos","and xperiodo_id=" + DAUtils.formatValue(dPeriodo,DA.DA_DT_LONG) + " and xanio_id=" + DAUtils.formatValue(dAnio,DA.DA_DT_LONG)));
		
		sComentarioBanco="P DECIMO3RO " + dCodigoAnio;
		sComentarioNomina2="PAGO DECIMO TERCERO";
		sComentarioPeriodo="PAGO DECIMO TERCERO";
		
		sPrefijoC="C";
		sPrefijoCta="CTA";
		sPrefijoPA="PA";
		sSiglaUsd="USD";
		sCodigoDptoBanco="1";
		
		iHorasFalta=0;

		iAnioInicial=(int) (dAnio-1);
		iAnioFinal=(int) (dAnio-0);
		
		iPeriodoInicial=(iAnioInicial*100)+12;
		iPeriodoFinal=(iAnioFinal*100)+11;
		
		iContadorFilas=1;
		
		try {
			
			eliminarDecimo(sEmpresa,sEntorno,sOrganizacion,dAnio,dPeriodo,"DT","");
			
			sSQL=sSqlParametros("xhorasfaltas,xrubrofaltas,xrubrodecimo3,xrubrodecimo3rol","",sEmpresa,sEntorno,sOrganizacion,"");
			rs=connData.openSQL(sSQL);
			
			while (rs.moveNext()){
				sHorasFalta=rs.getString("xhorasfaltas");
				sRubroFalta=rs.getString("xrubrofaltas");
				sRubroDeci3=rs.getString("xrubrodecimo3");
				sRubroDeci3rol=rs.getString("xrubrodecimo3rol");
			}
			rs.close();
			
			sSQL=sSqlEmpleados("and a.xdepartamento_id=" + DAUtils.formatValue(sDepartamentoViene,DA.DA_DT_TEXT) + " and a.xfechaingreso<=" + DAUtils.formatValue(calFin.getTime(),DA.DA_DT_DATE),sEmpresa,sEntorno,sOrganizacion);
			rs=connData.openSQL(sSQL);
			
			while (rs.moveNext()) {
				sSQL=sSqlAsistencia("sum(xhoras) valor","",sEmpresa,sEntorno,sOrganizacion,sHorasFalta,iPeriodoInicial,iPeriodoFinal,iAnioInicial,iAnioFinal,"",rs.getString("xempleado_id"));
				rs2=connData.openSQL(sSQL);
				
				while(rs2.moveNext()){
					iHorasFalta=rs2.getInt("valor");
				}
				rs2.close();
				
				sSQL=sSqlPagoNomina("sum(xvalor) valor","",sEmpresa,sEntorno,sOrganizacion,sRubroFalta,iPeriodoInicial,iPeriodoFinal,iAnioInicial,iAnioFinal,"");
				rs2=connData.openSQL(sSQL);
				
				while(rs2.moveNext()){
					dValorFaltas=rs2.getDouble("valor");
				}
				rs2.close();
				
				sSQL=sSqlPagoNomina("sum(xvalor) valor","",sEmpresa,sEntorno,sOrganizacion,sRubroDeci3,iPeriodoInicial,iPeriodoFinal,iAnioInicial,iAnioFinal,"");
				rs2=connData.openSQL(sSQL);
				
				while(rs2.moveNext()){
					dSumaProvisiones=rs2.getDouble("valor");
				}
				rs2.close();
				
				sSQL=sSqlPagoNomina("sum(xvalor) valor","",sEmpresa,sEntorno,sOrganizacion,sRubroDeci3rol,iPeriodoInicial,iPeriodoFinal,iAnioInicial,iAnioFinal,"");
				rs2=connData.openSQL(sSQL);
				
				while(rs2.moveNext()){
					dValorRoles=rs2.getDouble("valor");
				}
				rs2.close();
				
				sSQL=sSqlPagoNomina("sum(a.xvalor) valor","and a.xrubro_id in (" + rubrosIngresos("b.xrubro_id",sEmpresa,sEntorno,sOrganizacion,"and b.xtiporubro=" + DAUtils.formatValue("A",DA.DA_DT_TEXT) + " and b.xestado=" + DAUtils.formatValue(-1,DA.DA_DT_INTEGER) + " and b.xpagaiess=" + DAUtils.formatValue(-1,DA.DA_DT_INTEGER),"b.") + ")",sEmpresa,sEntorno,sOrganizacion,"",iPeriodoInicial,iPeriodoFinal,iAnioInicial,iAnioFinal,"a.");
				rs2=connData.openSQL(sSQL);
				
				while(rs2.moveNext()){
					dValorIngresos=rs2.getDouble("valor");
				}
				rs2.close();
				
				sSQL=sSqlRetenciones("sum(xvalortercero) valor","",sEmpresa,sEntorno,sOrganizacion,rs.getString("xempleado_id"),"");
				rs2=connData.openSQL(sSQL);
				
				while(rs2.moveNext()){
					dValorRetencion=rs2.getDouble("valor");
				}
				rs2.close();
		
				dFechaFin=calFin.getTime();
				dFechaInicio=calIni.getTime();
				
				iDiasLaborados=(int) ((dFechaFin.getTime()-dFechaInicio.getTime())/(24*60*60*1000))+1;
				
				if(iDiasLaborados>360){
					iDiasLaborados=360;
				}
				
				if(rs.getInt("xparcial")==0){
					sParcial="";
				}
				else{
					sParcial="X";
				}
				
				if(rs.getInt("xdiscapacidad")==0){
					sDiscapacidad="";
				}
				else{
					sDiscapacidad="X";
				}
				
				if(rs.getString("xformasdepago")=="T"){
					sFormasDePago="A";
				}
				else{
					sFormasDePago="P";
				}
				
				dCodigo=codigoDecimo(sEmpresa,sEntorno,sOrganizacion,sTipoDecimo,dAnio);
				
				iDiasFalta=iHorasFalta/rs.getInt("xhorasdiaria");
			
				dValorTotal=(dValorIngresos-dValorFaltas)/12;
				dValorRecibir=dValorTotal-dValorRetencion-dValorRoles;
				dValorBanco=dValorRecibir*100;
				
				iDiasLaborados=iDiasLaborados-iDiasFalta;
				
				insertarDecimo(
					sEmpresa,
					sEntorno,
					sOrganizacion,
					sTipoDecimo,
					dCodigo,
					dAnio,
					dPeriodo,
					dCodigoAnio,
					rs.getString("xbanco_id"),
					rs.getString("xbanco_desc"),
					rs.getString("xcargo_id"),
					rs.getString("xcargo_desc"),
					rs.getString("xcentrocosto_id"),
					rs.getString("xcentrocosto_desc"),
					rs.getString("xcontrato_id"),
					rs.getString("xcontrato_desc"),
					sParcial,
					rs.getString("xcorreo"),
					rs.getString("xdepartamento_id"),
					rs.getString("xdepartamento_desc"),
					iDiasLaborados,						
					sDiscapacidad,
					rs.getString("xempleado_id"),
					rs.getDate("xfechaingreso"),
					null,
					sFormasDePago,
					rs.getString("xformasdepago"),
					iHorasFalta,
					rs.getInt("xhorassemana"),
					rs.getString("xprimernombre") + " " + rs.getString("xsegundonombre"),
					rs.getString("xprimerapellido") + " " + rs.getString("xsegundoapellido"),
					rs.getString("xnombrecompleto"),
					dCodigoPeriodo,
					rs.getString("xseccion_id"),
					rs.getString("xseccion_desc"),
					rs.getString("xsectorial"),
					rs.getString("xsexo"),
					rs.getDouble("xsueldo"),
					dSumaProvisiones,
					dValorRoles,
					dValorIngresos,
					dValorTotal,
					dValorFaltas,
					dValorRetencion,
					dValorRecibir,
					dValorBanco,
					rs.getString("xcuenta"),
					sCodigoDptoBanco,
					sComentarioBanco,
					sComentarioNomina2,
					sComentarioPeriodo,
					iContadorFilas,
					rs.getString("xcuenta1"),
					rs.getString("xcuenta2"),
					rs.getString("xcuenta3"),
					rs.getString("xtipocuenta"),
					sEspacio1,
					sEspacio2,
					sEspacio3,
					sPrefijoC,
					sPrefijoCta,
					sPrefijoPA,
					sSiglaUsd,
					rs.getString("xbanco1"),
					rs.getString("xbanco2"),
					rs.getString("xbanco3"),
					rs.getString("xbanco_emp_desc1"),
					rs.getString("xbanco_emp_desc2"),
					rs.getString("xbanco_emp_desc3"),
					"RS"
				);
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
}