package com.gosmart3r.nom.ec;


import java.util.ArrayList;
import java.util.Date;

import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.session.Session;

public class Genera_SemQuin {
	//VARIABLES
	public DAConnectionSource connSource; // Acceso a sdic
	public DAConnection connData; // Acceso a datos
	
	Funciones_Generales Funciones_Generales;
	
	gp_emplnominas gp_emplnominas;
	gp_emplnominasDAO gp_emplnominasDAO;
	
	gp_rubrosempleado gp_rubrosempleado;
	gp_rubrosempleadoDAO gp_rubrosempleadoDAO;
	
	gp_rubros gp_rubros;
	gp_rubrosDAO gp_rubrosDAO;
	
	gp_pagonominaqs gp_pagonominaqs;
	gp_pagonominaqsDAO gp_pagonominaqsDAO;
	
	gp_rubrosdepartam gp_rubrosdepartam;
	gp_rubrosdepartamDAO gp_rubrosdepartamDAO;
	
	public Genera_SemQuin(Session session) {
		super();

		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean Genera_SemanaQuincena(Session session, String sEmpresa, String sOrganizacion , String sEntorno, String sEmpleado , String sDepartamento,
			String sOrigen, String sCargo, Date dFechaingreso , Date dFechaSalida, String sOpciondePago, int iAnio , int iPeriodo , int iSemana , int iQuincena, Date dFechafinperiodo){
		boolean bRetorno=false;
		String sUsuariogenera="";
		Date dFechagenera=null;
		Double dValorRubroFichaEmpl=0.00;
		//obtenemos todos los empleados
		ArrayList<gp_emplnominas> ListadoEmpleados ;
		ArrayList<gp_rubrosempleado> ListadoRubros;
		ArrayList<gp_rubros> ListadoRubrosEmpresa;
		ArrayList<gp_pagonominaqs> ListadoPagoNominaQS;
		ArrayList<gp_rubrosdepartam> ListadoRubrosDepartam;
		
		ListadoEmpleados=new ArrayList<gp_emplnominas>();
		gp_emplnominasDAO=new gp_emplnominasDAO(session);
		
		
		sUsuariogenera=session.getUserInfo().getUserName();	
		
		 
		try {
			dFechagenera= session.getServerNow();
		} catch (OTException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	
		
		ListadoEmpleados=gp_emplnominasDAO.ListadoEmpleados(session, sEmpresa, sOrganizacion, sEntorno, sEmpleado, sDepartamento, sOrigen, sCargo, dFechaingreso, dFechaSalida, dFechafinperiodo);
		int iNumeroListadoEmpleados=ListadoEmpleados.size();
		
		for(int i=0; i<iNumeroListadoEmpleados; i++){
			
			//obtenemos los rubros que tiene un empleado
			ListadoRubros=new ArrayList<gp_rubrosempleado>();
			gp_rubrosempleadoDAO=new gp_rubrosempleadoDAO(session);
			ListadoRubros=gp_rubrosempleadoDAO.ListadoRubrosEmpleados(session, sEmpresa, sOrganizacion, sEntorno, ListadoEmpleados.get(i).getXempleado_id());
			int iNumeroListadoRubros=ListadoRubros.size();
			
			System.out.println ("empleado----"+ListadoEmpleados.get(i).getXnombrecompleto());
			
			for(int j=0; j<iNumeroListadoRubros;j++){
				gp_rubros = new gp_rubros();
				gp_rubrosDAO=new gp_rubrosDAO(session);
				ListadoRubrosEmpresa = new ArrayList<gp_rubros>();
				ListadoRubrosEmpresa=gp_rubrosDAO.ListadoRubros(session, sEmpresa, sOrganizacion, sEntorno, ListadoRubros.get(j).getXrubro());
				
				//identificamos si es semana o quincesa 
				int iQuinSem=0;
				String sQuinSem="";
				dValorRubroFichaEmpl=0.00;
				if(iSemana>0){iQuinSem=iSemana;sQuinSem="S";dValorRubroFichaEmpl=ListadoRubros.get(j).getXvalorsemanalficha();}
				if(iQuincena>0){iQuinSem=iQuincena;sQuinSem="Q";dValorRubroFichaEmpl=ListadoRubros.get(j).getXvalorquincenalficha();}
				
				
				
				//gp_rubrosDAO.ObtieneValorRubro(session, sOpciondePago, gp_rubros, gp_emplnominas, iAnio, iPeriodo, sOrigen);
				Double dValorRetorno=0.00;
				dValorRetorno=gp_rubrosDAO.ObtieneValorRubro(session, sOpciondePago, ListadoRubrosEmpresa.get(0), ListadoEmpleados.get(i), iAnio, iPeriodo, sOrigen, iSemana, iQuincena);
				
				dValorRetorno=dValorRetorno + dValorRubroFichaEmpl;
				
				System.out.println ("--RUBRO--"+ListadoRubros.get(j).getXrubro()+"----VALOR-----"+dValorRetorno);
				
				//tomamos datos adicionales de los rubros por departamento  por ejemplo las cuentas
				gp_rubrosdepartam=new gp_rubrosdepartam();
				gp_rubrosdepartamDAO=new gp_rubrosdepartamDAO(session);
				ListadoRubrosDepartam=gp_rubrosdepartamDAO.ListadoRubrosDeparta(session, 
						ListadoEmpleados.get(i).getXempresa_id(), 
						ListadoEmpleados.get(i).getXorganizacion_id(), 
						ListadoEmpleados.get(i).getXentorno_id(), 
						ListadoRubros.get(j).getXrubro(),
						ListadoEmpleados.get(i).getXdepartamento_id());
				
				//INSERTAMOS EL PAGO RUBRO NOMINA QUINSENA O SEMANA
				
				gp_pagonominaqsDAO = new gp_pagonominaqsDAO(session);
				
				
				
				ListadoPagoNominaQS= gp_pagonominaqsDAO.ListadoPagoNominaQS(session, 
						ListadoEmpleados.get(i).getXempresa_id(), 
						ListadoEmpleados.get(i).getXorganizacion_id(), 
						ListadoEmpleados.get(i).getXentorno_id(), 
						iAnio, 
						iPeriodo, 
						iQuinSem, 
						sQuinSem, 
						ListadoRubros.get(j).getXrubro(), 
						sOrigen, 
						ListadoRubros.get(j).getXtiporubro(), 
						ListadoEmpleados.get(i).getXempleado_id());
				int iNumeroListadoPagoNominaQS=ListadoPagoNominaQS.size();
				
				if(iNumeroListadoPagoNominaQS>0){
					bRetorno=gp_pagonominaqsDAO.EliminaPagoNominaQS(session, 
							ListadoEmpleados.get(i).getXempresa_id(), 
							ListadoEmpleados.get(i).getXorganizacion_id(), 
							ListadoEmpleados.get(i).getXentorno_id(), 
							iAnio, 
							iPeriodo, 
							iQuinSem, 
							sQuinSem, 
							ListadoRubros.get(j).getXrubro(), 
							sOrigen, 
							ListadoRubros.get(j).getXtiporubro(), 
							ListadoEmpleados.get(i).getXempleado_id());
				}

				if(dValorRetorno>0){
					
					gp_pagonominaqs = new gp_pagonominaqs();
					gp_pagonominaqs.setXperiodo_id(iPeriodo);
					gp_pagonominaqs.setXanio_id(iAnio);
					gp_pagonominaqs.setXempresa_id(ListadoEmpleados.get(i).getXempresa_id());
					gp_pagonominaqs.setXentorno_id(ListadoEmpleados.get(i).getXentorno_id());
					gp_pagonominaqs.setXorganizacion_id(ListadoEmpleados.get(i).getXorganizacion_id());
					gp_pagonominaqs.setXrubro_id(ListadoRubros.get(j).getXrubro());
					gp_pagonominaqs.setXtipoorigen(sOrigen);
					gp_pagonominaqs.setXtiporubro(ListadoRubros.get(j).getXtiporubro());
					gp_pagonominaqs.setXempleado_id(ListadoEmpleados.get(i).getXempleado_id());
					gp_pagonominaqs.setXvalor(dValorRetorno);
					gp_pagonominaqs.setXdepartamento_id(ListadoEmpleados.get(i).getXdepartamento_id());
					gp_pagonominaqs.setXdepartamento_nom(ListadoEmpleados.get(i).getXnombre());
					gp_pagonominaqs.setXseccion_id(ListadoEmpleados.get(i).getXseccion_id());
					gp_pagonominaqs.setXseccion_nom(ListadoEmpleados.get(i).getXnombre_sec());
					gp_pagonominaqs.setXcargo_id(ListadoEmpleados.get(i).getXcargo_id());
					gp_pagonominaqs.setXcargo_nom(ListadoEmpleados.get(i).getXdescripcion());
					gp_pagonominaqs.setXcentrocosto_id(ListadoEmpleados.get(i).getXcentrocosto_id());
					gp_pagonominaqs.setXfechaingreso(ListadoEmpleados.get(i).getXfechaingreso());
					gp_pagonominaqs.setXusuariogenera(sUsuariogenera);
					gp_pagonominaqs.setXfechagenera(dFechagenera);
					gp_pagonominaqs.setXorden(ListadoRubros.get(j).getXorden());
					if(ListadoRubrosDepartam.size()>0){
						gp_pagonominaqs.setXcuentadebe(ListadoRubrosDepartam.get(0).getXcuentadebe());
						gp_pagonominaqs.setXcuentahaber(ListadoRubrosDepartam.get(0).getXcuentahaber());
					}
					gp_pagonominaqs.setXfechafinal(null);
					gp_pagonominaqs.setXdespliegarol(ListadoRubros.get(j).isXdespliegarol());
					gp_pagonominaqs.setXsaldoprestamos(0.00);
					gp_pagonominaqs.setXestadorubro(ListadoRubros.get(j).isXestado());
					gp_pagonominaqs.setXpagaiess(ListadoRubros.get(j).isXpagaiess());
					gp_pagonominaqs.setXpagaimpuesto(ListadoRubros.get(j).isXpagaimpuesto());
					gp_pagonominaqs.setXpagaprovision(ListadoRubros.get(j).isXpagaprovision());
					gp_pagonominaqs.setXrubrobasico(ListadoRubros.get(j).isXrubrobasico());
					gp_pagonominaqs.setXvalordevengado(0.00);
					gp_pagonominaqs.setXdespliegaliq(ListadoRubros.get(j).isXdespliegaliq());
					gp_pagonominaqs.setXcontadorliq(0);
					gp_pagonominaqs.setXquinsem(iQuinSem);
					gp_pagonominaqs.setXtipoquinsem(sQuinSem);
					
					bRetorno=gp_pagonominaqsDAO.InsertaPagoNominaQS(session, gp_pagonominaqs);
				}
				 
				
			//	System.out.println ("empleado----"+ListadoRubros.get(j).getXformula());
				
				//System.out.println ("empleado----"+ListadoRubros.get(j).getXtiformularubro());
				
				//System.out.println ("dValorRetorno----"+dValorRetorno);
			}
			
		
		}
		ListadoEmpleados.clear();
		return bRetorno;
	}
	
}