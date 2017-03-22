package com.gosmart3r.nom.ec;


import java.util.ArrayList;
import java.util.Date;

import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.session.Session;

public class Genera_Asistencia_SemQuin {
	//VARIABLES
		@SuppressWarnings("unused")
		private DAConnectionSource connSource; // Acceso a sdic
		@SuppressWarnings("unused")
		private DAConnection connData; // Acceso a datos
		
		gp_parametrosDAO gp_parametrosDAO;
		gp_parametros gp_parametros;
		
		gp_contratosDAO gp_contratosDAO;
		gp_contratos gp_contratos;
		
		gp_emplnominasDAO gp_emplnominasDAO;

		gp_semaperiodos gp_semaperiodos;
		gp_semaperiodosDAO gp_semaperiodosDAO;
		
		gp_asistenquse gp_asistenquse;
		gp_asistenquseDAO gp_asistenquseDAO;
		
		gp_dasistenciaempl gp_dasistenciaempl;
		gp_dasistenciaemplDAO gp_dasistenciaemplDAO;
		
		gp_quinperiodos gp_quinperiodos;
		gp_quinperiodosDAO gp_quinperiodosDAO;

		
		
	public Genera_Asistencia_SemQuin() {
			super();
			// TODO Auto-generated constructor stub
		}



