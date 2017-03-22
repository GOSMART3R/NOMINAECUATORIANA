package com.gosmart3r.nom.ec;

import java.util.Date;
 

import com.unit4.karat.base.OTException;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.session.Session;

public class gp_prestamos_fm extends FMDefaultEvents {
	 private  Genera_Cuotas_Prestamos funciones;
	    public enum SEGMENTS {
	        /** <b>Base query:</b> gp_cuotas<br> */
	        gp_cuotas,
	        /** <b>Base query:</b> gp_prestamos<br> */
	        gp_prestamos
	    }

	    public enum ITEMS {
	        /** <b>Description:</b> Año<br>
	        * <b>Segment name:</b> gp_prestamos<br>
	        * <b>Query field:</b> xanio_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Año<br>
	        * <br>Año<br> */
	        xanio_id,
	        /** <b>Description:</b> Año<br>
	        * <b>Segment name:</b> gp_cuotas<br>
	        * <b>Query field:</b> xanio_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Año<br>
	        * <br>Año<br> */
	        xanio_id_cu,
	        /** <b>Description:</b> Capital<br>
	        * <b>Segment name:</b> gp_cuotas<br>
	        * <b>Query field:</b> xcapital<br>
	        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
	        * <b>Input Label:</b> Capital<br>
	        * <br>Capital<br> */
	        xcapital_cu,
	        /** <b>Description:</b> Concepto<br>
	        * <b>Segment name:</b> gp_prestamos<br>
	        * <b>Query field:</b> xconcepto<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Concepto<br>
	        * <br>Concepto<br> */
	        xconcepto,
	        /** <b>Description:</b> Concepto<br>
	        * <b>Segment name:</b> gp_cuotas<br>
	        * <b>Query field:</b> xconcepto<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Concepto<br>
	        * <br>Concepto<br> */
	        xconcepto_cu,
	        /** <b>Description:</b> Cuota<br>
	        * <b>Segment name:</b> gp_cuotas<br>
	        * <b>Query field:</b> xcuotas_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Cuota<br>
	        * <br>Cuota<br> */
	        xcuotas_id_cu,
	        /** <b>Description:</b> Empleado<br>
	        * <b>Segment name:</b> gp_prestamos<br>
	        * <b>Query field:</b> xempleado_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Empleado<br>
	        * <br>Empleado<br> */
	        xempleado_id,
	        /** <b>Description:</b> Empleado<br>
	        * <b>Segment name:</b> gp_cuotas<br>
	        * <b>Query field:</b> xempleado_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Empleado<br>
	        * <br>Empleado<br> */
	        xempleado_id_cu,
	        /** <b>Description:</b> Código empresa<br>
	        * <b>Segment name:</b> gp_prestamos<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código empresa<br>
	        * <br>Código empresa<br> */
	        xempresa_id,
	        /** <b>Description:</b> Código empresa<br>
	        * <b>Segment name:</b> gp_cuotas<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código empresa<br>
	        * <br>Código empresa<br> */
	        xempresa_id_cu,
	        /** <b>Description:</b> Código entorno<br>
	        * <b>Segment name:</b> gp_prestamos<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código entorno<br>
	        * <br>Código entorno<br> */
	        xentorno_id,
	        /** <b>Description:</b> Código entorno<br>
	        * <b>Segment name:</b> gp_cuotas<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código entorno<br>
	        * <br>Código entorno<br> */
	        xentorno_id_cu,
	        /** <b>Description:</b> Estado<br>
	        * <b>Segment name:</b> gp_cuotas<br>
	        * <b>Query field:</b> xestadocuota<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Estado<br>
	        * <br>Estado<br> */
	        xestadocuota_cu,
	        /** <b>Description:</b> Fecha<br>
	        * <b>Segment name:</b> gp_prestamos<br>
	        * <b>Query field:</b> xfecha<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha<br>
	        * <br>Fecha<br> */
	        xfecha,
	        /** <b>Description:</b> Fecha<br>
	        * <b>Segment name:</b> gp_cuotas<br>
	        * <b>Query field:</b> xfecha<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha<br>
	        * <br>Fecha<br> */
	        xfecha_cu,
	        /** <b>Description:</b> Fecha de vencimiento<br>
	        * <b>Segment name:</b> gp_cuotas<br>
	        * <b>Query field:</b> xfechavencimiento<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha de vencimiento<br>
	        * <br>Fecha de vencimiento<br> */
	        xfechavencimiento_cu,
	        /** <b>Description:</b> Interes<br>
	        * <b>Segment name:</b> gp_cuotas<br>
	        * <b>Query field:</b> xinteres<br>
	        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
	        * <b>Input Label:</b> Interes<br>
	        * <br>Interes<br> */
	        xinteres_cu,
	        /** <b>Description:</b> Monto<br>
	        * <b>Segment name:</b> gp_prestamos<br>
	        * <b>Query field:</b> xmonto<br>
	        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
	        * <b>Input Label:</b> Monto<br>
	        * <br>Monto<br> */
	        xmonto,
	        /** <b>Description:</b> Total cuota<br>
	        * <b>Segment name:</b> gp_cuotas<br>
	        * <b>Query field:</b> xmontocuota<br>
	        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
	        * <b>Input Label:</b> Total cuota<br>
	        * <br>Total cuota<br> */
	        xmontocuota_cu,
	        /** <b>Description:</b> Número de cuotas<br>
	        * <b>Segment name:</b> gp_prestamos<br>
	        * <b>Query field:</b> xnumerocuotas<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Número de cuotas<br>
	        * <br>Número de cuotas<br> */
	        xnumerocuotas,
	        /** <b>Description:</b> Código organización<br>
	        * <b>Segment name:</b> gp_prestamos<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código organización<br>
	        * <br>Código organización<br> */
	        xorganizacion_id,
	        /** <b>Description:</b> Código organización<br>
	        * <b>Segment name:</b> gp_cuotas<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código organización<br>
	        * <br>Código organización<br> */
	        xorganizacion_id_cu,
	        /** <b>Description:</b> Periodo<br>
	        * <b>Segment name:</b> gp_prestamos<br>
	        * <b>Query field:</b> xperiodo_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Periodo<br>
	        * <br>Periodo<br> */
	        xperiodo_id,
	        /** <b>Description:</b> Periodo<br>
	        * <b>Segment name:</b> gp_cuotas<br>
	        * <b>Query field:</b> xperiodo_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Periodo<br>
	        * <br>Periodo<br> */
	        xperiodo_id_cu,
	        /** <b>Description:</b> Tasa<br>
	        * <b>Segment name:</b> gp_prestamos<br>
	        * <b>Query field:</b> xtasa<br>
	        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
	        * <b>Input Label:</b> Tasa<br>
	        * <br>Tasa<br> */
	        xtasa,
	        /** <b>Description:</b> Cuota<br>
	        * <b>Segment name:</b> gp_prestamos<br>
	        * <b>Query field:</b> xtiposcuotas<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Cuota<br>
	        * <br>Cuota<br> */
	        xtiposcuotas,
	        /** <b>Description:</b> Préstamo<br>
	        * <b>Segment name:</b> gp_prestamos<br>
	        * <b>Query field:</b> xtiposprestamos_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Préstamo<br>
	        * <br>Préstamo<br> */
	        xtiposprestamos_id,
	        /** <b>Description:</b> Préstamo<br>
	        * <b>Segment name:</b> gp_cuotas<br>
	        * <b>Query field:</b> xtiposprestamos_id<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Préstamo<br>
	        * <br>Préstamo<br> */
	        xtiposprestamos_id_cu
	    }

