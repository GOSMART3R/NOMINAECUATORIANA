package com.gosmart3r.nom.ec;

import com.unit4.karat.base.OTException;
import com.unit4.karat.bo.BODefaultEvents;
import com.unit4.karat.bo.BOEvent;
import com.unit4.karat.session.Session;

public class gp_saldovacacion_bo extends BODefaultEvents {
	  public enum SEGMENTS {
	        /** <b>Base query:</b> gp_saldovacacion<br> */
	        gp_saldovacacion
	    }

	    public enum ITEMS {
	        /** <b>Description:</b> Cargo<br>
	        * <b>Segment name:</b> gp_saldovacacion<br>
	        * <b>Query field:</b> xcargo_desc<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Cargo<br> */
	        xcargo_desc,
	        /** <b>Description:</b> Código cargo<br>
	        * <b>Segment name:</b> gp_saldovacacion<br>
	        * <b>Query field:</b> xcargo_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código cargo<br> */
	        xcargo_id,
	        /** <b>Description:</b> Días ganados<br>
	        * <b>Segment name:</b> gp_saldovacacion<br>
	        * <b>Query field:</b> xdiasganados<br>
	        * <b>Data Type:</b> DA.DA_DT_DOUBLE<br>
	        * <b>Input Label:</b> Días ganados<br> */
	        xdiasganados,
	        /** <b>Description:</b> Días tomados<br>
	        * <b>Segment name:</b> gp_saldovacacion<br>
	        * <b>Query field:</b> xdiastomados<br>
	        * <b>Data Type:</b> DA.DA_DT_INTEGER<br>
	        * <b>Input Label:</b> Días tomados<br> */
	        xdiastomados,
	        /** <b>Description:</b> Código del empleado<br>
	        * <b>Segment name:</b> gp_saldovacacion<br>
	        * <b>Query field:</b> xempleado_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código del empleado<br> */
	        xempleado_id,
	        /** <b>Description:</b> Código empresa<br>
	        * <b>Segment name:</b> gp_saldovacacion<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código empresa<br> */
	        xempresa_id,
	        /** <b>Description:</b> Código entorno<br>
	        * <b>Segment name:</b> gp_saldovacacion<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código entorno<br> */
	        xentorno_id,
	        /** <b>Description:</b> Fecha genera<br>
	        * <b>Segment name:</b> gp_saldovacacion<br>
	        * <b>Query field:</b> xfechagenera<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha genera<br> */
	        xfechagenera,
	        /** <b>Description:</b> Fecha ingreso<br>
	        * <b>Segment name:</b> gp_saldovacacion<br>
	        * <b>Query field:</b> xfechaingreso<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha ingreso<br> */
	        xfechaingreso,
	        /** <b>Description:</b> Días máximos<br>
	        * <b>Segment name:</b> gp_saldovacacion<br>
	        * <b>Query field:</b> xmaximosdias<br>
	        * <b>Data Type:</b> DA.DA_DT_DOUBLE<br>
	        * <b>Input Label:</b> Días máximos<br> */
	        xmaximosdias,
	        /** <b>Description:</b> Nombres/Apellidos empleado<br>
	        * <b>Segment name:</b> gp_saldovacacion<br>
	        * <b>Query field:</b> xnombreempleado<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Nombres/Apellidos empleado<br> */
	        xnombreempleado,
	        /** <b>Description:</b> Código organización<br>
	        * <b>Segment name:</b> gp_saldovacacion<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código organización<br> */
	        xorganizacion_id,
	        /** <b>Description:</b> Saldo de días<br>
	        * <b>Segment name:</b> gp_saldovacacion<br>
	        * <b>Query field:</b> xsaldodias<br>
	        * <b>Data Type:</b> DA.DA_DT_DOUBLE<br>
	        * <b>Input Label:</b> Saldo de días<br> */
	        xsaldodias,
	        /** <b>Description:</b> Usuario genera<br>
	        * <b>Segment name:</b> gp_saldovacacion<br>
	        * <b>Query field:</b> xusuariogenera<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Usuario genera<br> */
	        xusuariogenera
	    }

	public gp_saldovacacion_bo(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}
	 private  Funciones_Vacaciones funcionesVacaciones=new  Funciones_Vacaciones(session);
	    
		/* (non-Javadoc)
		 * @see com.unit4.karat.bo.BODefaultEvents#load(com.unit4.karat.bo.BOEvent)
		 */
		@Override
		public void load(BOEvent boEvent) throws OTException {
			// TODO Auto-generated method stub
			super.load(boEvent);
			boEvent.setRecall(true);
			
			String sEmpresa=getItem(ITEMS.xempresa_id).getValue();
			String sEntorno=getItem(ITEMS.xentorno_id).getValue();
			String sOrganizacion=getItem(ITEMS.xorganizacion_id).getValue();

			funcionesVacaciones.reporteSaldoVacacaciones(session,sEmpresa,sOrganizacion,sEntorno);
		}

}
