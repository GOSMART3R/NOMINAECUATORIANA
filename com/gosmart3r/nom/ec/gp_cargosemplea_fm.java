package com.gosmart3r.nom.ec;

import com.unit4.karat.form.FMDefaultEvents;
import com.unit4.karat.session.Session;

 


import java.util.Date;

import com.unit4.karat.base.OTException;
import com.unit4.karat.da.DA;
import com.unit4.karat.da.DAConnection;
import com.unit4.karat.da.DAConnectionSource;
import com.unit4.karat.da.DAResultSet;
import com.unit4.karat.da.DAUtils;
import com.unit4.karat.form.FMEvent;

 

public class gp_cargosemplea_fm extends FMDefaultEvents {

	
	   public enum CONTAINERS {
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br> */
	        _K_FIXED,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARD<br>
	        * <b>Caption:</b> Empleado / cargo<br> */
	        card1,
	        /** <b>Window class:</b> FMContainer.CONTAINER_FRAME<br>
	        * <b>Caption:</b> Cargo<br> */
	        collap1,
	        /** <b>Window class:</b> FMContainer.CONTAINER_WINDOW<br>
	        * <b>Caption:</b> Liquidación de haberes<br> */
	        gp_cargosemplea,
	        /** <b>Window class:</b> FMContainer.CONTAINER_CARDINDEX<br> */
	        K__MAINCARD
	    }

	    public enum VIEWS {
	        /** <b>Caption:</b> Imprimir liquidación<br>
	        * <b>View Type:</b> FMView.VIEW_BUTTON<br> */
	        btn_imprimir,
	        /** <b>Caption:</b> Liquidar empleado<br>
	        * <b>View Type:</b> FMView.VIEW_BUTTON<br> */
	        btn_liquidar,
	        /** <b>Caption:</b> Reversar liquidación<br>
	        * <b>View Type:</b> FMView.VIEW_BUTTON<br> */
	        btn_reversar,
	        /** <b>Caption:</b> Cargo<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Cargo<br> */
	        xcargo_id,
	        /** <b>Caption:</b> Centros de costo<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Centros de costo<br> */
	        xcentrocosto_id,
	        /** <b>Caption:</b> Contador liquidaciones<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Contador liquidaciones<br> */
	        xcontadorliq,
	        /** <b>Caption:</b> Contrato<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Contrato<br> */
	        xcontrato_id,
	        /** <b>Caption:</b> Departamento<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Departamento<br> */
	        xdepartamento_id,
	        /** <b>Caption:</b> Código del Empleado<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Código del Empleado<br> */
	        xempleado_id,
	        /** <b>Caption:</b> Código empresa<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Código empresa<br> */
	        xempresa_id,
	        /** <b>Caption:</b> Código entorno<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Código entorno<br> */
	        xentorno_id,
	        /** <b>Caption:</b> Estado nomina<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Estado nomina<br> */
	        xestadocargo,
	        /** <b>Caption:</b> Fecha de ingreso a la empresa<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Fecha de ingreso a la empresa<br> */
	        xfechaempresa,
	        /** <b>Caption:</b> Fecha final<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Fecha final<br> */
	        xfechafinal,
	        /** <b>Caption:</b> Fecha inicial<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Fecha inicial<br> */
	        xfechainicial,
	        /** <b>Caption:</b> Generado el<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Generado el<br> */
	        xfechaliq,
	        /** <b>Caption:</b> Reversado el<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Reversado el<br> */
	        xfecharev,
	        /** <b>Caption:</b> Motivo<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Motivo<br> */
	        xmotivo,
	        /** <b>Caption:</b> Motivo de liquidación<br>
	        * <b>View Type:</b> FMView.VIEW_DROPCOMBO<br>
	        * <br>Motivo de liquidación<br> */
	        xmotivoliqui,
	        /** <b>Caption:</b> Código organización<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Código organización<br> */
	        xorganizacion_id,
	        /** <b>Caption:</b> Sección<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Sección<br> */
	        xseccion_id,
	        /** <b>Caption:</b> Sueldo<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Sueldo<br> */
	        xsueldo,
	        /** <b>Caption:</b> Tipos de fondos de reserva<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Tipos de fondos de reserva<br> */
	        xtiposfondos,
	        /** <b>Caption:</b> Liquidado por<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Liquidado por<br> */
	        xusuarioliq,
	        /** <b>Caption:</b> Reversado por<br>
	        * <b>View Type:</b> FMView.VIEW_TEXTBOX<br>
	        * <br>Reversado por<br> */
	        xusuariorev
	    }

