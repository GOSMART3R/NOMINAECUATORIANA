/**
 * 
 */
package com.gosmart3r.nom.ec;


import com.unit4.karat.base.OTException;
import com.unit4.karat.base.log.Logger;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.session.Session;

/**
 * @author fr
 *
 */
public class gp_anios_fm extends FMDefaultEvents {
	private Funciones_Generales funciones;
	
    public enum SEGMENTS {
        /** <b>Base query:</b> gp_anios<br> */
        gp_anios,
        /** <b>Base query:</b> gp_constantesxanio<br> */
        gp_constantesxanio,
        /** <b>Base query:</b> gp_diasperiodos<br> */
        gp_diasperiodos,
        /** <b>Base query:</b> gp_periodos<br> */
        gp_periodos
    }

    public enum ITEMS {
        /** <b>Description:</b> A?o<br>
        * <b>Segment name:</b> gp_anios<br>
        * <b>Query field:</b> xanio<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> A?o<br>
        * <br>A?o<br> */
        xanio,
        /** <b>Description:</b> C?digo a?o<br>
        * <b>Segment name:</b> gp_anios<br>
        * <b>Query field:</b> xanio_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> C?digo a?o<br>
        * <br>C?digo a?o<br> */
        xanio_id,
        /** <b>Description:</b> C?digo a?o<br>
        * <b>Segment name:</b> gp_constantesxanio<br>
        * <b>Query field:</b> xanio_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> C?digo a?o<br>
        * <br>C?digo a?o<br> */
        xanio_id_con,
        /** <b>Description:</b> C?digo a?o<br>
        * <b>Segment name:</b> gp_diasperiodos<br>
        * <b>Query field:</b> xanio_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> C?digo a?o<br>
        * <br>C?digo a?o<br> */
        xanio_id_di,
        /** <b>Description:</b> C?digo a?o<br>
        * <b>Segment name:</b> gp_periodos<br>
        * <b>Query field:</b> xanio_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> C?digo a?o<br>
        * <br>C?digo a?o<br> */
        xanio_id_per,
        /** <b>Description:</b> C?digo constante<br>
        * <b>Segment name:</b> gp_constantesxanio<br>
        * <b>Query field:</b> xconstante_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C?digo constante<br>
        * <br>Las iniciales de los c?digo deberan ser : Rubro (R) , Variable (V), Constante (C)<br> */
        xconstante_id,
        /** <b>Description:</b> Desde<br>
        * <b>Segment name:</b> gp_periodos<br>
        * <b>Query field:</b> xdesde<br>
        * <b>Data Type:</b> DA.DA_DT_DATE<br>
        * <b>Input Label:</b> Desde<br>
        * <br>Desde<br> */
        xdesde,
        /** <b>Description:</b> D?as periodo<br>
        * <b>Segment name:</b> gp_periodos<br>
        * <b>Query field:</b> xdias<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> D?as periodo<br>
        * <br>D?as periodo<br> */
        xdias,
        /** <b>Description:</b> D?a<br>
        * <b>Segment name:</b> gp_diasperiodos<br>
        * <b>Query field:</b> xdias<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> D?a<br>
        * <br>D?a<br> */
        xdias_di,
        /** <b>Description:</b> C?digo empresa<br>
        * <b>Segment name:</b> gp_anios<br>
        * <b>Query field:</b> xempresa_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C?digo empresa<br>
        * <br>C?digo empresa<br> */
        xempresa_id,
        /** <b>Description:</b> C?digo empresa<br>
        * <b>Segment name:</b> gp_constantesxanio<br>
        * <b>Query field:</b> xempresa_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C?digo empresa<br>
        * <br>C?digo empresa<br> */
        xempresa_id_con,
        /** <b>Description:</b> C?digo empresa<br>
        * <b>Segment name:</b> gp_diasperiodos<br>
        * <b>Query field:</b> xempresa_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C?digo empresa<br>
        * <br>C?digo empresa<br> */
        xempresa_id_di,
        /** <b>Description:</b> C?digo empresa<br>
        * <b>Segment name:</b> gp_periodos<br>
        * <b>Query field:</b> xempresa_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C?digo empresa<br>
        * <br>C?digo empresa<br> */
        xempresa_id_per,
        /** <b>Description:</b> C?digo entorno<br>
        * <b>Segment name:</b> gp_anios<br>
        * <b>Query field:</b> xentorno_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C?digo entorno<br>
        * <br>C?digo entorno<br> */
        xentorno_id,
        /** <b>Description:</b> C?digo entorno<br>
        * <b>Segment name:</b> gp_constantesxanio<br>
        * <b>Query field:</b> xentorno_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C?digo entorno<br>
        * <br>C?digo entorno<br> */
        xentorno_id_con,
        /** <b>Description:</b> C?digo entorno<br>
        * <b>Segment name:</b> gp_diasperiodos<br>
        * <b>Query field:</b> xentorno_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C?digo entorno<br>
        * <br>C?digo entorno<br> */
        xentorno_id_di,
        /** <b>Description:</b> C?digo entorno<br>
        * <b>Segment name:</b> gp_periodos<br>
        * <b>Query field:</b> xentorno_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C?digo entorno<br>
        * <br>C?digo entorno<br> */
        xentorno_id_per,
        /** <b>Description:</b> Fecha<br>
        * <b>Segment name:</b> gp_diasperiodos<br>
        * <b>Query field:</b> xfecha<br>
        * <b>Data Type:</b> DA.DA_DT_DATE<br>
        * <b>Input Label:</b> Fecha<br>
        * <br>Fecha<br> */
        xfecha_di,
        /** <b>Description:</b> Hasta<br>
        * <b>Segment name:</b> gp_periodos<br>
        * <b>Query field:</b> xhasta<br>
        * <b>Data Type:</b> DA.DA_DT_DATE<br>
        * <b>Input Label:</b> Hasta<br>
        * <br>Hasta<br> */
        xhasta,
        /** <b>Description:</b> C?digo organizaci?n<br>
        * <b>Segment name:</b> gp_anios<br>
        * <b>Query field:</b> xorganizacion_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C?digo organizaci?n<br>
        * <br>C?digo organizaci?n<br> */
        xorganizacion_id,
        /** <b>Description:</b> C?digo organizaci?n<br>
        * <b>Segment name:</b> gp_constantesxanio<br>
        * <b>Query field:</b> xorganizacion_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C?digo organizaci?n<br>
        * <br>C?digo organizaci?n<br> */
        xorganizacion_id_con,
        /** <b>Description:</b> C?digo organizaci?n<br>
        * <b>Segment name:</b> gp_diasperiodos<br>
        * <b>Query field:</b> xorganizacion_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C?digo organizaci?n<br>
        * <br>C?digo organizaci?n<br> */
        xorganizacion_id_di,
        /** <b>Description:</b> C?digo organizaci?n<br>
        * <b>Segment name:</b> gp_periodos<br>
        * <b>Query field:</b> xorganizacion_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C?digo organizaci?n<br>
        * <br>C?digo organizaci?n<br> */
        xorganizacion_id_per,
        /** <b>Description:</b> Periodo<br>
        * <b>Segment name:</b> gp_periodos<br>
        * <b>Query field:</b> xperiodo<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> Periodo<br>
        * <br>Periodo<br> */
        xperiodo,
        /** <b>Description:</b> C?digo periodo<br>
        * <b>Segment name:</b> gp_periodos<br>
        * <b>Query field:</b> xperiodo_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> C?digo periodo<br>
        * <br>C?digo periodo<br> */
        xperiodo_id,
        /** <b>Description:</b> C?digo periodo<br>
        * <b>Segment name:</b> gp_diasperiodos<br>
        * <b>Query field:</b> xperiodo_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> C?digo periodo<br>
        * <br>C?digo periodo<br> */
        xperiodo_id_di,
        /** <b>Description:</b> Valor<br>
        * <b>Segment name:</b> gp_constantesxanio<br>
        * <b>Query field:</b> xvalor<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Valor<br>
        * <br>Valor<br> */
        xvalor
    }

