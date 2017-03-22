package com.gosmart3r.nom.ec;

import java.util.ArrayList;
import java.util.Date;

import com.unit4.karat.base.OTException;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.session.Session;

public class gp_quinsemdepar_fm extends FMDefaultEvents {
	Genera_Asistencia_SemQuin GeneraAsistenciaNomina;
	Genera_SemQuin GeneraSemanasQuincenas;
	
    public enum CONTAINERS {
        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
        _K_FIXED,		//NOSONAR
        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
        * <b>Caption:</b> Semana / Quincena<br> */
        card1,		//NOSONAR
        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
        * <b>Caption:</b> Semana / Quincena<br> */
        collap1,		//NOSONAR
        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
        * <b>Caption:</b> Genera semana / quincena<br> */
        gp_quinsemdepar,		//NOSONAR
        /** <b>Window class:</b> FMContainer.CONTAINER_CARDINDEX<br> */
        K__MAINCARD		//NOSONAR
    }

    public enum VIEWS {
        /** <b>Caption:</b> Generar<br>
        * <b>View Type:</b> FMView.VIEW_BUTTON<br> */
        btn_generar,		//NOSONAR
        /** <b>Caption:</b> Código año<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código año<br> */
        xanio_id,		//NOSONAR
        /** <b>Caption:</b> Código departamento<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código departamento<br> */
        xdepartamento_id,		//NOSONAR
        /** <b>Caption:</b> Desde<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Desde<br> */
        xdesde,		//NOSONAR
        /** <b>Caption:</b> Código empresa<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código empresa<br> */
        xempresa_id,		//NOSONAR
        /** <b>Caption:</b> Código entorno<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código entorno<br> */
        xentorno_id,		//NOSONAR
        /** <b>Caption:</b> Generado<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Generado<br> */
        xfecha,		//NOSONAR
        /** <b>Caption:</b> Hasta<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Hasta<br> */
        xhasta,		//NOSONAR
        /** <b>Caption:</b> Código organización<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código organización<br> */
        xorganizacion_id,		//NOSONAR
        /** <b>Caption:</b> Código periodo<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código periodo<br> */
        xperiodo_id,		//NOSONAR
        /** <b>Caption:</b> Semana / Quincena<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Quincena / Semana<br> */
        xquinsem,		//NOSONAR
        /** <b>Caption:</b> Semana / Quincena<br>
        * <b>View Type:</b> FMView.VIEW_DROPCOMBO<br>
        * <br>Semana / Quincena<br> */
        xtipoquinsem,		//NOSONAR
        /** <b>Caption:</b> Generado por<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Generado por<br> */
        xusuario		//NOSONAR
    }

