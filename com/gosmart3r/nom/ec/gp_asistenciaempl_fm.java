/**
 * 
 */
package com.gosmart3r.nom.ec;

import com.unit4.karat.base.OTException;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.session.Session;



/**
 * @author fr
 *
 */
public class gp_asistenciaempl_fm extends FMDefaultEvents {


    public enum SEGMENTS {
        /** <b>Base query:</b> gp_asistenciaempl<br> */
        gp_asistenciaempl,
        /** <b>Base query:</b> gp_dasistenciaempl<br> */
        gp_dasistenciaempl
    }

    public enum ITEMS {
        /** <b>Description:</b> Año<br>
        * <b>Segment name:</b> gp_asistenciaempl<br>
        * <b>Query field:</b> xanio_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Año<br>
        * <br>Año<br> */
        xanio_id,
        /** <b>Description:</b> Año<br>
        * <b>Segment name:</b> gp_dasistenciaempl<br>
        * <b>Query field:</b> xanio_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Año<br>
        * <br>Año<br> */
        xanio_id_da,
        /** <b>Description:</b> Código detalle<br>
        * <b>Segment name:</b> gp_dasistenciaempl<br>
        * <b>Query field:</b> xdasistenciaemp_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Código detalle<br>
        * <br>Código detalle<br> */
        xdasistenciaemp_id_da,
        /** <b>Description:</b> Empleado<br>
        * <b>Segment name:</b> gp_asistenciaempl<br>
        * <b>Query field:</b> xempleado_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Empleado<br>
        * <br>Empleado<br> */
        xempleado_id,
        /** <b>Description:</b> Empleado<br>
        * <b>Segment name:</b> gp_dasistenciaempl<br>
        * <b>Query field:</b> xempleado_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Empleado<br>
        * <br>Empleado<br> */
        xempleado_id_da,
        /** <b>Description:</b> Código empresa<br>
        * <b>Segment name:</b> gp_asistenciaempl<br>
        * <b>Query field:</b> xempresa_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código empresa<br>
        * <br>Código empresa<br> */
        xempresa_id,
        /** <b>Description:</b> Código empresa<br>
        * <b>Segment name:</b> gp_dasistenciaempl<br>
        * <b>Query field:</b> xempresa_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código empresa<br>
        * <br>Código empresa<br> */
        xempresa_id_da,
        /** <b>Description:</b> Código entorno<br>
        * <b>Segment name:</b> gp_asistenciaempl<br>
        * <b>Query field:</b> xentorno_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código entorno<br>
        * <br>Código entorno<br> */
        xentorno_id,
        /** <b>Description:</b> Código entorno<br>
        * <b>Segment name:</b> gp_dasistenciaempl<br>
        * <b>Query field:</b> xentorno_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código entorno<br>
        * <br>Código entorno<br> */
        xentorno_id_da,
        /** <b>Description:</b> Fecha<br>
        * <b>Segment name:</b> gp_dasistenciaempl<br>
        * <b>Query field:</b> xfecha<br>
        * <b>Data Type:</b> DA.DA_DT_DATE<br>
        * <b>Input Label:</b> Fecha<br>
        * <br>Fecha<br> */
        xfecha_da,
        /** <b>Description:</b> Fecha final<br>
        * <b>Segment name:</b> gp_asistenciaempl<br>
        * <b>Query field:</b> xfechafin<br>
        * <b>Data Type:</b> DA.DA_DT_DATE<br>
        * <b>Input Label:</b> Fecha final<br>
        * <br>Fecha final<br> */
        xfechafin,
        /** <b>Description:</b> Fecha genera<br>
        * <b>Segment name:</b> gp_asistenciaempl<br>
        * <b>Query field:</b> xfechagenera<br>
        * <b>Data Type:</b> DA.DA_DT_DATE<br>
        * <b>Input Label:</b> Fecha genera<br>
        * <br>Fecha genera<br> */
        xfechagenera,
        /** <b>Description:</b> Fecha genera<br>
        * <b>Segment name:</b> gp_dasistenciaempl<br>
        * <b>Query field:</b> xfechagenera<br>
        * <b>Data Type:</b> DA.DA_DT_DATE<br>
        * <b>Input Label:</b> Fecha genera<br>
        * <br>Fecha genera<br> */
        xfechagenera_da,
        /** <b>Description:</b> Fecha inicial<br>
        * <b>Segment name:</b> gp_asistenciaempl<br>
        * <b>Query field:</b> xfechainicio<br>
        * <b>Data Type:</b> DA.DA_DT_DATE<br>
        * <b>Input Label:</b> Fecha inicial<br>
        * <br>Fecha inicial<br> */
        xfechainicio,
        /** <b>Description:</b> Horas<br>
        * <b>Segment name:</b> gp_asistenciaempl<br>
        * <b>Query field:</b> xhoras<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Horas<br>
        * <br>Horas<br> */
        xhoras,
        /** <b>Description:</b> Horas<br>
        * <b>Segment name:</b> gp_dasistenciaempl<br>
        * <b>Query field:</b> xhoras<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Horas<br>
        * <br>Horas<br> */
        xhoras_da,
        /** <b>Description:</b> Minutos<br>
        * <b>Segment name:</b> gp_asistenciaempl<br>
        * <b>Query field:</b> xminutos<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Minutos<br>
        * <br>Minutos<br> */
        xminutos,
        /** <b>Description:</b> Minutos<br>
        * <b>Segment name:</b> gp_dasistenciaempl<br>
        * <b>Query field:</b> xminutos<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Minutos<br>
        * <br>Minutos<br> */
        xminutos_da,
        /** <b>Description:</b> Observaciones<br>
        * <b>Segment name:</b> gp_asistenciaempl<br>
        * <b>Query field:</b> xobservaciones<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Observaciones<br>
        * <br>Observaciones<br> */
        xobservaciones,
        /** <b>Description:</b> Observaciones<br>
        * <b>Segment name:</b> gp_dasistenciaempl<br>
        * <b>Query field:</b> xobservaciones<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Observaciones<br>
        * <br>Observaciones<br> */
        xobservaciones_da,
        /** <b>Description:</b> Código organización<br>
        * <b>Segment name:</b> gp_asistenciaempl<br>
        * <b>Query field:</b> xorganizacion_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código organización<br>
        * <br>Código organización<br> */
        xorganizacion_id,
        /** <b>Description:</b> Código organización<br>
        * <b>Segment name:</b> gp_dasistenciaempl<br>
        * <b>Query field:</b> xorganizacion_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Código organización<br>
        * <br>Código organización<br> */
        xorganizacion_id_da,
        /** <b>Description:</b> Periodo<br>
        * <b>Segment name:</b> gp_asistenciaempl<br>
        * <b>Query field:</b> xperiodo_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Periodo<br>
        * <br>Periodo<br> */
        xperiodo_id,
        /** <b>Description:</b> Periodo<br>
        * <b>Segment name:</b> gp_dasistenciaempl<br>
        * <b>Query field:</b> xperiodo_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Periodo<br>
        * <br>Periodo<br> */
        xperiodo_id_da,
        /** <b>Description:</b> Tipo de asistencia<br>
        * <b>Segment name:</b> gp_asistenciaempl<br>
        * <b>Query field:</b> xtipoasistencia_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Tipo de asistencia<br>
        * <br>Tipo de asistencia<br> */
        xtipoasistencia_id,
        /** <b>Description:</b> Tipo de asistencia<br>
        * <b>Segment name:</b> gp_dasistenciaempl<br>
        * <b>Query field:</b> xtipoasistencia_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Tipo de asistencia<br>
        * <br>Tipo de asistencia<br> */
        xtipoasistencia_id_da,
        /** <b>Description:</b> Origen<br>
        * <b>Segment name:</b> gp_asistenciaempl<br>
        * <b>Query field:</b> xtipoorigen<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Origen<br>
        * <br>Origen<br> */
        xtipoorigen,
        /** <b>Description:</b> Origen<br>
        * <b>Segment name:</b> gp_dasistenciaempl<br>
        * <b>Query field:</b> xtipoorigen<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Origen<br>
        * <br>Origen<br> */
        xtipoorigen_da,
        /** <b>Description:</b> Total horas<br>
        * <b>Segment name:</b> gp_asistenciaempl<br>
        * <b>Query field:</b> xtotalhoras<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Total horas<br>
        * <br>Total horas<br> */
        xtotalhoras,
        /** <b>Description:</b> Total horas<br>
        * <b>Segment name:</b> gp_dasistenciaempl<br>
        * <b>Query field:</b> xtotalhoras<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Total horas<br>
        * <br>Total horas<br> */
        xtotalhoras_da,
        /** <b>Description:</b> Usuario genera<br>
        * <b>Segment name:</b> gp_asistenciaempl<br>
        * <b>Query field:</b> xusuariogenera<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Usuario genera<br>
        * <br>Usuario genera<br> */
        xusuariogenera,
        /** <b>Description:</b> Usuario genera<br>
        * <b>Segment name:</b> gp_dasistenciaempl<br>
        * <b>Query field:</b> xusuariogenera<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Usuario genera<br>
        * <br>Usuario genera<br> */
        xusuariogenera_da
    }