    public enum CONTAINERS {
        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
        _K_FIXED,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
        * <b>Caption:</b> Periodos<br> */
        card1,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
        * <b>Caption:</b> Constantes<br> */
        card2,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
        * <b>Caption:</b> D?as x periodo<br> */
        card3,
        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
        * <b>Caption:</b> Periodo<br> */
        collap1,
        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
        * <b>Caption:</b> Constante<br> */
        collap2,
        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
        * <b>Caption:</b> D?as<br> */
        collap3,
        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
        * <b>Caption:</b> Generar a?os y periodos<br> */
        gp_anios,
        /** <b>Window class:</b> FMContainer.CONTAINER_GRID<br> */
        gr_constantes,
        /** <b>Window class:</b> FMContainer.CONTAINER_GRID<br> */
        gr_diasxperiodo,
        /** <b>Window class:</b> FMContainer.CONTAINER_GRID<br> */
        gr_periodos,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARDINDEX<br> */
        K__MAINCARD
    }

    public enum VIEWS {
        /** <b>Caption:</b> Generar d?as x periodos<br>
        * <b>View Type:</b> FMView.VIEW_BUTTON<br> */
        btn_generar,
        /** <b>Caption:</b> A?o<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>A?o<br> */
        xanio,
        /** <b>Caption:</b> C?digo a?o<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>C?digo a?o<br> */
        xanio_id,
        /** <b>Caption:</b> C?digo constante<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Las iniciales de los c?digo deberan ser : Rubro (R) , Variable (V), Constante (C)<br> */
        xconstante_id,
        /** <b>Caption:</b> Desde<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Desde<br> */
        xdesde,
        /** <b>Caption:</b> Desde<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Desde<br> */
        xdesde1,
        /** <b>Caption:</b> D?as periodo<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>D?as periodo<br> */
        xdias,
        /** <b>Caption:</b> D?a<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>D?a<br> */
        xdias_di,
        /** <b>Caption:</b> Fecha<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Fecha<br> */
        xfecha_di,
        /** <b>Caption:</b> Hasta<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Hasta<br> */
        xhasta,
        /** <b>Caption:</b> Hasta<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Hasta<br> */
        xhasta1,
        /** <b>Caption:</b> Periodo<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Periodo<br> */
        xperiodo,
        /** <b>Caption:</b> C?digo periodo<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>C?digo periodo<br> */
        xperiodo_id,
        /** <b>Caption:</b> C?digo periodo<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>C?digo periodo<br> */
        xperiodo_id_1,
        /** <b>Caption:</b> Valor<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Valor<br> */
        xvalor
    }

	
	
