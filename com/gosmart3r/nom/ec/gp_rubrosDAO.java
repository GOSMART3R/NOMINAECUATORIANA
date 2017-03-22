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

public class gp_rubrosDAO {
	// VARIABLES
		private DAConnectionSource connSource; // Acceso a sdic
		private DAConnection connData; // Acceso a datos
		
		Funciones_Generales Funciones_Generales;
		
		public gp_rubrosDAO(Session session) {
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public ArrayList<gp_rubros> ListadoRubros(Session session, String sEmpresa , String sOrganizacion , String sEntorno, String sRubro){
			DAResultSet rs=null;
			String sSql_rubros="";
			
			ArrayList ListadoRubros = new ArrayList();
			gp_rubros gp_rubros;
			
			this.connSource = session.getConnectionSource();
			try {
				this.connData = session.getConnectionData();
			} catch (OTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				sSql_rubros="";
				sSql_rubros+=" SELECT  ";
				sSql_rubros+=" xrubro_id,xentorno_id,xorganizacion_id,xempresa_id, ";
				sSql_rubros+=" xtiporubro,xdescripcion,xtiformularubro,xvalor, ";
				sSql_rubros+=" xformula,xestado,xpagaiess,xpagaimpuesto, ";
				sSql_rubros+=" xpagaprovision,xdespliegarol,xotrosvalores,xrubrobasico, ";
				sSql_rubros+=" xorden,xdescripcioncorta,xdespliegaliq,xformulaqui, ";
				sSql_rubros+=" xformulasem ";
				sSql_rubros+=" FROM  "+connSource.translateTable("gp_rubros");
				sSql_rubros+=" WHERE xempresa_id="+ DAUtils.formatValue(sEmpresa, DA.DA_DT_TEXT);
				sSql_rubros+=" AND xorganizacion_id="+ DAUtils.formatValue(sOrganizacion, DA.DA_DT_TEXT);
				sSql_rubros+=" AND xentorno_id="+ DAUtils.formatValue(sEntorno, DA.DA_DT_TEXT);
				if(sRubro.length()>0){sSql_rubros+=" AND xrubro_id="+ DAUtils.formatValue(sRubro, DA.DA_DT_TEXT);}
				rs=connData.openSQL(sSql_rubros);
				while(rs.moveNext()){
						gp_rubros = new gp_rubros();
						gp_rubros.setXrubro_id(rs.getString("xrubro_id"));
						gp_rubros.setXentorno_id(rs.getString("xentorno_id"));
						gp_rubros.setXorganizacion_id(rs.getString("xorganizacion_id"));
						gp_rubros.setXempresa_id(rs.getString("xempresa_id"));
						gp_rubros.setXtiporubro(rs.getString("xtiporubro"));
						gp_rubros.setXdescripcion(rs.getString("xdescripcion"));
						gp_rubros.setXtiformularubro(rs.getString("xtiformularubro"));
						gp_rubros.setXvalor(rs.getDouble("xvalor"));
						gp_rubros.setXformula(rs.getString("xformula"));
						gp_rubros.setXestado(rs.getBoolean("xestado"));
						gp_rubros.setXpagaiess(rs.getBoolean("xpagaiess"));
						gp_rubros.setXpagaimpuesto(rs.getBoolean("xpagaimpuesto"));
						gp_rubros.setXpagaprovision(rs.getBoolean("xpagaprovision"));
						gp_rubros.setXdespliegarol(rs.getBoolean("xdespliegarol"));
						gp_rubros.setXotrosvalores(rs.getBoolean("xotrosvalores"));
						gp_rubros.setXrubrobasico(rs.getBoolean("xrubrobasico"));
						gp_rubros.setXorden(rs.getInt("xorden"));
						gp_rubros.setXdescripcioncorta(rs.getString("xdescripcioncorta"));
						gp_rubros.setXdespliegaliq(rs.getBoolean("xdespliegaliq"));
						gp_rubros.setXformulaqui(rs.getString("xformulaqui"));
						gp_rubros.setXformulasem(rs.getString("xformulasem"));
						ListadoRubros.add(gp_rubros);
				}
				rs.close();
			} catch (DAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ListadoRubros;
		}
		
		//FUNCION PARA EVALUAR RUBROS ,LOS RUBROS SIEMPRE DEBERAN EMPEZAR COMO
				//@R-----RUBRO , @V------VARIABLES , @C-------CONSTANTE
				
				@SuppressWarnings("unused")
				public Double ObtieneValorRubro(Session session, String sOpciondePago, gp_rubros gp_rubros , gp_emplnominas gp_emplnominas , int iAnio , int iPeriodo , String sOrigen,
						int iSemana, int iQuincena){
					Double dValorRetorno=0.00;
					String sFormulaRubro="", sTipoFormula="", sOpcion="" , sEvalua="" , sSql_Evalua="";
					Integer iInicio=0 ;
					Boolean bRecorre=true;
					Double dValorRubro=0.00, dValoraRemplazar=0.00;
					DAResultSet rs=null;
					
					//ArrayList<gp_rubros> ListadoRubros= new ArrayList<gp_rubros>();
					
					//gp_rubros gp_rubros;
					//gp_rubrosDAO gp_rubrosDAO;
					
					//gp_rubros=new gp_rubros();
					//gp_rubrosDAO = new gp_rubrosDAO(session);
					
					gp_variables gp_variables;
					gp_variablesDAO gp_variablesDAO;
					
					gp_variables= new gp_variables();
					gp_variablesDAO = new gp_variablesDAO(session);
					
					gp_constantesxanio gp_constantesxanio;
					gp_constantesxanioDAO gp_constantesxanioDAO;
					
					gp_constantesxanio= new gp_constantesxanio();
					gp_constantesxanioDAO= new gp_constantesxanioDAO(session);
					
					ArrayList<gp_variables> ListadoVariables= new ArrayList<gp_variables>(); 

				//	ListadoRubros=gp_rubrosDAO.ListadoRubros(session, gp_rubros.getXempresa_id(), gp_rubros.getXorganizacion_id(), gp_rubros.getXentorno_id(), gp_rubros.getXrubro_id());

					if(sOpciondePago.equals("M")){sFormulaRubro=gp_rubros.getXformula();}//MENSUAL
					if(sOpciondePago.equals("S")){sFormulaRubro=gp_rubros.getXformulasem();}//SEMANAL
					if(sOpciondePago.equals("Q")){sFormulaRubro=gp_rubros.getXformulaqui();}//QUINCENAL
					
					//System.out.println ("-1-----funciooooooooooooooo-------"+sFormulaRubro);
					
					sTipoFormula=gp_rubros.getXtiformularubro();
					
					if(sFormulaRubro.length()==0 || sFormulaRubro==null){sTipoFormula="*";bRecorre=false;sFormulaRubro="0.00";}
					if(sFormulaRubro==null){sTipoFormula="*";bRecorre=false;sFormulaRubro="0.00";}
					
					if(sTipoFormula.equals("F")){ // SI ES DE TIPO FORMULA REVISA FORMULA
						  while (bRecorre) {
							  //BUSCAMOS EL @ 
							  iInicio=sFormulaRubro.indexOf("@");
							  if(iInicio>=0){
								  sOpcion="";
								  sOpcion=sFormulaRubro.substring(iInicio, iInicio + 2);
								  
								 // System.out.println ("-1--------------"+sFormulaRubro);
								 
								  
								  switch (sOpcion) {
								    case "@V": //VARIABLES	
								    	sEvalua=sFormulaRubro.substring(iInicio, iInicio + 6);
								    	ListadoVariables=gp_variablesDAO.ListaVariables(session, gp_rubros.getXempresa_id(), gp_rubros.getXorganizacion_id(), gp_rubros.getXentorno_id(), sEvalua);
								    	dValoraRemplazar=gp_variablesDAO.ObtieneValorVariable(session, ListadoVariables.get(0), gp_emplnominas, iAnio, iPeriodo, sOrigen, iSemana ,iQuincena);
								    	sFormulaRubro=sFormulaRubro.replace(sEvalua, dValoraRemplazar.toString());
								    	break;
								    case "@R": //RUBRO
								    	sEvalua=sFormulaRubro.substring(iInicio, iInicio + 6);
								    	ArrayList<gp_rubros> ListadoRubros= new ArrayList<gp_rubros>();
								    	gp_rubrosDAO gp_rubrosDAO;
								    	gp_rubrosDAO=new gp_rubrosDAO(session);
								    	ListadoRubros=gp_rubrosDAO.ListadoRubros(session, gp_rubros.getXempresa_id(), gp_rubros.getXorganizacion_id(),gp_rubros.getXentorno_id(), sEvalua);
								    	dValoraRemplazar=ObtieneValorRubro(session, sOpciondePago, ListadoRubros.get(0), gp_emplnominas, iAnio, iPeriodo, sOrigen , iSemana , iQuincena);
								    	sFormulaRubro=sFormulaRubro.replace(sEvalua, dValoraRemplazar.toString());
								    	break;
								    case "@C": //CONSTANTE
								    	sEvalua=sFormulaRubro.substring(iInicio, iInicio + 6);
								    	sFormulaRubro=sFormulaRubro.replace(sEvalua, gp_constantesxanioDAO.ListaConstantes(session, gp_rubros.getXempresa_id(), gp_rubros.getXorganizacion_id(), gp_rubros.getXentorno_id(), sEvalua, iAnio).get(0).getXvalor().toString());
								    	break;
								    default: //NO HACE NADA
								  }
							  
								  
								  
								  bRecorre=true;
							  }else{
								  bRecorre=false;
							  }
						  }
					}
					
					if(sTipoFormula.equals("V")||sTipoFormula.equals("C")){ // SI ES DE TIPO CONSTANTE O VALOR
						dValorRetorno=gp_rubros.getXvalor();
					}

					try {
						//System.out.println ("----- ----------"+sFormulaRubro);
						sSql_Evalua="";
						sSql_Evalua+=" SELECT   ("+sFormulaRubro+")";
						sSql_Evalua+=" FROM "+connSource.translateTable("gp_anios");
						sSql_Evalua+=" WHERE xempresa_id="+ DAUtils.formatValue(gp_rubros.getXempresa_id(), DA.DA_DT_TEXT);
						sSql_Evalua+=" AND xentorno_id="+ DAUtils.formatValue(gp_rubros.getXentorno_id(), DA.DA_DT_TEXT);
						sSql_Evalua+=" AND xorganizacion_id="+ DAUtils.formatValue(gp_rubros.getXorganizacion_id(), DA.DA_DT_TEXT);
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
					
					Funciones_Generales= new Funciones_Generales();
					
					return Funciones_Generales.Redondear(dValorRetorno,2);
				}

}