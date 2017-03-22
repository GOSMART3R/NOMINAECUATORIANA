package com.gosmart3r.nom.ec;



import com.unit4.karat.base.OTException;
import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.form.FMEvent;
import com.unit4.karat.session.Session;

public class gp_nominasdepar1_fm extends FMDefaultEvents {
	private Genera_Nominas funciones;
    public enum SEGMENTS {
        /** <b>Base query:</b> gp_asistencias<br> */
        gp_asistencias,
        /** <b>Base query:</b> gp_nominasdepar<br> */
        gp_nominasdepar1,
        /** <b>Base query:</b> gp_pagonomina<br> */
        gp_pagonomina,
        /** <b>Base query:</b> gp_rolesemple<br> */
        gp_rolesemple
    }

    public enum ITEMS {
        /** <b>Description:</b> C�digo a�o<br>
        * <b>Segment name:</b> gp_nominasdepar1<br>
        * <b>Query field:</b> xanio_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> C�digo a�o<br>
        * <br>C�digo a�o<br> */
        xanio_id,
        /** <b>Description:</b> C�digo departamento<br>
        * <b>Segment name:</b> gp_nominasdepar1<br>
        * <b>Query field:</b> xdepartamento_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo departamento<br>
        * <br>C�digo departamento<br> */
        xdepartamento_id,
        /** <b>Description:</b> C�digo del empleado<br>
        * <b>Segment name:</b> gp_pagonomina<br>
        * <b>Query field:</b> xempleado_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo del empleado<br>
        * <br>C�digo del empleado<br> */
        xempleado_id,
        /** <b>Description:</b> C�digo del Empleado<br>
        * <b>Segment name:</b> gp_asistencias<br>
        * <b>Query field:</b> xempleado_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo del Empleado<br>
        * <br>C�digo del Empleado<br> */
        xempleado_id_as,
        /** <b>Description:</b> C�digo del empleado<br>
        * <b>Segment name:</b> gp_rolesemple<br>
        * <b>Query field:</b> xempleado_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo del empleado<br>
        * <br>C�digo del empleado<br> */
        xempleado_id_ro,
        /** <b>Description:</b> C�digo empresa<br>
        * <b>Segment name:</b> gp_nominasdepar1<br>
        * <b>Query field:</b> xempresa_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo empresa<br>
        * <br>C�digo empresa<br> */
        xempresa_id,
        /** <b>Description:</b> C�digo entorno<br>
        * <b>Segment name:</b> gp_nominasdepar1<br>
        * <b>Query field:</b> xentorno_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo entorno<br>
        * <br>C�digo entorno<br> */
        xentorno_id,
        /** <b>Description:</b> Estado<br>
        * <b>Segment name:</b> gp_nominasdepar1<br>
        * <b>Query field:</b> xestado<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Estado<br>
        * <br>Estado<br> */
        xestado,
        /** <b>Description:</b> Generado<br>
        * <b>Segment name:</b> gp_nominasdepar1<br>
        * <b>Query field:</b> xfecha<br>
        * <b>Data Type:</b> DA.DA_DT_DATE<br>
        * <b>Input Label:</b> Generado<br>
        * <br>Generado<br> */
        xfecha,
        /** <b>Description:</b> Genera<br>
        * <b>Segment name:</b> gp_nominasdepar1<br>
        * <b>Query field:</b> xgenerar<br>
        * <b>Data Type:</b> DA.DA_DT_BOOLEAN<br>
        * <b>Input Label:</b> Genera<br>
        * <br>Genera<br> */
        xgenerar,
        /** <b>Description:</b> Horas<br>
        * <b>Segment name:</b> gp_asistencias<br>
        * <b>Query field:</b> xhoras<br>
        * <b>Data Type:</b> DA.DA_DT_DOUBLE<br>
        * <b>Input Label:</b> Horas<br>
        * <br>Horas<br> */
        xhoras,
        /** <b>Description:</b> N�mero de empleados<br>
        * <b>Segment name:</b> gp_nominasdepar1<br>
        * <b>Query field:</b> xnumeroempleados<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> N�mero de empleados<br>
        * <br>N�mero de empleados<br> */
        xnumeroempleados,
        /** <b>Description:</b> C�digo organizaci�n<br>
        * <b>Segment name:</b> gp_nominasdepar1<br>
        * <b>Query field:</b> xorganizacion_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo organizaci�n<br>
        * <br>C�digo organizaci�n<br> */
        xorganizacion_id,
        /** <b>Description:</b> C�digo periodo<br>
        * <b>Segment name:</b> gp_nominasdepar1<br>
        * <b>Query field:</b> xperiodo_id<br>
        * <b>Data Type:</b> DA.DA_DT_LONG<br>
        * <b>Input Label:</b> C�digo periodo<br>
        * <br>C�digo periodo<br> */
        xperiodo_id,
        /** <b>Description:</b> Rubros descuentos<br>
        * <b>Segment name:</b> gp_rolesemple<br>
        * <b>Query field:</b> xrubro_de<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Rubros descuentos<br>
        * <br>Las iniciales de los c�digo deberan ser : Rubro (R) , Variable (V), Constante (C)<br> */
        xrubro_de,
        /** <b>Description:</b> C�digo rubro<br>
        * <b>Segment name:</b> gp_pagonomina<br>
        * <b>Query field:</b> xrubro_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> C�digo rubro<br>
        * <br>C�digo rubro<br> */
        xrubro_id,
        /** <b>Description:</b> Rubros ingresos<br>
        * <b>Segment name:</b> gp_rolesemple<br>
        * <b>Query field:</b> xrubro_in<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Rubros ingresos<br>
        * <br>Las iniciales de los c�digo deberan ser : Rubro (R) , Variable (V), Constante (C)<br> */
        xrubro_in,
        /** <b>Description:</b> Tipo de asistencia<br>
        * <b>Segment name:</b> gp_asistencias<br>
        * <b>Query field:</b> xtipoasistencia_id<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Tipo de asistencia<br>
        * <br>Tipo de asistencia<br> */
        xtipoasistencia_id,
        /** <b>Description:</b> Generado por<br>
        * <b>Segment name:</b> gp_nominasdepar1<br>
        * <b>Query field:</b> xusuario<br>
        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
        * <b>Input Label:</b> Generado por<br>
        * <br>Generado por<br> */
        xusuario,
        /** <b>Description:</b> Valor<br>
        * <b>Segment name:</b> gp_pagonomina<br>
        * <b>Query field:</b> xvalor<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Valor<br>
        * <br>Valor<br> */
        xvalor,
        /** <b>Description:</b> Valores descuentos<br>
        * <b>Segment name:</b> gp_rolesemple<br>
        * <b>Query field:</b> xvalor_de<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Valores descuentos<br>
        * <br>Valores descuentos<br> */
        xvalor_de,
        /** <b>Description:</b> Valores ingresos<br>
        * <b>Segment name:</b> gp_rolesemple<br>
        * <b>Query field:</b> xvalor_in<br>
        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
        * <b>Input Label:</b> Valores ingresos<br>
        * <br>Valores ingresos<br> */
        xvalor_in
    }