	    public enum SEGMENTS {
	        /** <b>Base query:</b> gp_cargosemplea<br> */
	        gp_cargosemplea
	    }

	    public enum ITEMS {
	        /** <b>Description:</b> Acumula décimo cuarto<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xadecimoc<br>
	        * <b>Data Type:</b> DA.DA_DT_BOOLEAN<br>
	        * <b>Input Label:</b> Acumula décimo cuarto<br>
	        * <br>Acumula décimo cuarto<br> */
	        xadecimoc,
	        /** <b>Description:</b> Acumula décimo tercero<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xadecimot<br>
	        * <b>Data Type:</b> DA.DA_DT_BOOLEAN<br>
	        * <b>Input Label:</b> Acumula décimo tercero<br>
	        * <br>Acumula décimo tercero<br> */
	        xadecimot,
	        /** <b>Description:</b> Acumula fondos de reserva<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xafondos<br>
	        * <b>Data Type:</b> DA.DA_DT_BOOLEAN<br>
	        * <b>Input Label:</b> Acumula fondos de reserva<br>
	        * <br>Acumula fondos de reserva<br> */
	        xafondos,
	        /** <b>Description:</b> Cantón<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xcantonc_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Cantón<br>
	        * <br>Cantón<br> */
	        xcantonc_id,
	        /** <b>Description:</b> Cargo<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xcargo_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Cargo<br>
	        * <br>Cargo<br> */
	        xcargo_id,
	        /** <b>Description:</b> Centros de costo<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xcentrocosto_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Centros de costo<br>
	        * <br>Centros de costo<br> */
	        xcentrocosto_id,
	        /** <b>Description:</b> Ciudad<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xciudadc_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Ciudad<br>
	        * <br>Ciudad<br> */
	        xciudadc_id,
	        /** <b>Description:</b> Contador liquidaciones<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xcontadorliq<br>
	        * <b>Data Type:</b> DA.DA_DT_LONG<br>
	        * <b>Input Label:</b> Contador liquidaciones<br>
	        * <br>Contador liquidaciones<br> */
	        xcontadorliq,
	        /** <b>Description:</b> Contrato<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xcontrato_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Contrato<br>
	        * <br>Contrato<br> */
	        xcontrato_id,
	        /** <b>Description:</b> Departamento<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xdepartamento_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Departamento<br>
	        * <br>Departamento<br> */
	        xdepartamento_id,
	        /** <b>Description:</b> Código del Empleado<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xempleado_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código del Empleado<br>
	        * <br>Código del Empleado<br> */
	        xempleado_id,
	        /** <b>Description:</b> Código empresa<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xempresa_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código empresa<br>
	        * <br>Código empresa<br> */
	        xempresa_id,
	        /** <b>Description:</b> Código entorno<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xentorno_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código entorno<br>
	        * <br>Código entorno<br> */
	        xentorno_id,
	        /** <b>Description:</b> Estado nomina<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xestadocargo<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Estado nomina<br>
	        * <br>Estado nomina<br> */
	        xestadocargo,
	        /** <b>Description:</b> Fecha de ingreso a la empresa<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha de ingreso a la empresa<br>
	        * <br>Fecha de ingreso a la empresa<br> */
	        xfechaempresa,
	        /** <b>Description:</b> Fecha final<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xfechafinal<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha final<br>
	        * <br>Fecha final<br> */
	        xfechafinal,
	        /** <b>Description:</b> Fecha inicial<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xfechainicial<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Fecha inicial<br>
	        * <br>Fecha inicial<br> */
	        xfechainicial,
	        /** <b>Description:</b> Generado el<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xfechaliq<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Generado el<br>
	        * <br>Generado el<br> */
	        xfechaliq,
	        /** <b>Description:</b> Reversado el<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xfecharev<br>
	        * <b>Data Type:</b> DA.DA_DT_DATE<br>
	        * <b>Input Label:</b> Reversado el<br>
	        * <br>Reversado el<br> */
	        xfecharev,
	        /** <b>Description:</b> Motivo<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xmotivo<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Motivo<br>
	        * <br>Motivo<br> */
	        xmotivo,
	        /** <b>Description:</b> Motivo de liquidación<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xmotivoliqui<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Motivo de liquidación<br>
	        * <br>Motivo de liquidación<br> */
	        xmotivoliqui,
	        /** <b>Description:</b> Código organización<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xorganizacion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Código organización<br>
	        * <br>Código organización<br> */
	        xorganizacion_id,
	        /** <b>Description:</b> País<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xpaisc_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> País<br>
	        * <br>País<br> */
	        xpaisc_id,
	        /** <b>Description:</b> Parroquia<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xparroquiac_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Parroquia<br>
	        * <br>Parroquia<br> */
	        xparroquiac_id,
	        /** <b>Description:</b> Provincia<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xprovinciac_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Provincia<br>
	        * <br>Provincia<br> */
	        xprovinciac_id,
	        /** <b>Description:</b> Sección<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xseccion_id<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Sección<br>
	        * <br>Sección<br> */
	        xseccion_id,
	        /** <b>Description:</b> Sueldo<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xsueldo<br>
	        * <b>Data Type:</b> DA.DA_DT_CURRENCY<br>
	        * <b>Input Label:</b> Sueldo<br>
	        * <br>Sueldo<br> */
	        xsueldo,
	        /** <b>Description:</b> Tipos de fondos de reserva<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xtiposfondos<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Tipos de fondos de reserva<br>
	        * <br>Tipos de fondos de reserva<br> */
	        xtiposfondos,
	        /** <b>Description:</b> Liquidado por<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xusuarioliq<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Liquidado por<br>
	        * <br>Liquidado por<br> */
	        xusuarioliq,
	        /** <b>Description:</b> Reversado por<br>
	        * <b>Segment name:</b> gp_cargosemplea<br>
	        * <b>Query field:</b> xusuariorev<br>
	        * <b>Data Type:</b> DA.DA_DT_TEXT<br>
	        * <b>Input Label:</b> Reversado por<br>
	        * <br>Reversado por<br> */
	        xusuariorev
	    }

	    

	    

	    

	    