	// GENERA ASISTENCIAS SEMANALES O QUINCENALES
	@SuppressWarnings("unused")
	public Boolean GeneraAsistenciaSemanalQuincenal(Session session, String sEmpresa, String sOrganizacion, String sEntorno, int iAnio,	int iPeriodo, String sTipoSemQuin, int iSemQuin, String sDepartamento, Date dDesde, Date dHasta, String sXcontrato_id, Date dFechaFinalPeriodo) {
		boolean bRetorno = true;
		Date dFechagenera=null;
		String sUsuariogenera="";
		String sXhorastrabajadas="", sXhorasfondoreserva="";
		int iXhorasdiaria=0, iXhorassemana=0, iXhorasmensual=0;
		int iNumeroSemanaPeriodos=0;
		Double dHorasTrabajadas=0.00;
		Date dFechaQuinSema=null;

		DAResultSet rs = null;

		this.connSource = session.getConnectionSource();
		try {
			this.connData = session.getConnectionData();
		} catch (OTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		sUsuariogenera = session.getUserInfo().getUserName();

		try {
			dFechagenera = session.getServerNow();
		} catch (OTException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// LEEMOS LA TABLA DE PARAMETROS PARA INDENTIFICAR CUAL ES EL CAMPO DE
		// HORAS TRABAJADAS
		
		sXhorastrabajadas = "";
		sXhorasfondoreserva = "";
		
		gp_parametros= new gp_parametros();
		gp_parametrosDAO = new gp_parametrosDAO(session);
		gp_parametros=gp_parametrosDAO.RecuperaParametros(session, sEmpresa, sOrganizacion, sEntorno);
		sXhorastrabajadas = gp_parametros.getXhorastrabajadas();
		sXhorasfondoreserva = gp_parametros.getXhorasfondoreserva();
		
		
		//RECORREMOS LOS EMPLEADOS
		ArrayList<gp_emplnominas> ListadoEmpleados;
		ListadoEmpleados = new ArrayList<gp_emplnominas>();
		
		gp_emplnominasDAO = new gp_emplnominasDAO(session);
		ListadoEmpleados=gp_emplnominasDAO.ListadoEmpleados(session, sEmpresa, sOrganizacion, sEntorno, "", sDepartamento, "R", "", null, null,dFechaFinalPeriodo);
		int iNumeroEmpleados=ListadoEmpleados.size();
		
		ArrayList<gp_semaperiodos> ListaSemanaPeriodos;
		ListaSemanaPeriodos = new ArrayList<gp_semaperiodos>();
		
		
		ArrayList<gp_quinperiodos> ListaQuincenaPeriodos;
		ListaQuincenaPeriodos = new ArrayList<gp_quinperiodos>();
		
		
		
		
		for(int i=0 ; i<iNumeroEmpleados; i++){
			// CONTROLARA POR LAS HORAS DEFINIDAS EN EL CONTRATO
			iXhorasdiaria = iXhorassemana = iXhorasmensual = 0;
			
			gp_contratos= new gp_contratos();
			gp_contratosDAO= new gp_contratosDAO(session);
			gp_contratos=gp_contratosDAO.RecuperaContratos(session,  ListadoEmpleados.get(i).getXempresa_id(),  ListadoEmpleados.get(i).getXorganizacion_id(),  ListadoEmpleados.get(i).getXentorno_id(),  ListadoEmpleados.get(i).getXcontrato_id());
			iXhorasdiaria =gp_contratos.getXhorasdiaria();
			iXhorassemana = gp_contratos.getXhorassemana();
			iXhorasmensual = gp_contratos.getXhorasmensual();
			
			
			
			//POR CADA EMPLEADO VEMOS EL RANGO DE LA SEMANA O QUINCENA E INSERTAMOS LAS ASISTENCIAS
			if("S".equals(sTipoSemQuin)){ //SEMANAS
				gp_semaperiodosDAO=new gp_semaperiodosDAO(session);
				ListaSemanaPeriodos= gp_semaperiodosDAO.ListaSemanaPeriodos(session, sEmpresa, sOrganizacion, sEntorno, iAnio, iPeriodo, iSemQuin, null);
				iNumeroSemanaPeriodos=ListaSemanaPeriodos.size();
			}
			
			if("Q".equals(sTipoSemQuin)){ //QUINCENAS
				gp_quinperiodosDAO=new gp_quinperiodosDAO(session);
				ListaQuincenaPeriodos=gp_quinperiodosDAO.ListaQuincenasPeriodos(session, sEmpresa, sOrganizacion, sEntorno, iAnio, iPeriodo, iSemQuin, null);
				iNumeroSemanaPeriodos=ListaQuincenaPeriodos.size();
			}
			
			
			for(int j=0;j<iNumeroSemanaPeriodos;j++){
				//CALCULAMOS EL NUMERO DE HORAS TRABAJADAS EN LA SEMANA O QUINCESA
				dFechaQuinSema=null;
				if("S".equals(sTipoSemQuin)){dFechaQuinSema=ListaSemanaPeriodos.get(j).getXfecha();}
				if("Q".equals(sTipoSemQuin)){dFechaQuinSema=ListaQuincenaPeriodos.get(j).getXfecha();}
				
				if(ListadoEmpleados.get(i).getXfechaingreso().before(dFechaQuinSema)){
					dHorasTrabajadas=0.00;
					if("S".equals(sTipoSemQuin)){dHorasTrabajadas=(double) (ListaSemanaPeriodos.get(j).getiLaborable() * iXhorasdiaria);}
					if("Q".equals(sTipoSemQuin)){dHorasTrabajadas=(double) (ListaQuincenaPeriodos.get(j).getiLaborable() * iXhorasdiaria);}
					
					
					gp_asistenquse = new gp_asistenquse();
					gp_asistenquseDAO = new gp_asistenquseDAO(session);
					
					gp_asistenquse.setXentorno_id(ListadoEmpleados.get(i).getXentorno_id());
					gp_asistenquse.setXorganizacion_id(ListadoEmpleados.get(i).getXorganizacion_id());
					gp_asistenquse.setXempresa_id(ListadoEmpleados.get(i).getXempresa_id());
					
					if("S".equals(sTipoSemQuin)){gp_asistenquse.setXanio_id(ListaSemanaPeriodos.get(j).getXanio_id());}
					if("S".equals(sTipoSemQuin)){gp_asistenquse.setXperiodo_id(ListaSemanaPeriodos.get(j).getXperiodo_id());}
					
					if("Q".equals(sTipoSemQuin)){gp_asistenquse.setXanio_id(ListaQuincenaPeriodos.get(j).getXanio_id());}
					if("Q".equals(sTipoSemQuin)){gp_asistenquse.setXperiodo_id(ListaQuincenaPeriodos.get(j).getXperiodo_id());}
					
					gp_asistenquse.setXempleado_id(ListadoEmpleados.get(i).getXempleado_id());
					gp_asistenquse.setXtipoasistencia_id(sXhorastrabajadas);
					gp_asistenquse.setXtipoorigen("R");
					gp_asistenquse.setXquinsem(iSemQuin);
					gp_asistenquse.setXtipoquinsem(sTipoSemQuin);
					gp_asistenquse.setXdepartamento_id(ListadoEmpleados.get(i).getXdepartamento_id());
					gp_asistenquse.setXcargo_id(ListadoEmpleados.get(i).getXcargo_id());
					gp_asistenquse.setXseccion_id(ListadoEmpleados.get(i).getXseccion_id());
					gp_asistenquse.setXfechainicial(ListadoEmpleados.get(i).getXfechainicial());
					gp_asistenquse.setXhoras(dHorasTrabajadas);
					gp_asistenquse.setXusuariogenera(sUsuariogenera);
					gp_asistenquse.setXfechagenera(dFechagenera);
					gp_asistenquse.setXfechaingreso(ListadoEmpleados.get(i).getXfechaingreso());
					
					if("S".equals(sTipoSemQuin)){gp_asistenquse.setXfecha(ListaSemanaPeriodos.get(j).getXfecha());}
					if("Q".equals(sTipoSemQuin)){gp_asistenquse.setXfecha(ListaQuincenaPeriodos.get(j).getXfecha());}
					
					gp_asistenquseDAO.InsertaAsistenciaQuSe(session, gp_asistenquse);
					 
					
					//TOMAMOS LOS DATOS DE LA TABLA DE DETALLE DE NOVEDADES
					ArrayList<gp_dasistenciaempl> ListadoDAsistenciaEmpl;
					ListadoDAsistenciaEmpl= new ArrayList<gp_dasistenciaempl>();
					gp_dasistenciaemplDAO=new gp_dasistenciaemplDAO(session);
					
					if("S".equals(sTipoSemQuin)){
						ListadoDAsistenciaEmpl=gp_dasistenciaemplDAO.ListadoDAsistenciaEmpl(session, sEmpresa, sOrganizacion, sEntorno, iAnio, iPeriodo, ListadoEmpleados.get(i).getXempleado_id(), "R", ListaSemanaPeriodos.get(j).getXfecha());
					}
					
					if("Q".equals(sTipoSemQuin)){
						ListadoDAsistenciaEmpl=gp_dasistenciaemplDAO.ListadoDAsistenciaEmpl(session, sEmpresa, sOrganizacion, sEntorno, iAnio, iPeriodo, ListadoEmpleados.get(i).getXempleado_id(), "R", ListaQuincenaPeriodos.get(j).getXfecha());
					}
					
					int iNumeroListadoDAsistenciaEmpl=ListadoDAsistenciaEmpl.size();
					for(int k=0 ;k<iNumeroListadoDAsistenciaEmpl;k++){
						ArrayList<gp_asistenquse> ListadoAsistenciasQuSe;
						ListadoAsistenciasQuSe = new ArrayList<gp_asistenquse>();
						
						if("S".equals(sTipoSemQuin)){
							ListadoAsistenciasQuSe=gp_asistenquseDAO.ListadoAsistenciasQuSe(session, sEmpresa, sOrganizacion, sEntorno, iAnio, iPeriodo,  ListadoEmpleados.get(i).getXempleado_id(), ListadoDAsistenciaEmpl.get(k).getXtipoasistencia_id(), "R", ListaSemanaPeriodos.get(j).getXsemana_id(), sTipoSemQuin, ListaSemanaPeriodos.get(j).getXfecha());
						}
						
						if("Q".equals(sTipoSemQuin)){
							ListadoAsistenciasQuSe=gp_asistenquseDAO.ListadoAsistenciasQuSe(session, sEmpresa, sOrganizacion, sEntorno, iAnio, iPeriodo,  ListadoEmpleados.get(i).getXempleado_id(), ListadoDAsistenciaEmpl.get(k).getXtipoasistencia_id(), "R", ListaQuincenaPeriodos.get(j).getXquincena_id(), sTipoSemQuin, ListaQuincenaPeriodos.get(j).getXfecha());
						}
						
						int iExiste=ListadoAsistenciasQuSe.size();
						
							
						if(iExiste>0){
							//ACTUALIZAMOS
							Double dHorasActual=ListadoAsistenciasQuSe.get(0).getXhoras();
							ListadoAsistenciasQuSe.get(0).setXhoras(dHorasActual + ListadoDAsistenciaEmpl.get(k).getXtotalhoras());
							ListadoAsistenciasQuSe.get(0).setXusuariogenera(sUsuariogenera);
							ListadoAsistenciasQuSe.get(0).setXfechagenera(dFechagenera);
							
							gp_asistenquseDAO.ActualizaAsistenciaQuSe(session, ListadoAsistenciasQuSe.get(0));
							
							
						}else{
							//INSERTAMOS
							gp_asistenquse = new gp_asistenquse();
							gp_asistenquse.setXentorno_id(ListadoEmpleados.get(i).getXentorno_id());
							gp_asistenquse.setXorganizacion_id(ListadoEmpleados.get(i).getXorganizacion_id());
							gp_asistenquse.setXempresa_id(ListadoEmpleados.get(i).getXempresa_id());
							
							if("S".equals(sTipoSemQuin)){gp_asistenquse.setXanio_id(ListaSemanaPeriodos.get(j).getXanio_id());}
							if("S".equals(sTipoSemQuin)){gp_asistenquse.setXperiodo_id(ListaSemanaPeriodos.get(j).getXperiodo_id());}
							
							if("Q".equals(sTipoSemQuin)){gp_asistenquse.setXanio_id(ListaQuincenaPeriodos.get(j).getXanio_id());}
							if("Q".equals(sTipoSemQuin)){gp_asistenquse.setXperiodo_id(ListaQuincenaPeriodos.get(j).getXperiodo_id());}
							
							
							gp_asistenquse.setXempleado_id(ListadoEmpleados.get(i).getXempleado_id());
							gp_asistenquse.setXtipoasistencia_id(ListadoDAsistenciaEmpl.get(k).getXtipoasistencia_id());
							gp_asistenquse.setXtipoorigen("R");
							gp_asistenquse.setXquinsem(iSemQuin);
							gp_asistenquse.setXtipoquinsem(sTipoSemQuin);
							gp_asistenquse.setXdepartamento_id(ListadoEmpleados.get(i).getXdepartamento_id());
							gp_asistenquse.setXcargo_id(ListadoEmpleados.get(i).getXcargo_id());
							gp_asistenquse.setXseccion_id(ListadoEmpleados.get(i).getXseccion_id());
							gp_asistenquse.setXfechainicial(ListadoEmpleados.get(i).getXfechainicial());
							gp_asistenquse.setXhoras(ListadoDAsistenciaEmpl.get(k).getXtotalhoras());
							gp_asistenquse.setXusuariogenera(sUsuariogenera);
							gp_asistenquse.setXfechagenera(dFechagenera);
							gp_asistenquse.setXfechaingreso(ListadoEmpleados.get(i).getXfechaingreso());
							
							if("S".equals(sTipoSemQuin)){gp_asistenquse.setXfecha(ListaSemanaPeriodos.get(j).getXfecha());}
							if("Q".equals(sTipoSemQuin)){gp_asistenquse.setXfecha(ListaQuincenaPeriodos.get(j).getXfecha());}
							
							gp_asistenquseDAO.InsertaAsistenciaQuSe(session, gp_asistenquse);
						}
						
						ListadoAsistenciasQuSe.clear();
					}
					ListadoDAsistenciaEmpl.clear();
					
				}
				
				
				 
			}
			ListaSemanaPeriodos.clear();
			ListaQuincenaPeriodos.clear();
			
			
		}
		ListadoEmpleados.clear();

		System.out.println ("-1-----iNumeroEmpleados-------"+iNumeroEmpleados);
		
		return bRetorno;
	}
}