    public enum CONTAINERS {
        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
        _K_FIXED,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
        * <b>Caption:</b> N�minas<br> */
        card1,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
        * <b>Caption:</b> Valores pagados en n�mina<br> */
        card2,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
        * <b>Caption:</b> Novedades de asistencia<br> */
        card3,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
        * <b>Caption:</b> Roles de pago<br> */
        card4,
        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
        * <b>Caption:</b> Departamentos<br> */
        collap1,
        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
        * <b>Caption:</b> Valores pagados en n�mina<br> */
        collap2,
        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
        * <b>Caption:</b> Novedades de asistencia<br> */
        collap3,
        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
        * <b>Caption:</b> Roles de pago<br> */
        collap4,
        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
        * <b>Caption:</b> Opciones<br> */
        collap5,
        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
        * <b>Caption:</b> Valores pagados en n�mina<br> */
        gp_nominasdepar1,
        /** <b>Window class:</b> FMContainer.CONTAINER_GRID<br> */
        gr_asistencias,
        /** <b>Window class:</b> FMContainer.CONTAINER_GRID<br> */
        gr_pagonomina,
        /** <b>Window class:</b> FMContainer.CONTAINER_GRID<br> */
        gr_rolesemple,
        /** <b>Window class:</b> FMContainer.CONTAINER_CARDINDEX<br> */
        K__MAINCARD
    }