	    private DAConnectionSource connSource; // Acceso a sdic
		private DAConnection connData; // Acceso a datos
		public Genera_Asistencia_Nomina Asistencias;
		public Genera_Nominas Liquidar;

	
	public gp_cargosemplea_fm(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}
	

/* (non-Javadoc)
 * @see com.unit4.karat.form.FMDefaultEvents#formPreDeleteIn(com.unit4.karat.form.FMEvent)
 */
@Override
public void formPreDeleteIn(FMEvent fmEvent) throws OTException {
	// TODO Auto-generated method stub
	super.formPreDeleteIn(fmEvent);
	fmEvent.setRecall(true);
	fmEvent.cancel();//CANCELAMOS EL EVENTO DE GRABAR 
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
	String sXcargo_id,sXcentrocosto_id,sXcontrato_id,sXdepartamento_id,sXempleado_id,sXempresa_id,sXentorno_id,sXestadocargo,sXmotivo,sXmotivoliqui,sXorganizacion_id,sXseccion_id,sXtiposfondos,sXempleado_id_nom;
	Date dXfechaempresa,dXfechafinal,dXfechainicial,dFechagenera, dXhasta;
	String sOrigen="L", sSql_periodo="", sUsuariogenera="";
	Double dXsueldo;
	Integer iAnio=0,iPeriodo=0, iXcontadorliq=0;
	DAResultSet rs_periodo = null;
	
	this.connSource = session.getConnectionSource();
	try {
		this.connData = session.getConnectionData();
	} catch (OTException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	sUsuariogenera=session.getUserInfo().getUserName();	
	
	dFechagenera=null;
	try {
		dFechagenera= session.getServerNow();
	} catch (OTException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	
	if(true){//BOTON PARA EMPEZAR LAS LIQUIDACIONES
		sXcargo_id=getItem(ITEMS.xcargo_id).getValue();
		sXcentrocosto_id=getItem(ITEMS.xcentrocosto_id).getValue();
		sXcontrato_id=getItem(ITEMS.xcontrato_id).getValue();
		sXdepartamento_id=getItem(ITEMS.xdepartamento_id).getValue();
		sXempleado_id=getItem(ITEMS.xempleado_id).getValue();
		sXempresa_id=getItem(ITEMS.xempresa_id).getValue();
		sXentorno_id=getItem(ITEMS.xentorno_id).getValue();
		sXestadocargo=getItem(ITEMS.xestadocargo).getValue();
		sXmotivo=getItem(ITEMS.xmotivo).getValue();
		sXmotivoliqui=getItem(ITEMS.xmotivoliqui).getValue();
		sXorganizacion_id=getItem(ITEMS.xorganizacion_id).getValue();
		sXseccion_id=getItem(ITEMS.xseccion_id).getValue();
		sXtiposfondos=getItem(ITEMS.xtiposfondos).getValue();

		dXfechaempresa=getItem(ITEMS.xfechaempresa).getValueDate();
		dXfechafinal=getItem(ITEMS.xfechafinal).getValueDate();
		dXfechainicial=getItem(ITEMS.xfechainicial).getValueDate();
		dXsueldo=getItem(ITEMS.xsueldo).getValueDouble();
		
		sXempleado_id_nom=getItem(ITEMS.xempleado_id).getDescription();
		
		iXcontadorliq=getItem(ITEMS.xcontadorliq).getValueInteger();
		
		if(iXcontadorliq==null){iXcontadorliq=0;}
		
		//COMO NO PODEMOS IDENTIFICAR EL AÑO Y PERIODO DIRECTO DEBEMOS REALIZAR EL SELECT
		sSql_periodo=" ";
		sSql_periodo+=" SELECT   ";
		sSql_periodo+=" xanio_id,xperiodo_id  , xhasta ";
		sSql_periodo+=" FROM  "+connSource.translateTable("gp_periodos");
		sSql_periodo+=" WHERE xempresa_id="+ DAUtils.formatValue(sXempresa_id, DA.DA_DT_TEXT);
		sSql_periodo+=" AND xorganizacion_id="+ DAUtils.formatValue(sXorganizacion_id, DA.DA_DT_TEXT);
		sSql_periodo+=" AND xentorno_id="+ DAUtils.formatValue(sXentorno_id, DA.DA_DT_TEXT);
		sSql_periodo+=" AND "+ DAUtils.formatValue(dXfechafinal, DA.DA_DT_TEXT)+" >=xdesde ";
		sSql_periodo+=" AND "+ DAUtils.formatValue(dXfechafinal, DA.DA_DT_TEXT)+"<=xhasta ";
		rs_periodo=connData.openSQL(sSql_periodo);
		iAnio=0;
		iPeriodo=0;
		dXhasta=null;
		if(rs_periodo.moveNext()){
			iAnio=rs_periodo.getInt("xanio_id");
			iPeriodo=rs_periodo.getInt("xperiodo_id");
			dXhasta=rs_periodo.getDate("xhasta");
			if(iAnio==null){iAnio=0;}
			if(iPeriodo==null){iPeriodo=0;}
		}
		rs_periodo.close();
		
		if(iXcontadorliq==0 && "btn_liquidar".equals(fmEvent.getSourceName()) && iAnio>0 && iPeriodo>0 && fmObject.showMessageText("Se procedera a realizar la liquidación del empleado : " + sXempleado_id_nom , "Aceptar/Cancelar")==1){
			
			//IMPLEMENTADO CONTADOR DE LIQUIDACIONES , PARA SABER QUE REGISTRO SE VA HA LIQUIDAR
			//CONTROL INTERNO PARA IDENTIFICAR JUNTO CON LA FECHA DE LIQUIDACION LA LIQUIDACION
			int iContador = this.session.getConnectionSource().getCounter().getCounterValue("GP_LIQUIDACIONES", 2, 1);
			
			
			getItem(ITEMS.xcontadorliq).setValue(iContador);
			getItem(ITEMS.xusuarioliq).setValue(sUsuariogenera);
			getItem(ITEMS.xfechaliq).setValue(dFechagenera);
			
			System.out.println ("<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>><<<"+iContador+sUsuariogenera+dFechagenera);
			
			Liquidar=new Genera_Nominas();
			Liquidar.GeneraRol(session, sXempresa_id, sXentorno_id, sXorganizacion_id, iAnio, iPeriodo, sXdepartamento_id, sXempleado_id, sOrigen, dXfechainicial, dXfechafinal, sXcargo_id, sXmotivoliqui,iContador,dXhasta);
			
			boObject.save();
			fmObject.setPendingSave(false);
			fmObject.readRefresh();
			
			//Asistencias=new EAG3_Genera_Asistencia_Nomina();
			//Asistencias.GeneraAsistencia(session, sXentorno_id, sXorganizacion_id, sXempresa_id, sXdepartamento_id, sXempleado_id, iAnio, iPeriodo, sOrigen, dXfechaempresa, sXcargo_id, dXfechafinal, sXcontrato_id, sXtiposfondos);
		}
		
		if(iXcontadorliq>0 && "btn_reversar".equals(fmEvent.getSourceName()) && iAnio>0 && iPeriodo>0 && fmObject.showMessageText("Se procedera a reversar la liquidación del empleado : " + sXempleado_id_nom , "Aceptar/Cancelar")==1){
			
			//IMPLEMENTADO CONTADOR DE LIQUIDACIONES , PARA SABER QUE REGISTRO SE VA HA LIQUIDAR
			//CONTROL INTERNO PARA IDENTIFICAR JUNTO CON LA FECHA DE LIQUIDACION LA LIQUIDACION
			int iContador=0;
			
			iContador=getItem(ITEMS.xcontadorliq).getValueInteger();
			
			Liquidar=new Genera_Nominas();
			Liquidar.Reversaliquidacion(session, sXempresa_id, sXentorno_id, sXorganizacion_id, iAnio, iPeriodo, sXdepartamento_id, sXempleado_id, sOrigen, dXfechainicial, dXfechafinal, sXcargo_id, sXmotivoliqui,iContador);
			
			//boObject.save();
			fmObject.setPendingSave(false);
			fmObject.readRefresh();
			
			//Asistencias=new EAG3_Genera_Asistencia_Nomina();
			//Asistencias.GeneraAsistencia(session, sXentorno_id, sXorganizacion_id, sXempresa_id, sXdepartamento_id, sXempleado_id, iAnio, iPeriodo, sOrigen, dXfechaempresa, sXcargo_id, dXfechafinal, sXcontrato_id, sXtiposfondos);
		}
		
		
	}
}


}
