package com.gosmart3r.nom.ec;

 
import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.session.Session;

public class gp_repovaccab_fm extends FMDefaultEvents {
	  public enum CONTAINERS {
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
	        _K_FIXED,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
	        * <b>Caption:</b> Reporte<br> */
	        card1,
	        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br> */
	        collap1,
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
	        * <b>Caption:</b> Reporte de vacaciones y provisiones<br> */
	        gp_repovaccab,
	        /** <b>Window class:</b> FMContainer.CONTAINER_GRID<br> */
	        grd_reportevacacion,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARDINDEX<br> */
	        K__MAINCARD
	    }

	    public enum VIEWS {
	        /** <b>Caption:</b> Generar<br>
	        * <b>View Type:</b> FMView.VIEW_BUTTON<br> */
	        cmd_genera,
	        /** <b>Caption:</b> Código año<br>
	        * <b>View Type:</b> FMView.VIEW_DROPLIST<br> */
	        xanio_id,
	        /** <b>Caption:</b> Cargo<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xcargo_desc_rv,
	        /** <b>Caption:</b> Código cargo<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xcargo_id_rv,
	        /** <b>Caption:</b> Días ganados<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xdiasganados_rv,
	        /** <b>Caption:</b> Código del empleado<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xempleado_id_rv,
	        /** <b>Caption:</b> Código empresa<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xempresa_id,
	        /** <b>Caption:</b> Código entorno<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xentorno_id,
	        /** <b>Caption:</b> Fecha genera<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xfechagenera,
	        /** <b>Caption:</b> Fecha ingreso<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xfechaingreso_rv,
	        /** <b>Caption:</b> Días máximos<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xmaximosdias_rv,
	        /** <b>Caption:</b> Nombres/Apellidos empleado<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xnombreempleado_rv,
	        /** <b>Caption:</b> Código organización<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xorganizacion_id,
	        /** <b>Caption:</b> Código periodo<br>
	        * <b>View Type:</b> FMView.VIEW_DROPLIST<br> */
	        xperiodo_id,
	        /** <b>Caption:</b> Usuario genera<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xusuariogenera,
	        /** <b>Caption:</b> Valor provisión<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br> */
	        xvalorprovision_rv
	    }

	    public enum SEGMENTS {
	        /** <b>Description:</b> Reporte de vacaciones y provisiones<br>
	        * <b>Base query:</b> gp_reportevacacion<br> */
	        gp_reportevacacion,
	        /** <b>Description:</b> Cabecera reporte de vacaciones y provisiones<br>
	        * <b>Base query:</b> gp_repovaccab<br> */
	        gp_repovaccab
	    }