    public enum VIEWS {
        /** <b>Caption:</b> Elimina valores generados<br>
        * <b>View Type:</b> FMView.VIEW_BUTTON<br> */
        btn_borravalores,
        /** <b>Caption:</b> C�digo a�o<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>C�digo a�o<br> */
        xanio_id,
        /** <b>Caption:</b> C�digo departamento<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>C�digo departamento<br> */
        xdepartamento_id,
        /** <b>Caption:</b> C�digo del empleado<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>C�digo del empleado<br> */
        xempleado_id,
        /** <b>Caption:</b> C�digo del Empleado<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>C�digo del Empleado<br> */
        xempleado_id_as,
        /** <b>Caption:</b> C�digo del empleado<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>C�digo del empleado<br> */
        xempleado_id_ro,
        /** <b>Caption:</b> C�digo empresa<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>C�digo empresa<br> */
        xempresa_id,
        /** <b>Caption:</b> C�digo entorno<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>C�digo entorno<br> */
        xentorno_id,
        /** <b>Caption:</b> Generado<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Generado<br> */
        xfecha,
        /** <b>Caption:</b> Horas<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Horas<br> */
        xhoras,
        /** <b>Caption:</b> C�digo organizaci�n<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>C�digo organizaci�n<br> */
        xorganizacion_id,
        /** <b>Caption:</b> C�digo periodo<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>C�digo periodo<br> */
        xperiodo_id,
        /** <b>Caption:</b> Rubros descuentos<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Las iniciales de los c�digo deberan ser : Rubro (R) , Variable (V), Constante (C)<br> */
        xrubro_de,
        /** <b>Caption:</b> C�digo rubro<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>C�digo rubro<br> */
        xrubro_id,
        /** <b>Caption:</b> Rubros ingresos<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Las iniciales de los c�digo deberan ser : Rubro (R) , Variable (V), Constante (C)<br> */
        xrubro_in,
        /** <b>Caption:</b> Tipo de asistencia<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Tipo de asistencia<br> */
        xtipoasistencia_id,
        /** <b>Caption:</b> Generado por<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Generado por<br> */
        xusuario,
        /** <b>Caption:</b> Valor<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Valor<br> */
        xvalor,
        /** <b>Caption:</b> Valores descuentos<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Valores descuentos<br> */
        xvalor_de,
        /** <b>Caption:</b> Valores ingresos<br>
        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
        * <br>Valores ingresos<br> */
        xvalor_in
    }


	public gp_nominasdepar1_fm(Session session) {
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
		if("btn_borravalores".equals(fmEvent.getSourceName())){
			String sEmpresa=getItem(ITEMS.xempresa_id).getValue();
			String sEntorno=getItem(ITEMS.xentorno_id).getValue();
			String sOrganizacion=getItem(ITEMS.xorganizacion_id).getValue();
			Integer iAnio=getItem(ITEMS.xanio_id).getValueInteger();
			Integer iPeriodo=getItem(ITEMS.xperiodo_id).getValueInteger();
			String sDepartamento=getItem(ITEMS.xdepartamento_id).getValue();
			String sTipoorigen="R";
			String sEmpleado="";
			
			if(fmObject.showMessageText("Este proceso eliminara los datos permanentemente , desea realizarlo.?" , "Aceptar/Cancelar")==1){
				funciones = new Genera_Nominas();
				funciones.EliminaRoles(session, sEmpresa, sEntorno, sOrganizacion, iAnio, iPeriodo, sDepartamento, sTipoorigen, sEmpleado);
			}
		}
	}
 
}