	/**
	 * @param arg0
	 */
	public gp_anios_fm(Session session) {
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
		
		
		
		fmObject.showMessageText("Este proceso puede tardar unos minutos..", "Aceptar");
		
		
		if("btn_generar".equals(fmEvent.getSourceName())){
			String sEmpresa=getItem(ITEMS.xempresa_id).getValue();
			String sEntorno = getItem(ITEMS.xentorno_id).getValue();
			String sOrganizacion=getItem(ITEMS.xorganizacion_id).getValue();
			Integer iAnio=getItem(ITEMS.xanio_id).getValueInteger();
			
			funciones = new Funciones_Generales();
			
			funciones.GenerarPeriodos(session, sEmpresa, sEntorno, sOrganizacion, iAnio);
			
			funciones.GenerarDiasPeriodos(session, sEmpresa, sEntorno, sOrganizacion, iAnio);
			
			funciones.GeneraSemanas(session, iAnio, sEmpresa, sEntorno, sOrganizacion);
			
			funciones.GeneraConsildaSemanas(session, iAnio, sEmpresa, sEntorno, sOrganizacion);
			
			funciones.GenerarQuincenas(session, sEmpresa, sEntorno, sOrganizacion, iAnio);
			
			fmObject.showMessageText("Proceso terminado con exito...", "Aceptar");
			
			//indicar que ingreso a los formularios esto deberemos poner en todos los eventos
			//para identificar que ingresamos
			Logger log = Logger.getInstance("EPARTBO");
			log.info("Log Nomina - GoSmart3R",session.getSessionInfo());
		}
		
		
	}
}
