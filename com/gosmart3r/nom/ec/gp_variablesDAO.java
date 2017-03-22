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

public class gp_variablesDAO {
	public DAConnectionSource connSource; // Acceso a sdic
	public DAConnection connData; // Acceso a datos
	
	
	public gp_variablesDAO(Session session) {
		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<gp_variables> ListaVariables(Session session, String sEmpresa , String sOrganizacion , String sEntorno , String sVariable){
		ArrayList ListaVariables = new ArrayList();
		DAResultSet rs=null;
		String sSql_variables="";
		
		gp_variables gp_variables;
		
		try {
			sSql_variables="";
			sSql_variables+=" SELECT  ";
			sSql_variables+=" xvariable_id,xempresa_id,xorganizacion_id,xentorno_id, ";
			sSql_variables+=" xdescripcion,xtipovariable,xvalor,xsql1, ";
			sSql_variables+=" xsql2,xsql3,xsql4 ";
			sSql_variables+=" FROM "+connSource.translateTable("gp_variables");
			sSql_variables+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
			sSql_variables+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
			sSql_variables+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
			if(sVariable.length()>0){sSql_variables+=" AND xvariable_id="+ DAUtils.formatValue(sVariable, DA.DA_DT_TEXT);}
			rs=connData.openSQL(sSql_variables);
			while(rs.moveNext()){
				gp_variables= new gp_variables();
				gp_variables.setXvariable_id(rs.getString("xvariable_id"));
				gp_variables.setXempresa_id(rs.getString("xempresa_id"));
				gp_variables.setXorganizacion_id(rs.getString("xorganizacion_id"));
				gp_variables.setXentorno_id(rs.getString("xentorno_id"));
				gp_variables.setXdescripcion(rs.getString("xdescripcion"));
				gp_variables.setXtipovariable(rs.getString("xtipovariable"));
				gp_variables.setXvalor(rs.getDouble("xvalor"));
				gp_variables.setXsql1(rs.getString("xsql1"));
				gp_variables.setXsql2(rs.getString("xsql2"));
				gp_variables.setXsql3(rs.getString("xsql3"));
				gp_variables.setXsql4(rs.getString("xsql4"));
				ListaVariables.add(gp_variables);
			}
			rs.close();
		} catch (DAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ListaVariables;
	}
	
	public Double ObtieneValorVariable(Session session, gp_variables gp_variables, gp_emplnominas gp_emplnominas , int iAnio , int iPeriodo, String sOrigen,
			int iSemana , int iQuincena){
		Double dValorRetorno;
		DAResultSet rs=null;
		dValorRetorno=0.00;
		String sSql_consulta="";
		
		 switch (gp_variables.getXtipovariable()) {
		    case "S": //SQL	
		    	sSql_consulta=gp_variables.getXsentenciasql();
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
		    	//@SEMA---> SEMANA
		    	//@QUIN-->QUINCENA
		    	//TODOS LOS NUMEROS QUE SE PONGAN EN UNA FORMULA DEBERAN SER CON DOS DECIMALES PEJEMPLO 500.00 o 645.56
		    	
		    	//REMPLAZAMOS LA CONSULTA POR LAS VRIABLES
				try {
					sSql_consulta=sSql_consulta.replace("@BASE", session.getConnectionData().getDBA()+".");
				} catch (OTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	sSql_consulta=sSql_consulta.replace("@EMPR", DAUtils.formatValue(gp_variables.getXempresa_id(), DA.DA_DT_TEXT));
		    	sSql_consulta=sSql_consulta.replace("@ORGA", DAUtils.formatValue(gp_variables.getXorganizacion_id(), DA.DA_DT_TEXT));
		    	sSql_consulta=sSql_consulta.replace("@ENTO", DAUtils.formatValue(gp_variables.getXentorno_id(), DA.DA_DT_TEXT));
		    	sSql_consulta=sSql_consulta.replace("@ORIG",DAUtils.formatValue(sOrigen, DA.DA_DT_TEXT ));
		    	sSql_consulta=sSql_consulta.replace("@EMPL", DAUtils.formatValue(gp_emplnominas.getXempleado_id(), DA.DA_DT_TEXT));
		    	
		    	sSql_consulta=sSql_consulta.replace("@ANIO", DAUtils.formatValue(iAnio,DA.DA_DT_INTEGER).toString());
		    	sSql_consulta=sSql_consulta.replace("@PERI", DAUtils.formatValue(iPeriodo, DA.DA_DT_INTEGER).toString());
		    	
		    	sSql_consulta=sSql_consulta.replace("@SEMA", DAUtils.formatValue(iSemana, DA.DA_DT_INTEGER).toString());
		    	sSql_consulta=sSql_consulta.replace("@QUIN", DAUtils.formatValue(iQuincena, DA.DA_DT_INTEGER).toString());
		    	
		    	sSql_consulta=sSql_consulta.replace("@FEIN", DAUtils.formatValue(gp_emplnominas.getXfechaingreso(), DA.DA_DT_DATE).toString());
		    	
				sSql_consulta=sSql_consulta.replace("@DPTO", DAUtils.formatValue(gp_emplnominas.getXdepartamento_id(), DA.DA_DT_TEXT));
				sSql_consulta=sSql_consulta.replace("@SECC", DAUtils.formatValue(gp_emplnominas.getXseccion_id(), DA.DA_DT_TEXT));
				sSql_consulta=sSql_consulta.replace("@CARG", DAUtils.formatValue(gp_emplnominas.getXcargo_id(), DA.DA_DT_TEXT));
				sSql_consulta=sSql_consulta.replace("@FECI", DAUtils.formatValue(gp_emplnominas.getXfechainicial(), DA.DA_DT_DATE).toString());
				
				
		    	
		    	rs=null;
				try {
					rs = connData.openSQL(sSql_consulta);
			    	if (rs.moveNext()) {
			    		dValorRetorno=rs.getDouble(1);
			    		
			    	}
				} catch (DAException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		    	if(dValorRetorno==null){dValorRetorno=0.00;}
		    	break;
		    case "V": //VALOR
		    	dValorRetorno=gp_variables.getXvalor();
		    	break;
		    case "P": //PORCENTAJE
		    	dValorRetorno=gp_variables.getXvalor()/100.00;
		    	break;
		    default: //NO HACE NADA
		  }
		 
		 
		
		return dValorRetorno;
	}
	
}