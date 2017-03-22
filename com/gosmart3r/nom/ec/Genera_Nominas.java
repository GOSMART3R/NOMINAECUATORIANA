package com.gosmart3r.nom.ec;


import java.util.Date;




import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAException;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.session.Session;

public class Genera_Nominas {
		//VARIABLES
		private DAConnectionSource connSource; // Acceso a sdic
		private DAConnection connData; // Acceso a datos
		
		public Genera_Asistencia_Nomina Asistencias;
		public Genera_IRenta ImpuestoRenta;
		
		Funciones_Generales Funciones_Generales;
		
			
	public Genera_Nominas() {
		super();
		// TODO Auto-generated constructor stub
	}
	//FUNCIONES PARA GENERAR LA NOMINA
	//16/08/2016 FRANCISCO RUIZ
	//EL PROCESO REQUIERE QUE PRIMERO SE GUARDE EL REGISTRO
	
	
	
	//GENERAMOS LA TABLA DE NOMINA POR DEPARTAMENTO 
	//NO SE REALIZA NINGUN CALCULO
	public Boolean GeneraDepartamentos(Session session,String sEmpresa , String sEntorno, String sOrganizacion , Integer iAnio, Integer iPeriodo){
		Boolean bRetorno=false;
		String sSql_Departamentos, sXdepartamento_id, sSql_verifica, sSql_inserta, sUsuario;
		Date dFecha;
		Integer iExiste ,iNumeroEmpleados;
		
		DAResultSet rs = null, rs_verifica=null;
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		sUsuario=connSource.getUser();
		dFecha=new Date();
		
		try {
			sSql_Departamentos="";
			sSql_Departamentos+=" SELECT xdepartamento_id  , count(*) ";
			sSql_Departamentos+=" FROM  "+connSource.translateTable("gp_emplnominas");
			sSql_Departamentos+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_Departamentos+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_Departamentos+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_Departamentos+=" AND xfechafinal IS NULL ";
			sSql_Departamentos+=" GROUP BY xdepartamento_id ";
			
			rs = connData.openSQL(sSql_Departamentos);
			
			while (rs.moveNext()) {
				sXdepartamento_id="";
				iNumeroEmpleados=0;
				sXdepartamento_id=rs.getString("xdepartamento_id");
				iNumeroEmpleados=rs.getInt(2);
				//SE VERIFICA QUE EL DEPARTAMENTO NO ESTA PROCESADO
				//SIEMPRE SE INSERTA CON ESTADO PR---PROCESADO
				//NO SE INSERTA SI ESTA EN ESTADO GE---GENERADO O CE--CERRADO O CO..CONTABILIZADO
				sSql_verifica="";
				sSql_verifica+=" SELECT COUNT(*) ";
				sSql_verifica+=" FROM   "+connSource.translateTable("gp_nominasdepar");
				sSql_verifica+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_verifica+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_verifica+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_verifica+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
				sSql_verifica+=" AND xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
				sSql_verifica+=" AND xdepartamento_id="+ DAUtils.formatValue(sXdepartamento_id, DA.DA_DT_TEXT);
				//sSql_verifica+=" AND xestado='PR' ";
				rs_verifica = connData.openSQL(sSql_verifica);
				iExiste=0;
				if (rs_verifica.moveNext()) {
					iExiste=rs_verifica.getInt(1);
				}
				rs_verifica.close();
				
				if(iExiste.equals(0)|| iExiste==null){
					//SI NO EXISTE INSERTAMOS EN LA TABLA DE  NOMINA POR DEPARTAMENTO
					sSql_inserta="";
					sSql_inserta+=" INSERT INTO  "+connSource.translateTable("gp_nominasdepar")+"( ";
					sSql_inserta+=" xentorno_id, ";
					sSql_inserta+=" xorganizacion_id, ";
					sSql_inserta+=" xempresa_id, ";
					sSql_inserta+=" xperiodo_id, ";
					sSql_inserta+=" xanio_id, ";
					sSql_inserta+=" xdepartamento_id, ";
					sSql_inserta+=" xusuario, ";
					sSql_inserta+=" xfecha, ";
					sSql_inserta+=" xestado, ";
					sSql_inserta+="xnumeroempleados ";
					sSql_inserta+=" ) ";
					sSql_inserta+=" VALUES( ";
					sSql_inserta+=DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT)+" , ";
					sSql_inserta+=DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT)+" , ";
					sSql_inserta+=DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT)+" , ";
					sSql_inserta+=DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER)+" , ";
					sSql_inserta+=DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER)+" , ";
					sSql_inserta+=DAUtils.formatValue(sXdepartamento_id, DA.DA_DT_TEXT)+" , ";
					sSql_inserta+=DAUtils.formatValue(sUsuario, DA.DA_DT_TEXT)+" , ";
					sSql_inserta+=DAUtils.formatValue(dFecha, DA.DA_DT_DATE)+" , ";
					sSql_inserta+=" 'PR' ,";
					sSql_inserta+=DAUtils.formatValue(iNumeroEmpleados, DA.DA_DT_INTEGER)+"   ";
					sSql_inserta+=" ) ";
					connData.execSQL(sSql_inserta);
				}
				
				
			}
			rs.close();
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return bRetorno;
	}
	
	//FUNCIONES QUE ELIMINAN LOS VALORES GENERADOS EN NOMINA SOLO BORRA LOS QUE ESTAN EN TIPOORIGEN "R"
	@SuppressWarnings("unused")
	public Boolean EliminaRoles(Session session,String sEmpresa , String sEntorno, String sOrganizacion , Integer iAnio, Integer iPeriodo, String sDepartamento , String sTipoorigen, String sEmpleado){
		String sSql_eliminar,sSql_insertar, sUsuario,sSql_rubroprestamo;
		Date dFecha;
		DAResultSet rs = null;


		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		sUsuario=connSource.getUser();
		dFecha=new Date();
		
		
		
		try {
			//ELIMINA LOS REGISTROS DE LA NOMINA
			for(int i=1;i<=3;i++){
				sSql_eliminar="";
				sSql_eliminar+=" DELETE  ";
				if(i==1){sSql_eliminar+=" FROM   "+connSource.translateTable("gp_pagonomina");}
				if(i==2){sSql_eliminar+=" FROM   "+connSource.translateTable("gp_rolesemple");}
				if(i==3){sSql_eliminar+=" FROM   "+connSource.translateTable("gp_asistencias");}
				sSql_eliminar+=" WHERE xempresa_id= "+DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_eliminar+=" AND xorganizacion_id= "+DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_eliminar+=" AND xentorno_id=  "+DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_eliminar+=" AND xanio_id= "+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
				sSql_eliminar+=" AND xperiodo_id=  "+DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
				sSql_eliminar+=" AND xdepartamento_id= "+DAUtils.formatValue(sDepartamento, DA.DA_DT_TEXT);
				sSql_eliminar+=" AND xtipoorigen=  "+DAUtils.formatValue(sTipoorigen, DA.DA_DT_TEXT);
				if(sEmpleado.length()>0){sSql_eliminar+=" AND xempleado_id=  "+DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);}
				connData.execSQL(sSql_eliminar);
				
				//REGISTRAMOS EN AUDITORIA LA SENTENCIA SQL QUE BORRO LOS REGISTROS
				
				sSql_insertar="";
				sSql_insertar+=" INSERT INTO  "+connSource.translateTable("gp_auditoria");
				sSql_insertar+=" ( ";
				sSql_insertar+=" xusuario, ";
				sSql_insertar+=" xfecha, ";
				sSql_insertar+=" xauditoria_id, ";
				sSql_insertar+=" xdescripcion, ";
				sSql_insertar+=" xdescripcion1, ";
				sSql_insertar+=" xdescripcion2, ";
				sSql_insertar+=" xdescripcion3, ";
				sSql_insertar+=" xdescripcion4, ";
				sSql_insertar+=" xdescripcion5 ";
				sSql_insertar+=" ) ";
				sSql_insertar+=" VALUES( ";
				sSql_insertar+=DAUtils.formatValue(sUsuario, DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=DAUtils.formatValue(dFecha, DA.DA_DT_DATE)+" , ";
				sSql_insertar+=DAUtils.formatValue(i, DA.DA_DT_INTEGER)+" , ";
				sSql_insertar+=DAUtils.formatValue(sSql_eliminar.substring(1, sSql_eliminar.length()), DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=DAUtils.formatValue("", DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=DAUtils.formatValue("", DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=DAUtils.formatValue("", DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=DAUtils.formatValue("", DA.DA_DT_TEXT)+" , ";
				sSql_insertar+=DAUtils.formatValue("", DA.DA_DT_TEXT)+"  ";
				sSql_insertar+=" ) ";
				connData.execSQL(sSql_insertar);
			}
			//ACTUALIZA LOS DATOS DEL PRESTAMO A PENDIENTE
			sSql_rubroprestamo="";
			sSql_rubroprestamo+="  UPDATE  "+connSource.translateTable("gp_cuotas");
			sSql_rubroprestamo+="  SET xestadocuota='X' ";
			sSql_rubroprestamo+="  WHERE xempresa_id="+DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT)+"";
			sSql_rubroprestamo+="  AND xorganizacion_id="+DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT)+"";
			sSql_rubroprestamo+="  AND xentorno_id="+DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT)+"";
			sSql_rubroprestamo+="  AND xanio_id_des="+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER)+"";
			sSql_rubroprestamo+="  AND xperiodo_id_des="+DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER)+"";
			connData.execSQL(sSql_rubroprestamo);
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	//FUNCION PARA OBTENER EL VALOR DE LAS CONSTANTES DEFINIDAS EN LA PARTE DE LAS EMPRESAS
	public Double obtieneValorConstantes(String sEmpresa,String sEntorno,String sOrganizacion,Integer iAnio,String sConstante){
		String sSql_Constante;
		Double dValor;
		
		DAResultSet rs=null;
		
		sSql_Constante="";
		dValor=0.0;
		
		try {
			sSql_Constante="";
			sSql_Constante+="select xvalor ";
			sSql_Constante+="from " + connSource.translateTable("gp_constantesxanio") + " ";
			sSql_Constante+="where ";
			sSql_Constante+="xempresa_id=" + DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT) + " ";
			sSql_Constante+="and xentorno_id=" + DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT) + " ";
			sSql_Constante+="and xorganizacion_id=" + DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT) + " ";
			sSql_Constante+="and xanio_id=" + DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER) + " ";
			sSql_Constante+="and xconstante_id=" + DAUtils.formatValue(sConstante, DA.DA_DT_INTEGER) + " ";
			
			rs=connData.openSQL(sSql_Constante);

			if (rs.moveNext()) {
				dValor=rs.getDouble(1); 
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dValor;
	}
	
	
	//FUNCION PARA EVALUAR RUBROS ,LOS RUBROS SIEMPRE DEBERAN EMPEZAR COMO
	//@R-----RUBRO , @V------VARIABLES , @C-------CONSTANTE
	public Double ObtieneValorRubro(Session session, String sRubro ,String sEmpresa , String sEntorno, String sOrganizacion , Integer iAnio, Integer iPeriodo , String sDepartamento, String sEmpleado, String sOrigen, Date dFechaingreso, String sXdepartamento_id, String sXseccion_id, String sXcargo_id, Date dXfechainicial){
		Boolean bRecorre=true;
		Double dValorRetorno=0.00;
		String sOpcion="" ,sEvalua="" ,  sTipo="", sFormulaRubro="" , sSql_Rubro="" , sSql_Evalua="";
		Integer iInicio=0 ;
		Double dValorRubro=0.00, dValoraRemplazar=0.00;
	
		
		DAResultSet rs = null;

		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			sSql_Rubro="";
			sSql_Rubro+=" SELECT xformula , xtiformularubro,xvalor ";
			sSql_Rubro+=" FROM  "+connSource.translateTable("gp_rubros");
			sSql_Rubro+=" WHERE xrubro_id="+ DAUtils.formatValue(sRubro, DA.DA_DT_TEXT);
			sSql_Rubro+=" AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_Rubro+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_Rubro+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			rs = connData.openSQL(sSql_Rubro);

			if (rs.moveNext()) {
				sFormulaRubro=rs.getString("xformula");
				sTipo=rs.getString("xtiformularubro");
				dValorRubro=rs.getDouble("xvalor");
			}
			rs.close();
			
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if(sTipo.equals("F")){ // SI ES DE TIPO FORMULA REVISA FORMULA
			  while (bRecorre) {
				  //BUSCAMOS EL @ 
				  iInicio=sFormulaRubro.indexOf("@");
				  if(iInicio>=0){
					  sOpcion="";
					  sOpcion=sFormulaRubro.substring(iInicio, iInicio + 2);
					  
					  System.out.println ("-1--------------"+sFormulaRubro);
					 
					  
					  switch (sOpcion) {
					    case "@V": //VARIABLES	
					    	sEvalua=sFormulaRubro.substring(iInicio, iInicio + 6);
					    	dValoraRemplazar=ObtieneValorVariable(session, sEvalua, sEmpresa, sEntorno, sOrganizacion, iAnio, iPeriodo, sDepartamento, sEmpleado, sOrigen,dFechaingreso,  sXdepartamento_id,  sXseccion_id,  sXcargo_id,  dXfechainicial);
					    	sFormulaRubro=sFormulaRubro.replace(sEvalua, dValoraRemplazar.toString());
					    	break;
					    case "@R": //RUBRO
					    	sEvalua=sFormulaRubro.substring(iInicio, iInicio + 6);
					    	dValoraRemplazar=ObtieneValorRubro(session, sEvalua, sEmpresa, sEntorno, sOrganizacion, iAnio, iPeriodo, sDepartamento, sEmpleado,sOrigen,dFechaingreso,   sXdepartamento_id,   sXseccion_id,   sXcargo_id,   dXfechainicial);
					    	sFormulaRubro=sFormulaRubro.replace(sEvalua, dValoraRemplazar.toString());
					    	break;
					    case "@C": //CONSTANTE
					    	sEvalua=sFormulaRubro.substring(iInicio, iInicio + 6);
					    	sFormulaRubro=sFormulaRubro.replace(sEvalua, obtieneValorConstantes(sEmpresa,sEntorno,sOrganizacion,iAnio,sEvalua).toString());
					    	break;
					    default: //NO HACE NADA
					  }
				  
					  System.out.println ("-----2----------"+sFormulaRubro);
					  
					  bRecorre=true;
				  }else{
					  bRecorre=false;
				  }
			  }
		}
		
		if(sTipo.equals("V")||sTipo.equals("C")){ // SI ES DE TIPO CONSTANTE O VALOR
			dValorRetorno=dValorRubro;
		}

		try {
			sSql_Evalua="";
			sSql_Evalua+=" SELECT   ("+sFormulaRubro+")";
			sSql_Evalua+=" FROM "+connSource.translateTable("gp_anios");
			sSql_Evalua+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_Evalua+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_Evalua+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_Evalua+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			rs = connData.openSQL(sSql_Evalua);

			if (rs.moveNext()) {
				dValorRetorno=rs.getDouble(1); 
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*APLICANDO FORMULA DE EKON
		sValorRetorno="";
		FormulaContext formulaContext = new FormulaContext();
		FormulaEngine formulaEngine;
		try {
			formulaEngine = new FormulaEngine(session);
			formulaEngine.putContext("ctx", formulaContext);
			formulaEngine.prepare("formulaRubro", sFormulaRubro, "ctx");
			Object result = formulaEngine.execute("formulaRubro");
			sValorRetorno=result.toString();
		} catch (FormulaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Funciones_Generales = new Funciones_Generales();
		System.out.println ("resultado formulaaaaaaaaaaaaaaaaa"+Funciones_Generales.Redondear(dValorRetorno,2));
		//System.out.println ("resultado formul stringaaaaaaaaaaaaaaaaa"+sValorRetorno);
		
		 return Funciones_Generales.Redondear(dValorRetorno,2);
	}
	
	//EVALUAMOS LA VARIABLE ESTAS PUEDES SER SENTENCIAS SQL--->S , PORCENTAJE--->P (AL VALOR SE DIVIDE PARA 100), VALOR----->V
	//POR LO QUE PRIMERO DEBEMOS VERIFICAR EL TIPO DE VARIABLE
	
	public Double ObtieneValorVariable(Session session, String sVariable ,String sEmpresa , String sEntorno, String sOrganizacion , Integer iAnio, Integer iPeriodo , String sDepartamento, String sEmpleado, String sOrigen, Date dFechaingreso, String sXdepartamento_id, String sXseccion_id, String sXcargo_id, Date dXfechainicial){
		Double dValorRetorno;
		dValorRetorno=0.00;
		String sSql_variable ,sSql_consulta;
		String sXtipovariable, sXsql1,sXsql2, sXsql3,sXsql4;
		Double dXvalor;
		
		DAResultSet rs = null;
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		try {
			sSql_variable=" ";
			sSql_variable+=" SELECT xtipovariable, xsql1,xsql2,xvalor, xsql3,xsql4";
			sSql_variable+=" FROM "+connSource.translateTable("gp_variables");
			sSql_variable+=" WHERE xempresa_id= "+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_variable+=" AND xorganizacion_id= "+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_variable+=" AND xentorno_id= "+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_variable+=" AND xvariable_id= "+ DAUtils.formatValue(sVariable, DA.DA_DT_TEXT);
			rs = connData.openSQL(sSql_variable);

			sXtipovariable="";
			sXsql1="";
			sXsql2="";
			sXsql3="";
			sXsql4="";
			dXvalor=0.00;
			if (rs.moveNext()) {
				sXtipovariable=rs.getString("xtipovariable");
				sXsql1=rs.getString("xsql1");
				sXsql2=rs.getString("xsql2");
				sXsql3=rs.getString("xsql3");
				sXsql4=rs.getString("xsql4");
				dXvalor=rs.getDouble("xvalor");
			}
			rs.close();
			if(dXvalor==null){dXvalor=0.00;}
			if(sXsql1==null){sXsql1="";}
			if(sXsql2==null){sXsql2="";}
			
			if(sXsql3==null){sXsql3="";}
			if(sXsql4==null){sXsql4="";}
			
			 switch (sXtipovariable) {
			    case "S": //SQL	
			    	sSql_consulta=sXsql1 +" "+ sXsql2 +" "+ sXsql3 +" "+ sXsql4;
			    	//SE EVALUA LA CONSULTA SQL REMPLAZANDO LAS VARIABLES DEFINIDAS
			    	//EL RETORNO DEL SQL SIEMPRE SERA UN VALOR
			    	
			    	//@BASE-->USUARIO BASE DE DATOS
			    	//@EMPR-->EMPRESA    	@ORGA-->ORGANIZACION
			    	//@ENTO-->ENTORNO    	@ORIG-->ORIGEN
			    	//@EMPL-->EMPLEADO    	@ANIO-->ANIO ID
			    	//@PERI-->PERIODO ID	@FEIN-->FECHA INGRESO
			    	//@DPTO-->DEPARTAMENTO
			    	//@SECC-->SECCION
			    	//@CARG-->CARGO
			    	//@FECI-->FECHA INICIAL DEL CARGO
			    	//TODOS LOS NUMEROS QUE SE PONGAN EN UNA FORMULA DEBERAN SER CON DOS DECIMALES PEJEMPLO 500.00 o 645.56
			    	
			    	//REMPLAZAMOS LA CONSULTA POR LAS VRIABLES
					try {
						sSql_consulta=sSql_consulta.replace("@BASE", session.getConnectionData().getDBA()+".");
					} catch (OTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	
			    	sSql_consulta=sSql_consulta.replace("@EMPR", DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT));
			    	sSql_consulta=sSql_consulta.replace("@ORGA", DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT));
			    	sSql_consulta=sSql_consulta.replace("@ENTO", DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT));
			    	sSql_consulta=sSql_consulta.replace("@ORIG",DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT ));
			    	sSql_consulta=sSql_consulta.replace("@EMPL", DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT));
			    	sSql_consulta=sSql_consulta.replace("@ANIO", DAUtils.formatValue(iAnio,DA.DA_DT_INTEGER).toString());
			    	sSql_consulta=sSql_consulta.replace("@PERI", DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER).toString());
			    	sSql_consulta=sSql_consulta.replace("@FEIN", DAUtils.formatValue(dFechaingreso, DA.DA_DT_DATE).toString());
			    	
					sSql_consulta=sSql_consulta.replace("@DPTO", DAUtils.formatValue(sXdepartamento_id, DA.DA_DT_TEXT));
					sSql_consulta=sSql_consulta.replace("@SECC", DAUtils.formatValue(sXseccion_id, DA.DA_DT_TEXT));
					sSql_consulta=sSql_consulta.replace("@CARG", DAUtils.formatValue(sXcargo_id, DA.DA_DT_TEXT));
					sSql_consulta=sSql_consulta.replace("@FECI", DAUtils.formatValue(dXfechainicial, DA.DA_DT_DATE).toString());
			    	
			    	rs=null;
			    	rs = connData.openSQL(sSql_consulta);
			    	if (rs.moveNext()) {
			    		dValorRetorno=rs.getDouble(1);
			    		
			    	}
			    	if(dValorRetorno==null){dValorRetorno=0.00;}
			    	break;
			    case "V": //VALOR
			    	dValorRetorno=dXvalor;
			    	break;
			    case "P": //PORCENTAJE
			    	dValorRetorno=dXvalor/100;
			    	break;
			    default: //NO HACE NADA
			  }
			 
			 
			 
			 
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Funciones_Generales = new Funciones_Generales();
		return Funciones_Generales.Redondear(dValorRetorno,2);
	}
	
	//EVALUAMOS LAS CONSTANTES ESTAS VIENE DADAS POR AÑO , SIRVEN ESPECIFICAMENTE PARA VALORES ANUALES

	public Double ObtieneValorConstante(Session session, String sVariable ,String sEmpresa , String sEntorno, String sOrganizacion , Integer iAnio, Integer iPeriodo , String sDepartamento, String sEmpleado, String sOrigen, Date dFechaingreso){
		Double dValorRetorno=0.00;
		
		return dValorRetorno;
	}
	
	
	@SuppressWarnings("unused")
	public Boolean GeneraRolesEmple(Session session,String sEmpresa , String sEntorno, String sOrganizacion , Integer iAnio, Integer iPeriodo , String sEmpleado , String sOrigen, int iContadorliquidacion){
		String sSql_pagonomina="", sSql_existe="", sSql_insertar="", sSql_actualizar="",sSql_existe_in="",sSql_existe_des="" , sSql_pagonominaliq="";
		
		Integer iXperiodo_id = null,iXanio_id = null, iXorden, iXrolesemple_id=10, iXexiste, iXexiste_in, iXexiste_des;
		String sXempresa_id = null,sXentorno_id = null,sXorganizacion_id = null,sXrubro_id,sXtipoorigen = null,	sXtiporubro, sXempleado_id = null ,  sXdepartamento_id = null,sXdepartamento_nom = null,sXseccion_id = null,sXseccion_nom = null,sXcargo_id = null,sXcargo_nom = null,sXcentrocosto_id = null,sXusuariogenera = null,sXcuentadebe = null,sXcuentahaber = null;
		Double dXvalor,dXvalor_in,	dXvalor_de  , dXsaldoprestamos;
		Date dXfechaingreso = null ,  dXfechagenera = null,dXfechafinal = null  ;
		Boolean bXdespliegarol, bVerdadero=true, vFalso=false;
		String sXrubro_in,sXrubro_de;
		String [][]sVectorrubros=new String[50][50];
		Double [][]dVectorvalor=new Double[50][50];
		Double []dSaldoprestamo=new Double[50];
		Integer iFila_in=0,iFila_de=0, iColumna=0, iXcontadorliq=0;
		
		
		
		DAResultSet rs = null,rs_existe=null, rs_insertar=null, rs_actualizar=null;
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			sSql_pagonomina="";
			sSql_pagonomina+=" SELECT  "; 
			sSql_pagonomina+=" rolesemple.xperiodo_id,		    rolesemple.xanio_id,			rolesemple.xempresa_id,		    rolesemple.xentorno_id,   "; 
			sSql_pagonomina+=" rolesemple.xorganizacion_id,	    rolesemple.xrubro_id,			rolesemple.xtipoorigen,		    rolesemple.xtiporubro,    "; 
			sSql_pagonomina+=" rolesemple.xempleado_id,		    rolesemple.xvalor,				rolesemple.xdepartamento_id,	rolesemple.xdepartamento_nom,    "; 
			sSql_pagonomina+=" rolesemple.xseccion_id,		    rolesemple.xseccion_nom,		rolesemple.xcargo_id,			rolesemple.xcargo_nom,    "; 
			sSql_pagonomina+=" rolesemple.xcentrocosto_id,	    rolesemple.xfechaingreso,		rolesemple.xusuariogenera,		rolesemple.xfechagenera,    "; 
			sSql_pagonomina+=" rolesemple.xorden,			    rolesemple.xcuentadebe,		    rolesemple.xcuentahaber,		rolesemple.xfechafinal  , ";  
			sSql_pagonomina+=" rolesemple.xdespliegarol   ,     rolesemple.xsaldoprestamos ,    rolesemple.xcontadorliq ";  
			sSql_pagonomina+=" FROM ( "; 
			sSql_pagonomina+=" SELECT    "; 
			sSql_pagonomina+=" xperiodo_id,		    xanio_id,			xempresa_id,		xentorno_id,   "; 
			sSql_pagonomina+=" xorganizacion_id,	xrubro_id,			xtipoorigen,		xtiporubro,    "; 
			sSql_pagonomina+=" xempleado_id,		xvalor,				xdepartamento_id,	xdepartamento_nom, ";    
			sSql_pagonomina+=" xseccion_id,		    xseccion_nom,		xcargo_id,			xcargo_nom,    "; 
			sSql_pagonomina+=" xcentrocosto_id,	    xfechaingreso,		xusuariogenera,		xfechagenera,    "; 
			sSql_pagonomina+=" xorden,			    xcuentadebe,		xcuentahaber,		xfechafinal  ,  "; 
			sSql_pagonomina+=" xdespliegarol   ,    xsaldoprestamos ,   xcontadorliq  "; 
			sSql_pagonomina+=" FROM "+connSource.translateTable("gp_pagonomina");
			sSql_pagonomina+=" WHERE xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			sSql_pagonomina+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_pagonomina+=" AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_pagonomina+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_pagonomina+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_pagonomina+=" AND xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
			sSql_pagonomina+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			sSql_pagonomina+=" AND xtiporubro<>'P' ";
			if(sOrigen.equals("L")){ //EN EL CASO DE LIQUIDACION TENEMOS QUE PONER TAMBIEN LAS PROVISIONES QUE PARECEN EN ROL
				sSql_pagonominaliq+=" ) rolesemple  ";
				sSql_pagonominaliq+=" ORDER BY rolesemple.xtiporubro,rolesemple.xorden   ";
				//COMO DEBEMOS AGRUPAR LAS PROVISIONES QUE YA SE GENERARON Y NO SABEMOS EN QUE DEPARTAMENTO ES TOMAMOS LOS DATOS ACTUALES
				rs = connData.openSQL(sSql_pagonomina + sSql_pagonominaliq);
				if(rs.moveNext()){
					iXperiodo_id=rs.getInt("xperiodo_id");
					iXanio_id=rs.getInt("xanio_id");
					iXorden=rs.getInt("xorden");
					sXempresa_id=rs.getString("xempresa_id");
					sXentorno_id=rs.getString("xentorno_id");
					sXorganizacion_id=rs.getString("xorganizacion_id");
					sXrubro_id=rs.getString("xrubro_id");
					sXtipoorigen=rs.getString("xtipoorigen");
					sXtiporubro=rs.getString("xtiporubro");
					sXempleado_id=rs.getString("xempleado_id");
					sXdepartamento_id=rs.getString("xdepartamento_id");
					sXdepartamento_nom=rs.getString("xdepartamento_nom");
					sXseccion_id=rs.getString("xseccion_id");
					sXseccion_nom=rs.getString("xseccion_nom");
					sXcargo_id=rs.getString("xcargo_id");
					sXcargo_nom=rs.getString("xcargo_nom");
					sXcentrocosto_id=rs.getString("xcentrocosto_id");
					sXusuariogenera=rs.getString("xusuariogenera");
					sXcuentadebe=rs.getString("xcuentadebe");
					sXcuentahaber=rs.getString("xcuentahaber");
					dXvalor=rs.getDouble("xvalor");
					dXfechaingreso=rs.getDate("xfechaingreso");
					dXfechagenera=rs.getDate("xfechagenera");
					dXfechafinal=rs.getDate("xfechafinal");
					bXdespliegarol=rs.getBoolean("xdespliegarol");
					dXsaldoprestamos=rs.getDouble("xsaldoprestamos");
					iXcontadorliq=rs.getInt("xcontadorliq");
					//ARMAMOS EL SELECT PARA CONCIDERAR LAS PROVISIONES
					sSql_pagonominaliq="";
					sSql_pagonominaliq+=" UNION ";
					sSql_pagonominaliq+=" SELECT    ";
					sSql_pagonominaliq+=" "+DAUtils.formatValue(iXperiodo_id, DA.DA_DT_INTEGER)+",		"+DAUtils.formatValue(iXanio_id, DA.DA_DT_INTEGER)+",			xempresa_id,		xentorno_id,   ";
					sSql_pagonominaliq+=" xorganizacion_id,	xrubro_id,			"+DAUtils.formatValue(sXtipoorigen, DA.DA_DT_TEXT)+",		'A',    ";
					sSql_pagonominaliq+=" xempleado_id,		SUM(xvalor - xvalordevengado),		"+DAUtils.formatValue(sXdepartamento_id, DA.DA_DT_TEXT)+",	"+DAUtils.formatValue(sXdepartamento_nom, DA.DA_DT_TEXT)+",    ";
					sSql_pagonominaliq+=" "+DAUtils.formatValue(sXseccion_id, DA.DA_DT_TEXT)+",		"+DAUtils.formatValue(sXseccion_nom, DA.DA_DT_TEXT)+",		"+DAUtils.formatValue(sXcargo_id, DA.DA_DT_TEXT)+",			"+DAUtils.formatValue(sXcargo_nom, DA.DA_DT_TEXT)+",    ";
					sSql_pagonominaliq+=" "+DAUtils.formatValue(sXcentrocosto_id, DA.DA_DT_TEXT)+",	"+DAUtils.formatValue(dXfechaingreso, DA.DA_DT_DATE)+",		"+DAUtils.formatValue(sXusuariogenera, DA.DA_DT_TEXT)+",		"+DAUtils.formatValue(dXfechagenera, DA.DA_DT_DATE)+",    ";
					sSql_pagonominaliq+=" xorden,			    '',		'',		"+DAUtils.formatValue(dXfechafinal, DA.DA_DT_TEXT)+"  ,  ";
					sSql_pagonominaliq+=" xdespliegaliq  ,   0.00 ,   xcontadorliq  ";
					sSql_pagonominaliq+=" FROM "+connSource.translateTable("gp_pagonomina");
					sSql_pagonominaliq+=" WHERE  ";
					sSql_pagonominaliq+=" xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
					sSql_pagonominaliq+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
					sSql_pagonominaliq+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
					sSql_pagonominaliq+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
					sSql_pagonominaliq+=" AND xtiporubro='P' ";
					sSql_pagonominaliq+=" AND xcontadorliq="+ DAUtils.formatValue(iContadorliquidacion, DA.DA_DT_INTEGER);
					sSql_pagonominaliq+=" AND xfechafinal="+ DAUtils.formatValue(dXfechafinal, DA.DA_DT_DATE);
					sSql_pagonominaliq+=" AND xdespliegaliq="+ DAUtils.formatValue(bVerdadero, DA.DA_DT_BOOLEAN);
					sSql_pagonominaliq+=" GROUP BY  	xempresa_id,		xentorno_id,   ";
					sSql_pagonominaliq+=" xorganizacion_id,	xrubro_id,			 ";
					sSql_pagonominaliq+=" xempleado_id,		    ";
					sSql_pagonominaliq+=" xcentrocosto_id,	xfechaingreso,	 		    ";
					sSql_pagonominaliq+=" xorden,			    xdespliegaliq   ,      xcontadorliq  ";
					sSql_pagonominaliq+=" ) rolesemple  ";
					sSql_pagonominaliq+=" ORDER BY rolesemple.xtiporubro,rolesemple.xorden   ";
					sSql_pagonomina+=sSql_pagonominaliq;

				}
				rs.close();
				
				
			}
			if(sOrigen.equals("R")){
				sSql_pagonomina+=" ) rolesemple  ";
				sSql_pagonomina+=" ORDER BY rolesemple.xtiporubro,rolesemple.xorden   ";
			}
			
			
			rs = connData.openSQL(sSql_pagonomina);
			
			sVectorrubros=new String[50][50];
			dVectorvalor=new Double[50][50];
			dSaldoprestamo=new Double[50];
			iXcontadorliq=0;
			
			while (rs.moveNext()) {
				iXperiodo_id=rs.getInt("xperiodo_id");
				iXanio_id=rs.getInt("xanio_id");
				iXorden=rs.getInt("xorden");
				sXempresa_id=rs.getString("xempresa_id");
				sXentorno_id=rs.getString("xentorno_id");
				sXorganizacion_id=rs.getString("xorganizacion_id");
				sXrubro_id=rs.getString("xrubro_id");
				sXtipoorigen=rs.getString("xtipoorigen");
				sXtiporubro=rs.getString("xtiporubro");
				sXempleado_id=rs.getString("xempleado_id");
				sXdepartamento_id=rs.getString("xdepartamento_id");
				sXdepartamento_nom=rs.getString("xdepartamento_nom");
				sXseccion_id=rs.getString("xseccion_id");
				sXseccion_nom=rs.getString("xseccion_nom");
				sXcargo_id=rs.getString("xcargo_id");
				sXcargo_nom=rs.getString("xcargo_nom");
				sXcentrocosto_id=rs.getString("xcentrocosto_id");
				sXusuariogenera=rs.getString("xusuariogenera");
				sXcuentadebe=rs.getString("xcuentadebe");
				sXcuentahaber=rs.getString("xcuentahaber");
				dXvalor=rs.getDouble("xvalor");
				dXfechaingreso=rs.getDate("xfechaingreso");
				dXfechagenera=rs.getDate("xfechagenera");
				dXfechafinal=rs.getDate("xfechafinal");
				bXdespliegarol=rs.getBoolean("xdespliegarol");
				dXsaldoprestamos=rs.getDouble("xsaldoprestamos");
				iXcontadorliq=rs.getInt("xcontadorliq");
		
				sXrubro_in=null;
				dXvalor_in=null;
				sXrubro_de=null;
				dXvalor_de=null;
				
				
				if(sXtiporubro.equals("A")){//INGRESOS
					sVectorrubros[iFila_in][0]=sXrubro_id;
					dVectorvalor[iFila_in][0]=dXvalor;
					iFila_in+=1;
					sXrubro_in=sXrubro_id;
					dXvalor_in=dXvalor;
					sXrubro_de=null;
					dXvalor_de=null;
				}
				if(sXtiporubro.equals("B")){//DESCUENTO
					sVectorrubros[iFila_de][1]=sXrubro_id;
					dVectorvalor[iFila_de][1]=dXvalor;
					dSaldoprestamo[iFila_de]=dXsaldoprestamos;
					iFila_de+=1;
					sXrubro_in=null;
					dXvalor_in=null;
					sXrubro_de=sXrubro_id;
					dXvalor_de=dXvalor;
				}
		
			}
			
					
			Boolean bTermina=true;
			iFila_in=0;
			iFila_de=0;
			iXrolesemple_id=0;
		   do {   
			  
			   if (sVectorrubros[iFila_in][0]==null && sVectorrubros[iFila_de][1]==null){
					bTermina=false;
				}else{
			   
				    iXrolesemple_id+=10;
					sSql_insertar=" ";
					sSql_insertar+=" INSERT INTO "+connSource.translateTable("gp_rolesemple");
					sSql_insertar+=" ( ";
					sSql_insertar+=" xrolesemple_id, ";
					sSql_insertar+=" xperiodo_id,   ";
					sSql_insertar+=" xanio_id,   ";
					sSql_insertar+=" xempresa_id,   ";
					sSql_insertar+=" xentorno_id,   ";
					sSql_insertar+=" xorganizacion_id,   ";
					sSql_insertar+=" xtipoorigen,   ";
					sSql_insertar+=" xempleado_id,   ";
					sSql_insertar+=" xdepartamento_id,   ";
					sSql_insertar+=" xdepartamento_nom,   ";
					sSql_insertar+=" xseccion_id,   ";
					sSql_insertar+=" xseccion_nom,   ";
					sSql_insertar+=" xcargo_id,   ";
					sSql_insertar+=" xcargo_nom,   ";
					sSql_insertar+=" xcentrocosto_id,   ";
					sSql_insertar+=" xfechaingreso,   ";
					sSql_insertar+=" xusuariogenera,   ";
					sSql_insertar+=" xfechagenera,    ";
					sSql_insertar+=" xorden,    ";
					sSql_insertar+=" xcuentadebe,   "; 
					sSql_insertar+=" xcuentahaber,    ";
					sSql_insertar+=" xfechafinal  ,   ";
					sSql_insertar+=" xrubro_in,  ";
					sSql_insertar+=" xvalor_in,  ";
					sSql_insertar+=" xrubro_de,  ";
					sSql_insertar+=" xvalor_de   ,  ";
					sSql_insertar+=" xsaldoprestamo , ";
					sSql_insertar+=" xcontadorliq ";
					sSql_insertar+=" )  ";
					sSql_insertar+=" VALUES(  ";
					sSql_insertar+=DAUtils.formatValue(iXrolesemple_id, DA.DA_DT_INTEGER)+",  ";
					sSql_insertar+=DAUtils.formatValue(iXperiodo_id, DA.DA_DT_INTEGER)+",    ";
					sSql_insertar+=DAUtils.formatValue(iXanio_id, DA.DA_DT_INTEGER)+",    ";
					sSql_insertar+=DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT)+",    ";
					sSql_insertar+=DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT)+",    ";
					sSql_insertar+=DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT)+",   "; 
					sSql_insertar+=DAUtils.formatValue(sXtipoorigen, DA.DA_DT_TEXT)+",    ";
					sSql_insertar+=DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT)+",    ";
					sSql_insertar+=DAUtils.formatValue(sXdepartamento_id, DA.DA_DT_TEXT)+",    ";
					sSql_insertar+=DAUtils.formatValue(sXdepartamento_nom, DA.DA_DT_TEXT)+",    ";
					sSql_insertar+=DAUtils.formatValue(sXseccion_id, DA.DA_DT_TEXT)+",    ";
					sSql_insertar+=DAUtils.formatValue(sXseccion_nom, DA.DA_DT_TEXT)+",    ";
					sSql_insertar+=DAUtils.formatValue(sXcargo_id, DA.DA_DT_TEXT)+",    ";
					sSql_insertar+=DAUtils.formatValue(sXcargo_nom, DA.DA_DT_TEXT)+",    ";
					sSql_insertar+=DAUtils.formatValue(sXcentrocosto_id, DA.DA_DT_TEXT)+",   "; 
					sSql_insertar+=DAUtils.formatValue(dXfechaingreso, DA.DA_DT_DATE)+",    ";
					sSql_insertar+=DAUtils.formatValue(sXusuariogenera, DA.DA_DT_TEXT)+",    ";
					sSql_insertar+=DAUtils.formatValue(dXfechagenera, DA.DA_DT_DATE)+",    ";
					sSql_insertar+=DAUtils.formatValue(iXrolesemple_id, DA.DA_DT_INTEGER)+",    ";
					sSql_insertar+=DAUtils.formatValue(sXcuentadebe, DA.DA_DT_TEXT)+",   "; 
					sSql_insertar+=DAUtils.formatValue(sXcuentahaber, DA.DA_DT_TEXT)+",    ";
					sSql_insertar+=DAUtils.formatValue(dXfechafinal, DA.DA_DT_DATE)+" ,   ";
					sSql_insertar+=DAUtils.formatValue(sVectorrubros[iFila_in][0], DA.DA_DT_TEXT)+",  ";
					sSql_insertar+=DAUtils.formatValue(dVectorvalor[iFila_in][0], DA.DA_DT_DOUBLE)+",  ";
					sSql_insertar+=DAUtils.formatValue(sVectorrubros[iFila_de][1], DA.DA_DT_TEXT)+",  ";
					sSql_insertar+=DAUtils.formatValue(dVectorvalor[iFila_de][1], DA.DA_DT_DOUBLE)+"  ,    ";
					sSql_insertar+=DAUtils.formatValue(dSaldoprestamo[iFila_de], DA.DA_DT_DOUBLE)+"  ,    ";
					sSql_insertar+=DAUtils.formatValue(iXcontadorliq, DA.DA_DT_INTEGER)+"      ";
					sSql_insertar+=" ) ";
					connData.execSQL(sSql_insertar);
					iFila_in+=1;
					iFila_de+=1;
				}
			   
			  
		   } while (bTermina); 
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
	
	//FUNCION PARA OBTENER EL SALDO DE LOS PRESTAMOS
	public Double Saldoprestamos(Session session,String sXempresa_id , String  sXorganizacion_id , String sXentorno_id, String sXempleado_id, Integer iAnio , Integer iPeriodo, String sOrigen){
		String sSql_rubrosempleados="";
		DAResultSet rs_rubrosempleados=null;
		Double dValoresdescontados, dValorespendientes, dValortotal;
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		dValoresdescontados=dValorespendientes=dValortotal=0.00;
		
		try {
			sSql_rubrosempleados=" ";
			sSql_rubrosempleados+=" SELECT SUM(gp_cuotas.xmontocuota) ";
			sSql_rubrosempleados+=" FROM  ";
			sSql_rubrosempleados+=" "+connSource.translateTable("gp_cuotas")+ ", ";
			sSql_rubrosempleados+=" "+connSource.translateTable("gp_rubros");
			sSql_rubrosempleados+=" WHERE  gp_rubros.xempresa_id = gp_cuotas.xempresa_id ";
			sSql_rubrosempleados+=" AND gp_rubros.xorganizacion_id=gp_cuotas.xorganizacion_id ";
			sSql_rubrosempleados+=" AND gp_rubros.xentorno_id=gp_cuotas.xentorno_id ";
			sSql_rubrosempleados+=" AND gp_rubros.xrubro_id=gp_cuotas.xrubro_id ";
			sSql_rubrosempleados+=" AND gp_cuotas.xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
			sSql_rubrosempleados+=" AND gp_cuotas.xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
			sSql_rubrosempleados+=" AND gp_cuotas.xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
			sSql_rubrosempleados+=" AND gp_cuotas.xempleado_id="+ DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
			sSql_rubrosempleados+=" AND gp_cuotas.xestadocuota='D' ";
			sSql_rubrosempleados+=" AND gp_cuotas.xanio_id_des<="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_rubrosempleados+=" AND gp_cuotas.xperiodo_id_des<="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			rs_rubrosempleados=connData.openSQL(sSql_rubrosempleados);
			dValoresdescontados=0.00;
			if(rs_rubrosempleados.moveNext()){
				dValoresdescontados=rs_rubrosempleados.getDouble(1);
				if(dValoresdescontados==null){dValoresdescontados=0.00;}
			}
			rs_rubrosempleados.close();
			dValoresdescontados=0.00;
			
			sSql_rubrosempleados=" ";
			sSql_rubrosempleados+=" SELECT SUM(gp_cuotas.xmontocuota) ";
			sSql_rubrosempleados+=" FROM  ";
			sSql_rubrosempleados+=" "+connSource.translateTable("gp_cuotas")+ ", ";
			sSql_rubrosempleados+=" "+connSource.translateTable("gp_rubros");
			sSql_rubrosempleados+=" WHERE  gp_rubros.xempresa_id = gp_cuotas.xempresa_id ";
			sSql_rubrosempleados+=" AND gp_rubros.xorganizacion_id=gp_cuotas.xorganizacion_id ";
			sSql_rubrosempleados+=" AND gp_rubros.xentorno_id=gp_cuotas.xentorno_id ";
			sSql_rubrosempleados+=" AND gp_rubros.xrubro_id=gp_cuotas.xrubro_id ";
			sSql_rubrosempleados+=" AND gp_cuotas.xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
			sSql_rubrosempleados+=" AND gp_cuotas.xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
			sSql_rubrosempleados+=" AND gp_cuotas.xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
			sSql_rubrosempleados+=" AND gp_cuotas.xempleado_id="+ DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
			sSql_rubrosempleados+=" AND gp_cuotas.xestadocuota='X' ";
			//sSql_rubrosempleados+=" AND gp_cuotas.xanio_id_des > "+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			//sSql_rubrosempleados+=" AND gp_cuotas.xperiodo_id_des > "+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			rs_rubrosempleados=connData.openSQL(sSql_rubrosempleados);
			dValorespendientes=0.00;
			if(rs_rubrosempleados.moveNext()){
				dValorespendientes=rs_rubrosempleados.getDouble(1);
				if(dValorespendientes==null){dValorespendientes=0.00;}
			}
			rs_rubrosempleados.close();
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(sOrigen=="R"){dValortotal = dValorespendientes - dValoresdescontados ;}
		return dValortotal;
	}
	
	//FUNCION PARA CALCULAR EL DESAHUCIO, DESPIDO , EMBARAZO
	@SuppressWarnings("unused")
	public Double [] Rubroliquidacion(Session session,String sEmpresa , String sEntorno, String sOrganizacion , Integer iAnio, Integer iPeriodo , String sDepartamento, String sEmpleado, String sOrigen, Date dXfechai_liqui , Date dXfechas_liqui, String sXcargo_id_liqui ,String sXmotivoliqui, String sXrubrofaltas, Date dFechaingresoempr, String sXcontrato_id, String sXhorastrabajadas){
		String sSql_ingresos="",sSql_faltas="", sSql_periodoanterior="",sSql_condicion="", sSql_contrato="", sSql_periodoinicio="", sSql_horas="";
		DAResultSet rs_ingresos=null, rs_faltas=null, rs_periodoanterior=null, rs_contrato=null , rs_periodoinicio=null,rs_horas=null;
		Integer iAnioactual, iPeriodoactual, iAnioanterior, iPeriodoanterior, iContador, iAnioinicio , iPeriodoinicio;
		Double dValoractual, dValoranterior,dValoractualfaltas, dValoranteriorfaltas, dHorastrabajadas;
		Boolean bVerdadero=true, bFalso=false;
		Integer iXhorasmensual,iXhorassemana,iXhorasdiaria ;
		Double []dRetorno=new Double[4];
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//TOMAMOS EL PERIODO ACTUA Y ANTERIO
		dValoractual=dValoranterior=0.00;
		dValoractualfaltas=dValoranteriorfaltas=0.00;
		try {
			sSql_periodoanterior="";
			sSql_periodoanterior+=" SELECT  ";
			sSql_periodoanterior+=" xperiodo_id,xanio_id ";
			sSql_periodoanterior+=" FROM   "+connSource.translateTable("gp_periodos");
			sSql_periodoanterior+=" WHERE  ";
			sSql_periodoanterior+=" xanio_id<="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_periodoanterior+=" AND xperiodo_id<"+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			sSql_periodoanterior+=" AND xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_periodoanterior+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_periodoanterior+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_periodoanterior+=" ORDER BY xhasta DESC ";
			iAnioactual=iPeriodoactual=iAnioanterior=iPeriodoanterior=0;
			iContador=1;
			rs_periodoanterior=connData.openSQL(sSql_periodoanterior);
			while (rs_periodoanterior.moveNext()) {
				if(iContador==1){
					iAnioactual=rs_periodoanterior.getInt("xanio_id");
					iPeriodoactual=rs_periodoanterior.getInt("xperiodo_id");
				}
				
				if(iContador==2){
					iAnioanterior=rs_periodoanterior.getInt("xanio_id");
					iPeriodoanterior=rs_periodoanterior.getInt("xperiodo_id");
				}
				
				iContador+=1;
			}
			rs_periodoanterior.close();
			//TODOS LOS CALCULOS SE REALIZAN CON EL ULTIMO INGRESO GRAVABLE(PAGA IESS) - EL RUBRO DE FALTAS
			for(int i=1;i<=2;i++){
				sSql_ingresos="";
				sSql_ingresos+=" SELECT SUM(xvalor) ";
				sSql_ingresos+=" FROM   "+connSource.translateTable("gp_pagonomina");
				sSql_ingresos+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_ingresos+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_ingresos+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				sSql_ingresos+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
				sSql_ingresos+=" AND xpagaiess="+ DAUtils.formatValue(bVerdadero, DA.DA_DT_BOOLEAN);
				sSql_ingresos+=" AND xdespliegarol="+ DAUtils.formatValue(bVerdadero, DA.DA_DT_BOOLEAN);
				if(i==1){
					sSql_condicion="";
					sSql_condicion+=" AND xanio_id="+ DAUtils.formatValue(iAnioactual, DA.DA_DT_INTEGER);
					sSql_condicion+=" AND xperiodo_id="+ DAUtils.formatValue(iPeriodoactual, DA.DA_DT_INTEGER);
					sSql_condicion+=" AND xtipoorigen='R' ";
					sSql_condicion+=" AND xtiporubro='A' ";
					rs_ingresos=connData.openSQL(sSql_ingresos + sSql_condicion);
					if(rs_ingresos.moveNext()){
						dValoractual=rs_ingresos.getDouble(1);
						if(dValoractual==null){dValoractual=0.00;}
					}
					rs_ingresos.close();
					//TOMAMOS EL RUBRO DE FALTAS
					sSql_condicion="";
					sSql_condicion+=" AND xanio_id="+ DAUtils.formatValue(iAnioactual, DA.DA_DT_INTEGER);
					sSql_condicion+=" AND xperiodo_id="+ DAUtils.formatValue(iPeriodoactual, DA.DA_DT_INTEGER);
					sSql_condicion+=" AND xtipoorigen='R' ";
					sSql_condicion+=" AND xtiporubro='B' ";
					sSql_condicion+=" AND xrubro_id= "+ DAUtils.formatValue(sXrubrofaltas, DA.DA_DT_TEXT);
					rs_ingresos=connData.openSQL(sSql_ingresos + sSql_condicion);
					if(rs_ingresos.moveNext()){
						dValoractualfaltas=rs_ingresos.getDouble(1);
						if(dValoractualfaltas==null){dValoractualfaltas=0.00;}
					}
					rs_ingresos.close();
					
				}
				
				if(i==2){
					sSql_condicion="";
					sSql_condicion+=" AND xanio_id="+ DAUtils.formatValue(iAnioanterior, DA.DA_DT_INTEGER);
					sSql_condicion+=" AND xperiodo_id="+ DAUtils.formatValue(iPeriodoanterior, DA.DA_DT_INTEGER);
					sSql_condicion+=" AND xtipoorigen='R' ";
					sSql_condicion+=" AND xtiporubro='A' ";
					rs_ingresos=connData.openSQL(sSql_ingresos + sSql_condicion);
					if(rs_ingresos.moveNext()){
						dValoranterior=rs_ingresos.getDouble(1);
						if(dValoranterior==null){dValoranterior=0.00;}
					}
					rs_ingresos.close();
					//TOMAMOS EL RUBRO DE FALTAS
					sSql_condicion="";
					sSql_condicion+=" AND xanio_id="+ DAUtils.formatValue(iAnioanterior, DA.DA_DT_INTEGER);
					sSql_condicion+=" AND xperiodo_id="+ DAUtils.formatValue(iPeriodoanterior, DA.DA_DT_INTEGER);
					sSql_condicion+=" AND xtipoorigen='R' ";
					sSql_condicion+=" AND xtiporubro='B' ";
					sSql_condicion+=" AND xrubro_id= "+ DAUtils.formatValue(sXrubrofaltas, DA.DA_DT_TEXT);
					rs_ingresos=connData.openSQL(sSql_ingresos + sSql_condicion);
					if(rs_ingresos.moveNext()){
						dValoranteriorfaltas=rs_ingresos.getDouble(1);
						if(dValoranteriorfaltas==null){dValoranteriorfaltas=0.00;}
					}
					rs_ingresos.close();
				}
				
			}
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TOMAMOS EL PERIODO DEL EMPLEADO DESDE EL CUAL EMPEZO A TRABAJAR ESTO SE DA CON LA FECHA DE INGRESO
		//A LA EMPRESA CON EL dFechaingresoempr PODEMOS VER EL PERIODO DESDE DONDE EMPEZAR A TOMAR
		iAnioinicio =iPeriodoinicio=0;
		try {
			sSql_periodoinicio="";
			sSql_periodoinicio+=" SELECT xanio_id,xperiodo_id  ";
			sSql_periodoinicio+=" FROM  "+connSource.translateTable("gp_periodos");
			sSql_periodoinicio+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_periodoinicio+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_periodoinicio+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_periodoinicio+=" AND "+ DAUtils.formatValue(dFechaingresoempr, DA.DA_DT_DATE)+">=xdesde ";
			sSql_periodoinicio+=" AND "+ DAUtils.formatValue(dFechaingresoempr, DA.DA_DT_DATE)+"<=xhasta ";
			rs_periodoinicio=connData.openSQL(sSql_periodoinicio);
			if(rs_periodoinicio.moveNext()){
				iAnioinicio=rs_periodoinicio.getInt("xanio_id");
				iPeriodoinicio=rs_periodoinicio.getInt("xperiodo_id");
				if(iAnioinicio==null){iAnioinicio=0;}
				if(iPeriodoinicio==null){iPeriodoinicio=0;}
			}
			rs_periodoinicio.close();
		} catch (DAException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//TOMAMOS LOS DATOS DEL CONTRATO PARA PODER CALCULAR EN FUNCION DE LAS HORAS TRABAJADAS
		iXhorasmensual=iXhorassemana=iXhorasdiaria=0;
		try {
			sSql_contrato="";
			sSql_contrato+=" SELECT xhorasmensual,xhorassemana,xhorasdiaria  ";
			sSql_contrato+=" FROM  "+connSource.translateTable("gp_contratos");
			sSql_contrato+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_contrato+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_contrato+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_contrato+=" AND xcontrato_id="+ DAUtils.formatValue(sXcontrato_id, DA.DA_DT_TEXT);
			rs_contrato=connData.openSQL(sSql_contrato);
			iXhorasmensual=iXhorassemana=iXhorasdiaria=0;
			if(rs_contrato.moveNext()){
				iXhorasmensual=rs_contrato.getInt("xhorasmensual");
				iXhorassemana=rs_contrato.getInt("xhorassemana");
				iXhorasdiaria=rs_contrato.getInt("xhorasdiaria");
				if(iXhorasmensual==null){iXhorasmensual=0;}
				if(iXhorassemana==null){iXhorassemana=0;}
				if(iXhorasdiaria==null){iXhorasdiaria=0;}
			}
			
			
			rs_contrato.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//VAMOS A SUMAR TODAS LAS HORAS TRABAJADAS (H)
		dHorastrabajadas=0.00;
		try {
			sSql_horas="";
			sSql_horas+=" SELECT SUM(xhoras) ";
			sSql_horas+=" FROM "+connSource.translateTable("gp_asistencias");
			sSql_horas+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_horas+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_horas+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_horas+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			sSql_horas+=" AND xtipoasistencia_id="+ DAUtils.formatValue(sXhorastrabajadas, DA.DA_DT_TEXT);
			sSql_horas+=" AND xanio_id>="+ DAUtils.formatValue(iAnioinicio, DA.DA_DT_INTEGER);
			sSql_horas+=" AND xperiodo_id>="+ DAUtils.formatValue(iPeriodoinicio, DA.DA_DT_INTEGER);
			sSql_horas+=" AND xanio_id<="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_horas+=" AND xperiodo_id<="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			dHorastrabajadas=0.00;
			rs_horas=connData.openSQL(sSql_horas);
			if(rs_horas.moveNext()){
				dHorastrabajadas=rs_horas.getDouble(1);
				if(dHorastrabajadas==null){dHorastrabajadas=0.00;}
			}
			rs_horas.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//VAMOS A CALCULAR EN FUNCION DEL CONTRATO CUANTO SERIA UN AÑO DE TRABAJO
		Integer iUnanio=0, iTresanios=0, iVeinte5anios=0;
		Double dAniostrabajados=0.00, dValorcalculo=0.00;
		
		
		iUnanio=12*iXhorasmensual;
		
		iTresanios=(12*3)*iXhorasmensual;
		
		iVeinte5anios=(12*25)*iXhorasmensual;
		
		dAniostrabajados=dHorastrabajadas / (iUnanio);
		
		//EL AÑO QUE SE OBTIENE PUEDE SER UN NUMERO DECIMAL
		//POR LO QUE DEBEMOS SEPARAR LA PARTE DECIMAL, Y ENTERA
		int iAnioentero = Integer.valueOf(dAniostrabajados.intValue());
		Double dAniodecimal=dAniostrabajados - iAnioentero;
		int iAniodecimal=0;
		if(dAniodecimal>0){iAniodecimal+=1;}
		
		Double dDesahucionormal=0.00, dDesahuciointempestivo=0.00, dDespidointempestivo=0.00, dDespidomaternidad=0.00 , dPorcentajedesahucio=25.00;
		
		if(dValoractual>0){
			dValorcalculo=dValoractual - dValoractualfaltas;
		}else{
			if(dValoranterior>0){
				dValorcalculo=dValoranterior-dValoranteriorfaltas;
			}
		}
		
		dDesahucionormal=(dValorcalculo*(25.00/100.00))*iAnioentero;
		
		if(sXmotivoliqui=="2"){//DESPIDO INTEMPESTIVO
			//DESPIDO INTEMPESTIVO
			if((iAnioentero+iAniodecimal)<3){
				dDespidointempestivo=dValorcalculo*3.00;
			}else{
				if((iAnioentero+iAniodecimal)>25){
					dDespidointempestivo=dValorcalculo*25.00;
				}else{
					dDespidointempestivo=dValorcalculo*(iAnioentero+iAniodecimal);
				}
			}
			//DESAHUCIO
			if(iAnioentero<1){
				dDesahucionormal=(dValorcalculo*(25.00/100.00))*1.00;
			}else{
				dDesahucionormal=(dValorcalculo*(25.00/100.00))*iAnioentero;
			}
		}
		
		if(sXmotivoliqui=="6"){//DESPIDO EMBARAZO
			dDespidomaternidad=dValorcalculo*12.00;
			dDesahucionormal=(dValorcalculo*(25.00/100.00))*iAnioentero;
		}
		
		dRetorno[0]=dDesahucionormal;
		dRetorno[1]=dDesahucionormal;
		dRetorno[2]=dDespidointempestivo;
		dRetorno[3]=dDespidomaternidad;
		
		
		return dRetorno;
	}
	
	
	//FUNCION PARA REVERSAR LA LIQUIDACION
	@SuppressWarnings("unused")
	public Boolean Reversaliquidacion(Session session,String sEmpresa , String sEntorno, String sOrganizacion , Integer iAnio, Integer iPeriodo , String sDepartamento, String sEmpleado, String sOrigen, Date dXfechai_liqui , Date dXfechas_liqui, String sXcargo_id_liqui ,String sXmotivoliqui , int iContadorliquidacion){
		Boolean bRetorno=true, bVerdadero=true, bFalso=false;
		String sSql_borrapagonomina="",sSql_borraasistencia="", sSql_borrarolesemple="", sSql_actualizapagonomina="", sSql_actualizacargo="", sUsuariogenera="";
		DAResultSet rs_borrapagonomina=null,rs_borraasistencia=null, rs_borrarolesemple=null, rs_actualizapagonomina=null;
		Date dFechagenera;
		
		sUsuariogenera=session.getUserInfo().getUserName();	
		
		dFechagenera=null;
		try {
			dFechagenera= session.getServerNow();
		} catch (OTException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			//BORRAMOS PAGONOMINA
			sSql_borrapagonomina="";
			sSql_borrapagonomina+=" DELETE ";
			sSql_borrapagonomina+=" FROM  "+connSource.translateTable("gp_pagonomina");
			sSql_borrapagonomina+=" WHERE  ";
			sSql_borrapagonomina+=" xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_borrapagonomina+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_borrapagonomina+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_borrapagonomina+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_borrapagonomina+=" AND xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			sSql_borrapagonomina+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			sSql_borrapagonomina+=" AND xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
			sSql_borrapagonomina+=" AND xcontadorliq="+ DAUtils.formatValue(iContadorliquidacion, DA.DA_DT_INTEGER);
			sSql_borrapagonomina+=" AND xfechafinal="+ DAUtils.formatValue(dXfechas_liqui, DA.DA_DT_DATE);
			connData.execSQL(sSql_borrapagonomina);
			
			//BORRAMOS ROLES EMPLEADOS
			sSql_borrarolesemple="";
			sSql_borrarolesemple+=" DELETE ";
			sSql_borrarolesemple+=" FROM  "+connSource.translateTable("gp_rolesemple");
			sSql_borrarolesemple+=" WHERE  ";
			sSql_borrarolesemple+=" xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_borrarolesemple+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_borrarolesemple+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_borrarolesemple+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_borrarolesemple+=" AND xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			sSql_borrarolesemple+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			sSql_borrarolesemple+=" AND xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
			sSql_borrarolesemple+=" AND xcontadorliq="+ DAUtils.formatValue(iContadorliquidacion, DA.DA_DT_INTEGER);
			sSql_borrarolesemple+=" AND xfechafinal="+ DAUtils.formatValue(dXfechas_liqui, DA.DA_DT_DATE);
			connData.execSQL(sSql_borrarolesemple);
			
			//BORRAMOS ASISTENCIA
			sSql_borraasistencia="";
			sSql_borraasistencia+=" DELETE ";
			sSql_borraasistencia+=" FROM  "+connSource.translateTable("gp_asistencias");
			sSql_borraasistencia+=" WHERE  ";
			sSql_borraasistencia+=" xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_borraasistencia+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_borraasistencia+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_borraasistencia+=" AND xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
			sSql_borraasistencia+=" AND xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
			sSql_borraasistencia+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			sSql_borraasistencia+=" AND xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
			sSql_borraasistencia+=" AND xcontadorliq="+ DAUtils.formatValue(iContadorliquidacion, DA.DA_DT_INTEGER);
			sSql_borraasistencia+=" AND xfechafinal="+ DAUtils.formatValue(dXfechas_liqui, DA.DA_DT_DATE);
			connData.execSQL(sSql_borraasistencia);
			
			//ACTUALIZAMOS EL PAGO NOMINA DE LAS PROVISIONES MARCADAS
			sSql_actualizapagonomina="";
			sSql_actualizapagonomina+=" UPDATE "+connSource.translateTable("gp_pagonomina");
			sSql_actualizapagonomina+=" SET xfechafinal=NULL,xcontadorliq=NULL ";
			sSql_actualizapagonomina+=" WHERE  ";
			sSql_actualizapagonomina+=" xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_actualizapagonomina+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_actualizapagonomina+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_actualizapagonomina+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			sSql_actualizapagonomina+=" AND xcontadorliq="+ DAUtils.formatValue(iContadorliquidacion, DA.DA_DT_INTEGER);
			sSql_actualizapagonomina+=" AND xfechafinal="+ DAUtils.formatValue(dXfechas_liqui, DA.DA_DT_DATE);
			sSql_actualizapagonomina+=" AND xtiporubro='P' ";
			sSql_actualizapagonomina+=" AND xdespliegaliq="+ DAUtils.formatValue(bVerdadero, DA.DA_DT_BOOLEAN);
			connData.execSQL(sSql_actualizapagonomina);
			
			//RESETEAMOS LA FECHA DE LIQUIDACION DEL EMPLEADO , MOTIVO, CONTADOR , MOTIVO
			sSql_actualizacargo="";
			sSql_actualizacargo+=" UPDATE  "+connSource.translateTable("gp_cargosemplea");
			sSql_actualizacargo+=" SET xmotivo=null,xmotivoliqui=null,xcontadorliq=null,xfechafinal=null ,";
			sSql_actualizacargo+=" xfecharev="+DAUtils.formatValue(dFechagenera, DA.DA_DT_DATE)+",xusuariorev="+DAUtils.formatValue(sUsuariogenera, DA.DA_DT_TEXT)+" ";
			sSql_actualizacargo+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_actualizacargo+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_actualizacargo+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_actualizacargo+=" AND xempleado_id="+ DAUtils.formatValue(sEmpleado, DA.DA_DT_TEXT);
			sSql_actualizacargo+=" AND xdepartamento_id="+ DAUtils.formatValue(sDepartamento, DA.DA_DT_TEXT);
			sSql_actualizacargo+=" AND xfechafinal="+ DAUtils.formatValue(dXfechas_liqui, DA.DA_DT_DATE);
			sSql_actualizacargo+=" AND xcontadorliq="+ DAUtils.formatValue(iContadorliquidacion, DA.DA_DT_INTEGER);
			connData.execSQL(sSql_actualizacargo);
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			System.out.println ("<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>><<<"+e.getSQLErrorCode());
			e.printStackTrace();
		}
	
		return bRetorno;
	}
	
	
	
	//FUNCION PARA GENERAL ROL DE PAGOS
	// SON LOS PARAMETROS PARA LA LIQUIDACION Date dXfechai_liqui , Date dXfechas_liqui, String sXcargo_id_liqui , ,String sXmotivoliqui
	@SuppressWarnings("unused")
	public Boolean GeneraRol(Session session,String sEmpresa , String sEntorno, String sOrganizacion , Integer iAnio, Integer iPeriodo , String sDepartamento, String sEmpleado, String sOrigen, Date dXfechai_liqui , Date dXfechas_liqui, String sXcargo_id_liqui ,String sXmotivoliqui , int iContadorliquidacion, Date dFechafinperiodo){
		Boolean bRetorno=false, bXdespliegarol , bXadecimoc=false,	bXadecimot=false,		bXafondos=false, bXgenera=false;
		//VARIABLES PARA TOMAR LOS DATOS DE LA VISTA
		String  sXcargo_id, sXdepartamento_id, sXseccion_id, sXempresa_id, sXorganizacion_id, sXentorno_id, sXempleado_id,sXnombrecompleto, sXcentrocosto_id, sXestadocargo, sXrubro, sXcontrato_id;
		String sXcuentadebe, sXcuentahaber, sXnombre_dep , sXnombre_sec, sXnombre_car, sXtiporubro, sXrubrofondosreserv, sXrubroimpuestorent,sXtiposfondos;
		Date dXfechafinal, dXfechainicial,dXfechaingreso,dFechagenera ;
		Double dXsueldo, dXvalor,dXvalor_rubro, dXvalor_total_rubro, dXvalor_rubro_impuesto, dSaldoprestamo;
		Integer iXorden, iXrolesemple_id=10;
		String sSql_empleados , sSql_rubrosempleados , sSql_cuentas , sSql_nombres, sSql_insertarol, sUsuariogenera, sSql_rubroprestamo, sSql_parametros, sSql_generarubro;
		String[] sRubrosingreso, sRubrosdescuentos;
		Integer iRubrosingreso=0, iRubrosdescuento=0, iRubroprestamos=0,iLiquida=0, iGenerarubro=0;
		String  sXrubrodesahucio,sXrubrodespido,sXrubroembarazo, sXrubrofaltas, sXhorastrabajadas;
		Boolean bXestadorubro=false,bXpagaiess=false,bXpagaimpuesto=false,bXpagaprovision=false,bXrubrobasico=false, bXdespliegaliq=false;
		Double []dRetornoliquidar=new Double[4];
		Double dXrubrodesahucio=0.00 ,		dXrubrodespido=0.00 ,		dXrubroembarazo=0.00;
		
		
		DAResultSet rs = null, rs_rubros=null , rs_cuentas=null, rs_nombres=null , rs_insertarol=null, rs_prestamos=null, rs_parametros=null, rs_generarubro=null;
		
		sUsuariogenera=session.getUserInfo().getUserName();	
		
		dFechagenera=null;
		try {
			dFechagenera= session.getServerNow();
		} catch (OTException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		//UTLIZAMOS LA VISTA DE LOS EMPLEADOS DEPARTAMENTO
		//RECORREMOS LA TABLA PARA PROCESAR
		
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//TOMAMOS LOS DATOS DE PARAMETROS EMPRESA POR RUBROS ESPECIALES
		sXrubrofondosreserv="";
		sXrubroimpuestorent="";
		sXhorastrabajadas="";
		sXrubrofaltas="";
		sXrubrodesahucio="";
		sXrubrodespido="";
		sXrubroembarazo="";
		
		try {
			sSql_parametros="";
			sSql_parametros+=" SELECT xrubrofondosreserv, xrubroimpuestorent , xrubrodesahucio,xrubrodespido,xrubroembarazo , xrubrofaltas ,xhorastrabajadas ";
			sSql_parametros+=" FROM  "+connSource.translateTable("gp_parametros");
			sSql_parametros+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_parametros+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_parametros+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			rs_parametros=connData.openSQL(sSql_parametros);
			if(rs_parametros.moveNext()){
				sXrubrofondosreserv=rs_parametros.getString("xrubrofondosreserv");
				sXrubroimpuestorent=rs_parametros.getString("xrubroimpuestorent");
				sXrubrodesahucio=rs_parametros.getString("xrubrodesahucio");
				sXrubrodespido=rs_parametros.getString("xrubrodespido");
				sXrubroembarazo=rs_parametros.getString("xrubroembarazo");
				sXrubrofaltas=rs_parametros.getString("xrubrofaltas");
				sXhorastrabajadas=rs_parametros.getString("xhorastrabajadas");
				if(sXrubrofondosreserv==null){sXrubrofondosreserv="";}
				if(sXrubroimpuestorent==null){sXrubroimpuestorent="";}
				if(sXrubrodesahucio==null){sXrubrodesahucio="";}
				if(sXrubrodespido==null){sXrubrodespido="";}
				if(sXrubroembarazo==null){sXrubroembarazo="";}
				if(sXrubrofaltas==null){sXrubrofaltas="";}
			}
			rs_parametros.close();
		} catch (DAException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
				
		
		try {
			sSql_empleados="";
			sSql_empleados+="SELECT " ;
			sSql_empleados+="xcargo_id, " ;
			sSql_empleados+="xdepartamento_id, " ;
			sSql_empleados+="xseccion_id, " ;
			sSql_empleados+="xfechainicial, " ;
			sSql_empleados+="xempresa_id, " ;
			sSql_empleados+="xorganizacion_id, " ;
			sSql_empleados+="xentorno_id, " ;
			sSql_empleados+="xempleado_id, " ;
			sSql_empleados+="xnombrecompleto, " ;
			sSql_empleados+="xcentrocosto_id, " ;
			sSql_empleados+="xestadocargo, " ;
			sSql_empleados+="xfechafinal, " ;
			sSql_empleados+="xsueldo ," ;
			sSql_empleados+="xfechaingreso ," ;
			sSql_empleados+="xcontrato_id , " ;
			sSql_empleados+="xtiposfondos, " ;
			sSql_empleados+="xadecimoc, " ;
			sSql_empleados+="xadecimot, " ;
			sSql_empleados+="xafondos " ;
			sSql_empleados+=" FROM "+connSource.translateTable("gp_emplnominas") ;
			sSql_empleados+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_empleados+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			sSql_empleados+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
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
			
			if(sOrigen.equals("L")){//LIQUIDACION
				//EL PARAMETRO DE dXfechai ES LA FECHA DE INGRESO AL CARGO QUE SE VA HA LIQUIDAR
				//NO ES LA FECHA DE INGRESO A LA EMPRESA
				sSql_empleados+=" AND xfechafinal IS NULL";
				sSql_empleados+=" AND xfechainicial = "+ DAUtils.formatValue(dXfechai_liqui, DA.DA_DT_DATE);
				sSql_empleados+=" AND xcargo_id = "+DAUtils.formatValue(sXcargo_id_liqui, DA.DA_DT_TEXT);
				sSql_empleados+=" AND xdepartamento_id = "+DAUtils.formatValue(sDepartamento, DA.DA_DT_TEXT);
			}
			
			sRubrosingreso=sRubrosdescuentos=null;
			iRubrosingreso=iRubrosdescuento=0;
			
			//SE EMPIEZA A RECORRER LOS EMPLEADOS PARA GENERAR ASISTENCIAS Y VALORES DE NOMINA
			rs = connData.openSQL(sSql_empleados);
			
			while (rs.moveNext()) {
				sXcargo_id=rs.getString("xcargo_id");
				sXdepartamento_id=rs.getString("xdepartamento_id");
				sXseccion_id=rs.getString("xseccion_id");
				sXempresa_id=rs.getString("xempresa_id");
				sXorganizacion_id=rs.getString("xorganizacion_id");
				sXentorno_id=rs.getString("xentorno_id");
				sXempleado_id=rs.getString("xempleado_id");
				sXnombrecompleto=rs.getString("xnombrecompleto");
				sXcentrocosto_id=rs.getString("xcentrocosto_id");
				sXestadocargo=rs.getString("xestadocargo");
				dXfechafinal=rs.getDate("xfechafinal");
				dXfechainicial=rs.getDate("xfechainicial");
				dXsueldo=rs.getDouble("xsueldo");
				dXfechaingreso=rs.getDate("xfechaingreso");//FECHA DE INGRESO A LA EMPRESA VALIDO PARA CALCULOS DE FONDOS DE RESERVA
				sXcontrato_id=rs.getString("xcontrato_id");
				sXtiposfondos=rs.getString("xtiposfondos");
				bXafondos=rs.getBoolean("xafondos");
				bXadecimoc=rs.getBoolean("xadecimoc");
				bXadecimot=rs.getBoolean("xadecimot");
				dRetornoliquidar=null;
				
				//EN CASO DE LIQUIDACION SE TIENE FECHA DE SALIDA
				if(sOrigen=="R"){dXfechas_liqui=null;}
				
				//GENERAMOS ASISTENCIAS
				Asistencias = new Genera_Asistencia_Nomina();
				Asistencias.GeneraAsistencia(session, sXentorno_id, sXorganizacion_id, sXempresa_id, sXdepartamento_id, sXempleado_id, iAnio, iPeriodo,sOrigen ,dXfechaingreso,sXcargo_id,dXfechas_liqui ,sXcontrato_id , sXtiposfondos, dXfechas_liqui, iContadorliquidacion );
				Asistencias=null;
				//GENERAMOS EL ROL DE PAGOS TOMANDO EN CUENTA LOS RUBROS QUE SE TIENE EN LA FICHA
				//Y LOS RUBROS QUE SE INGRESARON POR OTROR RUBROS VARIABLES
				//DEBEMOS TOMAR EN CUENTA QUE SI ESTA EN LA FICHA Y NO EN EL DEPARTAMENTO
				//AL CONTABILIZAR EXISTIRA PROBRLEMAS
				sSql_rubrosempleados="";
				sSql_rubrosempleados+="  SELECT rubroempleado.xrubro_id as  xrubro, SUM(rubroempleado.xvalor)  as xvalor , rubroempleado.xorden as xorden , rubroempleado.xtiporubro as xtiporubro , rubroempleado.xdespliegarol as xdespliegarol , rubroempleado.xestado as xestado, rubroempleado.xpagaiess as xpagaiess , rubroempleado.xpagaimpuesto as xpagaimpuesto, rubroempleado.xpagaprovision as xpagaprovision ,rubroempleado.xrubrobasico as xrubrobasico , rubroempleado.xdespliegaliq as xdespliegaliq";
				sSql_rubrosempleados+="  FROM( ";
				sSql_rubrosempleados+="  SELECT gp_rubrosempleado.xrubro_id , gp_rubrosempleado.xvalor ,gp_rubros.xorden , gp_rubros.xtiporubro, gp_rubros.xdespliegarol, gp_rubros.xestado,gp_rubros.xpagaiess,gp_rubros.xpagaimpuesto,gp_rubros.xpagaprovision,gp_rubros.xrubrobasico , gp_rubros.xdespliegaliq";
				sSql_rubrosempleados+="  FROM  ";
				sSql_rubrosempleados+="  "+connSource.translateTable("gp_rubrosempleado")+" , ";
				sSql_rubrosempleados+="  "+connSource.translateTable("gp_rubros");
				sSql_rubrosempleados+="  WHERE gp_rubrosempleado.xrubro_id = gp_rubros.xrubro_id  ";
				sSql_rubrosempleados+="  AND gp_rubrosempleado.xempresa_id=gp_rubros.xempresa_id ";
				sSql_rubrosempleados+="  AND gp_rubrosempleado.xorganizacion_id=gp_rubros.xorganizacion_id ";
				sSql_rubrosempleados+="  AND gp_rubrosempleado.xentorno_id=gp_rubros.xentorno_id ";
				sSql_rubrosempleados+="  AND gp_rubrosempleado.xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+="  AND gp_rubrosempleado.xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+="  AND gp_rubrosempleado.xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+="  AND gp_rubrosempleado.xempleado_id="+ DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+="  UNION ";
				
				//PUESTO QUE EXISTE POSIBILIDAD DE QUE NO SE INCLUYA EN EL DEPARTAMENTO LOS RUBROS DESDE VARIABLES COLOCAMOS EL DESCUENTO Y EL RUBRO
				sSql_rubrosempleados+="  SELECT gp_rubrosvariables.xrubro_id,gp_rubrosvariables.xvalortotal ,gp_rubros.xorden , gp_rubros.xtiporubro , gp_rubros.xdespliegarol , gp_rubros.xestado,gp_rubros.xpagaiess,gp_rubros.xpagaimpuesto,gp_rubros.xpagaprovision,gp_rubros.xrubrobasico , gp_rubros.xdespliegaliq";
				sSql_rubrosempleados+="  FROM  ";
				sSql_rubrosempleados+="   "+connSource.translateTable("gp_rubrosvariables")+" , ";
				sSql_rubrosempleados+="   "+connSource.translateTable("gp_rubros");
				sSql_rubrosempleados+="  WHERE gp_rubrosvariables.xrubro_id= gp_rubros.xrubro_id ";
				sSql_rubrosempleados+="  AND gp_rubrosvariables.xempresa_id=gp_rubros.xempresa_id ";
				sSql_rubrosempleados+="  AND gp_rubrosvariables.xorganizacion_id=gp_rubros.xorganizacion_id ";
				sSql_rubrosempleados+="  AND gp_rubrosvariables.xentorno_id=gp_rubros.xentorno_id ";
				sSql_rubrosempleados+="  AND gp_rubrosvariables.xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+="  AND gp_rubrosvariables.xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+="  AND gp_rubrosvariables.xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+="  AND gp_rubrosvariables.xempleado_id="+ DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+="  AND gp_rubrosvariables.xanio_id="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
				sSql_rubrosempleados+="  AND gp_rubrosvariables.xperiodo_id="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
				sSql_rubrosempleados+="  AND gp_rubrosvariables.xtipoorigen="+ DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT);
				
				//PUESTO QUE EXISTE POSIBILIDAD DE QUE NO SE INCLUYA EN EL DEPARTAMENTO LOS RUBROS DE PRESTAMOS COLOCAMOS EL DESCUENTO Y EL RUBRO
				sSql_rubrosempleados+="  UNION ";
				sSql_rubrosempleados+=" SELECT gp_cuotas.xrubro_id,gp_cuotas.xmontocuota ,gp_rubros.xorden , gp_rubros.xtiporubro , gp_rubros.xdespliegarol ,gp_rubros.xestado,gp_rubros.xpagaiess,gp_rubros.xpagaimpuesto,gp_rubros.xpagaprovision,gp_rubros.xrubrobasico , gp_rubros.xdespliegaliq";
				sSql_rubrosempleados+=" FROM  ";
				sSql_rubrosempleados+=" "+connSource.translateTable("gp_cuotas")+ ", ";
				sSql_rubrosempleados+=" "+connSource.translateTable("gp_rubros");
				sSql_rubrosempleados+=" WHERE  gp_rubros.xempresa_id = gp_cuotas.xempresa_id ";
				sSql_rubrosempleados+=" AND gp_rubros.xorganizacion_id=gp_cuotas.xorganizacion_id ";
				sSql_rubrosempleados+=" AND gp_rubros.xentorno_id=gp_cuotas.xentorno_id ";
				sSql_rubrosempleados+=" AND gp_rubros.xrubro_id=gp_cuotas.xrubro_id ";
				sSql_rubrosempleados+=" AND gp_cuotas.xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" AND gp_cuotas.xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" AND gp_cuotas.xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" AND gp_cuotas.xempleado_id="+ DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" AND gp_cuotas.xestadocuota='X' ";
				if(sOrigen=="R"){//EN CASO DE ROL DESCONTAMOS SOLO EL PERIODO ACTUAL
					sSql_rubrosempleados+=" AND gp_cuotas.xanio_id_des="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
					sSql_rubrosempleados+=" AND gp_cuotas.xperiodo_id_des="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
				}
				
				if(sOrigen=="L"){//EN CASO DE LIQUIDACION DESCONTAMOS TODO LO PENDIENTE
					sSql_rubrosempleados+=" AND gp_cuotas.xanio_id_des>="+ DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER);
					sSql_rubrosempleados+=" AND gp_cuotas.xperiodo_id_des>="+ DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER);
				}
				
				//EXISTEN RUBROS QUE SON PARTE DE LOS PARAMETROS DE LA EMPRESA COMO SON IMPUESTO A LA RENTA , FONDOS DE RESERVA ETC., COMO VAN HA SER RUBROS POR DEFECTO DEBEMOS PARAMETRIZARLO DE ALGUNA MANERA
				sSql_rubrosempleados+="  UNION ";
				sSql_rubrosempleados+=" SELECT gp_parametros.xrubroimpuestorent,0.00 AS xvalor ,gp_rubros.xorden , gp_rubros.xtiporubro , gp_rubros.xdespliegarol , gp_rubros.xestado,gp_rubros.xpagaiess,gp_rubros.xpagaimpuesto,gp_rubros.xpagaprovision,gp_rubros.xrubrobasico , gp_rubros.xdespliegaliq";
				sSql_rubrosempleados+=" FROM "+connSource.translateTable("gp_parametros")+", ";
				sSql_rubrosempleados+=" "+connSource.translateTable("gp_rubros");
				sSql_rubrosempleados+=" WHERE gp_parametros.xempresa_id = gp_rubros.xempresa_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xorganizacion_id=gp_rubros.xorganizacion_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xentorno_id=gp_rubros.xentorno_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xrubroimpuestorent=gp_rubros.xrubro_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xempresa_id ="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" AND gp_parametros.xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" AND gp_parametros.xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" UNION ";
				sSql_rubrosempleados+=" SELECT gp_parametros.xrubrofondosreserv,0.00 AS xvalor ,gp_rubros.xorden , gp_rubros.xtiporubro , gp_rubros.xdespliegarol, gp_rubros.xestado,gp_rubros.xpagaiess,gp_rubros.xpagaimpuesto,gp_rubros.xpagaprovision,gp_rubros.xrubrobasico , gp_rubros.xdespliegaliq ";
				sSql_rubrosempleados+=" FROM "+connSource.translateTable("gp_parametros")+", ";
				sSql_rubrosempleados+=" "+connSource.translateTable("gp_rubros");
				sSql_rubrosempleados+=" WHERE gp_parametros.xempresa_id = gp_rubros.xempresa_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xorganizacion_id=gp_rubros.xorganizacion_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xentorno_id=gp_rubros.xentorno_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xrubrofondosreserv=gp_rubros.xrubro_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xempresa_id ="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" AND gp_parametros.xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" AND gp_parametros.xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" UNION ";
				sSql_rubrosempleados+=" SELECT gp_parametros.xrubroretjudicial,0.00 AS xvalor ,gp_rubros.xorden , gp_rubros.xtiporubro , gp_rubros.xdespliegarol, gp_rubros.xestado,gp_rubros.xpagaiess,gp_rubros.xpagaimpuesto,gp_rubros.xpagaprovision,gp_rubros.xrubrobasico , gp_rubros.xdespliegaliq";
				sSql_rubrosempleados+=" FROM "+connSource.translateTable("gp_parametros")+", ";
				sSql_rubrosempleados+=" "+connSource.translateTable("gp_rubros");
				sSql_rubrosempleados+=" WHERE gp_parametros.xempresa_id = gp_rubros.xempresa_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xorganizacion_id=gp_rubros.xorganizacion_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xentorno_id=gp_rubros.xentorno_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xrubroretjudicial=gp_rubros.xrubro_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xempresa_id ="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" AND gp_parametros.xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" AND gp_parametros.xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
				//ACONTINUACION SE TOMAN LOS RUBROS DE PARAMTROS DE LIQUIDACION
				//POR LO PRONTO SON DESAHUCIO, DESPIDO INTEMPESTIVO , DESPIDO EMBARAZO
				sSql_rubrosempleados+=" UNION ";
				sSql_rubrosempleados+=" SELECT gp_parametros.xrubrodesahucio,0.00 AS xvalor ,gp_rubros.xorden , gp_rubros.xtiporubro , gp_rubros.xdespliegarol, gp_rubros.xestado,gp_rubros.xpagaiess,gp_rubros.xpagaimpuesto,gp_rubros.xpagaprovision,gp_rubros.xrubrobasico, gp_rubros.xdespliegaliq ";
				sSql_rubrosempleados+=" FROM "+connSource.translateTable("gp_parametros")+", ";
				sSql_rubrosempleados+=" "+connSource.translateTable("gp_rubros");
				sSql_rubrosempleados+=" WHERE gp_parametros.xempresa_id = gp_rubros.xempresa_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xorganizacion_id=gp_rubros.xorganizacion_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xentorno_id=gp_rubros.xentorno_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xrubrodesahucio=gp_rubros.xrubro_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xempresa_id ="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" AND gp_parametros.xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" AND gp_parametros.xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" UNION ";
				sSql_rubrosempleados+=" SELECT gp_parametros.xrubrodespido,0.00 AS xvalor ,gp_rubros.xorden , gp_rubros.xtiporubro , gp_rubros.xdespliegarol, gp_rubros.xestado,gp_rubros.xpagaiess,gp_rubros.xpagaimpuesto,gp_rubros.xpagaprovision,gp_rubros.xrubrobasico, gp_rubros.xdespliegaliq ";
				sSql_rubrosempleados+=" FROM "+connSource.translateTable("gp_parametros")+", ";
				sSql_rubrosempleados+=" "+connSource.translateTable("gp_rubros");
				sSql_rubrosempleados+=" WHERE gp_parametros.xempresa_id = gp_rubros.xempresa_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xorganizacion_id=gp_rubros.xorganizacion_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xentorno_id=gp_rubros.xentorno_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xrubrodespido=gp_rubros.xrubro_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xempresa_id ="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" AND gp_parametros.xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" AND gp_parametros.xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" UNION ";
				sSql_rubrosempleados+=" SELECT gp_parametros.xrubroembarazo,0.00 AS xvalor ,gp_rubros.xorden , gp_rubros.xtiporubro , gp_rubros.xdespliegarol, gp_rubros.xestado,gp_rubros.xpagaiess,gp_rubros.xpagaimpuesto,gp_rubros.xpagaprovision,gp_rubros.xrubrobasico , gp_rubros.xdespliegaliq ";
				sSql_rubrosempleados+=" FROM "+connSource.translateTable("gp_parametros")+", ";
				sSql_rubrosempleados+=" "+connSource.translateTable("gp_rubros");
				sSql_rubrosempleados+=" WHERE gp_parametros.xempresa_id = gp_rubros.xempresa_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xorganizacion_id=gp_rubros.xorganizacion_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xentorno_id=gp_rubros.xentorno_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xrubroembarazo=gp_rubros.xrubro_id ";
				sSql_rubrosempleados+=" AND gp_parametros.xempresa_id ="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" AND gp_parametros.xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
				sSql_rubrosempleados+=" AND gp_parametros.xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
				
				
				sSql_rubrosempleados+="  )rubroempleado ";
				sSql_rubrosempleados+="  GROUP BY rubroempleado.xorden,   rubroempleado.xrubro_id , rubroempleado.xtiporubro , rubroempleado.xdespliegarol ,rubroempleado.xestado,rubroempleado.xpagaiess,rubroempleado.xpagaimpuesto,rubroempleado.xpagaprovision,rubroempleado.xrubrobasico,rubroempleado.xdespliegaliq";
				sSql_rubrosempleados+="  ORDER BY rubroempleado.xorden  ";
				rs_rubros= null;
				iXrolesemple_id=10;
				rs_rubros= connData.openSQL(sSql_rubrosempleados);

				while (rs_rubros.moveNext()) {
					sXrubro=rs_rubros.getString("xrubro");
					dXvalor=0.00;
					dXvalor=rs_rubros.getDouble("xvalor");
					iXorden=rs_rubros.getInt("xorden");
					sXtiporubro = rs_rubros.getString("xtiporubro");
					bXdespliegarol= bXestadorubro = bXpagaiess = bXpagaimpuesto = bXpagaprovision = bXrubrobasico=false;
					//CAMPOS BASICOS DEL RUBRO PARA HACER FORMULAS
					bXdespliegarol= rs_rubros.getBoolean("xdespliegarol");
					bXestadorubro= rs_rubros.getBoolean("xestado");
					bXpagaiess= rs_rubros.getBoolean("xpagaiess");
					bXpagaimpuesto= rs_rubros.getBoolean("xpagaimpuesto");
					bXpagaprovision= rs_rubros.getBoolean("xpagaprovision");
					bXrubrobasico= rs_rubros.getBoolean("xrubrobasico");
					bXdespliegaliq= rs_rubros.getBoolean("xdespliegaliq");
					
					if(bXdespliegarol==null){bXdespliegarol=false;}
					if(bXestadorubro==null){bXestadorubro=false;}
					if(bXpagaiess==null){bXpagaiess=false;}
					if(bXpagaimpuesto==null){bXpagaimpuesto=false;}
					if(bXpagaprovision==null){bXpagaprovision=false;}
					if(bXrubrobasico==null){bXrubrobasico=false;}
					
					//IDENTIFICAMOS SI SE DEBE GENERAR EL RUBRO bXgenera ESTO ESTA EN LA FICHA DEL EMPLEADO
					sSql_generarubro="";
					sSql_generarubro+=" SELECT xgenera ";
					sSql_generarubro+=" FROM  "+connSource.translateTable("gp_rubrosempleado");
					sSql_generarubro+=" WHERE xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
					sSql_generarubro+=" AND xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
					sSql_generarubro+=" AND xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
					sSql_generarubro+=" AND xempleado_id="+ DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
					sSql_generarubro+=" AND xrubro_id="+ DAUtils.formatValue(sXrubro, DA.DA_DT_TEXT);
					rs_generarubro=connData.openSQL(sSql_generarubro);
					if(rs_generarubro.moveNext()){
						bXgenera=rs_generarubro.getBoolean("xgenera");
						if (bXgenera==null){bXgenera=false;}
					}
					rs_generarubro.close();
					
					//PUESTO QUE EXISTEN RUBROS QUE NO NECESARIAMENTE ESTAN EN LA FICHA DEL EMPLEADO
					//ESTOS SIEMPRE SE DEBERAN GENERAR
					sSql_generarubro="";
					sSql_generarubro+=" SELECT COUNT(xrubro_id) ";
					sSql_generarubro+=" FROM  "+connSource.translateTable("gp_rubrosempleado");
					sSql_generarubro+=" WHERE xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
					sSql_generarubro+=" AND xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
					sSql_generarubro+=" AND xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
					sSql_generarubro+=" AND xempleado_id="+ DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT);
					sSql_generarubro+=" AND xrubro_id="+ DAUtils.formatValue(sXrubro, DA.DA_DT_TEXT);
					rs_generarubro=connData.openSQL(sSql_generarubro);
					iGenerarubro=0;
					if(rs_generarubro.moveNext()){
						iGenerarubro=rs_generarubro.getInt(1);
						if (iGenerarubro==null){iGenerarubro=0;}
					}
					if(bXgenera.equals(false)){
						if(iGenerarubro.equals(0)){
							bXgenera=true;
						}
					}
					rs_generarubro.close();
					
					
					//OBTENEMOS EL VALOR DEL RUBRO POR FORMULA
					dXvalor_rubro=0.00;
					dXvalor_rubro=ObtieneValorRubro(session, sXrubro, sXempresa_id, sXentorno_id, sXorganizacion_id, iAnio, iPeriodo, sXdepartamento_id, sXempleado_id,sOrigen,dXfechaingreso,  sXdepartamento_id,  sXseccion_id,  sXcargo_id,  dXfechainicial);
					
					//OBTENEMOS EL VALOR DE IMPUESTO A LA RENTA , ESTE ES UN CALCULO FIJO sXrubrofondosreserv=sXrubroimpuestorent=""
					dXvalor_rubro_impuesto=0.00;
					if(sXrubroimpuestorent.equals(sXrubro)){
						ImpuestoRenta = new Genera_IRenta();
						dXvalor_rubro_impuesto = ImpuestoRenta.ImpuestoRenta(session, sXempresa_id, sXentorno_id, sXorganizacion_id, iAnio, iPeriodo, sXdepartamento_id, sXempleado_id, sOrigen);
						if(dXvalor_rubro_impuesto==null){dXvalor_rubro_impuesto=0.00;}
						ImpuestoRenta=null;
					} 
					
					//EN CASO DE SER LIQUIDACION SE VERIFICARA QUE TIPO DE LIQUIDACION ES
					//PARA CADA RUBRO EXISTE UN CASO ESPECIAL
					dXrubrodesahucio=dXrubrodespido=dXrubroembarazo=0.00;
					if(sOrigen=="L"){
						iLiquida+=1;
						if(iLiquida==1){
							dRetornoliquidar=Rubroliquidacion(session,sXempresa_id , sXentorno_id, sXorganizacion_id ,  iAnio,  iPeriodo , sXdepartamento_id, sXempleado_id,  sOrigen, dXfechai_liqui ,dXfechas_liqui, sXcargo_id ,sXmotivoliqui,  sXrubrofaltas,  dXfechaingreso,  sXcontrato_id, sXhorastrabajadas);
							if(sXrubro.equals(sXrubrodesahucio)){dXrubrodesahucio=dRetornoliquidar[0];};
							if(sXrubro.equals(sXrubrodespido)){dXrubrodespido=dRetornoliquidar[2];}
							if(sXrubro.equals(sXrubroembarazo)){dXrubroembarazo=dRetornoliquidar[3];}
						}else{
							if(sXrubro.equals(sXrubrodesahucio)){dXrubrodesahucio=dRetornoliquidar[0];};
							if(sXrubro.equals(sXrubrodespido)){dXrubrodespido=dRetornoliquidar[2];}
							if(sXrubro.equals(sXrubroembarazo)){dXrubroembarazo=dRetornoliquidar[3];}
						}
					}
					
					if(dXvalor_rubro==null){dXvalor_rubro=0.00;}
					if(dXvalor==null){dXvalor=0.00;}
					//VALOR TOTAL DEL RUBRO
					dXvalor_total_rubro= dXvalor + dXvalor_rubro + dXvalor_rubro_impuesto + ( dXrubrodesahucio + dXrubrodespido  + dXrubroembarazo);
					
					//VERIFICAMOS SI ES UN RUBRO DE PRESTAMOS PARA CAMBIAR DE ESTADO LAS CUOTAS
					sSql_rubroprestamo="";
					sSql_rubroprestamo+=" select COUNT(xrubro_id)  ";
					sSql_rubroprestamo+=" FROM "+connSource.translateTable("gp_tiposprestamos");
					sSql_rubroprestamo+=" WHERE xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
					sSql_rubroprestamo+=" AND xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
					sSql_rubroprestamo+=" AND xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
					sSql_rubroprestamo+=" AND xrubro_id="+ DAUtils.formatValue(sXrubro, DA.DA_DT_TEXT);
					rs_prestamos=null;
					iRubroprestamos=0;
					rs_prestamos=connData.openSQL(sSql_rubroprestamo);
					if (rs_prestamos.moveNext()) {
						iRubroprestamos=rs_prestamos.getInt(1);
					}
					rs_prestamos.close();
					
					//TOMAMOS LOS DATOS DE LAS CUENTAS CONTABLES EN EL DEPARTAMENTO
					//IMPORTANTE PARA CONTABILIZAR, SI NO ESTA INFORMADA PONEMOS EL CODIGO DEL RUBRO CON LAS INCIALES CTA. @RUBRO
					sXcuentadebe="";
					sXcuentahaber="";
					sSql_cuentas="";
					sSql_cuentas+=" SELECT xcuentadebe, xcuentahaber  ";
					sSql_cuentas+=" FROM "+connSource.translateTable("gp_rubrosdepartam");
					sSql_cuentas+=" WHERE xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
					sSql_cuentas+=" AND xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
					sSql_cuentas+=" AND xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
					sSql_cuentas+=" AND xdepartamento_id="+ DAUtils.formatValue(sXdepartamento_id, DA.DA_DT_TEXT);
					sSql_cuentas+=" AND xrubro_id="+ DAUtils.formatValue(sXrubro, DA.DA_DT_TEXT);
					rs_cuentas=null;
					sXcuentadebe="";
					sXcuentahaber="";
					rs_cuentas = connData.openSQL(sSql_cuentas);
					if (rs_cuentas.moveNext()) {
						sXcuentadebe= rs_cuentas.getString("xcuentadebe");
						sXcuentahaber= rs_cuentas.getString("xcuentahaber");
			    	}
					rs_cuentas.close();
					//TOMAMOS EL NOMBRE DEL DEPARTAMENTO, SECCION , CARGO PARA INSERTAR EN LA TABLA PAGO NOMINA
					sXnombre_dep= "";
					sSql_nombres="";
					sSql_nombres+=" SELECT xnombre  ";
					sSql_nombres+=" FROM  "+connSource.translateTable("gp_departamentos");
					sSql_nombres+=" WHERE xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
					sSql_nombres+=" AND xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
					sSql_nombres+=" AND xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
					sSql_nombres+=" AND xdepartamento_id="+ DAUtils.formatValue(sXdepartamento_id, DA.DA_DT_TEXT);
					rs_nombres=null;
					sXnombre_dep= "";
					rs_nombres = connData.openSQL(sSql_nombres);
					if (rs_nombres.moveNext()) {
						sXnombre_dep= rs_nombres.getString("xnombre");
					}
					rs_nombres.close();
					
					sXnombre_sec="";
					sSql_nombres="";
					sSql_nombres+=" SELECT xnombre  ";
					sSql_nombres+=" FROM "+connSource.translateTable("gp_secciones");
					sSql_nombres+=" WHERE xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
					sSql_nombres+=" AND xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
					sSql_nombres+=" AND xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
					sSql_nombres+=" AND xseccion_id="+ DAUtils.formatValue(sXseccion_id, DA.DA_DT_TEXT);
					rs_nombres=null;
					sXnombre_sec= "";
					rs_nombres = connData.openSQL(sSql_nombres);
					if (rs_nombres.moveNext()) {
						sXnombre_sec= rs_nombres.getString("xnombre");
					}
					rs_nombres.close();
					
					sXnombre_car="";
					sSql_nombres="";
					sSql_nombres+=" SELECT  xdescripcion  ";
					sSql_nombres+=" FROM "+connSource.translateTable("gp_cargos");
					sSql_nombres+=" WHERE xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
					sSql_nombres+=" AND xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
					sSql_nombres+=" AND xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
					sSql_nombres+=" AND xcargo_id="+ DAUtils.formatValue(sXcargo_id, DA.DA_DT_TEXT);
					rs_nombres=null;
					sXnombre_car="";
					rs_nombres = connData.openSQL(sSql_nombres);
					if (rs_nombres.moveNext()) {
						sXnombre_car= rs_nombres.getString("xdescripcion");
					}
					rs_nombres.close();
					if(bXgenera){
						//SI ES LIQUIDACION PONEMOS EN LA FECHA FINAL LA FECHA DE LIQUIDACION
						if(sOrigen.equals("L")){dXfechafinal=dXfechas_liqui;}
						//INSERTAMOS LOS DATOS EN LA PAGO NOMINA
						sSql_insertarol="";
						sSql_insertarol+=" INSERT INTO "+connSource.translateTable("gp_pagonomina")+"( ";
						sSql_insertarol+=" xperiodo_id, ";
						sSql_insertarol+=" xanio_id, ";
						sSql_insertarol+=" xempresa_id, ";
						sSql_insertarol+=" xentorno_id, ";
						sSql_insertarol+=" xorganizacion_id, ";
						sSql_insertarol+=" xrubro_id, ";
						sSql_insertarol+=" xtipoorigen, ";
						sSql_insertarol+=" xtiporubro, ";
						sSql_insertarol+=" xempleado_id, ";
						sSql_insertarol+=" xvalor, ";
						sSql_insertarol+=" xdepartamento_id, ";
						sSql_insertarol+=" xdepartamento_nom, ";
						sSql_insertarol+=" xseccion_id, ";
						sSql_insertarol+=" xseccion_nom, ";
						sSql_insertarol+=" xcargo_id, ";
						sSql_insertarol+=" xcargo_nom, ";
						sSql_insertarol+=" xcentrocosto_id, ";
						sSql_insertarol+=" xfechaingreso, ";
						sSql_insertarol+=" xusuariogenera, ";
						sSql_insertarol+=" xfechagenera, ";
						sSql_insertarol+=" xorden, ";
						sSql_insertarol+=" xcuentadebe, ";
						sSql_insertarol+=" xcuentahaber, ";
						sSql_insertarol+=" xfechafinal  ,";
						sSql_insertarol+=" xdespliegarol  , ";
						sSql_insertarol+=" xsaldoprestamos ,";
						sSql_insertarol+=" xestadorubro ,";
						sSql_insertarol+=" xpagaiess ,";
						sSql_insertarol+=" xpagaimpuesto ,";
						sSql_insertarol+=" xpagaprovision ,";
						sSql_insertarol+=" xrubrobasico, ";
						sSql_insertarol+=" xvalordevengado , ";
						sSql_insertarol+=" xdespliegaliq , ";
						sSql_insertarol+=" xcontadorliq ";
						sSql_insertarol+=" ) ";
						sSql_insertarol+=" VALUES( ";
						sSql_insertarol+=DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER)+", ";
						sSql_insertarol+=DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER)+", ";
						sSql_insertarol+=DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT)+", ";
						sSql_insertarol+=DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT)+", ";
						sSql_insertarol+=DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT)+", ";
						sSql_insertarol+=DAUtils.formatValue(sXrubro, DA.DA_DT_TEXT)+", ";
						sSql_insertarol+=DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT)+", ";
						sSql_insertarol+=DAUtils.formatValue(sXtiporubro, DA.DA_DT_TEXT)+", ";
						sSql_insertarol+=DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT)+", ";
						sSql_insertarol+=DAUtils.formatValue(dXvalor_total_rubro, DA.DA_DT_DOUBLE)+", ";
						sSql_insertarol+=DAUtils.formatValue(sXdepartamento_id, DA.DA_DT_TEXT)+", ";
						sSql_insertarol+=DAUtils.formatValue(sXnombre_dep, DA.DA_DT_TEXT)+", ";
						sSql_insertarol+=DAUtils.formatValue(sXseccion_id, DA.DA_DT_TEXT)+",";
						sSql_insertarol+=DAUtils.formatValue(sXnombre_sec, DA.DA_DT_TEXT)+", ";
						sSql_insertarol+=DAUtils.formatValue(sXcargo_id, DA.DA_DT_TEXT)+", ";
						sSql_insertarol+=DAUtils.formatValue(sXnombre_car, DA.DA_DT_TEXT)+", ";
						sSql_insertarol+=DAUtils.formatValue(sXcentrocosto_id, DA.DA_DT_TEXT)+", ";
						sSql_insertarol+=DAUtils.formatValue(dXfechaingreso, DA.DA_DT_DATE)+", ";
						sSql_insertarol+=DAUtils.formatValue(sUsuariogenera, DA.DA_DT_TEXT)+", ";
						sSql_insertarol+=DAUtils.formatValue(dFechagenera, DA.DA_DT_DATE)+", ";
						sSql_insertarol+=DAUtils.formatValue(iXorden, DA.DA_DT_INTEGER)+",";
						sSql_insertarol+=DAUtils.formatValue(sXcuentadebe, DA.DA_DT_TEXT)+", ";
						sSql_insertarol+=DAUtils.formatValue(sXcuentahaber, DA.DA_DT_TEXT)+", ";
						sSql_insertarol+=DAUtils.formatValue(dXfechafinal, DA.DA_DT_DATE)+",";
						sSql_insertarol+=DAUtils.formatValue(bXdespliegarol, DA.DA_DT_BOOLEAN)+" , ";
						sSql_insertarol+="0.00 , ";
						sSql_insertarol+=DAUtils.formatValue(bXestadorubro, DA.DA_DT_BOOLEAN)+" , ";
						sSql_insertarol+=DAUtils.formatValue(bXpagaiess, DA.DA_DT_BOOLEAN)+" , ";
						sSql_insertarol+=DAUtils.formatValue(bXpagaimpuesto, DA.DA_DT_BOOLEAN)+" , ";
					    sSql_insertarol+=DAUtils.formatValue(bXpagaprovision, DA.DA_DT_BOOLEAN)+" , ";
						sSql_insertarol+=DAUtils.formatValue(bXrubrobasico, DA.DA_DT_BOOLEAN)+" , ";
						sSql_insertarol+="0.00  ,";
						sSql_insertarol+=DAUtils.formatValue(bXdespliegaliq, DA.DA_DT_BOOLEAN)+" , ";
						sSql_insertarol+=DAUtils.formatValue(iContadorliquidacion, DA.DA_DT_INTEGER)+" ";
						sSql_insertarol+=" ) ";
						if(dXvalor_total_rubro>0.00){
							connData.execSQL(sSql_insertarol);
							if(iRubroprestamos>0){
								//ACTUALIZAMOS EL ESTADO DE LA CUOTA DE LOS PRESTAMOS
								sSql_rubroprestamo="";
								sSql_rubroprestamo+="  UPDATE  "+connSource.translateTable("gp_cuotas");
								sSql_rubroprestamo+="  SET xestadocuota='D' ";
								sSql_rubroprestamo+="  WHERE xempresa_id="+DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT)+"";
								sSql_rubroprestamo+="  AND xorganizacion_id="+DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT)+"";
								sSql_rubroprestamo+="  AND xentorno_id="+DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT)+"";
								sSql_rubroprestamo+="  AND xempleado_id="+DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT)+"";
								sSql_rubroprestamo+="  AND xrubro_id="+DAUtils.formatValue(sXrubro, DA.DA_DT_TEXT)+"";
								if(sOrigen=="R"){//ROL DE PAGOS
									sSql_rubroprestamo+="  AND xanio_id_des="+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER)+"";
									sSql_rubroprestamo+="  AND xperiodo_id_des="+DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER)+"";
								}
								
								if(sOrigen=="L"){//LIQUIDACION
									sSql_rubroprestamo+="  AND xanio_id_des>="+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER)+"";
									sSql_rubroprestamo+="  AND xperiodo_id_des>="+DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER)+"";
								}
								
								connData.execSQL(sSql_rubroprestamo);
								dSaldoprestamo=0.00;
								dSaldoprestamo=Saldoprestamos(session, sXempresa_id, sXorganizacion_id, sXentorno_id, sXempleado_id, iAnio, iPeriodo , sOrigen);
								//ACTUALIZAMOS EN LA PAGONOMINA EL VALOR PENDIENTE DEL PRESTAMO
								sSql_rubroprestamo="";
								sSql_rubroprestamo+="  UPDATE  ";
								sSql_rubroprestamo+="  "+connSource.translateTable("gp_pagonomina");
								sSql_rubroprestamo+="  SET xsaldoprestamos="+DAUtils.formatValue(dSaldoprestamo, DA.DA_DT_DOUBLE)+"";
								sSql_rubroprestamo+="  WHERE xperiodo_id="+DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER)+"";
								sSql_rubroprestamo+="  AND xanio_id="+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER)+"";
								sSql_rubroprestamo+="  AND xempresa_id="+DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT)+"";
								sSql_rubroprestamo+="  AND xentorno_id="+DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT)+"";
								sSql_rubroprestamo+="  AND xorganizacion_id="+DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT)+"";
								sSql_rubroprestamo+="  AND xrubro_id="+DAUtils.formatValue(sXrubro, DA.DA_DT_TEXT)+"";
								sSql_rubroprestamo+="  AND xtipoorigen="+DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT)+"";
								sSql_rubroprestamo+="  AND xtiporubro="+DAUtils.formatValue(sXtiporubro, DA.DA_DT_TEXT)+"";
								sSql_rubroprestamo+="  AND xempleado_id="+DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT)+"";
								connData.execSQL(sSql_rubroprestamo);
	
							}
							//EN EL CASO DE LIQUIDACION DEBEMOS DEVENGAR LOS VALORES DE LAS PROVISIONES PENDIENTE
							//LAS CONDICIONES SERAN POR EL CONTADOR DE LIQUDIACIONES, FECHA SALIDA NOT NULL , Y VALOR  DEVENGADO<>0
							if(sOrigen.equals("L")){
								sSql_insertarol="";
								sSql_insertarol+=" UPDATE "+connSource.translateTable("gp_pagonomina");
								sSql_insertarol+=" SET xfechafinal="+DAUtils.formatValue(dXfechafinal, DA.DA_DT_DATE)+",";
								sSql_insertarol+=" xcontadorliq="+DAUtils.formatValue(iContadorliquidacion, DA.DA_DT_INTEGER);
								sSql_insertarol+=" WHERE xempresa_id="+DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT)+"";
								sSql_insertarol+=" AND xorganizacion_id="+DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT)+"";
								sSql_insertarol+=" AND xentorno_id="+DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT)+"";
								sSql_insertarol+=" AND xempleado_id="+DAUtils.formatValue(sXempleado_id, DA.DA_DT_TEXT)+"";
								sSql_insertarol+=" AND xtiporubro='P' ";
								sSql_insertarol+=" AND xfechafinal IS NULL ";
								sSql_insertarol+=" AND xanio_id<="+DAUtils.formatValue(iAnio, DA.DA_DT_INTEGER)+"";
								sSql_insertarol+=" AND xperiodo_id<="+DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER)+"";
								connData.execSQL(sSql_insertarol);
							}
						}
					}
		
				}
			
				rs_rubros.close();
				//GENERAMOS ROL INDIVIDUAL 
				GeneraRolesEmple(session, sEmpresa, sEntorno, sOrganizacion, iAnio, iPeriodo, sXempleado_id, sOrigen, iContadorliquidacion);
			}
			rs.close();
			
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return bRetorno;
	}

}