	public gp_quinsemdepar_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#formPreDeleteIn(com.unit4.karat.form.FMEvent)
	 */
	@SuppressWarnings("unused")
	@Override
	public void formPreDeleteIn(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.formPreDeleteIn(fmEvent);
		fmEvent.setRecall(true);
		
		fmEvent.setRecall(true);
		String sEmpresa="";
		String sOrganizacion="";
		String sEntorno="";
		int iAnio=0;
		int iPeriodo=0;
		String sTipoSemQuin="";
		int iSemQuin=0;
		String sDepartamento="";
		Date dDesde;
		Date dHasta;
		String sXcontrato_id;
		
		 sEmpresa=getItem(VIEWS.xempresa_id).getValue();
		 sOrganizacion=getItem(VIEWS.xorganizacion_id).getValue();
		 sEntorno=getItem(VIEWS.xentorno_id).getValue();
		 iAnio=getItem(VIEWS.xanio_id).getValueInteger();
		 iPeriodo=getItem(VIEWS.xperiodo_id).getValueInteger();
		 sTipoSemQuin=getItem(VIEWS.xtipoquinsem).getValue();
		 iSemQuin=getItem(VIEWS.xquinsem).getValueInteger();
		 sDepartamento=getItem(VIEWS.xdepartamento_id).getValue();
		 dDesde=getItem(VIEWS.xdesde).getValueDate();
		 dHasta=getItem(VIEWS.xhasta).getValueDate();
		 sXcontrato_id="1";
		 
		 gp_asistenquseDAO gp_asistenquseDAO;
		 gp_asistenquseDAO=new gp_asistenquseDAO(session);
		 int iExiste=gp_asistenquseDAO.ListadoAsistenciasQuSe(session, sEmpresa, sOrganizacion, sEntorno, iAnio, iPeriodo, "", "", "R", iSemQuin, sTipoSemQuin, null).size();
		 
		
		if (iExiste>0) {
			fmEvent.setCancel(FMEvent.CANCEL_NO_MESSAGE);
			fmEvent.setMessageTrace("Existen valores generados, no se permite eliminar.");
			fmEvent.setMessage("ShowError No es prosible eliminar este registro, existen valores ya procesados. Utilice el botón eliminar e intente nuevament.");
		}

	}
    

	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#viewClick(com.unit4.karat.form.FMEvent)
	 */
	@SuppressWarnings("unused")
	@Override
	public void viewClick(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.viewClick(fmEvent);
		fmEvent.setRecall(true);
		String sEmpresa="";
		String sOrganizacion="";
		String sEntorno="";
		int iAnio=0;
		int iPeriodo=0;
		String sTipoSemQuin="";
		int iSemQuin=0;
		String sDepartamento="";
		Date dDesde;
		Date dHasta;
		String sXcontrato_id;
		int iSemana=0;
		int iQuincena=0;
		Date dFechaFinalPeriodo=null;

		GeneraAsistenciaNomina = new Genera_Asistencia_SemQuin();
		GeneraSemanasQuincenas= new Genera_SemQuin(session);
		
		if ("btn_generar".equals(fmEvent.getSourceName())) {
			 sEmpresa=getItem(VIEWS.xempresa_id).getValue();
			 sOrganizacion=getItem(VIEWS.xorganizacion_id).getValue();
			 sEntorno=getItem(VIEWS.xentorno_id).getValue();
			 iAnio=getItem(VIEWS.xanio_id).getValueInteger();
			 iPeriodo=getItem(VIEWS.xperiodo_id).getValueInteger();
			 sTipoSemQuin=getItem(VIEWS.xtipoquinsem).getValue();
			 iSemQuin=getItem(VIEWS.xquinsem).getValueInteger();
			 sDepartamento=getItem(VIEWS.xdepartamento_id).getValue();
			 dDesde=getItem(VIEWS.xdesde).getValueDate();
			 dHasta=getItem(VIEWS.xhasta).getValueDate();
			 sXcontrato_id="1";
			 
			 gp_asistenquseDAO gp_asistenquseDAO;
			 gp_asistenquseDAO=new gp_asistenquseDAO(session);
			 int iExiste=gp_asistenquseDAO.ListadoAsistenciasQuSe(session, sEmpresa, sOrganizacion, sEntorno, iAnio, iPeriodo, "", "", "R", iSemQuin, sTipoSemQuin, null).size();
			 
			 if(iExiste>0){
				 fmObject.showMessageText("Existen valores ya generados, si desea volver a generarlos debe primero eliminarlos." , "Aceptar");
			 }else{
				 
				 //vamos a identificar la fecha final de la semana o la quincena para poder filtrar por esa fecha los empleados
				 gp_cquinperiodos gp_cquinperiodos;
				 gp_cquinperiodosDAO gp_cquinperiodosDAO;
				 ArrayList<gp_cquinperiodos>  ListaQuincenas = new ArrayList<gp_cquinperiodos>();
				 
				 gp_csemaperiodos gp_csemaperiodos;
				 gp_csemaperiodosDAO gp_csemaperiodosDAO;
				 ArrayList<gp_csemaperiodos> ListaSemanas= new ArrayList<gp_csemaperiodos>();
				 
				 
				 if(sTipoSemQuin.equals("S")){
					gp_csemaperiodosDAO= new gp_csemaperiodosDAO(session);
					ListaSemanas=gp_csemaperiodosDAO.ListaCSemanaPeriodos(session, sEmpresa, sOrganizacion, sEntorno, iAnio, iPeriodo, iSemQuin, null, null);
					
					dFechaFinalPeriodo=ListaSemanas.get(0).getXhasta();
					 iSemana=iSemQuin;
					 iQuincena=0;
					 
				 }
				 
				 
				 if(sTipoSemQuin.equals("Q"))
				 {
					 gp_cquinperiodosDAO=new gp_cquinperiodosDAO(session);
					 ListaQuincenas=gp_cquinperiodosDAO.ListaQuincenas(session, sEmpresa, sOrganizacion, sEntorno, iAnio, iPeriodo, iSemQuin, null, null);
					
					 dFechaFinalPeriodo=ListaQuincenas.get(0).getXhasta();
					 
					 iSemana=0;
					 iQuincena=iSemQuin;
					 }
				 
				 
				 GeneraAsistenciaNomina.GeneraAsistenciaSemanalQuincenal(session, sEmpresa, sOrganizacion, sEntorno, iAnio, iPeriodo, sTipoSemQuin, iSemQuin, sDepartamento, dDesde, dHasta, sXcontrato_id, dFechaFinalPeriodo);
					
				 
				 GeneraSemanasQuincenas.Genera_SemanaQuincena(session, sEmpresa, sOrganizacion, sEntorno, "", sDepartamento, "R", "", null, null,sTipoSemQuin,iAnio,iPeriodo, iSemana, iQuincena,dFechaFinalPeriodo);
				 
				 fmObject.showMessageText("Rol de pagos generado con exito. Departamento :" + sDepartamento , "Aceptar");
			 }
				
		}
		
		if("btn_eliminar".equals(fmEvent.getSourceName())){
			sEmpresa=getItem(VIEWS.xempresa_id).getValue();
			 sOrganizacion=getItem(VIEWS.xorganizacion_id).getValue();
			 sEntorno=getItem(VIEWS.xentorno_id).getValue();
			 iAnio=getItem(VIEWS.xanio_id).getValueInteger();
			 iPeriodo=getItem(VIEWS.xperiodo_id).getValueInteger();
			 sTipoSemQuin=getItem(VIEWS.xtipoquinsem).getValue();
			 iSemQuin=getItem(VIEWS.xquinsem).getValueInteger();
			 sDepartamento=getItem(VIEWS.xdepartamento_id).getValue();
			 dDesde=getItem(VIEWS.xdesde).getValueDate();
			 dHasta=getItem(VIEWS.xhasta).getValueDate();
			 
			String sMensaje="Departamento -> "+sDepartamento+" Periodo - >"+iPeriodo+" Semana(S) / Quincena (Q) - > "+sTipoSemQuin+" Código - >" +iSemQuin;
			int iOpcion=fmObject.showMessageText("Este proceso eliminará todos los valores generados para: "+sMensaje+". Desea realizarlo. ? ", "Cancelar/Aceptar");
			if(iOpcion==1){
				
			}else{
				
				gp_asistenquseDAO gp_asistenquseDAO;
				gp_asistenquseDAO=new gp_asistenquseDAO(session);
				gp_asistenquseDAO.EliminaAsistenciaQuSe(session, sEmpresa, sOrganizacion, sEntorno, iAnio, iPeriodo, "", "", "R", iSemQuin, sTipoSemQuin, null);
			}
		}
	}
  
    
  

}