	    public enum CONTAINERS {
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
	        _K_FIXED,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
	        * <b>Caption:</b> Cuotas<br> */
	        card1,
	        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
	        * <b>Caption:</b> Cuota<br> */
	        collap1,
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
	        * <b>Caption:</b> Préstamos<br> */
	        gp_prestamos,
	        /** <b>Window class:</b> FMContainer.CONTAINER_GRID<br> */
	        gr_cuotas,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARDINDEX<br> */
	        K__MAINCARD
	    }

	    public enum VIEWS {
	        /** <b>Caption:</b> Primer pago desde<br>
	        * <b>View Type:</b> FMView.VIEW_LABEL<br> */
	        lb_primerpago,
	        /** <b>Caption:</b> Año<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Año<br> */
	        xanio_id,
	        /** <b>Caption:</b> Capital<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Capital<br> */
	        xcapital_cu,
	        /** <b>Caption:</b> Concepto<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Concepto<br> */
	        xconcepto,
	        /** <b>Caption:</b> Cuota<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Cuota<br> */
	        xcuotas_id_cu,
	        /** <b>Caption:</b> Empleado<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Empleado<br> */
	        xempleado_id,
	        /** <b>Caption:</b> Estado<br>
	        * <b>View Type:</b> FMView.VIEW_DROPCOMBO<br>
	        * <br>Estado<br> */
	        xestadocuota_cu,
	        /** <b>Caption:</b> Fecha<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Fecha<br> */
	        xfecha,
	        /** <b>Caption:</b> Fecha de vencimiento<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Fecha de vencimiento<br> */
	        xfechavencimiento_cu,
	        /** <b>Caption:</b> Interes<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Interes<br> */
	        xinteres_cu,
	        /** <b>Caption:</b> Monto<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Monto<br> */
	        xmonto,
	        /** <b>Caption:</b> Total cuota<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Total cuota<br> */
	        xmontocuota_cu,
	        /** <b>Caption:</b> Número de cuotas<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Número de cuotas<br> */
	        xnumerocuotas,
	        /** <b>Caption:</b> Periodo<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Periodo<br> */
	        xperiodo_id,
	        /** <b>Caption:</b> Tasa<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Tasa<br> */
	        xtasa,
	        /** <b>Caption:</b> Cuota<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Cuota<br> */
	        xtiposcuotas,
	        /** <b>Caption:</b> Préstamo<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Préstamo<br> */
	        xtiposprestamos_id
	    }