	    public enum ITEMS {
	        /** <b>Description:</b> Código año<br>
	        * <b>Segment name:</b> gp_repovaccab<br>
	        * <b>Query field:</b> xanio_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código año<br> */
	        xanio_id,
	        /** <b>Description:</b> Código año<br>
	        * <b>Segment name:</b> gp_reportevacacion<br>
	        * <b>Query field:</b> xanio_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código año<br> */
	        xanio_id_rv,
	        /** <b>Description:</b> Cargo<br>
	        * <b>Segment name:</b> gp_reportevacacion<br>
	        * <b>Query field:</b> xcargo_desc<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Cargo<br> */
	        xcargo_desc_rv,
	        /** <b>Description:</b> Código cargo<br>
	        * <b>Segment name:</b> gp_reportevacacion<br>
	        * <b>Query field:</b> xcargo_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código cargo<br> */
	        xcargo_id_rv,
	        /** <b>Description:</b> Días ganados<br>
	        * <b>Segment name:</b> gp_reportevacacion<br>
	        * <b>Query field:</b> xdiasganados<br>
	        * <b>Data Type:</b> DA.DA_DT_DOUBLE<br>
	        * <b>Input Label:</b> Días ganados<br> */
	        xdiasganados_rv,
	        /** <b>Description:</b> Código del empleado<br>
	        * <b>Segment name:</b> gp_reportevacacion<br>
	        * <b>Query field:</b> xempleado_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código del empleado<br> */
	        xempleado_id_rv,
	        /** <b>Description:</b> Código empresa<br>
	        * <b>Segment name:</b> gp_repovaccab<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código empresa<br> */
	        xempresa_id,
	        /** <b>Description:</b> Código empresa<br>
	        * <b>Segment name:</b> gp_reportevacacion<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código empresa<br> */
	        xempresa_id_rv,
	        /** <b>Description:</b> Código entorno<br>
	        * <b>Segment name:</b> gp_repovaccab<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código entorno<br> */
	        xentorno_id,
	        /** <b>Description:</b> Código entorno<br>
	        * <b>Segment name:</b> gp_reportevacacion<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código entorno<br> */
	        xentorno_id_rv,
	        /** <b>Description:</b> Fecha genera<br>
	        * <b>Segment name:</b> gp_repovaccab<br>
	        * <b>Query field:</b> xfechagenera<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha genera<br> */
	        xfechagenera,
	        /** <b>Description:</b> Fecha ingreso<br>
	        * <b>Segment name:</b> gp_reportevacacion<br>
	        * <b>Query field:</b> xfechaingreso<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha ingreso<br> */
	        xfechaingreso_rv,
	        /** <b>Description:</b> Días máximos<br>
	        * <b>Segment name:</b> gp_reportevacacion<br>
	        * <b>Query field:</b> xmaximosdias<br>
	        * <b>Data Type:</b> DA.DA_DT_DOUBLE<br>
	        * <b>Input Label:</b> Días máximos<br> */
	        xmaximosdias_rv,
	        /** <b>Description:</b> Nombres/Apellidos empleado<br>
	        * <b>Segment name:</b> gp_reportevacacion<br>
	        * <b>Query field:</b> xnombreempleado<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Nombres/Apellidos empleado<br> */
	        xnombreempleado_rv,
	        /** <b>Description:</b> Código organización<br>
	        * <b>Segment name:</b> gp_repovaccab<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código organización<br> */
	        xorganizacion_id,
	        /** <b>Description:</b> Código organización<br>
	        * <b>Segment name:</b> gp_reportevacacion<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código organización<br> */
	        xorganizacion_id_rv,
	        /** <b>Description:</b> Código periodo<br>
	        * <b>Segment name:</b> gp_repovaccab<br>
	        * <b>Query field:</b> xperiodo_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código periodo<br> */
	        xperiodo_id,
	        /** <b>Description:</b> Código periodo<br>
	        * <b>Segment name:</b> gp_reportevacacion<br>
	        * <b>Query field:</b> xperiodo_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Código periodo<br> */
	        xperiodo_id_rv,
	        /** <b>Description:</b> Usuario genera<br>
	        * <b>Segment name:</b> gp_repovaccab<br>
	        * <b>Query field:</b> xusuariogenera<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Usuario genera<br> */
	        xusuariogenera,
	        /** <b>Description:</b> Valor provisión<br>
	        * <b>Segment name:</b> gp_reportevacacion<br>
	        * <b>Query field:</b> xvalorprovision<br>
	        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
	        * <b>Input Label:</b> Valor provisión<br> */
	        xvalorprovision_rv
	    }
	    
	    private  Funciones_Vacaciones funcionesVacaciones=new  Funciones_Vacaciones(session);

	public gp_repovaccab_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#viewClick(com.unit4.karat.form.FMEvent)
	 */
	@Override
	public void viewClick(FMEvent arg0) throws OTException {
		// TODO Auto-generated method stub
		super.viewClick(arg0);
		arg0.setRecall(true);
		
		int iAnio=getItem(ITEMS.xanio_id).getValueInteger();
		Integer dPeriodo=getItem(ITEMS.xperiodo_id).getValueInteger();
		
		if("cmd_genera".equals(arg0.getSourceName())){
			funcionesVacaciones.calcularSaldosVacaciones(session,getItem(ITEMS.xempresa_id).getValue(),getItem(ITEMS.xorganizacion_id).getValue(),getItem(ITEMS.xentorno_id).getValue(),getItem(ITEMS.xanio_id).getValueInteger(),getItem(ITEMS.xperiodo_id).getValueDouble(),fmObject);
		}
		
		boObject.readRefresh();
		
		boObject.setCurrentRestriction("xanio_id=" + DAUtils.formatValue(iAnio,DA.DA_DT_INTEGER) + " and xperiodo_id=" + DAUtils.formatValue(dPeriodo,DA.DA_DT_LONG));
		
		getItem(ITEMS.xanio_id).setValue(iAnio);
		getItem(ITEMS.xperiodo_id).setValue(dPeriodo);
		
		fmObject.setPendingSave(false);
		fmObject.readRefresh();
	}

	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#formItemValidate(com.unit4.karat.form.FMEvent)
	 */
	//@Override
	/*public void formItemValidate(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.formItemValidate(fmEvent);
		fmEvent.setRecall(true);
		
		int iAnio=getItem(ITEMS.xanio_id).getValueInteger();
		Integer dPeriodo=getItem(ITEMS.xperiodo_id).getValueInteger();
		
		if(iAnio!=0 && dPeriodo!=0){
			boObject.readRefresh();
			
			boObject.setCurrentRestriction("xanio_id=" + DAUtils.formatValue(iAnio,DA.DA_DT_INTEGER) + " and xperiodo_id=" + DAUtils.formatValue(dPeriodo,DA.DA_DT_LONG));
			
			getItem(ITEMS.xanio_id).setValue(iAnio);
			getItem(ITEMS.xperiodo_id).setValue(dPeriodo);
			
			fmObject.setPendingSave(false);
			fmObject.readRefresh();
		}
	}*/

}