    public enum CONTAINERS {
        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
        _K_FIXED,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
        * <b>Caption:</b> Detalle<br> */
        card1,
        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
        * <b>Caption:</b> Desglose<br> */
        collap1,
        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
        * <b>Caption:</b> Novedades de asistencia<br> */
        gp_asistenciaempl,
        /** <b>Window class:</b> FMContainer.CONTAINER_GRID<br> */
        gr_dasistemciaempl,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARDINDEX<br> */
        K__MAINCARD
    }

    public enum VIEWS {
        /** <b>Caption:</b> Año<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Año<br> */
        xanio_id,
        /** <b>Caption:</b> Código detalle<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Código detalle<br> */
        xdasistenciaemp_id_da,
        /** <b>Caption:</b> Empleado<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Empleado<br> */
        xempleado_id,
        /** <b>Caption:</b> Fecha<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Fecha<br> */
        xfecha_da,
        /** <b>Caption:</b> Fecha final<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Fecha final<br> */
        xfechafin,
        /** <b>Caption:</b> Fecha genera<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Fecha genera<br> */
        xfechagenera,
        /** <b>Caption:</b> Fecha genera<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Fecha genera<br> */
        xfechagenera_da,
        /** <b>Caption:</b> Fecha inicial<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Fecha inicial<br> */
        xfechainicio,
        /** <b>Caption:</b> Horas<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Horas<br> */
        xhoras,
        /** <b>Caption:</b> Horas<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Horas<br> */
        xhoras_da,
        /** <b>Caption:</b> Minutos<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Minutos<br> */
        xminutos,
        /** <b>Caption:</b> Minutos<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Minutos<br> */
        xminutos_da,
        /** <b>Caption:</b> Observaciones<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Observaciones<br> */
        xobservaciones,
        /** <b>Caption:</b> Periodo<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Periodo<br> */
        xperiodo_id,
        /** <b>Caption:</b> Tipo de asistencia<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Tipo de asistencia<br> */
        xtipoasistencia_id,
        /** <b>Caption:</b> Origen<br>
        * <b>View Type:</b> FMView.VIEW_DROPLIST<br>
        * <br>Origen<br> */
        xtipoorigen,
        /** <b>Caption:</b> Total horas<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Total horas<br> */
        xtotalhoras,
        /** <b>Caption:</b> Total horas<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Total horas<br> */
        xtotalhoras_da,
        /** <b>Caption:</b> Usuario genera<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Usuario genera<br> */
        xusuariogenera,
        /** <b>Caption:</b> Usuario genera<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Usuario genera<br> */
        xusuariogenera_da
    }

	
	/**
	 * @param session
	 */
	public gp_asistenciaempl_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#formItemValidate(com.unit4.karat.form.FMEvent)
	 */
	@Override
	public void formItemValidate(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.formItemValidate(fmEvent);
		fmEvent.setRecall(true);
		Double  dTotalHoras,dHoras, dMinutos;
		Boolean bSumadetalle=false;
		Integer iFilas=0,iHoras, iMinutos;
		
		iHoras=0;
		iMinutos=0;
		dTotalHoras=0.00;
		if(fmEvent.getSourceName().equals("xhoras") || fmEvent.getSourceName().equals("xminutos")){
			iHoras=getItem(ITEMS.xhoras).getValueInteger() ;
			iMinutos=getItem(ITEMS.xminutos).getValueInteger();
			dHoras=(double) (iHoras);
			dMinutos=(double) (iMinutos);
			dTotalHoras=(double) (dHoras+(dMinutos/60));
			getItem(ITEMS.xtotalhoras).setValue(dTotalHoras);
			if(((getSegment(SEGMENTS.gp_dasistenciaempl).getRows()) - 2)>=0){
				bSumadetalle=true;
			}else{
				bSumadetalle=false;
			}
		}
		
	
		
		iHoras=0;
		iMinutos=0;
		dTotalHoras=0.00;
		
		if(fmEvent.getSourceName().equals("xhoras_da") || fmEvent.getSourceName().equals("xminutos_da")||bSumadetalle==true){
			iHoras=getItem(ITEMS.xhoras_da).getValueInteger(fmEvent.getRow());
			iMinutos=getItem(ITEMS.xminutos_da).getValueInteger(fmEvent.getRow());
			dHoras=(double) (iHoras);
			dMinutos=(double) (iMinutos);
			dTotalHoras=  (double) (dHoras+(dMinutos/60)) ;
			getItem(ITEMS.xtotalhoras_da).setValue(fmEvent.getRow(),dTotalHoras);
			
			iFilas=(getSegment(SEGMENTS.gp_dasistenciaempl).getRows()) - 2;
			iHoras=iMinutos=0;
			dTotalHoras=0.00;
			for(int i=0 ; i<=iFilas ;i++){
				iHoras+=getItem(ITEMS.xhoras_da).getValueInteger(i);
				iMinutos+=getItem(ITEMS.xminutos_da).getValueInteger(i);
				dTotalHoras+=getItem(ITEMS.xtotalhoras_da).getValueDouble(i);
				
				//fmObject.showMessageText("<<<<<<<>>>>>>>" + getItem(ITEMS.xhoras_da).getValueDouble(i), "Aceptar/Cancelar");
			}
			
			if(iHoras>0 || iMinutos>0 || dTotalHoras>0){
				getItem(ITEMS.xhoras).setValue(iHoras);
				getItem(ITEMS.xminutos).setValue(iMinutos);
				getItem(ITEMS.xtotalhoras).setValue(dTotalHoras);
			}
		}
		

	}
	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#viewClick(com.unit4.karat.form.FMEvent)
	 */
	@Override
	public void viewClick(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.viewClick(fmEvent);
		fmEvent.setRecall(true);
		
		fmObject.showMessageText(session.getWorkDirUser() + "<<<<<<>>>>>"+session.getWorkDirCommon(), "Aceptar/Cancelar");
		
		
		if(fmEvent.getSourceName().equals("btn_importar")){
			fmObject.showMessageText(">>>>>>"+session.getGlobalData().getString("ficheroSelec"), "Aceptar/Cancelar");
			
		}
	}
	
	
}