	public gp_prestamos_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see com.unit4.karat.form.FMDefaultEvents#viewClick(com.unit4.karat.form.FMEvent)
	 */
	@Override
	public void viewClick(FMEvent fmEvent) throws OTException {
		// TODO Auto-generated method stub
		super.viewClick(fmEvent);
		fmEvent.setRecall(true);
		
		if(fmEvent.getSourceName().equals("btn_generacuotas")){
			Integer iAnio = getItem(ITEMS.xanio_id).getValueInteger();
			Integer iPeriodo=getItem(ITEMS.xperiodo_id).getValueInteger();
			Double dMonto =getItem(ITEMS.xmonto).getValueDouble();
			Double dTasa=getItem(ITEMS.xtasa).getValueDouble();
			Integer iNumerocuotas=getItem(ITEMS.xnumerocuotas).getValueInteger();
			String sEmpresa=getItem(ITEMS.xempresa_id).getValue();
			String sOrganizacion=getItem(ITEMS.xorganizacion_id).getValue();
			String sEntorno=getItem(ITEMS.xentorno_id).getValue();
			String sEmpleado=getItem(ITEMS.xempleado_id).getValue();
			Date dFecha=getItem(ITEMS.xfecha).getValueDate();
			String sTipoprestamo=getItem(ITEMS.xtiposprestamos_id).getValue();
			String sConcepto = getItem(ITEMS.xconcepto).getValue();
			String sTipoCuota=getItem(ITEMS.xtiposcuotas).getValue();
			Integer iRetorno=0;
			iRetorno=fmObject.showMessageText("Se procedera a generar las cuotas con los datos ingresados", "Aceptar/Cancelar");
			
			if(iRetorno==1){
				fmObject.save();
				funciones = new Genera_Cuotas_Prestamos();
				funciones.GeneraCuotas(session, sEmpresa, sOrganizacion, sEntorno, sEmpleado, dFecha, sTipoprestamo, sConcepto, sTipoCuota, dMonto, dTasa, iNumerocuotas, iAnio, iPeriodo);
				fmObject.readRefresh();
			}
					
		}
	}

	
    
 
}
